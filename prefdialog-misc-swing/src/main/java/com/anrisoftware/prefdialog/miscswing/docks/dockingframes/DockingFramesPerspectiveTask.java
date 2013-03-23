package com.anrisoftware.prefdialog.miscswing.docks.dockingframes;

import java.util.Map;

import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CWorkingArea;

import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.PerspectiveTask;
import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;

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
	 * @param control
	 *            the {@link CControl}.
	 * 
	 * @param workingArea
	 *            the {@link CWorkingArea}.
	 * 
	 * @param viewDockablePerspectives
	 *            the {@link Map} of the docks that are outside of the work
	 *            area.
	 * 
	 * @param editorDockablePerspectives
	 *            the {@link Map} of the docks that are in the work area.
	 */
	void setupPerspective(CControl control, CWorkingArea workingArea,
			Map<String, ViewDockWindow> viewDocks,
			Map<String, EditorDockWindow> editorDocks);
}
