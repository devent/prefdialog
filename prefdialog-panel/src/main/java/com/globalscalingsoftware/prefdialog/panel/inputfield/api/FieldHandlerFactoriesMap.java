package com.globalscalingsoftware.prefdialog.panel.inputfield.api;

import java.lang.annotation.Annotation;

import org.apache.commons.collections.MapIterator;

/**
 * Manages all {@link FieldHandlerFactory field handler factories} assigned to
 * each of the fields {@link Annotation}s. {@link FieldFactories} will look up
 * the field handler factory to use to create a new field based on the
 * annotation.
 * 
 * Each of the fields {@link Annotation}s can have only one
 * {@link FieldHandlerFactory} assigned to it.
 */
public interface FieldHandlerFactoriesMap {

	/**
	 * Adds a new {@link FieldHandlerFactory field handler factory} assigned to
	 * the field {@link Annotation annotation}.
	 * 
	 * @param fieldAnnotationClass
	 *            the class of the field {@link Annotation annotation}.
	 * @param factory
	 *            the {@link FieldHandlerFactory field handler factory}.
	 */
	void addFieldHandlerFactory(
			Class<? extends Annotation> fieldAnnotationClass,
			FieldHandlerFactory factory);

	/**
	 * Returns the {@link FieldHandlerFactory field handler factory} based on
	 * the field {@link Annotation annotation} class.
	 * 
	 * @throws NullPointerException
	 *             if there was no mapping of the field annotation class found.
	 */
	FieldHandlerFactory getFactory(Class<? extends Annotation> annotation);

	/**
	 * Returns a {@link MapIterator iterator} over the values of the factories
	 * map. The iterator cannot modify the map.
	 */
	MapIterator mapIterator();

}
