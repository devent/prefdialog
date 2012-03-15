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

class ComboBoxGtkTest extends AbstractFieldFixture {

	static factory = injector.getInstance(ComboBoxFieldHandlerFactory)

	static final String COMBO_BOX = "comboBox"

	static class General {

		@ComboBoxElements("Some combo box")
		List<String> comboBoxElements = [
			"first element",
			"second element",
			"third element"
		]

		@ComboBox(elements="Some combo box")
		String comboBox = "first element"
	}

	ComboBoxGtkTest() {
		super(new General(), COMBO_BOX, factory, "com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
	}

	@Test
	void "manually"() {
		//Thread.sleep 60000
	}
}

