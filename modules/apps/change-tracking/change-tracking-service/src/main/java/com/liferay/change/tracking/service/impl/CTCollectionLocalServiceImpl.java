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

package com.liferay.change.tracking.service.impl;

import com.liferay.change.tracking.exception.CTCollectionNameException;
import com.liferay.change.tracking.model.CTCollection;
import com.liferay.change.tracking.model.CTEntry;
import com.liferay.change.tracking.model.CTProcess;
import com.liferay.change.tracking.service.base.CTCollectionLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Daniel Kocsis
 */
public class CTCollectionLocalServiceImpl
	extends CTCollectionLocalServiceBaseImpl {

	@Override
	public CTCollection addCTCollection(
			long userId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		_validate(name);

		long ctCollectionId = counterLocalService.increment();

		CTCollection ctCollection = ctCollectionPersistence.create(
			ctCollectionId);

		User user = userLocalService.getUser(userId);

		ctCollection.setCompanyId(user.getCompanyId());
		ctCollection.setUserId(user.getUserId());
		ctCollection.setUserName(user.getFullName());

		Date now = new Date();

		ctCollection.setCreateDate(serviceContext.getCreateDate(now));
		ctCollection.setModifiedDate(serviceContext.getModifiedDate(now));

		ctCollection.setName(name);
		ctCollection.setDescription(description);
		ctCollection.setStatus(WorkflowConstants.STATUS_APPROVED);

		return ctCollectionPersistence.update(ctCollection);
	}

	@Override
	public void deleteCompanyCTCollections(long companyId)
		throws PortalException {

		List<CTCollection> ctCollections =
			ctCollectionPersistence.findByCompanyId(companyId);

		for (CTCollection ctCollection : ctCollections) {
			ctCollectionLocalService.deleteCTCollection(ctCollection);
		}
	}

	@Override
	public CTCollection deleteCTCollection(CTCollection ctCollection)
		throws PortalException {

		List<CTEntry> ctEntries = ctCollectionPersistence.getCTEntries(
			ctCollection.getCtCollectionId());

		for (CTEntry ctEntry : ctEntries) {
			int ctCollectionsSize = ctEntryPersistence.getCTCollectionsSize(
				ctCollection.getCtCollectionId());

			if (ctCollectionsSize > 1) {
				continue;
			}

			ctEntryLocalService.deleteCTEntry(ctEntry);
		}

		List<CTProcess> ctProcesses = ctProcessLocalService.getCTProcesses(
			ctCollection.getCtCollectionId());

		for (CTProcess ctProcess : ctProcesses) {
			ctProcessLocalService.deleteCTProcess(ctProcess);
		}

		ctCollectionPersistence.remove(ctCollection);

		ctCollectionPersistence.clearCTEntries(
			ctCollection.getCtCollectionId());

		return ctCollection;
	}

	@Override
	public CTCollection fetchCTCollection(long companyId, String name) {
		return ctCollectionPersistence.fetchByC_N(companyId, name);
	}

	@Override
	public List<CTCollection> getCTCollections(long companyId) {
		return ctCollectionPersistence.findByCompanyId(companyId);
	}

	@Override
	public CTCollection updateStatus(
			long userId, CTCollection ctCollection, int status,
			ServiceContext serviceContext)
		throws PortalException {

		Date modifiedDate = serviceContext.getModifiedDate(new Date());

		ctCollection.setModifiedDate(modifiedDate);

		ctCollection.setStatus(status);

		User user = userLocalService.getUser(userId);

		ctCollection.setStatusByUserId(user.getUserId());
		ctCollection.setStatusByUserName(user.getFullName());

		ctCollection.setStatusDate(modifiedDate);

		return ctCollectionPersistence.update(ctCollection);
	}

	private void _validate(String name) throws CTCollectionNameException {
		if (Validator.isNull(name)) {
			throw new CTCollectionNameException();
		}
	}

}