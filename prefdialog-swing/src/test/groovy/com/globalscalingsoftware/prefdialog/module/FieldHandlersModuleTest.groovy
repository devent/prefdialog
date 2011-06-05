package com.globalscalingsoftware.prefdialog.module

import org.junit.Test;

import com.google.inject.Guice;

class FieldHandlersModuleTest {
	
	@Test
	void testCreateInjector() {
		def injector = Guice.createInjector(new FieldHandlersModule())
		assert injector != null
	}
}
