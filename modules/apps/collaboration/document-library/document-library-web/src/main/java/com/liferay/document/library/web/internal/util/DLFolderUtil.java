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

package com.liferay.document.library.web.internal.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zoltan Csaszi
 */
public class DLFolderUtil {

	public static List<StagedModel> getFolderEntries(
			long groupId, Folder folder)
		throws PortalException {

		List<StagedModel> entries = new ArrayList<>();

		if (folder != null) {
			QueryDefinition<?> queryDefinition = new QueryDefinition<>(
				WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

			for (Object entry : DLFolderLocalServiceUtil.
					getFoldersAndFileEntriesAndFileShortcuts(
						groupId, folder.getFolderId(), null, true,
						queryDefinition)) {

				if (entry instanceof DLFileEntry) {
					entries.add(
						DLAppLocalServiceUtil.getFileEntry(
							((DLFileEntry)entry).getPrimaryKey()));
				}
				else if (entry instanceof DLFolder) {
					entries.add(
						DLAppLocalServiceUtil.getFolder(
							((DLFolder)entry).getPrimaryKey()));
				}
				else if (entry instanceof DLFileShortcut) {
					entries.add(
						DLAppLocalServiceUtil.getFileShortcut(
							((DLFileShortcut)entry).getPrimaryKey()));
				}
			}
		}

		return entries;
	}

}