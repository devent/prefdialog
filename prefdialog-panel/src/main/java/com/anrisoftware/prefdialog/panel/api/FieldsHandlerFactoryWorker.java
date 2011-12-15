package com.anrisoftware.prefdialog.panel.api;

import java.lang.annotation.Annotation;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.FieldHandlerFactory;

/**
 * Creates all {@link FieldHandler}s for which object's fields it can find a
 * {@link Annotation field annotation}.
 */
public interface FieldsHandlerFactoryWorker {

	/**
	 * Search a object's fields for {@link Annotation field annotations} and
	 * create for each valid annotation a new {@link FieldHandler} .
	 * 
	 * @param factoriesMap
	 *            the {@link FieldHandlerFactoriesMapImpl map} that contains the
	 *            mapping between {@link Annotation field annotations} and
	 *            {@link FieldHandlerFactory field handler factories}.
	 * 
	 * @param object
	 *            the {@link Object} which fields will be searched for valid
	 *            annotations.
	 * 
	 * @return a {@link Iterable} of all created {@link FieldHandler} .
	 */
	Iterable<FieldHandlerEntry> createFieldsHandlers(
			FieldHandlerFactoriesMap factoriesMap, Object object);

}
