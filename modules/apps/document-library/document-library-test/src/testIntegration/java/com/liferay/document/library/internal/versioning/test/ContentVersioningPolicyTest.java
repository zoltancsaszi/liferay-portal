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

package com.liferay.document.library.internal.versioning.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.versioning.VersioningPolicy;
import com.liferay.portal.kernel.security.RandomUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portlet.documentlibrary.model.impl.DLFileVersionImpl;

import java.util.Optional;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Adolfo Pérez
 */
@RunWith(Arquillian.class)
public class ContentVersioningPolicyTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testComponentIsAvailable() {
		Assert.assertNotNull(_versioningPolicy);
	}

	@Test(expected = NullPointerException.class)
	public void testFailsWithNullNextDLFileVersion() {
		_versioningPolicy.computeDLVersionNumberIncrease(
			new DLFileVersionImpl(), null, new String[0]);
	}

	@Test(expected = NullPointerException.class)
	public void testFailsWithNullPreviousDLFileVersion() {
		_versioningPolicy.computeDLVersionNumberIncrease(
			null, new DLFileVersionImpl(), new String[0]);
	}

	@Test
	public void testMajorVersionWhenContentChanges() {
		DLFileVersionImpl previousDLFileVersion = new DLFileVersionImpl();

		previousDLFileVersion.setSize(RandomUtil.nextInt(100) + 1);
		previousDLFileVersion.setChecksum(StringUtil.randomString(5));

		DLFileVersionImpl nextDLFileVersion = new DLFileVersionImpl();

		nextDLFileVersion.setSize(previousDLFileVersion.getSize());
		nextDLFileVersion.setChecksum(StringUtil.randomString(6));

		Optional<DLVersionNumberIncrease> dlVersionNumberIncreaseOptional =
			_versioningPolicy.computeDLVersionNumberIncrease(
				previousDLFileVersion, nextDLFileVersion, new String[0]);

		Assert.assertTrue(dlVersionNumberIncreaseOptional.isPresent());
		Assert.assertEquals(
			DLVersionNumberIncrease.MAJOR,
			dlVersionNumberIncreaseOptional.get());
	}

	@Test
	public void testMajorVersionWhenSizeChanges() {
		DLFileVersionImpl previousDLFileVersion = new DLFileVersionImpl();

		previousDLFileVersion.setSize(RandomUtil.nextInt(100) + 1);

		DLFileVersionImpl nextDLFileVersion = new DLFileVersionImpl();

		nextDLFileVersion.setSize(previousDLFileVersion.getSize() + 1);

		Optional<DLVersionNumberIncrease> dlVersionNumberIncreaseOptional =
			_versioningPolicy.computeDLVersionNumberIncrease(
				previousDLFileVersion, nextDLFileVersion, new String[0]);

		Assert.assertTrue(dlVersionNumberIncreaseOptional.isPresent());
		Assert.assertEquals(
			DLVersionNumberIncrease.MAJOR,
			dlVersionNumberIncreaseOptional.get());
	}

	@Test
	public void testNoVersionIncreaseWhenContentDoesntChange() {
		DLFileVersionImpl previousDLFileVersion = new DLFileVersionImpl();

		previousDLFileVersion.setSize(RandomUtil.nextInt(100) + 1);
		previousDLFileVersion.setChecksum(StringUtil.randomString(5));

		DLFileVersionImpl nextDLFileVersion = new DLFileVersionImpl();

		nextDLFileVersion.setSize(previousDLFileVersion.getSize());
		nextDLFileVersion.setChecksum(previousDLFileVersion.getChecksum());

		Optional<DLVersionNumberIncrease> dlVersionNumberIncreaseOptional =
			_versioningPolicy.computeDLVersionNumberIncrease(
				previousDLFileVersion, nextDLFileVersion, new String[0]);

		Assert.assertFalse(dlVersionNumberIncreaseOptional.isPresent());
	}

	@Test
	public void testNoVersionIncreaseWhenPreviousEmptyAndNextNonempty() {
		DLFileVersionImpl previousDLFileVersion = new DLFileVersionImpl();

		previousDLFileVersion.setSize(0);

		DLFileVersionImpl nextDLFileVersion = new DLFileVersionImpl();

		nextDLFileVersion.setSize(RandomUtil.nextInt(100) + 1);

		Optional<DLVersionNumberIncrease> dlVersionNumberIncreaseOptional =
			_versioningPolicy.computeDLVersionNumberIncrease(
				previousDLFileVersion, nextDLFileVersion, new String[0]);

		Assert.assertFalse(dlVersionNumberIncreaseOptional.isPresent());
	}

	@Inject(filter = "component.name=*.ContentVersioningPolicy")
	private VersioningPolicy _versioningPolicy;

}