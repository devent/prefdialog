/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-core.
 * 
 * prefdialog-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.core

import static com.anrisoftware.prefdialog.core.AbstractTitleField.*
import static com.anrisoftware.prefdialog.core.Preferences.*

import javax.swing.JLabel
import javax.swing.JPanel

import org.junit.Test

/**
 * Test the {@link AbstractTitleField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TitleFieldTest extends FieldTestUtils {

	def title = "Title Field Test"

	@Test
	void "show text field"() {
		def container = new JPanel()
		def component = new JLabel("Label")
		def preferenceField = preferencesTextField
		def field = titleFieldFactory.create(component, container, preferences, preferenceField).createField()
		beginPanelFrame title, container, {
			fixture.panel("${preferenceField.name}-$CONTAINER_NAME").requireEnabled()
			fixture.label(preferenceField.name).requireEnabled()
			fixture.label("${preferenceField.name}-$TITLE_NAME").requireText("textField:")
		}
	}
}
