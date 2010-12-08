package com.globalscalingsoftware.prefdialog.module;

import static com.google.inject.assistedinject.FactoryProvider.newFactory;

import com.globalscalingsoftware.prefdialog.internal.inputfield.child.ChildFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.ChildFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.textfield.TextFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.textfield.TextFieldHandlerFactory;
import com.google.inject.AbstractModule;

public class FieldHandlersModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TextFieldHandlerFactory.class).toProvider(
				newFactory(TextFieldHandlerFactory.class,
						TextFieldHandler.class));
		bind(ChildFieldHandlerFactory.class).toProvider(
				newFactory(ChildFieldHandlerFactory.class,
						ChildFieldHandler.class));
	}

}
