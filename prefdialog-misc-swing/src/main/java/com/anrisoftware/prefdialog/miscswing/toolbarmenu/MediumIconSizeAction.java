package com.anrisoftware.prefdialog.miscswing.toolbarmenu;

import com.anrisoftware.resources.images.api.IconSize;

/**
 * Sets medium icon size.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class MediumIconSizeAction extends AbstractIconSizeAction {

	private static final String ACTION_NAME = "medium_size_action";

	MediumIconSizeAction() {
		super(ACTION_NAME, IconSize.MEDIUM);
		putValue(NAME, "Medium (22x22)");
	}
}
