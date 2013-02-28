package com.anrisoftware.prefdialog.filechooser.panel.core;

import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.DEFAULT_ICON_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.DETAILED_TREE_VIEW_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.DETAILED_VIEW_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.HUGE_ICON_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.ICONS_ONLY_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.ICON_SIZE_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.LARGE_ICON_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.MEDIUM_ICON_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SHORT_VIEW_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SHOW_HIDDEN_FILES_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SMALL_ICON_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SORTING_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SORT_DATE_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SORT_DESCENDING_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SORT_FOLDER_FIRST_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SORT_NAME_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SORT_SIZE_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SORT_TYPE_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.TEXT_ALONGSIDE_ICONS_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.TEXT_ONLY_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.TEXT_POSITION_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.TREE_VIEW_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.VIEW_MENU_NAME;
import static javax.swing.SwingUtilities.invokeLater;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

import com.anrisoftware.prefdialog.annotations.TextPosition;

@SuppressWarnings("serial")
class UiOptionsMenu extends JPopupMenu {

	final JMenu sorting;

	final ButtonGroup sortingGroup;

	final JMenu view;

	final JCheckBoxMenuItem showHiddenFiles;

	final JCheckBoxMenuItem showPlaces;

	final JRadioButtonMenuItem sortName;

	final JRadioButtonMenuItem sortSize;

	final JRadioButtonMenuItem sortDate;

	final JRadioButtonMenuItem sortType;

	final JCheckBoxMenuItem sortDescending;

	final JCheckBoxMenuItem sortFolderFirst;

	final JRadioButtonMenuItem viewShort;

	final JRadioButtonMenuItem viewDetailed;

	final JRadioButtonMenuItem viewTree;

	final JRadioButtonMenuItem viewDetailedTree;

	final JMenu textPosition;

	final JMenu iconSize;

	final JRadioButtonMenuItem iconsOnly;

	final JRadioButtonMenuItem textOnly;

	final JRadioButtonMenuItem textAlongsideIcons;

	final JRadioButtonMenuItem defaultIcon;

	final JRadioButtonMenuItem smallIcon;

	final JRadioButtonMenuItem mediumIcon;

	final JRadioButtonMenuItem largeIcon;

	final JRadioButtonMenuItem hugeIcon;

	final ButtonGroup textPositionGroup;

	final ButtonGroup iconSizeGroup;

	final Map<String, JComponent> components;

	UiOptionsMenu() {
		this.components = new HashMap<String, JComponent>(40);
		this.sorting = new JMenu("Sorting");
		this.view = new JMenu("View");
		this.sortingGroup = new ButtonGroup();
		this.showHiddenFiles = new JCheckBoxMenuItem("Show Hidden Files");
		this.showPlaces = new JCheckBoxMenuItem("Show Places Panel");
		this.sortName = new JRadioButtonMenuItem("By Name");
		this.sortSize = new JRadioButtonMenuItem("By Size");
		this.sortDate = new JRadioButtonMenuItem("By Date");
		this.sortType = new JRadioButtonMenuItem("By Type");
		this.sortDescending = new JCheckBoxMenuItem("Descending");
		this.sortFolderFirst = new JCheckBoxMenuItem("Folder First");
		this.viewShort = new JRadioButtonMenuItem("Short View");
		this.viewDetailed = new JRadioButtonMenuItem("Detailed View");
		this.viewTree = new JRadioButtonMenuItem("Tree View");
		this.viewDetailedTree = new JRadioButtonMenuItem("Detailed Tree View");
		this.textPosition = new JMenu("Text Position");
		this.textPositionGroup = new ButtonGroup();
		this.iconsOnly = new JRadioButtonMenuItem("Icons Only");
		this.textOnly = new JRadioButtonMenuItem("Text Only");
		this.textAlongsideIcons = new JRadioButtonMenuItem(
				"Text Alongside Icons");
		this.iconSize = new JMenu("Icon Size");
		this.iconSizeGroup = new ButtonGroup();
		this.defaultIcon = new JRadioButtonMenuItem("Default");
		this.smallIcon = new JRadioButtonMenuItem("Small");
		this.mediumIcon = new JRadioButtonMenuItem("Medium");
		this.largeIcon = new JRadioButtonMenuItem("Large");
		this.hugeIcon = new JRadioButtonMenuItem("Huge");
		setupMenu();
		setupMenuItems();
	}

	private void setupMenu() {
		add(sorting);
		add(view);
		add(textPosition);
		textPosition.add(iconsOnly);
		textPosition.add(textOnly);
		textPosition.add(textAlongsideIcons);
		textPositionGroup.add(iconsOnly);
		textPositionGroup.add(textOnly);
		textPositionGroup.add(textAlongsideIcons);
		add(iconSize);
		iconSize.add(defaultIcon);
		iconSize.add(smallIcon);
		iconSize.add(mediumIcon);
		iconSize.add(largeIcon);
		iconSize.add(hugeIcon);
		iconSizeGroup.add(defaultIcon);
		iconSizeGroup.add(smallIcon);
		iconSizeGroup.add(mediumIcon);
		iconSizeGroup.add(largeIcon);
		iconSizeGroup.add(hugeIcon);
		add(showHiddenFiles);
		add(showPlaces);
		sorting.add(sortName);
		sorting.add(sortSize);
		sorting.add(sortDate);
		sorting.add(sortType);
		sorting.addSeparator();
		sorting.add(sortDescending);
		sorting.add(sortFolderFirst);
		sortingGroup.add(sortName);
		sortingGroup.add(sortSize);
		sortingGroup.add(sortDate);
		sortingGroup.add(sortType);
		view.add(viewShort);
		view.add(viewDetailed);
		view.add(viewTree);
		view.add(viewDetailedTree);
	}

