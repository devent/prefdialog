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
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.FileChooserFieldHandlerFactory

class FileChooserTextTest extends FieldFixtureHandler {

	static factory = injector.getInstance(FileChooserFieldHandlerFactory)

	static final String FILE_CUSTOM_TEXT = "fileCustomText"

	static final String FILE_DEFAULT = "fileDefault"

	static final String DEFAULT_TEXT = "Open…"

	static final String CUSTOM_TEXT = "custom"

	static class General {

		@FileChooser
		File fileDefault = new File("")

		@FileChooser(text="custom")
		File fileCustomText = new File("")
	}

	@Test
	void "default text"() {
		runFieldFixture new General(), FILE_DEFAULT, factory, {
			fixture.button("openfilebutton-$FILE_DEFAULT").requireText(DEFAULT_TEXT)
		}
	}

	@Test
	void "custom text"() {
		runFieldFixture new General(), FILE_CUSTOM_TEXT, factory, {
			fixture.button("openfilebutton-$FILE_CUSTOM_TEXT").requireText(CUSTOM_TEXT)
		}
	}

	@Test
	void testManual() {
		runFieldFixture new General(), FILE_DEFAULT, factory, {
			//Thread.sleep 60000
		}
	}
}
