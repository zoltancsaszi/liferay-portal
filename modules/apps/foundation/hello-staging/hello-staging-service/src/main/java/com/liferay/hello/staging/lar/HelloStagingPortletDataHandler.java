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

import com.liferay.exportimport.kernel.lar.BasePortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.exportimport.kernel.xstream.XStreamAliasRegistryUtil;
import com.liferay.hello.staging.constants.HelloStagingPortletKeys;
import com.liferay.hello.staging.model.Booking;
import com.liferay.hello.staging.model.Room;
import com.liferay.hello.staging.model.impl.BookingImpl;
import com.liferay.hello.staging.model.impl.RoomImpl;
import com.liferay.hello.staging.service.BookingLocalService;
import com.liferay.hello.staging.service.RoomLocalService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltan Csaszi
 */
@Component(
	immediate = true,
	property = {"javax.portlet.name=" + HelloStagingPortletKeys.HELLO_STAGING},
	service = PortletDataHandler.class
)
public class HelloStagingPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "samplelar";

	@Activate
	protected void activate() {
		setDeletionSystemEventStagedModelTypes(
			new StagedModelType(Booking.class),
			new StagedModelType(Room.class));

		setExportControls(
			new PortletDataHandlerBoolean(
				NAMESPACE, "booking", true, false, null,
				Booking.class.getName()));

		setImportControls(getExportControls());

		XStreamAliasRegistryUtil.register(BookingImpl.class, "Booking");
		XStreamAliasRegistryUtil.register(RoomImpl.class, "Room");
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (portletDataContext.addPrimaryKey(
				HelloStagingPortletDataHandler.class, "deleteData")) {

			return portletPreferences;
		}

		_bookingLocalService.deleteBookings(
			portletDataContext.getScopeGroupId());

		return portletPreferences;
	}

	@Override
	protected String doExportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		Element rootElement = addExportDataRootElement(portletDataContext);

		if (!portletDataContext.getBooleanParameter(NAMESPACE, "booking")) {
			return getExportDataRootElementString(rootElement);
		}

		rootElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getScopeGroupId()));

		ActionableDynamicQuery bookingActionableDynamicQuery =
			_bookingLocalService.getExportActionableDynamicQuery(
				portletDataContext);

		bookingActionableDynamicQuery.performActions();

		ActionableDynamicQuery roomActionableDynamicQuery =
			_roomLocalService.getExportActionableDynamicQuery(
				portletDataContext);

		roomActionableDynamicQuery.performActions();

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		if (!portletDataContext.getBooleanParameter(NAMESPACE, "booking")) {
			return null;
		}

		Element bookingsElement = portletDataContext.getImportDataGroupElement(
			Booking.class);

		List<Element> bookingElements = bookingsElement.elements();

		for (Element bookingElement : bookingElements) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, bookingElement);
		}

		Element roomsElement = portletDataContext.getImportDataGroupElement(
			Room.class);

		List<Element> roomElements = roomsElement.elements();

		for (Element roomElement : roomElements) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, roomElement);
		}

		return null;
	}

	@Override
	protected void doPrepareManifestSummary(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws Exception {

		ActionableDynamicQuery bookingActionableDynamicQuery =
			_bookingLocalService.getExportActionableDynamicQuery(
				portletDataContext);

		bookingActionableDynamicQuery.performCount();

		ActionableDynamicQuery roomActionableDynamicQuery =
			_roomLocalService.getExportActionableDynamicQuery(
				portletDataContext);

		roomActionableDynamicQuery.performCount();
	}

	@Reference
	private BookingLocalService _bookingLocalService;

	@Reference
	private RoomLocalService _roomLocalService;

}