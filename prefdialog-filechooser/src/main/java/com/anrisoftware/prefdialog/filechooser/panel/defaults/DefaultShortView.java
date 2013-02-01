package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import java.awt.Component;
import java.io.File;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.FileChooserUI;
import javax.swing.plaf.basic.BasicFileChooserUI;

@SuppressWarnings("serial")
public class DefaultShortView extends DefaultListCellRenderer {

	private final FileView fileView;

	public DefaultShortView() {
		this(null);
	}

	public DefaultShortView(FileView fileView) {
		this.fileView = createFileView(fileView);
	}

	private FileView createFileView(FileView fileView) {
		if (fileView == null) {
			fileView = getDefaultFileView();
		}
		return fileView;
	}

	private static FileView getDefaultFileView() {
		JFileChooser chooser = new JFileChooser();
		FileChooserUI ui = (FileChooserUI) BasicFileChooserUI.createUI(chooser);
		ui.installUI(chooser);
		return ui.getFileView(chooser);
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
