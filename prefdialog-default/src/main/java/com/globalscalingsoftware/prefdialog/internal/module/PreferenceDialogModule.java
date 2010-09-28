package com.globalscalingsoftware.prefdialog.internal.module;

import com.globalscalingsoftware.prefdialog.IAnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.IPreferenceDialog;
import com.globalscalingsoftware.prefdialog.IPreferenceDialogController;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.internal.AnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.internal.FieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.InputFieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.PreferenceDialog;
import com.globalscalingsoftware.prefdialog.internal.PreferenceDialogAnnotationsFilter;
import com.globalscalingsoftware.prefdialog.internal.PreferenceDialogController;
import com.globalscalingsoftware.prefdialog.internal.PreferencePanelAnnotationFilter;
import com.globalscalingsoftware.prefdialog.internal.ReflectionToolbox;
import com.google.inject.AbstractModule;

public class PreferenceDialogModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IAnnotationDiscovery.class).to(AnnotationDiscovery.class);
		bind(IReflectionToolbox.class).to(ReflectionToolbox.class);
		bindPreferencePanel();
		bindPreferenceDialog();
	}

	private void bindPreferenceDialog() {
		bind(PreferenceDialogAnnotationsFilter.class).asEagerSingleton();
		bind(IPreferenceDialogController.class).to(
				PreferenceDialogController.class);
		bind(IPreferenceDialog.class).to(PreferenceDialog.class);
	}

	private void bindPreferencePanel() {
		bind(PreferencePanelAnnotationFilter.class).asEagerSingleton();
		bind(FieldsFactory.class).asEagerSingleton();
		bind(InputFieldsFactory.class).asEagerSingleton();
	}
}
