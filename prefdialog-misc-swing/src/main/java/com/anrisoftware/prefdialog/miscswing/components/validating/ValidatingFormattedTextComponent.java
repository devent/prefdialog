package com.anrisoftware.prefdialog.miscswing.components.validating;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFormattedTextField;

/**
 * Test the input of a formatted text component.
 * 
 * @see AbstractValidatingComponent
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ValidatingFormattedTextComponent<ValueType, ComponentType extends JFormattedTextField>
		extends AbstractValidatingComponent<ValueType, ComponentType> {

	private final ActionListener actionListener;

	private String parseErrorText;

	/**
	 * Sets the text component for with the input will be validated.
	 * 
	 * @param component
	 *            the {@link JFormattedTextField}.
	 */
	public ValidatingFormattedTextComponent(ComponentType component) {
		super(component);
		this.parseErrorText = null;
		this.actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				validateInput();
			}
		};
		setupListeners();
	}

	private void setupListeners() {
		JFormattedTextField component = getComponent();
		component.addActionListener(actionListener);
	}

	@Override
	protected void validateInput() {
		try {
			getComponent().commitEdit();
			super.validateInput();
		} catch (ParseException e) {
			if (parseErrorText == null) {
				setInvalidText(e.getLocalizedMessage());
			} else {
				setInvalidText(parseErrorText);
			}
			setValueValid(false);
		}
	}

	@Override
	protected void setComponentValue(ValueType value) {
		getComponent().setValue(value);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ValueType getComponentValue() {
		return (ValueType) getComponent().getValue();
	}

	/**
	 * Set the text that is shown if the user input is can not be parsed by this
	 * field.
	 * 
	 * @param text
	 *            the {@link String} text.
	 */
	public void setParseErrorText(String text) {
		this.parseErrorText = text;
	}

}
