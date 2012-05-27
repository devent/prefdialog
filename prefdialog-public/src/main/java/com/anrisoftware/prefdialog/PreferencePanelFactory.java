package com.anrisoftware.prefdialog;

import javax.swing.JPanel;

/**
 * <p>
 * A factory to create new {@link PreferencePanel}.
 * </p>
 * 
 * @see PreferencePanel
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public interface PreferencePanelFactory {

	/**
	 * Creates a new {@link PreferencePanel preference panel handler}.
	 * 
	 * @param panel
	 *            the {@link JPanel} to which the input fields are added.
	 * 
	 * @param preference
	 *            the preference {@link Object} for which the input fields are
	 *            created.
	 * 
	 * @since 2.2
	 */
	PreferencePanel create(JPanel panel, Object preference);
}
