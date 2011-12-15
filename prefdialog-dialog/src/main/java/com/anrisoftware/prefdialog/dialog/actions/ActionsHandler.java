package com.anrisoftware.prefdialog.dialog.actions;

import java.util.HashMap;

import javax.swing.Action;

import com.google.common.collect.Maps;
import com.google.inject.Inject;

public class ActionsHandler {

	public static final String APPLY_ACTION_ID = "apply_action_id";

	public static final String CANCEL_ACTION_ID = "cancel_action_id";

	public static final String OK_ACTION_ID = "ok_action_id";

	private final HashMap<String, AbstractDelegateAction> actions;

	@Inject
	ActionsHandler(OkAction okAction, CancelAction cancelAction,
			ApplyAction applyAction) {
		this.actions = Maps.newHashMap();
		actions.put(OK_ACTION_ID, okAction);
		actions.put(CANCEL_ACTION_ID, cancelAction);
		actions.put(APPLY_ACTION_ID, applyAction);
	}

	public Action getAction(String name) {
		return actions.get(name);
	}

	public void setDelegate(String name, Action delegate) {
		actions.get(name).setDelegate(delegate);
	}

	public void setCallback(String name, Runnable callback) {
		actions.get(name).setCallback(callback);
	}
}
