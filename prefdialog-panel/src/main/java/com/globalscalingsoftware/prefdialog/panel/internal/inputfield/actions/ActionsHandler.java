package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.actions;

import javax.swing.Action;

import com.google.inject.Inject;

public class ActionsHandler {

	private final ApplyAction applyAction;

	private final RestoreAction restoreAction;

	@Inject
	ActionsHandler(ApplyAction applyAction, RestoreAction restoreAction) {
		this.applyAction = applyAction;
		this.restoreAction = restoreAction;
	}

	public Action getApplyAction() {
		return applyAction;
	}

	public Action getRestoreAction() {
		return restoreAction;
	}

	public void setApplyCallback(Runnable callback) {
		applyAction.setCallback(callback);
	}

	public void setRestoreCallback(Runnable callback) {
		restoreAction.setCallback(callback);
	}
}
