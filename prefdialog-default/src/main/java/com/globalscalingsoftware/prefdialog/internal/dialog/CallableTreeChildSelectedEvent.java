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
