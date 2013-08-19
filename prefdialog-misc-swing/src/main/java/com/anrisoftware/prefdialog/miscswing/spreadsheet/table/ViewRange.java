/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.spreadsheet.table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Contains the offset index and the maximum rows that are visible to the user.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class ViewRange {

	/**
	 * Offset index property.
	 * 
	 * @see #setOffset(int)
	 */
	public static final String OFFSET_PROPERTY = "offset";

	/**
	 * Maximum property.
	 * 
	 * @see #setMaximum(int)
	 */
	public static final String MAXIMUM_PROPERTY = "maximum";

	private final PropertyChangeSupport propertySupport;

	private int offset;

	private int maximum;

	private int extendAmount;

	/**
	 * Sets a view range of 2^15=32768.
	 */
	public ViewRange() {
		this((int) Math.pow(2, 15));
	}

	/**
	 * Sets a view range of the specified maximum rows.
	 * 
	 * @param maximum
	 *            the maximum rows of the view.
	 */
	public ViewRange(int maximum) {
		this.propertySupport = new PropertyChangeSupport(this);
		this.offset = 0;
		this.maximum = maximum;
		this.extendAmount = maximum;
	}

	/**
	 * Sets the offset of the view. The property listeners are notified with the
	 * {@link #OFFSET_PROPERTY} property name of the new offset.
	 * 
	 * @param offset
	 *            the offset index.
	 */
	public void setOffset(int offset) {
		int oldValue = this.offset;
		this.offset = offset;
		propertySupport.firePropertyChange(OFFSET_PROPERTY, oldValue, offset);
	}

	/**
	 * Returns the offset of the view.
	 * 
	 * @return the offset index.
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Sets the maximum of the view. The property listeners are notified with
	 * the {@link #MAXIMUM_PROPERTY} property name of the new maximum.
	 * 
	 * @param maximum
	 *            the maximum rows.
	 */
	public void setMaximum(int maximum) {
		int oldValue = this.maximum;
		this.maximum = maximum;
		propertySupport.firePropertyChange(MAXIMUM_PROPERTY, oldValue, maximum);
	}

	/**
	 * Returns the maximum of the view.
	 * 
	 * @return the maximum.
	 */
	public int getMaximum() {
		return maximum;
	}

	/**
	 * Sets how much the view should be extended if the user selects over the
	 * maximum of the view.
	 * 
	 * @param amount
	 *            the amount to extend.
	 */
	public void setExtendAmount(int ammount) {
		this.extendAmount = ammount;
	}

	/**
	 * Returns how much the view should be extended if the user selects over the
	 * maximum of the view.
	 * 
	 * @return the amount to extend.
	 */
	public int getExtendAmount() {
		return extendAmount;
	}

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
	 * @see #OFFSET_PROPERTY
	 * @see #MAXIMUM_PROPERTY
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.addPropertyChangeListener(listener);
	}

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
	 * @see #OFFSET_PROPERTY
	 * @see #MAXIMUM_PROPERTY
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.removePropertyChangeListener(listener);
	}

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(String,
	 *      PropertyChangeListener)
	 * @see #OFFSET_PROPERTY
	 * @see #MAXIMUM_PROPERTY
	 */
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertySupport.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(String,
	 *      PropertyChangeListener)
	 * @see #OFFSET_PROPERTY
	 * @see #MAXIMUM_PROPERTY
	 */
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertySupport.removePropertyChangeListener(propertyName, listener);
	}

}
