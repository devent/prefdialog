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
package com.anrisoftware.prefdialog.panel.inputfields.textfield

import static com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel.TITLE_LABEL

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.TextField
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.TextFieldHandlerFactory

class TextFieldTitleTest extends FieldFixtureHandler {

	static factory = injector.getInstance(TextFieldHandlerFactory)

	static final String DEFAULT_TITLE = "defaultTitle"

	static final String CUSTOM_TITLE = "customTitle"

	static final String HIDE_TITLE = "hideTitle"

	static class General {

		@TextField
		String defaultTitle = ""

		@TextField(title="Project name")
		String customTitle = ""

		@TextField(showTitle=false)
		String hideTitle = ""
	}

	@Test
	void "default title"() {
		createFieldFixture(new General(), DEFAULT_TITLE, factory)
		beginFixture()
		fixture.textBox(DEFAULT_TITLE).requireVisible()
		assert fixture.label("$TITLE_LABEL-$DEFAULT_TITLE").text() == DEFAULT_TITLE
		endFixture()
	}

	@Test
	void "custom title"() {
		createFieldFixture(new General(), CUSTOM_TITLE, factory)
		beginFixture()
		fixture.textBox(CUSTOM_TITLE).requireVisible()
		assert fixture.label("$TITLE_LABEL-$CUSTOM_TITLE").text() == "Project name"
		endFixture()
	}

	@Test
	void "hide title"() {
		createFieldFixture(new General(), HIDE_TITLE, factory)
		beginFixture()
		fixture.textBox(HIDE_TITLE).requireVisible()
		//assert fixture.label("$TITLE_LABEL-$CUSTOM_TITLE").text() == HIDE_TITLE
		endFixture()
	}
}
