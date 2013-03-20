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
	 * @param viewDockablePerspectives
	 *            the {@link Map} of the {@link DockablePerspective} for the
	 *            docks outside of the work area.
	 * 
	 * @param editorDockablePerspectives
	 *            the {@link Map} of the {@link DockablePerspective} for the
	 *            docks in the work area.
	 * 
	 * @param workAreaId
	 *            the identifier of the work area.
	 */
	public void setupPerspective(CControlPerspective perspectives,
			CPerspective perspective,
			Map<String, DockablePerspective> viewDockablePerspectives,
			Map<String, DockablePerspective> editorDockablePerspectives,
			String workAreaId);
}
