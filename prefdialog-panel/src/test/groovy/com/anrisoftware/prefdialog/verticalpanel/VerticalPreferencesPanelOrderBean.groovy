/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.verticalpanel

import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.TextField

/**
 * Bean with fields.
 * 
 * @see VerticalPreferencesPanel
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class VerticalPreferencesPanelOrderBean {

	public static final String NULL_VALUE = "nullValue"

	public static final String CHILD_A = "childA"

	public static final String CHILD_B = "childB"

	static class ChildABean {

		@FieldComponent
		@TextField
		public String childName = "Child A"

		@FieldComponent
		@TextField
		public String childTitle
	}

	static class ChildBBean {

		@FieldComponent
		@TextField
		public String childName = "Child B"

		@FieldComponent
		@TextField
		public String childTitle
	}

	@FieldComponent
	@VerticalPreferencesPanel
	public Object nullValue

	@FieldComponent(order = 2)
	@Child
	public ChildABean childA

	@FieldComponent(order = 1)
	@Child
	public ChildBBean childB
}

