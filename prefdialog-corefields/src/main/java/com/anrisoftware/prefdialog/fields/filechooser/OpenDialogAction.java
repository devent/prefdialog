package com.anrisoftware.prefdialog.fields.filechooser;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import com.anrisoftware.prefdialog.annotations.FileChooserModel;

/**
 * Opens the file chooser dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class OpenDialogAction implements ActionListener {

	private FileChooserModel model;

	/**
	 * Sets the model that will open the file chooser dialog.
	 * 
	 * @param model
	 *            the {@link FileChooserModel}.
	 */
	public void setFileChooserModel(FileChooserModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			model.openDialog((Component) e.getSource());
		} catch (PropertyVetoException e1) {
		}
	}

}
