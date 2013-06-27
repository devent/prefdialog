/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org> This file is part of
 * prefdialog-corefields. prefdialog-corefields is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version. prefdialog-corefields
 * is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You
 * should have received a copy of the GNU Lesser General Public License along
 * with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.text.filetext;

import static javax.swing.SwingUtilities.invokeLater;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Custom text field for a file. The {@link FontMetrics} in the paint method is
 * used to calculate the viewable characters for the file path.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class FileTextField extends JFormattedTextField {

	private static final Injector INJECTOR = Guice.createInjector();

	/**
	 * @see #create()
	 */
	public static JFormattedTextField createFileTextField() {
		return create();
	}

	/**
	 * @see #create(File)
	 */
	public static JFormattedTextField createFileTextField(File file) {
		return create(file);
	}

	/**
	 * @see #create(File)
	 */
	public static JFormattedTextField create() {
		return create(new File(""));
	}

	/**
	 * Creates the file text field.
	 * 
	 * @param file
	 *            the initial {@link File}.
	 * @return the file text field {@link JFormattedTextField}.
	 */
	public static JFormattedTextField create(File file) {
		FileTextField field = INJECTOR.getInstance(FileTextField.class);
		field.setValue(file);
		return field;
	}

	private final FileDisplayFormatter displayFormatter;

	private final FocusAdapter focusListener;

	private final ComponentAdapter componentListener;

	private final FileEditFormatter editFormatter;

	private final FileTextTransferHandler fileTextTransferHandler;

	@Inject
	FileTextField(FileDisplayFormatter displayFormatter,
			FileEditFormatter editFormatter,
			FileTextTransferHandler fileTextTransferHandler) {
		super(new File(""));
		this.displayFormatter = displayFormatter;
		this.editFormatter = editFormatter;
		this.fileTextTransferHandler = fileTextTransferHandler;
		this.focusListener = new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				updateCaretToLastCharacterOnFocus();
			}

		};
		this.componentListener = new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				updatePathMaxWidth();
			}

		};
		setupField();
	}

	private void setupField() {
		addFocusListener(focusListener);
		addComponentListener(componentListener);
		setFormatterFactory(new DefaultFormatterFactory(displayFormatter,
				displayFormatter, editFormatter));
		setTransferHandler(fileTextTransferHandler);
		setPreferredSize(new Dimension(50, getHeight()));
	}

	private void updatePathMaxWidth() {
		displayFormatter.updatePathMaxWidth(getWidth());
	}

	private void updateCaretToLastCharacterOnFocus() {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				setCaretToLastCharacter();
			}

		});
	}

	private void setCaretToLastCharacter() {
		int length = getDocument().getLength();
		setCaretPosition(length);
	}

	/**
	 * Sets the {@link FontMetrics} to the display formatter so it can calculate
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
