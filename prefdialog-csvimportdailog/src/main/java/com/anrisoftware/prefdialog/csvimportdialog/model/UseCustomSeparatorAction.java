package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.anrisoftware.prefdialog.fields.FieldComponent;

public class UseCustomSeparatorAction implements ActionListener {

	private FieldComponent<?> customSeparatorCharField;

	private FieldComponent<?> separatorCharField;

	public void setCustomSeparatorCharField(FieldComponent<?> field) {
		this.customSeparatorCharField = field;
	}

	public void setSeparatorCharField(FieldComponent<?> field) {
		this.separatorCharField = field;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean enabled = customSeparatorCharField.isEnabled();
		customSeparatorCharField.setEnabled(!enabled);
		separatorCharField.setEnabled(enabled);
	}

}
