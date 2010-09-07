package com.globalscalingsoftware.prefdialog.internal;

class ValidEvent {

	private final ValidatingFormattedTextField textField;

	private final boolean editValid;

	public ValidEvent(ValidatingFormattedTextField textField, boolean editValid) {
		this.textField = textField;
		this.editValid = editValid;
	}

	public ValidatingFormattedTextField getTextField() {
		return textField;
	}

	public boolean isEditValid() {
		return editValid;
	}

}
