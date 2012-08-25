package com.anrisoftware.prefdialog.core

import java.lang.reflect.Field

import javax.inject.Inject
import javax.swing.JComponent

import com.google.inject.assistedinject.Assisted

class MockFieldComponent extends AbstractFieldComponent {

	@Inject
	MockFieldComponent(@Assisted JComponent component, @Assisted Object parentObject,
	@Assisted Field field) {
		super(component, parentObject, field)
	}
}

