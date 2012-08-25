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
}
