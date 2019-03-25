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
import com.liferay.change.tracking.exception.NoSuchEntryException;
import com.liferay.change.tracking.model.CTEntry;
import com.liferay.change.tracking.service.CTEntryLocalServiceUtil;
import com.liferay.change.tracking.service.persistence.CTEntryPersistence;
import com.liferay.change.tracking.service.persistence.CTEntryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class CTEntryPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.liferay.change.tracking.service"));

	@Before
	public void setUp() {
		_persistence = CTEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CTEntry> iterator = _ctEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CTEntry ctEntry = _persistence.create(pk);

		Assert.assertNotNull(ctEntry);

		Assert.assertEquals(ctEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CTEntry newCTEntry = addCTEntry();

		_persistence.remove(newCTEntry);

		CTEntry existingCTEntry = _persistence.fetchByPrimaryKey(
			newCTEntry.getPrimaryKey());

		Assert.assertNull(existingCTEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCTEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CTEntry newCTEntry = _persistence.create(pk);

		newCTEntry.setCompanyId(RandomTestUtil.nextLong());

		newCTEntry.setUserId(RandomTestUtil.nextLong());

		newCTEntry.setUserName(RandomTestUtil.randomString());

		newCTEntry.setCreateDate(RandomTestUtil.nextDate());

		newCTEntry.setModifiedDate(RandomTestUtil.nextDate());

		newCTEntry.setModelClassNameId(RandomTestUtil.nextLong());

		newCTEntry.setModelClassPK(RandomTestUtil.nextLong());

		newCTEntry.setModelResourcePrimKey(RandomTestUtil.nextLong());

		newCTEntry.setChangeType(RandomTestUtil.nextInt());

		newCTEntry.setStatus(RandomTestUtil.nextInt());

		newCTEntry.setOriginalCollectionId(RandomTestUtil.nextLong());

		newCTEntry.setCollision(RandomTestUtil.randomBoolean());

		_ctEntries.add(_persistence.update(newCTEntry));

		CTEntry existingCTEntry = _persistence.findByPrimaryKey(
			newCTEntry.getPrimaryKey());

		Assert.assertEquals(
			existingCTEntry.getCtEntryId(), newCTEntry.getCtEntryId());
		Assert.assertEquals(
			existingCTEntry.getCompanyId(), newCTEntry.getCompanyId());
		Assert.assertEquals(
			existingCTEntry.getUserId(), newCTEntry.getUserId());
		Assert.assertEquals(
			existingCTEntry.getUserName(), newCTEntry.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingCTEntry.getCreateDate()),
			Time.getShortTimestamp(newCTEntry.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingCTEntry.getModifiedDate()),
			Time.getShortTimestamp(newCTEntry.getModifiedDate()));
		Assert.assertEquals(
			existingCTEntry.getModelClassNameId(),
			newCTEntry.getModelClassNameId());
		Assert.assertEquals(
			existingCTEntry.getModelClassPK(), newCTEntry.getModelClassPK());
		Assert.assertEquals(
			existingCTEntry.getModelResourcePrimKey(),
			newCTEntry.getModelResourcePrimKey());
		Assert.assertEquals(
			existingCTEntry.getChangeType(), newCTEntry.getChangeType());
		Assert.assertEquals(
			existingCTEntry.getStatus(), newCTEntry.getStatus());
		Assert.assertEquals(
			existingCTEntry.getOriginalCollectionId(),
			newCTEntry.getOriginalCollectionId());
		Assert.assertEquals(
			existingCTEntry.isCollision(), newCTEntry.isCollision());
	}

	@Test
	public void testCountByModelClassNameId() throws Exception {
		_persistence.countByModelClassNameId(RandomTestUtil.nextLong());

		_persistence.countByModelClassNameId(0L);
	}

	@Test
	public void testCountByC_C() throws Exception {
		_persistence.countByC_C(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByC_C(0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CTEntry newCTEntry = addCTEntry();

		CTEntry existingCTEntry = _persistence.findByPrimaryKey(
			newCTEntry.getPrimaryKey());

		Assert.assertEquals(existingCTEntry, newCTEntry);
	}

	@Test(expected = NoSuchEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CTEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"CTEntry", "ctEntryId", true, "companyId", true, "userId", true,
			"userName", true, "createDate", true, "modifiedDate", true,
			"modelClassNameId", true, "modelClassPK", true,
			"modelResourcePrimKey", true, "changeType", true, "status", true,
			"originalCollectionId", true, "collision", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CTEntry newCTEntry = addCTEntry();

		CTEntry existingCTEntry = _persistence.fetchByPrimaryKey(
			newCTEntry.getPrimaryKey());

		Assert.assertEquals(existingCTEntry, newCTEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CTEntry missingCTEntry = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCTEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CTEntry newCTEntry1 = addCTEntry();
		CTEntry newCTEntry2 = addCTEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCTEntry1.getPrimaryKey());
		primaryKeys.add(newCTEntry2.getPrimaryKey());

		Map<Serializable, CTEntry> ctEntries = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, ctEntries.size());
		Assert.assertEquals(
			newCTEntry1, ctEntries.get(newCTEntry1.getPrimaryKey()));
		Assert.assertEquals(
			newCTEntry2, ctEntries.get(newCTEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CTEntry> ctEntries = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(ctEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CTEntry newCTEntry = addCTEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCTEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CTEntry> ctEntries = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, ctEntries.size());
		Assert.assertEquals(
			newCTEntry, ctEntries.get(newCTEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CTEntry> ctEntries = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(ctEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CTEntry newCTEntry = addCTEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCTEntry.getPrimaryKey());

		Map<Serializable, CTEntry> ctEntries = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, ctEntries.size());
		Assert.assertEquals(
			newCTEntry, ctEntries.get(newCTEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CTEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<CTEntry>() {

				@Override
				public void performAction(CTEntry ctEntry) {
					Assert.assertNotNull(ctEntry);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CTEntry newCTEntry = addCTEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CTEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("ctEntryId", newCTEntry.getCtEntryId()));

		List<CTEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CTEntry existingCTEntry = result.get(0);

		Assert.assertEquals(existingCTEntry, newCTEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CTEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("ctEntryId", RandomTestUtil.nextLong()));

		List<CTEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CTEntry newCTEntry = addCTEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CTEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("ctEntryId"));

		Object newCtEntryId = newCTEntry.getCtEntryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"ctEntryId", new Object[] {newCtEntryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCtEntryId = result.get(0);

		Assert.assertEquals(existingCtEntryId, newCtEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CTEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("ctEntryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"ctEntryId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CTEntry newCTEntry = addCTEntry();

		_persistence.clearCache();

		CTEntry existingCTEntry = _persistence.findByPrimaryKey(
			newCTEntry.getPrimaryKey());

		Assert.assertEquals(
			Long.valueOf(existingCTEntry.getModelClassNameId()),
			ReflectionTestUtil.<Long>invoke(
				existingCTEntry, "getOriginalModelClassNameId",
				new Class<?>[0]));
		Assert.assertEquals(
			Long.valueOf(existingCTEntry.getModelClassPK()),
			ReflectionTestUtil.<Long>invoke(
				existingCTEntry, "getOriginalModelClassPK", new Class<?>[0]));
	}

	protected CTEntry addCTEntry() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CTEntry ctEntry = _persistence.create(pk);

		ctEntry.setCompanyId(RandomTestUtil.nextLong());

		ctEntry.setUserId(RandomTestUtil.nextLong());

		ctEntry.setUserName(RandomTestUtil.randomString());

		ctEntry.setCreateDate(RandomTestUtil.nextDate());

		ctEntry.setModifiedDate(RandomTestUtil.nextDate());

		ctEntry.setModelClassNameId(RandomTestUtil.nextLong());

		ctEntry.setModelClassPK(RandomTestUtil.nextLong());

		ctEntry.setModelResourcePrimKey(RandomTestUtil.nextLong());

		ctEntry.setChangeType(RandomTestUtil.nextInt());

		ctEntry.setStatus(RandomTestUtil.nextInt());

		ctEntry.setOriginalCollectionId(RandomTestUtil.nextLong());

		ctEntry.setCollision(RandomTestUtil.randomBoolean());

		_ctEntries.add(_persistence.update(ctEntry));

		return ctEntry;
	}

	private List<CTEntry> _ctEntries = new ArrayList<CTEntry>();
	private CTEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}