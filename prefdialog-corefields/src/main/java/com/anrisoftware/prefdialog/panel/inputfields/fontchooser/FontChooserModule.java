package com.anrisoftware.prefdialog.panel.inputfields.fontchooser;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.panel.inputfields.api.FontChooserFieldHandlerFactory;
import com.anrisoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox.FontComboBoxModule;
import com.anrisoftware.swingcomponents.fontchooser.defaults.FontChooserDefaultsModule;
import com.anrisoftware.swingcomponents.fontchooser.preview.FontChooserPreviewModule;
import com.anrisoftware.swingcomponents.slidingpanel.SlidingPanelModule;
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
		install(new SlidingPanelModule());
		install(new com.anrisoftware.swingcomponents.fontchooser.FontChooserModule());
		install(new FontChooserDefaultsModule());
		install(new FontChooserPreviewModule());
	}

}
