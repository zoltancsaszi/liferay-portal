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

package com.liferay.hello.staging.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BookingLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see BookingLocalService
 * @generated
 */
@ProviderType
public class BookingLocalServiceWrapper implements BookingLocalService,
	ServiceWrapper<BookingLocalService> {
	public BookingLocalServiceWrapper(BookingLocalService bookingLocalService) {
		_bookingLocalService = bookingLocalService;
	}

	/**
	* Adds the booking to the database. Also notifies the appropriate model listeners.
	*
	* @param booking the booking
	* @return the booking that was added
	*/
	@Override
	public com.liferay.hello.staging.model.Booking addBooking(
		com.liferay.hello.staging.model.Booking booking) {
		return _bookingLocalService.addBooking(booking);
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link BookingLocalServiceUtil} to access the booking local service.
	*/
	@Override
	public com.liferay.hello.staging.model.Booking addBooking(long userId,
		long groupId, java.lang.String bookingNumber,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookingLocalService.addBooking(userId, groupId, bookingNumber,
			serviceContext);
	}

	/**
	* Creates a new booking with the primary key. Does not add the booking to the database.
	*
	* @param bookingId the primary key for the new booking
	* @return the new booking
	*/
	@Override
	public com.liferay.hello.staging.model.Booking createBooking(long bookingId) {
		return _bookingLocalService.createBooking(bookingId);
	}

	/**
	* Deletes the booking from the database. Also notifies the appropriate model listeners.
	*
	* @param booking the booking
	* @return the booking that was removed
	*/
	@Override
	public com.liferay.hello.staging.model.Booking deleteBooking(
		com.liferay.hello.staging.model.Booking booking) {
		return _bookingLocalService.deleteBooking(booking);
	}

	/**
	* Deletes the booking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bookingId the primary key of the booking
	* @return the booking that was removed
	* @throws PortalException if a booking with the primary key could not be found
	*/
	@Override
	public com.liferay.hello.staging.model.Booking deleteBooking(long bookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookingLocalService.deleteBooking(bookingId);
	}

	@Override
	public void deleteBookings(long groupId) {
		_bookingLocalService.deleteBookings(groupId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookingLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _bookingLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _bookingLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.hello.staging.model.impl.BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _bookingLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.hello.staging.model.impl.BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _bookingLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _bookingLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _bookingLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.hello.staging.model.Booking fetchBooking(long bookingId) {
		return _bookingLocalService.fetchBooking(bookingId);
	}

	/**
	* Returns the booking matching the UUID and group.
	*
	* @param uuid the booking's UUID
	* @param groupId the primary key of the group
	* @return the matching booking, or <code>null</code> if a matching booking could not be found
	*/
	@Override
	public com.liferay.hello.staging.model.Booking fetchBookingByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _bookingLocalService.fetchBookingByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.hello.staging.model.Booking findByGroupIdAndBookingId(
		long groupId, long bookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookingLocalService.findByGroupIdAndBookingId(groupId, bookingId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _bookingLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the booking with the primary key.
	*
	* @param bookingId the primary key of the booking
	* @return the booking
	* @throws PortalException if a booking with the primary key could not be found
	*/
	@Override
	public com.liferay.hello.staging.model.Booking getBooking(long bookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookingLocalService.getBooking(bookingId);
	}

	/**
	* Returns the booking matching the UUID and group.
	*
	* @param uuid the booking's UUID
	* @param groupId the primary key of the group
	* @return the matching booking
	* @throws PortalException if a matching booking could not be found
	*/
	@Override
	public com.liferay.hello.staging.model.Booking getBookingByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookingLocalService.getBookingByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.hello.staging.model.impl.BookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @return the range of bookings
	*/
	@Override
	public java.util.List<com.liferay.hello.staging.model.Booking> getBookings(
		int start, int end) {
		return _bookingLocalService.getBookings(start, end);
	}

	@Override
	public java.util.List<com.liferay.hello.staging.model.Booking> getBookings(
		long groupId, int start, int end) {
		return _bookingLocalService.getBookings(groupId, start, end);
	}

	/**
	* Returns all the bookings matching the UUID and company.
	*
	* @param uuid the UUID of the bookings
	* @param companyId the primary key of the company
	* @return the matching bookings, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.hello.staging.model.Booking> getBookingsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _bookingLocalService.getBookingsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of bookings matching the UUID and company.
	*
	* @param uuid the UUID of the bookings
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of bookings
	* @param end the upper bound of the range of bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching bookings, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.hello.staging.model.Booking> getBookingsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.hello.staging.model.Booking> orderByComparator) {
		return _bookingLocalService.getBookingsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of bookings.
	*
	* @return the number of bookings
	*/
	@Override
	public int getBookingsCount() {
		return _bookingLocalService.getBookingsCount();
	}

	@Override
	public int getBookingsCount(long groupId) {
		return _bookingLocalService.getBookingsCount(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _bookingLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _bookingLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _bookingLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the booking in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param booking the booking
	* @return the booking that was updated
	*/
	@Override
	public com.liferay.hello.staging.model.Booking updateBooking(
		com.liferay.hello.staging.model.Booking booking) {
		return _bookingLocalService.updateBooking(booking);
	}

	@Override
	public com.liferay.hello.staging.model.Booking updateBooking(long userId,
		long bookingId, java.lang.String bookingNumber,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookingLocalService.updateBooking(userId, bookingId,
			bookingNumber, serviceContext);
	}

	@Override
	public BookingLocalService getWrappedService() {
		return _bookingLocalService;
	}

	@Override
	public void setWrappedService(BookingLocalService bookingLocalService) {
		_bookingLocalService = bookingLocalService;
	}

	private BookingLocalService _bookingLocalService;
}