/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.change.tracking.internal;

import com.liferay.change.tracking.CTEngineManager;
import com.liferay.change.tracking.CTManager;
import com.liferay.change.tracking.exception.CTEntryException;
import com.liferay.change.tracking.exception.CTException;
import com.liferay.change.tracking.exception.DuplicateCTEntryException;
import com.liferay.change.tracking.internal.util.ChangeTrackingThreadLocal;
import com.liferay.change.tracking.model.CTCollection;
import com.liferay.change.tracking.model.CTEntry;
import com.liferay.change.tracking.model.CTEntryAggregate;
import com.liferay.change.tracking.service.CTCollectionLocalService;
import com.liferay.change.tracking.service.CTEntryAggregateLocalService;
import com.liferay.change.tracking.service.CTEntryLocalService;
import com.liferay.change.tracking.util.comparator.CTEntryCreateDateComparator;
import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniel Kocsis
 */
@Component(immediate = true, service = CTManager.class)
public class CTManagerImpl implements CTManager {

	@Override
	public Optional<CTEntryAggregate> addRelatedCTEntry(
		long userId, CTEntry ownerCTEntry, CTEntry relatedCTEntry) {

		return addRelatedCTEntry(userId, ownerCTEntry, relatedCTEntry, false);
	}

	@Override
	public Optional<CTEntryAggregate> addRelatedCTEntry(
		long userId, CTEntry ownerCTEntry, CTEntry relatedCTEntry,
		boolean force) {

		if ((ownerCTEntry == null) || (relatedCTEntry == null)) {
			return Optional.empty();
		}

		Optional<CTCollection> activeCTCollectionOptional =
			getActiveCTCollectionOptional(userId);

		if (!activeCTCollectionOptional.isPresent()) {
			return Optional.empty();
		}

		long activeCTCollectionId = activeCTCollectionOptional.map(
			CTCollection::getCtCollectionId
		).orElse(
			0L
		);

		try {
			CTEntryAggregate ctEntryAggregate = TransactionInvokerUtil.invoke(
				_transactionConfig,
				() -> _addCTEntryAggregate(
					userId, activeCTCollectionId, ownerCTEntry, relatedCTEntry,
					force));

			return Optional.of(ctEntryAggregate);
		}
		catch (Throwable t) {
			_log.error("Unable to create change tracking entry aggregate", t);

			return Optional.empty();
		}
	}

	@Override
	public Optional<CTEntryAggregate> addRelatedCTEntry(
		long userId, long ownerCTEntryId, long relatedCTEntryId) {

		CTEntry ownerCTEntry = _ctEntryLocalService.fetchCTEntry(
			ownerCTEntryId);

		CTEntry relatedCTEntry = _ctEntryLocalService.fetchCTEntry(
			relatedCTEntryId);

		return addRelatedCTEntry(userId, ownerCTEntry, relatedCTEntry, false);
	}

	@Override
	public <T> T executeModelUpdate(
			UnsafeSupplier<T, PortalException> modelUpdateSupplier)
		throws PortalException {

		boolean resetFlag = false;

		try {
			if (!ChangeTrackingThreadLocal.isModelUpdateInProgress()) {
				resetFlag = true;

				ChangeTrackingThreadLocal.setModelUpdateInProgress(true);
			}

			return modelUpdateSupplier.get();
		}
		finally {
			if (resetFlag) {
				ChangeTrackingThreadLocal.setModelUpdateInProgress(false);
			}
		}
	}

	@Override
	public Optional<CTEntry> getActiveCTCollectionCTEntryOptional(
		long userId, long classNameId, long classPK) {

		Optional<CTCollection> ctCollectionOptional =
			getActiveCTCollectionOptional(userId);

		long ctCollectionId = ctCollectionOptional.map(
			CTCollection::getCtCollectionId
		).orElse(
			0L
		);

		long companyId = _getCompanyId(userId);

		CTEntry ctEntry = _getCTentry(
			companyId, ctCollectionId, classNameId, classPK);

		return Optional.ofNullable(ctEntry);
	}

