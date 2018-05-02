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

package com.liferay.document.library.internal.util;

import com.liferay.document.library.configuration.DLConfiguration;
import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.exception.FolderNameException;
import com.liferay.document.library.kernel.exception.InvalidFileVersionException;
import com.liferay.document.library.kernel.exception.SourceFileNameException;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.document.library.kernel.util.DLValidator;
import com.liferay.exportimport.kernel.exception.DLDataException;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.upload.UploadServletRequestConfigurationHelper;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.documentlibrary.webdav.DLWebDAVUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	configurationPid = "com.liferay.document.library.configuration.DLConfiguration",
	immediate = true, service = DLValidator.class
)
public final class DLValidatorImpl implements DLValidator {

	@Override
	public String fixName(String name) {
		if (Validator.isNull(name)) {
			return StringPool.UNDERLINE;
		}

		for (String blacklistChar : PropsValues.DL_CHAR_BLACKLIST) {
			name = StringUtil.replace(
				name, blacklistChar, StringPool.UNDERLINE);
		}

		name = replaceDLCharLastBlacklist(name);

		return replaceDLNameBlacklist(name);
	}

	@Override
	public long getMaxAllowableSize() {
		long fileMaxSize = _dlConfiguration.fileMaxSize();

		if (fileMaxSize == 0) {
			fileMaxSize = _uploadServletRequestConfigurationHelper.getMaxSize();
		}

		return fileMaxSize;
	}

	@Override
	public boolean isValidName(String name) {
		if (Validator.isNull(name)) {
			return false;
		}

		for (String blacklistChar : PropsValues.DL_CHAR_BLACKLIST) {
			if (name.contains(blacklistChar)) {
				return false;
			}
		}

		for (String blacklistLastChar : PropsValues.DL_CHAR_LAST_BLACKLIST) {
			if (blacklistLastChar.startsWith(UnicodeFormatter.UNICODE_PREFIX)) {
				blacklistLastChar = UnicodeFormatter.parseString(
					blacklistLastChar);
			}

			if (name.endsWith(blacklistLastChar)) {
				return false;
			}
		}

		String nameWithoutExtension = FileUtil.stripExtension(name);

		for (String blacklistName : PropsValues.DL_NAME_BLACKLIST) {
			if (StringUtil.equalsIgnoreCase(
					nameWithoutExtension, blacklistName)) {

				return false;
			}
		}

		return true;
	}

	@Override
	public void validateDirectoryName(String directoryName)
		throws FolderNameException {

		if (!isValidName(directoryName)) {
			DLDataException dde = new DLDataException(
				DLDataException.INVALID_FOLDER_NAME);

			dde.setData(new String[] {directoryName});

			throw new FolderNameException(directoryName, dde);
		}
	}

	@Override
	public void validateFileExtension(String fileName)
		throws FileExtensionException {

		boolean validFileExtension = false;

		String[] fileExtensions = _dlConfiguration.fileExtensions();

		for (String fileExtension : fileExtensions) {
			if (StringPool.STAR.equals(fileExtension) ||
				StringUtil.endsWith(fileName, fileExtension)) {

				validFileExtension = true;

				break;
			}
		}

		if (!validFileExtension) {
			DLDataException dde = new DLDataException(
				DLDataException.INVALID_FILE_NAME);

			dde.setData(new String[] {fileName});

			throw new FileExtensionException(fileName, dde);
		}
	}

	@Override
	public void validateFileName(String fileName) throws FileNameException {
		if (!isValidName(fileName)) {
			DLDataException dde = new DLDataException(
				DLDataException.INVALID_FILE_NAME);

			dde.setData(new String[] {fileName});

			throw new FileNameException(fileName, dde);
		}

		if (!DLWebDAVUtil.isRepresentableTitle(fileName)) {
			throw new FileNameException(
				"Unrepresentable WebDAV title for file name " + fileName);
		}
	}

	@Override
	public void validateFileSize(String fileName, byte[] bytes)
		throws FileSizeException {

		if (bytes == null) {
			DLDataException dde = new DLDataException(
				DLDataException.INVALID_FILE_SIZE);

			dde.setData(new String[] {fileName});

			throw new FileSizeException(fileName, dde);
		}

		validateFileSize(fileName, bytes.length);
	}

