package com.globalscalingsoftware.prefdialog.panel.inputfield.button;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.panel.inputfield.api.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

public interface ButtonGroupFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	ButtonGroupFieldHandler create(
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
