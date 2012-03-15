/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel.inputfields.combobox

import java.util.List

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.ComboBox
import com.anrisoftware.prefdialog.annotations.ComboBoxElements
import com.anrisoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.anrisoftware.prefdialog.panel.inputfields.api.ComboBoxFieldHandlerFactory

class ComboBoxReadOnlyTest extends AbstractFieldFixture {

	static factory = injector.getInstance(ComboBoxFieldHandlerFactory)

	static final String READ_ONLY = "readOnly"

	static class General {

		@ComboBoxElements("Some combo box")
		List<String> comboBoxElements = [
			"first element",
			"second element",
			"third element"
		]

		@ComboBox(elements="Some combo box", readonly=true)
		String readOnly = "first element"
	}

	ComboBoxReadOnlyTest() {
		super(new General(), READ_ONLY, factory)
	}

	@Test
	void "read only checkbox"() {
		fixture.comboBox(READ_ONLY).requireDisabled()
		fixture.comboBox(READ_ONLY).requireSelection 0
	}
}
