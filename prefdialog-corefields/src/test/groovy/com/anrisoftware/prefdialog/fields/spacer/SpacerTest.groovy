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
package com.anrisoftware.prefdialog.fields.spacer

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.fields.spacer.SpacerBean.*

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule
import com.anrisoftware.globalpom.reflection.beans.BeansModule
import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.fields.child.ChildFieldFactory
import com.anrisoftware.prefdialog.fields.child.ChildModule
import com.anrisoftware.prefdialog.fields.textfield.TextFieldFactory
import com.anrisoftware.prefdialog.fields.textfield.TextFieldModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see SpacerField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class SpacerTest {

	@Test
	void "default spacer"() {
		def title = "$NAME :: default spacer"
		def fieldName = SPACER
		def childField = childFactory.create(bean, CHILD_BEAN)
		def container = childField.getAWTComponent()
		childField.addField textFactory.create(bean.childBean, TOP)
		childField.addField spacerFactory.create(bean.childBean, fieldName)
		childField.addField textFactory.create(bean.childBean, BOTTOM)

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
		})
	}

	@Test
	void "manually"() {
		def title = "$NAME :: manually"
		def fieldName = SPACER
		def childField = childFactory.create(bean, CHILD_BEAN)
		def container = childField.getAWTComponent()
		childField.addField textFactory.create(bean.childBean, TOP)
		childField.addField spacerFactory.create(bean.childBean, fieldName)
		childField.addField textFactory.create(bean.childBean, BOTTOM)

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			Thread.sleep 60 * 1000l
			assert false : "Deactivate manually test"
		})
	}

	static final String NAME = SpacerTest.class.simpleName

	static Injector injector

	static SpacerFieldFactory spacerFactory

	static childFactory

	static textFactory

	SpacerBean bean

	@BeforeClass
	static void setupFactories() {
		injector = Guice.createInjector(
				new AnnotationsModule(), new BeansModule())
		spacerFactory = createSpacerFieldFactory injector
		childFactory = createChildFieldFactory injector
		textFactory = createTextFieldFactory injector
	}

	@Before
	void setupBean() {
		bean = new SpacerBean()
	}

	static ChildFieldFactory createChildFieldFactory(Injector injector) {
		injector.createChildInjector(new ChildModule()).getInstance(
				ChildFieldFactory)
	}

	static TextFieldFactory createTextFieldFactory(Injector injector) {
		injector.createChildInjector(new TextFieldModule()).getInstance(
				TextFieldFactory)
	}

	static SpacerFieldFactory createSpacerFieldFactory(Injector injector) {
		injector.createChildInjector(new SpacerModule()).getInstance(
				SpacerFieldFactory)
	}
}
