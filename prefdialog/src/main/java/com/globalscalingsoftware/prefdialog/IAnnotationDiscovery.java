package com.globalscalingsoftware.prefdialog;

public interface IAnnotationDiscovery {

	void discover(IAnnotationFilter filter, Object object,
			IDiscoveredListener listener);

}