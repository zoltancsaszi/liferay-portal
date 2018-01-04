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

package com.liferay.exportimport.kernel.lar.file;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author Mate Thurzo
 */
public class LARFileFactoryUtil {

	public static LARFile getLARFile(PortletDataContext portletDataContext) {
		LARFile larFile = _larFile.get();

		if (larFile == null) {
			larFile = _larFileFactory.getLARFile(portletDataContext);

			_larFile.set(larFile);
		}

		return larFile;
	}

	private static final ThreadLocal<LARFile> _larFile =
		new CentralizedThreadLocal<>(
			LARFileFactoryUtil.class + "._larFile", null);
	private static volatile LARFileFactory _larFileFactory =
		ServiceProxyFactory.newServiceTrackedInstance(
			LARFileFactory.class, LARFileFactoryUtil.class, "_larFileFactory",
			false);

}