package com.globalscalingsoftware.prefdialog;

import com.globalscalingsoftware.prefdialog.internal.DiscoveredListener;

public interface IAnnotationDiscovery {

	void discover(Object object, IFilter filter, DiscoveredListener listener);

}