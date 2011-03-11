package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.slider;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

public interface SliderFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	SliderFieldHandler create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}