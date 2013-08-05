/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
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
package com.anrisoftware.prefdialog.fields.textfield;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyVetoException;
import java.lang.annotation.Annotation;

import javax.inject.Inject;
import javax.swing.JTextField;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.miscswing.validatingfields.ValidatingTextField;
import com.google.inject.assistedinject.Assisted;

/**
 * Text field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class TextField extends AbstractTitleField<JTextField> {

	private static final String EDITABLE_ELEMENT = "editable";

	private static final Class<? extends Annotation> ANNOTATION_CLASS = com.anrisoftware.prefdialog.annotations.TextField.class;

	private final TextFieldLogger log;

	private final ValidatingTextField validating;

	private AnnotationAccess fieldAnnotation;

	private final ActionListener textAction;

	private final FocusAdapter textFocus;

	/**
	 * @see TextFieldFactory#create(Object, String)
	 */
	@Inject
	TextField(TextFieldLogger logger, @Assisted Object parentObject,
			@Assisted String fieldName) {
		super(new ValidatingTextField(), parentObject, fieldName);
		this.log = logger;
		this.validating = (ValidatingTextField) getComponent();
		this.textAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setValue(getComponent().getText());
				} catch (PropertyVetoException e1) {
				}
			}
		};
		this.textFocus = new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				try {
					setValue(getComponent().getText());
				} catch (PropertyVetoException e1) {
				}
			}

		};
	}

	@Inject
	void setBeanAccessFactory(AnnotationAccessFactory annotationAccessFactory) {
		this.fieldAnnotation = annotationAccessFactory.create(ANNOTATION_CLASS,
				getAccessibleObject());
		setupEditable();
		setupTextField();
	}

	private void setupEditable() {
		boolean editable = fieldAnnotation.getValue(EDITABLE_ELEMENT);
		setEditable(editable);
	}

	private void setupTextField() {
		getComponent().addActionListener(textAction);
		getComponent().addFocusListener(textFocus);
	}

	@Override
	public void setValue(Object value) throws PropertyVetoException {
		try {
			super.setValue(value);
			validating.setInputValid(true);
			getComponent().setText(String.valueOf(value));
		} catch (PropertyVetoException e) {
			validating.setInputValid(false);
			throw e;
		}
	}

	/**
	 * Sets if the field should be editable.
	 * 
	 * @param editable
	 *            {@code true} if the text field should be editable or
	 *            {@code false} if not.
	 */
	public void setEditable(boolean editable) {
		getComponent().setEditable(editable);
		log.editableSet(this, editable);
	}

	/**
	 * Returns if the field should is editable.
	 * 
	 * @return {@code true} if the text field is editable or {@code false} if
	 *         not.
	 */
	public boolean isEditable() {
		return getComponent().isEditable();
	}

}
