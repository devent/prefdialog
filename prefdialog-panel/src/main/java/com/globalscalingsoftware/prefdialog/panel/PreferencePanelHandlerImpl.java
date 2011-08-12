package com.globalscalingsoftware.prefdialog.panel;

import java.awt.Component;

import com.globalscalingsoftware.prefdialog.PreferencePanelHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfields.child.ChildFieldHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

class PreferencePanelHandlerImpl implements PreferencePanelHandler {

	private final Object preferences;

	private final String panelName;

	private final ChildFieldHandlerWorkerFactory childFieldHandlerWorkerFactory;

	private ChildFieldHandler childFieldHandler;

	@Inject
	PreferencePanelHandlerImpl(
			ChildFieldHandlerWorkerFactory childFieldHandlerWorkerFactory,
			@Assisted Object preferences, @Assisted String panelName) {
		this.preferences = preferences;
		this.panelName = panelName;
		this.childFieldHandlerWorkerFactory = childFieldHandlerWorkerFactory;
		this.childFieldHandler = null;
	}

	@Override
	public PreferencePanelHandler createPanel() {
		childFieldHandler = childFieldHandlerWorkerFactory.create(preferences,
				panelName).getChildFieldHandler();
		return this;
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
	public boolean isInputValid() {
		return childFieldHandler.isInputValid();
	}

	@Override
	public void updateUI() {
		childFieldHandler.updateUI();
	}
}
