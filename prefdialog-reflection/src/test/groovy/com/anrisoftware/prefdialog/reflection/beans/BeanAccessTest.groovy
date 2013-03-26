/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-reflection.
 * 
 * prefdialog-reflection is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-reflection is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-reflection. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.reflection.beans

import org.apache.commons.lang3.reflect.FieldUtils
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.prefdialog.reflection.exceptions.ReflectionError
import com.anrisoftware.prefdialog.reflection.utils.Bean
import com.anrisoftware.prefdialog.reflection.utils.ParentBean
import com.google.inject.Injector

/**
 * Tests for {@link BeanAccessImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class BeanAccessTest extends BeanUtils {

	@Test
	void "read field value"() {
		def field = FieldUtils.getField Bean, "stringField", true
		def value = factory.create(field, bean.bean).getValue()
		assertStringContent value, "Text"
	}

	@Test
	void "read field value via getter"() {
		def field = FieldUtils.getField Bean, "getterField", true
		def value = factory.create(field, bean.bean).getValue()
		assertStringContent value, "Getter Text"
		assert bean.bean.getterOfGetterFieldCalled
	}

	@Test
	void "read field value via getter that throws exception"() {
		def field = FieldUtils.getField Bean, "getterFieldThatThrowsException", true
		shouldFailWith(ReflectionError) {
			def value = factory.create(field, bean.bean).getValue()
		}
	}

	@Test
	void "write field value"() {
		def field = FieldUtils.getField Bean, "stringField", true
		String value = "value"
		factory.create(field, bean.bean).setValue(value)
		assertStringContent bean.bean.stringField, value
	}

	@Test
	void "write field value via setter"() {
		def field = FieldUtils.getField Bean, "setterField", true
		String value = "value"
		factory.create(field, bean.bean).setValue(value)
		assertStringContent bean.bean.setterField, value
		assert bean.bean.setterOfSetterFieldCalled
	}

	@Test
	void "write field value via setter that throws exception"() {
		def field = FieldUtils.getField Bean, "setterFieldThatThrowsException", true
		String value = "value"
		shouldFailWith(ReflectionError) {
			factory.create(field, bean.bean).setValue(value)
		}
	}

	static Injector injector

	static BeanAccessFactory factory

	ParentBean bean

	@Before
	void setupBean() {
		bean = new ParentBean()
	}

	@BeforeClass
	static void setupFactory() {
		injector = createInjector()
		factory = createFactory(injector)
	}
}
