package com.anrisoftware.prefdialog.fields;

import java.awt.Component;

import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Factory to create a new field component.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface FieldFactory {

	/**
	 * Creates a new field component for the specified bean object.
	 * 
	 * @param bean
	 *            the {@link Object}.
	 * 
	 * @return the {@link FieldComponent}.
	 */
	FieldComponent<? extends Component> create(Object bean);

	/**
	 * Creates a new field component for the specified bean object.
	 * 
	 * @param bean
	 *            the {@link Object}.
	 * 
	 * @param texts
	 *            the {@link Texts} resources.
	 * 
	 * @return the {@link FieldComponent}.
	 */
	FieldComponent<? extends Component> create(Object bean, Texts texts);

	/**
	 * Creates a new field component for the specified bean object.
	 * 
	 * @param bean
	 *            the {@link Object}.
	 * 
	 * @param texts
	 *            the {@link Texts} resources.
	 * 
	 * @param images
	 *            the {@link Images} resources.
	 * 
	 * @return the {@link FieldComponent}.
	 */
	FieldComponent<? extends Component> create(Object bean, Texts texts,
			Images images);
}
