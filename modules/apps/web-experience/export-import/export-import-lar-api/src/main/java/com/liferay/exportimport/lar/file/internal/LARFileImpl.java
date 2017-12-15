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

package com.liferay.exportimport.lar.file.internal;

import com.liferay.exportimport.kernel.lar.ExportImportClassedModelUtil;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.lar.file.LARFile;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.zip.ZipWriter;

import java.io.OutputStream;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import jodd.bean.BeanUtil;

/**
 * @author Mate Thurzo
 */
public class LARFileImpl implements LARFile {

	public LARFileImpl(PortletDataContext portletDataContext) {
		_portletDataContext = portletDataContext;
	}

	@Override
	public void endReadPortletData() {
	}

	@Override
	public void endWritePortletData() {
		if (_isWriteEventCompleted(_PORTLET_DATA_START)) {
			try {
				_xmlStreamWriter.writeEndElement();

				_xmlStreamWriter.flush();

				_xmlStreamWriter.close();

				_writeEventCompleted(_PORTLET_DATA_END);

				_outputStream.flush();
				_outputStream.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void closeReferenceXMLWriter() {
		if (_referenceXmlStreamWriter == null) {
			return;
		}

		try {
			_referenceXmlStreamWriter.writeEndDocument();

			_referenceXmlStreamWriter.flush();

			_referenceXmlStreamWriter.close();

			_referenceXmlStreamWriter = null;

			_referenceOutputStream.flush();
			_referenceOutputStream.close();
		}
		catch (Exception e) {
			_log.error(
				"Error during closing reference xml writer: ", e);
		}
	}

	@Override
	public void endWriteStagedModel() {
		if (_isWriteEventCompleted(_STAGED_MODEL_START)) {
			try {
				_xmlStreamWriter.writeEndElement();

				closeReferenceXMLWriter();

				_writeEventCompleted(_STAGED_MODEL_END);
				_writeEventReset(_STAGED_MODEL_START);
			}
			catch (Exception e) {
				e.printStackTrace();

				_writeEventReset(_STAGED_MODEL_END);
			}
		}
	}

	@Override
	public StagedModel getNextStagedModel() {
		return null;
	}

	@Override
	public boolean hasNextStagedModel() {
		return false;
	}

	@Override
	public String readPortletDataAttribute(String name) {
		return null;
	}

	@Override
	public String readStagedModelAttribute(String name) {
		return null;
	}

	@Override
	public Map<String, String> readStagedModelAttributes() {
		return null;
	}

	@Override
	public void readStagedModels() {
	}

	@Override
	public void registerStagedModelConsumer(
		String className, Consumer<StagedModel> consumer) {
	}

	@Override
	public void registerStagedModelFilter(
		String className, Predicate<?> filter) {
	}

	@Override
	public void registerStagedModelPostAction(
		String className, Function<?, ?> action) {
	}

	@Override
	public void registerStagedModelPreAction(
		String className, Function<?, ?> action) {
	}

	@Override
	public void startReadPortletData() {
	}

	@Override
	public void startWritePortletData(String name) {
		try {
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();

			ZipWriter zipWriter = _portletDataContext.getZipWriter();

			_outputStream = zipWriter.addEntry(
				ExportImportPathUtil.getPortletDataPath(_portletDataContext));

			_xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(
				_outputStream);

			_xmlStreamWriter.writeStartDocument();

			_xmlStreamWriter.writeStartElement(name);

			_writeEventCompleted(_PORTLET_DATA_START);
		}
		catch (Exception e) {
			_writeEventReset(_PORTLET_DATA_START);

			e.printStackTrace();
		}
	}

	@Override
	public void startWriteStagedModel(StagedModel stagedModel) {
		if (!_isWriteEventCompleted(_PORTLET_DATA_START)) {
			return;
		}

		try {
			_xmlStreamWriter.writeStartElement("staged-model");

			_xmlStreamWriter.writeAttribute("uuid", stagedModel.getUuid());

			if (stagedModel instanceof StagedGroupedModel) {
				StagedGroupedModel stagedGroupedModel =
					(StagedGroupedModel)stagedModel;

				_xmlStreamWriter.writeAttribute(
					"group-id",
					String.valueOf(stagedGroupedModel.getGroupId()));
			}

			_writeEventCompleted(_STAGED_MODEL_START);
		}
		catch (Exception e) {
			e.printStackTrace();

			_writeEventReset(_STAGED_MODEL_START);
		}
	}

	@Override
	public void writePortletDataAttribute(String name, String value) {
		if (_isWriteEventCompleted(_PORTLET_DATA_START)) {
			try {
				_xmlStreamWriter.writeAttribute(name, value);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void writeReferenceStagedModel(
		StagedModel referrerStagedModel, StagedModel stagedModel,
		String referenceType, boolean missing) {

		if (_referenceXmlStreamWriter == null) {
			_initReferenceXmlStreamWriter(referrerStagedModel);
		}

		try {
			_referenceXmlStreamWriter.writeStartElement("reference");

			String className = ExportImportClassedModelUtil.getClassName(
				stagedModel);

			_referenceXmlStreamWriter.writeAttribute("class-name", className);

			_referenceXmlStreamWriter.writeAttribute(
				"class-pk", String.valueOf(stagedModel.getPrimaryKeyObj()));

			_populateClassNameAttribute(stagedModel);

			if (stagedModel instanceof StagedGroupedModel) {
				StagedGroupedModel stagedGroupedModel =
					(StagedGroupedModel)stagedModel;

				_referenceXmlStreamWriter.writeAttribute(
					"group-id",
					String.valueOf(stagedGroupedModel.getGroupId()));

				try {
					Group group = GroupLocalServiceUtil.getGroup(
						stagedGroupedModel.getGroupId());

					long liveGroupId = group.getLiveGroupId();

					if (group.isStagedRemotely()) {
						liveGroupId = group.getRemoteLiveGroupId();
					}

					if (liveGroupId == GroupConstants.DEFAULT_LIVE_GROUP_ID) {
						liveGroupId = group.getGroupId();
					}

					_referenceXmlStreamWriter.writeAttribute(
						"live-group-id", String.valueOf(liveGroupId));

					if (group.isLayout()) {
						try {
							Layout scopeLayout =
								LayoutLocalServiceUtil.getLayout(
									group.getClassPK());

							_referenceXmlStreamWriter.writeAttribute(
								"scope-layout-uuid", scopeLayout.getUuid());
						}
						catch (NoSuchLayoutException nsle) {
							nsle.printStackTrace();
						}
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}

			/*if (Validator.isNotNull(binPath)) {
				referenceElement.addAttribute("path", binPath);
			}*/

			_referenceXmlStreamWriter.writeAttribute("type", referenceType);

			_referenceXmlStreamWriter.writeAttribute(
				"uuid", stagedModel.getUuid());
			_referenceXmlStreamWriter.writeAttribute(
				"company-id", String.valueOf(stagedModel.getCompanyId()));

			Map<String, String> referenceAttributes =
				StagedModelDataHandlerUtil.getReferenceAttributes(
					_portletDataContext, stagedModel);

			for (Map.Entry<String, String> referenceAttribute :
					referenceAttributes.entrySet()) {

				_referenceXmlStreamWriter.writeAttribute(
					referenceAttribute.getKey(), referenceAttribute.getValue());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeStagedModelAttribute(String name, String value) {
		if (_isWriteEventCompleted(_STAGED_MODEL_START)) {
			try {
				_xmlStreamWriter.writeAttribute(name, value);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void _initReferenceXmlStreamWriter(
		StagedModel referrerStagedModel) {

		XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();

		ZipWriter zipWriter = _portletDataContext.getZipWriter();

		String path = ExportImportPathUtil.getModelPath(
			referrerStagedModel, "ref");

		try {
			_referenceOutputStream = zipWriter.addEntry(path);

			_referenceXmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(
				_referenceOutputStream);

			_referenceXmlStreamWriter.writeStartDocument();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean _isWriteEventCompleted(int event) {
		return _writeEventArray[event];
	}

	private void _populateClassNameAttribute(StagedModel stagedModel) {
		String attachedClassName = null;

		if (stagedModel instanceof AttachedModel) {
			AttachedModel attachedModel = (AttachedModel)stagedModel;

			attachedClassName = attachedModel.getClassName();
		}
		else if (BeanUtil.hasProperty(stagedModel, "className")) {
			attachedClassName = BeanPropertiesUtil.getStringSilent(
				stagedModel, "className");
		}

		if (Validator.isNotNull(attachedClassName)) {
			try {
				_xmlStreamWriter.writeAttribute(
					"attached-class-name", attachedClassName);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void _writeEventCompleted(int event) {
		_writeEventArray[event] = true;
	}

	private void _writeEventReset(int event) {
		_writeEventArray[event] = false;
	}

	private static final int _PORTLET_DATA_END = 1;

	private static final int _PORTLET_DATA_START = 0;

	private static final int _STAGED_MODEL_END = 3;

	private static final int _STAGED_MODEL_START = 2;

	private static final Log _log = LogFactoryUtil.getLog(LARFileImpl.class);

	private final PortletDataContext _portletDataContext;
	private XMLStreamWriter _referenceXmlStreamWriter;
	private boolean[] _writeEventArray = new boolean[4];
	private XMLStreamWriter _xmlStreamWriter;
	private OutputStream _outputStream;
	private OutputStream _referenceOutputStream;

}