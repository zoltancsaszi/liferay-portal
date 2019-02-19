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

package com.liferay.asset.kernel.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.kernel.model.AssetCategoryVersion;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the asset category version service. This utility wraps <code>com.liferay.portlet.asset.service.persistence.impl.AssetCategoryVersionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetCategoryVersionPersistence
 * @generated
 */
@ProviderType
public class AssetCategoryVersionUtil {
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
	public static void clearCache(AssetCategoryVersion assetCategoryVersion) {
		getPersistence().clearCache(assetCategoryVersion);
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
	public static Map<Serializable, AssetCategoryVersion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AssetCategoryVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetCategoryVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetCategoryVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AssetCategoryVersion update(
		AssetCategoryVersion assetCategoryVersion) {
		return getPersistence().update(assetCategoryVersion);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AssetCategoryVersion update(
		AssetCategoryVersion assetCategoryVersion, ServiceContext serviceContext) {
		return getPersistence().update(assetCategoryVersion, serviceContext);
	}

	/**
	* Returns all the asset category versions where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByCategoryId(long categoryId) {
		return getPersistence().findByCategoryId(categoryId);
	}

	/**
	* Returns a range of all the asset category versions where categoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param categoryId the category ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByCategoryId(long categoryId,
		int start, int end) {
		return getPersistence().findByCategoryId(categoryId, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where categoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param categoryId the category ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByCategoryId(long categoryId,
		int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByCategoryId(categoryId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where categoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param categoryId the category ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByCategoryId(long categoryId,
		int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCategoryId(categoryId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByCategoryId_First(long categoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByCategoryId_First(categoryId, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByCategoryId_First(
		long categoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByCategoryId_First(categoryId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByCategoryId_Last(long categoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByCategoryId_Last(categoryId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByCategoryId_Last(long categoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByCategoryId_Last(categoryId, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where categoryId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByCategoryId_PrevAndNext(
		long assetCategoryVersionId, long categoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByCategoryId_PrevAndNext(assetCategoryVersionId,
			categoryId, orderByComparator);
	}

	/**
	* Removes all the asset category versions where categoryId = &#63; from the database.
	*
	* @param categoryId the category ID
	*/
	public static void removeByCategoryId(long categoryId) {
		getPersistence().removeByCategoryId(categoryId);
	}

	/**
	* Returns the number of asset category versions where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @return the number of matching asset category versions
	*/
	public static int countByCategoryId(long categoryId) {
		return getPersistence().countByCategoryId(categoryId);
	}

	/**
	* Returns the asset category version where categoryId = &#63; and version = &#63; or throws a <code>NoSuchCategoryVersionException</code> if it could not be found.
	*
	* @param categoryId the category ID
	* @param version the version
	* @return the matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByCategoryId_Version(
		long categoryId, int version)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence().findByCategoryId_Version(categoryId, version);
	}

	/**
	* Returns the asset category version where categoryId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param categoryId the category ID
	* @param version the version
	* @return the matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByCategoryId_Version(
		long categoryId, int version) {
		return getPersistence().fetchByCategoryId_Version(categoryId, version);
	}

	/**
	* Returns the asset category version where categoryId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param categoryId the category ID
	* @param version the version
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByCategoryId_Version(
		long categoryId, int version, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCategoryId_Version(categoryId, version,
			retrieveFromCache);
	}

	/**
	* Removes the asset category version where categoryId = &#63; and version = &#63; from the database.
	*
	* @param categoryId the category ID
	* @param version the version
	* @return the asset category version that was removed
	*/
	public static AssetCategoryVersion removeByCategoryId_Version(
		long categoryId, int version)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence().removeByCategoryId_Version(categoryId, version);
	}

	/**
	* Returns the number of asset category versions where categoryId = &#63; and version = &#63;.
	*
	* @param categoryId the category ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByCategoryId_Version(long categoryId, int version) {
		return getPersistence().countByCategoryId_Version(categoryId, version);
	}

	/**
	* Returns all the asset category versions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the asset category versions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUuid(String uuid, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUuid(String uuid, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByUuid_First(String uuid,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByUuid_First(String uuid,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByUuid_Last(String uuid,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByUuid_Last(String uuid,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where uuid = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByUuid_PrevAndNext(
		long assetCategoryVersionId, String uuid,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByUuid_PrevAndNext(assetCategoryVersionId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the asset category versions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of asset category versions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching asset category versions
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the asset category versions where uuid = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param version the version
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUuid_Version(String uuid,
		int version) {
		return getPersistence().findByUuid_Version(uuid, version);
	}

	/**
	* Returns a range of all the asset category versions where uuid = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUuid_Version(String uuid,
		int version, int start, int end) {
		return getPersistence().findByUuid_Version(uuid, version, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where uuid = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUuid_Version(String uuid,
		int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByUuid_Version(uuid, version, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where uuid = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUuid_Version(String uuid,
		int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_Version(uuid, version, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByUuid_Version_First(String uuid,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByUuid_Version_First(uuid, version, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByUuid_Version_First(String uuid,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_Version_First(uuid, version, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByUuid_Version_Last(String uuid,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByUuid_Version_Last(uuid, version, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByUuid_Version_Last(String uuid,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_Version_Last(uuid, version, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where uuid = &#63; and version = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param uuid the uuid
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByUuid_Version_PrevAndNext(
		long assetCategoryVersionId, String uuid, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByUuid_Version_PrevAndNext(assetCategoryVersionId,
			uuid, version, orderByComparator);
	}

	/**
	* Removes all the asset category versions where uuid = &#63; and version = &#63; from the database.
	*
	* @param uuid the uuid
	* @param version the version
	*/
	public static void removeByUuid_Version(String uuid, int version) {
		getPersistence().removeByUuid_Version(uuid, version);
	}

	/**
	* Returns the number of asset category versions where uuid = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByUuid_Version(String uuid, int version) {
		return getPersistence().countByUuid_Version(uuid, version);
	}

	/**
	* Returns all the asset category versions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUUID_G(String uuid,
		long groupId) {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns a range of all the asset category versions where uuid = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUUID_G(String uuid,
		long groupId, int start, int end) {
		return getPersistence().findByUUID_G(uuid, groupId, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where uuid = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUUID_G(String uuid,
		long groupId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByUUID_G(uuid, groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where uuid = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUUID_G(String uuid,
		long groupId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUUID_G(uuid, groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByUUID_G_First(String uuid,
		long groupId, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByUUID_G_First(uuid, groupId, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByUUID_G_First(String uuid,
		long groupId, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByUUID_G_First(uuid, groupId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByUUID_G_Last(String uuid,
		long groupId, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByUUID_G_Last(uuid, groupId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByUUID_G_Last(String uuid,
		long groupId, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByUUID_G_Last(uuid, groupId, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where uuid = &#63; and groupId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param uuid the uuid
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByUUID_G_PrevAndNext(
		long assetCategoryVersionId, String uuid, long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByUUID_G_PrevAndNext(assetCategoryVersionId, uuid,
			groupId, orderByComparator);
	}

	/**
	* Removes all the asset category versions where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	*/
	public static void removeByUUID_G(String uuid, long groupId) {
		getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of asset category versions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching asset category versions
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the asset category version where uuid = &#63; and groupId = &#63; and version = &#63; or throws a <code>NoSuchCategoryVersionException</code> if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param version the version
	* @return the matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByUUID_G_Version(String uuid,
		long groupId, int version)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence().findByUUID_G_Version(uuid, groupId, version);
	}

	/**
	* Returns the asset category version where uuid = &#63; and groupId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param version the version
	* @return the matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByUUID_G_Version(String uuid,
		long groupId, int version) {
		return getPersistence().fetchByUUID_G_Version(uuid, groupId, version);
	}

	/**
	* Returns the asset category version where uuid = &#63; and groupId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param version the version
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByUUID_G_Version(String uuid,
		long groupId, int version, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByUUID_G_Version(uuid, groupId, version,
			retrieveFromCache);
	}

	/**
	* Removes the asset category version where uuid = &#63; and groupId = &#63; and version = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param version the version
	* @return the asset category version that was removed
	*/
	public static AssetCategoryVersion removeByUUID_G_Version(String uuid,
		long groupId, int version)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence().removeByUUID_G_Version(uuid, groupId, version);
	}

