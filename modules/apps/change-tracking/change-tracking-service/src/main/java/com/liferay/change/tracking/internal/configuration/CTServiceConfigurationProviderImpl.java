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

package com.liferay.change.tracking.internal.configuration;

import com.liferay.change.tracking.configuration.CTServiceConfiguration;
import com.liferay.change.tracking.configuration.CTServiceConfigurationProvider;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Máté Thurzó
 */
@Component(service = CTServiceConfigurationProvider.class)
public class CTServiceConfigurationProviderImpl
	implements CTServiceConfigurationProvider {

	@Override
	public boolean enableChangeTracking() {
		return getCTServiceConfiguration().enableChangeTracking();
	}

	@Override
	public CTServiceConfiguration getCTServiceConfiguration() {
		try {
			return _configurationProvider.getSystemConfiguration(
				CTServiceConfiguration.class);
		}
		catch (ConfigurationException ce) {
			return new DummyCTServiceConfiguration();
		}
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED)
	private ModuleServiceLifecycle _moduleServiceLifecycle;

	private static class DummyCTServiceConfiguration
		implements CTServiceConfiguration {

		@Override
		public boolean enableChangeTracking() {
			return false;
		}

	}

}