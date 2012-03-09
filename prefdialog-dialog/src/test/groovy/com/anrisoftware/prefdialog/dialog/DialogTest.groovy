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
package com.anrisoftware.prefdialog.dialog


import org.junit.Test

import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.TextField

class DialogTest extends AbstractPreferenceDialogFixture {

	static class Preferences {

		@Child
		General general = new General()

		@Override
		String toString() {
			'Preferences'
		}
	}

	static class General {

		@TextField
		String name = ''

		@Override
		public String toString() {
			'General'
		}
	}

	def setupPreferences() {
		preferences = new Preferences()
	}

	@Test
	void testComponents() {
		assert fixture.target.title == 'Preferences'

		fixture.textBox('name').requireVisible()
		assert fixture.textBox('name').text() == ''
		assert fixture.button('ok').text() == 'Ok'
		assert fixture.button('cancel').text() == 'Cancel'
		assert fixture.button('apply').text() == 'Apply'
	}

	@Test
	void testClickEnterTextOk() {
		fixture.textBox('name').deleteText()
		fixture.textBox('name').enterText 'name'
		fixture.button('ok').click()

		assert preferences.general.name == 'name'
	}

	@Test
	void testClickEnterTextCancel() {
		fixture.textBox('name').enterText 'name'
		fixture.button('cancel').click()

		assert preferences.general.name == ''
	}

	@Test
	void testClickEnterTextApply() {
		fixture.textBox('name').enterText 'name'
		fixture.button('apply').click()

		assert fixture.textBox('name').text() == 'name'
		assert preferences.general.name == 'name'
	}

	@Test
	void testManual() {
		//Thread.sleep(60000)
	}
}