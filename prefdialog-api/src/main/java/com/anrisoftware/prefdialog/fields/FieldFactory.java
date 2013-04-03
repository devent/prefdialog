package com.anrisoftware.prefdialog.fields;

import java.awt.Component;

/**
 * Factory to create a new field component.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface FieldFactory<T extends Component> {

	/**
	 * Creates a new field component.
	 * 
	 * @param component
	 *            the {@link Component} of the field.
	 * 
	 * @param parentObject
	 *            the parent object of this field.
	 * 
	 * @param fieldName
	 *            the name of the field in the parent object.
	 * 
	 * @return the {@link FieldComponent}.
	 */
	FieldComponent<T> create(T component, Object parentObject, String fieldName);

}
