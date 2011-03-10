package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.checkbox;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

public interface CheckBoxFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	CheckBoxFieldHandler create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
