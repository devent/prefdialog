/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
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

	public static final preferencesTextField = "textField"

	public static final preferencesTextFieldWithTitleDisabled = "textFieldWithTitleDisabled"

	public static final preferencesTextFieldWithTitle = "textFieldWithTitle"

	public static final preferencesTextFieldWithTitleResource = "textFieldWithTitleResource"

	public static final preferencesTextFieldWithTitleResourceDe = "textFieldWithTitleResourceDe"

	public static final preferencesTextFieldWithTitleMissingResource = "textFieldWithTitleMissingResource"

	public static final preferencesTextFieldReadOnly = "textFieldReadOnly"

	public static final preferencesTextFieldWithToolTip = "textFieldWithToolTip"

	public static final preferencesTextFieldWithToolTipResource = "textFieldWithToolTipResource"

	public static final preferencesTextFieldWithIconResource = "textFieldWithIconResource"

	public static final preferencesTextFieldFixedWidth = "textFieldFixedWidth"

	public static final preferencesTextFieldFillWidth = "textFieldFillWidth"

	public static final preferencesTextFieldPreferredWidth = "textFieldPreferredWidth"

	@FieldComponent
	@TextField
	public String textField

	@FieldComponent(showTitle = false)
	@TextField
	public String textFieldWithTitleDisabled

	@FieldComponent(title = "Test Field")
	@TextField
	public String textFieldWithTitle

	@FieldComponent(title = "test_field")
	@TextField
	public String textFieldWithTitleResource

	@FieldComponent(title = "test_field", locale = "de")
	@TextField
	public String textFieldWithTitleResourceDe

	@FieldComponent(title = "missing_test_field")
	@TextField
	public String textFieldWithTitleMissingResource

	@FieldComponent(readOnly = true)
	@TextField
	public String textFieldReadOnly

	@FieldComponent(toolTip = "Tool Tip")
	@TextField
	public String textFieldWithToolTip

	@FieldComponent(title = "test_field", toolTip = "tool_tip")
	@TextField
	public String textFieldWithToolTipResource

	@FieldComponent(icon = "test_field_icon")
	@TextField
	public String textFieldWithIconResource

	@FieldComponent(width = 200d)
	@TextField
	public String textFieldFixedWidth

	@FieldComponent(width = -1.0d)
	@TextField
	public String textFieldFillWidth

	@FieldComponent(width = -2.0d)
	@TextField
	public String textFieldPreferredWidth
}
