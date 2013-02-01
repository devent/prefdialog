package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

public interface FileChooserPanelFactory {

	FileChooserPanel create();

	FileChooserPanel create(String currentDirectory);

	FileChooserPanel create(String currentDirectory, FileSystemView view);

	FileChooserPanel create(File currentDirectory);

	FileChooserPanel create(File currentDirectory, FileSystemView view);

}
