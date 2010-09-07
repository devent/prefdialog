package com.globalscalingsoftware.prefdialog;

import com.google.inject.assistedinject.Assisted;

public interface ITextFieldFactory {

	ITextField create(@Assisted("value") Object value,
			@Assisted("fieldName") String fieldName,
			@Assisted("helpText") String helpText,
			@Assisted("validator") IValidator<?> validator);
}
