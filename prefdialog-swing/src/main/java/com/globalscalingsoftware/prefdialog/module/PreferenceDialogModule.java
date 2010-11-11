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

/**
 * Binds the default dependencies for the preference dialog.
 * 
 * 
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