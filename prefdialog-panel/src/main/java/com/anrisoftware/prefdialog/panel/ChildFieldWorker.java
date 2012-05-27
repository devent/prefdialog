/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-panel.
 * 
 * prefdialog-panel is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-panel is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-panel. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel;

import static java.lang.String.format;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.FieldHandlerFactory;
import com.anrisoftware.prefdialog.annotations.Child;
import com.anrisoftware.prefdialog.annotations.Group;
import com.anrisoftware.prefdialog.panel.api.FactoriesMapFactory;
import com.anrisoftware.prefdialog.panel.api.FieldHandlerEntry;
import com.anrisoftware.prefdialog.panel.api.FieldHandlerFactoriesMap;
import com.anrisoftware.prefdialog.panel.api.FieldsHandlerFactoryWorker;
import com.anrisoftware.prefdialog.panel.inputfields.api.ChildFieldHandlerFactory;
import com.anrisoftware.prefdialog.panel.inputfields.api.GroupFieldHandlerFactory;
import com.anrisoftware.prefdialog.reflection.api.AnnotationDiscoveryCallback;
import com.anrisoftware.prefdialog.reflection.api.AnnotationDiscoveryWorkerFactory;
import com.anrisoftware.prefdialog.reflection.api.AnnotationFilter;
import com.anrisoftware.prefdialog.reflection.api.PredefinedAnnotationFilterFactory;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

/**
 * Search the preferences object for the {@link Child} annotation and create a
 * new child field handler with all fields. Search also for the {@link Group}
 * annotation to create a new group field handler with the help of the
 * {@link GroupFieldHandlerFactory}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class ChildFieldWorker {

	private final AnnotationDiscoveryWorkerFactory annotationDiscoveryFactory;

	private final PredefinedAnnotationFilterFactory annotationFilterFactory;

	private final FieldsHandlerFactoryWorker fieldFactoryWorker;

	private final FactoriesMapFactory factoriesMapFactory;

	private final Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactories;

	private final Object preferences;

	private final String childName;

	private final Collection<Class<? extends Annotation>> childAnnotations;

	private final ChildFieldWorkerLogger log;

	@Inject
	ChildFieldWorker(
			ChildFieldWorkerLogger logger,
			AnnotationDiscoveryWorkerFactory annotationDiscoveryFactory,
			PredefinedAnnotationFilterFactory annotationFilterFactory,
			FactoriesMapFactory factoriesMapFactory,
			FieldsHandlerFactoryWorker fieldFactories,
			@Named("field_handler_factories_map") Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactories,
			@Named("child_annotations") Collection<Class<? extends Annotation>> childAnnotations,
			@Assisted Object preferences, @Assisted String childName) {
		this.log = logger;
		this.annotationDiscoveryFactory = annotationDiscoveryFactory;
		this.annotationFilterFactory = annotationFilterFactory;
		this.fieldFactoryWorker = fieldFactories;
		this.fieldHandlerFactories = fieldHandlerFactories;
		this.factoriesMapFactory = factoriesMapFactory;
		this.preferences = preferences;
		this.childName = childName;
		this.childAnnotations = childAnnotations;
	}

	/**
	 * Returns the name of the child preference this panel will create the child
	 * field handler.
	 * 
	 * @return the {@link String} name.
	 * 
	 * @since 2.2
	 */
	public String getChildName() {
		return childName;
	}

	/**
	 * Returns the preferences object, need to have one field annotated with the
	 * {@link Child} annotation.
	 * 
	 * @return the preferences {@link Object}.
	 * 
	 * @since 2.2
	 */
	public Object getPreferences() {
		return preferences;
	}

	/**
	 * Returns a child field handler created from the preferences object.
	 * 
	 * @return the {@link FieldHandler} which is the child field handler.
	 */
	public FieldHandler<?> getChildFieldHandler() {
		return createChildFieldHandler(preferences);
	}

	private class DiscoverAnnotationsCallback implements
			AnnotationDiscoveryCallback {

		public FieldHandler<?> childFieldHandler;

		@Override
		public void fieldAnnotationDiscovered(Field field, Object value,
				Annotation a) {
			if (field.getName().equals(childName)
					|| value.toString().equals(childName)) {
				childFieldHandler = createChildFieldHandler(field, value);
				log.discoveredChildAnnotationForPanel(childName, value);
			}
		}
	}

	private FieldHandler<?> createChildFieldHandler(Object preferences) {
		AnnotationFilter annotationFilter = annotationFilterFactory
				.create(childAnnotations);
		DiscoverAnnotationsCallback callback = new DiscoverAnnotationsCallback();
		annotationDiscoveryFactory.create(annotationFilter, callback)
				.discoverAnnotations(preferences);
		checkChildFieldHandlerCreated(callback.childFieldHandler);
		return callback.childFieldHandler;
	}

	private void checkChildFieldHandlerCreated(FieldHandler<?> childFieldHandler) {
		if (childFieldHandler == null) {
			throw new NullPointerException(format(
					"Could not find a preference field with the name %s.",
					childName));
		}
	}

	private FieldHandler<?> createChildFieldHandler(Field field, Object value) {
		log.creatingNewChild(field, value);
		FieldHandlerFactoriesMap factoriesMap = factoriesMapFactory
				.create(fieldHandlerFactories);
		ChildFieldHandlerFactory factory = (ChildFieldHandlerFactory) factoriesMap
				.getFactory(Child.class);
		FieldHandler<?> childHandler = factory
				.create(preferences, value, field).setup();

		Iterable<FieldHandlerEntry> handlers = fieldFactoryWorker
				.createFieldsHandlers(factoriesMap, value);
		for (FieldHandlerEntry entry : handlers) {
			FieldHandler<?> handler = entry.getFieldHandler();
			setupGroupFieldHandler(entry.getAnnotation(), childHandler,
					handler, factoriesMap);
			childHandler.addFieldHandler(handler);
		}

		return childHandler;
	}

	private void setupGroupFieldHandler(Annotation a, FieldHandler<?> child,
			FieldHandler<?> handler, FieldHandlerFactoriesMap factoriesMap) {
		if (a.getClass() == Group.class) {
			return;
		}
		Object value = handler.getComponentValue();
		log.setupNewGroup(handler);
		Iterable<FieldHandlerEntry> handlers = fieldFactoryWorker
				.createFieldsHandlers(factoriesMap, value);
		for (FieldHandlerEntry entry : handlers) {
			handler.addFieldHandler(entry.getFieldHandler());
		}
	}

}
