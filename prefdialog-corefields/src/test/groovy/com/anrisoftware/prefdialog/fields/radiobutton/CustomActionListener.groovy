package com.anrisoftware.prefdialog.fields.radiobutton

import java.awt.event.ActionEvent
import java.awt.event.ActionListener

/**
 * @see RadioButtonBean
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class CustomActionListener implements ActionListener {

	boolean actionCalled = false

	@Override
	public void actionPerformed(ActionEvent e) {
		actionCalled = true
	}
}
