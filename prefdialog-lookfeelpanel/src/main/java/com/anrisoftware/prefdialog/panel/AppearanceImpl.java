package com.anrisoftware.prefdialog.panel;

import javax.swing.UIManager.LookAndFeelInfo;

import com.anrisoftware.prefdialog.annotations.ComboBox;
import com.anrisoftware.prefdialog.annotations.Group;
import com.anrisoftware.prefdialog.panel.api.Appearance;
import com.anrisoftware.prefdialog.panel.api.LookAndFeelFont;
import com.google.inject.Inject;

class AppearanceImpl implements Appearance {

	@ComboBox(title = "Look and feel:", model = LookAndFeelsModel.class, renderer = LookAndFeelsRenderer.class)
	private final LookAndFeelInfoListItem lookAndFeel;

	@Group(title = "Font:")
	private final LookAndFeelFont lookAndFeelFont;

	@Inject
	AppearanceImpl(LookAndFeelInfoListItem lookAndFeelItem,
			LookAndFeelFont lookAndFeelFont) {
		this.lookAndFeel = lookAndFeelItem;
		this.lookAndFeelFont = lookAndFeelFont;
	}

	@Override
	public LookAndFeelInfo getLookAndFeelInfo() {
		return lookAndFeel.getLookAndFeelInfo();
	}

	@Override
	public LookAndFeelFont getLookAndFeelFont() {
		return lookAndFeelFont;
	}

}
