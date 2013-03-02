package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.io.File;

import javax.swing.MutableComboBoxModel;

/**
 * Saves the current and past locations.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface LocationsModel extends MutableComboBoxModel<File> {

	/**
	 * Adds a new path as a new location. If the location count exceeds the
	 * maximum locations count the oldest locations is to be removed.
	 * 
	 * @param path
	 *            the {@link File} path.
	 */
	void addLocation(File path);

	/**
	 * Sets the maximum locations in this model. If the location count exceeds
	 * the maximum locations count the oldest locations is to be removed.
	 * 
	 * @param max
	 *            the maximum count of locations paths.
	 */
	void setMaximumLocations(int max);
}
