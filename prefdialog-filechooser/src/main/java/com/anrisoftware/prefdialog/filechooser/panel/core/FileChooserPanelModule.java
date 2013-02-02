package com.anrisoftware.prefdialog.filechooser.panel.core;

import com.anrisoftware.prefdialog.filechooser.panel.api.DirectoyModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelFactory;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileSelectionModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolButtonsModel;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.DefaultFileModel;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.DefaultFileSelectionModel;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.DefaultToolButtonsModel;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class FileChooserPanelModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(FileChooserPanel.class,
				FileChooserPanelImpl.class)
				.build(FileChooserPanelFactory.class));
		bind(FileModel.class).to(DefaultFileModel.class);
		bind(FileSelectionModel.class).to(DefaultFileSelectionModel.class);
		bind(ToolButtonsModel.class).to(DefaultToolButtonsModel.class);
		bind(DirectoyModel.class).to(DirectoyStackImpl.class);
	}

}
