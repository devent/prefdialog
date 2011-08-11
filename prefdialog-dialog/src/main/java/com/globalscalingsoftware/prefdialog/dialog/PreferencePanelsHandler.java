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
package com.globalscalingsoftware.prefdialog.dialog;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.apache.commons.collections.MapIterator;

import com.globalscalingsoftware.prefdialog.PreferencePanelHandler;
import com.globalscalingsoftware.prefdialog.dialog.CreatePreferencePanelHandlersWorker.CreatePreferencePanelHandlersWorkerFactory;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Create the {@link PreferencePanelHandler preference panel handlers} from the
 * preferences. Use {@link PreferencePanelsHandlerFactory} to create a new
 * preference panels handler.
 * 
 * @see PreferencePanelsHandlerFactory
 */
class PreferencePanelsHandler {

	/**
	 * Use this factory to create a {@link PreferencePanelsHandler}.
	 */
	interface PreferencePanelsHandlerFactory {

		/**
		 * Created a new {@link PreferencePanelsHandler}.
		 * 
		 * @param preferences
		 *            the preferences object from which the handler will create
		 *            all preference panels.
		 * @return the new created {@link PreferencePanelsHandler}.
		 */
		PreferencePanelsHandler create(@Assisted Object preferences);
	}

	private final CreatePreferencePanelHandlersWorkerFactory createPreferencePanelHandlersWorkerFactory;

	private final Object preferences;

	private PreferencePanelsCollection preferencePanelsCollection;

	private PreferencePanelHandler firstPreferencePanelHandler;

	@Inject
	PreferencePanelsHandler(
			CreatePreferencePanelHandlersWorkerFactory createPreferencePanelHandlersWorkerFactory,
			@Assisted Object preferences) {
		this.createPreferencePanelHandlersWorkerFactory = createPreferencePanelHandlersWorkerFactory;
		this.preferences = preferences;
	}

	/**
	 * Create the {@link PreferencePanelHandler preference panel handlers} from
	 * the preferences object.
	 * 
	 * @return this {@link PreferencePanelsHandler handler}.
	 */
	public PreferencePanelsHandler createPanels() {
		CreatePreferencePanelHandlersWorker createPreferencePanelHandlersWorker = createPreferencePanelHandlersWorkerFactory
				.create(preferences).createWorker();
		firstPreferencePanelHandler = createPreferencePanelHandlersWorker
				.getFirstPreferencePanelHandler();
		preferencePanelsCollection = createPreferencePanelHandlersWorker
				.getPreferencePanelsCollection();
		return this;
	}

	public DefaultMutableTreeNode getRootNode() {
		return preferencePanelsCollection.getRootNode();
	}

	public PreferencePanelHandler getFirstPreferencePanelHandler() {
		return firstPreferencePanelHandler;
	}

	public MapIterator getPreferencePanels() {
		return preferencePanelsCollection.getPreferencePanels();
	}

	public TreeNode[] getPath(Object value) {
		return preferencePanelsCollection.getPath(value);
	}

	public PreferencePanelHandler getPreferencePanelHandler(Object value) {
		return preferencePanelsCollection.getPreferencePanelHandler(value);
	}

	public boolean isInputValid() {
		return preferencePanelsCollection.isInputValid();
	}

	public void updateUI() {
		preferencePanelsCollection.updateUI();
	}
}
