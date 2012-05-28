package com.anrisoftware.prefdialog.dialog.childrentab;

import static java.awt.BorderLayout.CENTER;
import static java.lang.String.format;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.inject.Inject;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import javax.swing.event.SwingPropertyChangeSupport;

import com.anrisoftware.prefdialog.ChildrenPanel;
import com.anrisoftware.prefdialog.ChildrenPanels;
import com.anrisoftware.prefdialog.dialog.childrenpanels.EmptyChildrenPanels;
import com.google.inject.assistedinject.Assisted;

/**
 * Contains a list of available children in a {@link JTabbedPane} at the top and
 * inside the currently selected child {@link JPanel}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ChildrenPanelImpl implements ChildrenPanel {

	private final PropertyChangeSupport support;

	private final JPanel panel;

	private final JTabbedPane childrenTab;

	private final ChildrenPanelModelListener modelListener;

	private ListModel model;

	private ChildrenPanels panels;

	private Object selectedChild;

	@Inject
	public ChildrenPanelImpl(ChildrenPanelModelListener modelListener,
			EmptyChildrenPanels emptyChildrenPanels, @Assisted JPanel panel) {
		this.support = new SwingPropertyChangeSupport(this);
		this.modelListener = modelListener;
		this.panel = panel;
		this.childrenTab = new JTabbedPane();
		this.panels = emptyChildrenPanels;
		setupPanel();
		setupChildrenTab();
		setupModelChangeListener();
		setupPanelsChangeListener();
	}

	private void setupPanel() {
		panel.setLayout(new BorderLayout());
		panel.add(childrenTab, CENTER);
	}

	private void setupChildrenTab() {
		modelListener.setTabbedPane(childrenTab);
	}

	private void setupModelChangeListener() {
		support.addPropertyChangeListener(PROPERTY_CHILDREN_MODEL,
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						updateModelListener(evt);
						updateChildPanels();
					}

				});
	}

	private void updateModelListener(PropertyChangeEvent evt) {
		ListModel oldValue = (ListModel) evt.getOldValue();
		ListModel newValue = (ListModel) evt.getNewValue();
		if (oldValue != null) {
			oldValue.removeListDataListener(modelListener);
		}
		newValue.addListDataListener(modelListener);
		modelListener.updateChildren(newValue);
		Object firstChild = newValue.getElementAt(0);
		setSelectedChild(firstChild);
	}

	private void setupPanelsChangeListener() {
		support.addPropertyChangeListener(PROPERTY_CHILDREN_PANELS,
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						updateChildPanels();
					}

				});
	}

	private void updateChildPanels() {
		if (panels == null || model == null) {
			return;
		}
		for (int i = 0; i < model.getSize(); i++) {
			Object child = model.getElementAt(i);
			JPanel panel = panels.getChildPanel(this, child);
			childrenTab.setComponentAt(i, panel);
		}
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void setName(String name) {
		panel.setName(format("%s-%s", name, PANEL_NAME_POSTFIX));
	}

	@Override
	public void setChildrenModel(ListModel model) {
		ListModel oldValue = this.model;
		this.model = model;
		support.firePropertyChange(PROPERTY_CHILDREN_MODEL, oldValue, model);
	}

	@Override
	public ListModel getChildrenModel() {
		return model;
	}

	@Override
	public void setChildrenPanels(ChildrenPanels panels) {
		ChildrenPanels oldValue = this.panels;
		this.panels = panels;
		support.firePropertyChange(PROPERTY_CHILDREN_PANELS, oldValue, panels);
	}

	@Override
	public ChildrenPanels getChildrenPanels() {
		return panels;
	}

	@Override
	public void setSelectedChild(Object child) {
		JPanel panel = panels.getChildPanel(this, child);
		if (childrenPanelsAreNotSetYet(panel)) {
			return;
		}
		childrenTab.setSelectedComponent(panel);
		Object oldValue = selectedChild;
		selectedChild = child;
		support.firePropertyChange(PROPERTY_SELECTED_CHILD, oldValue, panels);
	}

	private boolean childrenPanelsAreNotSetYet(JPanel panel) {
		return childrenTab.indexOfComponent(panel) == -1;
	}

	@Override
	public Object getSelectedChild() {
		return selectedChild;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	@Override
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		support.addPropertyChangeListener(propertyName, listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		support.removePropertyChangeListener(propertyName, listener);
	}

}
