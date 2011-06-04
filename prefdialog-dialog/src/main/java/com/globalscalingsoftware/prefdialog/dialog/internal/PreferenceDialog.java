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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.Nullable;
import javax.swing.Action;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Creates and manages the UI of the preference dialog. Use
 * {@link PreferenceDialogFactory} to create a new {@link PreferenceDialog}.
 * 
 * @see PreferenceDialogFactory
 */
class PreferenceDialog {

	/**
	 * Use this factory to create a new {@link PreferenceDialog}.
	 */
	interface PreferenceDialogFactory {

		/**
		 * Creates a new {@link PreferenceDialog}.
		 * 
		 * @param owner
		 *            the {@link Frame owner} of the preference dialog, can be
		 *            <code>null</code>.
		 * @param rootNode
		 *            the {@link DefaultMutableTreeNode root tree node} that
		 *            contains all panels.
		 * @return the new created {@link PreferenceDialog}.
		 */
		PreferenceDialog create(@Assisted @Nullable Frame owner,
				@Assisted DefaultMutableTreeNode rootNode);
	}

	private static final Runnable EMPTY_CALLBACK = new Runnable() {

		@Override
		public void run() {
		}
	};

	private final UiPreferencesDialog uiPreferencesDialog;

	private final Frame owner;

	private final DefaultMutableTreeNode rootNode;

	private Component childPanel;

	private ChildSelectedCallback childSelectedCallback;

	private Runnable okCallback;

	private Runnable cancelCallback;

	private Runnable applyCallback;

	@Inject
	PreferenceDialog(@Assisted @Nullable Frame owner,
			@Assisted DefaultMutableTreeNode rootNode) {
		this.uiPreferencesDialog = new UiPreferencesDialog(owner);
		this.owner = owner;
		this.rootNode = rootNode;
		this.okCallback = EMPTY_CALLBACK;
		this.cancelCallback = EMPTY_CALLBACK;
		this.applyCallback = EMPTY_CALLBACK;
	}

	/**
	 * Creates the UI of the dialog.
	 * 
	 * @return this {@link PreferenceDialog dialog} with created UI.
	 */
	public PreferenceDialog createDialog() {
		setupChildTree();
		setChildPanel(childPanel);
		setupButtons();
		setupDialog();
		return this;
	}

	private void setupButtons() {
		uiPreferencesDialog.getOkButton().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						okCallback.run();
					}
				});
		uiPreferencesDialog.getCancelButton().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						cancelCallback.run();
					}
				});
		uiPreferencesDialog.getApplyButton().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						applyCallback.run();
					}
				});
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

	public void setApplyAction(Action action) {
		uiPreferencesDialog.getApplyButton().setAction(action);
	}

	public void setOkCallback(Runnable callback) {
		okCallback = callback;
	}

	public void setCancelCallback(Runnable callback) {
		cancelCallback = callback;
	}

	public void setApplyCallback(Runnable callback) {
		applyCallback = callback;
	}

	public Component getAWTComponent() {
		return uiPreferencesDialog;
	}

	public void setTitle(String title) {
		uiPreferencesDialog.setTitle(title);
	}

	public void updateUI() {
		SwingUtilities.updateComponentTreeUI(uiPreferencesDialog);
	}

}
