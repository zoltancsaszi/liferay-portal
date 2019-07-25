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

package com.liferay.change.tracking.rest.resource.v1_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.change.tracking.constants.CTConstants;
import com.liferay.change.tracking.definition.CTDefinition;
import com.liferay.change.tracking.definition.CTDefinitionRegistrar;
import com.liferay.change.tracking.definition.builder.CTDefinitionBuilder;
import com.liferay.change.tracking.engine.CTEngineManager;
import com.liferay.change.tracking.engine.CTManager;
import com.liferay.change.tracking.model.CTCollection;
import com.liferay.change.tracking.model.CTEntry;
import com.liferay.change.tracking.model.CTProcess;
import com.liferay.change.tracking.rest.client.constant.v1_0.ProcessType;
import com.liferay.change.tracking.rest.client.dto.v1_0.ProcessUser;
import com.liferay.change.tracking.rest.client.pagination.Page;
import com.liferay.change.tracking.rest.client.pagination.Pagination;
import com.liferay.change.tracking.rest.client.resource.v1_0.ProcessUserResource;
import com.liferay.change.tracking.rest.model.TestResourceModelClass;
import com.liferay.change.tracking.rest.model.TestVersionModelClass;
import com.liferay.change.tracking.service.CTCollectionLocalService;
import com.liferay.change.tracking.service.CTEntryLocalService;
import com.liferay.change.tracking.service.CTProcessLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portal.test.rule.Inject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * @author Zoltan Csaszi
 */
