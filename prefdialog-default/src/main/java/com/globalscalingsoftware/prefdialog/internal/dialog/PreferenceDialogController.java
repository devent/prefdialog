package com.globalscalingsoftware.prefdialog.internal.dialog;

import java.awt.Frame;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.globalscalingsoftware.prefdialog.IPreferenceDialogController;
import com.globalscalingsoftware.prefdialog.InputField;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.internal.AbstractAnnotationFilter;
import com.globalscalingsoftware.prefdialog.internal.AnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.internal.DiscoveredListener;
import com.globalscalingsoftware.prefdialog.internal.FieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.InputFieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.ReflectionToolbox;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.AbstractChildInputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.ChildInputField;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PreferenceDialogController implements IPreferenceDialogController {

	private final AnnotationDiscovery annotationDiscovery;
	private final Map<Object, InputField<?>> preferencePanels;
	private final PreferenceDialog preferenceDialog;
	private final Map<Object, TreeNode[]> treeNodes;
	private final Object preferences;
	private final Object preferencesStart;
	private final AbstractAnnotationFilter filter;
	private final InputFieldsFactory inputFieldsFactory;
	private final ReflectionToolbox reflectionToolbox;
	private final FieldsFactory fieldsFactory;

	@Inject
	PreferenceDialogController(AnnotationDiscovery annotationDiscovery,
			PreferenceDialogAnnotationsFilter filter,
			ReflectionToolbox reflectionToolbox,
			PreferenceDialog preferenceDialog,
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
		preferencePanels = new HashMap<Object, InputField<?>>();
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
				.getAWTComponent();
		preferenceDialog.setChildPanel(panel);
		preferenceDialog.setSelectedChild(treeNodes.get(preferencesStart));
	}

	private void setupEvents() {
		preferenceDialog.setChildSelected(new ChildSelectedAction(this));
		preferenceDialog.setOkEvent(new OkEvent(this));
		preferenceDialog.setCancelEvent(new CancelEvent(this));
	}

	private void discoverAnnotations(final Object preferences,
			final DefaultMutableTreeNode root) {
		DiscoveredListener listener = new DiscoveredListener() {

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

	void setChildPanel(Object object) {
		JPanel panel = (JPanel) preferencePanels.get(object).getAWTComponent();
		preferenceDialog.setChildPanel(panel);
	}

	void closeDialog() {
		preferenceDialog.close();
	}

	Map<Object, InputField<?>> getPreferencePanels() {
		return preferencePanels;
	}

}
