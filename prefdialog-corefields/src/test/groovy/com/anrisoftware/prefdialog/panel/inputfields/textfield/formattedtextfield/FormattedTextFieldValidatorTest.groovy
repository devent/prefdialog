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

	static final String NUMBER = "number"

	static class FieldsValidator implements Validator<Integer> {
		public boolean isValid(Integer value) {
			value >= 2 && value <= 100
		}
	}

	static class General {

		@FormattedTextField(validator=FieldsValidator, validatorText="Must be a number and between 2 and 100")
		int number = 4
	}

	FormattedTextFieldValidatorTest() {
		super(new General(), NUMBER, factory)
	}

	@Test
	void "enter invalid text"() {
		fixture.textBox(NUMBER).deleteText()
		fixture.textBox(NUMBER).enterText "1"
		fixture.textBox(NUMBER).requireToolTip "<html><strong>$NUMBER</strong> - Must be a number and between 2 and 100</html>"
	}

	@Test
	void "enter valid text and apply input"() {
		fixture.textBox(NUMBER).deleteText()
		fixture.textBox(NUMBER).enterText "10"
		inputField.applyInput parentObject

		assert fixture.textBox(NUMBER).text() == "10"
		assert parentObject.number == 10
	}
}
