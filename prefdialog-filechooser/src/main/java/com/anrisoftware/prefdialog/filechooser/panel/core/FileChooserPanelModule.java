package com.anrisoftware.prefdialog.filechooser.panel.core;

import com.anrisoftware.prefdialog.filechooser.panel.api.DirectoyModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelFactory;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelProperties;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileNameEditor;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileNameRenderer;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileSelectionModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.LocationsModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.PlacesModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.PlacesRenderer;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolButtonsModel;
import com.anrisoftware.prefdialog.filechooser.panel.core.docking.DockingModule;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.DefaultFileChooserPanelProperties;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.DefaultFileNameEditor;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.DefaultFileNameRenderer;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.DefaultFileSelectionModel;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.DefaultToolButtonsModel;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.filemodel.DefaultFileModel;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.locations.DefaultLocationsModel;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.places.DefaultPlacesModel;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.places.DefaultPlacesRenderer;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class FileChooserPanelModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new DockingModule());
		install(new FactoryModuleBuilder().implement(FileChooserPanel.class,
				FileChooserPanelImpl.class)
				.build(FileChooserPanelFactory.class));
		bind(FileChooserPanelProperties.class).to(
				DefaultFileChooserPanelProperties.class);
		bind(FileModel.class).to(DefaultFileModel.class);
		bind(FileSelectionModel.class).to(DefaultFileSelectionModel.class);
		bind(ToolButtonsModel.class).to(DefaultToolButtonsModel.class);
		bind(DirectoyModel.class).to(DirectoyStackImpl.class);
		bind(FileNameRenderer.class).to(DefaultFileNameRenderer.class);
		bind(FileNameEditor.class).to(DefaultFileNameEditor.class);
		bind(PlacesModel.class).to(DefaultPlacesModel.class);
		bind(PlacesRenderer.class).to(DefaultPlacesRenderer.class);
		bind(LocationsModel.class).to(DefaultLocationsModel.class);
	}

}
