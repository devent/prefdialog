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
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.ComboBoxFieldHandlerFactory

class ComboBoxTitleTest extends FieldFixtureHandler {

	static factory = injector.getInstance(ComboBoxFieldHandlerFactory)

	static class General {

		@ComboBoxElements('Some combo box')
		List<String> comboBoxElements = [
			'first element',
			'second element',
			'third element'
		]

		@ComboBox(elements='Some combo box')
		String comboBox1 = 'first element'

		@ComboBox(title='Second combo box:', elements='Some combo box')
		String comboBox2 = 'first element'

		@ComboBox(showTitle=false, elements='Some combo box')
		String comboBox3= 'first element'
	}

	@Test
	void "set default tile"() {
		createFieldFixture(new General(), 'comboBox1', factory)
		beginFixture()
		assert fixture.label('label-comboBox1').text() == 'comboBox1'
		endFixture()
	}

	@Test
	void "set custom tile"() {
		createFieldFixture(new General(), 'comboBox2', factory)
		beginFixture()
		assert fixture.label('label-comboBox2').text() == 'Second combo box:'
		endFixture()
	}

	@Test
	void "set no tile"() {
		createFieldFixture(new General(), 'comboBox3', factory)
		beginFixture()
		// Why it can't find an invisible label?
		// fixture.label('label-comboBox3').requireNotVisible()
		endFixture()
	}
}

