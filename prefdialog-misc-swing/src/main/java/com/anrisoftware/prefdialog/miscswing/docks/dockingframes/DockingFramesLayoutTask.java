package com.anrisoftware.prefdialog.miscswing.docks.dockingframes;

import java.io.Serializable;
import java.util.Map;

import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CWorkingArea;

import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask;
import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;

/**
 * Apply a layout for the Docking Frames.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface DockingFramesLayoutTask extends LayoutTask, Serializable {

	/**
	 * Sets the layout.
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

	void addEditor(CWorkingArea workingArea, EditorDockWindow dock);

	void addView(CControl control, ViewDockWindow dock);
}
