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
package com.anrisoftware.prefdialog.tabspanel

import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.TextField

/**
 * Bean with fields.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class TabsPreferencesPanelBean {

	public static final String NULL_VALUE = "nullValue"

	public static final String RENDERER_FIELD = "rendererField"

	public static final String RENDERER_CLASS_FIELD = "rendererClassField"

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
	@TabsPreferencesPanel
	public Object nullValue

	@FieldComponent
	@TabsPreferencesPanel(renderer = "customRenderer")
	public Object rendererField

	public CustomTabsRenderer customRenderer

	@FieldComponent
	@TabsPreferencesPanel(rendererClass = CustomTabsRenderer)
	public Object rendererClassField

	@FieldComponent
	@Child
	public ChildABean childA

	@FieldComponent
	@Child
	public ChildBBean childB
}