	/**
	* Returns the number of asset category versions where uuid = &#63; and groupId = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByUUID_G_Version(String uuid, long groupId,
		int version) {
		return getPersistence().countByUUID_G_Version(uuid, groupId, version);
	}

	/**
	* Returns all the asset category versions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the asset category versions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByUuid_C_PrevAndNext(
		long assetCategoryVersionId, String uuid, long companyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(assetCategoryVersionId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the asset category versions where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of asset category versions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching asset category versions
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the asset category versions where uuid = &#63; and companyId = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUuid_C_Version(String uuid,
		long companyId, int version) {
		return getPersistence().findByUuid_C_Version(uuid, companyId, version);
	}

	/**
	* Returns a range of all the asset category versions where uuid = &#63; and companyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUuid_C_Version(String uuid,
		long companyId, int version, int start, int end) {
		return getPersistence()
				   .findByUuid_C_Version(uuid, companyId, version, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where uuid = &#63; and companyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUuid_C_Version(String uuid,
		long companyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByUuid_C_Version(uuid, companyId, version, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where uuid = &#63; and companyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByUuid_C_Version(String uuid,
		long companyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C_Version(uuid, companyId, version, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63; and companyId = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByUuid_C_Version_First(String uuid,
		long companyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByUuid_C_Version_First(uuid, companyId, version,
			orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63; and companyId = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByUuid_C_Version_First(
		String uuid, long companyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Version_First(uuid, companyId, version,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63; and companyId = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByUuid_C_Version_Last(String uuid,
		long companyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByUuid_C_Version_Last(uuid, companyId, version,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63; and companyId = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByUuid_C_Version_Last(String uuid,
		long companyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Version_Last(uuid, companyId, version,
			orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where uuid = &#63; and companyId = &#63; and version = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param uuid the uuid
	* @param companyId the company ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByUuid_C_Version_PrevAndNext(
		long assetCategoryVersionId, String uuid, long companyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByUuid_C_Version_PrevAndNext(assetCategoryVersionId,
			uuid, companyId, version, orderByComparator);
	}

	/**
	* Removes all the asset category versions where uuid = &#63; and companyId = &#63; and version = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param version the version
	*/
	public static void removeByUuid_C_Version(String uuid, long companyId,
		int version) {
		getPersistence().removeByUuid_C_Version(uuid, companyId, version);
	}

	/**
	* Returns the number of asset category versions where uuid = &#63; and companyId = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByUuid_C_Version(String uuid, long companyId,
		int version) {
		return getPersistence().countByUuid_C_Version(uuid, companyId, version);
	}

	/**
	* Returns all the asset category versions where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the asset category versions where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByGroupId(long groupId,
		int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByGroupId_First(long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByGroupId_First(long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByGroupId_Last(long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByGroupId_Last(long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where groupId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByGroupId_PrevAndNext(
		long assetCategoryVersionId, long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(assetCategoryVersionId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the asset category versions where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of asset category versions where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching asset category versions
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the asset category versions where groupId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByGroupId_Version(
		long groupId, int version) {
		return getPersistence().findByGroupId_Version(groupId, version);
	}

	/**
	* Returns a range of all the asset category versions where groupId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByGroupId_Version(
		long groupId, int version, int start, int end) {
		return getPersistence()
				   .findByGroupId_Version(groupId, version, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByGroupId_Version(
		long groupId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByGroupId_Version(groupId, version, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByGroupId_Version(
		long groupId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId_Version(groupId, version, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByGroupId_Version_First(
		long groupId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByGroupId_Version_First(groupId, version,
			orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByGroupId_Version_First(
		long groupId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByGroupId_Version_First(groupId, version,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByGroupId_Version_Last(
		long groupId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByGroupId_Version_Last(groupId, version,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByGroupId_Version_Last(
		long groupId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByGroupId_Version_Last(groupId, version,
			orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where groupId = &#63; and version = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param groupId the group ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByGroupId_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByGroupId_Version_PrevAndNext(assetCategoryVersionId,
			groupId, version, orderByComparator);
	}

	/**
	* Removes all the asset category versions where groupId = &#63; and version = &#63; from the database.
	*
	* @param groupId the group ID
	* @param version the version
	*/
	public static void removeByGroupId_Version(long groupId, int version) {
		getPersistence().removeByGroupId_Version(groupId, version);
	}

	/**
	* Returns the number of asset category versions where groupId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByGroupId_Version(long groupId, int version) {
		return getPersistence().countByGroupId_Version(groupId, version);
	}

	/**
	* Returns all the asset category versions where parentCategoryId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByParentCategoryId(
		long parentCategoryId) {
		return getPersistence().findByParentCategoryId(parentCategoryId);
	}

	/**
	* Returns a range of all the asset category versions where parentCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByParentCategoryId(
		long parentCategoryId, int start, int end) {
		return getPersistence()
				   .findByParentCategoryId(parentCategoryId, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where parentCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByParentCategoryId(
		long parentCategoryId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByParentCategoryId(parentCategoryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where parentCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByParentCategoryId(
		long parentCategoryId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByParentCategoryId(parentCategoryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByParentCategoryId_First(
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByParentCategoryId_First(parentCategoryId,
			orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByParentCategoryId_First(
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByParentCategoryId_First(parentCategoryId,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByParentCategoryId_Last(
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByParentCategoryId_Last(parentCategoryId,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByParentCategoryId_Last(
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByParentCategoryId_Last(parentCategoryId,
			orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where parentCategoryId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByParentCategoryId_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByParentCategoryId_PrevAndNext(assetCategoryVersionId,
			parentCategoryId, orderByComparator);
	}

	/**
	* Removes all the asset category versions where parentCategoryId = &#63; from the database.
	*
	* @param parentCategoryId the parent category ID
	*/
	public static void removeByParentCategoryId(long parentCategoryId) {
		getPersistence().removeByParentCategoryId(parentCategoryId);
	}

	/**
	* Returns the number of asset category versions where parentCategoryId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @return the number of matching asset category versions
	*/
	public static int countByParentCategoryId(long parentCategoryId) {
		return getPersistence().countByParentCategoryId(parentCategoryId);
	}

	/**
	* Returns all the asset category versions where parentCategoryId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByParentCategoryId_Version(
		long parentCategoryId, int version) {
		return getPersistence()
				   .findByParentCategoryId_Version(parentCategoryId, version);
	}

	/**
	* Returns a range of all the asset category versions where parentCategoryId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByParentCategoryId_Version(
		long parentCategoryId, int version, int start, int end) {
		return getPersistence()
				   .findByParentCategoryId_Version(parentCategoryId, version,
			start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where parentCategoryId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByParentCategoryId_Version(
		long parentCategoryId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByParentCategoryId_Version(parentCategoryId, version,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where parentCategoryId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByParentCategoryId_Version(
		long parentCategoryId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByParentCategoryId_Version(parentCategoryId, version,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByParentCategoryId_Version_First(
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByParentCategoryId_Version_First(parentCategoryId,
			version, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByParentCategoryId_Version_First(
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByParentCategoryId_Version_First(parentCategoryId,
			version, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByParentCategoryId_Version_Last(
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByParentCategoryId_Version_Last(parentCategoryId,
			version, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByParentCategoryId_Version_Last(
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByParentCategoryId_Version_Last(parentCategoryId,
			version, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where parentCategoryId = &#63; and version = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByParentCategoryId_Version_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByParentCategoryId_Version_PrevAndNext(assetCategoryVersionId,
			parentCategoryId, version, orderByComparator);
	}

	/**
	* Removes all the asset category versions where parentCategoryId = &#63; and version = &#63; from the database.
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	*/
	public static void removeByParentCategoryId_Version(long parentCategoryId,
		int version) {
		getPersistence()
			.removeByParentCategoryId_Version(parentCategoryId, version);
	}

