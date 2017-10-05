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

package com.liferay.hello.staging.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.hello.staging.model.Room;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Room in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Room
 * @generated
 */
@ProviderType
public class RoomCacheModel implements CacheModel<Room>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RoomCacheModel)) {
			return false;
		}

		RoomCacheModel roomCacheModel = (RoomCacheModel)obj;

		if (roomId == roomCacheModel.roomId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, roomId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", roomId=");
		sb.append(roomId);
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
		sb.append(", bookingId=");
		sb.append(bookingId);
		sb.append(", roomNumber=");
		sb.append(roomNumber);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Room toEntityModel() {
		RoomImpl roomImpl = new RoomImpl();

		if (uuid == null) {
			roomImpl.setUuid(StringPool.BLANK);
		}
		else {
			roomImpl.setUuid(uuid);
		}

		roomImpl.setRoomId(roomId);
		roomImpl.setGroupId(groupId);
		roomImpl.setCompanyId(companyId);
		roomImpl.setUserId(userId);

		if (userName == null) {
			roomImpl.setUserName(StringPool.BLANK);
		}
		else {
			roomImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			roomImpl.setCreateDate(null);
		}
		else {
			roomImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			roomImpl.setModifiedDate(null);
		}
		else {
			roomImpl.setModifiedDate(new Date(modifiedDate));
		}

		roomImpl.setBookingId(bookingId);

		if (roomNumber == null) {
			roomImpl.setRoomNumber(StringPool.BLANK);
		}
		else {
			roomImpl.setRoomNumber(roomNumber);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			roomImpl.setLastPublishDate(null);
		}
		else {
			roomImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		roomImpl.resetOriginalValues();

		return roomImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		roomId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		bookingId = objectInput.readLong();
		roomNumber = objectInput.readUTF();
		lastPublishDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(roomId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(bookingId);

		if (roomNumber == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(roomNumber);
		}

		objectOutput.writeLong(lastPublishDate);
	}

	public String uuid;
	public long roomId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long bookingId;
	public String roomNumber;
	public long lastPublishDate;
}