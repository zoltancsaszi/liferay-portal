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

package com.liferay.hello.staging.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.hello.staging.model.Room;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the room service. This utility wraps {@link com.liferay.hello.staging.service.persistence.impl.RoomPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RoomPersistence
 * @see com.liferay.hello.staging.service.persistence.impl.RoomPersistenceImpl
 * @generated
 */
@ProviderType
public class RoomUtil {
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
	public static void clearCache(Room room) {
		getPersistence().clearCache(room);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Room> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Room> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Room> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Room> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Room update(Room room) {
		return getPersistence().update(room);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Room update(Room room, ServiceContext serviceContext) {
		return getPersistence().update(room, serviceContext);
	}

	/**
	* Returns all the rooms where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching rooms
	*/
	public static List<Room> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
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
	public static List<Room> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
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
	public static List<Room> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Room> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
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
	public static List<Room> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Room> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first room in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching room
	* @throws NoSuchRoomException if a matching room could not be found
	*/
	public static Room findByUuid_First(java.lang.String uuid,
		OrderByComparator<Room> orderByComparator)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first room in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching room, or <code>null</code> if a matching room could not be found
	*/
	public static Room fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Room> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last room in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching room
	* @throws NoSuchRoomException if a matching room could not be found
	*/
	public static Room findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Room> orderByComparator)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last room in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching room, or <code>null</code> if a matching room could not be found
	*/
	public static Room fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Room> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
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
	public static Room[] findByUuid_PrevAndNext(long roomId,
		java.lang.String uuid, OrderByComparator<Room> orderByComparator)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence()
				   .findByUuid_PrevAndNext(roomId, uuid, orderByComparator);
	}

	/**
	* Removes all the rooms where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of rooms where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching rooms
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the room where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRoomException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching room
	* @throws NoSuchRoomException if a matching room could not be found
	*/
	public static Room findByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the room where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching room, or <code>null</code> if a matching room could not be found
	*/
	public static Room fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the room where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching room, or <code>null</code> if a matching room could not be found
	*/
	public static Room fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the room where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the room that was removed
	*/
	public static Room removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of rooms where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching rooms
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the rooms where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching rooms
	*/
	public static List<Room> findByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
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
	public static List<Room> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
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
	public static List<Room> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Room> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
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
	public static List<Room> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Room> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
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
	public static Room findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Room> orderByComparator)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first room in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching room, or <code>null</code> if a matching room could not be found
	*/
	public static Room fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Room> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
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
	public static Room findByUuid_C_Last(java.lang.String uuid, long companyId,
		OrderByComparator<Room> orderByComparator)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last room in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching room, or <code>null</code> if a matching room could not be found
	*/
	public static Room fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Room> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
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
	public static Room[] findByUuid_C_PrevAndNext(long roomId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Room> orderByComparator)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(roomId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the rooms where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of rooms where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching rooms
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the rooms where groupId = &#63; and bookingId = &#63;.
	*
	* @param groupId the group ID
	* @param bookingId the booking ID
	* @return the matching rooms
	*/
	public static List<Room> findByGroupIdAndBookingId(long groupId,
		long bookingId) {
		return getPersistence().findByGroupIdAndBookingId(groupId, bookingId);
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
	public static List<Room> findByGroupIdAndBookingId(long groupId,
		long bookingId, int start, int end) {
		return getPersistence()
				   .findByGroupIdAndBookingId(groupId, bookingId, start, end);
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
	public static List<Room> findByGroupIdAndBookingId(long groupId,
		long bookingId, int start, int end,
		OrderByComparator<Room> orderByComparator) {
		return getPersistence()
				   .findByGroupIdAndBookingId(groupId, bookingId, start, end,
			orderByComparator);
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
	public static List<Room> findByGroupIdAndBookingId(long groupId,
		long bookingId, int start, int end,
		OrderByComparator<Room> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupIdAndBookingId(groupId, bookingId, start, end,
			orderByComparator, retrieveFromCache);
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
	public static Room findByGroupIdAndBookingId_First(long groupId,
		long bookingId, OrderByComparator<Room> orderByComparator)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence()
				   .findByGroupIdAndBookingId_First(groupId, bookingId,
			orderByComparator);
	}

	/**
	* Returns the first room in the ordered set where groupId = &#63; and bookingId = &#63;.
	*
	* @param groupId the group ID
	* @param bookingId the booking ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching room, or <code>null</code> if a matching room could not be found
	*/
	public static Room fetchByGroupIdAndBookingId_First(long groupId,
		long bookingId, OrderByComparator<Room> orderByComparator) {
		return getPersistence()
				   .fetchByGroupIdAndBookingId_First(groupId, bookingId,
			orderByComparator);
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
	public static Room findByGroupIdAndBookingId_Last(long groupId,
		long bookingId, OrderByComparator<Room> orderByComparator)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence()
				   .findByGroupIdAndBookingId_Last(groupId, bookingId,
			orderByComparator);
	}

	/**
	* Returns the last room in the ordered set where groupId = &#63; and bookingId = &#63;.
	*
	* @param groupId the group ID
	* @param bookingId the booking ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching room, or <code>null</code> if a matching room could not be found
	*/
	public static Room fetchByGroupIdAndBookingId_Last(long groupId,
		long bookingId, OrderByComparator<Room> orderByComparator) {
		return getPersistence()
				   .fetchByGroupIdAndBookingId_Last(groupId, bookingId,
			orderByComparator);
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
	public static Room[] findByGroupIdAndBookingId_PrevAndNext(long roomId,
		long groupId, long bookingId, OrderByComparator<Room> orderByComparator)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence()
				   .findByGroupIdAndBookingId_PrevAndNext(roomId, groupId,
			bookingId, orderByComparator);
	}

	/**
	* Removes all the rooms where groupId = &#63; and bookingId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param bookingId the booking ID
	*/
	public static void removeByGroupIdAndBookingId(long groupId, long bookingId) {
		getPersistence().removeByGroupIdAndBookingId(groupId, bookingId);
	}

	/**
	* Returns the number of rooms where groupId = &#63; and bookingId = &#63;.
	*
	* @param groupId the group ID
	* @param bookingId the booking ID
	* @return the number of matching rooms
	*/
	public static int countByGroupIdAndBookingId(long groupId, long bookingId) {
		return getPersistence().countByGroupIdAndBookingId(groupId, bookingId);
	}

	/**
	* Returns all the rooms where groupId = &#63; and roomNumber = &#63;.
	*
	* @param groupId the group ID
	* @param roomNumber the room number
	* @return the matching rooms
	*/
	public static List<Room> findByGroupIdAndRoomNumber(long groupId,
		java.lang.String roomNumber) {
		return getPersistence().findByGroupIdAndRoomNumber(groupId, roomNumber);
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
	public static List<Room> findByGroupIdAndRoomNumber(long groupId,
		java.lang.String roomNumber, int start, int end) {
		return getPersistence()
				   .findByGroupIdAndRoomNumber(groupId, roomNumber, start, end);
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
	public static List<Room> findByGroupIdAndRoomNumber(long groupId,
		java.lang.String roomNumber, int start, int end,
		OrderByComparator<Room> orderByComparator) {
		return getPersistence()
				   .findByGroupIdAndRoomNumber(groupId, roomNumber, start, end,
			orderByComparator);
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
	public static List<Room> findByGroupIdAndRoomNumber(long groupId,
		java.lang.String roomNumber, int start, int end,
		OrderByComparator<Room> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupIdAndRoomNumber(groupId, roomNumber, start, end,
			orderByComparator, retrieveFromCache);
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
	public static Room findByGroupIdAndRoomNumber_First(long groupId,
		java.lang.String roomNumber, OrderByComparator<Room> orderByComparator)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence()
				   .findByGroupIdAndRoomNumber_First(groupId, roomNumber,
			orderByComparator);
	}

	/**
	* Returns the first room in the ordered set where groupId = &#63; and roomNumber = &#63;.
	*
	* @param groupId the group ID
	* @param roomNumber the room number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching room, or <code>null</code> if a matching room could not be found
	*/
	public static Room fetchByGroupIdAndRoomNumber_First(long groupId,
		java.lang.String roomNumber, OrderByComparator<Room> orderByComparator) {
		return getPersistence()
				   .fetchByGroupIdAndRoomNumber_First(groupId, roomNumber,
			orderByComparator);
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
	public static Room findByGroupIdAndRoomNumber_Last(long groupId,
		java.lang.String roomNumber, OrderByComparator<Room> orderByComparator)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence()
				   .findByGroupIdAndRoomNumber_Last(groupId, roomNumber,
			orderByComparator);
	}

	/**
	* Returns the last room in the ordered set where groupId = &#63; and roomNumber = &#63;.
	*
	* @param groupId the group ID
	* @param roomNumber the room number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching room, or <code>null</code> if a matching room could not be found
	*/
	public static Room fetchByGroupIdAndRoomNumber_Last(long groupId,
		java.lang.String roomNumber, OrderByComparator<Room> orderByComparator) {
		return getPersistence()
				   .fetchByGroupIdAndRoomNumber_Last(groupId, roomNumber,
			orderByComparator);
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
	public static Room[] findByGroupIdAndRoomNumber_PrevAndNext(long roomId,
		long groupId, java.lang.String roomNumber,
		OrderByComparator<Room> orderByComparator)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence()
				   .findByGroupIdAndRoomNumber_PrevAndNext(roomId, groupId,
			roomNumber, orderByComparator);
	}

	/**
	* Removes all the rooms where groupId = &#63; and roomNumber = &#63; from the database.
	*
	* @param groupId the group ID
	* @param roomNumber the room number
	*/
	public static void removeByGroupIdAndRoomNumber(long groupId,
		java.lang.String roomNumber) {
		getPersistence().removeByGroupIdAndRoomNumber(groupId, roomNumber);
	}

	/**
	* Returns the number of rooms where groupId = &#63; and roomNumber = &#63;.
	*
	* @param groupId the group ID
	* @param roomNumber the room number
	* @return the number of matching rooms
	*/
	public static int countByGroupIdAndRoomNumber(long groupId,
		java.lang.String roomNumber) {
		return getPersistence().countByGroupIdAndRoomNumber(groupId, roomNumber);
	}

	/**
	* Returns the room where groupId = &#63; and bookingId = &#63; and roomNumber = &#63; or throws a {@link NoSuchRoomException} if it could not be found.
	*
	* @param groupId the group ID
	* @param bookingId the booking ID
	* @param roomNumber the room number
	* @return the matching room
	* @throws NoSuchRoomException if a matching room could not be found
	*/
	public static Room findByGroupIdAndBookingIdAndRoomNumber(long groupId,
		long bookingId, java.lang.String roomNumber)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence()
				   .findByGroupIdAndBookingIdAndRoomNumber(groupId, bookingId,
			roomNumber);
	}

	/**
	* Returns the room where groupId = &#63; and bookingId = &#63; and roomNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param bookingId the booking ID
	* @param roomNumber the room number
	* @return the matching room, or <code>null</code> if a matching room could not be found
	*/
	public static Room fetchByGroupIdAndBookingIdAndRoomNumber(long groupId,
		long bookingId, java.lang.String roomNumber) {
		return getPersistence()
				   .fetchByGroupIdAndBookingIdAndRoomNumber(groupId, bookingId,
			roomNumber);
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
	public static Room fetchByGroupIdAndBookingIdAndRoomNumber(long groupId,
		long bookingId, java.lang.String roomNumber, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByGroupIdAndBookingIdAndRoomNumber(groupId, bookingId,
			roomNumber, retrieveFromCache);
	}

	/**
	* Removes the room where groupId = &#63; and bookingId = &#63; and roomNumber = &#63; from the database.
	*
	* @param groupId the group ID
	* @param bookingId the booking ID
	* @param roomNumber the room number
	* @return the room that was removed
	*/
	public static Room removeByGroupIdAndBookingIdAndRoomNumber(long groupId,
		long bookingId, java.lang.String roomNumber)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence()
				   .removeByGroupIdAndBookingIdAndRoomNumber(groupId,
			bookingId, roomNumber);
	}

	/**
	* Returns the number of rooms where groupId = &#63; and bookingId = &#63; and roomNumber = &#63;.
	*
	* @param groupId the group ID
	* @param bookingId the booking ID
	* @param roomNumber the room number
	* @return the number of matching rooms
	*/
	public static int countByGroupIdAndBookingIdAndRoomNumber(long groupId,
		long bookingId, java.lang.String roomNumber) {
		return getPersistence()
				   .countByGroupIdAndBookingIdAndRoomNumber(groupId, bookingId,
			roomNumber);
	}

	/**
	* Caches the room in the entity cache if it is enabled.
	*
	* @param room the room
	*/
	public static void cacheResult(Room room) {
		getPersistence().cacheResult(room);
	}

	/**
	* Caches the rooms in the entity cache if it is enabled.
	*
	* @param rooms the rooms
	*/
	public static void cacheResult(List<Room> rooms) {
		getPersistence().cacheResult(rooms);
	}

	/**
	* Creates a new room with the primary key. Does not add the room to the database.
	*
	* @param roomId the primary key for the new room
	* @return the new room
	*/
	public static Room create(long roomId) {
		return getPersistence().create(roomId);
	}

	/**
	* Removes the room with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param roomId the primary key of the room
	* @return the room that was removed
	* @throws NoSuchRoomException if a room with the primary key could not be found
	*/
	public static Room remove(long roomId)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence().remove(roomId);
	}

	public static Room updateImpl(Room room) {
		return getPersistence().updateImpl(room);
	}

	/**
	* Returns the room with the primary key or throws a {@link NoSuchRoomException} if it could not be found.
	*
	* @param roomId the primary key of the room
	* @return the room
	* @throws NoSuchRoomException if a room with the primary key could not be found
	*/
	public static Room findByPrimaryKey(long roomId)
		throws com.liferay.hello.staging.exception.NoSuchRoomException {
		return getPersistence().findByPrimaryKey(roomId);
	}

	/**
	* Returns the room with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param roomId the primary key of the room
	* @return the room, or <code>null</code> if a room with the primary key could not be found
	*/
	public static Room fetchByPrimaryKey(long roomId) {
		return getPersistence().fetchByPrimaryKey(roomId);
	}

	public static java.util.Map<java.io.Serializable, Room> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the rooms.
	*
	* @return the rooms
	*/
	public static List<Room> findAll() {
		return getPersistence().findAll();
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
	public static List<Room> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<Room> findAll(int start, int end,
		OrderByComparator<Room> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<Room> findAll(int start, int end,
		OrderByComparator<Room> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the rooms from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of rooms.
	*
	* @return the number of rooms
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static RoomPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RoomPersistence, RoomPersistence> _serviceTracker =
		ServiceTrackerFactory.open(RoomPersistence.class);
}