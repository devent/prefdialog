package com.anrisoftware.prefdialog.dialog

import java.awt.event.ActionEvent

import javax.swing.AbstractAction

class ApplyAction extends AbstractAction {

	static final String CUSTOM_APPLY = "Custom Apply"

	ApplyAction() {
		super(CUSTOM_APPLY)
	}

	void actionPerformed(ActionEvent e) {
	}
}

