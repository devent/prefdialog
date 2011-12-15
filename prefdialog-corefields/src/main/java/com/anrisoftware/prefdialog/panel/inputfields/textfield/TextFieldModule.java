package com.anrisoftware.prefdialog.panel.inputfields.textfield;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.panel.inputfields.api.TextFieldHandlerFactory;
import com.anrisoftware.prefdialog.panel.inputfields.textfield.shared.TextFieldSharedModule;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the text field classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public class TextFieldModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new TextFieldSharedModule());
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, TextFieldHandler.class).build(TextFieldHandlerFactory.class));
	}

}
