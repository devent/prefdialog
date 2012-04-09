package com.anrisoftware.prefdialog.panel.inputfields.fontchooser;

import static com.anrisoftware.prefdialog.panel.inputfields.fontchooser.FontChooserPanel.FONTBOX;
import static com.anrisoftware.prefdialog.panel.inputfields.fontchooser.FontChooserPanel.OPEN_FONT_BUTTON;
import static com.anrisoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox.FontComboBox.getAvailableFontNames;
import static info.clearthought.layout.TableLayoutConstants.FILL;
import static info.clearthought.layout.TableLayoutConstants.PREFERRED;
import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.inject.Inject;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox.FontComboBox;
import com.anrisoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox.FontComboBoxFactory;
import com.anrisoftware.swingcomponents.slidingpanel.api.SlidingPanelFactory;

class FontChooserSlidingPanel {

	private final SlidingPanelFactory panelFactory;

	private final FontComboBox fontComboBox;

	private final JButton openFontChooserButton;

	private JPanel panel;

	private Font font;

	@Inject
	FontChooserSlidingPanel(SlidingPanelFactory panelFactory,
			FontComboBoxFactory fontComboBoxFactory) {
		this.panelFactory = panelFactory;
		this.fontComboBox = fontComboBoxFactory.create(getAvailableFontNames());
		this.openFontChooserButton = new JButton();
		setup();
	}

	private void setup() {
		setupPanel();
		setupOpenFontChooserButton();
		setupFontComboBox();
	}

	private void setupPanel() {
		double[] col = { FILL, PREFERRED };
		double[] row = { PREFERRED };
		TableLayout layout = new TableLayout(col, row);
		panel = panelFactory.create(layout);
		panel.add(fontComboBox, "0, 0");
		panel.add(openFontChooserButton, "1, 0");
	}

	private void setupOpenFontChooserButton() {
		openFontChooserButton.setText("…");
	}

	private void setupFontComboBox() {
		fontComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				Object object = fontComboBox.getSelectedItem();
				if (object instanceof String) {
					updateFontFamily((String) object);
				}
			}

		});
	}

	private void updateFontFamily(String family) {
		int style = font.getStyle();
		int size = font.getSize();
		font = new Font(family, style, size);
	}

	public JPanel getPanel() {
		return panel;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
		fontComboBox.setSelectedItem(font.getName());
	}

	public void setName(String name) {
		fontComboBox.setName(format("%s-%s", FONTBOX, name));
		openFontChooserButton.setName(format("%s-%s", OPEN_FONT_BUTTON, name));
	}

	public void setEnabled(boolean enabled) {
		fontComboBox.setEnabled(enabled);
		openFontChooserButton.setEnabled(enabled);
	}

	public void setOpenFileAction(final Runnable runnable) {
		openFontChooserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				runnable.run();
			}
		});
	}
}
