package com.globalscalingsoftware.prefdialog.internal;

import java.awt.Component;

import javax.swing.AbstractAction;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.globalscalingsoftware.prefdialog.Event;
import com.globalscalingsoftware.prefdialog.ICancelAction;
import com.globalscalingsoftware.prefdialog.IOkAction;
import com.globalscalingsoftware.prefdialog.IPreferenceDialog;
import com.google.inject.Inject;

public class PreferenceDialog implements IPreferenceDialog {

	private final IOkAction okAction;
	private final ICancelAction cancelAction;
	private final UiPreferencesDialog uiPreferencesDialog;
	private final CallableTreeChildSelectedEvent childSelectedEvent;
	private final RunnableActionEvent okEvent;
	private final RunnableActionEvent cancelEvent;

	@Inject
	PreferenceDialog(UiPreferencesDialog uiPreferencesDialog,
			IOkAction okAction, ICancelAction cancelAction) {
		this.uiPreferencesDialog = uiPreferencesDialog;
		this.okAction = okAction;
		this.cancelAction = cancelAction;
		childSelectedEvent = new CallableTreeChildSelectedEvent();
		okEvent = new RunnableActionEvent();
		cancelEvent = new RunnableActionEvent();
	}

	@Override
	public void open() {
		uiPreferencesDialog.getOkButton().setAction((AbstractAction) okAction);
		uiPreferencesDialog.getOkButton().addActionListener(okEvent);

		uiPreferencesDialog.getCancelButton().setAction(
				(AbstractAction) cancelAction);
		uiPreferencesDialog.getCancelButton().addActionListener(cancelEvent);

		final JTree childTree = uiPreferencesDialog.getChildTree();
		childTree.setRootVisible(false);
		childTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		childTree.addTreeSelectionListener(childSelectedEvent);

		uiPreferencesDialog.setModal(true);
		uiPreferencesDialog.pack();
		uiPreferencesDialog.setVisible(true);
	}

	@Override
	public void setRootNode(DefaultMutableTreeNode root) {
		uiPreferencesDialog.getChildTree().setModel(new DefaultTreeModel(root));
	}

	@Override
	public void setChildSelected(Event<Object> childSelected) {
		this.childSelectedEvent.setEvent(childSelected);
	}

	@Override
	public void setChildPanel(Component comp) {
		uiPreferencesDialog.getSplitPane().setRightComponent(comp);
		uiPreferencesDialog.pack();
	}

	@Override
	public void setSelectedChild(TreeNode[] path) {
		TreePath treePath = new TreePath(path);
		uiPreferencesDialog.getChildTree().setSelectionPath(treePath);
		uiPreferencesDialog.getChildTree().scrollPathToVisible(treePath);
	}

	@Override
	public void setOkEvent(Runnable okEvent) {
		this.okEvent.setEvent(okEvent);
	}

	@Override
	public void setCancelEvent(Runnable cancelEvent) {
		this.cancelEvent.setEvent(cancelEvent);
	}

	@Override
	public void close() {
		uiPreferencesDialog.setVisible(false);
	}
}
