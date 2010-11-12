package com.globalscalingsoftware.prefdialog.internal.inputfield.combobox;

import java.util.Collection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractLabelFieldPanel;

@SuppressWarnings("serial")
public class ComboBoxPanel extends AbstractLabelFieldPanel<JComboBox> {

	private static class ComboBoxModel extends DefaultComboBoxModel {

		public ComboBoxModel(Collection<?> values) {
			super(values.toArray());
		}

	}

	private ComboBoxModel comboBoxModel;

	public ComboBoxPanel() {
		super(new JComboBox());
	}

	public void setValues(Object values) {
		comboBoxModel = new ComboBoxModel((Collection<?>) values);
		getPanelField().setModel(comboBoxModel);
	}

	@Override
	public Object getValue() {
		return getPanelField().getSelectedItem();
	}

	@Override
	public void setValue(Object value) {
		getPanelField().setSelectedItem(value);
	}

}
