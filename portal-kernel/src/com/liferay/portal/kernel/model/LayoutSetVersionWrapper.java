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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LayoutSetVersion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetVersion
 * @generated
 */
@ProviderType
public class LayoutSetVersionWrapper implements LayoutSetVersion,
	ModelWrapper<LayoutSetVersion> {
	public LayoutSetVersionWrapper(LayoutSetVersion layoutSetVersion) {
		_layoutSetVersion = layoutSetVersion;
	}

	@Override
	public Class<?> getModelClass() {
		return LayoutSetVersion.class;
	}

	@Override
	public String getModelClassName() {
		return LayoutSetVersion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("layoutSetVersionId", getLayoutSetVersionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("layoutSetResourceId", getLayoutSetResourceId());
		attributes.put("logoId", getLogoId());
		attributes.put("themeId", getThemeId());
		attributes.put("colorSchemeId", getColorSchemeId());
		attributes.put("css", getCss());
		attributes.put("settings", getSettings());
		attributes.put("layoutSetPrototypeUuid", getLayoutSetPrototypeUuid());
		attributes.put("layoutSetPrototypeLinkEnabled",
			isLayoutSetPrototypeLinkEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long layoutSetVersionId = (Long)attributes.get("layoutSetVersionId");

		if (layoutSetVersionId != null) {
			setLayoutSetVersionId(layoutSetVersionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long layoutSetResourceId = (Long)attributes.get("layoutSetResourceId");

		if (layoutSetResourceId != null) {
			setLayoutSetResourceId(layoutSetResourceId);
		}

		Long logoId = (Long)attributes.get("logoId");

		if (logoId != null) {
			setLogoId(logoId);
		}

		String themeId = (String)attributes.get("themeId");

		if (themeId != null) {
			setThemeId(themeId);
		}

		String colorSchemeId = (String)attributes.get("colorSchemeId");

		if (colorSchemeId != null) {
			setColorSchemeId(colorSchemeId);
		}

		String css = (String)attributes.get("css");

		if (css != null) {
			setCss(css);
		}

		String settings = (String)attributes.get("settings");

		if (settings != null) {
			setSettings(settings);
		}

		String layoutSetPrototypeUuid = (String)attributes.get(
				"layoutSetPrototypeUuid");

		if (layoutSetPrototypeUuid != null) {
			setLayoutSetPrototypeUuid(layoutSetPrototypeUuid);
		}

		Boolean layoutSetPrototypeLinkEnabled = (Boolean)attributes.get(
				"layoutSetPrototypeLinkEnabled");

		if (layoutSetPrototypeLinkEnabled != null) {
			setLayoutSetPrototypeLinkEnabled(layoutSetPrototypeLinkEnabled);
		}
	}

	@Override
	public Object clone() {
		return new LayoutSetVersionWrapper((LayoutSetVersion)_layoutSetVersion.clone());
	}

	@Override
	public int compareTo(LayoutSetVersion layoutSetVersion) {
		return _layoutSetVersion.compareTo(layoutSetVersion);
	}

	/**
	* Returns the color scheme ID of this layout set version.
	*
	* @return the color scheme ID of this layout set version
	*/
	@Override
	public String getColorSchemeId() {
		return _layoutSetVersion.getColorSchemeId();
	}

	/**
	* Returns the company ID of this layout set version.
	*
	* @return the company ID of this layout set version
	*/
	@Override
	public long getCompanyId() {
		return _layoutSetVersion.getCompanyId();
	}

	/**
	* Returns the create date of this layout set version.
	*
	* @return the create date of this layout set version
	*/
	@Override
	public Date getCreateDate() {
		return _layoutSetVersion.getCreateDate();
	}

	/**
	* Returns the css of this layout set version.
	*
	* @return the css of this layout set version
	*/
	@Override
	public String getCss() {
		return _layoutSetVersion.getCss();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _layoutSetVersion.getExpandoBridge();
	}

	/**
	* Returns the layout set prototype link enabled of this layout set version.
	*
	* @return the layout set prototype link enabled of this layout set version
	*/
	@Override
	public boolean getLayoutSetPrototypeLinkEnabled() {
		return _layoutSetVersion.getLayoutSetPrototypeLinkEnabled();
	}

	/**
	* Returns the layout set prototype uuid of this layout set version.
	*
	* @return the layout set prototype uuid of this layout set version
	*/
	@Override
	public String getLayoutSetPrototypeUuid() {
		return _layoutSetVersion.getLayoutSetPrototypeUuid();
	}

	/**
	* Returns the layout set resource ID of this layout set version.
	*
	* @return the layout set resource ID of this layout set version
	*/
	@Override
	public long getLayoutSetResourceId() {
		return _layoutSetVersion.getLayoutSetResourceId();
	}

	/**
	* Returns the layout set version ID of this layout set version.
	*
	* @return the layout set version ID of this layout set version
	*/
	@Override
	public long getLayoutSetVersionId() {
		return _layoutSetVersion.getLayoutSetVersionId();
	}

	/**
	* Returns the logo ID of this layout set version.
	*
	* @return the logo ID of this layout set version
	*/
	@Override
	public long getLogoId() {
		return _layoutSetVersion.getLogoId();
	}

	/**
	* Returns the modified date of this layout set version.
	*
	* @return the modified date of this layout set version
	*/
	@Override
	public Date getModifiedDate() {
		return _layoutSetVersion.getModifiedDate();
	}

	/**
	* Returns the mvcc version of this layout set version.
	*
	* @return the mvcc version of this layout set version
	*/
	@Override
	public long getMvccVersion() {
		return _layoutSetVersion.getMvccVersion();
	}

	/**
	* Returns the primary key of this layout set version.
	*
	* @return the primary key of this layout set version
	*/
	@Override
	public long getPrimaryKey() {
		return _layoutSetVersion.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _layoutSetVersion.getPrimaryKeyObj();
	}

	/**
	* Returns the settings of this layout set version.
	*
	* @return the settings of this layout set version
	*/
	@Override
	public String getSettings() {
		return _layoutSetVersion.getSettings();
	}

	@Override
	public com.liferay.portal.kernel.util.UnicodeProperties getSettingsProperties() {
		return _layoutSetVersion.getSettingsProperties();
	}

	/**
	* Returns the theme ID of this layout set version.
	*
	* @return the theme ID of this layout set version
	*/
	@Override
	public String getThemeId() {
		return _layoutSetVersion.getThemeId();
	}

	@Override
	public int hashCode() {
		return _layoutSetVersion.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _layoutSetVersion.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _layoutSetVersion.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this layout set version is layout set prototype link enabled.
	*
	* @return <code>true</code> if this layout set version is layout set prototype link enabled; <code>false</code> otherwise
	*/
	@Override
	public boolean isLayoutSetPrototypeLinkEnabled() {
		return _layoutSetVersion.isLayoutSetPrototypeLinkEnabled();
	}

	@Override
	public boolean isNew() {
		return _layoutSetVersion.isNew();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_layoutSetVersion.setCachedModel(cachedModel);
	}

	/**
	* Sets the color scheme ID of this layout set version.
	*
	* @param colorSchemeId the color scheme ID of this layout set version
	*/
	@Override
	public void setColorSchemeId(String colorSchemeId) {
		_layoutSetVersion.setColorSchemeId(colorSchemeId);
	}

	/**
	* Sets the company ID of this layout set version.
	*
	* @param companyId the company ID of this layout set version
	*/
	@Override
	public void setCompanyId(long companyId) {
		_layoutSetVersion.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this layout set version.
	*
	* @param createDate the create date of this layout set version
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_layoutSetVersion.setCreateDate(createDate);
	}

	/**
	* Sets the css of this layout set version.
	*
	* @param css the css of this layout set version
	*/
	@Override
	public void setCss(String css) {
		_layoutSetVersion.setCss(css);
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
		_layoutSetVersion.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_layoutSetVersion.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_layoutSetVersion.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this layout set version is layout set prototype link enabled.
	*
	* @param layoutSetPrototypeLinkEnabled the layout set prototype link enabled of this layout set version
	*/
	@Override
	public void setLayoutSetPrototypeLinkEnabled(
		boolean layoutSetPrototypeLinkEnabled) {
		_layoutSetVersion.setLayoutSetPrototypeLinkEnabled(layoutSetPrototypeLinkEnabled);
	}

	/**
	* Sets the layout set prototype uuid of this layout set version.
	*
	* @param layoutSetPrototypeUuid the layout set prototype uuid of this layout set version
	*/
	@Override
	public void setLayoutSetPrototypeUuid(String layoutSetPrototypeUuid) {
		_layoutSetVersion.setLayoutSetPrototypeUuid(layoutSetPrototypeUuid);
	}

	/**
	* Sets the layout set resource ID of this layout set version.
	*
	* @param layoutSetResourceId the layout set resource ID of this layout set version
	*/
	@Override
	public void setLayoutSetResourceId(long layoutSetResourceId) {
		_layoutSetVersion.setLayoutSetResourceId(layoutSetResourceId);
	}

	/**
	* Sets the layout set version ID of this layout set version.
	*
	* @param layoutSetVersionId the layout set version ID of this layout set version
	*/
	@Override
	public void setLayoutSetVersionId(long layoutSetVersionId) {
		_layoutSetVersion.setLayoutSetVersionId(layoutSetVersionId);
	}

	/**
	* Sets the logo ID of this layout set version.
	*
	* @param logoId the logo ID of this layout set version
	*/
	@Override
	public void setLogoId(long logoId) {
		_layoutSetVersion.setLogoId(logoId);
	}

	/**
	* Sets the modified date of this layout set version.
	*
	* @param modifiedDate the modified date of this layout set version
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_layoutSetVersion.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the mvcc version of this layout set version.
	*
	* @param mvccVersion the mvcc version of this layout set version
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		_layoutSetVersion.setMvccVersion(mvccVersion);
	}

	@Override
	public void setNew(boolean n) {
		_layoutSetVersion.setNew(n);
	}

	/**
	* Sets the primary key of this layout set version.
	*
	* @param primaryKey the primary key of this layout set version
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_layoutSetVersion.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_layoutSetVersion.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the settings of this layout set version.
	*
	* @param settings the settings of this layout set version
	*/
	@Override
	public void setSettings(String settings) {
		_layoutSetVersion.setSettings(settings);
	}

	@Override
	public void setSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties settingsProperties) {
		_layoutSetVersion.setSettingsProperties(settingsProperties);
	}

	/**
	* Sets the theme ID of this layout set version.
	*
	* @param themeId the theme ID of this layout set version
	*/
	@Override
	public void setThemeId(String themeId) {
		_layoutSetVersion.setThemeId(themeId);
	}

	@Override
	public CacheModel<LayoutSetVersion> toCacheModel() {
		return _layoutSetVersion.toCacheModel();
	}

	@Override
	public LayoutSetVersion toEscapedModel() {
		return new LayoutSetVersionWrapper(_layoutSetVersion.toEscapedModel());
	}

	@Override
	public String toString() {
		return _layoutSetVersion.toString();
	}

	@Override
	public LayoutSetVersion toUnescapedModel() {
		return new LayoutSetVersionWrapper(_layoutSetVersion.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _layoutSetVersion.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LayoutSetVersionWrapper)) {
			return false;
		}

		LayoutSetVersionWrapper layoutSetVersionWrapper = (LayoutSetVersionWrapper)obj;

		if (Objects.equals(_layoutSetVersion,
					layoutSetVersionWrapper._layoutSetVersion)) {
			return true;
		}

		return false;
	}

	@Override
	public LayoutSetVersion getWrappedModel() {
		return _layoutSetVersion;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _layoutSetVersion.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _layoutSetVersion.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_layoutSetVersion.resetOriginalValues();
	}

	private final LayoutSetVersion _layoutSetVersion;
}