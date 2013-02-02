package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.io.File;

/**
 * Navigate in the directory structure of the file chooser dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface DirectoyModel {

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
	 * Tests that it is possible to go back to the last current directory.
	 * 
	 * @return {@code true} if there is a last current directory or
	 *         {@code false} if not.
	 */
	boolean canGoBack();

	/**
	 * Go back to the last current directory.
	 * 
	 * @return the last current {@link File} directory.
	 */
	File goBack();

	/**
	 * Tests that it is possible to go back to the first current directory.
	 * 
	 * @return {@code true} if there is a first current directory or
	 *         {@code false} if not.
	 */
	boolean canGoForward();

	/**
	 * Go back to the first current directory.
	 * 
	 * @return the first current {@link File} directory.
	 */
	File goForward();

	/**
	 * Tests that it is possible to go to the parent directory of the current
	 * directory.
	 * 
	 * @return {@code true} if there is a parent directory of the current
	 *         directory or {@code false} if not.
	 */
	boolean canGoUp();

	/**
	 * Go to the parent directory of the current directory.
	 * 
	 * @return the parent {@link File} directory of the current directory.
	 */
	File goUp();

}
