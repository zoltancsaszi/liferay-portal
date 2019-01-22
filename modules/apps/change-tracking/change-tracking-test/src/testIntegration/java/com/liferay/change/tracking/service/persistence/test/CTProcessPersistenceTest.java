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

package com.liferay.change.tracking.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.change.tracking.exception.NoSuchProcessException;
import com.liferay.change.tracking.model.CTProcess;
import com.liferay.change.tracking.service.CTProcessLocalServiceUtil;
import com.liferay.change.tracking.service.persistence.CTProcessPersistence;
import com.liferay.change.tracking.service.persistence.CTProcessUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class CTProcessPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.change.tracking.service"));

	@Before
	public void setUp() {
		_persistence = CTProcessUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CTProcess> iterator = _ctProcesses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CTProcess ctProcess = _persistence.create(pk);

		Assert.assertNotNull(ctProcess);

		Assert.assertEquals(ctProcess.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CTProcess newCTProcess = addCTProcess();

		_persistence.remove(newCTProcess);

		CTProcess existingCTProcess = _persistence.fetchByPrimaryKey(newCTProcess.getPrimaryKey());

		Assert.assertNull(existingCTProcess);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCTProcess();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CTProcess newCTProcess = _persistence.create(pk);

		newCTProcess.setCompanyId(RandomTestUtil.nextLong());

		newCTProcess.setUserId(RandomTestUtil.nextLong());

		newCTProcess.setUserName(RandomTestUtil.randomString());

		newCTProcess.setCreateDate(RandomTestUtil.nextDate());

		newCTProcess.setModifiedDate(RandomTestUtil.nextDate());

		newCTProcess.setBackgroundTaskId(RandomTestUtil.nextLong());

		newCTProcess.setCtCollectionId(RandomTestUtil.nextLong());

		_ctProcesses.add(_persistence.update(newCTProcess));

		CTProcess existingCTProcess = _persistence.findByPrimaryKey(newCTProcess.getPrimaryKey());

		Assert.assertEquals(existingCTProcess.getCtProcessId(),
			newCTProcess.getCtProcessId());
		Assert.assertEquals(existingCTProcess.getCompanyId(),
			newCTProcess.getCompanyId());
		Assert.assertEquals(existingCTProcess.getUserId(),
			newCTProcess.getUserId());
		Assert.assertEquals(existingCTProcess.getUserName(),
			newCTProcess.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCTProcess.getCreateDate()),
			Time.getShortTimestamp(newCTProcess.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCTProcess.getModifiedDate()),
			Time.getShortTimestamp(newCTProcess.getModifiedDate()));
		Assert.assertEquals(existingCTProcess.getBackgroundTaskId(),
			newCTProcess.getBackgroundTaskId());
		Assert.assertEquals(existingCTProcess.getCtCollectionId(),
			newCTProcess.getCtCollectionId());
	}

	@Test
	public void testCountByCompanyId() throws Exception {
		_persistence.countByCompanyId(RandomTestUtil.nextLong());

		_persistence.countByCompanyId(0L);
	}

	@Test
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CTProcess newCTProcess = addCTProcess();

		CTProcess existingCTProcess = _persistence.findByPrimaryKey(newCTProcess.getPrimaryKey());

		Assert.assertEquals(existingCTProcess, newCTProcess);
	}

	@Test(expected = NoSuchProcessException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CTProcess> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CTProcess", "ctProcessId",
			true, "companyId", true, "userId", true, "userName", true,
			"createDate", true, "modifiedDate", true, "backgroundTaskId", true,
			"ctCollectionId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CTProcess newCTProcess = addCTProcess();

		CTProcess existingCTProcess = _persistence.fetchByPrimaryKey(newCTProcess.getPrimaryKey());

		Assert.assertEquals(existingCTProcess, newCTProcess);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CTProcess missingCTProcess = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCTProcess);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CTProcess newCTProcess1 = addCTProcess();
		CTProcess newCTProcess2 = addCTProcess();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCTProcess1.getPrimaryKey());
		primaryKeys.add(newCTProcess2.getPrimaryKey());

		Map<Serializable, CTProcess> ctProcesses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ctProcesses.size());
		Assert.assertEquals(newCTProcess1,
			ctProcesses.get(newCTProcess1.getPrimaryKey()));
		Assert.assertEquals(newCTProcess2,
			ctProcesses.get(newCTProcess2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CTProcess> ctProcesses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ctProcesses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CTProcess newCTProcess = addCTProcess();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCTProcess.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CTProcess> ctProcesses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ctProcesses.size());
		Assert.assertEquals(newCTProcess,
			ctProcesses.get(newCTProcess.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CTProcess> ctProcesses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ctProcesses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CTProcess newCTProcess = addCTProcess();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCTProcess.getPrimaryKey());

		Map<Serializable, CTProcess> ctProcesses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ctProcesses.size());
		Assert.assertEquals(newCTProcess,
			ctProcesses.get(newCTProcess.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CTProcessLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CTProcess>() {
				@Override
				public void performAction(CTProcess ctProcess) {
					Assert.assertNotNull(ctProcess);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CTProcess newCTProcess = addCTProcess();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CTProcess.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ctProcessId",
				newCTProcess.getCtProcessId()));

		List<CTProcess> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CTProcess existingCTProcess = result.get(0);

		Assert.assertEquals(existingCTProcess, newCTProcess);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CTProcess.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ctProcessId",
				RandomTestUtil.nextLong()));

		List<CTProcess> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CTProcess newCTProcess = addCTProcess();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CTProcess.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("ctProcessId"));

		Object newCtProcessId = newCTProcess.getCtProcessId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ctProcessId",
				new Object[] { newCtProcessId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCtProcessId = result.get(0);

		Assert.assertEquals(existingCtProcessId, newCtProcessId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CTProcess.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("ctProcessId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ctProcessId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CTProcess addCTProcess() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CTProcess ctProcess = _persistence.create(pk);

		ctProcess.setCompanyId(RandomTestUtil.nextLong());

		ctProcess.setUserId(RandomTestUtil.nextLong());

		ctProcess.setUserName(RandomTestUtil.randomString());

		ctProcess.setCreateDate(RandomTestUtil.nextDate());

		ctProcess.setModifiedDate(RandomTestUtil.nextDate());

		ctProcess.setBackgroundTaskId(RandomTestUtil.nextLong());

		ctProcess.setCtCollectionId(RandomTestUtil.nextLong());

		_ctProcesses.add(_persistence.update(ctProcess));

		return ctProcess;
	}

	private List<CTProcess> _ctProcesses = new ArrayList<CTProcess>();
	private CTProcessPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}