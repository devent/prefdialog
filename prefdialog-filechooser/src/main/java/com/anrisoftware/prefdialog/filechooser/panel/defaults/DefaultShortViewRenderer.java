package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import static java.awt.Font.PLAIN;

import java.awt.Component;
import java.awt.Font;
import java.io.File;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.FileChooserUI;
import javax.swing.plaf.basic.BasicFileChooserUI;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileViewRenderer;

@SuppressWarnings("serial")
public class DefaultShortViewRenderer extends DefaultListCellRenderer implements
		FileViewRenderer<Object> {

	private final FileView fileView;

	public DefaultShortViewRenderer() {
		this(null);
	}

	public DefaultShortViewRenderer(FileView fileView) {
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
		setupFont();
		if (value instanceof File) {
			setup((File) value);
		}
		return this;
	}

	private void setupFont() {
		Font font = getFont();
		setFont(new Font(font.getName(), PLAIN, font.getSize()));
	}

	private void setup(final File file) {
		setIcon(fileView.getIcon(file));
		setText(fileView.getName(file));
	}

	@Override
	public int getLayoutOrientation() {
		return JList.VERTICAL_WRAP;
	}

	@Override
	public int getVisibleRowCount() {
		return -1;
	}

}
