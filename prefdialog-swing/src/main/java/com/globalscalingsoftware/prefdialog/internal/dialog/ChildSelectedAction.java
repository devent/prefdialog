package com.globalscalingsoftware.prefdialog.internal.dialog;

import com.globalscalingsoftware.prefdialog.Event;

class ChildSelectedAction implements Event<Object> {

	private final PreferenceDialogControllerImpl controller;

	public ChildSelectedAction(PreferenceDialogControllerImpl controller) {
		this.controller = controller;
	}

	@Override
	public void call(Object object) {
		controller.setChildPanel(object);
	}
}
