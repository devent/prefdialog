package com.globalscalingsoftware.prefdialog.internal.inputfield;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import com.globalscalingsoftware.annotations.Stateless;
import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.internal.reflection.FieldsAnnotationFilter;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;
import com.google.inject.Inject;

@Stateless
public class FieldsFactory {

	private final ReflectionToolbox reflectionToolbox;

	private final Map<Class<? extends Annotation>, Class<? extends FieldHandler<?>>> inputFieldImplementations;

	private final FieldsAnnotationFilter annotationFilter;

	@Inject
	FieldsFactory(FieldsAnnotationFilter annotationFilter,
			ReflectionToolbox reflectionToolbox) {
		this.annotationFilter = annotationFilter;
		this.reflectionToolbox = reflectionToolbox;
		this.inputFieldImplementations = annotationFilter
				.getFieldsImplementations();
	}

	public FieldHandler<?> createField(Object parentObject, Field field,
			Object value) {
		Class<? extends FieldHandler<?>> inputFieldClass = getInputFieldClassFrom(field);
		if (inputFieldClass == null) {
			return null;
		} else {
			return createInputField(parentObject, value, field, inputFieldClass);
		}
	}

	private Class<? extends FieldHandler<?>> getInputFieldClassFrom(Field field) {
		Class<? extends Annotation> a = getInputFieldAnnotationFrom(field);
		Class<? extends FieldHandler<?>> c = inputFieldImplementations.get(a);
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

	private FieldHandler<?> createInputField(Object parentObject, Object value,
			Field field, Class<? extends FieldHandler<?>> inputFieldClass) {
		Class<?>[] parameterTypes = new Class<?>[] { Object.class,
				Object.class, Field.class };
		FieldHandler<?> inputField = reflectionToolbox.newInstance(
				inputFieldClass, parameterTypes, parentObject, value, field);
		return inputField;
	}

}
