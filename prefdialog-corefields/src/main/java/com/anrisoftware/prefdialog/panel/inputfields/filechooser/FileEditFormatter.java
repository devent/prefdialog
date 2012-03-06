package com.anrisoftware.prefdialog.panel.inputfields.filechooser;

import java.io.File;
import java.text.ParseException;

import javax.swing.text.DefaultFormatter;

@SuppressWarnings("serial")
class FileEditFormatter extends DefaultFormatter {

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
		return path;
	}

	@Override
	public Object stringToValue(String string) throws ParseException {
		return new File(string);
	}
}