	@Override
	public void validateFileSize(String fileName, File file)
		throws FileSizeException {

		if (file == null) {
			DLDataException dde = new DLDataException(
				DLDataException.INVALID_FILE_SIZE);

			dde.setData(new String[] {fileName});
			throw new FileSizeException(fileName, dde);
		}

		validateFileSize(fileName, file.length());
	}

	@Override
	public void validateFileSize(String fileName, InputStream is)
		throws FileSizeException {

		try {
			if (is == null) {
				DLDataException dde = new DLDataException(
					DLDataException.INVALID_FILE_SIZE);

				dde.setData(new String[] {fileName});

				throw new FileSizeException(fileName, dde);
			}

			validateFileSize(fileName, is.available());
		}
		catch (IOException ioe) {
			DLDataException dde = new DLDataException(
				DLDataException.INVALID_FILE_SIZE, ioe);

			dde.setData(new String[] {fileName});

			throw new FileSizeException(dde);
		}
	}

	@Override
	public void validateFileSize(String fileName, long size)
		throws FileSizeException {

		long maxSize = _dlConfiguration.fileMaxSize();

		if ((maxSize > 0) && (size > maxSize)) {
			DLDataException dde = new DLDataException(
				DLDataException.INVALID_FILE_SIZE);

			dde.setData(new String[] {
				String.valueOf(size), String.valueOf(maxSize), fileName
			});

			throw new FileSizeException(
				StringBundler.concat(
					String.valueOf(size),
					" exceeds the maximum permitted size of ",
					String.valueOf(maxSize), " for file ", fileName),
				dde);
		}
	}

	@Override
	public void validateSourceFileExtension(
			String fileExtension, String sourceFileName)
		throws SourceFileNameException {

		String sourceFileExtension = FileUtil.getExtension(sourceFileName);

		if (Validator.isNotNull(sourceFileName) &&
			PropsValues.DL_FILE_EXTENSIONS_STRICT_CHECK &&
			!fileExtension.equals(sourceFileExtension)) {

			DLDataException dde = new DLDataException(
				DLDataException.INVALID_SOURCE_FIlE_EXTENSION);

			dde.setData(new String[] {
				sourceFileName, sourceFileExtension, fileExtension
			});

			throw new SourceFileNameException(sourceFileExtension, dde);
		}
	}

	@Override
	public void validateVersionLabel(String versionLabel)
		throws InvalidFileVersionException {

		if (Validator.isNull(versionLabel)) {
			return;
		}

		if (!DLUtil.isValidVersion(versionLabel)) {
			DLDataException dde = new DLDataException(
				DLDataException.INVALID_FILE_VERSION_LABEL);

			dde.setData(new String[] {versionLabel, "invalid"});

			throw new InvalidFileVersionException(
				"Invalid version label " + versionLabel, dde);
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_dlConfiguration = ConfigurableUtil.createConfigurable(
			DLConfiguration.class, properties);
	}

	protected String replaceDLCharLastBlacklist(String title) {
		String previousTitle = null;

		while (!title.equals(previousTitle)) {
			previousTitle = title;

			for (String blacklistLastChar :
					PropsValues.DL_CHAR_LAST_BLACKLIST) {

				if (blacklistLastChar.startsWith(
						UnicodeFormatter.UNICODE_PREFIX)) {

					blacklistLastChar = UnicodeFormatter.parseString(
						blacklistLastChar);
				}

				if (title.endsWith(blacklistLastChar)) {
					title = StringUtil.replaceLast(
						title, blacklistLastChar, StringPool.BLANK);
				}
			}
		}

		return title;
	}

	protected String replaceDLNameBlacklist(String title) {
		String extension = FileUtil.getExtension(title);
		String nameWithoutExtension = FileUtil.stripExtension(title);

		for (String blacklistName : PropsValues.DL_NAME_BLACKLIST) {
			if (StringUtil.equalsIgnoreCase(
					nameWithoutExtension, blacklistName)) {

				if (Validator.isNull(extension)) {
					return nameWithoutExtension + StringPool.UNDERLINE;
				}

				return StringBundler.concat(
					nameWithoutExtension, StringPool.UNDERLINE,
					StringPool.PERIOD, extension);
			}
		}

		return title;
	}

	private volatile DLConfiguration _dlConfiguration;

	@Reference
	private UploadServletRequestConfigurationHelper
		_uploadServletRequestConfigurationHelper;

}