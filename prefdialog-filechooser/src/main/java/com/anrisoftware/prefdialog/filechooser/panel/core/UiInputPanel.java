package com.anrisoftware.prefdialog.filechooser.panel.core;

import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.APPROVE_BUTTON_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.CANCEL_BUTTON_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.FILTER_FIELD_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.FILTER_LABEL_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.NAME_FIELD_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.NAME_LABEL_NAME;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
class UiInputPanel extends JPanel {

	final JLabel nameLabel;

	final JComboBox<Set<File>> nameField;

	final JLabel filterLabel;

	final JComboBox filterField;

	final JButton approveButton;

	final JButton cancelButton;

	final Map<String, JComponent> components;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	UiInputPanel() {
		setOpaque(false);
		setLayout(new MigLayout("", "[][grow][]", "3[][]"));

		this.components = new HashMap<String, JComponent>();

		nameLabel = new JLabel("Name:");
		nameLabel.setName(NAME_LABEL_NAME);
		add(nameLabel, "cell 0 0,alignx trailing");
		components.put(NAME_LABEL_NAME, nameLabel);

		nameField = new JComboBox();
		nameField.setName(NAME_FIELD_NAME);
		nameField.setEditable(true);
		add(nameField, "cell 1 0,growx");
		components.put(NAME_FIELD_NAME, nameField);

		approveButton = new JButton("Approve");
		approveButton.setName(APPROVE_BUTTON_NAME);
		add(approveButton, "growx 2,growy 0");
		components.put(APPROVE_BUTTON_NAME, approveButton);

		filterLabel = new JLabel("Filter:");
		filterLabel.setName(FILTER_LABEL_NAME);
		add(filterLabel, "cell 0 1,alignx trailing");
		components.put(FILTER_LABEL_NAME, filterLabel);

		filterField = new JComboBox();
		filterField.setName(FILTER_FIELD_NAME);
		filterField.setEditable(true);
		add(filterField, "cell 1 1,growx");
		components.put(FILTER_FIELD_NAME, filterField);

		cancelButton = new JButton("Cancel");
		cancelButton.setName(CANCEL_BUTTON_NAME);
		add(cancelButton, "growx 2,growy 1");
		components.put(CANCEL_BUTTON_NAME, cancelButton);
	}
}
