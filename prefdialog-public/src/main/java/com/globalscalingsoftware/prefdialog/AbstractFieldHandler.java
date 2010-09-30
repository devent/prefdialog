package com.globalscalingsoftware.prefdialog;

import java.awt.Component;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public abstract class AbstractFieldHandler<FieldComponentType extends FieldComponent>
		implements FieldHandler<FieldComponentType> {

	private final Object parentObject;
	private final Object value;
	private final Field field;
	private final Class<? extends Annotation> annotationClass;
	private final FieldComponentType component;

	public AbstractFieldHandler(Object parentObject, Object value, Field field,
			Class<? extends Annotation> annotationClass,
			FieldComponentType component) {
		this.parentObject = parentObject;
		this.value = value;
		this.field = field;
		this.annotationClass = annotationClass;
		this.component = component;
	}

	@Override
	public void setup() {
		setComponentName(field.getName());
		setComponentValue(value);
	}

	protected FieldComponentType getComponent() {
		return component;
	}

	protected Class<? extends Annotation> getAnnotationClass() {
		return annotationClass;
	}

	protected Field getField() {
		return field;
	}

	protected Object getParentObject() {
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
