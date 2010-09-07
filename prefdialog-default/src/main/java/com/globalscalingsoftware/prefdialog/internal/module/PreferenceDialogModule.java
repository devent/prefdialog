package com.globalscalingsoftware.prefdialog.internal.module;

import static com.google.inject.assistedinject.FactoryProvider.newFactory;

import com.globalscalingsoftware.prefdialog.IAnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.IAnnotationFilter;
import com.globalscalingsoftware.prefdialog.IFormattedTextFieldFactory;
import com.globalscalingsoftware.prefdialog.IPreferenceDialog;
import com.globalscalingsoftware.prefdialog.IPreferenceDialogController;
import com.globalscalingsoftware.prefdialog.IPreferencePanel;
import com.globalscalingsoftware.prefdialog.IPreferencePanelFactory;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.internal.AnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.internal.AnnotationsFilter;
import com.globalscalingsoftware.prefdialog.internal.PreferenceDialog;
import com.globalscalingsoftware.prefdialog.internal.PreferenceDialogController;
import com.globalscalingsoftware.prefdialog.internal.PreferencePanel;
import com.globalscalingsoftware.prefdialog.internal.PreferencePanelController;
import com.globalscalingsoftware.prefdialog.internal.ReflectionToolbox;
import com.globalscalingsoftware.prefdialog.internal.formattedtextfield.FormattedTextField;
import com.google.inject.AbstractModule;

public class PreferenceDialogModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IAnnotationDiscovery.class).to(AnnotationDiscovery.class);
		bind(IAnnotationFilter.class).to(AnnotationsFilter.class);
		bind(IReflectionToolbox.class).to(ReflectionToolbox.class);
		bindPreferencePanel();
		bindPreferenceDialog();
	}

	private void bindPreferenceDialog() {
		bind(IPreferenceDialogController.class).to(
				PreferenceDialogController.class);
		bind(IPreferenceDialog.class).to(PreferenceDialog.class);
	}

	private void bindPreferencePanel() {
		bind(IPreferencePanelFactory.class).toProvider(
				newFactory(IPreferencePanelFactory.class,
						PreferencePanelController.class));
		bind(IPreferencePanel.class).to(PreferencePanel.class);
		bind(IFormattedTextFieldFactory.class).toProvider(
				newFactory(IFormattedTextFieldFactory.class,
						FormattedTextField.class));
	}
}
