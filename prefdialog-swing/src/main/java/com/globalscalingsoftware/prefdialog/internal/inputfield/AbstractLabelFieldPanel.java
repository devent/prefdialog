package com.globalscalingsoftware.prefdialog.internal.inputfield;

import info.clearthought.layout.TableLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AbstractLabelFieldPanel<FieldType extends JComponent>
		extends AbstractFieldPanel<JPanel> {

	private final JLabel label;

	private final TableLayout layout;

	private final FieldType field;

	public AbstractLabelFieldPanel(FieldType field) {
		super(new JPanel());
		this.field = field;
		this.layout = createLayout();
		this.label = new JLabel();
		setupPanel();
	}

	private TableLayout createLayout() {
		double[] col = { TableLayout.FILL };
		double[] row = { TableLayout.PREFERRED, TableLayout.PREFERRED };
		return new TableLayout(col, row);
	}

	private void setupPanel() {
		JPanel panel = (JPanel) getAWTComponent();
		panel.setLayout(layout);

		panel.add(label, "0, 0");
		panel.add(field, "0, 1");

		label.setLabelFor(field);
		field.requestFocus();
	}

	public FieldType getPanelField() {
		return field;
	}

	@Override
	public abstract void setValue(Object value);

	@Override
	public abstract Object getValue();

	@Override
	public void setTitle(String title) {
		label.setText(title + ": ");
	}

	public void setLabelText(String text) {
		label.setText(text);
	}

	@Override
	public void setName(String name) {
		super.setName("panel-" + name);
		label.setName("label-" + name);
		field.setName(name);
	}

	@Override
	public void setWidth(double width) {
		JPanel panel = (JPanel) getAWTComponent();
		layout.setColumn(0, width);
		layout.layoutContainer(panel);
		panel.repaint();
	}

}