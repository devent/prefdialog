package com.globalscalingsoftware.prefdialog.swingutils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldComponent;
import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.swingutils.LoggerFactory.Logger;
import com.google.inject.Inject;

/**
 * Adds a label on top of the {@link FieldComponent} and read additional
 * annotation attributes and sets them to the {@link FieldComponent}.
 * 
 * The additional attributes are:
 * <ul>
 * <li>showTitle</li>
 * <li>title</li>
 * </ul>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @param <FieldComponentType>
 */
public class AbstractLabelFieldHandler<FieldComponentType extends AbstractLabelFieldPanel<?>>
		extends AbstractDefaultFieldHandler<FieldComponentType> {

	private Logger log;

	public AbstractLabelFieldHandler(Object parentObject, Object value,
			Field field, Class<? extends Annotation> annotationClass,
			FieldComponentType component) {
		super(parentObject, value, field, annotationClass, component);
	}

	/**
	 * Sets if the title is visible and the title text.
	 */
	@Override
	public FieldHandler<FieldComponentType> setup() {
		setupTitle();
		setupShowTitle();
		return super.setup();
	}

	private void setupShowTitle() {
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		Annotation a = getField().getAnnotation(annotationClass);
		boolean show = getReflectionToolbox().invokeMethodWithReturnType(
				"showTitle", Boolean.class, a);
		log.setShowTitle(show, this);
		getComponent().setShowTitle(show);
	}

	private void setupTitle() {
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		Field field = getField();
		String title = valueFromAnnotationInField("title", String.class, field,
				annotationClass);
		title = defaultTitleIfNameNotSet(title, field);
		log.setTitle(title, this);
		setComponentTitle(title);
	}

	private String defaultTitleIfNameNotSet(String title, Field field) {
		if (title.isEmpty()) {
			title = field.getName();
		}
		return title;
	}

	/**
	 * Injects the {@link LoggerFactory}.
	 */
	@Override
	@Inject
	public void setLoggerFactory(LoggerFactory loggerFactory) {
		log = loggerFactory.create(AbstractLabelFieldHandler.class);
		super.setLoggerFactory(loggerFactory);
	}

}
