package com.globalscalingsoftware.prefdialog.dialog.internal;

import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.globalscalingsoftware.prefdialog.PreferencePanelHandler;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory for {@link PreferencePanelsCollection}.
 */
interface PreferencePanelsCollectionFactory {

	/**
	 * Creates a new collection for {@link PreferencePanelHandler}.
	 * 
	 * @param panelHandlers
	 *            a map of the {@link PreferencePanelHandler}, assigned to the
	 *            preferences value for which the panel was created.
	 * @param firstPreferencePanelHandler
	 *            a reference to the first created
	 *            {@link PreferencePanelHandler}.
	 * @param treeNodes
	 *            a map of a path to the preference panels, assigned to the
	 *            preferences value for which the panel was created.
	 * @param rootNode
	 *            the {@link TreeNode} that is the root node for all the
	 *            {@link TreeNode tree nodes}.
	 * @return a new created {@link PreferencePanelsCollection}.
	 */
	PreferencePanelsCollection create(
			@Assisted("fieldHandlers") Map<Object, PreferencePanelHandler> panelHandlers,
			@Assisted PreferencePanelHandler firstPreferencePanelHandler,
			@Assisted("treeNodes") Map<Object, TreeNode[]> treeNodes,
			@Assisted DefaultMutableTreeNode rootNode);
}
