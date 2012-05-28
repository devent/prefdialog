package com.anrisoftware.prefdialog.dialog.childrenpanels;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import javax.inject.Inject;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import com.anrisoftware.prefdialog.ChildrenPanel;
import com.anrisoftware.prefdialog.ChildrenPanels;
import com.anrisoftware.prefdialog.PreferencePanel;
import com.anrisoftware.prefdialog.PreferencePanelFactory;
import com.anrisoftware.prefdialog.reflection.api.AnnotationDiscoveryCallback;
import com.anrisoftware.prefdialog.reflection.api.AnnotationDiscoveryWorkerFactory;
import com.anrisoftware.prefdialog.reflection.api.AnnotationFilter;
import com.anrisoftware.prefdialog.reflection.api.PredefinedAnnotationFilterFactory;
import com.google.common.collect.Maps;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

/**
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public class DefaultChildrenPanels implements ChildrenPanels, ListModel {

	private final DefaultChildrenPanelsLogger log;

	private final Object preferences;

	private final PredefinedAnnotationFilterFactory filterFactory;

	private final Collection<Class<? extends Annotation>> childAnnotations;

	private final AnnotationDiscoveryWorkerFactory discoveryFactory;

	private final PreferencePanelFactory panelFactory;

	private final Map<Object, PreferencePanel> panels;

	private final DefaultListModel model;

	/**
	 * Injects the needed dependencies for the worker.
	 * 
	 * @param logger
	 *            the {@link DefaultChildrenPanelsLogger} that will log
	 *            messages.
	 * 
	 * @param filterFactory
	 *            the {@link PredefinedAnnotationFilterFactory} that creates the
	 *            annotation filter.
	 * 
	 * @param discoveryFactory
	 *            the {@link AnnotationDiscoveryWorkerFactory} that creates the
	 *            annotation discovery worker.
	 * 
	 * @param panelFactory
	 *            the {@link PreferencePanelFactory} that creates the preference
	 *            panel.
	 * 
	 * @param childAnnotations
	 *            a {@link Collection} of {@link Annotation}s that are the child
	 *            annotations.
	 * 
	 * @param panel
	 *            the {@link JPanel} that is the container of the preference
	 *            panel.
	 * 
	 * @param preferences
	 *            the preferences {@link Object}.
	 * 
	 */
	@Inject
	DefaultChildrenPanels(
			DefaultChildrenPanelsLogger logger,
			PredefinedAnnotationFilterFactory filterFactory,
			AnnotationDiscoveryWorkerFactory discoveryFactory,
			PreferencePanelFactory panelFactory,
			@Named("childAnnotations") Collection<Class<? extends Annotation>> childAnnotations,
			@Assisted Object preferences) {
		this.log = logger;
		this.filterFactory = filterFactory;
		this.discoveryFactory = discoveryFactory;
		this.panelFactory = panelFactory;
		this.childAnnotations = childAnnotations;
		this.preferences = preferences;
		this.panels = Maps.newHashMap();
		this.model = new DefaultListModel();
		discoverAnnotations();
	}

	private void discoverAnnotations() {
		AnnotationFilter filter = filterFactory.create(childAnnotations);
		discoveryFactory.create(filter, new AnnotationDiscoveryCallback() {

			@Override
			public void fieldAnnotationDiscovered(Field field, Object value,
					Annotation a) {
				createChildField(field, value);
			}
		}).discoverAnnotations(preferences);
	}

	private void createChildField(Field field, Object value) {
		createPreferencePanel(preferences, value.toString());
	}

	private void createPreferencePanel(Object preferences, String childName) {
		PreferencePanel preferencePanel = panelFactory.create(new JPanel(),
				preferences, childName);
		log.preferencePanelCreated(childName);
		Object child = preferencePanel.getChildFieldHandler()
				.getComponentValue();
		panels.put(child, preferencePanel);
		model.addElement(child);
	}

	@Override
	public JPanel getChildPanel(ChildrenPanel panel, Object child) {
		return panels.get(child).getPanel();
	}

	@Override
	public int getSize() {
		return model.getSize();
	}

	@Override
	public Object getElementAt(int index) {
		return model.getElementAt(index);
	}

	@Override
	public boolean isInputValid() {
		boolean valid = true;
		for (PreferencePanel panel : panels.values()) {
			if (!panel.isInputValid()) {
				valid = false;
			}
		}
		return valid;
	}

	@Override
	public void applyAllInput() {
		for (PreferencePanel panel : panels.values()) {
			panel.applyInput();
		}
	}

	@Override
	public void restoreAllInput() {
		for (PreferencePanel panel : panels.values()) {
			panel.restoreInput();
		}
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		model.addListDataListener(l);
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		model.removeListDataListener(l);
	}

}
