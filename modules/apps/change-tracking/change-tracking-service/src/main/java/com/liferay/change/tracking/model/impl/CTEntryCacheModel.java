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

package com.liferay.change.tracking.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.change.tracking.model.CTEntry;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CTEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class CTEntryCacheModel implements CacheModel<CTEntry>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CTEntryCacheModel)) {
			return false;
		}

		CTEntryCacheModel ctEntryCacheModel = (CTEntryCacheModel)obj;

		if (ctEntryId == ctEntryCacheModel.ctEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ctEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{ctEntryId=");
		sb.append(ctEntryId);
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
		sb.append(", modelClassNameId=");
		sb.append(modelClassNameId);
		sb.append(", modelClassPK=");
		sb.append(modelClassPK);
		sb.append(", modelResourcePrimKey=");
		sb.append(modelResourcePrimKey);
		sb.append(", changeType=");
		sb.append(changeType);
		sb.append(", status=");
		sb.append(status);
		sb.append(", originalCollectionId=");
		sb.append(originalCollectionId);
		sb.append(", collision=");
		sb.append(collision);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CTEntry toEntityModel() {
		CTEntryImpl ctEntryImpl = new CTEntryImpl();

		ctEntryImpl.setCtEntryId(ctEntryId);
		ctEntryImpl.setCompanyId(companyId);
		ctEntryImpl.setUserId(userId);

		if (userName == null) {
			ctEntryImpl.setUserName("");
		}
		else {
			ctEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ctEntryImpl.setCreateDate(null);
		}
		else {
			ctEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ctEntryImpl.setModifiedDate(null);
		}
		else {
			ctEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		ctEntryImpl.setModelClassNameId(modelClassNameId);
		ctEntryImpl.setModelClassPK(modelClassPK);
		ctEntryImpl.setModelResourcePrimKey(modelResourcePrimKey);
		ctEntryImpl.setChangeType(changeType);
		ctEntryImpl.setStatus(status);
		ctEntryImpl.setOriginalCollectionId(originalCollectionId);
		ctEntryImpl.setCollision(collision);

		ctEntryImpl.resetOriginalValues();

		return ctEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ctEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		modelClassNameId = objectInput.readLong();

		modelClassPK = objectInput.readLong();

		modelResourcePrimKey = objectInput.readLong();

		changeType = objectInput.readInt();

		status = objectInput.readInt();

		originalCollectionId = objectInput.readLong();

		collision = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(ctEntryId);

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

		objectOutput.writeLong(modelClassNameId);

		objectOutput.writeLong(modelClassPK);

		objectOutput.writeLong(modelResourcePrimKey);

		objectOutput.writeInt(changeType);

		objectOutput.writeInt(status);

		objectOutput.writeLong(originalCollectionId);

		objectOutput.writeBoolean(collision);
	}

	public long ctEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long modelClassNameId;
	public long modelClassPK;
	public long modelResourcePrimKey;
	public int changeType;
	public int status;
	public long originalCollectionId;
	public boolean collision;

}