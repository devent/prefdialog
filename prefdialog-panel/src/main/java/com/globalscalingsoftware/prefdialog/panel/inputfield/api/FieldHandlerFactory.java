package com.globalscalingsoftware.prefdialog.panel.inputfield.api;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new {@link FieldHandler}. Each {@link FieldHandler} need
 * to have a constructor that have the parameters defined here injected. The
 * list of parameters defined is as minimal as possible, containing only the
 * information about the current field for which the {@link FieldHandler} is
 * created for.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
public interface FieldHandlerFactory {

	/**
	 * Creates a new {@link FieldHandler} for the given field.
	 * 
	 * @param parentObject
	 *            the {@link Object} where the field is defined.
	 * 
	 * @param value
	 *            the value of the field.
	 * 
	 * @param field
	 *            the {@link Field}.
	 * 
	 * @return the new created {@link FieldHandler}.
	 */
	FieldHandler<?> create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
