package com.globalscalingsoftware.prefdialog.panel.inputfields.fontchooser;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.FontChooserFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox.FontComboBoxModule;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the font chooser field classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public class FontChooserModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, FontChooserFieldHandler.class).build(
				FontChooserFieldHandlerFactory.class));
		install(new FactoryModuleBuilder().implement(
				LoggerFactory.Logger.class, LoggerFactory.Logger.class).build(
				LoggerFactory.class));
		install(new FontComboBoxModule());
	}

}
