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
package com.anrisoftware.prefdialog.panel

import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.TextField

/**
 * Bean with fields.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class PreferencesPanelBean {

	public static final String CHILD = "child"

	static class ChildBean {

		@FieldComponent
		@TextField
		public String childName

		@FieldComponent
		@TextField
		public String childTitle
	}

	@FieldComponent
	@Child
	public ChildBean child

	@FieldComponent
	@TextField
	public String name

	@FieldComponent
	@TextField
	public String title
}

