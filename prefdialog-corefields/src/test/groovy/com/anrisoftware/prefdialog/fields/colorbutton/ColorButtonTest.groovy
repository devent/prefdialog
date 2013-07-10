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
package com.anrisoftware.prefdialog.fields.colorbutton

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.fields.buttongroup.ButtonGroupService.*
import static com.anrisoftware.prefdialog.fields.colorbutton.ColorButtonBean.*
import groovy.util.logging.Slf4j

import java.awt.Color
import java.awt.event.KeyEvent

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.reflection.exceptions.ReflectionError
import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.prefdialog.core.FieldTestUtils
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see ColorButtonField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Slf4j
class ColorButtonTest extends FieldTestUtils {

	@Test
	void "with null value"() {
		def fieldName = COLOR_NULL_VALUE
		shouldFailWith(ReflectionError) {
			def field = factory.create(bean, fieldName)
		}
	}

	@Test
	void "apply user input"() {
		def title = "$NAME::apply user input"
		def fieldName = COLOR_BLACK
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()
		def buttonName = "$fieldName-0-$BUTTON_NAME"

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			fixture.button buttonName click()
			fixture.dialog().pressAndReleaseKeys KeyEvent.VK_ENTER
		}, { FrameFixture fixture ->
			assert bean.colorBlack == Color.BLACK
		})
	}

	@Test
	void "restore user input"() {
		def title = "$NAME::restore user input"
		def fieldName = COLOR_BLACK
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()
		def buttonName = "$fieldName-0-$BUTTON_NAME"

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			fixture.button buttonName click()
			fixture.dialog().pressAndReleaseKeys KeyEvent.VK_ENTER
			field.restoreInput()
		}, { FrameFixture fixture ->
			assert bean.colorBlack == Color.BLACK
		})
	}

	@Test
	void "with right alignment"() {
		def title = "$NAME::with right alignment"
		def fieldName = COLOR_RIGHT
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()
		def buttonName = "$fieldName-0-$BUTTON_NAME"

		new TestFrameUtil(title, container).withFixture({ })
	}

	@Test
	void "with center alignment"() {
		def title = "$NAME::with center alignment"
		def fieldName = COLOR_MIDDLE
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()
		def buttonName = "$fieldName-0-$BUTTON_NAME"

		new TestFrameUtil(title, container).withFixture({ })
	}

	@Test
	void "with left alignment"() {
		def title = "$NAME::with left alignment"
		def fieldName = COLOR_LEFT
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()
		def buttonName = "$fieldName-0-$BUTTON_NAME"

		new TestFrameUtil(title, container).withFixture({ })
	}

	//@Test
	void "manually"() {
		def title = "$NAME::manually"
		def fieldName = COLOR_BLACK
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()
		new TestFrameUtil(title, container).withFixture({
			Thread.sleep 60*1000
			assert false : "Deactivate manually test."
		})
	}

	static final String NAME = ColorButtonTest.class.simpleName

	static Injector injector

	static ColorButtonFieldFactory factory

	ColorButtonBean bean

	@BeforeClass
	static void setupFactories() {
		injector = Guice.createInjector(new CoreFieldComponentModule(), new ColorButtonModule())
		factory = injector.getInstance ColorButtonFieldFactory
	}

	@Before
	void setupBean() {
		bean = new ColorButtonBean()
	}
}
