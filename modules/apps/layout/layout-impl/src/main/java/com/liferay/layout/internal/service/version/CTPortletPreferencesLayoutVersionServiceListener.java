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

package com.liferay.layout.internal.service.version;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutVersion;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.version.VersionServiceListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniel Kocsis
 */
@Component(
	immediate = true,
	service = {
		CTPortletPreferencesLayoutVersionServiceListener.class,
		VersionServiceListener.class
	}
)
public class CTPortletPreferencesLayoutVersionServiceListener
	implements VersionServiceListener<Layout, LayoutVersion> {

	@Override
	public void afterCheckout(Layout draftLayout, int version)
		throws PortalException {
	}

	@Override
	public void afterCreateDraft(Layout draftLayout) throws PortalException {
	}

	@Override
	public void afterDelete(Layout draftLayout) throws PortalException {
	}

	@Override
	public void afterDeleteDraft(Layout draftLayout) throws PortalException {
	}

	@Override
	public void afterDeleteVersion(LayoutVersion layoutVersion)
		throws PortalException {
	}

	@Override
	public void afterPublishDraft(Layout draftLayout, int version)
		throws PortalException {
	}

	@Override
	public void afterUpdateDraft(Layout draftLayout) throws PortalException {
	}

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private PortletPreferencesLocalService _portletPreferencesLocalService;

}