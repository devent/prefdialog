package com.anrisoftware.prefdialog.miscswing.validatingfields;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import org.apache.commons.lang3.ObjectUtils;

/**
 * Test the input of a text component.
 * 
 * @see AbstractValidatingComponent
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ValidatingTextComponent<ComponentType extends JTextComponent>
		extends AbstractValidatingComponent<Object, ComponentType> {

	private final ActionListener actionListener;

	/**
	 * Sets the text component for with the input will be validated.
	 * 
	 * @param component
	 *            the {@link JTextComponent}.
	 */
	public ValidatingTextComponent(ComponentType component) {
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
		JTextComponent component = getComponent();
		if (component instanceof JTextField) {
			JTextField textField = (JTextField) component;
			textField.addActionListener(actionListener);
		}
	}

	@Override
	protected void setComponentValue(Object value) {
		getComponent().setText(ObjectUtils.toString(value));
	}

	@Override
	protected Object getComponentValue() {
		return getComponent().getText();
	}

}
