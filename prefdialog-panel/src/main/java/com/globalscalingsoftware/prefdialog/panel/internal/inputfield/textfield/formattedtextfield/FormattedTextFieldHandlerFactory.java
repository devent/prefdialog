package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield.formattedtextfield;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

public interface FormattedTextFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	FormattedTextFieldHandler create(
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
