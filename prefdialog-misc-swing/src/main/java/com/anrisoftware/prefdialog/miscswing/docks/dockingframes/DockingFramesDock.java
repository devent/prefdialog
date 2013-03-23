package com.anrisoftware.prefdialog.miscswing.docks.dockingframes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;
import javax.swing.JFrame;

import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CWorkingArea;
import bibliothek.gui.dock.common.theme.ThemeMap;

import com.anrisoftware.prefdialog.miscswing.docks.api.Dock;
import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.PerspectiveTask;
import com.anrisoftware.prefdialog.miscswing.docks.api.ToolDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;

public class DockingFramesDock implements Dock {

	private static final String WORK_AREA_ID = "work";

	private final Map<String, ToolDockWindow> toolDocks;

	private final Map<String, ViewDockWindow> viewDocks;

	private final Map<String, EditorDockWindow> editorDocks;

	private CControl control;

	private CWorkingArea workingArea;

	@Inject
	DockingFramesDock() {
		this.viewDocks = new ConcurrentHashMap<String, ViewDockWindow>();
		this.editorDocks = new ConcurrentHashMap<String, EditorDockWindow>();
		this.toolDocks = new ConcurrentHashMap<String, ToolDockWindow>();
	}

	@Override
	public Dock withFrame(JFrame frame) {
		control = new CControl(frame);
		frame.add(getComponent(), BorderLayout.CENTER);
		workingArea = control.createWorkingArea(WORK_AREA_ID);
		return this;
	}

	@Override
	public Component getComponent() {
		return control.getContentArea();
	}

	@Override
	public void addViewDock(ViewDockWindow dock) {
		viewDocks.put(dock.getId(), dock);
	}

	@Override
	public void addEditorDock(EditorDockWindow dock) {
		editorDocks.put(dock.getId(), dock);
	}

	@Override
	public void addToolDock(ToolDockWindow dock) {
		toolDocks.put(dock.getId(), dock);
	}

	/**
	 * @throws ClassCastException
	 *             if the specified task is not of type
	 *             {@link DockingFramesPerspectiveTask}.
	 */
	@Override
	public void applyPerspective(PerspectiveTask task) {
		((DockingFramesPerspectiveTask) task).setupPerspective(control,
				workingArea, viewDocks, editorDocks);
	}

	@Override
	public void savePerspective(String name, File file) throws IOException {
		control.save(name);
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
