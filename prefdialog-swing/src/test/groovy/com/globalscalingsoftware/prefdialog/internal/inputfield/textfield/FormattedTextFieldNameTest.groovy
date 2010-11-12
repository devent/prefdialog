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
package com.globalscalingsoftware.prefdialog.internal.inputfield.textfield

import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.FormattedTextField;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferencePanelTest 

class FormattedTextFieldNameTest extends AbstractPreferencePanelTest {
	
	static class General {
		
		@FormattedTextField("Number of fields")
		double fields = 4
		
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
	void testPanelClickApplyAndClose() {
		window.textBox("fields").enterText "10"
		assert window.label("label-fields").text() == "Number of fields: "
	}
}
