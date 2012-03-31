package com.anrisoftware.prefdialog.panel;

import java.awt.Font;

import com.anrisoftware.prefdialog.annotations.Checkbox;
import com.anrisoftware.prefdialog.annotations.ComboBox;
import com.anrisoftware.prefdialog.annotations.FontChooser;
import com.anrisoftware.prefdialog.panel.api.LookAndFeelFont;

class LookAndFeelFontImpl implements LookAndFeelFont {

	@FontChooser
	private final Font font = new Font("serif", 0, 12);

	@ComboBox(title = "Font size:", model = FontSizeModel.class)
	private final int fontSize = 12;

	@Checkbox(title = "Change the font:", text = "yes/no")
	private final boolean changeFont = false;

	@Override
	public boolean getChangeFont() {
		return changeFont;
	}

	@Override
	public Font getFont() {
		return font;
	}
}
