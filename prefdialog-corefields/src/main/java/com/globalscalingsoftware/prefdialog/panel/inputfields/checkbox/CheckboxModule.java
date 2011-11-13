package com.globalscalingsoftware.prefdialog.panel.inputfields.checkbox;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.CheckBoxFieldHandlerFactory;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the checkbox field classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public class CheckboxModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, CheckBoxFieldHandler.class).build(
				CheckBoxFieldHandlerFactory.class));
		install(new FactoryModuleBuilder().implement(
				LoggerFactory.Logger.class, LoggerFactory.Logger.class).build(
				LoggerFactory.class));
	}

}
