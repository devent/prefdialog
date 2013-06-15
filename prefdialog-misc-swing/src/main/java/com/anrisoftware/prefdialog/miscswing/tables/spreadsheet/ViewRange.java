package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewRange {

	public static final String OFFSET_PROPERTY = "offset";

	public static final String MAXIMUM_PROPERTY = "maximum";

	private final PropertyChangeSupport propertySupport;

	private int offset;

	private int maximum;

	public ViewRange() {
		this((int) Math.pow(2, 15));
	}

	public ViewRange(int maximum) {
		this.propertySupport = new PropertyChangeSupport(this);
		this.offset = 0;
		this.maximum = maximum;
	}

	public void setOffset(int offset) {
		int oldValue = this.offset;
		this.offset = offset;
		propertySupport.firePropertyChange(OFFSET_PROPERTY, oldValue, offset);
	}

	public int getOffset() {
		return offset;
	}

	public void setMaximum(int maximum) {
		int oldValue = this.maximum;
		this.maximum = maximum;
		propertySupport.firePropertyChange(MAXIMUM_PROPERTY, oldValue, maximum);
	}

	public int getMaximum() {
		return maximum;
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
