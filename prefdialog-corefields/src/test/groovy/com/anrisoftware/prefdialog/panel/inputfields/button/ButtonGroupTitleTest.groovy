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
package com.anrisoftware.prefdialog.panel.inputfields.button

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.ButtonGroup
import com.anrisoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.anrisoftware.prefdialog.panel.inputfields.api.ButtonGroupFieldHandlerFactory

class ButtonGroupTitleTest extends AbstractFieldFixture {

	static factory = injector.getInstance(ButtonGroupFieldHandlerFactory)

	static class General {

		@ButtonGroup(title='The title', showTitle=true)
		def buttons = [
			new Button1Action(),
			new Button2Action()
		]
	}

	ButtonGroupTitleTest() {
		super(new General(), 'buttons', factory)
	}

	@Test
	void "title set and visible"() {
		fixture.label("label-buttons").requireVisible()
		fixture.label("label-buttons").text() == 'The title'
	}

	@Test
	void testManually() {
		Thread.sleep 0
	}
}