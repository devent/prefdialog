package com.globalscalingsoftware.prefdialog.panel.inputfields.button;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.ButtonGroupFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfields.button.LoggerFactory.Logger;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ButtonModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, ButtonGroupFieldHandler.class).build(
				ButtonGroupFieldHandlerFactory.class));
		install(new FactoryModuleBuilder()
				.implement(Logger.class, Logger.class).build(
						LoggerFactory.class));
	}

}
