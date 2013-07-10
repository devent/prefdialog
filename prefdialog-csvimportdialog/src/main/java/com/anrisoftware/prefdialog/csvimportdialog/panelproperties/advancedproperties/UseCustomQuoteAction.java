package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.advancedproperties;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.anrisoftware.prefdialog.fields.FieldComponent;

public class UseCustomQuoteAction implements ActionListener {

	private FieldComponent<?> customQuoteField;

	private FieldComponent<?> quoteField;

	public void setCustomQuoteField(FieldComponent<?> field) {
		this.customQuoteField = field;
	}

	public void setQuoteField(FieldComponent<?> field) {
		this.quoteField = field;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean enabled = customQuoteField.isEnabled();
		customQuoteField.setEnabled(!enabled);
		quoteField.setEnabled(enabled);
	}

}
