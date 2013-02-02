package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.io.File;

/**
 * Navigate in the directory structor of the file chooser dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface DirectoyStack {

	/**
	 * Sets the current directory.
	 * 
	 * @param currentDirectory
	 *            the current {@link File} directory.
	 */
	void setCurrentDirectory(File currentDirectory);

	/**
	 * Returns the current directory.
	 * 
	 * @return the current {@link File} directory.
	 */
	File getCurrentDirectory();

	/**
	 * Go back to the last current directory.
	 * 
	 * @return the last current {@link File} directory.
	 */
	File goBack();

	/**
	 * Go back to the first current directory.
	 * 
	 * @return the first current {@link File} directory.
	 */
	File goForward();

	/**
	 * Go to the parent directory of the current directory.
	 * 
	 * @return the parent {@link File} directory of the current directory.
	 */
	File goUp();

}
