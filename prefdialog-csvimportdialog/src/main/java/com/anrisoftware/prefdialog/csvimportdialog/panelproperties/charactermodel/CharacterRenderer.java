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
