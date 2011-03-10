package com.globalscalingsoftware.prefdialog.panel.internal;

import java.awt.Component;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.PreferencePanelHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class PreferencePanelImpl implements PreferencePanelHandler {

	private final Object preferences;

	@Inject
	PreferencePanelImpl(@Assisted Object preferences) {
		this.preferences = preferences;
	}

	@Override
	public void setApplyAction(Action a) {
	}

	@Override
	public void setRestoreAction(Action a) {
	}

	@Override
	public Component getAWTComponent() {
		return null;
	}

}
