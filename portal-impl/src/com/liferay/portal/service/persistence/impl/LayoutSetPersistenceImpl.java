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

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.NoSuchLayoutSetException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.LayoutSetPersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.impl.LayoutSetImpl;
import com.liferay.portal.model.impl.LayoutSetModelImpl;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the layout set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LayoutSetPersistenceImpl
	extends BasePersistenceImpl<LayoutSet> implements LayoutSetPersistence {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LayoutSetUtil</code> to access the layout set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LayoutSetImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the layout sets where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching layout sets
	 */
	@Override
	public List<LayoutSet> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout sets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @return the range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout sets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout sets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<LayoutSet> list = null;

		if (useFinderCache) {
			list = (List<LayoutSet>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutSet layoutSet : list) {
					if (groupId != layoutSet.getGroupId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_LAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LayoutSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<LayoutSet>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutSet>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first layout set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByGroupId_First(
			long groupId, OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByGroupId_First(groupId, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchLayoutSetException(msg.toString());
	}

	/**
	 * Returns the first layout set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByGroupId_First(
		long groupId, OrderByComparator<LayoutSet> orderByComparator) {

		List<LayoutSet> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByGroupId_Last(
			long groupId, OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByGroupId_Last(groupId, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchLayoutSetException(msg.toString());
	}

	/**
	 * Returns the last layout set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByGroupId_Last(
		long groupId, OrderByComparator<LayoutSet> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<LayoutSet> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout sets before and after the current layout set in the ordered set where groupId = &#63;.
	 *
	 * @param layoutSetId the primary key of the current layout set
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet[] findByGroupId_PrevAndNext(
			long layoutSetId, long groupId,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = findByPrimaryKey(layoutSetId);

		Session session = null;

		try {
			session = openSession();

			LayoutSet[] array = new LayoutSetImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, layoutSet, groupId, orderByComparator, true);

			array[1] = layoutSet;

			array[2] = getByGroupId_PrevAndNext(
				session, layoutSet, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutSet getByGroupId_PrevAndNext(
		Session session, LayoutSet layoutSet, long groupId,
		OrderByComparator<LayoutSet> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LAYOUTSET_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

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
			query.append(LayoutSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(layoutSet)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LayoutSet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout sets where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (LayoutSet layoutSet :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(layoutSet);
		}
	}

	/**
	 * Returns the number of layout sets where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching layout sets
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LAYOUTSET_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"layoutSet.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByLayoutSetPrototypeUuid;
	private FinderPath _finderPathWithoutPaginationFindByLayoutSetPrototypeUuid;
	private FinderPath _finderPathCountByLayoutSetPrototypeUuid;

	/**
	 * Returns all the layout sets where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @return the matching layout sets
	 */
	@Override
	public List<LayoutSet> findByLayoutSetPrototypeUuid(
		String layoutSetPrototypeUuid) {

		return findByLayoutSetPrototypeUuid(
			layoutSetPrototypeUuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout sets where layoutSetPrototypeUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @return the range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByLayoutSetPrototypeUuid(
		String layoutSetPrototypeUuid, int start, int end) {

		return findByLayoutSetPrototypeUuid(
			layoutSetPrototypeUuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout sets where layoutSetPrototypeUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByLayoutSetPrototypeUuid(
		String layoutSetPrototypeUuid, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator) {

		return findByLayoutSetPrototypeUuid(
			layoutSetPrototypeUuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout sets where layoutSetPrototypeUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByLayoutSetPrototypeUuid(
		String layoutSetPrototypeUuid, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator,
		boolean useFinderCache) {

		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByLayoutSetPrototypeUuid;
				finderArgs = new Object[] {layoutSetPrototypeUuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByLayoutSetPrototypeUuid;
			finderArgs = new Object[] {
				layoutSetPrototypeUuid, start, end, orderByComparator
			};
		}

		List<LayoutSet> list = null;

		if (useFinderCache) {
			list = (List<LayoutSet>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutSet layoutSet : list) {
					if (!layoutSetPrototypeUuid.equals(
							layoutSet.getLayoutSetPrototypeUuid())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_LAYOUTSET_WHERE);

			boolean bindLayoutSetPrototypeUuid = false;

			if (layoutSetPrototypeUuid.isEmpty()) {
				query.append(
					_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_3);
			}
			else {
				bindLayoutSetPrototypeUuid = true;

				query.append(
					_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LayoutSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLayoutSetPrototypeUuid) {
					qPos.add(layoutSetPrototypeUuid);
				}

				if (!pagination) {
					list = (List<LayoutSet>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutSet>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first layout set in the ordered set where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByLayoutSetPrototypeUuid_First(
			String layoutSetPrototypeUuid,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByLayoutSetPrototypeUuid_First(
			layoutSetPrototypeUuid, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("layoutSetPrototypeUuid=");
		msg.append(layoutSetPrototypeUuid);

		msg.append("}");

		throw new NoSuchLayoutSetException(msg.toString());
	}

	/**
	 * Returns the first layout set in the ordered set where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByLayoutSetPrototypeUuid_First(
		String layoutSetPrototypeUuid,
		OrderByComparator<LayoutSet> orderByComparator) {

		List<LayoutSet> list = findByLayoutSetPrototypeUuid(
			layoutSetPrototypeUuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout set in the ordered set where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByLayoutSetPrototypeUuid_Last(
			String layoutSetPrototypeUuid,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByLayoutSetPrototypeUuid_Last(
			layoutSetPrototypeUuid, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("layoutSetPrototypeUuid=");
		msg.append(layoutSetPrototypeUuid);

		msg.append("}");

		throw new NoSuchLayoutSetException(msg.toString());
	}

	/**
	 * Returns the last layout set in the ordered set where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByLayoutSetPrototypeUuid_Last(
		String layoutSetPrototypeUuid,
		OrderByComparator<LayoutSet> orderByComparator) {

		int count = countByLayoutSetPrototypeUuid(layoutSetPrototypeUuid);

		if (count == 0) {
			return null;
		}

		List<LayoutSet> list = findByLayoutSetPrototypeUuid(
			layoutSetPrototypeUuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout sets before and after the current layout set in the ordered set where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetId the primary key of the current layout set
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet[] findByLayoutSetPrototypeUuid_PrevAndNext(
			long layoutSetId, String layoutSetPrototypeUuid,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		LayoutSet layoutSet = findByPrimaryKey(layoutSetId);

		Session session = null;

		try {
			session = openSession();

			LayoutSet[] array = new LayoutSetImpl[3];

			array[0] = getByLayoutSetPrototypeUuid_PrevAndNext(
				session, layoutSet, layoutSetPrototypeUuid, orderByComparator,
				true);

			array[1] = layoutSet;

			array[2] = getByLayoutSetPrototypeUuid_PrevAndNext(
				session, layoutSet, layoutSetPrototypeUuid, orderByComparator,
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

	protected LayoutSet getByLayoutSetPrototypeUuid_PrevAndNext(
		Session session, LayoutSet layoutSet, String layoutSetPrototypeUuid,
		OrderByComparator<LayoutSet> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LAYOUTSET_WHERE);

		boolean bindLayoutSetPrototypeUuid = false;

		if (layoutSetPrototypeUuid.isEmpty()) {
			query.append(
				_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_3);
		}
		else {
			bindLayoutSetPrototypeUuid = true;

			query.append(
				_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

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
			query.append(LayoutSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindLayoutSetPrototypeUuid) {
			qPos.add(layoutSetPrototypeUuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(layoutSet)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LayoutSet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout sets where layoutSetPrototypeUuid = &#63; from the database.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 */
	@Override
	public void removeByLayoutSetPrototypeUuid(String layoutSetPrototypeUuid) {
		for (LayoutSet layoutSet :
				findByLayoutSetPrototypeUuid(
					layoutSetPrototypeUuid, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(layoutSet);
		}
	}

	/**
	 * Returns the number of layout sets where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @return the number of matching layout sets
	 */
	@Override
	public int countByLayoutSetPrototypeUuid(String layoutSetPrototypeUuid) {
		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		FinderPath finderPath = _finderPathCountByLayoutSetPrototypeUuid;

		Object[] finderArgs = new Object[] {layoutSetPrototypeUuid};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LAYOUTSET_WHERE);

			boolean bindLayoutSetPrototypeUuid = false;

			if (layoutSetPrototypeUuid.isEmpty()) {
				query.append(
					_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_3);
			}
			else {
				bindLayoutSetPrototypeUuid = true;

				query.append(
					_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLayoutSetPrototypeUuid) {
					qPos.add(layoutSetPrototypeUuid);
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

	private static final String
		_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_2 =
			"layoutSet.layoutSetPrototypeUuid = ?";

	private static final String
		_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_3 =
			"(layoutSet.layoutSetPrototypeUuid IS NULL OR layoutSet.layoutSetPrototypeUuid = '')";

	private FinderPath _finderPathFetchByG_P;
	private FinderPath _finderPathCountByG_P;

	/**
	 * Returns the layout set where groupId = &#63; and privateLayout = &#63; or throws a <code>NoSuchLayoutSetException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @return the matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByG_P(long groupId, boolean privateLayout)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByG_P(groupId, privateLayout);

		if (layoutSet == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", privateLayout=");
			msg.append(privateLayout);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLayoutSetException(msg.toString());
		}

		return layoutSet;
	}

	/**
	 * Returns the layout set where groupId = &#63; and privateLayout = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @return the matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByG_P(long groupId, boolean privateLayout) {
		return fetchByG_P(groupId, privateLayout, true);
	}

	/**
	 * Returns the layout set where groupId = &#63; and privateLayout = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByG_P(
		long groupId, boolean privateLayout, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {groupId, privateLayout};
		}

		Object result = null;

		if (useFinderCache) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByG_P, finderArgs, this);
		}

		if (result instanceof LayoutSet) {
			LayoutSet layoutSet = (LayoutSet)result;

			if ((groupId != layoutSet.getGroupId()) ||
				(privateLayout != layoutSet.isPrivateLayout())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_PRIVATELAYOUT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(privateLayout);

				List<LayoutSet> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByG_P, finderArgs, list);
					}
				}
				else {
					LayoutSet layoutSet = list.get(0);

					result = layoutSet;

					cacheResult(layoutSet);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(
						_finderPathFetchByG_P, finderArgs);
				}

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
			return (LayoutSet)result;
		}
	}

	/**
	 * Removes the layout set where groupId = &#63; and privateLayout = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @return the layout set that was removed
	 */
	@Override
	public LayoutSet removeByG_P(long groupId, boolean privateLayout)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = findByG_P(groupId, privateLayout);

		return remove(layoutSet);
	}

	/**
	 * Returns the number of layout sets where groupId = &#63; and privateLayout = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @return the number of matching layout sets
	 */
	@Override
	public int countByG_P(long groupId, boolean privateLayout) {
		FinderPath finderPath = _finderPathCountByG_P;

		Object[] finderArgs = new Object[] {groupId, privateLayout};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_PRIVATELAYOUT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(privateLayout);

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

	private static final String _FINDER_COLUMN_G_P_GROUPID_2 =
		"layoutSet.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_P_PRIVATELAYOUT_2 =
		"layoutSet.privateLayout = ?";

	private FinderPath _finderPathWithPaginationFindByC_L;
	private FinderPath _finderPathWithoutPaginationFindByC_L;
	private FinderPath _finderPathCountByC_L;

	/**
	 * Returns all the layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @return the matching layout sets
	 */
	@Override
	public List<LayoutSet> findByC_L(
		long companyId, String layoutSetPrototypeUuid) {

		return findByC_L(
			companyId, layoutSetPrototypeUuid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @return the range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByC_L(
		long companyId, String layoutSetPrototypeUuid, int start, int end) {

		return findByC_L(companyId, layoutSetPrototypeUuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByC_L(
		long companyId, String layoutSetPrototypeUuid, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator) {

		return findByC_L(
			companyId, layoutSetPrototypeUuid, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByC_L(
		long companyId, String layoutSetPrototypeUuid, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator,
		boolean useFinderCache) {

		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_L;
				finderArgs = new Object[] {companyId, layoutSetPrototypeUuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_L;
			finderArgs = new Object[] {
				companyId, layoutSetPrototypeUuid, start, end, orderByComparator
			};
		}

		List<LayoutSet> list = null;

		if (useFinderCache) {
			list = (List<LayoutSet>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutSet layoutSet : list) {
					if ((companyId != layoutSet.getCompanyId()) ||
						!layoutSetPrototypeUuid.equals(
							layoutSet.getLayoutSetPrototypeUuid())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_LAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_C_L_COMPANYID_2);

			boolean bindLayoutSetPrototypeUuid = false;

			if (layoutSetPrototypeUuid.isEmpty()) {
				query.append(_FINDER_COLUMN_C_L_LAYOUTSETPROTOTYPEUUID_3);
			}
			else {
				bindLayoutSetPrototypeUuid = true;

				query.append(_FINDER_COLUMN_C_L_LAYOUTSETPROTOTYPEUUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LayoutSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindLayoutSetPrototypeUuid) {
					qPos.add(layoutSetPrototypeUuid);
				}

				if (!pagination) {
					list = (List<LayoutSet>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutSet>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first layout set in the ordered set where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByC_L_First(
			long companyId, String layoutSetPrototypeUuid,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByC_L_First(
			companyId, layoutSetPrototypeUuid, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", layoutSetPrototypeUuid=");
		msg.append(layoutSetPrototypeUuid);

		msg.append("}");

		throw new NoSuchLayoutSetException(msg.toString());
	}

	/**
	 * Returns the first layout set in the ordered set where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByC_L_First(
		long companyId, String layoutSetPrototypeUuid,
		OrderByComparator<LayoutSet> orderByComparator) {

		List<LayoutSet> list = findByC_L(
			companyId, layoutSetPrototypeUuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout set in the ordered set where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByC_L_Last(
			long companyId, String layoutSetPrototypeUuid,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByC_L_Last(
			companyId, layoutSetPrototypeUuid, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", layoutSetPrototypeUuid=");
		msg.append(layoutSetPrototypeUuid);

		msg.append("}");

		throw new NoSuchLayoutSetException(msg.toString());
	}

	/**
	 * Returns the last layout set in the ordered set where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByC_L_Last(
		long companyId, String layoutSetPrototypeUuid,
		OrderByComparator<LayoutSet> orderByComparator) {

		int count = countByC_L(companyId, layoutSetPrototypeUuid);

		if (count == 0) {
			return null;
		}

		List<LayoutSet> list = findByC_L(
			companyId, layoutSetPrototypeUuid, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout sets before and after the current layout set in the ordered set where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetId the primary key of the current layout set
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet[] findByC_L_PrevAndNext(
			long layoutSetId, long companyId, String layoutSetPrototypeUuid,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		LayoutSet layoutSet = findByPrimaryKey(layoutSetId);

		Session session = null;

		try {
			session = openSession();

			LayoutSet[] array = new LayoutSetImpl[3];

			array[0] = getByC_L_PrevAndNext(
				session, layoutSet, companyId, layoutSetPrototypeUuid,
				orderByComparator, true);

			array[1] = layoutSet;

			array[2] = getByC_L_PrevAndNext(
				session, layoutSet, companyId, layoutSetPrototypeUuid,
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

	protected LayoutSet getByC_L_PrevAndNext(
		Session session, LayoutSet layoutSet, long companyId,
		String layoutSetPrototypeUuid,
		OrderByComparator<LayoutSet> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LAYOUTSET_WHERE);

		query.append(_FINDER_COLUMN_C_L_COMPANYID_2);

		boolean bindLayoutSetPrototypeUuid = false;

		if (layoutSetPrototypeUuid.isEmpty()) {
			query.append(_FINDER_COLUMN_C_L_LAYOUTSETPROTOTYPEUUID_3);
		}
		else {
			bindLayoutSetPrototypeUuid = true;

			query.append(_FINDER_COLUMN_C_L_LAYOUTSETPROTOTYPEUUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

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
			query.append(LayoutSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindLayoutSetPrototypeUuid) {
			qPos.add(layoutSetPrototypeUuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(layoutSet)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LayoutSet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 */
	@Override
	public void removeByC_L(long companyId, String layoutSetPrototypeUuid) {
		for (LayoutSet layoutSet :
				findByC_L(
					companyId, layoutSetPrototypeUuid, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(layoutSet);
		}
	}

	/**
	 * Returns the number of layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @return the number of matching layout sets
	 */
	@Override
	public int countByC_L(long companyId, String layoutSetPrototypeUuid) {
		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		FinderPath finderPath = _finderPathCountByC_L;

		Object[] finderArgs = new Object[] {companyId, layoutSetPrototypeUuid};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_C_L_COMPANYID_2);

			boolean bindLayoutSetPrototypeUuid = false;

			if (layoutSetPrototypeUuid.isEmpty()) {
				query.append(_FINDER_COLUMN_C_L_LAYOUTSETPROTOTYPEUUID_3);
			}
			else {
				bindLayoutSetPrototypeUuid = true;

				query.append(_FINDER_COLUMN_C_L_LAYOUTSETPROTOTYPEUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindLayoutSetPrototypeUuid) {
					qPos.add(layoutSetPrototypeUuid);
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

	private static final String _FINDER_COLUMN_C_L_COMPANYID_2 =
		"layoutSet.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_L_LAYOUTSETPROTOTYPEUUID_2 =
		"layoutSet.layoutSetPrototypeUuid = ?";

	private static final String _FINDER_COLUMN_C_L_LAYOUTSETPROTOTYPEUUID_3 =
		"(layoutSet.layoutSetPrototypeUuid IS NULL OR layoutSet.layoutSetPrototypeUuid = '')";

	private FinderPath _finderPathFetchByP_L;
	private FinderPath _finderPathCountByP_L;

	/**
	 * Returns the layout set where privateLayout = &#63; and logoId = &#63; or throws a <code>NoSuchLayoutSetException</code> if it could not be found.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @return the matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByP_L(boolean privateLayout, long logoId)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByP_L(privateLayout, logoId);

		if (layoutSet == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("privateLayout=");
			msg.append(privateLayout);

			msg.append(", logoId=");
			msg.append(logoId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLayoutSetException(msg.toString());
		}

		return layoutSet;
	}

	/**
	 * Returns the layout set where privateLayout = &#63; and logoId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @return the matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByP_L(boolean privateLayout, long logoId) {
		return fetchByP_L(privateLayout, logoId, true);
	}

	/**
	 * Returns the layout set where privateLayout = &#63; and logoId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByP_L(
		boolean privateLayout, long logoId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {privateLayout, logoId};
		}

		Object result = null;

		if (useFinderCache) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByP_L, finderArgs, this);
		}

		if (result instanceof LayoutSet) {
			LayoutSet layoutSet = (LayoutSet)result;

			if ((privateLayout != layoutSet.isPrivateLayout()) ||
				(logoId != layoutSet.getLogoId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_P_L_PRIVATELAYOUT_2);

			query.append(_FINDER_COLUMN_P_L_LOGOID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(privateLayout);

				qPos.add(logoId);

				List<LayoutSet> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByP_L, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									privateLayout, logoId
								};
							}

							_log.warn(
								"LayoutSetPersistenceImpl.fetchByP_L(boolean, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					LayoutSet layoutSet = list.get(0);

					result = layoutSet;

					cacheResult(layoutSet);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(
						_finderPathFetchByP_L, finderArgs);
				}

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
			return (LayoutSet)result;
		}
	}

	/**
	 * Removes the layout set where privateLayout = &#63; and logoId = &#63; from the database.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @return the layout set that was removed
	 */
	@Override
	public LayoutSet removeByP_L(boolean privateLayout, long logoId)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = findByP_L(privateLayout, logoId);

		return remove(layoutSet);
	}

	/**
	 * Returns the number of layout sets where privateLayout = &#63; and logoId = &#63;.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @return the number of matching layout sets
	 */
	@Override
	public int countByP_L(boolean privateLayout, long logoId) {
		FinderPath finderPath = _finderPathCountByP_L;

		Object[] finderArgs = new Object[] {privateLayout, logoId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_P_L_PRIVATELAYOUT_2);

			query.append(_FINDER_COLUMN_P_L_LOGOID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(privateLayout);

				qPos.add(logoId);

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

	private static final String _FINDER_COLUMN_P_L_PRIVATELAYOUT_2 =
		"layoutSet.privateLayout = ? AND ";

	private static final String _FINDER_COLUMN_P_L_LOGOID_2 =
		"layoutSet.logoId = ?";

	public LayoutSetPersistenceImpl() {
		setModelClass(LayoutSet.class);

		setModelImplClass(LayoutSetImpl.class);
		setModelPKClass(long.class);
		setEntityCacheEnabled(LayoutSetModelImpl.ENTITY_CACHE_ENABLED);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("settings", "settings_");

		setDBColumnNames(dbColumnNames);
	}

	/**
	 * Caches the layout set in the entity cache if it is enabled.
	 *
	 * @param layoutSet the layout set
	 */
	@Override
	public void cacheResult(LayoutSet layoutSet) {
		EntityCacheUtil.putResult(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED, LayoutSetImpl.class,
			layoutSet.getPrimaryKey(), layoutSet);

		FinderCacheUtil.putResult(
			_finderPathFetchByG_P,
			new Object[] {layoutSet.getGroupId(), layoutSet.isPrivateLayout()},
			layoutSet);

		FinderCacheUtil.putResult(
			_finderPathFetchByP_L,
			new Object[] {layoutSet.isPrivateLayout(), layoutSet.getLogoId()},
			layoutSet);

		layoutSet.resetOriginalValues();
	}

	/**
	 * Caches the layout sets in the entity cache if it is enabled.
	 *
	 * @param layoutSets the layout sets
	 */
	@Override
	public void cacheResult(List<LayoutSet> layoutSets) {
		for (LayoutSet layoutSet : layoutSets) {
			if (EntityCacheUtil.getResult(
					LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
					LayoutSetImpl.class, layoutSet.getPrimaryKey()) == null) {

				cacheResult(layoutSet);
			}
			else {
				layoutSet.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all layout sets.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>com.liferay.portal.kernel.dao.orm.FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(LayoutSetImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the layout set.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>com.liferay.portal.kernel.dao.orm.FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LayoutSet layoutSet) {
		EntityCacheUtil.removeResult(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED, LayoutSetImpl.class,
			layoutSet.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LayoutSetModelImpl)layoutSet, true);
	}

	@Override
	public void clearCache(List<LayoutSet> layoutSets) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LayoutSet layoutSet : layoutSets) {
			EntityCacheUtil.removeResult(
				LayoutSetModelImpl.ENTITY_CACHE_ENABLED, LayoutSetImpl.class,
				layoutSet.getPrimaryKey());

			clearUniqueFindersCache((LayoutSetModelImpl)layoutSet, true);
		}
	}

	protected void cacheUniqueFindersCache(
		LayoutSetModelImpl layoutSetModelImpl) {

		Object[] args = new Object[] {
			layoutSetModelImpl.getGroupId(),
			layoutSetModelImpl.isPrivateLayout()
		};

		FinderCacheUtil.putResult(
			_finderPathCountByG_P, args, Long.valueOf(1), false);
		FinderCacheUtil.putResult(
			_finderPathFetchByG_P, args, layoutSetModelImpl, false);

		args = new Object[] {
			layoutSetModelImpl.isPrivateLayout(), layoutSetModelImpl.getLogoId()
		};

		FinderCacheUtil.putResult(
			_finderPathCountByP_L, args, Long.valueOf(1), false);
		FinderCacheUtil.putResult(
			_finderPathFetchByP_L, args, layoutSetModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LayoutSetModelImpl layoutSetModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				layoutSetModelImpl.getGroupId(),
				layoutSetModelImpl.isPrivateLayout()
			};

			FinderCacheUtil.removeResult(_finderPathCountByG_P, args);
			FinderCacheUtil.removeResult(_finderPathFetchByG_P, args);
		}

		if ((layoutSetModelImpl.getColumnBitmask() &
			 _finderPathFetchByG_P.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				layoutSetModelImpl.getOriginalGroupId(),
				layoutSetModelImpl.getOriginalPrivateLayout()
			};

			FinderCacheUtil.removeResult(_finderPathCountByG_P, args);
			FinderCacheUtil.removeResult(_finderPathFetchByG_P, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				layoutSetModelImpl.isPrivateLayout(),
				layoutSetModelImpl.getLogoId()
			};

			FinderCacheUtil.removeResult(_finderPathCountByP_L, args);
			FinderCacheUtil.removeResult(_finderPathFetchByP_L, args);
		}

		if ((layoutSetModelImpl.getColumnBitmask() &
			 _finderPathFetchByP_L.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				layoutSetModelImpl.getOriginalPrivateLayout(),
				layoutSetModelImpl.getOriginalLogoId()
			};

			FinderCacheUtil.removeResult(_finderPathCountByP_L, args);
			FinderCacheUtil.removeResult(_finderPathFetchByP_L, args);
		}
	}

	/**
	 * Creates a new layout set with the primary key. Does not add the layout set to the database.
	 *
	 * @param layoutSetId the primary key for the new layout set
	 * @return the new layout set
	 */
	@Override
	public LayoutSet create(long layoutSetId) {
		LayoutSet layoutSet = new LayoutSetImpl();

		layoutSet.setNew(true);
		layoutSet.setPrimaryKey(layoutSetId);

		layoutSet.setCompanyId(CompanyThreadLocal.getCompanyId());

		return layoutSet;
	}

	/**
	 * Removes the layout set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutSetId the primary key of the layout set
	 * @return the layout set that was removed
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet remove(long layoutSetId) throws NoSuchLayoutSetException {
		return remove((Serializable)layoutSetId);
	}

	/**
	 * Removes the layout set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the layout set
	 * @return the layout set that was removed
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet remove(Serializable primaryKey)
		throws NoSuchLayoutSetException {

		Session session = null;

		try {
			session = openSession();

			LayoutSet layoutSet = (LayoutSet)session.get(
				LayoutSetImpl.class, primaryKey);

			if (layoutSet == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLayoutSetException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(layoutSet);
		}
		catch (NoSuchLayoutSetException nsee) {
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
	protected LayoutSet removeImpl(LayoutSet layoutSet) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(layoutSet)) {
				layoutSet = (LayoutSet)session.get(
					LayoutSetImpl.class, layoutSet.getPrimaryKeyObj());
			}

			if (layoutSet != null) {
				session.delete(layoutSet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (layoutSet != null) {
			clearCache(layoutSet);
		}

		return layoutSet;
	}

	@Override
	public LayoutSet updateImpl(LayoutSet layoutSet) {
		boolean isNew = layoutSet.isNew();

		if (!(layoutSet instanceof LayoutSetModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(layoutSet.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(layoutSet);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in layoutSet proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LayoutSet implementation " +
					layoutSet.getClass());
		}

		LayoutSetModelImpl layoutSetModelImpl = (LayoutSetModelImpl)layoutSet;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (layoutSet.getCreateDate() == null)) {
			if (serviceContext == null) {
				layoutSet.setCreateDate(now);
			}
			else {
				layoutSet.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!layoutSetModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				layoutSet.setModifiedDate(now);
			}
			else {
				layoutSet.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (layoutSet.isNew()) {
				session.save(layoutSet);

				layoutSet.setNew(false);
			}
			else {
				layoutSet = (LayoutSet)session.merge(layoutSet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LayoutSetModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {layoutSetModelImpl.getGroupId()};

			FinderCacheUtil.removeResult(_finderPathCountByGroupId, args);
			FinderCacheUtil.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			args = new Object[] {
				layoutSetModelImpl.getLayoutSetPrototypeUuid()
			};

			FinderCacheUtil.removeResult(
				_finderPathCountByLayoutSetPrototypeUuid, args);
			FinderCacheUtil.removeResult(
				_finderPathWithoutPaginationFindByLayoutSetPrototypeUuid, args);

			args = new Object[] {
				layoutSetModelImpl.getCompanyId(),
				layoutSetModelImpl.getLayoutSetPrototypeUuid()
			};

			FinderCacheUtil.removeResult(_finderPathCountByC_L, args);
			FinderCacheUtil.removeResult(
				_finderPathWithoutPaginationFindByC_L, args);

			FinderCacheUtil.removeResult(
				_finderPathCountAll, FINDER_ARGS_EMPTY);
			FinderCacheUtil.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((layoutSetModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					layoutSetModelImpl.getOriginalGroupId()
				};

				FinderCacheUtil.removeResult(_finderPathCountByGroupId, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {layoutSetModelImpl.getGroupId()};

				FinderCacheUtil.removeResult(_finderPathCountByGroupId, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}

			if ((layoutSetModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByLayoutSetPrototypeUuid.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					layoutSetModelImpl.getOriginalLayoutSetPrototypeUuid()
				};

				FinderCacheUtil.removeResult(
					_finderPathCountByLayoutSetPrototypeUuid, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByLayoutSetPrototypeUuid,
					args);

				args = new Object[] {
					layoutSetModelImpl.getLayoutSetPrototypeUuid()
				};

				FinderCacheUtil.removeResult(
					_finderPathCountByLayoutSetPrototypeUuid, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByLayoutSetPrototypeUuid,
					args);
			}

			if ((layoutSetModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_L.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					layoutSetModelImpl.getOriginalCompanyId(),
					layoutSetModelImpl.getOriginalLayoutSetPrototypeUuid()
				};

				FinderCacheUtil.removeResult(_finderPathCountByC_L, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByC_L, args);

				args = new Object[] {
					layoutSetModelImpl.getCompanyId(),
					layoutSetModelImpl.getLayoutSetPrototypeUuid()
				};

				FinderCacheUtil.removeResult(_finderPathCountByC_L, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByC_L, args);
			}
		}

		EntityCacheUtil.putResult(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED, LayoutSetImpl.class,
			layoutSet.getPrimaryKey(), layoutSet, false);

		clearUniqueFindersCache(layoutSetModelImpl, false);
		cacheUniqueFindersCache(layoutSetModelImpl);

		layoutSet.resetOriginalValues();

		return layoutSet;
	}

	/**
	 * Returns the layout set with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the layout set
	 * @return the layout set
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByPrimaryKey(primaryKey);

		if (layoutSet == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLayoutSetException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return layoutSet;
	}

	/**
	 * Returns the layout set with the primary key or throws a <code>NoSuchLayoutSetException</code> if it could not be found.
	 *
	 * @param layoutSetId the primary key of the layout set
	 * @return the layout set
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet findByPrimaryKey(long layoutSetId)
		throws NoSuchLayoutSetException {

		return findByPrimaryKey((Serializable)layoutSetId);
	}

	/**
	 * Returns the layout set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param layoutSetId the primary key of the layout set
	 * @return the layout set, or <code>null</code> if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet fetchByPrimaryKey(long layoutSetId) {
		return fetchByPrimaryKey((Serializable)layoutSetId);
	}

	/**
	 * Returns all the layout sets.
	 *
	 * @return the layout sets
	 */
	@Override
	public List<LayoutSet> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @return the range of layout sets
	 */
	@Override
	public List<LayoutSet> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of layout sets
	 */
	@Override
	public List<LayoutSet> findAll(
		int start, int end, OrderByComparator<LayoutSet> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of layout sets
	 */
	@Override
	public List<LayoutSet> findAll(
		int start, int end, OrderByComparator<LayoutSet> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<LayoutSet> list = null;

		if (useFinderCache) {
			list = (List<LayoutSet>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LAYOUTSET);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LAYOUTSET;

				if (pagination) {
					sql = sql.concat(LayoutSetModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LayoutSet>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutSet>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the layout sets from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LayoutSet layoutSet : findAll()) {
			remove(layoutSet);
		}
	}

	/**
	 * Returns the number of layout sets.
	 *
	 * @return the number of layout sets
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LAYOUTSET);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

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
		return "layoutSetId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LAYOUTSET;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LayoutSetModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the layout set persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			LayoutSetModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByLayoutSetPrototypeUuid = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLayoutSetPrototypeUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByLayoutSetPrototypeUuid =
			new FinderPath(
				LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
				LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByLayoutSetPrototypeUuid",
				new String[] {String.class.getName()},
				LayoutSetModelImpl.LAYOUTSETPROTOTYPEUUID_COLUMN_BITMASK);

		_finderPathCountByLayoutSetPrototypeUuid = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByLayoutSetPrototypeUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByG_P = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_P",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			LayoutSetModelImpl.GROUPID_COLUMN_BITMASK |
			LayoutSetModelImpl.PRIVATELAYOUT_COLUMN_BITMASK);

		_finderPathCountByG_P = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P",
			new String[] {Long.class.getName(), Boolean.class.getName()});

		_finderPathWithPaginationFindByC_L = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_L",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_L = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_L",
			new String[] {Long.class.getName(), String.class.getName()},
			LayoutSetModelImpl.COMPANYID_COLUMN_BITMASK |
			LayoutSetModelImpl.LAYOUTSETPROTOTYPEUUID_COLUMN_BITMASK);

		_finderPathCountByC_L = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_L",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathFetchByP_L = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByP_L",
			new String[] {Boolean.class.getName(), Long.class.getName()},
			LayoutSetModelImpl.PRIVATELAYOUT_COLUMN_BITMASK |
			LayoutSetModelImpl.LOGOID_COLUMN_BITMASK);

		_finderPathCountByP_L = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_L",
			new String[] {Boolean.class.getName(), Long.class.getName()});
	}

	public void destroy() {
		EntityCacheUtil.removeCache(LayoutSetImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_LAYOUTSET =
		"SELECT layoutSet FROM LayoutSet layoutSet";

	private static final String _SQL_SELECT_LAYOUTSET_WHERE =
		"SELECT layoutSet FROM LayoutSet layoutSet WHERE ";

	private static final String _SQL_COUNT_LAYOUTSET =
		"SELECT COUNT(layoutSet) FROM LayoutSet layoutSet";

	private static final String _SQL_COUNT_LAYOUTSET_WHERE =
		"SELECT COUNT(layoutSet) FROM LayoutSet layoutSet WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "layoutSet.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No LayoutSet exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No LayoutSet exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutSetPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"settings"});

}