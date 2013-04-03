package com.anrisoftware.prefdialog.fields.buttongroup

import java.awt.event.ActionEvent

import javax.swing.AbstractAction

/**
 * Calls the callback.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ButtonAction extends AbstractAction {

	def callback

	ButtonAction(String name, def callback) {
		super(name)
		this.callback = callback
	}

	void actionPerformed(ActionEvent action) {
		callback()
	}
}


