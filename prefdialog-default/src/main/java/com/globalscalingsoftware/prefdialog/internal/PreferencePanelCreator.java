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

	private IApplyAction applyAction;

	private IRestoreAction restoreAction;

	private final IReflectionToolbox reflectionToolbox;

	private final IAnnotationDiscovery annotationDiscovery;

	private final AnnotationsFilter annotationsFilter;

	@Inject
	PreferencePanelCreator(IAnnotationDiscovery annotationDiscovery,
			AnnotationsFilter annotationsFilter,
			IReflectionToolbox reflectionToolbox) {
		this.annotationDiscovery = annotationDiscovery;
		this.annotationsFilter = annotationsFilter;
		this.reflectionToolbox = reflectionToolbox;
	}

	@Override
	public void setApplyAction(IApplyAction applyAction) {
		this.applyAction = applyAction;
	}

	@Override
	public void setRestoreAction(IRestoreAction restoreAction) {
		this.restoreAction = restoreAction;
	}

	@Override
	public IPreferencePanel createPanel(Object parentValue, Field field) {
		IPreferencePanel panel = new PreferencePanel(reflectionToolbox);
		panel.setApplyAction((Action) applyAction);
		panel.setRestoreAction((Action) restoreAction);

		PreferencePanelController controller = new PreferencePanelController(
				annotationDiscovery, annotationsFilter, panel);
		controller.setField(parentValue, field);

		return panel;
	}
}
