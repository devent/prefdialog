package com.globalscalingsoftware.prefdialog.internal;

import java.lang.reflect.Field;

import javax.swing.Action;
import javax.swing.JPanel;

import com.globalscalingsoftware.prefdialog.IApplyAction;
import com.globalscalingsoftware.prefdialog.IRestoreAction;
import com.google.inject.Inject;

public class PreferencePanelCreator {

	private final AnnotationDiscovery annotationDiscovery;

	private final AnnotationsFilter annotationsFilter;

	private final IApplyAction applyAction;

	private final IRestoreAction restoreAction;

	private final ReflectionToolbox reflectionToolbox;

	@Inject
	PreferencePanelCreator(AnnotationDiscovery annotationDiscovery,
			AnnotationsFilter annotationsFilter,
			ReflectionToolbox reflectionToolbox, IApplyAction applyAction,
			IRestoreAction restoreAction) {
		this.annotationDiscovery = annotationDiscovery;
		this.annotationsFilter = annotationsFilter;
		this.reflectionToolbox = reflectionToolbox;
		this.applyAction = applyAction;
		this.restoreAction = restoreAction;
	}

	public JPanel createPanel(Field field, Object value) {
		PreferencePanel panel = new PreferencePanel(annotationDiscovery,
				annotationsFilter, reflectionToolbox);
		panel.setApplyAction((Action) applyAction);
		panel.setRestoreAction((Action) restoreAction);
		return panel.getPanel(field, value);
	}
}
