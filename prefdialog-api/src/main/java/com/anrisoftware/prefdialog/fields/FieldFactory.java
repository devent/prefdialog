package com.anrisoftware.prefdialog.fields;

import java.awt.Component;

/**
 * Factory to create a new field component.
 * 
 * @param <ComponentType>
 *            the {@link Component} for this field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface FieldFactory<ComponentType extends Component> {

	/**
	 * Creates a new field component.
	 * 
	 * @param parentObject
	 *            the parent object of this field.
	 * 
	 * @param fieldName
	 *            the name of the field in the parent object.
	 * 
	 * @return the {@link FieldComponent}.
	 */
	FieldComponent<ComponentType> create(Object parentObject, String fieldName);

}
