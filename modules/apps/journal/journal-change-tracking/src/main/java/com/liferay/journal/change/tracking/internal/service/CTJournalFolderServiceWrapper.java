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

package com.liferay.journal.change.tracking.internal.service;

import com.liferay.change.tracking.CTEngineManager;
import com.liferay.change.tracking.CTManager;
import com.liferay.change.tracking.model.CTEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalFolderService;
import com.liferay.journal.service.JournalFolderServiceWrapper;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Gergely Mathe
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class CTJournalFolderServiceWrapper extends JournalFolderServiceWrapper {

	public CTJournalFolderServiceWrapper() {
		super(null);
	}

	public CTJournalFolderServiceWrapper(
		JournalFolderService journalFolderService) {

		super(journalFolderService);
	}

	@Override
	public List<Object> getFoldersAndArticles(
		long groupId, long folderId, int status, int start, int end,
		OrderByComparator<?> obc) {

		List<Object> objects = super.getFoldersAndArticles(
			groupId, folderId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			obc);

		return _filterByChangeTracking(objects, start, end);
	}

	@Override
	public List<Object> getFoldersAndArticles(
		long groupId, long folderId, int start, int end,
		OrderByComparator<?> obc) {

		List<Object> objects = super.getFoldersAndArticles(
			groupId, folderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		return _filterByChangeTracking(objects, start, end);
	}

	@Override
	public List<Object> getFoldersAndArticles(
		long groupId, long userId, long folderId, int status, int start,
		int end, OrderByComparator<?> obc) {

		List<Object> objects = super.getFoldersAndArticles(
			groupId, userId, folderId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, obc);

		return _filterByChangeTracking(objects, start, end);
	}

	@Override
	public List<Object> getFoldersAndArticles(
		long groupId, long userId, long folderId, int status, Locale locale,
		int start, int end, OrderByComparator<?> obc) {

		List<Object> objects = super.getFoldersAndArticles(
			groupId, userId, folderId, status, locale, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, obc);

		return _filterByChangeTracking(objects, start, end);
	}

	@Override
	public int getFoldersAndArticlesCount(
		long groupId, List<Long> folderIds, int status) {

		super.getFoldersAndArticlesCount(groupId, folderIds, status);

		int count = 0;

		for (long folderId : folderIds) {
			count += getFoldersAndArticlesCount(groupId, folderId, status);
		}

		return count;
	}

	@Override
	public int getFoldersAndArticlesCount(long groupId, long folderId) {
		super.getFoldersAndArticlesCount(groupId, folderId);

		return getFoldersAndArticlesCount(
			groupId, folderId, WorkflowConstants.STATUS_ANY);
	}

	@Override
	public int getFoldersAndArticlesCount(
		long groupId, long folderId, int status) {

		super.getFoldersAndArticlesCount(groupId, folderId, status);

		return getFoldersAndArticlesCount(groupId, 0, folderId, status);
	}

	@Override
	public int getFoldersAndArticlesCount(
		long groupId, long userId, long folderId, int status) {

		super.getFoldersAndArticlesCount(groupId, userId, folderId, status);

		List<Object> objects = getFoldersAndArticles(
			groupId, userId, folderId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		return objects.size();
	}

	@Reference(unbind = "-")
	protected void setJournalFolderService(
		JournalFolderService journalFolderService) {

		// Needed for synchronization

	}

	private List<Object> _filterByChangeTracking(
		List<Object> objects, int start, int end) {

		List<Object> filteredObjects = new ArrayList<>();

		for (Object object : objects) {
			if (object instanceof JournalArticle) {
				if (_isRetrievable(object)) {
					JournalArticle journalArticle =
						_journalArticleLocalService.fetchLatestArticle(
							((JournalArticle)object).getResourcePrimKey());

					if ((journalArticle != null) &&
						_hasPermission(journalArticle)) {

						filteredObjects.add(journalArticle);
					}
				}
			}
			else {
				filteredObjects.add(object);
			}
		}

		return ListUtil.subList(filteredObjects, start, end);
	}

	private boolean _hasPermission(JournalArticle journalArticle) {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker == null) {
			return false;
		}

		if (journalArticle == null) {
			return false;
		}

		try {
			_journalArticleModelResourcePermission.check(
				permissionChecker, journalArticle.getPrimaryKey(),
				ActionKeys.VIEW);

			return true;
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(pe.getMessage());
			}

			return false;
		}
	}

	private boolean _isRetrievable(Object object) {
		if (object == null) {
			return true;
		}

		if (!(object instanceof JournalArticle)) {
			return true;
		}

		JournalArticle journalArticle = (JournalArticle)object;

		if (!_ctEngineManager.isChangeTrackingEnabled(
				journalArticle.getCompanyId()) ||
			!_ctEngineManager.isChangeTrackingSupported(
				journalArticle.getCompanyId(), JournalArticle.class)) {

			return true;
		}

		if (_ctManager.isModelUpdateInProgress()) {
			return true;
		}

		Optional<CTEntry> ctEntryOptional =
			_ctManager.getLatestModelChangeCTEntryOptional(
				PrincipalThreadLocal.getUserId(),
				journalArticle.getResourcePrimKey());

		return ctEntryOptional.isPresent();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CTJournalFolderServiceWrapper.class);

	private static volatile ModelResourcePermission<JournalArticle>
		_journalArticleModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CTJournalArticleServiceWrapper.class,
				"_journalArticleModelResourcePermission", JournalArticle.class);

	@Reference
	private CTEngineManager _ctEngineManager;

	@Reference
	private CTManager _ctManager;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

}