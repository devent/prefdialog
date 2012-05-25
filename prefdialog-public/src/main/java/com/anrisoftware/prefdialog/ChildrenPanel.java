package com.anrisoftware.prefdialog;

import javax.swing.JPanel;

/**
 * The panel that contains a list of the child panels to the left and the
 * current preferences input fields of the selected child on the right.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public interface ChildrenPanel {

	/**
	 * Returns the {@link JPanel} that contains to the list of the children and
	 * the current selected child panel.
	 */
	JPanel getPanel();

}
