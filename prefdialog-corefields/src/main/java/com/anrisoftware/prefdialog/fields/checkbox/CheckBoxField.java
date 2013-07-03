/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-corefields.
 * 
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.checkbox;

import static com.anrisoftware.prefdialog.miscswing.lockedevents.LockedVetoableChangeListener.lockedVetoableChangeListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import javax.inject.Inject;
import javax.swing.JCheckBox;

import com.anrisoftware.prefdialog.annotations.CheckBox;
import com.anrisoftware.prefdialog.fields.fieldbutton.AbstractFieldButtonField;
import com.anrisoftware.prefdialog.miscswing.lockedevents.LockedVetoableChangeListener;
import com.anrisoftware.prefdialog.miscswing.validatingfields.ValidatingButtonComponent;
import com.anrisoftware.prefdialog.miscswing.validatingfields.ValidatingTextComponent;
import com.google.inject.assistedinject.Assisted;

/**
 * Check box field.
 * 
 * @see CheckBox
 * @see CheckBoxFieldFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class CheckBoxField extends AbstractFieldButtonField<JCheckBox> {

	private final CheckBoxFieldLogger log;

	private final ValidatingButtonComponent<JCheckBox> validating;

	private final LockedVetoableChangeListener valueVetoListener;

	/**
	 * @see CheckBoxFieldFactory#create(Object, String)
	 */
	@Inject
	CheckBoxField(CheckBoxFieldLogger logger, @Assisted Object parentObject,
			@Assisted String fieldName) {
		super(new JCheckBox(), parentObject, fieldName);
		JCheckBox checkBox = getComponent();
		this.validating = new ValidatingButtonComponent<JCheckBox>(checkBox);
		this.log = logger;
		this.valueVetoListener = lockedVetoableChangeListener(new VetoableChangeListener() {

			@Override
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				valueVetoListener.lock();
				setValue(evt.getNewValue());
				valueVetoListener.unlock();
			}

		});
		setupValidating();
	}

	private void setupValidating() {
		validating.addVetoableChangeListener(
				ValidatingTextComponent.VALUE_PROPERTY, valueVetoListener);
	}

	/**
	 * Sets the boolean value for the check-box.
	 * 
	 * @param value
	 *            the new boolean value. {@code true} for a checked check-box
	 *            and {@code false} for unchecked.
	 * 
	 * @throws PropertyVetoException
	 *             if the user input is not valid.
	 * 
	 * @throws IllegalArgumentException
	 *             if the value is not a boolean value.
	 */
	@Override
	public void setValue(Object value) throws PropertyVetoException {
		super.setValue(value);
		log.checkValue(this, value);
		valueVetoListener.lock();
		validating.setValue(value);
		valueVetoListener.unlock();
	}
}
