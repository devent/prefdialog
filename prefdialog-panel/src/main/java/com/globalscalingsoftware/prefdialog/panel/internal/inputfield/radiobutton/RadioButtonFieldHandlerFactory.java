package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.radiobutton;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

public interface RadioButtonFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	RadioButtonFieldHandler create(
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
