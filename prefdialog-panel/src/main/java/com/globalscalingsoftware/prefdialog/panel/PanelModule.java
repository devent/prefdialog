package com.globalscalingsoftware.prefdialog.panel;

import com.globalscalingsoftware.prefdialog.PreferencePanelHandler;
import com.globalscalingsoftware.prefdialog.PreferencePanelHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfield.InputFieldsModule;
import com.globalscalingsoftware.prefdialog.reflection.ReflectionModule;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class PanelModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new ReflectionModule());
		install(new InputFieldsModule());
		install(new FactoryModuleBuilder().implement(
				PreferencePanelHandler.class, PreferencePanelHandlerImpl.class)
				.build(PreferencePanelHandlerFactory.class));
		install(new FactoryModuleBuilder().implement(
				ChildFieldHandlerWorker.class, ChildFieldHandlerWorker.class)
				.build(ChildFieldHandlerWorkerFactory.class));
	}

}
