/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-csvimportdialog.
 *
 * prefdialog-csvimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-csvimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-csvimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.charactermodel;

import static java.lang.String.format;

import java.awt.Component;
import java.util.MissingResourceException;

import javax.inject.Inject;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;

/**
 * Converts known characters to names.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class CharacterRenderer extends DefaultListCellRenderer {

	private final Texts texts;

	/**
	 * Injects the texts resources factory.
	 * 
	 * @param textsFactory
	 *            the {@link TextsFactory}.
	 */
	@Inject
	CharacterRenderer(TextsFactory textsFactory) {
		this.texts = textsFactory.create(getClass().getSimpleName());
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		setupCharacter((Character) value);
		return this;
	}

	private void setupCharacter(char character) {
		String name = format("character_u%04x", (int) character);
		String title = String.valueOf(character);
		try {
			title = texts.getResource(name).getText();
		} catch (MissingResourceException e) {
		}
		setText(title);
	}
}
