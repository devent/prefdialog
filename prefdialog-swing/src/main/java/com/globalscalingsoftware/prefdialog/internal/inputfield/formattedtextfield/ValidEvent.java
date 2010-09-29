package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

class ValidEvent {

	private final ValidatingTextField<?> textField;

	private final boolean editValid;

	public ValidEvent(ValidatingTextField<?> textField, boolean editValid) {
		this.textField = textField;
		this.editValid = editValid;
	}

	public ValidatingTextField<?> getTextField() {
		return textField;
	}

	public boolean isEditValid() {
		return editValid;
	}

}
