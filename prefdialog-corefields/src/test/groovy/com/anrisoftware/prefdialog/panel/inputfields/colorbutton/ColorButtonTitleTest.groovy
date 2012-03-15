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
package com.anrisoftware.prefdialog.panel.inputfields.colorbutton

import static com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel.TITLE_LABEL

import java.awt.Color

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.ColorButton
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.ColorButtonFieldHandlerFactory

/**
 * Test the title and showTitle attributes of the color button.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class ColorButtonTitleTest extends FieldFixtureHandler {

	static factory = injector.getInstance(ColorButtonFieldHandlerFactory)

	static final String DEFAULT_TITLE = "color1"

	static final String CUSTOM_TITLE = "color2"

	static final String HIDE_TITLE = "color3"

	static class General {

		@ColorButton
		Color color1 = Color.WHITE

		@ColorButton(title="color2")
		Color color2 = Color.WHITE

		@ColorButton(showTitle=false)
		Color color3 = Color.WHITE
	}

	@Test
	void "default title"() {
		runFieldFixture new General(), DEFAULT_TITLE, factory, {
			assert fixture.label("$TITLE_LABEL-$DEFAULT_TITLE").text() == DEFAULT_TITLE
		}
	}

	@Test
	void "custom title"() {
		runFieldFixture new General(), CUSTOM_TITLE, factory, {
			assert fixture.label("$TITLE_LABEL-$CUSTOM_TITLE").text() == CUSTOM_TITLE
		}
	}

	@Test
	void "no title"() {
		runFieldFixture new General(), HIDE_TITLE, factory, {
			//assert fixture.label("$TITLE_LABEL-file3").text() == "file3"
		}
	}

	@Test
	void testManual() {
		//Thread.sleep(30000)
	}
}
