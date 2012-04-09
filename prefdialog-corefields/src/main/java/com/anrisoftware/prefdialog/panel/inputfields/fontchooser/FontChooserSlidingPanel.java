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
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import com.anrisoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox.FontComboBox;
import com.anrisoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox.FontComboBoxFactory;
import com.anrisoftware.swingcomponents.fontchooser.api.FontChooserFactory;
import com.anrisoftware.swingcomponents.fontchooser.api.FontChooserHandler;
import com.anrisoftware.swingcomponents.fontchooser.api.FontModel;
import com.anrisoftware.swingcomponents.fontchooser.panels.FontChooserPanelVertical;
import com.anrisoftware.swingcomponents.slidingpanel.api.SlidingPanel;
import com.anrisoftware.swingcomponents.slidingpanel.api.SlidingPanelFactory;

class FontChooserSlidingPanel {

	private final SlidingPanelFactory panelFactory;

	private final FontComboBox fontComboBox;

	private final JToggleButton openFontChooserButton;

	private final FontChooserPanelVertical fontChooserPanel;

	private final FontChooserHandler fontChooser;

	private SlidingPanel panel;

	private Font font;

	private final FontModel fontModel;

	@Inject
	FontChooserSlidingPanel(SlidingPanelFactory panelFactory,
			FontComboBoxFactory fontComboBoxFactory,
			FontChooserPanelVertical fontChooserPanel,
			FontChooserFactory fontChooserFactory, FontModel model) {
		this.font = new Font("Serif", Font.PLAIN, 12);
		this.panelFactory = panelFactory;
		this.fontComboBox = fontComboBoxFactory.create(getAvailableFontNames());
		this.openFontChooserButton = new JToggleButton();
		this.fontChooserPanel = fontChooserPanel;
		model.pushFont(font);
		this.fontChooser = fontChooserFactory.create(fontChooserPanel, model);
		this.fontModel = model;
		setup();
	}

	private void setup() {
		setupPanel();
		setupSlidingPanel();
		setupOpenFontChooserButton();
		setupFontComboBox();
	}

	private void setupPanel() {
		double[] col = { FILL, PREFERRED };
		double[] row = { PREFERRED, PREFERRED };
		TableLayout layout = new TableLayout(col, row);
		panel = panelFactory.create(layout);
		panel.add(fontComboBox, "0, 0");
		panel.add(openFontChooserButton, "1, 0");
		panel.add(fontChooserPanel, "0, 1, 1, 1");
	}

	private void setupSlidingPanel() {
		panel.setContainerRow(1);
		panel.doLayout();
		panel.setContainerSize(fontChooserPanel.getHeight());
		panel.setContainerFinalSize(PREFERRED);
		panel.setShow(false);
	}

	private void setupOpenFontChooserButton() {
		openFontChooserButton.setText("…");
		openFontChooserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SlidingPanel slidingPanel = panel;
				boolean show = openFontChooserButton.isSelected();
				slidingPanel.setAnimateShow(show);
			}
		});
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

}
