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

package com.liferay.document.library.web.lar;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.document.library.kernel.model.DLFileShortcutConstants;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService;
import com.liferay.document.library.kernel.service.DLFileShortcutLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.document.library.web.constants.DLPortletKeys;
import com.liferay.exportimport.exception.handler.ExceptionHandlerHelper;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerControl;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerRegistryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.exportimport.portlet.data.handler.helper.PortletDataHandlerControlHelper;
import com.liferay.exportimport.portlet.data.handler.helper.PortletDataHandlerHelper;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Conjunction;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.RepositoryLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.repository.liferayrepository.LiferayRepositoryDefiner;
import com.liferay.portal.repository.temporaryrepository.TemporaryFileEntryRepositoryDefiner;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.documentlibrary.constants.DLConstants;
import com.liferay.portlet.documentlibrary.service.permission.DLPermission;

import java.util.List;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Farache
 * @author Raymond Augé
 * @author Sampsa Sohlman
 * @author Mate Thurzo
 * @author Zsolt Berentey
 */
@Component(
	property = {
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY,
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY_ADMIN
	},
	service = PortletDataHandler.class
)
public class DLPortletDataHandler implements PortletDataHandler {

	public static final String NAMESPACE = "document_library";

	public static final String SCHEMA_VERSION = "1.0.0";

	@Override
	public PortletPreferences addDefaultData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		long startTime = _portletDataHandlerHelper.doBeforeAddDefaultData(
			portletId);

		try {
			return doAddDefaultData(
				portletDataContext, portletId, portletPreferences);
		}
		catch (PortletDataException pde) {
			throw pde;
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
		finally {
			_portletDataHandlerHelper.doAfterAddDefaultData(
				portletId, startTime);
		}
	}

	@Override
	public PortletPreferences deleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		long startTime = _portletDataHandlerHelper.doBeforeDelete(portletId);

