package com.anrisoftware.prefdialog.miscswing.toolbarmenu;

import java.awt.event.ActionEvent;

import com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction;

/**
 * Shows text only.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class TextOnlyAction extends AbstractResourcesAction {

	private static final String ACTION_NAME = "text_only_action";

	private ToolbarMenu toolbar;

	TextOnlyAction() {
		super(ACTION_NAME);
		putValue(NAME, "Text Only");
	}

	public void setToolbarMenu(ToolbarMenu toolbar) {
		this.toolbar = toolbar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		toolbar.setIconsOnly(false);
		toolbar.setTextOnly(true);
	}

}
