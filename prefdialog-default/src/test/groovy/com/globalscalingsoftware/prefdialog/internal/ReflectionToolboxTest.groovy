package com.globalscalingsoftware.prefdialog.internal

import com.globalscalingsoftware.prefdialog.IValidator;

import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.TextField;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class ReflectionToolboxTest {
	
	static final PROPERTY_VALUE = "property_value"
	
	static final HELP_TEXT = "help text"
	
	class InlineValidator implements IValidator<String> {
		
		public boolean isValid(String value) {
			value != null && !value.trim().isEmpty()
		}
	}
	
	static class Validator implements IValidator<String> {
		
		public boolean isValid(String value) {
			value != null && !value.trim().isEmpty()
		}
	}
	
	static class Bean {
		
		@TextField(validator=Validator, validatorText=ReflectionToolboxTest.HELP_TEXT)
		String property = ReflectionToolboxTest.PROPERTY_VALUE
	}
	
	static class ParentBean {
		
		def bean = new Bean()
	}
	
	def parentObject
	
	def bean
	
	def property
	
	def propertyField
	
	def testValue
	
	def propertyHelpText
	
	def toolbox
	
	@Before
	void beforeTest() {
		parentObject = new ParentBean()
		bean = parentObject.bean
		property = parentObject.bean.property
		propertyField = Bean.getDeclaredField("property")
		toolbox = new ReflectionToolbox()
	}
	
	@Test
	void testGetValueFromProperty() {
		def value = toolbox.getValueFrom(propertyField, bean)
		assertThat value, is(PROPERTY_VALUE)
	}
	
	@Test
	void testSetValueToProperty() {
		def newValue = "new_value"
		toolbox.setValueTo(propertyField, bean, newValue)
		assertThat bean.property, is(newValue)
	}
	
	@Test
	void testCreateNewValidatorWithParameters() {
		def objectClass = InlineValidator
		def object = toolbox.newInstance(objectClass, this)
		assertThat object, is(instanceOf(InlineValidator))
	}
	
	@Test
	void testCreateNewValidatorWithNoParameters() {
		def objectClass = Validator
		def object = toolbox.newInstance(objectClass)
		assertThat object, is(instanceOf(Validator))
	}
}
