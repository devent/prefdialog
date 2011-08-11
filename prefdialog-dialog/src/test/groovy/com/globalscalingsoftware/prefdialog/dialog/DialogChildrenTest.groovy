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
package com.globalscalingsoftware.prefdialog.dialog

import org.junit.Test

import com.globalscalingsoftware.prefdialog.annotations.Child
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField
import com.globalscalingsoftware.prefdialog.annotations.TextField

class DialogChildrenTest extends AbstractPreferenceDialogFixture {

	static class Preferences {

		@Child
		Child1 general = new Child1()

		@Child
		Child2 child2 = new Child2()
	}

	static class Child1 {

		@TextField
		String name = ''

		@FormattedTextField
		int fields = 4

		@Override
		public String toString() {
			'Child1'
		}
	}

	static class Child2 {

		@TextField
		String something = ''

		@FormattedTextField
		int moreFields = 4

		@Override
		public String toString() {
			'Child2'
		}
	}

	def setupPreferences() {
		preferences = new Preferences()
	}

	@Test
	void testClickOkAndClose() {
		fixture.textBox('name').enterText 'name'
		fixture.textBox('fields').enterText '10'
		fixture.tree('child_tree').clickPath 'Child2'
		fixture.textBox('something').enterText 'text'
		fixture.textBox('moreFields').enterText '20'
		fixture.button('ok').click()

		assert preferences.general.name == 'name'
		assert preferences.general.fields == 104
		assert preferences.child2.something == 'text'
		assert preferences.child2.moreFields == 204
	}
}
