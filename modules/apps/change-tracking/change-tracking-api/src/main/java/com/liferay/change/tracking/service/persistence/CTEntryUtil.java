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

package com.liferay.change.tracking.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.change.tracking.model.CTEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the ct entry service. This utility wraps <code>com.liferay.change.tracking.service.persistence.impl.CTEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CTEntryPersistence
 * @generated
 */
@ProviderType
public class CTEntryUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(CTEntry ctEntry) {
		getPersistence().clearCache(ctEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, CTEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CTEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CTEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CTEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CTEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CTEntry update(CTEntry ctEntry) {
		return getPersistence().update(ctEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CTEntry update(
		CTEntry ctEntry, ServiceContext serviceContext) {

		return getPersistence().update(ctEntry, serviceContext);
	}

	/**
	 * Returns the ct entry where modelClassNameId = &#63; and modelClassPK = &#63; or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param modelClassNameId the model class name ID
	 * @param modelClassPK the model class pk
	 * @return the matching ct entry
	 * @throws NoSuchEntryException if a matching ct entry could not be found
	 */
	public static CTEntry findByC_C(long modelClassNameId, long modelClassPK)
		throws com.liferay.change.tracking.exception.NoSuchEntryException {

		return getPersistence().findByC_C(modelClassNameId, modelClassPK);
	}

	/**
	 * Returns the ct entry where modelClassNameId = &#63; and modelClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param modelClassNameId the model class name ID
	 * @param modelClassPK the model class pk
	 * @return the matching ct entry, or <code>null</code> if a matching ct entry could not be found
	 */
	public static CTEntry fetchByC_C(long modelClassNameId, long modelClassPK) {
		return getPersistence().fetchByC_C(modelClassNameId, modelClassPK);
	}

	/**
	 * Returns the ct entry where modelClassNameId = &#63; and modelClassPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param modelClassNameId the model class name ID
	 * @param modelClassPK the model class pk
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching ct entry, or <code>null</code> if a matching ct entry could not be found
	 */
	public static CTEntry fetchByC_C(
		long modelClassNameId, long modelClassPK, boolean retrieveFromCache) {

		return getPersistence().fetchByC_C(
			modelClassNameId, modelClassPK, retrieveFromCache);
	}

	/**
	 * Removes the ct entry where modelClassNameId = &#63; and modelClassPK = &#63; from the database.
	 *
	 * @param modelClassNameId the model class name ID
	 * @param modelClassPK the model class pk
	 * @return the ct entry that was removed
	 */
	public static CTEntry removeByC_C(long modelClassNameId, long modelClassPK)
		throws com.liferay.change.tracking.exception.NoSuchEntryException {

		return getPersistence().removeByC_C(modelClassNameId, modelClassPK);
	}

	/**
	 * Returns the number of ct entries where modelClassNameId = &#63; and modelClassPK = &#63;.
	 *
	 * @param modelClassNameId the model class name ID
	 * @param modelClassPK the model class pk
	 * @return the number of matching ct entries
	 */
	public static int countByC_C(long modelClassNameId, long modelClassPK) {
		return getPersistence().countByC_C(modelClassNameId, modelClassPK);
	}

	/**
	 * Caches the ct entry in the entity cache if it is enabled.
	 *
	 * @param ctEntry the ct entry
	 */
	public static void cacheResult(CTEntry ctEntry) {
		getPersistence().cacheResult(ctEntry);
	}

	/**
	 * Caches the ct entries in the entity cache if it is enabled.
	 *
	 * @param ctEntries the ct entries
	 */
	public static void cacheResult(List<CTEntry> ctEntries) {
		getPersistence().cacheResult(ctEntries);
	}

	/**
	 * Creates a new ct entry with the primary key. Does not add the ct entry to the database.
	 *
	 * @param ctEntryId the primary key for the new ct entry
	 * @return the new ct entry
	 */
	public static CTEntry create(long ctEntryId) {
		return getPersistence().create(ctEntryId);
	}

	/**
	 * Removes the ct entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ctEntryId the primary key of the ct entry
	 * @return the ct entry that was removed
	 * @throws NoSuchEntryException if a ct entry with the primary key could not be found
	 */
	public static CTEntry remove(long ctEntryId)
		throws com.liferay.change.tracking.exception.NoSuchEntryException {

		return getPersistence().remove(ctEntryId);
	}

	public static CTEntry updateImpl(CTEntry ctEntry) {
		return getPersistence().updateImpl(ctEntry);
	}

	/**
	 * Returns the ct entry with the primary key or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param ctEntryId the primary key of the ct entry
	 * @return the ct entry
	 * @throws NoSuchEntryException if a ct entry with the primary key could not be found
	 */
	public static CTEntry findByPrimaryKey(long ctEntryId)
		throws com.liferay.change.tracking.exception.NoSuchEntryException {

		return getPersistence().findByPrimaryKey(ctEntryId);
	}

	/**
	 * Returns the ct entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ctEntryId the primary key of the ct entry
	 * @return the ct entry, or <code>null</code> if a ct entry with the primary key could not be found
	 */
	public static CTEntry fetchByPrimaryKey(long ctEntryId) {
		return getPersistence().fetchByPrimaryKey(ctEntryId);
	}

	/**
	 * Returns all the ct entries.
	 *
	 * @return the ct entries
	 */
	public static List<CTEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the ct entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CTEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ct entries
	 * @param end the upper bound of the range of ct entries (not inclusive)
	 * @return the range of ct entries
	 */
	public static List<CTEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the ct entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CTEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ct entries
	 * @param end the upper bound of the range of ct entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ct entries
	 */
	public static List<CTEntry> findAll(
		int start, int end, OrderByComparator<CTEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the ct entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CTEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ct entries
	 * @param end the upper bound of the range of ct entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ct entries
	 */
	public static List<CTEntry> findAll(
		int start, int end, OrderByComparator<CTEntry> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the ct entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of ct entries.
	 *
	 * @return the number of ct entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	 * Returns the primaryKeys of ct entry aggregates associated with the ct entry.
	 *
	 * @param pk the primary key of the ct entry
	 * @return long[] of the primaryKeys of ct entry aggregates associated with the ct entry
	 */
	public static long[] getCTEntryAggregatePrimaryKeys(long pk) {
		return getPersistence().getCTEntryAggregatePrimaryKeys(pk);
	}

	/**
	 * Returns all the ct entry aggregates associated with the ct entry.
	 *
	 * @param pk the primary key of the ct entry
	 * @return the ct entry aggregates associated with the ct entry
	 */
	public static List<com.liferay.change.tracking.model.CTEntryAggregate>
		getCTEntryAggregates(long pk) {

		return getPersistence().getCTEntryAggregates(pk);
	}

	/**
	 * Returns a range of all the ct entry aggregates associated with the ct entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CTEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the ct entry
	 * @param start the lower bound of the range of ct entries
	 * @param end the upper bound of the range of ct entries (not inclusive)
	 * @return the range of ct entry aggregates associated with the ct entry
	 */
	public static List<com.liferay.change.tracking.model.CTEntryAggregate>
		getCTEntryAggregates(long pk, int start, int end) {

		return getPersistence().getCTEntryAggregates(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the ct entry aggregates associated with the ct entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CTEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the ct entry
	 * @param start the lower bound of the range of ct entries
	 * @param end the upper bound of the range of ct entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ct entry aggregates associated with the ct entry
	 */
	public static List<com.liferay.change.tracking.model.CTEntryAggregate>
		getCTEntryAggregates(
			long pk, int start, int end,
			OrderByComparator
				<com.liferay.change.tracking.model.CTEntryAggregate>
					orderByComparator) {

		return getPersistence().getCTEntryAggregates(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of ct entry aggregates associated with the ct entry.
	 *
	 * @param pk the primary key of the ct entry
	 * @return the number of ct entry aggregates associated with the ct entry
	 */
	public static int getCTEntryAggregatesSize(long pk) {
		return getPersistence().getCTEntryAggregatesSize(pk);
	}

	/**
	 * Returns <code>true</code> if the ct entry aggregate is associated with the ct entry.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctEntryAggregatePK the primary key of the ct entry aggregate
	 * @return <code>true</code> if the ct entry aggregate is associated with the ct entry; <code>false</code> otherwise
	 */
	public static boolean containsCTEntryAggregate(
		long pk, long ctEntryAggregatePK) {

		return getPersistence().containsCTEntryAggregate(
			pk, ctEntryAggregatePK);
	}

	/**
	 * Returns <code>true</code> if the ct entry has any ct entry aggregates associated with it.
	 *
	 * @param pk the primary key of the ct entry to check for associations with ct entry aggregates
	 * @return <code>true</code> if the ct entry has any ct entry aggregates associated with it; <code>false</code> otherwise
	 */
	public static boolean containsCTEntryAggregates(long pk) {
		return getPersistence().containsCTEntryAggregates(pk);
	}

	/**
	 * Adds an association between the ct entry and the ct entry aggregate. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctEntryAggregatePK the primary key of the ct entry aggregate
	 */
	public static void addCTEntryAggregate(long pk, long ctEntryAggregatePK) {
		getPersistence().addCTEntryAggregate(pk, ctEntryAggregatePK);
	}

	/**
	 * Adds an association between the ct entry and the ct entry aggregate. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctEntryAggregate the ct entry aggregate
	 */
	public static void addCTEntryAggregate(
		long pk,
		com.liferay.change.tracking.model.CTEntryAggregate ctEntryAggregate) {

		getPersistence().addCTEntryAggregate(pk, ctEntryAggregate);
	}

	/**
	 * Adds an association between the ct entry and the ct entry aggregates. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctEntryAggregatePKs the primary keys of the ct entry aggregates
	 */
	public static void addCTEntryAggregates(
		long pk, long[] ctEntryAggregatePKs) {

		getPersistence().addCTEntryAggregates(pk, ctEntryAggregatePKs);
	}

	/**
	 * Adds an association between the ct entry and the ct entry aggregates. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctEntryAggregates the ct entry aggregates
	 */
	public static void addCTEntryAggregates(
		long pk,
		List<com.liferay.change.tracking.model.CTEntryAggregate>
			ctEntryAggregates) {

		getPersistence().addCTEntryAggregates(pk, ctEntryAggregates);
	}

	/**
	 * Clears all associations between the ct entry and its ct entry aggregates. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry to clear the associated ct entry aggregates from
	 */
	public static void clearCTEntryAggregates(long pk) {
		getPersistence().clearCTEntryAggregates(pk);
	}

	/**
	 * Removes the association between the ct entry and the ct entry aggregate. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctEntryAggregatePK the primary key of the ct entry aggregate
	 */
	public static void removeCTEntryAggregate(
		long pk, long ctEntryAggregatePK) {

		getPersistence().removeCTEntryAggregate(pk, ctEntryAggregatePK);
	}

	/**
	 * Removes the association between the ct entry and the ct entry aggregate. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctEntryAggregate the ct entry aggregate
	 */
	public static void removeCTEntryAggregate(
		long pk,
		com.liferay.change.tracking.model.CTEntryAggregate ctEntryAggregate) {

		getPersistence().removeCTEntryAggregate(pk, ctEntryAggregate);
	}

	/**
	 * Removes the association between the ct entry and the ct entry aggregates. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctEntryAggregatePKs the primary keys of the ct entry aggregates
	 */
	public static void removeCTEntryAggregates(
		long pk, long[] ctEntryAggregatePKs) {

		getPersistence().removeCTEntryAggregates(pk, ctEntryAggregatePKs);
	}

	/**
	 * Removes the association between the ct entry and the ct entry aggregates. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctEntryAggregates the ct entry aggregates
	 */
	public static void removeCTEntryAggregates(
		long pk,
		List<com.liferay.change.tracking.model.CTEntryAggregate>
			ctEntryAggregates) {

		getPersistence().removeCTEntryAggregates(pk, ctEntryAggregates);
	}

	/**
	 * Sets the ct entry aggregates associated with the ct entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctEntryAggregatePKs the primary keys of the ct entry aggregates to be associated with the ct entry
	 */
	public static void setCTEntryAggregates(
		long pk, long[] ctEntryAggregatePKs) {

		getPersistence().setCTEntryAggregates(pk, ctEntryAggregatePKs);
	}

	/**
	 * Sets the ct entry aggregates associated with the ct entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctEntryAggregates the ct entry aggregates to be associated with the ct entry
	 */
	public static void setCTEntryAggregates(
		long pk,
		List<com.liferay.change.tracking.model.CTEntryAggregate>
			ctEntryAggregates) {

		getPersistence().setCTEntryAggregates(pk, ctEntryAggregates);
	}

	/**
	 * Returns the primaryKeys of ct collections associated with the ct entry.
	 *
	 * @param pk the primary key of the ct entry
	 * @return long[] of the primaryKeys of ct collections associated with the ct entry
	 */
	public static long[] getCTCollectionPrimaryKeys(long pk) {
		return getPersistence().getCTCollectionPrimaryKeys(pk);
	}

	/**
	 * Returns all the ct collections associated with the ct entry.
	 *
	 * @param pk the primary key of the ct entry
	 * @return the ct collections associated with the ct entry
	 */
	public static List<com.liferay.change.tracking.model.CTCollection>
		getCTCollections(long pk) {

		return getPersistence().getCTCollections(pk);
	}

	/**
	 * Returns a range of all the ct collections associated with the ct entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CTEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the ct entry
	 * @param start the lower bound of the range of ct entries
	 * @param end the upper bound of the range of ct entries (not inclusive)
	 * @return the range of ct collections associated with the ct entry
	 */
	public static List<com.liferay.change.tracking.model.CTCollection>
		getCTCollections(long pk, int start, int end) {

		return getPersistence().getCTCollections(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the ct collections associated with the ct entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CTEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the ct entry
	 * @param start the lower bound of the range of ct entries
	 * @param end the upper bound of the range of ct entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ct collections associated with the ct entry
	 */
	public static List<com.liferay.change.tracking.model.CTCollection>
		getCTCollections(
			long pk, int start, int end,
			OrderByComparator<com.liferay.change.tracking.model.CTCollection>
				orderByComparator) {

		return getPersistence().getCTCollections(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of ct collections associated with the ct entry.
	 *
	 * @param pk the primary key of the ct entry
	 * @return the number of ct collections associated with the ct entry
	 */
	public static int getCTCollectionsSize(long pk) {
		return getPersistence().getCTCollectionsSize(pk);
	}

	/**
	 * Returns <code>true</code> if the ct collection is associated with the ct entry.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctCollectionPK the primary key of the ct collection
	 * @return <code>true</code> if the ct collection is associated with the ct entry; <code>false</code> otherwise
	 */
	public static boolean containsCTCollection(long pk, long ctCollectionPK) {
		return getPersistence().containsCTCollection(pk, ctCollectionPK);
	}

	/**
	 * Returns <code>true</code> if the ct entry has any ct collections associated with it.
	 *
	 * @param pk the primary key of the ct entry to check for associations with ct collections
	 * @return <code>true</code> if the ct entry has any ct collections associated with it; <code>false</code> otherwise
	 */
	public static boolean containsCTCollections(long pk) {
		return getPersistence().containsCTCollections(pk);
	}

	/**
	 * Adds an association between the ct entry and the ct collection. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctCollectionPK the primary key of the ct collection
	 */
	public static void addCTCollection(long pk, long ctCollectionPK) {
		getPersistence().addCTCollection(pk, ctCollectionPK);
	}

	/**
	 * Adds an association between the ct entry and the ct collection. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctCollection the ct collection
	 */
	public static void addCTCollection(
		long pk, com.liferay.change.tracking.model.CTCollection ctCollection) {

		getPersistence().addCTCollection(pk, ctCollection);
	}

	/**
	 * Adds an association between the ct entry and the ct collections. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctCollectionPKs the primary keys of the ct collections
	 */
	public static void addCTCollections(long pk, long[] ctCollectionPKs) {
		getPersistence().addCTCollections(pk, ctCollectionPKs);
	}

	/**
	 * Adds an association between the ct entry and the ct collections. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctCollections the ct collections
	 */
	public static void addCTCollections(
		long pk,
		List<com.liferay.change.tracking.model.CTCollection> ctCollections) {

		getPersistence().addCTCollections(pk, ctCollections);
	}

	/**
	 * Clears all associations between the ct entry and its ct collections. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry to clear the associated ct collections from
	 */
	public static void clearCTCollections(long pk) {
		getPersistence().clearCTCollections(pk);
	}

	/**
	 * Removes the association between the ct entry and the ct collection. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctCollectionPK the primary key of the ct collection
	 */
	public static void removeCTCollection(long pk, long ctCollectionPK) {
		getPersistence().removeCTCollection(pk, ctCollectionPK);
	}

	/**
	 * Removes the association between the ct entry and the ct collection. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctCollection the ct collection
	 */
	public static void removeCTCollection(
		long pk, com.liferay.change.tracking.model.CTCollection ctCollection) {

		getPersistence().removeCTCollection(pk, ctCollection);
	}

	/**
	 * Removes the association between the ct entry and the ct collections. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctCollectionPKs the primary keys of the ct collections
	 */
	public static void removeCTCollections(long pk, long[] ctCollectionPKs) {
		getPersistence().removeCTCollections(pk, ctCollectionPKs);
	}

	/**
	 * Removes the association between the ct entry and the ct collections. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctCollections the ct collections
	 */
	public static void removeCTCollections(
		long pk,
		List<com.liferay.change.tracking.model.CTCollection> ctCollections) {

		getPersistence().removeCTCollections(pk, ctCollections);
	}

	/**
	 * Sets the ct collections associated with the ct entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctCollectionPKs the primary keys of the ct collections to be associated with the ct entry
	 */
	public static void setCTCollections(long pk, long[] ctCollectionPKs) {
		getPersistence().setCTCollections(pk, ctCollectionPKs);
	}

	/**
	 * Sets the ct collections associated with the ct entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the ct entry
	 * @param ctCollections the ct collections to be associated with the ct entry
	 */
	public static void setCTCollections(
		long pk,
		List<com.liferay.change.tracking.model.CTCollection> ctCollections) {

		getPersistence().setCTCollections(pk, ctCollections);
	}

	public static CTEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CTEntryPersistence, CTEntryPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CTEntryPersistence.class);

		ServiceTracker<CTEntryPersistence, CTEntryPersistence> serviceTracker =
			new ServiceTracker<CTEntryPersistence, CTEntryPersistence>(
				bundle.getBundleContext(), CTEntryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}