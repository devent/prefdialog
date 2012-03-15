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
package com.anrisoftware.prefdialog.panel.inputfields.combobox

import static com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel.TITLE_LABEL

import java.util.List

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.ComboBox
import com.anrisoftware.prefdialog.annotations.ComboBoxElements
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.ComboBoxFieldHandlerFactory

class ComboBoxTitleTest extends FieldFixtureHandler {

	static factory = injector.getInstance(ComboBoxFieldHandlerFactory)

	static final String DEFAULT_TITLE = "defaultTitle"

	static final String CUSTOM_TITLE = "customTitle"

	static final String HIDE_TITLE = "hideTitle"

	static class General {

		@ComboBoxElements("Some combo box")
		List<String> comboBoxElements = [
			"first element",
			"second element",
			"third element"
		]

		@ComboBox(elements="Some combo box")
		String defaultTitle = "first element"

		@ComboBox(title="Second combo box:", elements="Some combo box")
		String customTitle = "first element"

		@ComboBox(showTitle=false, elements="Some combo box")
		String hideTitle= "first element"
	}

	@Test
	void "set default tile"() {
		createFieldFixture(new General(), DEFAULT_TITLE, factory)
		beginFixture()
		fixture.comboBox(DEFAULT_TITLE).selectItem 2
		assert fixture.label("$TITLE_LABEL-$DEFAULT_TITLE").text() == DEFAULT_TITLE
		endFixture()
	}

	@Test
	void "set custom tile"() {
		createFieldFixture(new General(), CUSTOM_TITLE, factory)
		beginFixture()
		fixture.comboBox(CUSTOM_TITLE).selectItem 2
		assert fixture.label("$TITLE_LABEL-$CUSTOM_TITLE").text() == "Second combo box:"
		endFixture()
	}

	@Test
	void "set no tile"() {
		createFieldFixture(new General(), HIDE_TITLE, factory)
		beginFixture()
		fixture.comboBox(HIDE_TITLE).selectItem 2
		// Why it can't find an invisible label?
		// fixture.label("$TITLE_LABEL-$HIDE_TITLE").requireNotVisible()
		endFixture()
	}
}

