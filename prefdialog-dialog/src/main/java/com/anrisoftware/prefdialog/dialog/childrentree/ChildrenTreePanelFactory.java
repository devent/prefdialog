package com.anrisoftware.prefdialog.dialog.childrentree;

import javax.swing.JPanel;
import javax.swing.JTree;

import com.anrisoftware.prefdialog.ChildrenPanel;
import com.anrisoftware.prefdialog.ChildrenPanelFactory;

/**
 * Factory to create a new {@link ChildrenPanel} from a given {@link JPanel},
 * that has a list of available children in a {@link JTree} at the left and at
 * the right the currently selected child {@link JPanel}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public interface ChildrenTreePanelFactory extends ChildrenPanelFactory {

	/**
	 * Creates a new {@link ChildrenPanel} from the given panel.
	 * 
	 * @param panel
	 *            the {@link JPanel} that will contains the components of the
	 *            children panel.
	 */
	@Override
	ChildrenPanel create(JPanel panel);
}
