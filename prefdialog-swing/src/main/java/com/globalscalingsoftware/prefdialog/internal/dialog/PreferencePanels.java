package com.globalscalingsoftware.prefdialog.internal.dialog;

import java.util.Collections;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.globalscalingsoftware.prefdialog.FieldHandler;

class PreferencePanels {

	private final Map<Object, FieldHandler<?>> fieldHandlers;

	private final Map<Object, TreeNode[]> treeNodes;

	private final DefaultMutableTreeNode rootNode;

	public PreferencePanels(Map<Object, FieldHandler<?>> fieldHandlers,
			Map<Object, TreeNode[]> treeNodes, DefaultMutableTreeNode rootNode) {
		this.fieldHandlers = fieldHandlers;
		this.treeNodes = treeNodes;
		this.rootNode = rootNode;
	}

	public DefaultMutableTreeNode getRootNode() {
		return rootNode;
	}

	public TreeNode[] getPath(Object value) {
		return treeNodes.get(value);
	}

	public FieldHandler<?> getFieldHandler(Object value) {
		return fieldHandlers.get(value);
	}

	public Map<Object, FieldHandler<?>> getPreferencePanels() {
		return Collections.unmodifiableMap(fieldHandlers);
	}

}