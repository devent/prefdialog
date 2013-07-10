/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.buttongroup

import static com.anrisoftware.prefdialog.core.AbstractTitleField.*
import static com.anrisoftware.prefdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.fields.buttongroup.ButtonGroupBean.*
import static com.anrisoftware.prefdialog.fields.buttongroup.ButtonGroupService.*

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Test the {@link ButtonGroupField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ButtonGroupTest {

	@Test
	void "with defaults"() {
		def title = "$NAME::with defaults"
		def fieldName = BUTTONS
		def titleName = "$fieldName-$TITLE_NAME"
		def buttons = [
			"$fieldName-0-$BUTTON_NAME",
			"$fieldName-1-$BUTTON_NAME"
		]
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()
		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			fixture.label titleName requireText "$fieldName:"
		}, { FrameFixture fixture ->
			fixture.button buttons[0] click()
			assert bean.button1Called == true
		}, { FrameFixture fixture ->
			fixture.button buttons[1] click()
			assert bean.button2Called == true
		})
	}

	@Test
	void "with horizontal alignment middle"() {
		def title = "$NAME::with horizontal alignment middle"
		def fieldName = BUTTONS_MIDDLE
		def buttons = [
			"$fieldName-0-$BUTTON_NAME",
			"$fieldName-1-$BUTTON_NAME"
		]
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()
		new TestFrameUtil(title, container).withFixture({FrameFixture fixture ->
			fixture.button buttons[0] click()
			assert bean.button1Called == true
		}, {FrameFixture fixture ->
			fixture.button buttons[1] click()
			assert bean.button2Called == true
		})
	}

	@Test
	void "with horizontal alignment left"() {
		def title = "$NAME::with horizontal alignment left"
		def fieldName = BUTTONS_LEFT
		def buttons = [
			"$fieldName-0-$BUTTON_NAME",
			"$fieldName-1-$BUTTON_NAME"
		]
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()
		new TestFrameUtil(title, container).withFixture({FrameFixture fixture ->
			fixture.button buttons[0] click()
			assert bean.button1Called == true
		}, {FrameFixture fixture ->
			fixture.button buttons[1] click()
			assert bean.button2Called == true
		})
	}

	@Test
	void "with horizontal alignment right"() {
		def title = "$NAME::with horizontal alignment right"
		def fieldName = BUTTONS_RIGHT
		def buttons = [
			"$fieldName-0-$BUTTON_NAME",
			"$fieldName-1-$BUTTON_NAME"
		]
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()
		new TestFrameUtil(title, container).withFixture({FrameFixture fixture ->
			fixture.button buttons[0] click()
			assert bean.button1Called == true
		}, {FrameFixture fixture ->
			fixture.button buttons[1] click()
			assert bean.button2Called == true
		})
	}

	static final String NAME = ButtonGroupTest.class.simpleName

	static Injector injector

	static ButtonGroupFieldFactory factory

	ButtonGroupBean bean

	@BeforeClass
	static void setupFactories() {
		injector = Guice.createInjector(new CoreFieldComponentModule(), new ButtonGroupModule())
		factory = injector.getInstance ButtonGroupFieldFactory
	}

	@Before
	void setupBean() {
		bean = new ButtonGroupBean()
	}
}
