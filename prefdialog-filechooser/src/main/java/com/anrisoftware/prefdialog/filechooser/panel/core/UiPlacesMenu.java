package com.anrisoftware.prefdialog.filechooser.panel.core;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

@SuppressWarnings("serial")
class UiPlacesMenu extends JPopupMenu {

	final JMenuItem addPlace;

	final JMenuItem removePlace;

	UiPlacesMenu() {
		this.addPlace = new JMenuItem("Add Place");
		this.removePlace = new JMenuItem("Add Place");
		setupMenu();
		setupMenuItems();
	}

	private void setupMenu() {
		add(addPlace);
		add(removePlace);
	}

	private void setupMenuItems() {
		addPlace.setText("Add Place");
		addPlace.setName("add-place-menu");
		addPlace.setMnemonic('a');
		removePlace.setText("Remove Place");
		removePlace.setName("remove-place-menu");
		removePlace.setMnemonic('r');
	}
}
