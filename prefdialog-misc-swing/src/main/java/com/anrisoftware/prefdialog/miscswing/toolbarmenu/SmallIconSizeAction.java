package com.anrisoftware.prefdialog.miscswing.toolbarmenu;

import com.anrisoftware.resources.images.api.IconSize;

/**
 * Sets small icon size.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class SmallIconSizeAction extends AbstractIconSizeAction {

	private static final String ACTION_NAME = "small_size_action";

	SmallIconSizeAction() {
		super(ACTION_NAME, IconSize.SMALL);
		putValue(NAME, "Small (16x16)");
		putValue(SELECTED_KEY, true);
	}
}
