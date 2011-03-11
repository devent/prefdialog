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

	public void setApplyParent(Action parentAction) {
		applyAction.setParentAction(parentAction);
	}

	public void setRestoreParent(Action parentAction) {
		applyAction.setParentAction(parentAction);
	}
}
