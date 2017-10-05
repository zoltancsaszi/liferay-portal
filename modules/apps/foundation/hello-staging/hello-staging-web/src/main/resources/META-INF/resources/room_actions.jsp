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

Room room = (Room)row.getObject();

String backUrl = ParamUtil.getString(request, "backURL", GetterUtil.getString(request.getAttribute("backURL")));
%>

<liferay-ui:icon-menu>
	<portlet:actionURL name="/room/delete" var="deleteRoomURL">
		<portlet:param
			name="mvcRenderCommandName"
			value="/booking/detailed"
		/>

		<portlet:param name="<%= HelloStagingConstants.BOOKING_ID %>"
			value="<%= String.valueOf(room.getBookingId()) %>"
		/>

		<portlet:param name="<%= HelloStagingConstants.ROOM_ID %>"
			value="<%= String.valueOf(room.getRoomId()) %>"
		/>

		<portlet:param name="backURL" value="<%= backUrl %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		message="delete"
		trash="<%= false %>"
		url="<%= deleteRoomURL.toString() %>"

	/>
</liferay-ui:icon-menu>