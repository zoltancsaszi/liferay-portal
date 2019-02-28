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

package com.liferay.portal.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.RepositoryServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>RepositoryServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.portal.kernel.model.RepositorySoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.portal.kernel.model.Repository</code>, that is translated to a
 * <code>com.liferay.portal.kernel.model.RepositorySoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RepositoryServiceHttp
 * @generated
 */
@ProviderType
public class RepositoryServiceSoap {
	public static void checkRepository(long repositoryId)
		throws RemoteException {
		try {
			RepositoryServiceUtil.checkRepository(repositoryId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteRepository(long repositoryId)
		throws RemoteException {
		try {
			RepositoryServiceUtil.deleteRepository(repositoryId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.RepositorySoap getRepository(
		long repositoryId) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Repository returnValue = RepositoryServiceUtil.getRepository(repositoryId);

			return com.liferay.portal.kernel.model.RepositorySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* @deprecated As of Wilberforce (7.0.x), with no direct replacement
	*/
	@Deprecated
	public static String[] getSupportedConfigurations(long classNameId)
		throws RemoteException {
		try {
			String[] returnValue = RepositoryServiceUtil.getSupportedConfigurations(classNameId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* @deprecated As of Wilberforce (7.0.x), with no direct replacement
	*/
	@Deprecated
	public static String[] getSupportedParameters(long classNameId,
		String configuration) throws RemoteException {
		try {
			String[] returnValue = RepositoryServiceUtil.getSupportedParameters(classNameId,
					configuration);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* @deprecated As of Wilberforce (7.0.x), with no direct replacement
	*/
	@Deprecated
	public static String[] getSupportedParameters(String className,
		String configuration) throws RemoteException {
		try {
			String[] returnValue = RepositoryServiceUtil.getSupportedParameters(className,
					configuration);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.util.UnicodeProperties getTypeSettingsProperties(
		long repositoryId) throws RemoteException {
		try {
			com.liferay.portal.kernel.util.UnicodeProperties returnValue = RepositoryServiceUtil.getTypeSettingsProperties(repositoryId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void updateRepository(long repositoryId, String name,
		String description) throws RemoteException {
		try {
			RepositoryServiceUtil.updateRepository(repositoryId, name,
				description);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(RepositoryServiceSoap.class);
}