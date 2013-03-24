package com.anrisoftware.prefdialog.miscswing.docks.api;

/**
 * Informed of changes in the layout.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface LayoutListener {

	/**
	 * Informed when the layout was saved.
	 * 
	 * @param event
	 *            the {@link LayoutWorkerEvent}.
	 */
	void layoutSaved(LayoutWorkerEvent event);

	/**
	 * Informed when the layout was loaded.
	 * 
	 * @param event
	 *            the {@link LayoutWorkerEvent}.
	 */
	void layoutLoaded(LayoutWorkerEvent event);

	/**
	 * Informed when there was an error.
	 * 
	 * @param event
	 *            the {@link LayoutWorkerErrorEvent}.
	 */
	void layoutError(LayoutWorkerErrorEvent event);

}
