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
package com.anrisoftware.prefdialog.panel.inputfields.textfield

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.TextField
import com.anrisoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.anrisoftware.prefdialog.panel.inputfields.api.TextFieldHandlerFactory

class TextFieldTest extends AbstractFieldFixture {

	static factory = injector.getInstance(TextFieldHandlerFactory)

	static final String SIMPLE = "simple"

	static class General {

		@TextField
		String simple = ""
	}

	TextFieldTest() {
		super(new General(), SIMPLE, factory)
	}

	@Test
	void "enter text and apply input"() {
		fixture.textBox(SIMPLE).enterText "test"
		inputField.applyInput parentObject

		assert fixture.textBox(SIMPLE).text() == "test"
		assert parentObject.simple == "test"
	}

	@Test
	void "enter text and restore input"() {
		fixture.textBox(SIMPLE).enterText "test"
		inputField.restoreInput parentObject

		assert fixture.textBox(SIMPLE).text() == ""
		assert parentObject.simple == ""
	}

	@Test
	void testManually() {
		//Thread.sleep 60000
	}
}
