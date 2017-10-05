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

package com.liferay.hello.staging.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.hello.staging.exception.NoSuchRoomException;
import com.liferay.hello.staging.model.Room;
import com.liferay.hello.staging.model.impl.RoomImpl;
import com.liferay.hello.staging.model.impl.RoomModelImpl;
import com.liferay.hello.staging.service.persistence.RoomPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the room service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RoomPersistence
 * @see com.liferay.hello.staging.service.persistence.RoomUtil
 * @generated
 */
@ProviderType
public class RoomPersistenceImpl extends BasePersistenceImpl<Room>
	implements RoomPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RoomUtil} to access the room persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RoomImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, RoomImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, RoomImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, RoomImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, RoomImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			RoomModelImpl.UUID_COLUMN_BITMASK |
			RoomModelImpl.ROOMNUMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the rooms where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching rooms
	 */
	@Override
	public List<Room> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rooms where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of rooms
	 * @param end the upper bound of the range of rooms (not inclusive)
	 * @return the range of matching rooms
	 */
	@Override
	public List<Room> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the rooms where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of rooms
	 * @param end the upper bound of the range of rooms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rooms
	 */
	@Override
	public List<Room> findByUuid(String uuid, int start, int end,
		OrderByComparator<Room> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the rooms where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of rooms
	 * @param end the upper bound of the range of rooms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching rooms
	 */
	@Override
	public List<Room> findByUuid(String uuid, int start, int end,
		OrderByComparator<Room> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<Room> list = null;

		if (retrieveFromCache) {
			list = (List<Room>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Room room : list) {
					if (!Objects.equals(uuid, room.getUuid())) {
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

			query.append(_SQL_SELECT_ROOM_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
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
				query.append(RoomModelImpl.ORDER_BY_JPQL);
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
					list = (List<Room>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Room>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first room in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching room
	 * @throws NoSuchRoomException if a matching room could not be found
	 */
	@Override
	public Room findByUuid_First(String uuid,
		OrderByComparator<Room> orderByComparator) throws NoSuchRoomException {
		Room room = fetchByUuid_First(uuid, orderByComparator);

		if (room != null) {
			return room;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRoomException(msg.toString());
	}

	/**
	 * Returns the first room in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching room, or <code>null</code> if a matching room could not be found
	 */
	@Override
	public Room fetchByUuid_First(String uuid,
		OrderByComparator<Room> orderByComparator) {
		List<Room> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last room in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching room
	 * @throws NoSuchRoomException if a matching room could not be found
	 */
	@Override
	public Room findByUuid_Last(String uuid,
		OrderByComparator<Room> orderByComparator) throws NoSuchRoomException {
		Room room = fetchByUuid_Last(uuid, orderByComparator);

		if (room != null) {
			return room;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRoomException(msg.toString());
	}

	/**
	 * Returns the last room in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching room, or <code>null</code> if a matching room could not be found
	 */
	@Override
	public Room fetchByUuid_Last(String uuid,
		OrderByComparator<Room> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Room> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the rooms before and after the current room in the ordered set where uuid = &#63;.
	 *
	 * @param roomId the primary key of the current room
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next room
	 * @throws NoSuchRoomException if a room with the primary key could not be found
	 */
	@Override
	public Room[] findByUuid_PrevAndNext(long roomId, String uuid,
		OrderByComparator<Room> orderByComparator) throws NoSuchRoomException {
		Room room = findByPrimaryKey(roomId);

		Session session = null;

		try {
			session = openSession();

			Room[] array = new RoomImpl[3];

			array[0] = getByUuid_PrevAndNext(session, room, uuid,
					orderByComparator, true);

			array[1] = room;

			array[2] = getByUuid_PrevAndNext(session, room, uuid,
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

	protected Room getByUuid_PrevAndNext(Session session, Room room,
		String uuid, OrderByComparator<Room> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ROOM_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
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
			query.append(RoomModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(room);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Room> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the rooms where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Room room : findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(room);
		}
	}

	/**
	 * Returns the number of rooms where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching rooms
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ROOM_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
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

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "room.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "room.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(room.uuid IS NULL OR room.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, RoomImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			RoomModelImpl.UUID_COLUMN_BITMASK |
			RoomModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the room where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRoomException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching room
	 * @throws NoSuchRoomException if a matching room could not be found
	 */
	@Override
	public Room findByUUID_G(String uuid, long groupId)
		throws NoSuchRoomException {
		Room room = fetchByUUID_G(uuid, groupId);

		if (room == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchRoomException(msg.toString());
		}

		return room;
	}

	/**
	 * Returns the room where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching room, or <code>null</code> if a matching room could not be found
	 */
	@Override
	public Room fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the room where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching room, or <code>null</code> if a matching room could not be found
	 */
	@Override
	public Room fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Room) {
			Room room = (Room)result;

			if (!Objects.equals(uuid, room.getUuid()) ||
					(groupId != room.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ROOM_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
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

				List<Room> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Room room = list.get(0);

					result = room;

					cacheResult(room);

					if ((room.getUuid() == null) ||
							!room.getUuid().equals(uuid) ||
							(room.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, room);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

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
			return (Room)result;
		}
	}

	/**
	 * Removes the room where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the room that was removed
	 */
	@Override
	public Room removeByUUID_G(String uuid, long groupId)
		throws NoSuchRoomException {
		Room room = findByUUID_G(uuid, groupId);

		return remove(room);
	}

	/**
	 * Returns the number of rooms where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching rooms
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ROOM_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
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

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "room.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "room.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(room.uuid IS NULL OR room.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "room.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, RoomImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, RoomImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			RoomModelImpl.UUID_COLUMN_BITMASK |
			RoomModelImpl.COMPANYID_COLUMN_BITMASK |
			RoomModelImpl.ROOMNUMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the rooms where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching rooms
	 */
	@Override
	public List<Room> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rooms where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of rooms
	 * @param end the upper bound of the range of rooms (not inclusive)
	 * @return the range of matching rooms
	 */
	@Override
	public List<Room> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the rooms where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of rooms
	 * @param end the upper bound of the range of rooms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rooms
	 */
	@Override
	public List<Room> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Room> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the rooms where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of rooms
	 * @param end the upper bound of the range of rooms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching rooms
	 */
	@Override
	public List<Room> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Room> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<Room> list = null;

		if (retrieveFromCache) {
			list = (List<Room>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Room room : list) {
					if (!Objects.equals(uuid, room.getUuid()) ||
							(companyId != room.getCompanyId())) {
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

			query.append(_SQL_SELECT_ROOM_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
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
				query.append(RoomModelImpl.ORDER_BY_JPQL);
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
					list = (List<Room>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Room>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first room in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching room
	 * @throws NoSuchRoomException if a matching room could not be found
	 */
	@Override
	public Room findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Room> orderByComparator) throws NoSuchRoomException {
		Room room = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (room != null) {
			return room;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRoomException(msg.toString());
	}

	/**
	 * Returns the first room in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching room, or <code>null</code> if a matching room could not be found
	 */
	@Override
	public Room fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Room> orderByComparator) {
		List<Room> list = findByUuid_C(uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last room in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching room
	 * @throws NoSuchRoomException if a matching room could not be found
	 */
	@Override
	public Room findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Room> orderByComparator) throws NoSuchRoomException {
		Room room = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (room != null) {
			return room;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRoomException(msg.toString());
	}

	/**
	 * Returns the last room in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching room, or <code>null</code> if a matching room could not be found
	 */
	@Override
	public Room fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Room> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Room> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the rooms before and after the current room in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param roomId the primary key of the current room
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next room
	 * @throws NoSuchRoomException if a room with the primary key could not be found
	 */
	@Override
	public Room[] findByUuid_C_PrevAndNext(long roomId, String uuid,
		long companyId, OrderByComparator<Room> orderByComparator)
		throws NoSuchRoomException {
		Room room = findByPrimaryKey(roomId);

		Session session = null;

		try {
			session = openSession();

			Room[] array = new RoomImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, room, uuid, companyId,
					orderByComparator, true);

			array[1] = room;

			array[2] = getByUuid_C_PrevAndNext(session, room, uuid, companyId,
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

	protected Room getByUuid_C_PrevAndNext(Session session, Room room,
		String uuid, long companyId, OrderByComparator<Room> orderByComparator,
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

		query.append(_SQL_SELECT_ROOM_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
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
			query.append(RoomModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(room);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Room> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the rooms where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Room room : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(room);
		}
	}

	/**
	 * Returns the number of rooms where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching rooms
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ROOM_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
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

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "room.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "room.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(room.uuid IS NULL OR room.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "room.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDBOOKINGID =
		new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, RoomImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupIdAndBookingId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDBOOKINGID =
		new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, RoomImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupIdAndBookingId",
			new String[] { Long.class.getName(), Long.class.getName() },
			RoomModelImpl.GROUPID_COLUMN_BITMASK |
			RoomModelImpl.BOOKINGID_COLUMN_BITMASK |
			RoomModelImpl.ROOMNUMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDBOOKINGID = new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndBookingId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the rooms where groupId = &#63; and bookingId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param bookingId the booking ID
	 * @return the matching rooms
	 */
	@Override
	public List<Room> findByGroupIdAndBookingId(long groupId, long bookingId) {
		return findByGroupIdAndBookingId(groupId, bookingId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rooms where groupId = &#63; and bookingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param bookingId the booking ID
	 * @param start the lower bound of the range of rooms
	 * @param end the upper bound of the range of rooms (not inclusive)
	 * @return the range of matching rooms
	 */
	@Override
	public List<Room> findByGroupIdAndBookingId(long groupId, long bookingId,
		int start, int end) {
		return findByGroupIdAndBookingId(groupId, bookingId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the rooms where groupId = &#63; and bookingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param bookingId the booking ID
	 * @param start the lower bound of the range of rooms
	 * @param end the upper bound of the range of rooms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rooms
	 */
	@Override
	public List<Room> findByGroupIdAndBookingId(long groupId, long bookingId,
		int start, int end, OrderByComparator<Room> orderByComparator) {
		return findByGroupIdAndBookingId(groupId, bookingId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the rooms where groupId = &#63; and bookingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param bookingId the booking ID
	 * @param start the lower bound of the range of rooms
	 * @param end the upper bound of the range of rooms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching rooms
	 */
	@Override
	public List<Room> findByGroupIdAndBookingId(long groupId, long bookingId,
		int start, int end, OrderByComparator<Room> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDBOOKINGID;
			finderArgs = new Object[] { groupId, bookingId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDBOOKINGID;
			finderArgs = new Object[] {
					groupId, bookingId,
					
					start, end, orderByComparator
				};
		}

		List<Room> list = null;

		if (retrieveFromCache) {
			list = (List<Room>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Room room : list) {
					if ((groupId != room.getGroupId()) ||
							(bookingId != room.getBookingId())) {
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

			query.append(_SQL_SELECT_ROOM_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDBOOKINGID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDBOOKINGID_BOOKINGID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RoomModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(bookingId);

				if (!pagination) {
					list = (List<Room>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Room>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first room in the ordered set where groupId = &#63; and bookingId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param bookingId the booking ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching room
	 * @throws NoSuchRoomException if a matching room could not be found
	 */
	@Override
	public Room findByGroupIdAndBookingId_First(long groupId, long bookingId,
		OrderByComparator<Room> orderByComparator) throws NoSuchRoomException {
		Room room = fetchByGroupIdAndBookingId_First(groupId, bookingId,
				orderByComparator);

		if (room != null) {
			return room;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", bookingId=");
		msg.append(bookingId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRoomException(msg.toString());
	}

	/**
	 * Returns the first room in the ordered set where groupId = &#63; and bookingId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param bookingId the booking ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching room, or <code>null</code> if a matching room could not be found
	 */
	@Override
	public Room fetchByGroupIdAndBookingId_First(long groupId, long bookingId,
		OrderByComparator<Room> orderByComparator) {
		List<Room> list = findByGroupIdAndBookingId(groupId, bookingId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last room in the ordered set where groupId = &#63; and bookingId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param bookingId the booking ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching room
	 * @throws NoSuchRoomException if a matching room could not be found
	 */
	@Override
	public Room findByGroupIdAndBookingId_Last(long groupId, long bookingId,
		OrderByComparator<Room> orderByComparator) throws NoSuchRoomException {
		Room room = fetchByGroupIdAndBookingId_Last(groupId, bookingId,
				orderByComparator);

		if (room != null) {
			return room;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", bookingId=");
		msg.append(bookingId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRoomException(msg.toString());
	}

	/**
	 * Returns the last room in the ordered set where groupId = &#63; and bookingId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param bookingId the booking ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching room, or <code>null</code> if a matching room could not be found
	 */
	@Override
	public Room fetchByGroupIdAndBookingId_Last(long groupId, long bookingId,
		OrderByComparator<Room> orderByComparator) {
		int count = countByGroupIdAndBookingId(groupId, bookingId);

		if (count == 0) {
			return null;
		}

		List<Room> list = findByGroupIdAndBookingId(groupId, bookingId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the rooms before and after the current room in the ordered set where groupId = &#63; and bookingId = &#63;.
	 *
	 * @param roomId the primary key of the current room
	 * @param groupId the group ID
	 * @param bookingId the booking ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next room
	 * @throws NoSuchRoomException if a room with the primary key could not be found
	 */
	@Override
	public Room[] findByGroupIdAndBookingId_PrevAndNext(long roomId,
		long groupId, long bookingId, OrderByComparator<Room> orderByComparator)
		throws NoSuchRoomException {
		Room room = findByPrimaryKey(roomId);

		Session session = null;

		try {
			session = openSession();

			Room[] array = new RoomImpl[3];

			array[0] = getByGroupIdAndBookingId_PrevAndNext(session, room,
					groupId, bookingId, orderByComparator, true);

			array[1] = room;

			array[2] = getByGroupIdAndBookingId_PrevAndNext(session, room,
					groupId, bookingId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Room getByGroupIdAndBookingId_PrevAndNext(Session session,
		Room room, long groupId, long bookingId,
		OrderByComparator<Room> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ROOM_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDBOOKINGID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDANDBOOKINGID_BOOKINGID_2);

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
			query.append(RoomModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(bookingId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(room);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Room> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the rooms where groupId = &#63; and bookingId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param bookingId the booking ID
	 */
	@Override
	public void removeByGroupIdAndBookingId(long groupId, long bookingId) {
		for (Room room : findByGroupIdAndBookingId(groupId, bookingId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(room);
		}
	}

	/**
	 * Returns the number of rooms where groupId = &#63; and bookingId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param bookingId the booking ID
	 * @return the number of matching rooms
	 */
	@Override
	public int countByGroupIdAndBookingId(long groupId, long bookingId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDANDBOOKINGID;

		Object[] finderArgs = new Object[] { groupId, bookingId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ROOM_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDBOOKINGID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDBOOKINGID_BOOKINGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(bookingId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPIDANDBOOKINGID_GROUPID_2 = "room.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDBOOKINGID_BOOKINGID_2 = "room.bookingId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDROOMNUMBER =
		new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, RoomImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupIdAndRoomNumber",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDROOMNUMBER =
		new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, RoomImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupIdAndRoomNumber",
			new String[] { Long.class.getName(), String.class.getName() },
			RoomModelImpl.GROUPID_COLUMN_BITMASK |
			RoomModelImpl.ROOMNUMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDROOMNUMBER = new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndRoomNumber",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the rooms where groupId = &#63; and roomNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roomNumber the room number
	 * @return the matching rooms
	 */
	@Override
	public List<Room> findByGroupIdAndRoomNumber(long groupId, String roomNumber) {
		return findByGroupIdAndRoomNumber(groupId, roomNumber,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rooms where groupId = &#63; and roomNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param roomNumber the room number
	 * @param start the lower bound of the range of rooms
	 * @param end the upper bound of the range of rooms (not inclusive)
	 * @return the range of matching rooms
	 */
	@Override
	public List<Room> findByGroupIdAndRoomNumber(long groupId,
		String roomNumber, int start, int end) {
		return findByGroupIdAndRoomNumber(groupId, roomNumber, start, end, null);
	}

	/**
	 * Returns an ordered range of all the rooms where groupId = &#63; and roomNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param roomNumber the room number
	 * @param start the lower bound of the range of rooms
	 * @param end the upper bound of the range of rooms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rooms
	 */
	@Override
	public List<Room> findByGroupIdAndRoomNumber(long groupId,
		String roomNumber, int start, int end,
		OrderByComparator<Room> orderByComparator) {
		return findByGroupIdAndRoomNumber(groupId, roomNumber, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the rooms where groupId = &#63; and roomNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param roomNumber the room number
	 * @param start the lower bound of the range of rooms
	 * @param end the upper bound of the range of rooms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching rooms
	 */
	@Override
	public List<Room> findByGroupIdAndRoomNumber(long groupId,
		String roomNumber, int start, int end,
		OrderByComparator<Room> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDROOMNUMBER;
			finderArgs = new Object[] { groupId, roomNumber };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDANDROOMNUMBER;
			finderArgs = new Object[] {
					groupId, roomNumber,
					
					start, end, orderByComparator
				};
		}

		List<Room> list = null;

		if (retrieveFromCache) {
			list = (List<Room>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Room room : list) {
					if ((groupId != room.getGroupId()) ||
							!Objects.equals(roomNumber, room.getRoomNumber())) {
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

			query.append(_SQL_SELECT_ROOM_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDROOMNUMBER_GROUPID_2);

			boolean bindRoomNumber = false;

			if (roomNumber == null) {
				query.append(_FINDER_COLUMN_GROUPIDANDROOMNUMBER_ROOMNUMBER_1);
			}
			else if (roomNumber.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GROUPIDANDROOMNUMBER_ROOMNUMBER_3);
			}
			else {
				bindRoomNumber = true;

				query.append(_FINDER_COLUMN_GROUPIDANDROOMNUMBER_ROOMNUMBER_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RoomModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindRoomNumber) {
					qPos.add(roomNumber);
				}

				if (!pagination) {
					list = (List<Room>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Room>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first room in the ordered set where groupId = &#63; and roomNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roomNumber the room number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching room
	 * @throws NoSuchRoomException if a matching room could not be found
	 */
	@Override
	public Room findByGroupIdAndRoomNumber_First(long groupId,
		String roomNumber, OrderByComparator<Room> orderByComparator)
		throws NoSuchRoomException {
		Room room = fetchByGroupIdAndRoomNumber_First(groupId, roomNumber,
				orderByComparator);

		if (room != null) {
			return room;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", roomNumber=");
		msg.append(roomNumber);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRoomException(msg.toString());
	}

	/**
	 * Returns the first room in the ordered set where groupId = &#63; and roomNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roomNumber the room number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching room, or <code>null</code> if a matching room could not be found
	 */
	@Override
	public Room fetchByGroupIdAndRoomNumber_First(long groupId,
		String roomNumber, OrderByComparator<Room> orderByComparator) {
		List<Room> list = findByGroupIdAndRoomNumber(groupId, roomNumber, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last room in the ordered set where groupId = &#63; and roomNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roomNumber the room number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching room
	 * @throws NoSuchRoomException if a matching room could not be found
	 */
	@Override
	public Room findByGroupIdAndRoomNumber_Last(long groupId,
		String roomNumber, OrderByComparator<Room> orderByComparator)
		throws NoSuchRoomException {
		Room room = fetchByGroupIdAndRoomNumber_Last(groupId, roomNumber,
				orderByComparator);

		if (room != null) {
			return room;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", roomNumber=");
		msg.append(roomNumber);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRoomException(msg.toString());
	}

	/**
	 * Returns the last room in the ordered set where groupId = &#63; and roomNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roomNumber the room number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching room, or <code>null</code> if a matching room could not be found
	 */
	@Override
	public Room fetchByGroupIdAndRoomNumber_Last(long groupId,
		String roomNumber, OrderByComparator<Room> orderByComparator) {
		int count = countByGroupIdAndRoomNumber(groupId, roomNumber);

		if (count == 0) {
			return null;
		}

		List<Room> list = findByGroupIdAndRoomNumber(groupId, roomNumber,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the rooms before and after the current room in the ordered set where groupId = &#63; and roomNumber = &#63;.
	 *
	 * @param roomId the primary key of the current room
	 * @param groupId the group ID
	 * @param roomNumber the room number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next room
	 * @throws NoSuchRoomException if a room with the primary key could not be found
	 */
	@Override
	public Room[] findByGroupIdAndRoomNumber_PrevAndNext(long roomId,
		long groupId, String roomNumber,
		OrderByComparator<Room> orderByComparator) throws NoSuchRoomException {
		Room room = findByPrimaryKey(roomId);

		Session session = null;

		try {
			session = openSession();

			Room[] array = new RoomImpl[3];

			array[0] = getByGroupIdAndRoomNumber_PrevAndNext(session, room,
					groupId, roomNumber, orderByComparator, true);

			array[1] = room;

			array[2] = getByGroupIdAndRoomNumber_PrevAndNext(session, room,
					groupId, roomNumber, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Room getByGroupIdAndRoomNumber_PrevAndNext(Session session,
		Room room, long groupId, String roomNumber,
		OrderByComparator<Room> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ROOM_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDANDROOMNUMBER_GROUPID_2);

		boolean bindRoomNumber = false;

		if (roomNumber == null) {
			query.append(_FINDER_COLUMN_GROUPIDANDROOMNUMBER_ROOMNUMBER_1);
		}
		else if (roomNumber.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GROUPIDANDROOMNUMBER_ROOMNUMBER_3);
		}
		else {
			bindRoomNumber = true;

			query.append(_FINDER_COLUMN_GROUPIDANDROOMNUMBER_ROOMNUMBER_2);
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
			query.append(RoomModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindRoomNumber) {
			qPos.add(roomNumber);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(room);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Room> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the rooms where groupId = &#63; and roomNumber = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param roomNumber the room number
	 */
	@Override
	public void removeByGroupIdAndRoomNumber(long groupId, String roomNumber) {
		for (Room room : findByGroupIdAndRoomNumber(groupId, roomNumber,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(room);
		}
	}

	/**
	 * Returns the number of rooms where groupId = &#63; and roomNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roomNumber the room number
	 * @return the number of matching rooms
	 */
	@Override
	public int countByGroupIdAndRoomNumber(long groupId, String roomNumber) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDANDROOMNUMBER;

		Object[] finderArgs = new Object[] { groupId, roomNumber };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ROOM_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDROOMNUMBER_GROUPID_2);

			boolean bindRoomNumber = false;

			if (roomNumber == null) {
				query.append(_FINDER_COLUMN_GROUPIDANDROOMNUMBER_ROOMNUMBER_1);
			}
			else if (roomNumber.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GROUPIDANDROOMNUMBER_ROOMNUMBER_3);
			}
			else {
				bindRoomNumber = true;

				query.append(_FINDER_COLUMN_GROUPIDANDROOMNUMBER_ROOMNUMBER_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindRoomNumber) {
					qPos.add(roomNumber);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPIDANDROOMNUMBER_GROUPID_2 = "room.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDROOMNUMBER_ROOMNUMBER_1 =
		"room.roomNumber IS NULL";
	private static final String _FINDER_COLUMN_GROUPIDANDROOMNUMBER_ROOMNUMBER_2 =
		"room.roomNumber = ?";
	private static final String _FINDER_COLUMN_GROUPIDANDROOMNUMBER_ROOMNUMBER_3 =
		"(room.roomNumber IS NULL OR room.roomNumber = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_GROUPIDANDBOOKINGIDANDROOMNUMBER =
		new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, RoomImpl.class,
			FINDER_CLASS_NAME_ENTITY,
			"fetchByGroupIdAndBookingIdAndRoomNumber",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			RoomModelImpl.GROUPID_COLUMN_BITMASK |
			RoomModelImpl.BOOKINGID_COLUMN_BITMASK |
			RoomModelImpl.ROOMNUMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDANDBOOKINGIDANDROOMNUMBER =
		new FinderPath(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupIdAndBookingIdAndRoomNumber",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the room where groupId = &#63; and bookingId = &#63; and roomNumber = &#63; or throws a {@link NoSuchRoomException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param bookingId the booking ID
	 * @param roomNumber the room number
	 * @return the matching room
	 * @throws NoSuchRoomException if a matching room could not be found
	 */
	@Override
	public Room findByGroupIdAndBookingIdAndRoomNumber(long groupId,
		long bookingId, String roomNumber) throws NoSuchRoomException {
		Room room = fetchByGroupIdAndBookingIdAndRoomNumber(groupId, bookingId,
				roomNumber);

		if (room == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", bookingId=");
			msg.append(bookingId);

			msg.append(", roomNumber=");
			msg.append(roomNumber);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchRoomException(msg.toString());
		}

		return room;
	}

	/**
	 * Returns the room where groupId = &#63; and bookingId = &#63; and roomNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param bookingId the booking ID
	 * @param roomNumber the room number
	 * @return the matching room, or <code>null</code> if a matching room could not be found
	 */
	@Override
	public Room fetchByGroupIdAndBookingIdAndRoomNumber(long groupId,
		long bookingId, String roomNumber) {
		return fetchByGroupIdAndBookingIdAndRoomNumber(groupId, bookingId,
			roomNumber, true);
	}

	/**
	 * Returns the room where groupId = &#63; and bookingId = &#63; and roomNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param bookingId the booking ID
	 * @param roomNumber the room number
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching room, or <code>null</code> if a matching room could not be found
	 */
	@Override
	public Room fetchByGroupIdAndBookingIdAndRoomNumber(long groupId,
		long bookingId, String roomNumber, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, bookingId, roomNumber };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_GROUPIDANDBOOKINGIDANDROOMNUMBER,
					finderArgs, this);
		}

		if (result instanceof Room) {
			Room room = (Room)result;

			if ((groupId != room.getGroupId()) ||
					(bookingId != room.getBookingId()) ||
					!Objects.equals(roomNumber, room.getRoomNumber())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_ROOM_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDBOOKINGIDANDROOMNUMBER_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDBOOKINGIDANDROOMNUMBER_BOOKINGID_2);

			boolean bindRoomNumber = false;

			if (roomNumber == null) {
				query.append(_FINDER_COLUMN_GROUPIDANDBOOKINGIDANDROOMNUMBER_ROOMNUMBER_1);
			}
			else if (roomNumber.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GROUPIDANDBOOKINGIDANDROOMNUMBER_ROOMNUMBER_3);
			}
			else {
				bindRoomNumber = true;

				query.append(_FINDER_COLUMN_GROUPIDANDBOOKINGIDANDROOMNUMBER_ROOMNUMBER_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(bookingId);

				if (bindRoomNumber) {
					qPos.add(roomNumber);
				}

				List<Room> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_GROUPIDANDBOOKINGIDANDROOMNUMBER,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"RoomPersistenceImpl.fetchByGroupIdAndBookingIdAndRoomNumber(long, long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Room room = list.get(0);

					result = room;

					cacheResult(room);

					if ((room.getGroupId() != groupId) ||
							(room.getBookingId() != bookingId) ||
							(room.getRoomNumber() == null) ||
							!room.getRoomNumber().equals(roomNumber)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_GROUPIDANDBOOKINGIDANDROOMNUMBER,
							finderArgs, room);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_GROUPIDANDBOOKINGIDANDROOMNUMBER,
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
			return (Room)result;
		}
	}

	/**
	 * Removes the room where groupId = &#63; and bookingId = &#63; and roomNumber = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param bookingId the booking ID
	 * @param roomNumber the room number
	 * @return the room that was removed
	 */
	@Override
	public Room removeByGroupIdAndBookingIdAndRoomNumber(long groupId,
		long bookingId, String roomNumber) throws NoSuchRoomException {
		Room room = findByGroupIdAndBookingIdAndRoomNumber(groupId, bookingId,
				roomNumber);

		return remove(room);
	}

	/**
	 * Returns the number of rooms where groupId = &#63; and bookingId = &#63; and roomNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param bookingId the booking ID
	 * @param roomNumber the room number
	 * @return the number of matching rooms
	 */
	@Override
	public int countByGroupIdAndBookingIdAndRoomNumber(long groupId,
		long bookingId, String roomNumber) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDANDBOOKINGIDANDROOMNUMBER;

		Object[] finderArgs = new Object[] { groupId, bookingId, roomNumber };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ROOM_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDANDBOOKINGIDANDROOMNUMBER_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDANDBOOKINGIDANDROOMNUMBER_BOOKINGID_2);

			boolean bindRoomNumber = false;

			if (roomNumber == null) {
				query.append(_FINDER_COLUMN_GROUPIDANDBOOKINGIDANDROOMNUMBER_ROOMNUMBER_1);
			}
			else if (roomNumber.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GROUPIDANDBOOKINGIDANDROOMNUMBER_ROOMNUMBER_3);
			}
			else {
				bindRoomNumber = true;

				query.append(_FINDER_COLUMN_GROUPIDANDBOOKINGIDANDROOMNUMBER_ROOMNUMBER_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(bookingId);

				if (bindRoomNumber) {
					qPos.add(roomNumber);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPIDANDBOOKINGIDANDROOMNUMBER_GROUPID_2 =
		"room.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDBOOKINGIDANDROOMNUMBER_BOOKINGID_2 =
		"room.bookingId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDANDBOOKINGIDANDROOMNUMBER_ROOMNUMBER_1 =
		"room.roomNumber IS NULL";
	private static final String _FINDER_COLUMN_GROUPIDANDBOOKINGIDANDROOMNUMBER_ROOMNUMBER_2 =
		"room.roomNumber = ?";
	private static final String _FINDER_COLUMN_GROUPIDANDBOOKINGIDANDROOMNUMBER_ROOMNUMBER_3 =
		"(room.roomNumber IS NULL OR room.roomNumber = '')";

	public RoomPersistenceImpl() {
		setModelClass(Room.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the room in the entity cache if it is enabled.
	 *
	 * @param room the room
	 */
	@Override
	public void cacheResult(Room room) {
		entityCache.putResult(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomImpl.class, room.getPrimaryKey(), room);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { room.getUuid(), room.getGroupId() }, room);

		finderCache.putResult(FINDER_PATH_FETCH_BY_GROUPIDANDBOOKINGIDANDROOMNUMBER,
			new Object[] {
				room.getGroupId(), room.getBookingId(), room.getRoomNumber()
			}, room);

		room.resetOriginalValues();
	}

	/**
	 * Caches the rooms in the entity cache if it is enabled.
	 *
	 * @param rooms the rooms
	 */
	@Override
	public void cacheResult(List<Room> rooms) {
		for (Room room : rooms) {
			if (entityCache.getResult(RoomModelImpl.ENTITY_CACHE_ENABLED,
						RoomImpl.class, room.getPrimaryKey()) == null) {
				cacheResult(room);
			}
			else {
				room.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all rooms.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RoomImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the room.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Room room) {
		entityCache.removeResult(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomImpl.class, room.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((RoomModelImpl)room, true);
	}

	@Override
	public void clearCache(List<Room> rooms) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Room room : rooms) {
			entityCache.removeResult(RoomModelImpl.ENTITY_CACHE_ENABLED,
				RoomImpl.class, room.getPrimaryKey());

			clearUniqueFindersCache((RoomModelImpl)room, true);
		}
	}

	protected void cacheUniqueFindersCache(RoomModelImpl roomModelImpl) {
		Object[] args = new Object[] {
				roomModelImpl.getUuid(), roomModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args, roomModelImpl,
			false);

		args = new Object[] {
				roomModelImpl.getGroupId(), roomModelImpl.getBookingId(),
				roomModelImpl.getRoomNumber()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_GROUPIDANDBOOKINGIDANDROOMNUMBER,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_GROUPIDANDBOOKINGIDANDROOMNUMBER,
			args, roomModelImpl, false);
	}

	protected void clearUniqueFindersCache(RoomModelImpl roomModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					roomModelImpl.getUuid(), roomModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((roomModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					roomModelImpl.getOriginalUuid(),
					roomModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					roomModelImpl.getGroupId(), roomModelImpl.getBookingId(),
					roomModelImpl.getRoomNumber()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDBOOKINGIDANDROOMNUMBER,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GROUPIDANDBOOKINGIDANDROOMNUMBER,
				args);
		}

		if ((roomModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_GROUPIDANDBOOKINGIDANDROOMNUMBER.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					roomModelImpl.getOriginalGroupId(),
					roomModelImpl.getOriginalBookingId(),
					roomModelImpl.getOriginalRoomNumber()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDBOOKINGIDANDROOMNUMBER,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GROUPIDANDBOOKINGIDANDROOMNUMBER,
				args);
		}
	}

	/**
	 * Creates a new room with the primary key. Does not add the room to the database.
	 *
	 * @param roomId the primary key for the new room
	 * @return the new room
	 */
	@Override
	public Room create(long roomId) {
		Room room = new RoomImpl();

		room.setNew(true);
		room.setPrimaryKey(roomId);

		String uuid = PortalUUIDUtil.generate();

		room.setUuid(uuid);

		room.setCompanyId(companyProvider.getCompanyId());

		return room;
	}

	/**
	 * Removes the room with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param roomId the primary key of the room
	 * @return the room that was removed
	 * @throws NoSuchRoomException if a room with the primary key could not be found
	 */
	@Override
	public Room remove(long roomId) throws NoSuchRoomException {
		return remove((Serializable)roomId);
	}

	/**
	 * Removes the room with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the room
	 * @return the room that was removed
	 * @throws NoSuchRoomException if a room with the primary key could not be found
	 */
	@Override
	public Room remove(Serializable primaryKey) throws NoSuchRoomException {
		Session session = null;

		try {
			session = openSession();

			Room room = (Room)session.get(RoomImpl.class, primaryKey);

			if (room == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRoomException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(room);
		}
		catch (NoSuchRoomException nsee) {
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
	protected Room removeImpl(Room room) {
		room = toUnwrappedModel(room);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(room)) {
				room = (Room)session.get(RoomImpl.class, room.getPrimaryKeyObj());
			}

			if (room != null) {
				session.delete(room);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (room != null) {
			clearCache(room);
		}

		return room;
	}

	@Override
	public Room updateImpl(Room room) {
		room = toUnwrappedModel(room);

		boolean isNew = room.isNew();

		RoomModelImpl roomModelImpl = (RoomModelImpl)room;

		if (Validator.isNull(room.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			room.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (room.getCreateDate() == null)) {
			if (serviceContext == null) {
				room.setCreateDate(now);
			}
			else {
				room.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!roomModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				room.setModifiedDate(now);
			}
			else {
				room.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (room.isNew()) {
				session.save(room);

				room.setNew(false);
			}
			else {
				room = (Room)session.merge(room);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!RoomModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { roomModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					roomModelImpl.getUuid(), roomModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					roomModelImpl.getGroupId(), roomModelImpl.getBookingId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDBOOKINGID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDBOOKINGID,
				args);

			args = new Object[] {
					roomModelImpl.getGroupId(), roomModelImpl.getRoomNumber()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDROOMNUMBER,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDROOMNUMBER,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((roomModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { roomModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { roomModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((roomModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						roomModelImpl.getOriginalUuid(),
						roomModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						roomModelImpl.getUuid(), roomModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((roomModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDBOOKINGID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						roomModelImpl.getOriginalGroupId(),
						roomModelImpl.getOriginalBookingId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDBOOKINGID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDBOOKINGID,
					args);

				args = new Object[] {
						roomModelImpl.getGroupId(), roomModelImpl.getBookingId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDBOOKINGID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDBOOKINGID,
					args);
			}

			if ((roomModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDROOMNUMBER.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						roomModelImpl.getOriginalGroupId(),
						roomModelImpl.getOriginalRoomNumber()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDROOMNUMBER,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDROOMNUMBER,
					args);

				args = new Object[] {
						roomModelImpl.getGroupId(),
						roomModelImpl.getRoomNumber()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPIDANDROOMNUMBER,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDANDROOMNUMBER,
					args);
			}
		}

		entityCache.putResult(RoomModelImpl.ENTITY_CACHE_ENABLED,
			RoomImpl.class, room.getPrimaryKey(), room, false);

		clearUniqueFindersCache(roomModelImpl, false);
		cacheUniqueFindersCache(roomModelImpl);

		room.resetOriginalValues();

		return room;
	}

	protected Room toUnwrappedModel(Room room) {
		if (room instanceof RoomImpl) {
			return room;
		}

		RoomImpl roomImpl = new RoomImpl();

		roomImpl.setNew(room.isNew());
		roomImpl.setPrimaryKey(room.getPrimaryKey());

		roomImpl.setUuid(room.getUuid());
		roomImpl.setRoomId(room.getRoomId());
		roomImpl.setGroupId(room.getGroupId());
		roomImpl.setCompanyId(room.getCompanyId());
		roomImpl.setUserId(room.getUserId());
		roomImpl.setUserName(room.getUserName());
		roomImpl.setCreateDate(room.getCreateDate());
		roomImpl.setModifiedDate(room.getModifiedDate());
		roomImpl.setBookingId(room.getBookingId());
		roomImpl.setRoomNumber(room.getRoomNumber());
		roomImpl.setLastPublishDate(room.getLastPublishDate());

		return roomImpl;
	}

	/**
	 * Returns the room with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the room
	 * @return the room
	 * @throws NoSuchRoomException if a room with the primary key could not be found
	 */
	@Override
	public Room findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRoomException {
		Room room = fetchByPrimaryKey(primaryKey);

		if (room == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRoomException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return room;
	}

	/**
	 * Returns the room with the primary key or throws a {@link NoSuchRoomException} if it could not be found.
	 *
	 * @param roomId the primary key of the room
	 * @return the room
	 * @throws NoSuchRoomException if a room with the primary key could not be found
	 */
	@Override
	public Room findByPrimaryKey(long roomId) throws NoSuchRoomException {
		return findByPrimaryKey((Serializable)roomId);
	}

	/**
	 * Returns the room with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the room
	 * @return the room, or <code>null</code> if a room with the primary key could not be found
	 */
	@Override
	public Room fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(RoomModelImpl.ENTITY_CACHE_ENABLED,
				RoomImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Room room = (Room)serializable;

		if (room == null) {
			Session session = null;

			try {
				session = openSession();

				room = (Room)session.get(RoomImpl.class, primaryKey);

				if (room != null) {
					cacheResult(room);
				}
				else {
					entityCache.putResult(RoomModelImpl.ENTITY_CACHE_ENABLED,
						RoomImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RoomModelImpl.ENTITY_CACHE_ENABLED,
					RoomImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return room;
	}

	/**
	 * Returns the room with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param roomId the primary key of the room
	 * @return the room, or <code>null</code> if a room with the primary key could not be found
	 */
	@Override
	public Room fetchByPrimaryKey(long roomId) {
		return fetchByPrimaryKey((Serializable)roomId);
	}

	@Override
	public Map<Serializable, Room> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Room> map = new HashMap<Serializable, Room>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Room room = fetchByPrimaryKey(primaryKey);

			if (room != null) {
				map.put(primaryKey, room);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(RoomModelImpl.ENTITY_CACHE_ENABLED,
					RoomImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Room)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ROOM_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (Room room : (List<Room>)q.list()) {
				map.put(room.getPrimaryKeyObj(), room);

				cacheResult(room);

				uncachedPrimaryKeys.remove(room.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RoomModelImpl.ENTITY_CACHE_ENABLED,
					RoomImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the rooms.
	 *
	 * @return the rooms
	 */
	@Override
	public List<Room> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rooms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rooms
	 * @param end the upper bound of the range of rooms (not inclusive)
	 * @return the range of rooms
	 */
	@Override
	public List<Room> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the rooms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rooms
	 * @param end the upper bound of the range of rooms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of rooms
	 */
	@Override
	public List<Room> findAll(int start, int end,
		OrderByComparator<Room> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the rooms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RoomModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rooms
	 * @param end the upper bound of the range of rooms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of rooms
	 */
	@Override
	public List<Room> findAll(int start, int end,
		OrderByComparator<Room> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Room> list = null;

		if (retrieveFromCache) {
			list = (List<Room>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ROOM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ROOM;

				if (pagination) {
					sql = sql.concat(RoomModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Room>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Room>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the rooms from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Room room : findAll()) {
			remove(room);
		}
	}

	/**
	 * Returns the number of rooms.
	 *
	 * @return the number of rooms
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ROOM);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
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
	protected Map<String, Integer> getTableColumnsMap() {
		return RoomModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the room persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RoomImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ROOM = "SELECT room FROM Room room";
	private static final String _SQL_SELECT_ROOM_WHERE_PKS_IN = "SELECT room FROM Room room WHERE roomId IN (";
	private static final String _SQL_SELECT_ROOM_WHERE = "SELECT room FROM Room room WHERE ";
	private static final String _SQL_COUNT_ROOM = "SELECT COUNT(room) FROM Room room";
	private static final String _SQL_COUNT_ROOM_WHERE = "SELECT COUNT(room) FROM Room room WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "room.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Room exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Room exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(RoomPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}