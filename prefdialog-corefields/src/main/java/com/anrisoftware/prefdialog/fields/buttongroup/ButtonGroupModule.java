package com.anrisoftware.prefdialog.fields.buttongroup;

import java.awt.Container;

import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.reflection.annotations.AnnotationsModule;
import com.anrisoftware.prefdialog.reflection.beans.BeansModule;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the button group field factory.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ButtonGroupModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new AnnotationsModule());
		install(new BeansModule());
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldComponent<Container>>() {
				}, ButtonGroupField.class).build(ButtonGroupFieldFactory.class));
	}

}
