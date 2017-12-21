package com.liferay.exportimport.kernel.lar.file;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

public interface LARFileFactory {

	public LARFile getLARFile(PortletDataContext portletDataContext);
}
