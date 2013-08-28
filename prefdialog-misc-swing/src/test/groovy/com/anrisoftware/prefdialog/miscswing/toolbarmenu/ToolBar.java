package com.anrisoftware.prefdialog.miscswing.toolbarmenu;

import javax.inject.Inject;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * Dummy toolbar.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class ToolBar extends JToolBar {

	private final JButton button;

	private ButtonAction buttonAction;

	ToolBar() {
		button = new JButton("Button");
		add(button);
	}

	@Inject
	void setButtonAction(ButtonAction action) {
		this.buttonAction = action;
		button.setAction(action);
	}

	public JButton getButton() {
		return button;
	}

	public ButtonAction getButtonAction() {
		return buttonAction;
	}
}
