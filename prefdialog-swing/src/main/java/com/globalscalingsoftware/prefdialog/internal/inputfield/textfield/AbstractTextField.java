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
package com.globalscalingsoftware.prefdialog.internal.inputfield.textfield;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;
import com.globalscalingsoftware.prefdialog.validators.Validator;

public abstract class AbstractTextField extends
		AbstractDefaultFieldHandler<TextFieldPanel> {

	private String validatorText;

	public AbstractTextField(Object parentObject, Object value, Field field,
			Class<? extends Annotation> annotationClass,
			ValidatingTextField<?> textField) {
		super(parentObject, value, field, annotationClass, new TextFieldPanel(
				textField));
	}

	@Override
	public void setup() {
		super.setup();
		validatorText = getValidatorTextFromFieldAnnotation();
		setupValidatorFromFieldAnnotation();
		setupValidListenerToComponent();
	}

	private String getValidatorTextFromFieldAnnotation() {
		Annotation a = getField().getAnnotation(getAnnotationClass());
		ReflectionToolbox reflectionToolbox = getReflectionToolbox();
		String text = reflectionToolbox.invokeMethodWithReturnType(
				"validatorText", String.class, a);
		return text;
	}

	private void setupValidListenerToComponent() {
		getComponent().addValidListener(new ValidListener() {

			@Override
			public void validChanged(ValidEvent validEvent) {
				if (validEvent.isEditValid()) {
					getComponent().clearValidatorText();
				} else {
					String validatorText = getValidatorText();
					getComponent().setValidatorText(validatorText);
				}
			}
		});
	}

	protected String getValidatorText() {
		return validatorText;
	}

	private void setupValidatorFromFieldAnnotation() {
		Annotation a = getField().getAnnotation(getAnnotationClass());
		Object validator = createValidator(a);
		getComponent().setValidator((Validator<?>) validator);
	}

	private Object createValidator(Annotation a) {
		ReflectionToolbox reflectionToolbox = getReflectionToolbox();
		Class<?> validatorClass = reflectionToolbox.invokeMethodWithReturnType(
				"validator", Class.class, a);
		Object validator = reflectionToolbox.newInstance(validatorClass);
		return validator;
	}

	@Override
	public Object getComponentValue() {
		return getComponent().getValue();
	}

}
