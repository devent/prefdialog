package com.anrisoftware.prefdialog.filechooser.panel.core;

import java.io.File;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;

@SuppressWarnings("serial")
class UiInputPanel extends JPanel {

	final JLabel nameLabel;

	final JComboBox<Set<File>> nameField;

	final JLabel filterLabel;

	final JComboBox filterField;

	final JButton approveButton;

	final JButton cancelButton;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	UiInputPanel() {
		setOpaque(false);
		setLayout(new MigLayout("", "[][grow][]", "3[][]"));

		nameLabel = new JLabel("Name:");
		add(nameLabel, "cell 0 0,alignx trailing");

		nameField = new JComboBox();
		nameField.setName(FileChooserPanel.NAME_FIELD_NAME);
		nameField.setEditable(true);
		add(nameField, "cell 1 0,growx");

		approveButton = new JButton("Approve");
		approveButton.setName(FileChooserPanel.APPROVE_BUTTON_NAME);
		add(approveButton, "growx 2,growy 0");

		filterLabel = new JLabel("Filter:");
		add(filterLabel, "cell 0 1,alignx trailing");

		filterField = new JComboBox();
		filterField.setName(FileChooserPanel.FILTER_FIELD_NAME);
		filterField.setEditable(true);
		add(filterField, "cell 1 1,growx");

		cancelButton = new JButton("Cancel");
		cancelButton.setName(FileChooserPanel.CANCEL_BUTTON_NAME);
		add(cancelButton, "growx 2,growy 1");

	}
}
