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

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.google.inject.Guice

/**
 * Test the button group field as a service.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ButtonGroupServiceTest {

	@Test
	void "service"() {
		def title = "$NAME::service"
		def fieldName = BUTTONS
		def field = factory.create(bean, fieldName)
		new TestFrameUtil(title, field.AWTComponent).withFixture({})
	}

	static final String NAME = ButtonGroupTest.class.simpleName

	static ButtonGroupFieldFactory factory

	ButtonGroupBean bean

	Container container

	@BeforeClass
	static void setupFactories() {
		def injector = Guice.createInjector(new CoreFieldComponentModule())
		factory = findService(INFO).getFactory(injector)
	}

	@Before
	void setupBean() {
		bean = new ButtonGroupBean()
		container = new JPanel()
	}
}
