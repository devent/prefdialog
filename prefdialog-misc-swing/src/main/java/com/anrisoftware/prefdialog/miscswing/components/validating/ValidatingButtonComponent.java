package com.anrisoftware.prefdialog.miscswing.components.validating;

import javax.swing.AbstractButton;

/**
 * Test the input of a button component.
 * 
 * @see AbstractValidatingComponent
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ValidatingButtonComponent<ComponentType extends AbstractButton>
		extends AbstractValidatingComponent<ComponentType> {

	/**
	 * Sets the button component for with the input will be validated.
	 * 
	 * @param component
	 *            the {@link AbstractButton}.
	 */
	public ValidatingButtonComponent(ComponentType component) {
		super(component);
	}

	@Override
	protected void setComponentValue(Object value) {
		boolean selected = (Boolean) value;
		getComponent().setSelected(selected);
	}

	@Override
	protected Object getComponentValue() {
		return getComponent().isSelected();
	}

}
