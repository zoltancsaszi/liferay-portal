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

package com.liferay.portal.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the LayoutSetVersion service. Represents a row in the &quot;LayoutSetVersion&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetVersionModel
 * @see com.liferay.portal.model.impl.LayoutSetVersionImpl
 * @see com.liferay.portal.model.impl.LayoutSetVersionModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.portal.model.impl.LayoutSetVersionImpl")
@ProviderType
public interface LayoutSetVersion extends LayoutSetVersionModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.model.impl.LayoutSetVersionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LayoutSetVersion, Long> LAYOUT_SET_VERSION_ID_ACCESSOR =
		new Accessor<LayoutSetVersion, Long>() {
			@Override
			public Long get(LayoutSetVersion layoutSetVersion) {
				return layoutSetVersion.getLayoutSetVersionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LayoutSetVersion> getTypeClass() {
				return LayoutSetVersion.class;
			}
		};

	public com.liferay.portal.kernel.util.UnicodeProperties getSettingsProperties();

	public void setSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties settingsProperties);
}