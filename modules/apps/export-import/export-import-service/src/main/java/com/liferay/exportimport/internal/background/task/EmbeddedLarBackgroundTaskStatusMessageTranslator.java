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

package com.liferay.exportimport.internal.background.task;

import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Zoltan Csaszi
 */
public class EmbeddedLarBackgroundTaskStatusMessageTranslator
	extends PortletExportImportBackgroundTaskStatusMessageTranslator {

	@Override
	public void translate(
		BackgroundTaskStatus backgroundTaskStatus, Message message) {

		if (!ExportImportThreadLocal.isEmbeddedLarImportInProcess()) {
			super.translate(backgroundTaskStatus, message);

			return;
		}

		clearBackgroundTaskStatus(backgroundTaskStatus);

		String messageType = message.getString("messageType");

		backgroundTaskStatus.setAttribute(
			"allModelAdditionCountersTotal",
			ExportImportThreadLocal.getAllPortletAdditionCountersTotal());

		backgroundTaskStatus.setAttribute(
			"currentPortletAdditionCounter",
			ExportImportThreadLocal.getCurrentPortletAdditionCounter());

		if (messageType.equals("portlet")) {
			translatePortletMessage(backgroundTaskStatus, message);
		}
		else if (messageType.equals("stagedModel")) {
			translateStagedModelMessage(backgroundTaskStatus, message);
		}
	}

	protected synchronized void translatePortletMessage(
		BackgroundTaskStatus backgroundTaskStatus, Message message) {

		if (!ExportImportThreadLocal.isEmbeddedLarImportInProcess()) {
			super.translatePortletMessage(backgroundTaskStatus, message);

			return;
		}

		String portletId = message.getString("portletId");

		backgroundTaskStatus.setAttribute("portletId", portletId);

		backgroundTaskStatus.setAttribute("stagedModelName", StringPool.BLANK);
		backgroundTaskStatus.setAttribute("stagedModelType", StringPool.BLANK);
		backgroundTaskStatus.setAttribute("uuid", StringPool.BLANK);
	}

	protected synchronized void translateStagedModelMessage(
		BackgroundTaskStatus backgroundTaskStatus, Message message) {

		String portletId = (String)backgroundTaskStatus.getAttribute(
			"portletId");

		if (Validator.isNull(portletId)) {
			return;
		}

		backgroundTaskStatus.setAttribute(
			"stagedModelName", message.getString("stagedModelName"));
		backgroundTaskStatus.setAttribute(
			"stagedModelType", message.getString("stagedModelType"));
		backgroundTaskStatus.setAttribute("uuid", message.getString("uuid"));
	}

}