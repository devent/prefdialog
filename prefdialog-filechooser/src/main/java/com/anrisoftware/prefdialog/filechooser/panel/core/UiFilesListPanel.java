package com.anrisoftware.prefdialog.filechooser.panel.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import net.miginfocom.swing.MigLayout;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolAction;
import com.anrisoftware.prefdialog.miscswing.lists.RubberBandingList;

@SuppressWarnings({ "serial", "rawtypes" })
final class UiFilesListPanel extends JPanel {

	final JComboBox locationField;

	final JPanel toolsPanel;

	final JPanel toolButtonsPanel;

	final JPanel optionalToolButtonsPanel;

	final JButton optionsButton;

	final JScrollPane filesScrollPane;

	final JList filesList;

	private final PopupButton optionsButtonPopup;

	/**
	 * Create the panel.
	 */
	UiFilesListPanel() {
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));

		toolsPanel = new JPanel();
		toolsPanel.setOpaque(false);
		toolsPanel.setBorder(null);
		add(toolsPanel, BorderLayout.NORTH);
		toolsPanel.setLayout(new MigLayout("", "0[grow]0", "0[][]"));

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
		optionsButton.setMargin(new Insets(1, 1, 1, 1));
		optionsButton.setBorderPainted(false);
		optionsButton.setHideActionText(true);
		toolButtonsPanel.add(optionsButton, "cell 2 0");
		optionsButtonPopup = PopupButton.decorate(optionsButton,
				new JPopupMenu());

		locationField = new JComboBox();
		locationField.setName(FileChooserPanel.LOCATION_FIELD_NAME);
		toolsPanel.add(locationField, "cell 0 1,growx");
		locationField.setEditable(true);

		filesScrollPane = new JScrollPane();
		add(filesScrollPane, BorderLayout.CENTER);

		filesList = new RubberBandingList();
		filesList.setName(FileChooserPanel.FILES_LIST_NAME);
		filesScrollPane.setViewportView(filesList);

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
		component.setHideActionText(true);
		optionalToolButtonsPanel.add(component);
		optionalToolButtonsPanel.getLayout().layoutContainer(
				optionalToolButtonsPanel);
		optionalToolButtonsPanel.invalidate();
	}

}
