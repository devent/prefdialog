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
package com.anrisoftware.prefdialog.panel.inputfields.radiobutton

import static com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel.TITLE_LABEL

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.RadioButton
import com.anrisoftware.prefdialog.panel.inputfields.Colors
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.RadioButtonFieldHandlerFactory

class RadioButtonTitleTest extends FieldFixtureHandler {

	static factory = injector.getInstance(RadioButtonFieldHandlerFactory)

	static final String DEFAULT_TITLE = "defaultTitle"

	static final String CUSTOM_TITLE = "customTitle"

	static final String HIDE_TITLE = "hideTitle"

	static class General {

		@RadioButton
		Colors defaultTitle = Colors.BLACK

		@RadioButton(title="Some colors")
		Colors customTitle = Colors.BLACK

		@RadioButton(showTitle=false)
		Colors hideTitle = Colors.BLACK
	}

	@Test
	void "default title"() {
		createFieldFixture(new General(), DEFAULT_TITLE, factory)
		beginFixture()
		assert fixture.label("$TITLE_LABEL-$DEFAULT_TITLE").text() == DEFAULT_TITLE
		endFixture()
	}

	@Test
	void "custom title"() {
		createFieldFixture(new General(), CUSTOM_TITLE, factory)
		beginFixture()
		assert fixture.label("$TITLE_LABEL-$CUSTOM_TITLE").text() == "Some colors"
		endFixture()
	}

	@Test
	void "no title"() {
		createFieldFixture(new General(), HIDE_TITLE, factory)
		beginFixture()
		//assert fixture.label("$TITLE_LABEL-$HIDE_TITLE").text() == "colors3"
		endFixture()
	}
}
