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

package com.liferay.dynamic.data.mapping.web.asset.model;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.dynamic.data.mapping.constants.DDMWebKeys;
import com.liferay.dynamic.data.mapping.model.DDMStructureVersion;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Zoltan Csaszi
 */
public class DDMStructureAssetRenderer
	extends BaseJSPAssetRenderer<DDMStructureVersion> {

	public static final String TYPE = "ddm_structure";

	public DDMStructureAssetRenderer(DDMStructureVersion ddmStructureVersion) {
		_ddmStructureVersion = ddmStructureVersion;
	}

	@Override
	public DDMStructureVersion getAssetObject() {
		return _ddmStructureVersion;
	}

	@Override
	public String getClassName() {
		return DDMStructureVersion.class.getName();
	}

	@Override
	public long getClassPK() {
		return _ddmStructureVersion.getStructureVersionId();
	}

	@Override
	public long getGroupId() {
		return _ddmStructureVersion.getGroupId();
	}

	@Override
	public String getJspPath(
		HttpServletRequest httpServletRequest, String template) {

		if (template.equals(TEMPLATE_FULL_CONTENT)) {
			return "/asset/structure/" + template + ".jsp";
		}

		return null;
	}

	public DDMStructureVersion getStructureVersion() {
		return _ddmStructureVersion;
	}

	@Override
	public String getSummary(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		Locale locale = getLocale(portletRequest);

		StringBundler sb = new StringBundler(4);

		sb.append("<strong>");
		sb.append(LanguageUtil.get(locale, "structure"));
		sb.append(":</strong> ");
		sb.append(_ddmStructureVersion.getName(locale));

		return sb.toString();
	}

	@Override
	public String getTitle(Locale locale) {
		return _ddmStructureVersion.getName(locale);
	}

	@Override
	public String getURLViewInContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		String noSuchEntryRedirect) {

		return StringPool.BLANK;
	}

	@Override
	public long getUserId() {
		return _ddmStructureVersion.getUserId();
	}

	@Override
	public String getUserName() {
		return _ddmStructureVersion.getUserName();
	}

	@Override
	public String getUuid() {
		return null;
	}

	@Override
	public boolean include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String template)
		throws Exception {

		httpServletRequest.setAttribute(
			DDMWebKeys.DYNAMIC_DATA_MAPPING_STRUCTURE_VERSION,
			_ddmStructureVersion);

		return super.include(httpServletRequest, httpServletResponse, template);
	}

	private final DDMStructureVersion _ddmStructureVersion;

}