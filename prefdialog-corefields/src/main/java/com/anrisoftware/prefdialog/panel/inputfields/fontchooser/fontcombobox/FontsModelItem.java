package com.anrisoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class FontsModelItem {

	private final Font font;

	private final JPanel panel;

	private final JLabel fontNameLabel;

	private final JLabel fontPreviewLabel;

	private String previewTemplate;

	public FontsModelItem(String name) {
		this.panel = new JPanel();
		this.font = Font.decode(name);
		this.fontNameLabel = new JLabel("Font name");
		this.fontPreviewLabel = new JLabel("Font preview");
		this.previewTemplate = "AaBbCc";
		setup();
	}

	private void setup() {
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.setOpaque(true);
		fontNameLabel.setOpaque(false);
		fontPreviewLabel.setOpaque(false);
		panel.add(fontNameLabel);
		panel.add(fontPreviewLabel);
		setupCurrentFont();
	}

	private void setupCurrentFont() {
		fontNameLabel.setText(font.getName());
		String preview = createPreviewFromOnlySupportedCharacters(font);
		fontPreviewLabel.setText(preview);
		fontPreviewLabel.setFont(font);
	}

	private String createPreviewFromOnlySupportedCharacters(Font font) {
		StringBuilder preview = new StringBuilder();
		for (int i = 0; i < previewTemplate.length(); i++) {
			char c = previewTemplate.charAt(i);
			if (font.canDisplay(c))
				preview.append(c);
		}
		return preview.toString();
	}

	public Font getFont() {
		return font;
	}

	JPanel getPanel() {
		return panel;
	}

	void setSelected(JList list, boolean isSelected) {
		if (isSelected) {
			panel.setBackground(list.getSelectionBackground());
			panel.setForeground(list.getSelectionForeground());
		} else {
			panel.setBackground(list.getBackground());
			panel.setForeground(list.getForeground());
		}
	}

	void setPreviewTemplate(String template) {
		this.previewTemplate = template;
	}

	@Override
	public String toString() {
		return font.getName();
	}

}
