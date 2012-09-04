/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-corefields.
 * 
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
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

import org.apache.commons.lang3.reflect.FieldUtils

import com.anrisoftware.prefdialog.annotations.Checkbox
import com.anrisoftware.prefdialog.annotations.FieldComponent

/**
 * Bean with check-box fields.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class CheckboxBean {

	static final CHECKBOX_NULL_VALUE = "checkboxNullValue"

	static final CHECKBOX_NULL_VALUE_FIELD = FieldUtils.getField(CheckboxBean, CHECKBOX_NULL_VALUE, true)

	static final CHECKBOX_NO_TEXT = "checkboxNoText"

	static final CHECKBOX_NO_TEXT_FIELD = FieldUtils.getField(CheckboxBean, CHECKBOX_NO_TEXT, true)

	static final CHECKBOX_WITH_TEXT = "checkboxWithText"

	static final CHECKBOX_WITH_TEXT_FIELD = FieldUtils.getField(CheckboxBean, CHECKBOX_WITH_TEXT, true)

	static final CHECKBOX_WITH_TEXT_RESOURCE = "checkboxWithTextResource"

	static final CHECKBOX_WITH_TEXT_RESOURCE_FIELD = FieldUtils.getField(CheckboxBean, CHECKBOX_WITH_TEXT_RESOURCE, true)

	@FieldComponent
	@Checkbox
	Boolean checkboxNullValue

	@FieldComponent
	@Checkbox
	boolean checkboxNoText

	@FieldComponent
	@Checkbox(text = "Checkbox Text")
	boolean checkboxWithText

	@FieldComponent
	@Checkbox(text = "checkbox_with_text_resource")
	boolean checkboxWithTextResource
}

