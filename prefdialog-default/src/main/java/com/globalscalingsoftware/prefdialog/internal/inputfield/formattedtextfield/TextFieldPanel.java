package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TextFieldPanel extends JPanel {

	private final JLabel label;

	private final String fieldName;

	private final ValidatingTextField<?> textField;

	public TextFieldPanel(String fieldName, ValidatingTextField<?> textField) {
		this.fieldName = fieldName;
		this.textField = textField;
		label = new JLabel();

		setupPanel();
	}

	private void setupPanel() {
		double[] col = { TableLayout.FILL, };
		double[] row = { TableLayout.PREFERRED, TableLayout.PREFERRED };
		setLayout(new TableLayout(col, row));

		add(label, "0, 0");
		add(textField.getField(), "0, 1");

		label.setText(fieldName + ": ");
		label.setLabelFor(textField.getField());
	}

	public void setHelpText(String helpText) {
		String text = format("%s (%s): ", fieldName, helpText);
		label.setText(text);
	}

	public void clearHelpText() {
		String text = format("%s: ", fieldName);
		label.setText(text);
	}

	public Object getValue() {
		return textField.getValue();
	}

	public void addValidListener(ValidListener l) {
		textField.addValidListener(l);
	}
}
