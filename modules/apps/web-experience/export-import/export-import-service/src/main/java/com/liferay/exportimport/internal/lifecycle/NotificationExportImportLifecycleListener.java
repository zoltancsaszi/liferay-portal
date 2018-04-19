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

package com.liferay.exportimport.internal.lifecycle;

import com.liferay.exportimport.changeset.constants.ChangesetPortletKeys;
import com.liferay.exportimport.constants.ExportImportPortletKeys;
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleEvent;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleListener;
import com.liferay.exportimport.kernel.lifecycle.ProcessAwareExportImportLifecycleListener;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.background.task.model.BackgroundTask;
import com.liferay.portal.background.task.service.BackgroundTaskLocalService;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskThreadLocal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationManagerUtil;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.Serializable;

import java.util.Map;

import com.liferay.staging.constants.StagingProcessesPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Akos Thurzo
 */
@Component(immediate = true, service = ExportImportLifecycleListener.class)
public class NotificationExportImportLifecycleListener
	implements ProcessAwareExportImportLifecycleListener {

	@Override
	public boolean isParallel() {
		return false;
	}

	@Override
	public void onProcessFailed(
			ExportImportLifecycleEvent exportImportLifecycleEvent)
		throws Exception {

		sendNotification(BackgroundTaskConstants.STATUS_FAILED);
	}

	@Override
	public void onProcessStarted(
			ExportImportLifecycleEvent exportImportLifecycleEvent)
		throws Exception {
	}

	@Override
	public void onProcessSucceeded(
			ExportImportLifecycleEvent exportImportLifecycleEvent)
		throws Exception {

		sendNotification(BackgroundTaskConstants.STATUS_SUCCESSFUL);
	}

	protected JSONObject getPayload(
		long backgroundTaskId, long exportImportConfigurationId, int status) {

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		jsonObject.put("backgroundTaskId", backgroundTaskId);
		jsonObject.put(
			"exportImportConfigurationId", exportImportConfigurationId);
		jsonObject.put("status", status);

		return jsonObject;
	}

	protected void sendNotification(int status) throws PortalException {
		long backgroundTaskId = BackgroundTaskThreadLocal.getBackgroundTaskId();

		BackgroundTask backgroundTask =
			_backgroundTaskLocalService.getBackgroundTask(backgroundTaskId);

		Map<String, Serializable> taskContextMap =
			backgroundTask.getTaskContextMap();

		long exportImportConfigurationId = GetterUtil.getLong(
			taskContextMap.get("exportImportConfigurationId"));

		ExportImportConfiguration exportImportConfiguration =
			_exportImportConfigurationLocalService.
				fetchExportImportConfiguration(exportImportConfigurationId);

		if (exportImportConfiguration == null) {
			return;
		}

		int processType = exportImportConfiguration.getType();

		String portletId = StringPool.BLANK;

		Map<String, Serializable> settingsMap =
			exportImportConfiguration.getSettingsMap();

		String settingsPortletId = GetterUtil.getString(
			settingsMap.get("portletId"));

		if (settingsPortletId.equals(ChangesetPortletKeys.CHANGESET)) {
			portletId = settingsPortletId;
		}
		else if ((processType ==
				  ExportImportConfigurationConstants.TYPE_EXPORT_LAYOUT) ||
				 (processType ==
				  ExportImportConfigurationConstants.TYPE_EXPORT_PORTLET)) {

			portletId = ExportImportPortletKeys.EXPORT;
		}
		else if ((processType ==
				  ExportImportConfigurationConstants.TYPE_IMPORT_LAYOUT) ||
				 (processType ==
				  ExportImportConfigurationConstants.TYPE_IMPORT_PORTLET)) {

			portletId = ExportImportPortletKeys.IMPORT;
		}
		else if ((processType ==
				  ExportImportConfigurationConstants.
					  TYPE_PUBLISH_LAYOUT_LOCAL) ||
				 (processType ==
				  ExportImportConfigurationConstants.
					  TYPE_PUBLISH_LAYOUT_REMOTE) ||
				 (processType ==
				  ExportImportConfigurationConstants.
					  TYPE_PUBLISH_PORTLET_LOCAL) ||
				 (processType ==
				  ExportImportConfigurationConstants.
					  TYPE_PUBLISH_PORTLET_REMOTE) ||
				 (processType ==
				  ExportImportConfigurationConstants.TYPE_PUBLISH_PORTLET)) {

			portletId = StagingProcessesPortletKeys.STAGING_PROCESSES;
		}

		boolean deliverExportWebsite = UserNotificationManagerUtil.isDeliver(
			backgroundTask.getUserId(), portletId, 0, 0,
			UserNotificationDeliveryConstants.TYPE_WEBSITE);

		if (deliverExportWebsite) {
			_userNotificationEventLocalService.sendUserNotificationEvents(
				backgroundTask.getUserId(), portletId,
				UserNotificationDeliveryConstants.TYPE_WEBSITE,
				getPayload(
					backgroundTaskId, exportImportConfigurationId, status));
		}
	}

	@Reference
	private BackgroundTaskLocalService _backgroundTaskLocalService;

	@Reference
	private ExportImportConfigurationLocalService
		_exportImportConfigurationLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private UserNotificationEventLocalService
		_userNotificationEventLocalService;

}