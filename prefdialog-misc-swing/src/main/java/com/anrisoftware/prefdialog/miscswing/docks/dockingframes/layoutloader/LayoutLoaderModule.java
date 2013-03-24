package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutloader;

import java.io.InputStream;

import javax.swing.SwingWorker;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the factory to create the load layout worker.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class LayoutLoaderModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<SwingWorker<InputStream, InputStream>>() {
				}, LoadLayoutWorker.class).build(LoadLayoutWorkerFactory.class));
	}

}
