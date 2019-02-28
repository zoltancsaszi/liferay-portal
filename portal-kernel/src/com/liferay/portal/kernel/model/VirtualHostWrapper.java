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
 * This class is a wrapper for {@link VirtualHost}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VirtualHost
 * @generated
 */
@ProviderType
public class VirtualHostWrapper extends BaseModelWrapper<VirtualHost>
	implements VirtualHost, ModelWrapper<VirtualHost> {
	public VirtualHostWrapper(VirtualHost virtualHost) {
		super(virtualHost);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("virtualHostId", getVirtualHostId());
		attributes.put("companyId", getCompanyId());
		attributes.put("layoutSetId", getLayoutSetId());
		attributes.put("hostname", getHostname());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long virtualHostId = (Long)attributes.get("virtualHostId");

		if (virtualHostId != null) {
			setVirtualHostId(virtualHostId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long layoutSetId = (Long)attributes.get("layoutSetId");

		if (layoutSetId != null) {
			setLayoutSetId(layoutSetId);
		}

		String hostname = (String)attributes.get("hostname");

		if (hostname != null) {
			setHostname(hostname);
		}
	}

	/**
	* Returns the company ID of this virtual host.
	*
	* @return the company ID of this virtual host
	*/
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	* Returns the hostname of this virtual host.
	*
	* @return the hostname of this virtual host
	*/
	@Override
	public String getHostname() {
		return model.getHostname();
	}

	/**
	* Returns the layout set ID of this virtual host.
	*
	* @return the layout set ID of this virtual host
	*/
	@Override
	public long getLayoutSetId() {
		return model.getLayoutSetId();
	}

	/**
	* Returns the mvcc version of this virtual host.
	*
	* @return the mvcc version of this virtual host
	*/
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	* Returns the primary key of this virtual host.
	*
	* @return the primary key of this virtual host
	*/
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	* Returns the virtual host ID of this virtual host.
	*
	* @return the virtual host ID of this virtual host
	*/
	@Override
	public long getVirtualHostId() {
		return model.getVirtualHostId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	* Sets the company ID of this virtual host.
	*
	* @param companyId the company ID of this virtual host
	*/
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	* Sets the hostname of this virtual host.
	*
	* @param hostname the hostname of this virtual host
	*/
	@Override
	public void setHostname(String hostname) {
		model.setHostname(hostname);
	}

	/**
	* Sets the layout set ID of this virtual host.
	*
	* @param layoutSetId the layout set ID of this virtual host
	*/
	@Override
	public void setLayoutSetId(long layoutSetId) {
		model.setLayoutSetId(layoutSetId);
	}

	/**
	* Sets the mvcc version of this virtual host.
	*
	* @param mvccVersion the mvcc version of this virtual host
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	* Sets the primary key of this virtual host.
	*
	* @param primaryKey the primary key of this virtual host
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	* Sets the virtual host ID of this virtual host.
	*
	* @param virtualHostId the virtual host ID of this virtual host
	*/
	@Override
	public void setVirtualHostId(long virtualHostId) {
		model.setVirtualHostId(virtualHostId);
	}

	@Override
	protected VirtualHostWrapper wrap(VirtualHost virtualHost) {
		return new VirtualHostWrapper(virtualHost);
	}
}