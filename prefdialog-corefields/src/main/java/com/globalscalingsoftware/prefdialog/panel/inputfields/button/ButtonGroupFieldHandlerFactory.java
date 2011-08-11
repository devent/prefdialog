package com.globalscalingsoftware.prefdialog.panel.inputfields.button;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

public interface ButtonGroupFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	ButtonGroupFieldHandler create(
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
