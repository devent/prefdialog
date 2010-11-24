/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-public.
 * 
 * prefdialog-public is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-public is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-public. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog;

import java.awt.Component;

/**
 * Setups and Handles outside messages to the underlying {@link FieldComponent}.
 */
public interface FieldHandler<FieldComponentType extends FieldComponent> {

	/**
	 * Setup the underlying {@link FieldComponent}.
	 */
	void setup();

	/**
	 * Sets the width of the component, as specified by the <code>width</code>
	 * annotation type element.
	 */
	void setComponentWidth(double width);

	/**
	 * Sets the name of the component. The name will be used to identify the
	 * component.
	 */
	void setComponentName(String name);

	/**
	 * Sets the title of the component. The title will either be used as text
	 * for the component's label.
	 */
	void setComponentTitle(String title);

	/**
	 * Sets the value of the component. The value must be the same type (or can
	 * be casts/parsed into) as the type of the attribute which this component
	 * represents.
	 */
	void setComponentValue(Object value);

	/**
	 * Returns the value of the component. After the call of
	 * {@link FieldHandler#applyInput(Object)} it should return the new value.
	 * After the call of {@link FieldHandler#restoreInput(Object)} it should
	 * return the old value.
	 */
	Object getComponentValue();

	/**
	 * Returns the AWT {@link Component} which can be added to a swing
	 * container.
	 */
	Component getAWTComponent();

	/**
	 * Apply the user input.
	 * 
	 * @param parent
	 *            the parent object to which this component belongs to.
	 */
	void applyInput(Object parent);

	/**
	 * Restore the value after the user entered some input.
	 * 
	 * @param parent
	 *            the parent object to which this component belongs to.
	 */
	void restoreInput(Object parent);

	/**
	 * Enables or disables the component.
	 */
	void setComponentEnabled(boolean enabled);

}