	/**
	* Returns the number of asset category versions where parentCategoryId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByParentCategoryId_Version(long parentCategoryId,
		int version) {
		return getPersistence()
				   .countByParentCategoryId_Version(parentCategoryId, version);
	}

	/**
	* Returns all the asset category versions where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByVocabularyId(
		long vocabularyId) {
		return getPersistence().findByVocabularyId(vocabularyId);
	}

	/**
	* Returns a range of all the asset category versions where vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByVocabularyId(
		long vocabularyId, int start, int end) {
		return getPersistence().findByVocabularyId(vocabularyId, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByVocabularyId(
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByVocabularyId(vocabularyId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByVocabularyId(
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByVocabularyId(vocabularyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByVocabularyId_First(
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByVocabularyId_First(vocabularyId, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByVocabularyId_First(
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByVocabularyId_First(vocabularyId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByVocabularyId_Last(
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByVocabularyId_Last(vocabularyId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByVocabularyId_Last(
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByVocabularyId_Last(vocabularyId, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where vocabularyId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByVocabularyId_PrevAndNext(
		long assetCategoryVersionId, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByVocabularyId_PrevAndNext(assetCategoryVersionId,
			vocabularyId, orderByComparator);
	}

	/**
	* Removes all the asset category versions where vocabularyId = &#63; from the database.
	*
	* @param vocabularyId the vocabulary ID
	*/
	public static void removeByVocabularyId(long vocabularyId) {
		getPersistence().removeByVocabularyId(vocabularyId);
	}

	/**
	* Returns the number of asset category versions where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @return the number of matching asset category versions
	*/
	public static int countByVocabularyId(long vocabularyId) {
		return getPersistence().countByVocabularyId(vocabularyId);
	}

	/**
	* Returns all the asset category versions where vocabularyId = &#63; and version = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByVocabularyId_Version(
		long vocabularyId, int version) {
		return getPersistence().findByVocabularyId_Version(vocabularyId, version);
	}

	/**
	* Returns a range of all the asset category versions where vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByVocabularyId_Version(
		long vocabularyId, int version, int start, int end) {
		return getPersistence()
				   .findByVocabularyId_Version(vocabularyId, version, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByVocabularyId_Version(
		long vocabularyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByVocabularyId_Version(vocabularyId, version, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByVocabularyId_Version(
		long vocabularyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByVocabularyId_Version(vocabularyId, version, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where vocabularyId = &#63; and version = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByVocabularyId_Version_First(
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByVocabularyId_Version_First(vocabularyId, version,
			orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where vocabularyId = &#63; and version = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByVocabularyId_Version_First(
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByVocabularyId_Version_First(vocabularyId, version,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where vocabularyId = &#63; and version = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByVocabularyId_Version_Last(
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByVocabularyId_Version_Last(vocabularyId, version,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where vocabularyId = &#63; and version = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByVocabularyId_Version_Last(
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByVocabularyId_Version_Last(vocabularyId, version,
			orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where vocabularyId = &#63; and version = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByVocabularyId_Version_PrevAndNext(
		long assetCategoryVersionId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByVocabularyId_Version_PrevAndNext(assetCategoryVersionId,
			vocabularyId, version, orderByComparator);
	}

	/**
	* Removes all the asset category versions where vocabularyId = &#63; and version = &#63; from the database.
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	*/
	public static void removeByVocabularyId_Version(long vocabularyId,
		int version) {
		getPersistence().removeByVocabularyId_Version(vocabularyId, version);
	}

	/**
	* Returns the number of asset category versions where vocabularyId = &#63; and version = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByVocabularyId_Version(long vocabularyId, int version) {
		return getPersistence()
				   .countByVocabularyId_Version(vocabularyId, version);
	}

	/**
	* Returns all the asset category versions where groupId = &#63; and parentCategoryId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P(long groupId,
		long parentCategoryId) {
		return getPersistence().findByG_P(groupId, parentCategoryId);
	}

	/**
	* Returns a range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P(long groupId,
		long parentCategoryId, int start, int end) {
		return getPersistence().findByG_P(groupId, parentCategoryId, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P(long groupId,
		long parentCategoryId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByG_P(groupId, parentCategoryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P(long groupId,
		long parentCategoryId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_P(groupId, parentCategoryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_P_First(long groupId,
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_First(groupId, parentCategoryId, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_P_First(long groupId,
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_First(groupId, parentCategoryId,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_P_Last(long groupId,
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_Last(groupId, parentCategoryId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_P_Last(long groupId,
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_Last(groupId, parentCategoryId, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByG_P_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_PrevAndNext(assetCategoryVersionId, groupId,
			parentCategoryId, orderByComparator);
	}

	/**
	* Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	*/
	public static void removeByG_P(long groupId, long parentCategoryId) {
		getPersistence().removeByG_P(groupId, parentCategoryId);
	}

	/**
	* Returns the number of asset category versions where groupId = &#63; and parentCategoryId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @return the number of matching asset category versions
	*/
	public static int countByG_P(long groupId, long parentCategoryId) {
		return getPersistence().countByG_P(groupId, parentCategoryId);
	}

	/**
	* Returns all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_Version(long groupId,
		long parentCategoryId, int version) {
		return getPersistence()
				   .findByG_P_Version(groupId, parentCategoryId, version);
	}

	/**
	* Returns a range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_Version(long groupId,
		long parentCategoryId, int version, int start, int end) {
		return getPersistence()
				   .findByG_P_Version(groupId, parentCategoryId, version,
			start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_Version(long groupId,
		long parentCategoryId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByG_P_Version(groupId, parentCategoryId, version,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_Version(long groupId,
		long parentCategoryId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_P_Version(groupId, parentCategoryId, version,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_P_Version_First(long groupId,
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_Version_First(groupId, parentCategoryId, version,
			orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_P_Version_First(long groupId,
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_Version_First(groupId, parentCategoryId,
			version, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_P_Version_Last(long groupId,
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_Version_Last(groupId, parentCategoryId, version,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_P_Version_Last(long groupId,
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_Version_Last(groupId, parentCategoryId, version,
			orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and version = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByG_P_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_Version_PrevAndNext(assetCategoryVersionId,
			groupId, parentCategoryId, version, orderByComparator);
	}

	/**
	* Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and version = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param version the version
	*/
	public static void removeByG_P_Version(long groupId, long parentCategoryId,
		int version) {
		getPersistence().removeByG_P_Version(groupId, parentCategoryId, version);
	}

	/**
	* Returns the number of asset category versions where groupId = &#63; and parentCategoryId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByG_P_Version(long groupId, long parentCategoryId,
		int version) {
		return getPersistence()
				   .countByG_P_Version(groupId, parentCategoryId, version);
	}

	/**
	* Returns all the asset category versions where groupId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_V(long groupId,
		long vocabularyId) {
		return getPersistence().findByG_V(groupId, vocabularyId);
	}

	/**
	* Returns a range of all the asset category versions where groupId = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_V(long groupId,
		long vocabularyId, int start, int end) {
		return getPersistence().findByG_V(groupId, vocabularyId, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_V(long groupId,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByG_V(groupId, vocabularyId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_V(long groupId,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_V(groupId, vocabularyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_V_First(long groupId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_V_First(groupId, vocabularyId, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_V_First(long groupId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_V_First(groupId, vocabularyId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_V_Last(long groupId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_V_Last(groupId, vocabularyId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_V_Last(long groupId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_V_Last(groupId, vocabularyId, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByG_V_PrevAndNext(
		long assetCategoryVersionId, long groupId, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_V_PrevAndNext(assetCategoryVersionId, groupId,
			vocabularyId, orderByComparator);
	}

	/**
	* Removes all the asset category versions where groupId = &#63; and vocabularyId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	*/
	public static void removeByG_V(long groupId, long vocabularyId) {
		getPersistence().removeByG_V(groupId, vocabularyId);
	}

	/**
	* Returns the number of asset category versions where groupId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @return the number of matching asset category versions
	*/
	public static int countByG_V(long groupId, long vocabularyId) {
		return getPersistence().countByG_V(groupId, vocabularyId);
	}

