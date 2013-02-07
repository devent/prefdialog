package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;

@SuppressWarnings("rawtypes")
public interface FileModel extends ListModel {

	static final String FILE_HIDDING_PROPERTY = "file_hidding";

	static final String DIRECTORY_PROPERTY = "directory";

	static final String FILE_SORT = "file_sort";

	void addChoosableFileFilter(FileFilter filter);

	boolean removeChoosableFileFilter(FileFilter filter);

	List<FileFilter> getChoosableFileFilters();

	FileFilter getAcceptAllFileFilter();

	void setFileSystemView(FileSystemView view);

	FileSystemView getFileSystemView();

	void setFileView(FileView view);

	FileView getFileView();

	void setFileHidingEnabled(boolean enabled);

	boolean isFileHidingEnabled();

	void setDirectory(File directory);

	File getDirectory();

	void setFileSort(FileSort sort);

	FileSort getFileSort();

	void setDescendingSort(boolean descending);

	boolean isDescendingSort();

	void setFolderFirstSort(boolean folderFirst);

	boolean isFolderFirstSort();

	/**
	 * Add a PropertyChangeListener to the listener list. The listener is
	 * registered for all properties.
	 * 
	 * @param l
	 *            the PropertyChangeListener to be added.
	 * 
	 * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
	 * @see #FILE_HIDDING_PROPERTY
	 * @see #DIRECTORY_PROPERTY
	 * @see #FILE_SORT
	 */
	void addPropertyChangeListener(PropertyChangeListener l);

	/**
	 * Add a PropertyChangeListener for a specific property. The listener will
	 * be invoked only when a call on firePropertyChange names that specific
	 * property.
	 * 
	 * @param name
	 *            The name of the property to listen on.
	 * @param l
	 *            The PropertyChangeListener to be added
	 * 
	 * @see PropertyChangeSupport#addPropertyChangeListener(String,
	 *      PropertyChangeListener)
	 * @see #FILE_HIDDING_PROPERTY
	 * @see #DIRECTORY_PROPERTY
	 * @see #FILE_SORT
	 */
	void addPropertyChangeListener(String name, PropertyChangeListener l);

	/**
	 * Remove a PropertyChangeListener from the listener list. This removes a
	 * PropertyChangeListener that was registered for all properties.
	 * 
	 * @param l
	 *            the PropertyChangeListener to be removed
	 * 
	 * @see PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
	 * @see #FILE_HIDDING_PROPERTY
	 * @see #DIRECTORY_PROPERTY
	 * @see #FILE_SORT
	 */
	void removePropertyChangeListener(PropertyChangeListener l);

	/**
	 * Remove a PropertyChangeListener for a specific property.
	 * 
	 * @param name
	 *            the name of the property that was listened on.
	 * 
	 * @param l
	 *            the PropertyChangeListener to be removed.
	 * 
	 * @see PropertyChangeSupport#removePropertyChangeListener(String,
	 *      PropertyChangeListener)
	 * @see #FILE_HIDDING_PROPERTY
	 * @see #DIRECTORY_PROPERTY
	 * @see #FILE_SORT
	 */
	void removePropertyChangeListener(String name, PropertyChangeListener l);
}
