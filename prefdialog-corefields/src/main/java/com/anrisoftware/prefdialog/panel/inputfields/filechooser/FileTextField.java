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

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;

import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultFormatterFactory;

import com.google.inject.Inject;

/**
 * Custom {@link JFormattedTextField} for a {@link File} field. We use the
 * {@link FontMetrics} in the paint method to calculate the viewable characters
 * and we shorten the path of the file.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
@SuppressWarnings("serial")
class FileTextField extends JFormattedTextField {

	private final FileDisplayFormatter displayFormatter;

	@Inject
	FileTextField(FileDisplayFormatter displayFormatter,
			FileEditFormatter editFormatter,
			FileTextTransferHandler fileTextTransferHandler) {
		this.displayFormatter = displayFormatter;
		setupUpdateOnResizing(displayFormatter);
		setupCaretToLastCharacterOnFocus();
		setFormatterFactory(new DefaultFormatterFactory(displayFormatter,
				displayFormatter, editFormatter));
		setTransferHandler(fileTextTransferHandler);
	}

	private void setupCaretToLastCharacterOnFocus() {
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						setCaretToLastCharacter();
					}

				});
			}
		});
	}

	private void setupUpdateOnResizing(
			final FileDisplayFormatter displayFormatter) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				displayFormatter.updatePathMaxWidth(getWidth());
			}
		});
	}

	private void setCaretToLastCharacter() {
		int length = getDocument().getLength();
		setCaretPosition(length);
	}

	/**
	 * Gets the {@link FontMetrics} to the display formatter so we can calculate
	 * the viewable characters in the text field.
	 */
	@Override
	public void paint(Graphics g) {
		g.setFont(getFont());
		FontMetrics fm = g.getFontMetrics();
		displayFormatter.setFontMetrics(fm, getWidth());
		super.paint(g);
	}

}
