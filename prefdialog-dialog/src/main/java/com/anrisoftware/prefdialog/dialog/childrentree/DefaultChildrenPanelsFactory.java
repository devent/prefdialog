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
	 * 
	 * @param preferences
	 *            the preferences {@link Object} from which the handler will
	 *            create all preference panels.
	 */
	DefaultChildrenPanels create(JPanel panel, Object preferences);
}
