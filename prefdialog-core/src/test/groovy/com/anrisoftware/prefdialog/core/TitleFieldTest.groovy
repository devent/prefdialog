/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-core.
 *
 * prefdialog-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.core

import static com.anrisoftware.prefdialog.core.AbstractTitleField.*
import static com.anrisoftware.prefdialog.core.Preferences.*

import javax.swing.JPanel
import javax.swing.JTextField

import org.junit.Test

import com.anrisoftware.resources.images.api.IconSize

/**
 * Test the {@link AbstractTitleField}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TitleFieldTest extends FieldTestUtils {

	def title = "Title Field Test"

	@Test
	void "show text field"() {
		def container = new JPanel()
		def component = new JTextField()
		def preferenceField = preferencesTextField
		def field = titleFieldFactory.create(component, container, preferences, preferenceField).createField()
		beginPanelFrame title, container, {
			fixture.panel("${preferenceField.name}-$CONTAINER_NAME").requireEnabled()
			fixture.textBox(preferenceField.name).requireEnabled()
			fixture.label("${preferenceField.name}-$TITLE_NAME").requireText("textField:")
		}
	}

	@Test
	void "show text field with title disabled"() {
		def container = new JPanel()
		def component = new JTextField()
		def preferenceField = preferencesTextFieldWithTitleDisabled
		def field = titleFieldFactory.create(component, container, preferences, preferenceField).createField()
		beginPanelFrame title, container, {
			fixture.label("${preferenceField.name}-$TITLE_NAME").requireText(null)
		}
	}

	@Test
	void "show text field with title"() {
		def container = new JPanel()
		def component = new JTextField()
		def preferenceField = preferencesTextFieldWithTitle
		def titleLabelName = "${preferenceField.name}-$TITLE_NAME"
		def field = titleFieldFactory.create(component, container, preferences, preferenceField).createField()
		beginPanelFrame title, container, {
			fixture.label(titleLabelName).requireText("Test Field:")
		}
	}

	@Test
	void "show text field with title resource"() {
		def texts = createTextsResource()
		def container = new JPanel()
		def component = new JTextField()
		def preferenceField = preferencesTextFieldWithTitleResource
		def titleLabelName = "${preferenceField.name}-$TITLE_NAME"
		def field = titleFieldFactory.create(
						component, container, preferences, preferenceField).createField().
						withTextsResource(texts)
		beginPanelFrame title, container, {
			fixture.label(titleLabelName).requireText("Test Field Eng:")
		}
	}

	@Test
	void "show text field with title resource change locale"() {
		def texts = createTextsResource()
		def container = new JPanel()
		def component = new JTextField()
		def preferenceField = preferencesTextFieldWithTitleResource
		def titleLabelName = "${preferenceField.name}-$TITLE_NAME"
		def field = titleFieldFactory.create(
						component, container, preferences, preferenceField).createField().
						withTextsResource(texts)
		beginPanelFrame title, container, {
			fixture.label(titleLabelName).requireText("Test Field Eng:")
		}, {
			field.locale = Locale.GERMAN
			fixture.label(titleLabelName).requireText("Test Field Deu:")
		}
	}

	@Test
	void "show text field with title missing resource"() {
		def texts = createTextsResource()
		def container = new JPanel()
		def component = new JTextField()
		def preferenceField = preferencesTextFieldWithTitleMissingResource
		def titleLabelName = "${preferenceField.name}-$TITLE_NAME"
		def field = titleFieldFactory.create(
						component, container, preferences, preferenceField).createField().
						withTextsResource(texts)
		beginPanelFrame title, container, {
			fixture.label(titleLabelName).requireText("missing_test_field:")
		}
	}

	@Test
	void "show text field read-only"() {
		def container = new JPanel()
		def component = new JTextField()
		def preferenceField = preferencesTextFieldReadOnly
		def titleLabelName = "${preferenceField.name}-$TITLE_NAME"
		def field = titleFieldFactory.create(component, container, preferences, preferenceField).createField()
		beginPanelFrame title, container, {
			fixture.label(titleLabelName).requireDisabled()
		}
	}

	@Test
	void "show text field with icon resource"() {
		def images = createImagesResource()
		def container = new JPanel()
		def component = new JTextField()
		def preferenceField = preferencesTextFieldWithIconResource
		def titleLabelName = "${preferenceField.name}-$TITLE_NAME"
		def field = titleFieldFactory.create(
						component, container, preferences, preferenceField).createField().
						withImagesResource(images)
		beginPanelFrame title, container, {
			fixture.label(titleLabelName).requireEnabled()
		}
	}

	@Test
	void "show text field with icon resource change icon size"() {
		def images = createImagesResource()
		def container = new JPanel()
		def component = new JTextField()
		def preferenceField = preferencesTextFieldWithIconResource
		def titleLabelName = "${preferenceField.name}-$TITLE_NAME"
		def field = titleFieldFactory.create(
						component, container, preferences, preferenceField).createField().
						withImagesResource(images)
		beginPanelFrame title, container, {
			fixture.label(titleLabelName).requireEnabled()
		}, {
			field.iconSize = IconSize.HUGE
		}, {
			field.iconSize = IconSize.LARGE
		}, {
			field.iconSize = IconSize.MEDIUM
		}, {
			field.iconSize = IconSize.SMALL
		}
	}

	@Test
	void "show text field with icon resource change locale"() {
		def images = createImagesResource()
		def container = new JPanel()
		def component = new JTextField()
		def preferenceField = preferencesTextFieldWithIconResource
		def titleLabelName = "${preferenceField.name}-$TITLE_NAME"
		def field = titleFieldFactory.create(
						component, container, preferences, preferenceField).createField().
						withImagesResource(images)
		beginPanelFrame title, container, {
			fixture.label(titleLabelName).requireEnabled()
		}, {
			field.locale = Locale.GERMAN
		}
	}
}
