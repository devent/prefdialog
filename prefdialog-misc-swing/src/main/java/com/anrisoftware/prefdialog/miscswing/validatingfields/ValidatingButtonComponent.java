package com.anrisoftware.prefdialog.miscswing.validatingfields;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
		extends AbstractValidatingComponent<Object, ComponentType> {

	private final ActionListener actionListener;

	private final ChangeListener changeListener;

	/**
	 * Sets the button component for with the input will be validated.
	 * 
	 * @param component
	 *            the {@link AbstractButton}.
	 */
	public ValidatingButtonComponent(ComponentType component) {
		super(component);
		this.actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				validateInput();
			}
		};
		this.changeListener = new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				validateInput();
			}
		};
		setupListeners();
	}

	private void setupListeners() {
		AbstractButton component = getComponent();
		component.addActionListener(actionListener);
		component.addChangeListener(changeListener);
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
