<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
SelectLayoutPageTemplateEntryDisplayContext selectLayoutPageTemplateEntryDisplayContext = new SelectLayoutPageTemplateEntryDisplayContext(layoutsAdminDisplayContext, request);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(String.valueOf(layoutsAdminDisplayContext.getPortletURL()));

renderResponse.setTitle(LanguageUtil.get(request, "select-template"));
%>

<clay:navigation-bar
	inverted="<%= true %>"
	items="<%= selectLayoutPageTemplateEntryDisplayContext.getNavigationItems() %>"
/>

<aui:form cssClass="container-fluid-1280" name="fm">
	<c:choose>
		<c:when test="<%= selectLayoutPageTemplateEntryDisplayContext.isBasicPages() %>">
			<liferay-util:include page="/select_basic_pages.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test="<%= selectLayoutPageTemplateEntryDisplayContext.isGlobalTemplates() %>">
			<liferay-util:include page="/select_global_templates.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:otherwise>
			<liferay-ui:search-container
				id="layoutPageTemplateEntries"
				total="<%= selectLayoutPageTemplateEntryDisplayContext.getLayoutPageTemplateEntriesCount() %>"
			>
				<liferay-ui:search-container-results
					results="<%= selectLayoutPageTemplateEntryDisplayContext.getLayoutPageTemplateEntries(searchContainer.getStart(), searchContainer.getEnd()) %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.layout.page.template.model.LayoutPageTemplateEntry"
					keyProperty="layoutPageTemplateEntryId"
					modelVar="layoutPageTemplateEntry"
				>

					<%
					row.setCssClass("entry-card lfr-asset-item " + row.getCssClass());
					%>

					<liferay-ui:search-container-column-text>

						<%
						Map<String, Object> addLayoutData = new HashMap<>();

						addLayoutData.put("layout-page-template-entry-id", layoutPageTemplateEntry.getLayoutPageTemplateEntryId());
						%>

						<liferay-frontend:icon-vertical-card
							actionJspServletContext="<%= application %>"
							cssClass='<%= renderResponse.getNamespace() + "add-layout-action-option" %>'
							data="<%= addLayoutData %>"
							icon="page"
							resultRow="<%= row %>"
							rowChecker="<%= searchContainer.getRowChecker() %>"
							title="<%= layoutPageTemplateEntry.getName() %>"
							url="javascript:;"
						>
							<liferay-frontend:vertical-card-header>
								<liferay-ui:message arguments="<%= LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - layoutPageTemplateEntry.getCreateDate().getTime(), true) %>" key="x-ago" translateArguments="<%= false %>" />
							</liferay-frontend:vertical-card-header>
						</liferay-frontend:icon-vertical-card>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator displayStyle="icon" markupView="lexicon" />
			</liferay-ui:search-container>

			<portlet:actionURL name="/layout/add_content_layout" var="addLayoutURL">
				<portlet:param name="mvcPath" value="/select_layout_page_template_entry.jsp" />
				<portlet:param name="groupId" value="<%= String.valueOf(layoutsAdminDisplayContext.getGroupId()) %>" />
				<portlet:param name="parentLayoutId" value="<%= String.valueOf(layoutsAdminDisplayContext.getParentLayoutId()) %>" />
				<portlet:param name="privateLayout" value="<%= String.valueOf(layoutsAdminDisplayContext.isPrivateLayout()) %>" />
				<portlet:param name="explicitCreation" value="<%= String.valueOf(true) %>" />
			</portlet:actionURL>

			<aui:script require="metal-dom/src/all/dom as dom,frontend-js-web/liferay/modal/commands/OpenSimpleInputModal.es as modalCommands">
				var addLayoutActionOptionQueryClickHandler = dom.delegate(
					document.body,
					'click',
					'.<portlet:namespace />add-layout-action-option',
					function(event) {
						var actionElement = event.delegateTarget;

						modalCommands.openSimpleInputModal(
							{
								dialogTitle: '<liferay-ui:message key="add-page" />',
								formSubmitURL: '<%= addLayoutURL %>',
								idFieldName: 'layoutPageTemplateEntryId',
								idFieldValue: actionElement.dataset.layoutPageTemplateEntryId,
								mainFieldName: 'name',
								mainFieldLabel: '<liferay-ui:message key="name" />',
								namespace: '<portlet:namespace />',
								spritemap: '<%= themeDisplay.getPathThemeImages() %>/lexicon/icons.svg'
							}
						);
					}
				);

				function handleDestroyPortlet () {
					addLayoutActionOptionQueryClickHandler.removeListener();

					Liferay.detach('destroyPortlet', handleDestroyPortlet);
				}

				Liferay.on('destroyPortlet', handleDestroyPortlet);
			</aui:script>
		</c:otherwise>
	</c:choose>
</aui:form>