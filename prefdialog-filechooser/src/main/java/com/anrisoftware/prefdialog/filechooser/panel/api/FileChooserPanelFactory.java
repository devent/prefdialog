package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.awt.Container;
import java.io.File;

import javax.swing.filechooser.FileSystemView;

public interface FileChooserPanelFactory {

	FileChooserPanel create(Container container);

	FileChooserPanel create(Container container, String currentDirectory);

	FileChooserPanel create(Container container, String currentDirectory,
			FileSystemView view);

	FileChooserPanel create(Container container, File currentDirectory);

	FileChooserPanel create(Container container, File currentDirectory,
			FileSystemView view);

}
