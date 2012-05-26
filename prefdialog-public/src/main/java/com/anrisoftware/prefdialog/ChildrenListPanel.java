package com.anrisoftware.prefdialog;

import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;

/**
 * The panel that contains a list of all children in the preferences dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public interface ChildrenListPanel {

	/**
	 * Property identifier that is used if a new child was selected in the
	 * panel.
	 */
	static final String PROPERTY_SELECTED_CHILD = "selected_child";

	/**
	 * The name postfix used for the children list panel.
	 */
	static final String PANEL_NAME_POSTFIX = "children-list-panel";

	/**
	 * Returns the {@link JPanel} that contains a list of all preferences
	 * children.
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
	 * Sets the selected child node in the tree.
	 * 
	 * @param path
	 *            an array of {@link TreeNode}s that are the path to that child
	 *            node.
	 */
	void setSelectedChild(TreeNode[] path);

	/**
	 * Sets the selected child node in the tree.
	 * 
	 * @param child
	 *            the child {@link Object} in the tree that we need to select.
	 */
	void setSelectedChild(Object child);

	/**
	 * Returns the child {@link Object} that is currently selected, or
	 * <code>null</code> if no child object is selected.
	 */
	Object getSelectedChild();

	/**
	 * Sets a new renderer that displays the children notes in the tree.
	 * 
	 * @param renderer
	 *            the {@link TreeCellRenderer} to set.
	 */
	void setChildRenderer(TreeCellRenderer renderer);

	/**
	 * Returns the currently set renderer that displays the children notes in
	 * the tree.
	 * 
	 * @return the set {@link TreeCellRenderer}.
	 * 
	 * @see #setChildRenderer(TreeCellRenderer)
	 */
	TreeCellRenderer getChildRenderer();

	/**
	 * Adds a new child node to the tree.
	 * 
	 * @param node
	 *            the {@link DefaultMutableTreeNode}.
	 */
	void addChildNode(DefaultMutableTreeNode node);

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
	 */
	void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener);

}
