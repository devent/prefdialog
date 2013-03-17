package com.anrisoftware.prefdialog.filechooser.panel.core;

import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.FILES_LIST_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.LOCATION_FIELD_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.OPTIONS_BUTTON_NAME;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import net.miginfocom.swing.MigLayout;

import com.anrisoftware.prefdialog.filechooser.panel.api.ToolAction;
import com.anrisoftware.prefdialog.miscswing.lists.RubberBandingList;
import com.anrisoftware.prefdialog.miscswing.menus.PopupMenuComponent;

@SuppressWarnings({ "serial" })
final class UiFilesListPanel extends JPanel {

	final Map<String, JComponent> components;

	final JComboBox<File> locationField;

	final JPanel toolsPanel;

	final JPanel toolButtonsPanel;

	final JPanel optionalToolButtonsPanel;

	final JButton optionsButton;

	final JScrollPane filesScrollPane;

	final JList filesList;

	private final PopupMenuComponent optionsPopup;

	/**
	 * Create the panel.
	 */
	UiFilesListPanel() {
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));

		this.components = new HashMap<String, JComponent>();

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
		optionsButton.setName(OPTIONS_BUTTON_NAME);
		optionsButton.setMargin(new Insets(1, 1, 1, 1));
		optionsButton.setBorderPainted(false);
		toolButtonsPanel.add(optionsButton, "cell 2 0");
		components.put(OPTIONS_BUTTON_NAME, optionsButton);
		optionsPopup = new PopupMenuComponent(optionsButton, new JPopupMenu());

		locationField = new JComboBox<File>();
		locationField.setName(LOCATION_FIELD_NAME);
		locationField.setEditable(true);
		toolsPanel.add(locationField, "cell 0 1,growx");
		components.put(LOCATION_FIELD_NAME, locationField);

		filesScrollPane = new JScrollPane();
		add(filesScrollPane, BorderLayout.CENTER);

		filesList = new RubberBandingList();
		filesList.setName(FILES_LIST_NAME);
		filesScrollPane.setViewportView(filesList);
		components.put(FILES_LIST_NAME, filesList);

	}

	public void setOptionsMenu(JPopupMenu menu) {
		optionsPopup.setPopupMenu(menu);
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
		component.setName(action.getName());
		component.setMargin(new Insets(1, 1, 1, 1));
		component.setBorderPainted(false);
		component.setHideActionText(true);
		if (action.getName() != null) {
			components.put(action.getName(), component);
		}
		optionalToolButtonsPanel.add(component);
		optionalToolButtonsPanel.getLayout().layoutContainer(
				optionalToolButtonsPanel);
		optionalToolButtonsPanel.invalidate();
	}

}
