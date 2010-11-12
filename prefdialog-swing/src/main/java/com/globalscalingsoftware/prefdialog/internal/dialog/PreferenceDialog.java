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
import com.google.inject.Inject;

public class PreferenceDialog {

	private UiPreferencesDialog uiPreferencesDialog;
	private final CallableTreeChildSelectedEvent childSelectedEvent;
	private final OkAction okAction;
	private final CancelAction cancelAction;
	private DefaultMutableTreeNode rootNode;
	private Component childPanel;
	private TreePath selectedPath;

	@Inject
	PreferenceDialog(OkAction okAction, CancelAction cancelAction) {
		this.okAction = okAction;
		this.cancelAction = cancelAction;
		childSelectedEvent = new CallableTreeChildSelectedEvent();
	}

	public void setup(Frame owner) {
		uiPreferencesDialog = new UiPreferencesDialog(owner);
		uiPreferencesDialog.getOkButton().setAction(okAction);
		uiPreferencesDialog.getCancelButton().setAction(cancelAction);

		JTree childTree = uiPreferencesDialog.getChildTree();
		childTree.setName("child_tree");
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
	}

	public void open() {
		uiPreferencesDialog.pack();
		uiPreferencesDialog.setVisible(true);
	}

	public UiPreferencesDialog getUiPreferencesDialog() {
		return uiPreferencesDialog;
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

	public void setOkAction(Action a) {
		this.okAction.setParentAction(a);
	}

	public void setCancelAction(Action a) {
		this.cancelAction.setParentAction(a);
	}

	public void close() {
		uiPreferencesDialog.setVisible(false);
	}

}
