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

class FileChooserTitleTest extends FieldFixtureHandler {

	static factory = injector.getInstance(FileChooserFieldHandlerFactory)

	static class General {

		@FileChooser
		File file1 = new File("")

		@FileChooser(title="Save to file:")
		File file2 = new File("")

		@FileChooser(showTitle=false)
		File file3 = new File("")
	}

	@Test
	void "default title"() {
		runFieldFixture new General(), "file1", factory, {
			assert fixture.label("titlelabel-file1").text() == "file1"
		}
	}

	@Test
	void "custom title"() {
		runFieldFixture new General(), "file2", factory, {
			assert fixture.label("titlelabel-file2").text() == "Save to file:"
		}
	}

	@Test
	void "no title"() {
		runFieldFixture new General(), "file3", factory, {
			//assert fixture.label("label-file3").text() == "file3"
		}
	}

	@Test
	void testManual() {
		runFieldFixture new General(), "file3", factory, {
			//Thread.sleep(30000)
		}
	}
}
