/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-panel.
 * 
 * prefdialog-panel is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-panel is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-panel. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.TextField

class ChildTitleTest extends PanelFixtureHandler {

	static final String DEFAULT_TITLE = "defaultTitle"

	static final String CUSTOM_TITLE = "customTitle"

	static class DefaultTitle {

		@TextField
		String text = ""

		@Override
		public String toString() {
			DEFAULT_TITLE
		}
	}

	static class CustomTitle {

		@TextField
		String text = ""

		@Override
		public String toString() {
			CUSTOM_TITLE
		}
	}

	static class Preferences {

		@Child
		DefaultTitle defaultTitle = new DefaultTitle()

		@Child(title="custom")
		CustomTitle customTitle = new CustomTitle()
	}

	@Test
	void "default child title"() {
		createFieldFixture(new Preferences(), DEFAULT_TITLE)
		beginFixture()
		assert fixture.label("label-$DEFAULT_TITLE").text() == DEFAULT_TITLE
		endFixture()
	}

	@Test
	void "custom child title"() {
		createFieldFixture(new Preferences(), CUSTOM_TITLE)
		beginFixture()
		assert fixture.label("label-$CUSTOM_TITLE").text() == "custom"
		endFixture()
	}

	@Test
	void "manually"() {
		createFieldFixture(new Preferences(), CUSTOM_TITLE)
		beginFixture()
		Thread.sleep 60000
		endFixture()
	}
}
