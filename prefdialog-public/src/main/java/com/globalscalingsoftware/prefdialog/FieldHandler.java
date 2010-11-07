package com.globalscalingsoftware.prefdialog;

import java.awt.Component;

/**
 * Setups and Handles outside messages to the underlying {@link FieldComponent}.
 */
public interface FieldHandler<FieldComponentType extends FieldComponent> {

	/**
	 * Setup the underlying {@link FieldComponent}.
	 */
	void setup();

	/**
	 * Sets the width of the component, as specified by the <code>width</code>
	 * annotation type element.
	 */
	void setComponentWidth(double width);

	/**
	 * Sets the name of the component. The name will be used to identify the
	 * component.
	 */
	void setComponentName(String name);

	/**
	 * Sets the title of the component. The title will either be used as text
	 * for the component's label.
	 */
	void setComponentTitle(String title);

	/**
	 * Sets the value of the component. The value must be the same type (or can
	 * be casts/parsed into) as the type of the attribute which this component
	 * represents.
	 */
	void setComponentValue(Object value);

	/**
	 * Returns the value of the component. After the call of
	 * {@link FieldHandler#applyInput(Object)} it should return the new value.
	 * After the call of {@link FieldHandler#restoreInput(Object)} it should
	 * return the old value.
	 */
	Object getComponentValue();

	/**
	 * Returns the AWT {@link Component} which can be added to a swing
	 * container.
	 */
	Component getAWTComponent();

	/**
	 * Apply the user input.
	 * 
	 * @param parent
	 *            the parent object to which this component belongs to.
	 */
	void applyInput(Object parent);

	/**
	 * Restore the value after the user entered some input.
	 * 
	 * @param parent
	 *            the parent object to which this component belongs to.
	 */
	void restoreInput(Object parent);

}