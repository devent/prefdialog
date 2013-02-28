package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.anrisoftware.prefdialog.annotations.TextPosition;

public interface FileChooserPanelProperties {

	static final String FILE_VIEW_PROPERTY = "file_view";

	static final String SELECTED_FILES_PROPERTY = "selected_files";

	static final String MAX_SELECTED_FILES_IN_QUEUE_PROPERTY = "max_selected_files_in_queue";

	static final String SELECTED_FILES_IN_QUEUE_PROPERTY = "selected_files_in_queue";

	static final String FILE_SORT_PROPERTY = "file_sort";

	static final String PLACES_PROPERTY = "places";

	static final String TEXT_POSITION_PROPERTY = "text_position";

	void setView(FileView view);

	FileView getView();

	FileSort getFileSort();

	void setFileSort(FileSort sort);

	void setDescendingSort(boolean descending);

	boolean isDescendingSort();

	void setFolderFirstSort(boolean folderFirst);

	boolean isFolderFirstSort();

	void setSelectedFiles(Set<File> selectedFiles);

	Set<File> getSelectedFiles();

	void addSelectedFiles(Collection<File> files);

	void clearSelectedFiles();

	void setMaxSelectedFilesInQueue(int max);

	int getMaxSelectedFilesInQueue();

	void addSelectedFilesToQueue(Set<File> selectedFiles);

	List<Set<File>> getSelectedFilesInQueue();

	void setTextPosition(TextPosition position);

	TextPosition getTextPosition();

	/**
	 * Add a PropertyChangeListener to the listener list. The listener is
	 * registered for all properties.
	 * 
	 * @param l
	 *            the PropertyChangeListener to be added.
	 * 
	 * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
	 * @see #FILE_VIEW_PROPERTY
	 * @see #SELECTED_FILES_PROPERTY
	 * @see #MAX_SELECTED_FILES_IN_QUEUE_PROPERTY
	 * @see #SELECTED_FILES_IN_QUEUE_PROPERTY
	 * @see #FILE_SORT_PROPERTY
	 * @see #PLACES_PROPERTY
	 * @see #TEXT_POSITION_PROPERTY
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
	 * @see #FILE_VIEW_PROPERTY
	 * @see #SELECTED_FILES_PROPERTY
	 * @see #MAX_SELECTED_FILES_IN_QUEUE_PROPERTY
	 * @see #SELECTED_FILES_IN_QUEUE_PROPERTY
	 * @see #FILE_SORT_PROPERTY
	 * @see #PLACES_PROPERTY
	 * @see #TEXT_POSITION_PROPERTY
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
	 * @see #FILE_VIEW_PROPERTY
	 * @see #SELECTED_FILES_PROPERTY
	 * @see #MAX_SELECTED_FILES_IN_QUEUE_PROPERTY
	 * @see #SELECTED_FILES_IN_QUEUE_PROPERTY
	 * @see #FILE_SORT_PROPERTY
	 * @see #PLACES_PROPERTY
	 * @see #TEXT_POSITION_PROPERTY
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
	 * @see #FILE_VIEW_PROPERTY
	 * @see #SELECTED_FILES_PROPERTY
	 * @see #MAX_SELECTED_FILES_IN_QUEUE_PROPERTY
	 * @see #SELECTED_FILES_IN_QUEUE_PROPERTY
	 * @see #FILE_SORT_PROPERTY
	 * @see #PLACES_PROPERTY
	 * @see #TEXT_POSITION_PROPERTY
	 */
	void removePropertyChangeListener(String name, PropertyChangeListener l);

}