package com.globalscalingsoftware.prefdialog;

import com.google.inject.assistedinject.Assisted;

public interface IFormattedTextFieldFactory {

	IFormattedTextField createFormattedTextField(
			@Assisted("value") Object value,
			@Assisted("fieldName") String fieldName,
			@Assisted("helpText") String helpText);

}
