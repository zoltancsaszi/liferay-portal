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

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;

/**
 * @author Zoltan Csaszi
 */
public class EmbeddedLarBackgroundTaskStatusMessageTranslator
	extends DefaultExportImportBackgroundTaskStatusMessageTranslator {

	@Override
	public void translate(
		BackgroundTaskStatus backgroundTaskStatus, Message message) {

		String messageType = message.getString("messageType");

		if (backgroundTaskStatus.getAttribute(
				"allPortletModelAdditionCounters") == null) {

			backgroundTaskStatus.setAttribute(
				"allPortletModelAdditionCounters",
				new HashMap<String, LongWrapper>());
		}

		if (backgroundTaskStatus.getAttribute(
				"allModelAdditionCountersTotal") == null) {

			backgroundTaskStatus.setAttribute(
				"allModelAdditionCountersTotal", 0L);
		}

		if (backgroundTaskStatus.getAttribute("allPortletAdditionCounter") ==
				null) {

			backgroundTaskStatus.setAttribute("allPortletAdditionCounter", 0L);
		}

		if (backgroundTaskStatus.getAttribute(
				"currentPortletModelAdditionCounters") == null) {

			backgroundTaskStatus.setAttribute(
				"currentPortletModelAdditionCounters",
				new HashMap<String, LongWrapper>());
		}

		if (messageType.equals("larFile")) {
			translateLarMessage(backgroundTaskStatus, message);
		}
		else if (messageType.equals("layout")) {
			translateLayoutMessage(backgroundTaskStatus, message);
		}
		else if (messageType.equals("portlet")) {
			translatePortletMessage(backgroundTaskStatus, message);
		}
		else if (messageType.equals("stagedModel")) {
			translateStagedModelMessage(backgroundTaskStatus, message);
		}
	}

	protected void clearBackgroundTaskStatus(
		BackgroundTaskStatus backgroundTaskStatus) {

		backgroundTaskStatus.clearAttributes();

		backgroundTaskStatus.setAttribute("currentLarFileAdditionCounter", 0L);
		backgroundTaskStatus.setAttribute("larFileCounterTotal", 0L);

		backgroundTaskStatus.setAttribute("allModelAdditionCountersTotal", 0L);
		backgroundTaskStatus.setAttribute("allPortletAdditionCounter", 0L);
		backgroundTaskStatus.setAttribute(
			"allPortletModelAdditionCounters",
			new HashMap<String, LongWrapper>());
		backgroundTaskStatus.setAttribute(
			"currentModelAdditionCountersTotal", 0L);
		backgroundTaskStatus.setAttribute("currentPortletAdditionCounter", 0L);
		backgroundTaskStatus.setAttribute(
			"currentPortletModelAdditionCounters",
			new HashMap<String, LongWrapper>());
	}

	protected long getAllModelAdditionCountersTotal(
		BackgroundTaskStatus backgroundTaskStatus) {

		long allModelAdditionCountersTotal = GetterUtil.getLong(
			backgroundTaskStatus.getAttribute("allModelAdditionCountersTotal"));
		long currentModelAdditionCountersTotal = GetterUtil.getLong(
			backgroundTaskStatus.getAttribute(
				"currentModelAdditionCountersTotal"));

		return allModelAdditionCountersTotal +
			currentModelAdditionCountersTotal;
	}

	protected long getAllPortletAdditionCounter(
		BackgroundTaskStatus backgroundTaskStatus) {

		long allPortletAdditionCounter = GetterUtil.getLong(
			backgroundTaskStatus.getAttribute("allPortletAdditionCounter"));
		long currentPortletAdditionCounter = GetterUtil.getLong(
			backgroundTaskStatus.getAttribute("currentPortletAdditionCounter"));

		return allPortletAdditionCounter + currentPortletAdditionCounter;
	}

	protected synchronized void translateLarMessage(
		BackgroundTaskStatus backgroundTaskStatus, Message message) {

		String portletId = message.getString("portletId");

		backgroundTaskStatus.setAttribute("portletId", portletId);

		if (message.getInteger("larFileCounterTotal") > 0) {
			backgroundTaskStatus.setAttribute(
				"larFileCounterTotal",
				message.getInteger("larFileCounterTotal"));
		}

		if (message.getBoolean("clearStatus")) {
			clearBackgroundTaskStatus(backgroundTaskStatus);
		}

		int currentLarFileAddition = message.getInteger(
			"currentLarFileAdditionCounter");

		if (currentLarFileAddition > 0) {
			backgroundTaskStatus.setAttribute(
				"currentLarFileAdditionCounter", currentLarFileAddition);
		}
	}

	@Override
	protected synchronized void translateLayoutMessage(
		BackgroundTaskStatus backgroundTaskStatus, Message message) {

		String phase = message.getString("phase");

		if (Validator.isNull(phase)) {
			phase = GetterUtil.getString(
				backgroundTaskStatus.getAttribute("phase"));
		}

		if (Validator.isNull(phase)) {
			clearBackgroundTaskStatus(backgroundTaskStatus);

			phase = Constants.EXPORT;
		}
		else {
			phase = Constants.IMPORT;
		}

		backgroundTaskStatus.setAttribute("phase", phase);

		super.translateLayoutMessage(backgroundTaskStatus, message);

		if (phase.equals(Constants.IMPORT)) {
			backgroundTaskStatus.setAttribute(
				"allModelAdditionCountersTotal",
				getAllModelAdditionCountersTotal(backgroundTaskStatus));
			backgroundTaskStatus.setAttribute(
				"allPortletAdditionCounter",
				getAllPortletAdditionCounter(backgroundTaskStatus));
			backgroundTaskStatus.setAttribute(
				"allPortletModelAdditionCounters",
				new HashMap<String, LongWrapper>());
			backgroundTaskStatus.setAttribute(
				"currentPortletModelAdditionCounters",
				new HashMap<String, LongWrapper>());
		}
	}

}