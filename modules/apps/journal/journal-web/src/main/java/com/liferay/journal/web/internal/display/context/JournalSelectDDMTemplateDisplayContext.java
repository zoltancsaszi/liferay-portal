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

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateServiceUtil;
import com.liferay.dynamic.data.mapping.util.DDMUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class JournalSelectDDMTemplateDisplayContext {

	public JournalSelectDDMTemplateDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_request = PortalUtil.getHttpServletRequest(renderRequest);
	}

	public long getDDMStructureId() {
		if (_ddmStructureId != null) {
			return _ddmStructureId;
		}

		_ddmStructureId = ParamUtil.getLong(_renderRequest, "ddmStructureId");

		return _ddmStructureId;
	}

	public long getDDMTemplateId() {
		if (_ddmTemplateId != null) {
			return _ddmTemplateId;
		}

		_ddmTemplateId = ParamUtil.getLong(_request, "ddmTemplateId");

		return _ddmTemplateId;
	}

	public String getEventName() {
		return _renderResponse.getNamespace() + "selectDDMTemplate";
	}

	public String getOrderByCol() {
		if (_orderByCol != null) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(
			_renderRequest, "orderByCol", "modified-date");

		return _orderByCol;
	}

	public String getOrderByType() {
		if (_orderByType != null) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(
			_renderRequest, "orderByType", "asc");

		return _orderByType;
	}

	public SearchContainer getTemplateSearch() throws Exception {
		if (_templateSearch != null) {
			return _templateSearch;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		SearchContainer templateSearch = new SearchContainer(
			_renderRequest, _getPortletURL(), null, "there-are-no-templates");

		if (templateSearch.isSearch()) {
			templateSearch.setEmptyResultsMessage("no-templates-were-found");
		}

		String orderByCol = getOrderByCol();
		String orderByType = getOrderByType();

		OrderByComparator<DDMTemplate> orderByComparator =
			DDMUtil.getTemplateOrderByComparator(
				getOrderByCol(), getOrderByType());

		templateSearch.setOrderByCol(orderByCol);
		templateSearch.setOrderByComparator(orderByComparator);
		templateSearch.setOrderByType(orderByType);

		long[] groupIds = PortalUtil.getCurrentAndAncestorSiteGroupIds(
			themeDisplay.getScopeGroupId());

		List<DDMTemplate> results = DDMTemplateServiceUtil.search(
			themeDisplay.getCompanyId(), groupIds,
			new long[] {PortalUtil.getClassNameId(DDMStructure.class)},
			new long[] {getDDMStructureId()},
			PortalUtil.getClassNameId(JournalArticle.class.getName()),
			_getKeywords(), StringPool.BLANK, StringPool.BLANK,
			WorkflowConstants.STATUS_ANY, templateSearch.getStart(),
			templateSearch.getEnd(), templateSearch.getOrderByComparator());

		templateSearch.setResults(results);

		if (ListUtil.isNotEmpty(results)) {
			templateSearch.setTotal(results.size());
		}
		else {
			templateSearch.setTotal(0);
		}

		_templateSearch = templateSearch;

		return _templateSearch;
	}

	public boolean isSearch() {
		if (Validator.isNotNull(_getKeywords())) {
			return true;
		}

		return false;
	}

	private String _getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_renderRequest, "keywords");

		return _keywords;
	}

	private PortletURL _getPortletURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter("mvcPath", "/select_ddm_template.jsp");

		long ddmTemplateId = getDDMTemplateId();

		if (ddmTemplateId != 0) {
			portletURL.setParameter(
				"ddmTemplateId", String.valueOf(ddmTemplateId));
		}

		long ddmStructureId = getDDMStructureId();

		if (ddmStructureId != 0) {
			portletURL.setParameter(
				"ddmStructureId", String.valueOf(ddmStructureId));
		}

		String keywords = _getKeywords();

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

		return portletURL;
	}

	private Long _ddmStructureId;
	private Long _ddmTemplateId;
	private String _keywords;
	private String _orderByCol;
	private String _orderByType;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final HttpServletRequest _request;
	private SearchContainer _templateSearch;

}