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

	@Inject
	PreferencePanelCreator(ReflectionToolbox reflectionToolbox,
			IApplyAction applyAction, IRestoreAction restoreAction) {
		this.reflectionToolbox = reflectionToolbox;
		this.applyAction = applyAction;
		this.restoreAction = restoreAction;
	}

	public PreferencePanel createPanel(Object parentValue, Field field) {
		PreferencePanel panel = new PreferencePanel(reflectionToolbox);
		panel.setApplyAction((Action) applyAction);
		panel.setRestoreAction((Action) restoreAction);
		return panel;
	}
}
