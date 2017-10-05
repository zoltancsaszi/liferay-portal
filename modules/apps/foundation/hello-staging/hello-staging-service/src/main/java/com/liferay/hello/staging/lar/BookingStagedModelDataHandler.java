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
import com.liferay.hello.staging.service.BookingLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltan Csaszi
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class BookingStagedModelDataHandler
	extends BaseStagedModelDataHandler<Booking> {

	public static final String[] CLASS_NAMES = {Booking.class.getName()};

	@Override
	public void deleteStagedModel(Booking booking) {
		_bookingLocalService.deleteBooking(booking);
	}

	@Override
	public void deleteStagedModel(
		String uuid, long groupId, String className, String extraData) {

		Booking booking = fetchStagedModelByUuidAndGroupId(uuid, groupId);

		if (booking != null) {
			deleteStagedModel(booking);
		}
	}

	@Override
	public Booking fetchStagedModelByUuidAndGroupId(String uuid, long groupId) {
		return _bookingLocalService.fetchBookingByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public List<Booking> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return _bookingLocalService.getBookingsByUuidAndCompanyId(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new StagedModelModifiedDateComparator<Booking>());
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(Booking booking) {
		return booking.getBookingNumber();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, Booking booking)
		throws Exception {

		Element bookingElement = portletDataContext.getExportDataElement(
			booking);

		portletDataContext.addClassedModel(
			bookingElement, ExportImportPathUtil.getModelPath(booking),
			booking);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, Booking booking)
		throws Exception {

		long userId = portletDataContext.getUserId(booking.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			booking);

		Booking importedBooking = null;

		if (portletDataContext.isDataStrategyMirror()) {
			Booking existingBooking = fetchStagedModelByUuidAndGroupId(
				booking.getUuid(), portletDataContext.getScopeGroupId());

			if (existingBooking == null) {
				serviceContext.setUuid(booking.getUuid());

				importedBooking = _bookingLocalService.addBooking(
					userId, portletDataContext.getScopeGroupId(),
					booking.getBookingNumber(), serviceContext);
			}
			else {
				importedBooking = _bookingLocalService.updateBooking(
					userId, existingBooking.getBookingId(),
					booking.getBookingNumber(), serviceContext);
			}
		}
		else {
			importedBooking = _bookingLocalService.addBooking(
				userId, portletDataContext.getScopeGroupId(),
				booking.getBookingNumber(), serviceContext);
		}

		portletDataContext.importClassedModel(booking, importedBooking);
	}

	@Reference
	private BookingLocalService _bookingLocalService;

}