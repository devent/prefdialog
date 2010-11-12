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
package com.globalscalingsoftware.prefdialog.internal.dialog;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.globalscalingsoftware.prefdialog.Event;

class CallableTreeChildSelectedEvent implements TreeSelectionListener {

	private Event<Object> event;

	public CallableTreeChildSelectedEvent() {
		event = new Event<Object>() {

			@Override
			public void call(Object object) {
			}
		};
	}

	public void setEvent(Event<Object> event) {
		this.event = event;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		JTree tree = (JTree) e.getSource();
		Object pathComponent = tree.getLastSelectedPathComponent();
		if (pathComponent == null) {
			return;
		}

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) pathComponent;
		Object nodeInfo = node.getUserObject();
		event.call(nodeInfo);
	}

}
