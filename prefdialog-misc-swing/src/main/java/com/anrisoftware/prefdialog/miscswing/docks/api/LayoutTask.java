/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.docks.api;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Apply the layout.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface LayoutTask {

	/**
	 * Property name of the name property.
	 */
	static final String NAME_PROPERTY = "name";

	/**
	 * Sets the name of the layout.
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
	 * Returns the name of the layout.
	 * 
	 * @return the name of the layout.
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
