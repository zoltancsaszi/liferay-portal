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

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.dynamic.data.mapping.constants.DDMPortletKeys;
import com.liferay.dynamic.data.mapping.model.DDMStructureVersion;
import com.liferay.dynamic.data.mapping.model.DDMTemplateVersion;
import com.liferay.dynamic.data.mapping.service.DDMTemplateVersionLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltan Csaszi
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + DDMPortletKeys.DYNAMIC_DATA_MAPPING,
	service = AssetRendererFactory.class
)
public class DDMTemplateAssetRendererFactory
	extends BaseAssetRendererFactory<DDMTemplateVersion> {

	public static final String TYPE = "ddm_template";

	public DDMTemplateAssetRendererFactory() {
		setClassName(DDMTemplateVersion.class.getName());
		setSelectable(false);
		setPortletId(DDMPortletKeys.DYNAMIC_DATA_MAPPING);
	}

	@Override
	public AssetEntry getAssetEntry(long assetEntryId) throws PortalException {
		return getAssetEntry(getClassName(), assetEntryId);
	}

	@Override
	public AssetEntry getAssetEntry(String className, long classPK)
		throws PortalException {

		DDMTemplateVersion ddmTemplateVersion =
			_ddmTemplateVersionLocalService.getTemplateVersion(classPK);

		User user = _userLocalService.getUserById(
			ddmTemplateVersion.getUserId());

		AssetEntry assetEntry = _assetEntryLocalService.createAssetEntry(
			classPK);

		assetEntry.setGroupId(ddmTemplateVersion.getGroupId());
		assetEntry.setCompanyId(user.getCompanyId());
		assetEntry.setUserId(user.getUserId());
		assetEntry.setUserName(user.getFullName());
		assetEntry.setCreateDate(ddmTemplateVersion.getCreateDate());
		assetEntry.setClassNameId(
			_portal.getClassNameId(DDMStructureVersion.class.getName()));
		assetEntry.setClassPK(ddmTemplateVersion.getTemplateVersionId());
		assetEntry.setTitle(
			ddmTemplateVersion.getName(LocaleUtil.getSiteDefault()));

		return assetEntry;
	}

	@Override
	public AssetRenderer<DDMTemplateVersion> getAssetRenderer(
			long classPK, int type)
		throws PortalException {

		DDMTemplateVersion ddmTemplateVersion =
			_ddmTemplateVersionLocalService.getTemplateVersion(classPK);

		DDMTemplateAssetRenderer ddmTemplateAssetRenderer =
			new DDMTemplateAssetRenderer(ddmTemplateVersion);

		ddmTemplateAssetRenderer.setAssetRendererType(type);
		ddmTemplateAssetRenderer.setServletContext(_servletContext);

		return ddmTemplateAssetRenderer;
	}

	@Override
	public String getClassName() {
		return DDMTemplateVersion.class.getName();
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean isSearchable() {
		return true;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.dynamic.data.mapping.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private DDMTemplateVersionLocalService _ddmTemplateVersionLocalService;

	@Reference
	private Portal _portal;

	private ServletContext _servletContext;

	@Reference
	private UserLocalService _userLocalService;

}