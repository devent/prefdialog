package com.globalscalingsoftware.prefdialog.panel.inputfields.api;

import java.lang.reflect.Field;

import javax.swing.JRadioButton;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new radio button {@link FieldHandler}. It uses the
 * {@link JRadioButton} component to give the user a unque choice in a group of
 * buttons.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public interface RadioButtonFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	FieldHandler<?> create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
