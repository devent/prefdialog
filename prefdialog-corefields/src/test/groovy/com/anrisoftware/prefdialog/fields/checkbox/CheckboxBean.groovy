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

	static final NULL_VALUE = "nullValue"

	static final NULL_VALUE_FIELD = FieldUtils.getField(CheckboxBean, NULL_VALUE, true)

	static final NO_TEXT = "noText"

	static final NO_TEXT_FIELD = FieldUtils.getField(CheckboxBean, NO_TEXT, true)

	static final WITH_TEXT = "withText"

	static final WITH_TEXT_FIELD = FieldUtils.getField(CheckboxBean, WITH_TEXT, true)

	static final WITH_TEXT_RESOURCE = "withTextResource"

	static final WITH_TEXT_RESOURCE_FIELD = FieldUtils.getField(CheckboxBean, WITH_TEXT_RESOURCE, true)

	static final NOT_SHOW_TEXT = "notShowText"

	static final NOT_SHOW_TEXT_FIELD = FieldUtils.getField(CheckboxBean, NOT_SHOW_TEXT, true)

	static final READ_ONLY = "readOnly"

	static final READ_ONLY_FIELD = FieldUtils.getField(CheckboxBean, READ_ONLY, true)

	@FieldComponent
	@Checkbox
	Boolean nullValue

	@FieldComponent
	@Checkbox
	boolean noText

	@FieldComponent
	@Checkbox(text = "Checkbox Text")
	boolean withText

	@FieldComponent
	@Checkbox(text = "checkbox_with_text_resource")
	boolean withTextResource

	@FieldComponent
	@Checkbox(showText = false)
	boolean notShowText

	@FieldComponent(readOnly = true)
	@Checkbox
	boolean readOnly
}

