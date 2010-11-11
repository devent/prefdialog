package com.globalscalingsoftware.prefdialog.internal.dialog;

import java.beans.PropertyChangeListener;

import javax.swing.Action;

public abstract class AbstractDelegateAction implements Action {

	protected Action parentAction;

	public AbstractDelegateAction() {
		super();
	}

	public void setParentAction(Action parentAction) {
		this.parentAction = parentAction;
	}

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