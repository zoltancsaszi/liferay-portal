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

package com.liferay.portal.background.task.service.persistence.impl;

import com.liferay.portal.background.task.model.BackgroundTask;
import com.liferay.portal.background.task.model.impl.BackgroundTaskImpl;
import com.liferay.portal.background.task.service.persistence.BackgroundTaskFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Iterator;
import java.util.List;

/**
 * @author Zoltan Csaszi
 */
public class BackgroundTaskFinderImpl
	extends BackgroundTaskFinderBaseImpl implements BackgroundTaskFinder {

	public static final String COUNT_BY_G_T_C =
		BackgroundTaskFinder.class.getName() + ".countByG_T_C";

	public static final String FIND_BY_G_T_C =
		BackgroundTaskFinder.class.getName() + ".findByG_T_C";

	@Override
	public int countByG_T_C(
		long[] groupIds, String[] taskExecutorClassNames, Boolean completed) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), COUNT_BY_G_T_C);

			sql = _replaceWhereConditions(
				groupIds, taskExecutorClassNames, sql, completed);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			for (long groupId : groupIds) {
				qPos.add(groupId);
			}

			for (String taskExecutorClassName : taskExecutorClassNames) {
				qPos.add(taskExecutorClassName);
			}

			if (completed != null) {
				qPos.add(completed.booleanValue());
			}

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<BackgroundTask> findByG_T_C(
		long[] groupIds, String[] taskExecutorClassNames, Boolean completed,
		int start, int end, boolean orderByType) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_G_T_C);

			sql = _replaceWhereConditions(
				groupIds, taskExecutorClassNames, sql, completed);

			sql = StringUtil.replace(
				sql, "[$ORDER_BY_TYPE$]", orderByType ? "ASC" : "DESC");

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("BackgroundTask", BackgroundTaskImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			for (long groupId : groupIds) {
				qPos.add(groupId);
			}

			for (String taskExecutorClassName : taskExecutorClassNames) {
				qPos.add(taskExecutorClassName);
			}

			if (completed != null) {
				qPos.add(completed.booleanValue());
			}

			return (List<BackgroundTask>)
				QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private String _getGroupCriteria(long[] groupIds) {
		StringBundler sb = new StringBundler();

		String result = StringPool.BLANK;

		if (groupIds.length > 0) {
			sb.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < groupIds.length; i++) {
				sb.append("(BackgroundTask.groupId = ?) OR ");
			}

			sb.append(StringPool.CLOSE_PARENTHESIS);

			result = StringUtil.replaceLast(
				sb.toString(), " OR ", StringPool.BLANK);
		}

		return result;
	}

	private String _getTaskExecutorClassNameCriteria(String[] classNames) {
		StringBundler sb = new StringBundler();

		String result = StringPool.BLANK;

		if (classNames.length > 0) {
			sb.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < classNames.length; i++) {
				sb.append("(BackgroundTask.taskExecutorClassName = ?) OR ");
			}

			sb.append(StringPool.CLOSE_PARENTHESIS);

			result = StringUtil.replaceLast(
				sb.toString(), " OR ", StringPool.BLANK);
		}

		return result;
	}

	private String _replaceWhereConditions(
		long[] groupIds, String[] taskExecutorClassNames, String sql,
		Boolean completed) {

		StringBundler sb = new StringBundler(5);

		String groupCriteria = GetterUtil.getString(
			_getGroupCriteria(groupIds));
		String taskExecutorCriteria = GetterUtil.getString(
			_getTaskExecutorClassNameCriteria(taskExecutorClassNames));

		sb.append(groupCriteria);

		if (!groupCriteria.isEmpty() && !taskExecutorCriteria.isEmpty()) {
			sb.append(" AND ");
		}

		sb.append(taskExecutorCriteria);

		if (completed != null) {
			if (Validator.isNotNull(groupCriteria) &&
				Validator.isNotNull(taskExecutorCriteria)) {

				sb.append(" AND ");
			}

			sb.append("(BackgroundTask.completed = ?)");
		}

		if (Validator.isNotNull(sb.toString())) {
			sql = StringUtil.replace(
				sql, "[$WHERE_CONDITIONS$]", "WHERE " + sb.toString());
		}
		else {
			sql = StringUtil.replace(
				sql, "[$WHERE_CONDITIONS$]", StringPool.BLANK);
		}

		return sql;
	}

}