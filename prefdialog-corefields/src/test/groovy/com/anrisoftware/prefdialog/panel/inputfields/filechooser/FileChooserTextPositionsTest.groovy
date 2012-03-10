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
import com.anrisoftware.prefdialog.annotations.TextPosition
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.FileChooserFieldHandlerFactory

class FileChooserTextPositionsTest extends FieldFixtureHandler {

	static factory = injector.getInstance(FileChooserFieldHandlerFactory)

	static final String FILE_TEXT_ALONGSIDE_ICON = "fileTextAlongsideIcon"

	static final String FILE_TEXT_UNDER_ICON = "fileTextUnderIcon"

	static final String FILE_TEXT_ONLY = "fileTextOnly"

	static final String FILE_ICON_ONLY = "fileIconOnly"

	static final String FILE_DEFAULT = "fileDefault"

	static class General {

		@FileChooser
		File fileDefault = new File("")

		@FileChooser(textPosition=TextPosition.ICON_ONLY)
		File fileIconOnly = new File("")

		@FileChooser(textPosition=TextPosition.TEXT_ONLY)
		File fileTextOnly = new File("")

		@FileChooser(textPosition=TextPosition.TEXT_ALONGSIDE_ICON)
		File fileTextAlongsideIcon = new File("")

		@FileChooser(textPosition=TextPosition.TEXT_UNDER_ICON)
		File fileTextUnderIcon = new File("")
	}

	@Test
	void "default text position size"() {
		createFieldFixture(new General(), FILE_DEFAULT, factory)
		beginFixture()
		assert fixture.button("openfilebutton-$FILE_DEFAULT").component().icon != null
		fixture.button("openfilebutton-$FILE_DEFAULT").requireText("...")
		endFixture()
	}

	@Test
	void "icon only"() {
		createFieldFixture(new General(), FILE_ICON_ONLY, factory)
		beginFixture()
		assert fixture.button("openfilebutton-$FILE_ICON_ONLY").component().icon != null
		fixture.button("openfilebutton-$FILE_ICON_ONLY").requireText(null)
		endFixture()
	}

	@Test
	void "text only"() {
		createFieldFixture(new General(), FILE_TEXT_ONLY, factory)
		beginFixture()
		assert fixture.button("openfilebutton-$FILE_TEXT_ONLY").component().icon == null
		fixture.button("openfilebutton-$FILE_TEXT_ONLY").requireText("...")
		endFixture()
	}

	@Test
	void "text alongside icon"() {
		createFieldFixture(new General(), FILE_TEXT_ALONGSIDE_ICON, factory)
		beginFixture()
		assert fixture.button("openfilebutton-$FILE_TEXT_ALONGSIDE_ICON").component().icon != null
		fixture.button("openfilebutton-$FILE_TEXT_ALONGSIDE_ICON").requireText("...")
		endFixture()
	}

	@Test
	void "text under icon"() {
		createFieldFixture(new General(), FILE_TEXT_UNDER_ICON, factory)
		beginFixture()
		assert fixture.button("openfilebutton-$FILE_TEXT_UNDER_ICON").component().icon != null
		fixture.button("openfilebutton-$FILE_TEXT_UNDER_ICON").requireText("...")
		endFixture()
	}

	@Test
	void testManual() {
		createFieldFixture(new General(), FILE_TEXT_ALONGSIDE_ICON, factory)
		beginFixture()
		Thread.sleep 5000
		endFixture()
	}
}
