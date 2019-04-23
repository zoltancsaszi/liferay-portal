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
	String changeListName = ParamUtil.getString(request, "changeListName");
	String changeListDescription = ParamUtil.getString(request, "changeListDescription");
	long ctCollectionId = ParamUtil.getLong(request, "ctCollectionId");

	CTCollection ctCollection = CTCollectionLocalServiceUtil.fetchCTCollection(ctCollectionId);

	if (ctCollection != null) {

		changeListName = ctCollection.getName();
		changeListDescription = ctCollection.getDescription();
	}
%>
<div class="change-list-publish-modal modal-body">
	<liferay-portlet:actionURL name="/change_lists/publish_ct_collection" var="publishCollectionURL">
		<portlet:param name="ctCollectionId" value="<%= String.valueOf(ctCollectionId) %>" />
	</liferay-portlet:actionURL>

	<aui:form action="<%= publishCollectionURL.toString() %>" method="POST" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "submitForm()"%>'>
		<h4><liferay-ui:message key="change-list"/></h4>

		<div class="sheet-text">
			<%= changeListName%>
		</div>

		<h4><liferay-ui:message key="description"/></h4>

		<div class="sheet-text">
			<%= changeListDescription %>
		</div>

		<aui:input label="ignore-collision" name="ignoreCollision" type="checkbox" />

		<aui:input label="schedule-publication" name="schedulePublication" type="checkbox" />

		<aui:button-row>
			<aui:button onClick='<%= renderResponse.getNamespace() + "closeModal(true);"%>' value="cancel"/>
			<aui:button type="submit" value="publish" />
		</aui:button-row>
	</aui:form>


	<script>
		function <portlet:namespace/>closeModal(destroy) {
			Liferay.Util.getWindow('<portlet:namespace/>publishIconDialog').hide();

			if (destroy) {
				Liferay.Util.getWindow('<portlet:namespace/>publishIconDialog').destroy();
			}
		}

		function <portlet:namespace/>submitForm(event) {

			var form = AUI().one('#<portlet:namespace/>fm');

			Liferay.Util.getOpener().Liferay.fire("refreshSelectChangeList");

			Liferay.Util.submitForm(form);

			<portlet:namespace/>closeModal(false);
		}
	</script>
</div>