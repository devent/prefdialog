package com.anrisoftware.prefdialog.miscswing.toolbarmenu;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

/**
 * Context menu to set text position and icon size.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class UiMenu extends JPopupMenu {

	private final JMenu iconSizeMenu;
	private final JMenu textPositionMenu;
	private final JRadioButtonMenuItem iconsOnlyMenu;
	private final JRadioButtonMenuItem textOnlyMenu;
	private final JRadioButtonMenuItem textAlongsideIconsMenu;
	private final JRadioButtonMenuItem smallMenu;
	private final JRadioButtonMenuItem mediumMenu;
	private final JRadioButtonMenuItem hugeMenu;
	private final JRadioButtonMenuItem largeMenu;
	private final ButtonGroup textPositionGroup;
	private final ButtonGroup iconSizeGroup;

	UiMenu() {

		textPositionMenu = new JMenu("Text Position");
		add(textPositionMenu);

		textOnlyMenu = new JRadioButtonMenuItem("Text Only");
		textPositionMenu.add(textOnlyMenu);

		iconsOnlyMenu = new JRadioButtonMenuItem("Icons Only");
		textPositionMenu.add(iconsOnlyMenu);

		textAlongsideIconsMenu = new JRadioButtonMenuItem(
				"Text Alongside Icons");
		textPositionMenu.add(textAlongsideIconsMenu);

		textPositionGroup = new ButtonGroup();
		textPositionGroup.add(textOnlyMenu);
		textPositionGroup.add(iconsOnlyMenu);
		textPositionGroup.add(textAlongsideIconsMenu);

		iconSizeMenu = new JMenu("Icon Size");
		add(iconSizeMenu);

		smallMenu = new JRadioButtonMenuItem("Small");
		iconSizeMenu.add(smallMenu);

		mediumMenu = new JRadioButtonMenuItem("Medium");
		iconSizeMenu.add(mediumMenu);

		largeMenu = new JRadioButtonMenuItem("Large");
		iconSizeMenu.add(largeMenu);

		hugeMenu = new JRadioButtonMenuItem("Huge");
		iconSizeMenu.add(hugeMenu);

		iconSizeGroup = new ButtonGroup();
		iconSizeGroup.add(smallMenu);
		iconSizeGroup.add(mediumMenu);
		iconSizeGroup.add(largeMenu);
		iconSizeGroup.add(hugeMenu);
	}

	public JMenu getIconSizeMenu() {
		return iconSizeMenu;
	}

	public JMenu getTextPositionMenu() {
		return textPositionMenu;
	}

	public JRadioButtonMenuItem getIconsOnlyMenu() {
		return iconsOnlyMenu;
	}

	public JRadioButtonMenuItem getTextOnlyMenu() {
		return textOnlyMenu;
	}

	public JRadioButtonMenuItem getTextAlongsideIconsMenu() {
		return textAlongsideIconsMenu;
	}

	public JRadioButtonMenuItem getSmallMenu() {
		return smallMenu;
	}

	public JRadioButtonMenuItem getMediumMenu() {
		return mediumMenu;
	}

	public JRadioButtonMenuItem getHugeMenu() {
		return hugeMenu;
	}

	public JRadioButtonMenuItem getLargeMenu() {
		return largeMenu;
	}
}
