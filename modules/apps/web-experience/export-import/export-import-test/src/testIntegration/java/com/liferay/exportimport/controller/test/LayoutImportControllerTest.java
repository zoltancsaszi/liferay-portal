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

package com.liferay.exportimport.controller.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.exportimport.constants.ExportImportConstants;
import com.liferay.exportimport.kernel.exception.LayoutImportException;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactoryUtil;
import com.liferay.portal.kernel.zip.ZipWriter;
import com.liferay.portal.kernel.zip.ZipWriterFactoryUtil;
import com.liferay.portal.lar.test.BaseExportImportTestCase;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Zoltan Csaszi
 */
@RunWith(Arquillian.class)
public class LayoutImportControllerTest extends BaseExportImportTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	@Override
	public void setUp() throws Exception {
		ServiceTestUtil.setUser(TestPropsValues.getUser());

		super.setUp();
	}

	@Test
	public void testLarFileSchemaVersionIsCompatible() throws Exception {
		exportLayouts(
			new long[] {layout.getLayoutId()}, getExportParameterMap());

		ZipReader zipReader = ZipReaderFactoryUtil.getZipReader(larFile);

		Document document = SAXReaderUtil.read(
			zipReader.getEntryAsInputStream("/manifest.xml"));

		Element rootElement = document.getRootElement();

		Element header = rootElement.element("header");

		header.addAttribute(
			"schema-version",
			ExportImportConstants.EXPORT_IMPORT_SCHEMA_VERSION);

		ZipWriter zipWriter = ZipWriterFactoryUtil.getZipWriter(larFile);

		zipWriter.addEntry("/manifest.xml", document.formattedString());
		larFile = zipWriter.getFile();

		importLayouts(getImportParameterMap());
	}

	@Test
	public void testLarFileSchemaVersionIsNotCompatible() throws Exception {
		exportLayouts(
			new long[] {layout.getLayoutId()}, getExportParameterMap());

		ZipReader zipReader = ZipReaderFactoryUtil.getZipReader(larFile);

		Document document = SAXReaderUtil.read(
			zipReader.getEntryAsInputStream("/manifest.xml"));

		Element rootElement = document.getRootElement();

		Element header = rootElement.element("header");

		header.addAttribute(
			"schema-version",
			"101" + ExportImportConstants.EXPORT_IMPORT_SCHEMA_VERSION);

		ZipWriter zipWriter = ZipWriterFactoryUtil.getZipWriter(larFile);

		zipWriter.addEntry("/manifest.xml", document.formattedString());
		larFile = zipWriter.getFile();

		try {
			importLayouts(getImportParameterMap());
			Assert.fail("The LAR file's schema-version is compatible.");
		}
		catch (Exception e) {
			Assert.assertTrue(e instanceof LayoutImportException);
		}
	}

}