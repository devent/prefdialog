package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield.LoggerFactory.Logger;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class TextFieldModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, TextFieldHandler.class).build(TextFieldHandlerFactory.class));
		install(new FactoryModuleBuilder()
				.implement(
						new TypeLiteral<com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield.shared.LoggerFactory.Logger>() {
						}, Logger.class).build(LoggerFactory.class));
	}

}
