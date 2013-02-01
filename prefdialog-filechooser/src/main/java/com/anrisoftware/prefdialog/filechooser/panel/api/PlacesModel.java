package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.io.File;

import javax.swing.ListModel;

@SuppressWarnings("rawtypes")
public interface PlacesModel extends ListModel {

	File getPlaceAt(int index);
}
