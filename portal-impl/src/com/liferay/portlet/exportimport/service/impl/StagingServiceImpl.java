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

package com.liferay.portlet.exportimport.service.impl;

import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.exportimport.kernel.lar.MissingReferences;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleManagerUtil;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portlet.exportimport.service.base.StagingServiceBaseImpl;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class StagingServiceImpl extends StagingServiceBaseImpl {

	@Override
	public void cleanUpStagingRequest(long stagingRequestId)
		throws PortalException {

		boolean portletStagingInProcess =
			ExportImportThreadLocal.isPortletStagingInProcess();

		ExportImportThreadLocal.setPortletStagingInProcess(true);

		try {
			checkPermission(stagingRequestId);

			stagingLocalService.cleanUpStagingRequest(stagingRequestId);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"StagingServiceImpl#cleanUpStagingRequest(" +
						stagingRequestId + ")",
					pe);
			}

			throw pe;
		}
		finally {
			ExportImportThreadLocal.setPortletStagingInProcess(
				portletStagingInProcess);
		}
	}

	@Override
	public long createStagingRequest(long groupId, String checksum)
		throws PortalException {

		boolean portletStagingInProcess =
			ExportImportThreadLocal.isPortletStagingInProcess();

		ExportImportThreadLocal.setPortletStagingInProcess(true);

		try {
			GroupPermissionUtil.check(
				getPermissionChecker(), groupId,
				ActionKeys.EXPORT_IMPORT_LAYOUTS);

			return stagingLocalService.createStagingRequest(
				getUserId(), groupId, checksum);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						"StagingServiceImpl#createStagingRequest(",
						String.valueOf(groupId), ", ", checksum, ")"),
					pe);
			}

			throw pe;
		}
		finally {
			ExportImportThreadLocal.setPortletStagingInProcess(
				portletStagingInProcess);
		}
	}

	@Override
	public boolean hasRemoteLayout(
			String uuid, long groupId, boolean privateLayout)
		throws PortalException {

		try {
			GroupPermissionUtil.check(
				getPermissionChecker(), groupId,
				ActionKeys.EXPORT_IMPORT_LAYOUTS);

			return layoutLocalService.hasLayout(uuid, groupId, privateLayout);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						"StagingServiceImpl#hasRemoteLayout(", uuid, ", ",
						String.valueOf(groupId), ", ",
						String.valueOf(privateLayout), ")"),
					pe);
			}

			throw pe;
		}
	}

	@Override
	public void propagateExportImportLifecycleEvent(
			int code, int processFlag, String processId,
			List<Serializable> arguments)
		throws PortalException {

		try {
			Serializable serializable = arguments.get(0);

			long groupId = GroupConstants.DEFAULT_LIVE_GROUP_ID;

			if (serializable instanceof PortletDataContext) {
				PortletDataContext portletDataContext =
					(PortletDataContext)serializable;

				groupId = portletDataContext.getGroupId();
			}
			else if (serializable instanceof ExportImportConfiguration) {
				ExportImportConfiguration exportImportConfiguration =
					(ExportImportConfiguration)serializable;

				groupId = MapUtil.getLong(
					exportImportConfiguration.getSettingsMap(),
					"targetGroupId");
			}

			GroupPermissionUtil.check(
				getPermissionChecker(), groupId,
				ActionKeys.EXPORT_IMPORT_LAYOUTS);

			ExportImportLifecycleManagerUtil.fireExportImportLifecycleEvent(
				code, processFlag, processId,
				arguments.toArray(new Serializable[arguments.size()]));
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				StringBundler sb = new StringBundler(9);

				sb.append(
					"StagingServiceImpl#propagateExportImportLifecycleEvent(");
				sb.append(code);
				sb.append(StringPool.COMMA_AND_SPACE);
				sb.append(processFlag);
				sb.append(StringPool.COMMA_AND_SPACE);
				sb.append(processId);
				sb.append(StringPool.COMMA_AND_SPACE);
				sb.append(arguments);
				sb.append(StringPool.CLOSE_PARENTHESIS);

				_log.debug(sb.toString(), pe);
			}

			throw pe;
		}
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public MissingReferences publishStagingRequest(
			long stagingRequestId, boolean privateLayout,
			Map<String, String[]> parameterMap)
		throws PortalException {

		checkPermission(stagingRequestId);

		return stagingLocalService.publishStagingRequest(
			getUserId(), stagingRequestId, privateLayout, parameterMap);
	}

	@Override
	public MissingReferences publishStagingRequest(
			long stagingRequestId,
			ExportImportConfiguration exportImportConfiguration)
		throws PortalException {

		boolean portletStagingInProcess =
			ExportImportThreadLocal.isPortletStagingInProcess();

		ExportImportThreadLocal.setPortletStagingInProcess(true);

		try {
			checkPermission(stagingRequestId);

			return stagingLocalService.publishStagingRequest(
				getUserId(), stagingRequestId, exportImportConfiguration);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						"StagingServiceImpl#publishStagingRequest(",
						String.valueOf(stagingRequestId), ", ",
						String.valueOf(exportImportConfiguration), ")"),
					pe);
			}

			throw pe;
		}
		finally {
			ExportImportThreadLocal.setPortletStagingInProcess(
				portletStagingInProcess);
		}
	}

	@Override
	public void updateStagingRequest(
			long stagingRequestId, String fileName, byte[] bytes)
		throws PortalException {

		boolean portletStagingInProcess =
			ExportImportThreadLocal.isPortletStagingInProcess();

		ExportImportThreadLocal.setPortletStagingInProcess(true);

		try {
			checkPermission(stagingRequestId);

			stagingLocalService.updateStagingRequest(
				getUserId(), stagingRequestId, fileName, bytes);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						"StagingServiceImpl#updateStagingRequest(",
						String.valueOf(stagingRequestId), ", ", fileName, ", ",
						String.valueOf(bytes.length), "bytes)"),
					pe);
			}

			throw pe;
		}
		finally {
			ExportImportThreadLocal.setPortletStagingInProcess(
				portletStagingInProcess);
		}
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             #publishStagingRequest(long, boolean, Map)}
	 */
	@Deprecated
	@Override
	public MissingReferences validateStagingRequest(
			long stagingRequestId, boolean privateLayout,
			Map<String, String[]> parameterMap)
		throws PortalException {

		boolean portletStagingInProcess =
			ExportImportThreadLocal.isPortletStagingInProcess();

		ExportImportThreadLocal.setPortletStagingInProcess(true);

		try {
			checkPermission(stagingRequestId);

			return stagingLocalService.validateStagingRequest(
				getUserId(), stagingRequestId, privateLayout, parameterMap);
		}
		finally {
			ExportImportThreadLocal.setPortletStagingInProcess(
				portletStagingInProcess);
		}
	}

	protected void checkPermission(long stagingRequestId)
		throws PortalException {

		Folder folder = PortletFileRepositoryUtil.getPortletFolder(
			stagingRequestId);

		GroupPermissionUtil.check(
			getPermissionChecker(), folder.getGroupId(),
			ActionKeys.EXPORT_IMPORT_LAYOUTS);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		StagingServiceImpl.class);

}