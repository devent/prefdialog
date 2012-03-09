/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-corefields.
 * 
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel.inputfields.filechooser;

import static java.io.File.separatorChar;

import java.awt.FontMetrics;
import java.io.File;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatter;

import org.apache.commons.lang.StringUtils;

/**
 * Custom display {@link DefaultFormatter} for a {@link File} field. It will
 * trim the file path according to the current viewable characters of the
 * {@link JFormattedTextField}. The text field need to set the
 * {@link FontMetrics} by calling the
 * {@link FileDisplayFormatter#setFontMetrics(FontMetrics, int)} method.
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

	/**
	 * Set the new maximum width of the {@link JFormattedTextField}. It will
	 * trim the path with the help of the previously set {@link FontMetrics}.
	 */
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

	/**
	 * Sets the {@link FontMetrics} from the {@link JFormattedTextField}. The
	 * font metrics is used to calculate the currently viewable characters in
	 * the text field.
	 * 
	 * @param fm
	 *            the {@link FontMetrics}.
	 * @param maxWidth
	 * 
	 *            the current width of the {@link JFormattedTextField}.
	 */
	public void setFontMetrics(FontMetrics fm, int maxWidth) {
		if (fontMetrics == null) {
			this.fontMetrics = fm;
			updatePathMaxWidth(maxWidth);
		}
	}
}
