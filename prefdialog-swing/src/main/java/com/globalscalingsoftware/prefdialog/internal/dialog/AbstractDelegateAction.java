package com.globalscalingsoftware.prefdialog.internal.dialog;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;

public abstract class AbstractDelegateAction implements Action {

	private Action parentAction;

	@SuppressWarnings("serial")
	public AbstractDelegateAction(String name) {
		parentAction = new AbstractAction(name) {

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		};
	}

	public void setParentAction(Action parentAction) {
		this.parentAction = parentAction;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		parentAction.actionPerformed(e);
		customActionPerformed(e);
	}

	protected abstract void customActionPerformed(ActionEvent e);

	@Override
	public Object getValue(String key) {
		return parentAction.getValue(key);
	}

	@Override
	public void putValue(String key, Object value) {
		parentAction.putValue(key, value);
	}

	@Override
	public void setEnabled(boolean b) {
		parentAction.setEnabled(b);
	}

	@Override
	public boolean isEnabled() {
		return parentAction.isEnabled();
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		parentAction.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		parentAction.removePropertyChangeListener(listener);
	}

}