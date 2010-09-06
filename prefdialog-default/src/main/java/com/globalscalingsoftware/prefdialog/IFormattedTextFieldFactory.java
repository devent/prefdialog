package com.globalscalingsoftware.prefdialog;

import java.lang.reflect.Field;

import com.google.inject.assistedinject.Assisted;

public interface IFormattedTextFieldFactory {

	IFormattedTextField createFormattedTextField(
			@Assisted("parentObject") Object parentObject,
			@Assisted("field") Field field, @Assisted("value") Object value);

}
