package com.anrisoftware.prefdialog.filechooser.panel.defaults.locations;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;

import com.anrisoftware.prefdialog.filechooser.panel.api.LocationsModel;

/**
 * Manage the locations paths. The paths are unique.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class DefaultLocationsModel extends DefaultComboBoxModel<File> implements
		LocationsModel {

	private final Set<File> uniqueLocations;

	private int max;

	public DefaultLocationsModel() {
		this.uniqueLocations = new HashSet<File>();
		this.max = 10;
	}

	@Override
	public void addElement(File anObject) {
		addLocation(anObject);
	}

	@Override
	public void insertElementAt(File anObject, int index) {
		addLocation(anObject);
	}

	@Override
	public void addLocation(File path) {
		if (uniqueLocations.add(path)) {
			super.insertElementAt(path, 0);
		}
		if (getSize() == max) {
			removeLast();
		}
	}

	@Override
	public void setMaximumLocations(int max) {
		int oldValue = this.max;
		this.max = max;
		if (oldValue < max) {
			for (int i = 0; i < max - oldValue; i++) {
				removeLast();
			}
		}
	}

	private void removeLast() {
		removeElementAt(getSize() - 1);
	}

	@Override
	public void removeElementAt(int index) {
		File path = getElementAt(index);
		uniqueLocations.remove(path);
		super.removeElementAt(index);
	}

	@Override
	public void removeAllElements() {
		uniqueLocations.clear();
		super.removeAllElements();
	}
}
