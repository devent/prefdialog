package com.globalscalingsoftware.prefdialog.dialog.internal.actions;

import javax.swing.Action;

import com.google.inject.Inject;

public class ActionsHandler {

	private final OkAction okAction;

	private final CancelAction cancelAction;

	@Inject
	ActionsHandler(OkAction okAction, CancelAction cancelAction) {
		this.okAction = okAction;
		this.cancelAction = cancelAction;
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

	public void setOkActionEnabled(boolean b) {
		okAction.setEnabled(b);
	}

}
