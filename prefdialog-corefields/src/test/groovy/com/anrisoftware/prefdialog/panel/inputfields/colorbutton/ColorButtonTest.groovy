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

import static com.anrisoftware.prefdialog.panel.inputfields.colorbutton.ColorButtonPanel.COLOR_BUTTON
import static com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel.TITLE_LABEL

import java.awt.Color

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.ColorButton
import com.anrisoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.anrisoftware.prefdialog.panel.inputfields.api.ColorButtonFieldHandlerFactory

/**
 * Test the behavior the color button.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class ColorButtonTest extends AbstractFieldFixture {

	static factory = injector.getInstance(ColorButtonFieldHandlerFactory)

	static final String COLOR = "color"

	static class General {

		@ColorButton
		Color color = Color.WHITE
	}

	ColorButtonTest() {
		super(new General(), COLOR, factory)
	}

	@Test
	void "names and titles"() {
		fixture.label("$TITLE_LABEL-$COLOR").requireVisible()
		assert fixture.label("$TITLE_LABEL-$COLOR").text() == COLOR
		fixture.button("$COLOR_BUTTON-$COLOR").requireVisible()
		assert fixture.button("$COLOR_BUTTON-$COLOR").text() == "#ffffff"
	}

	@Test
	void "manually"() {
		Thread.sleep 0
	}
}
