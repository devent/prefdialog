package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.awt.Component;

import javax.swing.ListModel;

@SuppressWarnings("rawtypes")
public interface FilePropertiesModel extends ListModel {

	Component getPropertiesComponentAt(int index);
}
