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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield.shared;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.AbstractLabelFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield.shared.LoggerFactory.Logger;
import com.globalscalingsoftware.prefdialog.reflection.internal.ReflectionToolbox;
import com.globalscalingsoftware.prefdialog.validators.Validator;

public abstract class AbstractTextFieldHandler extends
		AbstractLabelFieldHandler<TextFieldPanel> {

	private String validatorText;

	private final Logger log;

	public AbstractTextFieldHandler(LoggerFactory loggerFactory,
			ReflectionToolbox reflectionToolbox, Object parentObject,
			Object value, Field field,
			Class<? extends Annotation> annotationClass,
			ValidatingTextField<?> textField) {
		super(reflectionToolbox, parentObject, value, field, annotationClass,
				new TextFieldPanel(textField));
		this.log = loggerFactory.create(AbstractTextFieldHandler.class);
		setup();
	}

	private void setup() {
		validatorText = getAnnotationValue("validatorText", String.class);
		setupValidatorFromFieldAnnotation();
		setupValidListenerToComponent();
	}

	private <T> T getAnnotationValue(String name, Class<T> classType) {
		Annotation a = getField().getAnnotation(getAnnotationClass());
		return reflectionToolbox.invokeMethodWithReturnType(name, classType, a);
	}

	private void setupValidatorFromFieldAnnotation() {
		Validator<?> validator = createValidator();
		log.setValidator(getField(), validator);
		getComponent().setValidator(validator);
	}

	private <T> T createValidator() {
		Annotation a = getField().getAnnotation(getAnnotationClass());
		@SuppressWarnings("unchecked")
		Class<? extends T> validatorClass = reflectionToolbox
				.invokeMethodWithReturnType("validator", Class.class, a);
		return reflectionToolbox.newInstance(validatorClass);
	}

	private void setupValidListenerToComponent() {
		getComponent().addValidListener(new ValidListener() {

			@Override
			public void validChanged(ValidEvent validEvent) {
				if (validEvent.isEditValid()) {
					log.editValidClearValidatorText(getField());
					getComponent().clearValidatorText();
				} else {
					String validatorText = getValidatorText();
					log.setValidatorText(getField(), validatorText);
					getComponent().setValidatorText(validatorText);
				}
			}
		});
	}

	protected String getValidatorText() {
		return validatorText;
	}

	@Override
	public Object getComponentValue() {
		return getComponent().getValue();
	}

}
