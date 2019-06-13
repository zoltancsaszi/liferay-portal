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
import com.liferay.dynamic.data.mapping.model.DDMTemplateVersion;
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
public class DDMTemplateAssetRenderer
	extends BaseJSPAssetRenderer<DDMTemplateVersion> {

	public static final String TYPE = "ddm_template";

	public DDMTemplateAssetRenderer(DDMTemplateVersion ddmStructureVersion) {
		_ddmTemplateVersion = ddmStructureVersion;
	}

	@Override
	public DDMTemplateVersion getAssetObject() {
		return _ddmTemplateVersion;
	}

	@Override
	public String getClassName() {
		return DDMTemplateVersion.class.getName();
	}

	@Override
	public long getClassPK() {
		return _ddmTemplateVersion.getTemplateVersionId();
	}

	@Override
	public long getGroupId() {
		return _ddmTemplateVersion.getGroupId();
	}

	@Override
	public String getJspPath(
		HttpServletRequest httpServletRequest, String template) {

		if (template.equals(TEMPLATE_FULL_CONTENT)) {
			return "/asset/template/" + template + ".jsp";
		}

		return null;
	}

	@Override
	public String getSummary(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		Locale locale = getLocale(portletRequest);

		StringBundler sb = new StringBundler(4);

		sb.append("<strong>");
		sb.append(LanguageUtil.get(locale, "template"));
		sb.append(":</strong> ");
		sb.append(_ddmTemplateVersion.getName(locale));

		return sb.toString();
	}

	public DDMTemplateVersion getTemplateVersion() {
		return _ddmTemplateVersion;
	}

	@Override
	public String getTitle(Locale locale) {
		return _ddmTemplateVersion.getName(locale);
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
		return _ddmTemplateVersion.getUserId();
	}

	@Override
	public String getUserName() {
		return _ddmTemplateVersion.getUserName();
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
			_ddmTemplateVersion);

		return super.include(httpServletRequest, httpServletResponse, template);
	}

	private final DDMTemplateVersion _ddmTemplateVersion;

}