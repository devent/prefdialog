package com.globalscalingsoftware.prefdialog.panel.inputfield.radiobutton;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfield.api.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

public interface RadioButtonFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	FieldHandler<?> create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
