package com.anrisoftware.prefdialog;

import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

/**
 * The panel that contains a list of the child panels and is showing the current
 * selected child panel. The user can select in the list different children and
 * the panels are changed according to the selection.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public interface ChildrenPanel {

	/**
	 * The name postfix used for the children panel.
	 */
	static final String PANEL_NAME_POSTFIX = "children-panel";

	/**
	 * Property identifier that is used if a new child was selected in the
	 * panel.
	 */
	static final String PROPERTY_SELECTED_CHILD = "selected_child";

	/**
	 * Property identifier that is used if a new {@link ListModel} was set that
	 * contains the children that can be selected.
	 * 
	 * @see #setChildrenModel(ListModel)
	 */
	static final String PROPERTY_CHILDREN_MODEL = "children_model";

	/**
	 * Property identifier that is used if a new {@link ChildrenPanels} was set
	 * that returns the panel for the selected child.
	 * 
	 * @see #setChildrenModel(ListModel)
	 */
	static final String PROPERTY_CHILDREN_PANELS = "children_panels";

	/**
	 * Returns the {@link JPanel} that contains to the list of the children and
	 * the current selected child panel.
	 */
	JPanel getPanel();

	/**
	 * <p>
	 * Sets the name of this panel.
	 * </p>
	 * <p>
	 * Sets the name of each of the components in this panel prefixed with the
	 * given name.
	 * </p>
	 * 
	 * @param name
	 *            the given name.
	 */
	void setName(String name);

	/**
	 * <p>
	 * Sets a new children model that contains the children that can be
	 * selected.
	 * </p>
	 * <p>
	 * The registered property change listeners are notified with the
	 * {@link #PROPERTY_CHILDREN_MODEL} property identifier that a new model was
	 * set.
	 * </p>
	 * 
	 * @param model
	 *            the {@link ListModel} of children objects.
	 */
	void setChildrenModel(ListModel model);

	/**
	 * Returns the currently set children model.
	 * 
	 * @return the currently set {@link ListModel} that contains the children
	 *         objects.
	 */
	ListModel getChildrenModel();

	/**
	 * <p>
	 * Sets the panels that will return a panel for the selected child.
	 * </p>
	 * <p>
	 * We use the {@link ChildrenPanels#getChildPanel(ChildrenPanel, Object)} to
	 * get the {@link JPanel} for the selected child.
	 * </p>
	 * <p>
	 * The registered property change listeners are notified with the
	 * {@link #PROPERTY_CHILDREN_PANELS} property identifier that a new panels
	 * was set.
	 * </p>
	 * 
	 * @param renderer
	 *            the {@link ListCellRenderer} that will return a {@link JPanel}
	 *            for the currently selected child.
	 */
	void setChildrenPanels(ChildrenPanels panels);

	/**
	 * Returns the current set panels that will return a panel for the selected
	 * child.
	 * 
	 * @return the currently set {@link ChildrenPanel}.
	 */
	ChildrenPanels getChildrenPanels();

	/**
	 * <p>
	 * Sets the selected child.
	 * </p>
	 * <p>
	 * The registered property change listeners are notified with the
	 * {@link #PROPERTY_SELECTED_CHILD} property identifier.
	 * </p>
	 * 
	 * @param child
	 *            the preferences child {@link Object}.
	 */
	void setSelectedChild(Object child);

	/**
	 * Returns the child {@link Object} that is currently selected, or
	 * <code>null</code> if no child object is selected.
	 */
	Object getSelectedChild();

	/**
	 * <p>
	 * Add a PropertyChangeListener to the listener list.
	 * </p>
	 * <p>
	 * The listener is registered for all properties. The same listener object
	 * may be added more than once, and will be called as many times as it is
	 * added. If <code>listener</code> is null, no exception is thrown and no
	 * action is taken.
	 * </p>
	 * 
	 * @param listener
	 *            the {@link PropertyChangeListener} to be added.
	 * 
	 * @see #PROPERTY_SELECTED_CHILD
	 * @see #PROPERTY_CHILDREN_MODEL
	 * @see #PROPERTY_CHILDREN_PANELS
	 */
	void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * <p>
	 * Add a PropertyChangeListener for a specific property.
	 * </p>
	 * <p>
	 * The listener will be invoked only when a call on firePropertyChange names
	 * that specific property. The same listener object may be added more than
	 * once. For each property, the listener will be invoked the number of times
	 * it was added for that property. If <code>propertyName</code> or
	 * <code>listener</code> is null, no exception is thrown and no action is
	 * taken.
	 * </p>
	 * 
	 * @param propertyName
	 *            the name of the property to listen on.
	 * 
	 * @param listener
	 *            the {@link PropertyChangeListener} to be added.
	 * 
	 * @see #PROPERTY_SELECTED_CHILD
	 * @see #PROPERTY_CHILDREN_MODEL
	 * @see #PROPERTY_CHILDREN_PANELS
	 */
	void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener);

	/**
	 * <p>
	 * Remove a PropertyChangeListener from the listener list.
	 * </p>
	 * <p>
	 * This removes a PropertyChangeListener that was registered for all
	 * properties. If <code>listener</code> was added more than once to the same
	 * event source, it will be notified one less time after being removed. If
	 * <code>listener</code> is null, or was never added, no exception is thrown
	 * and no action is taken.
	 * </p>
	 * 
	 * @param listener
	 *            the {@link PropertyChangeListener} to be removed.
	 * 
	 * @see #PROPERTY_SELECTED_CHILD
	 * @see #PROPERTY_CHILDREN_MODEL
	 * @see #PROPERTY_CHILDREN_PANELS
	 */
	void removePropertyChangeListener(PropertyChangeListener listener);

	/**
	 * <p>
	 * Remove a PropertyChangeListener for a specific property.
	 * </p>
	 * <p>
	 * If <code>listener</code> was added more than once to the same event
	 * source for the specified property, it will be notified one less time
	 * after being removed. If <code>propertyName</code> is null, no exception
	 * is thrown and no action is taken. If <code>listener</code> is null, or
	 * was never added for the specified property, no exception is thrown and no
	 * action is taken.
	 * </p>
	 * 
	 * @param propertyName
	 *            the name of the property that was listened on.
	 * 
	 * @param listener
	 *            the {@link PropertyChangeListener} to be removed.
	 * 
	 * @see #PROPERTY_SELECTED_CHILD
	 * @see #PROPERTY_CHILDREN_MODEL
	 * @see #PROPERTY_CHILDREN_PANELS
	 */
	void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener);

}
