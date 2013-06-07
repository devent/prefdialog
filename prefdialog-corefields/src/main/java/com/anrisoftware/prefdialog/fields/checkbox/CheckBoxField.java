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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.lang.annotation.Annotation;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.JCheckBox;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.prefdialog.annotations.CheckBox;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.miscswing.components.validating.ValidatingButtonComponent;
import com.anrisoftware.prefdialog.miscswing.components.validating.ValidatingTextComponent;
import com.anrisoftware.resources.texts.api.Texts;
import com.google.inject.assistedinject.Assisted;

/**
 * Check box field. A check box field can only be checked or unchecked.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class CheckBoxField extends AbstractTitleField<JCheckBox> {

	private static final Class<? extends Annotation> ANNOTATION_CLASS = CheckBox.class;

	private static final String TEXT_ELEMENT = "text";

	private static final String SHOW_TEXT_ELEMENT = "showText";

	private final CheckBoxFieldLogger log;

	private final ValidatingButtonComponent<JCheckBox> validating;

	private final VetoableChangeListener valueVetoListener;

	private String textResource;

	private String text;

	private boolean showText;

	private AnnotationAccess fieldAnnotation;

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
		this.valueVetoListener = new VetoableChangeListener() {

			@Override
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				CheckBoxField.super.trySetValue(evt.getNewValue());
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
		setupText();
		setupShowText();
	}

	private void setupText() {
		String text = fieldAnnotation.getValue(TEXT_ELEMENT);
		text = isEmpty(text) ? getFieldName() : text;
		setText(text);
	}

	private void setupShowText() {
		boolean show = fieldAnnotation.getValue(SHOW_TEXT_ELEMENT);
		setShowText(show);
	}

	/**
	 * Sets the text of the check-box. Defaults to the empty string which means
	 * the field name is used as the text.
	 * <p>
	 * The text can also be a resource name that is queried in the supplied
	 * texts resource.
	 * 
	 * @param text
	 *            the text.
	 */
	public void setText(String text) {
		textResource = text;
		this.text = text;
		updateTextResource();
		log.textSet(this, text);
	}

	@Override
	public void setTexts(Texts texts) {
		super.setTexts(texts);
		updateTextsResources();
	}

	private void updateTextsResources() {
		updateTextResource();
	}

	private void updateTextResource() {
		if (haveTextResource(textResource)) {
			text = getTextResource(textResource);
		}
		if (showText) {
			getComponent().setText(text);
		} else {
			getComponent().setText("");
		}
	}

	/**
	 * Returns the text of the check-box.
	 * 
	 * @return the text.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets if the text of the check-box should be visible or not. Defaults to
	 * {@code true} which means that the text should be visible.
	 * 
	 * @param show
	 *            {@code true} for show the text or {@code false} to not show.
	 */
	public void setShowText(boolean show) {
		this.showText = show;
		updateTextResource();
		log.showTextSet(this, show);
	}

	/**
	 * Returns if the text is showing or not.
	 * 
	 * @return {@code true} for show the text or {@code false} to not show.
	 */
	public boolean getShowText() {
		return showText;
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
		validating.setValue(value);
	}

	@Override
	public void setLocale(Locale locale) {
		super.setLocale(locale);
		updateTextsResources();
	}

}
