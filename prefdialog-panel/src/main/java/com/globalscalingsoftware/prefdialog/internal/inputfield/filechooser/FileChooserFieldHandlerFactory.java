package com.globalscalingsoftware.prefdialog.internal.inputfield.filechooser;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

public interface FileChooserFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	FileChooserFieldHandler create(
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
