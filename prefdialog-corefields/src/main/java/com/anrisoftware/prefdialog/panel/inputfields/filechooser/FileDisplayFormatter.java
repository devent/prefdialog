package com.anrisoftware.prefdialog.panel.inputfields.filechooser;

import static java.io.File.separatorChar;

import java.io.File;
import java.text.ParseException;

import javax.swing.text.DefaultFormatter;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("serial")
class FileDisplayFormatter extends DefaultFormatter {

	private int maxLength;

	public FileDisplayFormatter() {
		this.maxLength = 40;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	@Override
	public boolean getOverwriteMode() {
		return false;
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value == null) {
			return "";
		}
		String path = createPath((File) value);
		return path;
	}

	private String createPath(File value) {
		String path = value.getAbsolutePath();
		if (path.length() > maxLength) {
			path = shortPath(path);
		}
		return path;
	}

	private String shortPath(String path) {
		String[] split = StringUtils.split(path, separatorChar);
		StringBuilder builder = new StringBuilder();
		for (int i = split.length - 1; i > 0 && builder.length() < maxLength; i--) {
			builder.insert(0, split[i]);
			builder.insert(0, separatorChar);
		}
		builder.insert(0, "..");
		return builder.toString();
	}
}
