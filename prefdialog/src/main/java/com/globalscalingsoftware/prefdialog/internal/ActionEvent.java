package com.globalscalingsoftware.prefdialog.internal;

import java.awt.event.ActionListener;

class ActionEvent implements ActionListener {

	private Runnable event;

	public ActionEvent() {
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
	public void actionPerformed(java.awt.event.ActionEvent e) {
		event.run();
	}

}
