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
	 * The name postfix used for the children panel.
	 */
	static final String PANEL = "children-panel";

	/**
	 * Returns the {@link JPanel} that contains to the list of the children and
	 * the current selected child panel.
	 */
	JPanel getPanel();

	/**
	 * <p>
	 * Sets the name of this panel.
	 * </p>
	 * <p>
	 * Sets the name of each of the components in this panel prefixed with the
	 * given name.
	 * </p>
	 * 
	 * @param name
	 *            the given name.
	 */
	void setName(String name);

	/**
	 * Sets the visible child panel.
	 * 
	 * @param panel
	 *            the {@link JPanel}.
	 */
	void setChildPanel(JPanel panel);

}
