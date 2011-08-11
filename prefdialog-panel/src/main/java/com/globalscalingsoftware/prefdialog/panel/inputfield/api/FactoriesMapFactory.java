package com.globalscalingsoftware.prefdialog.panel.inputfield.api;

import java.lang.annotation.Annotation;
import java.util.Map;

import com.google.inject.Module;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.internal.Annotations;

/**
 * Factory to create a new {@link FieldHandlerFactoriesMap} from the given {@link Map} of
 * annotations. With this factory it is possible to inject the known
 * {@link Annotations} and their {@link FieldHandlerFactory} in the Guice
 * {@link Module}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
public interface FactoriesMapFactory {

	/**
	 * Creates a new {@link FieldHandlerFactoriesMap} from the given {@link Map} of
	 * annotations.
	 * 
	 * @param fieldHandlerFactories
	 *            the {@link Map} of {@link Annotation}s that are mapped to one
	 *            {@link FieldHandlerFactory}.
	 * 
	 * @return the new created {@link FieldHandlerFactoriesMap}.
	 */
	FieldHandlerFactoriesMap create(
			@Assisted Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactories);
}
