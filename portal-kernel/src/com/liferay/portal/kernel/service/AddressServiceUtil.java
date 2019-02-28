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

package com.liferay.portal.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for Address. This utility wraps
 * <code>com.liferay.portal.service.impl.AddressServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AddressService
 * @generated
 */
@ProviderType
public class AddressServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.service.impl.AddressServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.model.Address addAddress(
		String className, long classPK, String street1, String street2,
		String street3, String city, String zip, long regionId, long countryId,
		long typeId, boolean mailing, boolean primary,
		ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addAddress(className, classPK, street1, street2, street3,
			city, zip, regionId, countryId, typeId, mailing, primary,
			serviceContext);
	}

	public static void deleteAddress(long addressId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteAddress(addressId);
	}

	public static com.liferay.portal.kernel.model.Address getAddress(
		long addressId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAddress(addressId);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Address> getAddresses(
		String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAddresses(className, classPK);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.Address updateAddress(
		long addressId, String street1, String street2, String street3,
		String city, String zip, long regionId, long countryId, long typeId,
		boolean mailing, boolean primary)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateAddress(addressId, street1, street2, street3, city,
			zip, regionId, countryId, typeId, mailing, primary);
	}

	public static AddressService getService() {
		if (_service == null) {
			_service = (AddressService)PortalBeanLocatorUtil.locate(AddressService.class.getName());

			ReferenceRegistry.registerReference(AddressServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static AddressService _service;
}