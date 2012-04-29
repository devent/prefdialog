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

import javax.swing.DefaultComboBoxModel

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.ComboBox
import com.anrisoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.anrisoftware.prefdialog.panel.inputfields.api.ComboBoxFieldHandlerFactory

/**
 * Test the {@link ComboBox} field with a custom model class.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
class ComboBoxCustomModelTest extends AbstractFieldFixture {

	static factory = injector.getInstance(ComboBoxFieldHandlerFactory)

	static final String COMBO_BOX = "comboBox"

	static class CustomComboBoxModel extends DefaultComboBoxModel {

		CustomComboBoxModel() {
			super([
				"first element",
				"second element",
				"third element"
			].toArray())
		}
	}

	static class General {

		@ComboBox(modelClass = CustomComboBoxModel)
		String comboBox = "second element"
	}

	ComboBoxCustomModelTest() {
		super(new General(), COMBO_BOX, factory)
	}

	@Test
	void "choose third element and apply input"() {
		fixture.comboBox(COMBO_BOX).selectItem 2
		inputField.applyInput parentObject

		fixture.comboBox(COMBO_BOX).requireSelection 2
		assert parentObject.comboBox == "third element"
	}

	@Test
	void "choose third element and restore input"() {
		fixture.comboBox(COMBO_BOX).selectItem 2
		inputField.restoreInput parentObject

		fixture.comboBox(COMBO_BOX).requireSelection 1
		assert parentObject.comboBox == "second element"
	}
}

