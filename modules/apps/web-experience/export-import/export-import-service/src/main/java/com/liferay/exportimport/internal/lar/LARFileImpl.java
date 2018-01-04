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

package com.liferay.exportimport.internal.lar;

import com.liferay.exportimport.kernel.lar.ExportImportClassedModelUtil;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.file.LARFile;
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

				_portletDataContext.setStreamProcessSupport(false);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void endWriteReference() {
		if (_isWriteEventCompleted(_REFERENCE_START)) {
			try {
				_xmlStreamWriter.writeEndElement();

				//closeReferenceXMLWriter();

				_writeEventCompleted(_REFERENCE_END);
				_writeEventReset(_REFERENCE_START);
			}
			catch (Exception e) {
				e.printStackTrace();

				_writeEventReset(_REFERENCE_END);
			}
		}
	}

	@Override
	public void endWriteStagedModel() {
		if (_isWriteEventCompleted(_STAGED_MODEL_START)) {
			try {
				_xmlStreamWriter.writeEndElement();

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

			_portletDataContext.setStreamProcessSupport(true);
		}
		catch (Exception e) {
			_writeEventReset(_PORTLET_DATA_START);

			e.printStackTrace();
		}
	}

	@Override
	public void startWriteReference() {
//		if (!_isWriteEventCompleted(_STAGED_MODEL_START)) {
//			return;
//		}

		if (!_isWriteEventCompleted(_REFERENCE_START)) {
			try {
				_xmlStreamWriter.writeStartElement("references");

				_writeEventCompleted(_REFERENCE_START);
				_writeEventReset(_REFERENCE_END);
			}
			catch (Exception e) {
				e.printStackTrace();

				_writeEventReset(_REFERENCE_START);
			}
		}
	}

	@Override
	public void startWriteStagedModel(StagedModel stagedModel) {
		if (!_isWriteEventCompleted(_PORTLET_DATA_START)) {
			return;
		}
//		if(_isWriteEventCompleted(_STAGED_MODEL_START)){
//			_stagedModelQueue.add(stagedModel);
//			return;
//		}

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
	public void writePropertyElement(
		String userUuid, String key, String value) {

		endWriteReference();

		try {
			_xmlStreamWriter.writeStartElement("property");
			_xmlStreamWriter.writeAttribute("userUuid", userUuid);
			_xmlStreamWriter.writeAttribute("key", key);
			_xmlStreamWriter.writeAttribute("value", value);
			_xmlStreamWriter.writeEndElement();
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);
		}
	}

	@Override
	public void writeReferenceAttribute(String name, String value) {
		if (_isWriteEventCompleted(_REFERENCE_START)) {
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

		if (!_isWriteEventCompleted(_REFERENCE_START)) {
			return;
		}

		try {
			_xmlStreamWriter.writeStartElement("reference");

			String className = ExportImportClassedModelUtil.getClassName(
				stagedModel);

			_xmlStreamWriter.writeAttribute("class-name", className);

			_xmlStreamWriter.writeAttribute(
				"class-pk", String.valueOf(stagedModel.getPrimaryKeyObj()));

			if (stagedModel instanceof StagedGroupedModel) {
				StagedGroupedModel stagedGroupedModel =
					(StagedGroupedModel)stagedModel;

				_xmlStreamWriter.writeAttribute(
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

					_xmlStreamWriter.writeAttribute(
						"live-group-id", String.valueOf(liveGroupId));

					if (group.isLayout()) {
						try {
							Layout scopeLayout =
								LayoutLocalServiceUtil.getLayout(
									group.getClassPK());

							_xmlStreamWriter.writeAttribute(
								"scope-layout-uuid", scopeLayout.getUuid());
						}
						catch (NoSuchLayoutException nsle) {
							_log.error(nsle.getMessage(), nsle);
						}
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}

				_xmlStreamWriter.writeAttribute("type", referenceType);

				_xmlStreamWriter.writeAttribute("uuid", stagedModel.getUuid());
				_xmlStreamWriter.writeAttribute(
					"company-id", String.valueOf(stagedModel.getCompanyId()));

				if (missing) {
					_xmlStreamWriter.writeAttribute("missing", "true");
				}

				Map<String, String> referenceAttributes =
					StagedModelDataHandlerUtil.getReferenceAttributes(
						_portletDataContext, stagedModel);

				for (Map.Entry<String, String> referenceAttribute :
						referenceAttributes.entrySet()) {

					_xmlStreamWriter.writeAttribute(
						referenceAttribute.getKey(),
						referenceAttribute.getValue());
				}
			}

			_xmlStreamWriter.writeEndElement();
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);
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

	@Override
	public void writeStructureFieldsElement(
		String ddmFormValuesPath, String structureUuid) {

		if (_isWriteEventCompleted(_STAGED_MODEL_START)) {
			try {
				_xmlStreamWriter.writeStartElement("structure-fields");
				_xmlStreamWriter.writeAttribute(
					"ddmFormValuesPath", ddmFormValuesPath);
				_xmlStreamWriter.writeAttribute("structureUuid", structureUuid);
				_xmlStreamWriter.writeEndElement();
			}
			catch (Exception e) {
				_log.error(e.getMessage(), e);
			}
		}
	}

	private void _closeReferenceXMLWriter() {
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
			_log.error("Error during closing reference xml writer: ", e);
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

	private static final int _REFERENCE_END = 5;

	private static final int _REFERENCE_START = 4;

	private static final int _STAGED_MODEL_END = 3;

	private static final int _STAGED_MODEL_START = 2;

	private static final Log _log = LogFactoryUtil.getLog(LARFileImpl.class);

	private OutputStream _outputStream;
	private final PortletDataContext _portletDataContext;
	private OutputStream _referenceOutputStream;
	private XMLStreamWriter _referenceXmlStreamWriter;
	private boolean[] _writeEventArray = new boolean[6];
	private XMLStreamWriter _xmlStreamWriter;

}