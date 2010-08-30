package com.globalscalingsoftware.prefdialog.internal;

import com.globalscalingsoftware.prefdialog.IAnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.IPreferenceDialog;
import com.globalscalingsoftware.prefdialog.IPreferenceDialogController;
import com.globalscalingsoftware.prefdialog.IPreferencePanelCreator;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.google.inject.AbstractModule;

public class PreferenceDialogServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IPreferenceDialog.class).to(PreferenceDialog.class);
		bind(IPreferenceDialogController.class).to(
				PreferenceDialogController.class);
		bind(IAnnotationDiscovery.class).to(AnnotationDiscovery.class);
		bind(IPreferencePanelCreator.class).to(PreferencePanelCreator.class);
		bind(IReflectionToolbox.class).to(ReflectionToolbox.class);
	}

}
