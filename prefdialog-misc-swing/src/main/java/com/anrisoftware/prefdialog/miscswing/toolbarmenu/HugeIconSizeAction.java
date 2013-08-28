package com.anrisoftware.prefdialog.miscswing.toolbarmenu;

import com.anrisoftware.resources.images.api.IconSize;

/**
 * Sets huge icon size.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class HugeIconSizeAction extends AbstractIconSizeAction {

	private static final String ACTION_NAME = "huge_size_action";

	HugeIconSizeAction() {
		super(ACTION_NAME, IconSize.HUGE);
		putValue(NAME, "Huge (48x48)");
	}
}
