package com.globalscalingsoftware.prefdialog.internal;

import java.awt.Frame;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.globalscalingsoftware.prefdialog.Event;
import com.globalscalingsoftware.prefdialog.IAnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.IAnnotationFilter;
import com.globalscalingsoftware.prefdialog.IDiscoveredListener;
import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IPreferenceDialog;
import com.globalscalingsoftware.prefdialog.IPreferenceDialogController;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.AbstractChildInputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.ChildInputField;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PreferenceDialogController implements IPreferenceDialogController {

	private final IAnnotationDiscovery annotationDiscovery;
	private Map<Object, IInputField> preferencePanels;
	private final IPreferenceDialog preferenceDialog;
	private final Map<Object, TreeNode[]> treeNodes;
	private final Object preferences;
	private final Object preferencesStart;
	private final IAnnotationFilter filter;
	private final InputFieldsFactory inputFieldsFactory;
	private final ReflectionToolbox reflectionToolbox;
	private final FieldsFactory fieldsFactory;

	@Inject
	PreferenceDialogController(IAnnotationDiscovery annotationDiscovery,
			PreferenceDialogAnnotationsFilter filter,
			ReflectionToolbox reflectionToolbox,
			IPreferenceDialog preferenceDialog,
			InputFieldsFactory inputFieldsFactory, FieldsFactory fieldsFactory,
			@Named("preferences") Object preferences,
			@Named("preferences_start") Object preferencesStart) {
		this.annotationDiscovery = annotationDiscovery;
		this.filter = filter;
		this.reflectionToolbox = reflectionToolbox;
		this.preferenceDialog = preferenceDialog;
		this.inputFieldsFactory = inputFieldsFactory;
		this.preferences = preferences;
		this.preferencesStart = preferencesStart;
		this.fieldsFactory = fieldsFactory;
		preferencePanels = new HashMap<Object, IInputField>();
		treeNodes = new HashMap<Object, TreeNode[]>();
	}

	@Override
	public void openDialog(Frame owner) {
		setupRootNode();
		setupEvents();
		setupPreferencesStart();
		preferenceDialog.open(owner);
	}

	private void setupRootNode() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		discoverAnnotations(preferences, root);
		preferenceDialog.setRootNode(root);
	}

	private void setupPreferencesStart() {
		JPanel panel = (JPanel) preferencePanels.get(preferencesStart)
				.getComponent();
		preferenceDialog.setChildPanel(panel);
		preferenceDialog.setSelectedChild(treeNodes.get(preferencesStart));
	}

	private void setupEvents() {
		preferenceDialog.setChildSelected(new Event<Object>() {

			@Override
			public void call(Object object) {
				JPanel panel = (JPanel) preferencePanels.get(object)
						.getComponent();
				preferenceDialog.setChildPanel(panel);
			}
		});
		preferenceDialog.setOkEvent(new Runnable() {

			@Override
			public void run() {
				preferenceDialog.close();

				Map<Object, IInputField> copy = copyPreferencePanels();
				putValuesInto(copy);
				preferencePanels = copy;
			}

			private void putValuesInto(Map<Object, IInputField> copy) {
				for (Map.Entry<Object, IInputField> entry : preferencePanels
						.entrySet()) {
					copy.put(entry.getKey(), entry.getValue());
				}
			}

			private Map<Object, IInputField> copyPreferencePanels() {
				return new HashMap<Object, IInputField>(preferencePanels);
			}
		});
		preferenceDialog.setCancelEvent(new Runnable() {

			@Override
			public void run() {
				preferenceDialog.close();
			}
		});
	}

	private void discoverAnnotations(final Object preferences,
			final DefaultMutableTreeNode root) {
		IDiscoveredListener listener = new IDiscoveredListener() {

			@Override
			public void fieldAnnotationDiscovered(Field field, Object value,
					Annotation a) {
				if (a instanceof Child) {
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(
							value);
					root.add(node);

					treeNodes.put(value, node.getPath());

					Class<ChildInputField> inputFieldClass = ChildInputField.class;
					AbstractChildInputField<?> panel = inputFieldsFactory
							.create(inputFieldClass, preferences, value, field);
					panel.setFieldsFactory(fieldsFactory);
					panel.setReflectionToolbox(reflectionToolbox);
					panel.setup();

					preferencePanels.put(value, panel);
				}
			}
		};
		annotationDiscovery.discover(filter, preferences, listener);
	}

}
