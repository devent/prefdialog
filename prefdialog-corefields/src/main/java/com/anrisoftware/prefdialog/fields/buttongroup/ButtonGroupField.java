package com.anrisoftware.prefdialog.fields.buttongroup;

import java.awt.Container;
import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.google.inject.assistedinject.Assisted;

class ButtonGroupField extends AbstractTitleField<JPanel, Container> {

	@Inject
	ButtonGroupField(@Assisted Container container,
			@Assisted Object parentObject, @Assisted Field field) {
		super(new JPanel(), container, parentObject, field);
	}

}
