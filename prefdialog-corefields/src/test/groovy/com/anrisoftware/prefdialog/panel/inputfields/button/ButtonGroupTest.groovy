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


import static com.anrisoftware.prefdialog.panel.inputfields.button.ButtonGroupPanel.BUTTON

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.ButtonGroup
import com.anrisoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.anrisoftware.prefdialog.panel.inputfields.api.ButtonGroupFieldHandlerFactory

class ButtonGroupTest extends AbstractFieldFixture {

	static factory = injector.getInstance(ButtonGroupFieldHandlerFactory)

	static final String BUTTONS = "buttons"

	static class General {

		@ButtonGroup
		def buttons = [
			new Button1Action(),
			new Button2Action()
		]
	}

	ButtonGroupTest() {
		super(new General(), BUTTONS, factory)
	}

	@Test
	void "click on buttons"() {
		fixture.button("$BUTTON-0-$BUTTONS").click()
		fixture.button("$BUTTON-1-$BUTTONS").click()
	}

	@Test
	void testManually() {
		Thread.sleep 0
	}
}
