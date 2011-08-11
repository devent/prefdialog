package com.globalscalingsoftware.prefdialog.panel.inputfield.child;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

public interface ChildFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	ChildFieldHandler create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
