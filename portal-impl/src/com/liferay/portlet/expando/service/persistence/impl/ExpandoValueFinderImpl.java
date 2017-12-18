package com.liferay.portlet.expando.service.persistence.impl;

import com.liferay.expando.kernel.service.persistence.ExpandoValueFinder;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

public class ExpandoValueFinderImpl extends ExpandoValueFinderBaseImpl
	implements ExpandoValueFinder {

	public static final String FIND_BY_CLASS_PK =
		ExpandoValueFinder.class.getName() + ".findByClassPk";

	@Override
	public List<String> getExpandoData(long classNameId, long classPk) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_CLASS_PK);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("columnValue", Type.STRING);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(classNameId);
			qPos.add(classPk);

			List results = QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			return results;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}
}
