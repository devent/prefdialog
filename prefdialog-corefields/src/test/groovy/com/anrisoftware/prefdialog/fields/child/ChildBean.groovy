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
package com.anrisoftware.prefdialog.fields.child

import org.apache.commons.lang3.reflect.FieldUtils

import com.anrisoftware.prefdialog.annotations.CheckBox
import com.anrisoftware.prefdialog.annotations.FieldComponent

/**
 * Bean with check-box fields.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ChildBean {

	static class ChildSubBean {

		@FieldComponent
		@CheckBox
		boolean checkbox
	}

	static final CHECKBOX = "checkbox"

	static final NULL_VALUE = "nullValue"

	static final NULL_VALUE_FIELD = FieldUtils.getField(ChildBean, NULL_VALUE, true)

	@FieldComponent
	@CheckBox
	ChildSubBean nullValue
}

