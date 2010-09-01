package com.globalscalingsoftware.prefdialog;

import java.awt.Component;

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

	void open();

}