package com.anrisoftware.prefdialog;

import java.awt.Component;
import java.awt.Dimension;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.prefdialog.fields.FieldComponent;

/**
 * Sets the component and sets the component name, width, and if the component
 * is enabled or disabled. Sets the common fields for the field component:
 * 
 * <ul>
 * <li>name,</li>
 * <li>width,</li>
 * <li>read-only flag.</li>
 * </ul>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public abstract class AbstractFieldComponent<ComponentType extends Component>
		implements FieldComponent<ComponentType> {

	private final ComponentType component;

	private final Object parentObject;

	private final Field field;

	private final Class<? extends Annotation> annotationClass;

	private final List<FieldComponent<?>> childFields;

	private AbstractFieldComponentLogger log;

	private String title;

	private Object value;

	protected AbstractFieldComponent(ComponentType component,
			Object parentObject, Field field,
			Class<? extends Annotation> annotationClass) {
		this.component = component;
		this.parentObject = parentObject;
		this.field = field;
		this.annotationClass = annotationClass;
		this.childFields = new ArrayList<FieldComponent<?>>();
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
		return true;
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
