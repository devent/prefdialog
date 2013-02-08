package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.io.File;

import javax.swing.ListModel;

public interface PlacesModel extends ListModel<File> {

	File getPlaceAt(int index);

	/**
	 * Returns the index of the specified place in the model.
	 * 
	 * @param file
	 *            the place {@link File}.
	 * 
	 * @return the index or {@code -1} if no such place was found.
	 */
	int indexOf(File file);
}
