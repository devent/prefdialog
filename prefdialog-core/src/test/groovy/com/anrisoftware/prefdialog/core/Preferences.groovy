/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-core.
 * 
 * prefdialog-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-core. If not, see <http://www.gnu.org/licenses/>.
 */
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

	public static final preferencesTextFieldWithToolTipResource = Preferences.class.getDeclaredField("textFieldWithToolTipResource")

	public static final preferencesTextFieldWithIconResource = Preferences.class.getDeclaredField("textFieldWithIconResource")

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

	@FieldComponent(toolTip = "tool_tip")
	@TextField
	String textFieldWithToolTipResource

	@FieldComponent(icon = "test_field_icon")
	@TextField
	String textFieldWithIconResource
}
