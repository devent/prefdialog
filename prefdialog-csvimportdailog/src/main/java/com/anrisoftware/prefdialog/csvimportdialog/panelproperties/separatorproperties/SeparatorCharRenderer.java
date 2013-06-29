package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.separatorproperties;

import static java.lang.String.format;

import java.awt.Component;
import java.util.MissingResourceException;

import javax.inject.Inject;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;

@SuppressWarnings("serial")
public class SeparatorCharRenderer extends DefaultListCellRenderer {

	private final Texts texts;

	@Inject
	SeparatorCharRenderer(TextsFactory textsFactory) {
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
