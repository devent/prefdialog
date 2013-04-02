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

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.annotations.TextPosition.*
import static com.anrisoftware.prefdialog.core.AbstractTitleField.*
import static com.anrisoftware.prefdialog.core.Preferences.*
import static com.anrisoftware.resources.images.api.IconSize.*

import java.awt.Component

import javax.swing.Icon

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.resources.images.api.IconSize
import com.anrisoftware.resources.images.api.Images
import com.anrisoftware.resources.texts.api.Texts
import com.google.inject.Injector

/**
 * Test the title field.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TitleFieldTest extends FieldTestUtils {

	@Test
	void "show text field"() {
		def title = "TitleFieldTest :: show text field"
		def preferenceField = preferencesTextField
		def containerName = "${preferenceField}-$CONTAINER_NAME"
		def titleName = "${preferenceField}-$TITLE_NAME"
		def field = factory.create(component, container, preferences, preferenceField)

		assertField field,
		name: containerName,
		title: preferenceField
		new TestFrameUtil(title, container).withFixture({FrameFixture fixture ->
			assertEnabled fixture, preferenceField, containerName, titleName
			assertNames fixture, preferenceField, containerName, titleName
			fixture.label titleName requireText("textField:")
		})
	}

	@Test
	void "with title disabled"() {
		def title = "TitleFieldTest :: with title disabled"
		def preferenceField = preferencesTextFieldWithTitleDisabled
		def containerName = "${preferenceField}-$CONTAINER_NAME"
		def titleName = "${preferenceField}-$TITLE_NAME"
		def field = factory.create(component, container, preferences, preferenceField)

		assertField field,
		name: containerName,
		title: preferenceField,
		showTitle: false
		new TestFrameUtil(title, container).withFixture({FrameFixture fixture ->
			assertEnabled fixture, preferenceField, containerName, titleName
			assertNames fixture, preferenceField, containerName, titleName
			fixture.label titleName requireText(null)
		})
	}

	@Test
	void "with title"() {
		def title = "TitleFieldTest :: with title"
		def preferenceField = preferencesTextFieldWithTitle
		def containerName = "${preferenceField}-$CONTAINER_NAME"
		def titleName = "${preferenceField}-$TITLE_NAME"
		def field = factory.create(component, container, preferences, preferenceField)

		assertField field,
		name: containerName,
		title: "Test Field"
		new TestFrameUtil(title, container).withFixture({FrameFixture fixture ->
			assertEnabled fixture, preferenceField, containerName, titleName
			assertNames fixture, preferenceField, containerName, titleName
			fixture.label titleName requireText("Test Field:")
		})
	}

	@Test
	void "with title resource"() {
		def title = "TitleFieldTest :: with title resource"
		def preferenceField = preferencesTextFieldWithTitleResource
		def containerName = "${preferenceField}-$CONTAINER_NAME"
		def titleName = "${preferenceField}-$TITLE_NAME"
		def field = factory.create(component, container, preferences, preferenceField)
		field.setTexts texts

		assertField field,
		name: containerName,
		title: "Test Field Eng"
		new TestFrameUtil(title, container).withFixture({FrameFixture fixture ->
			assertEnabled fixture, preferenceField, containerName, titleName
			assertNames fixture, preferenceField, containerName, titleName
			fixture.label titleName requireText("Test Field Eng:")
		})
	}

	@Test
	void "with title resource change locale"() {
		def title = "TitleFieldTest :: with title resource change locale"
		def preferenceField = preferencesTextFieldWithTitleResource
		def containerName = "${preferenceField}-$CONTAINER_NAME"
		def titleName = "${preferenceField}-$TITLE_NAME"
		def field = factory.create(component, container, preferences, preferenceField)
		field.setTexts texts

		assertField field,
		name: containerName,
		title: "Test Field Eng"
		new TestFrameUtil(title, container).withFixture({FrameFixture fixture ->
			assertEnabled fixture, preferenceField, containerName, titleName
			assertNames fixture, preferenceField, containerName, titleName
			fixture.label titleName requireText("Test Field Eng:")
		}, { FrameFixture fixture ->
			field.setLocale Locale.GERMAN
			fixture.label titleName requireText("Test Field Deu:")
		})
	}

	@Test
	void "with title missing resource"() {
		def title = "TitleFieldTest :: with title missing resource"
		def preferenceField = preferencesTextFieldWithTitleMissingResource
		def containerName = "${preferenceField}-$CONTAINER_NAME"
		def titleName = "${preferenceField}-$TITLE_NAME"
		def field = factory.create(component, container, preferences, preferenceField)

		shouldFailWith(MissingResourceException) { field.setTexts texts }
		assertField field,
		name: containerName,
		title: "missing_test_field"
	}

	@Test
	void "read-only"() {
		def title = "TitleFieldTest :: read-only"
		def preferenceField = preferencesTextFieldReadOnly
		def containerName = "${preferenceField}-$CONTAINER_NAME"
		def titleName = "${preferenceField}-$TITLE_NAME"
		def field = factory.create(component, container, preferences, preferenceField)

		assertField field,
		name: containerName,
		title: preferenceField,
		readOnly: true
		new TestFrameUtil(title, container).withFixture({FrameFixture fixture ->
			assertDisabled fixture, preferenceField, containerName, titleName
			assertNames fixture, preferenceField, containerName, titleName
		})
	}

	@Test
	void "with icon resource"() {
		def title = "TitleFieldTest :: with icon resource"
		def preferenceField = preferencesTextFieldWithIconResource
		def containerName = "${preferenceField}-$CONTAINER_NAME"
		def titleName = "${preferenceField}-$TITLE_NAME"
		def field = factory.create(component, container, preferences, preferenceField)
		field.setImages images

		assertField field,
		name: containerName,
		title: preferenceField,
		icon: { Icon icon -> assert icon.iconWidth == 16 },
		iconSize: SMALL
		new TestFrameUtil(title, container).withFixture({FrameFixture fixture ->
			assertEnabled fixture, preferenceField, containerName, titleName
			assertNames fixture, preferenceField, containerName, titleName
		})
	}

	@Test
	void "with icon resource change icon size"() {
		def title = "TitleFieldTest :: with icon resource change icon size"
		def preferenceField = preferencesTextFieldWithIconResource
		def containerName = "${preferenceField}-$CONTAINER_NAME"
		def titleName = "${preferenceField}-$TITLE_NAME"
		def field = factory.create(component, container, preferences, preferenceField)
		field.setImages images

		assertField field,
		name: containerName,
		title: preferenceField,
		icon: { Icon icon -> assert icon.iconWidth == 16 },
		iconSize: SMALL
		new TestFrameUtil(title, container).withFixture({FrameFixture fixture ->
			assertEnabled fixture, preferenceField, containerName, titleName
			assertNames fixture, preferenceField, containerName, titleName
		}, { field.setIconSize HUGE }, { field.setIconSize LARGE }, { field.setIconSize MEDIUM }, { field.setIconSize SMALL })
	}

	@Test
	void "with icon resource change locale"() {
		def title = "TitleFieldTest :: with icon resource change locale"
		def preferenceField = preferencesTextFieldWithIconResource
		def containerName = "${preferenceField}-$CONTAINER_NAME"
		def titleName = "${preferenceField}-$TITLE_NAME"
		def field = factory.create(component, container, preferences, preferenceField)
		field.setImages images

		assertField field,
		name: containerName,
		title: preferenceField,
		icon: { Icon icon -> assert icon.iconWidth == 16 },
		iconSize: SMALL
		new TestFrameUtil(title, container).withFixture({FrameFixture fixture ->
			assertEnabled fixture, preferenceField, containerName, titleName
			assertNames fixture, preferenceField, containerName, titleName
		}, { field.setLocale Locale.GERMAN })
	}

	static Injector injector

	static MockTitleFieldFactory factory

	static Texts texts

	static Images images

	Preferences preferences

	Component component

	Component container

	@BeforeClass
	static void setupFactories() {
		injector = createInjector()
		factory = injector.getInstance MockTitleFieldFactory
		texts = createTextsResource(injector)
		images = createImagesResource(injector)
	}

	@Before
	void setupPreferences() {
		preferences = new Preferences()
		component = createLabel()
		container = createContainer()
	}

	void assertEnabled(FrameFixture fixture, def preferenceField, def containerName, def titleName) {
		fixture.label preferenceField requireEnabled()
		fixture.panel containerName requireEnabled()
		fixture.label titleName requireEnabled()
	}

	void assertDisabled(FrameFixture fixture, def preferenceField, def containerName, def titleName) {
		fixture.label preferenceField requireDisabled()
		fixture.panel containerName requireDisabled()
		fixture.label titleName requireDisabled()
	}

	void assertNames(FrameFixture fixture, def preferenceField, def containerName, def titleName) {
		assert fixture.label(preferenceField).target.getName() == preferenceField
		assert fixture.panel(containerName).target.getName() == containerName
		assert fixture.label(titleName).target.getName() == titleName
	}
}
