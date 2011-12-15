package com.anrisoftware.prefdialog.panel.inputfields.api;

import java.lang.reflect.Field;

import javax.swing.JComboBox;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new combobox {@link FieldHandler}. It uses the
 * {@link JComboBox} component to list multiple items.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public interface ComboBoxFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	FieldHandler<?> create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, Field field);
}
