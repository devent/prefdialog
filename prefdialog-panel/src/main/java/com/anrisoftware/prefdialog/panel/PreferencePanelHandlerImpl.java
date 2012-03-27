package com.anrisoftware.prefdialog.panel;

import java.awt.Component;

import javax.swing.SwingUtilities;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.PreferencePanelHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

class PreferencePanelHandlerImpl implements PreferencePanelHandler {

	private final Object preferences;

	private final String panelName;

	private final ChildFieldHandlerWorkerFactory childFieldHandlerWorkerFactory;

	private FieldHandler<?> childFieldHandler;

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
		Object value = childFieldHandler.getComponentValue();
		childFieldHandler.applyInput(value);
	}

	@Override
	public void restoreInput() {
		Object value = childFieldHandler.getComponentValue();
		childFieldHandler.restoreInput(value);
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
	public FieldHandler<?> getField(String name) {
		return childFieldHandler.getField(name);
	}

	@Override
	public void updateUI() {
		SwingUtilities.updateComponentTreeUI(getAWTComponent());
	}
}
