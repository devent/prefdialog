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

	public static final preferencesTextField = Preferences.class.getDeclaredField("textField")

	public static final preferencesTextFieldWithTitle = Preferences.class.getDeclaredField("textFieldWithTitle")

	public static final preferencesTextFieldReadOnly = Preferences.class.getDeclaredField("textFieldReadOnly")

	public static final preferencesTextFieldWithToolTip = Preferences.class.getDeclaredField("textFieldWithToolTip")

	@FieldComponent
	@TextField
	String textField

	@FieldComponent(title = "Test Field")
	@TextField
	String textFieldWithTitle

	@FieldComponent(readOnly = true)
	@TextField
	String textFieldReadOnly

	@FieldComponent(toolTip = "Tool Tip")
	@TextField
	String textFieldWithToolTip
}
