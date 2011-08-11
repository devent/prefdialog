package com.globalscalingsoftware.prefdialog.panel.inputfields.textfield.formattedtextfield;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfields.textfield.formattedtextfield.LoggerFactory.Logger;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class FormattedTextFieldModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, FormattedTextFieldHandler.class).build(
				FormattedTextFieldHandlerFactory.class));
		install(new FactoryModuleBuilder()
				.implement(
						new TypeLiteral<com.globalscalingsoftware.prefdialog.panel.inputfields.textfield.shared.SharedTextFieldLoggerFactory.SharedTextFieldLogger>() {
						}, Logger.class).build(LoggerFactory.class));
	}

}
