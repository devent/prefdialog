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
import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.PerspectiveTask;
import com.anrisoftware.prefdialog.miscswing.docks.api.ToolDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.editordockable.EditorDockableFactory;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.editordockable.EditorDockableFactoryFactory;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.editordockable.EditorDockableLayoutFactory;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.viewdockable.ViewDockableFactory;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.viewdockable.ViewDockableFactoryFactory;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.viewdockable.ViewDockableLayoutFactory;

public class DockingFramesDock implements Dock {

	private static final String WORK_AREA_ID = "work";

	private CControl control;

	private final Map<String, DockablePerspective> viewDockablePerspectives;

	private final Map<String, DockablePerspective> editorDockablePerspectives;

	private final ViewDockableFactoryFactory viewDockableFactoryFactory;

	private final ViewDockableLayoutFactory viewDockableLayoutFactory;

	private final EditorDockableFactoryFactory editorDockableFactoryFactory;

	private final EditorDockableLayoutFactory editorDockableLayoutFactory;

	private CPerspective perspective;

	private CControlPerspective perspectives;

	@Inject
	DockingFramesDock(ViewDockableFactoryFactory viewDockableFactoryFactory,
			ViewDockableLayoutFactory viewDockableLayoutFactory,
			EditorDockableFactoryFactory editorDockableFactoryFactory,
			EditorDockableLayoutFactory editorDockableLayoutFactory) {
		this.viewDockableFactoryFactory = viewDockableFactoryFactory;
		this.viewDockableLayoutFactory = viewDockableLayoutFactory;
		this.editorDockableFactoryFactory = editorDockableFactoryFactory;
		this.editorDockableLayoutFactory = editorDockableLayoutFactory;
		this.viewDockablePerspectives = new ConcurrentHashMap<String, DockablePerspective>();
		this.editorDockablePerspectives = new ConcurrentHashMap<String, DockablePerspective>();
	}

	@Override
	public Dock withFrame(JFrame frame) {
		control = new CControl(frame);
		control.addMultipleDockableFactory(ViewDockableFactory.ID,
				viewDockableFactoryFactory.create());
		control.addMultipleDockableFactory(EditorDockableFactory.ID,
				editorDockableFactoryFactory.create());
		frame.add(getComponent(), BorderLayout.CENTER);
		control.createWorkingArea(WORK_AREA_ID);
		perspectives = control.getPerspectives();
		perspective = perspectives.createEmptyPerspective();
		CGridPerspective center = perspective.getContentArea().getCenter();
		CWorkingPerspective work = (CWorkingPerspective) perspective
				.getStation(WORK_AREA_ID);
		center.gridAdd(0, 50, 200, 200, work);
		return this;
	}

	@Override
	public Component getComponent() {
		return control.getContentArea();
	}

	@Override
	public void addViewDock(ViewDockWindow dock) {
		viewDockablePerspectives.put(dock.getId(),
				new DockablePerspective(dock, new MultipleCDockablePerspective(
						ViewDockableFactory.ID, dock.getId(),
						viewDockableLayoutFactory.createFor(dock))));
	}

	@Override
	public void addEditorDock(EditorDockWindow dock) {
		editorDockablePerspectives.put(dock.getId(), new DockablePerspective(
				dock, new MultipleCDockablePerspective(
						EditorDockableFactory.ID, dock.getId(),
						editorDockableLayoutFactory.createFor(dock))));
	}

	@Override
	public void addToolDock(ToolDockWindow dock) {
		viewDockablePerspectives.put(dock.getId(), new DockablePerspective(
				dock, new SingleCDockablePerspective(dock.getId())));
	}

	/**
	 * @throws ClassCastException
	 *             if the specified task is not of type
	 *             {@link DockingFramesPerspectiveTask}.
	 */
	@Override
	public void applyPerspective(PerspectiveTask task) {
		((DockingFramesPerspectiveTask) task).setupPerspective(perspectives,
				perspective, viewDockablePerspectives,
				editorDockablePerspectives, WORK_AREA_ID);
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
