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
package com.anrisoftware.prefdialog.panel.inputfields.filechooser

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.FileChooser
import com.anrisoftware.prefdialog.annotations.IconSize
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.FileChooserFieldHandlerFactory

class FileChooserIconSizesTest extends FieldFixtureHandler {

	static factory = injector.getInstance(FileChooserFieldHandlerFactory)

	static class General {

		@FileChooser
		File fileIconDefault = new File('')

		@FileChooser(iconSize=IconSize.SMALL)
		File fileIconSmall = new File('')

		@FileChooser(iconSize=IconSize.MEDIUM)
		File fileIconMedium = new File('')

		@FileChooser(iconSize=IconSize.LARGE)
		File fileIconLarge = new File('')

		@FileChooser(iconSize=IconSize.HUGE)
		File fileIconHuge = new File('')
	}

	@Test
	void "default icon size"() {
		createFieldFixture(new General(), 'fileIconDefault', factory)
		beginFixture()
		assert fixture.button("openfilebutton-fileIconDefault").component().icon.iconWidth == 16
		endFixture()
	}

	@Test
	void "small icon size"() {
		createFieldFixture(new General(), 'fileIconSmall', factory)
		beginFixture()
		assert fixture.button("openfilebutton-fileIconSmall").component().icon.iconWidth == 16
		endFixture()
	}

	@Test
	void "medium icon size"() {
		createFieldFixture(new General(), 'fileIconMedium', factory)
		beginFixture()
		assert fixture.button("openfilebutton-fileIconMedium").component().icon.iconWidth == 22
		endFixture()
	}

	@Test
	void "large icon size"() {
		createFieldFixture(new General(), 'fileIconLarge', factory)
		beginFixture()
		assert fixture.button("openfilebutton-fileIconLarge").component().icon.iconWidth == 32
		endFixture()
	}

	@Test
	void "huge icon size"() {
		createFieldFixture(new General(), 'fileIconHuge', factory)
		beginFixture()
		assert fixture.button("openfilebutton-fileIconHuge").component().icon.iconWidth == 48
		endFixture()
	}

	@Test
	void testManual() {
		createFieldFixture(new General(), 'fileIconHuge', factory)
		beginFixture()
		Thread.sleep 5000
		endFixture()
	}
}
