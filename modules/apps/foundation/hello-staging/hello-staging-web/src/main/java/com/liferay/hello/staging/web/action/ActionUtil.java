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
import com.liferay.hello.staging.model.Booking;
import com.liferay.hello.staging.service.BookingLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zoltan Csaszi
 */
public class ActionUtil {

	public static Booking getBooking(HttpServletRequest request)
		throws Exception {

		long sampleLARBookingId = ParamUtil.getLong(
			request, HelloStagingConstants.BOOKING_ID);

		Booking sampleLARBooking = null;

		if (sampleLARBookingId > GetterUtil.DEFAULT_LONG) {
			sampleLARBooking = BookingLocalServiceUtil.getBooking(
				sampleLARBookingId);
		}

		return sampleLARBooking;
	}

	public static Booking getBooking(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		return getBooking(request);
	}

}