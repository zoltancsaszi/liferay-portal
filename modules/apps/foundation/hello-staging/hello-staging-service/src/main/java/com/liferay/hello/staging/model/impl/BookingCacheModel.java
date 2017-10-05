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

import com.liferay.hello.staging.model.Booking;

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
 * The cache model class for representing Booking in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Booking
 * @generated
 */
@ProviderType
public class BookingCacheModel implements CacheModel<Booking>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BookingCacheModel)) {
			return false;
		}

		BookingCacheModel bookingCacheModel = (BookingCacheModel)obj;

		if (bookingId == bookingCacheModel.bookingId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, bookingId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", bookingId=");
		sb.append(bookingId);
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
		sb.append(", bookingNumber=");
		sb.append(bookingNumber);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Booking toEntityModel() {
		BookingImpl bookingImpl = new BookingImpl();

		if (uuid == null) {
			bookingImpl.setUuid(StringPool.BLANK);
		}
		else {
			bookingImpl.setUuid(uuid);
		}

		bookingImpl.setBookingId(bookingId);
		bookingImpl.setGroupId(groupId);
		bookingImpl.setCompanyId(companyId);
		bookingImpl.setUserId(userId);

		if (userName == null) {
			bookingImpl.setUserName(StringPool.BLANK);
		}
		else {
			bookingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			bookingImpl.setCreateDate(null);
		}
		else {
			bookingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			bookingImpl.setModifiedDate(null);
		}
		else {
			bookingImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (bookingNumber == null) {
			bookingImpl.setBookingNumber(StringPool.BLANK);
		}
		else {
			bookingImpl.setBookingNumber(bookingNumber);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			bookingImpl.setLastPublishDate(null);
		}
		else {
			bookingImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		bookingImpl.resetOriginalValues();

		return bookingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		bookingId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		bookingNumber = objectInput.readUTF();
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

		objectOutput.writeLong(bookingId);

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

		if (bookingNumber == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(bookingNumber);
		}

		objectOutput.writeLong(lastPublishDate);
	}

	public String uuid;
	public long bookingId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String bookingNumber;
	public long lastPublishDate;
}