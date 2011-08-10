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

import org.junit.Before

import com.globalscalingsoftware.prefdialog.annotations.TextField
import com.globalscalingsoftware.prefdialog.validators.NotEmptyString

class AbstractReflectionToolbox {

	static final PROPERTY_VALUE = "property_value"

	static final HELP_TEXT = "help text"

	static class Bean {

		@TextField(validator=NotEmptyString, validatorText=AbstractReflectionToolbox.HELP_TEXT)
		String property = AbstractReflectionToolbox.PROPERTY_VALUE
	}

	static class ParentBean {

		def bean = new Bean()

		def someMethod(String someParameter) {
		}

		def someMethodWithReturn() {
			return "test"
		}
	}

	def parentObject

	def bean

	def property

	def propertyField

	def testValue

	def propertyHelpText

	ReflectionToolbox toolbox

	@Before
	void beforeTest() {
		parentObject = new ParentBean()
		bean = parentObject.bean
		property = parentObject.bean.property
		propertyField = Bean.getDeclaredField("property")
		toolbox = new ReflectionToolbox()
	}
}
