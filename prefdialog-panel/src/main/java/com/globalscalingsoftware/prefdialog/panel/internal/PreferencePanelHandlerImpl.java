package com.globalscalingsoftware.prefdialog.panel.internal;

import java.awt.Component;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.PreferencePanelHandler;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FactoriesMap;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FactoriesMap.FactoriesMapFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FieldsFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.actions.ActionsHandler;
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

	private final Object preferences;

	private final AnnotationFilter annotationFilter;

	private final AnnotationDiscoveryFactory annotationDiscoveryFactory;

	private final FieldsFactory fieldFactories;

	private final FactoriesMap factoriesMap;

	private final ActionsHandler actionsHandler;

	private ChildFieldHandler childFieldHandler;

	@Inject
	PreferencePanelHandlerImpl(
			AnnotationFilterFactory annotationFilterFactory,
			AnnotationDiscoveryFactory annotationDiscoveryFactory,
			FieldsFactory fieldFactories,
			FactoriesMapFactory factoriesMapFactory,
			@Named("field_handler_factories_map") Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactories,
			ActionsHandler actionsHandler, @Assisted Object preferences) {
		this.annotationFilter = annotationFilterFactory
				.create(createChildAnnotations());
		this.annotationDiscoveryFactory = annotationDiscoveryFactory;
		this.fieldFactories = fieldFactories;
		this.factoriesMap = factoriesMapFactory.create(fieldHandlerFactories);
		this.actionsHandler = actionsHandler;
		this.preferences = preferences;
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
						childFieldHandler = createChildFieldHandler(field,
								value);
					}
				}).discoverAnnotations();
	}

	private ChildFieldHandler createChildFieldHandler(Field field, Object value) {
		ChildFieldHandlerFactory factory = (ChildFieldHandlerFactory) factoriesMap
				.getFactory(Child.class);
		ChildFieldHandler panel = factory.create(preferences, value, field);
		panel.setApplyAction(actionsHandler.getApplyAction());
		panel.setRestoreAction(actionsHandler.getRestoreAction());
		panel.setup();

		for (FieldHandler<?> fieldHandler : fieldFactories
				.createFieldsHandlers(factoriesMap, value)) {
			fieldHandler.setup();
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
		GroupFieldHandler groupFieldHandler = (GroupFieldHandler) handler;
		Object value = handler.getComponentValue();
		for (FieldHandler<?> fieldHandler : fieldFactories
				.createFieldsHandlers(factoriesMap, value)) {
			fieldHandler.setup();
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
		return preferences;
	}
}
