package com.globalscalingsoftware.prefdialog.internal.reflection;

import static java.lang.String.format;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.NoSuchElementException;

import com.google.inject.Inject;

public class FieldFactories {

	private final Map<Class<? extends Annotation>, FieldHandlerFactory> factories;

	@Inject
	FieldFactories(FactoriesMap factories) {
		this.factories = factories;
	}

	@SuppressWarnings("unchecked")
	public <T extends FieldHandlerFactory> T getFactory(
			Class<? extends Annotation> fieldAnnotation) {
		return (T) factories.get(fieldAnnotation);
	}

	@SuppressWarnings("unchecked")
	public <T extends FieldHandlerFactory> T getFactory(Annotation a) {
		for (Map.Entry<Class<? extends Annotation>, FieldHandlerFactory> entry : factories
				.entrySet()) {
			if (entry.getKey().isAssignableFrom(a.getClass())) {
				return (T) entry.getValue();
			}
		}
		throw new NoSuchElementException(format(
				"Could not find the factory for annotation '%s'.", a));
	}
}
