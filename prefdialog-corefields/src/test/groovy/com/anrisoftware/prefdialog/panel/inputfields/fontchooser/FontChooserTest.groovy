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
package com.anrisoftware.prefdialog.panel.inputfields.fontchooser

import static com.anrisoftware.prefdialog.panel.inputfields.fontchooser.FontChooserPanel.FONTBOX
import static com.anrisoftware.prefdialog.panel.inputfields.fontchooser.FontChooserPanel.OPEN_FONT_BUTTON
import static com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel.TITLE_LABEL

import java.awt.Font

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.FontChooser
import com.anrisoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.anrisoftware.prefdialog.panel.inputfields.api.FontChooserFieldHandlerFactory

/**
 * Tests the basic behavior of the font chooser field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class FontChooserTest extends AbstractFieldFixture {

	static factory = injector.getInstance(FontChooserFieldHandlerFactory)

	static final String FONT = "font"

	static class General {

		@FontChooser
		Font font = Font.decode(null)
	}

	FontChooserTest() {
		super(new General(), FONT, factory)
	}

	@Test
	void "titels"() {
		fixture.label("$TITLE_LABEL-$FONT").requireVisible()
		assert fixture.label("$TITLE_LABEL-$FONT").text() == FONT
		fixture.comboBox("$FONTBOX-$FONT").requireVisible()
		fixture.button("$OPEN_FONT_BUTTON-$FONT").requireVisible()
	}

	@Test
	void "manually"() {
		Thread.sleep 30000
	}
}
