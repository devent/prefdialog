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
package com.globalscalingsoftware.prefdialog.panel.inputfields.radiobutton

import org.junit.Test

import com.globalscalingsoftware.prefdialog.annotations.RadioButton
import com.globalscalingsoftware.prefdialog.panel.inputfields.Colors
import com.globalscalingsoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.RadioButtonFieldHandlerFactory

class RadioButtonTitleTest extends FieldFixtureHandler {

	static factory = injector.getInstance(RadioButtonFieldHandlerFactory)

	static class General {

		@RadioButton
		Colors colors1 = Colors.BLACK

		@RadioButton(title='Some colors')
		Colors colors2 = Colors.BLACK

		@RadioButton(showTitle=false)
		Colors colors3 = Colors.BLACK
	}

	@Test
	void "default title"() {
		createFieldFixture(new General(), 'colors1', factory)
		beginFixture()
		assert fixture.label('label-colors1').text() == 'colors1'
		endFixture()
	}

	@Test
	void "custom title"() {
		createFieldFixture(new General(), 'colors2', factory)
		beginFixture()
		assert fixture.label('label-colors2').text() == 'Some colors'
		endFixture()
	}

	@Test
	void "no title"() {
		createFieldFixture(new General(), 'colors3', factory)
		beginFixture()
		//assert fixture.label('label-colors2').text() == 'colors3'
		endFixture()
	}
}
