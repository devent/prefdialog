package com.globalscalingsoftware.prefdialog.panel.internal;

import static java.lang.String.format;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.swing.Action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.InputChangedCallback;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FactoriesMap;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FactoriesMap.FactoriesMapFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FieldsFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child.ChildFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child.ChildFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child.group.GroupFieldHandler;
import com.globalscalingsoftware.prefdialog.reflection.AnnotationDiscoveryCallback;
import com.globalscalingsoftware.prefdialog.reflection.AnnotationDiscoveryFactory;
import com.globalscalingsoftware.prefdialog.reflection.AnnotationFilterFactory;
import com.globalscalingsoftware.prefdialog.reflection.internal.AnnotationFilter;
import com.globalscalingsoftware.prefdialog.swingutils.actions.internal.InputChangedDelegateCallback;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

/**
 * Search the preferences object for the {@link Child} annotation and create a
 * new {@link ChildFieldHandler} with all fields.
 */
class ChildFieldHandlerWorker {

	interface ChildFieldHandlerWorkerFactory {

		/**
		 * Creates a new {@link ChildFieldHandlerWorker}.
		 * 
		 * @param preferences
		 *            the preferences object, need to have one field annotated
		 *            with the {@link Child} annotation.
		 * @param panelName
		 *            the name of the preferences panel.
		 * @param applyAction
		 *            the action associated with the "Apply" button of the
		 *            panel. If the user clicks on this button all input made
		 *            will be saved in the preferences object.
		 * @param restoreAction
		 *            the action associated with the "Restore" button of the
		 *            panel. If the user clicks on this button all fields will
		 *            be restored to the values of the preferences object.
		 * @param inputChangedCallback
		 *            the {@link InputChangedDelegateCallback callback} that
		 *            will be called after the user inputs some data in the
		 *            panel's fields.
		 * @return the new created {@link ChildFieldHandlerWorker}.
		 */
		ChildFieldHandlerWorker create(@Assisted Object preferences,
				@Assisted String panelName,
				@Assisted("applyAction") Action applyAction,
				@Assisted("restoreAction") Action restoreAction,
				@Assisted InputChangedDelegateCallback inputChangedCallback);
	}

	private final Logger l = LoggerFactory
			.getLogger(ChildFieldHandlerWorker.class);

	private final AnnotationDiscoveryFactory annotationDiscoveryFactory;

	private final AnnotationFilterFactory annotationFilterFactory;

	private final FieldsFactory fieldFactories;

	private final FactoriesMapFactory factoriesMapFactory;

	private final Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactories;

	private final Object preferences;

	private final String panelName;

	private final Action applyAction;

	private final Action restoreAction;

	private final InputChangedDelegateCallback inputChangedCallback;

	@Inject
	ChildFieldHandlerWorker(
			AnnotationDiscoveryFactory annotationDiscoveryFactory,
			AnnotationFilterFactory annotationFilterFactory,
			FactoriesMapFactory factoriesMapFactory,
			FieldsFactory fieldFactories,
			@Named("field_handler_factories_map") Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactories,
			@Assisted Object preferences, @Assisted String panelName,
			@Assisted("applyAction") Action applyAction,
			@Assisted("restoreAction") Action restoreAction,
			@Assisted InputChangedDelegateCallback inputChangedCallback) {
		this.annotationDiscoveryFactory = annotationDiscoveryFactory;
		this.annotationFilterFactory = annotationFilterFactory;
		this.fieldFactories = fieldFactories;
		this.fieldHandlerFactories = fieldHandlerFactories;
		this.factoriesMapFactory = factoriesMapFactory;
		this.preferences = preferences;
		this.panelName = panelName;
		this.applyAction = applyAction;
		this.restoreAction = restoreAction;
		this.inputChangedCallback = inputChangedCallback;
	}

	private Collection<Class<? extends Annotation>> createChildAnnotations() {
		Collection<Class<? extends Annotation>> childAnnotations = new ArrayList<Class<? extends Annotation>>();
		childAnnotations.add(Child.class);
		return childAnnotations;
	}

	/**
	 * Returns a {@link ChildFieldHandler} created from the preferences object.
	 */
	public ChildFieldHandler getChildFieldHandler() {
		ChildFieldHandler childFieldHandler = createChildFieldHandler(preferences);
		setupChildFieldHandler(childFieldHandler);
		return childFieldHandler;
	}

	private class DiscoverAnnotationsCallback implements
			AnnotationDiscoveryCallback {

		public ChildFieldHandler childFieldHandler;

		@Override
		public void fieldAnnotationDiscovered(Field field, Object value,
				Annotation a) {
			l.debug("Discrovered child annotation for field value {}.", value);
			if (value.toString().equals(panelName)) {
				childFieldHandler = createChildFieldHandler(field, value);
			}
		}
	}

	private ChildFieldHandler createChildFieldHandler(Object preferences) {
		AnnotationFilter annotationFilter = annotationFilterFactory
				.create(createChildAnnotations());
		DiscoverAnnotationsCallback callback = new DiscoverAnnotationsCallback();
		annotationDiscoveryFactory.create(annotationFilter, preferences,
				callback).discoverAnnotations();
		checkChildFieldHandlerCreated(callback.childFieldHandler);
		return callback.childFieldHandler;
	}

	private void checkChildFieldHandlerCreated(
			ChildFieldHandler childFieldHandler) {
		if (childFieldHandler == null) {
			throw new NullPointerException(format(
					"Could not find a preference field with the name %s.",
					panelName));
		}
	}

	private ChildFieldHandler createChildFieldHandler(Field field, Object value) {
		l.debug("Create a new child field handler for field {}.", value);
		FactoriesMap factoriesMap = factoriesMapFactory
				.create(fieldHandlerFactories);
		ChildFieldHandlerFactory factory = (ChildFieldHandlerFactory) factoriesMap
				.getFactory(Child.class);
		ChildFieldHandler panel = factory.create(preferences, value, field);
		panel.setApplyAction(applyAction);
		panel.setRestoreAction(restoreAction);

		for (FieldHandler<?> fieldHandler : fieldFactories
				.createFieldsHandlers(factoriesMap, value)) {
			setupGroupFieldHandler(panel, fieldHandler, factoriesMap);
			panel.addFieldHandler(fieldHandler);
		}

		return panel;
	}

	private void setupGroupFieldHandler(ChildFieldHandler child,
			FieldHandler<?> handler, FactoriesMap factoriesMap) {
		if (!(handler instanceof GroupFieldHandler)) {
			return;
		}
		l.debug("Create a new group field hanlder for child field {}.", child);
		GroupFieldHandler groupFieldHandler = (GroupFieldHandler) handler;
		Object value = handler.getComponentValue();
		for (FieldHandler<?> fieldHandler : fieldFactories
				.createFieldsHandlers(factoriesMap, value)) {
			groupFieldHandler.addFieldHandler(fieldHandler);
		}
	}

	private void setupChildFieldHandler(
			final ChildFieldHandler childFieldHandler) {
		childFieldHandler.setInputChangedCallback(new InputChangedCallback() {

			@Override
			public void inputChanged(Object source) {
				boolean enabled = childFieldHandler.isInputValid();
				l.debug("Set apply action enabled to {}.", enabled);
				childFieldHandler.setApplyEnabled(enabled);
				inputChangedCallback.inputChanged(this);
			}

		});
	}

}
