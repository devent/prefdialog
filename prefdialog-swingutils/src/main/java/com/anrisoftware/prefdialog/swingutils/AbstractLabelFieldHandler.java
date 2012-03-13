package com.anrisoftware.prefdialog.swingutils;

import static org.apache.commons.lang.StringUtils.isEmpty;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.anrisoftware.prefdialog.FieldComponent;
import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.annotations.IconSize;
import com.anrisoftware.prefdialog.annotations.TextPosition;
import com.anrisoftware.prefdialog.swingutils.LoggerFactory.Logger;
import com.google.common.io.Resources;
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
		setupShowTitle();
		setupIcon();
		setupTitle();
		return super.setup();
	}

	private void setupTitle() {
		TextPosition position = valueFromA("textPosition", TextPosition.class);
		String title = valueFromA("title", String.class);
		title = defaultTitle(title);
		switch (position) {
		case ICON_ONLY:
			getComponent().setTitle(null);
			break;
		case TEXT_ONLY:
			getComponent().setTitle(title);
			getComponent().setIcon(null);
			break;
		case TEXT_ALONGSIDE_ICON:
			getComponent().setTextUnderIcon(false);
			getComponent().setTitle(title);
			break;
		case TEXT_UNDER_ICON:
			getComponent().setTextUnderIcon(true);
			getComponent().setTitle(title);
			break;
		}
		log.setupText(position, title);
	}

	private void setupIcon() {
		String resourceName = valueFromA("icon", String.class);
		int iconSize = valueFromA("iconSize", IconSize.class).getWidth();
		resourceName = String.format(resourceName, iconSize);
		URL iconUrl = Resources.getResource(resourceName);
		if (isEmpty(resourceName) || iconUrl == null) {
			return;
		}
		ImageIcon icon = loadIcon(iconUrl);
		getComponent().setIcon(icon);
		log.setupButtonIcon(iconUrl);
	}

	private ImageIcon loadIcon(URL iconUrl) {
		try {
			return new ImageIcon(ImageIO.read(iconUrl));
		} catch (IOException e) {
			log.errorLoadIcon(e);
			return null;
		}
	}

	private void setupShowTitle() {
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		Annotation a = getField().getAnnotation(annotationClass);
		boolean show = getReflectionToolbox().invokeMethodWithReturnType(
				"showTitle", Boolean.class, a);
		log.setShowTitle(show, this);
		getComponent().setShowTitle(show);
	}

	/**
	 * Returns the value from a {@link Annotation} element. Uses the current
	 * field and the current annotation class.
	 * 
	 * @param name
	 *            the name of the element.
	 * 
	 * @param classType
	 *            the {@link Class} of the element.
	 */
	protected <T> T valueFromA(String name, Class<T> classType) {
		return getReflectionToolbox().valueFromA(getField(), name, classType,
				getAnnotationClass());
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
