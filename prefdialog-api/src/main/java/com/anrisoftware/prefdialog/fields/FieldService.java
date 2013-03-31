package com.anrisoftware.prefdialog.fields;

/**
 * Makes the field factory available as a service.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface FieldService {

	/**
	 * Returns the information about the field.
	 * 
	 * @return the {@link FieldInfo}.
	 */
	FieldInfo getInfo();

	/**
	 * Returns the field factory.
	 * 
	 * @return the {@link FieldFactory}.
	 */
	FieldFactory getFactory();
}
