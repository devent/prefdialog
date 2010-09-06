package com.globalscalingsoftware.prefdialog.internal;

import info.clearthought.layout.TableLayout;

import java.awt.Component;
import java.lang.reflect.Field;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.globalscalingsoftware.prefdialog.IFormattedTextField;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class FormattedTextField implements IFormattedTextField {

	private final ValidatingFormattedTextField textField;

	private final JLabel label;

	private final JPanel panel;

	private final Object parentObject;

	private final Field field;

	private final Object value;

	@Inject
	FormattedTextField(@Assisted("parentObject") Object parentObject,
			@Assisted("field") Field field, @Assisted("value") Object value) {
		this.parentObject = parentObject;
		this.field = field;
		this.value = value;
		panel = new JPanel();
		textField = new ValidatingFormattedTextField();
		label = new JLabel();
	}

	@Override
	public Component getComponent() {
		setupPanel();
		return panel;
	}

	private void setupPanel() {
		double[] col = { TableLayout.FILL, };
		double[] row = { TableLayout.PREFERRED, TableLayout.PREFERRED };
		panel.setLayout(new TableLayout(col, row));

		panel.add(label, "0, 0");
		panel.add(textField, "0, 1");

		label.setText(field.getName() + ": ");
		label.setLabelFor(textField);
		textField.setValue(value);
	}

	@Override
	public Object getValue() {
		return textField.getValue();
	}

}
