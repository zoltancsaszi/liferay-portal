<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
int bookingsCount = BookingLocalServiceUtil.getBookingsCount(scopeGroupId);
%>

<div class="container-fluid-1280">
	<div class="flex-container">
		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-results"
			total="<%= bookingsCount %>"
		>

			<%
			List<Booking> bookings = BookingLocalServiceUtil.getBookings(
				scopeGroupId,
				searchContainer.getStart(),
				searchContainer.getEnd());
			%>

			<liferay-ui:search-container-results
				results="<%= bookings %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.hello.staging.model.Booking"
				keyProperty="bookingId"
				modelVar="booking"
			>
				<liferay-ui:search-container-column-text
					name="booking-id"
					value="<%= String.valueOf(booking.getBookingId()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="booking-number"
					value="<%= HtmlUtil.escape(booking.getBookingNumber()) %>"
				/>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/booking_actions.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</div>
</div>