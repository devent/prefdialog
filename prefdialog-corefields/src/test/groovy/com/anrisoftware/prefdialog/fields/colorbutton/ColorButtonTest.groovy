/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
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

import static com.anrisoftware.prefdialog.fields.buttongroup.ButtonGroupPluginModule.*
import static com.anrisoftware.prefdialog.fields.colorbutton.ColorButtonBean.*

import javax.swing.JPanel

import org.junit.Before
import org.junit.Test

import com.anrisoftware.prefdialog.core.FieldTestUtils
import com.anrisoftware.prefdialog.reflection.exceptions.ReflectionError
import com.google.inject.Injector

/**
 * Test the {@link ColorButtonField}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ColorButtonTest extends FieldTestUtils {

	static final title = "Color Button Test"

	ColorButtonFieldFactory factory

	ColorButtonBean bean

	JPanel container

	@Before
	void createFactory() {
		factory = injector.getInstance ColorButtonFieldFactory
		bean = new ColorButtonBean()
		container = new JPanel()
	}

	Injector createInjector() {
		super.createInjector().createChildInjector new ColorButtonModule()
	}

	@Test
	void "color button with null value"() {
		shouldFailWith(ReflectionError) {
			factory.create(container, bean, COLOR_NULL_VALUE_FIELD).createField()
		}
	}

	@Test
	void "black color"() {
		factory.create(container, bean, COLOR_BLACK_FIELD).createField()
		beginPanelFrame title, container, {
			fixture.button("$COLOR_BLACK-0-$BUTTON_NAME").requireText("#000000")
		}, {
			fixture.button("$COLOR_BLACK-0-$BUTTON_NAME").click()
			fixture.dialog("dialog0").close()
		}
	}

	@Test
	void "right alignment"() {
		factory.create(container, bean, COLOR_RIGHT_FIELD).createField()
		beginPanelFrame title, container, {
		}
	}

	@Test
	void "middle alignment"() {
		factory.create(container, bean, COLOR_MIDDLE_FIELD).createField()
		beginPanelFrame title, container, {
		}
	}

	@Test
	void "left alignment"() {
		factory.create(container, bean, COLOR_LEFT_FIELD).createField()
		beginPanelFrame title, container, {
		}
	}

	//@Test
	void "black color manually"() {
		factory.create(container, bean, COLOR_BLACK_FIELD).createField()
		beginPanelFrame title, container, { Thread.sleep 60 * 1000 }
	}
}
