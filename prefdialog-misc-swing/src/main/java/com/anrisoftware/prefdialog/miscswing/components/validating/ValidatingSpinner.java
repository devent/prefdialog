package com.anrisoftware.prefdialog.miscswing.components.validating;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Validate the input of a spinner component.
 * 
 * @see AbstractValidatingComponent
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ValidatingSpinner<ComponentType extends JSpinner> extends
		AbstractValidatingComponent<Object, ComponentType> {

	private final ChangeListener listener;

	private String parseErrorText;

	private final FocusAdapter focusListener;

	/**
	 * Sets the spinner component for with the input will be validated.
	 * 
	 * @param component
	 *            the {@link JSpinner}.
	 */
	public ValidatingSpinner(ComponentType component) {
		super(component);
		this.parseErrorText = null;
		this.listener = new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				validateInput();
			}
		};
		this.focusListener = new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				validateInput();
			}

			@Override
			public void focusLost(FocusEvent e) {
				validateInput();
				restoreValueIfInvalid();
			}

		};
		setupSpinner();
	}

	private void setupSpinner() {
		JSpinner component = getComponent();
		component.addChangeListener(listener);
		if (component.getEditor() instanceof JSpinner.DefaultEditor) {
			JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) component
					.getEditor();
			editor.getTextField().addFocusListener(focusListener);
		}
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
	protected void setComponentValue(Object value) {
		try {
			getComponent().setValue(value);
		} catch (IllegalArgumentException e) {
		}
	}

	@Override
	protected Object getComponentValue() {
		return getComponent().getValue();
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
