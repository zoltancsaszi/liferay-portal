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

package com.liferay.journal.web.internal.portlet.action;

import com.liferay.exportimport.changeset.Changeset;
import com.liferay.exportimport.changeset.portlet.action.ExportImportChangesetMVCActionCommand;
import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.service.JournalFolderLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltan Csaszi
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + JournalPortletKeys.JOURNAL,
		"mvc.command.name=/journal/publish_folder"
	},
	service = MVCActionCommand.class
)
public class PublishFolderMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long folderId = ParamUtil.getLong(actionRequest, "folderId");

		Changeset.Builder builder = Changeset.create();

		Changeset changeset = builder.addStagedModel(
			() -> _journalFolderLocalService.fetchJournalFolder(folderId)
		).addStagedModelHierarchy(
			() -> _journalFolderLocalService.fetchJournalFolder(folderId),
			folder -> _journalFolderLocalService.getFoldersAndArticles(
				folder.getGroupId(), folder.getFolderId())
		).build();

		_exportImportChangesetMVCActionCommand.processPublishAction(
			actionRequest, actionResponse, changeset);
	}

	@Reference
	private ExportImportChangesetMVCActionCommand
		_exportImportChangesetMVCActionCommand;

	@Reference
	private JournalFolderLocalService _journalFolderLocalService;

}