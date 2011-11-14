package com.globalscalingsoftware.prefdialog.panel.inputfields.combobox;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.ComboBoxFieldHandlerFactory;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the combobox field classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public class ComboBoxModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, ComboBoxFieldHandler.class).build(
				ComboBoxFieldHandlerFactory.class));
		install(new FactoryModuleBuilder().implement(
				LoggerFactory.Logger.class, LoggerFactory.Logger.class).build(
				LoggerFactory.class));
	}

}
