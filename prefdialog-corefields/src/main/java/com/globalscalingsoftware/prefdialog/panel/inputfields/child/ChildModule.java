package com.globalscalingsoftware.prefdialog.panel.inputfields.child;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.ChildFieldHandlerFactory;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ChildModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, ChildFieldHandler.class).build(
				ChildFieldHandlerFactory.class));
	}

}
