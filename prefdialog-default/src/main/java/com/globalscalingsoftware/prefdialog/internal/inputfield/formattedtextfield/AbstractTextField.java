package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.Validator;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultInputField;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;

public abstract class AbstractTextField extends
		AbstractDefaultInputField<TextFieldPanel> {

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
