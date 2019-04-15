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
import com.liferay.journal.service.JournalArticleService;
import com.liferay.journal.service.JournalArticleServiceWrapper;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Portal;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltan Csaszi
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class CTJournalArticleServiceWrapper
	extends JournalArticleServiceWrapper {

	public CTJournalArticleServiceWrapper() {
		super(null);
	}

	public CTJournalArticleServiceWrapper(
		JournalArticleService journalArticleService) {

		super(journalArticleService);
	}

	@Override
	public List<JournalArticle> getArticlesByStructureId(
		long groupId, String ddmStructureKey, int status, int start, int end,
		OrderByComparator<JournalArticle> obc) {

		List<JournalArticle> journalArticles = _getArticlesByStructureId(
			groupId, ddmStructureKey, status, null);

		return ListUtil.subList(journalArticles, start, end);
	}

	@Override
	public int getArticlesCountByStructureId(
		long groupId, String ddmStructureKey, int status) {

		super.getArticlesCountByStructureId(groupId, ddmStructureKey, status);

		List<JournalArticle> journalArticles = _getArticlesByStructureId(
			groupId, ddmStructureKey, status, null);

		return journalArticles.size();
	}

	@Override
	public List<JournalArticle> getGroupArticles(
			long groupId, long userId, long rootFolderId, int status,
			boolean includeOwner, int start, int end,
			OrderByComparator<JournalArticle> orderByComparator)
		throws PortalException {

		List<JournalArticle> journalArticles = _getGroupArticles(
			groupId, userId, rootFolderId, status, includeOwner,
			orderByComparator);

		return ListUtil.subList(journalArticles, start, end);
	}

	@Override
	public int getGroupArticlesCount(
			long groupId, long userId, long rootFolderId, int status,
			boolean includeOwner)
		throws PortalException {

		super.getGroupArticlesCount(
			groupId, userId, rootFolderId, status, includeOwner);

		List<JournalArticle> journalArticles = _getGroupArticles(
			groupId, userId, rootFolderId, status, includeOwner, null);

		return journalArticles.size();
	}

	@Override
	public List<JournalArticle> search(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String keywords, Double version, String ddmStructureKey,
		String ddmTemplateKey, Date displayDateGT, Date displayDateLT,
		int status, Date reviewDate, int start, int end,
		OrderByComparator<JournalArticle> obc) {

		List<JournalArticle> journalArticles = _search(
			companyId, groupId, folderIds, classNameId, keywords, version,
			ddmStructureKey, ddmTemplateKey, displayDateGT, displayDateLT,
			status, reviewDate, obc);

		return ListUtil.subList(journalArticles, start, end);
	}

	@Override
	public int searchCount(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String keywords, Double version, String ddmStructureKey,
		String ddmTemplateKey, Date displayDateGT, Date displayDateLT,
		int status, Date reviewDate) {

		super.searchCount(
			companyId, groupId, folderIds, classNameId, keywords, version,
			ddmStructureKey, ddmTemplateKey, displayDateGT, displayDateLT,
			status, reviewDate);

		List<JournalArticle> journalArticles = _search(
			companyId, groupId, folderIds, classNameId, keywords, version,
			ddmStructureKey, ddmTemplateKey, displayDateGT, displayDateLT,
			status, reviewDate, null);

		return journalArticles.size();
	}

	private List<JournalArticle> _getArticlesByStructureId(
		long groupId, String ddmStructureKey, int status,
		OrderByComparator<JournalArticle> obc) {

		List<JournalArticle> journalArticles = super.getArticlesByStructureId(
			groupId, ddmStructureKey, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, obc);

		journalArticles.removeIf(
			journalArticle -> !_isRetrievable(journalArticle));

		return journalArticles;
	}

	private List<JournalArticle> _getGroupArticles(
			long groupId, long userId, long rootFolderId, int status,
			boolean includeOwner,
			OrderByComparator<JournalArticle> orderByComparator)
		throws PortalException {

		List<JournalArticle> journalArticles = super.getGroupArticles(
			groupId, userId, rootFolderId, status, includeOwner,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, orderByComparator);

		journalArticles.removeIf(
			journalArticle -> !_isRetrievable(journalArticle));

		return journalArticles;
	}

	private boolean _isRetrievable(JournalArticle journalArticle) {
		if (journalArticle == null) {
			return false;
		}

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
			_ctManager.getModelChangeCTEntryOptional(
				PrincipalThreadLocal.getUserId(),
				_portal.getClassNameId(JournalArticle.class.getName()),
				journalArticle.getId());

		return ctEntryOptional.isPresent();
	}

	private List<JournalArticle> _search(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String keywords, Double version, String ddmStructureKey,
		String ddmTemplateKey, Date displayDateGT, Date displayDateLT,
		int status, Date reviewDate, OrderByComparator<JournalArticle> obc) {

		List<JournalArticle> journalArticles = super.search(
			companyId, groupId, folderIds, classNameId, keywords, version,
			ddmStructureKey, ddmTemplateKey, displayDateGT, displayDateLT,
			status, reviewDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		journalArticles.removeIf(
			journalArticle -> _isRetrievable(journalArticle));

		return journalArticles;
	}

	@Reference
	private CTEngineManager _ctEngineManager;

	@Reference
	private CTManager _ctManager;

	@Reference
	private Portal _portal;

}