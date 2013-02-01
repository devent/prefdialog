package com.anrisoftware.prefdialog.filechooser.panel.core;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings({ "serial", "rawtypes" })
final class UiFileChooserPanel extends JPanel {

	private static final String FILES_LIST_NAME = "files-list";

	private static final String LOCATION_FIELD_NAME = "location-field";

	private static final String PREVIEW_BUTTON_NAME = "preview-button";

	private static final String REFRESH_BUTTON_NAME = "refresh-button";

	private static final String UP_BUTTON_NAME = "up-button";

	private static final String FORWARD_BUTTON_NAME = "forward-button";

	private static final String BACK_BUTTON_NAME = "back-button";

	private static final String CANCEL_BUTTON_NAME = "cancel-button";

	private static final String FILTER_FIELD_NAME = "filter-field";

	private static final String APPROVE_BUTTON_NAME = "approve-button";

	private static final String NAME_FIELD_NAME = "name-field";

	private static final String PLACES_LIST_NAME = "places-list";

	private static final String PLACES_TOOLBAR_NAME = "places-toolbar";

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

	private final PopupButton optionsButtonPopup;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	UiFileChooserPanel() {
		setLayout(new BorderLayout(0, 0));

		placesToolBar = new JToolBar();
		placesToolBar.setName(PLACES_TOOLBAR_NAME);
		placesToolBar.setOrientation(SwingConstants.VERTICAL);
		add(placesToolBar, BorderLayout.WEST);

		placesPanel = new JPanel();
		placesPanel.setBorder(new TitledBorder(null, "Places",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		placesToolBar.add(placesPanel);
		placesPanel.setLayout(new BorderLayout(0, 0));

		placesList = new JList(new Object[] { "One", "Two", "Three" });
		placesList.setName(PLACES_LIST_NAME);
		JScrollPane placesScrollPane = new JScrollPane(placesList);
		placesPanel.add(placesScrollPane);

		inputPanel = new JPanel();
		add(inputPanel, BorderLayout.SOUTH);
		inputPanel.setLayout(new MigLayout("", "[][grow][]", "[][]"));

		nameLabel = new JLabel("Name:");
		inputPanel.add(nameLabel, "cell 0 0,alignx trailing");

		nameField = new JComboBox();
		nameField.setName(NAME_FIELD_NAME);
		nameField.setEditable(true);
		inputPanel.add(nameField, "cell 1 0,growx");

		approveButton = new JButton("Approve");
		approveButton.setName(APPROVE_BUTTON_NAME);
		inputPanel.add(approveButton, "growx 2,growy 0");

		filterLabel = new JLabel("Filter:");
		inputPanel.add(filterLabel, "cell 0 1,alignx trailing");

		filterField = new JComboBox();
		filterField.setName(FILTER_FIELD_NAME);
		filterField.setEditable(true);
		inputPanel.add(filterField, "cell 1 1,growx");

		cancelButton = new JButton("Cancel");
		cancelButton.setName(CANCEL_BUTTON_NAME);
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
		backButton.setName(BACK_BUTTON_NAME);
		optionalToolButtonsPanel.add(backButton);

		forwardButton = new JButton("Go forward");
		forwardButton.setName(FORWARD_BUTTON_NAME);
		optionalToolButtonsPanel.add(forwardButton);

		upButton = new JButton("Go up");
		upButton.setName(UP_BUTTON_NAME);
		optionalToolButtonsPanel.add(upButton);

		refreshButton = new JButton("Refresh");
		refreshButton.setName(REFRESH_BUTTON_NAME);
		optionalToolButtonsPanel.add(refreshButton);

		showPreviewButton = new JToggleButton("Show preview");
		showPreviewButton.setName(PREVIEW_BUTTON_NAME);
		optionalToolButtonsPanel.add(showPreviewButton);

		optionsButton = new JButton("Options");
		optionsButton.setName("options-button");
		toolButtonsPanel.add(optionsButton, "cell 2 0");
		optionsButtonPopup = PopupButton.decorate(optionsButton,
				new JPopupMenu());

		locationFieldPanel = new JPanel();
		toolsPanel.add(locationFieldPanel, "cell 0 1,grow");
		locationFieldPanel.setLayout(new MigLayout("", "0[grow]0", "0[]0"));

		locationField = new JComboBox();
		locationField.setName(LOCATION_FIELD_NAME);
		locationFieldPanel.add(locationField, "cell 0 0,growx");
		locationField.setEditable(true);

		filesPanel = new JPanel();
		locationPanel.add(filesPanel, BorderLayout.CENTER);
		filesPanel.setLayout(new MigLayout("", "[grow,fill]", "0[grow,fill]3"));

		filesScrollPane = new JScrollPane();
		filesPanel.add(filesScrollPane, "cell 0 0");

		filesList = new JList();
		filesList.setName(FILES_LIST_NAME);
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
		button.setName(APPROVE_BUTTON_NAME);
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
		button.setName(CANCEL_BUTTON_NAME);
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

	public void setOptionsMenu(JPopupMenu menu) {
		optionsButtonPopup.setPopupMenu(menu);
	}

	private void updateContainer(Container container) {
		container.getLayout().layoutContainer(container);
		container.repaint();
	}

}
