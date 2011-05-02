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
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Named;

/**
 * Binds all dependencies for the {@link PreferenceDialogHandler}.
 */
public class InternalModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<PreferenceDialogHandler>() {
				}, PreferenceDialogHandlerImpl.class).build(
				PreferenceDialogHandlerFactory.class));
		install(new FactoryModuleBuilder().implement(PreferenceDialog.class,
				PreferenceDialog.class).build(PreferenceDialogFactory.class));
		install(new FactoryModuleBuilder().implement(
				PreferencePanelsCollection.class,
				PreferencePanelsCollection.class).build(
				PreferencePanelsCollectionFactory.class));
		install(new FactoryModuleBuilder().implement(
				CreatePreferencePanelHandlersWorker.class,
				CreatePreferencePanelHandlersWorker.class).build(
				CreatePreferencePanelHandlersWorkerFactory.class));
		install(new FactoryModuleBuilder().implement(
				PreferencePanelsHandler.class, PreferencePanelsHandler.class)
				.build(PreferencePanelsHandlerFactory.class));

	}

	@Provides
	@Named("childAnnotations")
	Collection<Class<? extends Annotation>> bindAnnotations() {
		Collection<Class<? extends Annotation>> annotations = new ArrayList<Class<? extends Annotation>>();
		annotations.add(Child.class);
		return annotations;
	}
}
