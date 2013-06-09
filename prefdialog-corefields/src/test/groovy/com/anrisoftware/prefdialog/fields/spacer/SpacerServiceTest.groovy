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

import static com.anrisoftware.prefdialog.core.AbstractTitleField.*
import static com.anrisoftware.prefdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.fields.spacer.SpacerBean.*
import static com.anrisoftware.prefdialog.fields.spacer.SpacerService.*

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule
import com.anrisoftware.globalpom.reflection.beans.BeansModule
import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see SpacerService
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class SpacerServiceTest {

	@Test
	void "with defaults"() {
		def title = "$NAME :: with defaults"
		def fieldName = SPACER
		bean.childBeanWithDefaultSpacer = new SpacerBean.ChildBeanWithDefaultSpacer()
		def field = factory.create(bean.childBeanWithDefaultSpacer, fieldName)
		def container = field.getAWTComponent()
		new TestFrameUtil(title, container).withFixture({})
	}

	static final String NAME = SpacerServiceTest.class.simpleName

	static Injector injector

	static SpacerFieldFactory factory

	SpacerBean bean

	@BeforeClass
	static void setupFactories() {
		injector = Guice.createInjector(new AnnotationsModule(), new BeansModule())
		factory = findService(INFO).getFactory(injector)
	}

	@Before
	void setupBean() {
		bean = new SpacerBean()
	}
}