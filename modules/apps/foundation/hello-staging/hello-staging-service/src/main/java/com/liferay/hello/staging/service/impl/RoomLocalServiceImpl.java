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

package com.liferay.hello.staging.service.impl;

import com.liferay.hello.staging.exception.AlreadyUsedRoomNumberException;
import com.liferay.hello.staging.exception.EmptyRoomNumberException;
import com.liferay.hello.staging.model.Booking;
import com.liferay.hello.staging.model.Room;
import com.liferay.hello.staging.service.base.RoomLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the room local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link RoomLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RoomLocalServiceBaseImpl
 * @see RoomLocalServiceUtil
 */
public class RoomLocalServiceImpl extends RoomLocalServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link RoomLocalServiceUtil} to access the room local service.
	 */

	@Override
	public Room addRoom(
			long userId, long groupId, long bookingId, String roomNumber,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validateAdd(groupId, bookingId, roomNumber);

		long roomId = counterLocalService.increment();

		Room room = roomPersistence.create(roomId);

		room.setGroupId(groupId);
		room.setCompanyId(user.getCompanyId());
		room.setUserId(userId);
		room.setUserName(user.getFullName());
		room.setCreateDate(serviceContext.getCreateDate(now));
		room.setModifiedDate(serviceContext.getModifiedDate(now));
		room.setBookingId(bookingId);
		room.setRoomNumber(roomNumber);

		roomPersistence.update(room);

		return room;
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public Room deleteRoom(Room room) {
		return roomPersistence.remove(room);
	}

	@Override
	public void deleteRooms(long groupId, long bookingId) {
		List<Room> rooms = roomPersistence.findByGroupIdAndBookingId(
			groupId, bookingId);

		for (Room room : rooms) {
			roomLocalService.deleteRoom(room);
		}
	}

	@Override
	public int getRoomCount(long groupId, long bookingId) {
		return roomPersistence.countByGroupIdAndBookingId(groupId, bookingId);
	}

	@Override
	public List<Room> getRooms(
		long groupId, long bookingId, int start, int end) {

		return roomPersistence.findByGroupIdAndBookingId(
			groupId, bookingId, start, end);
	}

	@Override
	public Room updateRoom(
			long userId, long roomId, long bookingId, String roomNumber,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		Room room = roomPersistence.findByPrimaryKey(roomId);

		validate(room.getGroupId(), bookingId, roomNumber);

		room.setUserId(userId);
		room.setUserName(user.getFullName());
		room.setModifiedDate(serviceContext.getModifiedDate(new Date()));
		room.setBookingId(bookingId);
		room.setRoomNumber(roomNumber);

		return roomPersistence.update(room);
	}

	protected void validate(long groupId, long bookingId, String roomNumber)
		throws PortalException {

		Booking booking = bookingLocalService.findByGroupIdAndBookingId(
			groupId, bookingId);

		if (Validator.isNull(roomNumber)) {
			throw new EmptyRoomNumberException();
		}
	}

	protected void validateAdd(long groupId, long bookingId, String roomNumber)
		throws PortalException {

		validate(groupId, bookingId, roomNumber);

		Room room = roomPersistence.fetchByGroupIdAndRoomNumber_First(
			groupId, roomNumber, null);

		if (Validator.isNotNull(room)) {
			throw new AlreadyUsedRoomNumberException();
		}
	}

	protected void validateUpdate(
			long groupId, Room room, long bookingId, String roomNumber)
		throws PortalException {

		validate(groupId, bookingId, roomNumber);

		if (!room.getRoomNumber().equals(roomNumber)) {
			Room otherRoom = roomPersistence.fetchByGroupIdAndRoomNumber_First(
				groupId, roomNumber, null);

			if (Validator.isNotNull(otherRoom)) {
				throw new AlreadyUsedRoomNumberException();
			}
		}
	}

}