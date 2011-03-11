/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;


public class AbstractDelegatedCallbackAction implements DelegatedCallbackAction {

	private Action parentAction;

	private Runnable callback;

	protected AbstractDelegatedCallbackAction(String name) {
		this.parentAction = createEmptyAction(name);
		this.callback = createEmptyCallback();
	}

	@SuppressWarnings("serial")
	private Action createEmptyAction(String name) {
		return new AbstractAction(name) {

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		};
	}

	private Runnable createEmptyCallback() {
		return new Runnable() {

			@Override
			public void run() {
			}
		};
	}

	@Override
	public void setParentAction(Action parentAction) {
		this.parentAction = parentAction;
	}

	@Override
	public void setCallback(Runnable callback) {
		this.callback = callback;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		callback.run();
		parentAction.actionPerformed(e);
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