/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-panel.
 *
 * prefdialog-panel is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-panel is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-panel. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.simpledialog

import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.TextField
import com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanel

/**
 * Bean with fields.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class SimpleDialogBean {

	public static final String NULL_VALUE = "nullValue"

	public static final String CHILD_A = "childA"

	public static final String CHILD_B = "childB"

	static class ChildABean {

		@FieldComponent
		@TextField
		public String childAName = "Child A"

		@FieldComponent
		@TextField
		public String childATitle
	}

	static class ChildBBean {

		@FieldComponent
		@TextField
		public String childBName = "Child B"

		@FieldComponent
		@TextField
		public String childBTitle
	}

	@FieldComponent
	@VerticalPreferencesPanel
	public Object nullValue

	@FieldComponent
	@Child
	public ChildABean childA

	@FieldComponent
	@Child
	public ChildBBean childB
}

