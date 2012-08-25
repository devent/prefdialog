package com.anrisoftware.prefdialog.core;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.reflection.annotations.AnnotationAccess;
import com.anrisoftware.prefdialog.reflection.beans.BeanAccess;

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
public abstract class AbstractFieldComponent<ComponentType extends JComponent>
		implements FieldComponent<ComponentType> {

	private static final Class<com.anrisoftware.prefdialog.annotations.FieldComponent> FIELD_COMPONENT_ANNOTATION_CLASS = com.anrisoftware.prefdialog.annotations.FieldComponent.class;

	private static final String TITLE_ELEMENT = "title";

	private static final String WIDTH_ELEMENT = "width";

	private static final String READ_ONLY_ELEMENT = "readOnly";

	private static final String TOOL_TIP_ELEMENT = "toolTip";

	private final ComponentType component;

	private final Object parentObject;

	private final Field field;

	private final Class<? extends Annotation> annotationClass;

	private final List<FieldComponent<?>> childFields;

	private AbstractFieldComponentLogger log;

	private String title;

	private Object value;

	private AnnotationAccess annotationAccess;

	private BeanAccess beanAccess;

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
		setupToolTip();
	}

	private void setupName() {
		String name = field.getName();
		setName(name);
	}

	private void setupTitle() {
		String title = annotationAccess.getValue(
				FIELD_COMPONENT_ANNOTATION_CLASS, field, TITLE_ELEMENT);
		title = isEmpty(title) ? null : title;
		setTitle(title);
	}

	private void setupValue() {
		Object value = beanAccess.getValue(field, parentObject);
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
		setEnabled(readOnly);
	}

	private void setupToolTip() {
		String text = annotationAccess.getValue(
				FIELD_COMPONENT_ANNOTATION_CLASS, field, TOOL_TIP_ELEMENT);
		text = isEmpty(text) ? null : text;
		setToolTipText(text);
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
		title = newTitle;
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
	public void setToolTipText(String text) {
		component.setToolTipText(text);
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
