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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CTProcess}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CTProcess
 * @generated
 */
@ProviderType
public class CTProcessWrapper
	extends BaseModelWrapper<CTProcess>
	implements CTProcess, ModelWrapper<CTProcess> {

	public CTProcessWrapper(CTProcess ctProcess) {
		super(ctProcess);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ctProcessId", getCtProcessId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("ctCollectionId", getCtCollectionId());
		attributes.put("backgroundTaskId", getBackgroundTaskId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ctProcessId = (Long)attributes.get("ctProcessId");

		if (ctProcessId != null) {
			setCtProcessId(ctProcessId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long ctCollectionId = (Long)attributes.get("ctCollectionId");

		if (ctCollectionId != null) {
			setCtCollectionId(ctCollectionId);
		}

		Long backgroundTaskId = (Long)attributes.get("backgroundTaskId");

		if (backgroundTaskId != null) {
			setBackgroundTaskId(backgroundTaskId);
		}
	}

	/**
	 * Returns the background task ID of this ct process.
	 *
	 * @return the background task ID of this ct process
	 */
	@Override
	public long getBackgroundTaskId() {
		return model.getBackgroundTaskId();
	}

	/**
	 * Returns the company ID of this ct process.
	 *
	 * @return the company ID of this ct process
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this ct process.
	 *
	 * @return the create date of this ct process
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the ct collection ID of this ct process.
	 *
	 * @return the ct collection ID of this ct process
	 */
	@Override
	public long getCtCollectionId() {
		return model.getCtCollectionId();
	}

	/**
	 * Returns the ct process ID of this ct process.
	 *
	 * @return the ct process ID of this ct process
	 */
	@Override
	public long getCtProcessId() {
		return model.getCtProcessId();
	}

	/**
	 * Returns the primary key of this ct process.
	 *
	 * @return the primary key of this ct process
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the user ID of this ct process.
	 *
	 * @return the user ID of this ct process
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this ct process.
	 *
	 * @return the user uuid of this ct process
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the background task ID of this ct process.
	 *
	 * @param backgroundTaskId the background task ID of this ct process
	 */
	@Override
	public void setBackgroundTaskId(long backgroundTaskId) {
		model.setBackgroundTaskId(backgroundTaskId);
	}

	/**
	 * Sets the company ID of this ct process.
	 *
	 * @param companyId the company ID of this ct process
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this ct process.
	 *
	 * @param createDate the create date of this ct process
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the ct collection ID of this ct process.
	 *
	 * @param ctCollectionId the ct collection ID of this ct process
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId) {
		model.setCtCollectionId(ctCollectionId);
	}

	/**
	 * Sets the ct process ID of this ct process.
	 *
	 * @param ctProcessId the ct process ID of this ct process
	 */
	@Override
	public void setCtProcessId(long ctProcessId) {
		model.setCtProcessId(ctProcessId);
	}

	/**
	 * Sets the primary key of this ct process.
	 *
	 * @param primaryKey the primary key of this ct process
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this ct process.
	 *
	 * @param userId the user ID of this ct process
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this ct process.
	 *
	 * @param userUuid the user uuid of this ct process
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected CTProcessWrapper wrap(CTProcess ctProcess) {
		return new CTProcessWrapper(ctProcess);
	}

}