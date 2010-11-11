package com.globalscalingsoftware.prefdialog.internal.dialog;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.globalscalingsoftware.annotations.Stateless;
import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.internal.inputfield.FieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.AbstractChildFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.ChildFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.reflection.AbstractAnnotationFilter;
import com.globalscalingsoftware.prefdialog.internal.reflection.AnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.internal.reflection.DiscoveredListener;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;
import com.google.inject.Inject;
import com.google.inject.name.Named;

@Stateless
public class InputFieldsFactory {

	private final AbstractAnnotationFilter filter;

	private final Object preferences;

	private final ReflectionToolbox reflectionToolbox;

	private final FieldsFactory fieldsFactory;

	private final AnnotationDiscovery annotationDiscovery;

	private final ApplyAction applyAction;

	private final RestoreAction restoreAction;

	@Inject
	InputFieldsFactory(@Named("preferences") Object preferences,
			AnnotationDiscovery annotationDiscovery,
			PreferenceDialogAnnotationsFilter filter,
			ReflectionToolbox reflectionToolbox, FieldsFactory fieldsFactory,
			ApplyAction applyAction, RestoreAction restoreAction) {
		this.preferences = preferences;
		this.annotationDiscovery = annotationDiscovery;
		this.filter = filter;
		this.reflectionToolbox = reflectionToolbox;
		this.fieldsFactory = fieldsFactory;
		this.applyAction = applyAction;
		this.restoreAction = restoreAction;
	}

	public PreferencePanels createRootNode() {
		Map<Object, TreeNode[]> treeNodes = new HashMap<Object, TreeNode[]>();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		Map<Object, FieldHandler<?>> fieldHandlers = new HashMap<Object, FieldHandler<?>>();

		return discoverAnnotations(root, treeNodes, fieldHandlers);
	}

	private PreferencePanels discoverAnnotations(
			final DefaultMutableTreeNode rootNode,
			final Map<Object, TreeNode[]> treeNodes,
			final Map<Object, FieldHandler<?>> fieldHandlers) {
		DiscoveredListener listener = new DiscoveredListener() {

			@Override
			public void fieldAnnotationDiscovered(Field field, Object value,
					Annotation a) {
				if (a instanceof Child) {
					createChildFieldHandler(rootNode, treeNodes, fieldHandlers,
							field, value);
				}
			}

		};
		annotationDiscovery.discover(filter, preferences, listener);
		return new PreferencePanels(fieldHandlers, treeNodes, rootNode);
	}

	private void createChildFieldHandler(DefaultMutableTreeNode rootNode,
			Map<Object, TreeNode[]> treeNodes,
			Map<Object, FieldHandler<?>> fieldHandlers, Field field,
			Object value) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(value);
		rootNode.add(node);
		treeNodes.put(value, node.getPath());

		Class<ChildFieldHandler> inputFieldClass = ChildFieldHandler.class;
		Class<?>[] parameterTypes = new Class<?>[] { Object.class,
				Object.class, Field.class };
		AbstractChildFieldHandler<?> panel = reflectionToolbox.newInstance(
				inputFieldClass, parameterTypes, preferences, value, field);
		panel.setFieldsFactory(fieldsFactory);
		panel.setReflectionToolbox(reflectionToolbox);
		panel.setApplyAction(applyAction);
		panel.setRestoreAction(restoreAction);
		panel.setup();

		fieldHandlers.put(value, panel);
	}

	public void setApplyAction(Action a) {
		this.applyAction.setParentAction(a);
	}

	public void setRestoreAction(Action a) {
		this.restoreAction.setParentAction(a);
	}
}
