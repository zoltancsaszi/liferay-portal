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

package com.liferay.portal.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.LayoutServiceUtil;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link LayoutServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutServiceSoap
 * @see HttpPrincipal
 * @see LayoutServiceUtil
 * @generated
 */
@ProviderType
public class LayoutServiceHttp {
	public static com.liferay.portal.kernel.model.Layout addLayout(
		HttpPrincipal httpPrincipal, long groupId, boolean privateLayout,
		long parentLayoutId,
		java.util.Map<java.util.Locale, String> localeNamesMap,
		java.util.Map<java.util.Locale, String> localeTitlesMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Map<java.util.Locale, String> keywordsMap,
		java.util.Map<java.util.Locale, String> robotsMap, String type,
		String typeSettings, boolean hidden,
		java.util.Map<java.util.Locale, String> friendlyURLMap,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"addLayout", _addLayoutParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, parentLayoutId, localeNamesMap,
					localeTitlesMap, descriptionMap, keywordsMap, robotsMap,
					type, typeSettings, hidden, friendlyURLMap, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Layout)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Layout addLayout(
		HttpPrincipal httpPrincipal, long groupId, boolean privateLayout,
		long parentLayoutId, String name, String title, String description,
		String type, boolean hidden, String friendlyURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"addLayout", _addLayoutParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, parentLayoutId, name, title, description,
					type, hidden, friendlyURL, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Layout)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.repository.model.FileEntry addTempFileEntry(
		HttpPrincipal httpPrincipal, long groupId, String folderName,
		String fileName, java.io.InputStream inputStream, String mimeType)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"addTempFileEntry", _addTempFileEntryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderName, fileName, inputStream, mimeType);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.repository.model.FileEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteLayout(HttpPrincipal httpPrincipal, long groupId,
		boolean privateLayout, long layoutId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"deleteLayout", _deleteLayoutParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, layoutId, serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteLayout(HttpPrincipal httpPrincipal, long plid,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"deleteLayout", _deleteLayoutParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, plid,
					serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteTempFileEntry(HttpPrincipal httpPrincipal,
		long groupId, String folderName, String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"deleteTempFileEntry", _deleteTempFileEntryParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderName, fileName);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static byte[] exportLayouts(HttpPrincipal httpPrincipal,
		long groupId, boolean privateLayout, long[] layoutIds,
		java.util.Map<String, String[]> parameterMap, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"exportLayouts", _exportLayoutsParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, layoutIds, parameterMap, startDate, endDate);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (byte[])returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static byte[] exportLayouts(HttpPrincipal httpPrincipal,
		long groupId, boolean privateLayout,
		java.util.Map<String, String[]> parameterMap, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"exportLayouts", _exportLayoutsParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, parameterMap, startDate, endDate);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (byte[])returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.io.File exportLayoutsAsFile(
		HttpPrincipal httpPrincipal,
		com.liferay.exportimport.kernel.model.ExportImportConfiguration exportImportConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"exportLayoutsAsFile", _exportLayoutsAsFileParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					exportImportConfiguration);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.io.File)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.io.File exportLayoutsAsFile(
		HttpPrincipal httpPrincipal, long groupId, boolean privateLayout,
		long[] layoutIds, java.util.Map<String, String[]> parameterMap,
		java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"exportLayoutsAsFile", _exportLayoutsAsFileParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, layoutIds, parameterMap, startDate, endDate);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.io.File)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long exportLayoutsAsFileInBackground(
		HttpPrincipal httpPrincipal,
		com.liferay.exportimport.kernel.model.ExportImportConfiguration exportImportConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"exportLayoutsAsFileInBackground",
					_exportLayoutsAsFileInBackgroundParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					exportImportConfiguration);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long exportLayoutsAsFileInBackground(
		HttpPrincipal httpPrincipal, long exportImportConfigurationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"exportLayoutsAsFileInBackground",
					_exportLayoutsAsFileInBackgroundParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					exportImportConfigurationId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long exportLayoutsAsFileInBackground(
		HttpPrincipal httpPrincipal, String taskName, long groupId,
		boolean privateLayout, long[] layoutIds,
		java.util.Map<String, String[]> parameterMap, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"exportLayoutsAsFileInBackground",
					_exportLayoutsAsFileInBackgroundParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					taskName, groupId, privateLayout, layoutIds, parameterMap,
					startDate, endDate);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long exportLayoutsAsFileInBackground(
		HttpPrincipal httpPrincipal, String taskName, long groupId,
		boolean privateLayout, long[] layoutIds,
		java.util.Map<String, String[]> parameterMap, java.util.Date startDate,
		java.util.Date endDate, String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"exportLayoutsAsFileInBackground",
					_exportLayoutsAsFileInBackgroundParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					taskName, groupId, privateLayout, layoutIds, parameterMap,
					startDate, endDate, fileName);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static byte[] exportPortletInfo(HttpPrincipal httpPrincipal,
		long plid, long groupId, String portletId,
		java.util.Map<String, String[]> parameterMap, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"exportPortletInfo", _exportPortletInfoParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey, plid,
					groupId, portletId, parameterMap, startDate, endDate);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (byte[])returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static byte[] exportPortletInfo(HttpPrincipal httpPrincipal,
		long companyId, String portletId,
		java.util.Map<String, String[]> parameterMap, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"exportPortletInfo", _exportPortletInfoParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, portletId, parameterMap, startDate, endDate);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (byte[])returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.io.File exportPortletInfoAsFile(
		HttpPrincipal httpPrincipal,
		com.liferay.exportimport.kernel.model.ExportImportConfiguration exportImportConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"exportPortletInfoAsFile",
					_exportPortletInfoAsFileParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					exportImportConfiguration);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.io.File)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.io.File exportPortletInfoAsFile(
		HttpPrincipal httpPrincipal, long plid, long groupId, String portletId,
		java.util.Map<String, String[]> parameterMap, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"exportPortletInfoAsFile",
					_exportPortletInfoAsFileParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey, plid,
					groupId, portletId, parameterMap, startDate, endDate);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.io.File)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.io.File exportPortletInfoAsFile(
		HttpPrincipal httpPrincipal, String portletId,
		java.util.Map<String, String[]> parameterMap, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"exportPortletInfoAsFile",
					_exportPortletInfoAsFileParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					portletId, parameterMap, startDate, endDate);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.io.File)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long exportPortletInfoAsFileInBackground(
		HttpPrincipal httpPrincipal, String taskName, long plid, long groupId,
		String portletId, java.util.Map<String, String[]> parameterMap,
		java.util.Date startDate, java.util.Date endDate, String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"exportPortletInfoAsFileInBackground",
					_exportPortletInfoAsFileInBackgroundParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					taskName, plid, groupId, portletId, parameterMap,
					startDate, endDate, fileName);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long exportPortletInfoAsFileInBackground(
		HttpPrincipal httpPrincipal, String taskName, String portletId,
		java.util.Map<String, String[]> parameterMap, java.util.Date startDate,
		java.util.Date endDate, String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"exportPortletInfoAsFileInBackground",
					_exportPortletInfoAsFileInBackgroundParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					taskName, portletId, parameterMap, startDate, endDate,
					fileName);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Layout> getAncestorLayouts(
		HttpPrincipal httpPrincipal, long plid)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"getAncestorLayouts", _getAncestorLayoutsParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(methodKey, plid);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Layout>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long getDefaultPlid(HttpPrincipal httpPrincipal,
		long groupId, long scopeGroupId, boolean privateLayout, String portletId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"getDefaultPlid", _getDefaultPlidParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					scopeGroupId, privateLayout, portletId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long getDefaultPlid(HttpPrincipal httpPrincipal,
		long groupId, long scopeGroupId, String portletId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"getDefaultPlid", _getDefaultPlidParameterTypes23);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					scopeGroupId, portletId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Layout getLayoutByUuidAndGroupId(
		HttpPrincipal httpPrincipal, String uuid, long groupId,
		boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"getLayoutByUuidAndGroupId",
					_getLayoutByUuidAndGroupIdParameterTypes24);

			MethodHandler methodHandler = new MethodHandler(methodKey, uuid,
					groupId, privateLayout);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Layout)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static String getLayoutName(HttpPrincipal httpPrincipal,
		long groupId, boolean privateLayout, long layoutId, String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"getLayoutName", _getLayoutNameParameterTypes25);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, layoutId, languageId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.LayoutReference[] getLayoutReferences(
		HttpPrincipal httpPrincipal, long companyId, String portletId,
		String preferencesKey, String preferencesValue) {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"getLayoutReferences", _getLayoutReferencesParameterTypes26);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, portletId, preferencesKey, preferencesValue);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.LayoutReference[])returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Layout> getLayouts(
		HttpPrincipal httpPrincipal, long groupId, boolean privateLayout) {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"getLayouts", _getLayoutsParameterTypes27);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Layout>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Layout> getLayouts(
		HttpPrincipal httpPrincipal, long groupId, boolean privateLayout,
		long parentLayoutId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"getLayouts", _getLayoutsParameterTypes28);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, parentLayoutId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Layout>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Layout> getLayouts(
		HttpPrincipal httpPrincipal, long groupId, boolean privateLayout,
		long parentLayoutId, boolean incomplete, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"getLayouts", _getLayoutsParameterTypes29);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, parentLayoutId, incomplete, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Layout>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Layout> getLayouts(
		HttpPrincipal httpPrincipal, long groupId, String type) {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"getLayouts", _getLayoutsParameterTypes30);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					type);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Layout>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Layout> getLayouts(
		HttpPrincipal httpPrincipal, long groupId, String type, int start,
		int end) {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"getLayouts", _getLayoutsParameterTypes31);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					type, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Layout>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getLayoutsCount(HttpPrincipal httpPrincipal,
		long groupId, boolean privateLayout) {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"getLayoutsCount", _getLayoutsCountParameterTypes32);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getLayoutsCount(HttpPrincipal httpPrincipal,
		long groupId, boolean privateLayout, long parentLayoutId) {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"getLayoutsCount", _getLayoutsCountParameterTypes33);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, parentLayoutId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getLayoutsCount(HttpPrincipal httpPrincipal,
		long groupId, boolean privateLayout, long parentLayoutId, int priority) {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"getLayoutsCount", _getLayoutsCountParameterTypes34);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, parentLayoutId, priority);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getLayoutsCount(HttpPrincipal httpPrincipal,
		long groupId, String type) {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"getLayoutsCount", _getLayoutsCountParameterTypes35);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					type);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static String[] getTempFileNames(HttpPrincipal httpPrincipal,
		long groupId, String folderName)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"getTempFileNames", _getTempFileNamesParameterTypes36);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderName);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (String[])returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static boolean hasLayout(HttpPrincipal httpPrincipal, String uuid,
		long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"hasLayout", _hasLayoutParameterTypes37);

			MethodHandler methodHandler = new MethodHandler(methodKey, uuid,
					groupId, privateLayout);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void importLayouts(HttpPrincipal httpPrincipal,
		com.liferay.exportimport.kernel.model.ExportImportConfiguration exportImportConfiguration,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importLayouts", _importLayoutsParameterTypes38);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					exportImportConfiguration, file);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void importLayouts(HttpPrincipal httpPrincipal,
		com.liferay.exportimport.kernel.model.ExportImportConfiguration exportImportConfiguration,
		java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importLayouts", _importLayoutsParameterTypes39);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					exportImportConfiguration, is);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void importLayouts(HttpPrincipal httpPrincipal, long groupId,
		boolean privateLayout, java.util.Map<String, String[]> parameterMap,
		byte[] bytes)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importLayouts", _importLayoutsParameterTypes40);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, parameterMap, bytes);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void importLayouts(HttpPrincipal httpPrincipal, long groupId,
		boolean privateLayout, java.util.Map<String, String[]> parameterMap,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importLayouts", _importLayoutsParameterTypes41);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, parameterMap, file);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void importLayouts(HttpPrincipal httpPrincipal, long groupId,
		boolean privateLayout, java.util.Map<String, String[]> parameterMap,
		java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importLayouts", _importLayoutsParameterTypes42);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, parameterMap, is);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long importLayoutsInBackground(HttpPrincipal httpPrincipal,
		String taskName, long groupId, boolean privateLayout,
		java.util.Map<String, String[]> parameterMap, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importLayoutsInBackground",
					_importLayoutsInBackgroundParameterTypes43);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					taskName, groupId, privateLayout, parameterMap, file);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long importLayoutsInBackground(HttpPrincipal httpPrincipal,
		String taskName, long groupId, boolean privateLayout,
		java.util.Map<String, String[]> parameterMap,
		java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importLayoutsInBackground",
					_importLayoutsInBackgroundParameterTypes44);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					taskName, groupId, privateLayout, parameterMap, inputStream);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void importPortletInfo(HttpPrincipal httpPrincipal,
		com.liferay.exportimport.kernel.model.ExportImportConfiguration exportImportConfiguration,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importPortletInfo", _importPortletInfoParameterTypes45);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					exportImportConfiguration, file);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void importPortletInfo(HttpPrincipal httpPrincipal,
		com.liferay.exportimport.kernel.model.ExportImportConfiguration exportImportConfiguration,
		java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importPortletInfo", _importPortletInfoParameterTypes46);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					exportImportConfiguration, is);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void importPortletInfo(HttpPrincipal httpPrincipal,
		long plid, long groupId, String portletId,
		java.util.Map<String, String[]> parameterMap, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importPortletInfo", _importPortletInfoParameterTypes47);

			MethodHandler methodHandler = new MethodHandler(methodKey, plid,
					groupId, portletId, parameterMap, file);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void importPortletInfo(HttpPrincipal httpPrincipal,
		long plid, long groupId, String portletId,
		java.util.Map<String, String[]> parameterMap, java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importPortletInfo", _importPortletInfoParameterTypes48);

			MethodHandler methodHandler = new MethodHandler(methodKey, plid,
					groupId, portletId, parameterMap, is);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void importPortletInfo(HttpPrincipal httpPrincipal,
		String portletId, java.util.Map<String, String[]> parameterMap,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importPortletInfo", _importPortletInfoParameterTypes49);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					portletId, parameterMap, file);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void importPortletInfo(HttpPrincipal httpPrincipal,
		String portletId, java.util.Map<String, String[]> parameterMap,
		java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importPortletInfo", _importPortletInfoParameterTypes50);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					portletId, parameterMap, is);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long importPortletInfoInBackground(
		HttpPrincipal httpPrincipal, String taskName, long plid, long groupId,
		String portletId, java.util.Map<String, String[]> parameterMap,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importPortletInfoInBackground",
					_importPortletInfoInBackgroundParameterTypes51);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					taskName, plid, groupId, portletId, parameterMap, file);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long importPortletInfoInBackground(
		HttpPrincipal httpPrincipal, String taskName, long plid, long groupId,
		String portletId, java.util.Map<String, String[]> parameterMap,
		java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importPortletInfoInBackground",
					_importPortletInfoInBackgroundParameterTypes52);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					taskName, plid, groupId, portletId, parameterMap, is);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void importPortletInfoInBackground(
		HttpPrincipal httpPrincipal, String taskName, String portletId,
		java.util.Map<String, String[]> parameterMap, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importPortletInfoInBackground",
					_importPortletInfoInBackgroundParameterTypes53);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					taskName, portletId, parameterMap, file);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void importPortletInfoInBackground(
		HttpPrincipal httpPrincipal, String taskName, String portletId,
		java.util.Map<String, String[]> parameterMap, java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"importPortletInfoInBackground",
					_importPortletInfoInBackgroundParameterTypes54);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					taskName, portletId, parameterMap, is);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void schedulePublishToLive(HttpPrincipal httpPrincipal,
		long sourceGroupId, long targetGroupId, boolean privateLayout,
		long[] layoutIds, java.util.Map<String, String[]> parameterMap,
		String scope, java.util.Date startDate, java.util.Date endDate,
		String groupName, String cronText, java.util.Date schedulerStartDate,
		java.util.Date schedulerEndDate, String description)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"schedulePublishToLive",
					_schedulePublishToLiveParameterTypes55);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					sourceGroupId, targetGroupId, privateLayout, layoutIds,
					parameterMap, scope, startDate, endDate, groupName,
					cronText, schedulerStartDate, schedulerEndDate, description);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void schedulePublishToLive(HttpPrincipal httpPrincipal,
		long sourceGroupId, long targetGroupId, boolean privateLayout,
		long[] layoutIds, java.util.Map<String, String[]> parameterMap,
		String groupName, String cronText, java.util.Date schedulerStartDate,
		java.util.Date schedulerEndDate, String description)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"schedulePublishToLive",
					_schedulePublishToLiveParameterTypes56);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					sourceGroupId, targetGroupId, privateLayout, layoutIds,
					parameterMap, groupName, cronText, schedulerStartDate,
					schedulerEndDate, description);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void schedulePublishToLive(HttpPrincipal httpPrincipal,
		long sourceGroupId, long targetGroupId, boolean privateLayout,
		java.util.Map<Long, Boolean> layoutIdMap,
		java.util.Map<String, String[]> parameterMap, String scope,
		java.util.Date startDate, java.util.Date endDate, String groupName,
		String cronText, java.util.Date schedulerStartDate,
		java.util.Date schedulerEndDate, String description)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"schedulePublishToLive",
					_schedulePublishToLiveParameterTypes57);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					sourceGroupId, targetGroupId, privateLayout, layoutIdMap,
					parameterMap, scope, startDate, endDate, groupName,
					cronText, schedulerStartDate, schedulerEndDate, description);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void schedulePublishToRemote(HttpPrincipal httpPrincipal,
		long sourceGroupId, boolean privateLayout,
		java.util.Map<Long, Boolean> layoutIdMap,
		java.util.Map<String, String[]> parameterMap, String remoteAddress,
		int remotePort, String remotePathContext, boolean secureConnection,
		long remoteGroupId, boolean remotePrivateLayout,
		java.util.Date startDate, java.util.Date endDate, String groupName,
		String cronText, java.util.Date schedulerStartDate,
		java.util.Date schedulerEndDate, String description)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"schedulePublishToRemote",
					_schedulePublishToRemoteParameterTypes58);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					sourceGroupId, privateLayout, layoutIdMap, parameterMap,
					remoteAddress, remotePort, remotePathContext,
					secureConnection, remoteGroupId, remotePrivateLayout,
					startDate, endDate, groupName, cronText,
					schedulerStartDate, schedulerEndDate, description);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void setLayouts(HttpPrincipal httpPrincipal, long groupId,
		boolean privateLayout, long parentLayoutId, long[] layoutIds,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"setLayouts", _setLayoutsParameterTypes59);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, parentLayoutId, layoutIds, serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void unschedulePublishToLive(HttpPrincipal httpPrincipal,
		long groupId, String jobName, String groupName)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"unschedulePublishToLive",
					_unschedulePublishToLiveParameterTypes60);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					jobName, groupName);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void unschedulePublishToRemote(HttpPrincipal httpPrincipal,
		long groupId, String jobName, String groupName)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"unschedulePublishToRemote",
					_unschedulePublishToRemoteParameterTypes61);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					jobName, groupName);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Layout updateIconImage(
		HttpPrincipal httpPrincipal, long plid, byte[] bytes)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"updateIconImage", _updateIconImageParameterTypes62);

			MethodHandler methodHandler = new MethodHandler(methodKey, plid,
					bytes);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Layout)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Layout updateLayout(
		HttpPrincipal httpPrincipal, long groupId, boolean privateLayout,
		long layoutId, long parentLayoutId,
		java.util.Map<java.util.Locale, String> localeNamesMap,
		java.util.Map<java.util.Locale, String> localeTitlesMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Map<java.util.Locale, String> keywordsMap,
		java.util.Map<java.util.Locale, String> robotsMap, String type,
		boolean hidden, java.util.Map<java.util.Locale, String> friendlyURLMap,
		boolean iconImage, byte[] iconBytes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"updateLayout", _updateLayoutParameterTypes63);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, layoutId, parentLayoutId, localeNamesMap,
					localeTitlesMap, descriptionMap, keywordsMap, robotsMap,
					type, hidden, friendlyURLMap, iconImage, iconBytes,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Layout)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Layout updateLayout(
		HttpPrincipal httpPrincipal, long groupId, boolean privateLayout,
		long layoutId, String typeSettings)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"updateLayout", _updateLayoutParameterTypes64);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, layoutId, typeSettings);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Layout)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Layout updateLookAndFeel(
		HttpPrincipal httpPrincipal, long groupId, boolean privateLayout,
		long layoutId, String themeId, String colorSchemeId, String css)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"updateLookAndFeel", _updateLookAndFeelParameterTypes65);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, layoutId, themeId, colorSchemeId, css);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Layout)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Layout updateName(
		HttpPrincipal httpPrincipal, long groupId, boolean privateLayout,
		long layoutId, String name, String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"updateName", _updateNameParameterTypes66);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, layoutId, name, languageId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Layout)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Layout updateName(
		HttpPrincipal httpPrincipal, long plid, String name, String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"updateName", _updateNameParameterTypes67);

			MethodHandler methodHandler = new MethodHandler(methodKey, plid,
					name, languageId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Layout)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Layout updateParentLayoutId(
		HttpPrincipal httpPrincipal, long groupId, boolean privateLayout,
		long layoutId, long parentLayoutId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"updateParentLayoutId",
					_updateParentLayoutIdParameterTypes68);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, layoutId, parentLayoutId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Layout)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Layout updateParentLayoutId(
		HttpPrincipal httpPrincipal, long plid, long parentPlid)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"updateParentLayoutId",
					_updateParentLayoutIdParameterTypes69);

			MethodHandler methodHandler = new MethodHandler(methodKey, plid,
					parentPlid);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Layout)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Layout updateParentLayoutIdAndPriority(
		HttpPrincipal httpPrincipal, long plid, long parentPlid, int priority)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"updateParentLayoutIdAndPriority",
					_updateParentLayoutIdAndPriorityParameterTypes70);

			MethodHandler methodHandler = new MethodHandler(methodKey, plid,
					parentPlid, priority);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Layout)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Layout updatePriority(
		HttpPrincipal httpPrincipal, long groupId, boolean privateLayout,
		long layoutId, int priority)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"updatePriority", _updatePriorityParameterTypes71);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, layoutId, priority);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Layout)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Layout updatePriority(
		HttpPrincipal httpPrincipal, long groupId, boolean privateLayout,
		long layoutId, long nextLayoutId, long previousLayoutId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"updatePriority", _updatePriorityParameterTypes72);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, layoutId, nextLayoutId, previousLayoutId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Layout)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Layout updatePriority(
		HttpPrincipal httpPrincipal, long plid, int priority)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"updatePriority", _updatePriorityParameterTypes73);

			MethodHandler methodHandler = new MethodHandler(methodKey, plid,
					priority);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Layout)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.exportimport.kernel.lar.MissingReferences validateImportLayoutsFile(
		HttpPrincipal httpPrincipal,
		com.liferay.exportimport.kernel.model.ExportImportConfiguration exportImportConfiguration,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"validateImportLayoutsFile",
					_validateImportLayoutsFileParameterTypes74);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					exportImportConfiguration, file);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.exportimport.kernel.lar.MissingReferences)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.exportimport.kernel.lar.MissingReferences validateImportLayoutsFile(
		HttpPrincipal httpPrincipal,
		com.liferay.exportimport.kernel.model.ExportImportConfiguration exportImportConfiguration,
		java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"validateImportLayoutsFile",
					_validateImportLayoutsFileParameterTypes75);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					exportImportConfiguration, inputStream);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.exportimport.kernel.lar.MissingReferences)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.exportimport.kernel.lar.MissingReferences validateImportLayoutsFile(
		HttpPrincipal httpPrincipal, long groupId, boolean privateLayout,
		java.util.Map<String, String[]> parameterMap, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"validateImportLayoutsFile",
					_validateImportLayoutsFileParameterTypes76);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, parameterMap, file);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.exportimport.kernel.lar.MissingReferences)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.exportimport.kernel.lar.MissingReferences validateImportLayoutsFile(
		HttpPrincipal httpPrincipal, long groupId, boolean privateLayout,
		java.util.Map<String, String[]> parameterMap,
		java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"validateImportLayoutsFile",
					_validateImportLayoutsFileParameterTypes77);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					privateLayout, parameterMap, inputStream);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.exportimport.kernel.lar.MissingReferences)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.exportimport.kernel.lar.MissingReferences validateImportPortletInfo(
		HttpPrincipal httpPrincipal,
		com.liferay.exportimport.kernel.model.ExportImportConfiguration exportImportConfiguration,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"validateImportPortletInfo",
					_validateImportPortletInfoParameterTypes78);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					exportImportConfiguration, file);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.exportimport.kernel.lar.MissingReferences)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.exportimport.kernel.lar.MissingReferences validateImportPortletInfo(
		HttpPrincipal httpPrincipal,
		com.liferay.exportimport.kernel.model.ExportImportConfiguration exportImportConfiguration,
		java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"validateImportPortletInfo",
					_validateImportPortletInfoParameterTypes79);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					exportImportConfiguration, inputStream);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.exportimport.kernel.lar.MissingReferences)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.exportimport.kernel.lar.MissingReferences validateImportPortletInfo(
		HttpPrincipal httpPrincipal, long plid, long groupId, String portletId,
		java.util.Map<String, String[]> parameterMap, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"validateImportPortletInfo",
					_validateImportPortletInfoParameterTypes80);

			MethodHandler methodHandler = new MethodHandler(methodKey, plid,
					groupId, portletId, parameterMap, file);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.exportimport.kernel.lar.MissingReferences)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.exportimport.kernel.lar.MissingReferences validateImportPortletInfo(
		HttpPrincipal httpPrincipal, long plid, long groupId, String portletId,
		java.util.Map<String, String[]> parameterMap,
		java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LayoutServiceUtil.class,
					"validateImportPortletInfo",
					_validateImportPortletInfoParameterTypes81);

			MethodHandler methodHandler = new MethodHandler(methodKey, plid,
					groupId, portletId, parameterMap, inputStream);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.exportimport.kernel.lar.MissingReferences)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LayoutServiceHttp.class);
	private static final Class<?>[] _addLayoutParameterTypes0 = new Class[] {
			long.class, boolean.class, long.class, java.util.Map.class,
			java.util.Map.class, java.util.Map.class, java.util.Map.class,
			java.util.Map.class, String.class, String.class, boolean.class,
			java.util.Map.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addLayoutParameterTypes1 = new Class[] {
			long.class, boolean.class, long.class, String.class, String.class,
			String.class, String.class, boolean.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addTempFileEntryParameterTypes2 = new Class[] {
			long.class, String.class, String.class, java.io.InputStream.class,
			String.class
		};
	private static final Class<?>[] _deleteLayoutParameterTypes3 = new Class[] {
			long.class, boolean.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteLayoutParameterTypes4 = new Class[] {
			long.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteTempFileEntryParameterTypes5 = new Class[] {
			long.class, String.class, String.class
		};
	private static final Class<?>[] _exportLayoutsParameterTypes6 = new Class[] {
			long.class, boolean.class, long[].class, java.util.Map.class,
			java.util.Date.class, java.util.Date.class
		};
	private static final Class<?>[] _exportLayoutsParameterTypes7 = new Class[] {
			long.class, boolean.class, java.util.Map.class, java.util.Date.class,
			java.util.Date.class
		};
	private static final Class<?>[] _exportLayoutsAsFileParameterTypes8 = new Class[] {
			com.liferay.exportimport.kernel.model.ExportImportConfiguration.class
		};
	private static final Class<?>[] _exportLayoutsAsFileParameterTypes9 = new Class[] {
			long.class, boolean.class, long[].class, java.util.Map.class,
			java.util.Date.class, java.util.Date.class
		};
	private static final Class<?>[] _exportLayoutsAsFileInBackgroundParameterTypes10 =
		new Class[] {
			com.liferay.exportimport.kernel.model.ExportImportConfiguration.class
		};
	private static final Class<?>[] _exportLayoutsAsFileInBackgroundParameterTypes11 =
		new Class[] { long.class };
	private static final Class<?>[] _exportLayoutsAsFileInBackgroundParameterTypes12 =
		new Class[] {
			String.class, long.class, boolean.class, long[].class,
			java.util.Map.class, java.util.Date.class, java.util.Date.class
		};
	private static final Class<?>[] _exportLayoutsAsFileInBackgroundParameterTypes13 =
		new Class[] {
			String.class, long.class, boolean.class, long[].class,
			java.util.Map.class, java.util.Date.class, java.util.Date.class,
			String.class
		};
	private static final Class<?>[] _exportPortletInfoParameterTypes14 = new Class[] {
			long.class, long.class, String.class, java.util.Map.class,
			java.util.Date.class, java.util.Date.class
		};
	private static final Class<?>[] _exportPortletInfoParameterTypes15 = new Class[] {
			long.class, String.class, java.util.Map.class, java.util.Date.class,
			java.util.Date.class
		};
	private static final Class<?>[] _exportPortletInfoAsFileParameterTypes16 = new Class[] {
			com.liferay.exportimport.kernel.model.ExportImportConfiguration.class
		};
	private static final Class<?>[] _exportPortletInfoAsFileParameterTypes17 = new Class[] {
			long.class, long.class, String.class, java.util.Map.class,
			java.util.Date.class, java.util.Date.class
		};
	private static final Class<?>[] _exportPortletInfoAsFileParameterTypes18 = new Class[] {
			String.class, java.util.Map.class, java.util.Date.class,
			java.util.Date.class
		};
	private static final Class<?>[] _exportPortletInfoAsFileInBackgroundParameterTypes19 =
		new Class[] {
			String.class, long.class, long.class, String.class,
			java.util.Map.class, java.util.Date.class, java.util.Date.class,
			String.class
		};
	private static final Class<?>[] _exportPortletInfoAsFileInBackgroundParameterTypes20 =
		new Class[] {
			String.class, String.class, java.util.Map.class,
			java.util.Date.class, java.util.Date.class, String.class
		};
	private static final Class<?>[] _getAncestorLayoutsParameterTypes21 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getDefaultPlidParameterTypes22 = new Class[] {
			long.class, long.class, boolean.class, String.class
		};
	private static final Class<?>[] _getDefaultPlidParameterTypes23 = new Class[] {
			long.class, long.class, String.class
		};
	private static final Class<?>[] _getLayoutByUuidAndGroupIdParameterTypes24 = new Class[] {
			String.class, long.class, boolean.class
		};
	private static final Class<?>[] _getLayoutNameParameterTypes25 = new Class[] {
			long.class, boolean.class, long.class, String.class
		};
	private static final Class<?>[] _getLayoutReferencesParameterTypes26 = new Class[] {
			long.class, String.class, String.class, String.class
		};
	private static final Class<?>[] _getLayoutsParameterTypes27 = new Class[] {
			long.class, boolean.class
		};
	private static final Class<?>[] _getLayoutsParameterTypes28 = new Class[] {
			long.class, boolean.class, long.class
		};
	private static final Class<?>[] _getLayoutsParameterTypes29 = new Class[] {
			long.class, boolean.class, long.class, boolean.class, int.class,
			int.class
		};
	private static final Class<?>[] _getLayoutsParameterTypes30 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _getLayoutsParameterTypes31 = new Class[] {
			long.class, String.class, int.class, int.class
		};
	private static final Class<?>[] _getLayoutsCountParameterTypes32 = new Class[] {
			long.class, boolean.class
		};
	private static final Class<?>[] _getLayoutsCountParameterTypes33 = new Class[] {
			long.class, boolean.class, long.class
		};
	private static final Class<?>[] _getLayoutsCountParameterTypes34 = new Class[] {
			long.class, boolean.class, long.class, int.class
		};
	private static final Class<?>[] _getLayoutsCountParameterTypes35 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _getTempFileNamesParameterTypes36 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _hasLayoutParameterTypes37 = new Class[] {
			String.class, long.class, boolean.class
		};
	private static final Class<?>[] _importLayoutsParameterTypes38 = new Class[] {
			com.liferay.exportimport.kernel.model.ExportImportConfiguration.class,
			java.io.File.class
		};
	private static final Class<?>[] _importLayoutsParameterTypes39 = new Class[] {
			com.liferay.exportimport.kernel.model.ExportImportConfiguration.class,
			java.io.InputStream.class
		};
	private static final Class<?>[] _importLayoutsParameterTypes40 = new Class[] {
			long.class, boolean.class, java.util.Map.class, byte[].class
		};
	private static final Class<?>[] _importLayoutsParameterTypes41 = new Class[] {
			long.class, boolean.class, java.util.Map.class, java.io.File.class
		};
	private static final Class<?>[] _importLayoutsParameterTypes42 = new Class[] {
			long.class, boolean.class, java.util.Map.class,
			java.io.InputStream.class
		};
	private static final Class<?>[] _importLayoutsInBackgroundParameterTypes43 = new Class[] {
			String.class, long.class, boolean.class, java.util.Map.class,
			java.io.File.class
		};
	private static final Class<?>[] _importLayoutsInBackgroundParameterTypes44 = new Class[] {
			String.class, long.class, boolean.class, java.util.Map.class,
			java.io.InputStream.class
		};
	private static final Class<?>[] _importPortletInfoParameterTypes45 = new Class[] {
			com.liferay.exportimport.kernel.model.ExportImportConfiguration.class,
			java.io.File.class
		};
	private static final Class<?>[] _importPortletInfoParameterTypes46 = new Class[] {
			com.liferay.exportimport.kernel.model.ExportImportConfiguration.class,
			java.io.InputStream.class
		};
	private static final Class<?>[] _importPortletInfoParameterTypes47 = new Class[] {
			long.class, long.class, String.class, java.util.Map.class,
			java.io.File.class
		};
	private static final Class<?>[] _importPortletInfoParameterTypes48 = new Class[] {
			long.class, long.class, String.class, java.util.Map.class,
			java.io.InputStream.class
		};
	private static final Class<?>[] _importPortletInfoParameterTypes49 = new Class[] {
			String.class, java.util.Map.class, java.io.File.class
		};
	private static final Class<?>[] _importPortletInfoParameterTypes50 = new Class[] {
			String.class, java.util.Map.class, java.io.InputStream.class
		};
	private static final Class<?>[] _importPortletInfoInBackgroundParameterTypes51 =
		new Class[] {
			String.class, long.class, long.class, String.class,
			java.util.Map.class, java.io.File.class
		};
	private static final Class<?>[] _importPortletInfoInBackgroundParameterTypes52 =
		new Class[] {
			String.class, long.class, long.class, String.class,
			java.util.Map.class, java.io.InputStream.class
		};
	private static final Class<?>[] _importPortletInfoInBackgroundParameterTypes53 =
		new Class[] {
			String.class, String.class, java.util.Map.class, java.io.File.class
		};
	private static final Class<?>[] _importPortletInfoInBackgroundParameterTypes54 =
		new Class[] {
			String.class, String.class, java.util.Map.class,
			java.io.InputStream.class
		};
	private static final Class<?>[] _schedulePublishToLiveParameterTypes55 = new Class[] {
			long.class, long.class, boolean.class, long[].class,
			java.util.Map.class, String.class, java.util.Date.class,
			java.util.Date.class, String.class, String.class,
			java.util.Date.class, java.util.Date.class, String.class
		};
	private static final Class<?>[] _schedulePublishToLiveParameterTypes56 = new Class[] {
			long.class, long.class, boolean.class, long[].class,
			java.util.Map.class, String.class, String.class,
			java.util.Date.class, java.util.Date.class, String.class
		};
	private static final Class<?>[] _schedulePublishToLiveParameterTypes57 = new Class[] {
			long.class, long.class, boolean.class, java.util.Map.class,
			java.util.Map.class, String.class, java.util.Date.class,
			java.util.Date.class, String.class, String.class,
			java.util.Date.class, java.util.Date.class, String.class
		};
	private static final Class<?>[] _schedulePublishToRemoteParameterTypes58 = new Class[] {
			long.class, boolean.class, java.util.Map.class, java.util.Map.class,
			String.class, int.class, String.class, boolean.class, long.class,
			boolean.class, java.util.Date.class, java.util.Date.class,
			String.class, String.class, java.util.Date.class,
			java.util.Date.class, String.class
		};
	private static final Class<?>[] _setLayoutsParameterTypes59 = new Class[] {
			long.class, boolean.class, long.class, long[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _unschedulePublishToLiveParameterTypes60 = new Class[] {
			long.class, String.class, String.class
		};
	private static final Class<?>[] _unschedulePublishToRemoteParameterTypes61 = new Class[] {
			long.class, String.class, String.class
		};
	private static final Class<?>[] _updateIconImageParameterTypes62 = new Class[] {
			long.class, byte[].class
		};
	private static final Class<?>[] _updateLayoutParameterTypes63 = new Class[] {
			long.class, boolean.class, long.class, long.class,
			java.util.Map.class, java.util.Map.class, java.util.Map.class,
			java.util.Map.class, java.util.Map.class, String.class,
			boolean.class, java.util.Map.class, boolean.class, byte[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateLayoutParameterTypes64 = new Class[] {
			long.class, boolean.class, long.class, String.class
		};
	private static final Class<?>[] _updateLookAndFeelParameterTypes65 = new Class[] {
			long.class, boolean.class, long.class, String.class, String.class,
			String.class
		};
	private static final Class<?>[] _updateNameParameterTypes66 = new Class[] {
			long.class, boolean.class, long.class, String.class, String.class
		};
	private static final Class<?>[] _updateNameParameterTypes67 = new Class[] {
			long.class, String.class, String.class
		};
	private static final Class<?>[] _updateParentLayoutIdParameterTypes68 = new Class[] {
			long.class, boolean.class, long.class, long.class
		};
	private static final Class<?>[] _updateParentLayoutIdParameterTypes69 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _updateParentLayoutIdAndPriorityParameterTypes70 =
		new Class[] { long.class, long.class, int.class };
	private static final Class<?>[] _updatePriorityParameterTypes71 = new Class[] {
			long.class, boolean.class, long.class, int.class
		};
	private static final Class<?>[] _updatePriorityParameterTypes72 = new Class[] {
			long.class, boolean.class, long.class, long.class, long.class
		};
	private static final Class<?>[] _updatePriorityParameterTypes73 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _validateImportLayoutsFileParameterTypes74 = new Class[] {
			com.liferay.exportimport.kernel.model.ExportImportConfiguration.class,
			java.io.File.class
		};
	private static final Class<?>[] _validateImportLayoutsFileParameterTypes75 = new Class[] {
			com.liferay.exportimport.kernel.model.ExportImportConfiguration.class,
			java.io.InputStream.class
		};
	private static final Class<?>[] _validateImportLayoutsFileParameterTypes76 = new Class[] {
			long.class, boolean.class, java.util.Map.class, java.io.File.class
		};
	private static final Class<?>[] _validateImportLayoutsFileParameterTypes77 = new Class[] {
			long.class, boolean.class, java.util.Map.class,
			java.io.InputStream.class
		};
	private static final Class<?>[] _validateImportPortletInfoParameterTypes78 = new Class[] {
			com.liferay.exportimport.kernel.model.ExportImportConfiguration.class,
			java.io.File.class
		};
	private static final Class<?>[] _validateImportPortletInfoParameterTypes79 = new Class[] {
			com.liferay.exportimport.kernel.model.ExportImportConfiguration.class,
			java.io.InputStream.class
		};
	private static final Class<?>[] _validateImportPortletInfoParameterTypes80 = new Class[] {
			long.class, long.class, String.class, java.util.Map.class,
			java.io.File.class
		};
	private static final Class<?>[] _validateImportPortletInfoParameterTypes81 = new Class[] {
			long.class, long.class, String.class, java.util.Map.class,
			java.io.InputStream.class
		};
}