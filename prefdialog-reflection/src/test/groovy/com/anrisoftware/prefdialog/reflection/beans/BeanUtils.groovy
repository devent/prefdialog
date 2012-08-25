package com.anrisoftware.prefdialog.reflection.beans

import org.junit.Before

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.reflection.utils.ParentBean;
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Create the injector to test annotation access.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class BeanUtils extends TestUtils {

	Injector injector

	ParentBean bean

	@Before
	void beforeTest() {
		injector = createInjector()
		bean = new ParentBean()
	}

	Injector createInjector() {
		Guice.createInjector new BeansModule()
	}
}
