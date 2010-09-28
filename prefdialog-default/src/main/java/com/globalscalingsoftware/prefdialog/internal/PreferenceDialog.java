package com.globalscalingsoftware.prefdialog.internal;

import java.awt.Component;
import java.awt.Frame;

import javax.swing.Action;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.globalscalingsoftware.prefdialog.Event;
import com.globalscalingsoftware.prefdialog.ICancelAction;
import com.globalscalingsoftware.prefdialog.IOkAction;
import com.google.inject.Inject;

public class PreferenceDialog {

	private UiPreferencesDialog uiPreferencesDialog;
	private final CallableTreeChildSelectedEvent childSelectedEvent;
	private final RunnableActionEvent okEvent;
	private final RunnableActionEvent cancelEvent;
	private final Action okAction;
	private final Action cancelAction;
	private DefaultMutableTreeNode rootNode;
	private Component childPanel;
	private TreePath selectedPath;

	@Inject
	PreferenceDialog(IOkAction okAction, ICancelAction cancelAction) {
		this.okAction = (Action) okAction;
		this.cancelAction = (Action) cancelAction;
		childSelectedEvent = new CallableTreeChildSelectedEvent();
		okEvent = new RunnableActionEvent();
		cancelEvent = new RunnableActionEvent();
	}

	public void open(Frame owner) {
		uiPreferencesDialog = new UiPreferencesDialog(owner);
		uiPreferencesDialog.getOkButton().setAction(okAction);
		uiPreferencesDialog.getOkButton().addActionListener(okEvent);
		uiPreferencesDialog.getCancelButton().setAction(cancelAction);
		uiPreferencesDialog.getCancelButton().addActionListener(cancelEvent);

		JTree childTree = uiPreferencesDialog.getChildTree();
		childTree.setModel(new DefaultTreeModel(rootNode));
		childTree.setRootVisible(false);
		childTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		childTree.addTreeSelectionListener(childSelectedEvent);
		childTree.setSelectionPath(selectedPath);
		childTree.scrollPathToVisible(selectedPath);

		setChildPanel(childPanel);

		uiPreferencesDialog.setLocationRelativeTo(owner);
		uiPreferencesDialog.setModal(true);
		uiPreferencesDialog.pack();
		uiPreferencesDialog.setVisible(true);
	}

	public void setRootNode(DefaultMutableTreeNode root) {
		this.rootNode = root;
	}

	public void setChildSelected(Event<Object> childSelected) {
		this.childSelectedEvent.setEvent(childSelected);
	}

	public void setChildPanel(Component comp) {
		this.childPanel = comp;
		if (uiPreferencesDialog != null) {
			uiPreferencesDialog.getSplitPane().setRightComponent(childPanel);
		}
	}

	public void setSelectedChild(TreeNode[] path) {
		this.selectedPath = new TreePath(path);
	}

	public void setOkEvent(Runnable okEvent) {
		this.okEvent.setEvent(okEvent);
	}

	public void setCancelEvent(Runnable cancelEvent) {
		this.cancelEvent.setEvent(cancelEvent);
	}

	public void close() {
		uiPreferencesDialog.setVisible(false);
	}

}
