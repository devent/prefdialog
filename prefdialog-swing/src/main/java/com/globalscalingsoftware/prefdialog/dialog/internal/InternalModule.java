package com.globalscalingsoftware.prefdialog.dialog.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;

import com.globalscalingsoftware.prefdialog.PreferenceDialogHandler;
import com.globalscalingsoftware.prefdialog.PreferenceDialogHandlerFactory;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.dialog.internal.CreatePreferencePanelHandlersWorker.CreatePreferencePanelHandlersWorkerFactory;
import com.globalscalingsoftware.prefdialog.dialog.internal.PreferenceDialog.PreferenceDialogFactory;
import com.globalscalingsoftware.prefdialog.dialog.internal.PreferencePanelsCollection.PreferencePanelsCollectionFactory;
import com.globalscalingsoftware.prefdialog.dialog.internal.PreferencePanelsHandler.PreferencePanelsHandlerFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryProvider;
import com.google.inject.name.Named;

/**
 * Binds all dependencies for the {@link PreferenceDialogHandler}.
 */
public class InternalModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(PreferenceDialogHandlerFactory.class).toProvider(
				FactoryProvider.newFactory(
						PreferenceDialogHandlerFactory.class,
						PreferenceDialogHandlerImpl.class));
		bind(PreferenceDialogFactory.class).toProvider(
				FactoryProvider.newFactory(PreferenceDialogFactory.class,
						PreferenceDialog.class));
		bind(PreferencePanelsCollectionFactory.class).toProvider(
				FactoryProvider.newFactory(
						PreferencePanelsCollectionFactory.class,
						PreferencePanelsCollection.class));
		bind(CreatePreferencePanelHandlersWorkerFactory.class).toProvider(
				FactoryProvider.newFactory(
						CreatePreferencePanelHandlersWorkerFactory.class,
						CreatePreferencePanelHandlersWorker.class));
		bind(PreferencePanelsHandlerFactory.class).toProvider(
				FactoryProvider.newFactory(
						PreferencePanelsHandlerFactory.class,
						PreferencePanelsHandler.class));
	}

	@Provides
	@Named("childAnnotations")
	Collection<Class<? extends Annotation>> bindAnnotations() {
		Collection<Class<? extends Annotation>> annotations = new ArrayList<Class<? extends Annotation>>();
		annotations.add(Child.class);
		return annotations;
	}
}
