package com.anrisoftware.prefdialog.filechooser.panel.defaults.places;

import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.filechooser.FileSystemView;

import com.anrisoftware.prefdialog.filechooser.panel.api.PlacesModel;

/**
 * Adds the home directory and any file system roots to the places.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class DefaultPlacesModel extends DefaultListModel<File> implements
		PlacesModel {

	public DefaultPlacesModel() {
		this(FileSystemView.getFileSystemView());
	}

	public DefaultPlacesModel(FileSystemView systemView) {
		addElement(systemView.getHomeDirectory());
		for (File file : File.listRoots()) {
			addElement(file);
		}
	}

	@Override
	public File getPlaceAt(int index) {
		return getElementAt(index);
	}

	@Override
	public int indexOf(File file) {
		return super.indexOf(file);
	}
}
