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

<%@ page import="com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil" %>

<%@ page import="javax.portlet.PortletPreferences" %>

<%
String cssClass = GetterUtil.getString(request.getAttribute("liferay-staging:status:cssClass"));
StagedModel stagedModel = (StagedModel)request.getAttribute("liferay-staging:status:stagedModel");
String portletId = GetterUtil.getString(request.getAttribute("liferay-staging:status:portletId"), themeDisplay.getPpid());
boolean simple = GetterUtil.getBoolean(request.getAttribute("liferay-staging:status:simple"));

Date lastPublishDate = null;
Date modifiedDate = null;

if ((stagedModel != null) && (stagedModel instanceof StagedGroupedModel)) {
	StagedGroupedModel stagedGroupedModel = (StagedGroupedModel)stagedModel;

	PortletPreferences jxPortletPreferences = PortletPreferencesFactoryUtil.getStrictPortletSetup(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), portletId);

	Date layoutLastPublishDate = ExportImportDateUtil.getLastPublishDate(themeDisplay.getLayoutSet());
	Date preferencesLastPublishDate = ExportImportDateUtil.getLastPublishDate(jxPortletPreferences);

	if (layoutLastPublishDate.after(preferencesLastPublishDate)) {
		lastPublishDate = layoutLastPublishDate;
	}
	else {
		lastPublishDate = preferencesLastPublishDate;
	}

	modifiedDate = stagedGroupedModel.getModifiedDate();
}

boolean published = false;

Group themeDisplayScopeGroup = themeDisplay.getScopeGroup();

boolean stagedPortlet = group.isInStagingPortlet(portletDisplay.getId());

if (stagedPortlet) {
	if (lastPublishDate == null) {
		lastPublishDate = modifiedDate;
	}

	if ((lastPublishDate != null) && lastPublishDate.after(modifiedDate)) {
		published = true;
	}

	if (Validator.isNull(cssClass)) {
		cssClass = "label-success";

		if (!published) {
			cssClass = "label-warning";
		}
	}
}
%>