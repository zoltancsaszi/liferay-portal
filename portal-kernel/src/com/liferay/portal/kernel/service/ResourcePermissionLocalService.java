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
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.Resource;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.spring.aop.Property;
import com.liferay.portal.kernel.spring.aop.Retry;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Provides the local service interface for ResourcePermission. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ResourcePermissionLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ResourcePermissionLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ResourcePermissionLocalServiceUtil} to access the resource permission local service. Add custom service methods to <code>com.liferay.portal.service.impl.ResourcePermissionLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public void addModelResourcePermissions(AuditedModel auditedModel,
		ServiceContext serviceContext) throws PortalException;

	public void addModelResourcePermissions(long companyId, long groupId,
		long userId, String name, String primKey,
		ModelPermissions modelPermissions) throws PortalException;

	/**
	* Adds resources for the model with the name and primary key string, always
	* creating a resource at the individual scope and only creating resources
	* at the group, group template, and company scope if such resources don't
	* already exist.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param userId the primary key of the user adding the resources
	* @param name a name for the resource, typically the model's class name
	* @param primKey the primary key string of the model instance, optionally
	an empty string if no instance exists
	* @param groupPermissions the group permissions to be applied
	* @param guestPermissions the guest permissions to be applied
	*/
	public void addModelResourcePermissions(long companyId, long groupId,
		long userId, String name, String primKey, String[] groupPermissions,
		String[] guestPermissions) throws PortalException;

	/**
	* Grants the role permission at the scope to perform the action on
	* resources of the type. Existing actions are retained.
	*
	* <p>
	* This method cannot be used to grant individual scope permissions, but is
	* only intended for adding permissions at the company, group, and
	* group-template scopes. For example, this method could be used to grant a
	* company scope permission to edit message board posts.
	* </p>
	*
	* <p>
	* If a company scope permission is granted to resources that the role
	* already had group scope permissions to, the group scope permissions are
	* deleted. Likewise, if a group scope permission is granted to resources
	* that the role already had company scope permissions to, the company scope
	* permissions are deleted. Be aware that this latter behavior can result in
	* an overall reduction in permissions for the role.
	* </p>
	*
	* <p>
	* Depending on the scope, the value of <code>primKey</code> will have
	* different meanings. For more information, see {@link
	* com.liferay.portal.model.impl.ResourcePermissionImpl}.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the resource's name, which can be either a class name or a
	portlet ID
	* @param scope the scope. This method only supports company, group, and
	group-template scope.
	* @param primKey the primary key
	* @param roleId the primary key of the role
	* @param actionId the action ID
	*/
	@Retry(acceptor = ExceptionRetryAcceptor.class, properties =  {
		@Property(name = ExceptionRetryAcceptor.EXCEPTION_NAME, value = "org.springframework.dao.DataIntegrityViolationException")
	}
	)
	public void addResourcePermission(long companyId, String name, int scope,
		String primKey, long roleId, String actionId) throws PortalException;

	/**
	* Adds the resource permission to the database. Also notifies the appropriate model listeners.
	*
	* @param resourcePermission the resource permission
	* @return the resource permission that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ResourcePermission addResourcePermission(
		ResourcePermission resourcePermission);

	/**
	* Adds resources for the entity with the name and primary key string,
	* always creating a resource at the individual scope and only creating
	* resources at the group, group template, and company scope if such
	* resources don't already exist.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param userId the primary key of the user adding the resources
	* @param name a name for the resource, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param primKey the primary key string of the resource instance,
	optionally an empty string if no instance exists
	* @param portletActions whether to associate portlet actions with the
	resource
	* @param addGroupPermissions whether to add group permissions
	* @param addGuestPermissions whether to add guest permissions
	*/
	public void addResourcePermissions(long companyId, long groupId,
		long userId, String name, String primKey, boolean portletActions,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws PortalException;

	/**
	* Grants the role permissions at the scope to perform the actions on all
	* resources of the type. Existing actions are retained.
	*
	* <p>
	* This method should only be used to add default permissions to existing
	* resources en masse during upgrades or while verifying permissions. For
	* example, this method could be used to grant site members individual scope
	* permissions to view all blog posts.
	* </p>
	*
	* @param resourceName the resource's name, which can be either a class name
	or a portlet ID
	* @param roleName the role's name
	* @param scope the scope
	* @param resourceActionBitwiseValue the bitwise IDs of the actions
	*/
	public void addResourcePermissions(String resourceName, String roleName,
		int scope, long resourceActionBitwiseValue);

	public void copyModelResourcePermissions(long companyId, String name,
		long oldPrimKey, long newPrimKey) throws PortalException;

	/**
	* Creates a new resource permission with the primary key. Does not add the resource permission to the database.
	*
	* @param resourcePermissionId the primary key for the new resource permission
	* @return the new resource permission
	*/
	@Transactional(enabled = false)
	public ResourcePermission createResourcePermission(
		long resourcePermissionId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the resource permission with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourcePermissionId the primary key of the resource permission
	* @return the resource permission that was removed
	* @throws PortalException if a resource permission with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ResourcePermission deleteResourcePermission(
		long resourcePermissionId) throws PortalException;

	/**
	* Deletes the resource permission from the database. Also notifies the appropriate model listeners.
	*
	* @param resourcePermission the resource permission
	* @return the resource permission that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ResourcePermission deleteResourcePermission(
		ResourcePermission resourcePermission);

	/**
	* Deletes all resource permissions at the scope to resources of the type.
	* This method should not be confused with any of the
	* <code>removeResourcePermission</code> methods, as its purpose is very
	* different. This method should only be used for deleting resource
	* permissions that refer to a resource when that resource is deleted. For
	* example this method could be used to delete all individual scope
	* permissions to a blog post when it is deleted.
	*
	* <p>
	* Depending on the scope, the value of <code>primKey</code> will have
	* different meanings. For more information, see {@link
	* com.liferay.portal.model.impl.ResourcePermissionImpl}.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the resource's name, which can be either a class name or a
	portlet ID
	* @param scope the scope
	* @param primKey the primary key
	*/
	public void deleteResourcePermissions(long companyId, String name,
		int scope, long primKey) throws PortalException;

	/**
	* Deletes all resource permissions at the scope to resources of the type.
	* This method should not be confused with any of the
	* <code>removeResourcePermission</code> methods, as its purpose is very
	* different. This method should only be used for deleting resource
	* permissions that refer to a resource when that resource is deleted. For
	* example this method could be used to delete all individual scope
	* permissions to a blog post when it is deleted.
	*
	* <p>
	* Depending on the scope, the value of <code>primKey</code> will have
	* different meanings. For more information, see {@link
	* com.liferay.portal.model.impl.ResourcePermissionImpl}.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the resource's name, which can be either a class name or a
	portlet ID
	* @param scope the scope
	* @param primKey the primary key
	*/
	public void deleteResourcePermissions(long companyId, String name,
		int scope, String primKey) throws PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ResourcePermissionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ResourcePermissionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public ResourcePermission fetchResourcePermission(long resourcePermissionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ResourcePermission fetchResourcePermission(long companyId,
		String name, int scope, String primKey, long roleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<Long, Set<String>> getAvailableResourcePermissionActionIds(
		long companyId, String name, int scope, String primKey,
		Collection<String> actionIds);

	/**
	* Returns the intersection of action IDs the role has permission at the
	* scope to perform on resources of the type.
	*
	* @param companyId he primary key of the company
	* @param name the resource's name, which can be either a class name or a
	portlet ID
	* @param scope the scope
	* @param primKey the primary key
	* @param roleId the primary key of the role
	* @param actionIds the action IDs
	* @return the intersection of action IDs the role has permission at the
	scope to perform on resources of the type
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<String> getAvailableResourcePermissionActionIds(
		long companyId, String name, int scope, String primKey, long roleId,
		Collection<String> actionIds) throws PortalException;

	/**
	* @deprecated As of Wilberforce (7.0.x), replaced by {@link
	#getAvailableResourcePermissionActionIds(long, String, int,
	String, Collection)}
	*/
	@Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<Long, Set<String>> getAvailableResourcePermissionActionIds(
		long companyId, String name, int scope, String primKey, long[] roleIds,
		Collection<String> actionIds);

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
	* Returns the resource permission with the primary key.
	*
	* @param resourcePermissionId the primary key of the resource permission
	* @return the resource permission
	* @throws PortalException if a resource permission with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ResourcePermission getResourcePermission(long resourcePermissionId)
		throws PortalException;

	/**
	* Returns the resource permission for the role at the scope to perform the
	* actions on resources of the type.
	*
	* @param companyId the primary key of the company
	* @param name the resource's name, which can be either a class name or a
	portlet ID
	* @param scope the scope
	* @param primKey the primary key
	* @param roleId the primary key of the role
	* @return the resource permission for the role at the scope to perform the
	actions on resources of the type
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ResourcePermission getResourcePermission(long companyId,
		String name, int scope, String primKey, long roleId)
		throws PortalException;

	/**
	* Returns a range of all the resource permissions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ResourcePermissionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @return the range of resource permissions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ResourcePermission> getResourcePermissions(int start, int end);

	/**
	* Returns all the resource permissions at the scope of the type.
	*
	* @param companyId the primary key of the company
	* @param name the resource's name, which can be either a class name or a
	portlet ID
	* @param scope the scope
	* @param primKey the primary key
	* @return the resource permissions at the scope of the type
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ResourcePermission> getResourcePermissions(long companyId,
		String name, int scope, String primKey);

	/**
	* Returns the number of resource permissions.
	*
	* @return the number of resource permissions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getResourcePermissionsCount();

	/**
	* Returns the number of resource permissions at the scope of the type.
	*
	* @param companyId the primary key of the company
	* @param name the resource's name, which can be either a class name or a
	portlet ID
	* @param scope the scope
	* @param primKey the primary key
	* @return the number of resource permissions at the scope of the type
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getResourcePermissionsCount(long companyId, String name,
		int scope, String primKey);

	/**
	* Returns the resource permissions that apply to the resource.
	*
	* @param companyId the primary key of the resource's company
	* @param groupId the primary key of the resource's group
	* @param name the resource's name, which can be either a class name or a
	portlet ID
	* @param primKey the primary key of the resource
	* @return the resource permissions associated with the resource
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ResourcePermission> getResourceResourcePermissions(
		long companyId, long groupId, String name, String primKey);

	/**
	* Returns all the resource permissions for the role.
	*
	* @param roleId the primary key of the role
	* @return the resource permissions for the role
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ResourcePermission> getRoleResourcePermissions(long roleId);

	/**
	* Returns a range of all the resource permissions for the role at the
	* scopes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param roleId the primary key of the role
	* @param scopes the scopes
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of resource permissions for the role at the scopes
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ResourcePermission> getRoleResourcePermissions(long roleId,
		int[] scopes, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Role> getRoles(long companyId, String name, int scope,
		String primKey, String actionId) throws PortalException;

	/**
	* Returns all the resource permissions where scope = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param scopes the scopes
	* @return the resource permissions where scope = any &#63;
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ResourcePermission> getScopeResourcePermissions(int[] scopes);

	/**
	* Returns <code>true</code> if the resource permission grants permission to
	* perform the resource action. Note that this method does not ensure that
	* the resource permission refers to the same type of resource as the
	* resource action.
	*
	* @param resourcePermission the resource permission
	* @param resourceAction the resource action
	* @return <code>true</code> if the resource permission grants permission to
	perform the resource action
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasActionId(ResourcePermission resourcePermission,
		ResourceAction resourceAction);

	/**
	* Returns <code>true</code> if the roles have permission at the scope to
	* perform the action on the resources.
	*
	* <p>
	* Depending on the scope, the value of <code>primKey</code> will have
	* different meanings. For more information, see {@link
	* com.liferay.portal.model.impl.ResourcePermissionImpl}.
	* </p>
	*
	* @param resources the resources
	* @param roleIds the primary keys of the roles
	* @param actionId the action ID
	* @return <code>true</code> if any one of the roles has permission to
	perform the action on any one of the resources;
	<code>false</code> otherwise
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasResourcePermission(List<Resource> resources,
		long[] roleIds, String actionId) throws PortalException;

	/**
	* Returns <code>true</code> if the role has permission at the scope to
	* perform the action on resources of the type.
	*
	* <p>
	* Depending on the scope, the value of <code>primKey</code> will have
	* different meanings. For more information, see {@link
	* com.liferay.portal.model.impl.ResourcePermissionImpl}.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the resource's name, which can be either a class name or a
	portlet ID
	* @param scope the scope
	* @param primKey the primary key
	* @param roleId the primary key of the role
	* @param actionId the action ID
	* @return <code>true</code> if the role has permission to perform the
	action on the resource; <code>false</code> otherwise
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasResourcePermission(long companyId, String name,
		int scope, String primKey, long roleId, String actionId)
		throws PortalException;

	/**
	* Returns <code>true</code> if the roles have permission at the scope to
	* perform the action on resources of the type.
	*
	* <p>
	* Depending on the scope, the value of <code>primKey</code> will have
	* different meanings. For more information, see {@link
	* com.liferay.portal.model.impl.ResourcePermissionImpl}.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the resource's name, which can be either a class name or a
	portlet ID
	* @param scope the scope
	* @param primKey the primary key
	* @param roleIds the primary keys of the roles
	* @param actionId the action ID
	* @return <code>true</code> if any one of the roles has permission to
	perform the action on the resource; <code>false</code> otherwise
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasResourcePermission(long companyId, String name,
		int scope, String primKey, long[] roleIds, String actionId)
		throws PortalException;

	/**
	* @deprecated As of Wilberforce (7.0.x), replaced by {@link #getRoles(long,
	String, int, String, String}
	*/
	@Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean[] hasResourcePermissions(long companyId, String name,
		int scope, String primKey, long[] roleIds, String actionId)
		throws PortalException;

	/**
	* Returns <code>true</code> if the role has permission at the scope to
	* perform the action on the resource.
	*
	* <p>
	* Depending on the scope, the value of <code>primKey</code> will have
	* different meanings. For more information, see {@link
	* com.liferay.portal.model.impl.ResourcePermissionImpl}.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the resource's name, which can be either a class name or a
	portlet ID
	* @param scope the scope
	* @param roleId the primary key of the role
	* @param actionId the action ID
	* @return <code>true</code> if the role has permission to perform the
	action on the resource; <code>false</code> otherwise
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasScopeResourcePermission(long companyId, String name,
		int scope, long roleId, String actionId) throws PortalException;

	public void initPortletDefaultPermissions(Portlet portlet)
		throws PortalException;

	/**
	* Reassigns all the resource permissions from the source role to the
	* destination role, and deletes the source role.
	*
	* @param fromRoleId the primary key of the source role
	* @param toRoleId the primary key of the destination role
	*/
	public void mergePermissions(long fromRoleId, long toRoleId)
		throws PortalException;

	/**
	* Grants the role default permissions to all the resources of the type and
	* at the scope stored in the resource permission, deletes the resource
	* permission, and deletes the resource permission's role if it has no
	* permissions remaining.
	*
	* @param resourcePermissionId the primary key of the resource permission
	* @param toRoleId the primary key of the role
	*/
	public void reassignPermissions(long resourcePermissionId, long toRoleId)
		throws PortalException;

	/**
	* Revokes permission at the scope from the role to perform the action on
	* resources of the type. For example, this method could be used to revoke a
	* group scope permission to edit blog posts.
	*
	* <p>
	* Depending on the scope, the value of <code>primKey</code> will have
	* different meanings. For more information, see {@link
	* com.liferay.portal.model.impl.ResourcePermissionImpl}.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the resource's name, which can be either a class name or a
	portlet ID
	* @param scope the scope
	* @param primKey the primary key
	* @param roleId the primary key of the role
	* @param actionId the action ID
	*/
	public void removeResourcePermission(long companyId, String name,
		int scope, String primKey, long roleId, String actionId)
		throws PortalException;

	/**
	* Revokes all permissions at the scope from the role to perform the action
	* on resources of the type. For example, this method could be used to
	* revoke all individual scope permissions to edit blog posts from site
	* members.
	*
	* @param companyId the primary key of the company
	* @param name the resource's name, which can be either a class name or a
	portlet ID
	* @param scope the scope
	* @param roleId the primary key of the role
	* @param actionId the action ID
	*/
	public void removeResourcePermissions(long companyId, String name,
		int scope, long roleId, String actionId) throws PortalException;

	/**
	* Updates the role's permissions at the scope, setting the actions that can
	* be performed on resources of the type, also setting the owner of any
	* newly created resource permissions. Existing actions are replaced.
	*
	* <p>
	* This method can be used to set permissions at any scope, but it is
	* generally only used at the individual scope. For example, it could be
	* used to set the guest permissions on a blog post.
	* </p>
	*
	* <p>
	* Depending on the scope, the value of <code>primKey</code> will have
	* different meanings. For more information, see {@link
	* com.liferay.portal.model.impl.ResourcePermissionImpl}.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the resource's name, which can be either a class name or a
	portlet ID
	* @param scope the scope
	* @param primKey the primary key
	* @param roleId the primary key of the role
	* @param ownerId the primary key of the owner (generally the user that
	created the resource)
	* @param actionIds the action IDs of the actions
	*/
	@Retry(acceptor = ExceptionRetryAcceptor.class, properties =  {
		@Property(name = ExceptionRetryAcceptor.EXCEPTION_NAME, value = "org.springframework.dao.DataIntegrityViolationException")
	}
	)
	public void setOwnerResourcePermissions(long companyId, String name,
		int scope, String primKey, long roleId, long ownerId, String[] actionIds)
		throws PortalException;

	/**
	* Updates the role's permissions at the scope, setting the actions that can
	* be performed on resources of the type. Existing actions are replaced.
	*
	* <p>
	* This method can be used to set permissions at any scope, but it is
	* generally only used at the individual scope. For example, it could be
	* used to set the guest permissions on a blog post.
	* </p>
	*
	* <p>
	* Depending on the scope, the value of <code>primKey</code> will have
	* different meanings. For more information, see {@link
	* com.liferay.portal.model.impl.ResourcePermissionImpl}.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the resource's name, which can be either a class name or a
	portlet ID
	* @param scope the scope
	* @param primKey the primary key
	* @param roleId the primary key of the role
	* @param actionIds the action IDs of the actions
	*/
	@Retry(acceptor = ExceptionRetryAcceptor.class, properties =  {
		@Property(name = ExceptionRetryAcceptor.EXCEPTION_NAME, value = "org.springframework.dao.DataIntegrityViolationException")
	}
	)
	public void setResourcePermissions(long companyId, String name, int scope,
		String primKey, long roleId, String[] actionIds)
		throws PortalException;

	/**
	* Updates the role's permissions at the scope, setting the actions that can
	* be performed on resources of the type. Existing actions are replaced.
	*
	* <p>
	* This method can be used to set permissions at any scope, but it is
	* generally only used at the individual scope. For example, it could be
	* used to set the guest permissions on a blog post.
	* </p>
	*
	* <p>
	* Depending on the scope, the value of <code>primKey</code> will have
	* different meanings. For more information, see {@link
	* com.liferay.portal.model.impl.ResourcePermissionImpl}.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the resource's name, which can be either a class name or a
	portlet ID
	* @param scope the scope
	* @param primKey the primary key
	* @param roleIdsToActionIds a map of role IDs to action IDs of the actions
	*/
	@Retry(acceptor = ExceptionRetryAcceptor.class, properties =  {
		@Property(name = ExceptionRetryAcceptor.EXCEPTION_NAME, value = "org.springframework.dao.DataIntegrityViolationException")
	}
	)
	public void setResourcePermissions(long companyId, String name, int scope,
		String primKey, Map<Long, String[]> roleIdsToActionIds)
		throws PortalException;

	/**
	* Updates the resources for the model, replacing their group and guest
	* permissions with new ones from the service context.
	*
	* @param auditedModel the model associated with the resources
	* @param serviceContext the service context to be applied. Can set group
	and guest permissions.
	*/
	public void updateModelResourcePermissions(AuditedModel auditedModel,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Updates the resource permission in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resourcePermission the resource permission
	* @return the resource permission that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ResourcePermission updateResourcePermission(
		ResourcePermission resourcePermission);

	/**
	* Updates resources matching the group, name, and primary key at the
	* individual scope, setting new group and guest permissions.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param name the resource's name, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param primKey the primary key of the resource instance
	* @param groupPermissions the group permissions to be applied
	* @param guestPermissions the guest permissions to be applied
	*/
	public void updateResourcePermissions(long companyId, long groupId,
		String name, long primKey, String[] groupPermissions,
		String[] guestPermissions) throws PortalException;

	public void updateResourcePermissions(long companyId, long groupId,
		String name, String primKey, ModelPermissions modelPermissions)
		throws PortalException;

	public void updateResourcePermissions(long companyId, long groupId,
		String name, String primKey, String[] groupPermissions,
		String[] guestPermissions) throws PortalException;

	public void updateResourcePermissions(long companyId, String name,
		int scope, String primKey, String newPrimKey);
}