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
package com.anrisoftware.prefdialog.fields.filechooser

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.FileChooser
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.FileChooserFieldHandlerFactory

class FileChooserIconsTest extends FieldFixtureHandler {

	static factory = injector.getInstance(FileChooserFieldHandlerFactory)

	static final String FILE_ICON_DEFAULT = "fileIconDefault"

	static final String FILE_ICON_CUSOM = "fileIconCusom"

	static class General {

		@FileChooser
		File fileIconDefault = new File("")

		@FileChooser(icon="com/anrisoftware/prefdialog/panel/inputfields/filechooser/blackwhite/document-open-folder-%d.png")
		File fileIconCusom = new File("")
	}

	@Test
	void "default icon size"() {
		runFieldFixture new General(), FILE_ICON_DEFAULT, factory, {
			assert fixture.button("openfilebutton-$FILE_ICON_DEFAULT").component().icon.iconWidth == 16
		}
	}

	@Test
	void "custom icon"() {
		runFieldFixture new General(), FILE_ICON_CUSOM, factory, {
			assert fixture.button("openfilebutton-$FILE_ICON_CUSOM").component().icon.iconWidth == 16
		}
	}

	@Test
	void testManual() {
		runFieldFixture new General(), FILE_ICON_CUSOM, factory, {
			//Thread.sleep 60000
		}
	}
}
