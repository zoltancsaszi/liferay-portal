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

package com.liferay.change.tracking.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.change.tracking.model.CTCollection;
import com.liferay.change.tracking.model.CTEntry;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for CTEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see CTEntryLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CTEntryLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CTEntryLocalServiceUtil} to access the ct entry local service. Add custom service methods to <code>com.liferay.change.tracking.service.impl.CTEntryLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public void addCTCollectionCTEntries(
		long ctCollectionId, List<CTEntry> ctEntries);

	public void addCTCollectionCTEntries(
		long ctCollectionId, long[] ctEntryIds);

	public void addCTCollectionCTEntry(long ctCollectionId, CTEntry ctEntry);

	public void addCTCollectionCTEntry(long ctCollectionId, long ctEntryId);

	/**
	 * Adds the ct entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param ctEntry the ct entry
	 * @return the ct entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CTEntry addCTEntry(CTEntry ctEntry);

	@Indexable(type = IndexableType.REINDEX)
	public CTEntry addCTEntry(
			long userId, long modelClassNameId, long modelClassPK,
			long modelResourcePrimKey, int changeType, long ctCollectionId,
			ServiceContext serviceContext)
		throws PortalException;

	public void addCTEntryAggregateCTEntries(
		long ctEntryAggregateId, List<CTEntry> ctEntries);

	public void addCTEntryAggregateCTEntries(
		long ctEntryAggregateId, long[] ctEntryIds);

	public void addCTEntryAggregateCTEntry(
		long ctEntryAggregateId, CTEntry ctEntry);

	public void addCTEntryAggregateCTEntry(
		long ctEntryAggregateId, long ctEntryId);

	public void clearCTCollectionCTEntries(long ctCollectionId);

	public void clearCTEntryAggregateCTEntries(long ctEntryAggregateId);

	/**
	 * Creates a new ct entry with the primary key. Does not add the ct entry to the database.
	 *
	 * @param ctEntryId the primary key for the new ct entry
	 * @return the new ct entry
	 */
	@Transactional(enabled = false)
	public CTEntry createCTEntry(long ctEntryId);

	public void deleteCTCollectionCTEntries(
		long ctCollectionId, List<CTEntry> ctEntries);

	public void deleteCTCollectionCTEntries(
		long ctCollectionId, long[] ctEntryIds);

	public void deleteCTCollectionCTEntry(long ctCollectionId, CTEntry ctEntry);

	public void deleteCTCollectionCTEntry(long ctCollectionId, long ctEntryId);

	/**
	 * Deletes the ct entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ctEntry the ct entry
	 * @return the ct entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public CTEntry deleteCTEntry(CTEntry ctEntry);

	/**
	 * Deletes the ct entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ctEntryId the primary key of the ct entry
	 * @return the ct entry that was removed
	 * @throws PortalException if a ct entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public CTEntry deleteCTEntry(long ctEntryId) throws PortalException;

	public void deleteCTEntryAggregateCTEntries(
		long ctEntryAggregateId, List<CTEntry> ctEntries);

	public void deleteCTEntryAggregateCTEntries(
		long ctEntryAggregateId, long[] ctEntryIds);

	public void deleteCTEntryAggregateCTEntry(
		long ctEntryAggregateId, CTEntry ctEntry);

	public void deleteCTEntryAggregateCTEntry(
		long ctEntryAggregateId, long ctEntryId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.change.tracking.model.impl.CTEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.change.tracking.model.impl.CTEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CTEntry> fetchCTEntries(long modelClassNameId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CTEntry> fetchCTEntries(
		long ctCollectionId, long modelResourcePrimKey,
		QueryDefinition<CTEntry> queryDefinition);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CTEntry> fetchCTEntries(
		long ctCollectionId, QueryDefinition<CTEntry> queryDefinition);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CTEntry> fetchCTEntries(String modelClassName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CTEntry fetchCTEntry(long ctEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CTEntry fetchCTEntry(long modelClassNameId, long modelClassPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CTEntry fetchCTEntry(
		long ctCollectionId, long modelClassNameId, long modelClassPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CTEntry> getCTCollectionCTEntries(long ctCollectionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CTEntry> getCTCollectionCTEntries(
		long ctCollectionId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CTEntry> getCTCollectionCTEntries(
		long ctCollectionId, int status, int start, int end,
		OrderByComparator<CTEntry> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CTEntry> getCTCollectionCTEntries(
		long ctCollectionId, int start, int end,
		OrderByComparator<CTEntry> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCTCollectionCTEntriesCount(long ctCollectionId);

	/**
	 * Returns the ctCollectionIds of the ct collections associated with the ct entry.
	 *
	 * @param ctEntryId the ctEntryId of the ct entry
	 * @return long[] the ctCollectionIds of ct collections associated with the ct entry
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getCTCollectionPrimaryKeys(long ctEntryId);

	/**
	 * Returns a range of all the ct entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.change.tracking.model.impl.CTEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ct entries
	 * @param end the upper bound of the range of ct entries (not inclusive)
	 * @return the range of ct entries
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CTEntry> getCTEntries(int start, int end);

	/**
	 * Returns the number of ct entries.
	 *
	 * @return the number of ct entries
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCTEntriesCount();

	/**
	 * Returns the ct entry with the primary key.
	 *
	 * @param ctEntryId the primary key of the ct entry
	 * @return the ct entry
	 * @throws PortalException if a ct entry with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CTEntry getCTEntry(long ctEntryId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CTEntry> getCTEntryAggregateCTEntries(long ctEntryAggregateId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CTEntry> getCTEntryAggregateCTEntries(
		long ctEntryAggregateId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CTEntry> getCTEntryAggregateCTEntries(
		long ctEntryAggregateId, int start, int end,
		OrderByComparator<CTEntry> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCTEntryAggregateCTEntriesCount(long ctEntryAggregateId);

	/**
	 * Returns the ctEntryAggregateIds of the ct entry aggregates associated with the ct entry.
	 *
	 * @param ctEntryId the ctEntryId of the ct entry
	 * @return long[] the ctEntryAggregateIds of ct entry aggregates associated with the ct entry
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getCTEntryAggregatePrimaryKeys(long ctEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CTEntry> getRelatedOwnerCTEntries(long ctEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CTEntry> getRelatedOwnerCTEntries(
		long ctEntryId, int start, int end,
		OrderByComparator<CTEntry> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRelatedOwnerCTEntriesCount(long ctEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasCTCollectionCTEntries(long ctCollectionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasCTCollectionCTEntry(long ctCollectionId, long ctEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasCTEntryAggregateCTEntries(long ctEntryAggregateId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasCTEntryAggregateCTEntry(
		long ctEntryAggregateId, long ctEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CTEntry> search(
		CTCollection ctCollection, long[] groupIds, long[] userIds,
		long[] classNameIds, int[] changeTypes, boolean collision,
		long otherCTCollectionId, QueryDefinition<CTEntry> queryDefinition);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long searchCount(
		CTCollection ctCollection, long[] groupIds, long[] userIds,
		long[] classNameIds, int[] changeTypes, boolean collision,
		long otherCTCollectionId, QueryDefinition<CTEntry> queryDefinition);

	public void setCTCollectionCTEntries(
		long ctCollectionId, long[] ctEntryIds);

	public void setCTEntryAggregateCTEntries(
		long ctEntryAggregateId, long[] ctEntryIds);

	@Indexable(type = IndexableType.REINDEX)
	public CTEntry updateCollision(long ctEntryId, boolean collision);

	/**
	 * Updates the ct entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ctEntry the ct entry
	 * @return the ct entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CTEntry updateCTEntry(CTEntry ctEntry);

	@Indexable(type = IndexableType.REINDEX)
	public CTEntry updateStatus(long ctEntryId, int status);

}