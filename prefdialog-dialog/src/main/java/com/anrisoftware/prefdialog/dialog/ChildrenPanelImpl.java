package com.anrisoftware.prefdialog.dialog;

import static java.awt.BorderLayout.CENTER;
import static java.lang.String.format;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;

import java.awt.BorderLayout;

import javax.inject.Inject;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.anrisoftware.prefdialog.ChildrenListPanel;
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

	private final ChildrenListPanel childrenListPanel;

	private final JSplitPane panelSplit;

	@Inject
	ChildrenPanelImpl(@Assisted JPanel panel,
			@Assisted ChildrenListPanel childrenListPanel) {
		this.panel = panel;
		this.childrenListPanel = childrenListPanel;
		this.panelSplit = new JSplitPane(HORIZONTAL_SPLIT);
		setupPanel();
		setupPanelSplit();
	}

	private void setupPanel() {
		panel.setLayout(new BorderLayout());
		panel.add(panelSplit, CENTER);
	}

	private void setupPanelSplit() {
		panelSplit.setLeftComponent(childrenListPanel.getPanel());
		panelSplit.setRightComponent(new JPanel());
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void setName(String name) {
		childrenListPanel.setName(name);
		panel.setName(format("%s-%s", name, PANEL));
	}

	@Override
	public void setChildPanel(JPanel panel) {
		panelSplit.setRightComponent(panel);
	}
}
