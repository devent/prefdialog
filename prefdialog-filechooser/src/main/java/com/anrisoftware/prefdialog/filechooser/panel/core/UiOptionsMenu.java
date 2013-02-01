package com.anrisoftware.prefdialog.filechooser.panel.core;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

@SuppressWarnings("serial")
class UiOptionsMenu extends JPopupMenu {

	final JMenu sorting;

	final JMenu view;

	final JCheckBoxMenuItem showHiddenFiles;

	final JCheckBoxMenuItem showPlaces;

	final JRadioButtonMenuItem sortName;

	final JRadioButtonMenuItem sortSize;

	final JRadioButtonMenuItem sortDate;

	final JRadioButtonMenuItem sortType;

	final JRadioButtonMenuItem viewShort;

	final JRadioButtonMenuItem viewDetailed;

	final JRadioButtonMenuItem viewTree;

	final JRadioButtonMenuItem viewDetailedTree;

	UiOptionsMenu() {
		this.sorting = new JMenu("Sorting");
		this.view = new JMenu("View");
		this.showHiddenFiles = new JCheckBoxMenuItem("Show Hidden Files");
		this.showPlaces = new JCheckBoxMenuItem("Show Places Panel");
		this.sortName = new JRadioButtonMenuItem("By Name");
		this.sortSize = new JRadioButtonMenuItem("By Size");
		this.sortDate = new JRadioButtonMenuItem("By Date");
		this.sortType = new JRadioButtonMenuItem("Bt Type");
		this.viewShort = new JRadioButtonMenuItem("Short View");
		this.viewDetailed = new JRadioButtonMenuItem("Detailed View");
		this.viewTree = new JRadioButtonMenuItem("Tree View");
		this.viewDetailedTree = new JRadioButtonMenuItem("Detailed Tree View");
		setupMenu();
		setupMenuItems();
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

	private void setupMenuItems() {
		sorting.setText("Sorting");
		sorting.setName("sorting-menu");
		sorting.setMnemonic('s');
		view.setText("View");
		view.setName("view-menu");
		view.setMnemonic('v');
		showHiddenFiles.setText("Show Hidden Files");
		showHiddenFiles.setName("show-hidden-files-menu");
		showHiddenFiles.setMnemonic('h');
		showHiddenFiles.setDisplayedMnemonicIndex(5);
		showPlaces.setText("Show Places Panel");
		showPlaces.setName("show-hidden-files-menu");
		showPlaces.setMnemonic('p');
		sortName.setText("By Name");
		sortName.setName("sort-name-menu");
		sortName.setMnemonic('n');
		sortSize.setText("By Size");
		sortSize.setName("sort-size-menu");
		sortSize.setMnemonic('s');
		sortDate.setText("By Date");
		sortDate.setName("sort-size-menu");
		sortDate.setMnemonic('d');
		sortType.setText("By Type");
		sortType.setName("sort-type-menu");
		sortType.setMnemonic('t');
		viewShort.setText("Short View");
		viewShort.setName("short-view-menu");
		viewShort.setMnemonic('s');
		viewDetailed.setText("Detailed View");
		viewDetailed.setName("detailed-view-menu");
		viewDetailed.setMnemonic('d');
		viewTree.setText("Tree View");
		viewTree.setName("tree-view-menu");
		viewTree.setMnemonic('t');
		viewDetailedTree.setText("Detailed Tree View");
		viewDetailedTree.setName("detailed-tree-view-menu");
		viewDetailedTree.setMnemonic('v');
	}
}
