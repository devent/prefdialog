/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.checkbox

import com.anrisoftware.prefdialog.annotations.CheckBox
import com.anrisoftware.prefdialog.annotations.FieldButton
import com.anrisoftware.prefdialog.annotations.FieldComponent

/**
 * Bean with check-box fields.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class CheckBoxBean {

	static final NULL_VALUE = "nullValue"

	static final NO_TEXT = "noText"

	static final WITH_TEXT = "withText"

	static final WITH_TEXT_RESOURCE = "withTextResource"

	static final NOT_SHOW_TEXT = "notShowText"

	static final READ_ONLY = "readOnly"

	@FieldComponent
	@FieldButton
	@CheckBox
	public Boolean nullValue

	@FieldComponent
	@FieldButton
	@CheckBox
	public boolean noText

	@FieldComponent
	@FieldButton(text = "Checkbox Text")
	@CheckBox
	public boolean withText

	@FieldComponent(title = "checkbox_with_text_resource_title")
	@FieldButton(text = "checkbox_with_text_resource_text")
	@CheckBox
	public boolean withTextResource

	@FieldComponent
	@FieldButton(showText = false)
	@CheckBox
	public boolean notShowText

	@FieldComponent(readOnly = true)
	@FieldButton
	@CheckBox
	public boolean readOnly
}

