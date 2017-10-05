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

package com.liferay.hello.staging.web.display;

import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.hello.staging.constants.HelloStagingPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zoltan Csaszi
 */
public class HelloStagingDisplayContext {

	public HelloStagingDisplayContext(HttpServletRequest request) {
		_request = request;
	}

	/**
	 * Checking whether site is staged and the portlet content is modifiable.
	 *
	 * @return false when it is a live group
	 */
	public boolean hasStagingPermission() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Boolean stagingPermission = StagingPermissionUtil.hasPermission(
			themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
			null, 0L, HelloStagingPortletKeys.HELLO_STAGING,
			ActionKeys.ADD_ENTRY);

		if (_log.isDebugEnabled()) {
			_log.debug("stagingPermission: " + stagingPermission);
		}

		if (stagingPermission != null) {
			return stagingPermission.booleanValue();
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		HelloStagingDisplayContext.class);

	private final HttpServletRequest _request;

}