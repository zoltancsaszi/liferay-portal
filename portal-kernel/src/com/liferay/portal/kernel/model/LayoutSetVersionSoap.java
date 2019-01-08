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

package com.liferay.portal.kernel.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class LayoutSetVersionSoap implements Serializable {
	public static LayoutSetVersionSoap toSoapModel(LayoutSetVersion model) {
		LayoutSetVersionSoap soapModel = new LayoutSetVersionSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setLayoutSetVersionId(model.getLayoutSetVersionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setLayoutSetResourceId(model.getLayoutSetResourceId());
		soapModel.setLogoId(model.getLogoId());
		soapModel.setThemeId(model.getThemeId());
		soapModel.setColorSchemeId(model.getColorSchemeId());
		soapModel.setCss(model.getCss());
		soapModel.setSettings(model.getSettings());
		soapModel.setLayoutSetPrototypeUuid(model.getLayoutSetPrototypeUuid());
		soapModel.setLayoutSetPrototypeLinkEnabled(model.isLayoutSetPrototypeLinkEnabled());

		return soapModel;
	}

	public static LayoutSetVersionSoap[] toSoapModels(LayoutSetVersion[] models) {
		LayoutSetVersionSoap[] soapModels = new LayoutSetVersionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LayoutSetVersionSoap[][] toSoapModels(
		LayoutSetVersion[][] models) {
		LayoutSetVersionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LayoutSetVersionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LayoutSetVersionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LayoutSetVersionSoap[] toSoapModels(
		List<LayoutSetVersion> models) {
		List<LayoutSetVersionSoap> soapModels = new ArrayList<LayoutSetVersionSoap>(models.size());

		for (LayoutSetVersion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LayoutSetVersionSoap[soapModels.size()]);
	}

	public LayoutSetVersionSoap() {
	}

	public long getPrimaryKey() {
		return _layoutSetVersionId;
	}

	public void setPrimaryKey(long pk) {
		setLayoutSetVersionId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getLayoutSetVersionId() {
		return _layoutSetVersionId;
	}

	public void setLayoutSetVersionId(long layoutSetVersionId) {
		_layoutSetVersionId = layoutSetVersionId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getLayoutSetResourceId() {
		return _layoutSetResourceId;
	}

	public void setLayoutSetResourceId(long layoutSetResourceId) {
		_layoutSetResourceId = layoutSetResourceId;
	}

	public long getLogoId() {
		return _logoId;
	}

	public void setLogoId(long logoId) {
		_logoId = logoId;
	}

	public String getThemeId() {
		return _themeId;
	}

	public void setThemeId(String themeId) {
		_themeId = themeId;
	}

	public String getColorSchemeId() {
		return _colorSchemeId;
	}

	public void setColorSchemeId(String colorSchemeId) {
		_colorSchemeId = colorSchemeId;
	}

	public String getCss() {
		return _css;
	}

	public void setCss(String css) {
		_css = css;
	}

	public String getSettings() {
		return _settings;
	}

	public void setSettings(String settings) {
		_settings = settings;
	}

	public String getLayoutSetPrototypeUuid() {
		return _layoutSetPrototypeUuid;
	}

	public void setLayoutSetPrototypeUuid(String layoutSetPrototypeUuid) {
		_layoutSetPrototypeUuid = layoutSetPrototypeUuid;
	}

	public boolean getLayoutSetPrototypeLinkEnabled() {
		return _layoutSetPrototypeLinkEnabled;
	}

	public boolean isLayoutSetPrototypeLinkEnabled() {
		return _layoutSetPrototypeLinkEnabled;
	}

	public void setLayoutSetPrototypeLinkEnabled(
		boolean layoutSetPrototypeLinkEnabled) {
		_layoutSetPrototypeLinkEnabled = layoutSetPrototypeLinkEnabled;
	}

	private long _mvccVersion;
	private long _layoutSetVersionId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _layoutSetResourceId;
	private long _logoId;
	private String _themeId;
	private String _colorSchemeId;
	private String _css;
	private String _settings;
	private String _layoutSetPrototypeUuid;
	private boolean _layoutSetPrototypeLinkEnabled;
}