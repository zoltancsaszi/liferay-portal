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

<%@ page import="com.liferay.portal.kernel.backgroundtask.BackgroundTask" %>
<%@ page import="java.text.Format" %>
<%@ page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="com.liferay.portal.kernel.util.FastDateFormatConstants" %>


<%@ include file="/init.jsp" %>

<%
BackgroundTask backgroundTask = (BackgroundTask)request.getAttribute("liferay-staging:process-startdate:backgroundTask");

boolean listView = ((Boolean)request.getAttribute("liferay-staging:process-startdate:listView")).booleanValue();

Date startDate = backgroundTask.getCreateDate();


//Locale locale = themeDisplay.getLocale();
//Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, (TimeZone)localeAndTimeZone[1]);
//String timeDescription = LanguageUtil.getTimeDescription(themeDisplay.getLocale(), duration, true);
//

Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(FastDateFormatConstants.MEDIUM, FastDateFormatConstants.SHORT, locale, timeZone);

%>