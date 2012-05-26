package com.anrisoftware.prefdialog;

import javax.swing.JPanel;

/**
 * Factory to create a new {@link ChildrenPanel} from a given {@link JPanel}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public interface ChildrenPanelFactory {

	/**
	 * Creates a new {@link ChildrenPanel} from the given panel.
	 * 
	 * @param panel
	 *            the {@link JPanel} that will contains the components of the
	 *            children panel.
	 */
	ChildrenPanel create(JPanel panel);
}
