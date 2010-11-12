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
import com.globalscalingsoftware.prefdialog.annotations.fields.Group 
import com.globalscalingsoftware.prefdialog.annotations.fields.TextField;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferencePanelTest 

class TextFieldGroupTest extends AbstractPreferencePanelTest {
	
	static class Group1 {
		
		@TextField
		String textField1 = ""
		
		@TextField
		String textField2 = ""
	}
	
	static class Group2 {
		
		@TextField
		String textField3 = ""
		
		@TextField
		String textField4 = ""
	}
	
	static class General {
		
		@TextField
		String preGroup = ""
		
		@Group
		Group1 group1 = new Group1()
		
		@Group
		Group2 group2 = new Group2()
		
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
		window.textBox("textField1").enterText "test1"
		window.textBox("textField2").enterText "test2"
		window.textBox("textField3").enterText "test3"
		window.textBox("textField4").enterText "test4"
		window.panel("general").button("apply").click()
		
		assert window.label("label-textField1").text() == "textField1: "
		assert preferences.general.group1.textField1 == "test1"
		assert preferences.general.group1.textField2 == "test2"
		assert preferences.general.group2.textField3 == "test3"
		assert preferences.general.group2.textField4 == "test4"
	}
}
