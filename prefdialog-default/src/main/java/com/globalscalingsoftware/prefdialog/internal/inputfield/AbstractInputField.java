package com.globalscalingsoftware.prefdialog.internal.inputfield;

import java.awt.Component;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;

public abstract class AbstractInputField<ComponentType extends Component & IComponent>
		implements IInputField {

	private IReflectionToolbox reflectionToolbox;

	private final ComponentType component;

	private final Object value;

	private final Field field;

	private final Object parentObject;

	private final Class<? extends Annotation> annotationClass;

	public AbstractInputField(Object parentObject, Object value, Field field,
			Class<? extends Annotation> annotationClass, ComponentType component) {
		this.parentObject = parentObject;
		this.value = value;
		this.field = field;
		this.annotationClass = annotationClass;
		this.component = component;
	}

	@Override
	public void setup() {
		component.setFieldName(getFieldName());
		component.setValue(value);
		setupFieldWidth();
	}

	private void setupFieldWidth() {
		double width = getWidthFromAnnotationIn(field, annotationClass);
		component.setFieldWidth(width);
	}

	private double getWidthFromAnnotationIn(Field field,
			Class<? extends Annotation> annotationClass) {
		Annotation a = field.getAnnotation(annotationClass);
		return getReflectionToolbox().invokeMethodWithReturnType("width",
				Double.class, a);
	}

	public void setReflectionToolbox(IReflectionToolbox reflectionToolbox) {
		this.reflectionToolbox = reflectionToolbox;
	}

	public IReflectionToolbox getReflectionToolbox() {
		return reflectionToolbox;
	}

	public Object getParentObject() {
		return parentObject;
	}

	public Field getField() {
		return field;
	}

	public String getFieldName() {
		return field.getName();
	}

	public Class<? extends Annotation> getAnnotationClass() {
		return annotationClass;
	}

	@Override
	public Object getValue() {
		return getComponent().getValue();
	}

	@Override
	public ComponentType getComponent() {
		return component;
	}

}
