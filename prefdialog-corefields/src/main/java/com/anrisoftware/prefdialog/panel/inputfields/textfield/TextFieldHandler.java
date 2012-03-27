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
package com.anrisoftware.prefdialog.panel.inputfields.textfield;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.swing.JTextField;

import com.anrisoftware.prefdialog.annotations.TextField;
import com.anrisoftware.prefdialog.panel.inputfields.textfield.shared.AbstractTextFieldHandler;
import com.anrisoftware.prefdialog.panel.inputfields.textfield.shared.ValidatingTextField;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Sets the {@link ValidatingTextField} as the managed component.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public class TextFieldHandler extends AbstractTextFieldHandler {

	/**
	 * Sets the parameter of the {@link TextFieldPanel}.
	 * 
	 * @param parentObject
	 *            the {@link Object} where the field is defined.
	 * 
	 * @param value
	 *            the value of the field.
	 * 
	 * @param field
	 *            the {@link Field}.
	 * 
	 * @param annotationClass
	 *            the {@link Annotation} {@link Class} of the field.
	 * 
	 * @param textField
	 *            the {@link ValidatingTextField} that is manages by this
	 *            handler.
	 */
	@Inject
	TextFieldHandler(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(parentObject, value, field, TextField.class,
				new ValidatingTextField<JTextField>(new JTextField()));
	}

}
