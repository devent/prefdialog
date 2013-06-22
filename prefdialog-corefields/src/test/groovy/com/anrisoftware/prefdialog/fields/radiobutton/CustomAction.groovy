package com.anrisoftware.prefdialog.fields.radiobutton

import java.awt.event.ActionEvent

import javax.swing.AbstractAction

/**
 * @see RadioButtonBean
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class CustomAction extends AbstractAction {

	boolean actionCalled = false

	@Override
	public void actionPerformed(ActionEvent e) {
		actionCalled = true
	}
}
