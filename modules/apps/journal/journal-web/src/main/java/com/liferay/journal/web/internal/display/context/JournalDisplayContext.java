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

package com.liferay.journal.web.internal.display.context;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemList;
import com.liferay.journal.configuration.JournalServiceConfiguration;
import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.model.JournalFolderConstants;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalArticleServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.journal.service.JournalFolderServiceUtil;
import com.liferay.journal.util.JournalChangeTrackingHelper;
import com.liferay.journal.util.comparator.FolderArticleArticleIdComparator;
import com.liferay.journal.util.comparator.FolderArticleDisplayDateComparator;
import com.liferay.journal.util.comparator.FolderArticleModifiedDateComparator;
import com.liferay.journal.util.comparator.FolderArticleTitleComparator;
import com.liferay.journal.web.asset.JournalArticleAssetRenderer;
import com.liferay.journal.web.configuration.JournalWebConfiguration;
import com.liferay.journal.web.internal.portlet.action.ActionUtil;
import com.liferay.journal.web.internal.search.EntriesChecker;
import com.liferay.journal.web.internal.search.EntriesMover;
import com.liferay.journal.web.internal.search.JournalSearcher;
import com.liferay.journal.web.internal.servlet.taglib.util.JournalArticleActionDropdownItemsProvider;
import com.liferay.journal.web.internal.servlet.taglib.util.JournalFolderActionDropdownItems;
import com.liferay.journal.web.util.JournalPortletUtil;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.service.MBMessageLocalServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletRequestModel;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.trash.TrashHelper;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Eudaldo Alonso
 */
public class JournalDisplayContext {

	public JournalDisplayContext(
		HttpServletRequest request, LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		PortletPreferences portletPreferences, TrashHelper trashHelper) {

		_request = request;
		_liferayPortletRequest = liferayPortletRequest;
		_liferayPortletResponse = liferayPortletResponse;
		_portletPreferences = portletPreferences;
		_trashHelper = trashHelper;

		_journalWebConfiguration =
			(JournalWebConfiguration)_request.getAttribute(
				JournalWebConfiguration.class.getName());
		_portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(
			_request);
		_themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_journalChangeTrackingHelper = _serviceTracker.getService();
	}

	public String[] getAddMenuFavItems() throws PortalException {
		if (_addMenuFavItems != null) {
			return _addMenuFavItems;
		}

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(_request);

		String key = JournalPortletUtil.getAddMenuFavItemKey(
			_liferayPortletRequest, _liferayPortletResponse);

		List<String> addMenuFavItemsList = new ArrayList<>();

		String[] addMenuFavItems = portalPreferences.getValues(
			JournalPortletKeys.JOURNAL, key, new String[0]);

		for (DDMStructure ddmStructure : getDDMStructures()) {
			if (ArrayUtil.contains(
					addMenuFavItems, ddmStructure.getStructureKey())) {

				addMenuFavItemsList.add(ddmStructure.getStructureKey());
			}
		}

		_addMenuFavItems = ArrayUtil.toStringArray(addMenuFavItemsList);

		return _addMenuFavItems;
	}

	public int getAddMenuFavItemsLength() throws PortalException {
		String[] addMenuFavItems = getAddMenuFavItems();

		return addMenuFavItems.length;
	}

	public JournalArticle getArticle() throws PortalException {
		if (_article != null) {
			return _article;
		}

		_article = ActionUtil.getArticle(_request);

		return _article;
	}

	public List<DropdownItem> getArticleActionDropdownItems(
			JournalArticle article)
		throws Exception {

		JournalArticleActionDropdownItemsProvider
			articleActionDropdownItemsProvider =
				new JournalArticleActionDropdownItemsProvider(
					article, _liferayPortletRequest, _liferayPortletResponse,
					_trashHelper);

		return articleActionDropdownItemsProvider.getActionDropdownItems();
	}

	public JournalArticleDisplay getArticleDisplay() throws Exception {
		if (_articleDisplay != null) {
			return _articleDisplay;
		}

		long groupId = ParamUtil.getLong(_request, "groupId");
		String articleId = ParamUtil.getString(_request, "articleId");
		double version = ParamUtil.getDouble(_request, "version");

		JournalArticle article = JournalArticleLocalServiceUtil.fetchArticle(
			groupId, articleId, version);

		if (article == null) {
			return _articleDisplay;
		}

		int page = ParamUtil.getInteger(_request, "page");

		_articleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(
			article, null, null, _themeDisplay.getLanguageId(), page,
			new PortletRequestModel(
				_liferayPortletRequest, _liferayPortletResponse),
			_themeDisplay);

		return _articleDisplay;
	}

