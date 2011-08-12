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
import com.globalscalingsoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.TextFieldHandlerFactory

class TextFieldTitleTest extends FieldFixtureHandler {

	static factory = injector.getInstance(TextFieldHandlerFactory)

	static class General {

		@TextField
		String name1 = ''

		@TextField(title='Project name')
		String name2 = ''

		@TextField(showTitle=false)
		String name3 = ''
	}

	@Test
	void "default title"() {
		createFieldFixture(new General(), 'name1', factory)
		beginFixture()
		fixture.textBox('name1').requireVisible()
		assert fixture.label('label-name1').text() == 'name1'
		endFixture()
	}

	@Test
	void "custom title"() {
		createFieldFixture(new General(), 'name2', factory)
		beginFixture()
		fixture.textBox('name2').requireVisible()
		assert fixture.label('label-name2').text() == 'Project name'
		endFixture()
	}
}
