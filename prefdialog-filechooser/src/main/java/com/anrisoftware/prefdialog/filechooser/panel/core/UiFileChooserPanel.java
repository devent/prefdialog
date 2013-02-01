package com.anrisoftware.prefdialog.filechooser.panel.core;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings({ "serial", "rawtypes" })
final class UiFileChooserPanel extends JPanel {

	private final JComboBox nameField;
	private final JComboBox filterField;
	private final JComboBox locationField;
	private final JToolBar placesToolBar;
	private final JPanel placesPanel;
	private final JList placesList;
	private final JPanel inputPanel;
	private JLabel nameLabel;
	private JButton approveButton;
	private JButton cancelButton;
	private final JPanel locationPanel;
	private final JPanel toolsPanel;
	private final JPanel toolButtonsPanel;
	private final JPanel optionalToolButtonsPanel;
	private final JButton backButton;
	private final JButton forwardButton;
	private final JButton upButton;
	private final JButton refreshButton;
	private final JToggleButton showPreviewButton;
	private final JButton optionsButton;
	private final JPanel locationFieldPanel;
	private final JPanel filesPanel;
	private final JScrollPane filesScrollPane;
	private final JList filesList;
	private JLabel filterLabel;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	UiFileChooserPanel() {
		setLayout(new BorderLayout(0, 0));

		placesToolBar = new JToolBar();
		placesToolBar.setOrientation(SwingConstants.VERTICAL);
		add(placesToolBar, BorderLayout.WEST);

		placesPanel = new JPanel();
		placesPanel.setBorder(new TitledBorder(null, "Places",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		placesToolBar.add(placesPanel);
		placesPanel.setLayout(new BorderLayout(0, 0));

		placesList = new JList(new Object[] { "One", "Two", "Three" });
		JScrollPane placesScrollPane = new JScrollPane(placesList);
		placesPanel.add(placesScrollPane);

		inputPanel = new JPanel();
		add(inputPanel, BorderLayout.SOUTH);
		inputPanel.setLayout(new MigLayout("", "[][grow][]", "[][]"));

		nameLabel = new JLabel("Name:");
		inputPanel.add(nameLabel, "cell 0 0,alignx trailing");

		nameField = new JComboBox();
		nameField.setEditable(true);
		inputPanel.add(nameField, "cell 1 0,growx");

		approveButton = new JButton("Approve");
		inputPanel.add(approveButton, "growx 2,growy 0");

		filterLabel = new JLabel("Filter:");
		inputPanel.add(filterLabel, "cell 0 1,alignx trailing");

		filterField = new JComboBox();
		filterField.setEditable(true);
		inputPanel.add(filterField, "cell 1 1,growx");

		cancelButton = new JButton("Cancel");
		inputPanel.add(cancelButton, "growx 2,growy 1");

		locationPanel = new JPanel();
		add(locationPanel, BorderLayout.CENTER);
		locationPanel.setLayout(new BorderLayout(0, 0));

		toolsPanel = new JPanel();
		toolsPanel.setBorder(null);
		locationPanel.add(toolsPanel, BorderLayout.NORTH);
		toolsPanel.setLayout(new MigLayout("", "[grow]", "[][]"));

		toolButtonsPanel = new JPanel();
		toolButtonsPanel.setBorder(null);
		toolsPanel.add(toolButtonsPanel, "cell 0 0,growx");
		toolButtonsPanel
				.setLayout(new MigLayout("", "0[][grow][]0", "0[grow]0"));

		optionalToolButtonsPanel = new JPanel();
		toolButtonsPanel.add(optionalToolButtonsPanel, "cell 0 0");
		optionalToolButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,
				0));

		backButton = new JButton("Go back");
		optionalToolButtonsPanel.add(backButton);

		forwardButton = new JButton("Go forward");
		optionalToolButtonsPanel.add(forwardButton);

		upButton = new JButton("Go up");
		optionalToolButtonsPanel.add(upButton);

		refreshButton = new JButton("Refresh");
		optionalToolButtonsPanel.add(refreshButton);

		showPreviewButton = new JToggleButton("Show preview");
		optionalToolButtonsPanel.add(showPreviewButton);

		optionsButton = new JButton("Options");
		toolButtonsPanel.add(optionsButton, "cell 2 0");

		locationFieldPanel = new JPanel();
		toolsPanel.add(locationFieldPanel, "cell 0 1,grow");
		locationFieldPanel.setLayout(new MigLayout("", "0[grow]0", "0[]0"));

		locationField = new JComboBox();
		locationFieldPanel.add(locationField, "cell 0 0,growx");
		locationField.setEditable(true);

		filesPanel = new JPanel();
		locationPanel.add(filesPanel, BorderLayout.CENTER);
		filesPanel.setLayout(new MigLayout("", "[grow,fill]", "0[grow,fill]3"));

		filesScrollPane = new JScrollPane();
		filesPanel.add(filesScrollPane, "cell 0 0");

		filesList = new JList();
		filesScrollPane.setViewportView(filesList);

	}

	public JComboBox getNameField() {
		return nameField;
	}

	public void setFilterLabel(JLabel label) {
		JLabel old = filterLabel;
		filterLabel = label;
		inputPanel.remove(old);
		inputPanel.add(label, "cell 0 1,alignx trailing");
		updateContainer(inputPanel);
	}

	public JLabel getFilterLabel() {
		return filterLabel;
	}

	public JComboBox getFilterField() {
		return filterField;
	}

	public JComboBox getLocationField() {
		return locationField;
	}

	public JToolBar getPlacesToolBar() {
		return placesToolBar;
	}

	public JPanel getPlacesPanel() {
		return placesPanel;
	}

	public JList getPlacesList() {
		return placesList;
	}

	public JPanel getInputPanel() {
		return inputPanel;
	}

	public void setNameLabel(JLabel label) {
		JLabel old = nameLabel;
		nameLabel = label;
		inputPanel.remove(old);
		inputPanel.add(label, "cell 0 0,alignx trailing");
		updateContainer(inputPanel);
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public void setApproveButton(JButton button) {
		JButton old = approveButton;
		approveButton = button;
		inputPanel.remove(old);
		inputPanel.add(button, "growx 2,growy 0");
		updateContainer(inputPanel);
	}

	public JButton getApproveButton() {
		return approveButton;
	}

	public void setCancelButton(JButton button) {
		JButton old = cancelButton;
		cancelButton = button;
		inputPanel.remove(old);
		inputPanel.add(cancelButton, "growx 2,growy 1");
		updateContainer(inputPanel);
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JPanel getLocationPanel() {
		return locationPanel;
	}

	public JPanel getToolsPanel() {
		return toolsPanel;
	}

	public JPanel getToolButtonsPanel() {
		return toolButtonsPanel;
	}

	public JPanel getOptionalToolButtonsPanel() {
		return optionalToolButtonsPanel;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public JButton getForwardButton() {
		return forwardButton;
	}

	public JButton getUpButton() {
		return upButton;
	}

	public JButton getRefreshButton() {
		return refreshButton;
	}

	public JToggleButton getShowPreviewButton() {
		return showPreviewButton;
	}

	public JButton getOptionsButton() {
		return optionsButton;
	}

	public JPanel getLocationFieldPanel() {
		return locationFieldPanel;
	}

	public JPanel getFilesPanel() {
		return filesPanel;
	}

	public JScrollPane getFilesScrollPane() {
		return filesScrollPane;
	}

	public JList getFilesList() {
		return filesList;
	}

	private void updateContainer(Container container) {
		container.getLayout().layoutContainer(container);
		container.repaint();
	}

}
