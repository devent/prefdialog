package com.globalscalingsoftware.prefdialog.panel.inputfields.radiobutton;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.RadioButtonFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfields.radiobutton.LoggerFactory.Logger;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class RadioButtonModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, RadioButtonFieldHandler.class).build(
				RadioButtonFieldHandlerFactory.class));
		install(new FactoryModuleBuilder()
				.implement(Logger.class, Logger.class).build(
						LoggerFactory.class));
	}

}
