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

package com.liferay.change.tracking.model;

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
public class CTEntrySoap implements Serializable {

	public static CTEntrySoap toSoapModel(CTEntry model) {
		CTEntrySoap soapModel = new CTEntrySoap();

		soapModel.setCtEntryId(model.getCtEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setModelClassNameId(model.getModelClassNameId());
		soapModel.setModelClassPK(model.getModelClassPK());
		soapModel.setModelResourcePrimKey(model.getModelResourcePrimKey());
		soapModel.setChangeType(model.getChangeType());
		soapModel.setStatus(model.getStatus());
		soapModel.setOriginalCollectionId(model.getOriginalCollectionId());
		soapModel.setCollision(model.isCollision());

		return soapModel;
	}

	public static CTEntrySoap[] toSoapModels(CTEntry[] models) {
		CTEntrySoap[] soapModels = new CTEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CTEntrySoap[][] toSoapModels(CTEntry[][] models) {
		CTEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CTEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CTEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CTEntrySoap[] toSoapModels(List<CTEntry> models) {
		List<CTEntrySoap> soapModels = new ArrayList<CTEntrySoap>(
			models.size());

		for (CTEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CTEntrySoap[soapModels.size()]);
	}

	public CTEntrySoap() {
	}

	public long getPrimaryKey() {
		return _ctEntryId;
	}

	public void setPrimaryKey(long pk) {
		setCtEntryId(pk);
	}

	public long getCtEntryId() {
		return _ctEntryId;
	}

	public void setCtEntryId(long ctEntryId) {
		_ctEntryId = ctEntryId;
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

	public long getModelClassNameId() {
		return _modelClassNameId;
	}

	public void setModelClassNameId(long modelClassNameId) {
		_modelClassNameId = modelClassNameId;
	}

	public long getModelClassPK() {
		return _modelClassPK;
	}

	public void setModelClassPK(long modelClassPK) {
		_modelClassPK = modelClassPK;
	}

	public long getModelResourcePrimKey() {
		return _modelResourcePrimKey;
	}

	public void setModelResourcePrimKey(long modelResourcePrimKey) {
		_modelResourcePrimKey = modelResourcePrimKey;
	}

	public int getChangeType() {
		return _changeType;
	}

	public void setChangeType(int changeType) {
		_changeType = changeType;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getOriginalCollectionId() {
		return _originalCollectionId;
	}

	public void setOriginalCollectionId(long originalCollectionId) {
		_originalCollectionId = originalCollectionId;
	}

	public boolean getCollision() {
		return _collision;
	}

	public boolean isCollision() {
		return _collision;
	}

	public void setCollision(boolean collision) {
		_collision = collision;
	}

	private long _ctEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _modelClassNameId;
	private long _modelClassPK;
	private long _modelResourcePrimKey;
	private int _changeType;
	private int _status;
	private long _originalCollectionId;
	private boolean _collision;

}