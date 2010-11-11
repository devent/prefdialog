package com.globalscalingsoftware.prefdialog.internal.reflection

import com.globalscalingsoftware.prefdialog.annotations.fields.TextField 
import com.globalscalingsoftware.prefdialog.validators.NotEmptyString 
import org.junit.Before;

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
