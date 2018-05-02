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

package com.liferay.document.library.web.internal.exportimport.portlet.preferences.processor;

import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.exportimport.kernel.exception.DLDataException;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.staging.MergeLayoutPrototypesThreadLocal;
import com.liferay.exportimport.portlet.preferences.processor.Capability;
import com.liferay.exportimport.portlet.preferences.processor.ExportImportPortletPreferencesProcessor;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mate Thurzo
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY,
	service = {
		DLExportImportPortletPreferencesProcessor.class,
		ExportImportPortletPreferencesProcessor.class
	}
)
public class DLExportImportPortletPreferencesProcessor
	implements ExportImportPortletPreferencesProcessor {

	@Override
	public List<Capability> getExportCapabilities() {
		return ListUtil.toList(new Capability[] {_exportCapability});
	}

	@Override
	public List<Capability> getImportCapabilities() {
		return ListUtil.toList(new Capability[] {_importCapability});
	}

	@Override
	public PortletPreferences processExportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		if (!MapUtil.getBoolean(
				portletDataContext.getParameterMap(),
				PortletDataHandlerKeys.PORTLET_DATA) &&
			MergeLayoutPrototypesThreadLocal.isInProgress()) {

			return portletPreferences;
		}

		String portletId = portletDataContext.getPortletId();

		long rootFolderId = GetterUtil.getLong(
			portletPreferences.getValue("rootFolderId", null));

		if (rootFolderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			Folder folder = null;

			try {
				folder = _dlAppLocalService.getFolder(rootFolderId);
			}
			catch (PortalException pe) {
				StringBundler sb = new StringBundler(4);

				sb.append("Portlet ");
				sb.append(portletId);
				sb.append(" refers to an invalid root folder ID ");
				sb.append(rootFolderId);

				_log.error(sb.toString());

				DLDataException dde = new DLDataException(
					DLDataException.INVALID_ROOT_FOLDER, pe);

				dde.setPortletId(portletId);

				dde.setData(new String[] {
					portletId, String.valueOf(rootFolderId)
				});

				throw dde;
			}

			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, portletId, folder);
		}

		return portletPreferences;
	}

	@Override
	public PortletPreferences processImportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		long rootFolderId = GetterUtil.getLong(
			portletPreferences.getValue("rootFolderId", null));

		if (rootFolderId > 0) {
			Element foldersElement =
				portletDataContext.getImportDataGroupElement(DLFolder.class);

			List<Element> folderElements = foldersElement.elements();

			if (!folderElements.isEmpty()) {
				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, folderElements.get(0));

				Map<Long, Long> folderIds =
					(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
						Folder.class + ".folderIdsAndRepositoryEntryIds");

				rootFolderId = MapUtil.getLong(
					folderIds, rootFolderId, rootFolderId);

				try {
					portletPreferences.setValue(
						"rootFolderId", String.valueOf(rootFolderId));
				}
				catch (ReadOnlyException roe) {
					//"Unable to update portlet preferences during import"
					throw new DLDataException(
						DLDataException.UNABLE_TO_UPDATE_PORTLET_PREFERENCES,
						roe);
				}
			}
		}

		return portletPreferences;
	}

	@Reference(unbind = "-")
	protected void setDLAppLocalService(DLAppLocalService dlAppLocalService) {
		_dlAppLocalService = dlAppLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DLExportImportPortletPreferencesProcessor.class);

	private DLAppLocalService _dlAppLocalService;

	@Reference(target = "(name=PortletDisplayTemplateExporter)")
	private Capability _exportCapability;

	@Reference(target = "(name=PortletDisplayTemplateImporter)")
	private Capability _importCapability;

}