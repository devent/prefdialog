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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.radiobutton

import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.Child 
import com.globalscalingsoftware.prefdialog.annotations.RadioButton 
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.AbstractPreferencePanelTest;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.Colors 

class MultipleRadioButtonTest extends AbstractPreferencePanelTest {
	
	static class General {
		
		@RadioButton(columns=2)
		Colors colors1 = Colors.BLACK
		
		@RadioButton(columns=2)
		Colors colors2 = Colors.BLUE
		
		@RadioButton
		Colors colors3 = Colors.CYAN
		
		@RadioButton
		Colors colors4 = Colors.GREEN
		
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
		preferences = new Preferences()
		panelName = "General"
	}
	
	@Test
	void testPanelClickApplyAndClose() {
		fixture.radioButton("colors1-BLUE").click()
		fixture.radioButton("colors2-BLUE").click()
		fixture.radioButton("colors3-BLUE").click()
		fixture.radioButton("colors4-BLUE").click()
		fixture.panel("general").button("apply").click()
		
		fixture.label("label-colors1").requireText "colors1: "
		fixture.label("label-colors2").requireText "colors2: "
		fixture.label("label-colors3").requireText "colors3: "
		fixture.label("label-colors4").requireText "colors4: "
		assert preferences.general.colors1 == Colors.BLUE
		assert preferences.general.colors2 == Colors.BLUE
		assert preferences.general.colors3 == Colors.BLUE
		assert preferences.general.colors4 == Colors.BLUE
	}
}
