/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-lookfeelpanel.
 * 
 * prefdialog-lookfeelpanel is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * 
 * prefdialog-lookfeelpanel is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-lookfeelpanel. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel;

import javax.swing.UIManager.LookAndFeelInfo;

import com.anrisoftware.prefdialog.annotations.ComboBox;
import com.anrisoftware.prefdialog.annotations.Group;
import com.anrisoftware.prefdialog.panel.api.Appearance;
import com.anrisoftware.prefdialog.panel.api.LookAndFeelFont;
import com.google.inject.Inject;

/**
 * Creates a {@link ComboBox} for the Look&Feel and a {@link Group} for the font
 * to choose.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class AppearanceImpl implements Appearance {

	@ComboBox(title = "Look and feel:", model = LookAndFeelsModel.class, renderer = LookAndFeelsRenderer.class)
	private final LookAndFeelInfoListItem lookAndFeel;

	@Group(title = "Font:")
	private final LookAndFeelFont lookAndFeelFont;

	/**
	 * Injects the dependencies.
	 * 
	 * @param lookAndFeelItem
	 *            the default {@link LookAndFeelInfoListItem}.
	 * 
	 * @param lookAndFeelFont
	 *            the {@link LookAndFeelFont}.
	 */
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
