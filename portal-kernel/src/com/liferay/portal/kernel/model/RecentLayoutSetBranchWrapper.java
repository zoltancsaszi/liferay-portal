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

import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RecentLayoutSetBranch}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecentLayoutSetBranch
 * @generated
 */
@ProviderType
public class RecentLayoutSetBranchWrapper extends BaseModelWrapper<RecentLayoutSetBranch>
	implements RecentLayoutSetBranch, ModelWrapper<RecentLayoutSetBranch> {
	public RecentLayoutSetBranchWrapper(
		RecentLayoutSetBranch recentLayoutSetBranch) {
		super(recentLayoutSetBranch);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("recentLayoutSetBranchId", getRecentLayoutSetBranchId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("layoutSetBranchId", getLayoutSetBranchId());
		attributes.put("layoutSetId", getLayoutSetId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long recentLayoutSetBranchId = (Long)attributes.get(
				"recentLayoutSetBranchId");

		if (recentLayoutSetBranchId != null) {
			setRecentLayoutSetBranchId(recentLayoutSetBranchId);
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

		Long layoutSetBranchId = (Long)attributes.get("layoutSetBranchId");

		if (layoutSetBranchId != null) {
			setLayoutSetBranchId(layoutSetBranchId);
		}

		Long layoutSetId = (Long)attributes.get("layoutSetId");

		if (layoutSetId != null) {
			setLayoutSetId(layoutSetId);
		}
	}

	/**
	* Returns the company ID of this recent layout set branch.
	*
	* @return the company ID of this recent layout set branch
	*/
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	* Returns the group ID of this recent layout set branch.
	*
	* @return the group ID of this recent layout set branch
	*/
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	* Returns the layout set branch ID of this recent layout set branch.
	*
	* @return the layout set branch ID of this recent layout set branch
	*/
	@Override
	public long getLayoutSetBranchId() {
		return model.getLayoutSetBranchId();
	}

	/**
	* Returns the layout set ID of this recent layout set branch.
	*
	* @return the layout set ID of this recent layout set branch
	*/
	@Override
	public long getLayoutSetId() {
		return model.getLayoutSetId();
	}

	/**
	* Returns the mvcc version of this recent layout set branch.
	*
	* @return the mvcc version of this recent layout set branch
	*/
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	* Returns the primary key of this recent layout set branch.
	*
	* @return the primary key of this recent layout set branch
	*/
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	* Returns the recent layout set branch ID of this recent layout set branch.
	*
	* @return the recent layout set branch ID of this recent layout set branch
	*/
	@Override
	public long getRecentLayoutSetBranchId() {
		return model.getRecentLayoutSetBranchId();
	}

	/**
	* Returns the user ID of this recent layout set branch.
	*
	* @return the user ID of this recent layout set branch
	*/
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	* Returns the user uuid of this recent layout set branch.
	*
	* @return the user uuid of this recent layout set branch
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
	* Sets the company ID of this recent layout set branch.
	*
	* @param companyId the company ID of this recent layout set branch
	*/
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	* Sets the group ID of this recent layout set branch.
	*
	* @param groupId the group ID of this recent layout set branch
	*/
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	* Sets the layout set branch ID of this recent layout set branch.
	*
	* @param layoutSetBranchId the layout set branch ID of this recent layout set branch
	*/
	@Override
	public void setLayoutSetBranchId(long layoutSetBranchId) {
		model.setLayoutSetBranchId(layoutSetBranchId);
	}

	/**
	* Sets the layout set ID of this recent layout set branch.
	*
	* @param layoutSetId the layout set ID of this recent layout set branch
	*/
	@Override
	public void setLayoutSetId(long layoutSetId) {
		model.setLayoutSetId(layoutSetId);
	}

	/**
	* Sets the mvcc version of this recent layout set branch.
	*
	* @param mvccVersion the mvcc version of this recent layout set branch
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	* Sets the primary key of this recent layout set branch.
	*
	* @param primaryKey the primary key of this recent layout set branch
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	* Sets the recent layout set branch ID of this recent layout set branch.
	*
	* @param recentLayoutSetBranchId the recent layout set branch ID of this recent layout set branch
	*/
	@Override
	public void setRecentLayoutSetBranchId(long recentLayoutSetBranchId) {
		model.setRecentLayoutSetBranchId(recentLayoutSetBranchId);
	}

	/**
	* Sets the user ID of this recent layout set branch.
	*
	* @param userId the user ID of this recent layout set branch
	*/
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	* Sets the user uuid of this recent layout set branch.
	*
	* @param userUuid the user uuid of this recent layout set branch
	*/
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected RecentLayoutSetBranchWrapper wrap(
		RecentLayoutSetBranch recentLayoutSetBranch) {
		return new RecentLayoutSetBranchWrapper(recentLayoutSetBranch);
	}
}