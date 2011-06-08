package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

public interface TextFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	FieldHandler<?> create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
