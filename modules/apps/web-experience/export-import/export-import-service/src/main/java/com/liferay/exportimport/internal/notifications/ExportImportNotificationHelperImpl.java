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
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalService;
import com.liferay.exportimport.notifications.ExportImportNotificationHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.background.task.model.BackgroundTask;
import com.liferay.portal.background.task.service.BackgroundTaskLocalService;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltan Csaszi
 */
@Component(immediate = true, service = ExportImportNotificationHelper.class)
public class ExportImportNotificationHelperImpl
	implements ExportImportNotificationHelper {

	@Override
	public String getBody(
			UserNotificationEvent userNotificationEvent, Locale locale)
		throws Exception {

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(locale);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			userNotificationEvent.getPayload());

		ExportImportConfiguration exportImportConfiguration = null;

		try {
			exportImportConfiguration =
				_exportImportConfigurationLocalService.
					getExportImportConfiguration(
						jsonObject.getLong("exportImportConfigurationId"));
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			return LanguageUtil.get(
				resourceBundle,
				"the-process-referenced-by-this-notification-does-not-exist");
		}

		String message =
			"x-" +
				ExportImportConfigurationConstants.getTypeLabel(
					exportImportConfiguration.getType());

		int status = jsonObject.getInt("status");

		if (status == BackgroundTaskConstants.STATUS_SUCCESSFUL) {
			message += "-process-finished-successfully";
		}
		else if (status == BackgroundTaskConstants.STATUS_FAILED) {
			message += "-process-failed";
		}
		else {
			return "Unable to process notification: " +
				HtmlUtil.escape(jsonObject.toString());
		}

		long backgroundTaskId = jsonObject.getLong("backgroundTaskId");

		BackgroundTask backgroundTask =
			_backgroundTaskLocalService.fetchBackgroundTask(backgroundTaskId);

		if (backgroundTask == null) {
			return StringPool.BLANK;
		}

		String processName = backgroundTask.getName();

		if (Validator.isNull(processName)) {
			processName = LanguageUtil.get(locale, "untitled");
		}

		return LanguageUtil.format(resourceBundle, message, processName);
	}

	@Override
	public String getLink(
			UserNotificationEvent userNotificationEvent,
			LiferayPortletRequest request, String currentURL)
		throws Exception {

		PortletURL renderURL = PortletURLFactoryUtil.create(
			request, ExportImportPortletKeys.EXPORT_IMPORT,
			PortletRequest.RENDER_PHASE);

		renderURL.setParameter("mvcPath", "/view_export_import.jsp");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			userNotificationEvent.getPayload());

		long backgroundTaskId = jsonObject.getLong("backgroundTaskId");

		BackgroundTask backgroundTask =
			_backgroundTaskLocalService.fetchBackgroundTask(backgroundTaskId);

		if (backgroundTask == null) {
			return StringPool.BLANK;
		}

		renderURL.setParameter(
			"backgroundTaskId", String.valueOf(backgroundTaskId));

		renderURL.setParameter("backURL", currentURL);

		return renderURL.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ExportImportNotificationHelperImpl.class);

	@Reference
	private BackgroundTaskLocalService _backgroundTaskLocalService;

	@Reference
	private ExportImportConfigurationLocalService
		_exportImportConfigurationLocalService;

	@Reference(target = "(bundle.symbolic.name=com.liferay.staging.lang)")
	private ResourceBundleLoader _resourceBundleLoader;

}