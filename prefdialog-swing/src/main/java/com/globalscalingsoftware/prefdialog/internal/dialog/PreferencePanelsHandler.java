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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.globalscalingsoftware.prefdialog.PreferencePanelHandler;
import com.globalscalingsoftware.prefdialog.PreferencePanelHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.dialog.PreferencePanelsCollection.PreferencePanelsCollectionFactory;
import com.globalscalingsoftware.prefdialog.reflection.AnnotationDiscoveryCallback;
import com.globalscalingsoftware.prefdialog.reflection.AnnotationDiscoveryFactory;
import com.globalscalingsoftware.prefdialog.reflection.AnnotationFilterFactory;
import com.globalscalingsoftware.prefdialog.reflection.internal.AnnotationFilter;
import com.google.inject.Inject;
import com.google.inject.internal.Maps;
import com.google.inject.name.Named;

/**
 * Create the {@link PreferencePanelHandler preference panel handlers} from the
 * preferences.
 */
public class PreferencePanelsHandler {

	private final Logger l = LoggerFactory
			.getLogger(PreferencePanelsHandler.class);

	private final AnnotationFilter annotationFilter;

	private final PreferencePanelsCollectionFactory preferencePanelsCollectionFactory;

	private final AnnotationDiscoveryFactory annotationDiscoveryFactory;

	private final PreferencePanelHandlerFactory preferencePanelHandlerFactory;

	@Inject
	PreferencePanelsHandler(
			AnnotationFilterFactory annotationFilterFactory,
			AnnotationDiscoveryFactory annotationDiscoveryFactory,
			@Named("childAnnotations") Collection<Class<? extends Annotation>> childAnnotations,
			PreferencePanelsCollectionFactory preferencePanelsCollectionFactory,
			PreferencePanelHandlerFactory preferencePanelHandlerFactory) {
		this.annotationFilter = annotationFilterFactory
				.create(childAnnotations);
		this.annotationDiscoveryFactory = annotationDiscoveryFactory;
		this.preferencePanelsCollectionFactory = preferencePanelsCollectionFactory;
		this.preferencePanelHandlerFactory = preferencePanelHandlerFactory;
	}

	/**
	 * Create a {@link PreferencePanelsCollection collection} of
	 * {@link PreferencePanelHandler} from the given preferences object.
	 */
	public PreferencePanelsCollection createPreferencePanelsCollection(
			Object preferences) {
		Map<Object, PreferencePanelHandler> panelHandlers = Maps.newHashMap();
		Map<Object, TreeNode[]> treeNodes = Maps.newHashMap();
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
		PreferencePanelHandler firstPreferencePanelHandler = createPreferencePanelHandlers(
				preferences, panelHandlers, treeNodes, rootNode);
		return preferencePanelsCollectionFactory.create(panelHandlers,
				firstPreferencePanelHandler, treeNodes, rootNode);
	}

	private PreferencePanelHandler createPreferencePanelHandlers(
			Object preferences,
			final Map<Object, PreferencePanelHandler> panelHandlers,
			final Map<Object, TreeNode[]> treeNodes,
			final DefaultMutableTreeNode rootNode) {
		CreatePreferencePanelHandlersCallback callback = new CreatePreferencePanelHandlersCallback(
				preferences, panelHandlers, treeNodes, rootNode);
		annotationDiscoveryFactory.create(annotationFilter, preferences,
				callback).discoverAnnotations();
		return callback.getFirstPreferencePanelHandler();
	}

	private class CreatePreferencePanelHandlersCallback implements
			AnnotationDiscoveryCallback {

		private final Map<Object, PreferencePanelHandler> panelHandlers;
		private final Map<Object, TreeNode[]> treeNodes;
		private final DefaultMutableTreeNode rootNode;
		private final Object preferences;
		private PreferencePanelHandler firstPreferencePanelHandler;

		public CreatePreferencePanelHandlersCallback(Object preferences,
				Map<Object, PreferencePanelHandler> panelHandlers,
				Map<Object, TreeNode[]> treeNodes,
				DefaultMutableTreeNode rootNode) {
			this.preferences = preferences;
			this.panelHandlers = panelHandlers;
			this.treeNodes = treeNodes;
			this.rootNode = rootNode;
			this.firstPreferencePanelHandler = null;
		}

		public PreferencePanelHandler getFirstPreferencePanelHandler() {
			return firstPreferencePanelHandler;
		}

		@Override
		public void fieldAnnotationDiscovered(Field field, Object value,
				Annotation a) {
			createPreferencePanelHandler(preferences, value.toString());
			createNodePath(value);
		}

		private void createPreferencePanelHandler(Object preferences,
				String panelName) {
			PreferencePanelHandler handler = preferencePanelHandlerFactory
					.create(preferences, panelName);
			if (firstPreferencePanelHandler == null) {
				firstPreferencePanelHandler = handler;
			}
			l.debug("New preference panel handler created for {} panel in preferences {}.",
					panelName, preferences);
			panelHandlers.put(handler.getPreferences(), handler);
		}

		private void createNodePath(Object value) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(value);
			rootNode.add(node);
			TreeNode[] path = node.getPath();
			treeNodes.put(value, path);
			l.debug("New node path {} created for {}.", path, value);
		}

	}

}