	public List<DropdownItem> getArticleHistoryActionDropdownItems(
			JournalArticle article)
		throws Exception {

		JournalArticleActionDropdownItemsProvider
			articleActionDropdownItemsProvider =
				new JournalArticleActionDropdownItemsProvider(
					article, _liferayPortletRequest, _liferayPortletResponse,
					_trashHelper);

		return articleActionDropdownItemsProvider.
			getArticleHistoryActionDropdownItems();
	}

	public List<DropdownItem> getArticleInfoPanelDropdownItems(
			JournalArticle article)
		throws Exception {

		return getArticleActionDropdownItems(article);
	}

	public List<DropdownItem> getArticleVersionActionDropdownItems(
			JournalArticle article)
		throws Exception {

		JournalArticleActionDropdownItemsProvider
			articleActionDropdownItemsProvider =
				new JournalArticleActionDropdownItemsProvider(
					article, _liferayPortletRequest, _liferayPortletResponse,
					_trashHelper);

		return articleActionDropdownItemsProvider.
			getArticleVersionActionDropdownItems();
	}

	public String[] getCharactersBlacklist() throws PortalException {
		JournalServiceConfiguration journalServiceConfiguration =
			ConfigurationProviderUtil.getCompanyConfiguration(
				JournalServiceConfiguration.class,
				_themeDisplay.getCompanyId());

		return journalServiceConfiguration.charactersblacklist();
	}

	public SearchContainer<MBMessage> getCommentsSearchContainer()
		throws PortalException {

		SearchContainer<MBMessage> searchContainer = new SearchContainer(
			_liferayPortletRequest, _liferayPortletResponse.createRenderURL(),
			null, null);

		SearchContext searchContext = SearchContextFactory.getInstance(
			_liferayPortletRequest.getHttpServletRequest());

		searchContext.setAttribute(
			Field.CLASS_NAME_ID,
			PortalUtil.getClassNameId(JournalArticle.class));

		searchContext.setAttribute("discussion", Boolean.TRUE);

		List<MBMessage> mbMessages = new ArrayList<>();

		Indexer indexer = IndexerRegistryUtil.getIndexer(MBMessage.class);

		Hits hits = indexer.search(searchContext);

		for (Document document : hits.getDocs()) {
			long entryClassPK = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			MBMessage mbMessage = MBMessageLocalServiceUtil.fetchMBMessage(
				entryClassPK);

			mbMessages.add(mbMessage);
		}

		searchContainer.setResults(mbMessages);

		searchContainer.setTotal(hits.getLength());

		return searchContainer;
	}

	public int getCommentsTotal() throws PortalException {
		SearchContainer<MBMessage> commentsSearchContainer =
			getCommentsSearchContainer();

		return commentsSearchContainer.getTotal();
	}

	public Map<String, Object> getComponentContext() throws Exception {
		Map<String, Object> componentContext = new HashMap<>();

		componentContext.put(
			"trashEnabled",
			_trashHelper.isTrashEnabled(_themeDisplay.getScopeGroupId()));

		return componentContext;
	}

	public String getDDMStructureKey() {
		if (_ddmStructureKey != null) {
			return _ddmStructureKey;
		}

		_ddmStructureKey = ParamUtil.getString(_request, "ddmStructureKey");

		return _ddmStructureKey;
	}

	public String getDDMStructureName() {
		if (_ddmStructureName != null) {
			return _ddmStructureName;
		}

		_ddmStructureName = LanguageUtil.get(_request, "basic-web-content");

		if (Validator.isNull(getDDMStructureKey())) {
			return _ddmStructureName;
		}

		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(
			_themeDisplay.getSiteGroupId(),
			PortalUtil.getClassNameId(JournalArticle.class),
			getDDMStructureKey(), true);

		if (ddmStructure != null) {
			_ddmStructureName = ddmStructure.getName(_themeDisplay.getLocale());
		}

		return _ddmStructureName;
	}

	public List<DDMStructure> getDDMStructures() throws PortalException {
		Integer restrictionType = getRestrictionType();

		return getDDMStructures(restrictionType);
	}

