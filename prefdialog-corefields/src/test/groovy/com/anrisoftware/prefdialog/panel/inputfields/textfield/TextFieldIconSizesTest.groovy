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

import com.anrisoftware.prefdialog.annotations.IconSize
import com.anrisoftware.prefdialog.annotations.TextField
import com.anrisoftware.prefdialog.annotations.TextPosition
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.TextFieldHandlerFactory

class TextFieldIconSizesTest extends FieldFixtureHandler {

	static factory = injector.getInstance(TextFieldHandlerFactory)

	static final String ICON_ONLY_DEFAULT_SIZE = "iconOnlyDefaultSize"

	static final String ICON_ONLY_SMALL_SIZE = "iconOnlySmallSize"

	static final String ICON_ONLY_MEDIUM_SIZE = "iconOnlyMediumSize"

	static final String ICON_ONLY_LARGE_SIZE = "iconOnlyLargeSize"

	static final String ICON_ONLY_HUGE_SIZE = "iconOnlyHugeSize"

	static class General {

		@TextField(icon="com/anrisoftware/prefdialog/panel/inputfields/info-%d.png", textPosition=TextPosition.ICON_ONLY)
		String iconOnlyDefaultSize = ""

		@TextField(icon="com/anrisoftware/prefdialog/panel/inputfields/info-%d.png", textPosition=TextPosition.ICON_ONLY, iconSize=IconSize.SMALL)
		String iconOnlySmallSize = ""

		@TextField(icon="com/anrisoftware/prefdialog/panel/inputfields/info-%d.png", textPosition=TextPosition.ICON_ONLY, iconSize=IconSize.MEDIUM)
		String iconOnlyMediumSize = ""

		@TextField(icon="com/anrisoftware/prefdialog/panel/inputfields/info-%d.png", textPosition=TextPosition.ICON_ONLY, iconSize=IconSize.LARGE)
		String iconOnlyLargeSize = ""

		@TextField(icon="com/anrisoftware/prefdialog/panel/inputfields/info-%d.png", textPosition=TextPosition.ICON_ONLY, iconSize=IconSize.HUGE)
		String iconOnlyHugeSize = ""
	}

	@Test
	void "icon only default size"() {
		runFieldFixture new General(), ICON_ONLY_DEFAULT_SIZE, factory, {
			assert fixture.label("$TITLE_LABEL-$ICON_ONLY_DEFAULT_SIZE").component().icon.iconWidth == 16
			assert fixture.label("$TITLE_LABEL-$ICON_ONLY_DEFAULT_SIZE").component().text == null
		}
	}

	@Test
	void "icon only small size"() {
		runFieldFixture new General(), ICON_ONLY_SMALL_SIZE, factory, {
			assert fixture.label("$TITLE_LABEL-$ICON_ONLY_SMALL_SIZE").component().icon.iconWidth == 16
			assert fixture.label("$TITLE_LABEL-$ICON_ONLY_SMALL_SIZE").component().text == null
		}
	}

	@Test
	void "icon only medium size"() {
		runFieldFixture new General(), ICON_ONLY_MEDIUM_SIZE, factory, {
			assert fixture.label("$TITLE_LABEL-$ICON_ONLY_MEDIUM_SIZE").component().icon.iconWidth == 22
			assert fixture.label("$TITLE_LABEL-$ICON_ONLY_MEDIUM_SIZE").component().text == null
		}
	}

	@Test
	void "icon only LARGE size"() {
		runFieldFixture new General(), ICON_ONLY_LARGE_SIZE, factory, {
			assert fixture.label("$TITLE_LABEL-$ICON_ONLY_LARGE_SIZE").component().icon.iconWidth == 32
			assert fixture.label("$TITLE_LABEL-$ICON_ONLY_LARGE_SIZE").component().text == null
		}
	}

	@Test
	void "icon only huge size"() {
		runFieldFixture new General(), ICON_ONLY_HUGE_SIZE, factory, {
			assert fixture.label("$TITLE_LABEL-$ICON_ONLY_HUGE_SIZE").component().icon.iconWidth == 48
			assert fixture.label("$TITLE_LABEL-$ICON_ONLY_HUGE_SIZE").component().text == null
		}
	}

	@Test
	void testManual() {
		runFieldFixture new General(), ICON_ONLY_HUGE_SIZE, factory, {
			//Thread.sleep 60000
		}
	}
}
