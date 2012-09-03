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

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.awt.Container;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.anrisoftware.prefdialog.annotations.Checkbox;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.google.inject.assistedinject.Assisted;

/**
 * Check box field. A check box field can only be checked or unchecked.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public class CheckboxField extends AbstractTitleField<JCheckBox, Container> {

	private static final Class<? extends Annotation> ANNOTATION_CLASS = Checkbox.class;

	private static final String TEXT_ELEMENT = "text";

	private final CheckboxFieldLogger log;

	private boolean adjusting;

	@Inject
	CheckboxField(CheckboxFieldLogger logger, @Assisted Container container,
			@Assisted Object parentObject, @Assisted Field field) {
		super(new JCheckBox(), container, parentObject, field);
		this.log = logger;
		this.adjusting = false;
		setup();
	}

	private void setup() {
		getComponent().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				adjusting = true;
				setValue(getComponent().isSelected());
				adjusting = false;
			}
		});
	}

	@Override
	public CheckboxField createField() {
		super.createField();
		setupText();
		return this;
	}

	private void setupText() {
		String text = getAnnotationAccess().getValue(ANNOTATION_CLASS,
				getField(), TEXT_ELEMENT);
		text = isEmpty(text) ? getField().getName() : text;
		setText(text);
	}

	public void setText(String newText) {
		getComponent().setText(newText);
		log.textSet(this, newText);
	}

	/**
	 * Sets the boolean value for the check-box.
	 * 
	 * @param newValue
	 *            the new boolean value. {@code true} for a checked check-box
	 *            and {@code false} for unchecked.
	 * 
	 * @throws IllegalArgumentException
	 *             if the value is not a boolean value.
	 */
	@Override
	public void setValue(Object newValue) {
		super.setValue(newValue);
		log.checkValue(this, newValue);
		if (!adjusting) {
			getComponent().setSelected((Boolean) newValue);
		}
	}

}
