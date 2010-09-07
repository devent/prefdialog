package com.globalscalingsoftware.prefdialog.internal

import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.internal.formattedtextfield.General;
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
		propertyHelpText = "Must be a number and between 2 and 100"
	}
	
	@Test
	void testGetValueFromProperty() {
		def toolbox = new ReflectionToolbox()
		def value = toolbox.getValueFrom(propertyField, parentObject)
		assertThat value, is(property)
	}
	
	@Test
	void testSetValueToProperty() {
		def toolbox = new ReflectionToolbox()
		toolbox.setValueTo(propertyField, parentObject, testValue)
		assertThat parentObject.fields, is(testValue)
	}
	
	@Test
	void testGetHelpTextFromProperty() {
		def toolbox = new ReflectionToolbox()
		def helpText = toolbox.getHelpText(propertyField)
		assertThat helpText, is(propertyHelpText)
	}
	
	@Test
	void testGetValidatorFromProperty() {
		def toolbox = new ReflectionToolbox()
		def validator = toolbox.getValidator(propertyField)
		assertThat validator, is(instanceOf(FieldsValidator))
	}
}
