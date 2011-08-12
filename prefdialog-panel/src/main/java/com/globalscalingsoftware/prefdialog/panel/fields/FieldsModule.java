package com.globalscalingsoftware.prefdialog.panel.fields;

import com.globalscalingsoftware.prefdialog.panel.api.FactoriesMapFactory;
import com.globalscalingsoftware.prefdialog.panel.api.FieldHandlerFactoriesMap;
import com.globalscalingsoftware.prefdialog.panel.api.FieldsHandlerFactoryWorker;
import com.globalscalingsoftware.prefdialog.panel.fields.LoggerFactory.Logger;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class FieldsModule extends AbstractModule {

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
