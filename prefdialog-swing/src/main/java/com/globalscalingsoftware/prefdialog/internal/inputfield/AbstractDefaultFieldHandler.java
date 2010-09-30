package com.globalscalingsoftware.prefdialog.internal.inputfield;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.AbstractFieldHandler;
import com.globalscalingsoftware.prefdialog.FieldComponent;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;

public abstract class AbstractDefaultFieldHandler<FieldComponentType extends FieldComponent>
		extends AbstractFieldHandler<FieldComponentType> {

	private ReflectionToolbox reflectionToolbox;

	public AbstractDefaultFieldHandler(Object parentObject, Object value,
			Field field, Class<? extends Annotation> annotationClass,
			FieldComponentType component) {
		super(parentObject, value, field, annotationClass, component);
	}

	@Override
	public void setup() {
		super.setup();
		setupComponent();
	}

	private void setupComponent() {
		Field field = getField();
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		setupComponentWidth(field, annotationClass);
		setupComponentName(field, annotationClass);
	}

	private void setupComponentName(Field field,
			Class<? extends Annotation> annotationClass) {
		String name = getValueFromAnnotationIn("value", String.class, field,
				annotationClass);
		name = defaultNameIfNameNotSet(name, field);
		setComponentName(name);
	}

	private String defaultNameIfNameNotSet(String name, Field field) {
		if (name.isEmpty()) {
			name = field.getName();
		}
		return name;
	}

	private void setupComponentWidth(Field field,
			Class<? extends Annotation> annotationClass) {
		double width = getValueFromAnnotationIn("width", Double.class, field,
				annotationClass);
		setComponentWidth(width);
	}

	private <T> T getValueFromAnnotationIn(String name, Class<T> returnType,
			Field field, Class<? extends Annotation> annotationClass) {
		Annotation a = field.getAnnotation(annotationClass);
		return getReflectionToolbox().invokeMethodWithReturnType(name,
				returnType, a);
	}

	public void setReflectionToolbox(ReflectionToolbox reflectionToolbox) {
		this.reflectionToolbox = reflectionToolbox;
	}

	public ReflectionToolbox getReflectionToolbox() {
		return reflectionToolbox;
	}

	@Override
	public void applyInput(Object parent) {
		Object value = getComponentValue();
		Field field = getField();
		getReflectionToolbox().setValueTo(field, parent, value);
	}

	@Override
	public void restoreInput(Object parent) {
		// TODO
	}
}
