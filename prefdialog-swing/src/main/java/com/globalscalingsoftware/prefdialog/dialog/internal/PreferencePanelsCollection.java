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

import static java.lang.String.format;

import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.UnmodifiableMap;

import com.globalscalingsoftware.prefdialog.PreferencePanelHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Contains all {@link PreferencePanelHandler preference panels} in the dialog.
 * Also contains the {@link TreeNode tree nodes} to the preference panels.
 */
class PreferencePanelsCollection {

	private final Map<Object, PreferencePanelHandler> panelHandlers;

	private final Map<Object, TreeNode[]> treeNodes;

	private final DefaultMutableTreeNode rootNode;

	private final PreferencePanelHandler firstPreferencePanelHandler;

	@Inject
	PreferencePanelsCollection(
			@Assisted("fieldHandlers") Map<Object, PreferencePanelHandler> panelHandlers,
			@Assisted PreferencePanelHandler firstPreferencePanelHandler,
			@Assisted("treeNodes") Map<Object, TreeNode[]> treeNodes,
			@Assisted DefaultMutableTreeNode rootNode) {
		this.panelHandlers = panelHandlers;
		this.firstPreferencePanelHandler = firstPreferencePanelHandler;
		this.treeNodes = treeNodes;
		this.rootNode = rootNode;
	}

	/**
	 * Returns the {@link TreeNode} that is the root node for all the
	 * {@link TreeNode tree nodes}.
	 */
	public DefaultMutableTreeNode getRootNode() {
		return rootNode;
	}

	/**
	 * Returns a path to a specified preference value.
	 */
	public TreeNode[] getPath(Object value) {
		checkNotNull(value, "Cannot get a tree node for a null value.");
		TreeNode[] path = treeNodes.get(value);
		checkNotNull(path, format("The preference panels collection does not "
				+ "contain a path for the value %s.", value));
		return path;
	}

	/**
	 * Returns a {@link PreferencePanelHandler} to a specified preference value.
	 */
	public PreferencePanelHandler getPreferencePanelHandler(Object value) {
		checkNotNull(value,
				"Cannot get a preference panel handler for a null value.");
		PreferencePanelHandler handler = panelHandlers.get(value);
		checkNotNull(handler, format(
				"The preference panels collection does not "
						+ "contain an entry for the value %s.", value));
		return handler;
	}

	private void checkNotNull(Object value, String message) {
		if (value == null) {
			throw new NullPointerException(message);
		}
	}

	/**
	 * Returns all {@link PreferencePanelHandler} that this container have.
	 * 
	 * @return a {@link MapIterator} to iterate over the containing
	 *         {@link PreferencePanelHandler} and to which preferences value
	 *         they are assigned. The iterator is read only.
	 */
	public MapIterator getPreferencePanels() {
		UnmodifiableMap handlers = (UnmodifiableMap) UnmodifiableMap
				.decorate(panelHandlers);
		return handlers.mapIterator();
	}

	/**
	 * Returns the first {@link PreferencePanelHandler}.
	 */
	public PreferencePanelHandler getFirstPreferencePanelHandler() {
		return firstPreferencePanelHandler;
	}

	public boolean isInputValid() {
		MapIterator panels = getPreferencePanels();
		while (panels.hasNext()) {
			panels.next();
			PreferencePanelHandler handler = (PreferencePanelHandler) panels
					.getValue();
			if (!handler.isInputValid()) {
				return false;
			}
		}
		return true;
	}

}
