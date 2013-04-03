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
package com.anrisoftware.prefdialog.fields.buttongroup

import static com.anrisoftware.prefdialog.core.AbstractTitleField.*
import static com.anrisoftware.prefdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.fields.buttongroup.ButtonGroupBean.*
import static com.anrisoftware.prefdialog.fields.buttongroup.ButtonGroupService.*

import java.awt.Container

import javax.swing.JPanel

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule
import com.anrisoftware.globalpom.reflection.beans.BeansModule
import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.fields.FieldService
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Test the button group field as a service.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ButtonGroupServiceTest {

	@Test
	void "with defaults"() {
		def title = "ButtonGroupServiceTest :: with defaults"
		def fieldName = BUTTONS
		def titleName = "$fieldName-$TITLE_NAME"
		def buttons = [
			"$fieldName-0-$BUTTON_NAME",
			"$fieldName-1-$BUTTON_NAME"
		]
		def field = factory.create(container, bean, fieldName)
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

	static Injector injector

	static ButtonGroupFieldFactory factory

	ButtonGroupBean bean

	Container container

	@BeforeClass
	static void setupFactories() {
		injector = Guice.createInjector(new AnnotationsModule(), new BeansModule())
		factory = findService().getFactory(injector)
	}

	static ButtonGroupService findService() {
		ServiceLoader.load(FieldService).find { it.info == INFO }
	}

	@Before
	void setupBean() {
		bean = new ButtonGroupBean()
		container = new JPanel()
	}
}