	public List<DDMStructure> getDDMStructures(Integer restrictionType)
		throws PortalException {

		if (_ddmStructures != null) {
			return _ddmStructures;
		}

		if (restrictionType == null) {
			restrictionType = getRestrictionType();
		}

		_ddmStructures = JournalFolderServiceUtil.getDDMStructures(
			PortalUtil.getCurrentAndAncestorSiteGroupIds(
				_themeDisplay.getScopeGroupId()),
			getFolderId(), restrictionType);

		Locale locale = _themeDisplay.getLocale();

		if (_journalWebConfiguration.journalBrowseByStructuresSortedByName()) {
			_ddmStructures.sort(
				(ddmStructure1, ddmStructure2) -> {
					String name1 = ddmStructure1.getName(locale);
					String name2 = ddmStructure2.getName(locale);

					return name1.compareTo(name2);
				});
		}

		return _ddmStructures;
	}

	public String getDDMTemplateKey() {
		if (_ddmTemplateKey != null) {
			return _ddmTemplateKey;
		}

		_ddmTemplateKey = ParamUtil.getString(_request, "ddmTemplateKey");

		return _ddmTemplateKey;
	}

	public String getDisplayStyle() {
		if (_displayStyle != null) {
			return _displayStyle;
		}

		String[] displayViews = getDisplayViews();

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(_request);

		_displayStyle = ParamUtil.getString(_request, "displayStyle");

		if (Validator.isNull(_displayStyle)) {
			_displayStyle = portalPreferences.getValue(
				JournalPortletKeys.JOURNAL, "display-style",
				_journalWebConfiguration.defaultDisplayView());
		}

		if (!ArrayUtil.contains(displayViews, _displayStyle)) {
			_displayStyle = displayViews[0];
		}

		return _displayStyle;
	}

	public String[] getDisplayViews() {
		if (_displayViews == null) {
			_displayViews = StringUtil.split(
				PrefsParamUtil.getString(
					_portletPreferences, _request, "displayViews",
					StringUtil.merge(_journalWebConfiguration.displayViews())));
		}

		return _displayViews;
	}

	public JournalFolder getFolder() {
		if (_folder != null) {
			return _folder;
		}

		_folder = (JournalFolder)_request.getAttribute(WebKeys.JOURNAL_FOLDER);

		if (_folder != null) {
			return _folder;
		}

		long folderId = ParamUtil.getLong(_request, "folderId");

		_folder = JournalFolderLocalServiceUtil.fetchFolder(folderId);

		return _folder;
	}

	public List<DropdownItem> getFolderActionDropdownItems(JournalFolder folder)
		throws Exception {

		JournalFolderActionDropdownItems folderActionDropdownItems =
			new JournalFolderActionDropdownItems(
				folder, _liferayPortletRequest, _liferayPortletResponse,
				_trashHelper);

		return folderActionDropdownItems.getActionDropdownItems();
	}

