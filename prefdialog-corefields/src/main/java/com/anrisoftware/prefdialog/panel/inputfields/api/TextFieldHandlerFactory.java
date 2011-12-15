package com.anrisoftware.prefdialog.panel.inputfields.api;

import java.lang.reflect.Field;

import javax.swing.JTextField;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new text {@link FieldHandler}. It uses the
 * {@link JTextField} component to enter and display simple text.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public interface TextFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	FieldHandler<?> create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, Field field);
}
