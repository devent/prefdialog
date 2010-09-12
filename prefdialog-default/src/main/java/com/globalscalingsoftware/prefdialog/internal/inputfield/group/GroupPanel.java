package com.globalscalingsoftware.prefdialog.internal.inputfield.group;

import info.clearthought.layout.TableLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.globalscalingsoftware.prefdialog.internal.inputfield.IComponent;

@SuppressWarnings("serial")
public class GroupPanel extends JPanel implements IComponent {

	private final JLabel label;

	private final JSeparator seperator;

	public GroupPanel() {
		label = new JLabel();
		seperator = new JSeparator(JSeparator.HORIZONTAL);
		setupPanel();
	}

	private void setupPanel() {
		double[] col = { TableLayout.FILL };
		double[] row = { TableLayout.PREFERRED, TableLayout.PREFERRED,
				TableLayout.FILL };
		TableLayout layout = new TableLayout(col, row);
		setLayout(layout);

		add(label, "0, 0");
		add(seperator, "0, 1");
	}

	@Override
	public void setFieldWidth(double width) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFieldName(String name) {
		label.setText(name);
	}

	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addPreferencesPanel(JPanel panel) {
		add(panel, "0, 2");
	}

}
