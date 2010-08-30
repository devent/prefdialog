package com.globalscalingsoftware.prefdialog.internal;

import java.awt.Component;

import javax.swing.AbstractAction;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
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

	private final IOkAction okAction;
	private final ICancelAction cancelAction;
	private final UiPreferencesDialog uiPreferencesDialog;
	private Event<Object> childSelected;
	private final RunnableActionEvent okEvent;
	private final RunnableActionEvent cancelEvent;

	@Inject
	PreferenceDialog(UiPreferencesDialog uiPreferencesDialog,
			IOkAction okAction, ICancelAction cancelAction) {
		this.uiPreferencesDialog = uiPreferencesDialog;
		this.okAction = okAction;
		this.cancelAction = cancelAction;
		okEvent = new RunnableActionEvent();
		cancelEvent = new RunnableActionEvent();
	}

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
		childTree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				if (childSelected == null) {
					return;
				}

				Object pathComponent = childTree.getLastSelectedPathComponent();
				if (pathComponent == null) {
					return;
				}

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) pathComponent;
				Object nodeInfo = node.getUserObject();
				childSelected.call(nodeInfo);

			}
		});

		uiPreferencesDialog.setModal(true);
		uiPreferencesDialog.pack();
		uiPreferencesDialog.setVisible(true);
	}

	public void setRootNode(DefaultMutableTreeNode root) {
		uiPreferencesDialog.getChildTree().setModel(new DefaultTreeModel(root));
	}

	public void setChildSelected(Event<Object> childSelected) {
		this.childSelected = childSelected;
	}

	public void setChildPanel(Component comp) {
		uiPreferencesDialog.getSplitPane().setRightComponent(comp);
		uiPreferencesDialog.pack();
	}

	public void setSelectedChild(TreeNode[] path) {
		TreePath treePath = new TreePath(path);
		uiPreferencesDialog.getChildTree().setSelectionPath(treePath);
		uiPreferencesDialog.getChildTree().scrollPathToVisible(treePath);
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
