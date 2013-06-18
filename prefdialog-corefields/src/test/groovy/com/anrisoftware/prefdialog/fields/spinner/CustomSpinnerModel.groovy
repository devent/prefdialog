package com.anrisoftware.prefdialog.fields.spinner

import javax.swing.SpinnerNumberModel

/**
 * Custom spinner model.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class CustomSpinnerModel extends SpinnerNumberModel {

	CustomSpinnerModel() {
		super(2, 2, 10, 2)
	}
}
