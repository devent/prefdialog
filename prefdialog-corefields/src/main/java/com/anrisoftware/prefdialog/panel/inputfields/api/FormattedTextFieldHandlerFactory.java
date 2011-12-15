package com.anrisoftware.prefdialog.panel.inputfields.api;

import java.lang.reflect.Field;

import javax.swing.JFormattedTextField;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new formatted text {@link FieldHandler}. It uses the
 * {@link JFormattedTextField} component to enter and show the value.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public interface FormattedTextFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	FieldHandler<?> create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, Field field);
}
