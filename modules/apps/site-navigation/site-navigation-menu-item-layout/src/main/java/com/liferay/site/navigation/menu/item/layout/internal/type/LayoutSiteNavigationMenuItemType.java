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

package com.liferay.site.navigation.menu.item.layout.internal.type;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.staging.LayoutStaging;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.item.selector.ItemSelector;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutRevision;
import com.liferay.portal.kernel.model.LayoutType;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.permission.LayoutPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.site.navigation.menu.item.layout.constants.SiteNavigationMenuItemTypeConstants;
import com.liferay.site.navigation.menu.item.layout.internal.constants.SiteNavigationMenuItemTypeLayoutWebKeys;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.type.SiteNavigationMenuItemType;

import java.io.IOException;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = "site.navigation.menu.item.type=" + SiteNavigationMenuItemTypeConstants.LAYOUT,
	service = SiteNavigationMenuItemType.class
)
public class LayoutSiteNavigationMenuItemType
	implements SiteNavigationMenuItemType {

	@Override
	public boolean exportData(
		PortletDataContext portletDataContext,
		Element siteNavigationMenuItemElement,
		SiteNavigationMenuItem siteNavigationMenuItem) {

		Layout layout = _getLayout(siteNavigationMenuItem);

		if (layout == null) {
			return false;
		}

		if (!ArrayUtil.contains(portletDataContext.getLayoutIds(), layout.getLayoutId())) {
			return false;
		}

		LayoutRevision layoutRevision = _layoutStaging.getLayoutRevision(
			layout);

		if ((layoutRevision != null) &&
			((layoutRevision.getStatus() == WorkflowConstants.STATUS_DRAFT) ||
			 layoutRevision.isIncomplete())) {

			return false;
		}

		portletDataContext.addReferenceElement(
			siteNavigationMenuItem, siteNavigationMenuItemElement, layout,
			PortletDataContext.REFERENCE_TYPE_DEPENDENCY, true);

		return true;
	}

	@Override
	public PortletURL getAddURL(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		PortletURL addURL = renderResponse.createActionURL();

		addURL.setParameter(
			ActionRequest.ACTION_NAME,
			"/navigation_menu/add_layout_site_navigation_menu_item");

		return addURL;
	}

	@Override
	public String getIcon() {
		return "page";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "page");
	}

	@Override
	public Layout getLayout(SiteNavigationMenuItem siteNavigationMenuItem) {
		return _getLayout(siteNavigationMenuItem);
	}

	@Override
	public String getRegularURL(
			HttpServletRequest request,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws Exception {

		Layout layout = _getLayout(siteNavigationMenuItem);

		return layout.getRegularURL(request);
	}

	@Override
	public String getResetLayoutURL(
			HttpServletRequest request,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws Exception {

		Layout layout = _getLayout(siteNavigationMenuItem);

		return layout.getResetLayoutURL(request);
	}

	@Override
	public String getResetMaxStateURL(
			HttpServletRequest request,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws Exception {

		Layout layout = _getLayout(siteNavigationMenuItem);

		return layout.getResetMaxStateURL(request);
	}

	@Override
	public String getSubtitle(
		SiteNavigationMenuItem siteNavigationMenuItem, Locale locale) {

		Layout layout = _getLayout(siteNavigationMenuItem);

		if (layout.isPublicLayout()) {
			return LanguageUtil.get(locale, "public-pages");
		}

		return LanguageUtil.get(locale, "private-pages");
	}

	@Override
	public String getTarget(SiteNavigationMenuItem siteNavigationMenuItem) {
		Layout layout = _getLayout(siteNavigationMenuItem);

		return layout.getTarget();
	}

	@Override
	public String getTitle(
		SiteNavigationMenuItem siteNavigationMenuItem, Locale locale) {

		String label = getName(siteNavigationMenuItem.getTypeSettings());

		if (Validator.isNotNull(label)) {
			return label;
		}

		Layout layout = _getLayout(siteNavigationMenuItem);

		if (layout != null) {
			return layout.getName(locale);
		}

		return getLabel(locale);
	}

	@Override
	public String getType() {
		return SiteNavigationMenuItemTypeConstants.LAYOUT;
	}

	@Override
	public String getTypeSettingsFromLayout(Layout layout) {
		UnicodeProperties unicodeProperties = new UnicodeProperties();

		unicodeProperties.setProperty(
			"groupId", String.valueOf(layout.getGroupId()));
		unicodeProperties.setProperty("layoutUuid", layout.getUuid());
		unicodeProperties.setProperty(
			"privateLayout", String.valueOf(layout.isPrivateLayout()));

		return unicodeProperties.toString();
	}

	@Override
	public String getUnescapedName(
		SiteNavigationMenuItem siteNavigationMenuItem, String languageId) {

		String title = getTitle(
			siteNavigationMenuItem, LocaleUtil.fromLanguageId(languageId));

		if (Validator.isNotNull(title)) {
			return title;
		}

		Layout layout = _getLayout(siteNavigationMenuItem);

		return layout.getName(languageId);
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws PortalException {

		Layout layout = _getLayout(siteNavigationMenuItem);

		return LayoutPermissionUtil.contains(
			permissionChecker, layout.getPlid(), ActionKeys.VIEW);
	}

	@Override
	public String iconURL(
		SiteNavigationMenuItem siteNavigationMenuItem, String pathImage) {

		Layout layout = _getLayout(siteNavigationMenuItem);

		if ((layout == null) || !layout.isIconImage()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(5);

		sb.append(pathImage);
		sb.append("/layout_icon?img_id=");
		sb.append(layout.getIconImageId());
		sb.append("&t=");
		sb.append(WebServerServletTokenUtil.getToken(layout.getIconImageId()));

		return sb.toString();
	}

	@Override
	public boolean importData(
		PortletDataContext portletDataContext,
		SiteNavigationMenuItem siteNavigationMenuItem,
		SiteNavigationMenuItem importedSiteNavigationMenuItem) {

		Map<Long, Long> layoutPlids =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Layout.class);

		Layout layout = _getLayout(importedSiteNavigationMenuItem);

		if (layout == null) {
			return false;
		}

		LayoutRevision layoutRevision = _layoutStaging.getLayoutRevision(
			layout);

		if ((layoutRevision != null) &&
			(layoutRevision.getStatus() == WorkflowConstants.STATUS_DRAFT)) {

			return false;
		}

		long plid = MapUtil.getLong(
			layoutPlids, layout.getPlid(), layout.getPlid());

		Layout importedLayout = _layoutLocalService.fetchLayout(plid);

		if (importedLayout != null) {
			UnicodeProperties typeSettingsProperties = new UnicodeProperties();

			typeSettingsProperties.fastLoad(
				siteNavigationMenuItem.getTypeSettings());

			typeSettingsProperties.put("layoutUuid", importedLayout.getUuid());
			typeSettingsProperties.put(
				"groupId", String.valueOf(importedLayout.getGroupId()));
			typeSettingsProperties.put(
				"privateLayout",
				String.valueOf(importedLayout.isPrivateLayout()));

			importedSiteNavigationMenuItem.setTypeSettings(
				typeSettingsProperties.toString());
		}

		return true;
	}

	@Override
	public boolean isBrowsable(SiteNavigationMenuItem siteNavigationMenuItem) {
		Layout layout = _getLayout(siteNavigationMenuItem);

		LayoutType layoutType = layout.getLayoutType();

		return layoutType.isBrowsable();
	}

	@Override
	public boolean isChildSelected(
			boolean selectable, SiteNavigationMenuItem siteNavigationMenuItem,
			Layout curLayout)
		throws PortalException {

		Layout layout = _getLayout(siteNavigationMenuItem);

		return layout.isChildSelected(selectable, curLayout);
	}

	@Override
	public boolean isSelected(
			boolean selectable, SiteNavigationMenuItem siteNavigationMenuItem,
			Layout curLayout)
		throws Exception {

		Layout layout = _getLayout(siteNavigationMenuItem);

		return layout.isSelected(
			selectable, curLayout, curLayout.getAncestorPlid());
	}

	@Override
	public void renderAddPage(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		request.setAttribute(
			SiteNavigationMenuItemTypeLayoutWebKeys.ITEM_SELECTOR,
			_itemSelector);

		_jspRenderer.renderJSP(
			_servletContext, request, response, "/add_layout.jsp");
	}

	@Override
	public void renderEditPage(
			HttpServletRequest request, HttpServletResponse response,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws IOException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		request.setAttribute(
			SiteNavigationMenuItemTypeLayoutWebKeys.ITEM_SELECTOR,
			_itemSelector);

		request.setAttribute(
			WebKeys.SEL_LAYOUT, _getLayout(siteNavigationMenuItem));
		request.setAttribute(
			WebKeys.TITLE,
			getTitle(siteNavigationMenuItem, themeDisplay.getLocale()));

		_jspRenderer.renderJSP(
			_servletContext, request, response, "/edit_layout.jsp");
	}

	private Layout _getLayout(SiteNavigationMenuItem siteNavigationMenuItem) {
		UnicodeProperties typeSettingsProperties = new UnicodeProperties();

		typeSettingsProperties.fastLoad(
			siteNavigationMenuItem.getTypeSettings());

		String layoutUuid = typeSettingsProperties.get("layoutUuid");

		boolean privateLayout = GetterUtil.getBoolean(
			typeSettingsProperties.get("privateLayout"));

		return _layoutLocalService.fetchLayoutByUuidAndGroupId(
			layoutUuid, siteNavigationMenuItem.getGroupId(), privateLayout);
	}

	@Reference
	private ItemSelector _itemSelector;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutStaging _layoutStaging;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.site.navigation.menu.item.layout)",
		unbind = "-"
	)
	private ServletContext _servletContext;

}