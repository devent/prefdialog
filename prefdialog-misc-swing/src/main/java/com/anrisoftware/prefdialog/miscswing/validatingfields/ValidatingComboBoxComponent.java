package com.anrisoftware.prefdialog.miscswing.validatingfields;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class ValidatingComboBoxComponent<ValueType, ComponentType extends JComboBox<? extends ValueType>>
		extends AbstractValidatingComponent<ValueType, ComponentType> {

	private final ActionListener actionListener;

	/**
	 * Sets the combo-box component for with the input will be validated.
	 * 
	 * @param component
	 *            the {@link JComboBox}.
	 */
	public ValidatingComboBoxComponent(ComponentType component) {
		super(component);
		this.actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				validateInput();
			}
		};
		setupListeners();
	}

	private void setupListeners() {
		ComponentType component = getComponent();
		component.addActionListener(actionListener);
	}

	@Override
	protected void setComponentValue(ValueType value) {
		getComponent().setSelectedItem(value);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ValueType getComponentValue() {
		return (ValueType) getComponent().getSelectedItem();
	}

}
