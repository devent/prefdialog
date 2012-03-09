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

class SliderReadOnlyTest extends AbstractFieldFixture {

	static factory = injector.getInstance(SliderFieldHandlerFactory)

	static class General {

		@Slider(readonly=true)
		int slider = 50
	}

	SliderReadOnlyTest() {
		super(new General(), 'slider', factory)
	}

	@Test
	void "read only"() {
		fixture.slider('slider').requireDisabled()
	}
}