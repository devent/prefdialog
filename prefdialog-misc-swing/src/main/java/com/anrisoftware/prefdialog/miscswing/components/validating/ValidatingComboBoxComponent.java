package com.anrisoftware.prefdialog.miscswing.components.validating;

import javax.swing.JComboBox;

/**
 * Test the input of a combo-box component.
 * 
 * @see AbstractValidatingComponent
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ValidatingComboBoxComponent<ComponentType extends JComboBox<?>>
		extends AbstractValidatingComponent<Integer, ComponentType> {

	/**
	 * Sets the combo-box component for with the input will be validated.
	 * 
	 * @param component
	 *            the {@link JComboBox}.
	 */
	public ValidatingComboBoxComponent(ComponentType component) {
		super(component);
	}

	@Override
	protected void setComponentValue(Integer value) {
		getComponent().setSelectedIndex(value);
	}

	@Override
	protected Integer getComponentValue() {
		return getComponent().getSelectedIndex();
	}

}
