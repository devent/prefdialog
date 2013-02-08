package com.anrisoftware.prefdialog.filechooser.panel.core;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.miscswing.lists.RubberBandingList;

@SuppressWarnings("serial")
class UiPlacesPanel extends JPanel {

	final JList<File> placesList;

	final JScrollPane placesScrollPane;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	UiPlacesPanel() {
		setLayout(new BorderLayout(0, 0));
		placesList = new RubberBandingList(
				new Object[] { "One", "Two", "Three" });
		placesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		placesList.setName(FileChooserPanel.PLACES_LIST_NAME);
		placesScrollPane = new JScrollPane(placesList);
		add(placesScrollPane);
	}
}
