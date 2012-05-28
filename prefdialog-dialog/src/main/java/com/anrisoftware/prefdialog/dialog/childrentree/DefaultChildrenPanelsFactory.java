package com.anrisoftware.prefdialog.dialog.childrentree;

import javax.swing.JPanel;

/**
 * Factory to create a new {@link PreferencePanelsWorker}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public interface DefaultChildrenPanelsFactory {

	/**
	 * Creates a new {@link PreferencePanelsWorker}.
	 * 
	 * @param panel
	 *            the {@link JPanel} that is the container of the preference
	 *            panel.
	 */
	DefaultChildrenPanels create(Object preferences);
}
