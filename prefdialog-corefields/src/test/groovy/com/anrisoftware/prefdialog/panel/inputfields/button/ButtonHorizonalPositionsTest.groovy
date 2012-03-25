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
package com.anrisoftware.prefdialog.panel.inputfields.button

import static com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel.TITLE_LABEL

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.ButtonGroup
import com.anrisoftware.prefdialog.annotations.HorizontalPositions
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.ButtonGroupFieldHandlerFactory


class ButtonHorizonalPositionsTest extends FieldFixtureHandler {

	static factory = injector.getInstance(ButtonGroupFieldHandlerFactory)

	static final String DEFAULT_POSITION = "defaultPosition"

	static final String POSITION_LEFT = "positionLeft"

	static final String POSITION_RIGHT = "positionRight"

	static final String POSITION_MIDDLE = "positionMiddle"

	static class General {

		@ButtonGroup
		def defaultPosition = [
			new Button1Action(),
			new Button2Action()
		]

		@ButtonGroup(horizontalPosition=HorizontalPositions.LEFT)
		def positionLeft = [
			new Button1Action(),
			new Button2Action()
		]

		@ButtonGroup(horizontalPosition=HorizontalPositions.RIGHT)
		def positionRight = [
			new Button1Action(),
			new Button2Action()
		]

		@ButtonGroup(horizontalPosition=HorizontalPositions.MIDDLE)
		def positionMiddle = [
			new Button1Action(),
			new Button2Action()
		]
	}

	@Test
	void "default position"() {
		runFieldFixture new General(), DEFAULT_POSITION, factory, {
			fixture.button("button-0-$DEFAULT_POSITION").click()
			fixture.button("button-1-$DEFAULT_POSITION").click()
		}
	}

	@Test
	void "position left"() {
		runFieldFixture new General(), POSITION_LEFT, factory, {
			fixture.button("button-0-$POSITION_LEFT").click()
			fixture.button("button-1-$POSITION_LEFT").click()
		}
	}

	@Test
	void "position right"() {
		runFieldFixture new General(), POSITION_RIGHT, factory, {
			fixture.button("button-0-$POSITION_RIGHT").click()
			fixture.button("button-1-$POSITION_RIGHT").click()
		}
	}

	@Test
	void "position middle"() {
		runFieldFixture new General(), POSITION_MIDDLE, factory, {
			fixture.button("button-0-$POSITION_MIDDLE").click()
			fixture.button("button-1-$POSITION_MIDDLE").click()
		}
	}

	@Test
	void testManual() {
		runFieldFixture new General(), DEFAULT_POSITION, factory, {
			//
			//Thread.sleep 60000 //
		}
	}
}
