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
package com.globalscalingsoftware.prefdialog.panel.inputfield;

import static com.google.common.collect.Lists.newArrayList;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfield.api.FieldHandlerFactoriesMap;
import com.globalscalingsoftware.prefdialog.panel.inputfield.api.FieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfield.api.FieldsHandlerFactoryWorker;
import com.globalscalingsoftware.prefdialog.reflection.api.AnnotationDiscoveryCallback;
import com.globalscalingsoftware.prefdialog.reflection.api.AnnotationDiscoveryWorkerFactory;
import com.globalscalingsoftware.prefdialog.reflection.api.AnnotationFilter;
import com.globalscalingsoftware.prefdialog.reflection.api.PredefinedAnnotationFilterFactory;
import com.google.inject.Inject;
import com.google.inject.name.Named;

class FieldsHandlerFactoryWorkerImpl implements FieldsHandlerFactoryWorker {

	private final AnnotationFilter annotationFilter;

	private final AnnotationDiscoveryWorkerFactory annotationDiscoveryFactory;

	@Inject
	FieldsHandlerFactoryWorkerImpl(
			@Named("field_annotations") Collection<Class<? extends Annotation>> fieldAnnotations,
			PredefinedAnnotationFilterFactory annotationFilterFactory,
			AnnotationDiscoveryWorkerFactory annotationDiscoveryFactory) {
		this.annotationFilter = annotationFilterFactory
				.create(fieldAnnotations);
		this.annotationDiscoveryFactory = annotationDiscoveryFactory;
	}

	@Override
	public List<FieldHandler<?>> createFieldsHandlers(
			final FieldHandlerFactoriesMap factoriesMap, final Object object) {
		final List<FieldHandler<?>> fieldHandlers = newArrayList();
		annotationDiscoveryFactory.create(annotationFilter,
				new AnnotationDiscoveryCallback() {

					@Override
					public void fieldAnnotationDiscovered(Field field,
							Object value, Annotation a) {
						FieldHandler<?> handler = createDiscoveredFieldHandler(
								factoriesMap, object, field, value, a);
						fieldHandlers.add(handler);
					}

				}).discoverAnnotations(object);
		return fieldHandlers;
	}

	private FieldHandler<?> createDiscoveredFieldHandler(
			FieldHandlerFactoriesMap factoriesMap, Object parentObject,
			Field field, Object value, Annotation a) {
		FieldHandlerFactory factory = factoriesMap.getFactory(a.getClass());
		return factory.create(parentObject, value, field);
	}
}
