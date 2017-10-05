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
import com.liferay.hello.staging.constants.HelloStagingWebKeys;
import com.liferay.hello.staging.model.Booking;
import com.liferay.hello.staging.service.BookingLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltan Csaszi
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + HelloStagingPortletKeys.HELLO_STAGING,
		"mvc.command.name=/booking/detailed"
	},
	service = MVCRenderCommand.class
)
public class DetailedBookingMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		String backURL = ParamUtil.getString(renderRequest, "backURL");
		long bookingId = ParamUtil.getLong(
			renderRequest, HelloStagingConstants.BOOKING_ID);

		try {
			Booking booking = _bookingLocalService.getBooking(bookingId);

			renderRequest.setAttribute(HelloStagingWebKeys.BOOKING, booking);

			renderRequest.setAttribute("backURL", backURL);
		}
		catch (PortalException pe) {
			SessionErrors.add(renderRequest, pe.getClass());
			_log.error(pe.getMessage(), pe);

			return "/error.jsp";
		}

		return getPath();
	}

	protected String getPath() {
		return "/booking_detailed.jsp";
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DetailedBookingMVCRenderCommand.class);

	@Reference
	private BookingLocalService _bookingLocalService;

}