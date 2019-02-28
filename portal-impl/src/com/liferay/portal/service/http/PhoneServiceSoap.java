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
import com.liferay.portal.kernel.service.PhoneServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>PhoneServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.portal.kernel.model.PhoneSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.portal.kernel.model.Phone</code>, that is translated to a
 * <code>com.liferay.portal.kernel.model.PhoneSoap</code>. Methods that SOAP
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
 * @see PhoneServiceHttp
 * @generated
 */
@ProviderType
public class PhoneServiceSoap {
	public static com.liferay.portal.kernel.model.PhoneSoap addPhone(
		String className, long classPK, String number, String extension,
		long typeId, boolean primary,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Phone returnValue = PhoneServiceUtil.addPhone(className,
					classPK, number, extension, typeId, primary, serviceContext);

			return com.liferay.portal.kernel.model.PhoneSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deletePhone(long phoneId) throws RemoteException {
		try {
			PhoneServiceUtil.deletePhone(phoneId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.PhoneSoap getPhone(
		long phoneId) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Phone returnValue = PhoneServiceUtil.getPhone(phoneId);

			return com.liferay.portal.kernel.model.PhoneSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.PhoneSoap[] getPhones(
		String className, long classPK) throws RemoteException {
		try {
			java.util.List<com.liferay.portal.kernel.model.Phone> returnValue = PhoneServiceUtil.getPhones(className,
					classPK);

			return com.liferay.portal.kernel.model.PhoneSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.PhoneSoap updatePhone(
		long phoneId, String number, String extension, long typeId,
		boolean primary) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Phone returnValue = PhoneServiceUtil.updatePhone(phoneId,
					number, extension, typeId, primary);

			return com.liferay.portal.kernel.model.PhoneSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PhoneServiceSoap.class);
}