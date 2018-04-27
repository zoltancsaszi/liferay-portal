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

package com.liferay.exportimport.kernel.exception;

import com.liferay.exportimport.kernel.lar.PortletDataException;

/**
 * @author Zoltan Csaszi
 */
public class DLDataException extends PortletDataException {

	public static final int CMIS_FILE_NAME_IS_NULL = 990;
	public static final int CMIS_FILE_DUPLICATED = 991;
	public static final int CMIS_FOLDER_DUPLICATED = 992;
	public static final int INVALID_FOLDER_NAME = 91;
	public static final int INVALID_FILE_NAME = 92;
	public static final int INVALID_FILE_SIZE = 93;
	public static final int INVALID_SOURCE_FIlE_EXTENSION = 94;
	public static final int INVALID_FILE_VERSION_LABEL = 95;
	public static final int INVALID_ROOT_FOLDER = 96;
	public static final int UNABLE_TO_UPDATE_PORTLET_PREFERENCES = 97;
	public static final int FILE_TITLE_IS_NULL = 98;
	public static final int PWC_FILE_VERSION = 99;
	public static final int IMAGE_SIZE_EXCEPTION = 100;
	public static final int NO_SUCH_FOLDER = 101;
	public static final int DUPLICATED_FOLDER_NAME = 102;
	public static final int DUPLICATED_FILE_NAME = 103;
	public static final int INVALID_FILE_ENTRY_TYPE = 104;
	public static final int FILE_EXTENSION_EXCEEDS_LIMIT = 105;
	public static final int REQUIRED_FILE_ENTRY_TYPE = 106;
	public static final int DUPLICATED_FILE_ENTRY_TYPE = 107;
	public static final int NO_SUCH_METADATA_SET = 108;

	public DLDataException(int type) {
		setType(type);
	}

	public DLDataException(int type, Throwable cause) {
		super(cause);
		setType(type);
	}

	public String[] getData() {
		return _data;
	}

	public void setData(String[] data) {
		_data = data;
	}

	private String[] _data;
}
