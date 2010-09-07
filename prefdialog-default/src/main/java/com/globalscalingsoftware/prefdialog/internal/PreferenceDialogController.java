package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.globalscalingsoftware.prefdialog.Event;
import com.globalscalingsoftware.prefdialog.IAnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.IDiscoveredListener;
import com.globalscalingsoftware.prefdialog.IPreferenceDialog;
import com.globalscalingsoftware.prefdialog.IPreferenceDialogController;
import com.globalscalingsoftware.prefdialog.IPreferencePanelController;
import com.globalscalingsoftware.prefdialog.IPreferencePanelFactory;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PreferenceDialogController implements IPreferenceDialogController {

	private final IAnnotationDiscovery annotationDiscovery;
	private final Map<Object, IPreferencePanelController> preferencePanels;
	private final IPreferenceDialog preferenceDialog;
	private final Map<Object, TreeNode[]> treeNodes;
	private final Object preferences;
	private final Object preferencesStart;
	private final IPreferencePanelFactory preferencePanelFactory;

	@Inject
	PreferenceDialogController(IAnnotationDiscovery annotationDiscovery,
			IPreferenceDialog preferenceDialog,
			IPreferencePanelFactory preferencePanelFactory,
			@Named("preferences") Object preferences,
			@Named("preferences_start") Object preferencesStart) {
		this.annotationDiscovery = annotationDiscovery;
		this.preferenceDialog = preferenceDialog;
		this.preferencePanelFactory = preferencePanelFactory;
		this.preferences = preferences;
		this.preferencesStart = preferencesStart;
		preferencePanels = new HashMap<Object, IPreferencePanelController>();
		treeNodes = new HashMap<Object, TreeNode[]>();
	}

	@Override
	public void openDialog() {
		setupRootNode();
		setupEvents();
		setupPreferencesStart();
		preferenceDialog.open();
	}

	private void setupRootNode() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		discoverAnnotations(preferences, root);
		preferenceDialog.setRootNode(root);
	}

	private void setupPreferencesStart() {
		JPanel panel = preferencePanels.get(preferencesStart).getPanel();
		preferenceDialog.setChildPanel(panel);
		preferenceDialog.setSelectedChild(treeNodes.get(preferencesStart));
	}

	private void setupEvents() {
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
				for (IPreferencePanelController panel : preferencePanels
						.values()) {
					panel.applyAllInput();
				}
			}
		});
		preferenceDialog.setCancelEvent(new Runnable() {

			@Override
			public void run() {
				preferenceDialog.close();
				for (IPreferencePanelController panel : preferencePanels
						.values()) {
					panel.undoAllInput();
				}
			}
		});
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

					IPreferencePanelController panel = preferencePanelFactory
							.create(value, field);
					panel.setupPanel();
					preferencePanels.put(value, panel);
				}
			}
		};
		annotationDiscovery.discover(preferences, listener);
	}

}
