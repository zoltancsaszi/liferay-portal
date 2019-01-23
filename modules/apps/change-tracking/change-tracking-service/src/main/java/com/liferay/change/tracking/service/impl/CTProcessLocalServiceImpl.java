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

import com.liferay.change.tracking.model.CTProcess;
import com.liferay.change.tracking.service.base.CTProcessLocalServiceBaseImpl;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * @author Daniel Kocsis
 */
public class CTProcessLocalServiceImpl extends CTProcessLocalServiceBaseImpl {

	@Override
	public CTProcess addCTProcess(
			long userId, long ctCollectionId, ServiceContext serviceContext)
		throws PortalException {

		_validate(ctCollectionId);

		long ctProcessId = counterLocalService.increment();

		CTProcess ctProcess = ctProcessPersistence.create(ctProcessId);

		User user = userLocalService.getUser(userId);

		ctProcess.setCompanyId(user.getCompanyId());
		ctProcess.setUserId(user.getUserId());
		ctProcess.setUserName(user.getFullName());

		Date now = new Date();

		ctProcess.setCreateDate(serviceContext.getCreateDate(now));
		ctProcess.setModifiedDate(serviceContext.getModifiedDate(now));

		ctProcess.setCtCollectionId(ctCollectionId);

		// todo: starting a new background task from here

		return ctProcessPersistence.update(ctProcess);
	}

	@Override
	public CTProcess deleteCTProcess(CTProcess ctProcess)
		throws PortalException {

		if (ctProcess.getBackgroundTaskId() > 0) {
			BackgroundTaskManagerUtil.deleteBackgroundTask(
				ctProcess.getBackgroundTaskId());
		}

		return ctProcessPersistence.remove(ctProcess);
	}

	@Override
	public List<CTProcess> getCTProcesses(long ctCollectionId) {
		return ctProcessPersistence.findByCollectionId(ctCollectionId);
	}

	private void _validate(long ctCollectionId) throws PortalException {
		ctCollectionLocalService.getCTCollection(ctCollectionId);
	}

}