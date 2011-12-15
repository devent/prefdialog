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
package com.anrisoftware.prefdialog.panel.inputfields.slider

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.Slider
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.SliderFieldHandlerFactory

class SliderTitleTest extends FieldFixtureHandler {

	static factory = injector.getInstance(SliderFieldHandlerFactory)

	static class General {

		@Slider
		int slider1 = 0

		@Slider(title='Some slider')
		int slider2 = 0

		@Slider(showTitle=false)
		int slider3 = 0
	}

	@Test
	void "default title"() {
		createFieldFixture(new General(), 'slider1', factory)
		beginFixture()
		assert fixture.label('label-slider1').text() == 'slider1'
		endFixture()
	}

	@Test
	void "custom title"() {
		createFieldFixture(new General(), 'slider2', factory)
		beginFixture()
		assert fixture.label('label-slider2').text() == 'Some slider'
		endFixture()
	}
}
