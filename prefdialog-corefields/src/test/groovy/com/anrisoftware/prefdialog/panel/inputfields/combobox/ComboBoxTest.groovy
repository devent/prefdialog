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
import com.anrisoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.anrisoftware.prefdialog.panel.inputfields.api.ComboBoxFieldHandlerFactory

class ComboBoxTest extends AbstractFieldFixture {

	static factory = injector.getInstance(ComboBoxFieldHandlerFactory)

	static final String COMBO_BOX = "comboBox"

	static class General {

		@ComboBox(elements="comboBoxElements")
		String comboBox = "first element"

		List comboBoxElements = [
			"first element",
			"second element",
			"third element"
		]
	}

	ComboBoxTest() {
		super(new General(), COMBO_BOX, factory)
	}

	@Test
	void "choose second element and apply input"() {
		fixture.comboBox(COMBO_BOX).selectItem 1
		inputField.applyInput parentObject

		fixture.comboBox(COMBO_BOX).requireSelection 1
		assert parentObject.comboBox == "second element"
	}

	@Test
	void "choose second element and restore input"() {
		fixture.comboBox(COMBO_BOX).selectItem 1
		inputField.restoreInput parentObject

		fixture.comboBox(COMBO_BOX).requireSelection 0
		assert parentObject.comboBox == "first element"
	}
}
