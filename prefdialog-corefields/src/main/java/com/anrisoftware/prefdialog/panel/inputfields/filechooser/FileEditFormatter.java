package com.anrisoftware.prefdialog.panel.inputfields.filechooser;

import java.io.File;
import java.text.ParseException;

import javax.swing.text.DefaultFormatter;

/**
 * Custom edit {@link DefaultFormatter} for a {@link File} bean. It will show
 * the whole path of the file.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
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
