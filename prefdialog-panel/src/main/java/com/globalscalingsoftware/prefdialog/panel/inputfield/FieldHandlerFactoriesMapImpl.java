package com.globalscalingsoftware.prefdialog.panel.inputfield;

import static java.lang.String.format;
import static org.apache.commons.collections.map.UnmodifiableMap.decorate;

import java.lang.annotation.Annotation;
import java.util.Map;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.UnmodifiableMap;

import com.globalscalingsoftware.prefdialog.panel.inputfield.api.FieldHandlerFactoriesMap;
import com.globalscalingsoftware.prefdialog.panel.inputfield.api.FieldHandlerFactory;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

class FieldHandlerFactoriesMapImpl implements FieldHandlerFactoriesMap {

	private final Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactories;

	@Inject
	FieldHandlerFactoriesMapImpl(
			@Assisted Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactories) {
		this.fieldHandlerFactories = fieldHandlerFactories;
	}

	@Override
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

	@Override
	public MapIterator mapIterator() {
		UnmodifiableMap map = (UnmodifiableMap) decorate(fieldHandlerFactories);
		return map.mapIterator();
	}

	@Override
	public FieldHandlerFactory getFactory(Class<? extends Annotation> annotation) {
		for (MapIterator it = mapIterator(); it.hasNext();) {
			it.next();
			Class<? extends Annotation> key = getAnnotationClass(it);
			FieldHandlerFactory value = (FieldHandlerFactory) it.getValue();
			if (key.isAssignableFrom(annotation)) {
				return value;
			}
		}
		throw new NullPointerException(
				format("This factories map does not contain a mapping for the field annotation class %s.",
						annotation));
	}

	@SuppressWarnings("unchecked")
	private Class<? extends Annotation> getAnnotationClass(MapIterator it) {
		return ((Class<? extends Annotation>) it.getKey());
	}

}
