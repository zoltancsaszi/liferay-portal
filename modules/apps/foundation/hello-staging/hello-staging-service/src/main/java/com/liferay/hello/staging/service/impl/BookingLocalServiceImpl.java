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

package com.liferay.hello.staging.service.impl;

import com.liferay.hello.staging.exception.EmptyBookingNumberException;
import com.liferay.hello.staging.model.Booking;
import com.liferay.hello.staging.service.base.BookingLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the booking local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link BookingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BookingLocalServiceBaseImpl
 * @see BookingLocalServiceUtil
 */
public class BookingLocalServiceImpl extends BookingLocalServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link BookingLocalServiceUtil} to access the booking local service.
	 */

	@Override
	public Booking addBooking(
			long userId, long groupId, String bookingNumber,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(bookingNumber);

		long bookingId = counterLocalService.increment();

		Booking booking = bookingPersistence.create(bookingId);

		booking.setGroupId(groupId);
		booking.setCompanyId(user.getCompanyId());
		booking.setUserId(userId);
		booking.setUserName(user.getFullName());
		booking.setCreateDate(serviceContext.getCreateDate(now));
		booking.setModifiedDate(serviceContext.getModifiedDate(now));
		booking.setBookingNumber(bookingNumber);

		bookingPersistence.update(booking);

		return booking;
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public Booking deleteBooking(Booking booking) {
		roomLocalService.deleteRooms(
			booking.getGroupId(), booking.getBookingId());

		return bookingPersistence.remove(booking);
	}

	@Override
	public Booking deleteBooking(long bookingId) {
		Booking booking = bookingPersistence.fetchByPrimaryKey(bookingId);

		return bookingLocalService.deleteBooking(booking);
	}

	@Override
	public void deleteBookings(long groupId) {
		List<Booking> bookings = bookingPersistence.findByGroupId(groupId);

		for (Booking booking : bookings) {
			bookingLocalService.deleteBooking(booking);
		}
	}

	public Booking findByGroupIdAndBookingId(long groupId, long bookingId)
		throws PortalException {

		return bookingPersistence.findByGroupIdAndBookingId(groupId, bookingId);
	}

	@Override
	public List<Booking> getBookings(long groupId, int start, int end) {
		return bookingPersistence.findByGroupId(groupId, start, end);
	}

	@Override
	public int getBookingsCount(long groupId) {
		return bookingPersistence.countByGroupId(groupId);
	}

	@Override
	public Booking updateBooking(
			long userId, long bookingId, String bookingNumber,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		validate(bookingNumber);

		Booking booking = bookingPersistence.findByPrimaryKey(bookingId);

		booking.setUserId(userId);
		booking.setUserName(user.getFullName());
		booking.setModifiedDate(serviceContext.getModifiedDate(new Date()));
		booking.setBookingNumber(bookingNumber);

		return bookingPersistence.update(booking);
	}

	protected void validate(String bookingNumber) throws PortalException {
		if (Validator.isNull(bookingNumber)) {
			throw new EmptyBookingNumberException();
		}
	}

}