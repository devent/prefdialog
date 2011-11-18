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
package com.globalscalingsoftware.prefdialog.panel.inputfields.color

import java.awt.Color

import org.junit.Test

import com.globalscalingsoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.ColorFieldHandlerFactory

class ColorTest extends AbstractFieldFixture {

	static factory = injector.getInstance(ColorFieldHandlerFactory)

	static class General {

		@com.globalscalingsoftware.prefdialog.annotations.Color
		Color color = Color.WHITE
	}

	ColorTest() {
		super(new General(), 'color', factory)
	}

	@Test
	void "names and titles"() {
		fixture.label('label-color').requireVisible()
		assert fixture.label('label-color').text() == 'color'
		fixture.button('colorbutton-color').requireVisible()
		assert fixture.button('colorbutton-color').text() == '#ffffff'
	}

	@Test
	void "manually"() {
		Thread.sleep 30000
	}
}
