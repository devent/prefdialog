package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import com.globalscalingsoftware.prefdialog.Child;
import com.globalscalingsoftware.prefdialog.Event;
import com.google.inject.Inject;

public class PreferenceDialogController {

	private final AnnotationDiscovery annotationDiscovery;
	private final AnnotationsFilter annotationsFilter;
	private final Map<Object, JPanel> preferencePanels;
	private final PreferenceDialog preferenceDialog;
	private final PreferencePanel preferencePanel;

	@Inject
	PreferenceDialogController(AnnotationDiscovery annotationDiscovery,
			AnnotationsFilter annotationsFilter,
			PreferenceDialog preferenceDialog, PreferencePanel preferencePanel) {
		this.annotationDiscovery = annotationDiscovery;
		this.annotationsFilter = annotationsFilter;
		this.preferenceDialog = preferenceDialog;
		this.preferencePanel = preferencePanel;
		preferencePanels = new HashMap<Object, JPanel>();
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

	private void discoverAnnotations(Object preferences,
			final DefaultMutableTreeNode root) {
		DiscoveredListener listener = new DiscoveredListener() {

			@Override
			public void fieldAnnotationDiscovered(Field field, Object value,
					Annotation a) {
				if (a instanceof Child) {
					MutableTreeNode node = new DefaultMutableTreeNode(value);
					root.add(node);

					JPanel panel = preferencePanel.createPanel(field, value);
					preferencePanels.put(value, panel);
				}
			}
		};
		try {
			annotationDiscovery.discover(preferences, annotationsFilter,
					listener);
		} catch (AnnotationDiscoveryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