	public long getFolderId() {
		if (_folderId != null) {
			return _folderId;
		}

		JournalFolder folder = getFolder();

		_folderId = BeanParamUtil.getLong(
			folder, _request, "folderId",
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		return _folderId;
	}

	public List<DropdownItem> getFolderInfoPanelDropdownItems(
			JournalFolder folder)
		throws Exception {

		JournalFolderActionDropdownItems folderActionDropdownItems =
			new JournalFolderActionDropdownItems(
				folder, _liferayPortletRequest, _liferayPortletResponse,
				_trashHelper);

		return folderActionDropdownItems.getInfoPanelActionDropdownItems();
	}

	public JSONArray getFoldersJSONArray() {
		JSONArray jsonArray = _getFoldersJSONArray(
			_themeDisplay.getScopeGroupId(),
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("children", jsonArray);
		jsonObject.put("icon", "folder");
		jsonObject.put("id", JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);
		jsonObject.put(
			"name", LanguageUtil.get(_themeDisplay.getLocale(), "home"));

		JSONArray rootJSONArray = JSONFactoryUtil.createJSONArray();

		rootJSONArray.put(jsonObject);

		return rootJSONArray;
	}

	public String getFolderTitle() {
		JournalFolder folder = getFolder();

		if (folder != null) {
			return folder.getName();
		}

		return StringPool.BLANK;
	}

	public List<NavigationItem> getInfoPanelNavigationItems() {
		return new NavigationItemList() {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(true);
						navigationItem.setHref(StringPool.BLANK);
						navigationItem.setLabel(
							LanguageUtil.get(_request, "details"));
					});
			}
		};
	}

	public String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_request, "keywords");

		return _keywords;
	}

	public String getNavigation() {
		if (_navigation != null) {
			return _navigation;
		}

		_navigation = ParamUtil.getString(_request, "navigation", "all");

		return _navigation;
	}

	public List<NavigationItem> getNavigationBarItems(String currentItem) {
		return new NavigationItemList() {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(
							currentItem.equals("web-content"));
						navigationItem.setHref(
							_liferayPortletResponse.createRenderURL());
						navigationItem.setLabel(
							LanguageUtil.get(_request, "web-content"));
					});

				Group group = _themeDisplay.getScopeGroup();

				if (!group.isLayout()) {
					add(
						navigationItem -> {
							navigationItem.setActive(
								currentItem.equals("structures"));
							navigationItem.setHref(
								_liferayPortletResponse.createRenderURL(),
								"mvcPath", "/view_ddm_structures.jsp");
							navigationItem.setLabel(
								LanguageUtil.get(_request, "structures"));
						});

					add(
						navigationItem -> {
							navigationItem.setActive(
								currentItem.equals("templates"));
							navigationItem.setHref(
								_liferayPortletResponse.createRenderURL(),
								"mvcPath", "/view_ddm_templates.jsp");
							navigationItem.setLabel(
								LanguageUtil.get(_request, "templates"));
						});
				}

				if (_journalWebConfiguration.showFeeds() &&
					PortalUtil.isRSSFeedsEnabled()) {

					add(
						navigationItem -> {
							navigationItem.setActive(
								currentItem.equals("feeds"));
							navigationItem.setHref(_getFeedsURL());
							navigationItem.setLabel(
								LanguageUtil.get(_request, "feeds"));
						});
				}
			}
		};
	}

	public String getOrderByCol() {
		if (_orderByCol != null) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(_request, "orderByCol");

		if (Validator.isNull(_orderByCol)) {
			_orderByCol = _portalPreferences.getValue(
				JournalPortletKeys.JOURNAL, "order-by-col", "modified-date");
		}
		else {
			boolean saveOrderBy = ParamUtil.getBoolean(_request, "saveOrderBy");

			if (saveOrderBy) {
				_portalPreferences.setValue(
					JournalPortletKeys.JOURNAL, "order-by-col", _orderByCol);
			}
		}

		return _orderByCol;
	}

	public String getOrderByType() {
		if (_orderByType != null) {
			return _orderByType;
		}

		if (isNavigationRecent()) {
			return "desc";
		}

		_orderByType = ParamUtil.getString(_request, "orderByType");

		if (Validator.isNull(_orderByType)) {
			_orderByType = _portalPreferences.getValue(
				JournalPortletKeys.JOURNAL, "order-by-type", "asc");
		}
		else {
			boolean saveOrderBy = ParamUtil.getBoolean(_request, "saveOrderBy");

			if (saveOrderBy) {
				_portalPreferences.setValue(
					JournalPortletKeys.JOURNAL, "order-by-type", _orderByType);
			}
		}

		return _orderByType;
	}

	public String[] getOrderColumns() {
		String[] orderColumns = {"display-date", "modified-date", "title"};

		if (!_journalWebConfiguration.journalArticleForceAutogenerateId()) {
			orderColumns = ArrayUtil.append(orderColumns, "id");
		}

		return orderColumns;
	}

	public String getOriginalAuthor(JournalArticle article) {
		long classPK = JournalArticleAssetRenderer.getClassPK(article);

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
			JournalArticle.class.getName(), classPK);

		if (assetEntry != null) {
			return assetEntry.getUserName();
		}

		return article.getUserName();
	}

	public long getParentFolderId() {
		if (_parentFolderId != null) {
			return _parentFolderId;
		}

		_parentFolderId = ParamUtil.getLong(
			_request, "parentFolderId",
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		return _parentFolderId;
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = _liferayPortletResponse.createRenderURL();

		String navigation = ParamUtil.getString(_request, "navigation");

		if (Validator.isNotNull(navigation)) {
			portletURL.setParameter(
				"navigation", HtmlUtil.escapeJS(getNavigation()));
		}

		portletURL.setParameter("folderId", String.valueOf(getFolderId()));

		if (isNavigationStructure()) {
			portletURL.setParameter("ddmStructureKey", getDDMStructureKey());
		}

		String status = ParamUtil.getString(_request, "status");

		if (Validator.isNotNull(status)) {
			portletURL.setParameter("status", String.valueOf(getStatus()));
		}

		String delta = ParamUtil.getString(_request, "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		String deltaEntry = ParamUtil.getString(_request, "deltaEntry");

		if (Validator.isNotNull(deltaEntry)) {
			portletURL.setParameter("deltaEntry", deltaEntry);
		}

		String displayStyle = ParamUtil.getString(_request, "displayStyle");

		if (Validator.isNotNull(displayStyle)) {
			portletURL.setParameter("displayStyle", getDisplayStyle());
		}

		String keywords = ParamUtil.getString(_request, "keywords");

		if (Validator.isNotNull(keywords)) {
			portletURL.setParameter("keywords", keywords);
		}

		String orderByCol = getOrderByCol();

		if (Validator.isNotNull(orderByCol)) {
			portletURL.setParameter("orderByCol", orderByCol);
		}

		String orderByType = getOrderByType();

		if (Validator.isNotNull(orderByType)) {
			portletURL.setParameter("orderByType", orderByType);
		}

		String tabs1 = getTabs1();

		if (Validator.isNotNull(tabs1)) {
			portletURL.setParameter("tabs1", tabs1);
		}

		return portletURL;
	}

	public int getRestrictionType() {
		if (_restrictionType != null) {
			return _restrictionType;
		}

		JournalFolder folder = getFolder();

		if (folder != null) {
			_restrictionType = folder.getRestrictionType();
		}
		else {
			_restrictionType = JournalFolderConstants.RESTRICTION_TYPE_INHERIT;
		}

		return _restrictionType;
	}

	public SearchContainer getSearchContainer(boolean showVersions)
		throws PortalException {

		if (_articleSearchContainer != null) {
			return _articleSearchContainer;
		}

		SearchContainer articleSearchContainer = new SearchContainer(
			_liferayPortletRequest, getPortletURL(), null, null);

		OrderByComparator<JournalArticle> orderByComparator =
			JournalPortletUtil.getArticleOrderByComparator(
				getOrderByCol(), getOrderByType());

		articleSearchContainer.setOrderByCol(getOrderByCol());
		articleSearchContainer.setOrderByComparator(orderByComparator);
		articleSearchContainer.setOrderByType(getOrderByType());

		if (!showVersions) {
			EntriesChecker entriesChecker = new EntriesChecker(
				_liferayPortletRequest, _liferayPortletResponse);

			entriesChecker.setCssClass("entry-selector");
			entriesChecker.setRememberCheckBoxStateURLRegex(
				StringBundler.concat(
					"^(?!.*", _liferayPortletResponse.getNamespace(),
					"redirect).*(folderId=", getFolderId(), ")"));

			articleSearchContainer.setRowChecker(entriesChecker);

			EntriesMover entriesMover = new EntriesMover(
				_trashHelper.isTrashEnabled(_themeDisplay.getScopeGroupId()));

			articleSearchContainer.setRowMover(entriesMover);
		}

		if (isNavigationMine() || isNavigationRecent()) {
			boolean includeOwner = true;

			if (isNavigationMine()) {
				includeOwner = false;
			}

			if (isNavigationRecent()) {
				articleSearchContainer.setOrderByCol("modified-date");
				articleSearchContainer.setOrderByType(getOrderByType());
			}

			int total = JournalArticleServiceUtil.getGroupArticlesCount(
				_themeDisplay.getScopeGroupId(), _themeDisplay.getUserId(),
				getFolderId(), getStatus(), includeOwner);

			articleSearchContainer.setTotal(total);

			List results = JournalArticleServiceUtil.getGroupArticles(
				_themeDisplay.getScopeGroupId(), _themeDisplay.getUserId(),
				getFolderId(), getStatus(), includeOwner,
				articleSearchContainer.getStart(),
				articleSearchContainer.getEnd(),
				articleSearchContainer.getOrderByComparator());

			articleSearchContainer.setResults(results);
		}
		else if (Validator.isNotNull(getDDMStructureKey())) {
			int total = JournalArticleServiceUtil.getArticlesCountByStructureId(
				_themeDisplay.getScopeGroupId(), getDDMStructureKey(),
				getStatus());

			articleSearchContainer.setTotal(total);

			List results = JournalArticleServiceUtil.getArticlesByStructureId(
				_themeDisplay.getScopeGroupId(), getDDMStructureKey(),
				getStatus(), articleSearchContainer.getStart(),
				articleSearchContainer.getEnd(),
				articleSearchContainer.getOrderByComparator());

			articleSearchContainer.setResults(results);
		}
		else if (Validator.isNotNull(getDDMTemplateKey())) {
			List<Long> folderIds = new ArrayList<>(1);

			if (getFolderId() !=
					JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

				folderIds.add(getFolderId());
			}

			int total = JournalArticleServiceUtil.searchCount(
				_themeDisplay.getCompanyId(), _themeDisplay.getScopeGroupId(),
				folderIds, JournalArticleConstants.CLASSNAME_ID_DEFAULT,
				getKeywords(), -1.0, getDDMStructureKey(), getDDMTemplateKey(),
				null, null, getStatus(), null);

			articleSearchContainer.setTotal(total);

			List results = JournalArticleServiceUtil.search(
				_themeDisplay.getCompanyId(), _themeDisplay.getScopeGroupId(),
				folderIds, JournalArticleConstants.CLASSNAME_ID_DEFAULT,
				getKeywords(), -1.0, getDDMStructureKey(), getDDMTemplateKey(),
				null, null, getStatus(), null,
				articleSearchContainer.getStart(),
				articleSearchContainer.getEnd(),
				articleSearchContainer.getOrderByComparator());

			articleSearchContainer.setResults(results);
		}
		else if (isSearch()) {
			List<Long> folderIds = new ArrayList<>(1);

			if (getFolderId() !=
					JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

				folderIds.add(getFolderId());
			}

			boolean orderByAsc = false;

			if (Objects.equals(getOrderByType(), "asc")) {
				orderByAsc = true;
			}

			Sort sort = null;

			if (Objects.equals(getOrderByCol(), "display-date")) {
				sort = new Sort("displayDate", Sort.LONG_TYPE, orderByAsc);
			}
			else if (Objects.equals(getOrderByCol(), "id")) {
				sort = new Sort(
					Field.getSortableFieldName(Field.ARTICLE_ID),
					Sort.STRING_TYPE, !orderByAsc);
			}
			else if (Objects.equals(getOrderByCol(), "modified-date")) {
				sort = new Sort(
					Field.MODIFIED_DATE, Sort.LONG_TYPE, orderByAsc);
			}
			else if (Objects.equals(getOrderByCol(), "title")) {
				sort = new Sort("title", Sort.STRING_TYPE, !orderByAsc);
			}

			Indexer indexer = null;

			if (!showVersions) {
				indexer = JournalSearcher.getInstance();
			}
			else {
				indexer = IndexerRegistryUtil.getIndexer(JournalArticle.class);
			}

			SearchContext searchContext = buildSearchContext(
				folderIds, articleSearchContainer.getStart(),
				articleSearchContainer.getEnd(), sort, showVersions);

			Hits hits = indexer.search(searchContext);

			int total = hits.getLength();

			articleSearchContainer.setTotal(total);

			List results = new ArrayList<>();

			Document[] documents = hits.getDocs();

			for (Document document : documents) {
				String className = document.get(Field.ENTRY_CLASS_NAME);
				long classPK = GetterUtil.getLong(
					document.get(Field.ENTRY_CLASS_PK));

				if (className.equals(JournalArticle.class.getName())) {
					JournalArticle article = null;

					if (!showVersions) {
						article =
							JournalArticleLocalServiceUtil.fetchLatestArticle(
								classPK, WorkflowConstants.STATUS_ANY, false);
					}
					else {
						String articleId = document.get(Field.ARTICLE_ID);
						long groupId = GetterUtil.getLong(
							document.get(Field.GROUP_ID));
						double version = GetterUtil.getDouble(
							document.get(Field.VERSION));

						article = JournalArticleLocalServiceUtil.fetchArticle(
							groupId, articleId, version);
					}

					results.add(article);
				}
				else if (className.equals(JournalFolder.class.getName())) {
					JournalFolder folder =
						JournalFolderLocalServiceUtil.getFolder(classPK);

					results.add(folder);
				}
			}

			articleSearchContainer.setResults(results);
		}
		else {
			int total = JournalFolderServiceUtil.getFoldersAndArticlesCount(
				_themeDisplay.getScopeGroupId(), 0, getFolderId(), getStatus());

			articleSearchContainer.setTotal(total);

			OrderByComparator<Object> folderOrderByComparator = null;

			boolean orderByAsc = false;

			if (Objects.equals(getOrderByType(), "asc")) {
				orderByAsc = true;
			}

			if (Objects.equals(getOrderByCol(), "display-date")) {
				folderOrderByComparator =
					new FolderArticleDisplayDateComparator(orderByAsc);
			}
			else if (Objects.equals(getOrderByCol(), "id")) {
				folderOrderByComparator = new FolderArticleArticleIdComparator(
					orderByAsc);
			}
			else if (Objects.equals(getOrderByCol(), "modified-date")) {
				folderOrderByComparator =
					new FolderArticleModifiedDateComparator(orderByAsc);
			}
			else if (Objects.equals(getOrderByCol(), "title")) {
				folderOrderByComparator = new FolderArticleTitleComparator(
					orderByAsc);
			}

			List results = JournalFolderServiceUtil.getFoldersAndArticles(
				_themeDisplay.getScopeGroupId(), 0, getFolderId(), getStatus(),
				_themeDisplay.getLocale(), articleSearchContainer.getStart(),
				articleSearchContainer.getEnd(), folderOrderByComparator);

			articleSearchContainer.setResults(results);
		}

		_articleSearchContainer = articleSearchContainer;

		return _articleSearchContainer;
	}

	public int getStatus() {
		if (_status != null) {
			return _status;
		}

		int defaultStatus = WorkflowConstants.STATUS_APPROVED;

		PermissionChecker permissionChecker =
			_themeDisplay.getPermissionChecker();

		if (permissionChecker.isContentReviewer(
				_themeDisplay.getCompanyId(),
				_themeDisplay.getScopeGroupId()) ||
			isNavigationMine()) {

			defaultStatus = WorkflowConstants.STATUS_ANY;
		}

		_status = ParamUtil.getInteger(_request, "status", defaultStatus);

		return _status;
	}

	public String getTabs1() {
		if (_tabs1 != null) {
			return _tabs1;
		}

		_tabs1 = ParamUtil.getString(_request, "tabs1");

		return _tabs1;
	}

	public int getTotalItems() throws PortalException {
		SearchContainer articleSearch = getSearchContainer(false);

		return articleSearch.getTotal();
	}

	public int getVersionsTotal() throws PortalException {
		SearchContainer articleSearch = getSearchContainer(true);

		return articleSearch.getTotal();
	}

	public boolean hasCommentsResults() throws PortalException {
		if (getCommentsTotal() > 0) {
			return true;
		}

		return false;
	}

	public boolean hasResults() throws PortalException {
		if (getTotalItems() > 0) {
			return true;
		}

		return false;
	}

	public boolean hasVersionsResults() throws PortalException {
		if (getVersionsTotal() > 0) {
			return true;
		}

		return false;
	}

	public boolean isChangeListVisible() {
		if (_journalChangeTrackingHelper == null) {
			return false;
		}

		return _journalChangeTrackingHelper.hasActiveCTCollection(
			_themeDisplay.getCompanyId(), _themeDisplay.getUserId());
	}

	public boolean isJournalArticleInChangeList(JournalArticle journalArticle) {
		if (_journalChangeTrackingHelper == null) {
			return false;
		}

		return _journalChangeTrackingHelper.isJournalArticleInChangeList(
			_themeDisplay.getUserId(), journalArticle.getId());
	}

	public boolean isNavigationHome() {
		if (Objects.equals(getNavigation(), "all")) {
			return true;
		}

		return false;
	}

	public boolean isNavigationMine() {
		if (Objects.equals(getNavigation(), "mine")) {
			return true;
		}

		return false;
	}

	public boolean isNavigationRecent() {
		if (Objects.equals(getNavigation(), "recent")) {
			return true;
		}

		return false;
	}

	public boolean isNavigationStructure() {
		if (Objects.equals(getNavigation(), "structure")) {
			return true;
		}

		return false;
	}

	public boolean isSearch() {
		if (Validator.isNotNull(getKeywords())) {
			return true;
		}

		return false;
	}

	public boolean isShowInfoButton() {
		if (Validator.isNotNull(getDDMStructureKey())) {
			return false;
		}

		if (isNavigationMine()) {
			return false;
		}

		if (isNavigationRecent()) {
			return false;
		}

		if (isSearch()) {
			return false;
		}

		return true;
	}

	protected SearchContext buildSearchContext(
		List<Long> folderIds, int start, int end, Sort sort,
		boolean showVersions) {

		SearchContext searchContext = new SearchContext();

		searchContext.setAndSearch(false);

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.ARTICLE_ID, getKeywords());
		attributes.put(
			Field.CLASS_NAME_ID, JournalArticleConstants.CLASSNAME_ID_DEFAULT);
		attributes.put(Field.CONTENT, getKeywords());
		attributes.put(Field.DESCRIPTION, getKeywords());
		attributes.put(Field.STATUS, getStatus());
		attributes.put(Field.TITLE, getKeywords());
		attributes.put("ddmStructureKey", getDDMStructureKey());
		attributes.put("ddmTemplateKey", getDDMTemplateKey());

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("expandoAttributes", getKeywords());
		params.put("keywords", getKeywords());

		attributes.put("params", params);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(_themeDisplay.getCompanyId());
		searchContext.setEnd(end);
		searchContext.setFolderIds(folderIds);
		searchContext.setGroupIds(new long[] {_themeDisplay.getScopeGroupId()});
		searchContext.setKeywords(getKeywords());
		searchContext.setAttribute("head", !showVersions);
		searchContext.setAttribute("latest", !showVersions);
		searchContext.setAttribute("params", params);

		if (!showVersions) {
			searchContext.setAttribute("showNonindexable", Boolean.TRUE);
		}

		searchContext.setEnd(end);
		searchContext.setFolderIds(folderIds);
		searchContext.setStart(start);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		if (sort != null) {
			searchContext.setSorts(sort);
		}

		searchContext.setStart(start);

		return searchContext;
	}

	private String _getFeedsURL() {
		PortletURL portletURL = _liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcPath", "/view_feeds.jsp");
		portletURL.setParameter("redirect", _themeDisplay.getURLCurrent());

		return portletURL.toString();
	}

	private JSONArray _getFoldersJSONArray(long groupId, long folderId) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<JournalFolder> folders = JournalFolderLocalServiceUtil.getFolders(
			groupId, folderId);

		for (JournalFolder folder : folders) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			JSONArray childrenJSONArray = _getFoldersJSONArray(
				groupId, folder.getFolderId());

			if (childrenJSONArray.length() > 0) {
				jsonObject.put("children", childrenJSONArray);
			}

			jsonObject.put("icon", "folder");
			jsonObject.put("id", folder.getFolderId());
			jsonObject.put("name", folder.getName());

			if (folder.getFolderId() == getParentFolderId()) {
				jsonObject.put("selected", true);
			}

			if (folder.getFolderId() == getFolderId()) {
				jsonObject.put("disabled", true);
			}

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	private static ServiceTracker
		<JournalChangeTrackingHelper, JournalChangeTrackingHelper>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			JournalChangeTrackingHelper.class);

		ServiceTracker<JournalChangeTrackingHelper, JournalChangeTrackingHelper>
			serviceTracker = new ServiceTracker<>(
				bundle.getBundleContext(), JournalChangeTrackingHelper.class,
				null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

	private String[] _addMenuFavItems;
	private JournalArticle _article;
	private JournalArticleDisplay _articleDisplay;
	private SearchContainer _articleSearchContainer;
	private String _ddmStructureKey;
	private String _ddmStructureName;
	private List<DDMStructure> _ddmStructures;
	private String _ddmTemplateKey;
	private String _displayStyle;
	private String[] _displayViews;
	private JournalFolder _folder;
	private Long _folderId;
	private final JournalChangeTrackingHelper _journalChangeTrackingHelper;
	private final JournalWebConfiguration _journalWebConfiguration;
	private String _keywords;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private String _navigation;
	private String _orderByCol;
	private String _orderByType;
	private Long _parentFolderId;
	private final PortalPreferences _portalPreferences;
	private final PortletPreferences _portletPreferences;
	private final HttpServletRequest _request;
	private Integer _restrictionType;
	private Integer _status;
	private String _tabs1;
	private final ThemeDisplay _themeDisplay;
	private final TrashHelper _trashHelper;

}