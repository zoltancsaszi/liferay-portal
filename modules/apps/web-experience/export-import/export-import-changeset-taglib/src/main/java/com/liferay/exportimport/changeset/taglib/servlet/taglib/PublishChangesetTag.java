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

package com.liferay.exportimport.changeset.taglib.servlet.taglib;

import com.liferay.exportimport.changeset.Changeset;
import com.liferay.exportimport.changeset.ChangesetEnvironment;
import com.liferay.exportimport.changeset.ChangesetManager;
import com.liferay.exportimport.changeset.ChangesetManagerUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.taglib.TagSupport;

import javax.servlet.jsp.JspException;

/**
 * @author Mate Thurzo
 */
public class PublishChangesetTag extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		Object themeDisplayObj = pageContext.getAttribute("themeDisplay");

		ThemeDisplay themeDisplay = null;

		if ((themeDisplayObj != null) &&
			(themeDisplayObj instanceof ThemeDisplay)) {

			themeDisplay = (ThemeDisplay)themeDisplayObj;
		}

		long groupId = 0;
		String portletId = null;

		if (themeDisplay == null) {
			Object groupIdObj = pageContext.getAttribute("groupId");

			groupId = GetterUtil.getLong(groupIdObj);

			Object portletIdObj = pageContext.getAttribute("portletId");

			portletId = GetterUtil.getString(portletIdObj);
		}
		else {
			groupId = themeDisplay.getScopeGroupId();

			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			portletId = portletDisplay.getRootPortletId();
		}

		ChangesetEnvironment.Builder builder = ChangesetEnvironment.create(
			groupId, portletId);

		ChangesetManager changesetManager =
			ChangesetManagerUtil.getChangesetManager();

		changesetManager.publishChangeset(_changeset, builder.create());

		return EVAL_PAGE;
	}

	public void setChangeset(Changeset changeset) {
		_changeset = changeset;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setPortletId(String portletId) {
		_portletId = portletId;
	}

	private Changeset _changeset;
	private long _groupId;
	private String _portletId;

}