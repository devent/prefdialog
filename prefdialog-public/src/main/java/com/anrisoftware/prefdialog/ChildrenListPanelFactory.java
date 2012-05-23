package com.anrisoftware.prefdialog;

import javax.swing.JPanel;

/**
 * Factory to create a new {@link ChildrenListPanel} from a {@link JPanel}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public interface ChildrenListPanelFactory {

	/**
	 * Creates a new {@link ChildrenListPanel} from the given panel.
	 * 
	 * @param panel
	 *            the {@link JPanel} that will contains the components of the
	 *            children list panel.
	 */
	ChildrenListPanel create(JPanel panel);
}
