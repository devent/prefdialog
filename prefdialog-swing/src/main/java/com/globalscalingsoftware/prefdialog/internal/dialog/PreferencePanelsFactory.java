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
import java.util.HashMap;

import javax.swing.Action;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.globalscalingsoftware.annotations.Stateless;
import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.internal.inputfield.FieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.actions.ApplyAction;
import com.globalscalingsoftware.prefdialog.internal.inputfield.actions.RestoreAction;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.ChildFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.ChildFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.reflection.AnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.internal.reflection.AnnotationDiscoveryCallback;
import com.globalscalingsoftware.prefdialog.internal.reflection.FieldFactories;
import com.google.inject.Inject;

@Stateless
public class PreferencePanelsFactory {

	private class FactoryWorker {

		private final AnnotationDiscovery annotationDiscovery;

		private final Object preferences;

		private final HashMap<Object, TreeNode[]> treeNodes;

		private final DefaultMutableTreeNode rootNode;

		private final HashMap<Object, ChildFieldHandler> fieldHandlers;

		private ChildFieldHandler preferencesStart;

		private final FieldFactories fieldFactories;

		public FactoryWorker(AnnotationDiscovery annotationDiscovery,
				FieldFactories fieldFactories, Object preferences) {
			this.annotationDiscovery = annotationDiscovery;
			this.fieldFactories = fieldFactories;
			this.preferences = preferences;
			this.treeNodes = new HashMap<Object, TreeNode[]>();
			this.rootNode = new DefaultMutableTreeNode();
			this.fieldHandlers = new HashMap<Object, ChildFieldHandler>();
			this.preferencesStart = null;
		}

		public PreferencePanels discoverAnnotations() {
			AnnotationDiscoveryCallback callback = new AnnotationDiscoveryCallback() {

				@Override
				public void fieldAnnotationDiscovered(Field field,
						Object value, Annotation a) {
					if (a instanceof Child) {
						createPreferencesStart(field, value);
					}
				}

			};
			annotationDiscovery.discoverAnnotations(preferences, callback);
			return new PreferencePanels(fieldHandlers, treeNodes, rootNode,
					preferencesStart);
		}

		private void createPreferencesStart(Field field, Object value) {
			ChildFieldHandler fieldHandler = createChildFieldHandler(field,
					value);
			if (preferencesStart == null) {
				preferencesStart = fieldHandler;
			}
		}

		private ChildFieldHandler createChildFieldHandler(Field field,
				Object value) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(value);
			rootNode.add(node);
			treeNodes.put(value, node.getPath());

			ChildFieldHandlerFactory factory = fieldFactories
					.getFactory(Child.class);
			ChildFieldHandler panel = factory.create(preferences, value, field);
			for (FieldHandler<?> fieldHandler : fieldsFactory
					.createFieldsHandlers(annotationDiscovery, fieldFactories,
							preferences, value)) {
				fieldHandler.setup();
				panel.addFieldHandler(fieldHandler);
			}

			panel.setApplyAction(applyAction);
			panel.setRestoreAction(restoreAction);
			panel.setup();

			fieldHandlers.put(value, panel);
			return panel;
		}

	}

	private final FieldsFactory fieldsFactory;

	private final ApplyAction applyAction;

	private final RestoreAction restoreAction;

	@Inject
	PreferencePanelsFactory(FieldsFactory fieldsFactory,
			ApplyAction applyAction, RestoreAction restoreAction) {
		this.fieldsFactory = fieldsFactory;
		this.applyAction = applyAction;
		this.restoreAction = restoreAction;
	}

	public PreferencePanels createRootNode(
			AnnotationDiscovery annotationDiscovery,
			FieldFactories fieldFactories, Object preferences) {
		return new FactoryWorker(annotationDiscovery, fieldFactories,
				preferences).discoverAnnotations();
	}

	public void setApplyAction(Action a) {
		this.applyAction.setParentAction(a);
	}

	public void setRestoreAction(Action a) {
		this.restoreAction.setParentAction(a);
	}
}
