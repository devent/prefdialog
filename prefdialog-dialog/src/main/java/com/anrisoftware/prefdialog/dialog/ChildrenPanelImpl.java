package com.anrisoftware.prefdialog.dialog;

import static java.awt.BorderLayout.CENTER;
import static javax.swing.JSplitPane.VERTICAL_SPLIT;

import java.awt.BorderLayout;

import javax.inject.Inject;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.anrisoftware.prefdialog.ChildrenPanel;
import com.google.inject.assistedinject.Assisted;

/**
 * The panel that contains a list of the child panels to the left and the
 * current preferences input fields of the selected child on the right.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ChildrenPanelImpl implements ChildrenPanel {

	private final JPanel panel;

	private final JSplitPane panelSplit;

	@Inject
	ChildrenPanelImpl(@Assisted JPanel panel) {
		this.panel = panel;
		this.panelSplit = new JSplitPane(VERTICAL_SPLIT);
		setupPanel();
	}

	private void setupPanel() {
		panel.setLayout(new BorderLayout());
		panel.add(panelSplit, CENTER);
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}
}
