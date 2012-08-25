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

	public static final preferencesTextFieldWithTitleResource = Preferences.class.getDeclaredField("textFieldWithTitleResource")

	public static final preferencesTextFieldWithTitleResourceDe = Preferences.class.getDeclaredField("textFieldWithTitleResourceDe")

	public static final preferencesTextFieldWithTitleMissingResource = Preferences.class.getDeclaredField("textFieldWithTitleMissingResource")

	public static final preferencesTextFieldReadOnly = Preferences.class.getDeclaredField("textFieldReadOnly")

	public static final preferencesTextFieldWithToolTip = Preferences.class.getDeclaredField("textFieldWithToolTip")

	@FieldComponent
	@TextField
	String textField

	@FieldComponent(title = "Test Field")
	@TextField
	String textFieldWithTitle

	@FieldComponent(title = "test_field")
	@TextField
	String textFieldWithTitleResource

	@FieldComponent(title = "test_field", locale = "de")
	@TextField
	String textFieldWithTitleResourceDe

	@FieldComponent(title = "missing_test_field")
	@TextField
	String textFieldWithTitleMissingResource

	@FieldComponent(readOnly = true)
	@TextField
	String textFieldReadOnly

	@FieldComponent(toolTip = "Tool Tip")
	@TextField
	String textFieldWithToolTip
}
