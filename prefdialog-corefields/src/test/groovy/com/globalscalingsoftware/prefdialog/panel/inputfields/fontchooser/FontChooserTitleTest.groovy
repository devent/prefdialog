/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This font is part of prefdialog-swing.
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
package com.globalscalingsoftware.prefdialog.panel.inputfields.fontchooser

import java.awt.Font

import org.junit.Test

import com.globalscalingsoftware.prefdialog.annotations.FontChooser
import com.globalscalingsoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.FontChooserFieldHandlerFactory

/**
 * Test default and custom title.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class FontChooserTitleTest extends FieldFixtureHandler {

	static factory = injector.getInstance(FontChooserFieldHandlerFactory)

	static class General {

		@FontChooser
		Font font1 = Font.decode(null)

		@FontChooser(title='Text font:')
		Font font2 = Font.decode(null)

		@FontChooser(showTitle=false)
		Font font3 = Font.decode(null)
	}

	@Test
	void "default title"() {
		createFieldFixture(new General(), 'font1', factory)
		beginFixture()
		assert fixture.label('label-font1').text() == 'font1'
		endFixture()
	}

	@Test
	void "custom title"() {
		createFieldFixture(new General(), 'font2', factory)
		beginFixture()
		assert fixture.label('label-font2').text() == 'Text font:'
		endFixture()
	}

	@Test
	void "no title"() {
		createFieldFixture(new General(), 'font3', factory)
		beginFixture()
		//assert fixture.label('label-font3').text() == 'font3'
		endFixture()
	}

	@Test
	void testManual() {
		createFieldFixture(new General(), 'font2', factory)
		beginFixture()
		Thread.sleep(30000)
		endFixture()
	}
}
