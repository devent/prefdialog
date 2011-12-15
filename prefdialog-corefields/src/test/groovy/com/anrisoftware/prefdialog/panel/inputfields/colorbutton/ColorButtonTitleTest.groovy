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
package com.anrisoftware.prefdialog.panel.inputfields.colorbutton

import java.awt.Color

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.ColorButton
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.ColorButtonFieldHandlerFactory

/**
 * Test the title and showTitle attributes of the color button.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class ColorButtonTitleTest extends FieldFixtureHandler {

	static factory = injector.getInstance(ColorButtonFieldHandlerFactory)

	static class General {

		@ColorButton
		Color color1 = Color.WHITE

		@ColorButton(title='Color:')
		Color color2 = Color.WHITE

		@ColorButton(showTitle=false)
		Color color3 = Color.WHITE
	}

	@Test
	void "default title"() {
		createFieldFixture(new General(), 'color1', factory)
		beginFixture()
		assert fixture.label('label-color1').text() == 'color1'
		endFixture()
	}

	@Test
	void "custom title"() {
		createFieldFixture(new General(), 'color2', factory)
		beginFixture()
		assert fixture.label('label-color2').text() == 'Color:'
		endFixture()
	}

	@Test
	void "no title"() {
		createFieldFixture(new General(), 'color3', factory)
		beginFixture()
		//assert fixture.label('label-file3').text() == 'file3'
		endFixture()
	}

	@Test
	void testManual() {
		//Thread.sleep(30000)
	}
}
