package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.button

import java.awt.event.ActionEvent

import javax.swing.AbstractAction

class Button2Action extends AbstractAction {

	def callback = {}

	Button2Action() {
		super('Apply and Close')
	}

	void actionPerformed(ActionEvent action) {
		callback()
	}
}


