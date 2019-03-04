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

package com.liferay.change.tracking.change.lists.history.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.change.tracking.CTEngineManager;
import com.liferay.change.tracking.constants.CTPanelCategoryKeys;
import com.liferay.change.tracking.constants.CTPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Laszlo Pap
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=150",
		"panel.category.key=" + CTPanelCategoryKeys.CONTROL_PANEL_CHANGE_LISTS
	},
	service = PanelApp.class
)
public class ChangeListsHistoryPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return CTPortletKeys.CHANGE_LISTS_HISTORY;
	}

	@Override
	public boolean isShow(PermissionChecker permissionChecker, Group group)
		throws PortalException {

		return _ctEngineManager.isChangeTrackingEnabled(group.getCompanyId());
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + CTPortletKeys.CHANGE_LISTS_HISTORY + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

	@Reference
	private CTEngineManager _ctEngineManager;

}