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

package com.liferay.portal.upgrade.v7_2_x;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Zoltan Csaszi
 */
public class UpgradeLayoutSet extends UpgradeProcess {

	protected void insertLayoutSetResources() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			runSQL("insert into LayoutSetResource(layoutSetResourceId, " +
				   "groupId, companyId, createDate, modifiedDate, pageCount, " +
				   "privateLayout) select layoutSetId, groupId, companyId, " +
				   "createDate, modifiedDate, pageCount, privateLayout " +
				   "from LayoutSet");
		}
	}

	protected void insertLayoutSetVersion() throws Exception {
		StringBundler sb = new StringBundler();

		sb.append("select layoutSetId, groupId, companyId, createDate, ");
		sb.append("modifiedDate, privateLayout, logoId, themeId, ");
		sb.append("colorSchemeId, css, pageCount, settings_, ");
		sb.append("layoutSetPrototypeUuid, layoutSetPrototypeLinkEnabled ");
		sb.append("from LayoutSet");


		StringBundler insertSb = new StringBundler();

		insertSb.append("insert into LayoutSetVersion(layoutSetVersionId, ");
		insertSb.append("companyId, createDate, modifiedDate, ");
		insertSb.append("layoutSetResourceId, logoId, themeId, colorSchemeId,");
		insertSb.append(" css, settings_, layoutSetPrototypeUuid, ");
		insertSb.append("layoutSetPrototypeLinkEnabled) values ");
		insertSb.append("(?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?)");

		try (LoggingTimer loggingTimer = new LoggingTimer();
			 Statement s = connection.createStatement();
			 ResultSet rs = s.executeQuery(sb.toString());
			 PreparedStatement ps = AutoBatchPreparedStatementUtil.autoBatch(
				 connection.prepareStatement(insertSb.toString()))) {

			while (rs.next()) {
				long layoutSetId = rs.getLong("layoutSetId");
				long companyId = rs.getLong("companyId");
				Date createDate = rs.getDate("createDate");

				//TODO: LayoutSetVersion missing modifiedDate
				Date modifiedDate = rs.getDate("modifiedDate");
				long logoId = rs.getLong("logoId");
				String themeId = rs.getString("themeId");
				String colorSchemeId = rs.getString("colorSchemeId");
				String css = rs.getString("css");
				String settings = rs.getString("settings_");
				String layoutSetPrototypeUuid =
					rs.getString("layoutSetPrototypeUuid");
				boolean layoutSetPrototypeLinkEnabled =
					rs.getBoolean("layoutSetPrototypeLinkEnabled");

				ps.setLong(1, _increment());
				ps.setLong(2, companyId);
				ps.setDate(3, createDate);
				ps.setDate(4, modifiedDate);
				ps.setLong(5, layoutSetId);
				ps.setLong(6, logoId);
				ps.setString(7, themeId);
				ps.setString(8, colorSchemeId);
				ps.setString(9, css);
				ps.setString(10, settings);
				ps.setString(11, layoutSetPrototypeUuid);
				ps.setBoolean(12, layoutSetPrototypeLinkEnabled);

				ps.addBatch();
			}

			ps.executeBatch();
		}
	}

	private static long _increment() {
		DB db = DBManagerUtil.getDB();

		return db.increment("LayoutSet");
	}

	protected void dropLayoutTable() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			runSQL("drop table LayoutSet");
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		System.out.println("updateLayoutSet");
		insertLayoutSetResources();
		insertLayoutSetVersion();
		dropLayoutTable();
	}

}