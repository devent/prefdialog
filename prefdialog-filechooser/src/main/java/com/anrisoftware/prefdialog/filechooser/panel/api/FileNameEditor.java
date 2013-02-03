package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.io.File;

import javax.swing.ComboBoxEditor;

public interface FileNameEditor extends ComboBoxEditor {

	void setEditorDelegate(ComboBoxEditor editor);

	/**
	 * Sets the current directory from the file chooser panel. Needed so the
	 * editor can return valid files.
	 * 
	 * @param file
	 *            the current {@link File} directory.
	 */
	void setCurrentDirectory(File file);

}
