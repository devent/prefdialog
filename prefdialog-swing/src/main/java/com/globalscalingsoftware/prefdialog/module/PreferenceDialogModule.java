package com.globalscalingsoftware.prefdialog.module;

import com.globalscalingsoftware.prefdialog.PreferenceDialogController;
import com.globalscalingsoftware.prefdialog.internal.dialog.InputFieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.dialog.PreferenceDialog;
import com.globalscalingsoftware.prefdialog.internal.dialog.PreferenceDialogAnnotationsFilter;
import com.globalscalingsoftware.prefdialog.internal.dialog.PreferenceDialogControllerImpl;
import com.globalscalingsoftware.prefdialog.internal.dialog.PreferenceDialogControllerInternal;
import com.globalscalingsoftware.prefdialog.internal.inputfield.FieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.reflection.AnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.internal.reflection.FieldsAnnotationFilter;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;

/**
 * Binds the default dependencies for the preference dialog.
 * 
 * We can use the module to create a new instance of
 * {@link PreferenceDialogController} with the help of an {@link Injector}. As
 * in the example:
 * 
 * <pre>
 * injector = Guice.createInjector(new PreferenceDialogModule());
 * controller = injector.getInstance(PreferenceDialogController.class);
 * controller.setPreferences(preferences);
 * controller.setPreferencesStart(preferences_start);
 * controller.setup(owner);
 * controller.openDialog();
 * if (controller.getOption() == OK) {
 *     compute preferences
 * }
 * </pre>
 * 
 * We can use Guice to inject the preferences:
 * 
 * <pre>
 * In preferencesModule:
 * binding.bind(Object.class).annotatedWith(Names.named("preferences")).toInstance(preferences);
 * binding.bind(Object.class).annotatedWith(Names.named("preferences_start")).toInstance(preferences.general);
 * 
 * injector = Guice
 * 		.createInjector(new PreferenceDialogModule(), preferencesModule);
 * controller = injector.getInstance(PreferenceDialogController.class);
 * controller.setup(owner);
 * controller.openDialog();
 * if (controller.getOption() == OK) {
 *     compute preferences
 * }
 * </pre>
 */
public class PreferenceDialogModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(AnnotationDiscovery.class).asEagerSingleton();
		bind(ReflectionToolbox.class).asEagerSingleton();
		bindPreferencePanel();
		bindPreferenceDialog();
	}

	private void bindPreferenceDialog() {
		bind(PreferenceDialogAnnotationsFilter.class).asEagerSingleton();
		bind(PreferenceDialogController.class).to(
				PreferenceDialogControllerImpl.class);
		bind(PreferenceDialogControllerInternal.class).to(
				PreferenceDialogControllerImpl.class);
		bind(PreferenceDialog.class);
	}

	private void bindPreferencePanel() {
		bind(FieldsAnnotationFilter.class).asEagerSingleton();
		bind(FieldsFactory.class).asEagerSingleton();
		bind(InputFieldsFactory.class).asEagerSingleton();
	}
}
