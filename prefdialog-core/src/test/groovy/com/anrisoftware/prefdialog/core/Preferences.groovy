package com.anrisoftware.prefdialog.core

import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.TextField

/**
 * Simple preferences for testing.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class Preferences {

	@FieldComponent
	@TextField
	String textField
}
