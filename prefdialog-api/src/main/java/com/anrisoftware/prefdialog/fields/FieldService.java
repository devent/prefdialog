package com.anrisoftware.prefdialog.fields;

import java.awt.Component;

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
	 * @param parent
	 *            the optional parent {@link Object}.
	 * 
	 * @return the {@link FieldFactory}.
	 */
	FieldFactory<? extends Component> getFactory(Object... parent);

}
