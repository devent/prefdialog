package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.importproperties;

import java.awt.Component;
import java.util.Locale;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.anrisoftware.prefdialog.miscswing.comboboxhistory.ItemDefault;

@SuppressWarnings("serial")
public class LocaleRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		if (value instanceof ItemDefault) {
			setupLocale((Locale) ((ItemDefault) value).getItem());
		} else if (value instanceof Locale) {
			setupLocale((Locale) value);
		}
		return this;
	}

	private void setupLocale(Locale locale) {
		setText(locale.getDisplayName());
	}
}
