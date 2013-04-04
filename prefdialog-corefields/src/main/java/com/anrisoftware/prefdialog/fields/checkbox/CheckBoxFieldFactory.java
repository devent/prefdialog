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

import java.awt.Container;

import javax.swing.JCheckBox;

import com.anrisoftware.prefdialog.fields.FieldFactory;

/**
 * Factory to create a new check box field. A check box field can only be
 * checked or unchecked.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface CheckBoxFieldFactory extends FieldFactory<Container> {

	/**
	 * Sets the check box component for the field.
	 * 
	 * @param checkBox
	 *            the {@link JCheckBox} component.
	 * 
	 * @return the {@link CheckBoxField}
	 * 
	 * @see FieldFactory#create(java.awt.Component, Object, String)
	 */
	CheckBoxField create(JCheckBox checkBox, Container container,
			Object parentObject, String fieldName);
}
