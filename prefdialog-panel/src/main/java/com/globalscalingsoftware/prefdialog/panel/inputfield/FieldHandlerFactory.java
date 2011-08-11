package com.globalscalingsoftware.prefdialog.panel.inputfield;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.google.inject.assistedinject.Assisted;

public interface FieldHandlerFactory {

	FieldHandler<?> create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
