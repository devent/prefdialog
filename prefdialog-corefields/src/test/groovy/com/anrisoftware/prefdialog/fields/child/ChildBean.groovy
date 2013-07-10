/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.fields.child

import com.anrisoftware.prefdialog.annotations.CheckBox
import com.anrisoftware.prefdialog.annotations.FieldButton
import com.anrisoftware.prefdialog.annotations.FieldComponent

/**
 * Bean with check-box fields.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ChildBean {

	static class ChildSubBean {

		@FieldComponent
		@FieldButton
		@CheckBox
		public boolean checkbox

		@FieldComponent(order = -1)
		@FieldButton
		@CheckBox
		public boolean checkboxOrder
	}

	static final CHECKBOX = "checkbox"

	static final CHECKBOX_ORDER = "checkboxOrder"

	static final NULL_VALUE = "nullValue"

	static final NO_TITLE = "noTitle"

	@FieldComponent
	public ChildSubBean nullValue

	@FieldComponent(showTitle = false)
	public ChildSubBean noTitle
}

