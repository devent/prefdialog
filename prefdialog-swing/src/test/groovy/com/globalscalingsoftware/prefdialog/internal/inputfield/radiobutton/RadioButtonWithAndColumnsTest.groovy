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
package com.globalscalingsoftware.prefdialog.internal.inputfield.radiobutton

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.RadioButton;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferencePanelTest;
import com.globalscalingsoftware.prefdialog.internal.inputfield.Colors;

import org.junit.Test;

class RadioButtonWithAndColumnsTest extends AbstractPreferencePanelTest {
	
	static class Preferences {
		
		@Child
		General general = new General()
	}
	
	static class General {
		
		@RadioButton(columns=2, width=-2.0d)
		Colors colors = Colors.BLACK
		
		@Override
		public String toString() {
			"General"
		}
	}
	
	def setupPreferences() {
		preferencesClass = Preferences
		preferences = new Preferences()
		preferencesParentName = "general"
		preferencesParentValue = preferences.general
	}
	
	@Test
	void testPanelClickApplyAndClose() {
		window.radioButton("colors-BLUE").click()
		window.panel("general").button("apply").click()
		
		assert window.label("label-colors").text() == "colors: "
		assert preferences.general.colors == Colors.BLUE
	}
}
