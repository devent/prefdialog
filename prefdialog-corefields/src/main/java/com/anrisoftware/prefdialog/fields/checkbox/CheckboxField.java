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
import java.util.Locale;
import java.util.MissingResourceException;

import javax.inject.Inject;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.anrisoftware.prefdialog.annotations.Checkbox;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.resources.api.Texts;
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

	private static final String SHOW_TEXT_ELEMENT = "showText";

	private final CheckboxFieldLogger log;

	private boolean adjusting;

	private String textResource;

	private boolean showText;

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
		setupShowText();
		return this;
	}

	private void setupText() {
		String text = getAnnotationAccess().getValue(ANNOTATION_CLASS,
				getField(), TEXT_ELEMENT);
		text = isEmpty(text) ? getField().getName() : text;
		setText(text);
	}

	@Override
	public void setTexts(Texts texts) {
		super.setTexts(texts);
		updateTextsResources();
	}

	private void updateTextsResources() {
		updateTextResource();
	}

	/**
	 * Sets the text of the check-box. Defaults to the empty string which means
	 * the field name is used as the text.
	 * <p>
	 * The text can also be a resource name that is queried in the supplied
	 * texts resource.
	 * 
	 * @param newText
	 *            the text.
	 */
	public void setText(String newText) {
		textResource = newText;
		getComponent().setText(newText);
		updateTextResource();
		log.textSet(this, newText);
	}

	private void updateTextResource() {
		if (isEmpty(textResource) || getTexts() == null) {
			return;
		}
		if (!showText) {
			getComponent().setText("");
			return;
		}
		String text;
		try {
			text = getTexts().getResource(textResource, getLocale()).getText();
		} catch (MissingResourceException e) {
			text = textResource;
			log.textResourceMissing(this, textResource);
		}
		getComponent().setText(text);
	}

	/**
	 * Returns the text of the check-box.
	 * 
	 * @return the text.
	 */
	public String getText() {
		return getComponent().getText();
	}

	private void setupShowText() {
		boolean show = getAnnotationAccess().getValue(ANNOTATION_CLASS,
				getField(), SHOW_TEXT_ELEMENT);
		setShowText(show);
	}

	/**
	 * Sets if the text of the check-box should be visible or not. Defaults to
	 * {@code true} which means that the text should be visible.
	 * 
	 * @param show
	 *            {@code true} for show the text or {@code false} to not show.
	 */
	public void setShowText(boolean show) {
		showText = show;
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

	@Override
	public void setLocale(Locale newLocale) {
		super.setLocale(newLocale);
		updateTextsResources();
	}

}
