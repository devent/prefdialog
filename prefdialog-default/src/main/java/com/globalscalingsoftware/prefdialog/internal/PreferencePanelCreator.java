package com.globalscalingsoftware.prefdialog.internal;

import java.lang.reflect.Field;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.IAnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.IApplyAction;
import com.globalscalingsoftware.prefdialog.IPreferencePanel;
import com.globalscalingsoftware.prefdialog.IPreferencePanelCreator;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.IRestoreAction;
import com.google.inject.Inject;

public class PreferencePanelCreator implements IPreferencePanelCreator {

	private final Action applyAction;

	private final Action restoreAction;

	private final IReflectionToolbox reflectionToolbox;

	private final IAnnotationDiscovery annotationDiscovery;

	@Inject
	PreferencePanelCreator(IAnnotationDiscovery annotationDiscovery,
			IReflectionToolbox reflectionToolbox, IApplyAction applyAction,
			IRestoreAction restoreAction) {
		this.annotationDiscovery = annotationDiscovery;
		this.reflectionToolbox = reflectionToolbox;
		this.applyAction = (Action) applyAction;
		this.restoreAction = (Action) restoreAction;
	}

	@Override
	public IPreferencePanel createPanel(Object parentValue, Field field) {
		IPreferencePanel panel = new PreferencePanel(reflectionToolbox);
		panel.setApplyAction(applyAction);
		panel.setRestoreAction(restoreAction);

		PreferencePanelController controller = new PreferencePanelController(
				annotationDiscovery, panel);
		controller.setField(parentValue, field);

		return panel;
	}
}
