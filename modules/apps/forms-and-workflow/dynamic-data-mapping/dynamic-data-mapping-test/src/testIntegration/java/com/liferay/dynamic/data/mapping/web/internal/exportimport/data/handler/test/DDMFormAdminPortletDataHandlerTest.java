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

package com.liferay.dynamic.data.mapping.web.internal.exportimport.data.handler.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.dynamic.data.mapping.form.web.internal.constants.DDMFormPortletKeys;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.test.util.DDMFormTestUtil;
import com.liferay.dynamic.data.mapping.test.util.DDMFormValuesTestUtil;
import com.liferay.dynamic.data.mapping.test.util.DDMStructureTestUtil;
import com.liferay.exportimport.kernel.lar.DataLevel;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.lar.test.BasePortletDataHandlerTestCase;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Zoltan Csaszi
 */
@RunWith(Arquillian.class)
@Sync
public class DDMFormAdminPortletDataHandlerTest
	extends BasePortletDataHandlerTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	@Override
	public void setUp() throws Exception {
		ServiceTestUtil.setUser(TestPropsValues.getUser());

		super.setUp();
	}

	@Override
	protected void addStagedModels() throws Exception {
		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			stagingGroup.getGroupId(), DDMFormInstance.class.getName());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(stagingGroup.getGroupId());

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(Locale.US, "test");

		JSONObject fieldValue = JSONFactoryUtil.createJSONObject();

		fieldValue.put("test", "test");

		JSONArray fieldValues = JSONFactoryUtil.createJSONArray();

		fieldValues.put(fieldValue);

		JSONObject serializedSettings = JSONFactoryUtil.createJSONObject();

		serializedSettings.put(
			"availableLanguageIds", JSONFactoryUtil.createJSONArray());
		serializedSettings.put("defaultLanguageId", "en");
		serializedSettings.put("fieldValues", fieldValues);

		DDMFormInstance formInstance =
			DDMFormInstanceLocalServiceUtil.addFormInstance(
				serviceContext.getUserId(), stagingGroup.getGroupId(),
				ddmStructure.getStructureId(), nameMap, nameMap,
				serializedSettings.toString(), serviceContext);

		DDMForm form = DDMFormTestUtil.createDDMForm("test");

		DDMFormValues ddmFormValues = DDMFormValuesTestUtil.createDDMFormValues(
			form);

		DDMFormInstanceRecordLocalServiceUtil.addFormInstanceRecord(
			serviceContext.getUserId(), stagingGroup.getGroupId(),
			formInstance.getFormInstanceId(), ddmFormValues, serviceContext);
	}

	@Override
	protected DataLevel getDataLevel() {
		return DataLevel.SITE;
	}

	@Override
	protected String[] getDataPortletPreferences() {
		return new String[0];
	}

	@Override
	protected String getPortletId() {
		return DDMFormPortletKeys.DYNAMIC_DATA_MAPPING_FORM_ADMIN;
	}

	@Override
	protected boolean isDataPortalLevel() {
		return false;
	}

	@Override
	protected boolean isDataPortletInstanceLevel() {
		return false;
	}

	@Override
	protected boolean isDataSiteLevel() {
		return true;
	}

}