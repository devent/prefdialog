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
package com.anrisoftware.prefdialog.fields.textfield.validating;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

import javax.swing.JTextField;
import javax.swing.ToolTipManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

import org.apache.commons.lang3.ObjectUtils;

/**
 * Will test the input of a text field and show a tool-tip text if the input is
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
 * @see VetoableChangeListener
 * @see PropertyVetoException
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class ValidatingTextField<TextFieldType extends JTextComponent> {

	/**
	 * Property that the value of the field have been changed. Can be vetoed
	 * before the change takes place.
	 */
	public static final String VALUE_PROPERTY = "value";

	private final VetoableChangeSupport support;

	private final Border oldBorder;

	private final Border highlighBorder;

	private Object value;

	private final TextFieldType field;

	private boolean valueValid;

	private String oldToolTipText;

	private boolean oldToolTipTextSet;

	private String invalidText;

	/**
	 * Sets the {@link JTextField} for with the input will be validated.
	 */
	public ValidatingTextField(TextFieldType field) {
		this.field = field;
		this.support = new VetoableChangeSupport(this);
		this.oldBorder = field.getBorder();
		this.highlighBorder = new LineBorder(Color.red, 1, false);
		this.valueValid = true;
		this.invalidText = field.getToolTipText();
		this.oldToolTipText = null;
		this.oldToolTipTextSet = false;
		setupTextField();
		setupListeners();
	}

	private void setupTextField() {
		int height = field.getPreferredSize().height;
		field.setPreferredSize(new Dimension(200, height));
	}

	private void setupListeners() {
		if (field instanceof JTextField) {
			JTextField textField = (JTextField) field;
			textField.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					validateInput();
				}
			});
		}
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

	private void validateInput() {
		Object oldValue = value;
		Object newValue = getValue();
		try {
			support.fireVetoableChange(VALUE_PROPERTY, oldValue, newValue);
			this.value = newValue;
			this.valueValid = true;
			normalField();
		} catch (PropertyVetoException e) {
			this.valueValid = false;
			this.invalidText = e.getLocalizedMessage();
			highlighField();
		}
	}

	private void restoreValueIfInvalid() {
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
	 * Returns the text field that is tested.
	 * 
	 * @return the {@link JTextComponent}
	 */
	public TextFieldType getField() {
		return field;
	}

	/**
	 * Sets the value to the field.
	 */
	public void setValue(Object value) {
		this.value = value;
		field.setText(ObjectUtils.toString(value));
	}

	/**
	 * Returns the value of the field.
	 * 
	 * @return the {@link Object} value.
	 */
	public Object getValue() {
		return field.getText();
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

	public void setShowToolTip(boolean show) {
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

}
