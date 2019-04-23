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

package com.liferay.change.tracking.my.change.lists.web.internal.product.navigation.personal.menu;

import com.liferay.change.tracking.configuration.CTServiceConfigurationProvider;
import com.liferay.change.tracking.constants.CTPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.product.navigation.personal.menu.BasePersonalMenuEntry;
import com.liferay.product.navigation.personal.menu.PersonalMenuEntry;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pei-Jung Lan
 */
@Component(
	immediate = true,
	property = {
		"product.navigation.personal.menu.entry.order:Integer=100",
		"product.navigation.personal.menu.group:Integer=200"
	},
	service = PersonalMenuEntry.class
)
public class MyChangeListsPersonalMenuEntry extends BasePersonalMenuEntry {

	@Override
	public boolean isShow(
			PortletRequest portletRequest, PermissionChecker permissionChecker)
		throws PortalException {

		return _ctServiceConfigurationProvider.enableChangeTracking();
	}

	@Override
	protected String getPortletId() {
		return CTPortletKeys.MY_CHANGE_LISTS;
	}

	@Reference
	private CTServiceConfigurationProvider _ctServiceConfigurationProvider;

}