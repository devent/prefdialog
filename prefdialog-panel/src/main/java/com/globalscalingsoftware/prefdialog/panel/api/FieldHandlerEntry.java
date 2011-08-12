package com.globalscalingsoftware.prefdialog.panel.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldHandler;

public interface FieldHandlerEntry {

	FieldHandler<?> getFieldHandler();

	Field getField();

	Object getValue();

	Annotation getAnnotation();
}