	private void setupMenuItems() {
		sorting.setText("Sorting");
		sorting.setName(SORTING_MENU_NAME);
		sorting.setMnemonic('s');
		components.put(SORTING_MENU_NAME, sorting);

		view.setText("View");
		view.setName(VIEW_MENU_NAME);
		view.setMnemonic('v');
		components.put(VIEW_MENU_NAME, sorting);

		showHiddenFiles.setText("Show Hidden Files");
		showHiddenFiles.setName(SHOW_HIDDEN_FILES_MENU_NAME);
		showHiddenFiles.setMnemonic('h');
		showHiddenFiles.setDisplayedMnemonicIndex(5);
		components.put(SHOW_HIDDEN_FILES_MENU_NAME, sorting);

		showPlaces.setText("Show Places Panel");
		showPlaces.setName("show-places-panel");
		showPlaces.setMnemonic('p');

		sortName.setText("By Name");
		sortName.setName(SORT_NAME_MENU_NAME);
		sortName.setMnemonic('n');
		components.put(SORT_NAME_MENU_NAME, sorting);

		sortSize.setText("By Size");
		sortSize.setName(SORT_SIZE_MENU_NAME);
		sortSize.setMnemonic('s');
		components.put(SORT_SIZE_MENU_NAME, sorting);

		sortDate.setText("By Date");
		sortDate.setName(SORT_DATE_MENU_NAME);
		sortDate.setMnemonic('d');
		components.put(SORT_DATE_MENU_NAME, sorting);

		sortType.setText("By Type");
		sortType.setName(SORT_TYPE_MENU_NAME);
		sortType.setMnemonic('t');
		components.put(SORT_TYPE_MENU_NAME, sorting);

		sortDescending.setText("Descending");
		sortDescending.setName(SORT_DESCENDING_MENU_NAME);
		sortDescending.setMnemonic('e');
		components.put(SORT_DESCENDING_MENU_NAME, sorting);

		sortFolderFirst.setText("Folder First");
		sortFolderFirst.setName(SORT_FOLDER_FIRST_MENU_NAME);
		sortFolderFirst.setMnemonic('f');
		components.put(SORT_FOLDER_FIRST_MENU_NAME, sorting);

		viewShort.setText("Short View");
		viewShort.setName(SHORT_VIEW_MENU_NAME);
		viewShort.setMnemonic('s');
		components.put(SHORT_VIEW_MENU_NAME, sorting);

		viewDetailed.setText("Detailed View");
		viewDetailed.setName(DETAILED_VIEW_MENU_NAME);
		viewDetailed.setMnemonic('d');
		components.put(DETAILED_VIEW_MENU_NAME, sorting);

		viewTree.setText("Tree View");
		viewTree.setName(TREE_VIEW_MENU_NAME);
		viewTree.setMnemonic('t');
		components.put(TREE_VIEW_MENU_NAME, sorting);

		viewDetailedTree.setText("Detailed Tree View");
		viewDetailedTree.setName(DETAILED_TREE_VIEW_MENU_NAME);
		viewDetailedTree.setMnemonic('v');
		components.put(DETAILED_TREE_VIEW_MENU_NAME, sorting);

		textPosition.setName(TEXT_POSITION_MENU_NAME);
		textPosition.setMnemonic('t');
		components.put(TEXT_POSITION_MENU_NAME, textPosition);

		iconsOnly.setName(ICONS_ONLY_MENU_NAME);
		iconsOnly.setMnemonic('i');
		components.put(ICONS_ONLY_MENU_NAME, iconsOnly);

		textOnly.setName(TEXT_ONLY_MENU_NAME);
		textOnly.setMnemonic('t');
		components.put(TEXT_ONLY_MENU_NAME, textOnly);

		textAlongsideIcons.setName(TEXT_ALONGSIDE_ICONS_MENU_NAME);
		textAlongsideIcons.setMnemonic('a');
		components.put(TEXT_ALONGSIDE_ICONS_MENU_NAME, textAlongsideIcons);

		iconSize.setName(ICON_SIZE_MENU_NAME);
		iconSize.setMnemonic('i');
		components.put(ICON_SIZE_MENU_NAME, iconSize);

		defaultIcon.setName(DEFAULT_ICON_MENU_NAME);
		defaultIcon.setMnemonic('d');
		components.put(DEFAULT_ICON_MENU_NAME, defaultIcon);

		smallIcon.setName(SMALL_ICON_MENU_NAME);
		smallIcon.setMnemonic('s');
		components.put(SMALL_ICON_MENU_NAME, smallIcon);

		mediumIcon.setName(MEDIUM_ICON_MENU_NAME);
		mediumIcon.setMnemonic('m');
		components.put(MEDIUM_ICON_MENU_NAME, mediumIcon);

		largeIcon.setName(LARGE_ICON_MENU_NAME);
		largeIcon.setMnemonic('l');
		components.put(LARGE_ICON_MENU_NAME, largeIcon);

		hugeIcon.setName(HUGE_ICON_MENU_NAME);
		hugeIcon.setMnemonic('h');
		components.put(HUGE_ICON_MENU_NAME, hugeIcon);
	}

	public void updateSelectedTextPosition(final TextPosition position) {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				updateSelectedTextPosition0(position);
			}
		});
	}

	private void updateSelectedTextPosition0(final TextPosition position) {
		switch (position) {
		case ICON_ONLY:
			iconsOnly.doClick();
			break;
		case TEXT_ALONGSIDE_ICON:
			textAlongsideIcons.doClick();
			break;
		case TEXT_ONLY:
			textOnly.doClick();
			break;
		case TEXT_UNDER_ICON:
			break;
		default:
			break;
		}
	}
}
