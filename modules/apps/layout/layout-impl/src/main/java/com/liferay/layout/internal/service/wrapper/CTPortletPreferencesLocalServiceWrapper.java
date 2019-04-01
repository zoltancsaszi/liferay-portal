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

package com.liferay.layout.internal.service.wrapper;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceWrapper;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.LayoutVersionPersistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Máté Thurzó
 */
@Component(service = ServiceWrapper.class)
public class CTPortletPreferencesLocalServiceWrapper
	extends PortletPreferencesLocalServiceWrapper {

	public CTPortletPreferencesLocalServiceWrapper() {
		super(null);
	}

	public CTPortletPreferencesLocalServiceWrapper(
		PortletPreferencesLocalService portletPreferencesLocalService) {

		super(portletPreferencesLocalService);
	}

	@Override
	public PortletPreferences fetchPortletPreferences(
		long ownerId, int ownerType, long plid, String portletId) {

		if (OverridePrefs.override && (plid == OverridePrefs.plid)) {
			Set<PortletPreferences> portletPreferences = null;
			//	super.getLayoutVersionPortletPreferences(
			//		OverridePrefs.layoutVersionId);

			return portletPreferences.stream(
			).filter(
				pp ->
					pp.getOwnerId() == ownerId &&
					pp.getOwnerType() == ownerType && pp.getPlid() == plid &&
					pp.getPortletId(
					).equals(
						portletId
					)
			).findFirst(
			).orElseGet(
				() -> super.fetchPortletPreferences(
					ownerId, ownerType, plid, portletId)
			);
		}

		return super.fetchPortletPreferences(
			ownerId, ownerType, plid, portletId);
	}

	@Override
	public List<PortletPreferences> getPortletPreferences(
		long ownerId, int ownerType, long plid) {

		if (OverridePrefs.override && (plid == OverridePrefs.plid)) {
			return new ArrayList<>(null);
			//super.getLayoutVersionPortletPreferences(
			//	OverridePrefs.layoutVersionId));
		}

		return super.getPortletPreferences(ownerId, ownerType, plid);
	}

	@Override
	public Map<String, javax.portlet.PortletPreferences> getStrictPreferences(
		Layout layout, List<Portlet> portlets) {

		Map<String, javax.portlet.PortletPreferences> portletPreferencesMap =
			super.getStrictPreferences(layout, portlets);

		return portletPreferencesMap;

		//portletPreferencesMap.entrySet().stream().filter(entry -> entry.getValue());
	}

	@Override
	public javax.portlet.PortletPreferences getStrictPreferences(
		long companyId, long ownerId, int ownerType, long plid,
		String portletId) {

		if (OverridePrefs.override && (plid == OverridePrefs.plid)) {
			Set<PortletPreferences> portletPreferences = null;
			//super.getLayoutVersionPortletPreferences(
			//	OverridePrefs.layoutVersionId);

			return portletPreferences.stream(
			).filter(
				pp ->
					pp.getOwnerId() == ownerId &&
					pp.getOwnerType() == ownerType && pp.getPlid() == plid &&
					pp.getPortletId(
					).equals(
						portletId
					)
			).findFirst(
			).map(
				pp -> PortletPreferencesFactoryUtil.fromXML(
					pp.getCompanyId(), pp.getOwnerId(), pp.getOwnerType(),
					pp.getPlid(), pp.getPortletId(), pp.getPreferences())
			).orElseGet(
				() -> super.getStrictPreferences(
					companyId, ownerId, ownerType, plid, portletId)
			);
		}

		return super.getStrictPreferences(
			companyId, ownerId, ownerType, plid, portletId);
	}

	@Reference(unbind = "-")
	protected void setPortletPreferencesLocalService(
		PortletPreferencesLocalService portletPreferencesLocalService) {

		// Needed for synchronization

	}

	@Reference
	private LayoutVersionPersistence _layoutVersionPersistence;

}