	/**
	* Returns all the asset category versions where groupId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_V_Version(long groupId,
		long vocabularyId, int version) {
		return getPersistence().findByG_V_Version(groupId, vocabularyId, version);
	}

	/**
	* Returns a range of all the asset category versions where groupId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_V_Version(long groupId,
		long vocabularyId, int version, int start, int end) {
		return getPersistence()
				   .findByG_V_Version(groupId, vocabularyId, version, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_V_Version(long groupId,
		long vocabularyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByG_V_Version(groupId, vocabularyId, version, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_V_Version(long groupId,
		long vocabularyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_V_Version(groupId, vocabularyId, version, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_V_Version_First(long groupId,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_V_Version_First(groupId, vocabularyId, version,
			orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_V_Version_First(long groupId,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_V_Version_First(groupId, vocabularyId, version,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_V_Version_Last(long groupId,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_V_Version_Last(groupId, vocabularyId, version,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_V_Version_Last(long groupId,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_V_Version_Last(groupId, vocabularyId, version,
			orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByG_V_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, long vocabularyId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_V_Version_PrevAndNext(assetCategoryVersionId,
			groupId, vocabularyId, version, orderByComparator);
	}

	/**
	* Removes all the asset category versions where groupId = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	*/
	public static void removeByG_V_Version(long groupId, long vocabularyId,
		int version) {
		getPersistence().removeByG_V_Version(groupId, vocabularyId, version);
	}

	/**
	* Returns the number of asset category versions where groupId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByG_V_Version(long groupId, long vocabularyId,
		int version) {
		return getPersistence()
				   .countByG_V_Version(groupId, vocabularyId, version);
	}

	/**
	* Returns all the asset category versions where parentCategoryId = &#63; and name = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_N(long parentCategoryId,
		String name) {
		return getPersistence().findByP_N(parentCategoryId, name);
	}

	/**
	* Returns a range of all the asset category versions where parentCategoryId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_N(long parentCategoryId,
		String name, int start, int end) {
		return getPersistence().findByP_N(parentCategoryId, name, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where parentCategoryId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_N(long parentCategoryId,
		String name, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByP_N(parentCategoryId, name, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where parentCategoryId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_N(long parentCategoryId,
		String name, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByP_N(parentCategoryId, name, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and name = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByP_N_First(long parentCategoryId,
		String name, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByP_N_First(parentCategoryId, name, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and name = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByP_N_First(long parentCategoryId,
		String name, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByP_N_First(parentCategoryId, name, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and name = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByP_N_Last(long parentCategoryId,
		String name, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByP_N_Last(parentCategoryId, name, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and name = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByP_N_Last(long parentCategoryId,
		String name, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByP_N_Last(parentCategoryId, name, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where parentCategoryId = &#63; and name = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByP_N_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, String name,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByP_N_PrevAndNext(assetCategoryVersionId,
			parentCategoryId, name, orderByComparator);
	}

	/**
	* Removes all the asset category versions where parentCategoryId = &#63; and name = &#63; from the database.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	*/
	public static void removeByP_N(long parentCategoryId, String name) {
		getPersistence().removeByP_N(parentCategoryId, name);
	}

	/**
	* Returns the number of asset category versions where parentCategoryId = &#63; and name = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @return the number of matching asset category versions
	*/
	public static int countByP_N(long parentCategoryId, String name) {
		return getPersistence().countByP_N(parentCategoryId, name);
	}

	/**
	* Returns all the asset category versions where parentCategoryId = &#63; and name = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param version the version
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_N_Version(
		long parentCategoryId, String name, int version) {
		return getPersistence()
				   .findByP_N_Version(parentCategoryId, name, version);
	}

	/**
	* Returns a range of all the asset category versions where parentCategoryId = &#63; and name = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_N_Version(
		long parentCategoryId, String name, int version, int start, int end) {
		return getPersistence()
				   .findByP_N_Version(parentCategoryId, name, version, start,
			end);
	}

	/**
	* Returns an ordered range of all the asset category versions where parentCategoryId = &#63; and name = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_N_Version(
		long parentCategoryId, String name, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByP_N_Version(parentCategoryId, name, version, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where parentCategoryId = &#63; and name = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_N_Version(
		long parentCategoryId, String name, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByP_N_Version(parentCategoryId, name, version, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and name = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByP_N_Version_First(
		long parentCategoryId, String name, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByP_N_Version_First(parentCategoryId, name, version,
			orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and name = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByP_N_Version_First(
		long parentCategoryId, String name, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByP_N_Version_First(parentCategoryId, name, version,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and name = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByP_N_Version_Last(
		long parentCategoryId, String name, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByP_N_Version_Last(parentCategoryId, name, version,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and name = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByP_N_Version_Last(
		long parentCategoryId, String name, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByP_N_Version_Last(parentCategoryId, name, version,
			orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where parentCategoryId = &#63; and name = &#63; and version = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByP_N_Version_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, String name,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByP_N_Version_PrevAndNext(assetCategoryVersionId,
			parentCategoryId, name, version, orderByComparator);
	}

	/**
	* Removes all the asset category versions where parentCategoryId = &#63; and name = &#63; and version = &#63; from the database.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param version the version
	*/
	public static void removeByP_N_Version(long parentCategoryId, String name,
		int version) {
		getPersistence().removeByP_N_Version(parentCategoryId, name, version);
	}

	/**
	* Returns the number of asset category versions where parentCategoryId = &#63; and name = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByP_N_Version(long parentCategoryId, String name,
		int version) {
		return getPersistence()
				   .countByP_N_Version(parentCategoryId, name, version);
	}

	/**
	* Returns all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_V(long parentCategoryId,
		long vocabularyId) {
		return getPersistence().findByP_V(parentCategoryId, vocabularyId);
	}

	/**
	* Returns a range of all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_V(long parentCategoryId,
		long vocabularyId, int start, int end) {
		return getPersistence()
				   .findByP_V(parentCategoryId, vocabularyId, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_V(long parentCategoryId,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByP_V(parentCategoryId, vocabularyId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_V(long parentCategoryId,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByP_V(parentCategoryId, vocabularyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByP_V_First(long parentCategoryId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByP_V_First(parentCategoryId, vocabularyId,
			orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByP_V_First(long parentCategoryId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByP_V_First(parentCategoryId, vocabularyId,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByP_V_Last(long parentCategoryId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByP_V_Last(parentCategoryId, vocabularyId,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByP_V_Last(long parentCategoryId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByP_V_Last(parentCategoryId, vocabularyId,
			orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByP_V_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByP_V_PrevAndNext(assetCategoryVersionId,
			parentCategoryId, vocabularyId, orderByComparator);
	}

	/**
	* Removes all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63; from the database.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	*/
	public static void removeByP_V(long parentCategoryId, long vocabularyId) {
		getPersistence().removeByP_V(parentCategoryId, vocabularyId);
	}

	/**
	* Returns the number of asset category versions where parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @return the number of matching asset category versions
	*/
	public static int countByP_V(long parentCategoryId, long vocabularyId) {
		return getPersistence().countByP_V(parentCategoryId, vocabularyId);
	}

	/**
	* Returns all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_V_Version(
		long parentCategoryId, long vocabularyId, int version) {
		return getPersistence()
				   .findByP_V_Version(parentCategoryId, vocabularyId, version);
	}

	/**
	* Returns a range of all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_V_Version(
		long parentCategoryId, long vocabularyId, int version, int start,
		int end) {
		return getPersistence()
				   .findByP_V_Version(parentCategoryId, vocabularyId, version,
			start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_V_Version(
		long parentCategoryId, long vocabularyId, int version, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByP_V_Version(parentCategoryId, vocabularyId, version,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_V_Version(
		long parentCategoryId, long vocabularyId, int version, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByP_V_Version(parentCategoryId, vocabularyId, version,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByP_V_Version_First(
		long parentCategoryId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByP_V_Version_First(parentCategoryId, vocabularyId,
			version, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByP_V_Version_First(
		long parentCategoryId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByP_V_Version_First(parentCategoryId, vocabularyId,
			version, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByP_V_Version_Last(
		long parentCategoryId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByP_V_Version_Last(parentCategoryId, vocabularyId,
			version, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByP_V_Version_Last(
		long parentCategoryId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByP_V_Version_Last(parentCategoryId, vocabularyId,
			version, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByP_V_Version_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, long vocabularyId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByP_V_Version_PrevAndNext(assetCategoryVersionId,
			parentCategoryId, vocabularyId, version, orderByComparator);
	}

	/**
	* Removes all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	*/
	public static void removeByP_V_Version(long parentCategoryId,
		long vocabularyId, int version) {
		getPersistence()
			.removeByP_V_Version(parentCategoryId, vocabularyId, version);
	}

