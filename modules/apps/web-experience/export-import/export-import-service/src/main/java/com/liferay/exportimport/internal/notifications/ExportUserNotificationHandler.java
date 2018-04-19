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

package com.liferay.exportimport.internal.notifications;

import com.liferay.exportimport.constants.ExportImportPortletKeys;
import com.liferay.exportimport.notifications.ExportImportNotificationHelper;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.notifications.BaseUserNotificationHandler;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;
import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltan Csaszi
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + ExportImportPortletKeys.EXPORT,
	service = UserNotificationHandler.class
)
public class ExportUserNotificationHandler extends BaseUserNotificationHandler {

	public ExportUserNotificationHandler() {
		setOpenDialog(true);
		setPortletId(ExportImportPortletKeys.EXPORT);
	}

	@Override
	protected String getBody(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		return _exportImportNotificationHelper.getBody(
			userNotificationEvent, serviceContext.getLocale());
	}

	@Override
	protected String getLink(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		return _exportImportNotificationHelper.getLink(
			userNotificationEvent, serviceContext.getLiferayPortletRequest(),
			serviceContext.getCurrentURL());
	}

	@Reference
	private ExportImportNotificationHelper _exportImportNotificationHelper;

}