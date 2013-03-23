package com.anrisoftware.prefdialog.miscswing.docks.api;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

/**
 * Layouts the child windows inside and outside the working area.
 * <p>
 * Example:
 * 
 * <pre>
 * dock = dock.withFrame(frame);
 * frame.add(dock.getComponent(), BorderLayout.CENTER);
 * dock.addSingleDock(window);
 * dock.addWorkDock(window);
 * dock.addToolDock(window);
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface Dock {

	/**
	 * Sets the frame to the dock.
	 * 
	 * @param frame
	 *            the {@link JFrame}.
	 * 
	 * @return the {@link Dock}.
	 */
	Dock withFrame(JFrame frame);

	/**
	 * Returns the AWT component of the dock to be added in a container.
	 * 
	 * @return the {@link Component}.
	 */
	Component getComponent();

	/**
	 * Adds a dock in the outside of the working area.
	 * 
	 * @param dock
	 *            the {@link ViewDockWindow}.
	 */
	void addViewDock(ViewDockWindow dock);

	/**
	 * Adds a dock in the working area.
	 * 
	 * @param dock
	 *            the {@link EditorDockWindow}.
	 */
	void addEditorDock(EditorDockWindow dock);

	/**
	 * Apply the perspective.
	 * 
	 * @param task
	 *            the {@link PerspectiveTask} that apply to perspective.
	 */
	void applyPerspective(PerspectiveTask task);

	/**
	 * Saves the current perspective under the specified name.
	 * 
	 * @param name
	 *            the name of the perspective.
	 * 
	 * @param file
	 *            the {@link File} file where to save the perspectives.
	 * 
	 * @throws IOException
	 *             if there was I/O error saving the perspectives.
	 */
	void savePerspective(String name, File file) throws IOException;

	/**
	 * Loads the previously saved perspective with the specified name.
	 * 
	 * @param name
	 *            the name of the perspective.
	 * 
	 * @param file
	 *            the {@link File} file from where to load the perspectives.
	 * 
	 * @throws IOException
	 *             if there was I/O error loading the perspectives.
	 */
	void loadPerspective(String name, File file) throws IOException;

	/**
	 * Sets a theme.
	 * 
	 * @param name
	 *            the theme name.
	 */
	void setTheme(String name);
}
