package com.globalscalingsoftware.prefdialog.internal.formattedtextfield;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.globalscalingsoftware.prefdialog.IInputField;

public class AbstractTextField implements IInputField {

	private final String fieldName;

	private final String helpText;

	private final JLabel label;

	private final JPanel panel;

	private final ValidatingTextField<?> textField;

	public AbstractTextField(String fieldName, String helpText,
			ValidatingTextField<?> textField) {
		this.fieldName = fieldName;
		this.textField = textField;
		this.helpText = helpText;
		panel = new JPanel();
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
		panel.add(textField.getField(), "0, 1");

		label.setText(fieldName + ": ");
		label.setLabelFor(textField.getField());
	}

	@Override
	public Object getValue() {
		return textField.getValue();
	}

}
