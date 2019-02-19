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

package com.liferay.asset.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AssetCategoryVersion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetCategoryVersion
 * @generated
 */
@ProviderType
public class AssetCategoryVersionWrapper extends BaseModelWrapper<AssetCategoryVersion>
	implements AssetCategoryVersion, ModelWrapper<AssetCategoryVersion> {
	public AssetCategoryVersionWrapper(
		AssetCategoryVersion assetCategoryVersion) {
		super(assetCategoryVersion);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetCategoryVersionId", getAssetCategoryVersionId());
		attributes.put("version", getVersion());
		attributes.put("uuid", getUuid());
		attributes.put("externalReferenceCode", getExternalReferenceCode());
		attributes.put("categoryId", getCategoryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentCategoryId", getParentCategoryId());
		attributes.put("leftCategoryId", getLeftCategoryId());
		attributes.put("rightCategoryId", getRightCategoryId());
		attributes.put("name", getName());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("vocabularyId", getVocabularyId());
		attributes.put("lastPublishDate", getLastPublishDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetCategoryVersionId = (Long)attributes.get(
				"assetCategoryVersionId");

		if (assetCategoryVersionId != null) {
			setAssetCategoryVersionId(assetCategoryVersionId);
		}

		Integer version = (Integer)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		String externalReferenceCode = (String)attributes.get(
				"externalReferenceCode");

		if (externalReferenceCode != null) {
			setExternalReferenceCode(externalReferenceCode);
		}

		Long categoryId = (Long)attributes.get("categoryId");

		if (categoryId != null) {
			setCategoryId(categoryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long parentCategoryId = (Long)attributes.get("parentCategoryId");

		if (parentCategoryId != null) {
			setParentCategoryId(parentCategoryId);
		}

		Long leftCategoryId = (Long)attributes.get("leftCategoryId");

		if (leftCategoryId != null) {
			setLeftCategoryId(leftCategoryId);
		}

		Long rightCategoryId = (Long)attributes.get("rightCategoryId");

		if (rightCategoryId != null) {
			setRightCategoryId(rightCategoryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long vocabularyId = (Long)attributes.get("vocabularyId");

		if (vocabularyId != null) {
			setVocabularyId(vocabularyId);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}
	}

	/**
	* Returns the asset category version ID of this asset category version.
	*
	* @return the asset category version ID of this asset category version
	*/
	@Override
	public long getAssetCategoryVersionId() {
		return model.getAssetCategoryVersionId();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return model.getAvailableLanguageIds();
	}

	/**
	* Returns the category ID of this asset category version.
	*
	* @return the category ID of this asset category version
	*/
	@Override
	public long getCategoryId() {
		return model.getCategoryId();
	}

	/**
	* Returns the company ID of this asset category version.
	*
	* @return the company ID of this asset category version
	*/
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	* Returns the create date of this asset category version.
	*
	* @return the create date of this asset category version
	*/
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return model.getDefaultLanguageId();
	}

	/**
	* Returns the description of this asset category version.
	*
	* @return the description of this asset category version
	*/
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	* Returns the localized description of this asset category version in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this asset category version
	*/
	@Override
	public String getDescription(java.util.Locale locale) {
		return model.getDescription(locale);
	}

	/**
	* Returns the localized description of this asset category version in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this asset category version. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getDescription(java.util.Locale locale, boolean useDefault) {
		return model.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this asset category version in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this asset category version
	*/
	@Override
	public String getDescription(String languageId) {
		return model.getDescription(languageId);
	}

	/**
	* Returns the localized description of this asset category version in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this asset category version
	*/
	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return model.getDescription(languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return model.getDescriptionCurrentLanguageId();
	}

	@Override
	public String getDescriptionCurrentValue() {
		return model.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this asset category version.
	*
	* @return the locales and localized descriptions of this asset category version
	*/
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return model.getDescriptionMap();
	}

	/**
	* Returns the external reference code of this asset category version.
	*
	* @return the external reference code of this asset category version
	*/
	@Override
	public String getExternalReferenceCode() {
		return model.getExternalReferenceCode();
	}

	/**
	* Returns the group ID of this asset category version.
	*
	* @return the group ID of this asset category version
	*/
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	* Returns the last publish date of this asset category version.
	*
	* @return the last publish date of this asset category version
	*/
	@Override
	public Date getLastPublishDate() {
		return model.getLastPublishDate();
	}

	/**
	* Returns the left category ID of this asset category version.
	*
	* @return the left category ID of this asset category version
	*/
	@Override
	public long getLeftCategoryId() {
		return model.getLeftCategoryId();
	}

	/**
	* Returns the modified date of this asset category version.
	*
	* @return the modified date of this asset category version
	*/
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	* Returns the name of this asset category version.
	*
	* @return the name of this asset category version
	*/
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	* Returns the parent category ID of this asset category version.
	*
	* @return the parent category ID of this asset category version
	*/
	@Override
	public long getParentCategoryId() {
		return model.getParentCategoryId();
	}

	/**
	* Returns the primary key of this asset category version.
	*
	* @return the primary key of this asset category version
	*/
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	* Returns the right category ID of this asset category version.
	*
	* @return the right category ID of this asset category version
	*/
	@Override
	public long getRightCategoryId() {
		return model.getRightCategoryId();
	}

	/**
	* Returns the title of this asset category version.
	*
	* @return the title of this asset category version
	*/
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	* Returns the localized title of this asset category version in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this asset category version
	*/
	@Override
	public String getTitle(java.util.Locale locale) {
		return model.getTitle(locale);
	}

	/**
	* Returns the localized title of this asset category version in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this asset category version. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getTitle(java.util.Locale locale, boolean useDefault) {
		return model.getTitle(locale, useDefault);
	}

	/**
	* Returns the localized title of this asset category version in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this asset category version
	*/
	@Override
	public String getTitle(String languageId) {
		return model.getTitle(languageId);
	}

	/**
	* Returns the localized title of this asset category version in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this asset category version
	*/
	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return model.getTitle(languageId, useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return model.getTitleCurrentLanguageId();
	}

	@Override
	public String getTitleCurrentValue() {
		return model.getTitleCurrentValue();
	}

	/**
	* Returns a map of the locales and localized titles of this asset category version.
	*
	* @return the locales and localized titles of this asset category version
	*/
	@Override
	public Map<java.util.Locale, String> getTitleMap() {
		return model.getTitleMap();
	}

	/**
	* Returns the user ID of this asset category version.
	*
	* @return the user ID of this asset category version
	*/
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	* Returns the user name of this asset category version.
	*
	* @return the user name of this asset category version
	*/
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	* Returns the user uuid of this asset category version.
	*
	* @return the user uuid of this asset category version
	*/
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	* Returns the uuid of this asset category version.
	*
	* @return the uuid of this asset category version
	*/
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	* Returns the version of this asset category version.
	*
	* @return the version of this asset category version
	*/
	@Override
	public int getVersion() {
		return model.getVersion();
	}

	/**
	* Returns the vocabulary ID of this asset category version.
	*
	* @return the vocabulary ID of this asset category version
	*/
	@Override
	public long getVocabularyId() {
		return model.getVocabularyId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		model.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		model.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the asset category version ID of this asset category version.
	*
	* @param assetCategoryVersionId the asset category version ID of this asset category version
	*/
	@Override
	public void setAssetCategoryVersionId(long assetCategoryVersionId) {
		model.setAssetCategoryVersionId(assetCategoryVersionId);
	}

	/**
	* Sets the category ID of this asset category version.
	*
	* @param categoryId the category ID of this asset category version
	*/
	@Override
	public void setCategoryId(long categoryId) {
		model.setCategoryId(categoryId);
	}

	/**
	* Sets the company ID of this asset category version.
	*
	* @param companyId the company ID of this asset category version
	*/
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this asset category version.
	*
	* @param createDate the create date of this asset category version
	*/
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	* Sets the description of this asset category version.
	*
	* @param description the description of this asset category version
	*/
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	* Sets the localized description of this asset category version in the language.
	*
	* @param description the localized description of this asset category version
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		model.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this asset category version in the language, and sets the default locale.
	*
	* @param description the localized description of this asset category version
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(String description, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		model.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		model.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this asset category version from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this asset category version
	*/
	@Override
	public void setDescriptionMap(Map<java.util.Locale, String> descriptionMap) {
		model.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this asset category version from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this asset category version
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap,
		java.util.Locale defaultLocale) {
		model.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Sets the external reference code of this asset category version.
	*
	* @param externalReferenceCode the external reference code of this asset category version
	*/
	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		model.setExternalReferenceCode(externalReferenceCode);
	}

	/**
	* Sets the group ID of this asset category version.
	*
	* @param groupId the group ID of this asset category version
	*/
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	* Sets the last publish date of this asset category version.
	*
	* @param lastPublishDate the last publish date of this asset category version
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		model.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the left category ID of this asset category version.
	*
	* @param leftCategoryId the left category ID of this asset category version
	*/
	@Override
	public void setLeftCategoryId(long leftCategoryId) {
		model.setLeftCategoryId(leftCategoryId);
	}

	/**
	* Sets the modified date of this asset category version.
	*
	* @param modifiedDate the modified date of this asset category version
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this asset category version.
	*
	* @param name the name of this asset category version
	*/
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	* Sets the parent category ID of this asset category version.
	*
	* @param parentCategoryId the parent category ID of this asset category version
	*/
	@Override
	public void setParentCategoryId(long parentCategoryId) {
		model.setParentCategoryId(parentCategoryId);
	}

	/**
	* Sets the primary key of this asset category version.
	*
	* @param primaryKey the primary key of this asset category version
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	* Sets the right category ID of this asset category version.
	*
	* @param rightCategoryId the right category ID of this asset category version
	*/
	@Override
	public void setRightCategoryId(long rightCategoryId) {
		model.setRightCategoryId(rightCategoryId);
	}

	/**
	* Sets the title of this asset category version.
	*
	* @param title the title of this asset category version
	*/
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	* Sets the localized title of this asset category version in the language.
	*
	* @param title the localized title of this asset category version
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(String title, java.util.Locale locale) {
		model.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this asset category version in the language, and sets the default locale.
	*
	* @param title the localized title of this asset category version
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		model.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		model.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this asset category version from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this asset category version
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap) {
		model.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this asset category version from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this asset category version
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap,
		java.util.Locale defaultLocale) {
		model.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the user ID of this asset category version.
	*
	* @param userId the user ID of this asset category version
	*/
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	* Sets the user name of this asset category version.
	*
	* @param userName the user name of this asset category version
	*/
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	* Sets the user uuid of this asset category version.
	*
	* @param userUuid the user uuid of this asset category version
	*/
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this asset category version.
	*
	* @param uuid the uuid of this asset category version
	*/
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	/**
	* Sets the version of this asset category version.
	*
	* @param version the version of this asset category version
	*/
	@Override
	public void setVersion(int version) {
		model.setVersion(version);
	}

	/**
	* Sets the vocabulary ID of this asset category version.
	*
	* @param vocabularyId the vocabulary ID of this asset category version
	*/
	@Override
	public void setVocabularyId(long vocabularyId) {
		model.setVocabularyId(vocabularyId);
	}

	@Override
	public long getVersionedModelId() {
		return model.getVersionedModelId();
	}

	@Override
	public void setVersionedModelId(long id) {
		model.setVersionedModelId(id);
	}

	@Override
	public void populateVersionedModel(AssetCategory assetCategory) {
		model.populateVersionedModel(assetCategory);
	}

	@Override
	public AssetCategory toVersionedModel() {
		return model.toVersionedModel();
	}

	@Override
	protected AssetCategoryVersionWrapper wrap(
		AssetCategoryVersion assetCategoryVersion) {
		return new AssetCategoryVersionWrapper(assetCategoryVersion);
	}
}