	/**
	* Returns the number of asset category versions where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByP_V_Version(long parentCategoryId,
		long vocabularyId, int version) {
		return getPersistence()
				   .countByP_V_Version(parentCategoryId, vocabularyId, version);
	}

	/**
	* Returns all the asset category versions where name = &#63; and vocabularyId = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByN_V(String name,
		long vocabularyId) {
		return getPersistence().findByN_V(name, vocabularyId);
	}

	/**
	* Returns a range of all the asset category versions where name = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByN_V(String name,
		long vocabularyId, int start, int end) {
		return getPersistence().findByN_V(name, vocabularyId, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where name = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByN_V(String name,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByN_V(name, vocabularyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where name = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByN_V(String name,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByN_V(name, vocabularyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where name = &#63; and vocabularyId = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByN_V_First(String name,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByN_V_First(name, vocabularyId, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where name = &#63; and vocabularyId = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByN_V_First(String name,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByN_V_First(name, vocabularyId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where name = &#63; and vocabularyId = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByN_V_Last(String name,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByN_V_Last(name, vocabularyId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where name = &#63; and vocabularyId = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByN_V_Last(String name,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByN_V_Last(name, vocabularyId, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where name = &#63; and vocabularyId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByN_V_PrevAndNext(
		long assetCategoryVersionId, String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByN_V_PrevAndNext(assetCategoryVersionId, name,
			vocabularyId, orderByComparator);
	}

	/**
	* Removes all the asset category versions where name = &#63; and vocabularyId = &#63; from the database.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	*/
	public static void removeByN_V(String name, long vocabularyId) {
		getPersistence().removeByN_V(name, vocabularyId);
	}

	/**
	* Returns the number of asset category versions where name = &#63; and vocabularyId = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @return the number of matching asset category versions
	*/
	public static int countByN_V(String name, long vocabularyId) {
		return getPersistence().countByN_V(name, vocabularyId);
	}

	/**
	* Returns all the asset category versions where name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByN_V_Version(String name,
		long vocabularyId, int version) {
		return getPersistence().findByN_V_Version(name, vocabularyId, version);
	}

	/**
	* Returns a range of all the asset category versions where name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByN_V_Version(String name,
		long vocabularyId, int version, int start, int end) {
		return getPersistence()
				   .findByN_V_Version(name, vocabularyId, version, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByN_V_Version(String name,
		long vocabularyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByN_V_Version(name, vocabularyId, version, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByN_V_Version(String name,
		long vocabularyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByN_V_Version(name, vocabularyId, version, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByN_V_Version_First(String name,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByN_V_Version_First(name, vocabularyId, version,
			orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByN_V_Version_First(String name,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByN_V_Version_First(name, vocabularyId, version,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByN_V_Version_Last(String name,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByN_V_Version_Last(name, vocabularyId, version,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByN_V_Version_Last(String name,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByN_V_Version_Last(name, vocabularyId, version,
			orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByN_V_Version_PrevAndNext(
		long assetCategoryVersionId, String name, long vocabularyId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByN_V_Version_PrevAndNext(assetCategoryVersionId, name,
			vocabularyId, version, orderByComparator);
	}

	/**
	* Removes all the asset category versions where name = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	*/
	public static void removeByN_V_Version(String name, long vocabularyId,
		int version) {
		getPersistence().removeByN_V_Version(name, vocabularyId, version);
	}

	/**
	* Returns the number of asset category versions where name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByN_V_Version(String name, long vocabularyId,
		int version) {
		return getPersistence().countByN_V_Version(name, vocabularyId, version);
	}

	/**
	* Returns all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_V(long groupId,
		long parentCategoryId, long vocabularyId) {
		return getPersistence()
				   .findByG_P_V(groupId, parentCategoryId, vocabularyId);
	}

	/**
	* Returns a range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_V(long groupId,
		long parentCategoryId, long vocabularyId, int start, int end) {
		return getPersistence()
				   .findByG_P_V(groupId, parentCategoryId, vocabularyId, start,
			end);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_V(long groupId,
		long parentCategoryId, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByG_P_V(groupId, parentCategoryId, vocabularyId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_V(long groupId,
		long parentCategoryId, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_P_V(groupId, parentCategoryId, vocabularyId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_P_V_First(long groupId,
		long parentCategoryId, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_V_First(groupId, parentCategoryId, vocabularyId,
			orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_P_V_First(long groupId,
		long parentCategoryId, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_V_First(groupId, parentCategoryId, vocabularyId,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_P_V_Last(long groupId,
		long parentCategoryId, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_V_Last(groupId, parentCategoryId, vocabularyId,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_P_V_Last(long groupId,
		long parentCategoryId, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_V_Last(groupId, parentCategoryId, vocabularyId,
			orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByG_P_V_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_V_PrevAndNext(assetCategoryVersionId, groupId,
			parentCategoryId, vocabularyId, orderByComparator);
	}

	/**
	* Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	*/
	public static void removeByG_P_V(long groupId, long parentCategoryId,
		long vocabularyId) {
		getPersistence().removeByG_P_V(groupId, parentCategoryId, vocabularyId);
	}

	/**
	* Returns the number of asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @return the number of matching asset category versions
	*/
	public static int countByG_P_V(long groupId, long parentCategoryId,
		long vocabularyId) {
		return getPersistence()
				   .countByG_P_V(groupId, parentCategoryId, vocabularyId);
	}

	/**
	* Returns all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_V_Version(long groupId,
		long parentCategoryId, long vocabularyId, int version) {
		return getPersistence()
				   .findByG_P_V_Version(groupId, parentCategoryId,
			vocabularyId, version);
	}

	/**
	* Returns a range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_V_Version(long groupId,
		long parentCategoryId, long vocabularyId, int version, int start,
		int end) {
		return getPersistence()
				   .findByG_P_V_Version(groupId, parentCategoryId,
			vocabularyId, version, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_V_Version(long groupId,
		long parentCategoryId, long vocabularyId, int version, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByG_P_V_Version(groupId, parentCategoryId,
			vocabularyId, version, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_V_Version(long groupId,
		long parentCategoryId, long vocabularyId, int version, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_P_V_Version(groupId, parentCategoryId,
			vocabularyId, version, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_P_V_Version_First(long groupId,
		long parentCategoryId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_V_Version_First(groupId, parentCategoryId,
			vocabularyId, version, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_P_V_Version_First(
		long groupId, long parentCategoryId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_V_Version_First(groupId, parentCategoryId,
			vocabularyId, version, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_P_V_Version_Last(long groupId,
		long parentCategoryId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_V_Version_Last(groupId, parentCategoryId,
			vocabularyId, version, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_P_V_Version_Last(long groupId,
		long parentCategoryId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_V_Version_Last(groupId, parentCategoryId,
			vocabularyId, version, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByG_P_V_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_V_Version_PrevAndNext(assetCategoryVersionId,
			groupId, parentCategoryId, vocabularyId, version, orderByComparator);
	}

	/**
	* Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	*/
	public static void removeByG_P_V_Version(long groupId,
		long parentCategoryId, long vocabularyId, int version) {
		getPersistence()
			.removeByG_P_V_Version(groupId, parentCategoryId, vocabularyId,
			version);
	}

