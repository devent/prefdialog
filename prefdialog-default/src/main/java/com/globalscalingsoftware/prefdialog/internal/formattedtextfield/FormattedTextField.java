package com.globalscalingsoftware.prefdialog.internal.formattedtextfield;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.globalscalingsoftware.prefdialog.IFormattedTextField;
import com.globalscalingsoftware.prefdialog.IValidator;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class FormattedTextField implements IFormattedTextField {

	private final ValidatingFormattedTextField textField;

	private final JLabel label;

	private final JPanel panel;

	private final Object value;

	private final String fieldName;

	private final String helpText;

	@Inject
	FormattedTextField(@Assisted("value") Object value,
			@Assisted("fieldName") String fieldName,
			@Assisted("helpText") String helpText,
			@Assisted("validator") IValidator<?> validator) {
		this.fieldName = fieldName;
		this.value = value;
		this.helpText = helpText;
		panel = new JPanel();
		textField = new ValidatingFormattedTextField(value, validator);
		label = new JLabel();
	}

	@Override
	public Component getComponent() {
		setupPanel();
		textField.addValidListener(new ValidListener() {

			@Override
			public void validChanged(ValidEvent validEvent) {
				if (validEvent.isEditValid()) {
					clearHelpText();
				} else {
					setHelpText();
				}
			}
		});
		return panel;
	}

	private void setHelpText() {
		String text = format("%s (%s): ", fieldName, helpText);
		label.setText(text);
	}

	private void clearHelpText() {
		String text = format("%s: ", fieldName);
		label.setText(text);
	}

	private void setupPanel() {
		double[] col = { TableLayout.FILL, };
		double[] row = { TableLayout.PREFERRED, TableLayout.PREFERRED };
		panel.setLayout(new TableLayout(col, row));

		panel.add(label, "0, 0");
		panel.add(textField, "0, 1");

		label.setText(fieldName + ": ");
		label.setLabelFor(textField);
		textField.setValue(value);
	}

	@Override
	public Object getValue() {
		return textField.getValue();
	}

}
