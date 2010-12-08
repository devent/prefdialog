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
package com.globalscalingsoftware.prefdialog.internal.inputfield.slider


import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.Slider 
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferencePanelTest 

class SliderCustomModelTest extends AbstractPreferencePanelTest {
	
	static class CustomModel extends DefaultBoundedRangeModel {
		
		CustomModel() {
			super(2, 1, 2, 1025)
		}
		
		@Override
		void setMinimum(int arg0) {
		}
		
		@Override
		int getMinimum() {
			return 2
		}
		
		@Override
		void setMaximum(int arg0) {
		}
		
		@Override
		int getMaximum() {
			return 1025
		}
		
		void setExtent(int arg0) {
		}

		@Override
		public int getExtent() {
			return 1
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
		int slider = 2
		
		@Override
		public String toString() {
			"General"
		}
	}
	
	static class Preferences {
		
		@Child
		General general = new General()
	}
	
	def setupPreferences() {
		preferencesClass = Preferences
		preferences = new Preferences()
		preferencesParentName = "general"
		preferencesParentValue = preferences.general
	}
	
	@Test
	void testMinimum() {
		window.slider("slider").slideToMinimum()
		window.panel("general").button("apply").click()
		assert preferences.general.slider == 2
	}
	
	@Test
	void testMaximum() {
		window.slider("slider").slideToMaximum()
		window.panel("general").button("apply").click()
		assert preferences.general.slider == 1024
	}
	
	@Test
	void testRandomValue() {
		window.slider("slider").slideTo 35
		window.panel("general").button("apply").click()
		assert preferences.general.slider == 64
	}
}