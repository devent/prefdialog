package com.anrisoftware.prefdialog.fields;

import java.awt.Component;
import java.awt.Dimension;

/**
 * Sets the component and sets the component name, width, and if the component
 * is enabled or disabled.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public abstract class AbstractFieldComponent<ComponentType extends Component>
		implements FieldComponent<ComponentType> {

	private final ComponentType component;

	private String title;

	private Object value;

	protected AbstractFieldComponent(ComponentType component) {
		this.component = component;
	}

	@Override
	public void setName(String newName) {
		component.setName(newName);
	}

	@Override
	public String getName() {
		return component.getName();
	}

	@Override
	public void setTitle(String newTitle) {
		title = newTitle;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setValue(Object newValue) {
		value = newValue;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setEnabled(boolean enabled) {
		component.setEnabled(enabled);
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
	}

	@Override
	public <T extends Component> FieldComponent<T> getField(String name) {
		throw new NullPointerException();
	}
}
