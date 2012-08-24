package com.anrisoftware.prefdialog.factories;

import java.lang.reflect.Field;

import com.anrisoftware.prefdialog.fields.FieldComponent;

/**
 * Factory to create a new field component for the preference bean object.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface FieldFactory {

	/**
	 * Creates a new field component for the specified field of the preference
	 * bean object.
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
	 * @return the new created {@link FieldComponent}.
	 */
	FieldComponent<?> create(Object parentObject, Object value, Field field);
}
