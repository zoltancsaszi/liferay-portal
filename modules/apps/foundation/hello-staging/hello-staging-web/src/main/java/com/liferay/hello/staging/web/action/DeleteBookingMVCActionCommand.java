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

package com.liferay.hello.staging.web.action;

import com.liferay.hello.staging.constants.HelloStagingConstants;
import com.liferay.hello.staging.constants.HelloStagingPortletKeys;
import com.liferay.hello.staging.service.BookingLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltan Csaszi
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + HelloStagingPortletKeys.HELLO_STAGING,
		"mvc.command.name=/booking/delete"
	},
	service = MVCActionCommand.class
)
public class DeleteBookingMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long bookingId = ParamUtil.getLong(
			actionRequest, HelloStagingConstants.BOOKING_ID);

		_bookingLocalService.deleteBooking(bookingId);
	}

	@Reference
	private BookingLocalService _bookingLocalService;

}