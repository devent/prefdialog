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
import com.anrisoftware.prefdialog.annotations.HorizontalPositions
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.ColorButtonFieldHandlerFactory

/**
 * Test the horizontal positions of the color button.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class ColorButtonHorizontalPositionTest extends FieldFixtureHandler {

	static factory = injector.getInstance(ColorButtonFieldHandlerFactory)

	static final String COLOR_DEFAULT = "colorDefault"

	static final String COLOR_LEFT = "colorLeft"

	static final String COLOR_RIGHT = "colorRight"

	static final String COLOR_MIDDLE = "colorMiddle"

	static class General {

		@ColorButton
		Color colorDefault = Color.WHITE

		@ColorButton(horizontalPosition=HorizontalPositions.LEFT)
		Color colorLeft = Color.WHITE

		@ColorButton(horizontalPosition=HorizontalPositions.RIGHT)
		Color colorRight = Color.WHITE

		@ColorButton(horizontalPosition=HorizontalPositions.MIDDLE)
		Color colorMiddle = Color.WHITE
	}

	@Test
	void "default horizontal position"() {
		createFieldFixture(new General(), COLOR_DEFAULT, factory)
		beginFixture()
		assert fixture.label("$TITLE_LABEL-$COLOR_DEFAULT").text() == COLOR_DEFAULT
		endFixture()
	}

	@Test
	void "left horizontal position"() {
		createFieldFixture(new General(), COLOR_LEFT, factory)
		beginFixture()
		assert fixture.label("$TITLE_LABEL-$COLOR_LEFT").text() == COLOR_LEFT
		endFixture()
	}

	@Test
	void "right horizontal position"() {
		createFieldFixture(new General(), COLOR_RIGHT, factory)
		beginFixture()
		assert fixture.label("$TITLE_LABEL-$COLOR_RIGHT").text() == COLOR_RIGHT
		endFixture()
	}

	@Test
	void "middle horizontal position"() {
		createFieldFixture(new General(), COLOR_MIDDLE, factory)
		beginFixture()
		assert fixture.label("$TITLE_LABEL-$COLOR_MIDDLE").text() == COLOR_MIDDLE
		endFixture()
	}

	@Test
	void testManual() {
		createFieldFixture(new General(), COLOR_MIDDLE, factory)
		beginFixture()
		//Thread.sleep(30000)
		endFixture()
	}
}
