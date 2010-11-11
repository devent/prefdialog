package com.globalscalingsoftware.prefdialog.internal.reflection

import org.junit.Test;

import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;

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
