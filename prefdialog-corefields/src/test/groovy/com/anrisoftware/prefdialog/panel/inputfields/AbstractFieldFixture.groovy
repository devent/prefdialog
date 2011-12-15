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
package com.anrisoftware.prefdialog.panel.inputfields


import javax.swing.JFrame

import org.fest.swing.fixture.FrameFixture
import org.junit.After
import org.junit.Before

abstract class AbstractFieldFixture extends FieldFixtureHandler {

	AbstractFieldFixture(def parentObject, def fieldName, def fieldFactory) {
		createFieldFixture(parentObject, fieldName, fieldFactory)
	}

	@Before
	void beforeTest() {
		beginFixture()
	}

	@After
	void afterTest() {
		endFixture()
	}
}
