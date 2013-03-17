package com.anrisoftware.prefdialog.miscswing.docks.api;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Apply a perspective.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface PerspectiveTask {

	/**
	 * Property name of the name property.
	 */
	static final String NAME_PROPERTY = "name";

	/**
	 * Sets the name of the perspective.
	 * <p>
	 * Property change listeners should be informed of the new name.
	 * 
	 * @param name
	 *            the name.
	 * 
	 * @see #NAME_PROPERTY
	 */
	void setName(String name);

	/**
	 * Returns the name of the perspective.
	 * 
	 * @return the name of the perspective.
	 */
	String getName();

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
	 * @see #NAME_PROPERTY
	 */
	void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
	 * @see #NAME_PROPERTY
	 */
	void removePropertyChangeListener(PropertyChangeListener listener);

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(String,
	 *      PropertyChangeListener)
	 * @see #NAME_PROPERTY
	 */
	void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener);

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(String,
	 *      PropertyChangeListener)
	 * @see #NAME_PROPERTY
	 */
	void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener);

}
