package com.anrisoftware.prefdialog.dialog.childrentab;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.anrisoftware.prefdialog.ChildrenPanel;
import com.anrisoftware.prefdialog.ChildrenPanelFactory;

/**
 * Factory to create a new {@link ChildrenPanel} from a given {@link JPanel},
 * that has a list of available children in a {@link JTabbedPane} at the top and
 * inside the currently selected child {@link JPanel}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public interface ChildrenTabPanelFactory extends ChildrenPanelFactory {

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
