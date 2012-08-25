package com.anrisoftware.prefdialog.core;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

import javax.inject.Inject;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.reflection.annotations.AnnotationAccess;
import com.anrisoftware.prefdialog.reflection.beans.BeanAccess;
import com.anrisoftware.prefdialog.reflection.beans.BeanFactory;
import com.anrisoftware.resources.api.Texts;

/**
 * Sets the component and sets the component name, width, and if the component
 * is enabled or disabled. Sets the common fields for the field component:
 * <p>
 * For the tool-tip to be set the component of this field must be of class
 * {@link JComponent}.
 * 
 * <ul>
 * <li>name</li>
 * <li>title</li>
 * <li>width</li>
 * <li>value</li>
 * <li>read-only flag</li>
 * <li>tool-tip</li>
 * <li>locale</li>
 * </ul>
 * 
 * @param <ComponentType>
 *            the type of the component of this field. Must be of class
 *            {@link Component}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public abstract class AbstractFieldComponent<ComponentType extends Component>
		implements FieldComponent<ComponentType> {

	private static final Class<com.anrisoftware.prefdialog.annotations.FieldComponent> FIELD_COMPONENT_ANNOTATION_CLASS = com.anrisoftware.prefdialog.annotations.FieldComponent.class;

	private static final String TITLE_ELEMENT = "title";

	private static final String WIDTH_ELEMENT = "width";

	private static final String READ_ONLY_ELEMENT = "readOnly";

	private static final String TOOL_TIP_ELEMENT = "toolTip";

	private static final String LOCALE_ELEMENT = "locale";

	private final ComponentType component;

	private final Object parentObject;

	private final Field field;

	private final List<FieldComponent<?>> childFields;

	private AbstractFieldComponentLogger log;

	private String title;

	private String titleResource;

	private Object value;

	private AnnotationAccess annotationAccess;

	private BeanAccess beanAccess;

	private BeanFactory beanFactory;

	private Texts texts;

	/**
	 * Sets the component of this field.
	 * 
	 * @param component
	 *            the {@link ComponentType} of this field.
	 * 
	 * @param parentObject
	 *            the parent object of this field.
	 * 
	 * @param field
	 *            the {@link Field}.
	 */
	protected AbstractFieldComponent(ComponentType component,
			Object parentObject, Field field) {
		this.component = component;
		this.parentObject = parentObject;
		this.field = field;
		this.childFields = new ArrayList<FieldComponent<?>>();
	}

	@Override
	public AbstractFieldComponent<ComponentType> createField() {
		setupLocale();
		setupName();
		setupTitle();
		setupValue();
		setupWidth();
		setupReadOnly();
		setupToolTip();
		return this;
	}

	private void setupLocale() {
		String localeName = annotationAccess.getValue(
				FIELD_COMPONENT_ANNOTATION_CLASS, field, LOCALE_ELEMENT);
		Locale locale = isEmpty(localeName) ? Locale.getDefault() : new Locale(
				localeName);
		setLocale(locale);
	}

	private void setupName() {
		String name = field.getName();
		setName(name);
	}

	private void setupTitle() {
		String title = annotationAccess.getValue(
				FIELD_COMPONENT_ANNOTATION_CLASS, field, TITLE_ELEMENT);
		title = isEmpty(title) ? field.getName() : title;
		setTitle(title);
	}

	private void setupValue() {
		Object value = beanAccess.getValue(field, parentObject);
		if (value == null) {
			value = beanFactory.createBean(field.getType());
		}
		setValue(value);
	}

	private void setupWidth() {
		double width = annotationAccess.getValue(
				FIELD_COMPONENT_ANNOTATION_CLASS, field, WIDTH_ELEMENT);
		setWidth(width);
	}

	private void setupReadOnly() {
		boolean readOnly = annotationAccess.getValue(
				FIELD_COMPONENT_ANNOTATION_CLASS, field, READ_ONLY_ELEMENT);
		setEnabled(!readOnly);
	}

	private void setupToolTip() {
		String text = annotationAccess.getValue(
				FIELD_COMPONENT_ANNOTATION_CLASS, field, TOOL_TIP_ELEMENT);
		if (!isEmpty(text)) {
			setToolTipText(text);
		}
	}

	/**
	 * Injects the logger for this field component.
	 * 
	 * @param logger
	 *            the {@link AbstractFieldComponentLogger}.
	 */
	@Inject
	void setAbstractFieldComponentLogger(AbstractFieldComponentLogger logger) {
		this.log = logger;
	}

	/**
	 * Injects the annotation access to access the elements of an annotation.
	 * 
	 * @param annotationAccess
	 *            the {@link AnnotationAccess}.
	 */
	@Inject
	void setAnnotationAccess(AnnotationAccess annotationAccess) {
		this.annotationAccess = annotationAccess;
	}

	/**
	 * Injects the bean access to access the fields of a object.
	 * 
	 * @param beanAccess
	 *            the {@link BeanAccess}.
	 */
	@Inject
	void setBeanAccess(BeanAccess beanAccess) {
		this.beanAccess = beanAccess;
	}

	/**
	 * Injects the bean factory to create beans.
	 * 
	 * @param beanFactory
	 *            the {@link BeanFactory}.
	 */
	@Inject
	void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	@Override
	public AbstractFieldComponent<ComponentType> withTextsResource(Texts texts) {
		setTexts(texts);
		return this;
	}

	@Override
	public void setTexts(Texts texts) {
		log.checkTextsResource(this, texts);
		this.texts = texts;
		updateTitleResource();
	}

	private void updateTitleResource() {
		if (isEmpty(titleResource) || texts == null) {
			return;
		}
		try {
			title = texts.getResource(titleResource, getLocale()).getText();
		} catch (MissingResourceException e) {
			log.titleResourceMissing(this, titleResource);
		}
	}

	@Override
	public void setName(String newName) {
		log.checkName(this, newName);
		component.setName(newName);
		log.nameSet(this, newName);
	}

	@Override
	public String getName() {
		return component.getName();
	}

	@Override
	public void setTitle(String newTitle) {
		titleResource = newTitle;
		title = newTitle;
		updateTitleResource();
		log.titleSet(this, newTitle);
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setValue(Object newValue) {
		log.checkValue(this, newValue);
		value = newValue;
		log.valueSet(this, newValue);
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setEnabled(boolean enabled) {
		component.setEnabled(enabled);
		log.enabledSet(this, enabled);
	}

	@Override
	public boolean isEnabled() {
		return component.isEnabled();
	}

	@Override
	public void setWidth(Number newWidth) {
		log.checkWidth(this, newWidth);
		Dimension d = component.getPreferredSize();
		d.width = newWidth.intValue();
		component.setPreferredSize(new Dimension(d));
		log.widthSet(this, newWidth);
	}

	@Override
	public Number getWidth() {
		return component.getWidth();
	}

	@Override
	public void setLocale(Locale newLocale) {
		log.checkLocale(this, newLocale);
		component.setLocale(newLocale);
		updateTitleResource();
		log.localeSet(this, newLocale);
	}

	@Override
	public Locale getLocale() {
		return component.getLocale();
	}

	@Override
	public void setToolTipText(String text) {
		JComponent jcomponent = (JComponent) component;
		jcomponent.setToolTipText(text);
	}

	@Override
	public void setShowToolTip(boolean show) {
		if (show) {
			showToolTip();
		} else {
			hideToolTip();
		}
	}

	private void showToolTip() {
		int id = 0;
		long when = 0;
		int modifiers = 0;
		int x = 0;
		int y = 0;
		int clickCount = 0;
		ToolTipManager.sharedInstance().mouseMoved(
				new MouseEvent(component, id, when, modifiers, x, y,
						clickCount, false));
	}

	private void hideToolTip() {
		int id = 0;
		long when = 0;
		int modifiers = 0;
		int x = 0;
		int y = 0;
		int clickCount = 0;
		ToolTipManager.sharedInstance().mouseExited(
				new MouseEvent(component, id, when, modifiers, x, y,
						clickCount, false));
	}

	@Override
	public boolean isInputValid() {
		boolean valid = true;
		for (FieldComponent<?> component : childFields) {
			if (!component.isInputValid()) {
				valid = false;
				break;
			}
		}
		log.inputIsValid(this, valid);
		return valid;
	}

	@Override
	public ComponentType getAWTComponent() {
		return component;
	}

	@Override
	public void addField(FieldComponent<?> field) {
		log.checkField(this, field);
		childFields.add(field);
	}

	@Override
	public <T extends Component> FieldComponent<T> getField(String name) {
		for (FieldComponent<?> component : childFields) {
			if (getName().equals(component.getName())) {
				return toComponent(component);
			}
		}
		throw log.noChildFieldFound(this, name);
	}

	@SuppressWarnings("unchecked")
	private <T extends Component> FieldComponent<T> toComponent(
			FieldComponent<?> component) {
		return (FieldComponent<T>) component;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", getName()).toString();
	}
}
