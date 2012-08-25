package com.anrisoftware.prefdialog.core;

import java.awt.Component;
import java.awt.Dimension;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.reflection.annotations.AnnotationAccess;

/**
 * Sets the component and sets the component name, width, and if the component
 * is enabled or disabled. Sets the common fields for the field component:
 * 
 * <ul>
 * <li>name,</li>
 * <li>title,</li>
 * <li>width,</li>
 * <li>value,</li>
 * <li>read-only flag.</li>
 * </ul>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public abstract class AbstractFieldComponent<ComponentType extends Component>
		implements FieldComponent<ComponentType> {

	private static final Class<com.anrisoftware.prefdialog.annotations.FieldComponent> FIELD_COMPONENT_ANNOTATION_CLASS = com.anrisoftware.prefdialog.annotations.FieldComponent.class;

	private static final Pair<String, Class<String>> TITLE_ELEMENT = new ImmutablePair<String, Class<String>>(
			"title", String.class);

	private static final Pair<String, Class<Double>> WIDTH_ELEMENT = new ImmutablePair<String, Class<Double>>(
			"width", Double.class);

	private static final Pair<String, Class<Boolean>> READ_ONLY_ELEMENT = new ImmutablePair<String, Class<Boolean>>(
			"readOnly", Boolean.class);

	private final ComponentType component;

	private final Object parentObject;

	private final Field field;

	private final Class<? extends Annotation> annotationClass;

	private final List<FieldComponent<?>> childFields;

	private AbstractFieldComponentLogger log;

	private String title;

	private Object value;

	private AnnotationAccess annotationAccess;

	protected AbstractFieldComponent(ComponentType component,
			Object parentObject, Field field,
			Class<? extends Annotation> annotationClass) {
		this.component = component;
		this.parentObject = parentObject;
		this.field = field;
		this.annotationClass = annotationClass;
		this.childFields = new ArrayList<FieldComponent<?>>();
		setup();
	}

	private void setup() {
		setupName();
		setupTitle();
		setupValue();
		setupWidth();
		setupReadOnly();
	}

	private void setupName() {
		String name = field.getName();
		setName(name);
	}

	private void setupTitle() {
		String title = annotationAccess
				.getElementValue(FIELD_COMPONENT_ANNOTATION_CLASS, field,
						TITLE_ELEMENT.getKey());
		setTitle(title);
	}

	private void setupValue() {
	}

	private void setupWidth() {
		double width = annotationAccess
				.getElementValue(FIELD_COMPONENT_ANNOTATION_CLASS, field,
						WIDTH_ELEMENT.getKey());
		setWidth(width);
	}

	private void setupReadOnly() {
		boolean readOnly = annotationAccess.getElementValue(
				FIELD_COMPONENT_ANNOTATION_CLASS, field,
				READ_ONLY_ELEMENT.getKey());
		setEnabled(readOnly);
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

	@Override
	public void setName(String newName) {
		component.setName(newName);
		log.nameSet(this, newName);
	}

	@Override
	public String getName() {
		return component.getName();
	}

	@Override
	public void setTitle(String newTitle) {
		title = newTitle;
		log.titleSet(this, newTitle);
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setValue(Object newValue) {
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
