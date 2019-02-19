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

package com.liferay.asset.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AssetCategoryVersion service. Represents a row in the &quot;AssetCategoryVersion&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AssetCategoryVersionModel
 * @generated
 */
@ImplementationClassName("com.liferay.portlet.asset.model.impl.AssetCategoryVersionImpl")
@ProviderType
public interface AssetCategoryVersion extends AssetCategoryVersionModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.portlet.asset.model.impl.AssetCategoryVersionImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AssetCategoryVersion, Long> ASSET_CATEGORY_VERSION_ID_ACCESSOR =
		new Accessor<AssetCategoryVersion, Long>() {
			@Override
			public Long get(AssetCategoryVersion assetCategoryVersion) {
				return assetCategoryVersion.getAssetCategoryVersionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssetCategoryVersion> getTypeClass() {
				return AssetCategoryVersion.class;
			}
		};

	public static final Accessor<AssetCategoryVersion, Long> CATEGORY_ID_ACCESSOR =
		new Accessor<AssetCategoryVersion, Long>() {
			@Override
			public Long get(AssetCategoryVersion assetCategoryVersion) {
				return assetCategoryVersion.getCategoryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssetCategoryVersion> getTypeClass() {
				return AssetCategoryVersion.class;
			}
		};

	public static final Accessor<AssetCategoryVersion, String> NAME_ACCESSOR = new Accessor<AssetCategoryVersion, String>() {
			@Override
			public String get(AssetCategoryVersion assetCategoryVersion) {
				return assetCategoryVersion.getName();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<AssetCategoryVersion> getTypeClass() {
				return AssetCategoryVersion.class;
			}
		};
}