	/**
	* Returns the number of asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByG_P_V_Version(long groupId, long parentCategoryId,
		long vocabularyId, int version) {
		return getPersistence()
				   .countByG_P_V_Version(groupId, parentCategoryId,
			vocabularyId, version);
	}

	/**
	* Returns all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_LikeN_V(long groupId,
		String name, long vocabularyId) {
		return getPersistence().findByG_LikeN_V(groupId, name, vocabularyId);
	}

	/**
	* Returns a range of all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_LikeN_V(long groupId,
		String name, long vocabularyId, int start, int end) {
		return getPersistence()
				   .findByG_LikeN_V(groupId, name, vocabularyId, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_LikeN_V(long groupId,
		String name, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByG_LikeN_V(groupId, name, vocabularyId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_LikeN_V(long groupId,
		String name, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_LikeN_V(groupId, name, vocabularyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_LikeN_V_First(long groupId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_LikeN_V_First(groupId, name, vocabularyId,
			orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_LikeN_V_First(long groupId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_LikeN_V_First(groupId, name, vocabularyId,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_LikeN_V_Last(long groupId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_LikeN_V_Last(groupId, name, vocabularyId,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_LikeN_V_Last(long groupId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_LikeN_V_Last(groupId, name, vocabularyId,
			orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where groupId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByG_LikeN_V_PrevAndNext(
		long assetCategoryVersionId, long groupId, String name,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_LikeN_V_PrevAndNext(assetCategoryVersionId,
			groupId, name, vocabularyId, orderByComparator);
	}

	/**
	* Removes all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	*/
	public static void removeByG_LikeN_V(long groupId, String name,
		long vocabularyId) {
		getPersistence().removeByG_LikeN_V(groupId, name, vocabularyId);
	}

	/**
	* Returns the number of asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @return the number of matching asset category versions
	*/
	public static int countByG_LikeN_V(long groupId, String name,
		long vocabularyId) {
		return getPersistence().countByG_LikeN_V(groupId, name, vocabularyId);
	}

	/**
	* Returns all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_LikeN_V_Version(
		long groupId, String name, long vocabularyId, int version) {
		return getPersistence()
				   .findByG_LikeN_V_Version(groupId, name, vocabularyId, version);
	}

	/**
	* Returns a range of all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_LikeN_V_Version(
		long groupId, String name, long vocabularyId, int version, int start,
		int end) {
		return getPersistence()
				   .findByG_LikeN_V_Version(groupId, name, vocabularyId,
			version, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_LikeN_V_Version(
		long groupId, String name, long vocabularyId, int version, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByG_LikeN_V_Version(groupId, name, vocabularyId,
			version, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_LikeN_V_Version(
		long groupId, String name, long vocabularyId, int version, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_LikeN_V_Version(groupId, name, vocabularyId,
			version, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_LikeN_V_Version_First(
		long groupId, String name, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_LikeN_V_Version_First(groupId, name, vocabularyId,
			version, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_LikeN_V_Version_First(
		long groupId, String name, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_LikeN_V_Version_First(groupId, name, vocabularyId,
			version, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_LikeN_V_Version_Last(
		long groupId, String name, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_LikeN_V_Version_Last(groupId, name, vocabularyId,
			version, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_LikeN_V_Version_Last(
		long groupId, String name, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_LikeN_V_Version_Last(groupId, name, vocabularyId,
			version, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where groupId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByG_LikeN_V_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, String name,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_LikeN_V_Version_PrevAndNext(assetCategoryVersionId,
			groupId, name, vocabularyId, version, orderByComparator);
	}

	/**
	* Removes all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	*/
	public static void removeByG_LikeN_V_Version(long groupId, String name,
		long vocabularyId, int version) {
		getPersistence()
			.removeByG_LikeN_V_Version(groupId, name, vocabularyId, version);
	}

	/**
	* Returns the number of asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByG_LikeN_V_Version(long groupId, String name,
		long vocabularyId, int version) {
		return getPersistence()
				   .countByG_LikeN_V_Version(groupId, name, vocabularyId,
			version);
	}

	/**
	* Returns all the asset category versions where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_N_V(
		long parentCategoryId, String name, long vocabularyId) {
		return getPersistence().findByP_N_V(parentCategoryId, name, vocabularyId);
	}

	/**
	* Returns a range of all the asset category versions where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_N_V(
		long parentCategoryId, String name, long vocabularyId, int start,
		int end) {
		return getPersistence()
				   .findByP_N_V(parentCategoryId, name, vocabularyId, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_N_V(
		long parentCategoryId, String name, long vocabularyId, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByP_N_V(parentCategoryId, name, vocabularyId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByP_N_V(
		long parentCategoryId, String name, long vocabularyId, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByP_N_V(parentCategoryId, name, vocabularyId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByP_N_V_First(
		long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByP_N_V_First(parentCategoryId, name, vocabularyId,
			orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByP_N_V_First(
		long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByP_N_V_First(parentCategoryId, name, vocabularyId,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByP_N_V_Last(long parentCategoryId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByP_N_V_Last(parentCategoryId, name, vocabularyId,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByP_N_V_Last(
		long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByP_N_V_Last(parentCategoryId, name, vocabularyId,
			orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByP_N_V_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, String name,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByP_N_V_PrevAndNext(assetCategoryVersionId,
			parentCategoryId, name, vocabularyId, orderByComparator);
	}

	/**
	* Removes all the asset category versions where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; from the database.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	*/
	public static void removeByP_N_V(long parentCategoryId, String name,
		long vocabularyId) {
		getPersistence().removeByP_N_V(parentCategoryId, name, vocabularyId);
	}

	/**
	* Returns the number of asset category versions where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @return the number of matching asset category versions
	*/
	public static int countByP_N_V(long parentCategoryId, String name,
		long vocabularyId) {
		return getPersistence()
				   .countByP_N_V(parentCategoryId, name, vocabularyId);
	}

