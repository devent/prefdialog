package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import static java.lang.String.format;

import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import org.apache.commons.lang3.StringUtils;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileNameRenderer;

@SuppressWarnings("serial")
public class DefaultFileNameRenderer extends DefaultListCellRenderer implements
		FileNameRenderer {

	private final Font font;

	public DefaultFileNameRenderer() {
		this.font = createFont();
	}

	private Font createFont() {
		Font font = getFont();
		return new Font(font.getName(), Font.PLAIN, font.getSize());
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		setupComponent();
		if (value instanceof Set) {
			@SuppressWarnings("unchecked")
			Set<File> files = (Set<File>) value;
			if (files.size() > 1) {
				setupFiles(files);
			} else if (files.size() == 1) {
				Iterator<File> it = files.iterator();
				it.hasNext();
				setupFile(it.next());
			}
		}
		return this;
	}

	private void setupComponent() {
		setFont(font);
	}

	private void setupFile(File file) {
		setText(file.getName());
	}

	private void setupFiles(Set<File> files) {
		List<String> names = new ArrayList<String>(files.size());
		for (File file : files) {
			names.add(format("\"%s\"", file.getName()));
		}
		setText(StringUtils.join(names, " "));
	}
}
