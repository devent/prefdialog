package com.globalscalingsoftware.prefdialog.panel.internal;

import java.awt.Component;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.InputChangedCallback;
import com.globalscalingsoftware.prefdialog.PreferencePanelHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.ChildFieldHandlerWorker.ChildFieldHandlerWorkerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.actions.ActionsHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child.ChildFieldHandler;
import com.globalscalingsoftware.prefdialog.swingutils.actions.internal.InputChangedDelegateCallback;
import com.globalscalingsoftware.prefdialog.swingutils.actions.internal.InputChangedDelegateCallback.InputChangedDelegateCallbackFactory;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

class PreferencePanelHandlerImpl implements PreferencePanelHandler {

	private final Object preferences;

	private final ActionsHandler actionsHandler;

	private final String panelName;

	private final InputChangedDelegateCallback inputChangedDelegateCallback;

	private final ChildFieldHandlerWorkerFactory childFieldHandlerWorkerFactory;

	private ChildFieldHandler childFieldHandler;

	@Inject
	PreferencePanelHandlerImpl(
			ActionsHandler actionsHandler,
			InputChangedDelegateCallbackFactory inputChangedDelegateCallbackFactory,
			ChildFieldHandlerWorkerFactory childFieldHandlerWorkerFactory,
			@Assisted Object preferences, @Assisted String panelName) {
		this.actionsHandler = actionsHandler;
		this.preferences = preferences;
		this.panelName = panelName;
		this.childFieldHandlerWorkerFactory = childFieldHandlerWorkerFactory;
		this.inputChangedDelegateCallback = inputChangedDelegateCallbackFactory
				.create();
		this.childFieldHandler = null;
	}

	@Override
	public PreferencePanelHandler createPanel() {
		Action applyAction = actionsHandler.getApplyAction();
		Action restoreAction = actionsHandler.getRestoreAction();
		childFieldHandler = childFieldHandlerWorkerFactory.create(preferences,
				panelName, applyAction, restoreAction,
				inputChangedDelegateCallback).getChildFieldHandler();
		return this;
	}

	@Override
	public void setApplyAction(Action a) {
		actionsHandler.setApplyParent(a);
		childFieldHandler.setApplyAction(actionsHandler.getApplyAction());
	}

	@Override
	public void setRestoreAction(Action a) {
		actionsHandler.setRestoreParent(a);
		childFieldHandler.setRestoreAction(actionsHandler.getRestoreAction());
	}

	@Override
	public Component getAWTComponent() {
		return childFieldHandler.getAWTComponent();
	}

	@Override
	public void applyInput() {
		childFieldHandler.applyInput();
	}

	@Override
	public void restoreInput() {
		childFieldHandler.restoreInput();
	}

	@Override
	public Object getPreferences() {
		return childFieldHandler.getComponentValue();
	}

	@Override
	public void setInputChangedCallback(InputChangedCallback callback) {
		inputChangedDelegateCallback.setDelegateCallback(callback);
	}

	@Override
	public boolean isInputValid() {
		return childFieldHandler.isInputValid();
	}
}
