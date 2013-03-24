package com.anrisoftware.prefdialog.miscswing.docks.api;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFrame;

/**
 * Layouts the child windows inside and outside the working area.
 * <p>
 * Example:
 * 
 * <pre>
 * dock = dock.withFrame(frame);
 * frame.add(dock.getComponent(), BorderLayout.CENTER);
 * dock.applyLayout(defaultLayout);
 * dock.addViewDock(viewDock);
 * dock.addEditorDock(editor);
 * dock.addEditorDock(editor);
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
	 * @param layout
	 *            the {@link LayoutTask} that apply to perspective.
	 */
	void applyLayout(LayoutTask layout);

	/**
	 * Saves the current layout under the specified name.
	 * 
	 * @param name
	 *            the name of the perspective.
	 * 
	 * @param file
	 *            the {@link File} file where to save the layout.
	 * 
	 * @throws IOException
	 *             if there was I/O error saving the layout.
	 */
	void saveLayout(String name, File file) throws IOException;

	/**
	 * Saves the current layout under the specified name.
	 * 
	 * @param name
	 *            the name of the layout.
	 * 
	 * @param stream
	 *            the {@link OutputStream} stream where to save the layout.
	 * 
	 * @throws IOException
	 *             if there was I/O error saving the layout.
	 */
	void saveLayout(String name, OutputStream stream) throws IOException;

	/**
	 * Loads the previously saved layout with the specified name.
	 * 
	 * @param name
	 *            the name of the layout.
	 * 
	 * @param file
	 *            the {@link File} file from where to load the layout.
	 * 
	 * @throws IOException
	 *             if there was I/O error loading the layout.
	 */
	void loadLayout(String name, File file) throws IOException;

	/**
	 * Loads the previously saved layout with the specified name.
	 * 
	 * @param name
	 *            the name of the layout.
	 * 
	 * @param stream
	 *            the {@link InputStream} stream from where to load the layout.
	 * 
	 * @throws IOException
	 *             if there was I/O error loading the layout.
	 */
	void loadLayout(String name, InputStream stream) throws IOException;

	/**
	 * Returns the current active layout.
	 * 
	 * @return the {@link LayoutTask}.
	 */
	LayoutTask getCurrentLayout();

	/**
	 * Sets a theme.
	 * 
	 * @param name
	 *            the theme name.
	 */
	void setTheme(String name);

	/**
	 * Adds a new listener that is informed of layout changes.
	 * 
	 * @param listener
	 *            the {@link LayoutListener}.
	 */
	void addLayoutListener(LayoutListener listener);

	/**
	 * Removes the listener that was informed of layout changes.
	 * 
	 * @param listener
	 *            the {@link LayoutListener}.
	 */
	void removeLayoutListener(LayoutListener listener);

}
