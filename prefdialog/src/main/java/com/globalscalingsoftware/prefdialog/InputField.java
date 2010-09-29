package com.globalscalingsoftware.prefdialog;

import java.awt.Component;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface InputField<ComponentType extends InputFieldComponent> {

	void setup();

	ComponentType getComponent();

	Object getValue();

	Class<? extends Annotation> getAnnotationClass();

	Field getField();

	Object getParentObject();

	void setComponentWidth(double width);

	void setComponentName(String name);

	void setComponentValue(Object value);

	Object getComponentValue();

	Component getAWTComponent();

	void applyInput(Object parent);
}