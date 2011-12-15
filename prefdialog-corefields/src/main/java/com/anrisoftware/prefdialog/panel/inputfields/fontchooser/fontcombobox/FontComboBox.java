package com.anrisoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

/**
 * A combo box that preview the fonts.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
@SuppressWarnings("serial")
public class FontComboBox extends JComboBox {

	private static final int DEFAULT_COMBOBOX_HEIGHT = new JComboBox()
			.getPreferredSize().height;

	public static String[] getAvailableFontNames() {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String[] fontNames = ge.getAvailableFontFamilyNames();
		Arrays.sort(fontNames);
		return fontNames;
	}

	@Inject
	FontComboBox(@Named("FontComboBoxRenderer") ListCellRenderer renderer,
			FontComboBoxEditorFactory editorFactory,
			@Assisted String[] fontNames) {
		super(new FontsModel(fontNames));
		setRenderer(renderer);
		setEditor(editorFactory.create(getModel()));
		setEditable(true);
	}

	@Override
	public Dimension getPreferredSize() {
		int width = super.getPreferredSize().width;
		return new Dimension(width, DEFAULT_COMBOBOX_HEIGHT);
	}

}
