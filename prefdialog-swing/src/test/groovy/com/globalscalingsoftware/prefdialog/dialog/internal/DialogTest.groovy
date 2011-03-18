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
package com.globalscalingsoftware.prefdialog.dialog.internal


import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.TextField 

class DialogTest extends AbstractPreferenceDialogFixture {
	
	static class Preferences {
		
		@Child
		General general = new General()
		
		@Override
		String toString() {
			"Preferences"
		}
	}
	
	static class General {
		
		@TextField
		String name = ""
		
		@Override
		public String toString() {
			"General"
		}
	}
	
	def setupPreferences() {
		preferences = new Preferences()
	}
	
	@Test
	void testComponents() {
		assert fixture.target.title == "Preferences"
		
		fixture.textBox("name").requireVisible()
		assert fixture.textBox("name").text() == ""
		
		fixture.button("ok").requireDisabled()
		fixture.button("ok").requireVisible()
		assert fixture.button("ok").text() == "Ok"
		
		fixture.button("cancel").requireEnabled()
		fixture.button("cancel").requireVisible()
		assert fixture.button("cancel").text() == "Cancel"
		
		fixture.button("restore").requireEnabled()
		fixture.button("restore").requireVisible()
		assert fixture.button("restore").text() == "Restore"
		
		fixture.button("apply").requireDisabled()
		fixture.button("apply").requireVisible()
		assert fixture.button("apply").text() == "Apply"
	}
	
	@Test
	void testClickEnterTextOk() {
		fixture.button("ok").requireDisabled()
		fixture.textBox("name").enterText "name"
		fixture.button("ok").requireEnabled()
		fixture.button("ok").click()
		assert preferences.general.name == "name"
	}
	
	@Test
	void testClickEnterTextCancel() {
		fixture.button("ok").requireDisabled()
		fixture.textBox("name").enterText "name"
		fixture.button("cancel").click()
		assert preferences.general.name == ""
	}
	
	@Test
	void testClickEnterTextApply() {
		fixture.button("ok").requireDisabled()
		fixture.button("apply").requireDisabled()
		
		fixture.textBox("name").enterText "name"
		fixture.button("apply").click()
		fixture.button("apply").requireDisabled()
		fixture.button("ok").requireEnabled()
		assert preferences.general.name == "name"
	}
	
	@Test
	void testClickEnterTextRestore() {
		fixture.button("ok").requireDisabled()
		fixture.button("apply").requireDisabled()
		
		fixture.textBox("name").enterText "name"
		fixture.button("restore").click()
		
		fixture.button("apply").requireDisabled()
		fixture.button("ok").requireEnabled()
		assert preferences.general.name == ""
	}
}
