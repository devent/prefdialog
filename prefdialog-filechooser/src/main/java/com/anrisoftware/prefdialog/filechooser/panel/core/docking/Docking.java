package com.anrisoftware.prefdialog.filechooser.panel.core.docking;

import java.awt.Container;

import javax.inject.Inject;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import bibliothek.extension.gui.dock.theme.eclipse.EclipseThemeConnector.TitleBar;
import bibliothek.extension.gui.dock.theme.eclipse.displayer.NoTitleBarDisplayer;
import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CGrid;
import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.intern.DefaultCommonDockable;
import bibliothek.gui.dock.displayer.DisplayerRequest;
import bibliothek.gui.dock.dockable.IconHandling;
import bibliothek.gui.dock.station.DockableDisplayer;
import bibliothek.gui.dock.themes.border.BorderModifier;
import bibliothek.gui.dock.util.ComponentWindowProvider;

import com.google.inject.assistedinject.Assisted;

public class Docking {

	private final CControl control;

	private final DefaultSingleCDockable mainDock;

	private final Container container;

	private final DefaultSingleCDockable placesDock;

	private final DefaultSingleCDockable inputDock;

	@Inject
	Docking(@Assisted Container container) {
		this.container = container;
		this.control = new CControl(new ComponentWindowProvider(container));
		this.mainDock = createFixedDock("main-dock", "Main Dock");
		this.inputDock = createFixedDock("input-dock", "Input Dock");
		this.placesDock = createPlacesDock();
		setupInputDock();
		setupContainer();
		setupDocks();
	}

	private void setupInputDock() {
		inputDock.setResizeLocked(true);
	}

	private void setupContainer() {
		container.add(control.getContentArea());
		CGrid grid = new CGrid(control);
		grid.add(0, 0, 0.2, 0.8, placesDock);
		grid.add(1, 0, 0.8, 0.9, mainDock);
		grid.add(0, 1, 1.0, 0.1, inputDock);
		control.getContentArea().deploy(grid);
		control.getController()
				.getThemeManager()
				.setBorderModifier("dock.border.displayer.basic.base",
						new BorderModifier() {
							@Override
							public Border modify(Border border) {
								return BorderFactory.createEmptyBorder();
							}
						});
	}

	private void setupDocks() {
		mainDock.setVisible(true);
		placesDock.setVisible(true);
	}

	private DefaultSingleCDockable createFixedDock(String id, String title) {
		DefaultSingleCDockable dockable = new DefaultSingleCDockable(id, title) {
			@Override
			protected DefaultCommonDockable createCommonDockable() {
				return new DefaultCommonDockable(this, getClose()) {
					@Override
					public void requestDisplayer(DisplayerRequest request) {
						DockableDisplayer displayer = new NoTitleBarDisplayer(
								request.getParent(), request.getTarget(),
								request.getTitle(), TitleBar.NONE);
						request.answer(displayer);
					}
				};

			}

		};
		dockable.setTitleText(title);
		dockable.setTitleShown(false);
		dockable.setCloseable(false);
		dockable.setExternalizable(false);
		dockable.setMinimizable(false);
		dockable.setMaximizable(false);
		dockable.setStackable(false);
		dockable.setSingleTabShown(false);
		return dockable;
	}

	private DefaultSingleCDockable createPlacesDock() {
		String id = "palces-dock";
		String title = "Places:";
		DefaultSingleCDockable dockable = new DefaultSingleCDockable(id, title);
		dockable.setTitleText(title);
		dockable.setTitleShown(true);
		dockable.setCloseable(true);
		dockable.setExternalizable(true);
		dockable.setMinimizable(false);
		dockable.setMaximizable(false);
		dockable.setStackable(false);
		dockable.setSingleTabShown(false);
		dockable.setTitleIconHandling(IconHandling.KEEP_NULL_ICON);
		dockable.setTitleIcon(null);
		return dockable;
	}

	public void setMainDockPanel(JPanel panel) {
		mainDock.add(panel);
	}

	public void setPlacesDockPanel(JPanel panel) {
		placesDock.add(panel);
	}

	public void setInputDockPanel(JPanel panel) {
		inputDock.add(panel);
	}
}
