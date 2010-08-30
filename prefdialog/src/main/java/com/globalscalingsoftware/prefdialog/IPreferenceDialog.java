package com.globalscalingsoftware.prefdialog;

import java.awt.Component;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public interface IPreferenceDialog {

	void setRootNode(DefaultMutableTreeNode root);

	void setChildSelected(Event<Object> childSelected);

	void setChildPanel(Component comp);

	void setSelectedChild(TreeNode[] path);

	void setOkEvent(Runnable okEvent);

	void setCancelEvent(Runnable cancelEvent);

	void close();

	void setOkAction(Action action);

	void setCancelAction(Action action);

	void open();

	void setOwner(JFrame owner);

}