package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.globalscalingsoftware.prefdialog.IValidator;

@SuppressWarnings("serial")
class TextFieldPanel extends JPanel {

	private final JLabel label;

	private final ValidatingTextField<?> textField;

	private String fieldName;

	public TextFieldPanel(ValidatingTextField<?> textField) {
		this.textField = textField;
		label = new JLabel();
		setupPanel();
	}

	public void setFieldName(String name) {
		this.fieldName = name;
		label.setText(name + ": ");
	}

	private void setupPanel() {
		double[] col = { TableLayout.FILL, };
		double[] row = { TableLayout.PREFERRED, TableLayout.PREFERRED };
		setLayout(new TableLayout(col, row));

		add(label, "0, 0");
		add(textField.getField(), "0, 1");

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

	public void setValidator(IValidator<?> validator) {
		textField.setValidator(validator);
	}
}
