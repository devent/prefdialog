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

import static com.anrisoftware.prefdialog.annotations.TextPosition.*
import static com.anrisoftware.prefdialog.core.AbstractContainerField.*
import static com.anrisoftware.prefdialog.core.Preferences.*
import static com.anrisoftware.resources.images.api.IconSize.*

import java.awt.Component

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.resources.images.api.Images
import com.anrisoftware.resources.texts.api.Texts
import com.google.inject.Injector

/**
 * Test the container field.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ContainerFieldTest extends FieldTestUtils {

	@Test
	void "show text field"() {
		def title = "ContainerFieldTest :: show text field"
		def preferenceField = preferencesTextField
		def containerName = "${preferenceField}-$CONTAINER_NAME"
		def name = preferenceField
		def field = factory.create(component, preferences, preferenceField)
		field.setContainer container

		assertField field,
		name: name,
		title: preferenceField
		new TestFrameUtil(title, field.AWTComponent).withFixture({FrameFixture fixture ->
			fixture.label preferenceField requireEnabled()
			fixture.panel containerName requireEnabled()
			assert fixture.label(preferenceField).target.getName() == preferenceField
			assert fixture.panel(containerName).target.getName() == containerName
		})
	}

	@Test
	void "fixed width"() {
		def title = "ContainerFieldTest :: fixed width"
		def preferenceField = preferencesTextFieldFixedWidth
		def containerName = "${preferenceField}-$CONTAINER_NAME"
		def name = preferenceField
		def field = factory.create(component, preferences, preferenceField)

		assertField field,
		name: name,
		title: preferenceField,
		width: 200.0d
		new TestFrameUtil(title, field.AWTComponent).withFixture({FrameFixture fixture ->
			fixture.label preferenceField requireEnabled()
			fixture.panel containerName requireEnabled()
			assert fixture.label(preferenceField).target.getSize().width == 200
			assert fixture.label(preferenceField).target.getName() == preferenceField
			assert fixture.panel(containerName).target.getName() == containerName
		})
	}

	@Test
	void "fill width"() {
		def title = "ContainerFieldTest :: fill width"
		def preferenceField = preferencesTextFieldFillWidth
		def containerName = "${preferenceField}-$CONTAINER_NAME"
		def name = preferenceField
		def field = factory.create(component, preferences, preferenceField)

		assertField field,
		name: name,
		title: preferenceField,
		width: -1.0d
		new TestFrameUtil(title, field.AWTComponent).withFixture({FrameFixture fixture ->
			fixture.label preferenceField requireEnabled()
			fixture.panel containerName requireEnabled()
			assert fixture.label(preferenceField).target.getName() == preferenceField
			assert fixture.panel(containerName).target.getName() == containerName
		})
	}

	@Test
	void "preferred width"() {
		def title = "ContainerFieldTest :: preferred width"
		def preferenceField = preferencesTextFieldPreferredWidth
		def containerName = "${preferenceField}-$CONTAINER_NAME"
		def name = preferenceField
		def field = factory.create(component, preferences, preferenceField)

		assertField field,
		name: name,
		title: preferenceField,
		width: -2.0d
		new TestFrameUtil(title, field.AWTComponent).withFixture({FrameFixture fixture ->
			fixture.label preferenceField requireEnabled()
			fixture.panel containerName requireEnabled()
			assert fixture.label(preferenceField).target.getName() == preferenceField
			assert fixture.panel(containerName).target.getName() == containerName
		})
	}

	static Injector injector

	static MockContainerFieldFactory factory

	static Texts texts

	static Images images

	Preferences preferences

	Component component

	Component container

	@BeforeClass
	static void setupFactories() {
		injector = createInjector()
		factory = injector.getInstance MockContainerFieldFactory
		texts = createTextsResource(injector)
		images = createImagesResource(injector)
	}

	@Before
	void setupPreferences() {
		preferences = new Preferences()
		component = createLabel()
		container = createContainer()
	}
}
