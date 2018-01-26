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

package com.liferay.exportimport.changeset.display.context;

import com.liferay.exportimport.changeset.constants.ChangesetPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;
import com.liferay.portal.kernel.servlet.taglib.ui.URLMenuItem;
import com.liferay.portal.kernel.servlet.taglib.ui.URLUIItem;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zoltan Csaszi
 */
public class ExportImportChangesetDisplayContext {

	public ExportImportChangesetDisplayContext(HttpServletRequest request) {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		_group = themeDisplay.getScopeGroup();
		_permissionChecker = themeDisplay.getPermissionChecker();

		_portletId = portletDisplay.getId();
		_request = request;
	}

	public void addSingleAssetPublishMenuItem(
			List<MenuItem> menuItems, StagedGroupedModel stagedGroupedModel)
		throws PortalException {

		PortletURL portletURL = PortletURLFactoryUtil.create(
			_request, ChangesetPortletKeys.CHANGESET,
			PortletRequest.ACTION_PHASE);

		long classNameId = PortalUtil.getClassNameId(
			stagedGroupedModel.getModelClassName());

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "exportImportEntity");
		portletURL.setParameter("mvcRenderCommandName", "exportImportEntity");
		portletURL.setParameter("cmd", Constants.PUBLISH);
		portletURL.setParameter("classNameId", String.valueOf(classNameId));
		portletURL.setParameter(
			"groupId", String.valueOf(stagedGroupedModel.getGroupId()));
		portletURL.setParameter("uuid", stagedGroupedModel.getUuid());
		portletURL.setParameter("portletId", _portletId);

		boolean showMenuItem = false;

		if ((_group.isStagingGroup() || _group.isStagedRemotely()) &&
			_group.isStagedPortlet(_portletId)) {

			showMenuItem = true;
		}

		if (!
				(GroupPermissionUtil.contains(
					_permissionChecker, _group,
					ActionKeys.EXPORT_IMPORT_PORTLET_INFO) &&
			 showMenuItem)) {

			return;
		}

		URLUIItem urlUIItem = new URLMenuItem();

		urlUIItem.setKey("publish");
		urlUIItem.setLabel("publish");
		urlUIItem.setURL(portletURL.toString());

		menuItems.add((MenuItem)urlUIItem);
	}

	private final Group _group;
	private final PermissionChecker _permissionChecker;
	private final String _portletId;
	private final HttpServletRequest _request;

}