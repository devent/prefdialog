package com.globalscalingsoftware.prefdialog.panel.inputfields.api;

import java.lang.reflect.Field;

import javax.swing.JPanel;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new child panel {@link FieldHandler}. It is a
 * {@link JPanel} that will contain the fields from the value.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public interface ChildFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	FieldHandler<?> create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
