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

class ComboBoxCustomModelTest extends AbstractFieldFixture {

	static factory = injector.getInstance(ComboBoxFieldHandlerFactory)

	static class CustomComboBoxModel extends DefaultComboBoxModel {

		CustomComboBoxModel() {
			super([
				'first element',
				'second element',
				'third element'
			].toArray())
		}
	}

	static class General {

		@ComboBox(model=CustomComboBoxModel)
		String comboBox = 'second element'
	}

	ComboBoxCustomModelTest() {
		super(new General(), 'comboBox', factory)
	}

	@Test
	void "choose third element and apply input"() {
		fixture.comboBox('comboBox').selectItem 2
		inputField.applyInput parentObject

		fixture.comboBox('comboBox').requireSelection 2
		assert parentObject.comboBox == 'third element'
	}

	@Test
	void "choose third element and restore input"() {
		fixture.comboBox('comboBox').selectItem 2
		inputField.restoreInput parentObject

		fixture.comboBox('comboBox').requireSelection 1
		assert parentObject.comboBox == 'second element'
	}
}

