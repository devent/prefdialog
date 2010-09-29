package com.globalscalingsoftware.prefdialog.internal.inputfield;

import info.clearthought.layout.TableLayout;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.globalscalingsoftware.prefdialog.InputFieldComponent;

public abstract class AbstractLabelFieldPanel<FieldType extends Component>
		implements InputFieldComponent {

	private final JPanel panel;

	private final JLabel label;

	private final FieldType field;

	private final TableLayout layout;

	public AbstractLabelFieldPanel(FieldType field) {
		this.panel = new JPanel();
		this.field = field;
		this.label = new JLabel();
		this.layout = createLayout();
		setupPanel();
	}

	private TableLayout createLayout() {
		double[] col = { TableLayout.FILL };
		double[] row = { TableLayout.PREFERRED, TableLayout.PREFERRED };
		return new TableLayout(col, row);
	}

	private void setupPanel() {
		panel.setLayout(layout);

		panel.add(label, "0, 0");
		panel.add(field, "0, 1");

		label.setLabelFor(field);
	}

	public FieldType getField() {
		return field;
	}

	@Override
	public abstract void setValue(Object value);

	@Override
	public abstract Object getValue();

	@Override
	public void setName(String name) {
		label.setText(name + ": ");
	}

	public void setLabelText(String text) {
		label.setText(text);
	}

	@Override
	public void setWidth(double width) {
		layout.setColumn(0, width);
		layout.layoutContainer(panel);
		panel.repaint();
	}

	@Override
	public Component getAWTComponent() {
		return panel;
	}
}