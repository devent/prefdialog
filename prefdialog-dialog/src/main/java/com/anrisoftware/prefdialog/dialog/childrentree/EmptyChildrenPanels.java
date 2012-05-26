package com.anrisoftware.prefdialog.dialog.childrentree;

import static java.awt.BorderLayout.CENTER;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.ChildrenPanel;
import com.anrisoftware.prefdialog.ChildrenPanels;

/**
 * Returns a panel with a label that shows the selected child. Is used as a
 * place holder until a real {@link ChildrenPanels} is set.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class EmptyChildrenPanels implements ChildrenPanels {

	@Override
	public JPanel getChildPanel(ChildrenPanel panel, Object child) {
		JPanel childPanel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(child.toString());
		childPanel.add(label, CENTER);
		return childPanel;
	}

}
