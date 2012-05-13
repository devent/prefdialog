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

import java.awt.Font;

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.FontChooser;
import com.anrisoftware.prefdialog.annotations.TextField

class ChildFontChooserTest extends PanelFixtureHandler {

	static final String DEFAULT_TITLE = "defaultTitle"

	static final String CUSTOM_TITLE = "customTitle"

	static final String HIDE_TITLE = "hideTitle"

	static class DefaultTitle {

		@TextField
		String text = ""

		@FontChooser
		Font font = Font.decode(null)

		@Override
		public String toString() {
			DEFAULT_TITLE
		}
	}

	static class Preferences {

		@Child
		DefaultTitle defaultTitle = new DefaultTitle()
	}

	@Test
	void "default child title"() {
		createFieldFixture(new Preferences(), DEFAULT_TITLE)
		beginFixture()
		assert fixture.label("$TITLE_LABEL-$DEFAULT_TITLE").text() == DEFAULT_TITLE
		endFixture()
	}

	@Test
	void "manually"() {
		createFieldFixture(new Preferences(), DEFAULT_TITLE)
		beginFixture()
		Thread.sleep 0 // 60000
		endFixture()
	}
}
