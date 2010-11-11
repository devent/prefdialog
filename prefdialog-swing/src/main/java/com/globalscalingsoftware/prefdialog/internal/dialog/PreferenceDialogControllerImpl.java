package com.globalscalingsoftware.prefdialog.internal.dialog;

import java.awt.Frame;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.Options;
import com.globalscalingsoftware.prefdialog.PreferenceDialogController;
import com.globalscalingsoftware.prefdialog.annotations.actions.ApplyAction;
import com.globalscalingsoftware.prefdialog.annotations.actions.RestoreAction;
import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.internal.FieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.InputFieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.AbstractChildFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.ChildFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.reflection.AbstractAnnotationFilter;
import com.globalscalingsoftware.prefdialog.internal.reflection.AnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.internal.reflection.DiscoveredListener;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PreferenceDialogControllerImpl implements
		PreferenceDialogController, PreferenceDialogControllerInternal {

	private final AnnotationDiscovery annotationDiscovery;
	private final Map<Object, FieldHandler<?>> preferencePanels;
	private final PreferenceDialog preferenceDialog;
	private final Map<Object, TreeNode[]> treeNodes;
	private final Object preferences;
	private final Object preferencesStart;
	private final AbstractAnnotationFilter filter;
	private final InputFieldsFactory inputFieldsFactory;
	private final ReflectionToolbox reflectionToolbox;
	private final FieldsFactory fieldsFactory;
	private Options option;
	private final Action applyAction;
	private final Action restoreAction;

	@Inject
	PreferenceDialogControllerImpl(AnnotationDiscovery annotationDiscovery,
			PreferenceDialogAnnotationsFilter filter,
			ReflectionToolbox reflectionToolbox,
			PreferenceDialog preferenceDialog,
			InputFieldsFactory inputFieldsFactory, FieldsFactory fieldsFactory,
			@Named("preferences") Object preferences,
			@Named("preferences_start") Object preferencesStart,
			@ApplyAction Action applyAction, @RestoreAction Action restoreAction) {
		System.out
				.println("PreferenceDialogControllerImpl.PreferenceDialogControllerImpl()");
		this.annotationDiscovery = annotationDiscovery;
		this.filter = filter;
		this.reflectionToolbox = reflectionToolbox;
		this.preferenceDialog = preferenceDialog;
		this.inputFieldsFactory = inputFieldsFactory;
		this.preferences = preferences;
		this.preferencesStart = preferencesStart;
		this.fieldsFactory = fieldsFactory;
		this.preferencePanels = new HashMap<Object, FieldHandler<?>>();
		this.treeNodes = new HashMap<Object, TreeNode[]>();
		this.option = Options.CANCEL;
		this.applyAction = applyAction;
		this.restoreAction = restoreAction;
	}

	@Override
	public void setup(Frame owner) {
		setupRootNode();
		setupChildSelectedAction();
		setupPreferencesStart();
		preferenceDialog.setup(owner);
	}

	@Override
	public void openDialog() {
		preferenceDialog.open();
	}

	public JDialog getPreferenceDialog() {
		return preferenceDialog.getUiPreferencesDialog();
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

	private void setupChildSelectedAction() {
		preferenceDialog.setChildSelected(new ChildSelectedAction(this));
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

					Class<ChildFieldHandler> inputFieldClass = ChildFieldHandler.class;
					AbstractChildFieldHandler<?> panel = inputFieldsFactory
							.create(inputFieldClass, preferences, value, field);
					panel.setFieldsFactory(fieldsFactory);
					panel.setReflectionToolbox(reflectionToolbox);
					panel.setApplyAction(applyAction);
					panel.setRestoreAction(restoreAction);
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

	@Override
	public void closeDialog(Options option) {
		this.option = option;
		preferenceDialog.close();
	}

	@Override
	public Map<Object, FieldHandler<?>> getPreferencePanels() {
		return preferencePanels;
	}

	@Override
	public Options getOption() {
		return option;
	}

	@Override
	public void setOkAction(Action a) {
		preferenceDialog.setOkAction(a);
	}

	@Override
	public void setCancelAction(Action a) {
		preferenceDialog.setCancelAction(a);
	}
}
