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

package com.liferay.change.tracking.service.persistence.impl;

import com.liferay.change.tracking.model.CTProcess;
import com.liferay.change.tracking.model.impl.CTProcessImpl;
import com.liferay.change.tracking.service.persistence.CTProcessFinder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Máté Thurzó
 */
public class CTProcessFinderImpl
	extends CTProcessFinderBaseImpl implements CTProcessFinder {

	public static final String FIND_BY_C_S =
		CTProcessFinder.class.getName() + ".findByC_S";

	@Override
	public List<CTProcess> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<?> orderByComparator) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_C_S);

			sql = _customSQL.replaceOrderBy(sql, orderByComparator);

			sql = StringUtil.replace(
				sql, "(CTProcess.companyId = ?) AND",
				"(CTProcess.companyId = ?)");

			sql = StringUtil.replace(
				sql, "(BackgroundTask.status = ?)", StringPool.BLANK);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("CTProcess", CTProcessImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<CTProcess>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CTProcess> findByC_S(
		long companyId, int status, int start, int end,
		OrderByComparator<?> orderByComparator) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_C_S);

			sql = _customSQL.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("CTProcess", CTProcessImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			qPos.add(status);

			return (List<CTProcess>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}