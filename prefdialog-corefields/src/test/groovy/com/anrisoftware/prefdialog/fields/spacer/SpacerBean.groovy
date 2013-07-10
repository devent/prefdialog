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
package com.anrisoftware.prefdialog.fields.spacer

import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.TextField

/**
 * @see Spacer
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class SpacerBean {

	static final CHILD_BEAN = "childBeanWithDefaultSpacer"

	static final CHILD_BEAN_FIXED_SPACER = "childBeanWithFixedSpacer"

	static final SPACER = "spacer"

	static final TOP = "top"

	static final BOTTOM = "bottom"

	static class ChildBeanWithDefaultSpacer {

		@FieldComponent
		@TextField
		public String top

		@FieldComponent
		@Spacer
		public String spacer

		@FieldComponent
		@TextField
		public String bottom
	}

	static class ChildBeanWithFixedSpacer {

		@FieldComponent
		@TextField
		public String top

		@FieldComponent(height = 100d)
		@Spacer
		public String spacer

		@FieldComponent
		@TextField
		public String bottom
	}

	@FieldComponent
	@Child
	public ChildBeanWithDefaultSpacer childBeanWithDefaultSpacer

	@FieldComponent
	@Child
	public ChildBeanWithFixedSpacer childBeanWithFixedSpacer
}

