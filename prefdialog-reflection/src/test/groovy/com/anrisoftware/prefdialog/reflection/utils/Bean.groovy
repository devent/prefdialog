package com.anrisoftware.prefdialog.reflection.utils

/**
 * Bean to test fields access.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class Bean {

	@BeanAnnotation(value = "Annotation Value", title = "Annotation Title")
	String annotatedField = 'Text'

	String stringField = 'Text'

	int intField = 5

	String getterField = 'Getter Text'

	boolean getterOfGetterFieldCalled = false

	String getGetterField() {
		getterOfGetterFieldCalled = true
		return getterField
	}

	String getterFieldThatThrowsException = 'Getter Text'

	String getGetterFieldThatThrowsException() {
		throw new Exception("Error in getter")
	}
}

