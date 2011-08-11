package com.globalscalingsoftware.prefdialog.dialog.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;

abstract class AbstractDelegateAction implements Action {

	private Action delegate;

	private Runnable callback;

	protected AbstractDelegateAction(String name) {
		delegate = createEmptyAction(name);
		callback = new Runnable() {

			@Override
			public void run() {
			}
		};
	}

	@SuppressWarnings("serial")
	private Action createEmptyAction(String name) {
		return new AbstractAction(name) {

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		};
	}

	public void setCallback(Runnable callback) {
		this.callback = callback;
	}

	public void setDelegate(Action delegate) {
		this.delegate = delegate;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		callback.run();
		delegate.actionPerformed(e);
	}

	@Override
	public Object getValue(String key) {
		return delegate.getValue(key);
	}

	@Override
	public void putValue(String key, Object value) {
		delegate.putValue(key, value);
	}

	@Override
	public void setEnabled(boolean b) {
		delegate.setEnabled(b);
	}

	@Override
	public boolean isEnabled() {
		return delegate.isEnabled();
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		delegate.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		delegate.removePropertyChangeListener(listener);
	}

	@Override
	public boolean equals(Object obj) {
		return false;
	}

}
