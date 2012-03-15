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

class FormattedTextFieldDoubleTest extends AbstractFieldFixture {

	static factory = injector.getInstance(FormattedTextFieldHandlerFactory)

	static final String DECIMAL = "decimal"

	static class General {

		@FormattedTextField
		double decimal = 0d
	}

	FormattedTextFieldDoubleTest() {
		super(new General(), DECIMAL, factory)
	}

	@Test
	void "enter valid input and apply"() {
		fixture.textBox(DECIMAL).deleteText()
		fixture.textBox(DECIMAL).enterText "0.001"
		inputField.applyInput parentObject

		assert fixture.textBox(DECIMAL).text() == "0.001"
		assert parentObject.decimal == 0.001
	}

	@Test
	void "manually"() {
		//Thread.sleep 60000
	}
}
