package com.anrisoftware.prefdialog.reflection.utils

/**
 * Bean to test fields access.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ParentBean {

	static class Bean {

		@BeanAnnotation(value = "Annotation Value")
		String annotatedField = 'Text'

		String stringField = 'Text'

		int intField = 5
	}

	Bean bean = new Bean()
}
