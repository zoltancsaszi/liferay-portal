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
 * This class is a wrapper for {@link RecentLayoutRevision}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecentLayoutRevision
 * @generated
 */
@ProviderType
public class RecentLayoutRevisionWrapper extends BaseModelWrapper<RecentLayoutRevision>
	implements RecentLayoutRevision, ModelWrapper<RecentLayoutRevision> {
	public RecentLayoutRevisionWrapper(
		RecentLayoutRevision recentLayoutRevision) {
		super(recentLayoutRevision);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("recentLayoutRevisionId", getRecentLayoutRevisionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("layoutRevisionId", getLayoutRevisionId());
		attributes.put("layoutSetBranchId", getLayoutSetBranchId());
		attributes.put("plid", getPlid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long recentLayoutRevisionId = (Long)attributes.get(
				"recentLayoutRevisionId");

		if (recentLayoutRevisionId != null) {
			setRecentLayoutRevisionId(recentLayoutRevisionId);
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

		Long layoutRevisionId = (Long)attributes.get("layoutRevisionId");

		if (layoutRevisionId != null) {
			setLayoutRevisionId(layoutRevisionId);
		}

		Long layoutSetBranchId = (Long)attributes.get("layoutSetBranchId");

		if (layoutSetBranchId != null) {
			setLayoutSetBranchId(layoutSetBranchId);
		}

		Long plid = (Long)attributes.get("plid");

		if (plid != null) {
			setPlid(plid);
		}
	}

	/**
	* Returns the company ID of this recent layout revision.
	*
	* @return the company ID of this recent layout revision
	*/
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	* Returns the group ID of this recent layout revision.
	*
	* @return the group ID of this recent layout revision
	*/
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	* Returns the layout revision ID of this recent layout revision.
	*
	* @return the layout revision ID of this recent layout revision
	*/
	@Override
	public long getLayoutRevisionId() {
		return model.getLayoutRevisionId();
	}

	/**
	* Returns the layout set branch ID of this recent layout revision.
	*
	* @return the layout set branch ID of this recent layout revision
	*/
	@Override
	public long getLayoutSetBranchId() {
		return model.getLayoutSetBranchId();
	}

	/**
	* Returns the mvcc version of this recent layout revision.
	*
	* @return the mvcc version of this recent layout revision
	*/
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	* Returns the plid of this recent layout revision.
	*
	* @return the plid of this recent layout revision
	*/
	@Override
	public long getPlid() {
		return model.getPlid();
	}

	/**
	* Returns the primary key of this recent layout revision.
	*
	* @return the primary key of this recent layout revision
	*/
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	* Returns the recent layout revision ID of this recent layout revision.
	*
	* @return the recent layout revision ID of this recent layout revision
	*/
	@Override
	public long getRecentLayoutRevisionId() {
		return model.getRecentLayoutRevisionId();
	}

	/**
	* Returns the user ID of this recent layout revision.
	*
	* @return the user ID of this recent layout revision
	*/
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	* Returns the user uuid of this recent layout revision.
	*
	* @return the user uuid of this recent layout revision
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
	* Sets the company ID of this recent layout revision.
	*
	* @param companyId the company ID of this recent layout revision
	*/
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	* Sets the group ID of this recent layout revision.
	*
	* @param groupId the group ID of this recent layout revision
	*/
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	* Sets the layout revision ID of this recent layout revision.
	*
	* @param layoutRevisionId the layout revision ID of this recent layout revision
	*/
	@Override
	public void setLayoutRevisionId(long layoutRevisionId) {
		model.setLayoutRevisionId(layoutRevisionId);
	}

	/**
	* Sets the layout set branch ID of this recent layout revision.
	*
	* @param layoutSetBranchId the layout set branch ID of this recent layout revision
	*/
	@Override
	public void setLayoutSetBranchId(long layoutSetBranchId) {
		model.setLayoutSetBranchId(layoutSetBranchId);
	}

	/**
	* Sets the mvcc version of this recent layout revision.
	*
	* @param mvccVersion the mvcc version of this recent layout revision
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	* Sets the plid of this recent layout revision.
	*
	* @param plid the plid of this recent layout revision
	*/
	@Override
	public void setPlid(long plid) {
		model.setPlid(plid);
	}

	/**
	* Sets the primary key of this recent layout revision.
	*
	* @param primaryKey the primary key of this recent layout revision
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	* Sets the recent layout revision ID of this recent layout revision.
	*
	* @param recentLayoutRevisionId the recent layout revision ID of this recent layout revision
	*/
	@Override
	public void setRecentLayoutRevisionId(long recentLayoutRevisionId) {
		model.setRecentLayoutRevisionId(recentLayoutRevisionId);
	}

	/**
	* Sets the user ID of this recent layout revision.
	*
	* @param userId the user ID of this recent layout revision
	*/
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	* Sets the user uuid of this recent layout revision.
	*
	* @param userUuid the user uuid of this recent layout revision
	*/
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected RecentLayoutRevisionWrapper wrap(
		RecentLayoutRevision recentLayoutRevision) {
		return new RecentLayoutRevisionWrapper(recentLayoutRevision);
	}
}