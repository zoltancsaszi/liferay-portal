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
import com.liferay.portal.kernel.model.PermissionedModel;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.ResourceBlock;
import com.liferay.portal.kernel.model.ResourceBlockPermissionsContainer;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.security.permission.ResourceBlockIdsBag;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for ResourceBlock. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ResourceBlockLocalServiceUtil
 * @deprecated As of Judson (7.1.x), with no direct replacement
 * @generated
 */
@Deprecated
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ResourceBlockLocalService extends BaseLocalService,
	PermissionedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ResourceBlockLocalServiceUtil} to access the resource block local service. Add custom service methods to <code>com.liferay.portal.service.impl.ResourceBlockLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public void addCompanyScopePermission(long companyId, String name,
		long roleId, String actionId) throws PortalException;

	public void addCompanyScopePermissions(long companyId, String name,
		long roleId, long actionIdsLong);

	public void addGroupScopePermission(long companyId, long groupId,
		String name, long roleId, String actionId) throws PortalException;

	public void addGroupScopePermissions(long companyId, long groupId,
		String name, long roleId, long actionIdsLong);

	public void addIndividualScopePermission(long companyId, long groupId,
		String name, long primKey, long roleId, String actionId)
		throws PortalException;

	public void addIndividualScopePermission(long companyId, long groupId,
		String name, PermissionedModel permissionedModel, long roleId,
		String actionId) throws PortalException;

	public void addIndividualScopePermissions(long companyId, long groupId,
		String name, long primKey, long roleId, long actionIdsLong)
		throws PortalException;

	public void addIndividualScopePermissions(long companyId, long groupId,
		String name, PermissionedModel permissionedModel, long roleId,
		long actionIdsLong);

	/**
	* Adds a resource block if necessary and associates the resource block
	* permissions with it. The resource block will have an initial reference
	* count of one.
	*
	* @param companyId the primary key of the resource block's company
	* @param groupId the primary key of the resource block's group
	* @param name the resource block's name
	* @param permissionsHash the resource block's permission hash
	* @param resourceBlockPermissionsContainer the resource block's
	permissions container
	* @return the new resource block
	*/
	public ResourceBlock addResourceBlock(long companyId, long groupId,
		String name, String permissionsHash,
		ResourceBlockPermissionsContainer resourceBlockPermissionsContainer);

	/**
	* Adds the resource block to the database. Also notifies the appropriate model listeners.
	*
	* @param resourceBlock the resource block
	* @return the resource block that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ResourceBlock addResourceBlock(ResourceBlock resourceBlock);

	/**
	* Creates a new resource block with the primary key. Does not add the resource block to the database.
	*
	* @param resourceBlockId the primary key for the new resource block
	* @return the new resource block
	*/
	@Transactional(enabled = false)
	public ResourceBlock createResourceBlock(long resourceBlockId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the resource block with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceBlockId the primary key of the resource block
	* @return the resource block that was removed
	* @throws PortalException if a resource block with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ResourceBlock deleteResourceBlock(long resourceBlockId)
		throws PortalException;

	/**
	* Deletes the resource block from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceBlock the resource block
	* @return the resource block that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ResourceBlock deleteResourceBlock(ResourceBlock resourceBlock);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ResourceBlockModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ResourceBlockModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public ResourceBlock fetchResourceBlock(long resourceBlockId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getActionId(String name, String actionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getActionIds(String name, List<String> actionIds)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<String> getActionIds(String name, long actionIdsLong);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<String> getCompanyScopePermissions(
		ResourceBlock resourceBlock, long roleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<String> getGroupScopePermissions(ResourceBlock resourceBlock,
		long roleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PermissionedModel getPermissionedModel(String name, long primKey)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<String> getPermissions(ResourceBlock resourceBlock, long roleId);

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the resource block with the primary key.
	*
	* @param resourceBlockId the primary key of the resource block
	* @return the resource block
	* @throws PortalException if a resource block with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ResourceBlock getResourceBlock(long resourceBlockId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ResourceBlock getResourceBlock(String name, long primKey)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Long> getResourceBlockIds(
		ResourceBlockIdsBag resourceBlockIdsBag, String name, String actionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ResourceBlockIdsBag getResourceBlockIdsBag(long companyId,
		long groupId, String name, long[] roleIds);

	/**
	* Returns a range of all the resource blocks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ResourceBlockModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource blocks
	* @param end the upper bound of the range of resource blocks (not inclusive)
	* @return the range of resource blocks
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ResourceBlock> getResourceBlocks(int start, int end);

	/**
	* Returns the number of resource blocks.
	*
	* @return the number of resource blocks
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getResourceBlocksCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Role> getRoles(String name, long primKey, String actionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasPermission(String name, long primKey, String actionId,
		ResourceBlockIdsBag resourceBlockIdsBag) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasPermission(String name,
		PermissionedModel permissionedModel, String actionId,
		ResourceBlockIdsBag resourceBlockIdsBag) throws PortalException;

	@Transactional(enabled = false)
	public boolean isSupported(String name);

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
	public void releasePermissionedModelResourceBlock(
		PermissionedModel permissionedModel);

	public void releasePermissionedModelResourceBlock(String name, long primKey)
		throws PortalException;

	/**
	* Decrements the reference count of the resource block and updates it in
	* the database or deletes the resource block if the reference count reaches
	* zero.
	*
	* @param resourceBlockId the primary key of the resource block
	*/
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
	public void releaseResourceBlock(long resourceBlockId);

	/**
	* Decrements the reference count of the resource block and updates it in
	* the database or deletes the resource block if the reference count reaches
	* zero.
	*
	* @param resourceBlock the resource block
	*/
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
	public void releaseResourceBlock(ResourceBlock resourceBlock);

	public void removeAllGroupScopePermissions(long companyId, String name,
		long roleId, long actionIdsLong);

	public void removeAllGroupScopePermissions(long companyId, String name,
		long roleId, String actionId) throws PortalException;

	public void removeCompanyScopePermission(long companyId, String name,
		long roleId, String actionId) throws PortalException;

	public void removeCompanyScopePermissions(long companyId, String name,
		long roleId, long actionIdsLong);

	public void removeGroupScopePermission(long companyId, long groupId,
		String name, long roleId, String actionId) throws PortalException;

	public void removeGroupScopePermissions(long companyId, long groupId,
		String name, long roleId, long actionIdsLong);

	public void removeIndividualScopePermission(long companyId, long groupId,
		String name, long primKey, long roleId, String actionId)
		throws PortalException;

	public void removeIndividualScopePermission(long companyId, long groupId,
		String name, PermissionedModel permissionedModel, long roleId,
		String actionId) throws PortalException;

	public void removeIndividualScopePermissions(long companyId, long groupId,
		String name, long primKey, long roleId, long actionIdsLong)
		throws PortalException;

	public void removeIndividualScopePermissions(long companyId, long groupId,
		String name, PermissionedModel permissionedModel, long roleId,
		long actionIdsLong);

	public void setCompanyScopePermissions(long companyId, String name,
		long roleId, List<String> actionIds) throws PortalException;

	public void setCompanyScopePermissions(long companyId, String name,
		long roleId, long actionIdsLong);

	public void setGroupScopePermissions(long companyId, long groupId,
		String name, long roleId, List<String> actionIds)
		throws PortalException;

	public void setGroupScopePermissions(long companyId, long groupId,
		String name, long roleId, long actionIdsLong);

	public void setIndividualScopePermissions(long companyId, long groupId,
		String name, long primKey, long roleId, List<String> actionIds)
		throws PortalException;

	public void setIndividualScopePermissions(long companyId, long groupId,
		String name, long primKey, long roleId, long actionIdsLong)
		throws PortalException;

	public void setIndividualScopePermissions(long companyId, long groupId,
		String name, long primKey, Map<Long, String[]> roleIdsToActionIds)
		throws PortalException;

	public void setIndividualScopePermissions(long companyId, long groupId,
		String name, PermissionedModel permissionedModel, long roleId,
		List<String> actionIds) throws PortalException;

	public void setIndividualScopePermissions(long companyId, long groupId,
		String name, PermissionedModel permissionedModel, long roleId,
		long actionIdsLong);

	public void updateCompanyScopePermissions(long companyId, String name,
		long roleId, long actionIdsLong, int operator);

	public void updateGroupScopePermissions(long companyId, long groupId,
		String name, long roleId, long actionIdsLong, int operator);

	public void updateIndividualScopePermissions(long companyId, long groupId,
		String name, PermissionedModel permissionedModel, long roleId,
		long actionIdsLong, int operator);

	/**
	* Updates the resource block in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resourceBlock the resource block
	* @return the resource block that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ResourceBlock updateResourceBlock(ResourceBlock resourceBlock);

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
	public ResourceBlock updateResourceBlockId(long companyId, long groupId,
		String name, PermissionedModel permissionedModel,
		String permissionsHash,
		ResourceBlockPermissionsContainer resourceBlockPermissionsContainer);

	public void verifyResourceBlockId(long companyId, String name, long primKey)
		throws PortalException;
}