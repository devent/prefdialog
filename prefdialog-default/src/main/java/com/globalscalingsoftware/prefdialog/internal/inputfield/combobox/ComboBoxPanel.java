package com.globalscalingsoftware.prefdialog.internal.inputfield.combobox;

import java.awt.FlowLayout;
import java.util.Collection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ComboBoxPanel extends JPanel {

	private class ComboBoxModel extends DefaultComboBoxModel {

		public ComboBoxModel(Collection<?> values) {
			super(values.toArray());
		}

	}

	private ComboBoxModel comboBoxModel;

	private final JComboBox comboBox;

	public ComboBoxPanel() {
		comboBox = new JComboBox();
		setupPanel();
	}

	private void setupPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(comboBox);
	}

	public void setValues(Object values) {
		comboBoxModel = new ComboBoxModel((Collection<?>) values);
		comboBox.setModel(comboBoxModel);
	}

	public Object getValue() {
		return comboBox.getSelectedItem();
	}

	public void setValue(Object value) {
		comboBox.setSelectedItem(value);
	}
}
