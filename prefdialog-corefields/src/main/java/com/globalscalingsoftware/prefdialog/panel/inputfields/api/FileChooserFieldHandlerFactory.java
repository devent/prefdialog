package com.globalscalingsoftware.prefdialog.panel.inputfields.api;

import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.FieldHandlerFactory;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new file chooser {@link FieldHandler}. It uses a
 * {@link JTextField} to enter and display the directory and the file name, in
 * addition with a {@link JButton} that openes a {@link JFileChooser}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public interface FileChooserFieldHandlerFactory extends FieldHandlerFactory {

	@Override
	FieldHandler<?> create(@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field);
}