	@Override
	public Optional<CTCollection> getActiveCTCollectionOptional(long userId) {
		long companyId = _getCompanyId(userId);

		if (companyId <= 0) {
			return Optional.empty();
		}

		if (!_ctEngineManager.isChangeTrackingEnabled(companyId)) {
			return Optional.empty();
		}

		long recentCTCollectionId = _ctEngineManager.getRecentCTCollectionId(
			userId);

		if (recentCTCollectionId == 0L) {
			Optional<CTCollection> productionCTCollectionOptional =
				_ctEngineManager.getProductionCTCollectionOptional(companyId);

			recentCTCollectionId = productionCTCollectionOptional.map(
				CTCollection::getCtCollectionId
			).orElse(
				0L
			);

			_ctEngineManager.checkoutCTCollection(userId, recentCTCollectionId);
		}

		return _ctEngineManager.getCTCollectionOptional(recentCTCollectionId);
	}

	@Override
	public Optional<CTEntryAggregate> getCTEntryAggregateOptional(
		CTEntry ctEntry, CTCollection ctCollection) {

		if ((ctEntry == null) || !ctEntry.hasCTEntryAggregate()) {
			return Optional.empty();
		}

		List<CTEntryAggregate> ctEntryCTEntryAggregates =
			ctEntry.getCTEntryAggregates();

		Stream<CTEntryAggregate> ctEntryAggregateStream =
			ctEntryCTEntryAggregates.parallelStream();

		return ctEntryAggregateStream.filter(
			ctEntryAggregate -> {
				List<CTCollection> ctEntryAggregateCTCollections =
					_ctCollectionLocalService.getCTEntryAggregateCTCollections(
						ctEntryAggregate.getCtEntryAggregateId());

				return ctEntryAggregateCTCollections.contains(ctCollection);
			}
		).findAny();
	}

	@Override
	public Optional<CTEntry> getLatestModelChangeCTEntryOptional(
		long userId, long resourcePrimKey) {

		long companyId = _getCompanyId(userId);

		if (companyId <= 0) {
			return Optional.empty();
		}

		if (!_ctEngineManager.isChangeTrackingEnabled(companyId)) {
			return Optional.empty();
		}

		QueryDefinition<CTEntry> queryDefinition = new QueryDefinition<>();

		queryDefinition.setEnd(1);
		queryDefinition.setOrderByComparator(new CTEntryCreateDateComparator());
		queryDefinition.setStart(0);

		List<CTEntry> ctEntries = getModelChangeCTEntries(
			userId, resourcePrimKey, queryDefinition);

		if (ListUtil.isEmpty(ctEntries)) {
			return Optional.empty();
		}

		return Optional.of(ctEntries.get(0));
	}

	@Override
	public List<CTEntry> getModelChangeCTEntries(
		long userId, long resourcePrimKey) {

		QueryDefinition<CTEntry> queryDefinition = new QueryDefinition<>();

		queryDefinition.setOrderByComparator(
			new CTEntryCreateDateComparator(true));

		return getModelChangeCTEntries(
			userId, resourcePrimKey, queryDefinition);
	}

	@Override
	public List<CTEntry> getModelChangeCTEntries(
		long userId, long resourcePrimKey,
		QueryDefinition<CTEntry> queryDefinition) {

		Optional<CTCollection> ctCollectionOptional =
			getActiveCTCollectionOptional(userId);

		long ctCollectionId = ctCollectionOptional.map(
			CTCollection::getCtCollectionId
		).orElse(
			0L
		);

		return _ctEntryLocalService.fetchCTEntries(
			ctCollectionId, resourcePrimKey, queryDefinition);
	}

