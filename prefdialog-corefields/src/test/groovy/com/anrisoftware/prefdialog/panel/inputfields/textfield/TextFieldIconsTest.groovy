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

import static com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel.TITLE_LABEL

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

		@TextField(icon="com/anrisoftware/prefdialog/panel/inputfields/info-%d.png", textPosition=TextPosition.ICON_ONLY)
		String iconOnly = ""

		@TextField(icon="com/anrisoftware/prefdialog/panel/inputfields/info-%d.png", textPosition=TextPosition.TEXT_ALONGSIDE_ICON)
		String alongsideIcon = ""

		@TextField(icon="com/anrisoftware/prefdialog/panel/inputfields/info-%d.png", textPosition=TextPosition.TEXT_UNDER_ICON)
		String underIcon = ""
	}

	@Test
	void "default no icon"() {
		runFieldFixture new General(), DEFAULT_NO_ICON, factory, {
			assert fixture.label("$TITLE_LABEL-$DEFAULT_NO_ICON").component().icon == null
			assert fixture.label("$TITLE_LABEL-$DEFAULT_NO_ICON").component().text == DEFAULT_NO_ICON
		}
	}

	@Test
	void "icon only"() {
		runFieldFixture new General(), ICON_ONLY, factory, {
			assert fixture.label("$TITLE_LABEL-$ICON_ONLY").component().icon.iconWidth == 16
			assert fixture.label("$TITLE_LABEL-$ICON_ONLY").component().text == null
		}
	}

	@Test
	void "alongside icon"() {
		runFieldFixture new General(), ALONGSIDE_ICON, factory, {
			assert fixture.label("$TITLE_LABEL-$ALONGSIDE_ICON").component().icon.iconWidth == 16
			assert fixture.label("$TITLE_LABEL-$ALONGSIDE_ICON").component().text == ALONGSIDE_ICON
		}
	}

	@Test
	void "under icon"() {
		runFieldFixture new General(), UNDER_ICON, factory, {
			assert fixture.label("$TITLE_LABEL-$UNDER_ICON").component().icon.iconWidth == 16
			assert fixture.label("$TITLE_LABEL-$UNDER_ICON").component().text == UNDER_ICON
		}
	}

	@Test
	void testManual() {
		runFieldFixture new General(), UNDER_ICON, factory, {
			//Thread.sleep 60000
		}
	}
}
