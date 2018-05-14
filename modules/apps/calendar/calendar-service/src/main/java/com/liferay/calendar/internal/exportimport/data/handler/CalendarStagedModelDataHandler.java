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

package com.liferay.calendar.internal.exportimport.data.handler;

import com.liferay.calendar.constants.CalendarPortletKeys;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarLocalService;
import com.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 * @author Daniel Kocsis
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + CalendarPortletKeys.CALENDAR,
	service = StagedModelDataHandler.class
)
public class CalendarStagedModelDataHandler
	extends BaseStagedModelDataHandler<Calendar> {

	public static final String[] CLASS_NAMES = {Calendar.class.getName()};

	@Override
	public void deleteStagedModel(Calendar calendar) throws PortalException {
		_calendarLocalService.deleteCalendar(calendar);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		Calendar calendar = fetchStagedModelByUuidAndGroupId(uuid, groupId);

		if (calendar != null) {
			deleteStagedModel(calendar);
		}
	}

	@Override
	public Calendar fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return _calendarLocalService.fetchCalendarByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<Calendar> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return _calendarLocalService.getCalendarsByUuidAndCompanyId(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new StagedModelModifiedDateComparator<Calendar>());
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(Calendar calendar) {
		return calendar.getNameCurrentValue();
	}

	@Override
	public boolean validateReference(
		PortletDataContext portletDataContext, Element referenceElement) {

		validateMissingGroupReference(portletDataContext, referenceElement);

		long companyId = GetterUtil.getLong(
			referenceElement.attributeValue("company-id"));

		String uuid = referenceElement.attributeValue("uuid");

		String displayName = referenceElement.attributeValue("display-name");

		Map<Long, Long> groupIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Group.class);

		long groupId = GetterUtil.getLong(
			referenceElement.attributeValue("group-id"));

		groupId = MapUtil.getLong(groupIds, groupId);

		try {
			return validateMissingReference(
				uuid, displayName, companyId, groupId);
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, Calendar calendar)
		throws Exception {

		CalendarResource calendarResource = calendar.getCalendarResource();

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, calendar, calendarResource,
			PortletDataContext.REFERENCE_TYPE_STRONG);

		String calendarName = calendar.getName(LocaleUtil.getDefault());

		Group group = _groupLocalService.getGroup(calendar.getGroupId());

		Element calendarElement = portletDataContext.getExportDataElement(
			calendar);

		if (!Objects.equals(calendarName, group.getDescriptiveName()) ||
			!calendarResource.isGroup()) {

			calendarElement.addAttribute("keepCalendarName", "true");
		}

		portletDataContext.addClassedModel(
			calendarElement, ExportImportPathUtil.getModelPath(calendar),
			calendar);
	}

	@Override
	protected void doImportMissingReference(
			PortletDataContext portletDataContext, String uuid, long groupId,
			long calendarId)
		throws Exception {

		Calendar existingCalendar = fetchMissingReference(uuid, groupId);

		if (existingCalendar == null) {
			return;
		}

		Map<Long, Long> calendarIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Calendar.class);

		calendarIds.put(calendarId, existingCalendar.getCalendarId());
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, Calendar calendar)
		throws Exception {

		long userId = portletDataContext.getUserId(calendar.getUserUuid());

		Map<Long, Long> calendarResourceIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				CalendarResource.class);

		long calendarResourceId = MapUtil.getLong(
			calendarResourceIds, calendar.getCalendarResourceId(),
			calendar.getCalendarResourceId());

		Map<Locale, String> calendarNameMap = getCalendarNameMap(
			portletDataContext, calendar);

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			calendar);

		Calendar importedCalendar = null;

		if (portletDataContext.isDataStrategyMirror()) {
			Calendar existingCalendar = fetchStagedModelByUuidAndGroupId(
				calendar.getUuid(), portletDataContext.getScopeGroupId());

			if (existingCalendar == null) {
				serviceContext.setUuid(calendar.getUuid());

				importedCalendar = _calendarLocalService.addCalendar(
					userId, portletDataContext.getScopeGroupId(),
					calendarResourceId, calendarNameMap,
					calendar.getDescriptionMap(), calendar.getTimeZoneId(),
					calendar.getColor(), calendar.isDefaultCalendar(),
					calendar.isEnableComments(), calendar.isEnableRatings(),
					serviceContext);
			}
			else {
				importedCalendar = _calendarLocalService.updateCalendar(
					existingCalendar.getCalendarId(), calendarNameMap,
					calendar.getDescriptionMap(), calendar.getTimeZoneId(),
					calendar.getColor(), calendar.isDefaultCalendar(),
					calendar.isEnableComments(), calendar.isEnableRatings(),
					serviceContext);
			}
		}
		else {
			importedCalendar = _calendarLocalService.addCalendar(
				userId, portletDataContext.getScopeGroupId(),
				calendarResourceId, calendarNameMap,
				calendar.getDescriptionMap(), calendar.getTimeZoneId(),
				calendar.getColor(), calendar.isDefaultCalendar(),
				calendar.isEnableComments(), calendar.isEnableRatings(),
				serviceContext);
		}

		portletDataContext.importClassedModel(calendar, importedCalendar);
	}

	protected Calendar fetchExistingCalendar(
		long companyId, long groupId, String name) {

		return _calendarLocalService.fetchGroupCalendarByName(
			companyId, groupId, name);
	}

	protected Map<Locale, String> getCalendarNameMap(
			PortletDataContext portletDataContext, Calendar calendar)
		throws Exception {

		Element element = portletDataContext.getImportDataStagedModelElement(
			calendar);

		boolean keepCalendarName = GetterUtil.getBoolean(
			element.attributeValue("keepCalendarName"));

		if (keepCalendarName) {
			return calendar.getNameMap();
		}

		Map<Locale, String> calendarNameMap = new HashMap<>();

		Group scopeGroup = _groupLocalService.getGroup(
			portletDataContext.getScopeGroupId());

		calendarNameMap.put(
			LocaleUtil.getDefault(), scopeGroup.getDescriptiveName());

		return calendarNameMap;
	}

	@Reference(unbind = "-")
	protected void setCalendarLocalService(
		CalendarLocalService calendarLocalService) {

		_calendarLocalService = calendarLocalService;
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	protected boolean validateMissingReference(
		String uuid, String displayName, long companyId, long groupId) {

		Calendar existingStagedModel = fetchMissingReference(uuid, groupId);

		if (existingStagedModel == null) {
			existingStagedModel = fetchExistingCalendar(
				companyId, groupId, displayName);
		}

		if (existingStagedModel == null) {
			return false;
		}

		return true;
	}

	private CalendarLocalService _calendarLocalService;
	private GroupLocalService _groupLocalService;

}