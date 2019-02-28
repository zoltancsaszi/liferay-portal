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

package com.liferay.portal.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

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
import com.liferay.portal.kernel.exception.NoSuchLayoutFriendlyURLException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutFriendlyURL;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.LayoutFriendlyURLPersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.impl.LayoutFriendlyURLImpl;
import com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the layout friendly url service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class LayoutFriendlyURLPersistenceImpl extends BasePersistenceImpl<LayoutFriendlyURL>
	implements LayoutFriendlyURLPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LayoutFriendlyURLUtil</code> to access the layout friendly url persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LayoutFriendlyURLImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the layout friendly urls where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByUuid(String uuid, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByUuid(String uuid, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
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

		List<LayoutFriendlyURL> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if (!uuid.equals(layoutFriendlyURL.getUuid())) {
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

			query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

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
				query.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
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
					list = (List<LayoutFriendlyURL>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutFriendlyURL>)QueryUtil.list(q,
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
	 * Returns the first layout friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByUuid_First(String uuid,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByUuid_First(uuid,
				orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchLayoutFriendlyURLException(msg.toString());
	}

	/**
	 * Returns the first layout friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByUuid_First(String uuid,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		List<LayoutFriendlyURL> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByUuid_Last(String uuid,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByUuid_Last(uuid,
				orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchLayoutFriendlyURLException(msg.toString());
	}

	/**
	 * Returns the last layout friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByUuid_Last(String uuid,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<LayoutFriendlyURL> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout friendly urls before and after the current layout friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param layoutFriendlyURLId the primary key of the current layout friendly url
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL[] findByUuid_PrevAndNext(
		long layoutFriendlyURLId, String uuid,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		uuid = Objects.toString(uuid, "");

		LayoutFriendlyURL layoutFriendlyURL = findByPrimaryKey(layoutFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL[] array = new LayoutFriendlyURLImpl[3];

			array[0] = getByUuid_PrevAndNext(session, layoutFriendlyURL, uuid,
					orderByComparator, true);

			array[1] = layoutFriendlyURL;

			array[2] = getByUuid_PrevAndNext(session, layoutFriendlyURL, uuid,
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

	protected LayoutFriendlyURL getByUuid_PrevAndNext(Session session,
		LayoutFriendlyURL layoutFriendlyURL, String uuid,
		OrderByComparator<LayoutFriendlyURL> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

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
			query.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
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
					layoutFriendlyURL)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<LayoutFriendlyURL> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout friendly urls where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (LayoutFriendlyURL layoutFriendlyURL : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching layout friendly urls
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

			query.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "layoutFriendlyURL.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(layoutFriendlyURL.uuid IS NULL OR layoutFriendlyURL.uuid = '')";
	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the layout friendly url where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchLayoutFriendlyURLException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByUUID_G(String uuid, long groupId)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByUUID_G(uuid, groupId);

		if (layoutFriendlyURL == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLayoutFriendlyURLException(msg.toString());
		}

		return layoutFriendlyURL;
	}

	/**
	 * Returns the layout friendly url where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the layout friendly url where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(_finderPathFetchByUUID_G,
					finderArgs, this);
		}

		if (result instanceof LayoutFriendlyURL) {
			LayoutFriendlyURL layoutFriendlyURL = (LayoutFriendlyURL)result;

			if (!Objects.equals(uuid, layoutFriendlyURL.getUuid()) ||
					(groupId != layoutFriendlyURL.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

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

				List<LayoutFriendlyURL> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(_finderPathFetchByUUID_G,
						finderArgs, list);
				}
				else {
					LayoutFriendlyURL layoutFriendlyURL = list.get(0);

					result = layoutFriendlyURL;

					cacheResult(layoutFriendlyURL);
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
			return (LayoutFriendlyURL)result;
		}
	}

	/**
	 * Removes the layout friendly url where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the layout friendly url that was removed
	 */
	@Override
	public LayoutFriendlyURL removeByUUID_G(String uuid, long groupId)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = findByUUID_G(uuid, groupId);

		return remove(layoutFriendlyURL);
	}

	/**
	 * Returns the number of layout friendly urls where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching layout friendly urls
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

			query.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "layoutFriendlyURL.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(layoutFriendlyURL.uuid IS NULL OR layoutFriendlyURL.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "layoutFriendlyURL.groupId = ?";
	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the layout friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
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

		List<LayoutFriendlyURL> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if (!uuid.equals(layoutFriendlyURL.getUuid()) ||
							(companyId != layoutFriendlyURL.getCompanyId())) {
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

			query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

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
				query.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
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
					list = (List<LayoutFriendlyURL>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutFriendlyURL>)QueryUtil.list(q,
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
	 * Returns the first layout friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchLayoutFriendlyURLException(msg.toString());
	}

	/**
	 * Returns the first layout friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		List<LayoutFriendlyURL> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchLayoutFriendlyURLException(msg.toString());
	}

	/**
	 * Returns the last layout friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<LayoutFriendlyURL> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout friendly urls before and after the current layout friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param layoutFriendlyURLId the primary key of the current layout friendly url
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL[] findByUuid_C_PrevAndNext(
		long layoutFriendlyURLId, String uuid, long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		uuid = Objects.toString(uuid, "");

		LayoutFriendlyURL layoutFriendlyURL = findByPrimaryKey(layoutFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL[] array = new LayoutFriendlyURLImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, layoutFriendlyURL,
					uuid, companyId, orderByComparator, true);

			array[1] = layoutFriendlyURL;

			array[2] = getByUuid_C_PrevAndNext(session, layoutFriendlyURL,
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

	protected LayoutFriendlyURL getByUuid_C_PrevAndNext(Session session,
		LayoutFriendlyURL layoutFriendlyURL, String uuid, long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

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
			query.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
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
					layoutFriendlyURL)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<LayoutFriendlyURL> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout friendly urls where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (LayoutFriendlyURL layoutFriendlyURL : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching layout friendly urls
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

			query.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "layoutFriendlyURL.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(layoutFriendlyURL.uuid IS NULL OR layoutFriendlyURL.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "layoutFriendlyURL.companyId = ?";
	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the layout friendly urls where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByGroupId(long groupId, int start,
		int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByGroupId(long groupId, int start,
		int end, OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByGroupId(long groupId, int start,
		int end, OrderByComparator<LayoutFriendlyURL> orderByComparator,
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

		List<LayoutFriendlyURL> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if ((groupId != layoutFriendlyURL.getGroupId())) {
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

			query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<LayoutFriendlyURL>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutFriendlyURL>)QueryUtil.list(q,
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
	 * Returns the first layout friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByGroupId_First(long groupId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByGroupId_First(groupId,
				orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchLayoutFriendlyURLException(msg.toString());
	}

	/**
	 * Returns the first layout friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByGroupId_First(long groupId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		List<LayoutFriendlyURL> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByGroupId_Last(long groupId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchLayoutFriendlyURLException(msg.toString());
	}

	/**
	 * Returns the last layout friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByGroupId_Last(long groupId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<LayoutFriendlyURL> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout friendly urls before and after the current layout friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param layoutFriendlyURLId the primary key of the current layout friendly url
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL[] findByGroupId_PrevAndNext(
		long layoutFriendlyURLId, long groupId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = findByPrimaryKey(layoutFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL[] array = new LayoutFriendlyURLImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, layoutFriendlyURL,
					groupId, orderByComparator, true);

			array[1] = layoutFriendlyURL;

			array[2] = getByGroupId_PrevAndNext(session, layoutFriendlyURL,
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

	protected LayoutFriendlyURL getByGroupId_PrevAndNext(Session session,
		LayoutFriendlyURL layoutFriendlyURL, long groupId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

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
			query.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					layoutFriendlyURL)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<LayoutFriendlyURL> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout friendly urls where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (LayoutFriendlyURL layoutFriendlyURL : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "layoutFriendlyURL.groupId = ?";
	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the layout friendly urls where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the layout friendly urls where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByCompanyId(long companyId, int start,
		int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<LayoutFriendlyURL> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCompanyId;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<LayoutFriendlyURL> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if ((companyId != layoutFriendlyURL.getCompanyId())) {
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

			query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<LayoutFriendlyURL>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutFriendlyURL>)QueryUtil.list(q,
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
	 * Returns the first layout friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByCompanyId_First(long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchLayoutFriendlyURLException(msg.toString());
	}

	/**
	 * Returns the first layout friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByCompanyId_First(long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		List<LayoutFriendlyURL> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByCompanyId_Last(long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchLayoutFriendlyURLException(msg.toString());
	}

	/**
	 * Returns the last layout friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByCompanyId_Last(long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<LayoutFriendlyURL> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout friendly urls before and after the current layout friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param layoutFriendlyURLId the primary key of the current layout friendly url
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL[] findByCompanyId_PrevAndNext(
		long layoutFriendlyURLId, long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = findByPrimaryKey(layoutFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL[] array = new LayoutFriendlyURLImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, layoutFriendlyURL,
					companyId, orderByComparator, true);

			array[1] = layoutFriendlyURL;

			array[2] = getByCompanyId_PrevAndNext(session, layoutFriendlyURL,
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

	protected LayoutFriendlyURL getByCompanyId_PrevAndNext(Session session,
		LayoutFriendlyURL layoutFriendlyURL, long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					layoutFriendlyURL)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<LayoutFriendlyURL> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout friendly urls where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (LayoutFriendlyURL layoutFriendlyURL : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "layoutFriendlyURL.companyId = ?";
	private FinderPath _finderPathWithPaginationFindByPlid;
	private FinderPath _finderPathWithoutPaginationFindByPlid;
	private FinderPath _finderPathCountByPlid;

	/**
	 * Returns all the layout friendly urls where plid = &#63;.
	 *
	 * @param plid the plid
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByPlid(long plid) {
		return findByPlid(plid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param plid the plid
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByPlid(long plid, int start, int end) {
		return findByPlid(plid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param plid the plid
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByPlid(long plid, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		return findByPlid(plid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param plid the plid
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByPlid(long plid, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByPlid;
			finderArgs = new Object[] { plid };
		}
		else {
			finderPath = _finderPathWithPaginationFindByPlid;
			finderArgs = new Object[] { plid, start, end, orderByComparator };
		}

		List<LayoutFriendlyURL> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if ((plid != layoutFriendlyURL.getPlid())) {
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

			query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

			query.append(_FINDER_COLUMN_PLID_PLID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(plid);

				if (!pagination) {
					list = (List<LayoutFriendlyURL>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutFriendlyURL>)QueryUtil.list(q,
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
	 * Returns the first layout friendly url in the ordered set where plid = &#63;.
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByPlid_First(long plid,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByPlid_First(plid,
				orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("plid=");
		msg.append(plid);

		msg.append("}");

		throw new NoSuchLayoutFriendlyURLException(msg.toString());
	}

	/**
	 * Returns the first layout friendly url in the ordered set where plid = &#63;.
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByPlid_First(long plid,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		List<LayoutFriendlyURL> list = findByPlid(plid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout friendly url in the ordered set where plid = &#63;.
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByPlid_Last(long plid,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByPlid_Last(plid,
				orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("plid=");
		msg.append(plid);

		msg.append("}");

		throw new NoSuchLayoutFriendlyURLException(msg.toString());
	}

	/**
	 * Returns the last layout friendly url in the ordered set where plid = &#63;.
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByPlid_Last(long plid,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		int count = countByPlid(plid);

		if (count == 0) {
			return null;
		}

		List<LayoutFriendlyURL> list = findByPlid(plid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout friendly urls before and after the current layout friendly url in the ordered set where plid = &#63;.
	 *
	 * @param layoutFriendlyURLId the primary key of the current layout friendly url
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL[] findByPlid_PrevAndNext(
		long layoutFriendlyURLId, long plid,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = findByPrimaryKey(layoutFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL[] array = new LayoutFriendlyURLImpl[3];

			array[0] = getByPlid_PrevAndNext(session, layoutFriendlyURL, plid,
					orderByComparator, true);

			array[1] = layoutFriendlyURL;

			array[2] = getByPlid_PrevAndNext(session, layoutFriendlyURL, plid,
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

	protected LayoutFriendlyURL getByPlid_PrevAndNext(Session session,
		LayoutFriendlyURL layoutFriendlyURL, long plid,
		OrderByComparator<LayoutFriendlyURL> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

		query.append(_FINDER_COLUMN_PLID_PLID_2);

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
			query.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(plid);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					layoutFriendlyURL)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<LayoutFriendlyURL> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout friendly urls where plid = &#63; from the database.
	 *
	 * @param plid the plid
	 */
	@Override
	public void removeByPlid(long plid) {
		for (LayoutFriendlyURL layoutFriendlyURL : findByPlid(plid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls where plid = &#63;.
	 *
	 * @param plid the plid
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByPlid(long plid) {
		FinderPath finderPath = _finderPathCountByPlid;

		Object[] finderArgs = new Object[] { plid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

			query.append(_FINDER_COLUMN_PLID_PLID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(plid);

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

	private static final String _FINDER_COLUMN_PLID_PLID_2 = "layoutFriendlyURL.plid = ?";
	private FinderPath _finderPathWithPaginationFindByP_F;
	private FinderPath _finderPathWithoutPaginationFindByP_F;
	private FinderPath _finderPathCountByP_F;

	/**
	 * Returns all the layout friendly urls where plid = &#63; and friendlyURL = &#63;.
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByP_F(long plid, String friendlyURL) {
		return findByP_F(plid, friendlyURL, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls where plid = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByP_F(long plid, String friendlyURL,
		int start, int end) {
		return findByP_F(plid, friendlyURL, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where plid = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByP_F(long plid, String friendlyURL,
		int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		return findByP_F(plid, friendlyURL, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where plid = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByP_F(long plid, String friendlyURL,
		int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
		boolean retrieveFromCache) {
		friendlyURL = Objects.toString(friendlyURL, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByP_F;
			finderArgs = new Object[] { plid, friendlyURL };
		}
		else {
			finderPath = _finderPathWithPaginationFindByP_F;
			finderArgs = new Object[] {
					plid, friendlyURL,
					
					start, end, orderByComparator
				};
		}

		List<LayoutFriendlyURL> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if ((plid != layoutFriendlyURL.getPlid()) ||
							!friendlyURL.equals(
								layoutFriendlyURL.getFriendlyURL())) {
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

			query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

			query.append(_FINDER_COLUMN_P_F_PLID_2);

			boolean bindFriendlyURL = false;

			if (friendlyURL.isEmpty()) {
				query.append(_FINDER_COLUMN_P_F_FRIENDLYURL_3);
			}
			else {
				bindFriendlyURL = true;

				query.append(_FINDER_COLUMN_P_F_FRIENDLYURL_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(plid);

				if (bindFriendlyURL) {
					qPos.add(friendlyURL);
				}

				if (!pagination) {
					list = (List<LayoutFriendlyURL>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutFriendlyURL>)QueryUtil.list(q,
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
	 * Returns the first layout friendly url in the ordered set where plid = &#63; and friendlyURL = &#63;.
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByP_F_First(long plid, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByP_F_First(plid,
				friendlyURL, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("plid=");
		msg.append(plid);

		msg.append(", friendlyURL=");
		msg.append(friendlyURL);

		msg.append("}");

		throw new NoSuchLayoutFriendlyURLException(msg.toString());
	}

	/**
	 * Returns the first layout friendly url in the ordered set where plid = &#63; and friendlyURL = &#63;.
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByP_F_First(long plid, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		List<LayoutFriendlyURL> list = findByP_F(plid, friendlyURL, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout friendly url in the ordered set where plid = &#63; and friendlyURL = &#63;.
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByP_F_Last(long plid, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByP_F_Last(plid,
				friendlyURL, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("plid=");
		msg.append(plid);

		msg.append(", friendlyURL=");
		msg.append(friendlyURL);

		msg.append("}");

		throw new NoSuchLayoutFriendlyURLException(msg.toString());
	}

	/**
	 * Returns the last layout friendly url in the ordered set where plid = &#63; and friendlyURL = &#63;.
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByP_F_Last(long plid, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		int count = countByP_F(plid, friendlyURL);

		if (count == 0) {
			return null;
		}

		List<LayoutFriendlyURL> list = findByP_F(plid, friendlyURL, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout friendly urls before and after the current layout friendly url in the ordered set where plid = &#63; and friendlyURL = &#63;.
	 *
	 * @param layoutFriendlyURLId the primary key of the current layout friendly url
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL[] findByP_F_PrevAndNext(long layoutFriendlyURLId,
		long plid, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		friendlyURL = Objects.toString(friendlyURL, "");

		LayoutFriendlyURL layoutFriendlyURL = findByPrimaryKey(layoutFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL[] array = new LayoutFriendlyURLImpl[3];

			array[0] = getByP_F_PrevAndNext(session, layoutFriendlyURL, plid,
					friendlyURL, orderByComparator, true);

			array[1] = layoutFriendlyURL;

			array[2] = getByP_F_PrevAndNext(session, layoutFriendlyURL, plid,
					friendlyURL, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutFriendlyURL getByP_F_PrevAndNext(Session session,
		LayoutFriendlyURL layoutFriendlyURL, long plid, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

		query.append(_FINDER_COLUMN_P_F_PLID_2);

		boolean bindFriendlyURL = false;

		if (friendlyURL.isEmpty()) {
			query.append(_FINDER_COLUMN_P_F_FRIENDLYURL_3);
		}
		else {
			bindFriendlyURL = true;

			query.append(_FINDER_COLUMN_P_F_FRIENDLYURL_2);
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
			query.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(plid);

		if (bindFriendlyURL) {
			qPos.add(friendlyURL);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					layoutFriendlyURL)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<LayoutFriendlyURL> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout friendly urls where plid = &#63; and friendlyURL = &#63; from the database.
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 */
	@Override
	public void removeByP_F(long plid, String friendlyURL) {
		for (LayoutFriendlyURL layoutFriendlyURL : findByP_F(plid, friendlyURL,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls where plid = &#63; and friendlyURL = &#63;.
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByP_F(long plid, String friendlyURL) {
		friendlyURL = Objects.toString(friendlyURL, "");

		FinderPath finderPath = _finderPathCountByP_F;

		Object[] finderArgs = new Object[] { plid, friendlyURL };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

			query.append(_FINDER_COLUMN_P_F_PLID_2);

			boolean bindFriendlyURL = false;

			if (friendlyURL.isEmpty()) {
				query.append(_FINDER_COLUMN_P_F_FRIENDLYURL_3);
			}
			else {
				bindFriendlyURL = true;

				query.append(_FINDER_COLUMN_P_F_FRIENDLYURL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(plid);

				if (bindFriendlyURL) {
					qPos.add(friendlyURL);
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

	private static final String _FINDER_COLUMN_P_F_PLID_2 = "layoutFriendlyURL.plid = ? AND ";
	private static final String _FINDER_COLUMN_P_F_FRIENDLYURL_2 = "layoutFriendlyURL.friendlyURL = ?";
	private static final String _FINDER_COLUMN_P_F_FRIENDLYURL_3 = "(layoutFriendlyURL.friendlyURL IS NULL OR layoutFriendlyURL.friendlyURL = '')";
	private FinderPath _finderPathWithPaginationFindByP_L;
	private FinderPath _finderPathWithoutPaginationFindByP_L;
	private FinderPath _finderPathFetchByP_L;
	private FinderPath _finderPathCountByP_L;
	private FinderPath _finderPathWithPaginationCountByP_L;

	/**
	 * Returns all the layout friendly urls where plid = any &#63; and languageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param plids the plids
	 * @param languageId the language ID
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByP_L(long[] plids, String languageId) {
		return findByP_L(plids, languageId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls where plid = any &#63; and languageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param plids the plids
	 * @param languageId the language ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByP_L(long[] plids, String languageId,
		int start, int end) {
		return findByP_L(plids, languageId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where plid = any &#63; and languageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param plids the plids
	 * @param languageId the language ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByP_L(long[] plids, String languageId,
		int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		return findByP_L(plids, languageId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where plid = &#63; and languageId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param plid the plid
	 * @param languageId the language ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByP_L(long[] plids, String languageId,
		int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
		boolean retrieveFromCache) {
		if (plids == null) {
			plids = new long[0];
		}
		else if (plids.length > 1) {
			plids = ArrayUtil.unique(plids);

			Arrays.sort(plids);
		}

		languageId = Objects.toString(languageId, "");

		if (plids.length == 1) {
			LayoutFriendlyURL layoutFriendlyURL = fetchByP_L(plids[0],
					languageId);

			if (layoutFriendlyURL == null) {
				return Collections.emptyList();
			}
			else {
				return Collections.singletonList(layoutFriendlyURL);
			}
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(plids), languageId };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(plids), languageId,
					
					start, end, orderByComparator
				};
		}

		List<LayoutFriendlyURL> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(_finderPathWithPaginationFindByP_L,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if (!ArrayUtil.contains(plids, layoutFriendlyURL.getPlid()) ||
							!languageId.equals(
								layoutFriendlyURL.getLanguageId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			try {
				if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
						(databaseInMaxParameters > 0) &&
						(plids.length > databaseInMaxParameters)) {
					list = new ArrayList<LayoutFriendlyURL>();

					long[][] plidsPages = (long[][])ArrayUtil.split(plids,
							databaseInMaxParameters);

					for (long[] plidsPage : plidsPages) {
						list.addAll(_findByP_L(plidsPage, languageId, start,
								end, orderByComparator, pagination));
					}

					Collections.sort(list, orderByComparator);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = _findByP_L(plids, languageId, start, end,
							orderByComparator, pagination);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(_finderPathWithPaginationFindByP_L,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(_finderPathWithPaginationFindByP_L,
					finderArgs);

				throw processException(e);
			}
		}

		return list;
	}

	private List<LayoutFriendlyURL> _findByP_L(long[] plids, String languageId,
		int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
		boolean pagination) {
		List<LayoutFriendlyURL> list = null;

		StringBundler query = new StringBundler();

		query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

		if (plids.length > 0) {
			query.append("(");

			query.append(_FINDER_COLUMN_P_L_PLID_7);

			query.append(StringUtil.merge(plids));

			query.append(")");

			query.append(")");

			query.append(WHERE_AND);
		}

		boolean bindLanguageId = false;

		if (languageId.isEmpty()) {
			query.append(_FINDER_COLUMN_P_L_LANGUAGEID_3);
		}
		else {
			bindLanguageId = true;

			query.append(_FINDER_COLUMN_P_L_LANGUAGEID_2);
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (orderByComparator != null) {
			appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
				orderByComparator);
		}
		else
		 if (pagination) {
			query.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindLanguageId) {
				qPos.add(languageId);
			}

			if (!pagination) {
				list = (List<LayoutFriendlyURL>)QueryUtil.list(q, getDialect(),
						start, end, false);

				Collections.sort(list);

				list = Collections.unmodifiableList(list);
			}
			else {
				list = (List<LayoutFriendlyURL>)QueryUtil.list(q, getDialect(),
						start, end);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return list;
	}

	/**
	 * Returns the layout friendly url where plid = &#63; and languageId = &#63; or throws a <code>NoSuchLayoutFriendlyURLException</code> if it could not be found.
	 *
	 * @param plid the plid
	 * @param languageId the language ID
	 * @return the matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByP_L(long plid, String languageId)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByP_L(plid, languageId);

		if (layoutFriendlyURL == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("plid=");
			msg.append(plid);

			msg.append(", languageId=");
			msg.append(languageId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLayoutFriendlyURLException(msg.toString());
		}

		return layoutFriendlyURL;
	}

	/**
	 * Returns the layout friendly url where plid = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param plid the plid
	 * @param languageId the language ID
	 * @return the matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByP_L(long plid, String languageId) {
		return fetchByP_L(plid, languageId, true);
	}

	/**
	 * Returns the layout friendly url where plid = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param plid the plid
	 * @param languageId the language ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByP_L(long plid, String languageId,
		boolean retrieveFromCache) {
		languageId = Objects.toString(languageId, "");

		Object[] finderArgs = new Object[] { plid, languageId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(_finderPathFetchByP_L,
					finderArgs, this);
		}

		if (result instanceof LayoutFriendlyURL) {
			LayoutFriendlyURL layoutFriendlyURL = (LayoutFriendlyURL)result;

			if ((plid != layoutFriendlyURL.getPlid()) ||
					!Objects.equals(languageId,
						layoutFriendlyURL.getLanguageId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

			query.append(_FINDER_COLUMN_P_L_PLID_2);

			boolean bindLanguageId = false;

			if (languageId.isEmpty()) {
				query.append(_FINDER_COLUMN_P_L_LANGUAGEID_3);
			}
			else {
				bindLanguageId = true;

				query.append(_FINDER_COLUMN_P_L_LANGUAGEID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(plid);

				if (bindLanguageId) {
					qPos.add(languageId);
				}

				List<LayoutFriendlyURL> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(_finderPathFetchByP_L,
						finderArgs, list);
				}
				else {
					LayoutFriendlyURL layoutFriendlyURL = list.get(0);

					result = layoutFriendlyURL;

					cacheResult(layoutFriendlyURL);
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(_finderPathFetchByP_L, finderArgs);

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
			return (LayoutFriendlyURL)result;
		}
	}

	/**
	 * Removes the layout friendly url where plid = &#63; and languageId = &#63; from the database.
	 *
	 * @param plid the plid
	 * @param languageId the language ID
	 * @return the layout friendly url that was removed
	 */
	@Override
	public LayoutFriendlyURL removeByP_L(long plid, String languageId)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = findByP_L(plid, languageId);

		return remove(layoutFriendlyURL);
	}

	/**
	 * Returns the number of layout friendly urls where plid = &#63; and languageId = &#63;.
	 *
	 * @param plid the plid
	 * @param languageId the language ID
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByP_L(long plid, String languageId) {
		languageId = Objects.toString(languageId, "");

		FinderPath finderPath = _finderPathCountByP_L;

		Object[] finderArgs = new Object[] { plid, languageId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

			query.append(_FINDER_COLUMN_P_L_PLID_2);

			boolean bindLanguageId = false;

			if (languageId.isEmpty()) {
				query.append(_FINDER_COLUMN_P_L_LANGUAGEID_3);
			}
			else {
				bindLanguageId = true;

				query.append(_FINDER_COLUMN_P_L_LANGUAGEID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(plid);

				if (bindLanguageId) {
					qPos.add(languageId);
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

	/**
	 * Returns the number of layout friendly urls where plid = any &#63; and languageId = &#63;.
	 *
	 * @param plids the plids
	 * @param languageId the language ID
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByP_L(long[] plids, String languageId) {
		if (plids == null) {
			plids = new long[0];
		}
		else if (plids.length > 1) {
			plids = ArrayUtil.unique(plids);

			Arrays.sort(plids);
		}

		languageId = Objects.toString(languageId, "");

		Object[] finderArgs = new Object[] { StringUtil.merge(plids), languageId };

		Long count = (Long)FinderCacheUtil.getResult(_finderPathWithPaginationCountByP_L,
				finderArgs, this);

		if (count == null) {
			try {
				if ((databaseInMaxParameters > 0) &&
						(plids.length > databaseInMaxParameters)) {
					count = Long.valueOf(0);

					long[][] plidsPages = (long[][])ArrayUtil.split(plids,
							databaseInMaxParameters);

					for (long[] plidsPage : plidsPages) {
						count += Long.valueOf(_countByP_L(plidsPage, languageId));
					}
				}
				else {
					count = Long.valueOf(_countByP_L(plids, languageId));
				}

				FinderCacheUtil.putResult(_finderPathWithPaginationCountByP_L,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(_finderPathWithPaginationCountByP_L,
					finderArgs);

				throw processException(e);
			}
		}

		return count.intValue();
	}

	private int _countByP_L(long[] plids, String languageId) {
		Long count = null;

		StringBundler query = new StringBundler();

		query.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

		if (plids.length > 0) {
			query.append("(");

			query.append(_FINDER_COLUMN_P_L_PLID_7);

			query.append(StringUtil.merge(plids));

			query.append(")");

			query.append(")");

			query.append(WHERE_AND);
		}

		boolean bindLanguageId = false;

		if (languageId.isEmpty()) {
			query.append(_FINDER_COLUMN_P_L_LANGUAGEID_3);
		}
		else {
			bindLanguageId = true;

			query.append(_FINDER_COLUMN_P_L_LANGUAGEID_2);
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindLanguageId) {
				qPos.add(languageId);
			}

			count = (Long)q.uniqueResult();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_P_L_PLID_2 = "layoutFriendlyURL.plid = ? AND ";
	private static final String _FINDER_COLUMN_P_L_PLID_7 = "layoutFriendlyURL.plid IN (";
	private static final String _FINDER_COLUMN_P_L_LANGUAGEID_2 = "layoutFriendlyURL.languageId = ?";
	private static final String _FINDER_COLUMN_P_L_LANGUAGEID_3 = "(layoutFriendlyURL.languageId IS NULL OR layoutFriendlyURL.languageId = '')";
	private FinderPath _finderPathWithPaginationFindByG_P_F;
	private FinderPath _finderPathWithoutPaginationFindByG_P_F;
	private FinderPath _finderPathCountByG_P_F;

	/**
	 * Returns all the layout friendly urls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByG_P_F(long groupId,
		boolean privateLayout, String friendlyURL) {
		return findByG_P_F(groupId, privateLayout, friendlyURL,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByG_P_F(long groupId,
		boolean privateLayout, String friendlyURL, int start, int end) {
		return findByG_P_F(groupId, privateLayout, friendlyURL, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByG_P_F(long groupId,
		boolean privateLayout, String friendlyURL, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		return findByG_P_F(groupId, privateLayout, friendlyURL, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByG_P_F(long groupId,
		boolean privateLayout, String friendlyURL, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
		boolean retrieveFromCache) {
		friendlyURL = Objects.toString(friendlyURL, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByG_P_F;
			finderArgs = new Object[] { groupId, privateLayout, friendlyURL };
		}
		else {
			finderPath = _finderPathWithPaginationFindByG_P_F;
			finderArgs = new Object[] {
					groupId, privateLayout, friendlyURL,
					
					start, end, orderByComparator
				};
		}

		List<LayoutFriendlyURL> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if ((groupId != layoutFriendlyURL.getGroupId()) ||
							(privateLayout != layoutFriendlyURL.isPrivateLayout()) ||
							!friendlyURL.equals(
								layoutFriendlyURL.getFriendlyURL())) {
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

			query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

			query.append(_FINDER_COLUMN_G_P_F_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_F_PRIVATELAYOUT_2);

			boolean bindFriendlyURL = false;

			if (friendlyURL.isEmpty()) {
				query.append(_FINDER_COLUMN_G_P_F_FRIENDLYURL_3);
			}
			else {
				bindFriendlyURL = true;

				query.append(_FINDER_COLUMN_G_P_F_FRIENDLYURL_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(privateLayout);

				if (bindFriendlyURL) {
					qPos.add(friendlyURL);
				}

				if (!pagination) {
					list = (List<LayoutFriendlyURL>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutFriendlyURL>)QueryUtil.list(q,
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
	 * Returns the first layout friendly url in the ordered set where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByG_P_F_First(long groupId,
		boolean privateLayout, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByG_P_F_First(groupId,
				privateLayout, friendlyURL, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", privateLayout=");
		msg.append(privateLayout);

		msg.append(", friendlyURL=");
		msg.append(friendlyURL);

		msg.append("}");

		throw new NoSuchLayoutFriendlyURLException(msg.toString());
	}

	/**
	 * Returns the first layout friendly url in the ordered set where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByG_P_F_First(long groupId,
		boolean privateLayout, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		List<LayoutFriendlyURL> list = findByG_P_F(groupId, privateLayout,
				friendlyURL, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout friendly url in the ordered set where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByG_P_F_Last(long groupId,
		boolean privateLayout, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByG_P_F_Last(groupId,
				privateLayout, friendlyURL, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", privateLayout=");
		msg.append(privateLayout);

		msg.append(", friendlyURL=");
		msg.append(friendlyURL);

		msg.append("}");

		throw new NoSuchLayoutFriendlyURLException(msg.toString());
	}

	/**
	 * Returns the last layout friendly url in the ordered set where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByG_P_F_Last(long groupId,
		boolean privateLayout, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		int count = countByG_P_F(groupId, privateLayout, friendlyURL);

		if (count == 0) {
			return null;
		}

		List<LayoutFriendlyURL> list = findByG_P_F(groupId, privateLayout,
				friendlyURL, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout friendly urls before and after the current layout friendly url in the ordered set where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * @param layoutFriendlyURLId the primary key of the current layout friendly url
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL[] findByG_P_F_PrevAndNext(
		long layoutFriendlyURLId, long groupId, boolean privateLayout,
		String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {
		friendlyURL = Objects.toString(friendlyURL, "");

		LayoutFriendlyURL layoutFriendlyURL = findByPrimaryKey(layoutFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL[] array = new LayoutFriendlyURLImpl[3];

			array[0] = getByG_P_F_PrevAndNext(session, layoutFriendlyURL,
					groupId, privateLayout, friendlyURL, orderByComparator, true);

			array[1] = layoutFriendlyURL;

			array[2] = getByG_P_F_PrevAndNext(session, layoutFriendlyURL,
					groupId, privateLayout, friendlyURL, orderByComparator,
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

	protected LayoutFriendlyURL getByG_P_F_PrevAndNext(Session session,
		LayoutFriendlyURL layoutFriendlyURL, long groupId,
		boolean privateLayout, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

		query.append(_FINDER_COLUMN_G_P_F_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_F_PRIVATELAYOUT_2);

		boolean bindFriendlyURL = false;

		if (friendlyURL.isEmpty()) {
			query.append(_FINDER_COLUMN_G_P_F_FRIENDLYURL_3);
		}
		else {
			bindFriendlyURL = true;

			query.append(_FINDER_COLUMN_G_P_F_FRIENDLYURL_2);
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
			query.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(privateLayout);

		if (bindFriendlyURL) {
			qPos.add(friendlyURL);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					layoutFriendlyURL)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<LayoutFriendlyURL> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout friendly urls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 */
	@Override
	public void removeByG_P_F(long groupId, boolean privateLayout,
		String friendlyURL) {
		for (LayoutFriendlyURL layoutFriendlyURL : findByG_P_F(groupId,
				privateLayout, friendlyURL, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByG_P_F(long groupId, boolean privateLayout,
		String friendlyURL) {
		friendlyURL = Objects.toString(friendlyURL, "");

		FinderPath finderPath = _finderPathCountByG_P_F;

		Object[] finderArgs = new Object[] { groupId, privateLayout, friendlyURL };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

			query.append(_FINDER_COLUMN_G_P_F_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_F_PRIVATELAYOUT_2);

			boolean bindFriendlyURL = false;

			if (friendlyURL.isEmpty()) {
				query.append(_FINDER_COLUMN_G_P_F_FRIENDLYURL_3);
			}
			else {
				bindFriendlyURL = true;

				query.append(_FINDER_COLUMN_G_P_F_FRIENDLYURL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(privateLayout);

				if (bindFriendlyURL) {
					qPos.add(friendlyURL);
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

	private static final String _FINDER_COLUMN_G_P_F_GROUPID_2 = "layoutFriendlyURL.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_F_PRIVATELAYOUT_2 = "layoutFriendlyURL.privateLayout = ? AND ";
	private static final String _FINDER_COLUMN_G_P_F_FRIENDLYURL_2 = "layoutFriendlyURL.friendlyURL = ?";
	private static final String _FINDER_COLUMN_G_P_F_FRIENDLYURL_3 = "(layoutFriendlyURL.friendlyURL IS NULL OR layoutFriendlyURL.friendlyURL = '')";
	private FinderPath _finderPathFetchByG_P_F_L;
	private FinderPath _finderPathCountByG_P_F_L;

	/**
	 * Returns the layout friendly url where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; and languageId = &#63; or throws a <code>NoSuchLayoutFriendlyURLException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param languageId the language ID
	 * @return the matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByG_P_F_L(long groupId, boolean privateLayout,
		String friendlyURL, String languageId)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByG_P_F_L(groupId,
				privateLayout, friendlyURL, languageId);

		if (layoutFriendlyURL == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", privateLayout=");
			msg.append(privateLayout);

			msg.append(", friendlyURL=");
			msg.append(friendlyURL);

			msg.append(", languageId=");
			msg.append(languageId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLayoutFriendlyURLException(msg.toString());
		}

		return layoutFriendlyURL;
	}

	/**
	 * Returns the layout friendly url where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param languageId the language ID
	 * @return the matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByG_P_F_L(long groupId,
		boolean privateLayout, String friendlyURL, String languageId) {
		return fetchByG_P_F_L(groupId, privateLayout, friendlyURL, languageId,
			true);
	}

	/**
	 * Returns the layout friendly url where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param languageId the language ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByG_P_F_L(long groupId,
		boolean privateLayout, String friendlyURL, String languageId,
		boolean retrieveFromCache) {
		friendlyURL = Objects.toString(friendlyURL, "");
		languageId = Objects.toString(languageId, "");

		Object[] finderArgs = new Object[] {
				groupId, privateLayout, friendlyURL, languageId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(_finderPathFetchByG_P_F_L,
					finderArgs, this);
		}

		if (result instanceof LayoutFriendlyURL) {
			LayoutFriendlyURL layoutFriendlyURL = (LayoutFriendlyURL)result;

			if ((groupId != layoutFriendlyURL.getGroupId()) ||
					(privateLayout != layoutFriendlyURL.isPrivateLayout()) ||
					!Objects.equals(friendlyURL,
						layoutFriendlyURL.getFriendlyURL()) ||
					!Objects.equals(languageId,
						layoutFriendlyURL.getLanguageId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

			query.append(_FINDER_COLUMN_G_P_F_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_F_L_PRIVATELAYOUT_2);

			boolean bindFriendlyURL = false;

			if (friendlyURL.isEmpty()) {
				query.append(_FINDER_COLUMN_G_P_F_L_FRIENDLYURL_3);
			}
			else {
				bindFriendlyURL = true;

				query.append(_FINDER_COLUMN_G_P_F_L_FRIENDLYURL_2);
			}

			boolean bindLanguageId = false;

			if (languageId.isEmpty()) {
				query.append(_FINDER_COLUMN_G_P_F_L_LANGUAGEID_3);
			}
			else {
				bindLanguageId = true;

				query.append(_FINDER_COLUMN_G_P_F_L_LANGUAGEID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(privateLayout);

				if (bindFriendlyURL) {
					qPos.add(friendlyURL);
				}

				if (bindLanguageId) {
					qPos.add(languageId);
				}

				List<LayoutFriendlyURL> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(_finderPathFetchByG_P_F_L,
						finderArgs, list);
				}
				else {
					LayoutFriendlyURL layoutFriendlyURL = list.get(0);

					result = layoutFriendlyURL;

					cacheResult(layoutFriendlyURL);
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(_finderPathFetchByG_P_F_L,
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
			return (LayoutFriendlyURL)result;
		}
	}

	/**
	 * Removes the layout friendly url where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; and languageId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param languageId the language ID
	 * @return the layout friendly url that was removed
	 */
	@Override
	public LayoutFriendlyURL removeByG_P_F_L(long groupId,
		boolean privateLayout, String friendlyURL, String languageId)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = findByG_P_F_L(groupId,
				privateLayout, friendlyURL, languageId);

		return remove(layoutFriendlyURL);
	}

	/**
	 * Returns the number of layout friendly urls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; and languageId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param languageId the language ID
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByG_P_F_L(long groupId, boolean privateLayout,
		String friendlyURL, String languageId) {
		friendlyURL = Objects.toString(friendlyURL, "");
		languageId = Objects.toString(languageId, "");

		FinderPath finderPath = _finderPathCountByG_P_F_L;

		Object[] finderArgs = new Object[] {
				groupId, privateLayout, friendlyURL, languageId
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

			query.append(_FINDER_COLUMN_G_P_F_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_F_L_PRIVATELAYOUT_2);

			boolean bindFriendlyURL = false;

			if (friendlyURL.isEmpty()) {
				query.append(_FINDER_COLUMN_G_P_F_L_FRIENDLYURL_3);
			}
			else {
				bindFriendlyURL = true;

				query.append(_FINDER_COLUMN_G_P_F_L_FRIENDLYURL_2);
			}

			boolean bindLanguageId = false;

			if (languageId.isEmpty()) {
				query.append(_FINDER_COLUMN_G_P_F_L_LANGUAGEID_3);
			}
			else {
				bindLanguageId = true;

				query.append(_FINDER_COLUMN_G_P_F_L_LANGUAGEID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(privateLayout);

				if (bindFriendlyURL) {
					qPos.add(friendlyURL);
				}

				if (bindLanguageId) {
					qPos.add(languageId);
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

	private static final String _FINDER_COLUMN_G_P_F_L_GROUPID_2 = "layoutFriendlyURL.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_F_L_PRIVATELAYOUT_2 = "layoutFriendlyURL.privateLayout = ? AND ";
	private static final String _FINDER_COLUMN_G_P_F_L_FRIENDLYURL_2 = "layoutFriendlyURL.friendlyURL = ? AND ";
	private static final String _FINDER_COLUMN_G_P_F_L_FRIENDLYURL_3 = "(layoutFriendlyURL.friendlyURL IS NULL OR layoutFriendlyURL.friendlyURL = '') AND ";
	private static final String _FINDER_COLUMN_G_P_F_L_LANGUAGEID_2 = "layoutFriendlyURL.languageId = ?";
	private static final String _FINDER_COLUMN_G_P_F_L_LANGUAGEID_3 = "(layoutFriendlyURL.languageId IS NULL OR layoutFriendlyURL.languageId = '')";

	public LayoutFriendlyURLPersistenceImpl() {
		setModelClass(LayoutFriendlyURL.class);

		setModelImplClass(LayoutFriendlyURLImpl.class);
		setModelPKClass(long.class);
		setEntityCacheEnabled(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED);
	}

	/**
	 * Caches the layout friendly url in the entity cache if it is enabled.
	 *
	 * @param layoutFriendlyURL the layout friendly url
	 */
	@Override
	public void cacheResult(LayoutFriendlyURL layoutFriendlyURL) {
		EntityCacheUtil.putResult(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFriendlyURLImpl.class, layoutFriendlyURL.getPrimaryKey(),
			layoutFriendlyURL);

		FinderCacheUtil.putResult(_finderPathFetchByUUID_G,
			new Object[] {
				layoutFriendlyURL.getUuid(), layoutFriendlyURL.getGroupId()
			}, layoutFriendlyURL);

		FinderCacheUtil.putResult(_finderPathFetchByP_L,
			new Object[] {
				layoutFriendlyURL.getPlid(), layoutFriendlyURL.getLanguageId()
			}, layoutFriendlyURL);

		FinderCacheUtil.putResult(_finderPathFetchByG_P_F_L,
			new Object[] {
				layoutFriendlyURL.getGroupId(),
				layoutFriendlyURL.isPrivateLayout(),
				layoutFriendlyURL.getFriendlyURL(),
				layoutFriendlyURL.getLanguageId()
			}, layoutFriendlyURL);

		layoutFriendlyURL.resetOriginalValues();
	}

	/**
	 * Caches the layout friendly urls in the entity cache if it is enabled.
	 *
	 * @param layoutFriendlyURLs the layout friendly urls
	 */
	@Override
	public void cacheResult(List<LayoutFriendlyURL> layoutFriendlyURLs) {
		for (LayoutFriendlyURL layoutFriendlyURL : layoutFriendlyURLs) {
			if (EntityCacheUtil.getResult(
						LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
						LayoutFriendlyURLImpl.class,
						layoutFriendlyURL.getPrimaryKey()) == null) {
				cacheResult(layoutFriendlyURL);
			}
			else {
				layoutFriendlyURL.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all layout friendly urls.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>com.liferay.portal.kernel.dao.orm.FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(LayoutFriendlyURLImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the layout friendly url.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>com.liferay.portal.kernel.dao.orm.FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LayoutFriendlyURL layoutFriendlyURL) {
		EntityCacheUtil.removeResult(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFriendlyURLImpl.class, layoutFriendlyURL.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LayoutFriendlyURLModelImpl)layoutFriendlyURL,
			true);
	}

	@Override
	public void clearCache(List<LayoutFriendlyURL> layoutFriendlyURLs) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LayoutFriendlyURL layoutFriendlyURL : layoutFriendlyURLs) {
			EntityCacheUtil.removeResult(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class, layoutFriendlyURL.getPrimaryKey());

			clearUniqueFindersCache((LayoutFriendlyURLModelImpl)layoutFriendlyURL,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		LayoutFriendlyURLModelImpl layoutFriendlyURLModelImpl) {
		Object[] args = new Object[] {
				layoutFriendlyURLModelImpl.getUuid(),
				layoutFriendlyURLModelImpl.getGroupId()
			};

		FinderCacheUtil.putResult(_finderPathCountByUUID_G, args,
			Long.valueOf(1), false);
		FinderCacheUtil.putResult(_finderPathFetchByUUID_G, args,
			layoutFriendlyURLModelImpl, false);

		args = new Object[] {
				layoutFriendlyURLModelImpl.getPlid(),
				layoutFriendlyURLModelImpl.getLanguageId()
			};

		FinderCacheUtil.putResult(_finderPathCountByP_L, args, Long.valueOf(1),
			false);
		FinderCacheUtil.putResult(_finderPathFetchByP_L, args,
			layoutFriendlyURLModelImpl, false);

		args = new Object[] {
				layoutFriendlyURLModelImpl.getGroupId(),
				layoutFriendlyURLModelImpl.isPrivateLayout(),
				layoutFriendlyURLModelImpl.getFriendlyURL(),
				layoutFriendlyURLModelImpl.getLanguageId()
			};

		FinderCacheUtil.putResult(_finderPathCountByG_P_F_L, args,
			Long.valueOf(1), false);
		FinderCacheUtil.putResult(_finderPathFetchByG_P_F_L, args,
			layoutFriendlyURLModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LayoutFriendlyURLModelImpl layoutFriendlyURLModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					layoutFriendlyURLModelImpl.getUuid(),
					layoutFriendlyURLModelImpl.getGroupId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByUUID_G, args);
			FinderCacheUtil.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((layoutFriendlyURLModelImpl.getColumnBitmask() &
				_finderPathFetchByUUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					layoutFriendlyURLModelImpl.getOriginalUuid(),
					layoutFriendlyURLModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByUUID_G, args);
			FinderCacheUtil.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					layoutFriendlyURLModelImpl.getPlid(),
					layoutFriendlyURLModelImpl.getLanguageId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByP_L, args);
			FinderCacheUtil.removeResult(_finderPathFetchByP_L, args);
		}

		if ((layoutFriendlyURLModelImpl.getColumnBitmask() &
				_finderPathFetchByP_L.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					layoutFriendlyURLModelImpl.getOriginalPlid(),
					layoutFriendlyURLModelImpl.getOriginalLanguageId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByP_L, args);
			FinderCacheUtil.removeResult(_finderPathFetchByP_L, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					layoutFriendlyURLModelImpl.getGroupId(),
					layoutFriendlyURLModelImpl.isPrivateLayout(),
					layoutFriendlyURLModelImpl.getFriendlyURL(),
					layoutFriendlyURLModelImpl.getLanguageId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_P_F_L, args);
			FinderCacheUtil.removeResult(_finderPathFetchByG_P_F_L, args);
		}

		if ((layoutFriendlyURLModelImpl.getColumnBitmask() &
				_finderPathFetchByG_P_F_L.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					layoutFriendlyURLModelImpl.getOriginalGroupId(),
					layoutFriendlyURLModelImpl.getOriginalPrivateLayout(),
					layoutFriendlyURLModelImpl.getOriginalFriendlyURL(),
					layoutFriendlyURLModelImpl.getOriginalLanguageId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_P_F_L, args);
			FinderCacheUtil.removeResult(_finderPathFetchByG_P_F_L, args);
		}
	}

	/**
	 * Creates a new layout friendly url with the primary key. Does not add the layout friendly url to the database.
	 *
	 * @param layoutFriendlyURLId the primary key for the new layout friendly url
	 * @return the new layout friendly url
	 */
	@Override
	public LayoutFriendlyURL create(long layoutFriendlyURLId) {
		LayoutFriendlyURL layoutFriendlyURL = new LayoutFriendlyURLImpl();

		layoutFriendlyURL.setNew(true);
		layoutFriendlyURL.setPrimaryKey(layoutFriendlyURLId);

		String uuid = PortalUUIDUtil.generate();

		layoutFriendlyURL.setUuid(uuid);

		layoutFriendlyURL.setCompanyId(companyProvider.getCompanyId());

		return layoutFriendlyURL;
	}

	/**
	 * Removes the layout friendly url with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutFriendlyURLId the primary key of the layout friendly url
	 * @return the layout friendly url that was removed
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL remove(long layoutFriendlyURLId)
		throws NoSuchLayoutFriendlyURLException {
		return remove((Serializable)layoutFriendlyURLId);
	}

	/**
	 * Removes the layout friendly url with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the layout friendly url
	 * @return the layout friendly url that was removed
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL remove(Serializable primaryKey)
		throws NoSuchLayoutFriendlyURLException {
		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL layoutFriendlyURL = (LayoutFriendlyURL)session.get(LayoutFriendlyURLImpl.class,
					primaryKey);

			if (layoutFriendlyURL == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLayoutFriendlyURLException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(layoutFriendlyURL);
		}
		catch (NoSuchLayoutFriendlyURLException nsee) {
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
	protected LayoutFriendlyURL removeImpl(LayoutFriendlyURL layoutFriendlyURL) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(layoutFriendlyURL)) {
				layoutFriendlyURL = (LayoutFriendlyURL)session.get(LayoutFriendlyURLImpl.class,
						layoutFriendlyURL.getPrimaryKeyObj());
			}

			if (layoutFriendlyURL != null) {
				session.delete(layoutFriendlyURL);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (layoutFriendlyURL != null) {
			clearCache(layoutFriendlyURL);
		}

		return layoutFriendlyURL;
	}

	@Override
	public LayoutFriendlyURL updateImpl(LayoutFriendlyURL layoutFriendlyURL) {
		boolean isNew = layoutFriendlyURL.isNew();

		if (!(layoutFriendlyURL instanceof LayoutFriendlyURLModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(layoutFriendlyURL.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(layoutFriendlyURL);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in layoutFriendlyURL proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LayoutFriendlyURL implementation " +
				layoutFriendlyURL.getClass());
		}

		LayoutFriendlyURLModelImpl layoutFriendlyURLModelImpl = (LayoutFriendlyURLModelImpl)layoutFriendlyURL;

		if (Validator.isNull(layoutFriendlyURL.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			layoutFriendlyURL.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (layoutFriendlyURL.getCreateDate() == null)) {
			if (serviceContext == null) {
				layoutFriendlyURL.setCreateDate(now);
			}
			else {
				layoutFriendlyURL.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!layoutFriendlyURLModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				layoutFriendlyURL.setModifiedDate(now);
			}
			else {
				layoutFriendlyURL.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (layoutFriendlyURL.isNew()) {
				session.save(layoutFriendlyURL);

				layoutFriendlyURL.setNew(false);
			}
			else {
				layoutFriendlyURL = (LayoutFriendlyURL)session.merge(layoutFriendlyURL);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LayoutFriendlyURLModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { layoutFriendlyURLModelImpl.getUuid() };

			FinderCacheUtil.removeResult(_finderPathCountByUuid, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid,
				args);

			args = new Object[] {
					layoutFriendlyURLModelImpl.getUuid(),
					layoutFriendlyURLModelImpl.getCompanyId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByUuid_C, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid_C,
				args);

			args = new Object[] { layoutFriendlyURLModelImpl.getGroupId() };

			FinderCacheUtil.removeResult(_finderPathCountByGroupId, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByGroupId,
				args);

			args = new Object[] { layoutFriendlyURLModelImpl.getCompanyId() };

			FinderCacheUtil.removeResult(_finderPathCountByCompanyId, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByCompanyId,
				args);

			args = new Object[] { layoutFriendlyURLModelImpl.getPlid() };

			FinderCacheUtil.removeResult(_finderPathCountByPlid, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByPlid,
				args);

			args = new Object[] {
					layoutFriendlyURLModelImpl.getPlid(),
					layoutFriendlyURLModelImpl.getFriendlyURL()
				};

			FinderCacheUtil.removeResult(_finderPathCountByP_F, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_F,
				args);

			args = new Object[] {
					layoutFriendlyURLModelImpl.getPlid(),
					layoutFriendlyURLModelImpl.getLanguageId()
				};

			FinderCacheUtil.removeResult(_finderPathCountByP_L, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_L,
				args);

			args = new Object[] {
					layoutFriendlyURLModelImpl.getGroupId(),
					layoutFriendlyURLModelImpl.isPrivateLayout(),
					layoutFriendlyURLModelImpl.getFriendlyURL()
				};

			FinderCacheUtil.removeResult(_finderPathCountByG_P_F, args);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_F,
				args);

			FinderCacheUtil.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindAll,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((layoutFriendlyURLModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByUuid.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutFriendlyURLModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(_finderPathCountByUuid, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid,
					args);

				args = new Object[] { layoutFriendlyURLModelImpl.getUuid() };

				FinderCacheUtil.removeResult(_finderPathCountByUuid, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid,
					args);
			}

			if ((layoutFriendlyURLModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutFriendlyURLModelImpl.getOriginalUuid(),
						layoutFriendlyURLModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByUuid_C, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid_C,
					args);

				args = new Object[] {
						layoutFriendlyURLModelImpl.getUuid(),
						layoutFriendlyURLModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByUuid_C, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByUuid_C,
					args);
			}

			if ((layoutFriendlyURLModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByGroupId.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutFriendlyURLModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByGroupId, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByGroupId,
					args);

				args = new Object[] { layoutFriendlyURLModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(_finderPathCountByGroupId, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByGroupId,
					args);
			}

			if ((layoutFriendlyURLModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByCompanyId.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutFriendlyURLModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByCompanyId, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByCompanyId,
					args);

				args = new Object[] { layoutFriendlyURLModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(_finderPathCountByCompanyId, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByCompanyId,
					args);
			}

			if ((layoutFriendlyURLModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByPlid.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutFriendlyURLModelImpl.getOriginalPlid()
					};

				FinderCacheUtil.removeResult(_finderPathCountByPlid, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByPlid,
					args);

				args = new Object[] { layoutFriendlyURLModelImpl.getPlid() };

				FinderCacheUtil.removeResult(_finderPathCountByPlid, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByPlid,
					args);
			}

			if ((layoutFriendlyURLModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByP_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutFriendlyURLModelImpl.getOriginalPlid(),
						layoutFriendlyURLModelImpl.getOriginalFriendlyURL()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_F, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_F,
					args);

				args = new Object[] {
						layoutFriendlyURLModelImpl.getPlid(),
						layoutFriendlyURLModelImpl.getFriendlyURL()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_F, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_F,
					args);
			}

			if ((layoutFriendlyURLModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByP_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutFriendlyURLModelImpl.getOriginalPlid(),
						layoutFriendlyURLModelImpl.getOriginalLanguageId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_L, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_L,
					args);

				args = new Object[] {
						layoutFriendlyURLModelImpl.getPlid(),
						layoutFriendlyURLModelImpl.getLanguageId()
					};

				FinderCacheUtil.removeResult(_finderPathCountByP_L, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByP_L,
					args);
			}

			if ((layoutFriendlyURLModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByG_P_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutFriendlyURLModelImpl.getOriginalGroupId(),
						layoutFriendlyURLModelImpl.getOriginalPrivateLayout(),
						layoutFriendlyURLModelImpl.getOriginalFriendlyURL()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P_F, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_F,
					args);

				args = new Object[] {
						layoutFriendlyURLModelImpl.getGroupId(),
						layoutFriendlyURLModelImpl.isPrivateLayout(),
						layoutFriendlyURLModelImpl.getFriendlyURL()
					};

				FinderCacheUtil.removeResult(_finderPathCountByG_P_F, args);
				FinderCacheUtil.removeResult(_finderPathWithoutPaginationFindByG_P_F,
					args);
			}
		}

		EntityCacheUtil.putResult(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFriendlyURLImpl.class, layoutFriendlyURL.getPrimaryKey(),
			layoutFriendlyURL, false);

		clearUniqueFindersCache(layoutFriendlyURLModelImpl, false);
		cacheUniqueFindersCache(layoutFriendlyURLModelImpl);

		layoutFriendlyURL.resetOriginalValues();

		return layoutFriendlyURL;
	}

	/**
	 * Returns the layout friendly url with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the layout friendly url
	 * @return the layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLayoutFriendlyURLException {
		LayoutFriendlyURL layoutFriendlyURL = fetchByPrimaryKey(primaryKey);

		if (layoutFriendlyURL == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLayoutFriendlyURLException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return layoutFriendlyURL;
	}

	/**
	 * Returns the layout friendly url with the primary key or throws a <code>NoSuchLayoutFriendlyURLException</code> if it could not be found.
	 *
	 * @param layoutFriendlyURLId the primary key of the layout friendly url
	 * @return the layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL findByPrimaryKey(long layoutFriendlyURLId)
		throws NoSuchLayoutFriendlyURLException {
		return findByPrimaryKey((Serializable)layoutFriendlyURLId);
	}

	/**
	 * Returns the layout friendly url with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param layoutFriendlyURLId the primary key of the layout friendly url
	 * @return the layout friendly url, or <code>null</code> if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByPrimaryKey(long layoutFriendlyURLId) {
		return fetchByPrimaryKey((Serializable)layoutFriendlyURLId);
	}

	/**
	 * Returns all the layout friendly urls.
	 *
	 * @return the layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findAll(int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findAll(int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
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

		List<LayoutFriendlyURL> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LAYOUTFRIENDLYURL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LAYOUTFRIENDLYURL;

				if (pagination) {
					sql = sql.concat(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LayoutFriendlyURL>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutFriendlyURL>)QueryUtil.list(q,
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
	 * Removes all the layout friendly urls from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LayoutFriendlyURL layoutFriendlyURL : findAll()) {
			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls.
	 *
	 * @return the number of layout friendly urls
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(_finderPathCountAll,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LAYOUTFRIENDLYURL);

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
		return "layoutFriendlyURLId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LAYOUTFRIENDLYURL;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LayoutFriendlyURLModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the layout friendly url persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
				new String[0]);

		_finderPathCountAll = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
				new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
				new String[] {
					String.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
				new String[] { String.class.getName() },
				LayoutFriendlyURLModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
				new String[] { String.class.getName() });

		_finderPathFetchByUUID_G = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class, FINDER_CLASS_NAME_ENTITY,
				"fetchByUUID_G",
				new String[] { String.class.getName(), Long.class.getName() },
				LayoutFriendlyURLModelImpl.UUID_COLUMN_BITMASK |
				LayoutFriendlyURLModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
				new String[] { String.class.getName(), Long.class.getName() });

		_finderPathWithPaginationFindByUuid_C = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
				new String[] {
					String.class.getName(), Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
				new String[] { String.class.getName(), Long.class.getName() },
				LayoutFriendlyURLModelImpl.UUID_COLUMN_BITMASK |
				LayoutFriendlyURLModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
				new String[] { String.class.getName(), Long.class.getName() });

		_finderPathWithPaginationFindByGroupId = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
				new String[] {
					Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
				new String[] { Long.class.getName() },
				LayoutFriendlyURLModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
				new String[] { Long.class.getName() });

		_finderPathWithPaginationFindByCompanyId = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
				new String[] {
					Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
				new String[] { Long.class.getName() },
				LayoutFriendlyURLModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
				new String[] { Long.class.getName() });

		_finderPathWithPaginationFindByPlid = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPlid",
				new String[] {
					Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByPlid = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPlid",
				new String[] { Long.class.getName() },
				LayoutFriendlyURLModelImpl.PLID_COLUMN_BITMASK);

		_finderPathCountByPlid = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlid",
				new String[] { Long.class.getName() });

		_finderPathWithPaginationFindByP_F = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_F",
				new String[] {
					Long.class.getName(), String.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByP_F = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_F",
				new String[] { Long.class.getName(), String.class.getName() },
				LayoutFriendlyURLModelImpl.PLID_COLUMN_BITMASK |
				LayoutFriendlyURLModelImpl.FRIENDLYURL_COLUMN_BITMASK);

		_finderPathCountByP_F = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_F",
				new String[] { Long.class.getName(), String.class.getName() });

		_finderPathWithPaginationFindByP_L = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_L",
				new String[] {
					Long.class.getName(), String.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByP_L = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_L",
				new String[] { Long.class.getName(), String.class.getName() },
				LayoutFriendlyURLModelImpl.PLID_COLUMN_BITMASK |
				LayoutFriendlyURLModelImpl.LANGUAGEID_COLUMN_BITMASK);

		_finderPathFetchByP_L = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class, FINDER_CLASS_NAME_ENTITY,
				"fetchByP_L",
				new String[] { Long.class.getName(), String.class.getName() },
				LayoutFriendlyURLModelImpl.PLID_COLUMN_BITMASK |
				LayoutFriendlyURLModelImpl.LANGUAGEID_COLUMN_BITMASK);

		_finderPathCountByP_L = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_L",
				new String[] { Long.class.getName(), String.class.getName() });

		_finderPathWithPaginationCountByP_L = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByP_L",
				new String[] { Long.class.getName(), String.class.getName() });

		_finderPathWithPaginationFindByG_P_F = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_F",
				new String[] {
					Long.class.getName(), Boolean.class.getName(),
					String.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByG_P_F = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P_F",
				new String[] {
					Long.class.getName(), Boolean.class.getName(),
					String.class.getName()
				},
				LayoutFriendlyURLModelImpl.GROUPID_COLUMN_BITMASK |
				LayoutFriendlyURLModelImpl.PRIVATELAYOUT_COLUMN_BITMASK |
				LayoutFriendlyURLModelImpl.FRIENDLYURL_COLUMN_BITMASK);

		_finderPathCountByG_P_F = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_F",
				new String[] {
					Long.class.getName(), Boolean.class.getName(),
					String.class.getName()
				});

		_finderPathFetchByG_P_F_L = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED,
				LayoutFriendlyURLImpl.class, FINDER_CLASS_NAME_ENTITY,
				"fetchByG_P_F_L",
				new String[] {
					Long.class.getName(), Boolean.class.getName(),
					String.class.getName(), String.class.getName()
				},
				LayoutFriendlyURLModelImpl.GROUPID_COLUMN_BITMASK |
				LayoutFriendlyURLModelImpl.PRIVATELAYOUT_COLUMN_BITMASK |
				LayoutFriendlyURLModelImpl.FRIENDLYURL_COLUMN_BITMASK |
				LayoutFriendlyURLModelImpl.LANGUAGEID_COLUMN_BITMASK);

		_finderPathCountByG_P_F_L = new FinderPath(LayoutFriendlyURLModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFriendlyURLModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_F_L",
				new String[] {
					Long.class.getName(), Boolean.class.getName(),
					String.class.getName(), String.class.getName()
				});
	}

	public void destroy() {
		EntityCacheUtil.removeCache(LayoutFriendlyURLImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	private static final String _SQL_SELECT_LAYOUTFRIENDLYURL = "SELECT layoutFriendlyURL FROM LayoutFriendlyURL layoutFriendlyURL";
	private static final String _SQL_SELECT_LAYOUTFRIENDLYURL_WHERE = "SELECT layoutFriendlyURL FROM LayoutFriendlyURL layoutFriendlyURL WHERE ";
	private static final String _SQL_COUNT_LAYOUTFRIENDLYURL = "SELECT COUNT(layoutFriendlyURL) FROM LayoutFriendlyURL layoutFriendlyURL";
	private static final String _SQL_COUNT_LAYOUTFRIENDLYURL_WHERE = "SELECT COUNT(layoutFriendlyURL) FROM LayoutFriendlyURL layoutFriendlyURL WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "layoutFriendlyURL.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LayoutFriendlyURL exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LayoutFriendlyURL exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LayoutFriendlyURLPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}