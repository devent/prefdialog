package com.globalscalingsoftware.prefdialog.panel.inputfields.slider;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.SliderFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfields.slider.LoggerFactory.Logger;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class SliderModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, SliderFieldHandler.class).build(
				SliderFieldHandlerFactory.class));
		install(new FactoryModuleBuilder()
				.implement(Logger.class, Logger.class).build(
						LoggerFactory.class));
	}

}
