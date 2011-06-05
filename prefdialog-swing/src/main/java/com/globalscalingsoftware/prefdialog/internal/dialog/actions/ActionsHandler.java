package com.globalscalingsoftware.prefdialog.internal.dialog.actions;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.internal.inputfield.actions.ApplyAction;
import com.globalscalingsoftware.prefdialog.internal.inputfield.actions.RestoreAction;
import com.google.inject.Inject;

public class ActionsHandler {

	private final OkAction okAction;
	private final CancelAction cancelAction;
	private final ApplyAction applyAction;
	private final RestoreAction restoreAction;

	@Inject
	ActionsHandler(OkAction okAction, CancelAction cancelAction,
			ApplyAction applyAction, RestoreAction restoreAction) {
		this.okAction = okAction;
		this.cancelAction = cancelAction;
		this.applyAction = applyAction;
		this.restoreAction = restoreAction;
	}

	public Action getOkAction() {
		return okAction;
	}

	public void setOkCallback(Runnable callback) {
		okAction.setCallback(callback);
	}

	public void setOkParentAction(Action action) {
		okAction.setParentAction(action);
	}

	public Action getCancelAction() {
		return cancelAction;
	}

	public void setCancelCallback(Runnable callback) {
		cancelAction.setCallback(callback);
	}

	public void setCancelParentAction(Action action) {
		cancelAction.setParentAction(action);
	}

	public Action getApplyAction() {
		return applyAction;
	}

	public void setApplyCallback(Runnable callback) {
		applyAction.setCallback(callback);
	}

	public void setApplyParentAction(Action action) {
		applyAction.setParentAction(action);
	}

	public Action getRestoreAction() {
		return restoreAction;
	}

	public void setRestoreCallback(Runnable callback) {
		restoreAction.setCallback(callback);
	}

	public void setRestoreParentAction(Action action) {
		restoreAction.setParentAction(action);
	}

}
