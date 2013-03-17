package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;
import javax.swing.JFrame;

import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.perspective.CControlPerspective;
import bibliothek.gui.dock.common.perspective.CGridPerspective;
import bibliothek.gui.dock.common.perspective.CPerspective;
import bibliothek.gui.dock.common.perspective.CWorkingPerspective;
import bibliothek.gui.dock.common.perspective.MultipleCDockablePerspective;
import bibliothek.gui.dock.common.perspective.SingleCDockablePerspective;
import bibliothek.gui.dock.common.theme.ThemeMap;

import com.anrisoftware.prefdialog.miscswing.docks.api.Dock;
import com.anrisoftware.prefdialog.miscswing.docks.api.PerspectiveTask;
import com.anrisoftware.prefdialog.miscswing.docks.api.SingleDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.ToolDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.WorkDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.singledockable.SingleDockableFactory;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.singledockable.SingleDockableFactoryFactory;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.singledockable.SingleDockableLayoutFactory;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.workdockable.WorkDockableFactory;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.workdockable.WorkDockableFactoryFactory;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.workdockable.WorkDockableLayoutFactory;

public class DockingFramesDock implements Dock {

	private static final String WORK_AREA_ID = "work";

	private CControl control;

	private final Map<String, DockablePerspective> singleDockablePerspectives;

	private final Map<String, DockablePerspective> workDockablePerspectives;

	private final SingleDockableFactoryFactory singleDockableFactoryFactory;

	private final SingleDockableLayoutFactory singleDockableLayoutFactory;

	private final WorkDockableFactoryFactory workDockableFactoryFactory;

	private final WorkDockableLayoutFactory workDockableLayoutFactory;

	private CPerspective perspective;

	private CControlPerspective perspectives;

	@Inject
	DockingFramesDock(
			SingleDockableFactoryFactory singleDockableFactoryFactory,
			SingleDockableLayoutFactory singleDockableLayoutFactory,
			WorkDockableFactoryFactory workDockableFactoryFactory,
			WorkDockableLayoutFactory workDockableLayoutFactory) {
		this.singleDockableFactoryFactory = singleDockableFactoryFactory;
		this.singleDockableLayoutFactory = singleDockableLayoutFactory;
		this.workDockableFactoryFactory = workDockableFactoryFactory;
		this.workDockableLayoutFactory = workDockableLayoutFactory;
		this.singleDockablePerspectives = new ConcurrentHashMap<String, DockablePerspective>();
		this.workDockablePerspectives = new ConcurrentHashMap<String, DockablePerspective>();
	}

	@Override
	public Dock withFrame(JFrame frame) {
		control = new CControl(frame);
		control.addMultipleDockableFactory(SingleDockableFactory.ID,
				singleDockableFactoryFactory.create());
		control.addMultipleDockableFactory(WorkDockableFactory.ID,
				workDockableFactoryFactory.create());
		frame.add(getComponent(), BorderLayout.CENTER);
		control.createWorkingArea(WORK_AREA_ID);
		perspectives = control.getPerspectives();
		perspective = perspectives.createEmptyPerspective();
		CGridPerspective center = perspective.getContentArea().getCenter();
		CWorkingPerspective work = (CWorkingPerspective) perspective
				.getStation(WORK_AREA_ID);
		center.gridAdd(0, 0, 200, 200, work);
		return this;
	}

	@Override
	public Component getComponent() {
		return control.getContentArea();
	}

	@Override
	public void addSingleDock(SingleDockWindow window) {
		singleDockablePerspectives.put(window.getId(), new DockablePerspective(
				window, new MultipleCDockablePerspective(
						SingleDockableFactory.ID, window.getId(),
						singleDockableLayoutFactory.createFor(window))));
	}

	@Override
	public void addWorkDock(WorkDockWindow window) {
		workDockablePerspectives.put(window.getId(), new DockablePerspective(
				window, new MultipleCDockablePerspective(
						WorkDockableFactory.ID, window.getId(),
						workDockableLayoutFactory.createFor(window))));
	}

	@Override
	public void addToolDock(ToolDockWindow window) {
		singleDockablePerspectives.put(window.getId(), new DockablePerspective(
				window, new SingleCDockablePerspective(window.getId())));
	}

	/**
	 * @throws ClassCastException
	 *             if the specified task is not of type
	 *             {@link DockingFramesPerspectiveTask}.
	 */
	@Override
	public void applyPerspective(PerspectiveTask task) {
		((DockingFramesPerspectiveTask) task).setupPerspective(perspectives,
				perspective, singleDockablePerspectives,
				workDockablePerspectives, WORK_AREA_ID);
		perspective.shrink();
		perspectives.setPerspective(perspective, true);
		perspectives.setPerspective(task.getName(), perspective);
	}

	@Override
	public void savePerspective(String name, File file) throws IOException {
		perspectives.setPerspective(name, perspective);
		control.write(file);
	}

	@Override
	public void loadPerspective(String name, File file) throws IOException {
		control.read(file);
		control.load(name);
	}

	@Override
	public void setTheme(String name) {
		ThemeMap themes = control.getThemes();
		themes.select(name);
	}
}
