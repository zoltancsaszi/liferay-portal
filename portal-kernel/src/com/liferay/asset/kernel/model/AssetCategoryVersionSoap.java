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
public class AssetCategoryVersionSoap implements Serializable {
	public static AssetCategoryVersionSoap toSoapModel(
		AssetCategoryVersion model) {
		AssetCategoryVersionSoap soapModel = new AssetCategoryVersionSoap();

		soapModel.setAssetCategoryVersionId(model.getAssetCategoryVersionId());
		soapModel.setVersion(model.getVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setExternalReferenceCode(model.getExternalReferenceCode());
		soapModel.setCategoryId(model.getCategoryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setParentCategoryId(model.getParentCategoryId());
		soapModel.setLeftCategoryId(model.getLeftCategoryId());
		soapModel.setRightCategoryId(model.getRightCategoryId());
		soapModel.setName(model.getName());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setVocabularyId(model.getVocabularyId());
		soapModel.setLastPublishDate(model.getLastPublishDate());

		return soapModel;
	}

	public static AssetCategoryVersionSoap[] toSoapModels(
		AssetCategoryVersion[] models) {
		AssetCategoryVersionSoap[] soapModels = new AssetCategoryVersionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetCategoryVersionSoap[][] toSoapModels(
		AssetCategoryVersion[][] models) {
		AssetCategoryVersionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetCategoryVersionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetCategoryVersionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetCategoryVersionSoap[] toSoapModels(
		List<AssetCategoryVersion> models) {
		List<AssetCategoryVersionSoap> soapModels = new ArrayList<AssetCategoryVersionSoap>(models.size());

		for (AssetCategoryVersion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetCategoryVersionSoap[soapModels.size()]);
	}

	public AssetCategoryVersionSoap() {
	}

	public long getPrimaryKey() {
		return _assetCategoryVersionId;
	}

	public void setPrimaryKey(long pk) {
		setAssetCategoryVersionId(pk);
	}

	public long getAssetCategoryVersionId() {
		return _assetCategoryVersionId;
	}

	public void setAssetCategoryVersionId(long assetCategoryVersionId) {
		_assetCategoryVersionId = assetCategoryVersionId;
	}

	public int getVersion() {
		return _version;
	}

	public void setVersion(int version) {
		_version = version;
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public long getCategoryId() {
		return _categoryId;
	}

	public void setCategoryId(long categoryId) {
		_categoryId = categoryId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
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

	public long getParentCategoryId() {
		return _parentCategoryId;
	}

	public void setParentCategoryId(long parentCategoryId) {
		_parentCategoryId = parentCategoryId;
	}

	public long getLeftCategoryId() {
		return _leftCategoryId;
	}

	public void setLeftCategoryId(long leftCategoryId) {
		_leftCategoryId = leftCategoryId;
	}

	public long getRightCategoryId() {
		return _rightCategoryId;
	}

	public void setRightCategoryId(long rightCategoryId) {
		_rightCategoryId = rightCategoryId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public long getVocabularyId() {
		return _vocabularyId;
	}

	public void setVocabularyId(long vocabularyId) {
		_vocabularyId = vocabularyId;
	}

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	private long _assetCategoryVersionId;
	private int _version;
	private String _uuid;
	private String _externalReferenceCode;
	private long _categoryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _parentCategoryId;
	private long _leftCategoryId;
	private long _rightCategoryId;
	private String _name;
	private String _title;
	private String _description;
	private long _vocabularyId;
	private Date _lastPublishDate;
}