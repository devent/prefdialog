package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import java.awt.Component;
import java.util.Set;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileNameRenderer;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.formats.FileNameFormat;

@SuppressWarnings("serial")
public class DefaultFileNameRenderer extends DefaultListCellRenderer implements
		FileNameRenderer {

	private final FileNameFormat format;

	public DefaultFileNameRenderer() {
		this.format = new FileNameFormat();
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		setupComponent();
		if (value instanceof Set) {
			setText(format.format(value));
		}
		return this;
	}

	private void setupComponent() {
	}

}
