package com.globalscalingsoftware.prefdialog.internal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RunnableActionEvent implements ActionListener {

	private Runnable event;

	public RunnableActionEvent() {
		event = new Runnable() {

			@Override
			public void run() {
			}
		};
	}

	public void setEvent(Runnable event) {
		this.event = event;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		event.run();
	}

}