		try {
			return doDeleteData(
				portletDataContext, portletId, portletPreferences);
		}
		catch (Exception e) {
			throw _exceptionHandlerHelper.handleException(
				e, PortletDataException.DELETE_PORTLET_DATA, portletId);
		}
		finally {
			_portletDataHandlerHelper.doAfterDelete(portletId, startTime);
		}
	}

	@Override
	public String exportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		Element rootElement = null;

		long startTime = _portletDataHandlerHelper.doBeforeExport(
			portletDataContext, portletId, rootElement,
			getDeletionSystemEventStagedModelTypes(), getExportControls(),
			portletPreferences);

		try {
			return doExportData(
				portletDataContext, portletId, portletPreferences);
		}
		catch (Exception e) {
			throw _exceptionHandlerHelper.handleException(
				e, PortletDataException.EXPORT_PORTLET_DATA, portletId);
		}
		finally {
			_portletDataHandlerHelper.doAfterExport(
				portletDataContext, rootElement, startTime);
		}
	}

	@Override
	public String[] getDataPortletPreferences() {
		return new String[] {"rootFolderId"};
	}

	@Override
	public StagedModelType[] getDeletionSystemEventStagedModelTypes() {
		return new StagedModelType[] {
			new StagedModelType(DLFileEntryType.class),
			new StagedModelType(DLFileShortcut.class),
			new StagedModelType(DLFileEntryConstants.getClassName()),
			new StagedModelType(DLFolderConstants.getClassName()),
			new StagedModelType(
				Repository.class.getName(),
				StagedModelType.REFERRER_CLASS_NAME_ALL)
		};
	}

	@Override
	public PortletDataHandlerControl[] getExportConfigurationControls(
			long companyId, long groupId, Portlet portlet,
			boolean privateLayout)
		throws Exception {

		return _portletDataHandlerControlHelper.getExportConfigurationControls(
			companyId, groupId, portlet, isDisplayPortlet(),
			getExportControls(), -1, privateLayout);
	}

	@Override
	public PortletDataHandlerControl[] getExportConfigurationControls(
			long companyId, long groupId, Portlet portlet, long plid,
			boolean privateLayout)
		throws Exception {

		return _portletDataHandlerControlHelper.getExportConfigurationControls(
			companyId, groupId, portlet, isDisplayPortlet(),
			getExportControls(), plid, privateLayout);
	}

	@Override
	public PortletDataHandlerControl[] getExportControls() {
		return new PortletDataHandlerControl[] {
			new PortletDataHandlerBoolean(
				NAMESPACE, "repositories", true, false, null,
				Repository.class.getName(),
				StagedModelType.REFERRER_CLASS_NAME_ALL),
			new PortletDataHandlerBoolean(
				NAMESPACE, "folders", true, false, null,
				DLFolderConstants.getClassName()),
			new PortletDataHandlerBoolean(
				NAMESPACE, "documents", true, false,
				new PortletDataHandlerControl[] {
					new PortletDataHandlerBoolean(
						NAMESPACE, "previews-and-thumbnails")
				},
				DLFileEntryConstants.getClassName()),
			new PortletDataHandlerBoolean(
				NAMESPACE, "document-types", true, false, null,
				DLFileEntryType.class.getName()),
			new PortletDataHandlerBoolean(
				NAMESPACE, "shortcuts", true, false, null,
				DLFileShortcutConstants.getClassName())
		};
	}

	@Override
	public long getExportModelCount(ManifestSummary manifestSummary) {
		return _portletDataHandlerHelper.getExportModelCount(
			manifestSummary, getExportControls());
	}

	@Override
	public PortletDataHandlerControl[] getImportConfigurationControls(
		Portlet portlet, ManifestSummary manifestSummary) {

		return _portletDataHandlerControlHelper.getImportConfigurationControls(
			portlet, manifestSummary, isDisplayPortlet(), getExportControls());
	}

	@Override
	public PortletDataHandlerControl[] getImportConfigurationControls(
		String[] configurationPortletOptions) {

		return _portletDataHandlerControlHelper.getImportConfigurationControls(
			configurationPortletOptions, isDisplayPortlet(),
			getExportControls());
	}

	@Override
	public PortletDataHandlerControl[] getImportControls() {
		return getExportControls();
	}

	/**
	 * @deprecated As of 1.5.0
	 */
	@Deprecated
	@Override
	public String getPortletId() {
		return DLPortletKeys.DOCUMENT_LIBRARY_ADMIN;
	}

	@Override
	public int getRank() {
		return 90;
	}

	@Override
	public String getSchemaVersion() {
		return SCHEMA_VERSION;
	}

	@Override
	public String getServiceName() {
		return DLConstants.SERVICE_NAME;
	}

	@Override
	public PortletDataHandlerControl[] getStagingControls() {
		return getExportControls();
	}

	@Override
	public PortletPreferences importData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws PortletDataException {

		Long startTime = null;
		Element rootElement = null;

		try {
			_portletDataHandlerHelper.doBeforeImport(
				portletDataContext, portletId, startTime, rootElement, data);

			return doImportData(
				portletDataContext, portletId, portletPreferences, data);
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);
			throw _exceptionHandlerHelper.handleException(
				e, PortletDataException.IMPORT_PORTLET_DATA, portletId);
		}
		finally {
			_portletDataHandlerHelper.doAfterImport(
				portletDataContext, rootElement, startTime);
		}
	}

	@Override
	public boolean isDataLocalized() {
		return true;
	}

	@Override
	public boolean isPublishToLiveByDefault() {
		return PropsValues.DL_PUBLISH_TO_LIVE_BY_DEFAULT;
	}

	@Override
	public void prepareManifestSummary(PortletDataContext portletDataContext)
		throws PortletDataException {

		prepareManifestSummary(portletDataContext, null);
	}

	@Override
	public void prepareManifestSummary(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		try {
			doPrepareManifestSummary(portletDataContext, portletPreferences);
		}
		catch (Exception e) {
			throw _exceptionHandlerHelper.handleException(
				e, PortletDataException.PREPARE_MANIFEST_SUMMARY,
				portletDataContext.getPortletId());
		}
	}

	/**
	 * @deprecated As of 7.0.0
	 */
	@Deprecated
	@Override
	public PortletPreferences processExportPortletPreferences(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		return null;
	}

	/**
	 * @deprecated As of 7.0.0
	 */
	@Deprecated
	@Override
	public PortletPreferences processImportPortletPreferences(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		return null;
	}

	@Override
	public void setPortletId(String portletId) {
	}

	@Override
	public void setRank(int rank) {
	}

	@Override
	public boolean validateSchemaVersion(String schemaVersion) {
		return _portletDataHandlerHelper.validateSchemaVersion(
			schemaVersion, getSchemaVersion());
	}

	protected PortletPreferences doAddDefaultData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		return portletPreferences;
	}

	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (portletDataContext.addPrimaryKey(
				DLPortletDataHandler.class, "deleteData")) {

			return portletPreferences;
		}

		_dlAppLocalService.deleteAll(portletDataContext.getScopeGroupId());
		_dlFileEntryTypeLocalService.deleteFileEntryTypes(
			portletDataContext.getScopeGroupId());

		if (portletPreferences == null) {
			return portletPreferences;
		}

		portletPreferences.setValue("enable-comment-ratings", StringPool.BLANK);
		portletPreferences.setValue("fileEntriesPerPage", StringPool.BLANK);
		portletPreferences.setValue("fileEntryColumns", StringPool.BLANK);
		portletPreferences.setValue("folderColumns", StringPool.BLANK);
		portletPreferences.setValue("foldersPerPage", StringPool.BLANK);
		portletPreferences.setValue("rootFolderId", StringPool.BLANK);
		portletPreferences.setValue("showFoldersSearch", StringPool.BLANK);
		portletPreferences.setValue("showSubfolders", StringPool.BLANK);

		return portletPreferences;
	}

	protected String doExportData(
			final PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		portletDataContext.addPortletPermissions(DLPermission.RESOURCE_NAME);

		Element rootElement = portletDataContext.addExportDataRootElement();

		rootElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getScopeGroupId()));

		if (portletDataContext.getBooleanParameter(NAMESPACE, "folders")) {
			ActionableDynamicQuery folderActionableDynamicQuery =
				getFolderActionableDynamicQuery(portletDataContext);

			folderActionableDynamicQuery.performActions();
		}

		if (portletDataContext.getBooleanParameter(NAMESPACE, "documents")) {
			ActionableDynamicQuery fileEntryActionableDynamicQuery =
				getFileEntryActionableDynamicQuery(portletDataContext);

			fileEntryActionableDynamicQuery.performActions();
		}

		if (portletDataContext.getBooleanParameter(
				NAMESPACE, "document-types")) {

			ActionableDynamicQuery fileEntryTypeActionableDynamicQuery =
				getDLFileEntryTypeActionableDynamicQuery(portletDataContext);

			fileEntryTypeActionableDynamicQuery.performActions();
		}

		if (portletDataContext.getBooleanParameter(NAMESPACE, "repositories")) {
			ActionableDynamicQuery repositoryActionableDynamicQuery =
				getRepositoryActionableDynamicQuery(portletDataContext);

			repositoryActionableDynamicQuery.performActions();
		}

		if (portletDataContext.getBooleanParameter(NAMESPACE, "shortcuts")) {
			ActionableDynamicQuery fileShortcutActionableDynamicQuery =
				getDLFileShortcutActionableDynamicQuery(portletDataContext);

			fileShortcutActionableDynamicQuery.performActions();
		}

		return portletDataContext.getExportDataRootElementString();
	}

	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		portletDataContext.importPortletPermissions(DLPermission.RESOURCE_NAME);

		if (portletDataContext.getBooleanParameter(NAMESPACE, "folders")) {
			Element foldersElement =
				portletDataContext.getImportDataGroupElement(DLFolder.class);

			List<Element> folderElements = foldersElement.elements();

			for (Element folderElement : folderElements) {
				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, folderElement);
			}
		}

		if (portletDataContext.getBooleanParameter(NAMESPACE, "documents")) {
			Element fileEntriesElement =
				portletDataContext.getImportDataGroupElement(DLFileEntry.class);

			List<Element> fileEntryElements = fileEntriesElement.elements();

			for (Element fileEntryElement : fileEntryElements) {
				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, fileEntryElement);
			}
		}

		if (portletDataContext.getBooleanParameter(
				NAMESPACE, "document-types")) {

			Element fileEntryTypesElement =
				portletDataContext.getImportDataGroupElement(
					DLFileEntryType.class);

			List<Element> fileEntryTypeElements =
				fileEntryTypesElement.elements();

			for (Element fileEntryTypeElement : fileEntryTypeElements) {
				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, fileEntryTypeElement);
			}
		}

		if (portletDataContext.getBooleanParameter(NAMESPACE, "repositories")) {
			Element repositoriesElement =
				portletDataContext.getImportDataGroupElement(Repository.class);

			List<Element> repositoryElements = repositoriesElement.elements();

			for (Element repositoryElement : repositoryElements) {
				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, repositoryElement);
			}
		}

		if (portletDataContext.getBooleanParameter(NAMESPACE, "shortcuts")) {
			Element fileShortcutsElement =
				portletDataContext.getImportDataGroupElement(
					DLFileShortcut.class);

			List<Element> fileShortcutElements =
				fileShortcutsElement.elements();

			for (Element fileShortcutElement : fileShortcutElements) {
				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, fileShortcutElement);
			}
		}

		return portletPreferences;
	}

	protected void doPrepareManifestSummary(
			final PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws Exception {

		ActionableDynamicQuery dlFileShortcutActionableDynamicQuery =
			getDLFileShortcutActionableDynamicQuery(portletDataContext);

		dlFileShortcutActionableDynamicQuery.performCount();

		ActionableDynamicQuery fileEntryActionableDynamicQuery =
			getFileEntryActionableDynamicQuery(portletDataContext);

		fileEntryActionableDynamicQuery.performCount();

		ActionableDynamicQuery fileEntryTypeActionableDynamicQuery =
			getDLFileEntryTypeActionableDynamicQuery(portletDataContext);

		fileEntryTypeActionableDynamicQuery.performCount();

		ActionableDynamicQuery folderActionableDynamicQuery =
			getFolderActionableDynamicQuery(portletDataContext);

		folderActionableDynamicQuery.performCount();

		ActionableDynamicQuery repositoryActionableDynamicQuery =
			getRepositoryActionableDynamicQuery(portletDataContext);

		repositoryActionableDynamicQuery.performCount();
	}

	protected ActionableDynamicQuery getDLFileEntryTypeActionableDynamicQuery(
			final PortletDataContext portletDataContext)
		throws Exception {

		ActionableDynamicQuery actionableDynamicQuery =
			_dlFileEntryTypeLocalService.getExportActionableDynamicQuery(
				portletDataContext);

		final ActionableDynamicQuery.AddCriteriaMethod addCriteriaMethod =
			actionableDynamicQuery.getAddCriteriaMethod();

		actionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					addCriteriaMethod.addCriteria(dynamicQuery);

					Property property = PropertyFactoryUtil.forName("groupId");

					dynamicQuery.add(
						property.in(
							new Long[] {portletDataContext.getScopeGroupId()}));
				}

			});

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DLFileEntryType>() {

				@Override
				public void performAction(DLFileEntryType dlFileEntryType)
					throws PortalException {

					if (dlFileEntryType.isExportable()) {
						StagedModelDataHandlerUtil.exportStagedModel(
							portletDataContext, dlFileEntryType);
					}
				}

			});

		return actionableDynamicQuery;
	}

	protected ActionableDynamicQuery getDLFileShortcutActionableDynamicQuery(
			final PortletDataContext portletDataContext)
		throws Exception {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			_dlFileShortcutLocalService.getExportActionableDynamicQuery(
				portletDataContext);

		final ActionableDynamicQuery.AddCriteriaMethod addCriteriaMethod =
			exportActionableDynamicQuery.getAddCriteriaMethod();

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					addCriteriaMethod.addCriteria(dynamicQuery);

					Property property = PropertyFactoryUtil.forName("active");

					dynamicQuery.add(property.eq(Boolean.TRUE));
				}

			});

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DLFileShortcut>() {

				@Override
				public void performAction(DLFileShortcut dlFileShortcut)
					throws PortalException {

					FileShortcut fileShortcut =
						_dlAppLocalService.getFileShortcut(
							dlFileShortcut.getFileShortcutId());

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, fileShortcut);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(DLFileShortcutConstants.getClassName()));

		return exportActionableDynamicQuery;
	}

	protected ActionableDynamicQuery getFileEntryActionableDynamicQuery(
			final PortletDataContext portletDataContext)
		throws Exception {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			_dlFileEntryLocalService.getExportActionableDynamicQuery(
				portletDataContext);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					DynamicQuery fileVersionDynamicQuery =
						DynamicQueryFactoryUtil.forClass(
							DLFileVersion.class, "dlFileVersion",
							PortalClassLoaderUtil.getClassLoader());

					fileVersionDynamicQuery.setProjection(
						ProjectionFactoryUtil.property("fileEntryId"));

					fileVersionDynamicQuery.add(
						RestrictionsFactoryUtil.eqProperty(
							"dlFileVersion.fileEntryId", "this.fileEntryId"));
					fileVersionDynamicQuery.add(
						RestrictionsFactoryUtil.eqProperty(
							"dlFileVersion.version", "this.version"));

					Property fileVersionStatusProperty =
						PropertyFactoryUtil.forName("dlFileVersion.status");

					StagedModelDataHandler<?> stagedModelDataHandler =
						StagedModelDataHandlerRegistryUtil.
							getStagedModelDataHandler(
								DLFileEntry.class.getName());

					fileVersionDynamicQuery.add(
						fileVersionStatusProperty.in(
							stagedModelDataHandler.getExportableStatuses()));

					Criterion fileVersionModifiedDateCriterion =
						portletDataContext.getDateRangeCriteria(
							"dlFileVersion.modifiedDate");
					Criterion fileVersionStatusDateCriterion =
						portletDataContext.getDateRangeCriteria(
							"dlFileVersion.statusDate");
					Criterion modifiedDateCriterion =
						portletDataContext.getDateRangeCriteria(
							"this.modifiedDate");

					if ((fileVersionStatusDateCriterion != null) &&
						(modifiedDateCriterion != null)) {

						Disjunction disjunction =
							RestrictionsFactoryUtil.disjunction();

						disjunction.add(fileVersionModifiedDateCriterion);
						disjunction.add(fileVersionStatusDateCriterion);
						disjunction.add(modifiedDateCriterion);

						fileVersionDynamicQuery.add(disjunction);
					}

					Property fileEntryIdProperty = PropertyFactoryUtil.forName(
						"fileEntryId");

					dynamicQuery.add(
						fileEntryIdProperty.in(fileVersionDynamicQuery));

					Property repositoryIdProperty = PropertyFactoryUtil.forName(
						"repositoryId");

					dynamicQuery.add(
						repositoryIdProperty.eq(
							portletDataContext.getScopeGroupId()));
				}

			});
		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DLFileEntry>() {

				@Override
				public void performAction(DLFileEntry dlFileEntry)
					throws PortalException {

					FileEntry fileEntry = _dlAppLocalService.getFileEntry(
						dlFileEntry.getFileEntryId());

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, fileEntry);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(DLFileEntryConstants.getClassName()));

		return exportActionableDynamicQuery;
	}

	protected ActionableDynamicQuery getFolderActionableDynamicQuery(
			final PortletDataContext portletDataContext)
		throws Exception {

		ExportActionableDynamicQuery exportActionableDynamicQuery =
			_dlFolderLocalService.getExportActionableDynamicQuery(
				portletDataContext);

		final ActionableDynamicQuery.AddCriteriaMethod addCriteriaMethod =
			exportActionableDynamicQuery.getAddCriteriaMethod();

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					addCriteriaMethod.addCriteria(dynamicQuery);

					Property property = PropertyFactoryUtil.forName(
						"repositoryId");

					dynamicQuery.add(
						property.eq(portletDataContext.getScopeGroupId()));
				}

			});

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DLFolder>() {

				@Override
				public void performAction(DLFolder dlFolder)
					throws PortalException {

					if (dlFolder.isInTrash()) {
						return;
					}

					Folder folder = _dlAppLocalService.getFolder(
						dlFolder.getFolderId());

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, folder);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(DLFolderConstants.getClassName()));

		return exportActionableDynamicQuery;
	}

	protected ActionableDynamicQuery getRepositoryActionableDynamicQuery(
			final PortletDataContext portletDataContext)
		throws Exception {

		ExportActionableDynamicQuery exportActionableDynamicQuery =
			_repositoryLocalService.getExportActionableDynamicQuery(
				portletDataContext);

		final ActionableDynamicQuery.AddCriteriaMethod addCriteriaMethod =
			exportActionableDynamicQuery.getAddCriteriaMethod();

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					addCriteriaMethod.addCriteria(dynamicQuery);

					Conjunction conjunction =
						RestrictionsFactoryUtil.conjunction();

					Property classNameIdProperty = PropertyFactoryUtil.forName(
						"classNameId");

					long liferayRepositoryClassNameId = _portal.getClassNameId(
						LiferayRepositoryDefiner.CLASS_NAME);

					conjunction.add(
						classNameIdProperty.ne(liferayRepositoryClassNameId));

					long tempFileRepositoryClassNameId = _portal.getClassNameId(
						TemporaryFileEntryRepositoryDefiner.CLASS_NAME);

					conjunction.add(
						classNameIdProperty.ne(tempFileRepositoryClassNameId));

					dynamicQuery.add(conjunction);

					Disjunction disjunction =
						RestrictionsFactoryUtil.disjunction();

					Property portletIdProperty = PropertyFactoryUtil.forName(
						"portletId");

					disjunction.add(portletIdProperty.isNull());
					disjunction.add(portletIdProperty.eq(StringPool.BLANK));
					disjunction.add(
						portletIdProperty.eq(
							DLPortletKeys.DOCUMENT_LIBRARY_ADMIN));
					disjunction.add(
						portletIdProperty.like(
							DLPortletKeys.DOCUMENT_LIBRARY + "%"));

					dynamicQuery.add(disjunction);
				}

			});

		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				_portal.getClassNameId(Repository.class.getName()),
				StagedModelType.REFERRER_CLASS_NAME_ID_ALL));

		return exportActionableDynamicQuery;
	}

	@Reference(unbind = "-")
	protected void setDLAppLocalService(DLAppLocalService dlAppLocalService) {
		_dlAppLocalService = dlAppLocalService;
	}

	@Reference(unbind = "-")
	protected void setDLFileEntryLocalService(
		DLFileEntryLocalService dlFileEntryLocalService) {

		_dlFileEntryLocalService = dlFileEntryLocalService;
	}

	@Reference(unbind = "-")
	protected void setDLFileEntryTypeLocalService(
		DLFileEntryTypeLocalService dlFileEntryTypeLocalService) {

		_dlFileEntryTypeLocalService = dlFileEntryTypeLocalService;
	}

	@Reference(unbind = "-")
	protected void setDLFileShortcutLocalService(
		DLFileShortcutLocalService dlFileShortcutLocalService) {

		_dlFileShortcutLocalService = dlFileShortcutLocalService;
	}

	@Reference(unbind = "-")
	protected void setDLFolderLocalService(
		DLFolderLocalService dlFolderLocalService) {

		_dlFolderLocalService = dlFolderLocalService;
	}

	@Reference(unbind = "-")
	protected void setRepositoryLocalService(
		RepositoryLocalService repositoryLocalService) {

		_repositoryLocalService = repositoryLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DLPortletDataHandler.class);

	private DLAppLocalService _dlAppLocalService;
	private DLFileEntryLocalService _dlFileEntryLocalService;
	private DLFileEntryTypeLocalService _dlFileEntryTypeLocalService;
	private DLFileShortcutLocalService _dlFileShortcutLocalService;
	private DLFolderLocalService _dlFolderLocalService;

	@Reference
	private ExceptionHandlerHelper _exceptionHandlerHelper;

	@Reference
	private Portal _portal;

	@Reference
	private PortletDataHandlerControlHelper _portletDataHandlerControlHelper;

	@Reference
	private PortletDataHandlerHelper _portletDataHandlerHelper;

	private RepositoryLocalService _repositoryLocalService;

}