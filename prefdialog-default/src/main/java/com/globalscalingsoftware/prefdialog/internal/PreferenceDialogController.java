package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.globalscalingsoftware.prefdialog.Event;
import com.globalscalingsoftware.prefdialog.IAnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.IDiscoveredListener;
import com.globalscalingsoftware.prefdialog.IPreferenceDialog;
import com.globalscalingsoftware.prefdialog.IPreferenceDialogController;
import com.globalscalingsoftware.prefdialog.IPreferencePanel;
import com.globalscalingsoftware.prefdialog.IPreferencePanelCreator;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.google.inject.Inject;

public class PreferenceDialogController implements IPreferenceDialogController {

	private final IAnnotationDiscovery annotationDiscovery;
	private final AnnotationsFilter annotationsFilter;
	private final Map<Object, IPreferencePanel> preferencePanels;
	private final IPreferenceDialog preferenceDialog;
	private final IPreferencePanelCreator preferencePanelCreator;
	private final Map<Object, TreeNode[]> treeNodes;

	@Inject
	PreferenceDialogController(IAnnotationDiscovery annotationDiscovery,
			AnnotationsFilter annotationsFilter,
			IPreferenceDialog preferenceDialog,
			IPreferencePanelCreator preferencePanelCreator) {
		this.annotationDiscovery = annotationDiscovery;
		this.annotationsFilter = annotationsFilter;
		this.preferenceDialog = preferenceDialog;
		this.preferencePanelCreator = preferencePanelCreator;
		preferencePanels = new HashMap<Object, IPreferencePanel>();
		treeNodes = new HashMap<Object, TreeNode[]>();
	}

	@Override
	public void setPreferences(Object preferences) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		discoverAnnotations(preferences, root);
		preferenceDialog.setRootNode(root);
		preferenceDialog.setChildSelected(new Event<Object>() {

			@Override
			public void call(Object object) {
				JPanel panel = preferencePanels.get(object).getPanel();
				preferenceDialog.setChildPanel(panel);
			}
		});
		preferenceDialog.setOkEvent(new Runnable() {

			@Override
			public void run() {
				preferenceDialog.close();
				for (IPreferencePanel panel : preferencePanels.values()) {
					panel.applyAllInput();
				}
			}
		});
		preferenceDialog.setCancelEvent(new Runnable() {

			@Override
			public void run() {
				preferenceDialog.close();
				for (IPreferencePanel panel : preferencePanels.values()) {
					panel.undoAllInput();
				}
			}
		});
	}

	@Override
	public void setChildObject(Object object) {
		JPanel panel = preferencePanels.get(object).getPanel();
		preferenceDialog.setChildPanel(panel);
		preferenceDialog.setSelectedChild(treeNodes.get(object));
	}

	@Override
	public void openDialog() {
		preferenceDialog.open();
	}

	@Override
	public void setOwner(JFrame owner) {
		preferenceDialog.setOwner(owner);
	}

	private void discoverAnnotations(Object preferences,
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

					IPreferencePanel panel = preferencePanelCreator
							.createPanel(value, field);
					preferencePanels.put(value, panel);
				}
			}
		};
		annotationDiscovery.discover(preferences, annotationsFilter, listener);
	}

}
