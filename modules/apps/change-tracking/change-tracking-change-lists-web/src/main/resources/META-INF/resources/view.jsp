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
String title = LanguageUtil.get(request, "select-change-list");

portletDisplay.setTitle(title);
renderResponse.setTitle(title);

SearchContainer<CTCollection> ctCollectionSearchContainer = changeListsDisplayContext.getSearchContainer();
%>

<clay:management-toolbar
	clearResultsURL="<%= changeListsDisplayContext.getViewSearchActionURL() %>"
	creationMenu="<%= changeListsDisplayContext.getCreationMenu() %>"
	filterDropdownItems="<%= changeListsDisplayContext.getFilterDropdownItems() %>"
	itemsTotal="<%= ctCollectionSearchContainer.getTotal() %>"
	searchActionURL="<%= changeListsDisplayContext.getViewSearchActionURL() %>"
	searchContainerId="changeLists"
	selectable="<%= false %>"
	showCreationMenu="<%= true %>"
	showSearch="<%= true %>"
	sortingOrder="<%= changeListsDisplayContext.getOrderByType() %>"
	sortingURL="<%= changeListsDisplayContext.getSortingURL() %>"
	viewTypeItems="<%= changeListsDisplayContext.getViewTypeItems() %>"
/>

<div class="lfr-search-container-wrapper main-content-body container-fluid-1280">
	<c:choose>
		<c:when test='<%= Objects.equals(changeListsDisplayContext.getDisplayStyle(), "list") %>'>
			<liferay-ui:search-container
				id="changeLists"
				searchContainer="<%= ctCollectionSearchContainer %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.change.tracking.model.CTCollection"
					keyProperty="ctCollectionId"
					modelVar="curCTCollection"
				>
					<liferay-ui:search-container-column-text
						name="name"
					>
						<%= HtmlUtil.escape(curCTCollection.getName()) %>
					</liferay-ui:search-container-column-text>
		
					<liferay-ui:search-container-column-text
						name="modified-date"
					>
						<%= curCTCollection.getModifiedDate() %>
					</liferay-ui:search-container-column-text>
		
					<liferay-ui:search-container-column-text
						name="created-by"
					>
						<%= curCTCollection.getUserName() %>
					</liferay-ui:search-container-column-text>
		
					<liferay-ui:search-container-column-text
						name="description"
					>
						<%= HtmlUtil.escape(curCTCollection.getDescription()) %>
					</liferay-ui:search-container-column-text>
		
					<liferay-ui:search-container-column-text>
						<liferay-ui:icon-menu
							direction="left-side"
							icon="<%= StringPool.BLANK %>"
							markupView="lexicon"
							message="<%= StringPool.BLANK %>"
							showWhenSingleIcon="<%= true %>"
						>
							<liferay-portlet:renderURL var="editCollectionURL">
								<portlet:param name="mvcRenderCommandName" value="/change_lists/edit_ct_collection" />
								<portlet:param name="backURL" value="<%= themeDisplay.getURLCurrent() %>" />
								<portlet:param name="ctCollectionId" value="<%= String.valueOf(curCTCollection.getCtCollectionId()) %>" />
							</liferay-portlet:renderURL>
		
							<liferay-ui:icon
								message="edit"
								url="<%= editCollectionURL %>"
							/>
		
							<liferay-portlet:actionURL name="/change_lists/checkout_ct_collection" var="checkoutCollectionURL">
								<portlet:param name="ctCollectionId" value="<%= String.valueOf(curCTCollection.getCtCollectionId()) %>" />
							</liferay-portlet:actionURL>
		
							<liferay-ui:icon
								message="make-active"
								url="<%= checkoutCollectionURL %>"
							/>
		
							<liferay-portlet:actionURL name="/change_lists/publish_ct_collection" var="publishCollectionURL">
								<portlet:param name="ctCollectionId" value="<%= String.valueOf(curCTCollection.getCtCollectionId()) %>" />
							</liferay-portlet:actionURL>
		
							<liferay-ui:icon
								message="publish"
								url="<%= publishCollectionURL %>"
							/>
		
							<liferay-portlet:actionURL var="deleteCollectionURL">
								<portlet:param name="ctCollectionId" value="<%= String.valueOf(curCTCollection.getCtCollectionId()) %>" />
							</liferay-portlet:actionURL>
		
							<liferay-ui:icon
								message="delete"
								url="<%= deleteCollectionURL %>"
							/>
						</liferay-ui:icon-menu>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>
		
				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</c:when>
		<c:when test='<%= Objects.equals(changeListsDisplayContext.getDisplayStyle(), "icon") %>'>
			<liferay-ui:search-container
				id="changeLists"
				searchContainer="<%= ctCollectionSearchContainer %>"
			>
				<div class="row">
					<liferay-ui:search-container-row
						className="com.liferay.change.tracking.model.CTCollection"
						keyProperty="ctCollectionId"
						modelVar="curCTCollection"
					>

					<div class="col-sm-4">
						<div class="card select-card-sheet border-left-blue">
							<div class="card-row card-row-layout-fixed card-row-padded card-row-valign-top select-card-header">
								<div class="card-col-content lfr-card-details-column">
									<span class="card-h3" data-qa-id="headerSubTitle"><%= HtmlUtil.escape(curCTCollection.getName()) %></span>
									<div class="select-card-sheet-block">
										<span class="card-h4"><liferay-ui:message key="description" /></span>
										<div class="card-text" data-qa-id="description">
											<%= HtmlUtil.escape(curCTCollection.getDescription()) %>
										</div>
									</div>
									<div class="select-card-sheet-block">
										<span class="card-h4"><liferay-ui:message key="created-by" /> & <liferay-ui:message key="modified-date" /></span>
										<div class="card-text" data-qa-id="created-by-modified-date">
											<%= curCTCollection.getUserName() + " - " + curCTCollection.getModifiedDate() %>
										</div>
									</div>
									<div class="select-card-sheet-block">
										<span class="card-h4"><liferay-ui:message key="changes" /></span>
										<div class="changes-row">
											<div class="changes">
												<div class="big-number" data-qa-id="changesAdded">0</div>
												<div class=""><liferay-ui:message key="added" /></div>
											</div>
											<div class="changes">
												<div class="big-number" data-qa-id="changesModified">0</div>
												<div class=""><liferay-ui:message key="modified" /></div>
											</div>
											<div class="changes">
												<div class="big-number" data-qa-id="changesDeleted">0</div>
												<div class=""><liferay-ui:message key="deleted" /></div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-col-field lfr-card-actions-column">
									<liferay-ui:icon-menu
										direction="left-side"
										icon="<%= StringPool.BLANK %>"
										markupView="lexicon"
										message="<%= StringPool.BLANK %>"
										showWhenSingleIcon="<%= true %>"
									>
										<liferay-portlet:renderURL var="editCollectionURL">
											<portlet:param name="mvcRenderCommandName" value="/change_lists/edit_ct_collection" />
											<portlet:param name="backURL" value="<%= themeDisplay.getURLCurrent() %>" />
											<portlet:param name="ctCollectionId" value="<%= String.valueOf(curCTCollection.getCtCollectionId()) %>" />
										</liferay-portlet:renderURL>
					
										<liferay-ui:icon
											message="edit"
											url="<%= editCollectionURL %>"
										/>
					
										<liferay-portlet:actionURL name="/change_lists/publish_ct_collection" var="publishCollectionURL">
											<portlet:param name="ctCollectionId" value="<%= String.valueOf(curCTCollection.getCtCollectionId()) %>" />
										</liferay-portlet:actionURL>
					
										<liferay-ui:icon
											message="publish"
											url="<%= publishCollectionURL %>"
										/>
					
										<liferay-portlet:actionURL var="deleteCollectionURL">
											<portlet:param name="ctCollectionId" value="<%= String.valueOf(curCTCollection.getCtCollectionId()) %>" />
										</liferay-portlet:actionURL>
					
										<liferay-ui:icon
											message="delete"
											url="<%= deleteCollectionURL %>"
										/>
									</liferay-ui:icon-menu>
								</div>
							</div>
						</div>
					</div>

					</liferay-ui:search-container-row>
				</div>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</c:when>
	</c:choose>
</div>