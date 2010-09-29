package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import com.globalscalingsoftware.annotations.Stateless;
import com.globalscalingsoftware.prefdialog.InputField;
import com.google.inject.Inject;

@Stateless
public class FieldsFactory {

	private final InputFieldsFactory inputFieldFactory;

	private final Map<Class<? extends Annotation>, Class<? extends InputField<?>>> inputFieldImplementations;

	private final FieldsAnnotationFilter annotationFilter;

	@Inject
	FieldsFactory(FieldsAnnotationFilter annotationFilter,
			InputFieldsFactory inputFieldFactory) {
		this.annotationFilter = annotationFilter;
		this.inputFieldFactory = inputFieldFactory;
		this.inputFieldImplementations = annotationFilter
				.getFieldsImplementations();
	}

	public InputField<?> createField(Object parentObject, Field field,
			Object value) {
		Class<? extends InputField<?>> inputFieldClass = getInputFieldClassFrom(field);
		if (inputFieldClass == null) {
			return null;
		} else {
			return createInputField(parentObject, value, field, inputFieldClass);
		}
	}

	private Class<? extends InputField<?>> getInputFieldClassFrom(Field field) {
		Class<? extends Annotation> a = getInputFieldAnnotationFrom(field);
		Class<? extends InputField<?>> c = inputFieldImplementations.get(a);
		return c;
	}

	private Class<? extends Annotation> getInputFieldAnnotationFrom(Field field) {
		Annotation[] annotations = field.getAnnotations();
		for (Annotation a : annotations) {
			if (annotationFilter.accept(a)) {
				return a.annotationType();
			}
		}
		return null;
	}

	private InputField<?> createInputField(Object parentObject, Object value,
			Field field, Class<? extends InputField<?>> inputFieldClass) {
		InputField<?> inputField = inputFieldFactory.create(inputFieldClass,
				parentObject, value, field);
		return inputField;
	}

}
