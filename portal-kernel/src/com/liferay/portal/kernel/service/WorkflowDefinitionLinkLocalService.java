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

package com.liferay.portal.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for WorkflowDefinitionLink. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see WorkflowDefinitionLinkLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface WorkflowDefinitionLinkLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WorkflowDefinitionLinkLocalServiceUtil} to access the workflow definition link local service. Add custom service methods to <code>com.liferay.portal.service.impl.WorkflowDefinitionLinkLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public WorkflowDefinitionLink addWorkflowDefinitionLink(long userId,
		long companyId, long groupId, String className, long classPK,
		long typePK, String workflowDefinitionName,
		int workflowDefinitionVersion) throws PortalException;

	/**
	* Adds the workflow definition link to the database. Also notifies the appropriate model listeners.
	*
	* @param workflowDefinitionLink the workflow definition link
	* @return the workflow definition link that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public WorkflowDefinitionLink addWorkflowDefinitionLink(
		WorkflowDefinitionLink workflowDefinitionLink);

	/**
	* Creates a new workflow definition link with the primary key. Does not add the workflow definition link to the database.
	*
	* @param workflowDefinitionLinkId the primary key for the new workflow definition link
	* @return the new workflow definition link
	*/
	@Transactional(enabled = false)
	public WorkflowDefinitionLink createWorkflowDefinitionLink(
		long workflowDefinitionLinkId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the workflow definition link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowDefinitionLinkId the primary key of the workflow definition link
	* @return the workflow definition link that was removed
	* @throws PortalException if a workflow definition link with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public WorkflowDefinitionLink deleteWorkflowDefinitionLink(
		long workflowDefinitionLinkId) throws PortalException;

	public void deleteWorkflowDefinitionLink(long companyId, long groupId,
		String className, long classPK, long typePK);

	/**
	* Deletes the workflow definition link from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowDefinitionLink the workflow definition link
	* @return the workflow definition link that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public WorkflowDefinitionLink deleteWorkflowDefinitionLink(
		WorkflowDefinitionLink workflowDefinitionLink);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.WorkflowDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.WorkflowDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WorkflowDefinitionLink fetchDefaultWorkflowDefinitionLink(
		long companyId, String className, long classPK, long typePK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WorkflowDefinitionLink fetchWorkflowDefinitionLink(
		long workflowDefinitionLinkId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WorkflowDefinitionLink fetchWorkflowDefinitionLink(long companyId,
		long groupId, String className, long classPK, long typePK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WorkflowDefinitionLink fetchWorkflowDefinitionLink(long companyId,
		long groupId, String className, long classPK, long typePK,
		boolean strict);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WorkflowDefinitionLink> fetchWorkflowDefinitionLinks(
		long companyId, long groupId, String className, long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WorkflowDefinitionLink getDefaultWorkflowDefinitionLink(
		long companyId, String className, long classPK, long typePK)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the workflow definition link with the primary key.
	*
	* @param workflowDefinitionLinkId the primary key of the workflow definition link
	* @return the workflow definition link
	* @throws PortalException if a workflow definition link with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WorkflowDefinitionLink getWorkflowDefinitionLink(
		long workflowDefinitionLinkId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WorkflowDefinitionLink getWorkflowDefinitionLink(long companyId,
		long groupId, String className, long classPK, long typePK)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WorkflowDefinitionLink getWorkflowDefinitionLink(long companyId,
		long groupId, String className, long classPK, long typePK,
		boolean strict) throws PortalException;

	/**
	* Returns a range of all the workflow definition links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.WorkflowDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow definition links
	* @param end the upper bound of the range of workflow definition links (not inclusive)
	* @return the range of workflow definition links
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WorkflowDefinitionLink> getWorkflowDefinitionLinks(int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WorkflowDefinitionLink> getWorkflowDefinitionLinks(
		long companyId, long groupId, String className, long classPK)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WorkflowDefinitionLink> getWorkflowDefinitionLinks(
		long companyId, String workflowDefinitionName,
		int workflowDefinitionVersion) throws PortalException;

	/**
	* Returns the number of workflow definition links.
	*
	* @return the number of workflow definition links
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWorkflowDefinitionLinksCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWorkflowDefinitionLinksCount(long companyId, long groupId,
		String className);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWorkflowDefinitionLinksCount(long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion);

	@Transactional(enabled = false)
	public boolean hasWorkflowDefinitionLink(long companyId, long groupId,
		String className);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasWorkflowDefinitionLink(long companyId, long groupId,
		String className, long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasWorkflowDefinitionLink(long companyId, long groupId,
		String className, long classPK, long typePK);

	public void updateWorkflowDefinitionLink(long userId, long companyId,
		long groupId, String className, long classPK, long typePK,
		String workflowDefinition) throws PortalException;

	public WorkflowDefinitionLink updateWorkflowDefinitionLink(long userId,
		long companyId, long groupId, String className, long classPK,
		long typePK, String workflowDefinitionName,
		int workflowDefinitionVersion) throws PortalException;

	/**
	* Updates the workflow definition link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workflowDefinitionLink the workflow definition link
	* @return the workflow definition link that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public WorkflowDefinitionLink updateWorkflowDefinitionLink(
		WorkflowDefinitionLink workflowDefinitionLink);

	public void updateWorkflowDefinitionLinks(long userId, long companyId,
		long groupId, String className, long classPK,
		List<ObjectValuePair<Long, String>> workflowDefinitionOVPs)
		throws PortalException;
}