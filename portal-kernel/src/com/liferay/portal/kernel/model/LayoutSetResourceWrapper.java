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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LayoutSetResource}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetResource
 * @generated
 */
@ProviderType
public class LayoutSetResourceWrapper implements LayoutSetResource,
	ModelWrapper<LayoutSetResource> {
	public LayoutSetResourceWrapper(LayoutSetResource layoutSetResource) {
		_layoutSetResource = layoutSetResource;
	}

	@Override
	public Class<?> getModelClass() {
		return LayoutSetResource.class;
	}

	@Override
	public String getModelClassName() {
		return LayoutSetResource.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("layoutSetResourceId", getLayoutSetResourceId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("pageCount", getPageCount());
		attributes.put("privateLayout", isPrivateLayout());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long layoutSetResourceId = (Long)attributes.get("layoutSetResourceId");

		if (layoutSetResourceId != null) {
			setLayoutSetResourceId(layoutSetResourceId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer pageCount = (Integer)attributes.get("pageCount");

		if (pageCount != null) {
			setPageCount(pageCount);
		}

		Boolean privateLayout = (Boolean)attributes.get("privateLayout");

		if (privateLayout != null) {
			setPrivateLayout(privateLayout);
		}
	}

	@Override
	public Object clone() {
		return new LayoutSetResourceWrapper((LayoutSetResource)_layoutSetResource.clone());
	}

	@Override
	public int compareTo(LayoutSetResource layoutSetResource) {
		return _layoutSetResource.compareTo(layoutSetResource);
	}

	/**
	* Returns the company ID of this layout set resource.
	*
	* @return the company ID of this layout set resource
	*/
	@Override
	public long getCompanyId() {
		return _layoutSetResource.getCompanyId();
	}

	/**
	* Returns the create date of this layout set resource.
	*
	* @return the create date of this layout set resource
	*/
	@Override
	public Date getCreateDate() {
		return _layoutSetResource.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _layoutSetResource.getExpandoBridge();
	}

	/**
	* Returns the group ID of this layout set resource.
	*
	* @return the group ID of this layout set resource
	*/
	@Override
	public long getGroupId() {
		return _layoutSetResource.getGroupId();
	}

	/**
	* Returns the layout set resource ID of this layout set resource.
	*
	* @return the layout set resource ID of this layout set resource
	*/
	@Override
	public long getLayoutSetResourceId() {
		return _layoutSetResource.getLayoutSetResourceId();
	}

	/**
	* Returns the modified date of this layout set resource.
	*
	* @return the modified date of this layout set resource
	*/
	@Override
	public Date getModifiedDate() {
		return _layoutSetResource.getModifiedDate();
	}

	/**
	* Returns the mvcc version of this layout set resource.
	*
	* @return the mvcc version of this layout set resource
	*/
	@Override
	public long getMvccVersion() {
		return _layoutSetResource.getMvccVersion();
	}

	/**
	* Returns the page count of this layout set resource.
	*
	* @return the page count of this layout set resource
	*/
	@Override
	public int getPageCount() {
		return _layoutSetResource.getPageCount();
	}

	/**
	* Returns the primary key of this layout set resource.
	*
	* @return the primary key of this layout set resource
	*/
	@Override
	public long getPrimaryKey() {
		return _layoutSetResource.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _layoutSetResource.getPrimaryKeyObj();
	}

	/**
	* Returns the private layout of this layout set resource.
	*
	* @return the private layout of this layout set resource
	*/
	@Override
	public boolean getPrivateLayout() {
		return _layoutSetResource.getPrivateLayout();
	}

	@Override
	public int hashCode() {
		return _layoutSetResource.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _layoutSetResource.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _layoutSetResource.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _layoutSetResource.isNew();
	}

	/**
	* Returns <code>true</code> if this layout set resource is private layout.
	*
	* @return <code>true</code> if this layout set resource is private layout; <code>false</code> otherwise
	*/
	@Override
	public boolean isPrivateLayout() {
		return _layoutSetResource.isPrivateLayout();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_layoutSetResource.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this layout set resource.
	*
	* @param companyId the company ID of this layout set resource
	*/
	@Override
	public void setCompanyId(long companyId) {
		_layoutSetResource.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this layout set resource.
	*
	* @param createDate the create date of this layout set resource
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_layoutSetResource.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
		_layoutSetResource.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_layoutSetResource.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_layoutSetResource.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this layout set resource.
	*
	* @param groupId the group ID of this layout set resource
	*/
	@Override
	public void setGroupId(long groupId) {
		_layoutSetResource.setGroupId(groupId);
	}

	/**
	* Sets the layout set resource ID of this layout set resource.
	*
	* @param layoutSetResourceId the layout set resource ID of this layout set resource
	*/
	@Override
	public void setLayoutSetResourceId(long layoutSetResourceId) {
		_layoutSetResource.setLayoutSetResourceId(layoutSetResourceId);
	}

	/**
	* Sets the modified date of this layout set resource.
	*
	* @param modifiedDate the modified date of this layout set resource
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_layoutSetResource.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the mvcc version of this layout set resource.
	*
	* @param mvccVersion the mvcc version of this layout set resource
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		_layoutSetResource.setMvccVersion(mvccVersion);
	}

	@Override
	public void setNew(boolean n) {
		_layoutSetResource.setNew(n);
	}

	/**
	* Sets the page count of this layout set resource.
	*
	* @param pageCount the page count of this layout set resource
	*/
	@Override
	public void setPageCount(int pageCount) {
		_layoutSetResource.setPageCount(pageCount);
	}

	/**
	* Sets the primary key of this layout set resource.
	*
	* @param primaryKey the primary key of this layout set resource
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_layoutSetResource.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_layoutSetResource.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this layout set resource is private layout.
	*
	* @param privateLayout the private layout of this layout set resource
	*/
	@Override
	public void setPrivateLayout(boolean privateLayout) {
		_layoutSetResource.setPrivateLayout(privateLayout);
	}

	@Override
	public CacheModel<LayoutSetResource> toCacheModel() {
		return _layoutSetResource.toCacheModel();
	}

	@Override
	public LayoutSetResource toEscapedModel() {
		return new LayoutSetResourceWrapper(_layoutSetResource.toEscapedModel());
	}

	@Override
	public String toString() {
		return _layoutSetResource.toString();
	}

	@Override
	public LayoutSetResource toUnescapedModel() {
		return new LayoutSetResourceWrapper(_layoutSetResource.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _layoutSetResource.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LayoutSetResourceWrapper)) {
			return false;
		}

		LayoutSetResourceWrapper layoutSetResourceWrapper = (LayoutSetResourceWrapper)obj;

		if (Objects.equals(_layoutSetResource,
					layoutSetResourceWrapper._layoutSetResource)) {
			return true;
		}

		return false;
	}

	@Override
	public LayoutSetResource getWrappedModel() {
		return _layoutSetResource;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _layoutSetResource.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _layoutSetResource.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_layoutSetResource.resetOriginalValues();
	}

	private final LayoutSetResource _layoutSetResource;
}