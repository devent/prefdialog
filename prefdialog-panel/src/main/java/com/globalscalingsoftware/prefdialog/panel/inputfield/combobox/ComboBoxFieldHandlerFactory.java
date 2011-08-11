package com.globalscalingsoftware.prefdialog.panel.inputfield.combobox;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfield.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

public interface ComboBoxFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	FieldHandler<?> create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
