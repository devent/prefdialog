package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.IValidator;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractInputField;

public abstract class AbstractTextField extends
		AbstractInputField<TextFieldPanel> {

	private final String validatorText;

	public AbstractTextField(IReflectionToolbox reflectionToolbox,
			Object parentObject, Object value, Field field,
			Class<? extends Annotation> annotationClass,
			ValidatingTextField<?> textField) {
		super(reflectionToolbox, parentObject, value, field, annotationClass,
				new TextFieldPanel(textField));

		validatorText = getValidatorTextFromFieldAnnotation();
		setupComponent();
	}

	private String getValidatorTextFromFieldAnnotation() {
		Annotation a = getField().getAnnotation(getAnnotationClass());
		IReflectionToolbox reflectionToolbox = getReflectionToolbox();
		String text = reflectionToolbox.invokeMethodWithReturnType(
				"validatorText", String.class, a);
		return text;
	}

	private void setupComponent() {
		setupValidatorFromFieldAnnotation();
		setupValidListenerToComponent();
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
		getComponent().setValidator((IValidator<?>) validator);
	}

	private Object createValidator(Annotation a) {
		IReflectionToolbox reflectionToolbox = getReflectionToolbox();
		Class<?> validatorClass = reflectionToolbox.invokeMethodWithReturnType(
				"validator", Class.class, a);
		Object validator = reflectionToolbox.newInstance(validatorClass);
		return validator;
	}

	@Override
	public Object getValue() {
		return getComponent().getValue();
	}

}
