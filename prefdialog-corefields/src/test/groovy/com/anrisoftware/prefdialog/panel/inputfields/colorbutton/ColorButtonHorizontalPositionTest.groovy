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
		createFieldFixture(new General(), 'colorDefault', factory)
		beginFixture()
		assert fixture.label('label-colorDefault').text() == 'colorDefault'
		endFixture()
	}

	@Test
	void "left horizontal position"() {
		createFieldFixture(new General(), 'colorLeft', factory)
		beginFixture()
		assert fixture.label('label-colorLeft').text() == 'colorLeft'
		endFixture()
	}

	@Test
	void "right horizontal position"() {
		createFieldFixture(new General(), 'colorRight', factory)
		beginFixture()
		assert fixture.label('label-colorRight').text() == 'colorRight'
		endFixture()
	}

	@Test
	void "middle horizontal position"() {
		createFieldFixture(new General(), 'colorMiddle', factory)
		beginFixture()
		assert fixture.label('label-colorMiddle').text() == 'colorMiddle'
		endFixture()
	}

	@Test
	void testManual() {
		createFieldFixture(new General(), 'colorMiddle', factory)
		beginFixture()
		//Thread.sleep(30000)
		endFixture()
	}
}
