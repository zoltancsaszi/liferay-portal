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

package com.liferay.site.navigation.internal.exportimport.data.handler;

import com.liferay.exportimport.kernel.lar.BasePortletDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportDateUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.exportimport.kernel.staging.Staging;
import com.liferay.exportimport.portlet.data.handler.helper.PortletDataHandlerHelper;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.site.navigation.admin.constants.SiteNavigationAdminPortletKeys;
import com.liferay.site.navigation.constants.SiteNavigationConstants;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.service.SiteNavigationMenuLocalService;

import java.util.List;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + SiteNavigationAdminPortletKeys.SITE_NAVIGATION_ADMIN,
	service = PortletDataHandler.class
)
public class SiteNavigationMenuPortletDataHandler
	extends BasePortletDataHandler {

	public static final String NAMESPACE = "navigation-menus";

	public static final String SCHEMA_VERSION = "1.0.0";

	@Override
	public String getSchemaVersion() {
		return SCHEMA_VERSION;
	}

	@Override
	public String getServiceName() {
		return SiteNavigationConstants.SERVICE_NAME;
	}

	@Override
	public boolean isConfigurationEnabled() {
		return false;
	}

	@Override
	public boolean isPublishToLiveByDefault() {
		return true;
	}

	@Override
	public boolean validateSchemaVersion(String schemaVersion) {
		return _portletDataHandlerHelper.validateSchemaVersion(
			schemaVersion, getSchemaVersion());
	}

	@Activate
	protected void activate() {
		setDeletionSystemEventStagedModelTypes(
			new StagedModelType(SiteNavigationMenu.class),
			new StagedModelType(SiteNavigationMenuItem.class));
		setExportControls(
			new PortletDataHandlerBoolean(
				NAMESPACE, "navigation-menus", true, false, null,
				SiteNavigationMenu.class.getName()),
			new PortletDataHandlerBoolean(
				NAMESPACE, "navigation-menu-items", true, false, null,
				SiteNavigationMenuItem.class.getName()));
		setStagingControls(getExportControls());
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (portletDataContext.addPrimaryKey(
				SiteNavigationMenuPortletDataHandler.class, "deleteData")) {

			return portletPreferences;
		}

		_siteNavigationMenuItemStagedModelRepository.deleteStagedModels(
			portletDataContext);
		_siteNavigationMenuStagedModelRepository.deleteStagedModels(
			portletDataContext);

		return portletPreferences;
	}

	@Override
	protected String doExportData(
			final PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		portletDataContext.addPortletPermissions(
			SiteNavigationConstants.RESOURCE_NAME);

		Element rootElement = addExportDataRootElement(portletDataContext);

		rootElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getScopeGroupId()));

		if (portletDataContext.getBooleanParameter(
				NAMESPACE, "navigation-menus")) {

			ExportActionableDynamicQuery
				siteNavigationMenuExportActionableDynamicQuery =
					_siteNavigationMenuStagedModelRepository.
						getExportActionableDynamicQuery(portletDataContext);

			siteNavigationMenuExportActionableDynamicQuery.performActions();
		}

		if (portletDataContext.getBooleanParameter(
				NAMESPACE, "navigation-menu-items")) {

			ActionableDynamicQuery
				siteNavigationMenuItemExportActionableDynamicQuery =
					_siteNavigationMenuItemStagedModelRepository.
						getExportActionableDynamicQuery(portletDataContext);

			siteNavigationMenuItemExportActionableDynamicQuery.performActions();
		}

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		portletDataContext.importPortletPermissions(
			SiteNavigationConstants.RESOURCE_NAME);

		if (portletDataContext.getBooleanParameter(
				NAMESPACE, "navigation-menus")) {

			Element siteNavigationMenusElement =
				portletDataContext.getImportDataGroupElement(
					SiteNavigationMenu.class);

			List<Element> siteNavigationMenuElements =
				siteNavigationMenusElement.elements();

			for (Element siteNavigationMenuElement :
					siteNavigationMenuElements) {

				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, siteNavigationMenuElement);
			}
		}

		if (portletDataContext.getBooleanParameter(
				NAMESPACE, "navigation-menu-items")) {

			Element siteNavigationMenuItemsElement =
				portletDataContext.getImportDataGroupElement(
					SiteNavigationMenuItem.class);

			List<Element> siteNavigationMenuItemElements =
				siteNavigationMenuItemsElement.elements();

			for (Element siteNavigationMenuItemElement :
					siteNavigationMenuItemElements) {

				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, siteNavigationMenuItemElement);
			}
		}

		_createNavigationMenuIfLayoutSetPrototype(portletDataContext);

		return portletPreferences;
	}

	@Override
	protected void doPrepareManifestSummary(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws Exception {

		if (ExportImportDateUtil.isRangeFromLastPublishDate(
				portletDataContext)) {

			_staging.populateLastPublishDateCounts(
				portletDataContext,
				new StagedModelType[] {
					new StagedModelType(SiteNavigationMenuItem.class.getName()),
					new StagedModelType(SiteNavigationMenu.class.getName())
				});

			return;
		}

		ActionableDynamicQuery
			siteNavigationMenuItemExportActionableDynamicQuery =
				_siteNavigationMenuItemStagedModelRepository.
					getExportActionableDynamicQuery(portletDataContext);

		siteNavigationMenuItemExportActionableDynamicQuery.performCount();

		ActionableDynamicQuery siteNavigationMenuExportActionableDynamicQuery =
			_siteNavigationMenuStagedModelRepository.
				getExportActionableDynamicQuery(portletDataContext);

		siteNavigationMenuExportActionableDynamicQuery.performCount();
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private void _createNavigationMenuIfLayoutSetPrototype(
			PortletDataContext portletDataContext)
		throws PortalException {

		String larType = portletDataContext.getType();

		if (!larType.equals("layout-set-prototype")) {
			return;
		}

		long userId = PrincipalThreadLocal.getUserId();

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(portletDataContext.getCompanyId());
		serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());
		serviceContext.setUserId(userId);

		_siteNavigationMenuLocalService.addDefaultSiteNavigationMenu(
			userId, portletDataContext.getGroupId(), serviceContext);
	}

	@Reference
	private PortletDataHandlerHelper _portletDataHandlerHelper;

	@Reference(
		target = "(model.class.name=com.liferay.site.navigation.model.SiteNavigationMenuItem)"
	)
	private StagedModelRepository<SiteNavigationMenuItem>
		_siteNavigationMenuItemStagedModelRepository;

	@Reference
	private SiteNavigationMenuLocalService _siteNavigationMenuLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.site.navigation.model.SiteNavigationMenu)"
	)
	private StagedModelRepository<SiteNavigationMenu>
		_siteNavigationMenuStagedModelRepository;

	@Reference
	private Staging _staging;

}