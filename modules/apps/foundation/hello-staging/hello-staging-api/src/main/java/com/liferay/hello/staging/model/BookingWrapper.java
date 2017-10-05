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

package com.liferay.hello.staging.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Booking}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Booking
 * @generated
 */
@ProviderType
public class BookingWrapper implements Booking, ModelWrapper<Booking> {
	public BookingWrapper(Booking booking) {
		_booking = booking;
	}

	@Override
	public Class<?> getModelClass() {
		return Booking.class;
	}

	@Override
	public String getModelClassName() {
		return Booking.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("bookingId", getBookingId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("bookingNumber", getBookingNumber());
		attributes.put("lastPublishDate", getLastPublishDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long bookingId = (Long)attributes.get("bookingId");

		if (bookingId != null) {
			setBookingId(bookingId);
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

		String bookingNumber = (String)attributes.get("bookingNumber");

		if (bookingNumber != null) {
			setBookingNumber(bookingNumber);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new BookingWrapper((Booking)_booking.clone());
	}

	@Override
	public int compareTo(Booking booking) {
		return _booking.compareTo(booking);
	}

	/**
	* Returns the booking ID of this booking.
	*
	* @return the booking ID of this booking
	*/
	@Override
	public long getBookingId() {
		return _booking.getBookingId();
	}

	/**
	* Returns the booking number of this booking.
	*
	* @return the booking number of this booking
	*/
	@Override
	public java.lang.String getBookingNumber() {
		return _booking.getBookingNumber();
	}

	/**
	* Returns the company ID of this booking.
	*
	* @return the company ID of this booking
	*/
	@Override
	public long getCompanyId() {
		return _booking.getCompanyId();
	}

	/**
	* Returns the create date of this booking.
	*
	* @return the create date of this booking
	*/
	@Override
	public Date getCreateDate() {
		return _booking.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _booking.getExpandoBridge();
	}

	/**
	* Returns the group ID of this booking.
	*
	* @return the group ID of this booking
	*/
	@Override
	public long getGroupId() {
		return _booking.getGroupId();
	}

	/**
	* Returns the last publish date of this booking.
	*
	* @return the last publish date of this booking
	*/
	@Override
	public Date getLastPublishDate() {
		return _booking.getLastPublishDate();
	}

	/**
	* Returns the modified date of this booking.
	*
	* @return the modified date of this booking
	*/
	@Override
	public Date getModifiedDate() {
		return _booking.getModifiedDate();
	}

	/**
	* Returns the primary key of this booking.
	*
	* @return the primary key of this booking
	*/
	@Override
	public long getPrimaryKey() {
		return _booking.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _booking.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this booking.
	*
	* @return the user ID of this booking
	*/
	@Override
	public long getUserId() {
		return _booking.getUserId();
	}

	/**
	* Returns the user name of this booking.
	*
	* @return the user name of this booking
	*/
	@Override
	public java.lang.String getUserName() {
		return _booking.getUserName();
	}

	/**
	* Returns the user uuid of this booking.
	*
	* @return the user uuid of this booking
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _booking.getUserUuid();
	}

	/**
	* Returns the uuid of this booking.
	*
	* @return the uuid of this booking
	*/
	@Override
	public java.lang.String getUuid() {
		return _booking.getUuid();
	}

	@Override
	public int hashCode() {
		return _booking.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _booking.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _booking.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _booking.isNew();
	}

	@Override
	public void persist() {
		_booking.persist();
	}

	/**
	* Sets the booking ID of this booking.
	*
	* @param bookingId the booking ID of this booking
	*/
	@Override
	public void setBookingId(long bookingId) {
		_booking.setBookingId(bookingId);
	}

	/**
	* Sets the booking number of this booking.
	*
	* @param bookingNumber the booking number of this booking
	*/
	@Override
	public void setBookingNumber(java.lang.String bookingNumber) {
		_booking.setBookingNumber(bookingNumber);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_booking.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this booking.
	*
	* @param companyId the company ID of this booking
	*/
	@Override
	public void setCompanyId(long companyId) {
		_booking.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this booking.
	*
	* @param createDate the create date of this booking
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_booking.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_booking.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_booking.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_booking.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this booking.
	*
	* @param groupId the group ID of this booking
	*/
	@Override
	public void setGroupId(long groupId) {
		_booking.setGroupId(groupId);
	}

	/**
	* Sets the last publish date of this booking.
	*
	* @param lastPublishDate the last publish date of this booking
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_booking.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this booking.
	*
	* @param modifiedDate the modified date of this booking
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_booking.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_booking.setNew(n);
	}

	/**
	* Sets the primary key of this booking.
	*
	* @param primaryKey the primary key of this booking
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_booking.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_booking.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this booking.
	*
	* @param userId the user ID of this booking
	*/
	@Override
	public void setUserId(long userId) {
		_booking.setUserId(userId);
	}

	/**
	* Sets the user name of this booking.
	*
	* @param userName the user name of this booking
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_booking.setUserName(userName);
	}

	/**
	* Sets the user uuid of this booking.
	*
	* @param userUuid the user uuid of this booking
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_booking.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this booking.
	*
	* @param uuid the uuid of this booking
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_booking.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Booking> toCacheModel() {
		return _booking.toCacheModel();
	}

	@Override
	public Booking toEscapedModel() {
		return new BookingWrapper(_booking.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _booking.toString();
	}

	@Override
	public Booking toUnescapedModel() {
		return new BookingWrapper(_booking.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _booking.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BookingWrapper)) {
			return false;
		}

		BookingWrapper bookingWrapper = (BookingWrapper)obj;

		if (Objects.equals(_booking, bookingWrapper._booking)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _booking.getStagedModelType();
	}

	@Override
	public Booking getWrappedModel() {
		return _booking;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _booking.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _booking.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_booking.resetOriginalValues();
	}

	private final Booking _booking;
}