package com.anrisoftware.prefdialog.dialog

import java.awt.event.ActionEvent

import javax.swing.AbstractAction

class CancelAction extends AbstractAction {

	static final String CUSTOM_CANCEL = "Custom Cancel"

	CancelAction() {
		super(CUSTOM_CANCEL)
	}

	void actionPerformed(ActionEvent e) {
	}
}

