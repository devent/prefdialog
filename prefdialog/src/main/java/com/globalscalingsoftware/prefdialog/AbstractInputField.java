package com.globalscalingsoftware.prefdialog;

import java.awt.Component;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public abstract class AbstractInputField<ComponentType extends InputFieldComponent>
		implements InputField<ComponentType> {

	private final Object parentObject;
	private final Object value;
	private final Field field;
	private final Class<? extends Annotation> annotationClass;
	private final ComponentType component;

	public AbstractInputField(Object parentObject, Object value, Field field,
			Class<? extends Annotation> annotationClass, ComponentType component) {
		this.parentObject = parentObject;
		this.value = value;
		this.field = field;
		this.annotationClass = annotationClass;
		this.component = component;
	}

	@Override
	public abstract void setup();

	@Override
	public ComponentType getComponent() {
		return component;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Class<? extends Annotation> getAnnotationClass() {
		return annotationClass;
	}

	@Override
	public Field getField() {
		return field;
	}

	@Override
	public Object getParentObject() {
		return parentObject;
	}

	@Override
	public void setComponentWidth(double width) {
		component.setWidth(width);
	}

	@Override
	public void setComponentName(String name) {
		component.setName(name);
	}

	@Override
	public void setComponentValue(Object value) {
		component.setValue(value);
	}

	@Override
	public Object getComponentValue() {
		return component.getValue();
	}

	@Override
	public Component getAWTComponent() {
		return component.getAWTComponent();
	}
}
