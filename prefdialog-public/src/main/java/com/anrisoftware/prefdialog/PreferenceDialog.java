package com.anrisoftware.prefdialog;

import java.beans.PropertyChangeListener;

import javax.swing.Action;

/**
 * The preferences dialog, contains the children panel and additional buttons to
 * apply, ok and cancel the dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public interface PreferenceDialog {

	/**
	 * Property identifier that is used if the status of the dialog has been
	 * changed.
	 */
	static final String PROPERTY_STATUS = "preferences_dialog_status";

	/**
	 * The name postfix used for the preferences dialog.
	 */
	static final String DIALOG_NAME_POSTFIX = "preferences-dialog";

	/**
	 * <p>
	 * Sets the name of this dialog.
	 * </p>
	 * <p>
	 * Sets the name of each of the components in this dialog prefixed with the
	 * given name.
	 * </p>
	 * 
	 * @param name
	 *            the given name.
	 */
	void setName(String name);

	/**
	 * Sets a new action of the O.k.-button.
	 * 
	 * @param action
	 *            the {@link Action}.
	 */
	void setOkAction(Action action);

	/**
	 * Sets a new action of the Cancel-button.
	 * 
	 * @param action
	 *            the {@link Action}.
	 */
	void setCancelAction(Action action);

	/**
	 * Sets a new action of the Apply-button.
	 * 
	 * @param action
	 *            the {@link Action}.
	 */
	void setApplyAction(Action action);

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
	 * @see #PROPERTY_STATUS
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
	 * @see #PROPERTY_STATUS
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
	 * @see #PROPERTY_STATUS
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
	 * @see #PROPERTY_STATUS
	 */
	void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener);

}
