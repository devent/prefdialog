package com.anrisoftware.prefdialog.core

import java.lang.reflect.Field

import org.junit.Before

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.reflection.annotations.AnnotationsModule
import com.anrisoftware.prefdialog.reflection.beans.BeansModule
import com.google.inject.Guice
import com.google.inject.Injector

class FieldTestUtils extends TestFrameUtil {

	Injector injector

	MockFieldComponentFactory fieldComponentFactory

	Preferences preferences

	Field preferencesTextField

	@Before
	void beforeTest() {
		injector = createInjector()
		fieldComponentFactory = injector.getInstance MockFieldComponentFactory
		preferences = new Preferences()
		preferencesTextField = Preferences.class.getDeclaredField("textField")
	}

	Injector createInjector() {
		Guice.createInjector new MockModule(), new AnnotationsModule(), new BeansModule()
	}
}
