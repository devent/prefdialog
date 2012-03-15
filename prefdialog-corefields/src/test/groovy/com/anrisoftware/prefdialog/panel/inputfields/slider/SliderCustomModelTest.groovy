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


import javax.swing.DefaultBoundedRangeModel

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.Slider
import com.anrisoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.anrisoftware.prefdialog.panel.inputfields.api.SliderFieldHandlerFactory

class SliderCustomModelTest extends AbstractFieldFixture {

	static factory = injector.getInstance(SliderFieldHandlerFactory)

	static final String CUSTOM_MODEL = "customModel"

	static class CustomModel extends DefaultBoundedRangeModel {

		CustomModel() {
			super(32, 0, 2, 1024)
		}

		@Override
		void setValue(int value) {
			def log2 = log2(value)
			if (log2 % 2 != 0) {
				def ex = Math.ceil(log2)
				value = Math.pow(2, ex)
			}
			super.setValue(value)
		}

		private log2(def x) {
			Math.log(x) / Math.log(2)
		}
	}

	static class General {

		@Slider(model=CustomModel)
		int customModel = 2
	}

	SliderCustomModelTest() {
		super(new General(), CUSTOM_MODEL, factory)
	}

	@Test
	void "choose minimum and apply input"() {
		fixture.slider(CUSTOM_MODEL).slideToMinimum()
		inputField.applyInput parentObject

		assert parentObject.customModel == 2
	}

	@Test
	void "choose maximum and apply input"() {
		fixture.slider(CUSTOM_MODEL).slideToMaximum()
		inputField.applyInput parentObject

		assert parentObject.customModel == 1024
	}

	@Test
	void "choose close and apply input"() {
		fixture.slider(CUSTOM_MODEL).slideTo 62
		inputField.applyInput parentObject

		assert parentObject.customModel == 64
	}

	@Test
	void testManually() {
		Thread.sleep 60000
	}
}
