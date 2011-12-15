package com.anrisoftware.prefdialog.panel;

import com.anrisoftware.prefdialog.PreferencePanelHandler;
import com.anrisoftware.prefdialog.PreferencePanelHandlerFactory;
import com.anrisoftware.prefdialog.panel.fields.FieldsModule;
import com.anrisoftware.prefdialog.reflection.ReflectionModule;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class PrefdialogPanelModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new ReflectionModule());
		install(new FieldsModule());
		install(new FactoryModuleBuilder().implement(
				PreferencePanelHandler.class, PreferencePanelHandlerImpl.class)
				.build(PreferencePanelHandlerFactory.class));
		install(new FactoryModuleBuilder().implement(
				ChildFieldHandlerWorker.class, ChildFieldHandlerWorker.class)
				.build(ChildFieldHandlerWorkerFactory.class));
	}

}
