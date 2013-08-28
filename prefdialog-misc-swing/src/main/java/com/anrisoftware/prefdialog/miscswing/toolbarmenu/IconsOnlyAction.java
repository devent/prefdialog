package com.anrisoftware.prefdialog.miscswing.toolbarmenu;

import java.awt.event.ActionEvent;

import com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction;

/**
 * Shows icons only.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class IconsOnlyAction extends AbstractResourcesAction {

	private static final String ACTION_NAME = "icons_only_action";

	private ToolbarMenu toolbar;

	IconsOnlyAction() {
		super(ACTION_NAME);
	}

	public void setToolbarMenu(ToolbarMenu toolbar) {
		this.toolbar = toolbar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		toolbar.setIconsOnly(true);
	}

}
