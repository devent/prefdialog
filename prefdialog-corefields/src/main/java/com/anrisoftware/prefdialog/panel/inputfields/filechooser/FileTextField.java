package com.anrisoftware.prefdialog.panel.inputfields.filechooser;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultFormatterFactory;

import com.google.inject.Inject;

@SuppressWarnings("serial")
public class FileTextField extends JFormattedTextField {

	private final FileDisplayFormatter displayFormatter;

	@Inject
	FileTextField(final FileDisplayFormatter displayFormatter,
			FileEditFormatter editFormatter) {
		this.displayFormatter = displayFormatter;
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				displayFormatter.updatePathMaxWidth(getWidth());
			}
		});
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						int length = getDocument().getLength();
						setCaretPosition(length);
					}
				});
			}
		});
		setFormatterFactory(new DefaultFormatterFactory(displayFormatter,
				displayFormatter, editFormatter));
	}

	@Override
	public void paint(Graphics g) {
		g.setFont(getFont());
		FontMetrics fm = g.getFontMetrics();
		displayFormatter.setFontMetrics(fm, getWidth());
		super.paint(g);
	}

}
