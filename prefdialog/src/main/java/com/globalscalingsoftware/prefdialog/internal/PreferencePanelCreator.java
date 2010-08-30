package com.globalscalingsoftware.prefdialog.internal;

import java.lang.reflect.Field;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.IApplyAction;
import com.globalscalingsoftware.prefdialog.IRestoreAction;
import com.google.inject.Inject;

public class PreferencePanelCreator {

	private final IApplyAction applyAction;

	private final IRestoreAction restoreAction;

	private final ReflectionToolbox reflectionToolbox;

	private final AnnotationDiscovery annotationDiscovery;

	private final AnnotationsFilter annotationsFilter;

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

	public PreferencePanel createPanel(Object parentValue, Field field) {
		PreferencePanel panel = new PreferencePanel(reflectionToolbox);
		panel.setApplyAction((Action) applyAction);
		panel.setRestoreAction((Action) restoreAction);

		PreferencePanelController controller = new PreferencePanelController(
				annotationDiscovery, annotationsFilter, panel);
		controller.setField(parentValue, field);

		return panel;
	}
}
