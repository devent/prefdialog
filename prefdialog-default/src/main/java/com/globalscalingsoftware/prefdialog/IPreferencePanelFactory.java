package com.globalscalingsoftware.prefdialog;

import java.lang.reflect.Field;

import com.google.inject.assistedinject.Assisted;

public interface IPreferencePanelFactory {

	IPreferencePanelController create(
			@Assisted("parentValue") Object parentValue,
			@Assisted("field") Field field);
}
