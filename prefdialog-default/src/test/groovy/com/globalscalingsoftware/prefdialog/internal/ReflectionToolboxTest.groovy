package com.globalscalingsoftware.prefdialog.internal

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class ReflectionToolboxTest {
	
	def parentObject
	
	def property
	
	def propertyField
	
	def testValue
	
	def propertyHelpText
	
	@Before
	void beforeTest() {
		parentObject = new General()
		property = parentObject.fields
		propertyField = General.getDeclaredField("fields")
		testValue = 99
		propertyHelpText = "Must be a number and greater then 2"
	}
	
	@Test
	void testGetValueFromProperty() {
		def toolbox = new ReflectionToolbox(null)
		def value = toolbox.getValueFrom(propertyField, parentObject)
		assertThat value, is(property)
	}
	
	@Test
	void testSetValueToProperty() {
		def toolbox = new ReflectionToolbox(null)
		toolbox.setValueTo(propertyField, parentObject, testValue)
		assertThat parentObject.fields, is(testValue)
	}
	
	@Test
	void testGetHelpTextFromProperty() {
		def filter = new AnnotationsFilter()
		def toolbox = new ReflectionToolbox(filter)
		def helpText = toolbox.getHelpText(propertyField)
		assertThat helpText, is(propertyHelpText)
	}
	
	@Test
	void testGetValidatorFromProperty() {
		def filter = new AnnotationsFilter()
		def toolbox = new ReflectionToolbox(filter)
		def validator = toolbox.getValidator(propertyField)
		assertThat validator, is(instanceOf(FieldsValidator))
	}
}
