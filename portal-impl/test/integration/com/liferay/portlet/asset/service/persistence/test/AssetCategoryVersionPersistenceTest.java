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

package com.liferay.portlet.asset.service.persistence.test;

import com.liferay.asset.kernel.exception.NoSuchCategoryVersionException;
import com.liferay.asset.kernel.model.AssetCategoryVersion;
import com.liferay.asset.kernel.service.persistence.AssetCategoryVersionPersistence;
import com.liferay.asset.kernel.service.persistence.AssetCategoryVersionUtil;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
public class AssetCategoryVersionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = AssetCategoryVersionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AssetCategoryVersion> iterator = _assetCategoryVersions.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetCategoryVersion assetCategoryVersion = _persistence.create(pk);

		Assert.assertNotNull(assetCategoryVersion);

		Assert.assertEquals(assetCategoryVersion.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AssetCategoryVersion newAssetCategoryVersion = addAssetCategoryVersion();

		_persistence.remove(newAssetCategoryVersion);

		AssetCategoryVersion existingAssetCategoryVersion = _persistence.fetchByPrimaryKey(newAssetCategoryVersion.getPrimaryKey());

		Assert.assertNull(existingAssetCategoryVersion);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAssetCategoryVersion();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetCategoryVersion newAssetCategoryVersion = _persistence.create(pk);

		newAssetCategoryVersion.setVersion(RandomTestUtil.nextInt());

		newAssetCategoryVersion.setUuid(RandomTestUtil.randomString());

		newAssetCategoryVersion.setExternalReferenceCode(RandomTestUtil.randomString());

		newAssetCategoryVersion.setCategoryId(RandomTestUtil.nextLong());

		newAssetCategoryVersion.setGroupId(RandomTestUtil.nextLong());

		newAssetCategoryVersion.setCompanyId(RandomTestUtil.nextLong());

		newAssetCategoryVersion.setUserId(RandomTestUtil.nextLong());

		newAssetCategoryVersion.setUserName(RandomTestUtil.randomString());

		newAssetCategoryVersion.setCreateDate(RandomTestUtil.nextDate());

		newAssetCategoryVersion.setModifiedDate(RandomTestUtil.nextDate());

		newAssetCategoryVersion.setParentCategoryId(RandomTestUtil.nextLong());

		newAssetCategoryVersion.setLeftCategoryId(RandomTestUtil.nextLong());

		newAssetCategoryVersion.setRightCategoryId(RandomTestUtil.nextLong());

		newAssetCategoryVersion.setName(RandomTestUtil.randomString());

		newAssetCategoryVersion.setTitle(RandomTestUtil.randomString());

		newAssetCategoryVersion.setDescription(RandomTestUtil.randomString());

		newAssetCategoryVersion.setVocabularyId(RandomTestUtil.nextLong());

		newAssetCategoryVersion.setLastPublishDate(RandomTestUtil.nextDate());

		_assetCategoryVersions.add(_persistence.update(newAssetCategoryVersion));

		AssetCategoryVersion existingAssetCategoryVersion = _persistence.findByPrimaryKey(newAssetCategoryVersion.getPrimaryKey());

		Assert.assertEquals(existingAssetCategoryVersion.getAssetCategoryVersionId(),
			newAssetCategoryVersion.getAssetCategoryVersionId());
		Assert.assertEquals(existingAssetCategoryVersion.getVersion(),
			newAssetCategoryVersion.getVersion());
		Assert.assertEquals(existingAssetCategoryVersion.getUuid(),
			newAssetCategoryVersion.getUuid());
		Assert.assertEquals(existingAssetCategoryVersion.getExternalReferenceCode(),
			newAssetCategoryVersion.getExternalReferenceCode());
		Assert.assertEquals(existingAssetCategoryVersion.getCategoryId(),
			newAssetCategoryVersion.getCategoryId());
		Assert.assertEquals(existingAssetCategoryVersion.getGroupId(),
			newAssetCategoryVersion.getGroupId());
		Assert.assertEquals(existingAssetCategoryVersion.getCompanyId(),
			newAssetCategoryVersion.getCompanyId());
		Assert.assertEquals(existingAssetCategoryVersion.getUserId(),
			newAssetCategoryVersion.getUserId());
		Assert.assertEquals(existingAssetCategoryVersion.getUserName(),
			newAssetCategoryVersion.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAssetCategoryVersion.getCreateDate()),
			Time.getShortTimestamp(newAssetCategoryVersion.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingAssetCategoryVersion.getModifiedDate()),
			Time.getShortTimestamp(newAssetCategoryVersion.getModifiedDate()));
		Assert.assertEquals(existingAssetCategoryVersion.getParentCategoryId(),
			newAssetCategoryVersion.getParentCategoryId());
		Assert.assertEquals(existingAssetCategoryVersion.getLeftCategoryId(),
			newAssetCategoryVersion.getLeftCategoryId());
		Assert.assertEquals(existingAssetCategoryVersion.getRightCategoryId(),
			newAssetCategoryVersion.getRightCategoryId());
		Assert.assertEquals(existingAssetCategoryVersion.getName(),
			newAssetCategoryVersion.getName());
		Assert.assertEquals(existingAssetCategoryVersion.getTitle(),
			newAssetCategoryVersion.getTitle());
		Assert.assertEquals(existingAssetCategoryVersion.getDescription(),
			newAssetCategoryVersion.getDescription());
		Assert.assertEquals(existingAssetCategoryVersion.getVocabularyId(),
			newAssetCategoryVersion.getVocabularyId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAssetCategoryVersion.getLastPublishDate()),
			Time.getShortTimestamp(newAssetCategoryVersion.getLastPublishDate()));
	}

	@Test
	public void testCountByCategoryId() throws Exception {
		_persistence.countByCategoryId(RandomTestUtil.nextLong());

		_persistence.countByCategoryId(0L);
	}

	@Test
	public void testCountByCategoryId_Version() throws Exception {
		_persistence.countByCategoryId_Version(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByCategoryId_Version(0L, 0);
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUuid_Version() throws Exception {
		_persistence.countByUuid_Version("", RandomTestUtil.nextInt());

		_persistence.countByUuid_Version("null", 0);

		_persistence.countByUuid_Version((String)null, 0);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G("", RandomTestUtil.nextLong());

		_persistence.countByUUID_G("null", 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUUID_G_Version() throws Exception {
		_persistence.countByUUID_G_Version("", RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByUUID_G_Version("null", 0L, 0);

		_persistence.countByUUID_G_Version((String)null, 0L, 0);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C_Version() throws Exception {
		_persistence.countByUuid_C_Version("", RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByUuid_C_Version("null", 0L, 0);

		_persistence.countByUuid_C_Version((String)null, 0L, 0);
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testCountByGroupId_Version() throws Exception {
		_persistence.countByGroupId_Version(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByGroupId_Version(0L, 0);
	}

	@Test
	public void testCountByParentCategoryId() throws Exception {
		_persistence.countByParentCategoryId(RandomTestUtil.nextLong());

		_persistence.countByParentCategoryId(0L);
	}

	@Test
	public void testCountByParentCategoryId_Version() throws Exception {
		_persistence.countByParentCategoryId_Version(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByParentCategoryId_Version(0L, 0);
	}

	@Test
	public void testCountByVocabularyId() throws Exception {
		_persistence.countByVocabularyId(RandomTestUtil.nextLong());

		_persistence.countByVocabularyId(0L);
	}

	@Test
	public void testCountByVocabularyId_Version() throws Exception {
		_persistence.countByVocabularyId_Version(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByVocabularyId_Version(0L, 0);
	}

	@Test
	public void testCountByG_P() throws Exception {
		_persistence.countByG_P(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByG_P(0L, 0L);
	}

	@Test
	public void testCountByG_P_Version() throws Exception {
		_persistence.countByG_P_Version(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByG_P_Version(0L, 0L, 0);
	}

	@Test
	public void testCountByG_V() throws Exception {
		_persistence.countByG_V(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByG_V(0L, 0L);
	}

	@Test
	public void testCountByG_V_Version() throws Exception {
		_persistence.countByG_V_Version(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByG_V_Version(0L, 0L, 0);
	}

	@Test
	public void testCountByP_N() throws Exception {
		_persistence.countByP_N(RandomTestUtil.nextLong(), "");

		_persistence.countByP_N(0L, "null");

		_persistence.countByP_N(0L, (String)null);
	}

	@Test
	public void testCountByP_N_Version() throws Exception {
		_persistence.countByP_N_Version(RandomTestUtil.nextLong(), "",
			RandomTestUtil.nextInt());

		_persistence.countByP_N_Version(0L, "null", 0);

		_persistence.countByP_N_Version(0L, (String)null, 0);
	}

	@Test
	public void testCountByP_V() throws Exception {
		_persistence.countByP_V(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByP_V(0L, 0L);
	}

	@Test
	public void testCountByP_V_Version() throws Exception {
		_persistence.countByP_V_Version(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByP_V_Version(0L, 0L, 0);
	}

	@Test
	public void testCountByN_V() throws Exception {
		_persistence.countByN_V("", RandomTestUtil.nextLong());

		_persistence.countByN_V("null", 0L);

		_persistence.countByN_V((String)null, 0L);
	}

	@Test
	public void testCountByN_V_Version() throws Exception {
		_persistence.countByN_V_Version("", RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByN_V_Version("null", 0L, 0);

		_persistence.countByN_V_Version((String)null, 0L, 0);
	}

	@Test
	public void testCountByG_P_V() throws Exception {
		_persistence.countByG_P_V(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByG_P_V(0L, 0L, 0L);
	}

	@Test
	public void testCountByG_P_V_Version() throws Exception {
		_persistence.countByG_P_V_Version(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByG_P_V_Version(0L, 0L, 0L, 0);
	}

	@Test
	public void testCountByG_LikeN_V() throws Exception {
		_persistence.countByG_LikeN_V(RandomTestUtil.nextLong(), "",
			RandomTestUtil.nextLong());

		_persistence.countByG_LikeN_V(0L, "null", 0L);

		_persistence.countByG_LikeN_V(0L, (String)null, 0L);
	}

	@Test
	public void testCountByG_LikeN_V_Version() throws Exception {
		_persistence.countByG_LikeN_V_Version(RandomTestUtil.nextLong(), "",
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByG_LikeN_V_Version(0L, "null", 0L, 0);

		_persistence.countByG_LikeN_V_Version(0L, (String)null, 0L, 0);
	}

	@Test
	public void testCountByP_N_V() throws Exception {
		_persistence.countByP_N_V(RandomTestUtil.nextLong(), "",
			RandomTestUtil.nextLong());

		_persistence.countByP_N_V(0L, "null", 0L);

		_persistence.countByP_N_V(0L, (String)null, 0L);
	}

	@Test
	public void testCountByP_N_V_Version() throws Exception {
		_persistence.countByP_N_V_Version(RandomTestUtil.nextLong(), "",
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByP_N_V_Version(0L, "null", 0L, 0);

		_persistence.countByP_N_V_Version(0L, (String)null, 0L, 0);
	}

	@Test
	public void testCountByG_P_N_V() throws Exception {
		_persistence.countByG_P_N_V(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), "", RandomTestUtil.nextLong());

		_persistence.countByG_P_N_V(0L, 0L, "null", 0L);

		_persistence.countByG_P_N_V(0L, 0L, (String)null, 0L);
	}

	@Test
	public void testCountByG_P_N_V_Version() throws Exception {
		_persistence.countByG_P_N_V_Version(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), "", RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByG_P_N_V_Version(0L, 0L, "null", 0L, 0);

		_persistence.countByG_P_N_V_Version(0L, 0L, (String)null, 0L, 0);
	}

	@Test
	public void testCountByC_ERC() throws Exception {
		_persistence.countByC_ERC(RandomTestUtil.nextLong(), "");

		_persistence.countByC_ERC(0L, "null");

		_persistence.countByC_ERC(0L, (String)null);
	}

	@Test
	public void testCountByC_ERC_Version() throws Exception {
		_persistence.countByC_ERC_Version(RandomTestUtil.nextLong(), "",
			RandomTestUtil.nextInt());

		_persistence.countByC_ERC_Version(0L, "null", 0);

		_persistence.countByC_ERC_Version(0L, (String)null, 0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AssetCategoryVersion newAssetCategoryVersion = addAssetCategoryVersion();

		AssetCategoryVersion existingAssetCategoryVersion = _persistence.findByPrimaryKey(newAssetCategoryVersion.getPrimaryKey());

		Assert.assertEquals(existingAssetCategoryVersion,
			newAssetCategoryVersion);
	}

	@Test(expected = NoSuchCategoryVersionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AssetCategoryVersion> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("AssetCategoryVersion",
			"assetCategoryVersionId", true, "version", true, "uuid", true,
			"externalReferenceCode", true, "categoryId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "parentCategoryId", true,
			"leftCategoryId", true, "rightCategoryId", true, "name", true,
			"title", true, "description", true, "vocabularyId", true,
			"lastPublishDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AssetCategoryVersion newAssetCategoryVersion = addAssetCategoryVersion();

		AssetCategoryVersion existingAssetCategoryVersion = _persistence.fetchByPrimaryKey(newAssetCategoryVersion.getPrimaryKey());

		Assert.assertEquals(existingAssetCategoryVersion,
			newAssetCategoryVersion);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetCategoryVersion missingAssetCategoryVersion = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAssetCategoryVersion);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AssetCategoryVersion newAssetCategoryVersion1 = addAssetCategoryVersion();
		AssetCategoryVersion newAssetCategoryVersion2 = addAssetCategoryVersion();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAssetCategoryVersion1.getPrimaryKey());
		primaryKeys.add(newAssetCategoryVersion2.getPrimaryKey());

		Map<Serializable, AssetCategoryVersion> assetCategoryVersions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, assetCategoryVersions.size());
		Assert.assertEquals(newAssetCategoryVersion1,
			assetCategoryVersions.get(newAssetCategoryVersion1.getPrimaryKey()));
		Assert.assertEquals(newAssetCategoryVersion2,
			assetCategoryVersions.get(newAssetCategoryVersion2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AssetCategoryVersion> assetCategoryVersions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(assetCategoryVersions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AssetCategoryVersion newAssetCategoryVersion = addAssetCategoryVersion();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAssetCategoryVersion.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AssetCategoryVersion> assetCategoryVersions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, assetCategoryVersions.size());
		Assert.assertEquals(newAssetCategoryVersion,
			assetCategoryVersions.get(newAssetCategoryVersion.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AssetCategoryVersion> assetCategoryVersions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(assetCategoryVersions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AssetCategoryVersion newAssetCategoryVersion = addAssetCategoryVersion();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAssetCategoryVersion.getPrimaryKey());

		Map<Serializable, AssetCategoryVersion> assetCategoryVersions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, assetCategoryVersions.size());
		Assert.assertEquals(newAssetCategoryVersion,
			assetCategoryVersions.get(newAssetCategoryVersion.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AssetCategoryVersion newAssetCategoryVersion = addAssetCategoryVersion();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetCategoryVersion.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("assetCategoryVersionId",
				newAssetCategoryVersion.getAssetCategoryVersionId()));

		List<AssetCategoryVersion> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AssetCategoryVersion existingAssetCategoryVersion = result.get(0);

		Assert.assertEquals(existingAssetCategoryVersion,
			newAssetCategoryVersion);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetCategoryVersion.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("assetCategoryVersionId",
				RandomTestUtil.nextLong()));

		List<AssetCategoryVersion> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AssetCategoryVersion newAssetCategoryVersion = addAssetCategoryVersion();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetCategoryVersion.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"assetCategoryVersionId"));

		Object newAssetCategoryVersionId = newAssetCategoryVersion.getAssetCategoryVersionId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("assetCategoryVersionId",
				new Object[] { newAssetCategoryVersionId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAssetCategoryVersionId = result.get(0);

		Assert.assertEquals(existingAssetCategoryVersionId,
			newAssetCategoryVersionId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetCategoryVersion.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"assetCategoryVersionId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("assetCategoryVersionId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		AssetCategoryVersion newAssetCategoryVersion = addAssetCategoryVersion();

		_persistence.clearCache();

		AssetCategoryVersion existingAssetCategoryVersion = _persistence.findByPrimaryKey(newAssetCategoryVersion.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingAssetCategoryVersion.getCategoryId()),
			ReflectionTestUtil.<Long>invoke(existingAssetCategoryVersion,
				"getOriginalCategoryId", new Class<?>[0]));
		Assert.assertEquals(Integer.valueOf(
				existingAssetCategoryVersion.getVersion()),
			ReflectionTestUtil.<Integer>invoke(existingAssetCategoryVersion,
				"getOriginalVersion", new Class<?>[0]));

		Assert.assertTrue(Objects.equals(
				existingAssetCategoryVersion.getUuid(),
				ReflectionTestUtil.invoke(existingAssetCategoryVersion,
					"getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(
				existingAssetCategoryVersion.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingAssetCategoryVersion,
				"getOriginalGroupId", new Class<?>[0]));
		Assert.assertEquals(Integer.valueOf(
				existingAssetCategoryVersion.getVersion()),
			ReflectionTestUtil.<Integer>invoke(existingAssetCategoryVersion,
				"getOriginalVersion", new Class<?>[0]));

		Assert.assertEquals(Long.valueOf(
				existingAssetCategoryVersion.getParentCategoryId()),
			ReflectionTestUtil.<Long>invoke(existingAssetCategoryVersion,
				"getOriginalParentCategoryId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(
				existingAssetCategoryVersion.getName(),
				ReflectionTestUtil.invoke(existingAssetCategoryVersion,
					"getOriginalName", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(
				existingAssetCategoryVersion.getVocabularyId()),
			ReflectionTestUtil.<Long>invoke(existingAssetCategoryVersion,
				"getOriginalVocabularyId", new Class<?>[0]));
		Assert.assertEquals(Integer.valueOf(
				existingAssetCategoryVersion.getVersion()),
			ReflectionTestUtil.<Integer>invoke(existingAssetCategoryVersion,
				"getOriginalVersion", new Class<?>[0]));
	}

	protected AssetCategoryVersion addAssetCategoryVersion()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetCategoryVersion assetCategoryVersion = _persistence.create(pk);

		assetCategoryVersion.setVersion(RandomTestUtil.nextInt());

		assetCategoryVersion.setUuid(RandomTestUtil.randomString());

		assetCategoryVersion.setExternalReferenceCode(RandomTestUtil.randomString());

		assetCategoryVersion.setCategoryId(RandomTestUtil.nextLong());

		assetCategoryVersion.setGroupId(RandomTestUtil.nextLong());

		assetCategoryVersion.setCompanyId(RandomTestUtil.nextLong());

		assetCategoryVersion.setUserId(RandomTestUtil.nextLong());

		assetCategoryVersion.setUserName(RandomTestUtil.randomString());

		assetCategoryVersion.setCreateDate(RandomTestUtil.nextDate());

		assetCategoryVersion.setModifiedDate(RandomTestUtil.nextDate());

		assetCategoryVersion.setParentCategoryId(RandomTestUtil.nextLong());

		assetCategoryVersion.setLeftCategoryId(RandomTestUtil.nextLong());

		assetCategoryVersion.setRightCategoryId(RandomTestUtil.nextLong());

		assetCategoryVersion.setName(RandomTestUtil.randomString());

		assetCategoryVersion.setTitle(RandomTestUtil.randomString());

		assetCategoryVersion.setDescription(RandomTestUtil.randomString());

		assetCategoryVersion.setVocabularyId(RandomTestUtil.nextLong());

		assetCategoryVersion.setLastPublishDate(RandomTestUtil.nextDate());

		_assetCategoryVersions.add(_persistence.update(assetCategoryVersion));

		return assetCategoryVersion;
	}

	private List<AssetCategoryVersion> _assetCategoryVersions = new ArrayList<AssetCategoryVersion>();
	private AssetCategoryVersionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}