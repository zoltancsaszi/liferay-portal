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

import com.liferay.asset.kernel.exception.NoSuchCategoryVersionException;
import com.liferay.asset.kernel.model.AssetCategoryVersion;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset category version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetCategoryVersionUtil
 * @generated
 */
@ProviderType
public interface AssetCategoryVersionPersistence extends BasePersistence<AssetCategoryVersion> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetCategoryVersionUtil} to access the asset category version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the asset category versions where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByCategoryId(
		long categoryId);

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
	public java.util.List<AssetCategoryVersion> findByCategoryId(
		long categoryId, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByCategoryId(
		long categoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByCategoryId(
		long categoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByCategoryId_First(long categoryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByCategoryId_First(long categoryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByCategoryId_Last(long categoryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByCategoryId_Last(long categoryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where categoryId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public AssetCategoryVersion[] findByCategoryId_PrevAndNext(
		long assetCategoryVersionId, long categoryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where categoryId = &#63; from the database.
	*
	* @param categoryId the category ID
	*/
	public void removeByCategoryId(long categoryId);

	/**
	* Returns the number of asset category versions where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @return the number of matching asset category versions
	*/
	public int countByCategoryId(long categoryId);

	/**
	* Returns the asset category version where categoryId = &#63; and version = &#63; or throws a <code>NoSuchCategoryVersionException</code> if it could not be found.
	*
	* @param categoryId the category ID
	* @param version the version
	* @return the matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByCategoryId_Version(long categoryId,
		int version) throws NoSuchCategoryVersionException;

	/**
	* Returns the asset category version where categoryId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param categoryId the category ID
	* @param version the version
	* @return the matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByCategoryId_Version(long categoryId,
		int version);

	/**
	* Returns the asset category version where categoryId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param categoryId the category ID
	* @param version the version
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByCategoryId_Version(long categoryId,
		int version, boolean retrieveFromCache);

	/**
	* Removes the asset category version where categoryId = &#63; and version = &#63; from the database.
	*
	* @param categoryId the category ID
	* @param version the version
	* @return the asset category version that was removed
	*/
	public AssetCategoryVersion removeByCategoryId_Version(long categoryId,
		int version) throws NoSuchCategoryVersionException;

	/**
	* Returns the number of asset category versions where categoryId = &#63; and version = &#63;.
	*
	* @param categoryId the category ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public int countByCategoryId_Version(long categoryId, int version);

	/**
	* Returns all the asset category versions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByUuid(String uuid);

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
	public java.util.List<AssetCategoryVersion> findByUuid(String uuid,
		int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where uuid = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public AssetCategoryVersion[] findByUuid_PrevAndNext(
		long assetCategoryVersionId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of asset category versions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching asset category versions
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the asset category versions where uuid = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param version the version
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByUuid_Version(
		String uuid, int version);

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
	public java.util.List<AssetCategoryVersion> findByUuid_Version(
		String uuid, int version, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByUuid_Version(
		String uuid, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByUuid_Version(
		String uuid, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByUuid_Version_First(String uuid,
		int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByUuid_Version_First(String uuid,
		int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByUuid_Version_Last(String uuid,
		int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByUuid_Version_Last(String uuid,
		int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByUuid_Version_PrevAndNext(
		long assetCategoryVersionId, String uuid, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where uuid = &#63; and version = &#63; from the database.
	*
	* @param uuid the uuid
	* @param version the version
	*/
	public void removeByUuid_Version(String uuid, int version);

	/**
	* Returns the number of asset category versions where uuid = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public int countByUuid_Version(String uuid, int version);

	/**
	* Returns all the asset category versions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByUUID_G(String uuid,
		long groupId);

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
	public java.util.List<AssetCategoryVersion> findByUUID_G(String uuid,
		long groupId, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByUUID_G(String uuid,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByUUID_G(String uuid,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByUUID_G_First(String uuid, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByUUID_G_First(String uuid, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByUUID_G_Last(String uuid, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByUUID_G_Last(String uuid, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByUUID_G_PrevAndNext(
		long assetCategoryVersionId, String uuid, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	*/
	public void removeByUUID_G(String uuid, long groupId);

	/**
	* Returns the number of asset category versions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching asset category versions
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns the asset category version where uuid = &#63; and groupId = &#63; and version = &#63; or throws a <code>NoSuchCategoryVersionException</code> if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param version the version
	* @return the matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByUUID_G_Version(String uuid, long groupId,
		int version) throws NoSuchCategoryVersionException;

	/**
	* Returns the asset category version where uuid = &#63; and groupId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param version the version
	* @return the matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByUUID_G_Version(String uuid,
		long groupId, int version);

	/**
	* Returns the asset category version where uuid = &#63; and groupId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param version the version
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByUUID_G_Version(String uuid,
		long groupId, int version, boolean retrieveFromCache);

	/**
	* Removes the asset category version where uuid = &#63; and groupId = &#63; and version = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param version the version
	* @return the asset category version that was removed
	*/
	public AssetCategoryVersion removeByUUID_G_Version(String uuid,
		long groupId, int version) throws NoSuchCategoryVersionException;

	/**
	* Returns the number of asset category versions where uuid = &#63; and groupId = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public int countByUUID_G_Version(String uuid, long groupId, int version);

	/**
	* Returns all the asset category versions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByUuid_C(String uuid,
		long companyId);

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
	public java.util.List<AssetCategoryVersion> findByUuid_C(String uuid,
		long companyId, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByUuid_C_PrevAndNext(
		long assetCategoryVersionId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of asset category versions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching asset category versions
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the asset category versions where uuid = &#63; and companyId = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByUuid_C_Version(
		String uuid, long companyId, int version);

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
	public java.util.List<AssetCategoryVersion> findByUuid_C_Version(
		String uuid, long companyId, int version, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByUuid_C_Version(
		String uuid, long companyId, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByUuid_C_Version(
		String uuid, long companyId, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetCategoryVersion findByUuid_C_Version_First(String uuid,
		long companyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where uuid = &#63; and companyId = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByUuid_C_Version_First(String uuid,
		long companyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion findByUuid_C_Version_Last(String uuid,
		long companyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where uuid = &#63; and companyId = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByUuid_C_Version_Last(String uuid,
		long companyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByUuid_C_Version_PrevAndNext(
		long assetCategoryVersionId, String uuid, long companyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where uuid = &#63; and companyId = &#63; and version = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param version the version
	*/
	public void removeByUuid_C_Version(String uuid, long companyId, int version);

	/**
	* Returns the number of asset category versions where uuid = &#63; and companyId = &#63; and version = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public int countByUuid_C_Version(String uuid, long companyId, int version);

	/**
	* Returns all the asset category versions where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByGroupId(long groupId);

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
	public java.util.List<AssetCategoryVersion> findByGroupId(long groupId,
		int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where groupId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public AssetCategoryVersion[] findByGroupId_PrevAndNext(
		long assetCategoryVersionId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of asset category versions where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching asset category versions
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the asset category versions where groupId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByGroupId_Version(
		long groupId, int version);

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
	public java.util.List<AssetCategoryVersion> findByGroupId_Version(
		long groupId, int version, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByGroupId_Version(
		long groupId, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByGroupId_Version(
		long groupId, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByGroupId_Version_First(long groupId,
		int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByGroupId_Version_First(long groupId,
		int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByGroupId_Version_Last(long groupId,
		int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByGroupId_Version_Last(long groupId,
		int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByGroupId_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where groupId = &#63; and version = &#63; from the database.
	*
	* @param groupId the group ID
	* @param version the version
	*/
	public void removeByGroupId_Version(long groupId, int version);

	/**
	* Returns the number of asset category versions where groupId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public int countByGroupId_Version(long groupId, int version);

	/**
	* Returns all the asset category versions where parentCategoryId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByParentCategoryId(
		long parentCategoryId);

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
	public java.util.List<AssetCategoryVersion> findByParentCategoryId(
		long parentCategoryId, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByParentCategoryId(
		long parentCategoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByParentCategoryId(
		long parentCategoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByParentCategoryId_First(
		long parentCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByParentCategoryId_First(
		long parentCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByParentCategoryId_Last(
		long parentCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByParentCategoryId_Last(
		long parentCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where parentCategoryId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public AssetCategoryVersion[] findByParentCategoryId_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where parentCategoryId = &#63; from the database.
	*
	* @param parentCategoryId the parent category ID
	*/
	public void removeByParentCategoryId(long parentCategoryId);

	/**
	* Returns the number of asset category versions where parentCategoryId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @return the number of matching asset category versions
	*/
	public int countByParentCategoryId(long parentCategoryId);

	/**
	* Returns all the asset category versions where parentCategoryId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByParentCategoryId_Version(
		long parentCategoryId, int version);

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
	public java.util.List<AssetCategoryVersion> findByParentCategoryId_Version(
		long parentCategoryId, int version, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByParentCategoryId_Version(
		long parentCategoryId, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByParentCategoryId_Version(
		long parentCategoryId, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByParentCategoryId_Version_First(
		long parentCategoryId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByParentCategoryId_Version_First(
		long parentCategoryId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByParentCategoryId_Version_Last(
		long parentCategoryId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByParentCategoryId_Version_Last(
		long parentCategoryId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByParentCategoryId_Version_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where parentCategoryId = &#63; and version = &#63; from the database.
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	*/
	public void removeByParentCategoryId_Version(long parentCategoryId,
		int version);

	/**
	* Returns the number of asset category versions where parentCategoryId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public int countByParentCategoryId_Version(long parentCategoryId,
		int version);

	/**
	* Returns all the asset category versions where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByVocabularyId(
		long vocabularyId);

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
	public java.util.List<AssetCategoryVersion> findByVocabularyId(
		long vocabularyId, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByVocabularyId(
		long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByVocabularyId(
		long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByVocabularyId_First(long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByVocabularyId_First(long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByVocabularyId_Last(long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByVocabularyId_Last(long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the asset category versions before and after the current asset category version in the ordered set where vocabularyId = &#63;.
	*
	* @param assetCategoryVersionId the primary key of the current asset category version
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public AssetCategoryVersion[] findByVocabularyId_PrevAndNext(
		long assetCategoryVersionId, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where vocabularyId = &#63; from the database.
	*
	* @param vocabularyId the vocabulary ID
	*/
	public void removeByVocabularyId(long vocabularyId);

	/**
	* Returns the number of asset category versions where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @return the number of matching asset category versions
	*/
	public int countByVocabularyId(long vocabularyId);

	/**
	* Returns all the asset category versions where vocabularyId = &#63; and version = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByVocabularyId_Version(
		long vocabularyId, int version);

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
	public java.util.List<AssetCategoryVersion> findByVocabularyId_Version(
		long vocabularyId, int version, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByVocabularyId_Version(
		long vocabularyId, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByVocabularyId_Version(
		long vocabularyId, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where vocabularyId = &#63; and version = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByVocabularyId_Version_First(
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where vocabularyId = &#63; and version = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByVocabularyId_Version_First(
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where vocabularyId = &#63; and version = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByVocabularyId_Version_Last(
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where vocabularyId = &#63; and version = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByVocabularyId_Version_Last(
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByVocabularyId_Version_PrevAndNext(
		long assetCategoryVersionId, long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where vocabularyId = &#63; and version = &#63; from the database.
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	*/
	public void removeByVocabularyId_Version(long vocabularyId, int version);

	/**
	* Returns the number of asset category versions where vocabularyId = &#63; and version = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public int countByVocabularyId_Version(long vocabularyId, int version);

	/**
	* Returns all the asset category versions where groupId = &#63; and parentCategoryId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByG_P(long groupId,
		long parentCategoryId);

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
	public java.util.List<AssetCategoryVersion> findByG_P(long groupId,
		long parentCategoryId, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByG_P(long groupId,
		long parentCategoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByG_P(long groupId,
		long parentCategoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByG_P_First(long groupId,
		long parentCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByG_P_First(long groupId,
		long parentCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByG_P_Last(long groupId,
		long parentCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByG_P_Last(long groupId,
		long parentCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByG_P_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	*/
	public void removeByG_P(long groupId, long parentCategoryId);

	/**
	* Returns the number of asset category versions where groupId = &#63; and parentCategoryId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @return the number of matching asset category versions
	*/
	public int countByG_P(long groupId, long parentCategoryId);

	/**
	* Returns all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByG_P_Version(
		long groupId, long parentCategoryId, int version);

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
	public java.util.List<AssetCategoryVersion> findByG_P_Version(
		long groupId, long parentCategoryId, int version, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByG_P_Version(
		long groupId, long parentCategoryId, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByG_P_Version(
		long groupId, long parentCategoryId, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetCategoryVersion findByG_P_Version_First(long groupId,
		long parentCategoryId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByG_P_Version_First(long groupId,
		long parentCategoryId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion findByG_P_Version_Last(long groupId,
		long parentCategoryId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByG_P_Version_Last(long groupId,
		long parentCategoryId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByG_P_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and version = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param version the version
	*/
	public void removeByG_P_Version(long groupId, long parentCategoryId,
		int version);

	/**
	* Returns the number of asset category versions where groupId = &#63; and parentCategoryId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public int countByG_P_Version(long groupId, long parentCategoryId,
		int version);

	/**
	* Returns all the asset category versions where groupId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByG_V(long groupId,
		long vocabularyId);

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
	public java.util.List<AssetCategoryVersion> findByG_V(long groupId,
		long vocabularyId, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByG_V(long groupId,
		long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByG_V(long groupId,
		long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByG_V_First(long groupId,
		long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByG_V_First(long groupId,
		long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByG_V_Last(long groupId, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByG_V_Last(long groupId,
		long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByG_V_PrevAndNext(
		long assetCategoryVersionId, long groupId, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where groupId = &#63; and vocabularyId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	*/
	public void removeByG_V(long groupId, long vocabularyId);

	/**
	* Returns the number of asset category versions where groupId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @return the number of matching asset category versions
	*/
	public int countByG_V(long groupId, long vocabularyId);

	/**
	* Returns all the asset category versions where groupId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByG_V_Version(
		long groupId, long vocabularyId, int version);

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
	public java.util.List<AssetCategoryVersion> findByG_V_Version(
		long groupId, long vocabularyId, int version, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByG_V_Version(
		long groupId, long vocabularyId, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByG_V_Version(
		long groupId, long vocabularyId, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetCategoryVersion findByG_V_Version_First(long groupId,
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByG_V_Version_First(long groupId,
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion findByG_V_Version_Last(long groupId,
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByG_V_Version_Last(long groupId,
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByG_V_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, long vocabularyId,
		int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where groupId = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	*/
	public void removeByG_V_Version(long groupId, long vocabularyId, int version);

	/**
	* Returns the number of asset category versions where groupId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public int countByG_V_Version(long groupId, long vocabularyId, int version);

	/**
	* Returns all the asset category versions where parentCategoryId = &#63; and name = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByP_N(
		long parentCategoryId, String name);

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
	public java.util.List<AssetCategoryVersion> findByP_N(
		long parentCategoryId, String name, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByP_N(
		long parentCategoryId, String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByP_N(
		long parentCategoryId, String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and name = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByP_N_First(long parentCategoryId,
		String name,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and name = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByP_N_First(long parentCategoryId,
		String name,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and name = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByP_N_Last(long parentCategoryId,
		String name,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and name = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByP_N_Last(long parentCategoryId,
		String name,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByP_N_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where parentCategoryId = &#63; and name = &#63; from the database.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	*/
	public void removeByP_N(long parentCategoryId, String name);

	/**
	* Returns the number of asset category versions where parentCategoryId = &#63; and name = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @return the number of matching asset category versions
	*/
	public int countByP_N(long parentCategoryId, String name);

	/**
	* Returns all the asset category versions where parentCategoryId = &#63; and name = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param version the version
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByP_N_Version(
		long parentCategoryId, String name, int version);

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
	public java.util.List<AssetCategoryVersion> findByP_N_Version(
		long parentCategoryId, String name, int version, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByP_N_Version(
		long parentCategoryId, String name, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByP_N_Version(
		long parentCategoryId, String name, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetCategoryVersion findByP_N_Version_First(long parentCategoryId,
		String name, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and name = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByP_N_Version_First(
		long parentCategoryId, String name, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion findByP_N_Version_Last(long parentCategoryId,
		String name, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and name = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByP_N_Version_Last(long parentCategoryId,
		String name, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByP_N_Version_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, String name,
		int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where parentCategoryId = &#63; and name = &#63; and version = &#63; from the database.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param version the version
	*/
	public void removeByP_N_Version(long parentCategoryId, String name,
		int version);

	/**
	* Returns the number of asset category versions where parentCategoryId = &#63; and name = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public int countByP_N_Version(long parentCategoryId, String name,
		int version);

	/**
	* Returns all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByP_V(
		long parentCategoryId, long vocabularyId);

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
	public java.util.List<AssetCategoryVersion> findByP_V(
		long parentCategoryId, long vocabularyId, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByP_V(
		long parentCategoryId, long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByP_V(
		long parentCategoryId, long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByP_V_First(long parentCategoryId,
		long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByP_V_First(long parentCategoryId,
		long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByP_V_Last(long parentCategoryId,
		long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByP_V_Last(long parentCategoryId,
		long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByP_V_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63; from the database.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	*/
	public void removeByP_V(long parentCategoryId, long vocabularyId);

	/**
	* Returns the number of asset category versions where parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @return the number of matching asset category versions
	*/
	public int countByP_V(long parentCategoryId, long vocabularyId);

	/**
	* Returns all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByP_V_Version(
		long parentCategoryId, long vocabularyId, int version);

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
	public java.util.List<AssetCategoryVersion> findByP_V_Version(
		long parentCategoryId, long vocabularyId, int version, int start,
		int end);

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
	public java.util.List<AssetCategoryVersion> findByP_V_Version(
		long parentCategoryId, long vocabularyId, int version, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByP_V_Version(
		long parentCategoryId, long vocabularyId, int version, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetCategoryVersion findByP_V_Version_First(long parentCategoryId,
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByP_V_Version_First(
		long parentCategoryId, long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion findByP_V_Version_Last(long parentCategoryId,
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByP_V_Version_Last(long parentCategoryId,
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByP_V_Version_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, long vocabularyId,
		int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	*/
	public void removeByP_V_Version(long parentCategoryId, long vocabularyId,
		int version);

	/**
	* Returns the number of asset category versions where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public int countByP_V_Version(long parentCategoryId, long vocabularyId,
		int version);

	/**
	* Returns all the asset category versions where name = &#63; and vocabularyId = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByN_V(String name,
		long vocabularyId);

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
	public java.util.List<AssetCategoryVersion> findByN_V(String name,
		long vocabularyId, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByN_V(String name,
		long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByN_V(String name,
		long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where name = &#63; and vocabularyId = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByN_V_First(String name, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where name = &#63; and vocabularyId = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByN_V_First(String name,
		long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where name = &#63; and vocabularyId = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByN_V_Last(String name, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where name = &#63; and vocabularyId = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByN_V_Last(String name, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByN_V_PrevAndNext(
		long assetCategoryVersionId, String name, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where name = &#63; and vocabularyId = &#63; from the database.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	*/
	public void removeByN_V(String name, long vocabularyId);

	/**
	* Returns the number of asset category versions where name = &#63; and vocabularyId = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @return the number of matching asset category versions
	*/
	public int countByN_V(String name, long vocabularyId);

	/**
	* Returns all the asset category versions where name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByN_V_Version(String name,
		long vocabularyId, int version);

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
	public java.util.List<AssetCategoryVersion> findByN_V_Version(String name,
		long vocabularyId, int version, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByN_V_Version(String name,
		long vocabularyId, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByN_V_Version(String name,
		long vocabularyId, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetCategoryVersion findByN_V_Version_First(String name,
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByN_V_Version_First(String name,
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion findByN_V_Version_Last(String name,
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByN_V_Version_Last(String name,
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByN_V_Version_PrevAndNext(
		long assetCategoryVersionId, String name, long vocabularyId,
		int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where name = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	*/
	public void removeByN_V_Version(String name, long vocabularyId, int version);

	/**
	* Returns the number of asset category versions where name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public int countByN_V_Version(String name, long vocabularyId, int version);

	/**
	* Returns all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByG_P_V(long groupId,
		long parentCategoryId, long vocabularyId);

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
	public java.util.List<AssetCategoryVersion> findByG_P_V(long groupId,
		long parentCategoryId, long vocabularyId, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByG_P_V(long groupId,
		long parentCategoryId, long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByG_P_V(long groupId,
		long parentCategoryId, long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetCategoryVersion findByG_P_V_First(long groupId,
		long parentCategoryId, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByG_P_V_First(long groupId,
		long parentCategoryId, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion findByG_P_V_Last(long groupId,
		long parentCategoryId, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByG_P_V_Last(long groupId,
		long parentCategoryId, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByG_P_V_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	*/
	public void removeByG_P_V(long groupId, long parentCategoryId,
		long vocabularyId);

	/**
	* Returns the number of asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @return the number of matching asset category versions
	*/
	public int countByG_P_V(long groupId, long parentCategoryId,
		long vocabularyId);

	/**
	* Returns all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByG_P_V_Version(
		long groupId, long parentCategoryId, long vocabularyId, int version);

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
	public java.util.List<AssetCategoryVersion> findByG_P_V_Version(
		long groupId, long parentCategoryId, long vocabularyId, int version,
		int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByG_P_V_Version(
		long groupId, long parentCategoryId, long vocabularyId, int version,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByG_P_V_Version(
		long groupId, long parentCategoryId, long vocabularyId, int version,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetCategoryVersion findByG_P_V_Version_First(long groupId,
		long parentCategoryId, long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

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
	public AssetCategoryVersion fetchByG_P_V_Version_First(long groupId,
		long parentCategoryId, long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion findByG_P_V_Version_Last(long groupId,
		long parentCategoryId, long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

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
	public AssetCategoryVersion fetchByG_P_V_Version_Last(long groupId,
		long parentCategoryId, long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByG_P_V_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	*/
	public void removeByG_P_V_Version(long groupId, long parentCategoryId,
		long vocabularyId, int version);

	/**
	* Returns the number of asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public int countByG_P_V_Version(long groupId, long parentCategoryId,
		long vocabularyId, int version);

	/**
	* Returns all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByG_LikeN_V(long groupId,
		String name, long vocabularyId);

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
	public java.util.List<AssetCategoryVersion> findByG_LikeN_V(long groupId,
		String name, long vocabularyId, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByG_LikeN_V(long groupId,
		String name, long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByG_LikeN_V(long groupId,
		String name, long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetCategoryVersion findByG_LikeN_V_First(long groupId,
		String name, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where groupId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByG_LikeN_V_First(long groupId,
		String name, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion findByG_LikeN_V_Last(long groupId, String name,
		long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where groupId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByG_LikeN_V_Last(long groupId,
		String name, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByG_LikeN_V_PrevAndNext(
		long assetCategoryVersionId, long groupId, String name,
		long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	*/
	public void removeByG_LikeN_V(long groupId, String name, long vocabularyId);

	/**
	* Returns the number of asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @return the number of matching asset category versions
	*/
	public int countByG_LikeN_V(long groupId, String name, long vocabularyId);

	/**
	* Returns all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByG_LikeN_V_Version(
		long groupId, String name, long vocabularyId, int version);

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
	public java.util.List<AssetCategoryVersion> findByG_LikeN_V_Version(
		long groupId, String name, long vocabularyId, int version, int start,
		int end);

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
	public java.util.List<AssetCategoryVersion> findByG_LikeN_V_Version(
		long groupId, String name, long vocabularyId, int version, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByG_LikeN_V_Version(
		long groupId, String name, long vocabularyId, int version, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetCategoryVersion findByG_LikeN_V_Version_First(long groupId,
		String name, long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

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
	public AssetCategoryVersion fetchByG_LikeN_V_Version_First(long groupId,
		String name, long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion findByG_LikeN_V_Version_Last(long groupId,
		String name, long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

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
	public AssetCategoryVersion fetchByG_LikeN_V_Version_Last(long groupId,
		String name, long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByG_LikeN_V_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, String name,
		long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	*/
	public void removeByG_LikeN_V_Version(long groupId, String name,
		long vocabularyId, int version);

	/**
	* Returns the number of asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public int countByG_LikeN_V_Version(long groupId, String name,
		long vocabularyId, int version);

	/**
	* Returns all the asset category versions where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByP_N_V(
		long parentCategoryId, String name, long vocabularyId);

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
	public java.util.List<AssetCategoryVersion> findByP_N_V(
		long parentCategoryId, String name, long vocabularyId, int start,
		int end);

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
	public java.util.List<AssetCategoryVersion> findByP_N_V(
		long parentCategoryId, String name, long vocabularyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByP_N_V(
		long parentCategoryId, String name, long vocabularyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetCategoryVersion findByP_N_V_First(long parentCategoryId,
		String name, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByP_N_V_First(long parentCategoryId,
		String name, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion findByP_N_V_Last(long parentCategoryId,
		String name, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByP_N_V_Last(long parentCategoryId,
		String name, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByP_N_V_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, String name,
		long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; from the database.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	*/
	public void removeByP_N_V(long parentCategoryId, String name,
		long vocabularyId);

	/**
	* Returns the number of asset category versions where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @return the number of matching asset category versions
	*/
	public int countByP_N_V(long parentCategoryId, String name,
		long vocabularyId);

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
	public AssetCategoryVersion findByP_N_V_Version(long parentCategoryId,
		String name, long vocabularyId, int version)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the asset category version where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByP_N_V_Version(long parentCategoryId,
		String name, long vocabularyId, int version);

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
	public AssetCategoryVersion fetchByP_N_V_Version(long parentCategoryId,
		String name, long vocabularyId, int version, boolean retrieveFromCache);

	/**
	* Removes the asset category version where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the asset category version that was removed
	*/
	public AssetCategoryVersion removeByP_N_V_Version(long parentCategoryId,
		String name, long vocabularyId, int version)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the number of asset category versions where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	*
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public int countByP_N_V_Version(long parentCategoryId, String name,
		long vocabularyId, int version);

	/**
	* Returns all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId);

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
	public java.util.List<AssetCategoryVersion> findByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId, int start,
		int end);

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
	public java.util.List<AssetCategoryVersion> findByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetCategoryVersion findByG_P_N_V_First(long groupId,
		long parentCategoryId, String name, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

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
	public AssetCategoryVersion fetchByG_P_N_V_First(long groupId,
		long parentCategoryId, String name, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion findByG_P_N_V_Last(long groupId,
		long parentCategoryId, String name, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

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
	public AssetCategoryVersion fetchByG_P_N_V_Last(long groupId,
		long parentCategoryId, String name, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByG_P_N_V_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		String name, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	*/
	public void removeByG_P_N_V(long groupId, long parentCategoryId,
		String name, long vocabularyId);

	/**
	* Returns the number of asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @return the number of matching asset category versions
	*/
	public int countByG_P_N_V(long groupId, long parentCategoryId, String name,
		long vocabularyId);

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
	public java.util.List<AssetCategoryVersion> findByG_P_N_V_Version(
		long groupId, long parentCategoryId, String name, long vocabularyId,
		int version);

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
	public java.util.List<AssetCategoryVersion> findByG_P_N_V_Version(
		long groupId, long parentCategoryId, String name, long vocabularyId,
		int version, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByG_P_N_V_Version(
		long groupId, long parentCategoryId, String name, long vocabularyId,
		int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByG_P_N_V_Version(
		long groupId, long parentCategoryId, String name, long vocabularyId,
		int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetCategoryVersion findByG_P_N_V_Version_First(long groupId,
		long parentCategoryId, String name, long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

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
	public AssetCategoryVersion fetchByG_P_N_V_Version_First(long groupId,
		long parentCategoryId, String name, long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion findByG_P_N_V_Version_Last(long groupId,
		long parentCategoryId, String name, long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

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
	public AssetCategoryVersion fetchByG_P_N_V_Version_Last(long groupId,
		long parentCategoryId, String name, long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByG_P_N_V_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		String name, long vocabularyId, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentCategoryId the parent category ID
	* @param name the name
	* @param vocabularyId the vocabulary ID
	* @param version the version
	*/
	public void removeByG_P_N_V_Version(long groupId, long parentCategoryId,
		String name, long vocabularyId, int version);

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
	public int countByG_P_N_V_Version(long groupId, long parentCategoryId,
		String name, long vocabularyId, int version);

	/**
	* Returns all the asset category versions where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByC_ERC(long companyId,
		String externalReferenceCode);

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
	public java.util.List<AssetCategoryVersion> findByC_ERC(long companyId,
		String externalReferenceCode, int start, int end);

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
	public java.util.List<AssetCategoryVersion> findByC_ERC(long companyId,
		String externalReferenceCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByC_ERC(long companyId,
		String externalReferenceCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByC_ERC_First(long companyId,
		String externalReferenceCode,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByC_ERC_First(long companyId,
		String externalReferenceCode,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

	/**
	* Returns the last asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version
	* @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	*/
	public AssetCategoryVersion findByC_ERC_Last(long companyId,
		String externalReferenceCode,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByC_ERC_Last(long companyId,
		String externalReferenceCode,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByC_ERC_PrevAndNext(
		long assetCategoryVersionId, long companyId,
		String externalReferenceCode,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where companyId = &#63; and externalReferenceCode = &#63; from the database.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	*/
	public void removeByC_ERC(long companyId, String externalReferenceCode);

	/**
	* Returns the number of asset category versions where companyId = &#63; and externalReferenceCode = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @return the number of matching asset category versions
	*/
	public int countByC_ERC(long companyId, String externalReferenceCode);

	/**
	* Returns all the asset category versions where companyId = &#63; and externalReferenceCode = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param version the version
	* @return the matching asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findByC_ERC_Version(
		long companyId, String externalReferenceCode, int version);

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
	public java.util.List<AssetCategoryVersion> findByC_ERC_Version(
		long companyId, String externalReferenceCode, int version, int start,
		int end);

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
	public java.util.List<AssetCategoryVersion> findByC_ERC_Version(
		long companyId, String externalReferenceCode, int version, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findByC_ERC_Version(
		long companyId, String externalReferenceCode, int version, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

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
	public AssetCategoryVersion findByC_ERC_Version_First(long companyId,
		String externalReferenceCode, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the first asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByC_ERC_Version_First(long companyId,
		String externalReferenceCode, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion findByC_ERC_Version_Last(long companyId,
		String externalReferenceCode, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the last asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	*/
	public AssetCategoryVersion fetchByC_ERC_Version_Last(long companyId,
		String externalReferenceCode, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public AssetCategoryVersion[] findByC_ERC_Version_PrevAndNext(
		long assetCategoryVersionId, long companyId,
		String externalReferenceCode, int version,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException;

	/**
	* Removes all the asset category versions where companyId = &#63; and externalReferenceCode = &#63; and version = &#63; from the database.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param version the version
	*/
	public void removeByC_ERC_Version(long companyId,
		String externalReferenceCode, int version);

	/**
	* Returns the number of asset category versions where companyId = &#63; and externalReferenceCode = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param externalReferenceCode the external reference code
	* @param version the version
	* @return the number of matching asset category versions
	*/
	public int countByC_ERC_Version(long companyId,
		String externalReferenceCode, int version);

	/**
	* Caches the asset category version in the entity cache if it is enabled.
	*
	* @param assetCategoryVersion the asset category version
	*/
	public void cacheResult(AssetCategoryVersion assetCategoryVersion);

	/**
	* Caches the asset category versions in the entity cache if it is enabled.
	*
	* @param assetCategoryVersions the asset category versions
	*/
	public void cacheResult(
		java.util.List<AssetCategoryVersion> assetCategoryVersions);

	/**
	* Creates a new asset category version with the primary key. Does not add the asset category version to the database.
	*
	* @param assetCategoryVersionId the primary key for the new asset category version
	* @return the new asset category version
	*/
	public AssetCategoryVersion create(long assetCategoryVersionId);

	/**
	* Removes the asset category version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetCategoryVersionId the primary key of the asset category version
	* @return the asset category version that was removed
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public AssetCategoryVersion remove(long assetCategoryVersionId)
		throws NoSuchCategoryVersionException;

	public AssetCategoryVersion updateImpl(
		AssetCategoryVersion assetCategoryVersion);

	/**
	* Returns the asset category version with the primary key or throws a <code>NoSuchCategoryVersionException</code> if it could not be found.
	*
	* @param assetCategoryVersionId the primary key of the asset category version
	* @return the asset category version
	* @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	*/
	public AssetCategoryVersion findByPrimaryKey(long assetCategoryVersionId)
		throws NoSuchCategoryVersionException;

	/**
	* Returns the asset category version with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetCategoryVersionId the primary key of the asset category version
	* @return the asset category version, or <code>null</code> if a asset category version with the primary key could not be found
	*/
	public AssetCategoryVersion fetchByPrimaryKey(long assetCategoryVersionId);

	/**
	* Returns all the asset category versions.
	*
	* @return the asset category versions
	*/
	public java.util.List<AssetCategoryVersion> findAll();

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
	public java.util.List<AssetCategoryVersion> findAll(int start, int end);

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
	public java.util.List<AssetCategoryVersion> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator);

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
	public java.util.List<AssetCategoryVersion> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the asset category versions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of asset category versions.
	*
	* @return the number of asset category versions
	*/
	public int countAll();

	/**
	* Returns the primaryKeys of asset entries associated with the asset category version.
	*
	* @param pk the primary key of the asset category version
	* @return long[] of the primaryKeys of asset entries associated with the asset category version
	*/
	public long[] getAssetEntryPrimaryKeys(long pk);

	/**
	* Returns all the asset entries associated with the asset category version.
	*
	* @param pk the primary key of the asset category version
	* @return the asset entries associated with the asset category version
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetEntry> getAssetEntries(
		long pk);

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
	public java.util.List<com.liferay.asset.kernel.model.AssetEntry> getAssetEntries(
		long pk, int start, int end);

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
	public java.util.List<com.liferay.asset.kernel.model.AssetEntry> getAssetEntries(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetEntry> orderByComparator);

	/**
	* Returns the number of asset entries associated with the asset category version.
	*
	* @param pk the primary key of the asset category version
	* @return the number of asset entries associated with the asset category version
	*/
	public int getAssetEntriesSize(long pk);

	/**
	* Returns <code>true</code> if the asset entry is associated with the asset category version.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntryPK the primary key of the asset entry
	* @return <code>true</code> if the asset entry is associated with the asset category version; <code>false</code> otherwise
	*/
	public boolean containsAssetEntry(long pk, long assetEntryPK);

	/**
	* Returns <code>true</code> if the asset category version has any asset entries associated with it.
	*
	* @param pk the primary key of the asset category version to check for associations with asset entries
	* @return <code>true</code> if the asset category version has any asset entries associated with it; <code>false</code> otherwise
	*/
	public boolean containsAssetEntries(long pk);

	/**
	* Adds an association between the asset category version and the asset entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntryPK the primary key of the asset entry
	*/
	public void addAssetEntry(long pk, long assetEntryPK);

	/**
	* Adds an association between the asset category version and the asset entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntry the asset entry
	*/
	public void addAssetEntry(long pk,
		com.liferay.asset.kernel.model.AssetEntry assetEntry);

	/**
	* Adds an association between the asset category version and the asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntryPKs the primary keys of the asset entries
	*/
	public void addAssetEntries(long pk, long[] assetEntryPKs);

	/**
	* Adds an association between the asset category version and the asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntries the asset entries
	*/
	public void addAssetEntries(long pk,
		java.util.List<com.liferay.asset.kernel.model.AssetEntry> assetEntries);

	/**
	* Clears all associations between the asset category version and its asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version to clear the associated asset entries from
	*/
	public void clearAssetEntries(long pk);

	/**
	* Removes the association between the asset category version and the asset entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntryPK the primary key of the asset entry
	*/
	public void removeAssetEntry(long pk, long assetEntryPK);

	/**
	* Removes the association between the asset category version and the asset entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntry the asset entry
	*/
	public void removeAssetEntry(long pk,
		com.liferay.asset.kernel.model.AssetEntry assetEntry);

	/**
	* Removes the association between the asset category version and the asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntryPKs the primary keys of the asset entries
	*/
	public void removeAssetEntries(long pk, long[] assetEntryPKs);

	/**
	* Removes the association between the asset category version and the asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntries the asset entries
	*/
	public void removeAssetEntries(long pk,
		java.util.List<com.liferay.asset.kernel.model.AssetEntry> assetEntries);

	/**
	* Sets the asset entries associated with the asset category version, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntryPKs the primary keys of the asset entries to be associated with the asset category version
	*/
	public void setAssetEntries(long pk, long[] assetEntryPKs);

	/**
	* Sets the asset entries associated with the asset category version, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the asset category version
	* @param assetEntries the asset entries to be associated with the asset category version
	*/
	public void setAssetEntries(long pk,
		java.util.List<com.liferay.asset.kernel.model.AssetEntry> assetEntries);
}