package com.globalscalingsoftware.prefdialog.panel;

import static java.lang.String.format;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.FieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.panel.api.FactoriesMapFactory;
import com.globalscalingsoftware.prefdialog.panel.api.FieldHandlerFactoriesMap;
import com.globalscalingsoftware.prefdialog.panel.api.FieldsHandlerFactoryWorker;
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.ChildFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfields.child.group.GroupFieldHandler;
import com.globalscalingsoftware.prefdialog.reflection.api.AnnotationDiscoveryCallback;
import com.globalscalingsoftware.prefdialog.reflection.api.AnnotationDiscoveryWorkerFactory;
import com.globalscalingsoftware.prefdialog.reflection.api.AnnotationFilter;
import com.globalscalingsoftware.prefdialog.reflection.api.PredefinedAnnotationFilterFactory;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

/**
 * Search the preferences object for the {@link Child} annotation and create a
 * new {@link ChildFieldHandler} with all fields.
 */
class ChildFieldHandlerWorker {

	private final Logger l = LoggerFactory
			.getLogger(ChildFieldHandlerWorker.class);

	private final AnnotationDiscoveryWorkerFactory annotationDiscoveryFactory;

	private final PredefinedAnnotationFilterFactory annotationFilterFactory;

	private final FieldsHandlerFactoryWorker fieldFactoryWorker;

	private final FactoriesMapFactory factoriesMapFactory;

	private final Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactories;

	private final Object preferences;

	private final String panelName;

	private final Collection<Class<? extends Annotation>> childAnnotations;

	@Inject
	ChildFieldHandlerWorker(
			AnnotationDiscoveryWorkerFactory annotationDiscoveryFactory,
			PredefinedAnnotationFilterFactory annotationFilterFactory,
			FactoriesMapFactory factoriesMapFactory,
			FieldsHandlerFactoryWorker fieldFactories,
			@Named("field_handler_factories_map") Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactories,
			@Named("child_annotations") Collection<Class<? extends Annotation>> childAnnotations,
			@Assisted Object preferences, @Assisted String panelName) {
		this.annotationDiscoveryFactory = annotationDiscoveryFactory;
		this.annotationFilterFactory = annotationFilterFactory;
		this.fieldFactoryWorker = fieldFactories;
		this.fieldHandlerFactories = fieldHandlerFactories;
		this.factoriesMapFactory = factoriesMapFactory;
		this.preferences = preferences;
		this.panelName = panelName;
		this.childAnnotations = childAnnotations;
	}

	/**
	 * Returns a {@link ChildFieldHandler} created from the preferences object.
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
			l.debug("Discrovered child annotation for field value {}.", value);
			if (value.toString().equals(panelName)) {
				childFieldHandler = createChildFieldHandler(field, value);
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
					panelName));
		}
	}

	private FieldHandler<?> createChildFieldHandler(Field field, Object value) {
		l.debug("Create a new child field handler for field {}.", value);
		FieldHandlerFactoriesMap factoriesMap = factoriesMapFactory
				.create(fieldHandlerFactories);
		ChildFieldHandlerFactory factory = (ChildFieldHandlerFactory) factoriesMap
				.getFactory(Child.class);
		FieldHandler<?> panel = factory.create(preferences, value, field);

		for (FieldHandler<?> fieldHandler : fieldFactoryWorker
				.createFieldsHandlers(factoriesMap, value)) {
			setupGroupFieldHandler(panel, fieldHandler, factoriesMap);
			panel.addFieldHandler(fieldHandler);
		}

		return panel;
	}

	private void setupGroupFieldHandler(FieldHandler<?> child,
			FieldHandler<?> handler, FieldHandlerFactoriesMap factoriesMap) {
		if (!(handler instanceof GroupFieldHandler)) {
			return;
		}
		l.debug("Create a new group field hanlder for child field {}.", child);
		GroupFieldHandler groupFieldHandler = (GroupFieldHandler) handler;
		Object value = handler.getComponentValue();
		for (FieldHandler<?> fieldHandler : fieldFactoryWorker
				.createFieldsHandlers(factoriesMap, value)) {
			groupFieldHandler.addFieldHandler(fieldHandler);
		}
	}

}
