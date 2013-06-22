package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.anrisoftware.prefdialog.fields.FieldComponent;

public class UseCustomSeparatorAction implements ActionListener {

	private FieldComponent<?> field;

	public void setUseCustomSeparatorField(FieldComponent<?> field) {
		this.field = field;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed()"); // TODO println
		// field.setEnabled(!field.isEnabled());
		// TODO Auto-generated method stub

	}

}
