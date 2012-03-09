package com.anrisoftware.prefdialog.panel.inputfields.filechooser;

import static java.io.File.separatorChar;

import java.awt.FontMetrics;
import java.io.File;
import java.text.ParseException;

import javax.swing.text.DefaultFormatter;

import org.apache.commons.lang.StringUtils;

/**
 * Custom display {@link DefaultFormatter} for a {@link File} bean. It will trim
 * directories so that we can see at most a maximum length of the file path.
 * Useful so the user can see the selected file.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
@SuppressWarnings("serial")
class FileDisplayFormatter extends DefaultFormatter {

	private String absolutePath;

	private String text;

	private int maxWidth;

	private FontMetrics fontMetrics;

	private String oldAbsolutePath;

	private String[] splitAbsolutePath;

	@Override
	public boolean getOverwriteMode() {
		return false;
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value == null) {
			return "";
		}
		absolutePath = asAbsolutePath((File) value);
		splitAbsolutePath = StringUtils.split(absolutePath, separatorChar);
		updatePathMaxWidth(getFormattedTextField().getWidth());
		return text;
	}

	private String asAbsolutePath(File file) {
		return file.getAbsolutePath();
	}

	public void updatePathMaxWidth(int newMaxWidth) {
		if (fontMetrics == null || splitAbsolutePath == null) {
			return;
		}
		newMaxWidth = newMaxWidth - 100;
		if (maxWidth == newMaxWidth && oldAbsolutePath == absolutePath) {
			return;
		}
		oldAbsolutePath = absolutePath;
		maxWidth = newMaxWidth;
		text = createShortPath();
		getFormattedTextField().setValue(getFormattedTextField().getValue());
	}

	private String createShortPath() {
		StringBuilder builder = new StringBuilder();
		int i;
		for (i = splitAbsolutePath.length - 1; i >= 0 && isInMaxWidth(builder); i--) {
			builder.insert(0, splitAbsolutePath[i]);
			builder.insert(0, separatorChar);
		}
		if (i > 0) {
			builder.insert(0, "..");
		}
		return builder.toString();
	}

	private boolean isInMaxWidth(StringBuilder builder) {
		return pixelsWidth(builder.toString()) < maxWidth;
	}

	private int pixelsWidth(String string) {
		return fontMetrics.stringWidth(string);
	}

	public void setFontMetrics(FontMetrics fm, int maxWidth) {
		if (fontMetrics == null) {
			this.fontMetrics = fm;
			updatePathMaxWidth(maxWidth);
		}
	}
}
