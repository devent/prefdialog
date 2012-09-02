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
import org.junit.Test

import com.anrisoftware.prefdialog.reflection.exceptions.ReflectionError
import com.anrisoftware.prefdialog.reflection.utils.Bean

/**
 * Tests for {@link BeanAccessImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class BeanAccessTest extends BeanUtils {

	@Test
	void "read field value"() {
		BeanAccess a = injector.getInstance BeanAccess
		def field = FieldUtils.getField Bean, "stringField", true
		def value = a.getValue field, bean.bean
		assertStringContent value, "Text"
	}

	@Test
	void "read field value via getter"() {
		BeanAccess a = injector.getInstance BeanAccess
		def field = FieldUtils.getField Bean, "getterField", true
		def value = a.getValue field, bean.bean
		assertStringContent value, "Getter Text"
		assert bean.bean.getterOfGetterFieldCalled
	}

	@Test
	void "read field value via getter that throws exception"() {
		BeanAccess a = injector.getInstance BeanAccess
		def field = FieldUtils.getField Bean, "getterFieldThatThrowsException", true
		shouldFailWith(ReflectionError) {
			a.getValue field, bean.bean
		}
	}

	@Test
	void "write field value"() {
		BeanAccess a = injector.getInstance BeanAccess
		def field = FieldUtils.getField Bean, "stringField", true
		String value = "value"
		a.setValue value, field, bean.bean
		assertStringContent bean.bean.stringField, value
	}

	@Test
	void "write field value via setter"() {
		BeanAccess a = injector.getInstance BeanAccess
		def field = FieldUtils.getField Bean, "setterField", true
		String value = "value"
		a.setValue value, field, bean.bean
		assertStringContent bean.bean.setterField, value
		assert bean.bean.setterOfSetterFieldCalled
	}

	@Test
	void "write field value via setter that throws exception"() {
		BeanAccess a = injector.getInstance BeanAccess
		def field = FieldUtils.getField Bean, "setterFieldThatThrowsException", true
		String value = "value"
		shouldFailWith(ReflectionError) {
			a.setValue value, field, bean.bean
		}
	}
}
