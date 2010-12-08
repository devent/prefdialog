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

import java.util.Collections;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.globalscalingsoftware.prefdialog.internal.inputfield.child.ChildFieldHandler;

public class PreferencePanels {

	private final Map<Object, ChildFieldHandler> fieldHandlers;

	private final Map<Object, TreeNode[]> treeNodes;

	private final DefaultMutableTreeNode rootNode;

	private final ChildFieldHandler preferencesStart;

	public PreferencePanels(Map<Object, ChildFieldHandler> fieldHandlers,
			Map<Object, TreeNode[]> treeNodes, DefaultMutableTreeNode rootNode,
			ChildFieldHandler preferencesStart) {
		this.fieldHandlers = fieldHandlers;
		this.treeNodes = treeNodes;
		this.rootNode = rootNode;
		this.preferencesStart = preferencesStart;
	}

	public DefaultMutableTreeNode getRootNode() {
		return rootNode;
	}

	public TreeNode[] getPath(Object value) {
		return treeNodes.get(value);
	}

	public ChildFieldHandler getFieldHandler(Object value) {
		return fieldHandlers.get(value);
	}

	public Map<Object, ChildFieldHandler> getPreferencePanels() {
		return Collections.unmodifiableMap(fieldHandlers);
	}

	public ChildFieldHandler getPreferencesStart() {
		return preferencesStart;
	}

}
