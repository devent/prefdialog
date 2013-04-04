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
package com.anrisoftware.prefdialog.fields.child

import static com.anrisoftware.prefdialog.fields.child.ChildBean.*
import static com.anrisoftware.prefdialog.fields.child.ChildService.*

import java.awt.Container

import javax.swing.JPanel

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule
import com.anrisoftware.globalpom.reflection.beans.BeansModule
import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.fields.checkbox.CheckBoxFieldFactory
import com.anrisoftware.prefdialog.fields.checkbox.CheckBoxModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Test the {@link ChildField}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ChildTest {

	@Test
	void "null value"() {
		def title = "ChildTest :: null value"
		def fieldName = NULL_VALUE
		def separatorName = "$fieldName-$TITLE_SEPARATOR_NAME"
		def scrollName = "$fieldName-$CHILDREN_SCROLL_NAME"
		def field = factory.create(container, bean, fieldName)
		def checkBoxFieldName = CHECKBOX
		def checkBox = checkBoxfactory.create(new JPanel(), bean.nullValue, checkBoxFieldName)

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			field.addField checkBox
			fixture.panel fieldName requireVisible()
			fixture.scrollPane scrollName requireVisible()
			fixture.checkBox checkBoxFieldName requireVisible()
		})
	}

	@Test
	void "with no title"() {
		def title = "ChildTest :: with no title"
		def fieldName = NO_TITLE
		def separatorName = "$fieldName-$TITLE_SEPARATOR_NAME"
		def scrollName = "$fieldName-$CHILDREN_SCROLL_NAME"
		def field = factory.create(container, bean, fieldName)
		def checkBoxFieldName = CHECKBOX
		def checkBox = checkBoxfactory.create(new JPanel(), bean.noTitle, checkBoxFieldName)

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			field.addField checkBox
			fixture.panel fieldName requireVisible()
			fixture.scrollPane scrollName requireVisible()
			fixture.checkBox checkBoxFieldName requireVisible()
		})
	}

	static Injector injector

	static ChildFieldFactory factory

	static CheckBoxFieldFactory checkBoxfactory

	ChildBean bean

	Container container

	@BeforeClass
	static void setupFactories() {
		injector = Guice.createInjector(new AnnotationsModule(), new BeansModule())
		factory = createChildFieldFactory injector
		checkBoxfactory = createCheckBoxFieldFactory injector
	}

	static ChildFieldFactory createChildFieldFactory(Injector injector) {
		injector.createChildInjector(new ChildModule()).getInstance(
				ChildFieldFactory)
	}

	static CheckBoxFieldFactory createCheckBoxFieldFactory(Injector injector) {
		injector.createChildInjector(new CheckBoxModule()).getInstance(
				CheckBoxFieldFactory)
	}

	@Before
	void setupBean() {
		bean = new ChildBean()
		container = new JPanel()
	}
}
