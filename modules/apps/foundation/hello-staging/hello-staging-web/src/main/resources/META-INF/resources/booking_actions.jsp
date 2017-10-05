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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Booking booking = (Booking)row.getObject();
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="viewBookingURL">
		<portlet:param name="mvcRenderCommandName" value="/booking/detailed" />
		<portlet:param name="<%= HelloStagingConstants.CMD %>" value="<%= HelloStagingConstants.VIEW %>" />
		<portlet:param name="<%= HelloStagingConstants.BOOKING_ID %>" value="<%= String.valueOf(booking.getBookingId()) %>" />
		<portlet:param name="backURL" value="<%= currentURL %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="view-details"
		url="<%= viewBookingURL %>"

	/>

	<c:if test="<%= stagingDisplayContext.hasStagingPermission() %>">
		<portlet:actionURL name="/booking/delete" var="deleteBookingURL">
			<portlet:param name="<%= HelloStagingConstants.BOOKING_ID %>"
				value="<%= String.valueOf(booking.getBookingId()) %>"
			/>
		</portlet:actionURL>

		<liferay-ui:icon-delete
			message="delete"
			trash="<%= false %>"
			url="<%= deleteBookingURL.toString() %>"

		/>
	</c:if>
</liferay-ui:icon-menu>