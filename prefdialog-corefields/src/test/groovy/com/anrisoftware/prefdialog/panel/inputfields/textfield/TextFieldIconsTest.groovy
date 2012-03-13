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
package com.anrisoftware.prefdialog.panel.inputfields.textfield

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.TextField
import com.anrisoftware.prefdialog.annotations.TextPosition
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.TextFieldHandlerFactory

class TextFieldIconsTest extends FieldFixtureHandler {

	static factory = injector.getInstance(TextFieldHandlerFactory)

	static final String DEFAULT_NO_ICON = "defaultNoIcon"

	static final String ICON_ONLY = "iconOnly"

	static final String ALONGSIDE_ICON = "alongsideIcon"

	static final String UNDER_ICON = "underIcon"

	static class General {

		@TextField
		String defaultNoIcon = ""

		@TextField(icon="com/anrisoftware/prefdialog/panel/inputfields/textfield/textfield-%d.png", textPosition=TextPosition.ICON_ONLY)
		String iconOnly = ""

		@TextField(icon="com/anrisoftware/prefdialog/panel/inputfields/textfield/textfield-%d.png", textPosition=TextPosition.TEXT_ALONGSIDE_ICON)
		String alongsideIcon = ""

		@TextField(icon="com/anrisoftware/prefdialog/panel/inputfields/textfield/textfield-%d.png", textPosition=TextPosition.TEXT_UNDER_ICON)
		String underIcon = ""
	}

	@Test
	void "default no icon"() {
		createFieldFixture(new General(), DEFAULT_NO_ICON, factory)
		beginFixture()
		assert fixture.label("title_label-$DEFAULT_NO_ICON").component().icon == null
		assert fixture.label("title_label-$DEFAULT_NO_ICON").component().text == DEFAULT_NO_ICON
		endFixture()
	}

	@Test
	void "icon only"() {
		createFieldFixture(new General(), ICON_ONLY, factory)
		beginFixture()
		assert fixture.label("title_label-$ICON_ONLY").component().icon.iconWidth == 16
		assert fixture.label("title_label-$ICON_ONLY").component().text == null
		endFixture()
	}

	@Test
	void "alongside icon"() {
		createFieldFixture(new General(), ALONGSIDE_ICON, factory)
		beginFixture()
		assert fixture.label("title_label-$ALONGSIDE_ICON").component().icon.iconWidth == 16
		assert fixture.label("title_label-$ALONGSIDE_ICON").component().text == ALONGSIDE_ICON
		endFixture()
	}

	@Test
	void "under icon"() {
		createFieldFixture(new General(), UNDER_ICON, factory)
		beginFixture()
		assert fixture.label("title_label-$UNDER_ICON").component().icon.iconWidth == 16
		assert fixture.label("title_label-$UNDER_ICON").component().text == UNDER_ICON
		endFixture()
	}

	@Test
	void testManual() {
		createFieldFixture(new General(), UNDER_ICON, factory)
		beginFixture()
		Thread.sleep 5000
		endFixture()
	}
}
