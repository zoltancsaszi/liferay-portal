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
String backURL = ParamUtil.getString(request, "backURL");

Booking booking = (Booking)request.getAttribute(HelloStagingWebKeys.BOOKING);

long bookingId = BeanParamUtil.getLong(booking, request, HelloStagingConstants.BOOKING_ID);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(backURL);

renderResponse.setTitle(
	LanguageUtil.get(request, "detailed-title") +
	booking.getBookingNumber());

int roomsCount = RoomLocalServiceUtil.getRoomCount(scopeGroupId, bookingId);
%>

<c:if test="<%= stagingDisplayContext.hasStagingPermission() %>">
	<liferay-ui:error
		key="<%= AlreadyUsedRoomNumberException.class.getName() %>"
		message="the-room-is-already-occupied"
	/>

	<portlet:actionURL name="/room/add" var="addRoomURL">
		<portlet:param
			name="<%= HelloStagingConstants.BOOKING_ID %>"
			value="<%= String.valueOf(bookingId) %>"
		/>

		<portlet:param name="mvcRenderCommandName" value="/booking/detailed" />
		<portlet:param name="backURL" value="<%= backURL %>" />
	</portlet:actionURL>

	<div id="addButtonContainer">
		<liferay-frontend:add-menu>
			<liferay-frontend:add-menu-item
				title='<%= LanguageUtil.get(request, "add-sample-room") %>'
				url="<%= addRoomURL %>"
			/>
		</liferay-frontend:add-menu>
	</div>
</c:if>

<div class="container-fluid-1280">
	<div class="flex-container">
		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-results"
			total="<%= roomsCount %>"
		>

			<%
			List<Room> rooms = RoomLocalServiceUtil.getRooms(
				scopeGroupId,
				bookingId,
				searchContainer.getStart(),
				searchContainer.getEnd());
			%>

			<liferay-ui:search-container-results
				results="<%= rooms %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.hello.staging.model.Room"
				keyProperty="roomId"
				modelVar="room"
			>
				<liferay-ui:search-container-column-text
					name="room-id"
					value="<%= String.valueOf(room.getRoomId()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="room-number"
					value="<%= HtmlUtil.escape(room.getRoomNumber()) %>"
				/>

				<c:if test="<%= stagingDisplayContext.hasStagingPermission() %>">
					<liferay-ui:search-container-column-jsp
						align="right"
						path="/room_actions.jsp"
					/>
				</c:if>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</div>
</div>