package com.anrisoftware.prefdialog.miscswing.toolbarmenu;

import java.awt.event.ActionEvent;

import com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction;

/**
 * Shows text alongside icons.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class TextAlongsideAction extends AbstractResourcesAction {

	private static final String ACTION_NAME = "text_alongside_action";

	private ToolbarMenu toolbar;

	TextAlongsideAction() {
		super(ACTION_NAME);
		putValue(NAME, "Text Alongside Icons");
	}

	public void setToolbarMenu(ToolbarMenu toolbar) {
		this.toolbar = toolbar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		toolbar.setIconsOnly(false);
		toolbar.setTextOnly(false);
	}

}
