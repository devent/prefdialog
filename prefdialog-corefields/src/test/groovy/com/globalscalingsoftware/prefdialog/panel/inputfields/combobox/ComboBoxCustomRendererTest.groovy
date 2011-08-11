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
package com.globalscalingsoftware.prefdialog.panel.inputfields.combobox

import java.awt.Component

import javax.swing.DefaultListCellRenderer
import javax.swing.JList

import org.junit.Test

import com.globalscalingsoftware.prefdialog.annotations.ComboBox
import com.globalscalingsoftware.prefdialog.annotations.ComboBoxElements
import com.globalscalingsoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.ComboBoxFieldHandlerFactory

class ComboBoxCustomRendererTest extends AbstractFieldFixture {

	static factory = injector.getInstance(ComboBoxFieldHandlerFactory)

	static class CustomComboBoxRenderer extends DefaultListCellRenderer {
		Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus)
			setText(value.toString().toUpperCase());
			return this
		}
	}

	static class General {

		@ComboBoxElements('Some combo box')
		List<String> comboBoxElements = [
			'first element',
			'second element',
			'third element'
		]

		@ComboBox(renderer=CustomComboBoxRenderer, elements='Some combo box')
		String comboBox = 'first element'
	}

	ComboBoxCustomRendererTest() {
		super(new General(), 'comboBox', factory)
	}

	@Test
	void "choose second element and apply input"() {
		fixture.comboBox('comboBox').selectItem 1
		inputField.applyInput parentObject

		fixture.comboBox('comboBox').requireSelection 1
		assert parentObject.comboBox == 'second element'
	}

	@Test
	void "choose second element and restore input"() {
		fixture.comboBox('comboBox').selectItem 1
		inputField.restoreInput parentObject

		fixture.comboBox('comboBox').requireSelection 0
		assert parentObject.comboBox == 'first element'
	}
}

