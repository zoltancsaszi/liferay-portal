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

package com.liferay.asset.tags.internal.exportimport.data.handler;

import com.liferay.asset.kernel.exception.DuplicateTagException;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.lar.file.LARFile;
import com.liferay.exportimport.lar.file.LARFileUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniel Kocsis
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class AssetTagStagedModelDataHandler
	extends BaseStagedModelDataHandler<AssetTag> {

	public static final String[] CLASS_NAMES = {AssetTag.class.getName()};

	@Override
	public void deleteStagedModel(AssetTag stagedAssetTag)
		throws PortalException {

		_assetTagLocalService.deleteTag(stagedAssetTag);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		AssetTag assetTag = fetchStagedModelByUuidAndGroupId(uuid, groupId);

		if (assetTag != null) {
			deleteStagedModel(assetTag);
		}
	}

	@Override
	public AssetTag fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		AssetTag assetTag = _assetTagLocalService.fetchAssetTagByUuidAndGroupId(
			uuid, groupId);

		if (assetTag == null) {
			return null;
		}

		return assetTag;
	}

	@Override
	public List<AssetTag> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return _assetTagLocalService.getAssetTagsByUuidAndCompanyId(
			uuid, companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(AssetTag assetTag) {
		return assetTag.getName();
	}

	protected ServiceContext createServiceContext(
		PortletDataContext portletDataContext, AssetTag assetTag) {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCreateDate(assetTag.getCreateDate());
		serviceContext.setModifiedDate(assetTag.getModifiedDate());
		serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());

		return serviceContext;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, AssetTag assetTag)
		throws Exception {

		LARFile larFile = LARFileUtil.getLARFile(portletDataContext);

		larFile.startWriteStagedModel(assetTag);

		portletDataContext.addStagedModel(assetTag);

		larFile.endWriteStagedModel();
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, AssetTag assetTag)
		throws Exception {

		long userId = portletDataContext.getUserId(assetTag.getUserUuid());

		ServiceContext serviceContext = createServiceContext(
			portletDataContext, assetTag);

		AssetTag existingAssetTag = fetchStagedModelByUuidAndGroupId(
			assetTag.getUuid(), portletDataContext.getScopeGroupId());

		if (portletDataContext.getBooleanParameter(
				AssetTagsPortletDataHandler.NAMESPACE, "merge-tags-by-name")) {

			Optional<AssetTag> assetTagOptional = Optional.ofNullable(
				_assetTagLocalService.fetchTag(
					portletDataContext.getScopeGroupId(), assetTag.getName()));

			existingAssetTag = assetTagOptional.orElse(existingAssetTag);
		}

		AssetTag importedAssetTag = null;

		if (existingAssetTag == null) {
			serviceContext.setUuid(assetTag.getUuid());

			try {
				importedAssetTag = _assetTagLocalService.addTag(
					userId, portletDataContext.getScopeGroupId(),
					assetTag.getName(), serviceContext);
			}
			catch (DuplicateTagException dte) {
				if (_log.isDebugEnabled()) {
					_log.debug(dte);
				}

				importedAssetTag = _assetTagLocalService.addTag(
					userId, portletDataContext.getScopeGroupId(),
					assetTag.getName() + " (Duplicate)", serviceContext);
			}
		}
		else {
			try {
				importedAssetTag = _assetTagLocalService.updateTag(
					userId, existingAssetTag.getTagId(), assetTag.getName(),
					serviceContext);
			}
			catch (DuplicateTagException dte) {
				if (_log.isDebugEnabled()) {
					_log.debug(dte);
				}

				importedAssetTag = _assetTagLocalService.updateTag(
					userId, existingAssetTag.getTagId(),
					assetTag.getName() + " (Duplicate)", serviceContext);
			}
		}

		portletDataContext.importClassedModel(assetTag, importedAssetTag);
	}

	@Reference(unbind = "-")
	protected void setAssetTagLocalService(
		AssetTagLocalService assetTagLocalService) {

		_assetTagLocalService = assetTagLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetTagStagedModelDataHandler.class);

	private AssetTagLocalService _assetTagLocalService;

}