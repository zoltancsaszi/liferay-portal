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

package com.liferay.portal.lar.test;

import com.liferay.exportimport.kernel.lar.DataLevel;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataContextFactoryUtil;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.zip.ZipWriter;
import com.liferay.portal.kernel.zip.ZipWriterFactoryUtil;
import com.liferay.portlet.PortletPreferencesImpl;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Zsolt Berentey
 */
public abstract class BasePortletDataHandlerTestCase {

	@Before
	public void setUp() throws Exception {
		stagingGroup = GroupTestUtil.addGroup();

		portletId = getPortletId();

		portletDataHandler = getPortletDataHandler(portletId);
	}

	@Test
	public void testDataLevel() throws Exception {
		Assert.assertEquals(getDataLevel(), portletDataHandler.getDataLevel());
	}

	@Test
	public void testDeletionSystemEventStagedModelTypes() throws Exception {
		Assert.assertArrayEquals(
			getDeletionSystemEventStagedModelTypes(),
			portletDataHandler.getDeletionSystemEventStagedModelTypes());
	}

	@Test
	public void testDeletionSystemEventStagedModelTypes() throws Exception {
		Assert.assertArrayEquals(
			getDeletionSystemEventStagedModelTypes(),
			portletDataHandler.getDeletionSystemEventStagedModelTypes());
	}

	@Test
	public void testDeleteData() throws Exception {
		initExport();

		long ownerId = PortletKeys.PREFS_OWNER_ID_DEFAULT;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_LAYOUT;

		javax.portlet.PortletPreferences portletPreferences =
			PortletPreferencesLocalServiceUtil.fetchPreferences(
				portletDataContext.getCompanyId(), ownerId, ownerType,
				portletDataContext.getPlid(),
				portletDataContext.getPortletId());

		if (portletPreferences == null) {
			portletPreferences = new PortletPreferencesImpl();
		}

		addStagedModels();

		portletDataHandler.deleteData(
			portletDataContext, portletId, portletPreferences);

		Assert.assertEquals(0, getStagedModels().size());

		for (String preferenceKey :
				portletDataHandler.getDataPortletPreferences()) {

			String portletPreference = portletPreferences.getValue(
				preferenceKey, StringPool.BLANK);

			Assert.assertEquals(StringPool.BLANK, portletPreference);
		}
	}

	@Test
	public void testDeletionSystemEventStagedModelTypes() throws Exception {
		Assert.assertArrayEquals(
			getDeletionSystemEventStagedModelTypes(),
			portletDataHandler.getDeletionSystemEventStagedModelTypes());
	}

	@Test
	public void testIsDataPortalLevel() {
		Assert.assertEquals(
			isDataPortalLevel(), portletDataHandler.isDataPortalLevel());
	}

	@Test
	public void testIsDataPortletInstanceLevel() {
		Assert.assertEquals(
			isDataPortletInstanceLevel(),
			portletDataHandler.isDataPortletInstanceLevel());
	}

	@Test
	public void testIsDataSiteLevel() {
		Assert.assertEquals(
			isDataSiteLevel(), portletDataHandler.isDataSiteLevel());
	}

	@Test
	public void testPrepareManifestSummary() throws Exception {
		initExport();

		addStagedModels();

		portletDataContext.setEndDate(getEndDate());

		portletDataHandler.prepareManifestSummary(portletDataContext);

		ManifestSummary manifestSummary =
			portletDataContext.getManifestSummary();

		ManifestSummary expectedManifestSummary =
			(ManifestSummary)manifestSummary.clone();

		manifestSummary.resetCounters();

		portletDataHandler.exportData(
			portletDataContext, portletId, new PortletPreferencesImpl());

		checkManifestSummary(expectedManifestSummary);
	}

	protected void addBooleanParameter(
		Map<String, String[]> parameterMap, String namespace, String name,
		boolean value) {

		PortletDataHandlerBoolean portletDataHandlerBoolean =
			new PortletDataHandlerBoolean(namespace, name);

		parameterMap.put(
			portletDataHandlerBoolean.getNamespacedControlName(),
			new String[] {String.valueOf(value)});
	}

