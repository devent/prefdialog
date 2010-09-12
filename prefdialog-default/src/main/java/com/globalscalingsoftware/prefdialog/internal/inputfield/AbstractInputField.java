package com.globalscalingsoftware.prefdialog.internal.inputfield;

import java.awt.Component;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;

public abstract class AbstractInputField<ComponentType extends Component & IComponent>
		implements IInputField {

	private final ComponentType component;

	private final IReflectionToolbox reflectionToolbox;

	private final Object value;

	private final Field field;

	private final Object parentObject;

	private final Class<? extends Annotation> annotationClass;

	public AbstractInputField(IReflectionToolbox reflectionToolbox,
			Object parentObject, Object value, Field field,
			Class<? extends Annotation> annotationClass, ComponentType component) {
		this.reflectionToolbox = reflectionToolbox;
		this.parentObject = parentObject;
		this.value = value;
		this.field = field;
		this.annotationClass = annotationClass;
		this.component = component;

		setupComponent();
	}

	private void setupComponent() {
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
		return reflectionToolbox.getFieldName(field);
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
