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
package com.globalscalingsoftware.prefdialog.panel.internal.panel.inputfield.child;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunnableActionEvent implements ActionListener {

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
