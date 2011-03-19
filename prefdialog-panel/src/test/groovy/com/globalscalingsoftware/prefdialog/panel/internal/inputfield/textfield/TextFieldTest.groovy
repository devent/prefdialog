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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield

import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.AbstractPreferencePanelTest;

class TextFieldTest extends AbstractPreferencePanelTest {
	
	static class General {
		
		@TextField
		String name = ""
		
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
	void testComponents() {
		fixture.label("label-name").requireVisible()
		assert fixture.label("label-name").text() == "name: "
		fixture.textBox("name").requireVisible()
		assert fixture.textBox("name").text() == ""
		fixture.button("apply").requireVisible()
		assert fixture.button("apply").text() == "Apply "
		fixture.button("restore").requireVisible()
		assert fixture.button("restore").text() == "Restore"
	}
	
	@Test
	void testEnterTextAndApply() {
		fixture.textBox("name").enterText "test"
		fixture.button("apply").click()
		
		assert fixture.label("label-name").text() == "name: "
		assert preferences.general.name == "test"
	}
	
	@Test
	void testEnterTextAndRestore() {
		fixture.textBox("name").enterText "test"
		fixture.button("restore").click()
		assert preferences.general.name == ""
	}
}
