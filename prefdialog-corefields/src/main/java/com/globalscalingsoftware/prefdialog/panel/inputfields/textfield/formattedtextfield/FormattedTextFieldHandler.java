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
package com.globalscalingsoftware.prefdialog.panel.inputfields.textfield.formattedtextfield;

import java.lang.reflect.Field;
import java.util.Map;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;

import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.panel.inputfields.textfield.shared.AbstractTextFieldHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

/**
 * Sets the {@link ValidatingFormattedTextField} as the managed component.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class FormattedTextFieldHandler extends AbstractTextFieldHandler {

	private final Map<Class<?>, String> validatorTexts;

	@Inject
	FormattedTextFieldHandler(
			@Named("ValidatorTexts") Map<Class<?>, String> validatorTexts,
			@Named("ValidatorFormats") Map<Class<?>, AbstractFormatterFactory> validatorFormats,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(parentObject, value, field, FormattedTextField.class,
				new ValidatingFormattedTextField(createTextField(field,
						validatorFormats)));
		this.validatorTexts = validatorTexts;
	}

	private static JFormattedTextField createTextField(Field field,
			Map<Class<?>, AbstractFormatterFactory> validatorFormats) {
		return new JFormattedTextField(validatorFormats.get(field.getType()));
	}

	@Override
	protected String getValidatorText() {
		String text = super.getValidatorText();
		return isTextEmpty(text) ? getDefaultValidatorText() : text;
	}

	private boolean isTextEmpty(String text) {
		return text.isEmpty();
	}

	private String getDefaultValidatorText() {
		return validatorTexts.get(getField().getType());
	}

}
