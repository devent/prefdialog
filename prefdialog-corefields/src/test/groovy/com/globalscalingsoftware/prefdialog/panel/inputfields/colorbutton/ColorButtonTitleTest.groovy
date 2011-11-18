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
package com.globalscalingsoftware.prefdialog.panel.inputfields.colorbutton

import org.junit.Test

import com.globalscalingsoftware.prefdialog.annotations.FileChooser
import com.globalscalingsoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.FileChooserFieldHandlerFactory

class ColorButtonTitleTest extends FieldFixtureHandler {

	static factory = injector.getInstance(FileChooserFieldHandlerFactory)

	static class General {

		@FileChooser
		File file1 = new File('')

		@FileChooser(title='Save to file:')
		File file2 = new File('')

		@FileChooser(showTitle=false)
		File file3 = new File('')
	}

	@Test
	void "default title"() {
		createFieldFixture(new General(), 'file1', factory)
		beginFixture()
		assert fixture.label('label-file1').text() == 'file1'
		endFixture()
	}

	@Test
	void "custom title"() {
		createFieldFixture(new General(), 'file2', factory)
		beginFixture()
		assert fixture.label('label-file2').text() == 'Save to file:'
		endFixture()
	}

	@Test
	void "no title"() {
		createFieldFixture(new General(), 'file3', factory)
		beginFixture()
		//assert fixture.label('label-file3').text() == 'file3'
		endFixture()
	}

	@Test
	void testManual() {
		//Thread.sleep(30000)
	}
}
