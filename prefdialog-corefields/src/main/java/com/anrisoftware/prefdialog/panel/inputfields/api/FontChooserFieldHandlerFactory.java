package com.anrisoftware.prefdialog.panel.inputfields.api;

import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new font chooser {@link FieldHandler}. It uses a special
 * {@link JComboBox} to display the selected font, in addition with a
 * {@link JButton} that opens a font-chooser dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public interface FontChooserFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	FieldHandler<?> create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, Field field);
}
