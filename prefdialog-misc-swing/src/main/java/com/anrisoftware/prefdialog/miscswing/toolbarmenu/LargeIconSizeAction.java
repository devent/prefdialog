package com.anrisoftware.prefdialog.miscswing.toolbarmenu;

import com.anrisoftware.resources.images.api.IconSize;

/**
 * Sets small icon size.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class LargeIconSizeAction extends AbstractIconSizeAction {

	private static final String ACTION_NAME = "small_size_action";

	LargeIconSizeAction() {
		super(ACTION_NAME, IconSize.LARGE);
		putValue(NAME, "Large (32x32)");
	}
}
