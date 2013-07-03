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
package com.anrisoftware.prefdialog.fields.textfield;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.lang.annotation.Annotation;

import javax.inject.Inject;
import javax.swing.JTextField;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.miscswing.validatingfields.ValidatingTextComponent;
import com.google.inject.assistedinject.Assisted;

/**
 * Simple text field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class TextField extends AbstractTitleField<JTextField> {

	private static final String EDITABLE_ELEMENT = "editable";

	private static final Class<? extends Annotation> ANNOTATION_CLASS = com.anrisoftware.prefdialog.annotations.TextField.class;

	private final TextFieldLogger log;

	private final ValidatingTextComponent<JTextField> validating;

	private final VetoableChangeListener valueVetoListener;

	private AnnotationAccess fieldAnnotation;

	/**
	 * @see TextFieldFactory#create(Object, String)
	 */
	@Inject
	TextField(TextFieldLogger logger, @Assisted Object parentObject,
			@Assisted String fieldName) {
		super(new JTextField(), parentObject, fieldName);
		this.log = logger;
		this.validating = new ValidatingTextComponent<JTextField>(
				getComponent());
		this.valueVetoListener = new VetoableChangeListener() {

			@Override
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				TextField.super.trySetValue(evt.getNewValue());
				changeValue(evt.getNewValue());
			}
		};
		setupValidating();
	}

	private void setupValidating() {
		validating.addVetoableChangeListener(
				ValidatingTextComponent.VALUE_PROPERTY, valueVetoListener);
	}

	@Inject
	void setBeanAccessFactory(AnnotationAccessFactory annotationAccessFactory) {
		this.fieldAnnotation = annotationAccessFactory.create(ANNOTATION_CLASS,
				getAccessibleObject());
		setupEditable();
	}

	private void setupEditable() {
		boolean editable = fieldAnnotation.getValue(EDITABLE_ELEMENT);
		setEditable(editable);
	}

	@Override
	protected void trySetValue(Object value) throws PropertyVetoException {
		validating.setValue(value);
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
