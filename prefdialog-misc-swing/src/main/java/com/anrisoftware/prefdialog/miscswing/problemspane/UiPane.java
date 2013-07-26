package com.anrisoftware.prefdialog.miscswing.problemspane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jdesktop.swingx.JXTreeTable;

@SuppressWarnings("serial")
class UiPane extends JPanel {

	private final JXTreeTable problemsTable;

	/**
	 * Create the panel.
	 */
	public UiPane() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		problemsTable = new JXTreeTable();
		problemsTable.setName("problemsTable");
		scrollPane.setViewportView(problemsTable);

	}

	public JXTreeTable getProblemsTable() {
		return problemsTable;
	}
}
