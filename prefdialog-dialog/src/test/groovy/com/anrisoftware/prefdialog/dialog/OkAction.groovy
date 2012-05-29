package com.anrisoftware.prefdialog.dialog

import java.awt.event.ActionEvent

import javax.swing.AbstractAction

class OkAction extends AbstractAction {

	static final String CUSTOM_OK = "Custom Ok"

	OkAction() {
		super(CUSTOM_OK)
	}

	void actionPerformed(ActionEvent e) {
	}
}

