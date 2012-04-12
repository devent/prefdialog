package com.anrisoftware.prefdialog.panel.inputfields.fontchooser;

import static com.anrisoftware.prefdialog.panel.inputfields.fontchooser.FieldPanel.FONTBOX;
import static com.anrisoftware.prefdialog.panel.inputfields.fontchooser.FieldPanel.OPEN_FONT_BUTTON;
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.inject.Inject;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import com.anrisoftware.swingcomponents.fontchooser.api.FontChooserFactory;
import com.anrisoftware.swingcomponents.fontchooser.api.FontComboBoxFactory;
import com.anrisoftware.swingcomponents.fontchooser.api.FontModel;
import com.anrisoftware.swingcomponents.fontchooser.api.FontModelEvent;
import com.anrisoftware.swingcomponents.fontchooser.api.FontModelListener;
import com.anrisoftware.swingcomponents.fontchooser.api.FontNameItem;
import com.anrisoftware.swingcomponents.fontchooser.api.FontNameItemFactory;
import com.anrisoftware.swingcomponents.fontchooser.panels.FontChooserPanelVertical;
import com.anrisoftware.swingcomponents.slidingpanel.api.SlidingPanel;
import com.anrisoftware.swingcomponents.slidingpanel.api.SlidingPanelFactory;

class Panel {

	private final SlidingPanelFactory panelFactory;

	private final JToggleButton openFontChooserButton;

	private final FontChooserPanelVertical fontChooserPanel;

	private final FontModel fontModel;

	private final JComboBox fontComboBox;

	private final FontNameItemFactory fontNameItemFactory;

	private SlidingPanel panel;

	private int minimumFontChooserHeight;

	private boolean updatingFont;

	private boolean fontChooserContainerSizeSet = false;

	@Inject
	Panel(SlidingPanelFactory panelFactory,
			FontComboBoxFactory fontComboBoxFactory,
			FontChooserPanelVertical fontChooserPanel,
			FontChooserFactory fontChooserFactory, FontModel model,
			FontNameItemFactory fontNameItemFactory) {
		this.panelFactory = panelFactory;
		this.openFontChooserButton = new JToggleButton();
		this.fontChooserPanel = fontChooserPanel;
		fontChooserFactory.create(fontChooserPanel, model);
		this.fontModel = model;
		this.minimumFontChooserHeight = 0;
		this.updatingFont = false;
		this.fontComboBox = new JComboBox();
		fontComboBoxFactory.create(fontComboBox, fontModel);
		this.fontNameItemFactory = fontNameItemFactory;
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
		fontChooserPanel.setApplyButtonVisible(false);
		fontChooserPanel.setPreferredSize(new Dimension(128, 512));
		fontModel.addModelListener(new FontModelListener() {

			@Override
			public void fontSet(FontModelEvent e) {
			}

			@Override
			public void fontAdded(FontModelEvent e) {
				updatingFont = true;
				Object item = fontNameItemFactory.create(e.getFont());
				fontComboBox.setSelectedItem(item);
				updatingFont = false;
			}

			@Override
			public void fontRemoved(FontModelEvent e) {
				Object item = fontNameItemFactory.create(fontModel.getFont());
				fontComboBox.setSelectedItem(item);
			}
		});
	}

	private void setupSlidingPanel() {
		panel.setContainerRow(1);
		panel.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				int height;
				height = fontChooserPanel.getHeight();
				height = Math.max(height, minimumFontChooserHeight);
				panel.setContainerSize(height);
				if (!fontChooserContainerSizeSet) {
					fontChooserContainerSizeSet = true;
					panel.setContainerShow(false);
				}
			}

		});
		panel.addPropertyChangeListener(SlidingPanel.ANIMATING_PROPERTY,
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						setOpenFontChooserButtonEnabled(panel.isAnimating());
					}
				});
		panel.doLayout();
		panel.setContainerSize(fontChooserPanel.getHeight());
		panel.setContainerFinalSize(FILL);
		if (minimumFontChooserHeight > 0) {
			fontChooserContainerSizeSet = true;
			panel.setContainerShow(false);
		}
	}

	protected void setOpenFontChooserButtonEnabled(final boolean animating) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				openFontChooserButton.setEnabled(animating);
			}
		});
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
				setFontChooserPanelVisible(show);
			}

		});
	}

	private void setFontChooserPanelVisible(final boolean show) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				panel.setAnimateShow(show);
			}
		});
	}

	private void setupFontComboBox() {
		fontComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				FontNameItem item = (FontNameItem) fontComboBox
						.getSelectedItem();
				updateFontFamily(item);
			}

		});
	}

	private void updateFontFamily(FontNameItem item) {
		if (updatingFont) {
			return;
		}
		updatingFont = true;
		Font font = fontModel.getFont();
		String family = item.getName();
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