	@Override
	public Optional<CTEntryAggregate> getModelChangeCTEntryAggregateOptional(
		long userId, long classNameId, long classPK) {

		Optional<CTEntry> ctEntryOptional = getModelChangeCTEntryOptional(
			userId, classNameId, classPK);

		if (!ctEntryOptional.isPresent()) {
			return Optional.empty();
		}

		Optional<CTCollection> ctCollectionOptional =
			getActiveCTCollectionOptional(userId);

		long ctCollectionId = ctCollectionOptional.map(
			CTCollection::getCtCollectionId
		).orElse(
			0L
		);

		long ctEntryId = ctEntryOptional.map(
			CTEntry::getCtEntryId
		).get();

		CTEntryAggregate ctEntryAggregate =
			_ctEntryAggregateLocalService.fetchLatestCTEntryAggregate(
				ctCollectionId, ctEntryId);

		if (ctEntryAggregate != null) {
			return Optional.of(ctEntryAggregate);
		}

		ctCollectionOptional =
			_ctEngineManager.getProductionCTCollectionOptional(userId);

		ctCollectionId = ctCollectionOptional.map(
			CTCollection::getCtCollectionId
		).orElse(
			0L
		);

		ctEntryAggregate =
			_ctEntryAggregateLocalService.fetchLatestCTEntryAggregate(
				ctCollectionId, ctEntryId);

		return Optional.ofNullable(ctEntryAggregate);
	}

	@Override
	public Optional<CTEntry> getModelChangeCTEntryOptional(
		long userId, long classNameId, long classPK) {

		Optional<CTEntry> ctEntryOptional =
			getActiveCTCollectionCTEntryOptional(userId, classNameId, classPK);

		if (ctEntryOptional.isPresent()) {
			return ctEntryOptional;
		}

		return getProductionCTCollectionCTEntryOptional(
			userId, classNameId, classPK);
	}

	@Override
	public Optional<CTEntry> getProductionCTCollectionCTEntryOptional(
		long userId, long classNameId, long classPK) {

		long companyId = _getCompanyId(userId);

		if (companyId <= 0) {
			return Optional.empty();
		}

		Optional<CTCollection> ctCollectionOptional =
			_ctEngineManager.getProductionCTCollectionOptional(companyId);

		long ctCollectionId = ctCollectionOptional.map(
			CTCollection::getCtCollectionId
		).orElse(
			0L
		);

		CTEntry ctEntry = _getCTentry(
			companyId, ctCollectionId, classNameId, classPK);

		return Optional.ofNullable(ctEntry);
	}

	@Override
	public List<CTEntry> getRelatedCTEntries(
		CTEntry ctEntry, CTCollection ctCollection) {

		Optional<CTEntryAggregate> ctEntryAggregateOptional =
			getCTEntryAggregateOptional(ctEntry, ctCollection);

		List<CTEntry> ctEntries = ctEntryAggregateOptional.map(
			CTEntryAggregate::getRelatedCTEntries
		).orElse(
			new ArrayList<>()
		);

		ctEntries.removeIf(ctEntry::equals);

		return ctEntries;
	}

	@Override
	public boolean isModelUpdateInProgress() {
		return ChangeTrackingThreadLocal.isModelUpdateInProgress();
	}

	@Override
	public Optional<CTEntry> registerModelChange(
			long userId, long classNameId, long classPK, long resourcePrimKey,
			int changeType)
		throws CTException {

		return registerModelChange(
			userId, classNameId, classPK, resourcePrimKey, changeType, false);
	}

