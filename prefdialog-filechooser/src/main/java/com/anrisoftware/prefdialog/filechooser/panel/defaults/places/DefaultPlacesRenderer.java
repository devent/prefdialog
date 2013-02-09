package com.anrisoftware.prefdialog.filechooser.panel.defaults.places;

import static com.anrisoftware.prefdialog.filechooser.panel.defaults.fileview.DefaultFileViewFactory.createDefaultFileView;

import java.awt.Component;
import java.io.File;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.filechooser.FileView;

import com.anrisoftware.prefdialog.filechooser.panel.api.PlacesRenderer;

@SuppressWarnings("serial")
public class DefaultPlacesRenderer extends DefaultListCellRenderer implements
		PlacesRenderer {

	private final FileView fileView;

	public DefaultPlacesRenderer() {
		this(null);
	}

	public DefaultPlacesRenderer(FileView fileView) {
		this.fileView = fileView == null ? createDefaultFileView() : fileView;
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		if (value instanceof File) {
			setup((File) value);
		}
		return this;
	}

	private void setup(final File file) {
		setIcon(fileView.getIcon(file));
		setText(fileView.getName(file));
	}
}
