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
package com.globalscalingsoftware.prefdialog.dialog.internal;

import java.awt.Component;
import java.awt.Frame;

import javax.swing.Action;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.internal.Nullable;

public class PreferenceDialog {

	public interface PreferenceDialogFactory {
		PreferenceDialog create(@Assisted @Nullable Frame owner,
				@Assisted DefaultMutableTreeNode rootNode);
	}

	private final UiPreferencesDialog uiPreferencesDialog;
	private Component childPanel;
	private final Frame owner;
	private final DefaultMutableTreeNode rootNode;
	private ChildSelectedCallback childSelectedCallback;

	@Inject
	PreferenceDialog(@Assisted @Nullable Frame owner,
			@Assisted DefaultMutableTreeNode rootNode) {
		this.uiPreferencesDialog = new UiPreferencesDialog(owner);
		this.owner = owner;
		this.rootNode = rootNode;
		setup();
	}

	private void setup() {
		setupChildTree();
		setChildPanel(childPanel);
		setupDialog();
	}

	private void setupDialog() {
		uiPreferencesDialog.setLocationRelativeTo(owner);
		uiPreferencesDialog.setModal(true);
	}

	private void setupChildTree() {
		JTree childTree = uiPreferencesDialog.getChildTree();
		childTree.setName("child_tree");
		childTree.setModel(new DefaultTreeModel(rootNode));
		childTree.setRootVisible(false);
		childTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		childTree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				JTree tree = (JTree) e.getSource();
				Object pathComponent = tree.getLastSelectedPathComponent();
				if (pathComponent == null) {
					return;
				}

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) pathComponent;
				Object nodeInfo = node.getUserObject();
				childSelectedCallback.call(nodeInfo);
			}
		});
	}

	public void open() {
		uiPreferencesDialog.pack();
		uiPreferencesDialog.setVisible(true);
	}

	public void close() {
		uiPreferencesDialog.setVisible(false);
	}

	public void setChildSelected(ChildSelectedCallback callback) {
		this.childSelectedCallback = callback;
	}

	public void setChildPanel(Component comp) {
		this.childPanel = comp;
		uiPreferencesDialog.getSplitPane().setRightComponent(childPanel);
	}

	public void setSelectedChild(TreeNode[] path) {
		TreePath selectedPath = new TreePath(path);
		JTree childTree = uiPreferencesDialog.getChildTree();
		childTree.setSelectionPath(selectedPath);
		childTree.scrollPathToVisible(selectedPath);
	}

	public void setOkAction(Action action) {
		uiPreferencesDialog.getOkButton().setAction(action);
	}

	public void setCancelAction(Action action) {
		uiPreferencesDialog.getCancelButton().setAction(action);
	}

	public Component getAWTComponent() {
		return uiPreferencesDialog;
	}

}