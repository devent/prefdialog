package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child.group;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

public interface GroupFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	GroupFieldHandler create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}