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

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.apache.commons.collections.MapIterator;

import com.globalscalingsoftware.prefdialog.InputChangedCallback;
import com.globalscalingsoftware.prefdialog.PreferencePanelHandler;
import com.globalscalingsoftware.prefdialog.dialog.internal.CreatePreferencePanelHandlersWorker.CreatePreferencePanelHandlersWorkerFactory;
import com.globalscalingsoftware.prefdialog.swingutils.actions.internal.InputChangedDelegateCallback;
import com.globalscalingsoftware.prefdialog.swingutils.actions.internal.InputChangedDelegateCallback.InputChangedDelegateCallbackFactory;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Create the {@link PreferencePanelHandler preference panel handlers} from the
 * preferences.
 */
public class PreferencePanelsHandler {

	public interface PreferencePanelsHandlerFactory {
		PreferencePanelsHandler create(@Assisted Object preferences);
	}

	private final InputChangedDelegateCallback inputChangedCallback;

	private final PreferencePanelsCollection preferencePanelsCollection;

	private final PreferencePanelHandler firstPreferencePanelHandler;

	@Inject
	PreferencePanelsHandler(
			InputChangedDelegateCallbackFactory inputChangedDelegateCallbackFactory,
			CreatePreferencePanelHandlersWorkerFactory createPreferencePanelHandlersWorkerFactory,
			@Assisted Object preferences) {
		this.inputChangedCallback = inputChangedDelegateCallbackFactory
				.create();
		CreatePreferencePanelHandlersWorker createPreferencePanelHandlersWorker = createPreferencePanelHandlersWorkerFactory
				.create(preferences, inputChangedCallback);
		this.firstPreferencePanelHandler = createPreferencePanelHandlersWorker
				.getFirstPreferencePanelHandler();
		this.preferencePanelsCollection = createPreferencePanelHandlersWorker
				.getPreferencePanelsCollection();
	}

	public void setInputChangedCallback(InputChangedCallback callback) {
		inputChangedCallback.setDelegateCallback(callback);
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
}
