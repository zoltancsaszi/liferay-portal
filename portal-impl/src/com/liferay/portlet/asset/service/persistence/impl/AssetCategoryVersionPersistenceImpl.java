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

package com.liferay.portlet.asset.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.kernel.exception.NoSuchCategoryVersionException;
import com.liferay.asset.kernel.model.AssetCategoryVersion;
import com.liferay.asset.kernel.service.persistence.AssetCategoryVersionPersistence;
import com.liferay.asset.kernel.service.persistence.AssetEntryPersistence;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;

import com.liferay.portlet.asset.model.impl.AssetCategoryVersionImpl;
import com.liferay.portlet.asset.model.impl.AssetCategoryVersionModelImpl;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the asset category version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AssetCategoryVersionPersistenceImpl extends BasePersistenceImpl<AssetCategoryVersion>
	implements AssetCategoryVersionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AssetCategoryVersionUtil</code> to access the asset category version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetCategoryVersionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCategoryId;
	private FinderPath _finderPathWithoutPaginationFindByCategoryId;
	private FinderPath _finderPathCountByCategoryId;

	/**
	 * Returns all the asset category versions where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByCategoryId(long categoryId) {
		return findByCategoryId(categoryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByCategoryId(long categoryId,
		int start, int end) {
		return findByCategoryId(categoryId, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByCategoryId(long categoryId,
		int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByCategoryId(categoryId, start, end, orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByCategoryId(long categoryId,
		int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCategoryId;
			finderArgs = new Object[] { categoryId };
		}
		else {
			finderPath = _finderPathWithPaginationFindByCategoryId;
			finderArgs = new Object[] { categoryId, start, end, orderByComparator };
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((categoryId != assetCategoryVersion.getCategoryId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_CATEGORYID_CATEGORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(categoryId);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first asset category version in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version
	 * @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion findByCategoryId_First(long categoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByCategoryId_First(categoryId,
				orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("categoryId=");
		msg.append(categoryId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByCategoryId_First(long categoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByCategoryId(categoryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category version in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version
	 * @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion findByCategoryId_Last(long categoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByCategoryId_Last(categoryId,
				orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("categoryId=");
		msg.append(categoryId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByCategoryId_Last(long categoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByCategoryId(categoryId);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByCategoryId(categoryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByCategoryId_PrevAndNext(
		long assetCategoryVersionId, long categoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByCategoryId_PrevAndNext(session,
					assetCategoryVersion, categoryId, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByCategoryId_PrevAndNext(session,
					assetCategoryVersion, categoryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByCategoryId_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		long categoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_CATEGORYID_CATEGORYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(categoryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where categoryId = &#63; from the database.
	 *
	 * @param categoryId the category ID
	 */
	@Override
	public void removeByCategoryId(long categoryId) {
		for (AssetCategoryVersion assetCategoryVersion : findByCategoryId(
				categoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByCategoryId(long categoryId) {
		FinderPath finderPath = _finderPathCountByCategoryId;

		Object[] finderArgs = new Object[] { categoryId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_CATEGORYID_CATEGORYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(categoryId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CATEGORYID_CATEGORYID_2 = "assetCategoryVersion.categoryId = ?";
	private FinderPath _finderPathFetchByCategoryId_Version;
	private FinderPath _finderPathCountByCategoryId_Version;

	/**
	 * Returns the asset category version where categoryId = &#63; and version = &#63; or throws a <code>NoSuchCategoryVersionException</code> if it could not be found.
	 *
	 * @param categoryId the category ID
	 * @param version the version
	 * @return the matching asset category version
	 * @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion findByCategoryId_Version(long categoryId,
		int version) throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByCategoryId_Version(categoryId,
				version);

		if (assetCategoryVersion == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("categoryId=");
			msg.append(categoryId);

			msg.append(", version=");
			msg.append(version);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCategoryVersionException(msg.toString());
		}

		return assetCategoryVersion;
	}

	/**
	 * Returns the asset category version where categoryId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param categoryId the category ID
	 * @param version the version
	 * @return the matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByCategoryId_Version(long categoryId,
		int version) {
		return fetchByCategoryId_Version(categoryId, version, true);
	}

	/**
	 * Returns the asset category version where categoryId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param categoryId the category ID
	 * @param version the version
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByCategoryId_Version(long categoryId,
		int version, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { categoryId, version };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(_finderPathFetchByCategoryId_Version,
					finderArgs, this);
		}

		if (result instanceof AssetCategoryVersion) {
			AssetCategoryVersion assetCategoryVersion = (AssetCategoryVersion)result;

			if ((categoryId != assetCategoryVersion.getCategoryId()) ||
					(version != assetCategoryVersion.getVersion())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_CATEGORYID_VERSION_CATEGORYID_2);

			query.append(_FINDER_COLUMN_CATEGORYID_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(categoryId);

				qPos.add(version);

				List<AssetCategoryVersion> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(_finderPathFetchByCategoryId_Version,
						finderArgs, list);
				}
				else {
					AssetCategoryVersion assetCategoryVersion = list.get(0);

					result = assetCategoryVersion;

					cacheResult(assetCategoryVersion);
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(_finderPathFetchByCategoryId_Version,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (AssetCategoryVersion)result;
		}
	}

	/**
	 * Removes the asset category version where categoryId = &#63; and version = &#63; from the database.
	 *
	 * @param categoryId the category ID
	 * @param version the version
	 * @return the asset category version that was removed
	 */
	@Override
	public AssetCategoryVersion removeByCategoryId_Version(long categoryId,
		int version) throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByCategoryId_Version(categoryId,
				version);

		return remove(assetCategoryVersion);
	}

	/**
	 * Returns the number of asset category versions where categoryId = &#63; and version = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param version the version
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByCategoryId_Version(long categoryId, int version) {
		FinderPath finderPath = _finderPathCountByCategoryId_Version;

		Object[] finderArgs = new Object[] { categoryId, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_CATEGORYID_VERSION_CATEGORYID_2);

			query.append(_FINDER_COLUMN_CATEGORYID_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(categoryId);

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CATEGORYID_VERSION_CATEGORYID_2 = "assetCategoryVersion.categoryId = ? AND ";
	private static final String _FINDER_COLUMN_CATEGORYID_VERSION_VERSION_2 = "assetCategoryVersion.version = ?";
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the asset category versions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByUuid(String uuid, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByUuid(String uuid, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if (!uuid.equals(assetCategoryVersion.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first asset category version in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version
	 * @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion findByUuid_First(String uuid,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByUuid_First(uuid,
				orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByUuid_First(String uuid,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category version in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version
	 * @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion findByUuid_Last(String uuid,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByUuid_Last(uuid,
				orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByUuid_Last(String uuid,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByUuid_PrevAndNext(
		long assetCategoryVersionId, String uuid,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		uuid = Objects.toString(uuid, "");

		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByUuid_PrevAndNext(session, assetCategoryVersion,
					uuid, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByUuid_PrevAndNext(session, assetCategoryVersion,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByUuid_PrevAndNext(Session session,
		AssetCategoryVersion assetCategoryVersion, String uuid,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (AssetCategoryVersion assetCategoryVersion : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "assetCategoryVersion.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(assetCategoryVersion.uuid IS NULL OR assetCategoryVersion.uuid = '')";
	private FinderPath _finderPathWithPaginationFindByUuid_Version;
	private FinderPath _finderPathWithoutPaginationFindByUuid_Version;
	private FinderPath _finderPathCountByUuid_Version;

	/**
	 * Returns all the asset category versions where uuid = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param version the version
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByUuid_Version(String uuid,
		int version) {
		return findByUuid_Version(uuid, version, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByUuid_Version(String uuid,
		int version, int start, int end) {
		return findByUuid_Version(uuid, version, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByUuid_Version(String uuid,
		int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByUuid_Version(uuid, version, start, end, orderByComparator,
			true);
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
	@Override
	public List<AssetCategoryVersion> findByUuid_Version(String uuid,
		int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid_Version;
			finderArgs = new Object[] { uuid, version };
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid_Version;
			finderArgs = new Object[] {
					uuid, version,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if (!uuid.equals(assetCategoryVersion.getUuid()) ||
							(version != assetCategoryVersion.getVersion())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_VERSION_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_VERSION_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_VERSION_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(version);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByUuid_Version_First(String uuid,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByUuid_Version_First(uuid,
				version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where uuid = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByUuid_Version_First(String uuid,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByUuid_Version(uuid, version, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByUuid_Version_Last(String uuid,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByUuid_Version_Last(uuid,
				version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where uuid = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByUuid_Version_Last(String uuid,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByUuid_Version(uuid, version);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByUuid_Version(uuid, version,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByUuid_Version_PrevAndNext(
		long assetCategoryVersionId, String uuid, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		uuid = Objects.toString(uuid, "");

		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByUuid_Version_PrevAndNext(session,
					assetCategoryVersion, uuid, version, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByUuid_Version_PrevAndNext(session,
					assetCategoryVersion, uuid, version, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByUuid_Version_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		String uuid, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_VERSION_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_VERSION_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_VERSION_VERSION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(version);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where uuid = &#63; and version = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param version the version
	 */
	@Override
	public void removeByUuid_Version(String uuid, int version) {
		for (AssetCategoryVersion assetCategoryVersion : findByUuid_Version(
				uuid, version, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where uuid = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param version the version
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByUuid_Version(String uuid, int version) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_Version;

		Object[] finderArgs = new Object[] { uuid, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_VERSION_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_VERSION_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_VERSION_UUID_2 = "assetCategoryVersion.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_VERSION_UUID_3 = "(assetCategoryVersion.uuid IS NULL OR assetCategoryVersion.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_VERSION_VERSION_2 = "assetCategoryVersion.version = ?";
	private FinderPath _finderPathWithPaginationFindByUUID_G;
	private FinderPath _finderPathWithoutPaginationFindByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns all the asset category versions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByUUID_G(String uuid, long groupId) {
		return findByUUID_G(uuid, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByUUID_G(String uuid, long groupId,
		int start, int end) {
		return findByUUID_G(uuid, groupId, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByUUID_G(String uuid, long groupId,
		int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByUUID_G(uuid, groupId, start, end, orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByUUID_G(String uuid, long groupId,
		int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUUID_G;
			finderArgs = new Object[] { uuid, groupId };
		}
		else {
			finderPath = _finderPathWithPaginationFindByUUID_G;
			finderArgs = new Object[] {
					uuid, groupId,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if (!uuid.equals(assetCategoryVersion.getUuid()) ||
							(groupId != assetCategoryVersion.getGroupId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByUUID_G_First(String uuid, long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByUUID_G_First(uuid,
				groupId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByUUID_G_First(String uuid, long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByUUID_G(uuid, groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByUUID_G_Last(String uuid, long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByUUID_G_Last(uuid,
				groupId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByUUID_G_Last(String uuid, long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByUUID_G(uuid, groupId);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByUUID_G(uuid, groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByUUID_G_PrevAndNext(
		long assetCategoryVersionId, String uuid, long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		uuid = Objects.toString(uuid, "");

		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByUUID_G_PrevAndNext(session, assetCategoryVersion,
					uuid, groupId, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByUUID_G_PrevAndNext(session, assetCategoryVersion,
					uuid, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByUUID_G_PrevAndNext(Session session,
		AssetCategoryVersion assetCategoryVersion, String uuid, long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_G_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_G_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 */
	@Override
	public void removeByUUID_G(String uuid, long groupId) {
		for (AssetCategoryVersion assetCategoryVersion : findByUUID_G(uuid,
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "assetCategoryVersion.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(assetCategoryVersion.uuid IS NULL OR assetCategoryVersion.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "assetCategoryVersion.groupId = ?";
	private FinderPath _finderPathFetchByUUID_G_Version;
	private FinderPath _finderPathCountByUUID_G_Version;

	/**
	 * Returns the asset category version where uuid = &#63; and groupId = &#63; and version = &#63; or throws a <code>NoSuchCategoryVersionException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param version the version
	 * @return the matching asset category version
	 * @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion findByUUID_G_Version(String uuid, long groupId,
		int version) throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByUUID_G_Version(uuid,
				groupId, version);

		if (assetCategoryVersion == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(", version=");
			msg.append(version);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCategoryVersionException(msg.toString());
		}

		return assetCategoryVersion;
	}

	/**
	 * Returns the asset category version where uuid = &#63; and groupId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param version the version
	 * @return the matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByUUID_G_Version(String uuid,
		long groupId, int version) {
		return fetchByUUID_G_Version(uuid, groupId, version, true);
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
	@Override
	public AssetCategoryVersion fetchByUUID_G_Version(String uuid,
		long groupId, int version, boolean retrieveFromCache) {
		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] { uuid, groupId, version };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(_finderPathFetchByUUID_G_Version,
					finderArgs, this);
		}

		if (result instanceof AssetCategoryVersion) {
			AssetCategoryVersion assetCategoryVersion = (AssetCategoryVersion)result;

			if (!Objects.equals(uuid, assetCategoryVersion.getUuid()) ||
					(groupId != assetCategoryVersion.getGroupId()) ||
					(version != assetCategoryVersion.getVersion())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_VERSION_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_VERSION_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_VERSION_GROUPID_2);

			query.append(_FINDER_COLUMN_UUID_G_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				qPos.add(version);

				List<AssetCategoryVersion> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(_finderPathFetchByUUID_G_Version,
						finderArgs, list);
				}
				else {
					AssetCategoryVersion assetCategoryVersion = list.get(0);

					result = assetCategoryVersion;

					cacheResult(assetCategoryVersion);
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(_finderPathFetchByUUID_G_Version,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (AssetCategoryVersion)result;
		}
	}

	/**
	 * Removes the asset category version where uuid = &#63; and groupId = &#63; and version = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param version the version
	 * @return the asset category version that was removed
	 */
	@Override
	public AssetCategoryVersion removeByUUID_G_Version(String uuid,
		long groupId, int version) throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByUUID_G_Version(uuid,
				groupId, version);

		return remove(assetCategoryVersion);
	}

	/**
	 * Returns the number of asset category versions where uuid = &#63; and groupId = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param version the version
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByUUID_G_Version(String uuid, long groupId, int version) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G_Version;

		Object[] finderArgs = new Object[] { uuid, groupId, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_VERSION_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_VERSION_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_VERSION_GROUPID_2);

			query.append(_FINDER_COLUMN_UUID_G_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_VERSION_UUID_2 = "assetCategoryVersion.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_VERSION_UUID_3 = "(assetCategoryVersion.uuid IS NULL OR assetCategoryVersion.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_VERSION_GROUPID_2 = "assetCategoryVersion.groupId = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_VERSION_VERSION_2 = "assetCategoryVersion.version = ?";
	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the asset category versions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if (!uuid.equals(assetCategoryVersion.getUuid()) ||
							(companyId != assetCategoryVersion.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByUuid_C_PrevAndNext(
		long assetCategoryVersionId, String uuid, long companyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		uuid = Objects.toString(uuid, "");

		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, assetCategoryVersion,
					uuid, companyId, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByUuid_C_PrevAndNext(session, assetCategoryVersion,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByUuid_C_PrevAndNext(Session session,
		AssetCategoryVersion assetCategoryVersion, String uuid, long companyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (AssetCategoryVersion assetCategoryVersion : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "assetCategoryVersion.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(assetCategoryVersion.uuid IS NULL OR assetCategoryVersion.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "assetCategoryVersion.companyId = ?";
	private FinderPath _finderPathWithPaginationFindByUuid_C_Version;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C_Version;
	private FinderPath _finderPathCountByUuid_C_Version;

	/**
	 * Returns all the asset category versions where uuid = &#63; and companyId = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param version the version
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByUuid_C_Version(String uuid,
		long companyId, int version) {
		return findByUuid_C_Version(uuid, companyId, version,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByUuid_C_Version(String uuid,
		long companyId, int version, int start, int end) {
		return findByUuid_C_Version(uuid, companyId, version, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByUuid_C_Version(String uuid,
		long companyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByUuid_C_Version(uuid, companyId, version, start, end,
			orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByUuid_C_Version(String uuid,
		long companyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid_C_Version;
			finderArgs = new Object[] { uuid, companyId, version };
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid_C_Version;
			finderArgs = new Object[] {
					uuid, companyId, version,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if (!uuid.equals(assetCategoryVersion.getUuid()) ||
							(companyId != assetCategoryVersion.getCompanyId()) ||
							(version != assetCategoryVersion.getVersion())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_VERSION_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_VERSION_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_VERSION_COMPANYID_2);

			query.append(_FINDER_COLUMN_UUID_C_VERSION_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				qPos.add(version);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByUuid_C_Version_First(String uuid,
		long companyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByUuid_C_Version_First(uuid,
				companyId, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByUuid_C_Version_First(String uuid,
		long companyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByUuid_C_Version(uuid, companyId,
				version, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByUuid_C_Version_Last(String uuid,
		long companyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByUuid_C_Version_Last(uuid,
				companyId, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByUuid_C_Version_Last(String uuid,
		long companyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByUuid_C_Version(uuid, companyId, version);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByUuid_C_Version(uuid, companyId,
				version, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByUuid_C_Version_PrevAndNext(
		long assetCategoryVersionId, String uuid, long companyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		uuid = Objects.toString(uuid, "");

		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByUuid_C_Version_PrevAndNext(session,
					assetCategoryVersion, uuid, companyId, version,
					orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByUuid_C_Version_PrevAndNext(session,
					assetCategoryVersion, uuid, companyId, version,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByUuid_C_Version_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		String uuid, long companyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_VERSION_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_VERSION_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_VERSION_COMPANYID_2);

		query.append(_FINDER_COLUMN_UUID_C_VERSION_VERSION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		qPos.add(version);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where uuid = &#63; and companyId = &#63; and version = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param version the version
	 */
	@Override
	public void removeByUuid_C_Version(String uuid, long companyId, int version) {
		for (AssetCategoryVersion assetCategoryVersion : findByUuid_C_Version(
				uuid, companyId, version, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where uuid = &#63; and companyId = &#63; and version = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param version the version
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByUuid_C_Version(String uuid, long companyId, int version) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C_Version;

		Object[] finderArgs = new Object[] { uuid, companyId, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_VERSION_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_VERSION_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_VERSION_COMPANYID_2);

			query.append(_FINDER_COLUMN_UUID_C_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_VERSION_UUID_2 = "assetCategoryVersion.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_VERSION_UUID_3 = "(assetCategoryVersion.uuid IS NULL OR assetCategoryVersion.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_VERSION_COMPANYID_2 = "assetCategoryVersion.companyId = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_VERSION_VERSION_2 = "assetCategoryVersion.version = ?";
	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the asset category versions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByGroupId(long groupId, int start,
		int end) {
		return findByGroupId(groupId, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByGroupId(long groupId, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByGroupId(long groupId, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByGroupId;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((groupId != assetCategoryVersion.getGroupId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first asset category version in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version
	 * @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion findByGroupId_First(long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByGroupId_First(groupId,
				orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByGroupId_First(long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category version in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version
	 * @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion findByGroupId_Last(long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByGroupId_Last(long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByGroupId(groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByGroupId_PrevAndNext(
		long assetCategoryVersionId, long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, assetCategoryVersion,
					groupId, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByGroupId_PrevAndNext(session, assetCategoryVersion,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByGroupId_PrevAndNext(Session session,
		AssetCategoryVersion assetCategoryVersion, long groupId,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (AssetCategoryVersion assetCategoryVersion : findByGroupId(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "assetCategoryVersion.groupId = ?";
	private FinderPath _finderPathWithPaginationFindByGroupId_Version;
	private FinderPath _finderPathWithoutPaginationFindByGroupId_Version;
	private FinderPath _finderPathCountByGroupId_Version;

	/**
	 * Returns all the asset category versions where groupId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param version the version
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByGroupId_Version(long groupId,
		int version) {
		return findByGroupId_Version(groupId, version, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByGroupId_Version(long groupId,
		int version, int start, int end) {
		return findByGroupId_Version(groupId, version, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByGroupId_Version(long groupId,
		int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByGroupId_Version(groupId, version, start, end,
			orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByGroupId_Version(long groupId,
		int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByGroupId_Version;
			finderArgs = new Object[] { groupId, version };
		}
		else {
			finderPath = _finderPathWithPaginationFindByGroupId_Version;
			finderArgs = new Object[] {
					groupId, version,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((groupId != assetCategoryVersion.getGroupId()) ||
							(version != assetCategoryVersion.getVersion())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_VERSION_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPID_VERSION_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(version);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByGroupId_Version_First(long groupId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByGroupId_Version_First(groupId,
				version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where groupId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByGroupId_Version_First(long groupId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByGroupId_Version(groupId,
				version, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByGroupId_Version_Last(long groupId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByGroupId_Version_Last(groupId,
				version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where groupId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByGroupId_Version_Last(long groupId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByGroupId_Version(groupId, version);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByGroupId_Version(groupId,
				version, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByGroupId_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByGroupId_Version_PrevAndNext(session,
					assetCategoryVersion, groupId, version, orderByComparator,
					true);

			array[1] = assetCategoryVersion;

			array[2] = getByGroupId_Version_PrevAndNext(session,
					assetCategoryVersion, groupId, version, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByGroupId_Version_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		long groupId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_VERSION_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPID_VERSION_VERSION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(version);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where groupId = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param version the version
	 */
	@Override
	public void removeByGroupId_Version(long groupId, int version) {
		for (AssetCategoryVersion assetCategoryVersion : findByGroupId_Version(
				groupId, version, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where groupId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param version the version
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByGroupId_Version(long groupId, int version) {
		FinderPath finderPath = _finderPathCountByGroupId_Version;

		Object[] finderArgs = new Object[] { groupId, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_VERSION_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPID_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_VERSION_GROUPID_2 = "assetCategoryVersion.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPID_VERSION_VERSION_2 = "assetCategoryVersion.version = ?";
	private FinderPath _finderPathWithPaginationFindByParentCategoryId;
	private FinderPath _finderPathWithoutPaginationFindByParentCategoryId;
	private FinderPath _finderPathCountByParentCategoryId;

	/**
	 * Returns all the asset category versions where parentCategoryId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByParentCategoryId(
		long parentCategoryId) {
		return findByParentCategoryId(parentCategoryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByParentCategoryId(
		long parentCategoryId, int start, int end) {
		return findByParentCategoryId(parentCategoryId, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByParentCategoryId(
		long parentCategoryId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByParentCategoryId(parentCategoryId, start, end,
			orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByParentCategoryId(
		long parentCategoryId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByParentCategoryId;
			finderArgs = new Object[] { parentCategoryId };
		}
		else {
			finderPath = _finderPathWithPaginationFindByParentCategoryId;
			finderArgs = new Object[] {
					parentCategoryId,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((parentCategoryId != assetCategoryVersion.getParentCategoryId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_PARENTCATEGORYID_PARENTCATEGORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first asset category version in the ordered set where parentCategoryId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version
	 * @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion findByParentCategoryId_First(
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByParentCategoryId_First(parentCategoryId,
				orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where parentCategoryId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByParentCategoryId_First(
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByParentCategoryId(parentCategoryId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category version in the ordered set where parentCategoryId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version
	 * @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion findByParentCategoryId_Last(
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByParentCategoryId_Last(parentCategoryId,
				orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where parentCategoryId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByParentCategoryId_Last(
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByParentCategoryId(parentCategoryId);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByParentCategoryId(parentCategoryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByParentCategoryId_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByParentCategoryId_PrevAndNext(session,
					assetCategoryVersion, parentCategoryId, orderByComparator,
					true);

			array[1] = assetCategoryVersion;

			array[2] = getByParentCategoryId_PrevAndNext(session,
					assetCategoryVersion, parentCategoryId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByParentCategoryId_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_PARENTCATEGORYID_PARENTCATEGORYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentCategoryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where parentCategoryId = &#63; from the database.
	 *
	 * @param parentCategoryId the parent category ID
	 */
	@Override
	public void removeByParentCategoryId(long parentCategoryId) {
		for (AssetCategoryVersion assetCategoryVersion : findByParentCategoryId(
				parentCategoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where parentCategoryId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByParentCategoryId(long parentCategoryId) {
		FinderPath finderPath = _finderPathCountByParentCategoryId;

		Object[] finderArgs = new Object[] { parentCategoryId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_PARENTCATEGORYID_PARENTCATEGORYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_PARENTCATEGORYID_PARENTCATEGORYID_2 =
		"assetCategoryVersion.parentCategoryId = ?";
	private FinderPath _finderPathWithPaginationFindByParentCategoryId_Version;
	private FinderPath _finderPathWithoutPaginationFindByParentCategoryId_Version;
	private FinderPath _finderPathCountByParentCategoryId_Version;

	/**
	 * Returns all the asset category versions where parentCategoryId = &#63; and version = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param version the version
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByParentCategoryId_Version(
		long parentCategoryId, int version) {
		return findByParentCategoryId_Version(parentCategoryId, version,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByParentCategoryId_Version(
		long parentCategoryId, int version, int start, int end) {
		return findByParentCategoryId_Version(parentCategoryId, version, start,
			end, null);
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
	@Override
	public List<AssetCategoryVersion> findByParentCategoryId_Version(
		long parentCategoryId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByParentCategoryId_Version(parentCategoryId, version, start,
			end, orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByParentCategoryId_Version(
		long parentCategoryId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByParentCategoryId_Version;
			finderArgs = new Object[] { parentCategoryId, version };
		}
		else {
			finderPath = _finderPathWithPaginationFindByParentCategoryId_Version;
			finderArgs = new Object[] {
					parentCategoryId, version,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((parentCategoryId != assetCategoryVersion.getParentCategoryId()) ||
							(version != assetCategoryVersion.getVersion())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_PARENTCATEGORYID_VERSION_PARENTCATEGORYID_2);

			query.append(_FINDER_COLUMN_PARENTCATEGORYID_VERSION_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				qPos.add(version);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByParentCategoryId_Version_First(
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByParentCategoryId_Version_First(parentCategoryId,
				version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where parentCategoryId = &#63; and version = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByParentCategoryId_Version_First(
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByParentCategoryId_Version(parentCategoryId,
				version, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByParentCategoryId_Version_Last(
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByParentCategoryId_Version_Last(parentCategoryId,
				version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where parentCategoryId = &#63; and version = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByParentCategoryId_Version_Last(
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByParentCategoryId_Version(parentCategoryId, version);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByParentCategoryId_Version(parentCategoryId,
				version, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByParentCategoryId_Version_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByParentCategoryId_Version_PrevAndNext(session,
					assetCategoryVersion, parentCategoryId, version,
					orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByParentCategoryId_Version_PrevAndNext(session,
					assetCategoryVersion, parentCategoryId, version,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByParentCategoryId_Version_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_PARENTCATEGORYID_VERSION_PARENTCATEGORYID_2);

		query.append(_FINDER_COLUMN_PARENTCATEGORYID_VERSION_VERSION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentCategoryId);

		qPos.add(version);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where parentCategoryId = &#63; and version = &#63; from the database.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param version the version
	 */
	@Override
	public void removeByParentCategoryId_Version(long parentCategoryId,
		int version) {
		for (AssetCategoryVersion assetCategoryVersion : findByParentCategoryId_Version(
				parentCategoryId, version, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where parentCategoryId = &#63; and version = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param version the version
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByParentCategoryId_Version(long parentCategoryId,
		int version) {
		FinderPath finderPath = _finderPathCountByParentCategoryId_Version;

		Object[] finderArgs = new Object[] { parentCategoryId, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_PARENTCATEGORYID_VERSION_PARENTCATEGORYID_2);

			query.append(_FINDER_COLUMN_PARENTCATEGORYID_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_PARENTCATEGORYID_VERSION_PARENTCATEGORYID_2 =
		"assetCategoryVersion.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_PARENTCATEGORYID_VERSION_VERSION_2 =
		"assetCategoryVersion.version = ?";
	private FinderPath _finderPathWithPaginationFindByVocabularyId;
	private FinderPath _finderPathWithoutPaginationFindByVocabularyId;
	private FinderPath _finderPathCountByVocabularyId;

	/**
	 * Returns all the asset category versions where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByVocabularyId(long vocabularyId) {
		return findByVocabularyId(vocabularyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByVocabularyId(long vocabularyId,
		int start, int end) {
		return findByVocabularyId(vocabularyId, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByVocabularyId(long vocabularyId,
		int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByVocabularyId(vocabularyId, start, end, orderByComparator,
			true);
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
	@Override
	public List<AssetCategoryVersion> findByVocabularyId(long vocabularyId,
		int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByVocabularyId;
			finderArgs = new Object[] { vocabularyId };
		}
		else {
			finderPath = _finderPathWithPaginationFindByVocabularyId;
			finderArgs = new Object[] {
					vocabularyId,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((vocabularyId != assetCategoryVersion.getVocabularyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_VOCABULARYID_VOCABULARYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(vocabularyId);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first asset category version in the ordered set where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version
	 * @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion findByVocabularyId_First(long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByVocabularyId_First(vocabularyId,
				orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByVocabularyId_First(long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByVocabularyId(vocabularyId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category version in the ordered set where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version
	 * @throws NoSuchCategoryVersionException if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion findByVocabularyId_Last(long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByVocabularyId_Last(vocabularyId,
				orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByVocabularyId_Last(long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByVocabularyId(vocabularyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByVocabularyId(vocabularyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByVocabularyId_PrevAndNext(
		long assetCategoryVersionId, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByVocabularyId_PrevAndNext(session,
					assetCategoryVersion, vocabularyId, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByVocabularyId_PrevAndNext(session,
					assetCategoryVersion, vocabularyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByVocabularyId_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_VOCABULARYID_VOCABULARYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(vocabularyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where vocabularyId = &#63; from the database.
	 *
	 * @param vocabularyId the vocabulary ID
	 */
	@Override
	public void removeByVocabularyId(long vocabularyId) {
		for (AssetCategoryVersion assetCategoryVersion : findByVocabularyId(
				vocabularyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByVocabularyId(long vocabularyId) {
		FinderPath finderPath = _finderPathCountByVocabularyId;

		Object[] finderArgs = new Object[] { vocabularyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_VOCABULARYID_VOCABULARYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(vocabularyId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_VOCABULARYID_VOCABULARYID_2 = "assetCategoryVersion.vocabularyId = ?";
	private FinderPath _finderPathWithPaginationFindByVocabularyId_Version;
	private FinderPath _finderPathWithoutPaginationFindByVocabularyId_Version;
	private FinderPath _finderPathCountByVocabularyId_Version;

	/**
	 * Returns all the asset category versions where vocabularyId = &#63; and version = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByVocabularyId_Version(
		long vocabularyId, int version) {
		return findByVocabularyId_Version(vocabularyId, version,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByVocabularyId_Version(
		long vocabularyId, int version, int start, int end) {
		return findByVocabularyId_Version(vocabularyId, version, start, end,
			null);
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
	@Override
	public List<AssetCategoryVersion> findByVocabularyId_Version(
		long vocabularyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByVocabularyId_Version(vocabularyId, version, start, end,
			orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByVocabularyId_Version(
		long vocabularyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByVocabularyId_Version;
			finderArgs = new Object[] { vocabularyId, version };
		}
		else {
			finderPath = _finderPathWithPaginationFindByVocabularyId_Version;
			finderArgs = new Object[] {
					vocabularyId, version,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((vocabularyId != assetCategoryVersion.getVocabularyId()) ||
							(version != assetCategoryVersion.getVersion())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_VOCABULARYID_VERSION_VOCABULARYID_2);

			query.append(_FINDER_COLUMN_VOCABULARYID_VERSION_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(vocabularyId);

				qPos.add(version);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByVocabularyId_Version_First(
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByVocabularyId_Version_First(vocabularyId,
				version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where vocabularyId = &#63; and version = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByVocabularyId_Version_First(
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByVocabularyId_Version(vocabularyId,
				version, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByVocabularyId_Version_Last(
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByVocabularyId_Version_Last(vocabularyId,
				version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where vocabularyId = &#63; and version = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByVocabularyId_Version_Last(
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByVocabularyId_Version(vocabularyId, version);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByVocabularyId_Version(vocabularyId,
				version, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByVocabularyId_Version_PrevAndNext(
		long assetCategoryVersionId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByVocabularyId_Version_PrevAndNext(session,
					assetCategoryVersion, vocabularyId, version,
					orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByVocabularyId_Version_PrevAndNext(session,
					assetCategoryVersion, vocabularyId, version,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByVocabularyId_Version_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_VOCABULARYID_VERSION_VOCABULARYID_2);

		query.append(_FINDER_COLUMN_VOCABULARYID_VERSION_VERSION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(vocabularyId);

		qPos.add(version);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where vocabularyId = &#63; and version = &#63; from the database.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 */
	@Override
	public void removeByVocabularyId_Version(long vocabularyId, int version) {
		for (AssetCategoryVersion assetCategoryVersion : findByVocabularyId_Version(
				vocabularyId, version, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where vocabularyId = &#63; and version = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByVocabularyId_Version(long vocabularyId, int version) {
		FinderPath finderPath = _finderPathCountByVocabularyId_Version;

		Object[] finderArgs = new Object[] { vocabularyId, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_VOCABULARYID_VERSION_VOCABULARYID_2);

			query.append(_FINDER_COLUMN_VOCABULARYID_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(vocabularyId);

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_VOCABULARYID_VERSION_VOCABULARYID_2 =
		"assetCategoryVersion.vocabularyId = ? AND ";
	private static final String _FINDER_COLUMN_VOCABULARYID_VERSION_VERSION_2 = "assetCategoryVersion.version = ?";
	private FinderPath _finderPathWithPaginationFindByG_P;
	private FinderPath _finderPathWithoutPaginationFindByG_P;
	private FinderPath _finderPathCountByG_P;

	/**
	 * Returns all the asset category versions where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByG_P(long groupId,
		long parentCategoryId) {
		return findByG_P(groupId, parentCategoryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_P(long groupId,
		long parentCategoryId, int start, int end) {
		return findByG_P(groupId, parentCategoryId, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_P(long groupId,
		long parentCategoryId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByG_P(groupId, parentCategoryId, start, end,
			orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByG_P(long groupId,
		long parentCategoryId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByG_P;
			finderArgs = new Object[] { groupId, parentCategoryId };
		}
		else {
			finderPath = _finderPathWithPaginationFindByG_P;
			finderArgs = new Object[] {
					groupId, parentCategoryId,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((groupId != assetCategoryVersion.getGroupId()) ||
							(parentCategoryId != assetCategoryVersion.getParentCategoryId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_PARENTCATEGORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentCategoryId);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByG_P_First(long groupId,
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_P_First(groupId,
				parentCategoryId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByG_P_First(long groupId,
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByG_P(groupId, parentCategoryId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByG_P_Last(long groupId,
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_P_Last(groupId,
				parentCategoryId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByG_P_Last(long groupId,
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByG_P(groupId, parentCategoryId);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByG_P(groupId, parentCategoryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByG_P_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByG_P_PrevAndNext(session, assetCategoryVersion,
					groupId, parentCategoryId, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByG_P_PrevAndNext(session, assetCategoryVersion,
					groupId, parentCategoryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByG_P_PrevAndNext(Session session,
		AssetCategoryVersion assetCategoryVersion, long groupId,
		long parentCategoryId,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_G_P_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_PARENTCATEGORYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentCategoryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 */
	@Override
	public void removeByG_P(long groupId, long parentCategoryId) {
		for (AssetCategoryVersion assetCategoryVersion : findByG_P(groupId,
				parentCategoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByG_P(long groupId, long parentCategoryId) {
		FinderPath finderPath = _finderPathCountByG_P;

		Object[] finderArgs = new Object[] { groupId, parentCategoryId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_PARENTCATEGORYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentCategoryId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_P_GROUPID_2 = "assetCategoryVersion.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_PARENTCATEGORYID_2 = "assetCategoryVersion.parentCategoryId = ?";
	private FinderPath _finderPathWithPaginationFindByG_P_Version;
	private FinderPath _finderPathWithoutPaginationFindByG_P_Version;
	private FinderPath _finderPathCountByG_P_Version;

	/**
	 * Returns all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param version the version
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByG_P_Version(long groupId,
		long parentCategoryId, int version) {
		return findByG_P_Version(groupId, parentCategoryId, version,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_P_Version(long groupId,
		long parentCategoryId, int version, int start, int end) {
		return findByG_P_Version(groupId, parentCategoryId, version, start,
			end, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_P_Version(long groupId,
		long parentCategoryId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByG_P_Version(groupId, parentCategoryId, version, start,
			end, orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByG_P_Version(long groupId,
		long parentCategoryId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByG_P_Version;
			finderArgs = new Object[] { groupId, parentCategoryId, version };
		}
		else {
			finderPath = _finderPathWithPaginationFindByG_P_Version;
			finderArgs = new Object[] {
					groupId, parentCategoryId, version,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((groupId != assetCategoryVersion.getGroupId()) ||
							(parentCategoryId != assetCategoryVersion.getParentCategoryId()) ||
							(version != assetCategoryVersion.getVersion())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_P_VERSION_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_VERSION_PARENTCATEGORYID_2);

			query.append(_FINDER_COLUMN_G_P_VERSION_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentCategoryId);

				qPos.add(version);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByG_P_Version_First(long groupId,
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_P_Version_First(groupId,
				parentCategoryId, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByG_P_Version_First(long groupId,
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByG_P_Version(groupId,
				parentCategoryId, version, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByG_P_Version_Last(long groupId,
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_P_Version_Last(groupId,
				parentCategoryId, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByG_P_Version_Last(long groupId,
		long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByG_P_Version(groupId, parentCategoryId, version);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByG_P_Version(groupId,
				parentCategoryId, version, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByG_P_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByG_P_Version_PrevAndNext(session,
					assetCategoryVersion, groupId, parentCategoryId, version,
					orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByG_P_Version_PrevAndNext(session,
					assetCategoryVersion, groupId, parentCategoryId, version,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByG_P_Version_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		long groupId, long parentCategoryId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_G_P_VERSION_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_VERSION_PARENTCATEGORYID_2);

		query.append(_FINDER_COLUMN_G_P_VERSION_VERSION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentCategoryId);

		qPos.add(version);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param version the version
	 */
	@Override
	public void removeByG_P_Version(long groupId, long parentCategoryId,
		int version) {
		for (AssetCategoryVersion assetCategoryVersion : findByG_P_Version(
				groupId, parentCategoryId, version, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where groupId = &#63; and parentCategoryId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param version the version
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByG_P_Version(long groupId, long parentCategoryId,
		int version) {
		FinderPath finderPath = _finderPathCountByG_P_Version;

		Object[] finderArgs = new Object[] { groupId, parentCategoryId, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_P_VERSION_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_VERSION_PARENTCATEGORYID_2);

			query.append(_FINDER_COLUMN_G_P_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentCategoryId);

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_P_VERSION_GROUPID_2 = "assetCategoryVersion.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_VERSION_PARENTCATEGORYID_2 = "assetCategoryVersion.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_VERSION_VERSION_2 = "assetCategoryVersion.version = ?";
	private FinderPath _finderPathWithPaginationFindByG_V;
	private FinderPath _finderPathWithoutPaginationFindByG_V;
	private FinderPath _finderPathCountByG_V;

	/**
	 * Returns all the asset category versions where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByG_V(long groupId, long vocabularyId) {
		return findByG_V(groupId, vocabularyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_V(long groupId,
		long vocabularyId, int start, int end) {
		return findByG_V(groupId, vocabularyId, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_V(long groupId,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByG_V(groupId, vocabularyId, start, end, orderByComparator,
			true);
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
	@Override
	public List<AssetCategoryVersion> findByG_V(long groupId,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByG_V;
			finderArgs = new Object[] { groupId, vocabularyId };
		}
		else {
			finderPath = _finderPathWithPaginationFindByG_V;
			finderArgs = new Object[] {
					groupId, vocabularyId,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((groupId != assetCategoryVersion.getGroupId()) ||
							(vocabularyId != assetCategoryVersion.getVocabularyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_V_GROUPID_2);

			query.append(_FINDER_COLUMN_G_V_VOCABULARYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(vocabularyId);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByG_V_First(long groupId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_V_First(groupId,
				vocabularyId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByG_V_First(long groupId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByG_V(groupId, vocabularyId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByG_V_Last(long groupId, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_V_Last(groupId,
				vocabularyId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByG_V_Last(long groupId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByG_V(groupId, vocabularyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByG_V(groupId, vocabularyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByG_V_PrevAndNext(
		long assetCategoryVersionId, long groupId, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByG_V_PrevAndNext(session, assetCategoryVersion,
					groupId, vocabularyId, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByG_V_PrevAndNext(session, assetCategoryVersion,
					groupId, vocabularyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByG_V_PrevAndNext(Session session,
		AssetCategoryVersion assetCategoryVersion, long groupId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_G_V_GROUPID_2);

		query.append(_FINDER_COLUMN_G_V_VOCABULARYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(vocabularyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where groupId = &#63; and vocabularyId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 */
	@Override
	public void removeByG_V(long groupId, long vocabularyId) {
		for (AssetCategoryVersion assetCategoryVersion : findByG_V(groupId,
				vocabularyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByG_V(long groupId, long vocabularyId) {
		FinderPath finderPath = _finderPathCountByG_V;

		Object[] finderArgs = new Object[] { groupId, vocabularyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_V_GROUPID_2);

			query.append(_FINDER_COLUMN_G_V_VOCABULARYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(vocabularyId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_V_GROUPID_2 = "assetCategoryVersion.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_V_VOCABULARYID_2 = "assetCategoryVersion.vocabularyId = ?";
	private FinderPath _finderPathWithPaginationFindByG_V_Version;
	private FinderPath _finderPathWithoutPaginationFindByG_V_Version;
	private FinderPath _finderPathCountByG_V_Version;

	/**
	 * Returns all the asset category versions where groupId = &#63; and vocabularyId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByG_V_Version(long groupId,
		long vocabularyId, int version) {
		return findByG_V_Version(groupId, vocabularyId, version,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_V_Version(long groupId,
		long vocabularyId, int version, int start, int end) {
		return findByG_V_Version(groupId, vocabularyId, version, start, end,
			null);
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
	@Override
	public List<AssetCategoryVersion> findByG_V_Version(long groupId,
		long vocabularyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByG_V_Version(groupId, vocabularyId, version, start, end,
			orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByG_V_Version(long groupId,
		long vocabularyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByG_V_Version;
			finderArgs = new Object[] { groupId, vocabularyId, version };
		}
		else {
			finderPath = _finderPathWithPaginationFindByG_V_Version;
			finderArgs = new Object[] {
					groupId, vocabularyId, version,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((groupId != assetCategoryVersion.getGroupId()) ||
							(vocabularyId != assetCategoryVersion.getVocabularyId()) ||
							(version != assetCategoryVersion.getVersion())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_V_VERSION_GROUPID_2);

			query.append(_FINDER_COLUMN_G_V_VERSION_VOCABULARYID_2);

			query.append(_FINDER_COLUMN_G_V_VERSION_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(vocabularyId);

				qPos.add(version);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByG_V_Version_First(long groupId,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_V_Version_First(groupId,
				vocabularyId, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByG_V_Version_First(long groupId,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByG_V_Version(groupId,
				vocabularyId, version, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByG_V_Version_Last(long groupId,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_V_Version_Last(groupId,
				vocabularyId, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByG_V_Version_Last(long groupId,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByG_V_Version(groupId, vocabularyId, version);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByG_V_Version(groupId,
				vocabularyId, version, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByG_V_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, long vocabularyId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByG_V_Version_PrevAndNext(session,
					assetCategoryVersion, groupId, vocabularyId, version,
					orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByG_V_Version_PrevAndNext(session,
					assetCategoryVersion, groupId, vocabularyId, version,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByG_V_Version_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		long groupId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_G_V_VERSION_GROUPID_2);

		query.append(_FINDER_COLUMN_G_V_VERSION_VOCABULARYID_2);

		query.append(_FINDER_COLUMN_G_V_VERSION_VERSION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(vocabularyId);

		qPos.add(version);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where groupId = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 */
	@Override
	public void removeByG_V_Version(long groupId, long vocabularyId, int version) {
		for (AssetCategoryVersion assetCategoryVersion : findByG_V_Version(
				groupId, vocabularyId, version, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where groupId = &#63; and vocabularyId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByG_V_Version(long groupId, long vocabularyId, int version) {
		FinderPath finderPath = _finderPathCountByG_V_Version;

		Object[] finderArgs = new Object[] { groupId, vocabularyId, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_V_VERSION_GROUPID_2);

			query.append(_FINDER_COLUMN_G_V_VERSION_VOCABULARYID_2);

			query.append(_FINDER_COLUMN_G_V_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(vocabularyId);

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_V_VERSION_GROUPID_2 = "assetCategoryVersion.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_V_VERSION_VOCABULARYID_2 = "assetCategoryVersion.vocabularyId = ? AND ";
	private static final String _FINDER_COLUMN_G_V_VERSION_VERSION_2 = "assetCategoryVersion.version = ?";
	private FinderPath _finderPathWithPaginationFindByP_N;
	private FinderPath _finderPathWithoutPaginationFindByP_N;
	private FinderPath _finderPathCountByP_N;

	/**
	 * Returns all the asset category versions where parentCategoryId = &#63; and name = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByP_N(long parentCategoryId,
		String name) {
		return findByP_N(parentCategoryId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByP_N(long parentCategoryId,
		String name, int start, int end) {
		return findByP_N(parentCategoryId, name, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByP_N(long parentCategoryId,
		String name, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByP_N(parentCategoryId, name, start, end, orderByComparator,
			true);
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
	@Override
	public List<AssetCategoryVersion> findByP_N(long parentCategoryId,
		String name, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		name = Objects.toString(name, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByP_N;
			finderArgs = new Object[] { parentCategoryId, name };
		}
		else {
			finderPath = _finderPathWithPaginationFindByP_N;
			finderArgs = new Object[] {
					parentCategoryId, name,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((parentCategoryId != assetCategoryVersion.getParentCategoryId()) ||
							!name.equals(assetCategoryVersion.getName())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_P_N_PARENTCATEGORYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_P_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_P_N_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByP_N_First(long parentCategoryId,
		String name, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByP_N_First(parentCategoryId,
				name, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", name=");
		msg.append(name);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where parentCategoryId = &#63; and name = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByP_N_First(long parentCategoryId,
		String name, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByP_N(parentCategoryId, name, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByP_N_Last(long parentCategoryId,
		String name, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByP_N_Last(parentCategoryId,
				name, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", name=");
		msg.append(name);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where parentCategoryId = &#63; and name = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByP_N_Last(long parentCategoryId,
		String name, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByP_N(parentCategoryId, name);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByP_N(parentCategoryId, name,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByP_N_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, String name,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		name = Objects.toString(name, "");

		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByP_N_PrevAndNext(session, assetCategoryVersion,
					parentCategoryId, name, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByP_N_PrevAndNext(session, assetCategoryVersion,
					parentCategoryId, name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByP_N_PrevAndNext(Session session,
		AssetCategoryVersion assetCategoryVersion, long parentCategoryId,
		String name, OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_P_N_PARENTCATEGORYID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			query.append(_FINDER_COLUMN_P_N_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_P_N_NAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentCategoryId);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where parentCategoryId = &#63; and name = &#63; from the database.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 */
	@Override
	public void removeByP_N(long parentCategoryId, String name) {
		for (AssetCategoryVersion assetCategoryVersion : findByP_N(
				parentCategoryId, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where parentCategoryId = &#63; and name = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByP_N(long parentCategoryId, String name) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByP_N;

		Object[] finderArgs = new Object[] { parentCategoryId, name };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_P_N_PARENTCATEGORYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_P_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_P_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				if (bindName) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_P_N_PARENTCATEGORYID_2 = "assetCategoryVersion.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_P_N_NAME_2 = "assetCategoryVersion.name = ?";
	private static final String _FINDER_COLUMN_P_N_NAME_3 = "(assetCategoryVersion.name IS NULL OR assetCategoryVersion.name = '')";
	private FinderPath _finderPathWithPaginationFindByP_N_Version;
	private FinderPath _finderPathWithoutPaginationFindByP_N_Version;
	private FinderPath _finderPathCountByP_N_Version;

	/**
	 * Returns all the asset category versions where parentCategoryId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param version the version
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByP_N_Version(long parentCategoryId,
		String name, int version) {
		return findByP_N_Version(parentCategoryId, name, version,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByP_N_Version(long parentCategoryId,
		String name, int version, int start, int end) {
		return findByP_N_Version(parentCategoryId, name, version, start, end,
			null);
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
	@Override
	public List<AssetCategoryVersion> findByP_N_Version(long parentCategoryId,
		String name, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByP_N_Version(parentCategoryId, name, version, start, end,
			orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByP_N_Version(long parentCategoryId,
		String name, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		name = Objects.toString(name, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByP_N_Version;
			finderArgs = new Object[] { parentCategoryId, name, version };
		}
		else {
			finderPath = _finderPathWithPaginationFindByP_N_Version;
			finderArgs = new Object[] {
					parentCategoryId, name, version,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((parentCategoryId != assetCategoryVersion.getParentCategoryId()) ||
							!name.equals(assetCategoryVersion.getName()) ||
							(version != assetCategoryVersion.getVersion())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_P_N_VERSION_PARENTCATEGORYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_P_N_VERSION_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_P_N_VERSION_NAME_2);
			}

			query.append(_FINDER_COLUMN_P_N_VERSION_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(version);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByP_N_Version_First(long parentCategoryId,
		String name, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByP_N_Version_First(parentCategoryId,
				name, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByP_N_Version_First(
		long parentCategoryId, String name, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByP_N_Version(parentCategoryId,
				name, version, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByP_N_Version_Last(long parentCategoryId,
		String name, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByP_N_Version_Last(parentCategoryId,
				name, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByP_N_Version_Last(long parentCategoryId,
		String name, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByP_N_Version(parentCategoryId, name, version);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByP_N_Version(parentCategoryId,
				name, version, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByP_N_Version_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, String name,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		name = Objects.toString(name, "");

		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByP_N_Version_PrevAndNext(session,
					assetCategoryVersion, parentCategoryId, name, version,
					orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByP_N_Version_PrevAndNext(session,
					assetCategoryVersion, parentCategoryId, name, version,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByP_N_Version_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		long parentCategoryId, String name, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_P_N_VERSION_PARENTCATEGORYID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			query.append(_FINDER_COLUMN_P_N_VERSION_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_P_N_VERSION_NAME_2);
		}

		query.append(_FINDER_COLUMN_P_N_VERSION_VERSION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentCategoryId);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(version);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where parentCategoryId = &#63; and name = &#63; and version = &#63; from the database.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param version the version
	 */
	@Override
	public void removeByP_N_Version(long parentCategoryId, String name,
		int version) {
		for (AssetCategoryVersion assetCategoryVersion : findByP_N_Version(
				parentCategoryId, name, version, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where parentCategoryId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param version the version
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByP_N_Version(long parentCategoryId, String name,
		int version) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByP_N_Version;

		Object[] finderArgs = new Object[] { parentCategoryId, name, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_P_N_VERSION_PARENTCATEGORYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_P_N_VERSION_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_P_N_VERSION_NAME_2);
			}

			query.append(_FINDER_COLUMN_P_N_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_P_N_VERSION_PARENTCATEGORYID_2 = "assetCategoryVersion.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_P_N_VERSION_NAME_2 = "assetCategoryVersion.name = ? AND ";
	private static final String _FINDER_COLUMN_P_N_VERSION_NAME_3 = "(assetCategoryVersion.name IS NULL OR assetCategoryVersion.name = '') AND ";
	private static final String _FINDER_COLUMN_P_N_VERSION_VERSION_2 = "assetCategoryVersion.version = ?";
	private FinderPath _finderPathWithPaginationFindByP_V;
	private FinderPath _finderPathWithoutPaginationFindByP_V;
	private FinderPath _finderPathCountByP_V;

	/**
	 * Returns all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByP_V(long parentCategoryId,
		long vocabularyId) {
		return findByP_V(parentCategoryId, vocabularyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByP_V(long parentCategoryId,
		long vocabularyId, int start, int end) {
		return findByP_V(parentCategoryId, vocabularyId, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByP_V(long parentCategoryId,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByP_V(parentCategoryId, vocabularyId, start, end,
			orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByP_V(long parentCategoryId,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByP_V;
			finderArgs = new Object[] { parentCategoryId, vocabularyId };
		}
		else {
			finderPath = _finderPathWithPaginationFindByP_V;
			finderArgs = new Object[] {
					parentCategoryId, vocabularyId,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((parentCategoryId != assetCategoryVersion.getParentCategoryId()) ||
							(vocabularyId != assetCategoryVersion.getVocabularyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_P_V_PARENTCATEGORYID_2);

			query.append(_FINDER_COLUMN_P_V_VOCABULARYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				qPos.add(vocabularyId);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByP_V_First(long parentCategoryId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByP_V_First(parentCategoryId,
				vocabularyId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByP_V_First(long parentCategoryId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByP_V(parentCategoryId,
				vocabularyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByP_V_Last(long parentCategoryId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByP_V_Last(parentCategoryId,
				vocabularyId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByP_V_Last(long parentCategoryId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByP_V(parentCategoryId, vocabularyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByP_V(parentCategoryId,
				vocabularyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByP_V_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByP_V_PrevAndNext(session, assetCategoryVersion,
					parentCategoryId, vocabularyId, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByP_V_PrevAndNext(session, assetCategoryVersion,
					parentCategoryId, vocabularyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByP_V_PrevAndNext(Session session,
		AssetCategoryVersion assetCategoryVersion, long parentCategoryId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_P_V_PARENTCATEGORYID_2);

		query.append(_FINDER_COLUMN_P_V_VOCABULARYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentCategoryId);

		qPos.add(vocabularyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63; from the database.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 */
	@Override
	public void removeByP_V(long parentCategoryId, long vocabularyId) {
		for (AssetCategoryVersion assetCategoryVersion : findByP_V(
				parentCategoryId, vocabularyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByP_V(long parentCategoryId, long vocabularyId) {
		FinderPath finderPath = _finderPathCountByP_V;

		Object[] finderArgs = new Object[] { parentCategoryId, vocabularyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_P_V_PARENTCATEGORYID_2);

			query.append(_FINDER_COLUMN_P_V_VOCABULARYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				qPos.add(vocabularyId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_P_V_PARENTCATEGORYID_2 = "assetCategoryVersion.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_P_V_VOCABULARYID_2 = "assetCategoryVersion.vocabularyId = ?";
	private FinderPath _finderPathWithPaginationFindByP_V_Version;
	private FinderPath _finderPathWithoutPaginationFindByP_V_Version;
	private FinderPath _finderPathCountByP_V_Version;

	/**
	 * Returns all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByP_V_Version(long parentCategoryId,
		long vocabularyId, int version) {
		return findByP_V_Version(parentCategoryId, vocabularyId, version,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByP_V_Version(long parentCategoryId,
		long vocabularyId, int version, int start, int end) {
		return findByP_V_Version(parentCategoryId, vocabularyId, version,
			start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByP_V_Version(long parentCategoryId,
		long vocabularyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByP_V_Version(parentCategoryId, vocabularyId, version,
			start, end, orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByP_V_Version(long parentCategoryId,
		long vocabularyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByP_V_Version;
			finderArgs = new Object[] { parentCategoryId, vocabularyId, version };
		}
		else {
			finderPath = _finderPathWithPaginationFindByP_V_Version;
			finderArgs = new Object[] {
					parentCategoryId, vocabularyId, version,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((parentCategoryId != assetCategoryVersion.getParentCategoryId()) ||
							(vocabularyId != assetCategoryVersion.getVocabularyId()) ||
							(version != assetCategoryVersion.getVersion())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_P_V_VERSION_PARENTCATEGORYID_2);

			query.append(_FINDER_COLUMN_P_V_VERSION_VOCABULARYID_2);

			query.append(_FINDER_COLUMN_P_V_VERSION_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				qPos.add(vocabularyId);

				qPos.add(version);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByP_V_Version_First(long parentCategoryId,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByP_V_Version_First(parentCategoryId,
				vocabularyId, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByP_V_Version_First(
		long parentCategoryId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByP_V_Version(parentCategoryId,
				vocabularyId, version, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByP_V_Version_Last(long parentCategoryId,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByP_V_Version_Last(parentCategoryId,
				vocabularyId, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByP_V_Version_Last(long parentCategoryId,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByP_V_Version(parentCategoryId, vocabularyId, version);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByP_V_Version(parentCategoryId,
				vocabularyId, version, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByP_V_Version_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, long vocabularyId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByP_V_Version_PrevAndNext(session,
					assetCategoryVersion, parentCategoryId, vocabularyId,
					version, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByP_V_Version_PrevAndNext(session,
					assetCategoryVersion, parentCategoryId, vocabularyId,
					version, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByP_V_Version_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		long parentCategoryId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_P_V_VERSION_PARENTCATEGORYID_2);

		query.append(_FINDER_COLUMN_P_V_VERSION_VOCABULARYID_2);

		query.append(_FINDER_COLUMN_P_V_VERSION_VERSION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentCategoryId);

		qPos.add(vocabularyId);

		qPos.add(version);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 */
	@Override
	public void removeByP_V_Version(long parentCategoryId, long vocabularyId,
		int version) {
		for (AssetCategoryVersion assetCategoryVersion : findByP_V_Version(
				parentCategoryId, vocabularyId, version, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByP_V_Version(long parentCategoryId, long vocabularyId,
		int version) {
		FinderPath finderPath = _finderPathCountByP_V_Version;

		Object[] finderArgs = new Object[] {
				parentCategoryId, vocabularyId, version
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_P_V_VERSION_PARENTCATEGORYID_2);

			query.append(_FINDER_COLUMN_P_V_VERSION_VOCABULARYID_2);

			query.append(_FINDER_COLUMN_P_V_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				qPos.add(vocabularyId);

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_P_V_VERSION_PARENTCATEGORYID_2 = "assetCategoryVersion.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_P_V_VERSION_VOCABULARYID_2 = "assetCategoryVersion.vocabularyId = ? AND ";
	private static final String _FINDER_COLUMN_P_V_VERSION_VERSION_2 = "assetCategoryVersion.version = ?";
	private FinderPath _finderPathWithPaginationFindByN_V;
	private FinderPath _finderPathWithoutPaginationFindByN_V;
	private FinderPath _finderPathCountByN_V;

	/**
	 * Returns all the asset category versions where name = &#63; and vocabularyId = &#63;.
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByN_V(String name, long vocabularyId) {
		return findByN_V(name, vocabularyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByN_V(String name, long vocabularyId,
		int start, int end) {
		return findByN_V(name, vocabularyId, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByN_V(String name, long vocabularyId,
		int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByN_V(name, vocabularyId, start, end, orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByN_V(String name, long vocabularyId,
		int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		name = Objects.toString(name, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByN_V;
			finderArgs = new Object[] { name, vocabularyId };
		}
		else {
			finderPath = _finderPathWithPaginationFindByN_V;
			finderArgs = new Object[] {
					name, vocabularyId,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if (!name.equals(assetCategoryVersion.getName()) ||
							(vocabularyId != assetCategoryVersion.getVocabularyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_N_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_N_V_NAME_2);
			}

			query.append(_FINDER_COLUMN_N_V_VOCABULARYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(vocabularyId);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByN_V_First(String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByN_V_First(name,
				vocabularyId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where name = &#63; and vocabularyId = &#63;.
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByN_V_First(String name,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByN_V(name, vocabularyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByN_V_Last(String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByN_V_Last(name,
				vocabularyId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where name = &#63; and vocabularyId = &#63;.
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByN_V_Last(String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByN_V(name, vocabularyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByN_V(name, vocabularyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByN_V_PrevAndNext(
		long assetCategoryVersionId, String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		name = Objects.toString(name, "");

		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByN_V_PrevAndNext(session, assetCategoryVersion,
					name, vocabularyId, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByN_V_PrevAndNext(session, assetCategoryVersion,
					name, vocabularyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByN_V_PrevAndNext(Session session,
		AssetCategoryVersion assetCategoryVersion, String name,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		boolean bindName = false;

		if (name.isEmpty()) {
			query.append(_FINDER_COLUMN_N_V_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_N_V_NAME_2);
		}

		query.append(_FINDER_COLUMN_N_V_VOCABULARYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(vocabularyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where name = &#63; and vocabularyId = &#63; from the database.
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 */
	@Override
	public void removeByN_V(String name, long vocabularyId) {
		for (AssetCategoryVersion assetCategoryVersion : findByN_V(name,
				vocabularyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where name = &#63; and vocabularyId = &#63;.
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByN_V(String name, long vocabularyId) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByN_V;

		Object[] finderArgs = new Object[] { name, vocabularyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_N_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_N_V_NAME_2);
			}

			query.append(_FINDER_COLUMN_N_V_VOCABULARYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(vocabularyId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_N_V_NAME_2 = "assetCategoryVersion.name = ? AND ";
	private static final String _FINDER_COLUMN_N_V_NAME_3 = "(assetCategoryVersion.name IS NULL OR assetCategoryVersion.name = '') AND ";
	private static final String _FINDER_COLUMN_N_V_VOCABULARYID_2 = "assetCategoryVersion.vocabularyId = ?";
	private FinderPath _finderPathWithPaginationFindByN_V_Version;
	private FinderPath _finderPathWithoutPaginationFindByN_V_Version;
	private FinderPath _finderPathCountByN_V_Version;

	/**
	 * Returns all the asset category versions where name = &#63; and vocabularyId = &#63; and version = &#63;.
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByN_V_Version(String name,
		long vocabularyId, int version) {
		return findByN_V_Version(name, vocabularyId, version,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByN_V_Version(String name,
		long vocabularyId, int version, int start, int end) {
		return findByN_V_Version(name, vocabularyId, version, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByN_V_Version(String name,
		long vocabularyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByN_V_Version(name, vocabularyId, version, start, end,
			orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByN_V_Version(String name,
		long vocabularyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		name = Objects.toString(name, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByN_V_Version;
			finderArgs = new Object[] { name, vocabularyId, version };
		}
		else {
			finderPath = _finderPathWithPaginationFindByN_V_Version;
			finderArgs = new Object[] {
					name, vocabularyId, version,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if (!name.equals(assetCategoryVersion.getName()) ||
							(vocabularyId != assetCategoryVersion.getVocabularyId()) ||
							(version != assetCategoryVersion.getVersion())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_N_V_VERSION_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_N_V_VERSION_NAME_2);
			}

			query.append(_FINDER_COLUMN_N_V_VERSION_VOCABULARYID_2);

			query.append(_FINDER_COLUMN_N_V_VERSION_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(vocabularyId);

				qPos.add(version);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByN_V_Version_First(String name,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByN_V_Version_First(name,
				vocabularyId, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByN_V_Version_First(String name,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByN_V_Version(name, vocabularyId,
				version, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByN_V_Version_Last(String name,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByN_V_Version_Last(name,
				vocabularyId, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByN_V_Version_Last(String name,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByN_V_Version(name, vocabularyId, version);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByN_V_Version(name, vocabularyId,
				version, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByN_V_Version_PrevAndNext(
		long assetCategoryVersionId, String name, long vocabularyId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		name = Objects.toString(name, "");

		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByN_V_Version_PrevAndNext(session,
					assetCategoryVersion, name, vocabularyId, version,
					orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByN_V_Version_PrevAndNext(session,
					assetCategoryVersion, name, vocabularyId, version,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByN_V_Version_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		String name, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		boolean bindName = false;

		if (name.isEmpty()) {
			query.append(_FINDER_COLUMN_N_V_VERSION_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_N_V_VERSION_NAME_2);
		}

		query.append(_FINDER_COLUMN_N_V_VERSION_VOCABULARYID_2);

		query.append(_FINDER_COLUMN_N_V_VERSION_VERSION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(vocabularyId);

		qPos.add(version);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where name = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 */
	@Override
	public void removeByN_V_Version(String name, long vocabularyId, int version) {
		for (AssetCategoryVersion assetCategoryVersion : findByN_V_Version(
				name, vocabularyId, version, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where name = &#63; and vocabularyId = &#63; and version = &#63;.
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByN_V_Version(String name, long vocabularyId, int version) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByN_V_Version;

		Object[] finderArgs = new Object[] { name, vocabularyId, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_N_V_VERSION_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_N_V_VERSION_NAME_2);
			}

			query.append(_FINDER_COLUMN_N_V_VERSION_VOCABULARYID_2);

			query.append(_FINDER_COLUMN_N_V_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(vocabularyId);

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_N_V_VERSION_NAME_2 = "assetCategoryVersion.name = ? AND ";
	private static final String _FINDER_COLUMN_N_V_VERSION_NAME_3 = "(assetCategoryVersion.name IS NULL OR assetCategoryVersion.name = '') AND ";
	private static final String _FINDER_COLUMN_N_V_VERSION_VOCABULARYID_2 = "assetCategoryVersion.vocabularyId = ? AND ";
	private static final String _FINDER_COLUMN_N_V_VERSION_VERSION_2 = "assetCategoryVersion.version = ?";
	private FinderPath _finderPathWithPaginationFindByG_P_V;
	private FinderPath _finderPathWithoutPaginationFindByG_P_V;
	private FinderPath _finderPathCountByG_P_V;

	/**
	 * Returns all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByG_P_V(long groupId,
		long parentCategoryId, long vocabularyId) {
		return findByG_P_V(groupId, parentCategoryId, vocabularyId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_P_V(long groupId,
		long parentCategoryId, long vocabularyId, int start, int end) {
		return findByG_P_V(groupId, parentCategoryId, vocabularyId, start, end,
			null);
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
	@Override
	public List<AssetCategoryVersion> findByG_P_V(long groupId,
		long parentCategoryId, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByG_P_V(groupId, parentCategoryId, vocabularyId, start, end,
			orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByG_P_V(long groupId,
		long parentCategoryId, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByG_P_V;
			finderArgs = new Object[] { groupId, parentCategoryId, vocabularyId };
		}
		else {
			finderPath = _finderPathWithPaginationFindByG_P_V;
			finderArgs = new Object[] {
					groupId, parentCategoryId, vocabularyId,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((groupId != assetCategoryVersion.getGroupId()) ||
							(parentCategoryId != assetCategoryVersion.getParentCategoryId()) ||
							(vocabularyId != assetCategoryVersion.getVocabularyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_P_V_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_V_PARENTCATEGORYID_2);

			query.append(_FINDER_COLUMN_G_P_V_VOCABULARYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentCategoryId);

				qPos.add(vocabularyId);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByG_P_V_First(long groupId,
		long parentCategoryId, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_P_V_First(groupId,
				parentCategoryId, vocabularyId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByG_P_V_First(long groupId,
		long parentCategoryId, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByG_P_V(groupId,
				parentCategoryId, vocabularyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByG_P_V_Last(long groupId,
		long parentCategoryId, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_P_V_Last(groupId,
				parentCategoryId, vocabularyId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByG_P_V_Last(long groupId,
		long parentCategoryId, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByG_P_V(groupId, parentCategoryId, vocabularyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByG_P_V(groupId,
				parentCategoryId, vocabularyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByG_P_V_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByG_P_V_PrevAndNext(session, assetCategoryVersion,
					groupId, parentCategoryId, vocabularyId, orderByComparator,
					true);

			array[1] = assetCategoryVersion;

			array[2] = getByG_P_V_PrevAndNext(session, assetCategoryVersion,
					groupId, parentCategoryId, vocabularyId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByG_P_V_PrevAndNext(Session session,
		AssetCategoryVersion assetCategoryVersion, long groupId,
		long parentCategoryId, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_G_P_V_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_V_PARENTCATEGORYID_2);

		query.append(_FINDER_COLUMN_G_P_V_VOCABULARYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentCategoryId);

		qPos.add(vocabularyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 */
	@Override
	public void removeByG_P_V(long groupId, long parentCategoryId,
		long vocabularyId) {
		for (AssetCategoryVersion assetCategoryVersion : findByG_P_V(groupId,
				parentCategoryId, vocabularyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByG_P_V(long groupId, long parentCategoryId,
		long vocabularyId) {
		FinderPath finderPath = _finderPathCountByG_P_V;

		Object[] finderArgs = new Object[] {
				groupId, parentCategoryId, vocabularyId
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_P_V_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_V_PARENTCATEGORYID_2);

			query.append(_FINDER_COLUMN_G_P_V_VOCABULARYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentCategoryId);

				qPos.add(vocabularyId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_P_V_GROUPID_2 = "assetCategoryVersion.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_V_PARENTCATEGORYID_2 = "assetCategoryVersion.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_V_VOCABULARYID_2 = "assetCategoryVersion.vocabularyId = ?";
	private FinderPath _finderPathWithPaginationFindByG_P_V_Version;
	private FinderPath _finderPathWithoutPaginationFindByG_P_V_Version;
	private FinderPath _finderPathCountByG_P_V_Version;

	/**
	 * Returns all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByG_P_V_Version(long groupId,
		long parentCategoryId, long vocabularyId, int version) {
		return findByG_P_V_Version(groupId, parentCategoryId, vocabularyId,
			version, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_P_V_Version(long groupId,
		long parentCategoryId, long vocabularyId, int version, int start,
		int end) {
		return findByG_P_V_Version(groupId, parentCategoryId, vocabularyId,
			version, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_P_V_Version(long groupId,
		long parentCategoryId, long vocabularyId, int version, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByG_P_V_Version(groupId, parentCategoryId, vocabularyId,
			version, start, end, orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByG_P_V_Version(long groupId,
		long parentCategoryId, long vocabularyId, int version, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByG_P_V_Version;
			finderArgs = new Object[] {
					groupId, parentCategoryId, vocabularyId, version
				};
		}
		else {
			finderPath = _finderPathWithPaginationFindByG_P_V_Version;
			finderArgs = new Object[] {
					groupId, parentCategoryId, vocabularyId, version,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((groupId != assetCategoryVersion.getGroupId()) ||
							(parentCategoryId != assetCategoryVersion.getParentCategoryId()) ||
							(vocabularyId != assetCategoryVersion.getVocabularyId()) ||
							(version != assetCategoryVersion.getVersion())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_P_V_VERSION_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_V_VERSION_PARENTCATEGORYID_2);

			query.append(_FINDER_COLUMN_G_P_V_VERSION_VOCABULARYID_2);

			query.append(_FINDER_COLUMN_G_P_V_VERSION_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentCategoryId);

				qPos.add(vocabularyId);

				qPos.add(version);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByG_P_V_Version_First(long groupId,
		long parentCategoryId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_P_V_Version_First(groupId,
				parentCategoryId, vocabularyId, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByG_P_V_Version_First(long groupId,
		long parentCategoryId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByG_P_V_Version(groupId,
				parentCategoryId, vocabularyId, version, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByG_P_V_Version_Last(long groupId,
		long parentCategoryId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_P_V_Version_Last(groupId,
				parentCategoryId, vocabularyId, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByG_P_V_Version_Last(long groupId,
		long parentCategoryId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByG_P_V_Version(groupId, parentCategoryId,
				vocabularyId, version);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByG_P_V_Version(groupId,
				parentCategoryId, vocabularyId, version, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByG_P_V_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByG_P_V_Version_PrevAndNext(session,
					assetCategoryVersion, groupId, parentCategoryId,
					vocabularyId, version, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByG_P_V_Version_PrevAndNext(session,
					assetCategoryVersion, groupId, parentCategoryId,
					vocabularyId, version, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByG_P_V_Version_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		long groupId, long parentCategoryId, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_G_P_V_VERSION_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_V_VERSION_PARENTCATEGORYID_2);

		query.append(_FINDER_COLUMN_G_P_V_VERSION_VOCABULARYID_2);

		query.append(_FINDER_COLUMN_G_P_V_VERSION_VERSION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentCategoryId);

		qPos.add(vocabularyId);

		qPos.add(version);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 */
	@Override
	public void removeByG_P_V_Version(long groupId, long parentCategoryId,
		long vocabularyId, int version) {
		for (AssetCategoryVersion assetCategoryVersion : findByG_P_V_Version(
				groupId, parentCategoryId, vocabularyId, version,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
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
	@Override
	public int countByG_P_V_Version(long groupId, long parentCategoryId,
		long vocabularyId, int version) {
		FinderPath finderPath = _finderPathCountByG_P_V_Version;

		Object[] finderArgs = new Object[] {
				groupId, parentCategoryId, vocabularyId, version
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_P_V_VERSION_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_V_VERSION_PARENTCATEGORYID_2);

			query.append(_FINDER_COLUMN_G_P_V_VERSION_VOCABULARYID_2);

			query.append(_FINDER_COLUMN_G_P_V_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentCategoryId);

				qPos.add(vocabularyId);

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_P_V_VERSION_GROUPID_2 = "assetCategoryVersion.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_V_VERSION_PARENTCATEGORYID_2 = "assetCategoryVersion.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_V_VERSION_VOCABULARYID_2 = "assetCategoryVersion.vocabularyId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_V_VERSION_VERSION_2 = "assetCategoryVersion.version = ?";
	private FinderPath _finderPathWithPaginationFindByG_LikeN_V;
	private FinderPath _finderPathWithoutPaginationFindByG_LikeN_V;
	private FinderPath _finderPathCountByG_LikeN_V;

	/**
	 * Returns all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByG_LikeN_V(long groupId,
		String name, long vocabularyId) {
		return findByG_LikeN_V(groupId, name, vocabularyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_LikeN_V(long groupId,
		String name, long vocabularyId, int start, int end) {
		return findByG_LikeN_V(groupId, name, vocabularyId, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_LikeN_V(long groupId,
		String name, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByG_LikeN_V(groupId, name, vocabularyId, start, end,
			orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByG_LikeN_V(long groupId,
		String name, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		name = Objects.toString(name, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByG_LikeN_V;
			finderArgs = new Object[] { groupId, name, vocabularyId };
		}
		else {
			finderPath = _finderPathWithPaginationFindByG_LikeN_V;
			finderArgs = new Object[] {
					groupId, name, vocabularyId,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((groupId != assetCategoryVersion.getGroupId()) ||
							!name.equals(assetCategoryVersion.getName()) ||
							(vocabularyId != assetCategoryVersion.getVocabularyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_LIKEN_V_GROUPID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_G_LIKEN_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_LIKEN_V_NAME_2);
			}

			query.append(_FINDER_COLUMN_G_LIKEN_V_VOCABULARYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(vocabularyId);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByG_LikeN_V_First(long groupId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_LikeN_V_First(groupId,
				name, vocabularyId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByG_LikeN_V_First(long groupId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByG_LikeN_V(groupId, name,
				vocabularyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByG_LikeN_V_Last(long groupId, String name,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_LikeN_V_Last(groupId,
				name, vocabularyId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByG_LikeN_V_Last(long groupId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByG_LikeN_V(groupId, name, vocabularyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByG_LikeN_V(groupId, name,
				vocabularyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByG_LikeN_V_PrevAndNext(
		long assetCategoryVersionId, long groupId, String name,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		name = Objects.toString(name, "");

		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByG_LikeN_V_PrevAndNext(session,
					assetCategoryVersion, groupId, name, vocabularyId,
					orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByG_LikeN_V_PrevAndNext(session,
					assetCategoryVersion, groupId, name, vocabularyId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByG_LikeN_V_PrevAndNext(Session session,
		AssetCategoryVersion assetCategoryVersion, long groupId, String name,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_G_LIKEN_V_GROUPID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			query.append(_FINDER_COLUMN_G_LIKEN_V_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_LIKEN_V_NAME_2);
		}

		query.append(_FINDER_COLUMN_G_LIKEN_V_VOCABULARYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(vocabularyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 */
	@Override
	public void removeByG_LikeN_V(long groupId, String name, long vocabularyId) {
		for (AssetCategoryVersion assetCategoryVersion : findByG_LikeN_V(
				groupId, name, vocabularyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByG_LikeN_V(long groupId, String name, long vocabularyId) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByG_LikeN_V;

		Object[] finderArgs = new Object[] { groupId, name, vocabularyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_LIKEN_V_GROUPID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_G_LIKEN_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_LIKEN_V_NAME_2);
			}

			query.append(_FINDER_COLUMN_G_LIKEN_V_VOCABULARYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(vocabularyId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_LIKEN_V_GROUPID_2 = "assetCategoryVersion.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_LIKEN_V_NAME_2 = "assetCategoryVersion.name = ? AND ";
	private static final String _FINDER_COLUMN_G_LIKEN_V_NAME_3 = "(assetCategoryVersion.name IS NULL OR assetCategoryVersion.name = '') AND ";
	private static final String _FINDER_COLUMN_G_LIKEN_V_VOCABULARYID_2 = "assetCategoryVersion.vocabularyId = ?";
	private FinderPath _finderPathWithPaginationFindByG_LikeN_V_Version;
	private FinderPath _finderPathWithoutPaginationFindByG_LikeN_V_Version;
	private FinderPath _finderPathCountByG_LikeN_V_Version;

	/**
	 * Returns all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByG_LikeN_V_Version(long groupId,
		String name, long vocabularyId, int version) {
		return findByG_LikeN_V_Version(groupId, name, vocabularyId, version,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_LikeN_V_Version(long groupId,
		String name, long vocabularyId, int version, int start, int end) {
		return findByG_LikeN_V_Version(groupId, name, vocabularyId, version,
			start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_LikeN_V_Version(long groupId,
		String name, long vocabularyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByG_LikeN_V_Version(groupId, name, vocabularyId, version,
			start, end, orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByG_LikeN_V_Version(long groupId,
		String name, long vocabularyId, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		name = Objects.toString(name, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByG_LikeN_V_Version;
			finderArgs = new Object[] { groupId, name, vocabularyId, version };
		}
		else {
			finderPath = _finderPathWithPaginationFindByG_LikeN_V_Version;
			finderArgs = new Object[] {
					groupId, name, vocabularyId, version,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((groupId != assetCategoryVersion.getGroupId()) ||
							!name.equals(assetCategoryVersion.getName()) ||
							(vocabularyId != assetCategoryVersion.getVocabularyId()) ||
							(version != assetCategoryVersion.getVersion())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_LIKEN_V_VERSION_GROUPID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_G_LIKEN_V_VERSION_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_LIKEN_V_VERSION_NAME_2);
			}

			query.append(_FINDER_COLUMN_G_LIKEN_V_VERSION_VOCABULARYID_2);

			query.append(_FINDER_COLUMN_G_LIKEN_V_VERSION_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(vocabularyId);

				qPos.add(version);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByG_LikeN_V_Version_First(long groupId,
		String name, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_LikeN_V_Version_First(groupId,
				name, vocabularyId, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByG_LikeN_V_Version_First(long groupId,
		String name, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByG_LikeN_V_Version(groupId,
				name, vocabularyId, version, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByG_LikeN_V_Version_Last(long groupId,
		String name, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_LikeN_V_Version_Last(groupId,
				name, vocabularyId, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByG_LikeN_V_Version_Last(long groupId,
		String name, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByG_LikeN_V_Version(groupId, name, vocabularyId,
				version);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByG_LikeN_V_Version(groupId,
				name, vocabularyId, version, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByG_LikeN_V_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, String name,
		long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		name = Objects.toString(name, "");

		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByG_LikeN_V_Version_PrevAndNext(session,
					assetCategoryVersion, groupId, name, vocabularyId, version,
					orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByG_LikeN_V_Version_PrevAndNext(session,
					assetCategoryVersion, groupId, name, vocabularyId, version,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByG_LikeN_V_Version_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		long groupId, String name, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_G_LIKEN_V_VERSION_GROUPID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			query.append(_FINDER_COLUMN_G_LIKEN_V_VERSION_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_LIKEN_V_VERSION_NAME_2);
		}

		query.append(_FINDER_COLUMN_G_LIKEN_V_VERSION_VOCABULARYID_2);

		query.append(_FINDER_COLUMN_G_LIKEN_V_VERSION_VERSION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(vocabularyId);

		qPos.add(version);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where groupId = &#63; and name = &#63; and vocabularyId = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param version the version
	 */
	@Override
	public void removeByG_LikeN_V_Version(long groupId, String name,
		long vocabularyId, int version) {
		for (AssetCategoryVersion assetCategoryVersion : findByG_LikeN_V_Version(
				groupId, name, vocabularyId, version, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
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
	@Override
	public int countByG_LikeN_V_Version(long groupId, String name,
		long vocabularyId, int version) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByG_LikeN_V_Version;

		Object[] finderArgs = new Object[] { groupId, name, vocabularyId, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_LIKEN_V_VERSION_GROUPID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_G_LIKEN_V_VERSION_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_LIKEN_V_VERSION_NAME_2);
			}

			query.append(_FINDER_COLUMN_G_LIKEN_V_VERSION_VOCABULARYID_2);

			query.append(_FINDER_COLUMN_G_LIKEN_V_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(vocabularyId);

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_LIKEN_V_VERSION_GROUPID_2 = "assetCategoryVersion.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_LIKEN_V_VERSION_NAME_2 = "assetCategoryVersion.name = ? AND ";
	private static final String _FINDER_COLUMN_G_LIKEN_V_VERSION_NAME_3 = "(assetCategoryVersion.name IS NULL OR assetCategoryVersion.name = '') AND ";
	private static final String _FINDER_COLUMN_G_LIKEN_V_VERSION_VOCABULARYID_2 = "assetCategoryVersion.vocabularyId = ? AND ";
	private static final String _FINDER_COLUMN_G_LIKEN_V_VERSION_VERSION_2 = "assetCategoryVersion.version = ?";
	private FinderPath _finderPathWithPaginationFindByP_N_V;
	private FinderPath _finderPathWithoutPaginationFindByP_N_V;
	private FinderPath _finderPathCountByP_N_V;

	/**
	 * Returns all the asset category versions where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByP_N_V(long parentCategoryId,
		String name, long vocabularyId) {
		return findByP_N_V(parentCategoryId, name, vocabularyId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByP_N_V(long parentCategoryId,
		String name, long vocabularyId, int start, int end) {
		return findByP_N_V(parentCategoryId, name, vocabularyId, start, end,
			null);
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
	@Override
	public List<AssetCategoryVersion> findByP_N_V(long parentCategoryId,
		String name, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByP_N_V(parentCategoryId, name, vocabularyId, start, end,
			orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByP_N_V(long parentCategoryId,
		String name, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		name = Objects.toString(name, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByP_N_V;
			finderArgs = new Object[] { parentCategoryId, name, vocabularyId };
		}
		else {
			finderPath = _finderPathWithPaginationFindByP_N_V;
			finderArgs = new Object[] {
					parentCategoryId, name, vocabularyId,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((parentCategoryId != assetCategoryVersion.getParentCategoryId()) ||
							!name.equals(assetCategoryVersion.getName()) ||
							(vocabularyId != assetCategoryVersion.getVocabularyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_P_N_V_PARENTCATEGORYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_P_N_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_P_N_V_NAME_2);
			}

			query.append(_FINDER_COLUMN_P_N_V_VOCABULARYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(vocabularyId);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByP_N_V_First(long parentCategoryId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByP_N_V_First(parentCategoryId,
				name, vocabularyId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByP_N_V_First(long parentCategoryId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByP_N_V(parentCategoryId, name,
				vocabularyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByP_N_V_Last(long parentCategoryId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByP_N_V_Last(parentCategoryId,
				name, vocabularyId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByP_N_V_Last(long parentCategoryId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByP_N_V(parentCategoryId, name, vocabularyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByP_N_V(parentCategoryId, name,
				vocabularyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByP_N_V_PrevAndNext(
		long assetCategoryVersionId, long parentCategoryId, String name,
		long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		name = Objects.toString(name, "");

		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByP_N_V_PrevAndNext(session, assetCategoryVersion,
					parentCategoryId, name, vocabularyId, orderByComparator,
					true);

			array[1] = assetCategoryVersion;

			array[2] = getByP_N_V_PrevAndNext(session, assetCategoryVersion,
					parentCategoryId, name, vocabularyId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByP_N_V_PrevAndNext(Session session,
		AssetCategoryVersion assetCategoryVersion, long parentCategoryId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_P_N_V_PARENTCATEGORYID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			query.append(_FINDER_COLUMN_P_N_V_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_P_N_V_NAME_2);
		}

		query.append(_FINDER_COLUMN_P_N_V_VOCABULARYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentCategoryId);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(vocabularyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; from the database.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 */
	@Override
	public void removeByP_N_V(long parentCategoryId, String name,
		long vocabularyId) {
		for (AssetCategoryVersion assetCategoryVersion : findByP_N_V(
				parentCategoryId, name, vocabularyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByP_N_V(long parentCategoryId, String name,
		long vocabularyId) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByP_N_V;

		Object[] finderArgs = new Object[] { parentCategoryId, name, vocabularyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_P_N_V_PARENTCATEGORYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_P_N_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_P_N_V_NAME_2);
			}

			query.append(_FINDER_COLUMN_P_N_V_VOCABULARYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(vocabularyId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_P_N_V_PARENTCATEGORYID_2 = "assetCategoryVersion.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_P_N_V_NAME_2 = "assetCategoryVersion.name = ? AND ";
	private static final String _FINDER_COLUMN_P_N_V_NAME_3 = "(assetCategoryVersion.name IS NULL OR assetCategoryVersion.name = '') AND ";
	private static final String _FINDER_COLUMN_P_N_V_VOCABULARYID_2 = "assetCategoryVersion.vocabularyId = ?";
	private FinderPath _finderPathFetchByP_N_V_Version;
	private FinderPath _finderPathCountByP_N_V_Version;

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
	@Override
	public AssetCategoryVersion findByP_N_V_Version(long parentCategoryId,
		String name, long vocabularyId, int version)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByP_N_V_Version(parentCategoryId,
				name, vocabularyId, version);

		if (assetCategoryVersion == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("parentCategoryId=");
			msg.append(parentCategoryId);

			msg.append(", name=");
			msg.append(name);

			msg.append(", vocabularyId=");
			msg.append(vocabularyId);

			msg.append(", version=");
			msg.append(version);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCategoryVersionException(msg.toString());
		}

		return assetCategoryVersion;
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
	@Override
	public AssetCategoryVersion fetchByP_N_V_Version(long parentCategoryId,
		String name, long vocabularyId, int version) {
		return fetchByP_N_V_Version(parentCategoryId, name, vocabularyId,
			version, true);
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
	@Override
	public AssetCategoryVersion fetchByP_N_V_Version(long parentCategoryId,
		String name, long vocabularyId, int version, boolean retrieveFromCache) {
		name = Objects.toString(name, "");

		Object[] finderArgs = new Object[] {
				parentCategoryId, name, vocabularyId, version
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(_finderPathFetchByP_N_V_Version,
					finderArgs, this);
		}

		if (result instanceof AssetCategoryVersion) {
			AssetCategoryVersion assetCategoryVersion = (AssetCategoryVersion)result;

			if ((parentCategoryId != assetCategoryVersion.getParentCategoryId()) ||
					!Objects.equals(name, assetCategoryVersion.getName()) ||
					(vocabularyId != assetCategoryVersion.getVocabularyId()) ||
					(version != assetCategoryVersion.getVersion())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_P_N_V_VERSION_PARENTCATEGORYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_P_N_V_VERSION_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_P_N_V_VERSION_NAME_2);
			}

			query.append(_FINDER_COLUMN_P_N_V_VERSION_VOCABULARYID_2);

			query.append(_FINDER_COLUMN_P_N_V_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(vocabularyId);

				qPos.add(version);

				List<AssetCategoryVersion> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(_finderPathFetchByP_N_V_Version,
						finderArgs, list);
				}
				else {
					AssetCategoryVersion assetCategoryVersion = list.get(0);

					result = assetCategoryVersion;

					cacheResult(assetCategoryVersion);
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(_finderPathFetchByP_N_V_Version,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (AssetCategoryVersion)result;
		}
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
	@Override
	public AssetCategoryVersion removeByP_N_V_Version(long parentCategoryId,
		String name, long vocabularyId, int version)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = findByP_N_V_Version(parentCategoryId,
				name, vocabularyId, version);

		return remove(assetCategoryVersion);
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
	@Override
	public int countByP_N_V_Version(long parentCategoryId, String name,
		long vocabularyId, int version) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByP_N_V_Version;

		Object[] finderArgs = new Object[] {
				parentCategoryId, name, vocabularyId, version
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_P_N_V_VERSION_PARENTCATEGORYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_P_N_V_VERSION_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_P_N_V_VERSION_NAME_2);
			}

			query.append(_FINDER_COLUMN_P_N_V_VERSION_VOCABULARYID_2);

			query.append(_FINDER_COLUMN_P_N_V_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(vocabularyId);

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_P_N_V_VERSION_PARENTCATEGORYID_2 = "assetCategoryVersion.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_P_N_V_VERSION_NAME_2 = "assetCategoryVersion.name = ? AND ";
	private static final String _FINDER_COLUMN_P_N_V_VERSION_NAME_3 = "(assetCategoryVersion.name IS NULL OR assetCategoryVersion.name = '') AND ";
	private static final String _FINDER_COLUMN_P_N_V_VERSION_VOCABULARYID_2 = "assetCategoryVersion.vocabularyId = ? AND ";
	private static final String _FINDER_COLUMN_P_N_V_VERSION_VERSION_2 = "assetCategoryVersion.version = ?";
	private FinderPath _finderPathWithPaginationFindByG_P_N_V;
	private FinderPath _finderPathWithoutPaginationFindByG_P_N_V;
	private FinderPath _finderPathCountByG_P_N_V;

	/**
	 * Returns all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId) {
		return findByG_P_N_V(groupId, parentCategoryId, name, vocabularyId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId, int start,
		int end) {
		return findByG_P_N_V(groupId, parentCategoryId, name, vocabularyId,
			start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByG_P_N_V(groupId, parentCategoryId, name, vocabularyId,
			start, end, orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId, int start,
		int end, OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		name = Objects.toString(name, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByG_P_N_V;
			finderArgs = new Object[] {
					groupId, parentCategoryId, name, vocabularyId
				};
		}
		else {
			finderPath = _finderPathWithPaginationFindByG_P_N_V;
			finderArgs = new Object[] {
					groupId, parentCategoryId, name, vocabularyId,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((groupId != assetCategoryVersion.getGroupId()) ||
							(parentCategoryId != assetCategoryVersion.getParentCategoryId()) ||
							!name.equals(assetCategoryVersion.getName()) ||
							(vocabularyId != assetCategoryVersion.getVocabularyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_P_N_V_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_N_V_PARENTCATEGORYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_G_P_N_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_P_N_V_NAME_2);
			}

			query.append(_FINDER_COLUMN_G_P_N_V_VOCABULARYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentCategoryId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(vocabularyId);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByG_P_N_V_First(long groupId,
		long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_P_N_V_First(groupId,
				parentCategoryId, name, vocabularyId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByG_P_N_V_First(long groupId,
		long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByG_P_N_V(groupId,
				parentCategoryId, name, vocabularyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByG_P_N_V_Last(long groupId,
		long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_P_N_V_Last(groupId,
				parentCategoryId, name, vocabularyId, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByG_P_N_V_Last(long groupId,
		long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByG_P_N_V(groupId, parentCategoryId, name, vocabularyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByG_P_N_V(groupId,
				parentCategoryId, name, vocabularyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByG_P_N_V_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		name = Objects.toString(name, "");

		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByG_P_N_V_PrevAndNext(session, assetCategoryVersion,
					groupId, parentCategoryId, name, vocabularyId,
					orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByG_P_N_V_PrevAndNext(session, assetCategoryVersion,
					groupId, parentCategoryId, name, vocabularyId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByG_P_N_V_PrevAndNext(Session session,
		AssetCategoryVersion assetCategoryVersion, long groupId,
		long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_G_P_N_V_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_N_V_PARENTCATEGORYID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			query.append(_FINDER_COLUMN_G_P_N_V_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_P_N_V_NAME_2);
		}

		query.append(_FINDER_COLUMN_G_P_N_V_VOCABULARYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentCategoryId);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(vocabularyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 */
	@Override
	public void removeByG_P_N_V(long groupId, long parentCategoryId,
		String name, long vocabularyId) {
		for (AssetCategoryVersion assetCategoryVersion : findByG_P_N_V(
				groupId, parentCategoryId, name, vocabularyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
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
	@Override
	public int countByG_P_N_V(long groupId, long parentCategoryId, String name,
		long vocabularyId) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByG_P_N_V;

		Object[] finderArgs = new Object[] {
				groupId, parentCategoryId, name, vocabularyId
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_P_N_V_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_N_V_PARENTCATEGORYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_G_P_N_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_P_N_V_NAME_2);
			}

			query.append(_FINDER_COLUMN_G_P_N_V_VOCABULARYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentCategoryId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(vocabularyId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_P_N_V_GROUPID_2 = "assetCategoryVersion.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_N_V_PARENTCATEGORYID_2 = "assetCategoryVersion.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_N_V_NAME_2 = "assetCategoryVersion.name = ? AND ";
	private static final String _FINDER_COLUMN_G_P_N_V_NAME_3 = "(assetCategoryVersion.name IS NULL OR assetCategoryVersion.name = '') AND ";
	private static final String _FINDER_COLUMN_G_P_N_V_VOCABULARYID_2 = "assetCategoryVersion.vocabularyId = ?";
	private FinderPath _finderPathWithPaginationFindByG_P_N_V_Version;
	private FinderPath _finderPathWithoutPaginationFindByG_P_N_V_Version;
	private FinderPath _finderPathCountByG_P_N_V_Version;

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
	@Override
	public List<AssetCategoryVersion> findByG_P_N_V_Version(long groupId,
		long parentCategoryId, String name, long vocabularyId, int version) {
		return findByG_P_N_V_Version(groupId, parentCategoryId, name,
			vocabularyId, version, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_P_N_V_Version(long groupId,
		long parentCategoryId, String name, long vocabularyId, int version,
		int start, int end) {
		return findByG_P_N_V_Version(groupId, parentCategoryId, name,
			vocabularyId, version, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByG_P_N_V_Version(long groupId,
		long parentCategoryId, String name, long vocabularyId, int version,
		int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByG_P_N_V_Version(groupId, parentCategoryId, name,
			vocabularyId, version, start, end, orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByG_P_N_V_Version(long groupId,
		long parentCategoryId, String name, long vocabularyId, int version,
		int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		name = Objects.toString(name, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByG_P_N_V_Version;
			finderArgs = new Object[] {
					groupId, parentCategoryId, name, vocabularyId, version
				};
		}
		else {
			finderPath = _finderPathWithPaginationFindByG_P_N_V_Version;
			finderArgs = new Object[] {
					groupId, parentCategoryId, name, vocabularyId, version,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((groupId != assetCategoryVersion.getGroupId()) ||
							(parentCategoryId != assetCategoryVersion.getParentCategoryId()) ||
							!name.equals(assetCategoryVersion.getName()) ||
							(vocabularyId != assetCategoryVersion.getVocabularyId()) ||
							(version != assetCategoryVersion.getVersion())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(7 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(7);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_P_N_V_VERSION_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_N_V_VERSION_PARENTCATEGORYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_G_P_N_V_VERSION_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_P_N_V_VERSION_NAME_2);
			}

			query.append(_FINDER_COLUMN_G_P_N_V_VERSION_VOCABULARYID_2);

			query.append(_FINDER_COLUMN_G_P_N_V_VERSION_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentCategoryId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(vocabularyId);

				qPos.add(version);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByG_P_N_V_Version_First(long groupId,
		long parentCategoryId, String name, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_P_N_V_Version_First(groupId,
				parentCategoryId, name, vocabularyId, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByG_P_N_V_Version_First(long groupId,
		long parentCategoryId, String name, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByG_P_N_V_Version(groupId,
				parentCategoryId, name, vocabularyId, version, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByG_P_N_V_Version_Last(long groupId,
		long parentCategoryId, String name, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByG_P_N_V_Version_Last(groupId,
				parentCategoryId, name, vocabularyId, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByG_P_N_V_Version_Last(long groupId,
		long parentCategoryId, String name, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByG_P_N_V_Version(groupId, parentCategoryId, name,
				vocabularyId, version);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByG_P_N_V_Version(groupId,
				parentCategoryId, name, vocabularyId, version, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByG_P_N_V_Version_PrevAndNext(
		long assetCategoryVersionId, long groupId, long parentCategoryId,
		String name, long vocabularyId, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		name = Objects.toString(name, "");

		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByG_P_N_V_Version_PrevAndNext(session,
					assetCategoryVersion, groupId, parentCategoryId, name,
					vocabularyId, version, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByG_P_N_V_Version_PrevAndNext(session,
					assetCategoryVersion, groupId, parentCategoryId, name,
					vocabularyId, version, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByG_P_N_V_Version_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		long groupId, long parentCategoryId, String name, long vocabularyId,
		int version, OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(8 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(7);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_G_P_N_V_VERSION_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_N_V_VERSION_PARENTCATEGORYID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			query.append(_FINDER_COLUMN_G_P_N_V_VERSION_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_P_N_V_VERSION_NAME_2);
		}

		query.append(_FINDER_COLUMN_G_P_N_V_VERSION_VOCABULARYID_2);

		query.append(_FINDER_COLUMN_G_P_N_V_VERSION_VERSION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentCategoryId);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(vocabularyId);

		qPos.add(version);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
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
	@Override
	public void removeByG_P_N_V_Version(long groupId, long parentCategoryId,
		String name, long vocabularyId, int version) {
		for (AssetCategoryVersion assetCategoryVersion : findByG_P_N_V_Version(
				groupId, parentCategoryId, name, vocabularyId, version,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
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
	@Override
	public int countByG_P_N_V_Version(long groupId, long parentCategoryId,
		String name, long vocabularyId, int version) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByG_P_N_V_Version;

		Object[] finderArgs = new Object[] {
				groupId, parentCategoryId, name, vocabularyId, version
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_P_N_V_VERSION_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_N_V_VERSION_PARENTCATEGORYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_G_P_N_V_VERSION_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_P_N_V_VERSION_NAME_2);
			}

			query.append(_FINDER_COLUMN_G_P_N_V_VERSION_VOCABULARYID_2);

			query.append(_FINDER_COLUMN_G_P_N_V_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentCategoryId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(vocabularyId);

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_P_N_V_VERSION_GROUPID_2 = "assetCategoryVersion.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_N_V_VERSION_PARENTCATEGORYID_2 =
		"assetCategoryVersion.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_N_V_VERSION_NAME_2 = "assetCategoryVersion.name = ? AND ";
	private static final String _FINDER_COLUMN_G_P_N_V_VERSION_NAME_3 = "(assetCategoryVersion.name IS NULL OR assetCategoryVersion.name = '') AND ";
	private static final String _FINDER_COLUMN_G_P_N_V_VERSION_VOCABULARYID_2 = "assetCategoryVersion.vocabularyId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_N_V_VERSION_VERSION_2 = "assetCategoryVersion.version = ?";
	private FinderPath _finderPathWithPaginationFindByC_ERC;
	private FinderPath _finderPathWithoutPaginationFindByC_ERC;
	private FinderPath _finderPathCountByC_ERC;

	/**
	 * Returns all the asset category versions where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByC_ERC(long companyId,
		String externalReferenceCode) {
		return findByC_ERC(companyId, externalReferenceCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByC_ERC(long companyId,
		String externalReferenceCode, int start, int end) {
		return findByC_ERC(companyId, externalReferenceCode, start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByC_ERC(long companyId,
		String externalReferenceCode, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByC_ERC(companyId, externalReferenceCode, start, end,
			orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByC_ERC(long companyId,
		String externalReferenceCode, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByC_ERC;
			finderArgs = new Object[] { companyId, externalReferenceCode };
		}
		else {
			finderPath = _finderPathWithPaginationFindByC_ERC;
			finderArgs = new Object[] {
					companyId, externalReferenceCode,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((companyId != assetCategoryVersion.getCompanyId()) ||
							!externalReferenceCode.equals(
								assetCategoryVersion.getExternalReferenceCode())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindExternalReferenceCode) {
					qPos.add(externalReferenceCode);
				}

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByC_ERC_First(long companyId,
		String externalReferenceCode,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByC_ERC_First(companyId,
				externalReferenceCode, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", externalReferenceCode=");
		msg.append(externalReferenceCode);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the first asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByC_ERC_First(long companyId,
		String externalReferenceCode,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByC_ERC(companyId,
				externalReferenceCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByC_ERC_Last(long companyId,
		String externalReferenceCode,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByC_ERC_Last(companyId,
				externalReferenceCode, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", externalReferenceCode=");
		msg.append(externalReferenceCode);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
	}

	/**
	 * Returns the last asset category version in the ordered set where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category version, or <code>null</code> if a matching asset category version could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByC_ERC_Last(long companyId,
		String externalReferenceCode,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByC_ERC(companyId, externalReferenceCode);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByC_ERC(companyId,
				externalReferenceCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByC_ERC_PrevAndNext(
		long assetCategoryVersionId, long companyId,
		String externalReferenceCode,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByC_ERC_PrevAndNext(session, assetCategoryVersion,
					companyId, externalReferenceCode, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByC_ERC_PrevAndNext(session, assetCategoryVersion,
					companyId, externalReferenceCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByC_ERC_PrevAndNext(Session session,
		AssetCategoryVersion assetCategoryVersion, long companyId,
		String externalReferenceCode,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

		boolean bindExternalReferenceCode = false;

		if (externalReferenceCode.isEmpty()) {
			query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
		}
		else {
			bindExternalReferenceCode = true;

			query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindExternalReferenceCode) {
			qPos.add(externalReferenceCode);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 */
	@Override
	public void removeByC_ERC(long companyId, String externalReferenceCode) {
		for (AssetCategoryVersion assetCategoryVersion : findByC_ERC(
				companyId, externalReferenceCode, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByC_ERC(long companyId, String externalReferenceCode) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		FinderPath finderPath = _finderPathCountByC_ERC;

		Object[] finderArgs = new Object[] { companyId, externalReferenceCode };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindExternalReferenceCode) {
					qPos.add(externalReferenceCode);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_ERC_COMPANYID_2 = "assetCategoryVersion.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 = "assetCategoryVersion.externalReferenceCode = ?";
	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 = "(assetCategoryVersion.externalReferenceCode IS NULL OR assetCategoryVersion.externalReferenceCode = '')";
	private FinderPath _finderPathWithPaginationFindByC_ERC_Version;
	private FinderPath _finderPathWithoutPaginationFindByC_ERC_Version;
	private FinderPath _finderPathCountByC_ERC_Version;

	/**
	 * Returns all the asset category versions where companyId = &#63; and externalReferenceCode = &#63; and version = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param version the version
	 * @return the matching asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findByC_ERC_Version(long companyId,
		String externalReferenceCode, int version) {
		return findByC_ERC_Version(companyId, externalReferenceCode, version,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findByC_ERC_Version(long companyId,
		String externalReferenceCode, int version, int start, int end) {
		return findByC_ERC_Version(companyId, externalReferenceCode, version,
			start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findByC_ERC_Version(long companyId,
		String externalReferenceCode, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findByC_ERC_Version(companyId, externalReferenceCode, version,
			start, end, orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findByC_ERC_Version(long companyId,
		String externalReferenceCode, int version, int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByC_ERC_Version;
			finderArgs = new Object[] { companyId, externalReferenceCode, version };
		}
		else {
			finderPath = _finderPathWithPaginationFindByC_ERC_Version;
			finderArgs = new Object[] {
					companyId, externalReferenceCode, version,
					
					start, end, orderByComparator
				};
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategoryVersion assetCategoryVersion : list) {
					if ((companyId != assetCategoryVersion.getCompanyId()) ||
							!externalReferenceCode.equals(
								assetCategoryVersion.getExternalReferenceCode()) ||
							(version != assetCategoryVersion.getVersion())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_C_ERC_VERSION_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				query.append(_FINDER_COLUMN_C_ERC_VERSION_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				query.append(_FINDER_COLUMN_C_ERC_VERSION_EXTERNALREFERENCECODE_2);
			}

			query.append(_FINDER_COLUMN_C_ERC_VERSION_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindExternalReferenceCode) {
					qPos.add(externalReferenceCode);
				}

				qPos.add(version);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetCategoryVersion findByC_ERC_Version_First(long companyId,
		String externalReferenceCode, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByC_ERC_Version_First(companyId,
				externalReferenceCode, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", externalReferenceCode=");
		msg.append(externalReferenceCode);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByC_ERC_Version_First(long companyId,
		String externalReferenceCode, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		List<AssetCategoryVersion> list = findByC_ERC_Version(companyId,
				externalReferenceCode, version, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion findByC_ERC_Version_Last(long companyId,
		String externalReferenceCode, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByC_ERC_Version_Last(companyId,
				externalReferenceCode, version, orderByComparator);

		if (assetCategoryVersion != null) {
			return assetCategoryVersion;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", externalReferenceCode=");
		msg.append(externalReferenceCode);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchCategoryVersionException(msg.toString());
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
	@Override
	public AssetCategoryVersion fetchByC_ERC_Version_Last(long companyId,
		String externalReferenceCode, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		int count = countByC_ERC_Version(companyId, externalReferenceCode,
				version);

		if (count == 0) {
			return null;
		}

		List<AssetCategoryVersion> list = findByC_ERC_Version(companyId,
				externalReferenceCode, version, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetCategoryVersion[] findByC_ERC_Version_PrevAndNext(
		long assetCategoryVersionId, long companyId,
		String externalReferenceCode, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator)
		throws NoSuchCategoryVersionException {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		AssetCategoryVersion assetCategoryVersion = findByPrimaryKey(assetCategoryVersionId);

		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion[] array = new AssetCategoryVersionImpl[3];

			array[0] = getByC_ERC_Version_PrevAndNext(session,
					assetCategoryVersion, companyId, externalReferenceCode,
					version, orderByComparator, true);

			array[1] = assetCategoryVersion;

			array[2] = getByC_ERC_Version_PrevAndNext(session,
					assetCategoryVersion, companyId, externalReferenceCode,
					version, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategoryVersion getByC_ERC_Version_PrevAndNext(
		Session session, AssetCategoryVersion assetCategoryVersion,
		long companyId, String externalReferenceCode, int version,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_ASSETCATEGORYVERSION_WHERE);

		query.append(_FINDER_COLUMN_C_ERC_VERSION_COMPANYID_2);

		boolean bindExternalReferenceCode = false;

		if (externalReferenceCode.isEmpty()) {
			query.append(_FINDER_COLUMN_C_ERC_VERSION_EXTERNALREFERENCECODE_3);
		}
		else {
			bindExternalReferenceCode = true;

			query.append(_FINDER_COLUMN_C_ERC_VERSION_EXTERNALREFERENCECODE_2);
		}

		query.append(_FINDER_COLUMN_C_ERC_VERSION_VERSION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindExternalReferenceCode) {
			qPos.add(externalReferenceCode);
		}

		qPos.add(version);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategoryVersion)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategoryVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset category versions where companyId = &#63; and externalReferenceCode = &#63; and version = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param version the version
	 */
	@Override
	public void removeByC_ERC_Version(long companyId,
		String externalReferenceCode, int version) {
		for (AssetCategoryVersion assetCategoryVersion : findByC_ERC_Version(
				companyId, externalReferenceCode, version, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions where companyId = &#63; and externalReferenceCode = &#63; and version = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param version the version
	 * @return the number of matching asset category versions
	 */
	@Override
	public int countByC_ERC_Version(long companyId,
		String externalReferenceCode, int version) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		FinderPath finderPath = _finderPathCountByC_ERC_Version;

		Object[] finderArgs = new Object[] {
				companyId, externalReferenceCode, version
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETCATEGORYVERSION_WHERE);

			query.append(_FINDER_COLUMN_C_ERC_VERSION_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				query.append(_FINDER_COLUMN_C_ERC_VERSION_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				query.append(_FINDER_COLUMN_C_ERC_VERSION_EXTERNALREFERENCECODE_2);
			}

			query.append(_FINDER_COLUMN_C_ERC_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindExternalReferenceCode) {
					qPos.add(externalReferenceCode);
				}

				qPos.add(version);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_ERC_VERSION_COMPANYID_2 = "assetCategoryVersion.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_ERC_VERSION_EXTERNALREFERENCECODE_2 =
		"assetCategoryVersion.externalReferenceCode = ? AND ";
	private static final String _FINDER_COLUMN_C_ERC_VERSION_EXTERNALREFERENCECODE_3 =
		"(assetCategoryVersion.externalReferenceCode IS NULL OR assetCategoryVersion.externalReferenceCode = '') AND ";
	private static final String _FINDER_COLUMN_C_ERC_VERSION_VERSION_2 = "assetCategoryVersion.version = ?";

	public AssetCategoryVersionPersistenceImpl() {
		setModelClass(AssetCategoryVersion.class);

		setModelImplClass(AssetCategoryVersionImpl.class);
		setModelPKClass(long.class);
		setEntityCacheEnabled(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED);
	}

	/**
	 * Caches the asset category version in the entity cache if it is enabled.
	 *
	 * @param assetCategoryVersion the asset category version
	 */
	@Override
	public void cacheResult(AssetCategoryVersion assetCategoryVersion) {
		EntityCacheUtil.putResult(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
			AssetCategoryVersionImpl.class,
			assetCategoryVersion.getPrimaryKey(), assetCategoryVersion);

		FinderCacheUtil.putResult(_finderPathFetchByCategoryId_Version,
			new Object[] {
				assetCategoryVersion.getCategoryId(),
				assetCategoryVersion.getVersion()
			}, assetCategoryVersion);

		FinderCacheUtil.putResult(_finderPathFetchByUUID_G_Version,
			new Object[] {
				assetCategoryVersion.getUuid(),
				assetCategoryVersion.getGroupId(),
				assetCategoryVersion.getVersion()
			}, assetCategoryVersion);

		FinderCacheUtil.putResult(_finderPathFetchByP_N_V_Version,
			new Object[] {
				assetCategoryVersion.getParentCategoryId(),
				assetCategoryVersion.getName(),
				assetCategoryVersion.getVocabularyId(),
				assetCategoryVersion.getVersion()
			}, assetCategoryVersion);

		assetCategoryVersion.resetOriginalValues();
	}

	/**
	 * Caches the asset category versions in the entity cache if it is enabled.
	 *
	 * @param assetCategoryVersions the asset category versions
	 */
	@Override
	public void cacheResult(List<AssetCategoryVersion> assetCategoryVersions) {
		for (AssetCategoryVersion assetCategoryVersion : assetCategoryVersions) {
			if (EntityCacheUtil.getResult(
						AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
						AssetCategoryVersionImpl.class,
						assetCategoryVersion.getPrimaryKey()) == null) {
				cacheResult(assetCategoryVersion);
			}
			else {
				assetCategoryVersion.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset category versions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>com.liferay.portal.kernel.dao.orm.FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(AssetCategoryVersionImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset category version.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>com.liferay.portal.kernel.dao.orm.FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetCategoryVersion assetCategoryVersion) {
		EntityCacheUtil.removeResult(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
			AssetCategoryVersionImpl.class, assetCategoryVersion.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AssetCategoryVersionModelImpl)assetCategoryVersion,
			true);
	}

	@Override
	public void clearCache(List<AssetCategoryVersion> assetCategoryVersions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetCategoryVersion assetCategoryVersion : assetCategoryVersions) {
			EntityCacheUtil.removeResult(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				assetCategoryVersion.getPrimaryKey());

			clearUniqueFindersCache((AssetCategoryVersionModelImpl)assetCategoryVersion,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		AssetCategoryVersionModelImpl assetCategoryVersionModelImpl) {
		Object[] args = new Object[] {
				assetCategoryVersionModelImpl.getCategoryId(),
				assetCategoryVersionModelImpl.getVersion()
			};

		FinderCacheUtil.putResult(_finderPathCountByCategoryId_Version, args,
			Long.valueOf(1), false);
		FinderCacheUtil.putResult(_finderPathFetchByCategoryId_Version, args,
			assetCategoryVersionModelImpl, false);

		args = new Object[] {
				assetCategoryVersionModelImpl.getUuid(),
				assetCategoryVersionModelImpl.getGroupId(),
				assetCategoryVersionModelImpl.getVersion()
			};

		FinderCacheUtil.putResult(_finderPathCountByUUID_G_Version, args,
			Long.valueOf(1), false);
		FinderCacheUtil.putResult(_finderPathFetchByUUID_G_Version, args,
			assetCategoryVersionModelImpl, false);

		args = new Object[] {
				assetCategoryVersionModelImpl.getParentCategoryId(),
				assetCategoryVersionModelImpl.getName(),
				assetCategoryVersionModelImpl.getVocabularyId(),
				assetCategoryVersionModelImpl.getVersion()
			};

		FinderCacheUtil.putResult(_finderPathCountByP_N_V_Version, args,
			Long.valueOf(1), false);
		FinderCacheUtil.putResult(_finderPathFetchByP_N_V_Version, args,
			assetCategoryVersionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AssetCategoryVersionModelImpl assetCategoryVersionModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					assetCategoryVersionModelImpl.getCategoryId(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByCategoryId_Version,
				args);
			FinderCacheUtil.removeResult(_finderPathFetchByCategoryId_Version,
				args);
		}

		if ((assetCategoryVersionModelImpl.getColumnBitmask() &
				_finderPathFetchByCategoryId_Version.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					assetCategoryVersionModelImpl.getOriginalCategoryId(),
					assetCategoryVersionModelImpl.getOriginalVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByCategoryId_Version,
				args);
			FinderCacheUtil.removeResult(_finderPathFetchByCategoryId_Version,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					assetCategoryVersionModelImpl.getUuid(),
					assetCategoryVersionModelImpl.getGroupId(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByUUID_G_Version, args);
			FinderCacheUtil.removeResult(_finderPathFetchByUUID_G_Version, args);
		}

		if ((assetCategoryVersionModelImpl.getColumnBitmask() &
				_finderPathFetchByUUID_G_Version.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					assetCategoryVersionModelImpl.getOriginalUuid(),
					assetCategoryVersionModelImpl.getOriginalGroupId(),
					assetCategoryVersionModelImpl.getOriginalVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByUUID_G_Version, args);
			FinderCacheUtil.removeResult(_finderPathFetchByUUID_G_Version, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					assetCategoryVersionModelImpl.getParentCategoryId(),
					assetCategoryVersionModelImpl.getName(),
					assetCategoryVersionModelImpl.getVocabularyId(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByP_N_V_Version, args);
			FinderCacheUtil.removeResult(_finderPathFetchByP_N_V_Version, args);
		}

		if ((assetCategoryVersionModelImpl.getColumnBitmask() &
				_finderPathFetchByP_N_V_Version.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					assetCategoryVersionModelImpl.getOriginalParentCategoryId(),
					assetCategoryVersionModelImpl.getOriginalName(),
					assetCategoryVersionModelImpl.getOriginalVocabularyId(),
					assetCategoryVersionModelImpl.getOriginalVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByP_N_V_Version, args);
			FinderCacheUtil.removeResult(_finderPathFetchByP_N_V_Version, args);
		}
	}

	/**
	 * Creates a new asset category version with the primary key. Does not add the asset category version to the database.
	 *
	 * @param assetCategoryVersionId the primary key for the new asset category version
	 * @return the new asset category version
	 */
	@Override
	public AssetCategoryVersion create(long assetCategoryVersionId) {
		AssetCategoryVersion assetCategoryVersion = new AssetCategoryVersionImpl();

		assetCategoryVersion.setNew(true);
		assetCategoryVersion.setPrimaryKey(assetCategoryVersionId);

		assetCategoryVersion.setCompanyId(companyProvider.getCompanyId());

		return assetCategoryVersion;
	}

	/**
	 * Removes the asset category version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetCategoryVersionId the primary key of the asset category version
	 * @return the asset category version that was removed
	 * @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	 */
	@Override
	public AssetCategoryVersion remove(long assetCategoryVersionId)
		throws NoSuchCategoryVersionException {
		return remove((Serializable)assetCategoryVersionId);
	}

	/**
	 * Removes the asset category version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset category version
	 * @return the asset category version that was removed
	 * @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	 */
	@Override
	public AssetCategoryVersion remove(Serializable primaryKey)
		throws NoSuchCategoryVersionException {
		Session session = null;

		try {
			session = openSession();

			AssetCategoryVersion assetCategoryVersion = (AssetCategoryVersion)session.get(AssetCategoryVersionImpl.class,
					primaryKey);

			if (assetCategoryVersion == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCategoryVersionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetCategoryVersion);
		}
		catch (NoSuchCategoryVersionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected AssetCategoryVersion removeImpl(
		AssetCategoryVersion assetCategoryVersion) {
		assetCategoryVersionToAssetEntryTableMapper.deleteLeftPrimaryKeyTableMappings(assetCategoryVersion.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(assetCategoryVersion)) {
				assetCategoryVersion = (AssetCategoryVersion)session.get(AssetCategoryVersionImpl.class,
						assetCategoryVersion.getPrimaryKeyObj());
			}

			if (assetCategoryVersion != null) {
				session.delete(assetCategoryVersion);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (assetCategoryVersion != null) {
			clearCache(assetCategoryVersion);
		}

		return assetCategoryVersion;
	}

	@Override
	public AssetCategoryVersion updateImpl(
		AssetCategoryVersion assetCategoryVersion) {
		boolean isNew = assetCategoryVersion.isNew();

		if (!(assetCategoryVersion instanceof AssetCategoryVersionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(assetCategoryVersion.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(assetCategoryVersion);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in assetCategoryVersion proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AssetCategoryVersion implementation " +
				assetCategoryVersion.getClass());
		}

		AssetCategoryVersionModelImpl assetCategoryVersionModelImpl = (AssetCategoryVersionModelImpl)assetCategoryVersion;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (assetCategoryVersion.getCreateDate() == null)) {
			if (serviceContext == null) {
				assetCategoryVersion.setCreateDate(now);
			}
			else {
				assetCategoryVersion.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!assetCategoryVersionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				assetCategoryVersion.setModifiedDate(now);
			}
			else {
				assetCategoryVersion.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (assetCategoryVersion.isNew()) {
				session.save(assetCategoryVersion);

				assetCategoryVersion.setNew(false);
			}
			else {
				throw new IllegalArgumentException(
					"AssetCategoryVersion is read only, create a new version instead");
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AssetCategoryVersionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					assetCategoryVersionModelImpl.getCategoryId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByCategoryId, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByCategoryId,
				args);

			args = new Object[] { assetCategoryVersionModelImpl.getUuid() };

			FinderCacheUtil.removeResult(_finderPathCountByUuid, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getUuid(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByUuid_Version, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid_Version,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getUuid(),
					assetCategoryVersionModelImpl.getGroupId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByUUID_G, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUUID_G,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getUuid(),
					assetCategoryVersionModelImpl.getCompanyId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByUuid_C, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid_C,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getUuid(),
					assetCategoryVersionModelImpl.getCompanyId(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByUuid_C_Version, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid_C_Version,
				args);

			args = new Object[] { assetCategoryVersionModelImpl.getGroupId() };

			FinderCacheUtil.removeResult(_finderPathCountByGroupId, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByGroupId,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getGroupId(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByGroupId_Version, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByGroupId_Version,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getParentCategoryId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByParentCategoryId,
				args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByParentCategoryId,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getParentCategoryId(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByParentCategoryId_Version,
				args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByParentCategoryId_Version,
				args);

			args = new Object[] { assetCategoryVersionModelImpl.getVocabularyId() };

			FinderCacheUtil.removeResult(_finderPathCountByVocabularyId, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByVocabularyId,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getVocabularyId(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByVocabularyId_Version,
				args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByVocabularyId_Version,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getGroupId(),
					assetCategoryVersionModelImpl.getParentCategoryId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_P, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getGroupId(),
					assetCategoryVersionModelImpl.getParentCategoryId(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_P_Version, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_Version,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getGroupId(),
					assetCategoryVersionModelImpl.getVocabularyId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_V, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_V,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getGroupId(),
					assetCategoryVersionModelImpl.getVocabularyId(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_V_Version, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_V_Version,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getParentCategoryId(),
					assetCategoryVersionModelImpl.getName()
				};

			FinderCacheUtil.removeResult(_finderPathCountByP_N, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_N,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getParentCategoryId(),
					assetCategoryVersionModelImpl.getName(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByP_N_Version, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_N_Version,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getParentCategoryId(),
					assetCategoryVersionModelImpl.getVocabularyId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByP_V, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_V,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getParentCategoryId(),
					assetCategoryVersionModelImpl.getVocabularyId(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByP_V_Version, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_V_Version,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getName(),
					assetCategoryVersionModelImpl.getVocabularyId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByN_V, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByN_V,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getName(),
					assetCategoryVersionModelImpl.getVocabularyId(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByN_V_Version, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByN_V_Version,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getGroupId(),
					assetCategoryVersionModelImpl.getParentCategoryId(),
					assetCategoryVersionModelImpl.getVocabularyId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_P_V, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_V,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getGroupId(),
					assetCategoryVersionModelImpl.getParentCategoryId(),
					assetCategoryVersionModelImpl.getVocabularyId(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_P_V_Version, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_V_Version,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getGroupId(),
					assetCategoryVersionModelImpl.getName(),
					assetCategoryVersionModelImpl.getVocabularyId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_LikeN_V, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_LikeN_V,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getGroupId(),
					assetCategoryVersionModelImpl.getName(),
					assetCategoryVersionModelImpl.getVocabularyId(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_LikeN_V_Version,
				args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_LikeN_V_Version,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getParentCategoryId(),
					assetCategoryVersionModelImpl.getName(),
					assetCategoryVersionModelImpl.getVocabularyId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByP_N_V, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_N_V,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getGroupId(),
					assetCategoryVersionModelImpl.getParentCategoryId(),
					assetCategoryVersionModelImpl.getName(),
					assetCategoryVersionModelImpl.getVocabularyId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_P_N_V, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_N_V,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getGroupId(),
					assetCategoryVersionModelImpl.getParentCategoryId(),
					assetCategoryVersionModelImpl.getName(),
					assetCategoryVersionModelImpl.getVocabularyId(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_P_N_V_Version, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_N_V_Version,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getCompanyId(),
					assetCategoryVersionModelImpl.getExternalReferenceCode()
				};

			FinderCacheUtil.removeResult(_finderPathCountByC_ERC, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByC_ERC,
				args);

			args = new Object[] {
					assetCategoryVersionModelImpl.getCompanyId(),
					assetCategoryVersionModelImpl.getExternalReferenceCode(),
					assetCategoryVersionModelImpl.getVersion()
				};

			FinderCacheUtil.removeResult(_finderPathCountByC_ERC_Version, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByC_ERC_Version,
				args);

			FinderCacheUtil.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindAll,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByCategoryId.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalCategoryId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByCategoryId, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByCategoryId,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getCategoryId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByCategoryId, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByCategoryId,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByUuid.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(_finderPathCountByUuid, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid,
					args);

				args = new Object[] { assetCategoryVersionModelImpl.getUuid() };

				FinderCacheUtil.removeResult(_finderPathCountByUuid, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByUuid_Version.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalUuid(),
						assetCategoryVersionModelImpl.getOriginalVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByUuid_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid_Version,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getUuid(),
						assetCategoryVersionModelImpl.getVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByUuid_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid_Version,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByUUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalUuid(),
						assetCategoryVersionModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByUUID_G, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUUID_G,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getUuid(),
						assetCategoryVersionModelImpl.getGroupId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByUUID_G, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUUID_G,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalUuid(),
						assetCategoryVersionModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByUuid_C, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid_C,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getUuid(),
						assetCategoryVersionModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByUuid_C, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid_C,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByUuid_C_Version.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalUuid(),
						assetCategoryVersionModelImpl.getOriginalCompanyId(),
						assetCategoryVersionModelImpl.getOriginalVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByUuid_C_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid_C_Version,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getUuid(),
						assetCategoryVersionModelImpl.getCompanyId(),
						assetCategoryVersionModelImpl.getVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByUuid_C_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid_C_Version,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByGroupId.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByGroupId, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByGroupId,
					args);

				args = new Object[] { assetCategoryVersionModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(_finderPathCountByGroupId, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByGroupId,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByGroupId_Version.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalGroupId(),
						assetCategoryVersionModelImpl.getOriginalVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByGroupId_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByGroupId_Version,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getGroupId(),
						assetCategoryVersionModelImpl.getVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByGroupId_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByGroupId_Version,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByParentCategoryId.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalParentCategoryId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByParentCategoryId,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByParentCategoryId,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getParentCategoryId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByParentCategoryId,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByParentCategoryId,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByParentCategoryId_Version.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalParentCategoryId(),
						assetCategoryVersionModelImpl.getOriginalVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByParentCategoryId_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByParentCategoryId_Version,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getParentCategoryId(),
						assetCategoryVersionModelImpl.getVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByParentCategoryId_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByParentCategoryId_Version,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByVocabularyId.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByVocabularyId,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByVocabularyId,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByVocabularyId,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByVocabularyId,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByVocabularyId_Version.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalVocabularyId(),
						assetCategoryVersionModelImpl.getOriginalVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByVocabularyId_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByVocabularyId_Version,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getVocabularyId(),
						assetCategoryVersionModelImpl.getVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByVocabularyId_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByVocabularyId_Version,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByG_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalGroupId(),
						assetCategoryVersionModelImpl.getOriginalParentCategoryId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getGroupId(),
						assetCategoryVersionModelImpl.getParentCategoryId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByG_P_Version.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalGroupId(),
						assetCategoryVersionModelImpl.getOriginalParentCategoryId(),
						assetCategoryVersionModelImpl.getOriginalVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P_Version, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_Version,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getGroupId(),
						assetCategoryVersionModelImpl.getParentCategoryId(),
						assetCategoryVersionModelImpl.getVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P_Version, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_Version,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByG_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalGroupId(),
						assetCategoryVersionModelImpl.getOriginalVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_V,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getGroupId(),
						assetCategoryVersionModelImpl.getVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_V,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByG_V_Version.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalGroupId(),
						assetCategoryVersionModelImpl.getOriginalVocabularyId(),
						assetCategoryVersionModelImpl.getOriginalVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_V_Version, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_V_Version,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getGroupId(),
						assetCategoryVersionModelImpl.getVocabularyId(),
						assetCategoryVersionModelImpl.getVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_V_Version, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_V_Version,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByP_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalParentCategoryId(),
						assetCategoryVersionModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_N, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_N,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getParentCategoryId(),
						assetCategoryVersionModelImpl.getName()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_N, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_N,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByP_N_Version.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalParentCategoryId(),
						assetCategoryVersionModelImpl.getOriginalName(),
						assetCategoryVersionModelImpl.getOriginalVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_N_Version, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_N_Version,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getParentCategoryId(),
						assetCategoryVersionModelImpl.getName(),
						assetCategoryVersionModelImpl.getVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_N_Version, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_N_Version,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByP_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalParentCategoryId(),
						assetCategoryVersionModelImpl.getOriginalVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_V,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getParentCategoryId(),
						assetCategoryVersionModelImpl.getVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_V,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByP_V_Version.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalParentCategoryId(),
						assetCategoryVersionModelImpl.getOriginalVocabularyId(),
						assetCategoryVersionModelImpl.getOriginalVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_V_Version, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_V_Version,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getParentCategoryId(),
						assetCategoryVersionModelImpl.getVocabularyId(),
						assetCategoryVersionModelImpl.getVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_V_Version, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_V_Version,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByN_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalName(),
						assetCategoryVersionModelImpl.getOriginalVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByN_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByN_V,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getName(),
						assetCategoryVersionModelImpl.getVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByN_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByN_V,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByN_V_Version.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalName(),
						assetCategoryVersionModelImpl.getOriginalVocabularyId(),
						assetCategoryVersionModelImpl.getOriginalVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByN_V_Version, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByN_V_Version,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getName(),
						assetCategoryVersionModelImpl.getVocabularyId(),
						assetCategoryVersionModelImpl.getVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByN_V_Version, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByN_V_Version,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByG_P_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalGroupId(),
						assetCategoryVersionModelImpl.getOriginalParentCategoryId(),
						assetCategoryVersionModelImpl.getOriginalVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_V,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getGroupId(),
						assetCategoryVersionModelImpl.getParentCategoryId(),
						assetCategoryVersionModelImpl.getVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_V,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByG_P_V_Version.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalGroupId(),
						assetCategoryVersionModelImpl.getOriginalParentCategoryId(),
						assetCategoryVersionModelImpl.getOriginalVocabularyId(),
						assetCategoryVersionModelImpl.getOriginalVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P_V_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_V_Version,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getGroupId(),
						assetCategoryVersionModelImpl.getParentCategoryId(),
						assetCategoryVersionModelImpl.getVocabularyId(),
						assetCategoryVersionModelImpl.getVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P_V_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_V_Version,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByG_LikeN_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalGroupId(),
						assetCategoryVersionModelImpl.getOriginalName(),
						assetCategoryVersionModelImpl.getOriginalVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_LikeN_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_LikeN_V,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getGroupId(),
						assetCategoryVersionModelImpl.getName(),
						assetCategoryVersionModelImpl.getVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_LikeN_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_LikeN_V,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByG_LikeN_V_Version.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalGroupId(),
						assetCategoryVersionModelImpl.getOriginalName(),
						assetCategoryVersionModelImpl.getOriginalVocabularyId(),
						assetCategoryVersionModelImpl.getOriginalVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_LikeN_V_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_LikeN_V_Version,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getGroupId(),
						assetCategoryVersionModelImpl.getName(),
						assetCategoryVersionModelImpl.getVocabularyId(),
						assetCategoryVersionModelImpl.getVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_LikeN_V_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_LikeN_V_Version,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByP_N_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalParentCategoryId(),
						assetCategoryVersionModelImpl.getOriginalName(),
						assetCategoryVersionModelImpl.getOriginalVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_N_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_N_V,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getParentCategoryId(),
						assetCategoryVersionModelImpl.getName(),
						assetCategoryVersionModelImpl.getVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_N_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_N_V,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByG_P_N_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalGroupId(),
						assetCategoryVersionModelImpl.getOriginalParentCategoryId(),
						assetCategoryVersionModelImpl.getOriginalName(),
						assetCategoryVersionModelImpl.getOriginalVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P_N_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_N_V,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getGroupId(),
						assetCategoryVersionModelImpl.getParentCategoryId(),
						assetCategoryVersionModelImpl.getName(),
						assetCategoryVersionModelImpl.getVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P_N_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_N_V,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByG_P_N_V_Version.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalGroupId(),
						assetCategoryVersionModelImpl.getOriginalParentCategoryId(),
						assetCategoryVersionModelImpl.getOriginalName(),
						assetCategoryVersionModelImpl.getOriginalVocabularyId(),
						assetCategoryVersionModelImpl.getOriginalVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P_N_V_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_N_V_Version,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getGroupId(),
						assetCategoryVersionModelImpl.getParentCategoryId(),
						assetCategoryVersionModelImpl.getName(),
						assetCategoryVersionModelImpl.getVocabularyId(),
						assetCategoryVersionModelImpl.getVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P_N_V_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_N_V_Version,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByC_ERC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalCompanyId(),
						assetCategoryVersionModelImpl.getOriginalExternalReferenceCode()
					};

				FinderCacheUtil.removeResult(_finderPathCountByC_ERC, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByC_ERC,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getCompanyId(),
						assetCategoryVersionModelImpl.getExternalReferenceCode()
					};

				FinderCacheUtil.removeResult(_finderPathCountByC_ERC, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByC_ERC,
					args);
			}

			if ((assetCategoryVersionModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByC_ERC_Version.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryVersionModelImpl.getOriginalCompanyId(),
						assetCategoryVersionModelImpl.getOriginalExternalReferenceCode(),
						assetCategoryVersionModelImpl.getOriginalVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByC_ERC_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByC_ERC_Version,
					args);

				args = new Object[] {
						assetCategoryVersionModelImpl.getCompanyId(),
						assetCategoryVersionModelImpl.getExternalReferenceCode(),
						assetCategoryVersionModelImpl.getVersion()
					};

				FinderCacheUtil.removeResult(_finderPathCountByC_ERC_Version,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByC_ERC_Version,
					args);
			}
		}

		EntityCacheUtil.putResult(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
			AssetCategoryVersionImpl.class,
			assetCategoryVersion.getPrimaryKey(), assetCategoryVersion, false);

		clearUniqueFindersCache(assetCategoryVersionModelImpl, false);
		cacheUniqueFindersCache(assetCategoryVersionModelImpl);

		assetCategoryVersion.resetOriginalValues();

		return assetCategoryVersion;
	}

	/**
	 * Returns the asset category version with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset category version
	 * @return the asset category version
	 * @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	 */
	@Override
	public AssetCategoryVersion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCategoryVersionException {
		AssetCategoryVersion assetCategoryVersion = fetchByPrimaryKey(primaryKey);

		if (assetCategoryVersion == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCategoryVersionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return assetCategoryVersion;
	}

	/**
	 * Returns the asset category version with the primary key or throws a <code>NoSuchCategoryVersionException</code> if it could not be found.
	 *
	 * @param assetCategoryVersionId the primary key of the asset category version
	 * @return the asset category version
	 * @throws NoSuchCategoryVersionException if a asset category version with the primary key could not be found
	 */
	@Override
	public AssetCategoryVersion findByPrimaryKey(long assetCategoryVersionId)
		throws NoSuchCategoryVersionException {
		return findByPrimaryKey((Serializable)assetCategoryVersionId);
	}

	/**
	 * Returns the asset category version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetCategoryVersionId the primary key of the asset category version
	 * @return the asset category version, or <code>null</code> if a asset category version with the primary key could not be found
	 */
	@Override
	public AssetCategoryVersion fetchByPrimaryKey(long assetCategoryVersionId) {
		return fetchByPrimaryKey((Serializable)assetCategoryVersionId);
	}

	/**
	 * Returns all the asset category versions.
	 *
	 * @return the asset category versions
	 */
	@Override
	public List<AssetCategoryVersion> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetCategoryVersion> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<AssetCategoryVersion> findAll(int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<AssetCategoryVersion> findAll(int start, int end,
		OrderByComparator<AssetCategoryVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<AssetCategoryVersion> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategoryVersion>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ASSETCATEGORYVERSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETCATEGORYVERSION;

				if (pagination) {
					sql = sql.concat(AssetCategoryVersionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategoryVersion>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the asset category versions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AssetCategoryVersion assetCategoryVersion : findAll()) {
			remove(assetCategoryVersion);
		}
	}

	/**
	 * Returns the number of asset category versions.
	 *
	 * @return the number of asset category versions
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(_finderPathCountAll,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETCATEGORYVERSION);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(_finderPathCountAll,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(_finderPathCountAll,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the primaryKeys of asset entries associated with the asset category version.
	 *
	 * @param pk the primary key of the asset category version
	 * @return long[] of the primaryKeys of asset entries associated with the asset category version
	 */
	@Override
	public long[] getAssetEntryPrimaryKeys(long pk) {
		long[] pks = assetCategoryVersionToAssetEntryTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the asset entries associated with the asset category version.
	 *
	 * @param pk the primary key of the asset category version
	 * @return the asset entries associated with the asset category version
	 */
	@Override
	public List<com.liferay.asset.kernel.model.AssetEntry> getAssetEntries(
		long pk) {
		return getAssetEntries(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
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
	@Override
	public List<com.liferay.asset.kernel.model.AssetEntry> getAssetEntries(
		long pk, int start, int end) {
		return getAssetEntries(pk, start, end, null);
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
	@Override
	public List<com.liferay.asset.kernel.model.AssetEntry> getAssetEntries(
		long pk, int start, int end,
		OrderByComparator<com.liferay.asset.kernel.model.AssetEntry> orderByComparator) {
		return assetCategoryVersionToAssetEntryTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of asset entries associated with the asset category version.
	 *
	 * @param pk the primary key of the asset category version
	 * @return the number of asset entries associated with the asset category version
	 */
	@Override
	public int getAssetEntriesSize(long pk) {
		long[] pks = assetCategoryVersionToAssetEntryTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the asset entry is associated with the asset category version.
	 *
	 * @param pk the primary key of the asset category version
	 * @param assetEntryPK the primary key of the asset entry
	 * @return <code>true</code> if the asset entry is associated with the asset category version; <code>false</code> otherwise
	 */
	@Override
	public boolean containsAssetEntry(long pk, long assetEntryPK) {
		return assetCategoryVersionToAssetEntryTableMapper.containsTableMapping(pk,
			assetEntryPK);
	}

	/**
	 * Returns <code>true</code> if the asset category version has any asset entries associated with it.
	 *
	 * @param pk the primary key of the asset category version to check for associations with asset entries
	 * @return <code>true</code> if the asset category version has any asset entries associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsAssetEntries(long pk) {
		if (getAssetEntriesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the asset category version and the asset entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category version
	 * @param assetEntryPK the primary key of the asset entry
	 */
	@Override
	public void addAssetEntry(long pk, long assetEntryPK) {
		AssetCategoryVersion assetCategoryVersion = fetchByPrimaryKey(pk);

		if (assetCategoryVersion == null) {
			assetCategoryVersionToAssetEntryTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, assetEntryPK);
		}
		else {
			assetCategoryVersionToAssetEntryTableMapper.addTableMapping(assetCategoryVersion.getCompanyId(),
				pk, assetEntryPK);
		}
	}

	/**
	 * Adds an association between the asset category version and the asset entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category version
	 * @param assetEntry the asset entry
	 */
	@Override
	public void addAssetEntry(long pk,
		com.liferay.asset.kernel.model.AssetEntry assetEntry) {
		AssetCategoryVersion assetCategoryVersion = fetchByPrimaryKey(pk);

		if (assetCategoryVersion == null) {
			assetCategoryVersionToAssetEntryTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, assetEntry.getPrimaryKey());
		}
		else {
			assetCategoryVersionToAssetEntryTableMapper.addTableMapping(assetCategoryVersion.getCompanyId(),
				pk, assetEntry.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the asset category version and the asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category version
	 * @param assetEntryPKs the primary keys of the asset entries
	 */
	@Override
	public void addAssetEntries(long pk, long[] assetEntryPKs) {
		long companyId = 0;

		AssetCategoryVersion assetCategoryVersion = fetchByPrimaryKey(pk);

		if (assetCategoryVersion == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = assetCategoryVersion.getCompanyId();
		}

		assetCategoryVersionToAssetEntryTableMapper.addTableMappings(companyId,
			pk, assetEntryPKs);
	}

	/**
	 * Adds an association between the asset category version and the asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category version
	 * @param assetEntries the asset entries
	 */
	@Override
	public void addAssetEntries(long pk,
		List<com.liferay.asset.kernel.model.AssetEntry> assetEntries) {
		addAssetEntries(pk,
			ListUtil.toLongArray(assetEntries,
				com.liferay.asset.kernel.model.AssetEntry.ENTRY_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the asset category version and its asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category version to clear the associated asset entries from
	 */
	@Override
	public void clearAssetEntries(long pk) {
		assetCategoryVersionToAssetEntryTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the asset category version and the asset entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category version
	 * @param assetEntryPK the primary key of the asset entry
	 */
	@Override
	public void removeAssetEntry(long pk, long assetEntryPK) {
		assetCategoryVersionToAssetEntryTableMapper.deleteTableMapping(pk,
			assetEntryPK);
	}

	/**
	 * Removes the association between the asset category version and the asset entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category version
	 * @param assetEntry the asset entry
	 */
	@Override
	public void removeAssetEntry(long pk,
		com.liferay.asset.kernel.model.AssetEntry assetEntry) {
		assetCategoryVersionToAssetEntryTableMapper.deleteTableMapping(pk,
			assetEntry.getPrimaryKey());
	}

	/**
	 * Removes the association between the asset category version and the asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category version
	 * @param assetEntryPKs the primary keys of the asset entries
	 */
	@Override
	public void removeAssetEntries(long pk, long[] assetEntryPKs) {
		assetCategoryVersionToAssetEntryTableMapper.deleteTableMappings(pk,
			assetEntryPKs);
	}

	/**
	 * Removes the association between the asset category version and the asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category version
	 * @param assetEntries the asset entries
	 */
	@Override
	public void removeAssetEntries(long pk,
		List<com.liferay.asset.kernel.model.AssetEntry> assetEntries) {
		removeAssetEntries(pk,
			ListUtil.toLongArray(assetEntries,
				com.liferay.asset.kernel.model.AssetEntry.ENTRY_ID_ACCESSOR));
	}

	/**
	 * Sets the asset entries associated with the asset category version, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category version
	 * @param assetEntryPKs the primary keys of the asset entries to be associated with the asset category version
	 */
	@Override
	public void setAssetEntries(long pk, long[] assetEntryPKs) {
		Set<Long> newAssetEntryPKsSet = SetUtil.fromArray(assetEntryPKs);
		Set<Long> oldAssetEntryPKsSet = SetUtil.fromArray(assetCategoryVersionToAssetEntryTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeAssetEntryPKsSet = new HashSet<Long>(oldAssetEntryPKsSet);

		removeAssetEntryPKsSet.removeAll(newAssetEntryPKsSet);

		assetCategoryVersionToAssetEntryTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeAssetEntryPKsSet));

		newAssetEntryPKsSet.removeAll(oldAssetEntryPKsSet);

		long companyId = 0;

		AssetCategoryVersion assetCategoryVersion = fetchByPrimaryKey(pk);

		if (assetCategoryVersion == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = assetCategoryVersion.getCompanyId();
		}

		assetCategoryVersionToAssetEntryTableMapper.addTableMappings(companyId,
			pk, ArrayUtil.toLongArray(newAssetEntryPKsSet));
	}

	/**
	 * Sets the asset entries associated with the asset category version, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category version
	 * @param assetEntries the asset entries to be associated with the asset category version
	 */
	@Override
	public void setAssetEntries(long pk,
		List<com.liferay.asset.kernel.model.AssetEntry> assetEntries) {
		try {
			long[] assetEntryPKs = new long[assetEntries.size()];

			for (int i = 0; i < assetEntries.size(); i++) {
				com.liferay.asset.kernel.model.AssetEntry assetEntry = assetEntries.get(i);

				assetEntryPKs[i] = assetEntry.getPrimaryKey();
			}

			setAssetEntries(pk, assetEntryPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return EntityCacheUtil.getEntityCache();
	}

	@Override
	protected String getPKDBName() {
		return "assetCategoryVersionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ASSETCATEGORYVERSION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AssetCategoryVersionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the asset category version persistence.
	 */
	public void afterPropertiesSet() {
		assetCategoryVersionToAssetEntryTableMapper = TableMapperFactory.getTableMapper("AssetEntries_AssetCategories",
				"companyId", "assetCategoryVersionId", "entryId", this,
				assetEntryPersistence);

		_finderPathWithPaginationFindAll = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
				new String[0]);

		_finderPathCountAll = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
				new String[0]);

		_finderPathWithPaginationFindByCategoryId = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCategoryId",
				new String[] {
					Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByCategoryId = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCategoryId",
				new String[] { Long.class.getName() },
				AssetCategoryVersionModelImpl.CATEGORYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByCategoryId = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCategoryId",
				new String[] { Long.class.getName() });

		_finderPathFetchByCategoryId_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class, FINDER_CLASS_NAME_ENTITY,
				"fetchByCategoryId_Version",
				new String[] { Long.class.getName(), Integer.class.getName() },
				AssetCategoryVersionModelImpl.CATEGORYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByCategoryId_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByCategoryId_Version",
				new String[] { Long.class.getName(), Integer.class.getName() });

		_finderPathWithPaginationFindByUuid = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
				new String[] {
					String.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
				new String[] { String.class.getName() },
				AssetCategoryVersionModelImpl.UUID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
				new String[] { String.class.getName() });

		_finderPathWithPaginationFindByUuid_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_Version",
				new String[] {
					String.class.getName(), Integer.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByUuid_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByUuid_Version",
				new String[] { String.class.getName(), Integer.class.getName() },
				AssetCategoryVersionModelImpl.UUID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByUuid_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByUuid_Version",
				new String[] { String.class.getName(), Integer.class.getName() });

		_finderPathWithPaginationFindByUUID_G = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUUID_G",
				new String[] {
					String.class.getName(), Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByUUID_G = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUUID_G",
				new String[] { String.class.getName(), Long.class.getName() },
				AssetCategoryVersionModelImpl.UUID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
				new String[] { String.class.getName(), Long.class.getName() });

		_finderPathFetchByUUID_G_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class, FINDER_CLASS_NAME_ENTITY,
				"fetchByUUID_G_Version",
				new String[] {
					String.class.getName(), Long.class.getName(),
					Integer.class.getName()
				},
				AssetCategoryVersionModelImpl.UUID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByUUID_G_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByUUID_G_Version",
				new String[] {
					String.class.getName(), Long.class.getName(),
					Integer.class.getName()
				});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
				new String[] {
					String.class.getName(), Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
				new String[] { String.class.getName(), Long.class.getName() },
				AssetCategoryVersionModelImpl.UUID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.COMPANYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
				new String[] { String.class.getName(), Long.class.getName() });

		_finderPathWithPaginationFindByUuid_C_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C_Version",
				new String[] {
					String.class.getName(), Long.class.getName(),
					Integer.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByUuid_C_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByUuid_C_Version",
				new String[] {
					String.class.getName(), Long.class.getName(),
					Integer.class.getName()
				},
				AssetCategoryVersionModelImpl.UUID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.COMPANYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByUuid_C_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByUuid_C_Version",
				new String[] {
					String.class.getName(), Long.class.getName(),
					Integer.class.getName()
				});

		_finderPathWithPaginationFindByGroupId = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
				new String[] {
					Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
				new String[] { Long.class.getName() },
				AssetCategoryVersionModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
				new String[] { Long.class.getName() });

		_finderPathWithPaginationFindByGroupId_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByGroupId_Version",
				new String[] {
					Long.class.getName(), Integer.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByGroupId_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByGroupId_Version",
				new String[] { Long.class.getName(), Integer.class.getName() },
				AssetCategoryVersionModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByGroupId_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByGroupId_Version",
				new String[] { Long.class.getName(), Integer.class.getName() });

		_finderPathWithPaginationFindByParentCategoryId = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByParentCategoryId",
				new String[] {
					Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByParentCategoryId = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByParentCategoryId",
				new String[] { Long.class.getName() },
				AssetCategoryVersionModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByParentCategoryId = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByParentCategoryId", new String[] { Long.class.getName() });

		_finderPathWithPaginationFindByParentCategoryId_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByParentCategoryId_Version",
				new String[] {
					Long.class.getName(), Integer.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByParentCategoryId_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByParentCategoryId_Version",
				new String[] { Long.class.getName(), Integer.class.getName() },
				AssetCategoryVersionModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByParentCategoryId_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByParentCategoryId_Version",
				new String[] { Long.class.getName(), Integer.class.getName() });

		_finderPathWithPaginationFindByVocabularyId = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByVocabularyId",
				new String[] {
					Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByVocabularyId = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByVocabularyId", new String[] { Long.class.getName() },
				AssetCategoryVersionModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByVocabularyId = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByVocabularyId", new String[] { Long.class.getName() });

		_finderPathWithPaginationFindByVocabularyId_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByVocabularyId_Version",
				new String[] {
					Long.class.getName(), Integer.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByVocabularyId_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByVocabularyId_Version",
				new String[] { Long.class.getName(), Integer.class.getName() },
				AssetCategoryVersionModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByVocabularyId_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByVocabularyId_Version",
				new String[] { Long.class.getName(), Integer.class.getName() });

		_finderPathWithPaginationFindByG_P = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByG_P = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P",
				new String[] { Long.class.getName(), Long.class.getName() },
				AssetCategoryVersionModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByG_P = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P",
				new String[] { Long.class.getName(), Long.class.getName() });

		_finderPathWithPaginationFindByG_P_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_Version",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Integer.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByG_P_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P_Version",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Integer.class.getName()
				},
				AssetCategoryVersionModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByG_P_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByG_P_Version",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Integer.class.getName()
				});

		_finderPathWithPaginationFindByG_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_V",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByG_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_V",
				new String[] { Long.class.getName(), Long.class.getName() },
				AssetCategoryVersionModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByG_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_V",
				new String[] { Long.class.getName(), Long.class.getName() });

		_finderPathWithPaginationFindByG_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_V_Version",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Integer.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByG_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_V_Version",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Integer.class.getName()
				},
				AssetCategoryVersionModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByG_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByG_V_Version",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Integer.class.getName()
				});

		_finderPathWithPaginationFindByP_N = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_N",
				new String[] {
					Long.class.getName(), String.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByP_N = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_N",
				new String[] { Long.class.getName(), String.class.getName() },
				AssetCategoryVersionModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.NAME_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByP_N = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_N",
				new String[] { Long.class.getName(), String.class.getName() });

		_finderPathWithPaginationFindByP_N_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_N_Version",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Integer.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByP_N_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_N_Version",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Integer.class.getName()
				},
				AssetCategoryVersionModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.NAME_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByP_N_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByP_N_Version",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Integer.class.getName()
				});

		_finderPathWithPaginationFindByP_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_V",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByP_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_V",
				new String[] { Long.class.getName(), Long.class.getName() },
				AssetCategoryVersionModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByP_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_V",
				new String[] { Long.class.getName(), Long.class.getName() });

		_finderPathWithPaginationFindByP_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_V_Version",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Integer.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByP_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_V_Version",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Integer.class.getName()
				},
				AssetCategoryVersionModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByP_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByP_V_Version",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Integer.class.getName()
				});

		_finderPathWithPaginationFindByN_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByN_V",
				new String[] {
					String.class.getName(), Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByN_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByN_V",
				new String[] { String.class.getName(), Long.class.getName() },
				AssetCategoryVersionModelImpl.NAME_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByN_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByN_V",
				new String[] { String.class.getName(), Long.class.getName() });

		_finderPathWithPaginationFindByN_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByN_V_Version",
				new String[] {
					String.class.getName(), Long.class.getName(),
					Integer.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByN_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByN_V_Version",
				new String[] {
					String.class.getName(), Long.class.getName(),
					Integer.class.getName()
				},
				AssetCategoryVersionModelImpl.NAME_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByN_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByN_V_Version",
				new String[] {
					String.class.getName(), Long.class.getName(),
					Integer.class.getName()
				});

		_finderPathWithPaginationFindByG_P_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_V",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByG_P_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P_V",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName()
				},
				AssetCategoryVersionModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByG_P_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_V",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName()
				});

		_finderPathWithPaginationFindByG_P_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_V_Version",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName(), Integer.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByG_P_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByG_P_V_Version",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName(), Integer.class.getName()
				},
				AssetCategoryVersionModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByG_P_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByG_P_V_Version",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName(), Integer.class.getName()
				});

		_finderPathWithPaginationFindByG_LikeN_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_LikeN_V",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByG_LikeN_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_LikeN_V",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Long.class.getName()
				},
				AssetCategoryVersionModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.NAME_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByG_LikeN_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_LikeN_V",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Long.class.getName()
				});

		_finderPathWithPaginationFindByG_LikeN_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByG_LikeN_V_Version",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Long.class.getName(), Integer.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByG_LikeN_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByG_LikeN_V_Version",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Long.class.getName(), Integer.class.getName()
				},
				AssetCategoryVersionModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.NAME_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByG_LikeN_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByG_LikeN_V_Version",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Long.class.getName(), Integer.class.getName()
				});

		_finderPathWithPaginationFindByP_N_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_N_V",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByP_N_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_N_V",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Long.class.getName()
				},
				AssetCategoryVersionModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.NAME_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByP_N_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_N_V",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Long.class.getName()
				});

		_finderPathFetchByP_N_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class, FINDER_CLASS_NAME_ENTITY,
				"fetchByP_N_V_Version",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Long.class.getName(), Integer.class.getName()
				},
				AssetCategoryVersionModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.NAME_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByP_N_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByP_N_V_Version",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Long.class.getName(), Integer.class.getName()
				});

		_finderPathWithPaginationFindByG_P_N_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_N_V",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					String.class.getName(), Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByG_P_N_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P_N_V",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					String.class.getName(), Long.class.getName()
				},
				AssetCategoryVersionModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.NAME_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByG_P_N_V = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_N_V",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					String.class.getName(), Long.class.getName()
				});

		_finderPathWithPaginationFindByG_P_N_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByG_P_N_V_Version",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					String.class.getName(), Long.class.getName(),
					Integer.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByG_P_N_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByG_P_N_V_Version",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					String.class.getName(), Long.class.getName(),
					Integer.class.getName()
				},
				AssetCategoryVersionModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.NAME_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByG_P_N_V_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByG_P_N_V_Version",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					String.class.getName(), Long.class.getName(),
					Integer.class.getName()
				});

		_finderPathWithPaginationFindByC_ERC = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_ERC",
				new String[] {
					Long.class.getName(), String.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByC_ERC = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_ERC",
				new String[] { Long.class.getName(), String.class.getName() },
				AssetCategoryVersionModelImpl.COMPANYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.EXTERNALREFERENCECODE_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByC_ERC = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ERC",
				new String[] { Long.class.getName(), String.class.getName() });

		_finderPathWithPaginationFindByC_ERC_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_ERC_Version",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Integer.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByC_ERC_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryVersionImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByC_ERC_Version",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Integer.class.getName()
				},
				AssetCategoryVersionModelImpl.COMPANYID_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.EXTERNALREFERENCECODE_COLUMN_BITMASK |
				AssetCategoryVersionModelImpl.VERSION_COLUMN_BITMASK);

		_finderPathCountByC_ERC_Version = new FinderPath(AssetCategoryVersionModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByC_ERC_Version",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Integer.class.getName()
				});
	}

	public void destroy() {
		EntityCacheUtil.removeCache(AssetCategoryVersionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper("AssetEntries_AssetCategories");
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;
	protected TableMapper<AssetCategoryVersion, com.liferay.asset.kernel.model.AssetEntry> assetCategoryVersionToAssetEntryTableMapper;
	private static final String _SQL_SELECT_ASSETCATEGORYVERSION = "SELECT assetCategoryVersion FROM AssetCategoryVersion assetCategoryVersion";
	private static final String _SQL_SELECT_ASSETCATEGORYVERSION_WHERE = "SELECT assetCategoryVersion FROM AssetCategoryVersion assetCategoryVersion WHERE ";
	private static final String _SQL_COUNT_ASSETCATEGORYVERSION = "SELECT COUNT(assetCategoryVersion) FROM AssetCategoryVersion assetCategoryVersion";
	private static final String _SQL_COUNT_ASSETCATEGORYVERSION_WHERE = "SELECT COUNT(assetCategoryVersion) FROM AssetCategoryVersion assetCategoryVersion WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetCategoryVersion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetCategoryVersion exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetCategoryVersion exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AssetCategoryVersionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}