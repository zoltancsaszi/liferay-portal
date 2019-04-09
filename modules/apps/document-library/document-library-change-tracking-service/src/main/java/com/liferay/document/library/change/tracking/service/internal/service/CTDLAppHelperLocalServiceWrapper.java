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

package com.liferay.document.library.change.tracking.service.internal.service;

import com.liferay.change.tracking.CTManager;
import com.liferay.change.tracking.constants.CTConstants;
import com.liferay.change.tracking.exception.CTEntryException;
import com.liferay.change.tracking.exception.CTException;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLAppHelperLocalServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.Portal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltan Csaszi
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class CTDLAppHelperLocalServiceWrapper
	extends DLAppHelperLocalServiceWrapper {

	public CTDLAppHelperLocalServiceWrapper() {
		super(null);
	}

	public CTDLAppHelperLocalServiceWrapper(
		DLAppHelperLocalServiceWrapper dlAppHelperLocalServiceWrapper) {

		super(dlAppHelperLocalServiceWrapper);
	}

	//TODO: move from/to trash, restore must be implemented

	@Override
	public FileEntry moveFileEntryToTrash(long userId, FileEntry fileEntry)
		throws PortalException {

		fileEntry = super.moveFileEntryToTrash(userId, fileEntry);

		_registerChange(fileEntry, CTConstants.CT_CHANGE_TYPE_DELETION, true);

		return fileEntry;
	}

	public void restoreFileEntryFromTrash(long userId, FileEntry fileEntry)
		throws PortalException {

		super.restoreFileEntryFromTrash(userId, fileEntry);

		_registerChange(
			fileEntry, CTConstants.CT_CHANGE_TYPE_RESTORE_FROM_TRASH, true);
	}

	private void _registerChange(
			FileEntry fileEntry, int changeType, boolean force)
		throws CTException {

		if (fileEntry == null) {
			return;
		}

		try {
			FileVersion fileVersion = fileEntry.getFileVersion();

			_ctManager.registerModelChange(
				PrincipalThreadLocal.getUserId(),
				_portal.getClassNameId(DLFileVersion.class.getName()),
				fileVersion.getFileVersionId(), fileVersion.getFileEntryId(),
				changeType, force);
		}
		catch (CTException cte) {
			if (cte instanceof CTEntryException) {
				if (_log.isWarnEnabled()) {
					_log.warn(cte.getMessage());
				}
			}
			else {
				throw cte;
			}
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(pe.getMessage());
			}

			throw new CTException(
				fileEntry.getCompanyId(), pe.getMessage(), pe);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CTDLAppHelperLocalServiceWrapper.class);

	@Reference
	private CTManager _ctManager;

	@Reference
	private Portal _portal;

}