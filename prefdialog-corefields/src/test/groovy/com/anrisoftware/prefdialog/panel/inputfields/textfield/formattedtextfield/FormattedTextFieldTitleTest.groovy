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
package com.anrisoftware.prefdialog.panel.inputfields.textfield.formattedtextfield

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.FormattedTextField
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.FormattedTextFieldHandlerFactory

class FormattedTextFieldTitleTest extends FieldFixtureHandler {

	static factory = injector.getInstance(FormattedTextFieldHandlerFactory)

	static class General {

		@FormattedTextField
		double fields1 = 4

		@FormattedTextField(title='Number of fields')
		double fields2 = 4

		@FormattedTextField(showTitle=false)
		double fields3 = 4
	}

	@Test
	void "default title"() {
		createFieldFixture(new General(), 'fields1', factory)
		beginFixture()
		assert fixture.textBox('fields1').text() == '4'
		assert fixture.label('label-fields1').text() == 'fields1'
		endFixture()
	}

	@Test
	void "custom title"() {
		createFieldFixture(new General(), 'fields2', factory)
		beginFixture()
		assert fixture.textBox('fields2').text() == '4'
		assert fixture.label('label-fields2').text() == 'Number of fields'
		endFixture()
	}
}
