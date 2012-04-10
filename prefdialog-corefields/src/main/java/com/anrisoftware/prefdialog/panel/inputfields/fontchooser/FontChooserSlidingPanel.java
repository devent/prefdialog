package com.anrisoftware.prefdialog.panel.inputfields.fontchooser;

import static com.anrisoftware.prefdialog.panel.inputfields.fontchooser.FontChooserPanel.FONTBOX;
import static com.anrisoftware.prefdialog.panel.inputfields.fontchooser.FontChooserPanel.OPEN_FONT_BUTTON;
import static com.anrisoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox.FontComboBox.getAvailableFontNames;
import static info.clearthought.layout.TableLayoutConstants.FILL;
import static info.clearthought.layout.TableLayoutConstants.PREFERRED;
import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
import com.anrisoftware.swingcomponents.fontchooser.api.FontModelEvent;
import com.anrisoftware.swingcomponents.fontchooser.api.FontModelListener;
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

	private final FontModel fontModel;

	private int minimumFontChooserHeight;

	private boolean updatingFont;

	@Inject
	FontChooserSlidingPanel(SlidingPanelFactory panelFactory,
			FontComboBoxFactory fontComboBoxFactory,
			FontChooserPanelVertical fontChooserPanel,
			FontChooserFactory fontChooserFactory, FontModel model) {
		this.panelFactory = panelFactory;
		this.fontComboBox = fontComboBoxFactory.create(getAvailableFontNames());
		this.openFontChooserButton = new JToggleButton();
		this.fontChooserPanel = fontChooserPanel;
		model.pushFont(Font.decode(null));
		this.fontChooser = fontChooserFactory.create(fontChooserPanel, model);
		this.fontModel = model;
		this.minimumFontChooserHeight = 0;
		this.updatingFont = false;
		setup();
	}

	private void setup() {
		setupPanel();
		setupFontChooserPanel();
		setupSlidingPanel();
		setupOpenFontChooserButton();
		setupFontComboBox();
	}

	private void setupPanel() {
		double[] col = { FILL, PREFERRED };
		double[] row = { PREFERRED, FILL };
		TableLayout layout = new TableLayout(col, row);
		panel = panelFactory.create(layout);
		panel.add(fontComboBox, "0, 0");
		panel.add(openFontChooserButton, "1, 0");
		panel.add(fontChooserPanel, "0, 1, 1, 1");
	}

	private void setupFontChooserPanel() {
		fontChooserPanel.setPreferredSize(new Dimension(128, 512));
		fontModel.addModelListener(new FontModelListener() {

			@Override
			public void fontSet(FontModelEvent e) {
			}

			@Override
			public void fontAdded(FontModelEvent e) {
				updatingFont = true;
				fontComboBox.setSelectedItem(e.getFont().getName());
				updatingFont = false;
			}

			@Override
			public void fontRemoved(FontModelEvent e) {
				fontComboBox.setSelectedItem(e.getFont().getName());
			}
		});
	}

	private void setupSlidingPanel() {
		panel.setContainerRow(1);
		panel.addComponentListener(new ComponentAdapter() {

			private boolean containerSizeSet = false;

			@Override
			public void componentResized(ComponentEvent e) {
				int height;
				height = fontChooserPanel.getHeight();
				height = Math.max(height, minimumFontChooserHeight);
				panel.setContainerSize(height);
				if (!containerSizeSet) {
					containerSizeSet = true;
					panel.setContainerShow(false);
				}
			}

		});
		panel.setContainerSize(fontChooserPanel.getHeight());
		panel.setContainerFinalSize(FILL);
	}

	private void setupOpenFontChooserButton() {
		openFontChooserButton.setText("…");
		openFontChooserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (panel.isContainerShow()) {
					panel.setContainerSize(fontChooserPanel.getHeight());
				}
				boolean show = openFontChooserButton.isSelected();
				panel.setAnimateShow(show);
			}
		});
	}

	private void setupFontComboBox() {
		Dimension size = fontComboBox.getPreferredSize();
		fontComboBox.setPreferredSize(new Dimension(128, size.height));
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
		if (updatingFont) {
			return;
		}
		updatingFont = true;
		Font font = fontModel.getFont();
		int style = font.getStyle();
		int size = font.getSize();
		font = new Font(family, style, size);
		fontModel.pushFont(font);
		updatingFont = false;
	}

	public JPanel getPanel() {
		return panel;
	}

	public Font getFont() {
		return fontModel.getFont();
	}

	public void setFont(Font font) {
		fontModel.setFont(font);
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

	public void setMinimumFontChooserHeight(int height) {
		this.minimumFontChooserHeight = height;
	}

}
