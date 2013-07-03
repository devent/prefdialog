/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.validatingfields;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.ToolTipManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Will test the input of a component and show a tool-tip text if the input is
 * not valid.
 * <p>
 * The validity of the value is determined by the vetoable property change
 * listeners. Each of the listeners are informed of the new value and if one of
 * the listeners throws a {@link PropertyVetoException} the value is invalid.
 * 
 * <p>
 * Example:
 * 
 * <pre>
 * field = new ValidatingTextField(new JTextField());
 * addVetoableChangeListener(new VetoableChangeListener() {
 * 
 * 	&#064;Override
 * 	public void vetoableChange(PropertyChangeEvent evt)
 * 			throws PropertyVetoException {
 * 		if (evt.getNewValue() != validValue) {
 * 			throw new PropertyVetoException(mess, evt);
 * 		}
 * 	}
 * });
 * 
 * panel.add(field.getField(), BorderLayout.CENTER);
 * </pre>
 * 
 * @param <ValueType>
 *            the type of the value.
 * 
 * @param <ComponentType>
 *            the type of the {@link JComponent}.
 * 
 * @see VetoableChangeListener
 * @see PropertyVetoException
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public abstract class AbstractValidatingComponent<ValueType, ComponentType extends JComponent>
		implements Serializable {

	/**
	 * Property that the tool-tip of the component is shown.
	 */
	public static final String SHOW_TOOL_TIP_PROEPRTY = "showToolTip";

	/**
	 * Property that the value of the field have been changed. Can be vetoed
	 * before the change takes place.
	 */
	public static final String VALUE_PROPERTY = "value";

	private final VetoableChangeSupport support;

	private final PropertyChangeSupport properties;

	private final Border oldBorder;

	private final Border highlighBorder;

	private ValueType value;

	private final ComponentType field;

	private boolean valueValid;

	private String oldToolTipText;

	private boolean oldToolTipTextSet;

	private String invalidText;

	/**
	 * Sets the {@link JComponent} for with the input will be validated.
	 */
	public AbstractValidatingComponent(ComponentType field) {
		this.properties = new PropertyChangeSupport(this);
		this.field = field;
		this.support = new VetoableChangeSupport(this);
		this.oldBorder = field.getBorder();
		this.highlighBorder = new LineBorder(Color.red, 1, false);
		this.valueValid = true;
		this.invalidText = field.getToolTipText();
		this.oldToolTipText = null;
		this.oldToolTipTextSet = false;
		this.invalidText = null;
		setupListeners();
	}

	private void setupListeners() {
		field.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				validateInput();
			}

			@Override
			public void focusLost(FocusEvent e) {
				validateInput();
				restoreValueIfInvalid();
			}

		});
		field.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				validateInput();
			}

			@Override
			public void componentShown(ComponentEvent e) {
				validateInput();
			}
		});
	}

	/**
	 * Validates the input.
	 */
	protected void validateInput() {
		ValueType oldValue = value;
		ValueType newValue = getValue();
		try {
			support.fireVetoableChange(VALUE_PROPERTY, oldValue, newValue);
			this.value = newValue;
			setValueValid(true);
		} catch (PropertyVetoException e) {
			setValueValid(false);
			if (invalidText == null) {
				setInvalidText(e.getLocalizedMessage());
			}
		}
	}

	protected void restoreValueIfInvalid() {
		if (!valueValid) {
			setValue(value);
		}
	}

	private void highlighField() {
		field.setBorder(highlighBorder);
		setShowToolTip(true);
	}

	private void normalField() {
		field.setBorder(oldBorder);
		setShowToolTip(false);
	}

	/**
	 * Returns the text component that is tested.
	 * 
	 * @return the {@link JComponent}
	 */
	public ComponentType getComponent() {
		return field;
	}

	/**
	 * Sets the value to the component.
	 * 
	 * @param value
	 *            the {@link Object} value.
	 */
	public void setValue(ValueType value) {
		setComponentValue(value);
		validateInput();
	}

	/**
	 * Sets the value to the component.
	 * 
	 * @param value
	 *            the {@link Object} value.
	 */
	protected abstract void setComponentValue(ValueType value);

	/**
	 * Returns the value of the component.
	 * 
	 * @return the {@link Object} value.
	 */
	public ValueType getValue() {
		return getComponentValue();
	}

	/**
	 * Returns the value of the component.
	 * 
	 * @return the {@link Object} value.
	 */
	protected abstract ValueType getComponentValue();

	/**
	 * Set if the user input is valid.
	 * 
	 * @param valid
	 *            set to {@code true} if the input is valid, or {@code false} if
	 *            not.
	 */
	public void setValueValid(boolean valid) {
		this.valueValid = valid;
		if (valid) {
			normalField();
		} else {
			highlighField();
		}
	}

	/**
	 * Returns if the current value is valid. The validity of the value is
	 * determined by the change listeners.
	 * 
	 * @return {@code true} if it is valid or {@code false} if not valid.
	 */
	public boolean isValueValid() {
		return valueValid;
	}

	/**
	 * Set the text that is shown if the user input is invalid.
	 * 
	 * @param text
	 *            the {@link String} text.
	 */
	public void setInvalidText(String text) {
		this.invalidText = text;
	}

	/**
	 * Set if the tool-tip of the field should be shown.
	 * 
	 * @param show
	 *            set to {@code true} to show the tool-tip or to {@code false}
	 *            to hide.
	 */
	public void setShowToolTip(boolean show) {
		properties.firePropertyChange(SHOW_TOOL_TIP_PROEPRTY, null, show);
		if (show) {
			showToolTip();
		} else if (!show) {
			hideToolTip();
		}
	}

	private void showToolTip() {
		if (!oldToolTipTextSet) {
			oldToolTipText = field.getToolTipText();
			oldToolTipTextSet = true;
		}
		field.setToolTipText(invalidText);
		int id = 0;
		long when = 0;
		int modifiers = 0;
		int x = 0;
		int y = 0;
		int clickCount = 0;
		ToolTipManager.sharedInstance().mouseMoved(
				new MouseEvent(field, id, when, modifiers, x, y, clickCount,
						false));
	}

	private void hideToolTip() {
		field.setToolTipText(oldToolTipText);
		oldToolTipText = null;
		oldToolTipTextSet = false;
		int id = 0;
		long when = 0;
		int modifiers = 0;
		int x = 0;
		int y = 0;
		int clickCount = 0;
		ToolTipManager.sharedInstance().mouseExited(
				new MouseEvent(field, id, when, modifiers, x, y, clickCount,
						false));
	}

	/**
	 * @see VetoableChangeSupport#addVetoableChangeListener(VetoableChangeListener)
	 * @see #VALUE_PROPERTY
	 */
	public void addVetoableChangeListener(VetoableChangeListener listener) {
		support.addVetoableChangeListener(listener);
	}

	/**
	 * @see VetoableChangeSupport#removeVetoableChangeListener(VetoableChangeListener)
	 * @see #VALUE_PROPERTY
	 */
	public void removeVetoableChangeListener(VetoableChangeListener listener) {
		support.removeVetoableChangeListener(listener);
	}

	/**
	 * @see VetoableChangeSupport#addVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 * @see #VALUE_PROPERTY
	 */
	public void addVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		support.addVetoableChangeListener(propertyName, listener);
	}

	/**
	 * @see VetoableChangeSupport#removeVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 * @see #VALUE_PROPERTY
	 */
	public void removeVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		support.removeVetoableChangeListener(propertyName, listener);
	}

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
	 * @see #SHOW_TOOL_TIP_PROEPRTY
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		properties.addPropertyChangeListener(listener);
	}

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
	 * @see #SHOW_TOOL_TIP_PROEPRTY
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		properties.removePropertyChangeListener(listener);
	}

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(String,
	 *      PropertyChangeListener)
	 * @see #SHOW_TOOL_TIP_PROEPRTY
	 */
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		properties.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(String,
	 *      PropertyChangeListener)
	 * @see #SHOW_TOOL_TIP_PROEPRTY
	 */
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		properties.removePropertyChangeListener(propertyName, listener);
	}

}