@RunWith(Arquillian.class)
public class ProcessUserResourceTest extends BaseProcessUserResourceTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		ServiceTestUtil.setUser(TestPropsValues.getUser());

		_user = UserTestUtil.addUser();

		_users = new ArrayList<>();

		// If the test environment has change tracking enabled, then disable
		// change tracking for the first run

		if (_ctEngineManager.isChangeTrackingEnabled(
				TestPropsValues.getCompanyId())) {

			_originallyEnabled = true;

			_ctEngineManager.disableChangeTracking(
				TestPropsValues.getCompanyId());
		}

		_testVersionClassName = _classNameLocalService.addClassName(
			TestVersionModelClass.class.getName());

		_testResourceClassName = _classNameLocalService.addClassName(
			TestResourceModelClass.class.getName());

		_ctDefinition = _ctDefinitionBuilder.setContentType(
			"Test Object"
		).setContentTypeLanguageKey(
			"test-object"
		).setEntityClasses(
			TestResourceModelClass.class, TestVersionModelClass.class
		).setResourceEntitiesByCompanyIdFunction(
			id -> Collections.emptyList()
		).setResourceEntityByResourceEntityIdFunction(
			id -> new TestResourceModelClass()
		).setEntityIdsFromResourceEntityFunctions(
			testResource -> 0L, testResource -> 0L
		).setVersionEntitiesFromResourceEntityFunction(
			testResource -> Collections.emptyList()
		).setVersionEntityByVersionEntityIdFunction(
			id -> new TestVersionModelClass()
		).setVersionEntityDetails(
			Collections.emptyList(), o -> RandomTestUtil.randomString(),
			o -> RandomTestUtil.randomString(), o -> 1L
		).setEntityIdsFromVersionEntityFunctions(
			testVersion -> 0L, testVersion -> 0L
		).setVersionEntityStatusInfo(
			new Integer[] {WorkflowConstants.STATUS_APPROVED},
			testVersion -> WorkflowConstants.STATUS_APPROVED
		).build();

		_ctDefinitionRegistrar.register(_ctDefinition);

		ProcessUserResource.Builder builder = ProcessUserResource.builder();

		processUserResource = builder.locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	@Override
	public void tearDown() throws Exception {
		if (_ctDefinition != null) {
			_ctDefinitionRegistrar.unregister(_ctDefinition);
		}

		// If the change tracking was enabled originally, then leave it in the
		// same state

		if (_originallyEnabled) {
			_ctEngineManager.enableChangeTracking(
				TestPropsValues.getCompanyId(), TestPropsValues.getUserId());
		}
		else {
			_ctEngineManager.disableChangeTracking(
				TestPropsValues.getCompanyId());
		}
	}

	@Override
	public void testGetProcessUsersPage() throws Exception {
		Set<Long> userIds = new HashSet<>();

		_createChangeEntry();

		CTProcess ctProcess = _ctProcessLocalService.addCTProcess(
			_user.getUserId(), _ctCollection.getCtCollectionId(), true,
			new ServiceContext());

		userIds.add(ctProcess.getUserId());

		User otherUser = UserTestUtil.addUser();

		ctProcess = _ctProcessLocalService.addCTProcess(
			otherUser.getUserId(), _ctCollection.getCtCollectionId(), true,
			new ServiceContext());

		userIds.add(ctProcess.getUserId());

		Page<ProcessUser> processUserPage =
			processUserResource.getProcessUsersPage(
				TestPropsValues.getCompanyId(), StringPool.BLANK,
				ProcessType.ALL, null);

		Assert.assertEquals(
			"Wrong number of processes", 2, processUserPage.getTotalCount());

		Collection<ProcessUser> processUsers = processUserPage.getItems();

		Stream<ProcessUser> processUserStream = processUsers.stream();

		processUserStream.forEach(
			processUser -> userIds.remove(processUser.getUserId()));

		Assert.assertTrue("userIds should be empty", userIds.isEmpty());
	}

	@Override
	public void testGetProcessUsersPageWithPagination() throws Exception {
		for (int i = 0; i < 20; i++) {
			_createChangeEntry();

			User user = UserTestUtil.addUser();

			_users.add(user);

			_ctProcessLocalService.addCTProcess(
				user.getUserId(), _ctCollection.getCtCollectionId(), true,
				new ServiceContext());
		}

		Pagination pagination = Pagination.of(2, 5);

		Page<ProcessUser> processUserPage =
			processUserResource.getProcessUsersPage(
				TestPropsValues.getCompanyId(), StringPool.BLANK,
				ProcessType.ALL, pagination);

		Assert.assertEquals("Wrong page number", 2, processUserPage.getPage());

		Assert.assertEquals(
			"Wrong total count", 5, processUserPage.getTotalCount());
	}

	private void _createChangeEntry() throws Exception {
		String name = RandomTestUtil.randomString();

		long modelResourcePrimKey = RandomTestUtil.nextLong();

		_ctCollection = _ctCollectionLocalService.addCTCollection(
			_user.getUserId(), name, RandomTestUtil.randomString(),
			new ServiceContext());

		_ctEntry = _ctEntryLocalService.addCTEntry(
			_user.getUserId(), _testVersionClassName.getClassNameId(), 0,
			modelResourcePrimKey, CTConstants.CT_CHANGE_TYPE_ADDITION,
			_ctCollection.getCtCollectionId(), new ServiceContext());
	}

	@Inject
	private ClassNameLocalService _classNameLocalService;

	@DeleteAfterTestRun
	private CTCollection _ctCollection;

	@Inject
	private CTCollectionLocalService _ctCollectionLocalService;

	private CTDefinition _ctDefinition;

	@Inject
	private CTDefinitionBuilder<TestResourceModelClass, TestVersionModelClass>
		_ctDefinitionBuilder;

	@Inject
	private CTDefinitionRegistrar _ctDefinitionRegistrar;

	@Inject
	private CTEngineManager _ctEngineManager;

	@DeleteAfterTestRun
	private CTEntry _ctEntry;

	@Inject
	private CTEntryLocalService _ctEntryLocalService;

	@Inject
	private CTManager _ctManager;

	@Inject
	private CTProcessLocalService _ctProcessLocalService;

	private boolean _originallyEnabled;

	@DeleteAfterTestRun
	private ClassName _testResourceClassName;

	@DeleteAfterTestRun
	private ClassName _testVersionClassName;

	@DeleteAfterTestRun
	private User _user;

	@DeleteAfterTestRun
	private List<User> _users;

}