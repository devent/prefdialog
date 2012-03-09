package com.anrisoftware.prefdialog.panel.inputfields.filechooser;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFormattedTextField;
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
		setFormatterFactory(new DefaultFormatterFactory(displayFormatter,
				displayFormatter, editFormatter));
	}

	@Override
	public void paint(Graphics g) {
		g.setFont(new Font("SansSerif", Font.BOLD, 12));
		FontMetrics fm = g.getFontMetrics();
		displayFormatter.setFontMetrics(fm, getWidth());
		super.paint(g);
	}

}
