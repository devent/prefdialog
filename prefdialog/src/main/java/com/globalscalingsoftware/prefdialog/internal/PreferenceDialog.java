package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.globalscalingsoftware.prefdialog.Child;
import com.globalscalingsoftware.prefdialog.ICancelAction;
import com.globalscalingsoftware.prefdialog.IOkAction;
import com.globalscalingsoftware.prefdialog.IRestoreAction;
import com.google.inject.Inject;

public class PreferenceDialog implements TreeSelectionListener {

	private final UiPreferencesDialog uiPreferencesDialog;
	private final AnnotationDiscovery annotationDiscovery;
	private final AnnotationsFilter annotationsFilter;

	private final Map<Object, JPanel> preferencePanels;

	@Inject
	PreferenceDialog(AnnotationDiscovery annotationDiscovery,
			AnnotationsFilter annotationsFilter,
			UiPreferencesDialog uiPreferencesDialog, IOkAction okAction,
			IRestoreAction restoreAction, ICancelAction cancelAction) {
		this.annotationDiscovery = annotationDiscovery;
		this.annotationsFilter = annotationsFilter;
		this.uiPreferencesDialog = uiPreferencesDialog;
		preferencePanels = new HashMap<Object, JPanel>();
	}

	public void open(Object preferences) {
		final DefaultMutableTreeNode top = new DefaultMutableTreeNode("root");
		uiPreferencesDialog.getPreferencesTree().setRootVisible(false);
		uiPreferencesDialog.getPreferencesTree().getSelectionModel()
				.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		uiPreferencesDialog.getPreferencesTree().addTreeSelectionListener(this);

		discoverAnnotations(preferences, top);
		uiPreferencesDialog.getPreferencesTree().setModel(
				new DefaultTreeModel(top));

		uiPreferencesDialog.setModal(true);
		uiPreferencesDialog.pack();
		uiPreferencesDialog.setVisible(true);
	}

	private void discoverAnnotations(Object preferences,
			final DefaultMutableTreeNode top) {
		DiscoveredListener listener = new DiscoveredListener() {

			@Override
			public void fieldAnnotationDiscovered(Field field, Object value,
					Annotation a) {
				if (a instanceof Child) {
					MutableTreeNode node = new DefaultMutableTreeNode(value);
					top.add(node);

					JPanel panel = new PreferencePanel(field, value);
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

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) uiPreferencesDialog
				.getPreferencesTree().getLastSelectedPathComponent();

		if (node == null)
			// Nothing is selected.
			return;

		Object nodeInfo = node.getUserObject();
		JPanel panel = preferencePanels.get(nodeInfo);

		uiPreferencesDialog.getMainSplitPane().setRightComponent(panel);
	}
}
