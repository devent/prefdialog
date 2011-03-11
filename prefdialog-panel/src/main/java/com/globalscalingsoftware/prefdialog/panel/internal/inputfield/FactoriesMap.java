package com.globalscalingsoftware.prefdialog.panel.internal.inputfield;

import static java.lang.String.format;

import java.lang.annotation.Annotation;
import java.util.Map;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.UnmodifiableMap;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Contains all {@link FieldHandlerFactory field handler factories} assigned to
 * each of the fields {@link Annotation annotations}. {@link FieldFactories}
 * will look up the field handler factory to use to create a new field based on
 * the annotation.
 * 
 * Each of the fields {@link Annotation annotations} can have only one
 * {@link FieldHandlerFactory field handler factory} assigned to it.
 */
public class FactoriesMap {

	public interface FactoriesMapFactory {
		FactoriesMap create(
				@Assisted Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactories);
	}

	private final Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactories;

	@Inject
	FactoriesMap(
			@Assisted Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactories) {
		this.fieldHandlerFactories = fieldHandlerFactories;
	}

	/**
	 * Adds a new {@link FieldHandlerFactory field handler factory} assigned to
	 * the field {@link Annotation annotation}.
	 * 
	 * @param fieldAnnotationClass
	 *            the class of the field {@link Annotation annotation}.
	 * @param factory
	 *            the {@link FieldHandlerFactory field handler factory}.
	 */
	public void addFieldHandlerFactory(
			Class<? extends Annotation> fieldAnnotationClass,
			FieldHandlerFactory factory) {
		checkForKey(fieldAnnotationClass);
		checkForValue(factory);
		fieldHandlerFactories.put(fieldAnnotationClass, factory);
	}

	private void checkForKey(Class<? extends Annotation> fieldAnnotationClass) {
		if (fieldHandlerFactories.containsKey(fieldAnnotationClass)) {
			throw new IllegalArgumentException(
					format("Factories map already contains the field annotation class %s.",
							fieldAnnotationClass));
		}
	}

	private void checkForValue(FieldHandlerFactory factory) {
		if (fieldHandlerFactories.containsValue(factory)) {
			throw new IllegalArgumentException(
					format("Factories map already contains the field handler factory %s.",
							factory));
		}
	}

	/**
	 * Returns a {@link MapIterator iterator} over the values of the factories
	 * map. The iterator cannot modify the map.
	 */
	public MapIterator mapIterator() {
		UnmodifiableMap map = (UnmodifiableMap) UnmodifiableMap
				.decorate(fieldHandlerFactories);
		return map.mapIterator();
	}

	/**
	 * Returns the {@link FieldHandlerFactory field handler factory} based on
	 * the field {@link Annotation annotation} class.
	 * 
	 * @throws NullPointerException
	 *             if there was no mapping of the field annotation class found.
	 */
	public FieldHandlerFactory getFactory(Class<? extends Annotation> annotation) {
		FieldHandlerFactory factory = fieldHandlerFactories.get(annotation);
		checkNull(annotation, factory);
		return factory;
	}

	private void checkNull(Class<? extends Annotation> annotation,
			FieldHandlerFactory factory) {
		if (factory == null) {
			throw new NullPointerException(
					format("This factories map does not contain a mapping for the field annotation class %s.",
							annotation));
		}
	}

}
