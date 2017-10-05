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

package com.liferay.hello.staging.lar;

import com.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.hello.staging.model.Booking;
import com.liferay.hello.staging.model.Room;
import com.liferay.hello.staging.service.RoomLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltan Csaszi
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class RoomStagedModelDataHandler
	extends BaseStagedModelDataHandler<Room> {

	public static final String[] CLASS_NAMES = {Room.class.getName()};

	@Override
	public void deleteStagedModel(Room room) {
		_roomLocalService.deleteRoom(room);
	}

	@Override
	public void deleteStagedModel(
		String uuid, long groupId, String className, String extraData) {

		Room room = fetchStagedModelByUuidAndGroupId(uuid, groupId);

		if (room != null) {
			deleteStagedModel(room);
		}
	}

	@Override
	public Room fetchStagedModelByUuidAndGroupId(String uuid, long groupId) {
		return _roomLocalService.fetchRoomByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public List<Room> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return _roomLocalService.getRoomsByUuidAndCompanyId(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new StagedModelModifiedDateComparator<Room>());
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(Room room) {
		return room.getRoomNumber();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, Room room)
		throws Exception {

		Element roomElement = portletDataContext.getExportDataElement(room);

		portletDataContext.addClassedModel(
			roomElement, ExportImportPathUtil.getModelPath(room), room);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, Room room)
		throws Exception {

		Map<Long, Long> bookingIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Booking.class);

		long bookingId = MapUtil.getLong(
			bookingIds, room.getBookingId(), room.getBookingId());

		long userId = portletDataContext.getUserId(room.getUserUuid());

		Room importedRoom = (Room)room.clone();

		importedRoom.setGroupId(portletDataContext.getScopeGroupId());
		importedRoom.setBookingId(bookingId);

		Room existingRoom = _roomLocalService.fetchRoomByUuidAndGroupId(
			room.getUuid(), portletDataContext.getScopeGroupId());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			room);

		if (portletDataContext.isDataStrategyMirror()) {
			if (existingRoom == null) {
				serviceContext.setUuid(room.getUuid());

				importedRoom = _roomLocalService.addRoom(
					userId, portletDataContext.getScopeGroupId(), bookingId,
					room.getRoomNumber(), serviceContext);
			}
			else {
				importedRoom = _roomLocalService.updateRoom(
					userId, existingRoom.getRoomId(), bookingId,
					room.getRoomNumber(), serviceContext);
			}
		}
		else {
			importedRoom = _roomLocalService.addRoom(
				userId, portletDataContext.getScopeGroupId(), bookingId,
				room.getRoomNumber(), serviceContext);
		}

		portletDataContext.importClassedModel(room, importedRoom);
	}

	@Reference
	private RoomLocalService _roomLocalService;

}