package com.globalscalingsoftware.prefdialog.panel.inputfields.child.group;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

public interface GroupFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	GroupFieldHandler create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
