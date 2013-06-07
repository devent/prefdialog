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
package com.anrisoftware.prefdialog.panel

import static com.anrisoftware.prefdialog.core.AbstractTitleField.*
import static com.anrisoftware.prefdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.fields.checkbox.CheckBoxBean.*
import static com.anrisoftware.prefdialog.fields.checkbox.CheckBoxService.*

import java.awt.Container

import javax.swing.JPanel

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule
import com.anrisoftware.globalpom.reflection.beans.BeansModule
import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Test the check box field as a service.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class PreferencesPanelServiceTest {

	@Test
	void "with defaults"() {
		def title = "CheckBoxServiceTest :: with defaults"
		def fieldName = NO_TEXT
		def field = factory.create(container, bean, fieldName)
		new TestFrameUtil(title, container).withFixture({})
	}

	static Injector injector

	static CheckBoxFieldFactory factory

	PreferencesPanelBean bean

	Container container

	@BeforeClass
	static void setupFactories() {
		injector = Guice.createInjector(new AnnotationsModule(), new BeansModule())
		factory = findService(INFO).getFactory(injector)
	}

	@Before
	void setupBean() {
		bean = new PreferencesPanelBean()
		container = new JPanel()
	}
}
