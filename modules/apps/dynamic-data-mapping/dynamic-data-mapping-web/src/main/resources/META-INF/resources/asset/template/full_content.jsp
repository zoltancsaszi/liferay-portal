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

<%@ include file="/META-INF/resources/init.jsp" %>

<%
	DDMTemplateVersion ddmTemplateVersion = (DDMTemplateVersion)request.getAttribute(DDMWebKeys.DYNAMIC_DATA_MAPPING_TEMPLATE_VERSION);

	PortletURL portletURL = PortletURLFactoryUtil.create(request, DDMPortletKeys.DYNAMIC_DATA_MAPPING, PortletRequest.RENDER_PHASE);

	portletURL.setParameter("mvcPath", "edit_template.jsp");
%>

<strong><liferay-ui:message key="template" />:</strong> <a href="<%= portletURL.toString() %></a><br />

<strong><liferay-ui:message key="name" />:</strong> <%= HtmlUtil.escape(ddmTemplateVersion.getName(locale)) %><br />

<strong><liferay-ui:message key="version" />:</strong> <%= ddmTemplateVersion.getVersion() %>