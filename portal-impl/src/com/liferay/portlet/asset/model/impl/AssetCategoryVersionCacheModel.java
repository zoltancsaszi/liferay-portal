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

package com.liferay.portlet.asset.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.kernel.model.AssetCategoryVersion;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AssetCategoryVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AssetCategoryVersionCacheModel implements CacheModel<AssetCategoryVersion>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetCategoryVersionCacheModel)) {
			return false;
		}

		AssetCategoryVersionCacheModel assetCategoryVersionCacheModel = (AssetCategoryVersionCacheModel)obj;

		if (assetCategoryVersionId == assetCategoryVersionCacheModel.assetCategoryVersionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, assetCategoryVersionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{assetCategoryVersionId=");
		sb.append(assetCategoryVersionId);
		sb.append(", version=");
		sb.append(version);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", categoryId=");
		sb.append(categoryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", parentCategoryId=");
		sb.append(parentCategoryId);
		sb.append(", leftCategoryId=");
		sb.append(leftCategoryId);
		sb.append(", rightCategoryId=");
		sb.append(rightCategoryId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", vocabularyId=");
		sb.append(vocabularyId);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AssetCategoryVersion toEntityModel() {
		AssetCategoryVersionImpl assetCategoryVersionImpl = new AssetCategoryVersionImpl();

		assetCategoryVersionImpl.setAssetCategoryVersionId(assetCategoryVersionId);
		assetCategoryVersionImpl.setVersion(version);

		if (uuid == null) {
			assetCategoryVersionImpl.setUuid("");
		}
		else {
			assetCategoryVersionImpl.setUuid(uuid);
		}

		if (externalReferenceCode == null) {
			assetCategoryVersionImpl.setExternalReferenceCode("");
		}
		else {
			assetCategoryVersionImpl.setExternalReferenceCode(externalReferenceCode);
		}

		assetCategoryVersionImpl.setCategoryId(categoryId);
		assetCategoryVersionImpl.setGroupId(groupId);
		assetCategoryVersionImpl.setCompanyId(companyId);
		assetCategoryVersionImpl.setUserId(userId);

		if (userName == null) {
			assetCategoryVersionImpl.setUserName("");
		}
		else {
			assetCategoryVersionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			assetCategoryVersionImpl.setCreateDate(null);
		}
		else {
			assetCategoryVersionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			assetCategoryVersionImpl.setModifiedDate(null);
		}
		else {
			assetCategoryVersionImpl.setModifiedDate(new Date(modifiedDate));
		}

		assetCategoryVersionImpl.setParentCategoryId(parentCategoryId);
		assetCategoryVersionImpl.setLeftCategoryId(leftCategoryId);
		assetCategoryVersionImpl.setRightCategoryId(rightCategoryId);

		if (name == null) {
			assetCategoryVersionImpl.setName("");
		}
		else {
			assetCategoryVersionImpl.setName(name);
		}

		if (title == null) {
			assetCategoryVersionImpl.setTitle("");
		}
		else {
			assetCategoryVersionImpl.setTitle(title);
		}

		if (description == null) {
			assetCategoryVersionImpl.setDescription("");
		}
		else {
			assetCategoryVersionImpl.setDescription(description);
		}

		assetCategoryVersionImpl.setVocabularyId(vocabularyId);

		if (lastPublishDate == Long.MIN_VALUE) {
			assetCategoryVersionImpl.setLastPublishDate(null);
		}
		else {
			assetCategoryVersionImpl.setLastPublishDate(new Date(
					lastPublishDate));
		}

		assetCategoryVersionImpl.resetOriginalValues();

		return assetCategoryVersionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		assetCategoryVersionId = objectInput.readLong();

		version = objectInput.readInt();
		uuid = objectInput.readUTF();
		externalReferenceCode = objectInput.readUTF();

		categoryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		parentCategoryId = objectInput.readLong();

		leftCategoryId = objectInput.readLong();

		rightCategoryId = objectInput.readLong();
		name = objectInput.readUTF();
		title = objectInput.readUTF();
		description = objectInput.readUTF();

		vocabularyId = objectInput.readLong();
		lastPublishDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(assetCategoryVersionId);

		objectOutput.writeInt(version);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		if (externalReferenceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalReferenceCode);
		}

		objectOutput.writeLong(categoryId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(parentCategoryId);

		objectOutput.writeLong(leftCategoryId);

		objectOutput.writeLong(rightCategoryId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(vocabularyId);
		objectOutput.writeLong(lastPublishDate);
	}

	public long assetCategoryVersionId;
	public int version;
	public String uuid;
	public String externalReferenceCode;
	public long categoryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long parentCategoryId;
	public long leftCategoryId;
	public long rightCategoryId;
	public String name;
	public String title;
	public String description;
	public long vocabularyId;
	public long lastPublishDate;
}