package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.io.File;

import javax.swing.ListModel;

public interface PlacesModel extends ListModel<File> {

	File getPlaceAt(int index);
}
