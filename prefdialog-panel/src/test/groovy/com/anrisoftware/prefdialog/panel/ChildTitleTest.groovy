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

import static com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel.TITLE_LABEL

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.TextField

class ChildTitleTest extends PanelFixtureHandler {

	static final String DEFAULT_TITLE = "defaultTitle"

	static final String CUSTOM_TITLE = "customTitle"

	static final String HIDE_TITLE = "hideTitle"

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

	static class HideTitle {

		@TextField
		String text = ""

		@Override
		public String toString() {
			HIDE_TITLE
		}
	}

	static class Preferences {

		@Child
		DefaultTitle defaultTitle = new DefaultTitle()

		@Child(title="custom")
		CustomTitle customTitle = new CustomTitle()

		@Child(showTitle=false)
		HideTitle hideTitle = new HideTitle()
	}

	@Test
	void "default child title"() {
		createFieldFixture(new Preferences(), DEFAULT_TITLE)
		beginFixture()
		assert fixture.label("$TITLE_LABEL-$DEFAULT_TITLE").text() == DEFAULT_TITLE
		endFixture()
	}

	@Test
	void "custom child title"() {
		createFieldFixture(new Preferences(), CUSTOM_TITLE)
		beginFixture()
		assert fixture.label("$TITLE_LABEL-$CUSTOM_TITLE").text() == "custom"
		endFixture()
	}

	@Test
	void "hide child title"() {
		createFieldFixture(new Preferences(), HIDE_TITLE)
		beginFixture()
		// Thread.sleep 1000
		endFixture()
	}

	@Test
	void "manually"() {
		createFieldFixture(new Preferences(), HIDE_TITLE)
		beginFixture()
		// Thread.sleep 60000
		endFixture()
	}
}
