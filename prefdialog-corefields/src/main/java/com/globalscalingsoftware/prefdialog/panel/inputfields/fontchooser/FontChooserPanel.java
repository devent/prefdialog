/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.panel.inputfields.fontchooser;

import static java.lang.String.format;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;

import com.globalscalingsoftware.prefdialog.panel.inputfields.fontchooser.FontChooserComboBox.Item;
import com.globalscalingsoftware.prefdialog.swingutils.AbstractLabelFieldPanel;
import com.google.inject.Inject;

/**
 * Sets a {@link FontChooserComboBox} with a {@link JButton}. the combo box will
 * show the current selected font and the button can open a font chooser dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class FontChooserPanel extends AbstractLabelFieldPanel<UiFontChooserPanel> {

	private Font font;

	private String title;

	/**
	 * Set the {@link UiFontChooserPanel}.
	 */
	@Inject
	FontChooserPanel(UiFontChooserPanel panel) {
		super(panel);
		setup();
	}

	private void setup() {
		getPanelField().getFontBox().addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				Item item = (FontChooserComboBox.Item) e.getItem();
				font = item.getItemFont();
			}
		});
	}

	/**
	 * Set a {@link Runnable} that is called if the user clicks on the open file
	 * button.
	 */
	public void setOpenFileAction(final Runnable runnable) {
		getPanelField().getOpenFontChooserButton().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						runnable.run();
					}
				});
	}

	@Override
	public Object getValue() {
		return font;
	}

	@Override
	public void setValue(Object value) {
		if (value instanceof Font) {
			font = (Font) value;
			getPanelField().getFontBox().setSelectedItem(font.getName());
		}
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		getPanelField().getFontBox().setName(format("fontbox-%s", name));
		getPanelField().getOpenFontChooserButton().setName(
				format("openfontbutton-%s", name));
	}

	@Override
	public void setEnabled(boolean enabled) {
		getPanelField().getFontBox().setEnabled(enabled);
		getPanelField().getOpenFontChooserButton().setEnabled(enabled);
	}

	@Override
	public boolean isInputValid() {
		return true;
	}

	@Override
	public void setTitle(String title) {
		super.setTitle(title);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
