package com.globalscalingsoftware.prefdialog.internal.inputfield;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.AbstractInputField;
import com.globalscalingsoftware.prefdialog.InputFieldComponent;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;

public abstract class AbstractDefaultInputField<ComponentType extends InputFieldComponent>
		extends AbstractInputField<ComponentType> {

	private ReflectionToolbox reflectionToolbox;

	public AbstractDefaultInputField(Object parentObject, Object value,
			Field field, Class<? extends Annotation> annotationClass,
			ComponentType component) {
		super(parentObject, value, field, annotationClass, component);
	}

	@Override
	public void setup() {
		setComponentName(getField().getName());
		setComponentValue(getValue());
		setupFieldWidth();
	}

	private void setupFieldWidth() {
		Field field = getField();
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		double width = getWidthFromAnnotationIn(field, annotationClass);
		setComponentWidth(width);
	}

	private double getWidthFromAnnotationIn(Field field,
			Class<? extends Annotation> annotationClass) {
		Annotation a = field.getAnnotation(annotationClass);
		return getReflectionToolbox().invokeMethodWithReturnType("width",
				Double.class, a);
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
