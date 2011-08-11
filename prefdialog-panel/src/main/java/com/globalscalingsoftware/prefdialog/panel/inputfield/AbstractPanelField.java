package com.globalscalingsoftware.prefdialog.panel.inputfield;

import info.clearthought.layout.TableLayout;

import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;

public abstract class AbstractPanelField<FieldType extends JComponent> extends
		AbstractFieldComponent<JPanel> {

	private TableLayout layout;

	private final FieldType field;

	public AbstractPanelField(FieldType field) {
		super(new JPanel());
		this.field = field;
		this.layout = createLayout();
		setupPanel();
	}

	private TableLayout createLayout() {
		double[] col = { TableLayout.FILL };
		double[] row = { TableLayout.PREFERRED };
		return new TableLayout(col, row);
	}

	private void setupPanel() {
		JPanel panel = (JPanel) getAWTComponent();
		panel.setLayout(layout);

		panel.add(field, "0, 0");

		field.requestFocus();
	}

	public void setLayout(TableLayout layout) {
		this.layout = layout;
	}

	public FieldType getPanelField() {
		return field;
	}

	public TableLayout getLayout() {
		return layout;
	}

	@Override
	public void setWidth(double width) {
		JPanel panel = (JPanel) getAWTComponent();
		layout.setColumn(0, width);
		layout.layoutContainer(panel);
		panel.repaint();
	}

	@Override
	public void setName(String name) {
		super.setName("panel-" + name);
		field.setName(name);
	}

	@Override
	public void setEnabled(boolean enabled) {
		field.setEnabled(enabled);
	}

	public void setToolTipText(String text) {
		field.setToolTipText(text);
	}

	public void showToolTip() {
		int id = 0;
		long when = 0;
		int modifiers = 0;
		int x = 0;
		int y = 0;
		int clickCount = 0;
		ToolTipManager.sharedInstance().mouseMoved(
				new MouseEvent(field, id, when, modifiers, x, y, clickCount,
						false));
	}

	public void hideToolTip() {
		int id = 0;
		long when = 0;
		int modifiers = 0;
		int x = 0;
		int y = 0;
		int clickCount = 0;
		ToolTipManager.sharedInstance().mouseExited(
				new MouseEvent(field, id, when, modifiers, x, y, clickCount,
						false));
	}

}