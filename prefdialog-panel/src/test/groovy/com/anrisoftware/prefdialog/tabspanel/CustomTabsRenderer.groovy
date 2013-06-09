package com.anrisoftware.prefdialog.tabspanel

/**
 * Custom renderer.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class CustomTabsRenderer extends DefaultTabsRenderer {

	static titles = ["Custom A", "Custom B"]

	@Override
	public String getTitle(int index) {
		titles[index]
	}
}
