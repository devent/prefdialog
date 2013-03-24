package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutsaver;

import java.io.OutputStream;

import javax.swing.SwingWorker;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the factory to create the save layout worker.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class LayoutSaverModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<SwingWorker<OutputStream, OutputStream>>() {
				}, SaveLayoutWorker.class).build(SaveLayoutWorkerFactory.class));
	}

}
