package com.anrisoftware.prefdialog.swingutils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.anrisoftware.prefdialog.FieldComponent;
import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.swingutils.LoggerFactory.Logger;
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
 * @since 2.1
 * @param <FieldComponentType>
 */
public class AbstractLabelFieldHandler<FieldComponentType extends AbstractLabelFieldPanel<?>>
		extends AbstractDefaultFieldHandler<FieldComponentType> {

	private Logger log;

	/**
	 * Sets the parameter of the {@link FieldHandler}.
	 * 
	 * @param parentObject
	 *            the {@link Object} where the field is defined.
	 * 
	 * @param value
	 *            the value of the field.
	 * 
	 * @param field
	 *            the {@link Field}.
	 * 
	 * @param annotationClass
	 *            the {@link Annotation} {@link Class} of the field.
	 * 
	 * @param component
	 *            the {@link FieldComponent} that is manages by this handler.
	 */
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
		String title = getReflectionToolbox().valueFromA(getField(), "title",
				String.class, getAnnotationClass());
		title = defaultTitle(title);
		setComponentTitle(title);
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
