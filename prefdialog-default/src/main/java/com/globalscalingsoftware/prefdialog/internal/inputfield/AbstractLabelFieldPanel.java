package com.globalscalingsoftware.prefdialog.internal.inputfield;

import info.clearthought.layout.TableLayout;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractLabelFieldPanel<FieldType extends Component>
		extends JPanel {

	private final JLabel label;

	private final FieldType field;

	private final TableLayout layout;

	public AbstractLabelFieldPanel(FieldType field) {
		this.field = field;
		label = new JLabel();
		double[] col = { TableLayout.FILL };
		double[] row = { TableLayout.PREFERRED, TableLayout.PREFERRED };
		layout = new TableLayout(col, row);
		setupPanel();
	}

	protected void setupPanel() {
		setLayout(layout);

		add(label, "0, 0");
		add(field, "0, 1");

		label.setLabelFor(field);
	}

	public FieldType getField() {
		return field;
	}

	public abstract void setValue(Object value);

	public abstract Object getValue();

	public void setFieldName(String name) {
		label.setText(name + ": ");
	}

	public void setLabelText(String text) {
		label.setText(text);
	}

	public void setFieldWidth(double width) {
		layout.setColumn(0, width);
		layout.layoutContainer(this);
		repaint();
	}
}