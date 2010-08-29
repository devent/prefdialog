package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.globalscalingsoftware.prefdialog.Child;
import com.globalscalingsoftware.prefdialog.Event;
import com.google.inject.Inject;

public class PreferenceDialogController {

	private final AnnotationDiscovery annotationDiscovery;
	private final AnnotationsFilter annotationsFilter;
	private final Map<Object, JPanel> preferencePanels;
	private final PreferenceDialog preferenceDialog;
	private final PreferencePanelCreator preferencePanel;
	private final Map<Object, TreeNode[]> treeNodes;

	@Inject
	PreferenceDialogController(AnnotationDiscovery annotationDiscovery,
			AnnotationsFilter annotationsFilter,
			PreferenceDialog preferenceDialog,
			PreferencePanelCreator preferencePanel) {
		this.annotationDiscovery = annotationDiscovery;
		this.annotationsFilter = annotationsFilter;
		this.preferenceDialog = preferenceDialog;
		this.preferencePanel = preferencePanel;
		preferencePanels = new HashMap<Object, JPanel>();
		treeNodes = new HashMap<Object, TreeNode[]>();
	}

	public void setPreferences(Object preferences) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		discoverAnnotations(preferences, root);
		preferenceDialog.setRootNode(root);
		preferenceDialog.setChildSelected(new Event<Object>() {

			@Override
			public void call(Object object) {
				JPanel panel = preferencePanels.get(object);
				preferenceDialog.setChildPanel(panel);
			}
		});
	}

	public void setChildObject(Object object) {
		JPanel panel = preferencePanels.get(object);
		preferenceDialog.setChildPanel(panel);
		preferenceDialog.setSelectedChild(treeNodes.get(object));
	}

	private void discoverAnnotations(Object preferences,
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

					JPanel panel = preferencePanel.createPanel(field, value);
					preferencePanels.put(value, panel);
				}
			}
		};
		annotationDiscovery.discover(preferences, annotationsFilter, listener);
	}

}
