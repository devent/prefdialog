package com.globalscalingsoftware.prefdialog.panel.inputfield;

import com.globalscalingsoftware.prefdialog.panel.inputfield.LoggerFactory.Logger;
import com.globalscalingsoftware.prefdialog.panel.inputfield.api.FactoriesMapFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfield.api.FieldHandlerFactoriesMap;
import com.globalscalingsoftware.prefdialog.panel.inputfield.api.FieldsHandlerFactoryWorker;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class InputFieldsModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder()
				.implement(Logger.class, Logger.class).build(
						LoggerFactory.class));
		install(new FactoryModuleBuilder().implement(
				FieldHandlerFactoriesMap.class,
				FieldHandlerFactoriesMapImpl.class).build(
				FactoriesMapFactory.class));
		bind(FieldsHandlerFactoryWorker.class).to(
				FieldsHandlerFactoryWorkerImpl.class);
	}

}
