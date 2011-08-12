/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.reflection

import org.junit.Test


class ReflectionToolboxTest extends AbstractReflectionToolbox {

	@Test
	void testGetValueFromProperty() {
		def value = toolbox.getValueFrom(propertyField, bean)
		assert value == PROPERTY_VALUE
	}

	@Test
	void testSetValueToProperty() {
		def newValue = "new_value"
		toolbox.setValueTo(propertyField, bean, newValue)
		assert bean.property == newValue
	}

	@Test
	void testInvokeMethodWithParameters() {
		def name = "someMethod"
		def parameterType = String
		def object = parentObject
		def value = "some string"
		toolbox.invokeMethodWithParameters name, parameterType, object, value
	}

	@Test
	void testInvokeMethodWithReturnType() {
		def name = "someMethodWithReturn"
		def returnType = String
		def object = parentObject

		def value = toolbox.invokeMethodWithReturnType(name, returnType, object)
		assert value == "test"
	}
}