	/**
	* Returns the asset category version where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63; or throws a <code>NoSuchCategoryVersionException</code> if it could not be found.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByP_N_V_Version(
		long parentCategoryId, String name, long vocabularyId, int version)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByP_N_V_Version(parentCategoryId, name, vocabularyId,
			version);
	}

	/**
	* Returns the asset category version where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByP_N_V_Version(
		long parentCategoryId, String name, long vocabularyId, int version) {
		return getPersistence()
				   .fetchByP_N_V_Version(parentCategoryId, name, vocabularyId,
			version);
	}

	/**
	* Returns the asset category version where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByP_N_V_Version(
		long parentCategoryId, String name, long vocabularyId, int version,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByP_N_V_Version(parentCategoryId, name, vocabularyId,
			version, retrieveFromCache);
	}

	/**
	* Removes the asset category version where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the asset category version that was removed
	*/
	public static AssetCategoryVersion removeByP_N_V_Version(
		long parentCategoryId, String name, long vocabularyId, int version)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .removeByP_N_V_Version(parentCategoryId, name, vocabularyId,
			version);
	}

	/**
	* Returns the number of asset category versions where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByP_N_V_Version(long parentCategoryId, String name,
		long vocabularyId, int version) {
		return getPersistence()
				   .countByP_N_V_Version(parentCategoryId, name, vocabularyId,
			version);
	}

	/**
	* Returns all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId) {
		return getPersistence()
				   .findByG_P_N_V(groupId, parentCategoryId, name, vocabularyId);
	}

	/**
	* Returns a range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId, int start,
		int end) {
		return getPersistence()
				   .findByG_P_N_V(groupId, parentCategoryId, name,
			vocabularyId, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByG_P_N_V(groupId, parentCategoryId, name,
			vocabularyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_P_N_V(groupId, parentCategoryId, name,
			vocabularyId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_P_N_V_First(long groupId,
		long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_N_V_First(groupId, parentCategoryId, name,
			vocabularyId, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_P_N_V_First(long groupId,
		long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_N_V_First(groupId, parentCategoryId, name,
			vocabularyId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_P_N_V_Last(long groupId,
		long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_N_V_Last(groupId, parentCategoryId, name,
			vocabularyId, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_P_N_V_Last(long groupId,
		long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_N_V_Last(groupId, parentCategoryId, name,
			vocabularyId, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByG_P_N_V_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_N_V_PrevAndNext(assetCategoryVersionId, groupId,
			parentCategoryId, name, vocabularyId, orderByComparator);
	}

	/**
	* Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	*/
	public static void removeByG_P_N_V(long groupId, long parentCategoryId,
		String name, long vocabularyId) {
		getPersistence()
			.removeByG_P_N_V(groupId, parentCategoryId, name, vocabularyId);
	}

	/**
	* Returns the number of asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @return the number of matching asset category versions
	*/
	public static int countByG_P_N_V(long groupId, long parentCategoryId,
		String name, long vocabularyId) {
		return getPersistence()
				   .countByG_P_N_V(groupId, parentCategoryId, name, vocabularyId);
	}

	/**
	* Returns all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_N_V_Version(
		long groupId, long parentCategoryId, String name, long vocabularyId,
		int version) {
		return getPersistence()
				   .findByG_P_N_V_Version(groupId, parentCategoryId, name,
			vocabularyId, version);
	}

	/**
	* Returns a range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_N_V_Version(
		long groupId, long parentCategoryId, String name, long vocabularyId,
		int version, int start, int end) {
		return getPersistence()
				   .findByG_P_N_V_Version(groupId, parentCategoryId, name,
			vocabularyId, version, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_N_V_Version(
		long groupId, long parentCategoryId, String name, long vocabularyId,
		int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByG_P_N_V_Version(groupId, parentCategoryId, name,
			vocabularyId, version, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByG_P_N_V_Version(
		long groupId, long parentCategoryId, String name, long vocabularyId,
		int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_P_N_V_Version(groupId, parentCategoryId, name,
			vocabularyId, version, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_P_N_V_Version_First(
		long groupId, long parentCategoryId, String name, long vocabularyId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_N_V_Version_First(groupId, parentCategoryId,
			name, vocabularyId, version, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_P_N_V_Version_First(
		long groupId, long parentCategoryId, String name, long vocabularyId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_N_V_Version_First(groupId, parentCategoryId,
			name, vocabularyId, version, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByG_P_N_V_Version_Last(
		long groupId, long parentCategoryId, String name, long vocabularyId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_N_V_Version_Last(groupId, parentCategoryId, name,
			vocabularyId, version, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByG_P_N_V_Version_Last(
		long groupId, long parentCategoryId, String name, long vocabularyId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_N_V_Version_Last(groupId, parentCategoryId,
			name, vocabularyId, version, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByG_P_N_V_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		String name, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByG_P_N_V_Version_PrevAndNext(assetCategoryVersionId,
			groupId, parentCategoryId, name, vocabularyId, version,
			orderByComparator);
	}

	/**
	* Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	*/
	public static void removeByG_P_N_V_Version(long groupId,
		long parentCategoryId, String name, long vocabularyId, int version) {
		getPersistence()
			.removeByG_P_N_V_Version(groupId, parentCategoryId, name,
			vocabularyId, version);
	}

	/**
	* Returns the number of asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByG_P_N_V_Version(long groupId,
		long parentCategoryId, String name, long vocabularyId, int version) {
		return getPersistence()
				   .countByG_P_N_V_Version(groupId, parentCategoryId, name,
			vocabularyId, version);
	}

	/**
	* Returns all the asset category versions where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByC_ERC(long companyId,
		String externalReferenceCode) {
		return getPersistence().findByC_ERC(companyId, externalReferenceCode);
	}

	/**
	* Returns a range of all the asset category versions where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByC_ERC(long companyId,
		String externalReferenceCode, int start, int end) {
		return getPersistence()
				   .findByC_ERC(companyId, externalReferenceCode, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByC_ERC(long companyId,
		String externalReferenceCode, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByC_ERC(companyId, externalReferenceCode, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByC_ERC(long companyId,
		String externalReferenceCode, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_ERC(companyId, externalReferenceCode, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByC_ERC_First(long companyId,
		String externalReferenceCode,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByC_ERC_First(companyId, externalReferenceCode,
			orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByC_ERC_First(long companyId,
		String externalReferenceCode,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByC_ERC_First(companyId, externalReferenceCode,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByC_ERC_Last(long companyId,
		String externalReferenceCode,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByC_ERC_Last(companyId, externalReferenceCode,
			orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByC_ERC_Last(long companyId,
		String externalReferenceCode,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByC_ERC_Last(companyId, externalReferenceCode,
			orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByC_ERC_PrevAndNext(
		long assetCategoryVersionId, long companyId,
		String externalReferenceCode,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByC_ERC_PrevAndNext(assetCategoryVersionId, companyId,
			externalReferenceCode, orderByComparator);
	}

	/**
	* Removes all the asset category versions where companyId = &#63; and externalReferenceCode = &#63; from the database.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	*/
	public static void removeByC_ERC(long companyId,
		String externalReferenceCode) {
		getPersistence().removeByC_ERC(companyId, externalReferenceCode);
	}

	/**
	* Returns the number of asset category versions where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the number of matching asset category versions
	*/
	public static int countByC_ERC(long companyId, String externalReferenceCode) {
		return getPersistence().countByC_ERC(companyId, externalReferenceCode);
	}

	/**
	* Returns all the asset category versions where companyId = &#63; and externalReferenceCode = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param version the version
	* @return the matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByC_ERC_Version(
		long companyId, String externalReferenceCode, int version) {
		return getPersistence()
				   .findByC_ERC_Version(companyId, externalReferenceCode,
			version);
	}

	/**
	* Returns a range of all the asset category versions where companyId = &#63; and externalReferenceCode = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByC_ERC_Version(
		long companyId, String externalReferenceCode, int version, int start,
		int end) {
		return getPersistence()
				   .findByC_ERC_Version(companyId, externalReferenceCode,
			version, start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions where companyId = &#63; and externalReferenceCode = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByC_ERC_Version(
		long companyId, String externalReferenceCode, int version, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .findByC_ERC_Version(companyId, externalReferenceCode,
			version, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions where companyId = &#63; and externalReferenceCode = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param version the version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching asset category versions
	*/
	public static List<AssetCategoryVersion> findByC_ERC_Version(
		long companyId, String externalReferenceCode, int version, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_ERC_Version(companyId, externalReferenceCode,
			version, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByC_ERC_Version_First(
		long companyId, String externalReferenceCode, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByC_ERC_Version_First(companyId, externalReferenceCode,
			version, orderByComparator);
	}

	/**
	* Returns the first asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByC_ERC_Version_First(
		long companyId, String externalReferenceCode, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByC_ERC_Version_First(companyId,
			externalReferenceCode, version, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion findByC_ERC_Version_Last(
		long companyId, String externalReferenceCode, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByC_ERC_Version_Last(companyId, externalReferenceCode,
			version, orderByComparator);
	}

	/**
	* Returns the last asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public static AssetCategoryVersion fetchByC_ERC_Version_Last(
		long companyId, String externalReferenceCode, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence()
				   .fetchByC_ERC_Version_Last(companyId, externalReferenceCode,
			version, orderByComparator);
	}

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63; and version = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion[] findByC_ERC_Version_PrevAndNext(
		long assetCategoryVersionId, long companyId,
		String externalReferenceCode, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence()
				   .findByC_ERC_Version_PrevAndNext(assetCategoryVersionId,
			companyId, externalReferenceCode, version, orderByComparator);
	}

	/**
	* Removes all the asset category versions where companyId = &#63; and externalReferenceCode = &#63; and version = &#63; from the database.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param version the version
	*/
	public static void removeByC_ERC_Version(long companyId,
		String externalReferenceCode, int version) {
		getPersistence()
			.removeByC_ERC_Version(companyId, externalReferenceCode, version);
	}

	/**
	* Returns the number of asset category versions where companyId = &#63; and externalReferenceCode = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public static int countByC_ERC_Version(long companyId,
		String externalReferenceCode, int version) {
		return getPersistence()
				   .countByC_ERC_Version(companyId, externalReferenceCode,
			version);
	}

	/**
	* Caches the asset category version in the entity cache if it is enabled.
	*
	* @param assetCategoryVersion the asset category version
	*/
	public static void cacheResult(AssetCategoryVersion assetCategoryVersion) {
		getPersistence().cacheResult(assetCategoryVersion);
	}

	/**
	* Caches the asset category versions in the entity cache if it is enabled.
	*
	* @param assetCategoryVersions the asset category versions
	*/
	public static void cacheResult(
		List<AssetCategoryVersion> assetCategoryVersions) {
		getPersistence().cacheResult(assetCategoryVersions);
	}

	/**
	* Creates a new asset category version with the primary key. Does not add the asset category version to the database.
	*
	* @param assetCategoryVersionId the primary key for the new asset category version
	* @return the new asset category version
	*/
	public static AssetCategoryVersion create(long assetCategoryVersionId) {
		return getPersistence().create(assetCategoryVersionId);
	}

	/**
	* Removes the asset category version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetCategoryVersionId the primary key of the asset category version
	* @return the asset category version that was removed
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion remove(long assetCategoryVersionId)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence().remove(assetCategoryVersionId);
	}

	public static AssetCategoryVersion updateImpl(
		AssetCategoryVersion assetCategoryVersion) {
		return getPersistence().updateImpl(assetCategoryVersion);
	}

	/**
	* Returns the asset category version with the primary key or throws a <code>NoSuchCategoryVersionException</code> if it could not be found.
	*
	* @param assetCategoryVersionId the primary key of the asset category version
	* @return the asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion findByPrimaryKey(
		long assetCategoryVersionId)
		throws com.liferay.asset.kernel.exception.NoSuchCategoryVersionException {
		return getPersistence().findByPrimaryKey(assetCategoryVersionId);
	}

	/**
	* Returns the asset category version with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetCategoryVersionId the primary key of the asset category version
	* @return the asset category version, or <code>null</code> if a asset category version with the primary key could not be found
	*/
	public static AssetCategoryVersion fetchByPrimaryKey(
		long assetCategoryVersionId) {
		return getPersistence().fetchByPrimaryKey(assetCategoryVersionId);
	}

	/**
	* Returns all the asset category versions.
	*
	* @return the asset category versions
	*/
	public static List<AssetCategoryVersion> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the asset category versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of asset category versions
	*/
	public static List<AssetCategoryVersion> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the asset category versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset category versions
	*/
	public static List<AssetCategoryVersion> findAll(int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the asset category versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of asset category versions
	*/
	public static List<AssetCategoryVersion> findAll(int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the asset category versions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset category versions.
	*
	* @return the number of asset category versions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of asset entries associated with the asset category version.
	*
	* @param pk the primary key of the asset category version
	* @return long[] of the primaryKeys of asset entries associated with the asset category version
	*/
	public static long[] getAssetEntryPrimaryKeys(long pk) {
		return getPersistence().getAssetEntryPrimaryKeys(pk);
	}

	/**
	* Returns all the asset entries associated with the asset category version.
	*
	* @param pk the primary key of the asset category version
	* @return the asset entries associated with the asset category version
	*/
	public static List<com.liferay.asset.kernel.model.AssetEntry> getAssetEntries(
		long pk) {
		return getPersistence().getAssetEntries(pk);
	}

	/**
	* Returns a range of all the asset entries associated with the asset category version.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the asset category version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @return the range of asset entries associated with the asset category version
	*/
	public static List<com.liferay.asset.kernel.model.AssetEntry> getAssetEntries(
		long pk, int start, int end) {
		return getPersistence().getAssetEntries(pk, start, end);
	}

	/**
	* Returns an ordered range of all the asset entries associated with the asset category version.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the asset category version
	* @param start the lower bound of the range of asset category versions
	* @param end the upper bound of the range of asset category versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset entries associated with the asset category version
	*/
	public static List<com.liferay.asset.kernel.model.AssetEntry> getAssetEntries(
		long pk, int start, int end,
		OrderByComparator<com.liferay.asset.kernel.model.AssetEntry> orderByComparator) {
		return getPersistence()
				   .getAssetEntries(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of asset entries associated with the asset category version.
	*
	* @param pk the primary key of the asset category version
	* @return the number of asset entries associated with the asset category version
	*/
	public static int getAssetEntriesSize(long pk) {
		return getPersistence().getAssetEntriesSize(pk);
	}

	/**
	* Returns <code>true</code> if the asset entry is associated with the asset category version.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntryPK the primary key of the asset entry
	* @return <code>true</code> if the asset entry is associated with the asset category version; <code>false</code> otherwise
	*/
	public static boolean containsAssetEntry(long pk, long assetEntryPK) {
		return getPersistence().containsAssetEntry(pk, assetEntryPK);
	}

	/**
	* Returns <code>true</code> if the asset category version has any asset entries associated with it.
	*
	* @param pk the primary key of the asset category version to check for associations with asset entries
	* @return <code>true</code> if the asset category version has any asset entries associated with it; <code>false</code> otherwise
	*/
	public static boolean containsAssetEntries(long pk) {
		return getPersistence().containsAssetEntries(pk);
	}

	/**
	* Adds an association between the asset category version and the asset entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntryPK the primary key of the asset entry
	*/
	public static void addAssetEntry(long pk, long assetEntryPK) {
		getPersistence().addAssetEntry(pk, assetEntryPK);
	}

	/**
	* Adds an association between the asset category version and the asset entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntry the asset entry
	*/
	public static void addAssetEntry(long pk,
		com.liferay.asset.kernel.model.AssetEntry assetEntry) {
		getPersistence().addAssetEntry(pk, assetEntry);
	}

	/**
	* Adds an association between the asset category version and the asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntryPKs the primary keys of the asset entries
	*/
	public static void addAssetEntries(long pk, long[] assetEntryPKs) {
		getPersistence().addAssetEntries(pk, assetEntryPKs);
	}

	/**
	* Adds an association between the asset category version and the asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntries the asset entries
	*/
	public static void addAssetEntries(long pk,
		List<com.liferay.asset.kernel.model.AssetEntry> assetEntries) {
		getPersistence().addAssetEntries(pk, assetEntries);
	}

	/**
	* Clears all associations between the asset category version and its asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version to clear the associated asset entries from
	*/
	public static void clearAssetEntries(long pk) {
		getPersistence().clearAssetEntries(pk);
	}

	/**
	* Removes the association between the asset category version and the asset entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntryPK the primary key of the asset entry
	*/
	public static void removeAssetEntry(long pk, long assetEntryPK) {
		getPersistence().removeAssetEntry(pk, assetEntryPK);
	}

	/**
	* Removes the association between the asset category version and the asset entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntry the asset entry
	*/
	public static void removeAssetEntry(long pk,
		com.liferay.asset.kernel.model.AssetEntry assetEntry) {
		getPersistence().removeAssetEntry(pk, assetEntry);
	}

	/**
	* Removes the association between the asset category version and the asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntryPKs the primary keys of the asset entries
	*/
	public static void removeAssetEntries(long pk, long[] assetEntryPKs) {
		getPersistence().removeAssetEntries(pk, assetEntryPKs);
	}

	/**
	* Removes the association between the asset category version and the asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntries the asset entries
	*/
	public static void removeAssetEntries(long pk,
		List<com.liferay.asset.kernel.model.AssetEntry> assetEntries) {
		getPersistence().removeAssetEntries(pk, assetEntries);
	}

	/**
	* Sets the asset entries associated with the asset category version, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntryPKs the primary keys of the asset entries to be associated with the asset category version
	*/
	public static void setAssetEntries(long pk, long[] assetEntryPKs) {
		getPersistence().setAssetEntries(pk, assetEntryPKs);
	}

	/**
	* Sets the asset entries associated with the asset category version, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntries the asset entries to be associated with the asset category version
	*/
	public static void setAssetEntries(long pk,
		List<com.liferay.asset.kernel.model.AssetEntry> assetEntries) {
		getPersistence().setAssetEntries(pk, assetEntries);
	}

	public static AssetCategoryVersionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetCategoryVersionPersistence)PortalBeanLocatorUtil.locate(AssetCategoryVersionPersistence.class.getName());

			ReferenceRegistry.registerReference(AssetCategoryVersionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static AssetCategoryVersionPersistence _persistence;
}