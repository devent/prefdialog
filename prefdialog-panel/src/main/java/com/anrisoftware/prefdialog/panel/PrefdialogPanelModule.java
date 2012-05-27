package com.anrisoftware.prefdialog.panel;

import com.anrisoftware.prefdialog.PreferencePanel;
import com.anrisoftware.prefdialog.PreferencePanelFactory;
import com.anrisoftware.prefdialog.panel.fields.FieldsModule;
import com.anrisoftware.prefdialog.reflection.ReflectionModule;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the preference panel classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public class PrefdialogPanelModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new ReflectionModule());
		install(new FieldsModule());
		install(new FactoryModuleBuilder().implement(PreferencePanel.class,
				PreferencePanelImpl.class).build(PreferencePanelFactory.class));
		install(new FactoryModuleBuilder().implement(ChildFieldWorker.class,
				ChildFieldWorker.class).build(ChildFieldWorkerFactory.class));
	}

}
