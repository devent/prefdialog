package com.globalscalingsoftware.prefdialog.panel.inputfields.api;

import java.lang.reflect.Field;

import javax.swing.JCheckBox;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new checkbox {@link FieldHandler}. It will use the
 * {@link JCheckBox} component so that the user can switch between two states.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public interface CheckBoxFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	FieldHandler<?> create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
