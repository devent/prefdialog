package com.anrisoftware.prefdialog.fields.buttongroup;

import java.awt.Container;
import java.lang.reflect.Field;

/**
 * Factory to create a new button group field for the preference bean object.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
interface ButtonGroupFieldFactory {

	/**
	 * Creates a new button group field for the specified field of the
	 * preference bean object.
	 * 
	 * @param container
	 *            the {@link Container} for the button group field.
	 * 
	 * @param bean
	 *            the preference bean {@link Object} where the field is defined.
	 * 
	 * @param field
	 *            the {@link Field}.
	 * 
	 * @return the {@link ButtonGroupField}.
	 */
	ButtonGroupField create(Container container, Object bean, Field field);
}
