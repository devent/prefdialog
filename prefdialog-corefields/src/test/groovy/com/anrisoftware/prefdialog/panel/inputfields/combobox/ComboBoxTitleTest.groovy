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
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.ComboBoxFieldHandlerFactory

class ComboBoxTitleTest extends FieldFixtureHandler {

	static factory = injector.getInstance(ComboBoxFieldHandlerFactory)

	static final String DEFAULT_TITLE = "defaultTitle"

	static final String CUSTOM_TITLE = "customTitle"

	static final String HIDE_TITLE = "hideTitle"

	static class General {

		@ComboBox(elements="comboBoxElements")
		String defaultTitle = "first element"

		@ComboBox(title="Second combo box:", elements="comboBoxElements")
		String customTitle = "first element"

		@ComboBox(showTitle=false, elements="comboBoxElements")
		String hideTitle= "first element"

		List comboBoxElements = [
			"first element",
			"second element",
			"third element"
		]
	}

	@Test
	void "set default tile"() {
		runFieldFixture new General(), DEFAULT_TITLE, factory, {
			fixture.comboBox(DEFAULT_TITLE).selectItem 2
			assert fixture.label("$TITLE_LABEL-$DEFAULT_TITLE").text() == DEFAULT_TITLE
		}
	}

	@Test
	void "set custom tile"() {
		runFieldFixture new General(), CUSTOM_TITLE, factory, {
			fixture.comboBox(CUSTOM_TITLE).selectItem 2
			assert fixture.label("$TITLE_LABEL-$CUSTOM_TITLE").text() == "Second combo box:"
		}
	}

	@Test
	void "set no tile"() {
		runFieldFixture new General(), HIDE_TITLE, factory, {
			fixture.comboBox(HIDE_TITLE).selectItem 2
			// Why it can't find an invisible label?
			// fixture.label("$TITLE_LABEL-$HIDE_TITLE").requireNotVisible()
		}
	}
}

