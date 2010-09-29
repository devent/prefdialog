package com.globalscalingsoftware.prefdialog.internal.dialog;

import com.globalscalingsoftware.prefdialog.Event;

public class ChildSelectedAction implements Event<Object> {

	private final PreferenceDialogController controller;

	public ChildSelectedAction(PreferenceDialogController controller) {
		this.controller = controller;
	}

	@Override
	public void call(Object object) {
		controller.setChildPanel(object);
	}
}