	protected void addParameters(Map<String, String[]> parameterMap) {
	}

	protected abstract void addStagedModels() throws Exception;

	protected void checkManifestSummary(
		ManifestSummary expectedManifestSummary) {

		ManifestSummary manifestSummary =
			portletDataContext.getManifestSummary();

		checkManifestSummaryReferrerClassNames(manifestSummary);

		for (String manifestSummaryKey :
				expectedManifestSummary.getManifestSummaryKeys()) {

			String[] keyParts = StringUtil.split(
				manifestSummaryKey, StringPool.POUND);

			long expectedModelAdditionCount =
				expectedManifestSummary.getModelAdditionCount(
					manifestSummaryKey);

			StagedModelType stagedModelType = new StagedModelType(keyParts[0]);

			if (keyParts.length > 1) {
				stagedModelType = new StagedModelType(keyParts[0], keyParts[1]);
			}

			long modelAdditionCount = manifestSummary.getModelAdditionCount(
				stagedModelType);

			if (expectedModelAdditionCount == 0) {
				Assert.assertFalse(modelAdditionCount > 0);
			}
			else {
				Assert.assertEquals(
					expectedModelAdditionCount, modelAdditionCount);
			}
		}
	}

	protected void checkManifestSummaryReferrerClassNames(
		ManifestSummary manifestSummary) {

		for (String manifestSummaryKey :
			manifestSummary.getManifestSummaryKeys()) {

			Assert.assertFalse(
				manifestSummaryKey.endsWith(
					StagedModelType.REFERRER_CLASS_NAME_ALL));
			Assert.assertFalse(
				manifestSummaryKey.endsWith(
					StagedModelType.REFERRER_CLASS_NAME_ANY));
		}
	}

	protected DataLevel getDataLevel() {
		return DataLevel.SITE;
	}

	protected StagedModelType[] getDeletionSystemEventStagedModelTypes() {
		return new StagedModelType[0];
	}

	protected Date getEndDate() {
		return new Date();
	}

	protected PortletDataHandler getPortletDataHandler(String portletId) {
		try {
			Registry registry = RegistryUtil.getRegistry();

			Collection<PortletDataHandler> portletDataHandlers =
				registry.getServices(
					PortletDataHandler.class,
					"(javax.portlet.name=" + portletId + ")");

			Iterator<PortletDataHandler> iterator =
				portletDataHandlers.iterator();

			return iterator.next();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected abstract String getPortletId();

	protected List<StagedModel> getStagedModels() {
		return new ArrayList<>();
	}

	protected Date getStartDate() {
		return new Date(System.currentTimeMillis() - Time.HOUR);
	}

	protected void initExport() throws Exception {
		Map<String, String[]> parameterMap = new LinkedHashMap<>();

		addParameters(parameterMap);

		zipWriter = ZipWriterFactoryUtil.getZipWriter();

		portletDataContext =
			PortletDataContextFactoryUtil.createExportPortletDataContext(
				stagingGroup.getCompanyId(), stagingGroup.getGroupId(),
				parameterMap, getStartDate(), getEndDate(), zipWriter);

		rootElement = SAXReaderUtil.createElement("root");

		portletDataContext.setExportDataRootElement(rootElement);

		missingReferencesElement = SAXReaderUtil.createElement(
			"missing-references");

		portletDataContext.setMissingReferencesElement(
			missingReferencesElement);
	}

	protected boolean isDataPortalLevel() {
		DataLevel dataLevel = getDataLevel();

		return dataLevel.equals(DataLevel.PORTAL);
	}

	protected boolean isDataPortletInstanceLevel() {
		DataLevel dataLevel = getDataLevel();

		return dataLevel.equals(DataLevel.PORTLET_INSTANCE);
	}

	protected boolean isDataSiteLevel() {
		DataLevel dataLevel = getDataLevel();

		return dataLevel.equals(DataLevel.SITE);
	}

	protected Element missingReferencesElement;
	protected PortletDataContext portletDataContext;
	protected PortletDataHandler portletDataHandler;
	protected String portletId;
	protected Element rootElement;

	@DeleteAfterTestRun
	protected Group stagingGroup;

	protected ZipWriter zipWriter;

}