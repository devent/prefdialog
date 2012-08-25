/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-core.
 * 
 * prefdialog-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields;

import java.awt.Component;

/**
 * The preference panel field.
 * 
 * @param <ComponentType>
 *            the {@link Component} for this field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface FieldComponent<ComponentType extends Component> {

	/**
	 * Sets the name the field. The name should be a unique ID of the field
	 * inside the container.
	 * 
	 * @param newName
	 *            the name.
	 * 
	 * @throws NullPointerException
	 *             if the new name is {@code null}.
	 */
	void setName(String newName);

	/**
	 * Returns the name the field.
	 * 
	 * @return the name of the field.
	 */
	String getName();

	/**
	 * Sets the title of the field. The title is visible to the user and do not
	 * have to be unique.
	 * 
	 * @param newTitle
	 *            the title.
	 */
	void setTitle(String newTitle);

	/**
	 * Returns the title of the field.
	 * 
	 * @return the title of the field or {@code null} for no title.
	 */
	String getTitle();

	/**
	 * Sets the value of the field.
	 * 
	 * @param newValue
	 *            the value {@link Object}.
	 * 
	 * @throws NullPointerException
	 *             if the new value is {@code null}.
	 */
	void setValue(Object newValue);

	/**
	 * Returns the value of the field.
	 * 
	 * @return the value {@link Object}.
	 */
	Object getValue();

	/**
	 * Sets if the field is enabled or not. Read-only fields should be disabled
	 * so the user can not enter any input.
	 * 
	 * @param enabled
	 *            {@code true} if the field is enabled, {@code false} if not.
	 */
	void setEnabled(boolean enabled);

	/**
	 * Returns if the field is enabled or not.
	 * 
	 * @return {@code true} if the field is enabled, {@code false} if not.
	 */
	boolean isEnabled();

	/**
	 * Sets the physical width of the field inside the container. The width can
	 * be in pixels, a percentage or some special constant.
	 * 
	 * @param newWidth
	 *            the width {@link Number}.
	 * 
	 * @throws NullPointerException
	 *             if the new width is {@code null}.
	 */
	void setWidth(Number newWidth);

	/**
	 * Returns the physical width of the field inside the container. The width
	 * can be in pixels, a percentage or some special constant.
	 * 
	 * @return the {@link Number} width.
	 */
	Number getWidth();

	/**
	 * Set the text of the tool-tip for the component.
	 * 
	 * @param text
	 *            the text or {@code null} if the tool-tip should be disabled.
	 */
	void setToolTipText(String text);

	/**
	 * Show or hide the tool-tip for the component.
	 * 
	 * @param {@code true} to show the tool-tip, {@code false} to hide the
	 *        tool-tip.
	 */
	void setShowToolTip(boolean show);

	/**
	 * Tests if the current input for the field is valid.
	 * 
	 * @return {@code true} if the current input is valid or {@code false} if it
	 *         is not valid.
	 */
	boolean isInputValid();

	/**
	 * Returns the component to be added in the preferences container for this
	 * field.
	 * 
	 * @return the {@link Component}.
	 */
	ComponentType getAWTComponent();

	/**
	 * Add a new child field component to this field.
	 * 
	 * @param field
	 *            the {@link FieldComponent} to add.
	 * 
	 * @throws NullPointerException
	 *             if the field is {@code null}.
	 */
	void addField(FieldComponent<?> field);

	/**
	 * Returns the field component that is in this field with the specified
	 * name.
	 * 
	 * @param name
	 *            the name of the field.
	 * 
	 * @param <T>
	 *            the type of the {@link Component}.
	 * 
	 * @return the {@link FieldComponent} with the specified name.
	 * 
	 * @throws NullPointerException
	 *             if no field component with the specified name was found in
	 *             this field.
	 */
	<T extends Component> FieldComponent<T> getField(String name);

}
