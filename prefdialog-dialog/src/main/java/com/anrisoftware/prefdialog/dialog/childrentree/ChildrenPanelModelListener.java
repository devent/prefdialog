package com.anrisoftware.prefdialog.dialog.childrentree;

import java.util.Map;

import javax.inject.Inject;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import com.google.common.collect.Maps;

/**
 * Updates the tree model according to the children model.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ChildrenPanelModelListener implements ListDataListener {

	private final MutableTreeNode rootNode;

	private final Map<Object, TreeNode[]> childrenNodes;

	private DefaultTreeModel treeModel;

	/**
	 * Sets the tree model that is updated if the children model was changed.
	 * 
	 */
	@Inject
	ChildrenPanelModelListener() {
		this.rootNode = new DefaultMutableTreeNode();
		this.childrenNodes = Maps.newHashMap();
	}

	@Override
	public void intervalAdded(ListDataEvent e) {
		int begin = e.getIndex0();
		int end = e.getIndex1();
		ListModel model = (ListModel) e.getSource();
		insert(begin, end, model);
		treeModel.reload(rootNode);
	}

	private void insert(int begin, int end, ListModel model) {
		int i = begin;
		do {
			Object child = model.getElementAt(i);
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(child);
			rootNode.insert(node, i);
			TreeNode[] path = node.getPath();
			childrenNodes.put(node.getUserObject(), path);
		} while (++i < end);
	}

	@Override
	public void intervalRemoved(ListDataEvent e) {
		int begin = e.getIndex0();
		int end = e.getIndex1();
		remove(begin, end);
		treeModel.reload(rootNode);
	}

	private void remove(int begin, int end) {
		int i = begin;
		do {
			rootNode.remove(i);
		} while (++i < end);
	}

	@Override
	public void contentsChanged(ListDataEvent e) {
		int begin = e.getIndex0();
		int end = e.getIndex1();
		remove(begin, end);
		ListModel model = (ListModel) e.getSource();
		insert(begin, end, model);
		treeModel.reload(rootNode);
	}

	/**
	 * Returns the root node that contains the children nodes.
	 * 
	 * @return the {@link TreeNode} that is the root node.
	 */
	public TreeNode getRootNode() {
		return rootNode;
	}

	/**
	 * Returns the tree path to a child object.
	 * 
	 * @param child
	 *            the child {@link Object}.
	 * 
	 * @return an array of {@link TreeNode}s that is the tree path to the node
	 *         containing the child object.
	 */
	public TreeNode[] getChildPath(Object child) {
		return childrenNodes.get(child);
	}

	/**
	 * Sets a new tree model.
	 * 
	 * @param model
	 *            the new {@link DefaultTreeModel}.
	 */
	public void setModel(DefaultTreeModel model) {
		this.treeModel = model;
	}

	/**
	 * Remove old children from the tree model and adds new children.
	 * 
	 * @param model
	 *            the {@link ListModel} that contains the children.
	 */
	public void updateChildren(ListModel model) {
		int begin = 0;
		int end = model.getSize();
		insert(begin, end, model);
		treeModel.reload(rootNode);
	}
}
