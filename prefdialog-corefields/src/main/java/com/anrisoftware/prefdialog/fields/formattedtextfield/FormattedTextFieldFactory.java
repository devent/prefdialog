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
package com.anrisoftware.prefdialog.fields.formattedtextfield;

import java.awt.Container;

import javax.swing.JFormattedTextField;

import com.anrisoftware.prefdialog.fields.FieldFactory;

/**
 * Factory to create a new formatted text field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface FormattedTextFieldFactory extends FieldFactory<Container> {

	/**
	 * Sets the text field for the field.
	 * 
	 * @param textField
	 *            the {@link JFormattedTextField} text field.
	 * 
	 * @return the {@link FormattedTextField}
	 * 
	 * @see FieldFactory#create(java.awt.Component, Object, String)
	 */
	FormattedTextField create(JFormattedTextField textField,
			Container container, Object parentObject, String fieldName);
}
