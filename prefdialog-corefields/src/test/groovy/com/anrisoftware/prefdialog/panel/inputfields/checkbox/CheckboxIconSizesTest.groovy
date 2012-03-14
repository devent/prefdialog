/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-corefields.
 * 
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel.inputfields.checkbox

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.Checkbox
import com.anrisoftware.prefdialog.annotations.IconSize
import com.anrisoftware.prefdialog.annotations.TextPosition
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.CheckBoxFieldHandlerFactory

class CheckboxIconSizesTest extends FieldFixtureHandler {

	static factory = injector.getInstance(CheckBoxFieldHandlerFactory)

	static final String ALONGSIDE_ICON_DEFAULT_SIZE = "alongsideIconDefaultSize"

	static final String ALONGSIDE_ICON_SMALL_SIZE = "alongsideIconSmallSize"

	static final String ALONGSIDE_ICON_MEDIUM_SIZE = "alongsideIconMediumSize"

	static final String ALONGSIDE_ICON_LARGE_SIZE = "alongsideIconLargeSize"

	static final String ALONGSIDE_ICON_HUGE_SIZE = "alongsideIconHugeSize"

	static class General {

		@Checkbox(icon="com/anrisoftware/prefdialog/panel/inputfields/info-%d.png", textPosition=TextPosition.TEXT_ALONGSIDE_ICON)
		def alongsideIconDefaultSize = false

		@Checkbox(icon="com/anrisoftware/prefdialog/panel/inputfields/info-%d.png", textPosition=TextPosition.TEXT_ALONGSIDE_ICON, iconSize=IconSize.SMALL)
		def alongsideIconSmallSize = false

		@Checkbox(icon="com/anrisoftware/prefdialog/panel/inputfields/info-%d.png", textPosition=TextPosition.TEXT_ALONGSIDE_ICON, iconSize=IconSize.MEDIUM)
		def alongsideIconMediumSize = false

		@Checkbox(icon="com/anrisoftware/prefdialog/panel/inputfields/info-%d.png", textPosition=TextPosition.TEXT_ALONGSIDE_ICON, iconSize=IconSize.LARGE)
		def alongsideIconLargeSize = false

		@Checkbox(icon="com/anrisoftware/prefdialog/panel/inputfields/info-%d.png", textPosition=TextPosition.TEXT_ALONGSIDE_ICON, iconSize=IconSize.HUGE)
		def alongsideIconHugeSize = false
	}

	@Test
	void "alongside icon default size"() {
		createFieldFixture(new General(), ALONGSIDE_ICON_DEFAULT_SIZE, factory)
		beginFixture()
		fixture.checkBox("$ALONGSIDE_ICON_DEFAULT_SIZE").click()
		assert fixture.label("title_label-$ALONGSIDE_ICON_DEFAULT_SIZE").component().icon.iconWidth == 16
		assert fixture.label("title_label-$ALONGSIDE_ICON_DEFAULT_SIZE").component().text == ALONGSIDE_ICON_DEFAULT_SIZE
		endFixture()
	}

	@Test
	void "alongside icon small size"() {
		createFieldFixture(new General(), ALONGSIDE_ICON_SMALL_SIZE, factory)
		beginFixture()
		fixture.checkBox("$ALONGSIDE_ICON_SMALL_SIZE").click()
		assert fixture.label("title_label-$ALONGSIDE_ICON_SMALL_SIZE").component().icon.iconWidth == 16
		assert fixture.label("title_label-$ALONGSIDE_ICON_SMALL_SIZE").component().text == ALONGSIDE_ICON_SMALL_SIZE
		endFixture()
	}

	@Test
	void "alongside icon medium size"() {
		createFieldFixture(new General(), ALONGSIDE_ICON_MEDIUM_SIZE, factory)
		beginFixture()
		fixture.checkBox("$ALONGSIDE_ICON_MEDIUM_SIZE").click()
		assert fixture.label("title_label-$ALONGSIDE_ICON_MEDIUM_SIZE").component().icon.iconWidth == 22
		assert fixture.label("title_label-$ALONGSIDE_ICON_MEDIUM_SIZE").component().text == ALONGSIDE_ICON_MEDIUM_SIZE
		endFixture()
	}

	@Test
	void "alongside icon LARGE size"() {
		createFieldFixture(new General(), ALONGSIDE_ICON_LARGE_SIZE, factory)
		beginFixture()
		fixture.checkBox("$ALONGSIDE_ICON_LARGE_SIZE").click()
		assert fixture.label("title_label-$ALONGSIDE_ICON_LARGE_SIZE").component().icon.iconWidth == 32
		assert fixture.label("title_label-$ALONGSIDE_ICON_LARGE_SIZE").component().text == ALONGSIDE_ICON_LARGE_SIZE
		endFixture()
	}

	@Test
	void "alongside icon huge size"() {
		createFieldFixture(new General(), ALONGSIDE_ICON_HUGE_SIZE, factory)
		beginFixture()
		fixture.checkBox("$ALONGSIDE_ICON_HUGE_SIZE").click()
		assert fixture.label("title_label-$ALONGSIDE_ICON_HUGE_SIZE").component().icon.iconWidth == 48
		assert fixture.label("title_label-$ALONGSIDE_ICON_HUGE_SIZE").component().text == ALONGSIDE_ICON_HUGE_SIZE
		endFixture()
	}

	@Test
	void testManual() {
		createFieldFixture(new General(), ALONGSIDE_ICON_HUGE_SIZE, factory)
		beginFixture()
		//Thread.sleep 60000
		endFixture()
	}
}
