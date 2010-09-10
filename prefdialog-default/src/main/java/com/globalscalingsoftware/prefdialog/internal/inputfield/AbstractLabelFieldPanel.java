package com.globalscalingsoftware.prefdialog.internal.inputfield;

import info.clearthought.layout.TableLayout;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractLabelFieldPanel<FieldType extends Component>
		extends JPanel {

	private final JLabel label;

	private final FieldType textField;

	public AbstractLabelFieldPanel(FieldType textField) {
		this.textField = textField;
		label = new JLabel();
		setupPanel();
	}

	public abstract void setValue(Object value);

	public abstract Object getValue();

	public void setFieldName(String name) {
		label.setText(name + ": ");
	}

	public void setLabelText(String text) {
		label.setText(text);
	}

	protected void setupPanel() {
		double[] col = { TableLayout.FILL, };
		double[] row = { TableLayout.PREFERRED, TableLayout.PREFERRED };
		setLayout(new TableLayout(col, row));

		add(label, "0, 0");
		add(textField, "0, 1");

		label.setLabelFor(textField);
	}

}