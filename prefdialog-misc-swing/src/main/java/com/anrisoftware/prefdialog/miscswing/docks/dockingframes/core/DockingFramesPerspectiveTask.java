package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core;

import java.util.Map;

import bibliothek.gui.dock.common.perspective.CControlPerspective;
import bibliothek.gui.dock.common.perspective.CPerspective;

import com.anrisoftware.prefdialog.miscswing.docks.api.PerspectiveTask;

/**
 * Apply a perspective for the Docking Frames.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface DockingFramesPerspectiveTask extends PerspectiveTask {

	/**
	 * Sets the perspective.
	 * 
	 * @param perspectives
	 *            the {@link CControlPerspective}.
	 * 
	 * @param perspective
	 *            the {@link CPerspective}.
	 * 
	 * @param singleDockablePerspectives
	 *            the {@link Map} of the {@link DockablePerspective} for the
	 *            windows outside of the work area.
	 * 
	 * @param workDockablePerspectives
	 *            the {@link Map} of the {@link DockablePerspective} for the
	 *            windows in the work area.
	 * 
	 * @param workAreaId
	 *            the identifier of the work area.
	 */
	public void setupPerspective(CControlPerspective perspectives,
			CPerspective perspective,
			Map<String, DockablePerspective> singleDockablePerspectives,
			Map<String, DockablePerspective> workDockablePerspectives,
			String workAreaId);
}
