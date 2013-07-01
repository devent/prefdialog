package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core;

import java.io.Serializable;
import java.util.Map;

import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CWorkingArea;
import bibliothek.gui.dock.common.MultipleCDockable;

import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask;
import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;

/**
 * Apply a layout for the Docking Frames.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface DockingFramesLayoutTask extends LayoutTask, Serializable {

	/**
	 * Sets the layout.
	 * <p>
	 * <h2>AWT Threading</h2>
	 * <p>
	 * Should be done in the AWT thread.
	 * 
	 * @param control
	 *            the {@link CControl}.
	 * 
	 * @param workingArea
	 *            the {@link CWorkingArea}.
	 * 
	 * @param docks
	 *            the {@link Map} of the docks that are outside of the work
	 *            area.
	 */
	void setupLayout(CControl control, CWorkingArea workingArea,
			Map<String, ViewDockWindow> docks);

	/**
	 * Adds a new editor dock window.
	 * <p>
	 * <h2>AWT Threading</h2>
	 * <p>
	 * Should be done in the AWT thread.
	 * 
	 * @param control
	 *            the {@link CControl} where to add the dock.
	 * 
	 * @param dock
	 *            the {@link EditorDockWindow} dock.
	 * 
	 * @return the {@link MultipleCDockable} of the editor dock.
	 */
	MultipleCDockable addEditor(CWorkingArea workingArea, EditorDockWindow dock);

	/**
	 * Adds a new view dock window.
	 * <p>
	 * <h2>AWT Threading</h2>
	 * <p>
	 * Should be done in the AWT thread.
	 * 
	 * @param control
	 *            the {@link CControl} where to add the dock.
	 * 
	 * @param dock
	 *            the {@link ViewDockWindow} dock.
	 */
	void addView(CControl control, ViewDockWindow dock);
}
