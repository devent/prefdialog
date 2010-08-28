package com.globalscalingsoftware.prefdialog.internal;

import java.awt.Component;

import javax.swing.AbstractAction;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.globalscalingsoftware.prefdialog.Event;
import com.globalscalingsoftware.prefdialog.ICancelAction;
import com.globalscalingsoftware.prefdialog.IOkAction;
import com.globalscalingsoftware.prefdialog.IRestoreAction;
import com.google.inject.Inject;

public class PreferenceDialog {

	private final IOkAction okAction;
	private final IRestoreAction restoreAction;
	private final ICancelAction cancelAction;
	private final UiPreferencesDialog uiPreferencesDialog;
	private Event<Object> childSelected;

	@Inject
	PreferenceDialog(UiPreferencesDialog uiPreferencesDialog,
			IOkAction okAction, IRestoreAction restoreAction,
			ICancelAction cancelAction) {
		this.uiPreferencesDialog = uiPreferencesDialog;
		this.okAction = okAction;
		this.restoreAction = restoreAction;
		this.cancelAction = cancelAction;
	}

	public void open() {
		uiPreferencesDialog.getOkButton().setAction((AbstractAction) okAction);
		uiPreferencesDialog.getRestoreButton().setAction(
				(AbstractAction) restoreAction);
		uiPreferencesDialog.getCancelButton().setAction(
				(AbstractAction) cancelAction);

		uiPreferencesDialog.getPreferencesTree().setRootVisible(false);
		uiPreferencesDialog.getPreferencesTree().getSelectionModel()
				.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		uiPreferencesDialog.getPreferencesTree().addTreeSelectionListener(
				new TreeSelectionListener() {

					@Override
					public void valueChanged(TreeSelectionEvent e) {
						if (childSelected == null) {
							return;
						}

						Object pathComponent = uiPreferencesDialog
								.getPreferencesTree()
								.getLastSelectedPathComponent();
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
		uiPreferencesDialog.getPreferencesTree().setModel(
				new DefaultTreeModel(root));
	}

	public void setChildSelected(Event<Object> childSelected) {
		this.childSelected = childSelected;
	}

	public void setChildPanel(Component comp) {
		uiPreferencesDialog.getMainSplitPane().setRightComponent(comp);
	}
}
