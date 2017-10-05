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

package com.liferay.hello.staging.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.hello.staging.exception.NoSuchBookingException;
import com.liferay.hello.staging.model.Booking;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the booking service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.hello.staging.service.persistence.impl.BookingPersistenceImpl
 * @see BookingUtil
 * @generated
 */
@ProviderType
public interface BookingPersistence extends BasePersistence<Booking> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BookingUtil} to access the booking persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the bookings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching bookings
	*/
	public java.util.List<Booking> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the bookings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @return the range of matching bookings
	*/
	public java.util.List<Booking> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the bookings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching bookings
	*/
	public java.util.List<Booking> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator);

	/**
	* Returns an ordered range of all the bookings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching bookings
	*/
	public java.util.List<Booking> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public Booking findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Returns the first booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public Booking fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator);

	/**
	* Returns the last booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public Booking findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Returns the last booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public Booking fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator);

	/**
	* Returns the bookings before and after the current booking in the ordered set where uuid = &#63;.
	*
	* @param bookingId the primary key of the current booking
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next booking
	* @throws NoSuchBookingException if a booking with the primary key could not be found
	*/
	public Booking[] findByUuid_PrevAndNext(long bookingId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Removes all the bookings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of bookings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching bookings
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the booking where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBookingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public Booking findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchBookingException;

	/**
	* Returns the booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public Booking fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public Booking fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the booking where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the booking that was removed
	*/
	public Booking removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchBookingException;

	/**
	* Returns the number of bookings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching bookings
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the bookings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching bookings
	*/
	public java.util.List<Booking> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the bookings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @return the range of matching bookings
	*/
	public java.util.List<Booking> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the bookings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching bookings
	*/
	public java.util.List<Booking> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator);

	/**
	* Returns an ordered range of all the bookings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching bookings
	*/
	public java.util.List<Booking> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public Booking findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Returns the first booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public Booking fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator);

	/**
	* Returns the last booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public Booking findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Returns the last booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public Booking fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator);

	/**
	* Returns the bookings before and after the current booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param bookingId the primary key of the current booking
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next booking
	* @throws NoSuchBookingException if a booking with the primary key could not be found
	*/
	public Booking[] findByUuid_C_PrevAndNext(long bookingId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Removes all the bookings where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of bookings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching bookings
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the bookings where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching bookings
	*/
	public java.util.List<Booking> findByGroupId(long groupId);

	/**
	* Returns a range of all the bookings where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @return the range of matching bookings
	*/
	public java.util.List<Booking> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the bookings where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching bookings
	*/
	public java.util.List<Booking> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator);

	/**
	* Returns an ordered range of all the bookings where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching bookings
	*/
	public java.util.List<Booking> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first booking in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public Booking findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Returns the first booking in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public Booking fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator);

	/**
	* Returns the last booking in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public Booking findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Returns the last booking in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public Booking fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator);

	/**
	* Returns the bookings before and after the current booking in the ordered set where groupId = &#63;.
	*
	* @param bookingId the primary key of the current booking
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next booking
	* @throws NoSuchBookingException if a booking with the primary key could not be found
	*/
	public Booking[] findByGroupId_PrevAndNext(long bookingId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator)
		throws NoSuchBookingException;

	/**
	* Removes all the bookings where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of bookings where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching bookings
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the booking where groupId = &#63; and bookingId = &#63; or throws a {@link NoSuchBookingException} if it could not be found.
	*
	* @param groupId the group ID
	* @param bookingId the booking ID
	* @return the matching booking
	* @throws NoSuchBookingException if a matching booking could not be found
	*/
	public Booking findByGroupIdAndBookingId(long groupId, long bookingId)
		throws NoSuchBookingException;

	/**
	* Returns the booking where groupId = &#63; and bookingId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param bookingId the booking ID
	* @return the matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public Booking fetchByGroupIdAndBookingId(long groupId, long bookingId);

	/**
	* Returns the booking where groupId = &#63; and bookingId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param bookingId the booking ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching booking, or <code>null</code> if a matching booking could not be found
	*/
	public Booking fetchByGroupIdAndBookingId(long groupId, long bookingId,
		boolean retrieveFromCache);

	/**
	* Removes the booking where groupId = &#63; and bookingId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param bookingId the booking ID
	* @return the booking that was removed
	*/
	public Booking removeByGroupIdAndBookingId(long groupId, long bookingId)
		throws NoSuchBookingException;

	/**
	* Returns the number of bookings where groupId = &#63; and bookingId = &#63;.
	*
	* @param groupId the group ID
	* @param bookingId the booking ID
	* @return the number of matching bookings
	*/
	public int countByGroupIdAndBookingId(long groupId, long bookingId);

	/**
	* Caches the booking in the entity cache if it is enabled.
	*
	* @param booking the booking
	*/
	public void cacheResult(Booking booking);

	/**
	* Caches the bookings in the entity cache if it is enabled.
	*
	* @param bookings the bookings
	*/
	public void cacheResult(java.util.List<Booking> bookings);

	/**
	* Creates a new booking with the primary key. Does not add the booking to the database.
	*
	* @param bookingId the primary key for the new booking
	* @return the new booking
	*/
	public Booking create(long bookingId);

	/**
	* Removes the booking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bookingId the primary key of the booking
	* @return the booking that was removed
	* @throws NoSuchBookingException if a booking with the primary key could not be found
	*/
	public Booking remove(long bookingId) throws NoSuchBookingException;

	public Booking updateImpl(Booking booking);

	/**
	* Returns the booking with the primary key or throws a {@link NoSuchBookingException} if it could not be found.
	*
	* @param bookingId the primary key of the booking
	* @return the booking
	* @throws NoSuchBookingException if a booking with the primary key could not be found
	*/
	public Booking findByPrimaryKey(long bookingId)
		throws NoSuchBookingException;

	/**
	* Returns the booking with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param bookingId the primary key of the booking
	* @return the booking, or <code>null</code> if a booking with the primary key could not be found
	*/
	public Booking fetchByPrimaryKey(long bookingId);

	@Override
	public java.util.Map<java.io.Serializable, Booking> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the bookings.
	*
	* @return the bookings
	*/
	public java.util.List<Booking> findAll();

	/**
	* Returns a range of all the bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @return the range of bookings
	*/
	public java.util.List<Booking> findAll(int start, int end);

	/**
	* Returns an ordered range of all the bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of bookings
	*/
	public java.util.List<Booking> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator);

	/**
	* Returns an ordered range of all the bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of bookings
	*/
	public java.util.List<Booking> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Booking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the bookings from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of bookings.
	*
	* @return the number of bookings
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}