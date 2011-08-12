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
package com.globalscalingsoftware.prefdialog.panel.inputfields.textfield

import org.junit.Test

import com.globalscalingsoftware.prefdialog.annotations.TextField
import com.globalscalingsoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.TextFieldHandlerFactory
import com.globalscalingsoftware.prefdialog.validators.Validator

class TextFieldValidatorTest extends AbstractFieldFixture {

	static factory = injector.getInstance(TextFieldHandlerFactory)

	static class StringValidator implements Validator<String> {

		public boolean isValid(String value) {
			value != null && !value.trim().isEmpty()
		}
	}

	static class General {

		@TextField(validator=StringValidator, validatorText='Can not be empty')
		String name = ''
	}

	TextFieldValidatorTest() {
		super(new General(), 'name', factory)
	}

	@Test
	void "enter invalid text"() {
		fixture.textBox('name').selectAll()
		fixture.textBox('name').enterText ' '
		fixture.textBox('name').requireToolTip '<html><strong>name</strong> - Can not be empty</html>'
	}

	@Test
	void "enter ivalid text and apply input"() {
		fixture.textBox('name').enterText 'test'
		inputField.applyInput parentObject

		assert fixture.textBox('name').text() == 'test'
		assert parentObject.name == 'test'
	}
}
