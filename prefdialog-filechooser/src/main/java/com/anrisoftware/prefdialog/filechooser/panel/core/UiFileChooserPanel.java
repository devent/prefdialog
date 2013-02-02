package com.anrisoftware.prefdialog.filechooser.panel.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.AbstractButton;
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

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolAction;

@SuppressWarnings({ "serial", "rawtypes" })
final class UiFileChooserPanel extends JPanel {

	final JComboBox nameField;
	final JComboBox filterField;
	final JComboBox locationField;
	final JToolBar placesToolBar;
	final JPanel placesPanel;
	final JList placesList;
	final JPanel inputPanel;
	final JLabel nameLabel;
	final JButton approveButton;
	final JButton cancelButton;
	final JPanel locationPanel;
	final JPanel toolsPanel;
	final JPanel toolButtonsPanel;
	final JPanel optionalToolButtonsPanel;
	final JButton optionsButton;
	final JPanel locationFieldPanel;
	final JPanel filesPanel;
	final JScrollPane filesScrollPane;
	final JList filesList;
	final JLabel filterLabel;

	private final PopupButton optionsButtonPopup;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	UiFileChooserPanel() {
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));

		placesToolBar = new JToolBar();
		placesToolBar.setName(FileChooserPanel.PLACES_TOOLBAR_NAME);
		placesToolBar.setOrientation(SwingConstants.VERTICAL);
		add(placesToolBar, BorderLayout.WEST);

		placesPanel = new JPanel();
		placesPanel.setBorder(new TitledBorder(null, "Places",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		placesToolBar.add(placesPanel);
		placesPanel.setLayout(new BorderLayout(0, 0));

		placesList = new JList(new Object[] { "One", "Two", "Three" });
		placesList.setName(FileChooserPanel.PLACES_LIST_NAME);
		JScrollPane placesScrollPane = new JScrollPane(placesList);
		placesPanel.add(placesScrollPane);

		inputPanel = new JPanel();
		inputPanel.setOpaque(false);
		add(inputPanel, BorderLayout.SOUTH);
		inputPanel.setLayout(new MigLayout("", "[][grow][]", "[][]"));

		nameLabel = new JLabel("Name:");
		inputPanel.add(nameLabel, "cell 0 0,alignx trailing");

		nameField = new JComboBox();
		nameField.setName(FileChooserPanel.NAME_FIELD_NAME);
		nameField.setEditable(true);
		inputPanel.add(nameField, "cell 1 0,growx");

		approveButton = new JButton("Approve");
		approveButton.setName(FileChooserPanel.APPROVE_BUTTON_NAME);
		inputPanel.add(approveButton, "growx 2,growy 0");

		filterLabel = new JLabel("Filter:");
		inputPanel.add(filterLabel, "cell 0 1,alignx trailing");

		filterField = new JComboBox();
		filterField.setName(FileChooserPanel.FILTER_FIELD_NAME);
		filterField.setEditable(true);
		inputPanel.add(filterField, "cell 1 1,growx");

		cancelButton = new JButton("Cancel");
		cancelButton.setName(FileChooserPanel.CANCEL_BUTTON_NAME);
		inputPanel.add(cancelButton, "growx 2,growy 1");

		locationPanel = new JPanel();
		locationPanel.setOpaque(false);
		add(locationPanel, BorderLayout.CENTER);
		locationPanel.setLayout(new BorderLayout(0, 0));

		toolsPanel = new JPanel();
		toolsPanel.setOpaque(false);
		toolsPanel.setBorder(null);
		locationPanel.add(toolsPanel, BorderLayout.NORTH);
		toolsPanel.setLayout(new MigLayout("", "[grow]", "[][]"));

		toolButtonsPanel = new JPanel();
		toolButtonsPanel.setOpaque(false);
		toolButtonsPanel.setBorder(null);
		toolsPanel.add(toolButtonsPanel, "cell 0 0,growx");
		toolButtonsPanel
				.setLayout(new MigLayout("", "0[][grow][]0", "0[grow]0"));

		optionalToolButtonsPanel = new JPanel();
		optionalToolButtonsPanel.setOpaque(false);
		toolButtonsPanel.add(optionalToolButtonsPanel, "cell 0 0");
		optionalToolButtonsPanel
				.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		optionsButton = new JButton("Options");
		optionsButton.setName("options-button");
		toolButtonsPanel.add(optionsButton, "cell 2 0");
		optionsButtonPopup = PopupButton.decorate(optionsButton,
				new JPopupMenu());

		locationFieldPanel = new JPanel();
		locationFieldPanel.setOpaque(false);
		toolsPanel.add(locationFieldPanel, "cell 0 1,grow");
		locationFieldPanel.setLayout(new MigLayout("", "0[grow]0", "0[]0"));

		locationField = new JComboBox();
		locationField.setName(FileChooserPanel.LOCATION_FIELD_NAME);
		locationFieldPanel.add(locationField, "cell 0 0,growx");
		locationField.setEditable(true);

		filesPanel = new JPanel();
		filesPanel.setOpaque(false);
		locationPanel.add(filesPanel, BorderLayout.CENTER);
		filesPanel.setLayout(new MigLayout("", "[grow,fill]", "0[grow,fill]3"));

		filesScrollPane = new JScrollPane();
		filesPanel.add(filesScrollPane, "cell 0 0");

		filesList = new JList();
		filesList.setName(FileChooserPanel.FILES_LIST_NAME);
		filesScrollPane.setViewportView(filesList);

	}

	public JComboBox getNameField() {
		return nameField;
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

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public JButton getApproveButton() {
		return approveButton;
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

	public void removeToolButtons() {
		optionalToolButtonsPanel.removeAll();
		optionalToolButtonsPanel.getLayout().layoutContainer(
				optionalToolButtonsPanel);
		optionalToolButtonsPanel.invalidate();
	}

	public void addToolButton(ToolAction action) {
		AbstractButton component;
		if (action.isToggleButton()) {
			component = new JToggleButton(action);
		} else if (action.isSeparator()) {
			component = new JButton(action);
			component.setPreferredSize(new Dimension(6, component
					.getPreferredSize().height));
			component.setBorderPainted(false);
			component.setContentAreaFilled(false);
			component.setOpaque(false);
			component.setFocusable(false);
		} else {
			component = new JButton(action);
		}
		component.setMargin(new Insets(1, 1, 1, 1));
		component.setBorderPainted(false);
		component.setHideActionText(false);
		optionalToolButtonsPanel.add(component);
		optionalToolButtonsPanel.getLayout().layoutContainer(
				optionalToolButtonsPanel);
		optionalToolButtonsPanel.invalidate();
	}

}
