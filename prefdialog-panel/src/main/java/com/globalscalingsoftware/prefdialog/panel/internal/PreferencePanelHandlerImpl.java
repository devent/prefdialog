package com.globalscalingsoftware.prefdialog.panel.internal;

import static java.lang.String.format;

import java.awt.Component;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.swing.Action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.PreferencePanelHandler;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.panel.internal.actions.ActionsHandler;
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
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

public class PreferencePanelHandlerImpl implements PreferencePanelHandler {

	private final Logger l = LoggerFactory
			.getLogger(PreferencePanelHandlerImpl.class);

	private final Object preferences;

	private final AnnotationFilter annotationFilter;

	private final AnnotationDiscoveryFactory annotationDiscoveryFactory;

	private final FieldsFactory fieldFactories;

	private final FactoriesMap factoriesMap;

	private final ActionsHandler actionsHandler;

	private ChildFieldHandler childFieldHandler;

	private final String panelName;

	@Inject
	PreferencePanelHandlerImpl(
			AnnotationFilterFactory annotationFilterFactory,
			AnnotationDiscoveryFactory annotationDiscoveryFactory,
			FieldsFactory fieldFactories,
			FactoriesMapFactory factoriesMapFactory,
			@Named("field_handler_factories_map") Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactories,
			ActionsHandler actionsHandler, @Assisted Object preferences,
			@Assisted String panelName) {
		this.annotationFilter = annotationFilterFactory
				.create(createChildAnnotations());
		this.annotationDiscoveryFactory = annotationDiscoveryFactory;
		this.fieldFactories = fieldFactories;
		this.factoriesMap = factoriesMapFactory.create(fieldHandlerFactories);
		this.actionsHandler = actionsHandler;
		this.preferences = preferences;
		this.panelName = panelName;
		this.childFieldHandler = null;
		createChild();
	}

	private Collection<Class<? extends Annotation>> createChildAnnotations() {
		Collection<Class<? extends Annotation>> childAnnotations = new ArrayList<Class<? extends Annotation>>();
		childAnnotations.add(Child.class);
		return childAnnotations;
	}

	private void createChild() {
		discoverAnnotations();
	}

	private void discoverAnnotations() {
		annotationDiscoveryFactory.create(annotationFilter, preferences,
				new AnnotationDiscoveryCallback() {

					@Override
					public void fieldAnnotationDiscovered(Field field,
							Object value, Annotation a) {
						l.debug("Discrovered child annotation for field value {}.",
								value);
						if (value.toString().equals(panelName)) {
							childFieldHandler = createChildFieldHandler(field,
									value);
						}
					}
				}).discoverAnnotations();
		checkChildFieldHandlerCreated();
	}

	private void checkChildFieldHandlerCreated() {
		if (childFieldHandler == null) {
			throw new NullPointerException(format(
					"Could not find a preference field with the name %s.",
					panelName));
		}
	}

	private ChildFieldHandler createChildFieldHandler(Field field, Object value) {
		l.debug("Create a new child field handler for field {}.", value);
		ChildFieldHandlerFactory factory = (ChildFieldHandlerFactory) factoriesMap
				.getFactory(Child.class);
		ChildFieldHandler panel = factory.create(preferences, value, field);
		panel.setApplyAction(actionsHandler.getApplyAction());
		panel.setRestoreAction(actionsHandler.getRestoreAction());

		for (FieldHandler<?> fieldHandler : fieldFactories
				.createFieldsHandlers(factoriesMap, value)) {
			setupGroupFieldHandler(panel, fieldHandler);
			panel.addFieldHandler(fieldHandler);
		}

		return panel;
	}

	private void setupGroupFieldHandler(ChildFieldHandler child,
			FieldHandler<?> handler) {
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

	@Override
	public void setApplyAction(Action a) {
		actionsHandler.setApplyParent(a);
	}

	@Override
	public void setRestoreAction(Action a) {
		actionsHandler.setRestoreParent(a);
	}

	@Override
	public Component getAWTComponent() {
		return childFieldHandler.getAWTComponent();
	}

	@Override
	public void applyInput() {
		childFieldHandler.applyInput();
	}

	@Override
	public void restoreInput() {
		childFieldHandler.restoreInput();
	}

	@Override
	public Object getPreferences() {
		return childFieldHandler.getComponentValue();
	}

}
