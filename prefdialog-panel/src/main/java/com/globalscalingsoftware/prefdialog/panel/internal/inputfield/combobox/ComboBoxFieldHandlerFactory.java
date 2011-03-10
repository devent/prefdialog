package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.combobox;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

public interface ComboBoxFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	ComboBoxFieldHandler create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
