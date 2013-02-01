package com.anrisoftware.prefdialog.filechooser.panel.core;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

@SuppressWarnings("serial")
class UiOptionsMenu extends JPopupMenu {

	private final JMenu sorting;

	private final JMenu view;

	private final JCheckBoxMenuItem showHiddenFiles;

	private final JCheckBoxMenuItem showPlaces;

	private final JRadioButtonMenuItem sortName;

	private final JRadioButtonMenuItem sortSize;

	private final JRadioButtonMenuItem sortDate;

	private final JRadioButtonMenuItem sortType;

	private final JRadioButtonMenuItem viewShort;

	private final JRadioButtonMenuItem viewDetailed;

	private final JRadioButtonMenuItem viewTree;

	private final JRadioButtonMenuItem viewDetailedTree;

	UiOptionsMenu() {
		this.sorting = new JMenu();
		this.view = new JMenu();
		this.showHiddenFiles = new JCheckBoxMenuItem();
		this.showPlaces = new JCheckBoxMenuItem();
		this.sortName = new JRadioButtonMenuItem();
		this.sortSize = new JRadioButtonMenuItem();
		this.sortDate = new JRadioButtonMenuItem();
		this.sortType = new JRadioButtonMenuItem();
		this.viewShort = new JRadioButtonMenuItem();
		this.viewDetailed = new JRadioButtonMenuItem();
		this.viewTree = new JRadioButtonMenuItem();
		this.viewDetailedTree = new JRadioButtonMenuItem();
		setupMenu();
	}

	private void setupMenu() {
		add(sorting);
		add(view);
		add(showHiddenFiles);
		add(showPlaces);
		sorting.add(sortName);
		sorting.add(sortSize);
		sorting.add(sortDate);
		sorting.add(sortType);
		view.add(viewShort);
		view.add(viewDetailed);
		view.add(viewTree);
		view.add(viewDetailedTree);
	}

}
