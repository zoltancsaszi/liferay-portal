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

import com.liferay.asset.kernel.exception.NoSuchCategoryException;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.persistence.AssetCategoryPersistence;
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
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.service.persistence.impl.NestedSetsTreeManager;
import com.liferay.portal.kernel.service.persistence.impl.PersistenceNestedSetsTreeManager;
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import com.liferay.portlet.asset.model.impl.AssetCategoryImpl;
import com.liferay.portlet.asset.model.impl.AssetCategoryModelImpl;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the asset category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AssetCategoryPersistenceImpl extends BasePersistenceImpl<AssetCategory>
	implements AssetCategoryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AssetCategoryUtil</code> to access the asset category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetCategoryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationCountAncestors;
	private FinderPath _finderPathWithPaginationCountDescendants;
	private FinderPath _finderPathWithPaginationGetAncestors;
	private FinderPath _finderPathWithPaginationGetDescendants;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the asset categories where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching asset categories
	 */
	@Override
	public List<AssetCategory> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByUuid(String uuid, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset categories where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByUuid(String uuid, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator,
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

		List<AssetCategory> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategory>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategory assetCategory : list) {
					if (!uuid.equals(assetCategory.getUuid())) {
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

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset category in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByUuid_First(String uuid,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByUuid_First(uuid, orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the first asset category in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByUuid_First(String uuid,
		OrderByComparator<AssetCategory> orderByComparator) {
		List<AssetCategory> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByUuid_Last(String uuid,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByUuid_Last(uuid, orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the last asset category in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByUuid_Last(String uuid,
		OrderByComparator<AssetCategory> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<AssetCategory> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set where uuid = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] findByUuid_PrevAndNext(long categoryId, String uuid,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		uuid = Objects.toString(uuid, "");

		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = getByUuid_PrevAndNext(session, assetCategory, uuid,
					orderByComparator, true);

			array[1] = assetCategory;

			array[2] = getByUuid_PrevAndNext(session, assetCategory, uuid,
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

	protected AssetCategory getByUuid_PrevAndNext(Session session,
		AssetCategory assetCategory, String uuid,
		OrderByComparator<AssetCategory> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
			query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset categories where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (AssetCategory assetCategory : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetCategory);
		}
	}

	/**
	 * Returns the number of asset categories where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching asset categories
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

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "assetCategory.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(assetCategory.uuid IS NULL OR assetCategory.uuid = '')";
	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the asset category where uuid = &#63; and groupId = &#63; and head = &#63; or throws a <code>NoSuchCategoryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param head the head
	 * @return the matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByUUID_G(String uuid, long groupId, boolean head)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByUUID_G(uuid, groupId, head);

		if (assetCategory == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(", head=");
			msg.append(head);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCategoryException(msg.toString());
		}

		return assetCategory;
	}

	/**
	 * Returns the asset category where uuid = &#63; and groupId = &#63; and head = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param head the head
	 * @return the matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByUUID_G(String uuid, long groupId, boolean head) {
		return fetchByUUID_G(uuid, groupId, head, true);
	}

	/**
	 * Returns the asset category where uuid = &#63; and groupId = &#63; and head = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param head the head
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByUUID_G(String uuid, long groupId, boolean head,
		boolean retrieveFromCache) {
		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] { uuid, groupId, head };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(_finderPathFetchByUUID_G,
					finderArgs, this);
		}

		if (result instanceof AssetCategory) {
			AssetCategory assetCategory = (AssetCategory)result;

			if (!Objects.equals(uuid, assetCategory.getUuid()) ||
					(groupId != assetCategory.getGroupId()) ||
					(head != assetCategory.isHead())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			query.append(_FINDER_COLUMN_UUID_G_HEAD_2);

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

				qPos.add(head);

				List<AssetCategory> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(_finderPathFetchByUUID_G,
						finderArgs, list);
				}
				else {
					AssetCategory assetCategory = list.get(0);

					result = assetCategory;

					cacheResult(assetCategory);
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(_finderPathFetchByUUID_G,
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
			return (AssetCategory)result;
		}
	}

	/**
	 * Removes the asset category where uuid = &#63; and groupId = &#63; and head = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param head the head
	 * @return the asset category that was removed
	 */
	@Override
	public AssetCategory removeByUUID_G(String uuid, long groupId, boolean head)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = findByUUID_G(uuid, groupId, head);

		return remove(assetCategory);
	}

	/**
	 * Returns the number of asset categories where uuid = &#63; and groupId = &#63; and head = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param head the head
	 * @return the number of matching asset categories
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId, boolean head) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId, head };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			query.append(_FINDER_COLUMN_UUID_G_HEAD_2);

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

				qPos.add(head);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "assetCategory.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(assetCategory.uuid IS NULL OR assetCategory.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "assetCategory.groupId = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_HEAD_2 = "assetCategory.head = ?";
	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the asset categories where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching asset categories
	 */
	@Override
	public List<AssetCategory> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<AssetCategory> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset categories where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<AssetCategory> orderByComparator,
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

		List<AssetCategory> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategory>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategory assetCategory : list) {
					if (!uuid.equals(assetCategory.getUuid()) ||
							(companyId != assetCategory.getCompanyId())) {
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

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset category in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the first asset category in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<AssetCategory> orderByComparator) {
		List<AssetCategory> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the last asset category in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<AssetCategory> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategory> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] findByUuid_C_PrevAndNext(long categoryId,
		String uuid, long companyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		uuid = Objects.toString(uuid, "");

		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, assetCategory, uuid,
					companyId, orderByComparator, true);

			array[1] = assetCategory;

			array[2] = getByUuid_C_PrevAndNext(session, assetCategory, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategory getByUuid_C_PrevAndNext(Session session,
		AssetCategory assetCategory, String uuid, long companyId,
		OrderByComparator<AssetCategory> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
			query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset categories where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (AssetCategory assetCategory : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategory);
		}
	}

	/**
	 * Returns the number of asset categories where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching asset categories
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

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "assetCategory.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(assetCategory.uuid IS NULL OR assetCategory.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "assetCategory.companyId = ?";
	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the asset categories where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching asset categories
	 */
	@Override
	public List<AssetCategory> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByGroupId(long groupId, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset categories where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByGroupId(long groupId, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator,
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

		List<AssetCategory> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategory>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategory assetCategory : list) {
					if ((groupId != assetCategory.getGroupId())) {
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

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset category in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByGroupId_First(long groupId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByGroupId_First(groupId,
				orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the first asset category in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByGroupId_First(long groupId,
		OrderByComparator<AssetCategory> orderByComparator) {
		List<AssetCategory> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByGroupId_Last(long groupId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the last asset category in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByGroupId_Last(long groupId,
		OrderByComparator<AssetCategory> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<AssetCategory> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set where groupId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] findByGroupId_PrevAndNext(long categoryId,
		long groupId, OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, assetCategory,
					groupId, orderByComparator, true);

			array[1] = assetCategory;

			array[2] = getByGroupId_PrevAndNext(session, assetCategory,
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

	protected AssetCategory getByGroupId_PrevAndNext(Session session,
		AssetCategory assetCategory, long groupId,
		OrderByComparator<AssetCategory> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
			query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset categories that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByGroupId(long groupId, int start,
		int end) {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<AssetCategory> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AssetCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, AssetCategoryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, AssetCategoryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<AssetCategory>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set of asset categories that the user has permission to view where groupId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] filterFindByGroupId_PrevAndNext(long categoryId,
		long groupId, OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(categoryId, groupId,
				orderByComparator);
		}

		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, assetCategory,
					groupId, orderByComparator, true);

			array[1] = assetCategory;

			array[2] = filterGetByGroupId_PrevAndNext(session, assetCategory,
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

	protected AssetCategory filterGetByGroupId_PrevAndNext(Session session,
		AssetCategory assetCategory, long groupId,
		OrderByComparator<AssetCategory> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AssetCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, AssetCategoryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, AssetCategoryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset categories where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (AssetCategory assetCategory : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategory);
		}
	}

	/**
	 * Returns the number of asset categories where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching asset categories
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

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

	/**
	 * Returns the number of asset categories that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching asset categories that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_ASSETCATEGORY_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "assetCategory.groupId = ?";
	private FinderPath _finderPathWithPaginationFindByParentCategoryId;
	private FinderPath _finderPathWithoutPaginationFindByParentCategoryId;
	private FinderPath _finderPathCountByParentCategoryId;

	/**
	 * Returns all the asset categories where parentCategoryId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @return the matching asset categories
	 */
	@Override
	public List<AssetCategory> findByParentCategoryId(long parentCategoryId) {
		return findByParentCategoryId(parentCategoryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories where parentCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentCategoryId the parent category ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByParentCategoryId(long parentCategoryId,
		int start, int end) {
		return findByParentCategoryId(parentCategoryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories where parentCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentCategoryId the parent category ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByParentCategoryId(long parentCategoryId,
		int start, int end, OrderByComparator<AssetCategory> orderByComparator) {
		return findByParentCategoryId(parentCategoryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset categories where parentCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentCategoryId the parent category ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByParentCategoryId(long parentCategoryId,
		int start, int end, OrderByComparator<AssetCategory> orderByComparator,
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

		List<AssetCategory> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategory>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategory assetCategory : list) {
					if ((parentCategoryId != assetCategory.getParentCategoryId())) {
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

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_PARENTCATEGORYID_PARENTCATEGORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCategoryId);

				if (!pagination) {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset category in the ordered set where parentCategoryId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByParentCategoryId_First(long parentCategoryId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByParentCategoryId_First(parentCategoryId,
				orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the first asset category in the ordered set where parentCategoryId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByParentCategoryId_First(long parentCategoryId,
		OrderByComparator<AssetCategory> orderByComparator) {
		List<AssetCategory> list = findByParentCategoryId(parentCategoryId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category in the ordered set where parentCategoryId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByParentCategoryId_Last(long parentCategoryId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByParentCategoryId_Last(parentCategoryId,
				orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the last asset category in the ordered set where parentCategoryId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByParentCategoryId_Last(long parentCategoryId,
		OrderByComparator<AssetCategory> orderByComparator) {
		int count = countByParentCategoryId(parentCategoryId);

		if (count == 0) {
			return null;
		}

		List<AssetCategory> list = findByParentCategoryId(parentCategoryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set where parentCategoryId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] findByParentCategoryId_PrevAndNext(long categoryId,
		long parentCategoryId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = getByParentCategoryId_PrevAndNext(session,
					assetCategory, parentCategoryId, orderByComparator, true);

			array[1] = assetCategory;

			array[2] = getByParentCategoryId_PrevAndNext(session,
					assetCategory, parentCategoryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategory getByParentCategoryId_PrevAndNext(Session session,
		AssetCategory assetCategory, long parentCategoryId,
		OrderByComparator<AssetCategory> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
			query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentCategoryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset categories where parentCategoryId = &#63; from the database.
	 *
	 * @param parentCategoryId the parent category ID
	 */
	@Override
	public void removeByParentCategoryId(long parentCategoryId) {
		for (AssetCategory assetCategory : findByParentCategoryId(
				parentCategoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategory);
		}
	}

	/**
	 * Returns the number of asset categories where parentCategoryId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @return the number of matching asset categories
	 */
	@Override
	public int countByParentCategoryId(long parentCategoryId) {
		FinderPath finderPath = _finderPathCountByParentCategoryId;

		Object[] finderArgs = new Object[] { parentCategoryId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

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
		"assetCategory.parentCategoryId = ?";
	private FinderPath _finderPathWithPaginationFindByVocabularyId;
	private FinderPath _finderPathWithoutPaginationFindByVocabularyId;
	private FinderPath _finderPathCountByVocabularyId;

	/**
	 * Returns all the asset categories where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset categories
	 */
	@Override
	public List<AssetCategory> findByVocabularyId(long vocabularyId) {
		return findByVocabularyId(vocabularyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories where vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByVocabularyId(long vocabularyId, int start,
		int end) {
		return findByVocabularyId(vocabularyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories where vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByVocabularyId(long vocabularyId, int start,
		int end, OrderByComparator<AssetCategory> orderByComparator) {
		return findByVocabularyId(vocabularyId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the asset categories where vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByVocabularyId(long vocabularyId, int start,
		int end, OrderByComparator<AssetCategory> orderByComparator,
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

		List<AssetCategory> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategory>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategory assetCategory : list) {
					if ((vocabularyId != assetCategory.getVocabularyId())) {
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

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_VOCABULARYID_VOCABULARYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(vocabularyId);

				if (!pagination) {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset category in the ordered set where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByVocabularyId_First(long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByVocabularyId_First(vocabularyId,
				orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the first asset category in the ordered set where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByVocabularyId_First(long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator) {
		List<AssetCategory> list = findByVocabularyId(vocabularyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category in the ordered set where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByVocabularyId_Last(long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByVocabularyId_Last(vocabularyId,
				orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the last asset category in the ordered set where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByVocabularyId_Last(long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator) {
		int count = countByVocabularyId(vocabularyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategory> list = findByVocabularyId(vocabularyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set where vocabularyId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] findByVocabularyId_PrevAndNext(long categoryId,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = getByVocabularyId_PrevAndNext(session, assetCategory,
					vocabularyId, orderByComparator, true);

			array[1] = assetCategory;

			array[2] = getByVocabularyId_PrevAndNext(session, assetCategory,
					vocabularyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategory getByVocabularyId_PrevAndNext(Session session,
		AssetCategory assetCategory, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
			query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(vocabularyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset categories where vocabularyId = &#63; from the database.
	 *
	 * @param vocabularyId the vocabulary ID
	 */
	@Override
	public void removeByVocabularyId(long vocabularyId) {
		for (AssetCategory assetCategory : findByVocabularyId(vocabularyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategory);
		}
	}

	/**
	 * Returns the number of asset categories where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset categories
	 */
	@Override
	public int countByVocabularyId(long vocabularyId) {
		FinderPath finderPath = _finderPathCountByVocabularyId;

		Object[] finderArgs = new Object[] { vocabularyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

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

	private static final String _FINDER_COLUMN_VOCABULARYID_VOCABULARYID_2 = "assetCategory.vocabularyId = ?";
	private FinderPath _finderPathWithPaginationFindByG_P;
	private FinderPath _finderPathWithoutPaginationFindByG_P;
	private FinderPath _finderPathCountByG_P;

	/**
	 * Returns all the asset categories where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @return the matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_P(long groupId, long parentCategoryId) {
		return findByG_P(groupId, parentCategoryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_P(long groupId, long parentCategoryId,
		int start, int end) {
		return findByG_P(groupId, parentCategoryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_P(long groupId, long parentCategoryId,
		int start, int end, OrderByComparator<AssetCategory> orderByComparator) {
		return findByG_P(groupId, parentCategoryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset categories where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_P(long groupId, long parentCategoryId,
		int start, int end, OrderByComparator<AssetCategory> orderByComparator,
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

		List<AssetCategory> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategory>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategory assetCategory : list) {
					if ((groupId != assetCategory.getGroupId()) ||
							(parentCategoryId != assetCategory.getParentCategoryId())) {
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

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_PARENTCATEGORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset category in the ordered set where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByG_P_First(long groupId, long parentCategoryId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByG_P_First(groupId,
				parentCategoryId, orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the first asset category in the ordered set where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByG_P_First(long groupId, long parentCategoryId,
		OrderByComparator<AssetCategory> orderByComparator) {
		List<AssetCategory> list = findByG_P(groupId, parentCategoryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category in the ordered set where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByG_P_Last(long groupId, long parentCategoryId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByG_P_Last(groupId,
				parentCategoryId, orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the last asset category in the ordered set where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByG_P_Last(long groupId, long parentCategoryId,
		OrderByComparator<AssetCategory> orderByComparator) {
		int count = countByG_P(groupId, parentCategoryId);

		if (count == 0) {
			return null;
		}

		List<AssetCategory> list = findByG_P(groupId, parentCategoryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] findByG_P_PrevAndNext(long categoryId, long groupId,
		long parentCategoryId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = getByG_P_PrevAndNext(session, assetCategory, groupId,
					parentCategoryId, orderByComparator, true);

			array[1] = assetCategory;

			array[2] = getByG_P_PrevAndNext(session, assetCategory, groupId,
					parentCategoryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategory getByG_P_PrevAndNext(Session session,
		AssetCategory assetCategory, long groupId, long parentCategoryId,
		OrderByComparator<AssetCategory> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
			query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset categories that the user has permission to view where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @return the matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_P(long groupId,
		long parentCategoryId) {
		return filterFindByG_P(groupId, parentCategoryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories that the user has permission to view where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_P(long groupId,
		long parentCategoryId, int start, int end) {
		return filterFindByG_P(groupId, parentCategoryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories that the user has permissions to view where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_P(long groupId,
		long parentCategoryId, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P(groupId, parentCategoryId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_PARENTCATEGORYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AssetCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, AssetCategoryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, AssetCategoryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentCategoryId);

			return (List<AssetCategory>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set of asset categories that the user has permission to view where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] filterFindByG_P_PrevAndNext(long categoryId,
		long groupId, long parentCategoryId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_PrevAndNext(categoryId, groupId, parentCategoryId,
				orderByComparator);
		}

		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = filterGetByG_P_PrevAndNext(session, assetCategory,
					groupId, parentCategoryId, orderByComparator, true);

			array[1] = assetCategory;

			array[2] = filterGetByG_P_PrevAndNext(session, assetCategory,
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

	protected AssetCategory filterGetByG_P_PrevAndNext(Session session,
		AssetCategory assetCategory, long groupId, long parentCategoryId,
		OrderByComparator<AssetCategory> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_PARENTCATEGORYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AssetCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, AssetCategoryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, AssetCategoryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentCategoryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset categories where groupId = &#63; and parentCategoryId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 */
	@Override
	public void removeByG_P(long groupId, long parentCategoryId) {
		for (AssetCategory assetCategory : findByG_P(groupId, parentCategoryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategory);
		}
	}

	/**
	 * Returns the number of asset categories where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @return the number of matching asset categories
	 */
	@Override
	public int countByG_P(long groupId, long parentCategoryId) {
		FinderPath finderPath = _finderPathCountByG_P;

		Object[] finderArgs = new Object[] { groupId, parentCategoryId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

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

	/**
	 * Returns the number of asset categories that the user has permission to view where groupId = &#63; and parentCategoryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @return the number of matching asset categories that the user has permission to view
	 */
	@Override
	public int filterCountByG_P(long groupId, long parentCategoryId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P(groupId, parentCategoryId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_ASSETCATEGORY_WHERE);

		query.append(_FINDER_COLUMN_G_P_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_PARENTCATEGORYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentCategoryId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_P_GROUPID_2 = "assetCategory.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_PARENTCATEGORYID_2 = "assetCategory.parentCategoryId = ?";
	private FinderPath _finderPathWithPaginationFindByG_V;
	private FinderPath _finderPathWithoutPaginationFindByG_V;
	private FinderPath _finderPathCountByG_V;
	private FinderPath _finderPathWithPaginationCountByG_V;

	/**
	 * Returns all the asset categories where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_V(long groupId, long vocabularyId) {
		return findByG_V(groupId, vocabularyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_V(long groupId, long vocabularyId,
		int start, int end) {
		return findByG_V(groupId, vocabularyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_V(long groupId, long vocabularyId,
		int start, int end, OrderByComparator<AssetCategory> orderByComparator) {
		return findByG_V(groupId, vocabularyId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the asset categories where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_V(long groupId, long vocabularyId,
		int start, int end, OrderByComparator<AssetCategory> orderByComparator,
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

		List<AssetCategory> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategory>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategory assetCategory : list) {
					if ((groupId != assetCategory.getGroupId()) ||
							(vocabularyId != assetCategory.getVocabularyId())) {
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

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_G_V_GROUPID_2);

			query.append(_FINDER_COLUMN_G_V_VOCABULARYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset category in the ordered set where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByG_V_First(long groupId, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByG_V_First(groupId, vocabularyId,
				orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the first asset category in the ordered set where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByG_V_First(long groupId, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator) {
		List<AssetCategory> list = findByG_V(groupId, vocabularyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category in the ordered set where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByG_V_Last(long groupId, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByG_V_Last(groupId, vocabularyId,
				orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the last asset category in the ordered set where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByG_V_Last(long groupId, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator) {
		int count = countByG_V(groupId, vocabularyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategory> list = findByG_V(groupId, vocabularyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] findByG_V_PrevAndNext(long categoryId, long groupId,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = getByG_V_PrevAndNext(session, assetCategory, groupId,
					vocabularyId, orderByComparator, true);

			array[1] = assetCategory;

			array[2] = getByG_V_PrevAndNext(session, assetCategory, groupId,
					vocabularyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategory getByG_V_PrevAndNext(Session session,
		AssetCategory assetCategory, long groupId, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
			query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset categories that the user has permission to view where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_V(long groupId, long vocabularyId) {
		return filterFindByG_V(groupId, vocabularyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories that the user has permission to view where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_V(long groupId, long vocabularyId,
		int start, int end) {
		return filterFindByG_V(groupId, vocabularyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories that the user has permissions to view where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_V(long groupId, long vocabularyId,
		int start, int end, OrderByComparator<AssetCategory> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_V(groupId, vocabularyId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_V_GROUPID_2);

		query.append(_FINDER_COLUMN_G_V_VOCABULARYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AssetCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, AssetCategoryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, AssetCategoryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(vocabularyId);

			return (List<AssetCategory>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set of asset categories that the user has permission to view where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] filterFindByG_V_PrevAndNext(long categoryId,
		long groupId, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_V_PrevAndNext(categoryId, groupId, vocabularyId,
				orderByComparator);
		}

		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = filterGetByG_V_PrevAndNext(session, assetCategory,
					groupId, vocabularyId, orderByComparator, true);

			array[1] = assetCategory;

			array[2] = filterGetByG_V_PrevAndNext(session, assetCategory,
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

	protected AssetCategory filterGetByG_V_PrevAndNext(Session session,
		AssetCategory assetCategory, long groupId, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_V_GROUPID_2);

		query.append(_FINDER_COLUMN_G_V_VOCABULARYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AssetCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, AssetCategoryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, AssetCategoryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(vocabularyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset categories that the user has permission to view where groupId = &#63; and vocabularyId = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyIds the vocabulary IDs
	 * @return the matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_V(long groupId,
		long[] vocabularyIds) {
		return filterFindByG_V(groupId, vocabularyIds, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories that the user has permission to view where groupId = &#63; and vocabularyId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param vocabularyIds the vocabulary IDs
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_V(long groupId,
		long[] vocabularyIds, int start, int end) {
		return filterFindByG_V(groupId, vocabularyIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories that the user has permission to view where groupId = &#63; and vocabularyId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param vocabularyIds the vocabulary IDs
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_V(long groupId,
		long[] vocabularyIds, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_V(groupId, vocabularyIds, start, end,
				orderByComparator);
		}

		if (vocabularyIds == null) {
			vocabularyIds = new long[0];
		}
		else if (vocabularyIds.length > 1) {
			vocabularyIds = ArrayUtil.unique(vocabularyIds);

			Arrays.sort(vocabularyIds);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_V_GROUPID_2);

		if (vocabularyIds.length > 0) {
			query.append("(");

			query.append(_FINDER_COLUMN_G_V_VOCABULARYID_7);

			query.append(StringUtil.merge(vocabularyIds));

			query.append(")");

			query.append(")");
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AssetCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, AssetCategoryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, AssetCategoryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<AssetCategory>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the asset categories where groupId = &#63; and vocabularyId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param vocabularyIds the vocabulary IDs
	 * @return the matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_V(long groupId, long[] vocabularyIds) {
		return findByG_V(groupId, vocabularyIds, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories where groupId = &#63; and vocabularyId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param vocabularyIds the vocabulary IDs
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_V(long groupId, long[] vocabularyIds,
		int start, int end) {
		return findByG_V(groupId, vocabularyIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories where groupId = &#63; and vocabularyId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param vocabularyIds the vocabulary IDs
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_V(long groupId, long[] vocabularyIds,
		int start, int end, OrderByComparator<AssetCategory> orderByComparator) {
		return findByG_V(groupId, vocabularyIds, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the asset categories where groupId = &#63; and vocabularyId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_V(long groupId, long[] vocabularyIds,
		int start, int end, OrderByComparator<AssetCategory> orderByComparator,
		boolean retrieveFromCache) {
		if (vocabularyIds == null) {
			vocabularyIds = new long[0];
		}
		else if (vocabularyIds.length > 1) {
			vocabularyIds = ArrayUtil.unique(vocabularyIds);

			Arrays.sort(vocabularyIds);
		}

		if (vocabularyIds.length == 1) {
			return findByG_V(groupId, vocabularyIds[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { groupId, StringUtil.merge(vocabularyIds) };
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(vocabularyIds),
					
					start, end, orderByComparator
				};
		}

		List<AssetCategory> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategory>)FinderCacheUtil.getResult(_finderPathWithPaginationFindByG_V,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategory assetCategory : list) {
					if ((groupId != assetCategory.getGroupId()) ||
							!ArrayUtil.contains(vocabularyIds,
								assetCategory.getVocabularyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_G_V_GROUPID_2);

			if (vocabularyIds.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_V_VOCABULARYID_7);

				query.append(StringUtil.merge(vocabularyIds));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(_finderPathWithPaginationFindByG_V,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(_finderPathWithPaginationFindByG_V,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the asset categories where groupId = &#63; and vocabularyId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 */
	@Override
	public void removeByG_V(long groupId, long vocabularyId) {
		for (AssetCategory assetCategory : findByG_V(groupId, vocabularyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategory);
		}
	}

	/**
	 * Returns the number of asset categories where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset categories
	 */
	@Override
	public int countByG_V(long groupId, long vocabularyId) {
		FinderPath finderPath = _finderPathCountByG_V;

		Object[] finderArgs = new Object[] { groupId, vocabularyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

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

	/**
	 * Returns the number of asset categories where groupId = &#63; and vocabularyId = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyIds the vocabulary IDs
	 * @return the number of matching asset categories
	 */
	@Override
	public int countByG_V(long groupId, long[] vocabularyIds) {
		if (vocabularyIds == null) {
			vocabularyIds = new long[0];
		}
		else if (vocabularyIds.length > 1) {
			vocabularyIds = ArrayUtil.unique(vocabularyIds);

			Arrays.sort(vocabularyIds);
		}

		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(vocabularyIds)
			};

		Long count = (Long)FinderCacheUtil.getResult(_finderPathWithPaginationCountByG_V,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_G_V_GROUPID_2);

			if (vocabularyIds.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_V_VOCABULARYID_7);

				query.append(StringUtil.merge(vocabularyIds));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(_finderPathWithPaginationCountByG_V,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(_finderPathWithPaginationCountByG_V,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset categories that the user has permission to view where groupId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset categories that the user has permission to view
	 */
	@Override
	public int filterCountByG_V(long groupId, long vocabularyId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_V(groupId, vocabularyId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_ASSETCATEGORY_WHERE);

		query.append(_FINDER_COLUMN_G_V_GROUPID_2);

		query.append(_FINDER_COLUMN_G_V_VOCABULARYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(vocabularyId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of asset categories that the user has permission to view where groupId = &#63; and vocabularyId = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param vocabularyIds the vocabulary IDs
	 * @return the number of matching asset categories that the user has permission to view
	 */
	@Override
	public int filterCountByG_V(long groupId, long[] vocabularyIds) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_V(groupId, vocabularyIds);
		}

		if (vocabularyIds == null) {
			vocabularyIds = new long[0];
		}
		else if (vocabularyIds.length > 1) {
			vocabularyIds = ArrayUtil.unique(vocabularyIds);

			Arrays.sort(vocabularyIds);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_ASSETCATEGORY_WHERE);

		query.append(_FINDER_COLUMN_G_V_GROUPID_2);

		if (vocabularyIds.length > 0) {
			query.append("(");

			query.append(_FINDER_COLUMN_G_V_VOCABULARYID_7);

			query.append(StringUtil.merge(vocabularyIds));

			query.append(")");

			query.append(")");
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_V_GROUPID_2 = "assetCategory.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_V_VOCABULARYID_2 = "assetCategory.vocabularyId = ?";
	private static final String _FINDER_COLUMN_G_V_VOCABULARYID_7 = "assetCategory.vocabularyId IN (";
	private FinderPath _finderPathWithPaginationFindByP_N;
	private FinderPath _finderPathWithoutPaginationFindByP_N;
	private FinderPath _finderPathCountByP_N;

	/**
	 * Returns all the asset categories where parentCategoryId = &#63; and name = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @return the matching asset categories
	 */
	@Override
	public List<AssetCategory> findByP_N(long parentCategoryId, String name) {
		return findByP_N(parentCategoryId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories where parentCategoryId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByP_N(long parentCategoryId, String name,
		int start, int end) {
		return findByP_N(parentCategoryId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories where parentCategoryId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByP_N(long parentCategoryId, String name,
		int start, int end, OrderByComparator<AssetCategory> orderByComparator) {
		return findByP_N(parentCategoryId, name, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the asset categories where parentCategoryId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByP_N(long parentCategoryId, String name,
		int start, int end, OrderByComparator<AssetCategory> orderByComparator,
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

		List<AssetCategory> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategory>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategory assetCategory : list) {
					if ((parentCategoryId != assetCategory.getParentCategoryId()) ||
							!name.equals(assetCategory.getName())) {
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

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset category in the ordered set where parentCategoryId = &#63; and name = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByP_N_First(long parentCategoryId, String name,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByP_N_First(parentCategoryId, name,
				orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", name=");
		msg.append(name);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the first asset category in the ordered set where parentCategoryId = &#63; and name = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByP_N_First(long parentCategoryId, String name,
		OrderByComparator<AssetCategory> orderByComparator) {
		List<AssetCategory> list = findByP_N(parentCategoryId, name, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category in the ordered set where parentCategoryId = &#63; and name = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByP_N_Last(long parentCategoryId, String name,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByP_N_Last(parentCategoryId, name,
				orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", name=");
		msg.append(name);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the last asset category in the ordered set where parentCategoryId = &#63; and name = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByP_N_Last(long parentCategoryId, String name,
		OrderByComparator<AssetCategory> orderByComparator) {
		int count = countByP_N(parentCategoryId, name);

		if (count == 0) {
			return null;
		}

		List<AssetCategory> list = findByP_N(parentCategoryId, name, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set where parentCategoryId = &#63; and name = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] findByP_N_PrevAndNext(long categoryId,
		long parentCategoryId, String name,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		name = Objects.toString(name, "");

		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = getByP_N_PrevAndNext(session, assetCategory,
					parentCategoryId, name, orderByComparator, true);

			array[1] = assetCategory;

			array[2] = getByP_N_PrevAndNext(session, assetCategory,
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

	protected AssetCategory getByP_N_PrevAndNext(Session session,
		AssetCategory assetCategory, long parentCategoryId, String name,
		OrderByComparator<AssetCategory> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
			query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset categories where parentCategoryId = &#63; and name = &#63; from the database.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 */
	@Override
	public void removeByP_N(long parentCategoryId, String name) {
		for (AssetCategory assetCategory : findByP_N(parentCategoryId, name,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategory);
		}
	}

	/**
	 * Returns the number of asset categories where parentCategoryId = &#63; and name = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @return the number of matching asset categories
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

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

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

	private static final String _FINDER_COLUMN_P_N_PARENTCATEGORYID_2 = "assetCategory.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_P_N_NAME_2 = "assetCategory.name = ?";
	private static final String _FINDER_COLUMN_P_N_NAME_3 = "(assetCategory.name IS NULL OR assetCategory.name = '')";
	private FinderPath _finderPathWithPaginationFindByP_V;
	private FinderPath _finderPathWithoutPaginationFindByP_V;
	private FinderPath _finderPathCountByP_V;

	/**
	 * Returns all the asset categories where parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset categories
	 */
	@Override
	public List<AssetCategory> findByP_V(long parentCategoryId,
		long vocabularyId) {
		return findByP_V(parentCategoryId, vocabularyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories where parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByP_V(long parentCategoryId,
		long vocabularyId, int start, int end) {
		return findByP_V(parentCategoryId, vocabularyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories where parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByP_V(long parentCategoryId,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator) {
		return findByP_V(parentCategoryId, vocabularyId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset categories where parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByP_V(long parentCategoryId,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator,
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

		List<AssetCategory> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategory>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategory assetCategory : list) {
					if ((parentCategoryId != assetCategory.getParentCategoryId()) ||
							(vocabularyId != assetCategory.getVocabularyId())) {
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

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_P_V_PARENTCATEGORYID_2);

			query.append(_FINDER_COLUMN_P_V_VOCABULARYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset category in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByP_V_First(long parentCategoryId,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByP_V_First(parentCategoryId,
				vocabularyId, orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the first asset category in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByP_V_First(long parentCategoryId,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator) {
		List<AssetCategory> list = findByP_V(parentCategoryId, vocabularyId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByP_V_Last(long parentCategoryId,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByP_V_Last(parentCategoryId,
				vocabularyId, orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCategoryId=");
		msg.append(parentCategoryId);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the last asset category in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByP_V_Last(long parentCategoryId,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator) {
		int count = countByP_V(parentCategoryId, vocabularyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategory> list = findByP_V(parentCategoryId, vocabularyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set where parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] findByP_V_PrevAndNext(long categoryId,
		long parentCategoryId, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = getByP_V_PrevAndNext(session, assetCategory,
					parentCategoryId, vocabularyId, orderByComparator, true);

			array[1] = assetCategory;

			array[2] = getByP_V_PrevAndNext(session, assetCategory,
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

	protected AssetCategory getByP_V_PrevAndNext(Session session,
		AssetCategory assetCategory, long parentCategoryId, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
			query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset categories where parentCategoryId = &#63; and vocabularyId = &#63; from the database.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 */
	@Override
	public void removeByP_V(long parentCategoryId, long vocabularyId) {
		for (AssetCategory assetCategory : findByP_V(parentCategoryId,
				vocabularyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategory);
		}
	}

	/**
	 * Returns the number of asset categories where parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset categories
	 */
	@Override
	public int countByP_V(long parentCategoryId, long vocabularyId) {
		FinderPath finderPath = _finderPathCountByP_V;

		Object[] finderArgs = new Object[] { parentCategoryId, vocabularyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

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

	private static final String _FINDER_COLUMN_P_V_PARENTCATEGORYID_2 = "assetCategory.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_P_V_VOCABULARYID_2 = "assetCategory.vocabularyId = ?";
	private FinderPath _finderPathWithPaginationFindByN_V;
	private FinderPath _finderPathWithoutPaginationFindByN_V;
	private FinderPath _finderPathCountByN_V;

	/**
	 * Returns all the asset categories where name = &#63; and vocabularyId = &#63;.
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset categories
	 */
	@Override
	public List<AssetCategory> findByN_V(String name, long vocabularyId) {
		return findByN_V(name, vocabularyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories where name = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByN_V(String name, long vocabularyId,
		int start, int end) {
		return findByN_V(name, vocabularyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories where name = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByN_V(String name, long vocabularyId,
		int start, int end, OrderByComparator<AssetCategory> orderByComparator) {
		return findByN_V(name, vocabularyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset categories where name = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByN_V(String name, long vocabularyId,
		int start, int end, OrderByComparator<AssetCategory> orderByComparator,
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

		List<AssetCategory> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategory>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategory assetCategory : list) {
					if (!name.equals(assetCategory.getName()) ||
							(vocabularyId != assetCategory.getVocabularyId())) {
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

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset category in the ordered set where name = &#63; and vocabularyId = &#63;.
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByN_V_First(String name, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByN_V_First(name, vocabularyId,
				orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the first asset category in the ordered set where name = &#63; and vocabularyId = &#63;.
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByN_V_First(String name, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator) {
		List<AssetCategory> list = findByN_V(name, vocabularyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category in the ordered set where name = &#63; and vocabularyId = &#63;.
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByN_V_Last(String name, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByN_V_Last(name, vocabularyId,
				orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(", vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the last asset category in the ordered set where name = &#63; and vocabularyId = &#63;.
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByN_V_Last(String name, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator) {
		int count = countByN_V(name, vocabularyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategory> list = findByN_V(name, vocabularyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set where name = &#63; and vocabularyId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] findByN_V_PrevAndNext(long categoryId, String name,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		name = Objects.toString(name, "");

		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = getByN_V_PrevAndNext(session, assetCategory, name,
					vocabularyId, orderByComparator, true);

			array[1] = assetCategory;

			array[2] = getByN_V_PrevAndNext(session, assetCategory, name,
					vocabularyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategory getByN_V_PrevAndNext(Session session,
		AssetCategory assetCategory, String name, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
			query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset categories where name = &#63; and vocabularyId = &#63; from the database.
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 */
	@Override
	public void removeByN_V(String name, long vocabularyId) {
		for (AssetCategory assetCategory : findByN_V(name, vocabularyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategory);
		}
	}

	/**
	 * Returns the number of asset categories where name = &#63; and vocabularyId = &#63;.
	 *
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset categories
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

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

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

	private static final String _FINDER_COLUMN_N_V_NAME_2 = "assetCategory.name = ? AND ";
	private static final String _FINDER_COLUMN_N_V_NAME_3 = "(assetCategory.name IS NULL OR assetCategory.name = '') AND ";
	private static final String _FINDER_COLUMN_N_V_VOCABULARYID_2 = "assetCategory.vocabularyId = ?";
	private FinderPath _finderPathWithPaginationFindByG_P_V;
	private FinderPath _finderPathWithoutPaginationFindByG_P_V;
	private FinderPath _finderPathCountByG_P_V;

	/**
	 * Returns all the asset categories where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_P_V(long groupId, long parentCategoryId,
		long vocabularyId) {
		return findByG_P_V(groupId, parentCategoryId, vocabularyId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_P_V(long groupId, long parentCategoryId,
		long vocabularyId, int start, int end) {
		return findByG_P_V(groupId, parentCategoryId, vocabularyId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the asset categories where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_P_V(long groupId, long parentCategoryId,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator) {
		return findByG_P_V(groupId, parentCategoryId, vocabularyId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset categories where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_P_V(long groupId, long parentCategoryId,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator,
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

		List<AssetCategory> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategory>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategory assetCategory : list) {
					if ((groupId != assetCategory.getGroupId()) ||
							(parentCategoryId != assetCategory.getParentCategoryId()) ||
							(vocabularyId != assetCategory.getVocabularyId())) {
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

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_G_P_V_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_V_PARENTCATEGORYID_2);

			query.append(_FINDER_COLUMN_G_P_V_VOCABULARYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset category in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByG_P_V_First(long groupId, long parentCategoryId,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByG_P_V_First(groupId,
				parentCategoryId, vocabularyId, orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
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

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the first asset category in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByG_P_V_First(long groupId,
		long parentCategoryId, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator) {
		List<AssetCategory> list = findByG_P_V(groupId, parentCategoryId,
				vocabularyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByG_P_V_Last(long groupId, long parentCategoryId,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByG_P_V_Last(groupId,
				parentCategoryId, vocabularyId, orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
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

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the last asset category in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByG_P_V_Last(long groupId, long parentCategoryId,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator) {
		int count = countByG_P_V(groupId, parentCategoryId, vocabularyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategory> list = findByG_P_V(groupId, parentCategoryId,
				vocabularyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] findByG_P_V_PrevAndNext(long categoryId,
		long groupId, long parentCategoryId, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = getByG_P_V_PrevAndNext(session, assetCategory, groupId,
					parentCategoryId, vocabularyId, orderByComparator, true);

			array[1] = assetCategory;

			array[2] = getByG_P_V_PrevAndNext(session, assetCategory, groupId,
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

	protected AssetCategory getByG_P_V_PrevAndNext(Session session,
		AssetCategory assetCategory, long groupId, long parentCategoryId,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator,
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

		query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
			query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset categories that the user has permission to view where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_P_V(long groupId,
		long parentCategoryId, long vocabularyId) {
		return filterFindByG_P_V(groupId, parentCategoryId, vocabularyId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories that the user has permission to view where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_P_V(long groupId,
		long parentCategoryId, long vocabularyId, int start, int end) {
		return filterFindByG_P_V(groupId, parentCategoryId, vocabularyId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories that the user has permissions to view where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_P_V(long groupId,
		long parentCategoryId, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_V(groupId, parentCategoryId, vocabularyId, start,
				end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_V_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_V_PARENTCATEGORYID_2);

		query.append(_FINDER_COLUMN_G_P_V_VOCABULARYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AssetCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, AssetCategoryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, AssetCategoryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentCategoryId);

			qPos.add(vocabularyId);

			return (List<AssetCategory>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set of asset categories that the user has permission to view where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] filterFindByG_P_V_PrevAndNext(long categoryId,
		long groupId, long parentCategoryId, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_V_PrevAndNext(categoryId, groupId,
				parentCategoryId, vocabularyId, orderByComparator);
		}

		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = filterGetByG_P_V_PrevAndNext(session, assetCategory,
					groupId, parentCategoryId, vocabularyId, orderByComparator,
					true);

			array[1] = assetCategory;

			array[2] = filterGetByG_P_V_PrevAndNext(session, assetCategory,
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

	protected AssetCategory filterGetByG_P_V_PrevAndNext(Session session,
		AssetCategory assetCategory, long groupId, long parentCategoryId,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_V_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_V_PARENTCATEGORYID_2);

		query.append(_FINDER_COLUMN_G_P_V_VOCABULARYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AssetCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, AssetCategoryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, AssetCategoryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentCategoryId);

		qPos.add(vocabularyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset categories where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 */
	@Override
	public void removeByG_P_V(long groupId, long parentCategoryId,
		long vocabularyId) {
		for (AssetCategory assetCategory : findByG_P_V(groupId,
				parentCategoryId, vocabularyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetCategory);
		}
	}

	/**
	 * Returns the number of asset categories where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset categories
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

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

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

	/**
	 * Returns the number of asset categories that the user has permission to view where groupId = &#63; and parentCategoryId = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset categories that the user has permission to view
	 */
	@Override
	public int filterCountByG_P_V(long groupId, long parentCategoryId,
		long vocabularyId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_V(groupId, parentCategoryId, vocabularyId);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_ASSETCATEGORY_WHERE);

		query.append(_FINDER_COLUMN_G_P_V_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_V_PARENTCATEGORYID_2);

		query.append(_FINDER_COLUMN_G_P_V_VOCABULARYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentCategoryId);

			qPos.add(vocabularyId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_P_V_GROUPID_2 = "assetCategory.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_V_PARENTCATEGORYID_2 = "assetCategory.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_V_VOCABULARYID_2 = "assetCategory.vocabularyId = ?";
	private FinderPath _finderPathWithPaginationFindByG_LikeN_V;
	private FinderPath _finderPathWithPaginationCountByG_LikeN_V;

	/**
	 * Returns all the asset categories where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_LikeN_V(long groupId, String name,
		long vocabularyId) {
		return findByG_LikeN_V(groupId, name, vocabularyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_LikeN_V(long groupId, String name,
		long vocabularyId, int start, int end) {
		return findByG_LikeN_V(groupId, name, vocabularyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_LikeN_V(long groupId, String name,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator) {
		return findByG_LikeN_V(groupId, name, vocabularyId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset categories where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_LikeN_V(long groupId, String name,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator,
		boolean retrieveFromCache) {
		name = Objects.toString(name, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByG_LikeN_V;
		finderArgs = new Object[] {
				groupId, name, vocabularyId,
				
				start, end, orderByComparator
			};

		List<AssetCategory> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategory>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategory assetCategory : list) {
					if ((groupId != assetCategory.getGroupId()) ||
							!StringUtil.wildcardMatches(
								assetCategory.getName(), name, '_', '%', '\\',
								false) ||
							(vocabularyId != assetCategory.getVocabularyId())) {
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

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(StringUtil.toLowerCase(name));
				}

				qPos.add(vocabularyId);

				if (!pagination) {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset category in the ordered set where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByG_LikeN_V_First(long groupId, String name,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByG_LikeN_V_First(groupId, name,
				vocabularyId, orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
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

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the first asset category in the ordered set where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByG_LikeN_V_First(long groupId, String name,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator) {
		List<AssetCategory> list = findByG_LikeN_V(groupId, name, vocabularyId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category in the ordered set where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByG_LikeN_V_Last(long groupId, String name,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByG_LikeN_V_Last(groupId, name,
				vocabularyId, orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
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

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the last asset category in the ordered set where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByG_LikeN_V_Last(long groupId, String name,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator) {
		int count = countByG_LikeN_V(groupId, name, vocabularyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategory> list = findByG_LikeN_V(groupId, name, vocabularyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] findByG_LikeN_V_PrevAndNext(long categoryId,
		long groupId, String name, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		name = Objects.toString(name, "");

		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = getByG_LikeN_V_PrevAndNext(session, assetCategory,
					groupId, name, vocabularyId, orderByComparator, true);

			array[1] = assetCategory;

			array[2] = getByG_LikeN_V_PrevAndNext(session, assetCategory,
					groupId, name, vocabularyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategory getByG_LikeN_V_PrevAndNext(Session session,
		AssetCategory assetCategory, long groupId, String name,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator,
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

		query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
			query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindName) {
			qPos.add(StringUtil.toLowerCase(name));
		}

		qPos.add(vocabularyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset categories that the user has permission to view where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_LikeN_V(long groupId, String name,
		long vocabularyId) {
		return filterFindByG_LikeN_V(groupId, name, vocabularyId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories that the user has permission to view where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_LikeN_V(long groupId, String name,
		long vocabularyId, int start, int end) {
		return filterFindByG_LikeN_V(groupId, name, vocabularyId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the asset categories that the user has permissions to view where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_LikeN_V(long groupId, String name,
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_LikeN_V(groupId, name, vocabularyId, start, end,
				orderByComparator);
		}

		name = Objects.toString(name, "");

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

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

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AssetCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, AssetCategoryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, AssetCategoryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindName) {
				qPos.add(StringUtil.toLowerCase(name));
			}

			qPos.add(vocabularyId);

			return (List<AssetCategory>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set of asset categories that the user has permission to view where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] filterFindByG_LikeN_V_PrevAndNext(long categoryId,
		long groupId, String name, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_LikeN_V_PrevAndNext(categoryId, groupId, name,
				vocabularyId, orderByComparator);
		}

		name = Objects.toString(name, "");

		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = filterGetByG_LikeN_V_PrevAndNext(session, assetCategory,
					groupId, name, vocabularyId, orderByComparator, true);

			array[1] = assetCategory;

			array[2] = filterGetByG_LikeN_V_PrevAndNext(session, assetCategory,
					groupId, name, vocabularyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetCategory filterGetByG_LikeN_V_PrevAndNext(Session session,
		AssetCategory assetCategory, long groupId, String name,
		long vocabularyId, OrderByComparator<AssetCategory> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

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

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AssetCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, AssetCategoryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, AssetCategoryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindName) {
			qPos.add(StringUtil.toLowerCase(name));
		}

		qPos.add(vocabularyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset categories that the user has permission to view where groupId = &#63; and name LIKE &#63; and vocabularyId = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyIds the vocabulary IDs
	 * @return the matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_LikeN_V(long groupId, String name,
		long[] vocabularyIds) {
		return filterFindByG_LikeN_V(groupId, name, vocabularyIds,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories that the user has permission to view where groupId = &#63; and name LIKE &#63; and vocabularyId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyIds the vocabulary IDs
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_LikeN_V(long groupId, String name,
		long[] vocabularyIds, int start, int end) {
		return filterFindByG_LikeN_V(groupId, name, vocabularyIds, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the asset categories that the user has permission to view where groupId = &#63; and name LIKE &#63; and vocabularyId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyIds the vocabulary IDs
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_LikeN_V(long groupId, String name,
		long[] vocabularyIds, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_LikeN_V(groupId, name, vocabularyIds, start, end,
				orderByComparator);
		}

		name = Objects.toString(name, "");

		if (vocabularyIds == null) {
			vocabularyIds = new long[0];
		}
		else if (vocabularyIds.length > 1) {
			vocabularyIds = ArrayUtil.unique(vocabularyIds);

			Arrays.sort(vocabularyIds);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_LIKEN_V_GROUPID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			query.append(_FINDER_COLUMN_G_LIKEN_V_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_LIKEN_V_NAME_2);
		}

		if (vocabularyIds.length > 0) {
			query.append("(");

			query.append(_FINDER_COLUMN_G_LIKEN_V_VOCABULARYID_7);

			query.append(StringUtil.merge(vocabularyIds));

			query.append(")");

			query.append(")");
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AssetCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, AssetCategoryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, AssetCategoryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindName) {
				qPos.add(StringUtil.toLowerCase(name));
			}

			return (List<AssetCategory>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the asset categories where groupId = &#63; and name LIKE &#63; and vocabularyId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyIds the vocabulary IDs
	 * @return the matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_LikeN_V(long groupId, String name,
		long[] vocabularyIds) {
		return findByG_LikeN_V(groupId, name, vocabularyIds, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories where groupId = &#63; and name LIKE &#63; and vocabularyId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyIds the vocabulary IDs
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_LikeN_V(long groupId, String name,
		long[] vocabularyIds, int start, int end) {
		return findByG_LikeN_V(groupId, name, vocabularyIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories where groupId = &#63; and name LIKE &#63; and vocabularyId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyIds the vocabulary IDs
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_LikeN_V(long groupId, String name,
		long[] vocabularyIds, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator) {
		return findByG_LikeN_V(groupId, name, vocabularyIds, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset categories where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_LikeN_V(long groupId, String name,
		long[] vocabularyIds, int start, int end,
		OrderByComparator<AssetCategory> orderByComparator,
		boolean retrieveFromCache) {
		name = Objects.toString(name, "");

		if (vocabularyIds == null) {
			vocabularyIds = new long[0];
		}
		else if (vocabularyIds.length > 1) {
			vocabularyIds = ArrayUtil.unique(vocabularyIds);

			Arrays.sort(vocabularyIds);
		}

		if (vocabularyIds.length == 1) {
			return findByG_LikeN_V(groupId, name, vocabularyIds[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, name, StringUtil.merge(vocabularyIds)
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, name, StringUtil.merge(vocabularyIds),
					
					start, end, orderByComparator
				};
		}

		List<AssetCategory> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategory>)FinderCacheUtil.getResult(_finderPathWithPaginationFindByG_LikeN_V,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategory assetCategory : list) {
					if ((groupId != assetCategory.getGroupId()) ||
							!StringUtil.wildcardMatches(
								assetCategory.getName(), name, '_', '%', '\\',
								false) ||
							!ArrayUtil.contains(vocabularyIds,
								assetCategory.getVocabularyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_G_LIKEN_V_GROUPID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_G_LIKEN_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_LIKEN_V_NAME_2);
			}

			if (vocabularyIds.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_LIKEN_V_VOCABULARYID_7);

				query.append(StringUtil.merge(vocabularyIds));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(StringUtil.toLowerCase(name));
				}

				if (!pagination) {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(_finderPathWithPaginationFindByG_LikeN_V,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(_finderPathWithPaginationFindByG_LikeN_V,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the asset categories where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 */
	@Override
	public void removeByG_LikeN_V(long groupId, String name, long vocabularyId) {
		for (AssetCategory assetCategory : findByG_LikeN_V(groupId, name,
				vocabularyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetCategory);
		}
	}

	/**
	 * Returns the number of asset categories where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset categories
	 */
	@Override
	public int countByG_LikeN_V(long groupId, String name, long vocabularyId) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathWithPaginationCountByG_LikeN_V;

		Object[] finderArgs = new Object[] { groupId, name, vocabularyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

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
					qPos.add(StringUtil.toLowerCase(name));
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

	/**
	 * Returns the number of asset categories where groupId = &#63; and name LIKE &#63; and vocabularyId = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyIds the vocabulary IDs
	 * @return the number of matching asset categories
	 */
	@Override
	public int countByG_LikeN_V(long groupId, String name, long[] vocabularyIds) {
		name = Objects.toString(name, "");

		if (vocabularyIds == null) {
			vocabularyIds = new long[0];
		}
		else if (vocabularyIds.length > 1) {
			vocabularyIds = ArrayUtil.unique(vocabularyIds);

			Arrays.sort(vocabularyIds);
		}

		Object[] finderArgs = new Object[] {
				groupId, name, StringUtil.merge(vocabularyIds)
			};

		Long count = (Long)FinderCacheUtil.getResult(_finderPathWithPaginationCountByG_LikeN_V,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_G_LIKEN_V_GROUPID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_G_LIKEN_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_LIKEN_V_NAME_2);
			}

			if (vocabularyIds.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_LIKEN_V_VOCABULARYID_7);

				query.append(StringUtil.merge(vocabularyIds));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(StringUtil.toLowerCase(name));
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(_finderPathWithPaginationCountByG_LikeN_V,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(_finderPathWithPaginationCountByG_LikeN_V,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset categories that the user has permission to view where groupId = &#63; and name LIKE &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset categories that the user has permission to view
	 */
	@Override
	public int filterCountByG_LikeN_V(long groupId, String name,
		long vocabularyId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_LikeN_V(groupId, name, vocabularyId);
		}

		name = Objects.toString(name, "");

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_ASSETCATEGORY_WHERE);

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

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindName) {
				qPos.add(StringUtil.toLowerCase(name));
			}

			qPos.add(vocabularyId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of asset categories that the user has permission to view where groupId = &#63; and name LIKE &#63; and vocabularyId = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param vocabularyIds the vocabulary IDs
	 * @return the number of matching asset categories that the user has permission to view
	 */
	@Override
	public int filterCountByG_LikeN_V(long groupId, String name,
		long[] vocabularyIds) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_LikeN_V(groupId, name, vocabularyIds);
		}

		name = Objects.toString(name, "");

		if (vocabularyIds == null) {
			vocabularyIds = new long[0];
		}
		else if (vocabularyIds.length > 1) {
			vocabularyIds = ArrayUtil.unique(vocabularyIds);

			Arrays.sort(vocabularyIds);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_ASSETCATEGORY_WHERE);

		query.append(_FINDER_COLUMN_G_LIKEN_V_GROUPID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			query.append(_FINDER_COLUMN_G_LIKEN_V_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_LIKEN_V_NAME_2);
		}

		if (vocabularyIds.length > 0) {
			query.append("(");

			query.append(_FINDER_COLUMN_G_LIKEN_V_VOCABULARYID_7);

			query.append(StringUtil.merge(vocabularyIds));

			query.append(")");

			query.append(")");
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindName) {
				qPos.add(StringUtil.toLowerCase(name));
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_LIKEN_V_GROUPID_2 = "assetCategory.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_LIKEN_V_NAME_2 = "lower(assetCategory.name) LIKE ? AND ";
	private static final String _FINDER_COLUMN_G_LIKEN_V_NAME_3 = "(assetCategory.name IS NULL OR assetCategory.name LIKE '') AND ";
	private static final String _FINDER_COLUMN_G_LIKEN_V_VOCABULARYID_2 = "assetCategory.vocabularyId = ?";
	private static final String _FINDER_COLUMN_G_LIKEN_V_VOCABULARYID_7 = "assetCategory.vocabularyId IN (";
	private FinderPath _finderPathFetchByP_N_V;
	private FinderPath _finderPathCountByP_N_V;

	/**
	 * Returns the asset category where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and head = &#63; or throws a <code>NoSuchCategoryException</code> if it could not be found.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param head the head
	 * @return the matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByP_N_V(long parentCategoryId, String name,
		long vocabularyId, boolean head) throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByP_N_V(parentCategoryId, name,
				vocabularyId, head);

		if (assetCategory == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("parentCategoryId=");
			msg.append(parentCategoryId);

			msg.append(", name=");
			msg.append(name);

			msg.append(", vocabularyId=");
			msg.append(vocabularyId);

			msg.append(", head=");
			msg.append(head);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCategoryException(msg.toString());
		}

		return assetCategory;
	}

	/**
	 * Returns the asset category where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and head = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param head the head
	 * @return the matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByP_N_V(long parentCategoryId, String name,
		long vocabularyId, boolean head) {
		return fetchByP_N_V(parentCategoryId, name, vocabularyId, head, true);
	}

	/**
	 * Returns the asset category where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and head = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param head the head
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByP_N_V(long parentCategoryId, String name,
		long vocabularyId, boolean head, boolean retrieveFromCache) {
		name = Objects.toString(name, "");

		Object[] finderArgs = new Object[] {
				parentCategoryId, name, vocabularyId, head
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(_finderPathFetchByP_N_V,
					finderArgs, this);
		}

		if (result instanceof AssetCategory) {
			AssetCategory assetCategory = (AssetCategory)result;

			if ((parentCategoryId != assetCategory.getParentCategoryId()) ||
					!Objects.equals(name, assetCategory.getName()) ||
					(vocabularyId != assetCategory.getVocabularyId()) ||
					(head != assetCategory.isHead())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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

			query.append(_FINDER_COLUMN_P_N_V_HEAD_2);

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

				qPos.add(head);

				List<AssetCategory> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(_finderPathFetchByP_N_V,
						finderArgs, list);
				}
				else {
					AssetCategory assetCategory = list.get(0);

					result = assetCategory;

					cacheResult(assetCategory);
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(_finderPathFetchByP_N_V, finderArgs);

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
			return (AssetCategory)result;
		}
	}

	/**
	 * Removes the asset category where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and head = &#63; from the database.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param head the head
	 * @return the asset category that was removed
	 */
	@Override
	public AssetCategory removeByP_N_V(long parentCategoryId, String name,
		long vocabularyId, boolean head) throws NoSuchCategoryException {
		AssetCategory assetCategory = findByP_N_V(parentCategoryId, name,
				vocabularyId, head);

		return remove(assetCategory);
	}

	/**
	 * Returns the number of asset categories where parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; and head = &#63;.
	 *
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param head the head
	 * @return the number of matching asset categories
	 */
	@Override
	public int countByP_N_V(long parentCategoryId, String name,
		long vocabularyId, boolean head) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByP_N_V;

		Object[] finderArgs = new Object[] {
				parentCategoryId, name, vocabularyId, head
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

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

			query.append(_FINDER_COLUMN_P_N_V_HEAD_2);

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

				qPos.add(head);

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

	private static final String _FINDER_COLUMN_P_N_V_PARENTCATEGORYID_2 = "assetCategory.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_P_N_V_NAME_2 = "assetCategory.name = ? AND ";
	private static final String _FINDER_COLUMN_P_N_V_NAME_3 = "(assetCategory.name IS NULL OR assetCategory.name = '') AND ";
	private static final String _FINDER_COLUMN_P_N_V_VOCABULARYID_2 = "assetCategory.vocabularyId = ? AND ";
	private static final String _FINDER_COLUMN_P_N_V_HEAD_2 = "assetCategory.head = ?";
	private FinderPath _finderPathWithPaginationFindByG_P_N_V;
	private FinderPath _finderPathWithoutPaginationFindByG_P_N_V;
	private FinderPath _finderPathCountByG_P_N_V;

	/**
	 * Returns all the asset categories where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId) {
		return findByG_P_N_V(groupId, parentCategoryId, name, vocabularyId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId, int start,
		int end) {
		return findByG_P_N_V(groupId, parentCategoryId, name, vocabularyId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId, int start,
		int end, OrderByComparator<AssetCategory> orderByComparator) {
		return findByG_P_N_V(groupId, parentCategoryId, name, vocabularyId,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset categories where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset categories
	 */
	@Override
	public List<AssetCategory> findByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId, int start,
		int end, OrderByComparator<AssetCategory> orderByComparator,
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

		List<AssetCategory> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategory>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetCategory assetCategory : list) {
					if ((groupId != assetCategory.getGroupId()) ||
							(parentCategoryId != assetCategory.getParentCategoryId()) ||
							!name.equals(assetCategory.getName()) ||
							(vocabularyId != assetCategory.getVocabularyId())) {
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

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset category in the ordered set where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByG_P_N_V_First(long groupId,
		long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByG_P_N_V_First(groupId,
				parentCategoryId, name, vocabularyId, orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
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

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the first asset category in the ordered set where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByG_P_N_V_First(long groupId,
		long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator) {
		List<AssetCategory> list = findByG_P_N_V(groupId, parentCategoryId,
				name, vocabularyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset category in the ordered set where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByG_P_N_V_Last(long groupId,
		long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByG_P_N_V_Last(groupId,
				parentCategoryId, name, vocabularyId, orderByComparator);

		if (assetCategory != null) {
			return assetCategory;
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

		throw new NoSuchCategoryException(msg.toString());
	}

	/**
	 * Returns the last asset category in the ordered set where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByG_P_N_V_Last(long groupId,
		long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator) {
		int count = countByG_P_N_V(groupId, parentCategoryId, name, vocabularyId);

		if (count == 0) {
			return null;
		}

		List<AssetCategory> list = findByG_P_N_V(groupId, parentCategoryId,
				name, vocabularyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] findByG_P_N_V_PrevAndNext(long categoryId,
		long groupId, long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		name = Objects.toString(name, "");

		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = getByG_P_N_V_PrevAndNext(session, assetCategory,
					groupId, parentCategoryId, name, vocabularyId,
					orderByComparator, true);

			array[1] = assetCategory;

			array[2] = getByG_P_N_V_PrevAndNext(session, assetCategory,
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

	protected AssetCategory getByG_P_N_V_PrevAndNext(Session session,
		AssetCategory assetCategory, long groupId, long parentCategoryId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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
			query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
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
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset categories that the user has permission to view where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId) {
		return filterFindByG_P_N_V(groupId, parentCategoryId, name,
			vocabularyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories that the user has permission to view where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId, int start,
		int end) {
		return filterFindByG_P_N_V(groupId, parentCategoryId, name,
			vocabularyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories that the user has permissions to view where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset categories that the user has permission to view
	 */
	@Override
	public List<AssetCategory> filterFindByG_P_N_V(long groupId,
		long parentCategoryId, String name, long vocabularyId, int start,
		int end, OrderByComparator<AssetCategory> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_N_V(groupId, parentCategoryId, name, vocabularyId,
				start, end, orderByComparator);
		}

		name = Objects.toString(name, "");

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(7);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

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

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AssetCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, AssetCategoryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, AssetCategoryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentCategoryId);

			if (bindName) {
				qPos.add(name);
			}

			qPos.add(vocabularyId);

			return (List<AssetCategory>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the asset categories before and after the current asset category in the ordered set of asset categories that the user has permission to view where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * @param categoryId the primary key of the current asset category
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory[] filterFindByG_P_N_V_PrevAndNext(long categoryId,
		long groupId, long parentCategoryId, String name, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator)
		throws NoSuchCategoryException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_N_V_PrevAndNext(categoryId, groupId,
				parentCategoryId, name, vocabularyId, orderByComparator);
		}

		name = Objects.toString(name, "");

		AssetCategory assetCategory = findByPrimaryKey(categoryId);

		Session session = null;

		try {
			session = openSession();

			AssetCategory[] array = new AssetCategoryImpl[3];

			array[0] = filterGetByG_P_N_V_PrevAndNext(session, assetCategory,
					groupId, parentCategoryId, name, vocabularyId,
					orderByComparator, true);

			array[1] = assetCategory;

			array[2] = filterGetByG_P_N_V_PrevAndNext(session, assetCategory,
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

	protected AssetCategory filterGetByG_P_N_V_PrevAndNext(Session session,
		AssetCategory assetCategory, long groupId, long parentCategoryId,
		String name, long vocabularyId,
		OrderByComparator<AssetCategory> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(8 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(7);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

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

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AssetCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AssetCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, AssetCategoryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, AssetCategoryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentCategoryId);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(vocabularyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					assetCategory)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<AssetCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset categories where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 */
	@Override
	public void removeByG_P_N_V(long groupId, long parentCategoryId,
		String name, long vocabularyId) {
		for (AssetCategory assetCategory : findByG_P_N_V(groupId,
				parentCategoryId, name, vocabularyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetCategory);
		}
	}

	/**
	 * Returns the number of asset categories where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset categories
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

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

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

	/**
	 * Returns the number of asset categories that the user has permission to view where groupId = &#63; and parentCategoryId = &#63; and name = &#63; and vocabularyId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentCategoryId the parent category ID
	 * @param name the name
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching asset categories that the user has permission to view
	 */
	@Override
	public int filterCountByG_P_N_V(long groupId, long parentCategoryId,
		String name, long vocabularyId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_N_V(groupId, parentCategoryId, name, vocabularyId);
		}

		name = Objects.toString(name, "");

		StringBundler query = new StringBundler(5);

		query.append(_FILTER_SQL_COUNT_ASSETCATEGORY_WHERE);

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

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AssetCategory.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentCategoryId);

			if (bindName) {
				qPos.add(name);
			}

			qPos.add(vocabularyId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_P_N_V_GROUPID_2 = "assetCategory.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_N_V_PARENTCATEGORYID_2 = "assetCategory.parentCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_N_V_NAME_2 = "assetCategory.name = ? AND ";
	private static final String _FINDER_COLUMN_G_P_N_V_NAME_3 = "(assetCategory.name IS NULL OR assetCategory.name = '') AND ";
	private static final String _FINDER_COLUMN_G_P_N_V_VOCABULARYID_2 = "assetCategory.vocabularyId = ?";
	private FinderPath _finderPathFetchByC_ERC;
	private FinderPath _finderPathCountByC_ERC;

	/**
	 * Returns the asset category where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchCategoryException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByC_ERC(long companyId,
		String externalReferenceCode) throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByC_ERC(companyId,
				externalReferenceCode);

		if (assetCategory == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", externalReferenceCode=");
			msg.append(externalReferenceCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCategoryException(msg.toString());
		}

		return assetCategory;
	}

	/**
	 * Returns the asset category where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByC_ERC(long companyId,
		String externalReferenceCode) {
		return fetchByC_ERC(companyId, externalReferenceCode, true);
	}

	/**
	 * Returns the asset category where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByC_ERC(long companyId,
		String externalReferenceCode, boolean retrieveFromCache) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		Object[] finderArgs = new Object[] { companyId, externalReferenceCode };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(_finderPathFetchByC_ERC,
					finderArgs, this);
		}

		if (result instanceof AssetCategory) {
			AssetCategory assetCategory = (AssetCategory)result;

			if ((companyId != assetCategory.getCompanyId()) ||
					!Objects.equals(externalReferenceCode,
						assetCategory.getExternalReferenceCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

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

				List<AssetCategory> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(_finderPathFetchByC_ERC,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"AssetCategoryPersistenceImpl.fetchByC_ERC(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AssetCategory assetCategory = list.get(0);

					result = assetCategory;

					cacheResult(assetCategory);
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(_finderPathFetchByC_ERC, finderArgs);

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
			return (AssetCategory)result;
		}
	}

	/**
	 * Removes the asset category where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the asset category that was removed
	 */
	@Override
	public AssetCategory removeByC_ERC(long companyId,
		String externalReferenceCode) throws NoSuchCategoryException {
		AssetCategory assetCategory = findByC_ERC(companyId,
				externalReferenceCode);

		return remove(assetCategory);
	}

	/**
	 * Returns the number of asset categories where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching asset categories
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

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

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

	private static final String _FINDER_COLUMN_C_ERC_COMPANYID_2 = "assetCategory.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 = "assetCategory.externalReferenceCode = ?";
	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 = "(assetCategory.externalReferenceCode IS NULL OR assetCategory.externalReferenceCode = '')";
	private FinderPath _finderPathFetchByHeadId;
	private FinderPath _finderPathCountByHeadId;

	/**
	 * Returns the asset category where headId = &#63; or throws a <code>NoSuchCategoryException</code> if it could not be found.
	 *
	 * @param headId the head ID
	 * @return the matching asset category
	 * @throws NoSuchCategoryException if a matching asset category could not be found
	 */
	@Override
	public AssetCategory findByHeadId(long headId)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByHeadId(headId);

		if (assetCategory == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("headId=");
			msg.append(headId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCategoryException(msg.toString());
		}

		return assetCategory;
	}

	/**
	 * Returns the asset category where headId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param headId the head ID
	 * @return the matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByHeadId(long headId) {
		return fetchByHeadId(headId, true);
	}

	/**
	 * Returns the asset category where headId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param headId the head ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching asset category, or <code>null</code> if a matching asset category could not be found
	 */
	@Override
	public AssetCategory fetchByHeadId(long headId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { headId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(_finderPathFetchByHeadId,
					finderArgs, this);
		}

		if (result instanceof AssetCategory) {
			AssetCategory assetCategory = (AssetCategory)result;

			if ((headId != assetCategory.getHeadId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ASSETCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_HEADID_HEADID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(headId);

				List<AssetCategory> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(_finderPathFetchByHeadId,
						finderArgs, list);
				}
				else {
					AssetCategory assetCategory = list.get(0);

					result = assetCategory;

					cacheResult(assetCategory);
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(_finderPathFetchByHeadId,
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
			return (AssetCategory)result;
		}
	}

	/**
	 * Removes the asset category where headId = &#63; from the database.
	 *
	 * @param headId the head ID
	 * @return the asset category that was removed
	 */
	@Override
	public AssetCategory removeByHeadId(long headId)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = findByHeadId(headId);

		return remove(assetCategory);
	}

	/**
	 * Returns the number of asset categories where headId = &#63;.
	 *
	 * @param headId the head ID
	 * @return the number of matching asset categories
	 */
	@Override
	public int countByHeadId(long headId) {
		FinderPath finderPath = _finderPathCountByHeadId;

		Object[] finderArgs = new Object[] { headId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_HEADID_HEADID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(headId);

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

	private static final String _FINDER_COLUMN_HEADID_HEADID_2 = "assetCategory.headId = ?";

	public AssetCategoryPersistenceImpl() {
		setModelClass(AssetCategory.class);

		setModelImplClass(AssetCategoryImpl.class);
		setModelPKClass(long.class);
		setEntityCacheEnabled(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED);
	}

	/**
	 * Caches the asset category in the entity cache if it is enabled.
	 *
	 * @param assetCategory the asset category
	 */
	@Override
	public void cacheResult(AssetCategory assetCategory) {
		EntityCacheUtil.putResult(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
			AssetCategoryImpl.class, assetCategory.getPrimaryKey(),
			assetCategory);

		FinderCacheUtil.putResult(_finderPathFetchByUUID_G,
			new Object[] {
				assetCategory.getUuid(), assetCategory.getGroupId(),
				assetCategory.isHead()
			}, assetCategory);

		FinderCacheUtil.putResult(_finderPathFetchByP_N_V,
			new Object[] {
				assetCategory.getParentCategoryId(), assetCategory.getName(),
				assetCategory.getVocabularyId(), assetCategory.isHead()
			}, assetCategory);

		FinderCacheUtil.putResult(_finderPathFetchByC_ERC,
			new Object[] {
				assetCategory.getCompanyId(),
				assetCategory.getExternalReferenceCode()
			}, assetCategory);

		FinderCacheUtil.putResult(_finderPathFetchByHeadId,
			new Object[] { assetCategory.getHeadId() }, assetCategory);

		assetCategory.resetOriginalValues();
	}

	/**
	 * Caches the asset categories in the entity cache if it is enabled.
	 *
	 * @param assetCategories the asset categories
	 */
	@Override
	public void cacheResult(List<AssetCategory> assetCategories) {
		for (AssetCategory assetCategory : assetCategories) {
			if (EntityCacheUtil.getResult(
						AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
						AssetCategoryImpl.class, assetCategory.getPrimaryKey()) == null) {
				cacheResult(assetCategory);
			}
			else {
				assetCategory.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset categories.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>com.liferay.portal.kernel.dao.orm.FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(AssetCategoryImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset category.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>com.liferay.portal.kernel.dao.orm.FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetCategory assetCategory) {
		EntityCacheUtil.removeResult(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
			AssetCategoryImpl.class, assetCategory.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AssetCategoryModelImpl)assetCategory, true);
	}

	@Override
	public void clearCache(List<AssetCategory> assetCategories) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetCategory assetCategory : assetCategories) {
			EntityCacheUtil.removeResult(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryImpl.class, assetCategory.getPrimaryKey());

			clearUniqueFindersCache((AssetCategoryModelImpl)assetCategory, true);
		}
	}

	protected void cacheUniqueFindersCache(
		AssetCategoryModelImpl assetCategoryModelImpl) {
		Object[] args = new Object[] {
				assetCategoryModelImpl.getUuid(),
				assetCategoryModelImpl.getGroupId(),
				assetCategoryModelImpl.isHead()
			};

		FinderCacheUtil.putResult(_finderPathCountByUUID_G, args,
			Long.valueOf(1), false);
		FinderCacheUtil.putResult(_finderPathFetchByUUID_G, args,
			assetCategoryModelImpl, false);

		args = new Object[] {
				assetCategoryModelImpl.getParentCategoryId(),
				assetCategoryModelImpl.getName(),
				assetCategoryModelImpl.getVocabularyId(),
				assetCategoryModelImpl.isHead()
			};

		FinderCacheUtil.putResult(_finderPathCountByP_N_V, args,
			Long.valueOf(1), false);
		FinderCacheUtil.putResult(_finderPathFetchByP_N_V, args,
			assetCategoryModelImpl, false);

		args = new Object[] {
				assetCategoryModelImpl.getCompanyId(),
				assetCategoryModelImpl.getExternalReferenceCode()
			};

		FinderCacheUtil.putResult(_finderPathCountByC_ERC, args,
			Long.valueOf(1), false);
		FinderCacheUtil.putResult(_finderPathFetchByC_ERC, args,
			assetCategoryModelImpl, false);

		args = new Object[] { assetCategoryModelImpl.getHeadId() };

		FinderCacheUtil.putResult(_finderPathCountByHeadId, args,
			Long.valueOf(1), false);
		FinderCacheUtil.putResult(_finderPathFetchByHeadId, args,
			assetCategoryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AssetCategoryModelImpl assetCategoryModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					assetCategoryModelImpl.getUuid(),
					assetCategoryModelImpl.getGroupId(),
					assetCategoryModelImpl.isHead()
				};

			FinderCacheUtil.removeResult(_finderPathCountByUUID_G, args);
			FinderCacheUtil.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((assetCategoryModelImpl.getColumnBitmask() &
				_finderPathFetchByUUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					assetCategoryModelImpl.getOriginalUuid(),
					assetCategoryModelImpl.getOriginalGroupId(),
					assetCategoryModelImpl.getOriginalHead()
				};

			FinderCacheUtil.removeResult(_finderPathCountByUUID_G, args);
			FinderCacheUtil.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					assetCategoryModelImpl.getParentCategoryId(),
					assetCategoryModelImpl.getName(),
					assetCategoryModelImpl.getVocabularyId(),
					assetCategoryModelImpl.isHead()
				};

			FinderCacheUtil.removeResult(_finderPathCountByP_N_V, args);
			FinderCacheUtil.removeResult(_finderPathFetchByP_N_V, args);
		}

		if ((assetCategoryModelImpl.getColumnBitmask() &
				_finderPathFetchByP_N_V.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					assetCategoryModelImpl.getOriginalParentCategoryId(),
					assetCategoryModelImpl.getOriginalName(),
					assetCategoryModelImpl.getOriginalVocabularyId(),
					assetCategoryModelImpl.getOriginalHead()
				};

			FinderCacheUtil.removeResult(_finderPathCountByP_N_V, args);
			FinderCacheUtil.removeResult(_finderPathFetchByP_N_V, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					assetCategoryModelImpl.getCompanyId(),
					assetCategoryModelImpl.getExternalReferenceCode()
				};

			FinderCacheUtil.removeResult(_finderPathCountByC_ERC, args);
			FinderCacheUtil.removeResult(_finderPathFetchByC_ERC, args);
		}

		if ((assetCategoryModelImpl.getColumnBitmask() &
				_finderPathFetchByC_ERC.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					assetCategoryModelImpl.getOriginalCompanyId(),
					assetCategoryModelImpl.getOriginalExternalReferenceCode()
				};

			FinderCacheUtil.removeResult(_finderPathCountByC_ERC, args);
			FinderCacheUtil.removeResult(_finderPathFetchByC_ERC, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { assetCategoryModelImpl.getHeadId() };

			FinderCacheUtil.removeResult(_finderPathCountByHeadId, args);
			FinderCacheUtil.removeResult(_finderPathFetchByHeadId, args);
		}

		if ((assetCategoryModelImpl.getColumnBitmask() &
				_finderPathFetchByHeadId.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					assetCategoryModelImpl.getOriginalHeadId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByHeadId, args);
			FinderCacheUtil.removeResult(_finderPathFetchByHeadId, args);
		}
	}

	/**
	 * Creates a new asset category with the primary key. Does not add the asset category to the database.
	 *
	 * @param categoryId the primary key for the new asset category
	 * @return the new asset category
	 */
	@Override
	public AssetCategory create(long categoryId) {
		AssetCategory assetCategory = new AssetCategoryImpl();

		assetCategory.setNew(true);
		assetCategory.setPrimaryKey(categoryId);

		String uuid = PortalUUIDUtil.generate();

		assetCategory.setUuid(uuid);

		assetCategory.setCompanyId(companyProvider.getCompanyId());

		return assetCategory;
	}

	/**
	 * Removes the asset category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param categoryId the primary key of the asset category
	 * @return the asset category that was removed
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory remove(long categoryId) throws NoSuchCategoryException {
		return remove((Serializable)categoryId);
	}

	/**
	 * Removes the asset category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset category
	 * @return the asset category that was removed
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory remove(Serializable primaryKey)
		throws NoSuchCategoryException {
		Session session = null;

		try {
			session = openSession();

			AssetCategory assetCategory = (AssetCategory)session.get(AssetCategoryImpl.class,
					primaryKey);

			if (assetCategory == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetCategory);
		}
		catch (NoSuchCategoryException nsee) {
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
	protected AssetCategory removeImpl(AssetCategory assetCategory) {
		assetCategoryToAssetEntryTableMapper.deleteLeftPrimaryKeyTableMappings(assetCategory.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (rebuildTreeEnabled) {
				if (session.isDirty()) {
					session.flush();
				}

				nestedSetsTreeManager.delete(assetCategory);

				clearCache();

				session.clear();
			}

			if (!session.contains(assetCategory)) {
				assetCategory = (AssetCategory)session.get(AssetCategoryImpl.class,
						assetCategory.getPrimaryKeyObj());
			}

			if (assetCategory != null) {
				session.delete(assetCategory);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (assetCategory != null) {
			clearCache(assetCategory);
		}

		return assetCategory;
	}

	@Override
	public AssetCategory updateImpl(AssetCategory assetCategory) {
		boolean isNew = assetCategory.isNew();

		if (!(assetCategory instanceof AssetCategoryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(assetCategory.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(assetCategory);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in assetCategory proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AssetCategory implementation " +
				assetCategory.getClass());
		}

		AssetCategoryModelImpl assetCategoryModelImpl = (AssetCategoryModelImpl)assetCategory;

		if (Validator.isNull(assetCategory.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			assetCategory.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (assetCategory.getCreateDate() == null)) {
			if (serviceContext == null) {
				assetCategory.setCreateDate(now);
			}
			else {
				assetCategory.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!assetCategoryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				assetCategory.setModifiedDate(now);
			}
			else {
				assetCategory.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (rebuildTreeEnabled) {
				if (session.isDirty()) {
					session.flush();
				}

				if (isNew) {
					nestedSetsTreeManager.insert(assetCategory,
						fetchByPrimaryKey(assetCategory.getParentCategoryId()));
				}
				else if (assetCategory.getParentCategoryId() != assetCategoryModelImpl.getOriginalParentCategoryId()) {
					nestedSetsTreeManager.move(assetCategory,
						fetchByPrimaryKey(
							assetCategoryModelImpl.getOriginalParentCategoryId()),
						fetchByPrimaryKey(assetCategory.getParentCategoryId()));
				}

				clearCache();

				session.clear();
			}

			if (assetCategory.isNew()) {
				session.save(assetCategory);

				assetCategory.setNew(false);
			}
			else {
				assetCategory = (AssetCategory)session.merge(assetCategory);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AssetCategoryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { assetCategoryModelImpl.getUuid() };

			FinderCacheUtil.removeResult(_finderPathCountByUuid, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid,
				args);

			args = new Object[] {
					assetCategoryModelImpl.getUuid(),
					assetCategoryModelImpl.getCompanyId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByUuid_C, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid_C,
				args);

			args = new Object[] { assetCategoryModelImpl.getGroupId() };

			FinderCacheUtil.removeResult(_finderPathCountByGroupId, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByGroupId,
				args);

			args = new Object[] { assetCategoryModelImpl.getParentCategoryId() };

			FinderCacheUtil.removeResult(_finderPathCountByParentCategoryId,
				args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByParentCategoryId,
				args);

			args = new Object[] { assetCategoryModelImpl.getVocabularyId() };

			FinderCacheUtil.removeResult(_finderPathCountByVocabularyId, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByVocabularyId,
				args);

			args = new Object[] {
					assetCategoryModelImpl.getGroupId(),
					assetCategoryModelImpl.getParentCategoryId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_P, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P,
				args);

			args = new Object[] {
					assetCategoryModelImpl.getGroupId(),
					assetCategoryModelImpl.getVocabularyId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_V, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_V,
				args);

			args = new Object[] {
					assetCategoryModelImpl.getParentCategoryId(),
					assetCategoryModelImpl.getName()
				};

			FinderCacheUtil.removeResult(_finderPathCountByP_N, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_N,
				args);

			args = new Object[] {
					assetCategoryModelImpl.getParentCategoryId(),
					assetCategoryModelImpl.getVocabularyId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByP_V, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_V,
				args);

			args = new Object[] {
					assetCategoryModelImpl.getName(),
					assetCategoryModelImpl.getVocabularyId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByN_V, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByN_V,
				args);

			args = new Object[] {
					assetCategoryModelImpl.getGroupId(),
					assetCategoryModelImpl.getParentCategoryId(),
					assetCategoryModelImpl.getVocabularyId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_P_V, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_V,
				args);

			args = new Object[] {
					assetCategoryModelImpl.getGroupId(),
					assetCategoryModelImpl.getParentCategoryId(),
					assetCategoryModelImpl.getName(),
					assetCategoryModelImpl.getVocabularyId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_P_N_V, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_N_V,
				args);

			FinderCacheUtil.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindAll,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((assetCategoryModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByUuid.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(_finderPathCountByUuid, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid,
					args);

				args = new Object[] { assetCategoryModelImpl.getUuid() };

				FinderCacheUtil.removeResult(_finderPathCountByUuid, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid,
					args);
			}

			if ((assetCategoryModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryModelImpl.getOriginalUuid(),
						assetCategoryModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByUuid_C, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid_C,
					args);

				args = new Object[] {
						assetCategoryModelImpl.getUuid(),
						assetCategoryModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByUuid_C, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid_C,
					args);
			}

			if ((assetCategoryModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByGroupId.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByGroupId, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByGroupId,
					args);

				args = new Object[] { assetCategoryModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(_finderPathCountByGroupId, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByGroupId,
					args);
			}

			if ((assetCategoryModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByParentCategoryId.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryModelImpl.getOriginalParentCategoryId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByParentCategoryId,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByParentCategoryId,
					args);

				args = new Object[] { assetCategoryModelImpl.getParentCategoryId() };

				FinderCacheUtil.removeResult(_finderPathCountByParentCategoryId,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByParentCategoryId,
					args);
			}

			if ((assetCategoryModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByVocabularyId.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryModelImpl.getOriginalVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByVocabularyId,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByVocabularyId,
					args);

				args = new Object[] { assetCategoryModelImpl.getVocabularyId() };

				FinderCacheUtil.removeResult(_finderPathCountByVocabularyId,
					args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByVocabularyId,
					args);
			}

			if ((assetCategoryModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByG_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryModelImpl.getOriginalGroupId(),
						assetCategoryModelImpl.getOriginalParentCategoryId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P,
					args);

				args = new Object[] {
						assetCategoryModelImpl.getGroupId(),
						assetCategoryModelImpl.getParentCategoryId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P,
					args);
			}

			if ((assetCategoryModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByG_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryModelImpl.getOriginalGroupId(),
						assetCategoryModelImpl.getOriginalVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_V,
					args);

				args = new Object[] {
						assetCategoryModelImpl.getGroupId(),
						assetCategoryModelImpl.getVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_V,
					args);
			}

			if ((assetCategoryModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByP_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryModelImpl.getOriginalParentCategoryId(),
						assetCategoryModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_N, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_N,
					args);

				args = new Object[] {
						assetCategoryModelImpl.getParentCategoryId(),
						assetCategoryModelImpl.getName()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_N, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_N,
					args);
			}

			if ((assetCategoryModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByP_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryModelImpl.getOriginalParentCategoryId(),
						assetCategoryModelImpl.getOriginalVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_V,
					args);

				args = new Object[] {
						assetCategoryModelImpl.getParentCategoryId(),
						assetCategoryModelImpl.getVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_V,
					args);
			}

			if ((assetCategoryModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByN_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryModelImpl.getOriginalName(),
						assetCategoryModelImpl.getOriginalVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByN_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByN_V,
					args);

				args = new Object[] {
						assetCategoryModelImpl.getName(),
						assetCategoryModelImpl.getVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByN_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByN_V,
					args);
			}

			if ((assetCategoryModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByG_P_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryModelImpl.getOriginalGroupId(),
						assetCategoryModelImpl.getOriginalParentCategoryId(),
						assetCategoryModelImpl.getOriginalVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_V,
					args);

				args = new Object[] {
						assetCategoryModelImpl.getGroupId(),
						assetCategoryModelImpl.getParentCategoryId(),
						assetCategoryModelImpl.getVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_V,
					args);
			}

			if ((assetCategoryModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByG_P_N_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetCategoryModelImpl.getOriginalGroupId(),
						assetCategoryModelImpl.getOriginalParentCategoryId(),
						assetCategoryModelImpl.getOriginalName(),
						assetCategoryModelImpl.getOriginalVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P_N_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_N_V,
					args);

				args = new Object[] {
						assetCategoryModelImpl.getGroupId(),
						assetCategoryModelImpl.getParentCategoryId(),
						assetCategoryModelImpl.getName(),
						assetCategoryModelImpl.getVocabularyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P_N_V, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_N_V,
					args);
			}
		}

		EntityCacheUtil.putResult(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
			AssetCategoryImpl.class, assetCategory.getPrimaryKey(),
			assetCategory, false);

		clearUniqueFindersCache(assetCategoryModelImpl, false);
		cacheUniqueFindersCache(assetCategoryModelImpl);

		assetCategory.resetOriginalValues();

		return assetCategory;
	}

	/**
	 * Returns the asset category with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset category
	 * @return the asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCategoryException {
		AssetCategory assetCategory = fetchByPrimaryKey(primaryKey);

		if (assetCategory == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return assetCategory;
	}

	/**
	 * Returns the asset category with the primary key or throws a <code>NoSuchCategoryException</code> if it could not be found.
	 *
	 * @param categoryId the primary key of the asset category
	 * @return the asset category
	 * @throws NoSuchCategoryException if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory findByPrimaryKey(long categoryId)
		throws NoSuchCategoryException {
		return findByPrimaryKey((Serializable)categoryId);
	}

	/**
	 * Returns the asset category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param categoryId the primary key of the asset category
	 * @return the asset category, or <code>null</code> if a asset category with the primary key could not be found
	 */
	@Override
	public AssetCategory fetchByPrimaryKey(long categoryId) {
		return fetchByPrimaryKey((Serializable)categoryId);
	}

	/**
	 * Returns all the asset categories.
	 *
	 * @return the asset categories
	 */
	@Override
	public List<AssetCategory> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of asset categories
	 */
	@Override
	public List<AssetCategory> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset categories
	 */
	@Override
	public List<AssetCategory> findAll(int start, int end,
		OrderByComparator<AssetCategory> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of asset categories
	 */
	@Override
	public List<AssetCategory> findAll(int start, int end,
		OrderByComparator<AssetCategory> orderByComparator,
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

		List<AssetCategory> list = null;

		if (retrieveFromCache) {
			list = (List<AssetCategory>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ASSETCATEGORY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETCATEGORY;

				if (pagination) {
					sql = sql.concat(AssetCategoryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetCategory>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the asset categories from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AssetCategory assetCategory : findAll()) {
			remove(assetCategory);
		}
	}

	/**
	 * Returns the number of asset categories.
	 *
	 * @return the number of asset categories
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(_finderPathCountAll,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETCATEGORY);

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
	 * Returns the primaryKeys of asset entries associated with the asset category.
	 *
	 * @param pk the primary key of the asset category
	 * @return long[] of the primaryKeys of asset entries associated with the asset category
	 */
	@Override
	public long[] getAssetEntryPrimaryKeys(long pk) {
		long[] pks = assetCategoryToAssetEntryTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the asset entries associated with the asset category.
	 *
	 * @param pk the primary key of the asset category
	 * @return the asset entries associated with the asset category
	 */
	@Override
	public List<com.liferay.asset.kernel.model.AssetEntry> getAssetEntries(
		long pk) {
		return getAssetEntries(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the asset entries associated with the asset category.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the asset category
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @return the range of asset entries associated with the asset category
	 */
	@Override
	public List<com.liferay.asset.kernel.model.AssetEntry> getAssetEntries(
		long pk, int start, int end) {
		return getAssetEntries(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entries associated with the asset category.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AssetCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the asset category
	 * @param start the lower bound of the range of asset categories
	 * @param end the upper bound of the range of asset categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset entries associated with the asset category
	 */
	@Override
	public List<com.liferay.asset.kernel.model.AssetEntry> getAssetEntries(
		long pk, int start, int end,
		OrderByComparator<com.liferay.asset.kernel.model.AssetEntry> orderByComparator) {
		return assetCategoryToAssetEntryTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of asset entries associated with the asset category.
	 *
	 * @param pk the primary key of the asset category
	 * @return the number of asset entries associated with the asset category
	 */
	@Override
	public int getAssetEntriesSize(long pk) {
		long[] pks = assetCategoryToAssetEntryTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the asset entry is associated with the asset category.
	 *
	 * @param pk the primary key of the asset category
	 * @param assetEntryPK the primary key of the asset entry
	 * @return <code>true</code> if the asset entry is associated with the asset category; <code>false</code> otherwise
	 */
	@Override
	public boolean containsAssetEntry(long pk, long assetEntryPK) {
		return assetCategoryToAssetEntryTableMapper.containsTableMapping(pk,
			assetEntryPK);
	}

	/**
	 * Returns <code>true</code> if the asset category has any asset entries associated with it.
	 *
	 * @param pk the primary key of the asset category to check for associations with asset entries
	 * @return <code>true</code> if the asset category has any asset entries associated with it; <code>false</code> otherwise
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
	 * Adds an association between the asset category and the asset entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category
	 * @param assetEntryPK the primary key of the asset entry
	 */
	@Override
	public void addAssetEntry(long pk, long assetEntryPK) {
		AssetCategory assetCategory = fetchByPrimaryKey(pk);

		if (assetCategory == null) {
			assetCategoryToAssetEntryTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, assetEntryPK);
		}
		else {
			assetCategoryToAssetEntryTableMapper.addTableMapping(assetCategory.getCompanyId(),
				pk, assetEntryPK);
		}
	}

	/**
	 * Adds an association between the asset category and the asset entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category
	 * @param assetEntry the asset entry
	 */
	@Override
	public void addAssetEntry(long pk,
		com.liferay.asset.kernel.model.AssetEntry assetEntry) {
		AssetCategory assetCategory = fetchByPrimaryKey(pk);

		if (assetCategory == null) {
			assetCategoryToAssetEntryTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, assetEntry.getPrimaryKey());
		}
		else {
			assetCategoryToAssetEntryTableMapper.addTableMapping(assetCategory.getCompanyId(),
				pk, assetEntry.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the asset category and the asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category
	 * @param assetEntryPKs the primary keys of the asset entries
	 */
	@Override
	public void addAssetEntries(long pk, long[] assetEntryPKs) {
		long companyId = 0;

		AssetCategory assetCategory = fetchByPrimaryKey(pk);

		if (assetCategory == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = assetCategory.getCompanyId();
		}

		assetCategoryToAssetEntryTableMapper.addTableMappings(companyId, pk,
			assetEntryPKs);
	}

	/**
	 * Adds an association between the asset category and the asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category
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
	 * Clears all associations between the asset category and its asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category to clear the associated asset entries from
	 */
	@Override
	public void clearAssetEntries(long pk) {
		assetCategoryToAssetEntryTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the asset category and the asset entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category
	 * @param assetEntryPK the primary key of the asset entry
	 */
	@Override
	public void removeAssetEntry(long pk, long assetEntryPK) {
		assetCategoryToAssetEntryTableMapper.deleteTableMapping(pk, assetEntryPK);
	}

	/**
	 * Removes the association between the asset category and the asset entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category
	 * @param assetEntry the asset entry
	 */
	@Override
	public void removeAssetEntry(long pk,
		com.liferay.asset.kernel.model.AssetEntry assetEntry) {
		assetCategoryToAssetEntryTableMapper.deleteTableMapping(pk,
			assetEntry.getPrimaryKey());
	}

	/**
	 * Removes the association between the asset category and the asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category
	 * @param assetEntryPKs the primary keys of the asset entries
	 */
	@Override
	public void removeAssetEntries(long pk, long[] assetEntryPKs) {
		assetCategoryToAssetEntryTableMapper.deleteTableMappings(pk,
			assetEntryPKs);
	}

	/**
	 * Removes the association between the asset category and the asset entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category
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
	 * Sets the asset entries associated with the asset category, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category
	 * @param assetEntryPKs the primary keys of the asset entries to be associated with the asset category
	 */
	@Override
	public void setAssetEntries(long pk, long[] assetEntryPKs) {
		Set<Long> newAssetEntryPKsSet = SetUtil.fromArray(assetEntryPKs);
		Set<Long> oldAssetEntryPKsSet = SetUtil.fromArray(assetCategoryToAssetEntryTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeAssetEntryPKsSet = new HashSet<Long>(oldAssetEntryPKsSet);

		removeAssetEntryPKsSet.removeAll(newAssetEntryPKsSet);

		assetCategoryToAssetEntryTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeAssetEntryPKsSet));

		newAssetEntryPKsSet.removeAll(oldAssetEntryPKsSet);

		long companyId = 0;

		AssetCategory assetCategory = fetchByPrimaryKey(pk);

		if (assetCategory == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = assetCategory.getCompanyId();
		}

		assetCategoryToAssetEntryTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newAssetEntryPKsSet));
	}

	/**
	 * Sets the asset entries associated with the asset category, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the asset category
	 * @param assetEntries the asset entries to be associated with the asset category
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
		return "categoryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ASSETCATEGORY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AssetCategoryModelImpl.TABLE_COLUMNS_MAP;
	}

	@Override
	public long countAncestors(AssetCategory assetCategory) {
		Object[] finderArgs = new Object[] {
				assetCategory.getGroupId(), assetCategory.getLeftCategoryId(),
				assetCategory.getRightCategoryId()
			};

		Long count = (Long)FinderCacheUtil.getResult(_finderPathWithPaginationCountAncestors,
				finderArgs, this);

		if (count == null) {
			try {
				count = nestedSetsTreeManager.countAncestors(assetCategory);

				FinderCacheUtil.putResult(_finderPathWithPaginationCountAncestors,
					finderArgs, count);
			}
			catch (SystemException se) {
				FinderCacheUtil.removeResult(_finderPathWithPaginationCountAncestors,
					finderArgs);

				throw se;
			}
		}

		return count.intValue();
	}

	@Override
	public long countDescendants(AssetCategory assetCategory) {
		Object[] finderArgs = new Object[] {
				assetCategory.getGroupId(), assetCategory.getLeftCategoryId(),
				assetCategory.getRightCategoryId()
			};

		Long count = (Long)FinderCacheUtil.getResult(_finderPathWithPaginationCountDescendants,
				finderArgs, this);

		if (count == null) {
			try {
				count = nestedSetsTreeManager.countDescendants(assetCategory);

				FinderCacheUtil.putResult(_finderPathWithPaginationCountDescendants,
					finderArgs, count);
			}
			catch (SystemException se) {
				FinderCacheUtil.removeResult(_finderPathWithPaginationCountDescendants,
					finderArgs);

				throw se;
			}
		}

		return count.intValue();
	}

	@Override
	public List<AssetCategory> getAncestors(AssetCategory assetCategory) {
		Object[] finderArgs = new Object[] {
				assetCategory.getGroupId(), assetCategory.getLeftCategoryId(),
				assetCategory.getRightCategoryId()
			};

		List<AssetCategory> list = (List<AssetCategory>)FinderCacheUtil.getResult(_finderPathWithPaginationGetAncestors,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetCategory tempAssetCategory : list) {
				if ((assetCategory.getLeftCategoryId() < tempAssetCategory.getLeftCategoryId()) ||
						(assetCategory.getRightCategoryId() > tempAssetCategory.getRightCategoryId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			try {
				list = nestedSetsTreeManager.getAncestors(assetCategory);

				cacheResult(list);

				FinderCacheUtil.putResult(_finderPathWithPaginationGetAncestors,
					finderArgs, list);
			}
			catch (SystemException se) {
				FinderCacheUtil.removeResult(_finderPathWithPaginationGetAncestors,
					finderArgs);

				throw se;
			}
		}

		return list;
	}

	@Override
	public List<AssetCategory> getDescendants(AssetCategory assetCategory) {
		Object[] finderArgs = new Object[] {
				assetCategory.getGroupId(), assetCategory.getLeftCategoryId(),
				assetCategory.getRightCategoryId()
			};

		List<AssetCategory> list = (List<AssetCategory>)FinderCacheUtil.getResult(_finderPathWithPaginationGetDescendants,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetCategory tempAssetCategory : list) {
				if ((assetCategory.getLeftCategoryId() > tempAssetCategory.getLeftCategoryId()) ||
						(assetCategory.getRightCategoryId() < tempAssetCategory.getRightCategoryId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			try {
				list = nestedSetsTreeManager.getDescendants(assetCategory);

				cacheResult(list);

				FinderCacheUtil.putResult(_finderPathWithPaginationGetDescendants,
					finderArgs, list);
			}
			catch (SystemException se) {
				FinderCacheUtil.removeResult(_finderPathWithPaginationGetDescendants,
					finderArgs);

				throw se;
			}
		}

		return list;
	}

	/**
	 * Rebuilds the asset categories tree for the scope using the modified pre-order tree traversal algorithm.
	 *
	 * <p>
	 * Only call this method if the tree has become stale through operations other than normal CRUD. Under normal circumstances the tree is automatically rebuilt whenver necessary.
	 * </p>
	 *
	 * @param groupId the ID of the scope
	 * @param force whether to force the rebuild even if the tree is not stale
	 */
	@Override
	public void rebuildTree(long groupId, boolean force) {
		if (!rebuildTreeEnabled) {
			return;
		}

		if (force || (countOrphanTreeNodes(groupId) > 0)) {
			Session session = null;

			try {
				session = openSession();

				if (session.isDirty()) {
					session.flush();
				}

				SQLQuery selectQuery = session.createSQLQuery(
						"SELECT categoryId FROM AssetCategory WHERE groupId = ? AND parentCategoryId = ? ORDER BY categoryId ASC");

				selectQuery.addScalar("categoryId",
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				SQLQuery updateQuery = session.createSQLQuery(
						"UPDATE AssetCategory SET leftCategoryId = ?, rightCategoryId = ? WHERE categoryId = ?");

				rebuildTree(session, selectQuery, updateQuery, groupId, 0, 0);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}

			clearCache();
		}
	}

	@Override
	public void setRebuildTreeEnabled(boolean rebuildTreeEnabled) {
		this.rebuildTreeEnabled = rebuildTreeEnabled;
	}

	protected long countOrphanTreeNodes(long groupId) {
		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(
					"SELECT COUNT(*) AS COUNT_VALUE FROM AssetCategory WHERE groupId = ? AND (leftCategoryId = 0 OR leftCategoryId IS NULL OR rightCategoryId = 0 OR rightCategoryId IS NULL)");

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (Long)q.uniqueResult();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected long rebuildTree(Session session, SQLQuery selectQuery,
		SQLQuery updateQuery, long groupId, long parentCategoryId,
		long leftCategoryId) {
		long rightCategoryId = leftCategoryId + 1;

		QueryPos qPos = QueryPos.getInstance(selectQuery);

		qPos.add(groupId);
		qPos.add(parentCategoryId);

		List<Long> categoryIds = selectQuery.list();

		for (long categoryId : categoryIds) {
			rightCategoryId = rebuildTree(session, selectQuery, updateQuery,
					groupId, categoryId, rightCategoryId);
		}

		if (parentCategoryId > 0) {
			qPos = QueryPos.getInstance(updateQuery);

			qPos.add(leftCategoryId);
			qPos.add(rightCategoryId);
			qPos.add(parentCategoryId);

			updateQuery.executeUpdate();
		}

		return rightCategoryId + 1;
	}

	/**
	 * Initializes the asset category persistence.
	 */
	public void afterPropertiesSet() {
		assetCategoryToAssetEntryTableMapper = TableMapperFactory.getTableMapper("AssetEntries_AssetCategories",
				"companyId", "categoryId", "entryId", this,
				assetEntryPersistence);

		_finderPathWithPaginationFindAll = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
				new String[0]);

		_finderPathCountAll = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
				new String[0]);

		_finderPathWithPaginationCountAncestors = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countAncestors",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName()
				});

		_finderPathWithPaginationCountDescendants = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countDescendants",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName()
				});

		_finderPathWithPaginationGetAncestors = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "getAncestors",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName()
				});

		_finderPathWithPaginationGetDescendants = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "getDescendants",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName()
				});

		_finderPathWithPaginationFindByUuid = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
				new String[] {
					String.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
				new String[] { String.class.getName() },
				AssetCategoryModelImpl.UUID_COLUMN_BITMASK |
				AssetCategoryModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
				new String[] { String.class.getName() });

		_finderPathFetchByUUID_G = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class, FINDER_CLASS_NAME_ENTITY,
				"fetchByUUID_G",
				new String[] {
					String.class.getName(), Long.class.getName(),
					Boolean.class.getName()
				},
				AssetCategoryModelImpl.UUID_COLUMN_BITMASK |
				AssetCategoryModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryModelImpl.HEAD_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
				new String[] {
					String.class.getName(), Long.class.getName(),
					Boolean.class.getName()
				});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
				new String[] {
					String.class.getName(), Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
				new String[] { String.class.getName(), Long.class.getName() },
				AssetCategoryModelImpl.UUID_COLUMN_BITMASK |
				AssetCategoryModelImpl.COMPANYID_COLUMN_BITMASK |
				AssetCategoryModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
				new String[] { String.class.getName(), Long.class.getName() });

		_finderPathWithPaginationFindByGroupId = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
				new String[] {
					Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
				new String[] { Long.class.getName() },
				AssetCategoryModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
				new String[] { Long.class.getName() });

		_finderPathWithPaginationFindByParentCategoryId = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByParentCategoryId",
				new String[] {
					Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByParentCategoryId = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByParentCategoryId",
				new String[] { Long.class.getName() },
				AssetCategoryModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByParentCategoryId = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByParentCategoryId", new String[] { Long.class.getName() });

		_finderPathWithPaginationFindByVocabularyId = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByVocabularyId",
				new String[] {
					Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByVocabularyId = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByVocabularyId", new String[] { Long.class.getName() },
				AssetCategoryModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByVocabularyId = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByVocabularyId", new String[] { Long.class.getName() });

		_finderPathWithPaginationFindByG_P = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByG_P = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P",
				new String[] { Long.class.getName(), Long.class.getName() },
				AssetCategoryModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByG_P = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P",
				new String[] { Long.class.getName(), Long.class.getName() });

		_finderPathWithPaginationFindByG_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_V",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByG_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_V",
				new String[] { Long.class.getName(), Long.class.getName() },
				AssetCategoryModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByG_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_V",
				new String[] { Long.class.getName(), Long.class.getName() });

		_finderPathWithPaginationCountByG_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_V",
				new String[] { Long.class.getName(), Long.class.getName() });

		_finderPathWithPaginationFindByP_N = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_N",
				new String[] {
					Long.class.getName(), String.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByP_N = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_N",
				new String[] { Long.class.getName(), String.class.getName() },
				AssetCategoryModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByP_N = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_N",
				new String[] { Long.class.getName(), String.class.getName() });

		_finderPathWithPaginationFindByP_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_V",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByP_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_V",
				new String[] { Long.class.getName(), Long.class.getName() },
				AssetCategoryModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByP_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_V",
				new String[] { Long.class.getName(), Long.class.getName() });

		_finderPathWithPaginationFindByN_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByN_V",
				new String[] {
					String.class.getName(), Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByN_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByN_V",
				new String[] { String.class.getName(), Long.class.getName() },
				AssetCategoryModelImpl.NAME_COLUMN_BITMASK |
				AssetCategoryModelImpl.VOCABULARYID_COLUMN_BITMASK);

		_finderPathCountByN_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByN_V",
				new String[] { String.class.getName(), Long.class.getName() });

		_finderPathWithPaginationFindByG_P_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_V",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByG_P_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P_V",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName()
				},
				AssetCategoryModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByG_P_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_V",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName()
				});

		_finderPathWithPaginationFindByG_LikeN_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_LikeN_V",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithPaginationCountByG_LikeN_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_LikeN_V",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Long.class.getName()
				});

		_finderPathFetchByP_N_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class, FINDER_CLASS_NAME_ENTITY,
				"fetchByP_N_V",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Long.class.getName(), Boolean.class.getName()
				},
				AssetCategoryModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryModelImpl.NAME_COLUMN_BITMASK |
				AssetCategoryModelImpl.VOCABULARYID_COLUMN_BITMASK |
				AssetCategoryModelImpl.HEAD_COLUMN_BITMASK);

		_finderPathCountByP_N_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_N_V",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Long.class.getName(), Boolean.class.getName()
				});

		_finderPathWithPaginationFindByG_P_N_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_N_V",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					String.class.getName(), Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByG_P_N_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P_N_V",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					String.class.getName(), Long.class.getName()
				},
				AssetCategoryModelImpl.GROUPID_COLUMN_BITMASK |
				AssetCategoryModelImpl.PARENTCATEGORYID_COLUMN_BITMASK |
				AssetCategoryModelImpl.NAME_COLUMN_BITMASK |
				AssetCategoryModelImpl.VOCABULARYID_COLUMN_BITMASK);

		_finderPathCountByG_P_N_V = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_N_V",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					String.class.getName(), Long.class.getName()
				});

		_finderPathFetchByC_ERC = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class, FINDER_CLASS_NAME_ENTITY,
				"fetchByC_ERC",
				new String[] { Long.class.getName(), String.class.getName() },
				AssetCategoryModelImpl.COMPANYID_COLUMN_BITMASK |
				AssetCategoryModelImpl.EXTERNALREFERENCECODE_COLUMN_BITMASK);

		_finderPathCountByC_ERC = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ERC",
				new String[] { Long.class.getName(), String.class.getName() });

		_finderPathFetchByHeadId = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED,
				AssetCategoryImpl.class, FINDER_CLASS_NAME_ENTITY,
				"fetchByHeadId", new String[] { Long.class.getName() },
				AssetCategoryModelImpl.HEADID_COLUMN_BITMASK);

		_finderPathCountByHeadId = new FinderPath(AssetCategoryModelImpl.ENTITY_CACHE_ENABLED,
				AssetCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByHeadId",
				new String[] { Long.class.getName() });
	}

	public void destroy() {
		EntityCacheUtil.removeCache(AssetCategoryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper("AssetEntries_AssetCategories");
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;
	protected TableMapper<AssetCategory, com.liferay.asset.kernel.model.AssetEntry> assetCategoryToAssetEntryTableMapper;
	protected NestedSetsTreeManager<AssetCategory> nestedSetsTreeManager = new PersistenceNestedSetsTreeManager<AssetCategory>(this,
			"AssetCategory", "AssetCategory", AssetCategoryImpl.class,
			"categoryId", "groupId", "leftCategoryId", "rightCategoryId");
	protected boolean rebuildTreeEnabled = true;
	private static final String _SQL_SELECT_ASSETCATEGORY = "SELECT assetCategory FROM AssetCategory assetCategory";
	private static final String _SQL_SELECT_ASSETCATEGORY_WHERE = "SELECT assetCategory FROM AssetCategory assetCategory WHERE ";
	private static final String _SQL_COUNT_ASSETCATEGORY = "SELECT COUNT(assetCategory) FROM AssetCategory assetCategory";
	private static final String _SQL_COUNT_ASSETCATEGORY_WHERE = "SELECT COUNT(assetCategory) FROM AssetCategory assetCategory WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "assetCategory.categoryId";
	private static final String _FILTER_SQL_SELECT_ASSETCATEGORY_WHERE = "SELECT DISTINCT {assetCategory.*} FROM AssetCategory assetCategory WHERE ";
	private static final String _FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {AssetCategory.*} FROM (SELECT DISTINCT assetCategory.categoryId FROM AssetCategory assetCategory WHERE ";
	private static final String _FILTER_SQL_SELECT_ASSETCATEGORY_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN AssetCategory ON TEMP_TABLE.categoryId = AssetCategory.categoryId";
	private static final String _FILTER_SQL_COUNT_ASSETCATEGORY_WHERE = "SELECT COUNT(DISTINCT assetCategory.categoryId) AS COUNT_VALUE FROM AssetCategory assetCategory WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "assetCategory";
	private static final String _FILTER_ENTITY_TABLE = "AssetCategory";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetCategory.";
	private static final String _ORDER_BY_ENTITY_TABLE = "AssetCategory.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetCategory exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetCategory exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AssetCategoryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}