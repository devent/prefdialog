package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.advancedproperties;

import static java.lang.String.format;

import java.awt.Component;
import java.util.MissingResourceException;

import javax.inject.Inject;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;

/**
 * Renders line end symbols.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class LineEndRenderer extends DefaultListCellRenderer {

	private final Texts texts;

	/**
	 * Injects the texts resources factory.
	 * 
	 * @param textsFactory
	 *            the {@link TextsFactory}.
	 */
	@Inject
	LineEndRenderer(TextsFactory textsFactory) {
		this.texts = textsFactory.create(getClass().getSimpleName());
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		if (value instanceof LineEnd) {
			setupLineEnd((LineEnd) value);
		}
		return this;
	}

	private void setupLineEnd(LineEnd value) {
		try {
			String name = format("symbols_%s", value.name().toLowerCase());
			String title = texts.getResource(name).getText();
			setText(title);
		} catch (MissingResourceException e) {
			setText(value.toString());
		}
	}
}
