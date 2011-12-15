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
import com.anrisoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.anrisoftware.prefdialog.panel.inputfields.api.FormattedTextFieldHandlerFactory
import com.anrisoftware.prefdialog.validators.Validator

class FormattedTextFieldValidatorTest extends AbstractFieldFixture {

	static factory = injector.getInstance(FormattedTextFieldHandlerFactory)

	static class FieldsValidator implements Validator<Integer> {
		public boolean isValid(Integer value) {
			value >= 2 && value <= 100
		}
	}

	static class General {

		@FormattedTextField(validator=FieldsValidator, validatorText='Must be a number and between 2 and 100')
		int fields = 4
	}

	FormattedTextFieldValidatorTest() {
		super(new General(), 'fields', factory)
	}

	@Test
	void "enter invalid text"() {
		fixture.textBox('fields').deleteText()
		fixture.textBox('fields').enterText '1'
		fixture.textBox('fields').requireToolTip '<html><strong>fields</strong> - Must be a number and between 2 and 100</html>'
	}

	@Test
	void "enter valid text and apply input"() {
		fixture.textBox('fields').deleteText()
		fixture.textBox('fields').enterText '10'
		inputField.applyInput parentObject

		assert fixture.textBox('fields').text() == '10'
		assert parentObject.fields == 10
	}
}