	@Override
	public Optional<CTEntry> registerModelChange(
			long userId, long classNameId, long classPK, long resourcePrimKey,
			int changeType, boolean force)
		throws CTException {

		long companyId = _getCompanyId(userId);

		if (!_ctEngineManager.isChangeTrackingEnabled(companyId) ||
			!_ctEngineManager.isChangeTrackingSupported(
				companyId, classNameId)) {

			return Optional.empty();
		}

		Optional<CTCollection> ctCollectionOptional =
			getActiveCTCollectionOptional(userId);

		if (!ctCollectionOptional.isPresent()) {
			return Optional.empty();
		}

		CTCollection ctCollection = ctCollectionOptional.get();

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAttribute("force", force);

			Optional<CTEntry> previousModelChangeCTEntryOptional =
				getLatestModelChangeCTEntryOptional(userId, resourcePrimKey);

			// Creating a new change tracking entry

			CTEntry ctEntry = _ctEntryLocalService.addCTEntry(
				userId, classNameId, classPK, resourcePrimKey, changeType,
				ctCollection.getCtCollectionId(), serviceContext);

			// Updating existing related change tracking entry aggregate

			previousModelChangeCTEntryOptional.flatMap(
				latestModelChangeCTEntry -> getCTEntryAggregateOptional(
					latestModelChangeCTEntry, ctCollection)
			).ifPresent(
				ctEntryAggregate -> _updateCTEntryInCTEntryAggregate(
					ctEntryAggregate, ctEntry, force)
			);

			return Optional.of(ctEntry);
		}
		catch (DuplicateCTEntryException dctee) {
			StringBundler sb = new StringBundler(8);

			sb.append("Duplicate CTEntry with class name ID ");
			sb.append(classNameId);
			sb.append(", class PK ");
			sb.append(classPK);
			sb.append(", and resource primary key ");
			sb.append(resourcePrimKey);
			sb.append(" in change tracking collection ");
			sb.append(ctCollection.getCtCollectionId());

			throw new CTEntryException(
				0L, companyId, userId, classNameId, classPK, resourcePrimKey,
				ctCollection.getCtCollectionId(), sb.toString(), dctee);
		}
		catch (PortalException pe) {
			StringBundler sb = new StringBundler(8);

			sb.append("Unable to register model change  with class name ID ");
			sb.append(classNameId);
			sb.append(", class PK ");
			sb.append(classPK);
			sb.append(", and resource primary key ");
			sb.append(resourcePrimKey);
			sb.append(" in change tracking collection ");
			sb.append(ctCollection.getCtCollectionId());

			throw new CTException(companyId, sb.toString(), pe);
		}
	}

	@Override
	public Optional<CTEntry> unregisterModelChange(
		long userId, long classNameId, long classPK) {

		long companyId = _getCompanyId(userId);

		if (companyId <= 0) {
			return Optional.empty();
		}

		if (!_ctEngineManager.isChangeTrackingEnabled(companyId) ||
			!_ctEngineManager.isChangeTrackingSupported(
				companyId, classNameId)) {

			return Optional.empty();
		}

		Optional<CTEntry> ctEntryOptional = getModelChangeCTEntryOptional(
			userId, classNameId, classPK);

		return ctEntryOptional.map(
			ctEntry -> _ctEntryLocalService.deleteCTEntry(ctEntry));
	}

	private CTEntryAggregate _addCTEntryAggregate(
			long userId, long activeCTCollectionId, CTEntry ownerCTEntry,
			CTEntry relatedCTEntry, boolean force)
		throws PortalException {

		CTEntryAggregate ctEntryAggregate =
			_ctEntryAggregateLocalService.fetchLatestCTEntryAggregate(
				activeCTCollectionId, ownerCTEntry.getCtEntryId());

		if (ctEntryAggregate == null) {
			ctEntryAggregate =
				_ctEntryAggregateLocalService.addCTEntryAggregate(
					userId, activeCTCollectionId, ownerCTEntry.getCtEntryId(),
					new ServiceContext());

			_ctEntryAggregateLocalService.addCTEntry(
				ctEntryAggregate, relatedCTEntry);
		}
		else if (!_containsResource(
					ctEntryAggregate,
					relatedCTEntry.getModelResourcePrimKey())) {

			_ctEntryAggregateLocalService.addCTEntry(
				ctEntryAggregate, relatedCTEntry);
		}
		else {
			_updateCTEntryInCTEntryAggregate(
				ctEntryAggregate, relatedCTEntry, force);
		}

		return ctEntryAggregate;
	}

	private boolean _containsResource(
		CTEntryAggregate ctEntryAggregate, long resourcePrimKey) {

		List<CTEntry> relatedCTEntries = ctEntryAggregate.getRelatedCTEntries();

		Stream<CTEntry> relatedCTEntriesStream =
			relatedCTEntries.parallelStream();

		if (relatedCTEntriesStream.anyMatch(
				ctEntry ->
					ctEntry.getModelResourcePrimKey() == resourcePrimKey)) {

			return true;
		}

		return false;
	}

	private CTEntryAggregate _copyCTEntryAggregate(
			CTEntryAggregate ctEntryAggregate)
		throws PortalException {

		long userId = PrincipalThreadLocal.getUserId();

		Optional<CTCollection> activeCTCollectionOptional =
			getActiveCTCollectionOptional(userId);

		long activeCTCollectionId = activeCTCollectionOptional.map(
			CTCollection::getCtCollectionId
		).orElse(
			0L
		);

		CTEntryAggregate ctEntryAggregateCopy =
			_ctEntryAggregateLocalService.addCTEntryAggregate(
				userId, activeCTCollectionId,
				ctEntryAggregate.getOwnerCTEntryId(), new ServiceContext());

		_ctEntryLocalService.addCTEntryAggregateCTEntries(
			ctEntryAggregateCopy.getCtEntryAggregateId(),
			_ctEntryAggregateLocalService.getCTEntryPrimaryKeys(
				ctEntryAggregate.getCtEntryAggregateId()));

		return ctEntryAggregateCopy;
	}

	private long _getCompanyId(long userId) {
		long companyId = 0;

		User user = _userLocalService.fetchUser(userId);

		if (user == null) {
			companyId = CompanyThreadLocal.getCompanyId();
		}
		else {
			companyId = user.getCompanyId();
		}

		if (companyId <= 0) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get company ID");
			}
		}

		return companyId;
	}

	private CTEntry _getCTentry(
		long companyId, long ctCollectionId, long classNameId, long classPK) {

		if (!_ctEngineManager.isChangeTrackingEnabled(companyId) ||
			!_ctEngineManager.isChangeTrackingSupported(
				companyId, classNameId)) {

			return null;
		}

		return _ctEntryLocalService.fetchCTEntry(
			ctCollectionId, classNameId, classPK);
	}

	private void _updateCTEntryInCTEntryAggregate(
		CTEntryAggregate ctEntryAggregate, CTEntry ctEntry) {

		List<CTEntry> relatedCTEntries = ctEntryAggregate.getRelatedCTEntries();

		Stream<CTEntry> relatedCTEntriesStream = relatedCTEntries.stream();

		Optional<CTEntry> previousCTEntryOptional =
			relatedCTEntriesStream.filter(
				relatedCTEntry ->
					relatedCTEntry.getModelResourcePrimKey() ==
						ctEntry.getModelResourcePrimKey()
			).findFirst();

		previousCTEntryOptional.ifPresent(
			previousCTEntry -> {
				_ctEntryAggregateLocalService.removeCTEntry(
					ctEntryAggregate, previousCTEntry);

				_ctEntryAggregateLocalService.addCTEntry(
					ctEntryAggregate, ctEntry);
			});
	}

	private void _updateCTEntryInCTEntryAggregate(
		CTEntryAggregate ctEntryAggregate, CTEntry ctEntry, boolean force) {

		if (!force) {
			try {
				ctEntryAggregate = _copyCTEntryAggregate(ctEntryAggregate);
			}
			catch (PortalException pe) {
				_log.error(
					"Unable to copy change tracking entry aggregate " +
						ctEntryAggregate.getCtEntryAggregateId(),
					pe);

				return;
			}
		}

		_updateCTEntryInCTEntryAggregate(ctEntryAggregate, ctEntry);
	}

	private static final Log _log = LogFactoryUtil.getLog(CTManagerImpl.class);

	@Reference
	private CTCollectionLocalService _ctCollectionLocalService;

	@Reference
	private CTEngineManager _ctEngineManager;

	@Reference
	private CTEntryAggregateLocalService _ctEntryAggregateLocalService;

	@Reference
	private CTEntryLocalService _ctEntryLocalService;

	private final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	@Reference
	private UserLocalService _userLocalService;

}