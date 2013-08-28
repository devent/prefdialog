package com.anrisoftware.prefdialog.miscswing.toolbarmenu;

import java.awt.event.ActionEvent;

import com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction;

/**
 * Dummy toolbar button action.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class ButtonAction extends AbstractResourcesAction {

	private static final String ACTION_NAME = "button";

	protected ButtonAction() {
		super(ACTION_NAME);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
