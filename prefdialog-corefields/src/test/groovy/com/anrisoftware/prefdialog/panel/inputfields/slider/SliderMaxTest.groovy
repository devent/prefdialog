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
import com.anrisoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.anrisoftware.prefdialog.panel.inputfields.api.SliderFieldHandlerFactory

class SliderMaxTest extends AbstractFieldFixture {

	static factory = injector.getInstance(SliderFieldHandlerFactory)

	static class General {

		@Slider(max=200)
		int slider = 50
	}

	SliderMaxTest() {
		super(new General(), 'slider', factory)
	}

	@Test
	void "choose maximum and apply input"() {
		fixture.slider('slider').slideToMaximum()
		inputField.applyInput parentObject

		assert parentObject.slider == 200
	}
}
