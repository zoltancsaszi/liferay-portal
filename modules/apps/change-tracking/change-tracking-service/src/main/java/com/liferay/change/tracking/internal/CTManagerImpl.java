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
import com.liferay.change.tracking.configuration.CTConfiguration;
import com.liferay.change.tracking.configuration.CTConfigurationRegistry;
import com.liferay.change.tracking.exception.CTEntryException;
import com.liferay.change.tracking.exception.CTException;
import com.liferay.change.tracking.exception.DuplicateCTEntryException;
import com.liferay.change.tracking.internal.util.CTEntryCollisionHelperUtil;
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
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
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
		long userId, long modelClassNameId, long modelClassPK) {

		Optional<CTCollection> ctCollectionOptional =
			getActiveCTCollectionOptional(userId);

		long ctCollectionId = ctCollectionOptional.map(
			CTCollection::getCtCollectionId
		).orElse(
			0L
		);

		long companyId = _getCompanyId(userId);

		CTEntry ctEntry = _getCTentry(
			companyId, ctCollectionId, modelClassNameId, modelClassPK);

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
		long userId, long modelClassNameId, long modelClassPK) {

		Optional<CTEntry> ctEntryOptional = getModelChangeCTEntryOptional(
			userId, modelClassNameId, modelClassPK);

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
		long userId, long modelClassNameId, long modelClassPK) {

		Optional<CTEntry> ctEntryOptional =
			getActiveCTCollectionCTEntryOptional(
				userId, modelClassNameId, modelClassPK);

		if (ctEntryOptional.isPresent()) {
			return ctEntryOptional;
		}

		return getProductionCTCollectionCTEntryOptional(
			userId, modelClassNameId, modelClassPK);
	}

	@Override
	public Optional<CTEntry> getProductionCTCollectionCTEntryOptional(
		long userId, long modelClassNameId, long modelClassPK) {

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
			companyId, ctCollectionId, modelClassNameId, modelClassPK);

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
			long userId, long modelClassNameId, long modelClassPK,
			long modelResourcePrimKey, int changeType)
		throws CTException {

		return registerModelChange(
			userId, modelClassNameId, modelClassPK, modelResourcePrimKey,
			changeType, false);
	}

	@Override
	public Optional<CTEntry> registerModelChange(
			long userId, long modelClassNameId, long modelClassPK,
			long modelResourcePrimKey, int changeType, boolean force)
		throws CTException {

		long companyId = _getCompanyId(userId);

		if (!_ctEngineManager.isChangeTrackingEnabled(companyId) ||
			!_ctEngineManager.isChangeTrackingSupported(
				companyId, modelClassNameId)) {

			return Optional.empty();
		}

		Optional<CTCollection> ctCollectionOptional =
			getActiveCTCollectionOptional(userId);

		if (!ctCollectionOptional.isPresent()) {
			return Optional.empty();
		}

		CTCollection ctCollection = ctCollectionOptional.get();

		Optional<CTEntry> ctEntryOptional = Optional.empty();

		try {
			ctEntryOptional = TransactionInvokerUtil.invoke(
				_transactionConfig,
				() -> _registerModelChange(
					userId, modelClassNameId, modelClassPK,
					modelResourcePrimKey, changeType, force, companyId,
					ctCollection));
		}
		catch (Throwable t) {
			if (t instanceof CTException) {
				throw (CTException)t;
			}

			_log.error("Unable to register model change", t);
		}

		return ctEntryOptional;
	}

	@Override
	public <V extends BaseModel> void registerRelatedChanges(
		long userId, long classNameId, long classPK) {

		registerRelatedChanges(userId, classNameId, classPK, false);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <V extends BaseModel> void registerRelatedChanges(
		long userId, long classNameId, long classPK, boolean force) {

		long companyId = _getCompanyId(userId);

		if (!_ctEngineManager.isChangeTrackingEnabled(companyId) ||
			!_ctEngineManager.isChangeTrackingSupported(
				companyId, classNameId)) {

			return;
		}

		Optional<CTConfiguration<?, ?>> ctConfigurationOptional =
			_ctConfigurationRegistry.
				getCTConfigurationOptionalByVersionClassName(
					_portal.getClassName(classNameId));

		if (!ctConfigurationOptional.isPresent()) {
			return;
		}

		CTConfiguration<?, V> ctConfiguration =
			(CTConfiguration<?, V>)ctConfigurationOptional.get();

		List<Function<V, ? extends BaseModel>> relatedEntityFunctions =
			ctConfiguration.getVersionEntityRelatedEntityFunctions();

		Function<Long, V> versionEntityByVersionEntityIdFunction =
			ctConfiguration.getVersionEntityByVersionEntityIdFunction();

		V versionEntity = versionEntityByVersionEntityIdFunction.apply(classPK);

		relatedEntityFunctions.forEach(
			relatedEntityFunction -> _registerRelatedChange(
				userId, classNameId, classPK, versionEntity,
				relatedEntityFunction, force));
	}

	@Override
	public Optional<CTEntry> unregisterModelChange(
		long userId, long modelClassNameId, long modelClassPK) {

		long companyId = _getCompanyId(userId);

		if (companyId <= 0) {
			return Optional.empty();
		}

		if (!_ctEngineManager.isChangeTrackingEnabled(companyId) ||
			!_ctEngineManager.isChangeTrackingSupported(
				companyId, modelClassNameId)) {

			return Optional.empty();
		}

		Optional<CTEntry> ctEntryOptional = getModelChangeCTEntryOptional(
			userId, modelClassNameId, modelClassPK);

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

	private void _checkCollisions(CTEntry ctEntry, CTCollection ctCollection) {
		if (!ctCollection.isProduction()) {
			return;
		}

		CTEntryCollisionHelperUtil.checkCollidingCTEntries(ctEntry);
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
		long companyId, long ctCollectionId, long modelClassNameId,
		long modelClassPK) {

		if (!_ctEngineManager.isChangeTrackingEnabled(companyId) ||
			!_ctEngineManager.isChangeTrackingSupported(
				companyId, modelClassNameId)) {

			return null;
		}

		return _ctEntryLocalService.fetchCTEntry(
			ctCollectionId, modelClassNameId, modelClassPK);
	}

	private Optional<CTEntry> _registerModelChange(
			long userId, long modelClassNameId, long modelClassPK,
			long modelResourcePrimKey, int changeType, boolean force,
			long companyId, CTCollection ctCollection)
		throws CTException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAttribute("force", force);

			Optional<CTEntry> previousModelChangeCTEntryOptional =
				getLatestModelChangeCTEntryOptional(
					userId, modelResourcePrimKey);

			// Creating a new change tracking entry

			CTEntry ctEntry = _ctEntryLocalService.addCTEntry(
				userId, modelClassNameId, modelClassPK, modelResourcePrimKey,
				changeType, ctCollection.getCtCollectionId(), serviceContext);

			_checkCollisions(ctEntry, ctCollection);

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

			sb.append("Duplicate CTEntry with model class name ID ");
			sb.append(modelClassNameId);
			sb.append(", model class PK ");
			sb.append(modelClassPK);
			sb.append(", and model resource primary key ");
			sb.append(modelResourcePrimKey);
			sb.append(" in change tracking collection ");
			sb.append(ctCollection.getCtCollectionId());

			throw new CTEntryException(
				0L, companyId, userId, modelClassNameId, modelClassPK,
				modelResourcePrimKey, ctCollection.getCtCollectionId(),
				sb.toString(), dctee);
		}
		catch (PortalException pe) {
			StringBundler sb = new StringBundler(9);

			sb.append("Unable to register model change with model class name ");
			sb.append("ID ");
			sb.append(modelClassNameId);
			sb.append(", model class PK ");
			sb.append(modelClassPK);
			sb.append(", and model resource primary key ");
			sb.append(modelResourcePrimKey);
			sb.append(" in change tracking collection ");
			sb.append(ctCollection.getCtCollectionId());

			throw new CTException(companyId, sb.toString(), pe);
		}
	}

	private <V extends BaseModel, R extends BaseModel> void
		_registerRelatedChange(
			long userId, long classNameId, long classPK, V versionEntity,
			Function<V, R> relatedEntityFunction, boolean force) {

		Optional<CTEntry> versionEntityCTEntryOptional =
			getModelChangeCTEntryOptional(userId, classNameId, classPK);

		if (!versionEntityCTEntryOptional.isPresent()) {
			return;
		}

		R relatedEntity = relatedEntityFunction.apply(versionEntity);

		if (relatedEntity == null) {
			return;
		}

		long relatedEntityClassPK = (Long)relatedEntity.getPrimaryKeyObj();

		Optional<CTEntry> relatedEntityCTEntryOptional =
			getModelChangeCTEntryOptional(
				userId,
				_portal.getClassNameId(relatedEntity.getModelClassName()),
				relatedEntityClassPK);

		if (!relatedEntityCTEntryOptional.isPresent()) {
			relatedEntityCTEntryOptional = getLatestModelChangeCTEntryOptional(
				userId, relatedEntityClassPK);
		}

		if (!relatedEntityCTEntryOptional.isPresent()) {
			return;
		}

		addRelatedCTEntry(
			userId, versionEntityCTEntryOptional.get(),
			relatedEntityCTEntryOptional.get(), force);
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
	private CTConfigurationRegistry _ctConfigurationRegistry;

	@Reference
	private CTEngineManager _ctEngineManager;

	@Reference
	private CTEntryAggregateLocalService _ctEntryAggregateLocalService;

	@Reference
	private CTEntryLocalService _ctEntryLocalService;

	@Reference
	private Portal _portal;

	private final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	@Reference
	private UserLocalService _userLocalService;

}