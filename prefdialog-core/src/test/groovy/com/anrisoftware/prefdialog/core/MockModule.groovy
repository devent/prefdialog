package com.anrisoftware.prefdialog.core

import com.google.inject.AbstractModule
import com.google.inject.assistedinject.FactoryModuleBuilder

class MockModule extends AbstractModule {

	@Override
	protected void configure() {
		install new FactoryModuleBuilder().implement(MockFieldComponent, MockFieldComponent).build(MockFieldComponentFactory)
	}
}

