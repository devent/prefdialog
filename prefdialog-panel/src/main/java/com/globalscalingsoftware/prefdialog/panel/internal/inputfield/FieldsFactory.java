/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.reflection.PredefinedAnnotationFilter;
import com.globalscalingsoftware.prefdialog.reflection.api.AnnotationDiscoveryCallback;
import com.globalscalingsoftware.prefdialog.reflection.api.AnnotationDiscoveryWorkerFactory;
import com.globalscalingsoftware.prefdialog.reflection.api.PredefinedAnnotationFilterFactory;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Creates all {@link FieldHandler field handler} for which object's fields it
 * can find a {@link Annotation field annotation}.
 */
public class FieldsFactory {

	private final PredefinedAnnotationFilter annotationFilter;

	private final AnnotationDiscoveryWorkerFactory annotationDiscoveryFactory;

	@Inject
	FieldsFactory(
			@Named("field_annotations") Collection<Class<? extends Annotation>> fieldAnnotations,
			PredefinedAnnotationFilterFactory annotationFilterFactory,
			AnnotationDiscoveryWorkerFactory annotationDiscoveryFactory) {
		this.annotationFilter = annotationFilterFactory
				.create(fieldAnnotations);
		this.annotationDiscoveryFactory = annotationDiscoveryFactory;
	}

	/**
	 * Search a object's fields for {@link Annotation field annotations} and
	 * create for each valid annotation a new {@link FieldHandler field handler}
	 * .
	 * 
	 * @param factoriesMap
	 *            the {@link FactoriesMap map} that contains the mapping between
	 *            {@link Annotation field annotations} and
	 *            {@link FieldHandlerFactory field handler factories}.
	 * @param object
	 *            the {@link Object} which fields will be searched for valid
	 *            annotations.
	 * @return a {@link List} of all created {@link FieldHandler field handlers}
	 *         .
	 */
	public List<FieldHandler<?>> createFieldsHandlers(
			final FactoriesMap factoriesMap, final Object object) {
		final ArrayList<FieldHandler<?>> fieldHandlers = new ArrayList<FieldHandler<?>>();
		annotationDiscoveryFactory.create(annotationFilter, object,
				new AnnotationDiscoveryCallback() {

					@Override
					public void fieldAnnotationDiscovered(Field field,
							Object value, Annotation a) {
						FieldHandler<?> handler = createDiscoveredFieldHandler(
								factoriesMap, object, field, value, a);
						fieldHandlers.add(handler);
					}

				}).discoverAnnotations();
		return fieldHandlers;
	}

	private FieldHandler<?> createDiscoveredFieldHandler(
			FactoriesMap factoriesMap, Object parentObject, Field field,
			Object value, Annotation a) {
		FieldHandlerFactory factory = factoriesMap.getFactory(a.getClass());
		return factory.create(parentObject, value, field);
	}
}
