package com.globalscalingsoftware.prefdialog.swingutils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldComponent;
import com.globalscalingsoftware.prefdialog.reflection.ReflectionToolbox;
import com.globalscalingsoftware.prefdialog.swingutils.SharedSwingLoggerFactory.SharedSwingLogger;

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

	private final SharedSwingLogger log;

	public AbstractLabelFieldHandler(SharedSwingLoggerFactory loggerFactory,
			ReflectionToolbox reflectionToolbox, Object parentObject,
			Object value, Field field,
			Class<? extends Annotation> annotationClass,
			FieldComponentType component) {
		super(reflectionToolbox, parentObject, value, field, annotationClass,
				component);
		this.log = loggerFactory.create(AbstractLabelFieldHandler.class);
		setup();
	}

	private void setup() {
		setupTitle();
		setupShowTitle();
	}

	private void setupShowTitle() {
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		Annotation a = getField().getAnnotation(annotationClass);
		boolean show = reflectionToolbox.invokeMethodWithReturnType(
				"showTitle", Boolean.class, a);
		log.setShowTitle(getField(), show);
		getComponent().setShowTitle(show);
	}

	private void setupTitle() {
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		Field field = getField();
		String title = getValueFromAnnotationIn("title", String.class, field,
				annotationClass);
		title = defaultTitleIfNameNotSet(title, field);
		log.setTitle(field, title);
		setComponentTitle(title);
	}

	private String defaultTitleIfNameNotSet(String title, Field field) {
		if (title.isEmpty()) {
			title = field.getName();
		}
		return title;
	}

}
